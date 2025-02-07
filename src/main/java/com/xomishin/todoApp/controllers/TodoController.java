package com.xomishin.todoApp.controllers;

import com.xomishin.todoApp.model.ToDoItem;
import com.xomishin.todoApp.repositories.ToDoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TodoController implements CommandLineRunner {

    private final ToDoItemRepository toDoItemRepository;

    public TodoController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping
    public String index(Model model) {

        List<ToDoItem> allTodos = toDoItemRepository.findAll();
        model.addAttribute("allTodos",allTodos);
        return "index";
    }

    @Override
    public void run(String... args) throws Exception {
        toDoItemRepository.save(new ToDoItem("Уборка"));
        toDoItemRepository.save(new ToDoItem("Прогулка"));
    }
}
