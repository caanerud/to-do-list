package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chrisaanerud on 4/18/17.
 */
@SuppressWarnings("ALL")
@Controller
public class ListController {
    @Autowired
    ListRepository listRepository;

    @GetMapping("/")
    public String listTasks(Model model, @RequestParam(defaultValue = "") String search) {
        model.addAttribute("search", search);
        model.addAttribute("tasks", listRepository.listTasks(search));
        return "index";
    }

    @GetMapping("/listForm")
    public String listForm(Model model, Integer id) {
        if (id == null) {
            model.addAttribute("task", new ToDoList());
        } else {
            model.addAttribute("task", listRepository.getTask(id));
        }
        return "listForm";
    }

    @PostMapping("/saveTask")
    public String saveTask(ToDoList task){
        listRepository.saveTask(task);
        return "redirect:/";
    }
}
