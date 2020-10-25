package practicumopdracht;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Mabel Project");
        stage.setWidth(640);
        stage.setHeight(480);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
