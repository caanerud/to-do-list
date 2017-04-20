package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chrisaanerud on 4/19/17.
 */
@RestController
public class RestTaskController {
    @Autowired
    ListRepository listRepository;
    @GetMapping("/api/tasks")
    public List<ToDoList> listTasks(@RequestParam(defaultValue = "") String search) {
        return listRepository.listTasks(search);

    }
    @GetMapping("/api/task/{id}")
    public ToDoList getTask(@PathVariable("id") Integer id){
        return listRepository.getTask(id);
    }

    @PostMapping("/api/task")
    public ToDoList saveTask(@RequestBody ToDoList task){
        return listRepository.saveTask(task);

    }
}
