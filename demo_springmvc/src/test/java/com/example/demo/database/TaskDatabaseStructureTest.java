package com.example.demo.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.config.MyBatisConfig;

public class TaskDatabaseStructureTest {

    private static Connection connection;
    private static AnnotationConfigApplicationContext context;

    // Before everything, we need to set up the Spring context and get the connection to the database
    @BeforeClass
    public static void setUpClass() throws Exception {

        context = new AnnotationConfigApplicationContext(
                MyBatisConfig.class
        );

        DataSource dataSource
                = context.getBean(DataSource.class);

        connection = dataSource.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

        if (connection != null) {
            connection.close();
        }

        if (context != null) {
            context.close();
        }
    }

    // We need to check if the TASK table has the expected columns with the expected types and constraints
    @Test
    public void shouldHaveExpectedTaskColumns() throws Exception {

        // We need to get the metadata of the database to check the structure of the TASK table
        DatabaseMetaData metaData = connection.getMetaData();

        boolean idFound;
        boolean titleFound;
        boolean completedFound;
        // We guarantee that the result set is closed after we are done with it, to avoid resource leaks
        try (ResultSet columns = metaData.getColumns(
                null,
                "DEMO_APP",
                "TASK",
                null
        )) {
            idFound = false;
            titleFound = false;
            completedFound = false;
            // We iterate over the columns of the TASK table to check if they match our expectations
            while (columns.next()) {

                String columnName = columns.getString("COLUMN_NAME");

                switch (columnName) {

                    case "ID":

                        idFound = true;

                        assertEquals(
                                "NUMBER",
                                columns.getString("TYPE_NAME")
                        );

                        assertEquals(
                                19,
                                columns.getInt("COLUMN_SIZE")
                        );

                        assertEquals(
                                "NO",
                                columns.getString("IS_NULLABLE")
                        );

                        break;

                    case "TITLE":

                        titleFound = true;

                        assertEquals(
                                "VARCHAR2",
                                columns.getString("TYPE_NAME")
                        );

                        assertEquals(
                                255,
                                columns.getInt("COLUMN_SIZE")
                        );

                        assertEquals(
                                "NO",
                                columns.getString("IS_NULLABLE")
                        );

                        break;

                    case "COMPLETED":

                        completedFound = true;

                        assertEquals(
                                "NUMBER",
                                columns.getString("TYPE_NAME")
                        );

                        assertEquals(
                                1,
                                columns.getInt("COLUMN_SIZE")
                        );

                        assertEquals(
                                "NO",
                                columns.getString("IS_NULLABLE")
                        );

                        break;
                }
            }
        }

        assertTrue(
                "La colonne ID est absente",
                idFound
        );

        assertTrue(
                "La colonne TITLE est absente",
                titleFound
        );

        assertTrue(
                "La colonne COMPLETED est absente",
                completedFound
        );
    }

    // We need to check if the COMPLETED column has a default value of 0
    @Test
    public void shouldHaveDefaultValueZeroForCompleted() throws Exception {

        String sql
                = "SELECT DATA_DEFAULT "
                + "FROM USER_TAB_COLUMNS "
                + "WHERE TABLE_NAME = 'TASK' "
                + "AND COLUMN_NAME = 'COMPLETED'";
        // We guarantee that the prepared statement and the result set are closed after we are done with them, to avoid resource leaks
        try (java.sql.PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

            assertTrue(
                    "La colonne COMPLETED est absente",
                    result.next()
            );

            String defaultValue
                    = result.getString("DATA_DEFAULT");

            assertNotNull(
                    "COMPLETED n'a pas de valeur par défaut",
                    defaultValue
            );

            assertEquals(
                    "0",
                    defaultValue.trim()
            );

        }
    }

    // We need to check if the TASK table has a primary key on the ID column
    @Test
    public void shouldHavePrimaryKeyOnId() throws Exception {

        DatabaseMetaData metaData = connection.getMetaData();

        boolean primaryKeyFound;
        try (ResultSet primaryKeys = metaData.getPrimaryKeys(
                null,
                "DEMO_APP",
                "TASK"
        )) {
            primaryKeyFound = false;
            while (primaryKeys.next()) {

                String columnName
                        = primaryKeys.getString("COLUMN_NAME");

                // We check if the primary key constraint is on the ID column, if so we set the flag to true
                if ("ID".equals(columnName)) {

                    primaryKeyFound = true;
                }
            }
        }
        // Test fail if the primary key constraint is not found on the ID column
        assertTrue(
                "La clé primaire sur ID est absente",
                primaryKeyFound
        );
    }
}
