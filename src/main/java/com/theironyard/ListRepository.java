package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Created by chrisaanerud on 4/18/17.
 */
@SuppressWarnings("ALL")
@Component
public class ListRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ToDoList> listTasks(String search) {
        return jdbcTemplate.query(
                "SELECT * FROM \"Tasks\" WHERE lower(taskname) LIKE lower(?) " +
                        " OR lower(details) LIKE lower(?) ORDER BY completed",
                      //  " OR priority = ?" +
                       // " OR duedate = ?" +
                      //  " OR completed = ?",
                new Object[]{"%" + search + "%", "%" + search + "%"},
                (resultSet, i) -> new ToDoList(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("completed"),
                        resultSet.getDate("duedate"),
                        resultSet.getInt("priority"),
                        resultSet.getString("taskname"),
                        resultSet.getString("details"),
                        resultSet.getInt("user")

                )
        ) ;

    }

    public ToDoList getTask(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM \"Tasks\" WHERE id =?",
                new Object[]{id},
                (resultSet, i) -> new ToDoList(
                        resultSet.getInt("id"),
                        resultSet.getBoolean("completed"),
                        resultSet.getDate("duedate"),
                        resultSet.getInt("priority"),
                        resultSet.getString("taskname"),
                        resultSet.getString("details"),
                        resultSet.getInt("user")

                )
        ) ;
    }

    public ToDoList saveTask(ToDoList task) {
        if (task.getId() == null) {

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(con -> {
                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO \"Tasks\"(taskname, details, priority, duedate, completed) " +
                                        "VALUES (?, ?, ?, ?, ?)",
                                Statement.RETURN_GENERATED_KEYS);

                        ps.setString(1, task.getTaskName());
                        ps.setString(2, task.getDetails());
                        ps.setInt(3, task.getPriority());
                        ps.setDate(4, (Date) task.getDueDate());
                        ps.setBoolean(5, task.getCompleted());

                        return ps;
                    },
                    keyHolder);


            task.setId((Integer) keyHolder.getKeys().get("id"));

        } else {

            jdbcTemplate.update(
                    "UPDATE \"Tasks\" " +
                            "SET " +
                            "taskname = ?, " +
                            "details = ?, " +
                            "priority = ?, " +
                            "duedate = ?, " +
                            "completed = ? " +
                            "WHERE id = ?",
                    new Object[]{
                            task.getTaskName(),
                            task.getDetails(),
                            task.getPriority(),
                            task.getDueDate(),
                            task.getCompleted(),
                            task.getId()
                    }
            );
        }


        return task;

    }
}
