package com.psk.chess.projekt;

import com.psk.chess.projekt.figures.FigureNames;
import com.psk.chess.projekt.figures.Menus;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.psk.chess.projekt.Drawing.*;
import static com.psk.chess.projekt.MovingFigures.selectFigure;


public class GameLoop extends AnimationTimer {
    private Mouse.MouseCoordinates mouseCoordinatesRelative = new Mouse.MouseCoordinates();
    private Scene scene;
    private Pane pane;
    private Stage stage;
    private FigureNames[][] gameBoard;
    private Rectangle[][] figureTextures;
    private MovingFigures.SelectedFigure selectedFigure;
    public static Menus menus = Menus.MAIN_MENU;
    public static boolean allowFirstCreationOfButtons = true;

    public GameLoop(Scene scene, Pane pane, Stage stage, FigureNames[][] gameBoard, Rectangle[][] figureTextures, MovingFigures.SelectedFigure selectedFigure) {
        this.scene = scene;
        this.pane = pane;
        this.stage = stage;
        this.gameBoard = gameBoard;
        this.figureTextures = figureTextures;
        selectFigure(pane, scene, gameBoard, mouseCoordinatesRelative, selectedFigure, menus);
    }

    private void onlineMenu() {
        Button button_server = new Button("Create Room");
        Button button_join = new Button("Join Room");
        Button button_back = new Button("Go Back.");

        button_server.setOnAction(event -> {

        });
        button_join.setOnAction(event -> {

        });
        button_back.setOnAction(event -> {
            menus = Menus.MAIN_MENU;
        });

        button_server.setLayoutX(75.0);
        button_server.setLayoutY(150.0);
        button_join.setLayoutX(430);
        button_join.setLayoutY(150);
        button_back.setLayoutX(75.0);
        button_back.setLayoutY(430.0);

        pane.getChildren().add(button_server);
        pane.getChildren().add(button_join);
        pane.getChildren().add(button_back);
    }

    private void offlineGame() {
        updateFiguresOnBoard(gameBoard, figureTextures, pane);
        Mouse.getMouseCoordsRelativeToChessBoard(scene, mouseCoordinatesRelative);
        for (int i = 0; i < 8; i++) {
            if (gameBoard[0][i] == FigureNames.WHITEPAWN || gameBoard[7][i] == FigureNames.BLACKPAWN) {
                drawPromotionFigures(gameBoard, pane);
            }
        }
    }

    private void main_menu() {
        Button button_offline = new Button("Offline Game");
        Button button_online = new Button("Online Game");
        Button button_quit = new Button("Quit Game");

        button_offline.setOnAction(event -> {
            menus = Menus.OFFLINE_GAME;
        });
        button_online.setOnAction(event -> {
            menus = Menus.ONLINE_MENU;
        });
        button_quit.setOnAction(event -> {
            System.out.println("Quit Game");
            System.exit(0);
        });

        button_online.setLayoutX(75.0);
        button_online.setLayoutY(150.0);
        button_offline.setLayoutX(75.0);
        button_offline.setLayoutY(180.0);
        button_quit.setLayoutX(75.0);
        button_quit.setLayoutY(210.0);

        pane.getChildren().add(button_offline);
        pane.getChildren().add(button_online);
        pane.getChildren().add(button_quit);
    }

    private static void gameBoardSetToBasic(FigureNames[][] gameBoard) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                gameBoard[i][j] = FigureNames.EMPTY;
            }
        }
        gameBoard[0][0] = FigureNames.BLACKROOK;
        gameBoard[0][1] = FigureNames.BLACKHORSEY;
        gameBoard[0][2] = FigureNames.BLACKBISHOP;
        gameBoard[0][3] = FigureNames.BLACKQUEEN;
        gameBoard[0][4] = FigureNames.BLACKKING;
        gameBoard[0][5] = FigureNames.BLACKBISHOP;
        gameBoard[0][6] = FigureNames.BLACKHORSEY;
        gameBoard[0][7] = FigureNames.BLACKROOK;

        gameBoard[7][0] = FigureNames.WHITEROOK;
        gameBoard[7][1] = FigureNames.WHITEHORSEY;
        gameBoard[7][2] = FigureNames.WHITEBISHOP;
        gameBoard[7][3] = FigureNames.WHITEQUEEN;
        gameBoard[7][4] = FigureNames.WHITEKING;
        gameBoard[7][5] = FigureNames.WHITEBISHOP;
        gameBoard[7][6] = FigureNames.WHITEHORSEY;
        gameBoard[7][7] = FigureNames.WHITEROOK;

        for (int i = 0; i < 8; i++) {
            gameBoard[1][i] = FigureNames.BLACKPAWN;
            gameBoard[6][i] = FigureNames.WHITEPAWN;
        }
    }

    @Override
    public void handle(long now) {
        if(menus != Menus.MAIN_MENU) {
            pane.getChildren().clear();
        }
        if(pane.getChildren().isEmpty() || allowFirstCreationOfButtons || menus != Menus.MAIN_MENU) {
            createChessBoard(pane);
        }
        switch (menus) {
            case MAIN_MENU:
                if(pane.getChildren().isEmpty() || allowFirstCreationOfButtons){
                    gameBoardSetToBasic(gameBoard);
                    main_menu();
                    allowFirstCreationOfButtons = false;
                }
                break;
            case OFFLINE_GAME:
                offlineGame();
                break;
            case ONLINE_MENU:
                onlineMenu();
                break;
            case ONLINE_GAME:
                break;
        }

        pane.layout();
    }
}
