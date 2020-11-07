package controllers;

import javafx.scene.Scene;
import practicumopdracht.MainApplication;
import views.NewProductView;

public class NewProductController extends Controller {

    private NewProductView newProductView;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private MainController mainController;

    private Scene scene;

    public NewProductController(MainController mainController){
        this.mainController = mainController;
        newProductView = new NewProductView();
        newProductView.getMenuMainView().setOnAction(event -> changeButtonHandler());

        scene = new Scene(newProductView.getRoot(), WIDTH, HEIGHT);




    }

    private void changeButtonHandler() {
        mainController.displayView();
    }

    public void displayView() {
        MainApplication.setStage(scene);
    }


}
