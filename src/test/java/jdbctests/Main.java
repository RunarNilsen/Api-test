package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        // Oracle database connection strings
        String dbUrl = "jdbc:oracle:thin:@3.239.252.41:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        // 1.  Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // 2. Create statement
        Statement statement = connection.createStatement();
        // Run sql query and get the result in resultset object
        //String sqlQuery= "Select * From regions";
        String sqlQuery= "Select first_name, last_name, salary From employees";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        /* Example

        // Retrieving information from the resultSet
        // First we have to move pointer to the first row (from header to first row)
        resultSet.next(); // moving pointer one row ahead. Pointer starts from the header
        System.out.println(resultSet.getString("region_name")); // using column name
        System.out.println(resultSet.getString(1) +"-"+resultSet.getString(2)); // using column index
        resultSet.next(); // moving pointer one row ahead.
        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(1) +"-"+resultSet.getString(2)); // using column index
        resultSet.next(); // moving pointer one row ahead.
        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(1) +"-"+resultSet.getString(2)); // using column index
        */
        System.out.println("----------------------------------------------------------");

        // resultSet.next() return true.We can use this to reach all rows with loop
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) +"-"+resultSet.getString(2)+
                    "-"+resultSet.getString(3)); // using column index
        }

        // close all connections
        resultSet.close();
        statement.close();
        connection.close();



    }



}
