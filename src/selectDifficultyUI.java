

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class selectDifficultyUI extends Application {

    private Label header;
    private Button Mode1, Mode2;
    private GridPane alligngrid;


    public selectDifficultyUI(Stage stage){
        start(stage);
    }


    public void start(Stage primaryStage) {

        Group group = new Group();

        alligngrid = new GridPane();
        alligngrid.setPadding(new Insets(15));
        GridPane.setHalignment(group, HPos.CENTER);
        GridPane.setValignment(group, VPos.CENTER);
        alligngrid.getRowConstraints().add(0, new RowConstraints(75));
        alligngrid.setAlignment(Pos.CENTER);

        Mode1 = new Button("Schwiergkeitsgrad \"einfach\"");
        Mode1.setFont(new Font("MPLUSRounded1c-Regular", 20));
        Mode1.setPrefHeight(75);
        Mode1.setPrefWidth(600);
        Mode1.setLayoutX(100);
        Mode1.setLayoutY(150);
        Mode1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new GUI(new UserTurn(0), new AITurnEasy(1), Color.rgb(255,255,255), Color.rgb(0,0,0), primaryStage);
            }
        });
        Mode1.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        Mode1.setTextFill(Color.rgb(255,255,255));
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
                new GUI(new UserTurn(0), new AITurnHard(1), Color.rgb(255,255,255), Color.rgb(0,0,0), primaryStage);
            }
        });
        Mode2.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        Mode2.setTextFill(Color.rgb(255,255,255));
        Mode2.setVisible(true);
        group.getChildren().add(Mode2);

        header = new Label("Wähle die Schwierigkeit!");
        header.setFont(new Font("Century Gothic", 34));
        header.setLayoutX(100);
        header.setLayoutY(50);

        header.setTextFill(Color.rgb(255,255,255));

        group.getChildren().add(header);

        alligngrid.add(group, 1, 0);
        alligngrid.setStyle("-fx-background-color:#303030; -fx-opacity:1;");

        primaryStage.setMaximized(false);

        Scene scene = new Scene(alligngrid, 800, 500, Color.rgb(176, 226, 233));
        primaryStage.setScene(scene);


        primaryStage.centerOnScreen();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                new MenuUI(primaryStage);
            }
        });

    }
}
