package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.utils.AppointmentsDB;
import sample.utils.CustomersDB;
import sample.utils.UsersDB;

import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * The type Apt add screen controller.
 */
public class aptAddScreenController implements Initializable {

    /**
     * Exit.
     *
     * @param event the event
     */
    @FXML
    void exit(ActionEvent event) {


        customerAddScreenController.exitAlert(event);


    }


    @FXML
    private TextField desc;


    @FXML
    private ComboBox<String> location;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<String> contactName;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> startTime;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> endTIme;

    @FXML
    private TextField user;

    @FXML
    private ComboBox<Integer> custIDCombo;


    private final ObservableList<String> contactNames = FXCollections.observableArrayList("Anika Costa", "Daniel Garcia", "Li Lee");
    private final ObservableList<String> typeList = FXCollections.observableArrayList("Planning Session", "De-Briefing");
//    private final ObservableList<String> timeList = FXCollections.observableArrayList("8:00", "9:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "4:00",
//            "5:00");

    private final ObservableList<String> timeList = FXCollections.observableArrayList("8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM");

    private final ObservableList<String> locations = FXCollections.observableArrayList("Phoenix, Arizona", "White Plains, New York", "Montreal, Canada", "London, England");


    /**
     * Pick contact name.
     */
    @FXML
    public void pickContactName() {
        String currentContactName = contactName.getSelectionModel().getSelectedItem();
        if (currentContactName.equals("Anika Costa")) {
            contactName.getSelectionModel().select(0);
        } else if (currentContactName.equals("Daniel Garcia")) {

            contactName.getSelectionModel().select(1);
        } else {
            contactName.getSelectionModel().select(2);

        }

    }


    /**
     * Switch start times string.
     *
     * @return the string
     */
    public String switchStartTimes() {
        String currentTime = startTime.getSelectionModel().getSelectedItem();
        String x = "";

        switch (currentTime) {
            case "8:00 AM":
                x = "8:00";
                break;
            case "9:00 AM":
                x = "9:00";
                break;
            case "10:00 AM":
                x = "10:00";
                break;
            case "11:00 AM":
                x = "11:00";
                break;
            case "12:00 PM":
                x = "12:00";
                break;
            case "1:00 PM":
                x = "1:00";
                break;
            case "2:00 PM":
                x = "2:00";
                break;
            case "3:00 PM":
                x = "3:00";
                break;
            case "4:00 PM":
                x = "4:00";
                break;
            case "5:00 PM":
                x = "5:00";
                break;


        }

        return x;
    }

    /**
     * Switch end times string.
     *
     * @return the string
     */
    public String switchEndTimes() {
        String currentTime = endTIme.getSelectionModel().getSelectedItem();
        String x = "";

        switch (currentTime) {
            case "8:00 AM":
                x = "8:00";
                break;
            case "9:00 AM":
                x = "9:00";
                break;
            case "10:00 AM":
                x = "10:00";
                break;
            case "11:00 AM":
                x = "11:00";
                break;
            case "12:00 PM":
                x = "12:00";
                break;
            case "1:00 PM":
                x = "1:00";
                break;
            case "2:00 PM":
                x = "2:00";
                break;
            case "3:00 PM":
                x = "3:00";
                break;
            case "4:00 PM":
                x = "4:00";
                break;
            case "5:00 PM":
                x = "5:00";
                break;


        }

        return x;
    }


    /**
     * Save apt.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void saveApt(ActionEvent actionEvent) {
        try {
            int aptType = type.getSelectionModel().getSelectedIndex();
            int custIdConvert = custIDCombo.getSelectionModel().getSelectedItem();


            LocalDate ld = date.getValue();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // DateFormat dateTimeFormatter1 = new SimpleDateFormat("HH:mm:ss.S", Locale.ENGLISH);

            String newX = dateTimeFormatter.format(ld) + " " + switchStartTimes();
            String newY = dateTimeFormatter.format(ld) + " " + switchEndTimes();


            String createdBy = user.getText();
            String lastUpdatedBy = user.getText();
            int userId = UsersDB.getActiveUserID().getUserID();

            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());


            if (type.getSelectionModel().getSelectedIndex() == -1 || startTime.getSelectionModel().getSelectedIndex() == -1 ||
                    endTIme.getSelectionModel().getSelectedIndex() == -1 || contactName.getSelectionModel().getSelectedIndex() == -1 || location.getSelectionModel().getSelectedIndex() == -1) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();


            } else if (AppointmentsDB.overlappingApt(-1, location.getSelectionModel().getSelectedItem(), newX)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Appointment Overlap. Pick a different time or location.");
                alert.showAndWait();


            } else if
            (newX.equals(newY)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Start and End Time can not be the same");
                alert.showAndWait();


            } else if
            (AppointmentsDB.overlappingAptTime(-1,location.getSelectionModel().getSelectedItem(), newX)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Schedule conflicting");
                alert.setContentText("Appointment Schedule Conflicts With Existing Appointment. Pick new times or location");
                alert.showAndWait();


            }

            else if
            (startTime.getSelectionModel().getSelectedIndex() + 1 > endTIme.getSelectionModel().getSelectedIndex() + 1) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Start Time Must Be Before End Time");
                alert.showAndWait();


            } else {


                AppointmentsDB.saveNewApt(title.getText(), desc.getText(), contactName.getSelectionModel().getSelectedIndex() + 1, typeList.get(aptType), location.getSelectionModel().getSelectedItem(), newX, newY, custIdConvert, createdBy, lastUpdate, lastUpdatedBy, userId);


                Stage stage;
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.close();


            }


        } catch (NullPointerException | NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();


        }


    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        contactName.setItems(contactNames);
        location.setItems(locations);
        type.setItems(typeList);
        startTime.setItems(timeList);
        endTIme.setItems(timeList);


        user.setText(UsersDB.getActiveUser().getUserName());


        custIDCombo.setItems(CustomersDB.getAllCustomerIDs());

        //lambda to set date picker to only work days
        date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(
                        empty ||
                                date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                                date.isBefore(LocalDate.now()));
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                        date.isBefore(LocalDate.now())) {
                    setStyle("-fx-background-color: #cbcaca;");
                }
            }
        });


    }


}