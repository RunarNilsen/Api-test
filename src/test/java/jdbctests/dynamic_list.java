package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {


    // Oracle database connection strings
    String dbUrl = "jdbc:oracle:thin:@3.239.252.41:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void metaDataExample1() throws SQLException {
        /*
        This gets all rows and columns from the query and add them inside the List of Maps.
         */

        // 1.  Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // 2. Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Run sql query and get the result in resultSet object
        String sqlQuery= "Select * From departments";
        //String sqlQuery= "Select first_name, last_name, salary From employees";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // we use resultSet metadata to get information about the tables
        // get the resultSet object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        // List for keeping all rows as a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        // while(resultSet.next()) helps to iterate through columns
        while (resultSet.next()) {
            // key(columns) will be string but value can be any type of data. That's why we choose <String, Object>
            Map<String, Object> row = new HashMap<>(); // creating empty Map
            // number of columns
            int colCount = rsMetadata.getColumnCount();
            // for loop helps to iterate through columns
            for (int i = 1; i <= colCount; i++) {
                row.put(rsMetadata.getColumnName(i), resultSet.getObject(i));
            }

            queryData.add(row); // adding a Map to the List of Maps
        }

        // Printing the rows
        for (Map<String, Object> row: queryData) {
            System.out.println(row.toString());

        }




        // close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }



}
