package com.psk.chess.projekt;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class Mouse {
    static double getXMousePosition() {
        // Wartości są zwracane w relacji do systemu nie okna.
        return MouseInfo.getPointerInfo().getLocation().getX();
    }

    static double getYMousePosition() {
        return MouseInfo.getPointerInfo().getLocation().getY();
    }
}
