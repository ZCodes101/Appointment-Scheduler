package sample.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Model.Appointments;
import sample.Model.Customers;
import sample.utils.AppointmentsDB;
import sample.utils.CustomersDB;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * The type Apt main screen controller.
 */
public class aptMainScreenController implements Initializable {


    @FXML
    private TableView<Appointments> allAptTble;

    @FXML
    private TableView<Appointments> allAptTbleWk;

    @FXML
    private TableView<Appointments> allTable;


    @FXML
    private TableColumn<Appointments, Integer> aptID;

    @FXML
    private TableColumn<Appointments, String> title;

    @FXML
    private TableColumn<Appointments, String> description;

    @FXML
    private TableColumn<Appointments, String> location;

    @FXML
    private TableColumn<Appointments, String> contact;

    @FXML
    private TableColumn<Appointments, String> type;

    @FXML
    private TableColumn<Appointments, String> start;

    @FXML
    private TableColumn<Appointments, String> end;

    @FXML
    private TableColumn<Appointments, Integer> customerID;


    @FXML
    private TableColumn<Appointments, Integer> AptIDwk;

    @FXML
    private TableColumn<Appointments, String> titleWk;

    @FXML
    private TableColumn<Appointments, String> descWk;

    @FXML
    private TableColumn<Appointments, String> locationWk;

    @FXML
    private TableColumn<Appointments, String> cNmaeWk;

    @FXML
    private TableColumn<Appointments, String> typeWk;

    @FXML
    private TableColumn<Appointments, String> startWk;

    @FXML
    private TableColumn<Appointments, String> endWk;

    @FXML
    private TableColumn<Appointments, Integer> custIDWk;


    @FXML
    private TableColumn<Appointments, Integer> allAptID;

    @FXML
    private TableColumn<Appointments, String> allTitle;

    @FXML
    private TableColumn<Appointments, String> allDesc;

    @FXML
    private TableColumn<Appointments, String> allLocation;

    @FXML
    private TableColumn<Appointments, String> allContactName;

    @FXML
    private TableColumn<Appointments, String> allType;

    @FXML
    private TableColumn<Appointments, String> allStart;

    @FXML
    private TableColumn<Appointments, String> allEnd;

    @FXML
    private TableColumn<Appointments, Integer> allCustID;


    @FXML
    private Tab allAptTab;

    @FXML
    private Tab monthTab;
    @FXML
    private Tab weekTab;


    private Appointments selected;


    /**
     * Add apt.
     *
     * @throws IOException the io exception
     */
    @FXML
    void addApt() throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("aptAddScreen.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Customer Management Options");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }


    /**
     * The X.
     */
    Appointments x;

    /**
     * Mod apt.
     *
     * @param event the event
     * @throws IOException    the io exception
     * @throws ParseException the parse exception
     */
    @FXML
    void modApt(ActionEvent event) throws IOException, ParseException {

        if (monthTab.isSelected()) {
            x = allAptTble.getSelectionModel().getSelectedItem();
        } else if (weekTab.isSelected()) {
            x = allAptTbleWk.getSelectionModel().getSelectedItem();
        } else if (allAptTab.isSelected()) {
            x = allTable.getSelectionModel().getSelectedItem();


        }


        if (x == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please Select An Item To Modify!");

            alert.showAndWait();


        } else {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("aptModScreen.fxml"));
            loader.load();
            aptModScreenController msc = loader.getController();
            msc.populateFields(x);


            Stage stage;
            Parent root;
            stage = new Stage();
            stage.setResizable(false);
            root = loader.getRoot();
            stage.setScene(new Scene(root));


            stage.setTitle("Customer Management Options");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


        }


    }


    /**
     * Delete.
     */
    @FXML
    void delete() {


        try {


            if (monthTab.isSelected()) {
                if (allAptTble.getSelectionModel().getSelectedItem() != null) {
                    selected = allAptTble.getSelectionModel().getSelectedItem();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Select an Appointment to delete.");


                    alert.showAndWait()
                            .filter(response -> response == ButtonType.OK);
                    return;

                }
            } else if (weekTab.isSelected()) {
                if (allAptTbleWk.getSelectionModel().getSelectedItem() != null) {
                    selected = allAptTbleWk.getSelectionModel().getSelectedItem();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Select an Appointment to delete.");


                    alert.showAndWait()
                            .filter(response -> response == ButtonType.OK);
                    return;

                }
            } else if (allAptTab.isSelected()) {
                if (allTable.getSelectionModel().getSelectedItem() != null) {
                    selected = allTable.getSelectionModel().getSelectedItem();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Select an Appointment to delete.");


                    alert.showAndWait()
                            .filter(response -> response == ButtonType.OK);

                    return;

                }
            }


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete Appointment Data");
            alert.setContentText("This Will Delete: " + selected.getContact() + " Appointment. Continue?");
            alert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {

                    AppointmentsDB.deleteApt(selected.getAppointmentID());

                    allAptTble.setItems(AppointmentsDB.getMonthlyAppointments());
                    allAptTbleWk.setItems(AppointmentsDB.getWeeklyApt());
                    allTable.setItems(AppointmentsDB.getAllAppoinments());


                    Alert alert2 = new Alert(Alert.AlertType.NONE);
                    alert2.setTitle("Notice");
                    alert2.getDialogPane().getButtonTypes().add(ButtonType.OK);

                    alert2.setHeaderText("Delete Appointment Data");
                    alert2.setContentText("Appointment: " + selected.getAppointmentID() + ", Type: " + selected.getType() + " was canceled.");
                    alert2.showAndWait();


                }
            }));


        } catch (NullPointerException e) {
            // System.out.println(e.getMessage());
        }
    }


    @FXML
    private void exit(ActionEvent event) {

        Stage stage;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }


    @FXML
    private TableView<Customers> cTableView;


    @FXML
    private TableColumn<Customers, Integer> ctID;

    @FXML
    private TableColumn<Customers, Integer> ctName;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //month tab table
        aptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));

        allAptTble.setItems(AppointmentsDB.getMonthlyAppointments());

        //customer info table
        ctID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        ctName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        cTableView.setItems(CustomersDB.getAllCustomers());


        //week table
        AptIDwk.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        cNmaeWk.setCellValueFactory(new PropertyValueFactory<>("contact"));
        custIDWk.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        titleWk.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descWk.setCellValueFactory(new PropertyValueFactory<>("Description"));
        typeWk.setCellValueFactory(new PropertyValueFactory<>("Type"));
        locationWk.setCellValueFactory(new PropertyValueFactory<>("Location"));
        startWk.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endWk.setCellValueFactory(new PropertyValueFactory<>("End"));

        allAptTbleWk.setItems(AppointmentsDB.getWeeklyApt());


        //all tab table
        allAptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        allContactName.setCellValueFactory(new PropertyValueFactory<>("contact"));
        allCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        allTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        allDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        allType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        allLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        allStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        allEnd.setCellValueFactory(new PropertyValueFactory<>("End"));


        allTable.setItems(AppointmentsDB.getAllAppoinments());


    }

}
