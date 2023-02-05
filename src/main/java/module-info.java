module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.ksr.computerrecognitionsystems to javafx.fxml;
    exports pl.ksr.computerrecognitionsystems;
}