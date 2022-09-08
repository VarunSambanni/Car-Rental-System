module com.example.javafxproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxproj to javafx.fxml;
    exports com.example.javafxproj;
}