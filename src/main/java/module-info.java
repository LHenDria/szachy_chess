module com.psk.chess.projekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.psk.chess.projekt to javafx.fxml;
    exports com.psk.chess.projekt;
    exports com.psk.chess.projekt.figures;
    opens com.psk.chess.projekt.figures to javafx.fxml;
}