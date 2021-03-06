package sample.Model;

import javafx.scene.control.Alert;
import sample.utils.DB;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Appointments {

    private int appointmentID;      //auto gen
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;     //auto gen
    private int userID;     //auto gen
    private int contactID;
    private String contact;
    private String month;

    public Appointments(int appointment_id, String start) {
        this.appointmentID = appointment_id;
        this.start = start;

    }

    public String getStartTimeForAlertTest() {
        return startTimeForAlertTest;
    }

    public void setStartTimeForAlertTest(String startTimeForAlertTest) {
        this.startTimeForAlertTest = startTimeForAlertTest;
    }

    private String startTimeForAlertTest;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;


    public Appointments() {

    }

    public Appointments(int appointmentID, String title, String description, String location,
                        String type, String start, String end, Date createDate, String createdBy,
                        Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public Appointments(int appointment_id, String title, String description, String location, String contact, String type, String start, String end, int customer_id) {
        this.appointmentID = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customer_id;


    }

    public Appointments(String title, String description, String location, String contact, String type, String start, String end, int customer_id) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customer_id;


    }


    public Appointments(String title, String description, String location, int contactID, String type, String start, String end, int customer_id) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactID = contactID;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customer_id;


    }

    public Appointments(int customer_id) {
        this.customerID = customer_id;


    }

    public Appointments(String start) {
        this.start = start;


    }

    public Appointments(int appointmentId, String start, String end, String title, String type, int customerId, String user) {

        this.appointmentID = appointmentId;
        this.title = title;
        this.type = type;
        this.start = start;
        this.end = end;

        this.lastUpdatedBy = user;
        this.customerID = customerId;


    }

    public Appointments(String type, String month, int total) {

        this.type = type;
        this.month = month;
        this.total = total;

    }


    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }



    public void setAppointmentAlert() {

        boolean isFound = false;


        try {

            Statement statement = DB.startConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Start FROM appointments;");

            LocalTime currentTime = LocalTime.now();

            while (resultSet.next()) {

                String startTime = resultSet.getString("Start");

                DateTimeFormatter formateDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime localFormattedTime = LocalDateTime.parse(startTime, formateDateTime);
                ZonedDateTime zonedFormattedTime = localFormattedTime.atZone(ZoneId.of("UTC"));

                ZoneId localZoneId = ZoneId.systemDefault();
                ZonedDateTime zonedUTCTime = zonedFormattedTime.withZoneSameInstant(localZoneId);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("kk:mm");

                LocalTime localTime = LocalTime.parse(zonedUTCTime.toString().substring(11, 16), format);
                String appointmentTime = localTime.toString();
                long difference = ChronoUnit.MINUTES.between(currentTime, localTime);

                if (difference > 0 && difference <= 15) {


                    String alertMessage = String.format("Reminder! Upcoming Appointment at %s",
                            appointmentTime);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Reminder");
                    alert.setHeaderText("Upcoming appointment within 15 minutes!");
                    alert.setContentText(alertMessage);
                    alert.showAndWait();

                    isFound = true;

                    break;
                }

            }
            if (!isFound) {
                String alertMessage = "No Upcoming appointment within 15 minutes";
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Reminder");
                alert.setHeaderText("No Upcoming appointment within 15 minutes!");
                alert.setContentText(alertMessage);
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}