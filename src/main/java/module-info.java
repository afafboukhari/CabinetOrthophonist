module com.example.cabinetorthophonist {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires jdk.compiler;

    opens com.example.cabinetorthophonist to javafx.fxml;
    exports com.example.cabinetorthophonist;
}