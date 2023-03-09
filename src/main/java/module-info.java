module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires lombok;
    requires com.opencsv;
    requires org.apache.commons.lang3;

    opens frontend to javafx.fxml;
    exports frontend;
    exports backend;
    exports backend.reader;
    exports backend.extractor;
    exports backend.model;
    exports backend.process;
    exports backend.process.exception;
    exports backend.model.adapter;
    opens backend.reader;
    opens backend.model;
}