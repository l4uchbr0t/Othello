package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.attribute.standard.RequestingUserName;
import java.util.ArrayList;

public class GUI extends Application {

    private Board spielboard;
    private Rectangle rec;
    private Line[] lines;
    private AnchorPane anchor;
    private GridPane grid;
    private int[][] color;
    private Circle[][] circles;
    private Label team;
    ArrayList<Position> test;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");

        test = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                test.add(new Position(i, j));
            }
        }

        Group group = new Group();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(1000, 1000);
        for (int i = 0; i < 8; i++) {
            grid.addColumn(i);
            grid.addRow(i);
        }
        grid.setLayoutX(100);
        grid.setLayoutY(100);


        Rectangle rec = new Rectangle(200, 200, 800, 800);
        rec.setFill(Color.GREEN);
        group.getChildren().add(rec);

        lines = new Line[18];
        for (int i = 0; i < 9; i++) {
            lines[i] = new Line();
            lines[i].setStartX(i * 100 + 200);
            lines[i].setStartY(200);
            lines[i].setEndX(i * 100 + 200);
            lines[i].setEndY(1000);
            group.getChildren().add(lines[i]);
        }
        int l = 0;
        for (int i = 9; i < 18; i++) {
            lines[i] = new Line();
            lines[i].setStartX(200);
            lines[i].setStartY(l * 100 + 200);
            lines[i].setEndX(1000);
            lines[i].setEndY(l * 100 + 200);
            group.getChildren().add(lines[i]);
            l++;
        }

        team = new Label("Zug von Team Weiß");
        team.setFont(new Font("Arial", 30));
        team.setLayoutX(20);
        team.setLayoutY(20);
        group.getChildren().add(team);

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

        circles = new Circle[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                circles[i][j] = new Circle();
                circles[i][j].setRadius(49);
                circles[i][j].setFill(Color.BLACK);
                circles[i][j].setOpacity(100);
                circles[i][j].setVisible(false);
                grid.add(circles[i][j], i, j);
            }
        }

        color = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                color[i][j] = -1;
            }
        }


        group.getChildren().add(grid);


        Scene scene = new Scene(group, 1200, 1200, Color.GRAY);
        primaryStage.setScene(scene);

        primaryStage.show();


        spielboard = new Board(new UserTurn(0), new AITurn(1));

        update();

    }

    public void setStone(int x, int y) {
        if (spielboard.blackTurn.getStance() == 1) {
            if (validturnavailable(0)) {
                if (spielboard.whiteTurn.validturn(x, y)) {
                    spielboard.onTurn(x, y, 0);
                }else{
                    return;
                }
            }
            update();
            if (validturnavailable(1)) {
                spielboard.onTurn(0, 0, 1);
            }
            update();
        } else {
            if (spielboard.currentTeam == 0) {
                if (validturnavailable(0)) {
                    if (spielboard.whiteTurn.validturn(x, y)) {
                        spielboard.onTurn(x, y, 0);
                        update();
                    }
                } else {
                    if (validturnavailable(1)) {
                        spielboard.onTurn(x, y, 1);
                        update();
                    }
                }
            } else {
                if (validturnavailable(1)) {
                    if (spielboard.blackTurn.validturn(x, y)) {
                        spielboard.onTurn(x, y, 1);
                        update();
                    }
                } else {
                    if (validturnavailable(0)) {
                        if (spielboard.whiteTurn.validturn(x, y)) {
                            spielboard.onTurn(x, y, 0);
                            update();
                        }
                    }
                }
            }
        }

    }


    public boolean validturnavailable(int team) {

        for (Position p : test) {
            if (spielboard.board[p.getX()][p.getY()] == -1 && spielboard.castRays(team, p, false)) {
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
                    circles[i][j].setFill(Color.WHITE);
                    circles[i][j].setVisible(true);
                } else if (color[i][j] == 1) {
                    circles[i][j].setFill(Color.BLACK);
                    circles[i][j].setVisible(true);
                } else {
                    circles[i][j].setVisible(false);
                }
            }
        }
        if (spielboard.currentTeam == 0) {
            team.setText("Zug von Team Weiß");
        } else {
            team.setText("Zug von Team Schwarz");
        }
    }
}
