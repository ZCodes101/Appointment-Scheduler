module C195test {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    exports sample.Views to javafx.fxml, javafx.graphics;

    opens sample.Model to javafx.base;
    opens sample.Views to javafx.fxml;
}