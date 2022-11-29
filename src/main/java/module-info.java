module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.monfort to javafx.fxml;
    exports com.monfort;
}