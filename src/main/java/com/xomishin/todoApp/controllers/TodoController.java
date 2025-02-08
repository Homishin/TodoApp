package com.xomishin.todoApp.controllers;

import com.xomishin.todoApp.model.ToDoItem;
import com.xomishin.todoApp.repositories.ToDoItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        model.addAttribute("newTodo", new ToDoItem());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ToDoItem toDoItem){
        toDoItemRepository.save(toDoItem);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id){
        toDoItemRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/removeAll")
    public String revomeAllItems(){
        toDoItemRepository.deleteAll();

        return "redirect:/";
    }

    @PostMapping("/search")
    public String serchTodoItem(@RequestParam("serchTerm") String serchTerm, Model model) {
        List<ToDoItem> allTodo = toDoItemRepository.findAll();
        List<ToDoItem> serchResult = new ArrayList<>();

        for(ToDoItem item : allTodo) {
            if (item.getTitle().toLowerCase().contains(serchTerm.toLowerCase())) {
                serchResult.add(item);
            }
        }

        model.addAttribute("allTodos",serchResult);
        model.addAttribute("newTodo", new ToDoItem());
        model.addAttribute("serchTerm", serchTerm);
        return "index";

    }

    @Override
    public void run(String... args) throws Exception {
        toDoItemRepository.save(new ToDoItem("Уборка"));
        toDoItemRepository.save(new ToDoItem("Прогулка"));
    }
}
