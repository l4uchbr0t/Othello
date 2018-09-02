package src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class GUI extends Application {

    private Board spielboard;
    private Rectangle rec;
    private Line[] lines;
    private AnchorPane anchor;
    private GridPane grid;
    private int[][] color;
    private Circle[][] circles;
    ArrayList<Position> checkposition;
    private Label stonecount, turncount;
    private Paint colorwhite, colorblack;


    public GUI(Turn White, Turn Black, Paint one, Paint two) {
        start(new Stage(), White, Black, one, two);
    }

    public void start(Stage primaryStage) {

    }

    public void start(Stage primaryStage, Turn White, Turn Black, Paint one, Paint two) {
        primaryStage.setTitle("Othello");

        colorwhite = one;
        colorblack = two;

        checkposition = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                checkposition.add(new Position(i, j));
            }
        }

        Group group = new Group();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setPrefSize(1000, 1000);
        for (int i = 0; i < 8; i++) {
            grid.addColumn(i);
            grid.addRow(i);
        }
        grid.setLayoutX(0);
        grid.setLayoutY(50);

        Rectangle rec = new Rectangle(100, 150, 800, 800);
        rec.setFill(Color.rgb(126, 192, 238));
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
        group.getChildren().add(turncount);

        stonecount = new Label("Derzeit: ");
        stonecount.setFont(new Font("MPLUSRounded1c-Regular", 30));
        stonecount.setLayoutX(300);
        stonecount.setLayoutY(25);
        group.getChildren().add(stonecount);

        Button[][] btn = new Button[8][8];
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

        Button reset = new Button("Zurücksetzen");
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

        Button backtomenu = new Button("Zurück zum Menü");
        backtomenu.setPrefSize(150, 70);
        backtomenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new MenuUI();
            }
        });
        backtomenu.setFont(new Font("MPLUSRounded1c-Regular", 15));
        backtomenu.setAlignment(Pos.CENTER);
        backtomenu.setLayoutX(725);
        backtomenu.setLayoutY(35);


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

        ColumnConstraints column[] = new ColumnConstraints[8];
        for (int i = 0; i < column.length; i++) {
            column[i] = new ColumnConstraints();
            column[i].setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(column[i]);
        }

        grid.setAlignment(Pos.CENTER);

        group.getChildren().add(grid);
        group.getChildren().add(reset);
        group.getChildren().add(backtomenu);

        Scene scene = new Scene(group, 1000, 1000, Color.rgb(176, 226, 233));
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
            }
        });

        primaryStage.show();

        spielboard = new Board(White, Black);

        update();

    }

    public void setStone(int x, int y) {
        if (spielboard.blackTurn.getStance() == 1) {
            if (validturnavailable(0)) {
                if (spielboard.whiteTurn.validturn(x, y)) {
                    spielboard.onTurn(x, y, 0);
                } else {
                    return;
                }
                spielboard.setCurrentTeam(1);
            }else{
                spielboard.setCurrentTeam(1);
            }
            update();


            if (validturnavailable(1)) {
                spielboard.onTurn(0, 0, 1);
                spielboard.setCurrentTeam(0);
            }else{
                spielboard.setCurrentTeam(0);
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
                if (validturnavailable(0)) {
                    if (spielboard.whiteTurn.validturn(x, y)) {
                        spielboard.onTurn(x, y, 0);
                        spielboard.setCurrentTeam(1);
                    }
                } else {
                    spielboard.setCurrentTeam(1);
                }
                update();
            } else {
                if (validturnavailable(1)) {
                    if (spielboard.blackTurn.validturn(x, y)) {
                        spielboard.onTurn(x, y, 1);
                        spielboard.setCurrentTeam(0);
                    }
                } else {
                    spielboard.setCurrentTeam(0);
                }
                update();
            }
        }

    }

    public boolean validturnavailable(int teamint) {
        for (Position p : checkposition) {
            if (spielboard.board[p.getX()][p.getY()] == -1 && spielboard.castRays(teamint, p, false)) {
                return true;
            }
        }
        return false;
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

        stonecount.setText(spielboard.countStones(0) + " Steine \n" +  + spielboard.countStones(1) + " Steine");


        if (!validturnavailable(0) && !validturnavailable(1)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Spiel beendet!");
            alert.setHeaderText("Das Spiel ist beendet!");
            alert.setContentText(spielboard.getWinner());

            alert.showAndWait();
        }
    }
    public void writeturncount(){
        if (spielboard.currentTeam == 0) {
            turncount.setText("> Spieler 1: \nSpieler 2: ");

        } else {
            turncount.setText("Spieler 1: \n> Spieler 2: ");
        }
    }
}
