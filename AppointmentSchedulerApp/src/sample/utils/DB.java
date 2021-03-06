package sample.utils;


import java.sql.*;

public class DB {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/WJ06yUO?autoReconnect=true&useSSL=false";
    //private static final String ipAddress = "//3.227.166.251/WJ06yUO";

    //jdbc url
    private static final String jdbcURL = protocol + vendorName + ipAddress;

    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    public static Connection conn = null;

    private static final String userName = "U06yUO";
    private static final String password = "53688908966";


    //establishes connection to the database
    public static Connection startConnection() {
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, userName, password);
            // System.out.println("Successful");


        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e.getMessage());

        }
        return conn;

    }


    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }

    }


    private static Statement statement;


    public static void setStatement(Connection connection) throws SQLException {

        statement = connection.createStatement();


    }


    public static Statement getStatement() {

        return statement;
    }


}
