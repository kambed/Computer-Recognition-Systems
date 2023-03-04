module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires com.sun.xml.bind;


    opens frontend to javafx.fxml;
    exports frontend;
}