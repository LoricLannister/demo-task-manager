package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.config.MyBatisConfig;
import com.example.demo.model.Task;

public class TaskMapperOracleTest {

    private static AnnotationConfigApplicationContext context;

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSession sqlSession;

    private static TaskMapper taskMapper;

    private static Long testTaskId;

    private static final String TEST_TITLE
            = "TEST_MYBATIS_FIND";

    // Before everything, we need to set up the Spring context and get the SqlSessionFactory and TaskMapper
    @BeforeClass
    public static void setUpClass() throws Exception {

        context = new AnnotationConfigApplicationContext(
                MyBatisConfig.class
        );

        sqlSessionFactory
                = context.getBean(SqlSessionFactory.class);

        sqlSession
                = sqlSessionFactory.openSession();

        taskMapper
                = sqlSession.getMapper(TaskMapper.class);

        // Creation of a known task for the test
        Task task = new Task();

        task.setTitle(TEST_TITLE);
        task.setCompleted(false);

        taskMapper.insert(task);

        sqlSession.commit();

        testTaskId = task.getId();
    }

    // After everything, we need to clean up the test data and close the SqlSession and Spring context
    @AfterClass
    public static void tearDownClass() {

        if (taskMapper != null && testTaskId != null) {

            taskMapper.delete(testTaskId);

            sqlSession.commit();
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        if (context != null) {
            context.close();
        }
    }

    @Test
    public void shouldFindInsertedTask() {

        List<Task> tasks = taskMapper.findAll();

        assertNotNull(tasks);

        Task foundTask = null;

        for (Task task : tasks) {

            if (TEST_TITLE.equals(task.getTitle())) {
                foundTask = task;
                break;
            }
        }

        assertNotNull(
                "La tâche de test n'a pas été trouvée",
                foundTask
        );

        assertEquals(
                TEST_TITLE,
                foundTask.getTitle()
        );

        assertFalse(
                foundTask.isCompleted()
        );
    }

    @Test
    public void shouldUpdateTask() {

        Task task = new Task();

        task.setTitle("TEST_MYBATIS_UPDATE");
        task.setCompleted(false);

        taskMapper.insert(task);
        sqlSession.commit();

        Long id = task.getId();

        assertNotNull(id);

        // Modification
        task.setTitle("TEST_MYBATIS_UPDATE_MODIFIED");
        task.setCompleted(true);

        int rowsUpdated = taskMapper.update(task);

        sqlSession.commit();

        assertEquals(1, rowsUpdated);

        // On relit depuis Oracle
        List<Task> tasks = taskMapper.findAll();

        Task updatedTask = null;

        for (Task currentTask : tasks) {

            if (id.equals(currentTask.getId())) {
                updatedTask = currentTask;
                break;
            }
        }

        assertNotNull(
                "La tâche modifiée n'a pas été retrouvée",
                updatedTask
        );

        assertEquals(
                "TEST_MYBATIS_UPDATE_MODIFIED",
                updatedTask.getTitle()
        );

        assertTrue(
                updatedTask.isCompleted()
        );

        // Nettoyage
        taskMapper.delete(id);
        sqlSession.commit();
    }

    @Test
    public void shouldDeleteTask() {

        // Création d'une tâche dédiée au test
        Task task = new Task();

        task.setTitle("TEST_MYBATIS_DELETE");
        task.setCompleted(false);

        taskMapper.insert(task);
        sqlSession.commit();

        Long id = task.getId();

        assertNotNull(id);

        // Suppression
        int rowsDeleted = taskMapper.delete(id);

        sqlSession.commit();

        assertEquals(1, rowsDeleted);

        // Vérification : la tâche ne doit plus exister
        List<Task> tasks = taskMapper.findAll();

        for (Task currentTask : tasks) {

            assertFalse(
                    "La tâche supprimée existe encore",
                    id.equals(currentTask.getId())
            );
        }
    }
}
