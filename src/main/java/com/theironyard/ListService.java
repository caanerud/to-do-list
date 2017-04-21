package com.theironyard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by chrisaanerud on 4/20/17.
 */
@Component
public class ListService {
    @Value("${milk.api.key}")
    String apiKey;
    @Value("${milk.api.url}")
    String apiUrl;

}
