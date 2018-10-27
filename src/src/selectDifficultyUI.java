package src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class selectDifficultyUI extends Application {

    private Label header;
    private Button Mode1, Mode2;


    public selectDifficultyUI(){
        start(new Stage());
    }


    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");

        Group group = new Group();

        Mode1 = new Button("Schwiergkeitsgrad \"einfach\"");
        Mode1.setFont(new Font("MPLUSRounded1c-Regular", 20));
        Mode1.setPrefHeight(75);
        Mode1.setPrefWidth(600);
        Mode1.setLayoutX(100);
        Mode1.setLayoutY(150);
        Mode1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new GUI(new UserTurn(0), new AITurnEasy(1), Color.rgb(255,255,255), Color.rgb(0,0,0));
            }
        });
        Mode1.setVisible(true);
        group.getChildren().add(Mode1);

        Mode2 = new Button("Schwiergkeitsgrad \"schwer\"");
        Mode2.setFont(new Font("MPLUSRounded1c-Regular", 20));
        Mode2.setPrefHeight(75);
        Mode2.setPrefWidth(600);
        Mode2.setLayoutX(100);
        Mode2.setLayoutY(300);
        Mode2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new GUI(new UserTurn(0), new AITurnHard(1), Color.rgb(255,255,255), Color.rgb(0,0,0));
            }
        });
        Mode2.setVisible(true);
        group.getChildren().add(Mode2);

        header = new Label("Wähle die Schwierigkeit!");
        header.setFont(new Font("Arial", 34));
        header.setLayoutX(100);
        header.setLayoutY(50);

        group.getChildren().add(header);

        Scene scene = new Scene(group, 800, 500, Color.rgb(176, 226, 233));
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
                new MenuUI();
            }
        });

        primaryStage.show();
    }
}
