package sample.utils;

import sample.Model.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {


    private static Users activeUser;

    // Getter
    public static Users getActiveUser() {
        return activeUser;
    }


    private static Users activeUserID;

    // Getter
    public static Users getActiveUserID() {
        return activeUserID;
    }


    // Try Login via DB
    public static Boolean login(String username, String password) {
        try {
            Statement statement = DB.startConnection().createStatement();
            String query = "SELECT * FROM users WHERE User_Name='" + username + "' AND Password='" + password + "'";
            ResultSet results = statement.executeQuery(query);
            if (results.next()) {
                activeUser = new Users();
                activeUser.setUserName(results.getString("User_Name"));
                activeUserID = new Users();
                activeUserID.setUserID(results.getInt("User_ID"));

                statement.close();
                UserLog.userActivityLog(username, true);

                return true;
            } else {
                UserLog.userActivityLog(username, false);

                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }


}
