module com.example.jgrafy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.jgrafy to javafx.fxml;
    exports com.example.jgrafy;
}