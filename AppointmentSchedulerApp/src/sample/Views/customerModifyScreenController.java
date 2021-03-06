package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.Customers;
import sample.utils.CustomersDB;

import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;

/**
 * The type Customer modify screen controller.
 */
public class customerModifyScreenController implements Initializable {


    @FXML
    private TextField name;

    @FXML
    private TextField address;


    @FXML
    private TextField zip;

    @FXML
    private TextField phone;

    @FXML
    private ComboBox<String> divCombo;

    @FXML
    private ComboBox<String> country;


    @FXML
    private TextField customerID;


    /**
     * Pick div.
     */
    @FXML
    public void pickDiv() {

    }

    /**
     * Pick country.
     */
    @FXML
    public void pickCountry() {

        String currentCountry = country.getSelectionModel().getSelectedItem();

        if (currentCountry.equals("UK")) {
            divCombo.setItems(divsUk);
        } else if (currentCountry.equals("Canada")) {
            divCombo.setItems(divsCA);
        } else {
            divCombo.setItems(divsUS);
        }


    }

    /**
     * Exit.
     *
     * @param event the event
     */
    @FXML
    void exit(ActionEvent event) {

        customerAddScreenController.exitAlert(event);

    }


    private int id;

    /**
     * Save customer.
     *
     * @param event the event
     * @throws SQLException the sql exception
     */
    @FXML
    void saveCustomer(ActionEvent event) throws SQLException {

        try {


            if (name.getText().isEmpty() || address.getText().isEmpty() || zip.getText().isEmpty() || phone.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();


            } else {


                CustomersDB.updateCustomer(id, name.getText(), address.getText(), zip.getText(), phone.getText(), switchDivNameNums());

                Stage stage;
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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
     * @param customer the customer
     */
    public void populateFields(Customers customer) {
        id = customer.getCustomerID();

        name.setText(customer.getCustomerName());
        address.setText(customer.getAddress());


        divCombo.getSelectionModel().select(customer.getDivisionID());

        if (divsUS.contains(divCombo.getSelectionModel().getSelectedItem())) {
            country.getSelectionModel().select("United States");
            divCombo.setItems(divsUS);


        } else if (divsUk.contains(divCombo.getSelectionModel().getSelectedItem())) {

            country.getSelectionModel().select("UK");
            divCombo.setItems(divsUk);


        } else {
            country.getSelectionModel().select("Canada");
            divCombo.setItems(divsCA);


        }


        customerID.setText(Integer.toString(customer.getCustomerID()));


        zip.setText(customer.getZipCode());
        phone.setText(customer.getPhoneNumber());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        country.setItems(countries);
    }


    private final ObservableList<String> divsUS = FXCollections.observableArrayList(
            "Alabama", "Arizona", "Arkansas",
            "California", "Colorado", "Connecticut",
            "Delaware", "District of Columbia",
            "Florida",
            "Georgia",
            "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky",
            "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
            "Ohio", "Oklahoma", "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina", "South Dakota",
            "Tennessee", "Texas",
            "Utah",
            "Vermont", "Virginia",
            "Washington", "West Virginia", "Wisconsin", "Wyoming",
            "Hawaii",
            "Alaska");
    private final ObservableList<String> divsCA = FXCollections.observableArrayList(


            "Northwest Territories",
            "Alberta",
            "British Columbia",
            "Manitoba",
            "New Brunswick",
            "Nova Scotia",
            "Prince Edward Island",
            "Ontario",
            "Quâ”œÂ®bec",
            "Saskatchewan",
            "Nunavut",
            "Yukon"
    );
    private final ObservableList<String> divsUk = FXCollections.observableArrayList(

            "Newfoundland and Labrador",
            "England",
            "Wales",
            "Scotland",
            "Northern Ireland"


    );
    /**
     * The Countries.
     */
    ObservableList<String> countries = FXCollections.observableArrayList("UK", "Canada", "United States");

    /**
     * Switch div name nums int.
     *
     * @return the int
     */
//set current selection in combo to correct name/index
    public int switchDivNameNums() {
        String currentDiv = divCombo.getSelectionModel().getSelectedItem();
        //current div = current string
        int x = divCombo.getSelectionModel().getSelectedIndex();

        switch (currentDiv) {
            case "Alabama":
                x = 1;
                break;
            case "Arizona":
                x = 2;
                break;
            case "Arkansas":
                x = 3;
                break;
            case "California":
                x = 4;
                break;
            case "Colorado":
                x = 5;
                break;
            case "Connecticut":
                x = 6;
                break;
            case "Delaware":
                x = 7;
                break;
            case "District of Columbia":
                x = 8;
                break;
            case "Florida":
                x = 9;
                break;
            case "Georgia":
                x = 10;
                break;
            case "Idaho":
                x = 11;
                break;
            case "Illinois":
                x = 12;
                break;
            case "Indiana":
                x = 13;
                break;
            case "Iowa":
                x = 14;
                break;
            case "Kansas":
                x = 15;
                break;
            case "Kentucky":
                x = 16;
                break;
            case "Louisiana":
                x = 17;
                break;
            case "Maine":
                x = 18;
                break;
            case "Maryland":
                x = 19;
                break;
            case "Massachusetts":
                x = 20;
                break;
            case "Michigan":
                x = 21;
                break;
            case "Minnesota":
                x = 22;
                break;
            case "Mississippi":
                x = 23;
                break;
            case "Missouri":
                x = 24;
                break;
            case "Montana":
                x = 25;
                break;
            case "Nebraska":
                x = 26;
                break;
            case "Nevada":
                x = 27;
                break;
            case "New Hampshire":
                x = 28;
                break;
            case "New Jersey":
                x = 29;
                break;
            case "New Mexico":
                x = 30;
                break;
            case "New York":
                x = 31;
                break;
            case "North Carolina":
                x = 32;
                break;
            case "North Dakota":
                x = 33;
                break;
            case "Ohio":
                x = 34;
                break;
            case "Oklahoma":
                x = 35;
                break;
            case "Oregon":
                x = 36;
                break;
            case "Pennsylvania":
                x = 37;
                break;
            case "Rhode Island":
                x = 38;
                break;
            case "South Carolina":
                x = 39;
                break;
            case "South Dakota":
                x = 40;
                break;
            case "Tennessee":
                x = 41;
                break;
            case "Texas":
                x = 42;
                break;
            case "Utah":
                x = 43;
                break;
            case "Vermont":
                x = 44;
                break;
            case "Virginia":
                x = 45;
                break;
            case "Washington":
                x = 46;
                break;
            case "West Virginia":
                x = 47;
                break;
            case "Wisconsin":
                x = 48;
                break;
            case "Wyoming":
                x = 49;
                break;
            case "Hawaii":
                x = 52;
                break;
            case "Alaska":
                x = 54;
                break;
            ///////////////////
            case "Northwest Territories":
                x = 60;
                break;
            case "Alberta":
                x = 61;
                break;
            case "British Columbia":
                x = 62;
                break;
            case "Manitoba":
                x = 63;
                break;
            case "New Brunswic":
                x = 64;
                break;
            case "Nova Scotia":
                x = 65;
                break;
            case "Prince Edward Island":
                x = 66;
                break;
            case "Ontario":
                x = 67;
                break;
            case "Quâ”œÂ®bec":
                x = 68;
                break;
            case "Saskatchewan":
                x = 69;
                break;
            case "Nunavut":
                x = 70;
                break;
            case "Yukon":
                x = 71;
                break;
            case "Newfoundland and Labrador":
                x = 72;
                ///////////////
                break;
            case "England":
                x = 101;
                break;
            case "Wales":
                x = 102;
                break;
            case "Scotland":
                x = 103;
                break;
            case "Northern Ireland":
                x = 104;
                break;

        }


        return x;
    }


}
