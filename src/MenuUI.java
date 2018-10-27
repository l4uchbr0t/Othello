
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.omg.CORBA.BAD_INV_ORDER;

import javax.print.attribute.standard.RequestingUserName;
import java.util.ArrayList;

public class MenuUI extends Application {

    private Label header;
    private Button UvU, UvA, Exit;
    private GridPane alligngrid;


    public MenuUI(Stage stage){
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

        UvU = new Button("Spieler gegen Spieler");
        UvU.setFont(new Font("MPLUSRounded1c-Regular", 25));
        UvU.setPrefHeight(75);
        UvU.setPrefWidth(800);
        UvU.setLayoutX(100);
        UvU.setLayoutY(-50);
        UvU.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new personalizeUI(primaryStage);
                //new GUI(new UserTurn(0), new UserTurn(1));
            }
        });
        UvU.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        UvU.setTextFill(Color.rgb(255,255,255));
        UvU.setVisible(true);

        UvA = new Button("Spieler gegen Computer");
        UvA.setFont(new Font("MPLUSRounded1c-Regular", 25));
        UvA.setPrefHeight(75);
        UvA.setPrefWidth(800);
        UvA.setLayoutX(100);
        UvA.setLayoutY(100);
        UvA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new selectDifficultyUI(primaryStage);
            }
        });
        UvA.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        UvA.setTextFill(Color.rgb(255,255,255));
        UvA.setVisible(true);



        Exit = new Button("Spiel beenden");
        Exit.setFont(new Font("MPLUSRounded1c-Regular", 25));
        Exit.setPrefHeight(75);
        Exit.setPrefWidth(800);
        Exit.setLayoutX(100);
        Exit.setLayoutY(250);
        Exit.setCancelButton(true);
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        Exit.setStyle("-fx-background-color:#400101; -fx-opacity:1; -fx-border-color:#FFFFFF;");
        Exit.setTextFill(Color.rgb(255,255,255));
        Exit.setVisible(true);

        header = new Label("Othello");
        header.setFont(new Font("Century Gothic", 70));
        header.setAlignment(Pos.CENTER);
        header.setTextFill(Color.rgb(255,255,255));
        alligngrid.add(header, 1, 0);

        group.getChildren().add(UvU);
        group.getChildren().add(UvA);
        group.getChildren().add(Exit);

        alligngrid.setVgap(50);
        alligngrid.add(group, 1, 1);

        alligngrid.setStyle("-fx-background-color:#202020; -fx-opacity:1;");

        primaryStage.setMaximized(false);

        Scene scene = new Scene(alligngrid, 1000, 700, Color.rgb(176, 226, 233));

        primaryStage.setScene(scene);


        primaryStage.centerOnScreen();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

    }


}
