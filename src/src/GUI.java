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

public class GUI extends Application {

    private Board spielboard;
    private Rectangle rec;
    private Line[] lines;
    private AnchorPane anchor;
    private GridPane grid;
    private int[][] color;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");

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


        Button[][] btn = new Button[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                btn[i][j] = new Button();
                btn[i][j].setPrefHeight(100);
                btn[i][j].setPrefWidth(100);
                btn[i][j].setOpacity(0);
                btn[i][j].setVisible(true);
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Test!");
                    }
                });
                grid.add(btn[i][j], i, j);
            }
        }

        Circle[][] circles = new Circle[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                circles[i][j] = new Circle();
                circles[i][j].setRadius(49);
                circles[i][j].setFill(Color.BLACK);
                circles[i][j].setOpacity(0);
                circles[i][j].setVisible(false);
                grid.add(circles[i][j], i, j);
            }
        }


        group.getChildren().add(grid);


        Scene scene = new Scene(group, 1200, 1200, Color.GRAY);
        primaryStage.setScene(scene);

        primaryStage.show();


        //spielboard = new Board(new UserTurn(0), new AITurn(1));


    }
}
