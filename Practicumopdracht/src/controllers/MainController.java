package controllers;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import views.MainView;
import views.StartView;

import java.util.Optional;

public class MainController extends Controller  {

    private Alert alert;

    private MainView mainView;
    private StartController startController;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;

    private Scene scene;

    public MainController(StartController startController) {
        this.startController = startController;
        mainView = new MainView();

        mainView.getMenuItemLaden().setOnAction(event -> bestandLadenButtonHandler());

        scene = new Scene(mainView.getRoot(), WIDTH, HEIGHT);
    }

    private void bestandLadenButtonHandler() {

        approvalAlert("Weet u zeker dat u deze data wilt laden?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                succesAlert("Data geladen");
            } catch (Exception e) {
                succesAlert("Fout met laden");
            }

        }
    }

    private void succesAlert(String melding) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(melding);
        alert.showAndWait();
    }

    private void approvalAlert(String melding) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Let op!");
        alert.setHeaderText(null);
        alert.setContentText(melding);
        ButtonType cancelButtonType = new ButtonType("Nee", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);

    }


    public void displayView() {
        MainApplication.setStage(scene);
    }

}
