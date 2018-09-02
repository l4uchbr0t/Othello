package src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
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
import javafx.stage.WindowEvent;

import javax.print.attribute.standard.RequestingUserName;
import java.util.ArrayList;

public class MenuUI extends Application {

    private Label header;
    private Button UvU, UvA1, Exit;

    public MenuUI(){
        start(new Stage());
    }


    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");

        Group group = new Group();

        UvU = new Button("Spieler gegen Spieler");
        UvU.setFont(new Font("MPLUSRounded1c-Regular", 25));
        UvU.setPrefHeight(75);
        UvU.setPrefWidth(800);
        UvU.setLayoutX(100);
        UvU.setLayoutY(200);
        UvU.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new personalizeUI();
                //new GUI(new UserTurn(0), new UserTurn(1));
            }
        });
        UvU.setVisible(true);

        UvA1 = new Button("Spieler gegen Computer");
        UvA1.setFont(new Font("MPLUSRounded1c-Regular", 25));
        UvA1.setPrefHeight(75);
        UvA1.setPrefWidth(800);
        UvA1.setLayoutX(100);
        UvA1.setLayoutY(350);
        UvA1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new selectDifficultyUI();
            }
        });
        UvA1.setVisible(true);



        Exit = new Button("Spiel beenden");
        Exit.setFont(new Font("MPLUSRounded1c-Regular", 25));
        Exit.setPrefHeight(75);
        Exit.setPrefWidth(800);
        Exit.setLayoutX(100);
        Exit.setLayoutY(500);
        Exit.setCancelButton(true);
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        Exit.setVisible(true);

        header = new Label("Othello");
        header.setFont(new Font("Century Gothic", 70));
        header.setLayoutX(100);
        header.setLayoutY(50);

        group.getChildren().add(header);
        group.getChildren().add(UvU);
        group.getChildren().add(UvA1);
        group.getChildren().add(Exit);


        Scene scene = new Scene(group, 1000, 700, Color.rgb(176, 226, 233));
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.show();
    }


}
