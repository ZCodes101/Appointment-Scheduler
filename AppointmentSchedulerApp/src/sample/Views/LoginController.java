package sample.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.utils.UsersDB;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * The type Login controller.
 */
public class LoginController implements Initializable {


    @FXML
    private Label userIdTextLbl;

    @FXML
    private Button submitButton;

    @FXML
    private Label enterPasswordLbl;

    @FXML
    private TextField countryCode;


    private String errorHeader;
    private String errorTitle;
    private String errorText;

    @FXML
    private TextField userIdTextField;


    @FXML
    private TextField enterPasswordTextField;


    /**
     * Try login.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    public void tryLogin(ActionEvent event) throws IOException {
        String username = userIdTextField.getText();
        String password = enterPasswordTextField.getText();
        boolean isValid = UsersDB.login(username, password);


        if (isValid) {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(errorHeader);
            alert.setContentText(errorText);
            alert.showAndWait();
        }


    }


    @FXML
    private Label signInLabel;

    @FXML
    private Label language;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale currentLocale = Locale.getDefault();

        // countryCode.setText(Locale.getDefault().getISO3Country());
        countryCode.setText(currentLocale.getCountry());

        //currentLocale.getCountry()

        Locale locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("sample/language/login", locale);
        userIdTextLbl.setText(resourceBundle.getString("Username"));
        enterPasswordLbl.setText(resourceBundle.getString("Password"));
        submitButton.setText(resourceBundle.getString("submit"));


        signInLabel.setText(resourceBundle.getString("Login"));

        try {


            language.setText(resourceBundle.getString("language"));
        } catch (NullPointerException e) {
            System.out.println("Null Point for language label");
        }


        errorHeader = resourceBundle.getString("errorHeader");
        errorTitle = resourceBundle.getString("errorTitle");
        errorText = resourceBundle.getString("errorText");

    }

}
