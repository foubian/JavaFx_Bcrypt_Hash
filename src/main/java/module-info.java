module com.loginfxmysql.loginfxmysql {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.logging;
    requires com.jfoenix;
    requires java.sql;
    requires jbcrypt;

    opens com.loginfxmysql.loginfxmysql to javafx.fxml;
    exports com.loginfxmysql.loginfxmysql;
}