module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires jakarta.xml.bind;
    requires com.opencsv;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires java.logging;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens frontend to javafx.fxml;
    exports frontend;
}