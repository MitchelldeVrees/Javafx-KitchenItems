package controllers;

import javafx.scene.Scene;
import practicumopdracht.MainApplication;
import views.StartView;

public class StartController extends Controller {

    private StartView startView;
    private MainController mainController;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Scene scene;

    public StartController() {
        startView = new StartView();
        startView.getStart().setOnAction(actionEvent -> startButtonClick());
        mainController = new MainController(this);
        scene = new Scene(startView.getRoot(), WIDTH, HEIGHT);
    }

    private void startButtonClick(){
        mainController.displayView();


    }
    public void displayView() {
        MainApplication.setStage(scene);
    }
}
