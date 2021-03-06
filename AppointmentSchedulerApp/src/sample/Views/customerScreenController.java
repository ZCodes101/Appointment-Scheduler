package sample.Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Model.Customers;
import sample.utils.CustomersDB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Customer screen controller.
 */
public class customerScreenController implements Initializable {

    @FXML
    private TableView<Customers> customersTable;

    @FXML
    private TableColumn<Customers, Integer> customerID;

    @FXML
    private TableColumn<Customers, String> customerName;

    @FXML
    private TableColumn<Customers, String> customerPhone;

    @FXML
    private TableColumn<Customers, String> address;
    @FXML
    private TableColumn<Customers, String> zipCode;
    @FXML
    private TableColumn<Customers, String> div;


    private static final ObservableList<Customers> allCustomers = FXCollections.observableArrayList();


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


    /**
     * Add customer.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void addCustomer(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("customerAddScreen.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Customer Management Options");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }


    @FXML
    private AnchorPane main;


    /**
     * Modify.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void modify(ActionEvent event) throws IOException {


        Customers x = customersTable.getSelectionModel().getSelectedItem();


        if (x == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please Select An Item To Modify!");

            alert.showAndWait();


        } else {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("customerModifyScreen.fxml"));
            loader.load();
            customerModifyScreenController msc = loader.getController();
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

    private Customers selected;

    /**
     * Delete customer.
     */
    @FXML
    void deleteCustomer() {


        try {


            if (customersTable.getSelectionModel().getSelectedItem() == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Select an Customer to delete.");


                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK);


            } else if (customersTable.getSelectionModel().getSelectedItem() != null) {
                selected = customersTable.getSelectionModel().getSelectedItem();
            } else {
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete Customer Data");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setContentText("This Will Delete: " + selected.getCustomerName() + " Data and all appointments attached to it. Continue?");
            alert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                    CustomersDB.deleteCustomer(selected.getCustomerID());
                    customersTable.setItems(CustomersDB.getAllCustomers());


                    Alert alert2 = new Alert(Alert.AlertType.NONE);
                    alert2.setTitle("Notice");
                    alert2.getDialogPane().getButtonTypes().add(ButtonType.OK);

                    alert2.setHeaderText("Delete Appointment Data");
                    alert2.setContentText("Customer: " + selected.getCustomerName() + " was deleted.");
                    alert2.showAndWait();


                }
            }));


        } catch (NullPointerException e) {
            System.out.println(e.getMessage());


        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        // address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        zipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

        div.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

        customersTable.setItems(CustomersDB.getAllCustomers());
    }


    /**
     * Refresh.
     */
    public void refresh() {
        allCustomers.clear();

        CustomersDB.getAllCustomers();

    }


}
