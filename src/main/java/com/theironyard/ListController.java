package com.theironyard;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;
import java.sql.SQLException;

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

//        @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/results").setViewName("results");

    @GetMapping("/listForm")
    public String listForm(Model model, Integer taskId) {
        if (taskId == null) {
            model.addAttribute("task", new ToDoList());
        } else {
            model.addAttribute("task", listRepository.getTask(taskId));
        }
        return "listForm";
    }

    @PostMapping("/saveTask")
    public String saveTask(@Valid @ModelAttribute("task") ToDoList task, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("task", task);
            return "listForm";
        }
        listRepository.saveTask(task);
        return "redirect:/";
    }
    @GetMapping("/deletetask")
    public String deleteTask(Model model,Integer id) throws SQLException {
        System.out.println(id);
        listRepository.deleteTask(id);

//        model.addAttribute("id", listRepository.deleteTask(id));
        return "redirect:/";
    }
    @Autowired
    public ListService listService;

    @RequestMapping(path = "listForm")
    public String listToDoLists(ModelMap model) {

        model.addAttribute("toDoLists", listService.listToDoLists());

        return "listToDoLists";
    }

    @PostMapping("/createList")
    public String createToDoList(String name) {
        listService.createToDoList(name);
        return "redirect:/";
    }
}