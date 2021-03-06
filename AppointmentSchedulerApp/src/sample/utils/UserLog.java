package sample.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type User log.
 */
public class UserLog {

//    private static final String FILENAME = "login_activity.txt";

    /**
     * User activity log.
     *
     * @param userName     the user name
     * @param isSuccessful the is successful
     */
    public static void userActivityLog(String userName, boolean isSuccessful) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("login_activity.txt", true));
            PrintWriter printWriter = new PrintWriter(writer);
            //   System.out.println(userName + " " + isSuccessful);
            printWriter.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")) + " " + userName + (isSuccessful ? " Successfully Logged In" : " Failed To Log In"));
            writer.flush();

        } catch (IOException e) {
            //  e.printStackTrace();
            System.out.println("Activity Log Error: " + e.getMessage());
        }


    }


}
