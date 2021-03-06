package sample.Views;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Model.Appointments;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Main screen controller.
 */
public class mainScreenController implements Initializable {


    /**
     * Exit app.
     *
     * @param event the event
     */
    @FXML
    void exitApp(ActionEvent event) {

        Stage stage;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }

    /**
     * Open apt menu.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void openAptMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("aptMainScreen.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Customer Management Options");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    /**
     * Open customer menu.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void openCustomerMenu(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("customerScreen.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Customer Management Options");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();


    }


    /**
     * Open reports menu.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void openReportsMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reports.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Customer Management Options");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Appointments checkAppointments = new Appointments();
        checkAppointments.setAppointmentAlert();


    }


}


