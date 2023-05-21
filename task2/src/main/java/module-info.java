module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires jakarta.xml.bind;
    requires com.opencsv;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    opens frontend to javafx.fxml;
    exports frontend;
    exports frontend.utils;
    exports frontend.model;
    exports backend.lingustic.quantifier;
    exports backend.lingustic;
    opens frontend.utils to javafx.fxml;
}