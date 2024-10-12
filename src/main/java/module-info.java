module com.psk.chess.projekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.psk.chess.projekt to javafx.fxml;
    exports com.psk.chess.projekt;
}