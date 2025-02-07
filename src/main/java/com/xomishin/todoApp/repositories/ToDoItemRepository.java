package com.xomishin.todoApp.repositories;

import com.xomishin.todoApp.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<ToDoItem,Long> {

}
