module com.example.numbertoword {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;

    opens com.dranchuk.numbertoword to javafx.fxml;
    exports com.dranchuk.numbertoword;
}