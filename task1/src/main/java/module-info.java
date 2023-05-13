module pl.ksr.computerrecognitionsystems {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires jakarta.xml.bind;
    requires com.opencsv;
    requires org.apache.commons.lang3;
    requires java.logging;

    opens frontend to javafx.fxml;
    exports frontend;
    exports backend;
    exports backend.reader;
    exports backend.extractor;
    exports backend.model;
    exports backend.process;
    exports backend.model.adapter;
    exports backend.knn.metric;
    exports backend.knn.measure;
    opens backend.reader;
    opens backend.model;
}