package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.Appointments;
import sample.utils.AppointmentsDB;

import sample.utils.UsersDB;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * The type Apt mod screen controller.
 */
public class aptModScreenController implements Initializable {

    /**
     * Exit.
     *
     * @param event the event
     */
//closes the window
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
    private TextField aptID;


    @FXML
    private ComboBox<Integer> custIDCombo;


    private final ObservableList<String> contactNames = FXCollections.observableArrayList("Anika Costa", "Daniel Garcia", "Li Lee");
    private final ObservableList<String> typeList = FXCollections.observableArrayList("Planning Session", "De-Briefing");
    private final ObservableList<String> timeList = FXCollections.observableArrayList("8:00", "9:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "4:00",
            "5:00");
    private final ObservableList<String> locations = FXCollections.observableArrayList("Phoenix, Arizona", "White Plains, New York", "Montreal, Canada", "London, England");


    /**
     * Pick contact name.
     */
//select the contacts and assign to index
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


    //saves apt

    /**
     * Save apt.
     *
     * @param actionEvent the action event
     * @throws SQLException the sql exception
     */
    @FXML
    public void saveApt(ActionEvent actionEvent) throws SQLException {
        int aid2 = Integer.parseInt(aptID.getText());

        String lastUpdatedBy = user.getText();
        int userId = UsersDB.getActiveUserID().getUserID();

        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());


        try {


            int aptType = type.getSelectionModel().getSelectedIndex();

            int custIdConvert = custIDCombo.getSelectionModel().getSelectedItem();


            LocalDate ld = date.getValue();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            String newX = dateTimeFormatter.format(ld) + " " + timeList.get(startTime.getSelectionModel().getSelectedIndex());
            String newY = dateTimeFormatter.format(ld) + " " + timeList.get(endTIme.getSelectionModel().getSelectedIndex());


            if (type.getSelectionModel().getSelectedIndex() == -1 || startTime.getSelectionModel().getSelectedIndex() == -1 ||
                    endTIme.getSelectionModel().getSelectedIndex() == -1 || contactName.getSelectionModel().getSelectedIndex() == -1) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();


            } else if (newX.equals(newY)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Start and End Time can not be the same");
                alert.showAndWait();


//            } else if (AppointmentsDB.overlappingAppointmentLocation(-1, location.getSelectionModel().getSelectedItem())) {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Dialog");
//            alert.setContentText("Location Overlap. Pick a different location or cancel to Keep Selected Date and Time.");
//            alert.showAndWait();
//
//
//        }


            } else {

                AppointmentsDB.updateApt(title.getText(), desc.getText(), contactName.getSelectionModel().getSelectedIndex() + 1, typeList.get(aptType), location.getSelectionModel().getSelectedItem(), newX, newY, custIdConvert, lastUpdate, lastUpdatedBy, userId, aid2);
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

    /**
     * Populate fields.
     *
     * @param appointments the appointments
     * @throws ParseException the parse exception
     */
//populates the appointment fields
    public void populateFields(Appointments appointments) throws ParseException {
        int aid = appointments.getAppointmentID();

        aptID.setText(Integer.toString(aid));


        contactName.getSelectionModel().select(appointments.getContact());

        //convert time string and spilt into date or time
        String str = appointments.getStart();
        String str2 = appointments.getEnd();
        String str3 = appointments.getStart();


        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str2);
        Date date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str3);

        String newString = new SimpleDateFormat("H:mm").format(date1);
        String newString2 = new SimpleDateFormat("H:mm").format(date2);
        String newString3 = new SimpleDateFormat("yyyy-MM-dd").format(date3);

        date.setValue(LocalDate.parse(newString3));


        startTime.getSelectionModel().select(newString);
        endTIme.getSelectionModel().select(newString2);


        type.getSelectionModel().select(appointments.getType());
        custIDCombo.setValue(appointments.getCustomerID());
        title.setText(appointments.getTitle());
        location.getSelectionModel().select(appointments.getLocation());
        desc.setText(appointments.getDescription());
    }


    @FXML
    private TextField user;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        contactName.setItems(contactNames);
        type.setItems(typeList);
        startTime.setItems(timeList);
        endTIme.setItems(timeList);

        location.setItems(locations);

        user.setText(UsersDB.getActiveUser().getUserName());


        custIDCombo.setItems((AppointmentsDB.getAllCustIDs()));

        //set date picker to only work days
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