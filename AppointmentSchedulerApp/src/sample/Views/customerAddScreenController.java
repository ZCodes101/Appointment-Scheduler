package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.utils.CustomersDB;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The type Customer add screen controller.
 */
public class customerAddScreenController implements Initializable {


    @FXML
    private ComboBox<String> divCombo;


    @FXML
    private ComboBox<String> country;

    /**
     * The Countries.
     */
    ObservableList<String> countries = FXCollections.observableArrayList("UK", "Canada", "United States");


    /**
     * Pick div.
     */
    @FXML
    public void pickDiv() {
        String currentDiv = divCombo.getSelectionModel().getSelectedItem();


        try {


            if (currentDiv.equals("England") || currentDiv.equals("Wales") || currentDiv.equals("Scotland") || currentDiv.equals("Northern Ireland")) {
                country.getSelectionModel().select(0);
            } else if (currentDiv.equals("Northwest Territories") || currentDiv.equals("Alberta") || currentDiv.equals("British Columbia") || currentDiv.equals("Manitoba") || currentDiv.equals("New Brunswick") ||
                    currentDiv.equals("Nova Scotia") || currentDiv.equals("Prince Edward Island") || currentDiv.equals("Ontario")
                    || currentDiv.equals("Quâ”œÂ®bec") || currentDiv.equals("Saskatchewan") || currentDiv.equals("Nunavut") || currentDiv.equals("Yukon") || currentDiv.equals("Newfoundland and Labrador")) {

                country.getSelectionModel().select(1);

            } else {
                country.getSelectionModel().select(2);


            }

        } catch (NullPointerException ignored) {

        }

    }

    /**
     * Pick country.
     */
    @FXML
    public void pickCountry() {
        int countryString = country.getSelectionModel().getSelectedIndex();


        if (countryString == 0) {
            divCombo.setItems(divsUk);

        } else if (countryString == 1) {
            divCombo.setItems(divsCA);
        } else if (countryString == 2) {
            divCombo.setItems(divsUS);
        }


    }


    @FXML
    private TextField name;

    @FXML
    private TextField address;


    @FXML
    private TextField zip;

    @FXML
    private TextField phone;


    /**
     * Exit.
     *
     * @param event the event
     */
    @FXML
    void exit(ActionEvent event) {

        exitAlert(event);

    }

    /**
     * Exit alert.
     *
     * @param event the event
     */
    static void exitAlert(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Any input that is not saved will be cleared. Continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {


            Stage stage;
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    /**
     * Save customer.
     *
     * @param event the event
     */
    @FXML
    void saveCustomer(ActionEvent event) {

        try {


            if (name.getText().isEmpty() || address.getText().isEmpty() || zip.getText().isEmpty() || phone.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();


            } else {

                CustomersDB.saveNewCustomer(name.getText(), address.getText(), zip.getText(), phone.getText(), switchDivNameNums());


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


    private final ObservableList<String> divsUS = FXCollections.observableArrayList(
            "Alabama", "Arizona", "Arkansas", "Alaska",
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
            "Hawaii");
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
            "Yukon",
            "Newfoundland and Labrador"

    );
    private final ObservableList<String> divsUk = FXCollections.observableArrayList(

            "England",
            "Wales",
            "Scotland",
            "Northern Ireland");


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        country.setItems(countries);

    }


    /**
     * Switch div name nums int.
     *
     * @return the int
     */
    public int switchDivNameNums() {
        String currentDiv = divCombo.getSelectionModel().getSelectedItem();
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
            case "New Brunswick":
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
