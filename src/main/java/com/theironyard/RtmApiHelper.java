package com.theironyard;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class RtmApiHelper {
    private static String API_KEY;
    private static String SHARED_SECRET;
    private static String AUTH_TOKEN;
    private static String frob       = "f7cbd73cab912fd18db4c4c57669dd37951461f0";

    static {
        try {
            Properties properties = new Properties();
            InputStream in = "".getClass().getResourceAsStream("/application-default.properties");
            properties.load(in);
            in.close();

            API_KEY       = properties.getProperty("API_KEY");
            SHARED_SECRET = properties.getProperty("SHARED_SECRET");
            AUTH_TOKEN    = properties.getProperty("AUTH_TOKEN");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }



    // RememberTheMilk API call, returns a Map

    public static Map<String, String> withApiSig(Map<String, String> params) throws NoSuchAlgorithmException {
        // Start out the StringBuffer with the shared secret...
        StringBuffer buffer = new StringBuffer(SHARED_SECRET);

        // Create a new TreeMap
        Map<String, String> sortedMap = new TreeMap<String, String>(params);

        //add each key and value to the buffer
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            buffer.append(entry.getKey());
            buffer.append(entry.getValue());
        }

        String api_sig = md5(buffer.toString());
        sortedMap.put("api_sig", api_sig);
        return sortedMap;
    }

    public static String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(input.getBytes(),0, input.length());
        String md5Str = new BigInteger(1,m.digest()).toString(16);

        return md5Str;
    }

    public static String signedUrl(String baseUrl, Map<String, String> params) throws NoSuchAlgorithmException {
        // Start with the baseUrl
        StringBuffer buffer = new StringBuffer(baseUrl);

        boolean first = true;

        for (Map.Entry<String, String> entry : withApiSig(params).entrySet()) {
            if (first) {
                buffer.append("?");
                first = false;
            } else {
                buffer.append("&");
            }

            buffer.append(entry.getKey());
            buffer.append("=");
            buffer.append(entry.getValue());
        }

        return buffer.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String authHost = "http://www.rememberthemilk.com/services/auth/";
        String restHost = "http://api.rememberthemilk.com/services/rest/";

        if (AUTH_TOKEN == null) {

            if (frob == null) {

                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key", API_KEY);
                params.put("perms", "delete");

                System.out.println(signedUrl(authHost, params));
            } else {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api_key", API_KEY);
                params.put("format", "json");
                params.put("method", "rtm.auth.getToken");
                params.put("frob", frob);

                System.out.println(signedUrl(restHost, params));
            }
        } else {
            {
                Map<String, String> params = new HashMap<String, String>();

                params.clear();
                params.put("api_key", API_KEY);
                params.put("auth_token", AUTH_TOKEN);
                params.put("format","json");
                params.put("method", "rtm.auth.checkToken");

                System.out.println("rtm.auth.checkToken:");
                System.out.println(signedUrl(restHost, params));
                System.out.println("");

                params.clear();
                params.put("api_key", API_KEY);
                params.put("auth_token", AUTH_TOKEN);
                params.put("format","json");
                params.put("method", "rtm.lists.getList");

                System.out.println("rtm.lists.getList:");
                System.out.println(signedUrl(restHost, params));
                System.out.println("");
            }
        }
    }
}