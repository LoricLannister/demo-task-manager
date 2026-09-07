package com.example.demo.implementation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;

public class TaskMapperImpl implements TaskMapper {

    private final SqlSession sqlSession;
    private static final String NAMESPACE = "com.example.demo.mapper.TaskMapper.";

    public TaskMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Task selectById(Long id) {
        return sqlSession.selectOne(NAMESPACE + "selectById", id);
    }

    @Override
    public List<Task> getAllDatabase() {
        return sqlSession.selectList(NAMESPACE + "getAllDatabase");
    }

    @Override
    public int insertionInDatabase(Task task) {
        // sqlSession.insert return the number of rows inserted (int)
        return sqlSession.insert(NAMESPACE + "insertionInDatabase", task);
    }

    @Override
    public int updateInDatabase(Task task) {
        // sqlSession.update return the number of rows modified (int)
        return sqlSession.update(NAMESPACE + "updateInDatabase", task);
    }

    @Override
    public int deleteFromDatabase(Long id) {
        // sqlSession.delete return the number of rows deleted (int)
        return sqlSession.delete(NAMESPACE + "deleteFromDatabase", id);
    }
}
