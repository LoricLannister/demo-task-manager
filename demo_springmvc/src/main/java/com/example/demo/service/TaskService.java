package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    public Task save(Task task) {
        taskMapper.insert(task);
        return task;
    }

    public Task update(Task task) {
        taskMapper.update(task);
        return task;
    }

    public void delete(Long id) {
        taskMapper.delete(id);
    }
}
