package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Appointments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AppointmentsDB {

    //get Monthly appointments

    public static ObservableList<Appointments> getMonthlyAppointments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        LocalDateTime st = LocalDateTime.now();
        LocalDateTime en = LocalDateTime.now().plusMonths(1);


        //  LocalDate ld = date.getValue();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");


        String newX = dateTimeFormatter.format(st);
        String newY = dateTimeFormatter.format(en);


        try {
            Statement statement = DB.startConnection().createStatement();
            String query = "SELECT appointments.Appointment_ID, appointments.Title,appointments.Description, contacts.Contact_Name, appointments.Type ,appointments.Location, appointments.Start, appointments.End, appointments.Customer_ID  FROM appointments INNER JOIN  contacts ON appointments.Contact_ID = contacts.Contact_ID  WHERE Start >= '" + newX + "'  AND Start <= '" + newY + "' ; ";

            String x = "";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Appointments appointment = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getString("Start"),
                        rs.getString("End"),
                        rs.getInt("Customer_ID"));
                allAppointments.add(appointment);


            }
            statement.close();
            return allAppointments;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    // Get all Appointments
    public static ObservableList<Appointments> getAllAppoinments() {
        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {
            Statement statement = DB.startConnection().createStatement();

            String x = "SELECT appointments.Appointment_ID, appointments.Title,appointments.Description, contacts.Contact_Name, appointments.Type ,appointments.Location, appointments.Start, appointments.End, appointments.Customer_ID FROM appointments INNER JOIN  contacts ON appointments.Contact_ID = contacts.Contact_ID;";

            ResultSet rs = statement.executeQuery(x);
            while (rs.next()) {
                Appointments appointment = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getString("Start"),
                        rs.getString("End"),
                        rs.getInt("Customer_ID"));
                allAppointments.add(appointment);

            }

            statement.close();
            return allAppointments;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    //   Delete Customer if found from DB
    public static boolean deleteApt(int id) {
        try {
            Statement statement = DB.startConnection().createStatement();
            String x = "DELETE FROM appointments WHERE Appointment_ID =" + id;

            int udx = statement.executeUpdate(x);
            if (udx == 1) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return false;

    }


    // Get Weekly Appointments
    public static ObservableList<Appointments> getWeeklyApt() {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        Appointments appointment;

        LocalDateTime st = LocalDateTime.now();
        LocalDateTime en = LocalDateTime.now().plusWeeks(1);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");


        String newX = dateTimeFormatter.format(st);
        String newY = dateTimeFormatter.format(en);

        try {
            Statement statement = DB.startConnection().createStatement();
            String query = "SELECT appointments.Appointment_ID, appointments.Title,appointments.Description, contacts.Contact_Name, appointments.Type ,appointments.Location, appointments.Start, appointments.End, appointments.Customer_ID  FROM appointments INNER JOIN  contacts ON appointments.Contact_ID = contacts.Contact_ID  WHERE Start >= '" + newX + "'  AND Start <= '" + newY + "' ; ";


            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                appointment = new Appointments(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Contact_Name"),
                        rs.getString("Type"),
                        rs.getString("Start"),
                        rs.getString("End"),
                        rs.getInt("Customer_ID"));
                appointments.add(appointment);
            }
            statement.close();
            return appointments;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    //save new Apt
    public static void saveNewApt(String title, String description, int contactNameID, String type, String location, String start, String end, int customerID, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int userId) {


        try {

            Statement stment = DB.startConnection().createStatement();
            String x = "INSERT INTO appointments SET Title='" + title + "', Description='" + description + "', Contact_ID='" + contactNameID + "', Type='" + type + "', Location='" + location + "', Start='" + start + "', End='" + end + "', Created_By='" + createdBy + "', Last_Update='" + lastUpdate + "', Last_Updated_By='" + lastUpdatedBy + "', User_ID='" + userId + "', Customer_ID=" + customerID;

            stment.execute(x);

        } catch (SQLException e) {


            System.out.println("SQLException: " + e.getMessage());
        }

    }


    //updates appointment
    public static void updateApt(String title, String description, int contactNameID, String type, String location, String start, String end, int customerID, Timestamp lastUpdate, String lastUpdatedBy, int userId, int aptID) {


        try {


            Statement stment = DB.startConnection().createStatement();
            String x = "UPDATE appointments SET Title='" + title + "', Description='" + description + "', Contact_ID='" + contactNameID + "', Type='" + type + "', Location='" + location + "', Start='" + start + "', End='" + end + "', Last_Update='" + lastUpdate + "', Last_Updated_By='" + lastUpdatedBy + "', User_ID='" + userId + "', Customer_ID=" + customerID + " WHERE Appointment_ID=" + aptID;

            stment.executeUpdate(x);

        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
        }

    }


    // Return all Customers in apt db
    public static ObservableList<Integer> getAllCustIDs() {
        ObservableList<Integer> allCustomersID = FXCollections.observableArrayList();


        try {
            Statement statement = DB.startConnection().createStatement();


            String y = "SELECT Customer_ID  FROM customers;";


            ResultSet rs = statement.executeQuery(y);
            while (rs.next()) {
                Appointments appointments = new Appointments(
                        rs.getInt("Customer_ID")


                );
                allCustomersID.add(appointments.getCustomerID());
            }
            statement.close();
            return allCustomersID;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    //  Overlap Apt
    public static boolean overlappingApt(int id, String location, String start) {
        try {
            Statement stmnt = DB.startConnection().createStatement();
            String query = "SELECT * FROM appointments WHERE Start = '" + start + "' AND Location = '" + location + "'";
            ResultSet results = stmnt.executeQuery(query);
            if (results.next()) {
                if (results.getInt("Appointment_ID") == id) {
                    stmnt.close();
                    return false;
                }
                stmnt.close();
                return true;
            } else {
                stmnt.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQLExcpection: " + e.getMessage());
            return true;
        }
    }


    //  Overlap Apt
    public static boolean overlappingAptTime(int id, String location, String start) {
        try {
            Statement stmnt = DB.startConnection().createStatement();
            String query = "SELECT *\n" +
                    "FROM appointments\n" +
                    "WHERE CAST('" + start + "' AS TIME) BETWEEN CAST(appointments.Start AS TIME) AND CAST(appointments.End AS TIME) AND Location = '" + location + "'";


            ResultSet results = stmnt.executeQuery(query);
            if (results.next()) {
                if (results.getInt("Appointment_ID") == id) {
                    stmnt.close();
                    return false;
                }
                stmnt.close();
                return true;
            } else {
                stmnt.close();
                return false;
            }


//            ResultSet results = stmnt.executeQuery(query);
//            if (results.next()) {
//                String z = results.getString("Location");
//                String x = results.getString("Start");
//                String y = results.getString("End");
//
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                Date date_from = formatter.parse(x);
//                Date date_to = formatter.parse(y);
//                Date dateNow = formatter.parse(start);
//                System.out.println("AAAA");
//
//
//
//


//
//                if ( ((date_from.after(dateNow) && date_to.before(dateNow))) &&(location.equals(z) )) {
//                    System.out.println(start + ": " + location);
//                    stmnt.close();
//                    return true;


//                    if (results.getInt("Appointment_ID") == id) {
//                        stmnt.close();
//                        return false;
//                    }
//                    //stmnt.close();
//                   // return true;
//
//
//               else
//                   if ((date_from.after(dateNow) && date_to.before(dateNow)) && (location.equals(z) )) {
//                    System.out.println("Yes time between");
//                       System.out.println("1");
//
//
//                       stmnt.close();
//                    return false;
//
//
//                }
//
//                stmnt.close();
//                return true;
//
////                if ((results.getInt("Appointment_ID") == id )) {
////                    stmnt.close();
////                    return false;
////                }
////                stmnt.close();
////                return true;
//            } else {
//                System.out.println("2");
//
//                stmnt.close();
//                return false;
//            }
        } catch (SQLException e) {
            System.out.println("SQLExcpection: " + e.getMessage());
            return true;
        }
    }

    //  Overlap Apt location
    public static boolean overlappingAppointmentLocation(int id, String location) {
        try {
            Statement stmnt = DB.startConnection().createStatement();
            String query = "SELECT * FROM appointments WHERE Location = '" + location + "'";
            ResultSet results = stmnt.executeQuery(query);
            if (results.next()) {
                if (results.getInt("Appointment_ID") == id) {
                    stmnt.close();
                    return false;
                }
                stmnt.close();
                return true;
            } else {
                stmnt.close();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("SQLExcpection: " + e.getMessage());
            return true;
        }
    }


}
