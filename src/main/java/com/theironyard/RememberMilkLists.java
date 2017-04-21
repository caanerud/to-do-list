package com.theironyard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by chrisaanerud on 4/20/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RememberMilkLists {
    private String name;

    public RememberMilkLists(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RememberMilkLists(){

    }
}
