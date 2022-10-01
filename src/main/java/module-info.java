module com.example.numbertoword {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dranchuk.numbertoword to javafx.fxml;
    exports com.dranchuk.numbertoword;
}