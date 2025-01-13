module com.psk.chess.projekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    requires org.slf4j;
    requires org.java_websocket;
    requires java.net.http;

    opens com.psk.chess.projekt to javafx.fxml;
    exports com.psk.chess.projekt;
    exports com.psk.chess.projekt.figures;
    exports com.psk.chess.projekt.api;
    opens com.psk.chess.projekt.figures to javafx.fxml;
    exports com.psk.chess.projekt.figures.checks;
    opens com.psk.chess.projekt.figures.checks to javafx.fxml;
    exports com.psk.chess.projekt.menus;
    opens com.psk.chess.projekt.menus to javafx.fxml;
    exports com.psk.chess.projekt.mouse;
    opens com.psk.chess.projekt.mouse to javafx.fxml;
}