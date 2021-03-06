package sample.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.utils.DB;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //primaryStage.setTitle("Sign In");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws SQLException the sql exception
     */
    public static void main(String[] args) throws SQLException {


        Connection conn = DB.startConnection();

        DB.setStatement(conn); //statement object created
        //Statement statement = DB.getStatement();

        launch(args);
        DB.closeConnection();

    }
}
