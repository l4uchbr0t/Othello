package src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class personalizeUI extends Application {

    private Label header, playerone, playertwo;

    public personalizeUI(){
        start(new Stage());
    }


    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");

        Group group = new Group();

        header = new Label("Wähle deinen Look!");
        header.setFont(new Font("Century Gothic", 50));
        header.setAlignment(Pos.CENTER);
        header.setPrefSize(600, 50);
        header.setLayoutX(0);
        header.setLayoutY(20);
        group.getChildren().add(header);

        playerone = new Label("Spieler 1");
        playerone.setFont(new Font("Century Gothic", 30));
        playerone.setAlignment(Pos.CENTER);
        playerone.setPrefSize(300, 50);
        playerone.setLayoutX(0);
        playerone.setLayoutY(80);
        group.getChildren().add(playerone);

        playertwo = new Label("Spieler 2");
        playertwo.setFont(new Font("Century Gothic", 30));
        playertwo.setAlignment(Pos.CENTER);
        playertwo.setPrefSize(300, 50);
        playertwo.setLayoutX(300);
        playertwo.setLayoutY(80);
        group.getChildren().add(playertwo);

        Rectangle rec1 = new Rectangle(25, 150, 250, 250);
        rec1.setFill(Color.TRANSPARENT);
        rec1.setStroke(Color.BLACK);
        group.getChildren().add(rec1);

        Rectangle rec2 = new Rectangle(325, 150, 250, 250);
        rec2.setFill(Color.TRANSPARENT);
        rec2.setStroke(Color.BLACK);
        group.getChildren().add(rec2);

        Circle circle1 = new Circle(150, 275, 100);
        circle1.setFill(Color.WHITE);
        circle1.setStroke(Color.BLACK);
        group.getChildren().add(circle1);

        Circle circle2 = new Circle(450, 275, 100);
        circle2.setFill(Color.BLACK);
        circle2.setStroke(Color.BLACK);
        group.getChildren().add(circle2);


        Rectangle[] ColorsOne = new Rectangle[14];
        for (int i = 0; i < ColorsOne.length; i++) {
            ColorsOne[i] = new Rectangle(60, 60);
            group.getChildren().add(ColorsOne[i]);
        }
        for (int i = 0; i < 4; i++) {
            ColorsOne[i].setStroke(Color.BLACK);
            ColorsOne[i].setX(15 + i*70);
            ColorsOne[i].setY(425);
        }
        int a = 0;
        for (int i = 4; i < 8; i++) {
            ColorsOne[i].setStroke(Color.BLACK);
            ColorsOne[i].setX(15 + a*70);
            ColorsOne[i].setY(500);
            a++;
        }
        int b = 0;
        for (int i = 8; i < 12; i++) {
            ColorsOne[i].setStroke(Color.BLACK);
            ColorsOne[i].setX(15 + b*70);
            ColorsOne[i].setY(575);
            b++;
        }
        ColorsOne[12].setStroke(Color.BLACK);
        ColorsOne[12].setX(15 + 1*70);
        ColorsOne[12].setY(650);

        ColorsOne[13].setStroke(Color.BLACK);
        ColorsOne[13].setX(15 + 2*70);
        ColorsOne[13].setY(650);

        ColorsOne[0].setFill(Color.rgb(138, 43, 226));
        ColorsOne[1].setFill(Color.rgb(65, 105, 225));
        ColorsOne[2].setFill(Color.rgb(0, 134, 139));
        ColorsOne[3].setFill(Color.rgb(72, 209, 204));
        ColorsOne[4].setFill(Color.rgb(110, 139, 61));
        ColorsOne[5].setFill(Color.rgb(0, 255, 0));
        ColorsOne[6].setFill(Color.rgb(255, 255, 0));
        ColorsOne[7].setFill(Color.rgb(255, 215, 0));
        ColorsOne[8].setFill(Color.rgb(255, 181, 197));
        ColorsOne[9].setFill(Color.rgb(205, 16, 118));
        ColorsOne[10].setFill(Color.rgb(205, 51, 51));
        ColorsOne[11].setFill(Color.rgb(139, 54, 38));
        ColorsOne[12].setFill(Color.rgb(0, 0, 0));
        ColorsOne[13].setFill(Color.rgb(255, 255, 255));



        Rectangle[] ColorsTwo = new Rectangle[14];
        for (int i = 0; i < ColorsTwo.length; i++) {
            ColorsTwo[i] = new Rectangle(60, 60);
            group.getChildren().add(ColorsTwo[i]);
        }
        for (int i = 0; i < 4; i++) {
            ColorsTwo[i].setStroke(Color.BLACK);
            ColorsTwo[i].setX(525 - i*70);
            ColorsTwo[i].setY(425);
        }
        int e = 0;
        for (int i = 4; i < 8; i++) {
            ColorsTwo[i].setStroke(Color.BLACK);
            ColorsTwo[i].setX(525 - e*70);
            ColorsTwo[i].setY(500);
            e++;
        }
        int f = 0;
        for (int i = 8; i < 12; i++) {
            ColorsTwo[i].setStroke(Color.BLACK);
            ColorsTwo[i].setX(525 - f*70);
            ColorsTwo[i].setY(575);
            f++;
        }
        ColorsTwo[12].setStroke(Color.BLACK);
        ColorsTwo[12].setX(525 - 1*70);
        ColorsTwo[12].setY(650);

        ColorsTwo[13].setStroke(Color.BLACK);
        ColorsTwo[13].setX(525 - 2*70);
        ColorsTwo[13].setY(650);

        ColorsTwo[0].setFill(Color.rgb(138, 43, 226));
        ColorsTwo[1].setFill(Color.rgb(65, 105, 225));
        ColorsTwo[2].setFill(Color.rgb(0, 134, 139));
        ColorsTwo[3].setFill(Color.rgb(72, 209, 204));
        ColorsTwo[4].setFill(Color.rgb(110, 139, 61));
        ColorsTwo[5].setFill(Color.rgb(0, 255, 0));
        ColorsTwo[6].setFill(Color.rgb(255, 255, 0));
        ColorsTwo[7].setFill(Color.rgb(255, 215, 0));
        ColorsTwo[8].setFill(Color.rgb(255, 181, 197));
        ColorsTwo[9].setFill(Color.rgb(205, 16, 118));
        ColorsTwo[10].setFill(Color.rgb(205, 51, 51));
        ColorsTwo[11].setFill(Color.rgb(139, 54, 38));
        ColorsTwo[12].setFill(Color.rgb(0, 0, 0));
        ColorsTwo[13].setFill(Color.rgb(255, 255, 255));



        Button[] ButtonsOne = new Button[14];
        for (int i = 0; i < ButtonsOne.length; i++) {
            ButtonsOne[i] = new Button();
            ButtonsOne[i].setPrefSize(60, 60);
            ButtonsOne[i].setOpacity(0);
            final int temp = i;
            ButtonsOne[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(ColorsOne[temp].getFill().equals(circle2.getFill())){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Fehler!");
                        alert.setHeaderText("Diese Farbe kannst du nicht auswählen!");
                        alert.setContentText("Du kannst diese Farbe gerade nicht auswählen, da Spieler 2 bereits diese Farbe ausgewählt hat! Bitte wähle eine andere Farbe.");

                        alert.showAndWait();
                    }else {
                        circle1.setFill(ColorsOne[temp].getFill());
                    }
                }
            });
            group.getChildren().add(ButtonsOne[i]);
        }
        for (int i = 0; i < 4; i++) {
            ButtonsOne[i].setLayoutX(15 + i*70);
            ButtonsOne[i].setLayoutY(425);
        }
        int c = 0;
        for (int i = 4; i < 8; i++) {
            ButtonsOne[i].setLayoutX(15 + c*70);
            ButtonsOne[i].setLayoutY(500);
            c++;
        }
        int d = 0;
        for (int i = 8; i < 12; i++) {
            ButtonsOne[i].setLayoutX(15 + d*70);
            ButtonsOne[i].setLayoutY(575);
            d++;
        }
        ButtonsOne[12].setLayoutX(15 + 1*70);
        ButtonsOne[12].setLayoutY(650);

        ButtonsOne[13].setLayoutX(15 + 2*70);
        ButtonsOne[13].setLayoutY(650);



        Button[] ButtonsTwo = new Button[14];
        for (int i = 0; i < ButtonsTwo.length; i++) {
            ButtonsTwo[i] = new Button();
            ButtonsTwo[i].setPrefSize(60, 60);
            ButtonsTwo[i].setOpacity(0);
            final int temp = i;
            ButtonsTwo[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(ColorsTwo[temp].getFill().equals(circle1.getFill())){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Fehler!");
                        alert.setHeaderText("Diese Farbe kannst du nicht auswählen!");
                        alert.setContentText("Du kannst diese Farbe gerade nicht auswählen, da Spieler 1 bereits diese Farbe ausgewählt hat! Bitte wähle eine andere Farbe.");

                        alert.showAndWait();
                    }else {
                        circle2.setFill(ColorsTwo[temp].getFill());
                    }
                }
            });
            group.getChildren().add(ButtonsTwo[i]);
        }
        for (int i = 0; i < 4; i++) {
            ButtonsTwo[i].setLayoutX(525 - i*70);
            ButtonsTwo[i].setLayoutY(425);
        }
        int g = 0;
        for (int i = 4; i < 8; i++) {
            ButtonsTwo[i].setLayoutX(525 - g*70);
            ButtonsTwo[i].setLayoutY(500);
            g++;
        }
        int h = 0;
        for (int i = 8; i < 12; i++) {
            ButtonsTwo[i].setLayoutX(525 - h*70);
            ButtonsTwo[i].setLayoutY(575);
            h++;
        }
        ButtonsTwo[12].setLayoutX(525 - 1*70);
        ButtonsTwo[12].setLayoutY(650);

        ButtonsTwo[13].setLayoutX(525 - 2*70);
        ButtonsTwo[13].setLayoutY(650);


        Button back = new Button("Zurück");
        back.setPrefSize(250, 75);
        back.setLayoutX(25);
        back.setLayoutY(750);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new MenuUI();
            }
        });
        group.getChildren().add(back);

        Button cont = new Button("Weiter");
        cont.setPrefSize(250, 75);
        cont.setLayoutX(325);
        cont.setLayoutY(750);
        cont.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new GUI(new UserTurn(0), new UserTurn(1), circle1.getFill(), circle2.getFill());
            }
        });
        group.getChildren().add(cont);


        Scene scene = new Scene(group, 600, 850, Color.rgb(176, 226, 233));
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
