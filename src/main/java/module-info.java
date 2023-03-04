module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires com.sun.xml.bind;

    opens pl.ksr.computerrecognitionsystems;
    exports pl.ksr.computerrecognitionsystems;
    exports pl.ksr.computerrecognitionsystems.model;
    opens pl.ksr.computerrecognitionsystems.model;
}