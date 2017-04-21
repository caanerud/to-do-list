package com.theironyard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chrisaanerud on 4/20/17.
 */
@Component
public class ListService {
    @Value("${milk.api.key}")
    String apiKey;
    @Value("${milk.api.url}")
    String apiUrl;


        public List<RememberMilkLists> listToDoLists(){
            RestTemplate restTemplate = new RestTemplate();
            RememberMilkLists[] toDoLists = restTemplate.getForObject(
                    apiUrl + "rtm.locations.getList" + this.apiKey,
                    RememberMilkLists[].class);
            return Arrays.asList(toDoLists);

        }

    public RememberMilkLists createToDoList(String name){

        RememberMilkLists toDoList = new RememberMilkLists();
        toDoList.setName(name);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(apiUrl + "rtm.locations.getList"
                + this.apiKey, toDoList, RememberMilkLists.class);
    }

}
