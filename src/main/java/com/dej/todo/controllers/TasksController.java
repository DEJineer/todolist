package com.dej.todo.controllers;

import com.dej.todo.models.Task;
import com.dej.todo.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> list(){
        return taskRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Task get(@PathVariable Long id){
        return taskRepository.getOne(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        return taskRepository.saveAndFlush(task);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable Long id, @RequestBody Task task){
        Task existingTask = taskRepository.getOne(id);
        BeanUtils.copyProperties(task, existingTask, "task_id");
        return taskRepository.saveAndFlush(existingTask);
    }
}
