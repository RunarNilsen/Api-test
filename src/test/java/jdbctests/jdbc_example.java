package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {
    /*
    Here we will learn about different resultSet keywords
        next()—> move to next row
        previous()—> move to previous row
        beforeFirst()—> goes before the first row
        afterLast()—>goes after last row
        getRow()—> get the current row number
        last()—> moves to last row
        absolute() —> goes specific row


        "ResultSet.TYPE_SCROLL_INSENSITIVE" allow us to navigate up and down in the query result
        "ResultSet.CONCUR_READ_ONLY"  means that users can only read values from databases, don't update the database
     */


    // Oracle database connection strings
    String dbUrl = "jdbc:oracle:thin:@3.239.252.41:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {


        // 1.  Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // 2. Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Run sql query and get the result in resultSet object
        String sqlQuery= "Select * From departments";
        //String sqlQuery= "Select first_name, last_name, salary From employees";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // 1. How to find how many rows are in the query
        resultSet.last(); // it moves to last row
        int rowCount = resultSet.getRow(); // it gets the current rows number
        System.out.println("rowCount = " + rowCount);

        // 2. Printing all rows of second column
        resultSet.beforeFirst(); // goes back to the first row
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        // close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metaDataExample() throws SQLException {


        // 1.  Create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // 2. Create statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // Run sql query and get the result in resultSet object
        String sqlQuery= "Select * From departments";
        //String sqlQuery= "Select first_name, last_name, salary From employees";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        // get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        /* Examples
        System.out.println("User = " + dbMetadata.getUserName() );
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());

         */


        // we use resultSet metadata to get information about the tables
        // get the resultSet object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        // How many columns do we have?
        int columnCount = rsMetadata.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        // Getting columns name that is on the specified index
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        // Print all the column names dynamically
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }



        // close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }



}
