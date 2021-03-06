package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.Appointments;
import sample.Model.Customers;
import sample.utils.DB;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * The type Reports controller.
 */
public class reportsController implements Initializable {
    /**
     * Exit.
     *
     * @param event the event
     */
    @FXML
    void exit(ActionEvent event) {

        Stage stage;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();

    }


    @FXML
    private TableView<Appointments> monthTypeTable;

    @FXML
    private TableColumn<Appointments, String> monthTab;

    @FXML
    private TableColumn<Appointments, String> typeTab;


    @FXML
    private TableColumn<Appointments, Integer> totalTab;


    private static final ObservableList<Appointments> typeMonth = FXCollections.observableArrayList();


    /**
     * Gets report one.
     */
//first report
    public void getReportOne() {

        typeMonth.clear();

        try {
            Statement statement = DB.startConnection().createStatement();
            String query = "SELECT Type, MONTHNAME(start) as 'Month', COUNT(*) as 'Total' FROM appointments GROUP BY Type, Month ASC;";
            ResultSet results = statement.executeQuery(query);


            while (results.next()) {
                Appointments appointments = new Appointments(
                        results.getString("Type"),
                        results.getString("Month"),
                        results.getInt("Total")


                );
                typeMonth.add(appointments);

            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLExcpetion: " + e.getMessage());
        }
    }


//report two


    /**
     * Gets li lee.
     *
     * @return the li lee
     */
////get Lii  Lee Contacts
    public ObservableList<Appointments> getLiLee() {

        try {

            ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();


            try (Statement statement = DB.startConnection().createStatement()) {
                String query = "SELECT appointments.Appointment_ID, appointments.Title,appointments.Description, contacts.Contact_Name, appointments.Type ,appointments.Location, appointments.Start, appointments.End, appointments.Customer_ID \n" +
                        " FROM appointments INNER JOIN  contacts ON appointments.Contact_ID = contacts.Contact_ID \n" +
                        "WHERE Contact_Name = \"Li Lee\";";


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
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }


    /**
     * Gets anika.
     *
     * @return the anika
     */
//get Anika Costa contacts
    public ObservableList<Appointments> getAnika() {

        try {

            ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();


            try (Statement statement = DB.startConnection().createStatement()) {
                String query = "SELECT appointments.Appointment_ID, appointments.Title,appointments.Description, contacts.Contact_Name, appointments.Type ,appointments.Location, appointments.Start, appointments.End, appointments.Customer_ID \n" +
                        " FROM appointments INNER JOIN  contacts ON appointments.Contact_ID = contacts.Contact_ID \n" +
                        "WHERE Contact_Name = \"Anika Costa\";";


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
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }


    /**
     * Gets garcia.
     *
     * @return the garcia
     */
//get Garcia contacts
    public ObservableList<Appointments> getGarcia() {

        try {


            ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();


            try (Statement statement = DB.startConnection().createStatement()) {
                String query = "SELECT appointments.Appointment_ID, appointments.Title,appointments.Description, contacts.Contact_Name, appointments.Type ,appointments.Location, appointments.Start, appointments.End, appointments.Customer_ID \n" +
                        " FROM appointments INNER JOIN  contacts ON appointments.Contact_ID = contacts.Contact_ID \n" +
                        "WHERE Contact_Name = \"Daniel Garcia\";";


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
            }


        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return null;
    }


    @FXML
    private TableView<Appointments> secondReportTable;


    @FXML
    private TableColumn<Appointments, Integer> aptID;

    @FXML
    private TableColumn<Appointments, String> start;

    @FXML
    private TableColumn<Appointments, String> end;

    @FXML
    private TableColumn<Appointments, String> location;

    @FXML
    private TableColumn<Appointments, String> type;

    @FXML
    private TableColumn<Appointments, String> description;

    @FXML
    private TableColumn<Appointments, String> title;


    @FXML
    private TableColumn<Appointments, Integer> custID;


    private final ObservableList<String> contactNames = FXCollections.observableArrayList("Anika Costa", "Daniel Garcia", "Li Lee");


    @FXML
    private ComboBox<String> contactComboBox;


    /**
     * Pick contact name.
     */
    @FXML
    public void pickContactName() {
        String currentContactName = contactComboBox.getSelectionModel().getSelectedItem();
        switch (currentContactName) {
            case "Anika Costa":
                secondReportTable.setItems(getAnika());

                break;
            case "Li Lee":
                secondReportTable.setItems(getLiLee());


                break;
            case "Daniel Garcia":
                secondReportTable.setItems(getGarcia());


                break;
        }

    }


    @FXML
    private TableView<Customers> cTotalAptTable;

    @FXML
    private TableColumn<Customers, String> customerCol;

    @FXML
    private TableColumn<Customers, Integer> aptsCol;


    private static final ObservableList<Customers> totalCustomers = FXCollections.observableArrayList();


    /**
     * Gets third report.
     */
    public void getThirdReport() {

        totalCustomers.clear();

        try {
            Statement statement = DB.startConnection().createStatement();
            String query = "SELECT customers.Customer_Name, COUNT(*) as 'Total' FROM customers JOIN appointments \n" +
                    "ON customers.Customer_ID = appointments.Customer_ID GROUP BY Customer_Name;";
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                Customers customer = new Customers(
                        results.getString("Customer_Name"),
                        results.getInt("Total")
                );
                totalCustomers.add(customer);

            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLExcpetion: " + e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getReportOne();
        getThirdReport();


        contactComboBox.setItems(contactNames);
        aptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        custID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));


        cTotalAptTable.setItems(totalCustomers);
        aptsCol.setCellValueFactory(new PropertyValueFactory<>("Total"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));


        monthTypeTable.setItems(typeMonth);
        totalTab.setCellValueFactory(new PropertyValueFactory<>("Total"));
        monthTab.setCellValueFactory(new PropertyValueFactory<>("Month"));
        typeTab.setCellValueFactory(new PropertyValueFactory<>("Type"));


    }

}


