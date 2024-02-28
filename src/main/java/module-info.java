module com.tawsiftorabi.websocketchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tawsiftorabi.websocketchat to javafx.fxml;
    exports com.tawsiftorabi.websocketchat;
}