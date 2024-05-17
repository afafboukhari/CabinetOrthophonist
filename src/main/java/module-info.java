module com.example.cabinetorthophonist {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.cabinetorthophonist to javafx.fxml;
    exports com.example.cabinetorthophonist;
}