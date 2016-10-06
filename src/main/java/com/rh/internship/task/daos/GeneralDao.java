package com.rh.internship.task.daos;

import java.sql.*;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Saeed Zarinfam
 */
public class GeneralDao{
    // JDBC database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/task_db?characterEncoding=UTF-8&useSSL=false";

    // Database credentials
    static final String USER = "test";
    static final String PASS = "p";

    public <T> Optional<T> runQuery(Function<Statement, T> queryBlock){

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (Statement stmt = conn.createStatement()) {
                return Optional.ofNullable(queryBlock.apply(stmt));
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
