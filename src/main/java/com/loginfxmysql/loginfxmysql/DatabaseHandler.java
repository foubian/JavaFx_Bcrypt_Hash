package com.loginfxmysql.loginfxmysql;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;


public class DatabaseHandler {

    /**
     * Variable de Conexion
     */
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/demo-laravel?useSSL=false";
    private static final String DATABASE_USERNAME = "admin";
    private static final String DATABASE_PASSWORD = "fouad123";

    /**
     * Function Login Check
     */
    public boolean validate(String emailId, String password) throws SQLException {
        final String SELECT_QUERY = "SELECT * FROM users WHERE email = ?";
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String hash1 = resultSet.getString("password"), hash;
                hash = hash1.replace("$2y$", "$2a$");
                if (BCrypt.checkpw(password, hash)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }
    /** END Function Login Check */

    /**
     * Show SQL error
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    /** END Show error */


}
