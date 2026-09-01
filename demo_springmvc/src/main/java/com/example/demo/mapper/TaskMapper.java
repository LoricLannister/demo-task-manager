package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Task;

public interface TaskMapper {

    @Select("SELECT ID, TITLE, COMPLETED FROM TASK ORDER BY ID")
    List<Task> findAll();

    int insert(Task task);

    int update(Task task);

    int delete(Long id);
}
