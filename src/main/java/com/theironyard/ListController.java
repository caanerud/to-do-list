package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
