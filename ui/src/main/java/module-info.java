module edu.jimei.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires main;


    opens util.ui to javafx.fxml;
    exports util.ui;
}