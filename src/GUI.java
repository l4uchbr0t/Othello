

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class GUI extends Application {

    private Board spielboard;
    private Line[] lines;
    private GridPane grid, alligngrid;
    private int[][] color;
    private Circle[][] circles;
    private Label stonecount, turncount;
    private Paint colorwhite, colorblack;
    private Turn Black, White;
    private Group group;
    private Button backtomenu, reset;
    private Rectangle rec;
    private Button[][] btn;
    private ColumnConstraints[] column;

    public GUI(Turn WhiteNew, Turn BlackNew, Paint one, Paint two, Stage stage) { //Konstruktor
        this.White = WhiteNew;
        this.Black = BlackNew;

        colorwhite = one;
        colorblack = two;

        group = new Group();

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(1000, 1000);
        for (int i = 0; i < 8; i++) {
            grid.addColumn(i);
            grid.addRow(i);
        }
        grid.setLayoutX(0);
        grid.setLayoutY(50);

        alligngrid = new GridPane();
        alligngrid.setPadding(new Insets(15));
        GridPane.setHalignment(group, HPos.CENTER);
        GridPane.setValignment(group, VPos.CENTER);
        alligngrid.getRowConstraints().add(0, new RowConstraints(75));
        alligngrid.setAlignment(Pos.CENTER);

        rec = new Rectangle(100, 150, 800, 800);
        rec.setFill(Color.rgb(100, 14, 14));
        group.getChildren().add(rec);

        lines = new Line[18];
        for (int i = 0; i < 9; i++) {
            lines[i] = new Line();
            lines[i].setStartX(i * 100 + 100);
            lines[i].setStartY(150);
            lines[i].setEndX(i * 100 + 100);
            lines[i].setEndY(950);
            group.getChildren().add(lines[i]);
        }
        int l = 0;
        for (int i = 9; i < 18; i++) {
            lines[i] = new Line();
            lines[i].setStartX(100);
            lines[i].setStartY(l * 100 + 150);
            lines[i].setEndX(900);
            lines[i].setEndY(l * 100 + 150);
            group.getChildren().add(lines[i]);
            l++;
        }

        turncount = new Label("Derzeit: ");
        turncount.setFont(new Font("MPLUSRounded1c-Regular", 30));
        turncount.setLayoutX(100);
        turncount.setLayoutY(25);
        turncount.setTextFill(Color.rgb(255,255,255));
        group.getChildren().add(turncount);

        stonecount = new Label("Derzeit: ");
        stonecount.setFont(new Font("MPLUSRounded1c-Regular", 30));
        stonecount.setLayoutX(300);
        stonecount.setLayoutY(25);
        stonecount.setTextFill(Color.rgb(255,255,255));
        group.getChildren().add(stonecount);

        btn = new Button[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final int x = i;
                final int y = j;
                btn[i][j] = new Button();
                btn[i][j].setPrefHeight(100);
                btn[i][j].setPrefWidth(100);
                btn[i][j].setOpacity(0);
                btn[i][j].setVisible(true);
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        setStone(x, y);
                    }
                });
                grid.add(btn[i][j], i, j);
            }
        }

        reset = new Button("Zurücksetzen");
        reset.setPrefSize(150, 70);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Zurückgesetzt!");
                alert.setHeaderText("Das Spiel wurde zurückgesetzt und kann nun von vorne beginnen!");
                alert.setContentText(spielboard.getWinnerATM());

                alert.showAndWait();
                spielboard = new Board(White, Black);
                update();
            }
        });
        reset.setFont(new Font("MPLUSRounded1c-Regular", 15));
        reset.setAlignment(Pos.CENTER);
        reset.setLayoutX(525);
        reset.setLayoutY(35);
        reset.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        reset.setTextFill(Color.rgb(255,255,255));

        backtomenu = new Button("Zurück zum Menü");
        backtomenu.setPrefSize(150, 70);

        backtomenu.setFont(new Font("MPLUSRounded1c-Regular", 15));
        backtomenu.setAlignment(Pos.CENTER);
        backtomenu.setLayoutX(725);
        backtomenu.setLayoutY(35);
        backtomenu.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        backtomenu.setTextFill(Color.rgb(255,255,255));

        circles = new Circle[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                circles[i][j] = new Circle();
                circles[i][j].setRadius(47);
                circles[i][j].setFill(Color.BLACK);
                circles[i][j].setOpacity(100);
                circles[i][j].setVisible(false);
                grid.setAlignment(Pos.CENTER);
                grid.add(circles[i][j], i, j);
            }
        }

        color = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                color[i][j] = -1;
            }
        }

        column = new ColumnConstraints[8];
        for (int i = 0; i < column.length; i++) {
            column[i] = new ColumnConstraints();
            column[i].setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(column[i]);
        }

        group.getChildren().add(grid);
        group.getChildren().add(reset);
        group.getChildren().add(backtomenu);

        alligngrid.add(group, 1, 0);
        alligngrid.setStyle("-fx-background-color:#303030; -fx-opacity:1;");

        start(stage);
    }

    public void start(Stage primaryStage) {

        backtomenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new MenuUI(primaryStage);
            }
        });

        Scene scene = new Scene(alligngrid, 1000, 1000, Color.rgb(176, 226, 233));
        primaryStage.setScene(scene);


        primaryStage.centerOnScreen();

        primaryStage.setMaximized(true);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
            }
        });

        spielboard = new Board(White, Black);

        update();

    }

    public void setStone(int x, int y) {
        if (spielboard.blackTurn.getStance() == 1) {
            if (spielboard.validturnavailable(0)) {
                if (spielboard.whiteTurn.validturn(x, y)) {
                    spielboard.onTurn(x, y, 0);
                } else {
                    return;
                }
                spielboard.setCurrentTeam(1);
            } else {
                spielboard.setCurrentTeam(1);
            }
            update();

            if (spielboard.countStones(0) != 2 || spielboard.countStones(1) != 2) {
                if (spielboard.validturnavailable(1)) {
                    spielboard.onTurn(0, 0, 1);
                    spielboard.setCurrentTeam(0);
                } else {
                    spielboard.setCurrentTeam(0);
                }
            }


            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };

            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    update();
                }
            });
            new Thread(sleeper).start();


        } else {
            if (spielboard.currentTeam == 0) {
                if (spielboard.validturnavailable(0)) {
                    if (spielboard.whiteTurn.validturn(x, y)) {
                        spielboard.onTurn(x, y, 0);
                        spielboard.setCurrentTeam(1);
                    }
                } else {
                    if (spielboard.validturnavailable(1)) {
                        spielboard.setCurrentTeam(1);
                    }
                }
                update();
            } else {
                if (spielboard.validturnavailable(1)) {
                    if (spielboard.blackTurn.validturn(x, y)) {
                        spielboard.onTurn(x, y, 1);
                        spielboard.setCurrentTeam(0);
                    }
                } else {
                    if (spielboard.validturnavailable(0)) {
                        spielboard.setCurrentTeam(0);
                    }
                }
                update();
            }
        }

    }

    public void update() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                color[i][j] = spielboard.getstate(i, j);
                if (color[i][j] == 0) {
                    circles[i][j].setFill(colorwhite);
                    circles[i][j].setStroke(Color.BLACK);
                    circles[i][j].setVisible(true);
                } else if (color[i][j] == 1) {
                    circles[i][j].setFill(colorblack);
                    circles[i][j].setStroke(Color.BLACK);
                    circles[i][j].setVisible(true);
                } else {
                    circles[i][j].setVisible(false);
                }
            }
        }

        writeturncount();

        stonecount.setText(spielboard.countStones(0) + " Steine \n" + +spielboard.countStones(1) + " Steine");


        if (!spielboard.validturnavailable(0) && !spielboard.validturnavailable(1)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Spiel beendet!");
            alert.setHeaderText("Das Spiel ist beendet!");
            alert.setContentText(spielboard.getWinner());

            alert.showAndWait();
            spielboard = new Board(White, Black);
            update();
        }
    }

    public void writeturncount() {
        if (spielboard.currentTeam == 0) {
            turncount.setText("> Spieler 1: \nSpieler 2: ");

        } else {
            turncount.setText("Spieler 1: \n> Spieler 2: ");
        }
    }
}
