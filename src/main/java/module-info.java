module com.example.glossary1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.glossary1 to javafx.fxml;
    exports com.example.glossary1;
}