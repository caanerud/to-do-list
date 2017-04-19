package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

/**
 * Created by chrisaanerud on 4/18/17.
 */
@SuppressWarnings("ALL")
@Component
public class ListRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Object listTasks(String search) {
        return null;
    }
}
