
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MenuUI(primaryStage);
        primaryStage.setTitle("Othello");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.centerOnScreen();

        primaryStage.show();


    }
}
