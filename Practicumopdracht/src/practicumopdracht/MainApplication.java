package practicumopdracht;

import controllers.StartController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApplication extends Application {

    private String title = "Mabel voedsel";
    private static Stage stage;

    public static void setStage(Scene scene) {
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void start(Stage stage) {
        MainApplication.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle(title);
        StartController startController = new StartController();
        startController.displayView();


    }
}
