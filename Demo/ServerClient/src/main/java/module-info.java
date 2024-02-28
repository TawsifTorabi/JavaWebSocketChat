module com.tt.serverclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tt.serverclient to javafx.fxml;
    exports com.tt.serverclient;
}