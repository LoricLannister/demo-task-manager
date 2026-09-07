package com.example.demo.mapper;

import java.util.List;

import com.example.demo.model.Task;

public interface TaskMapper {

    Task selectById(Long id);

    List<Task> getAllDatabase();

    int insertionInDatabase(Task task);

    int updateInDatabase(Task task);

    int deleteFromDatabase(Long id);
}
