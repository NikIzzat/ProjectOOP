module com.example.groupprojectoop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.groupprojectoop to javafx.fxml;
    exports com.example.groupprojectoop;
}