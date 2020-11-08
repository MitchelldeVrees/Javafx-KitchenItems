package controllers;

import data.ObjectProductDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import models.Product;
import practicumopdracht.MainApplication;
import views.MainView;
import views.NewProductView;

import java.util.Optional;

public class MainController extends Controller  {

    private Alert alert;
    private ObjectProductDAO objectProductDAO = new ObjectProductDAO();
    private MainView mainView;
    private NewProductView newProductView;
    private StartController startController;
    private NewProductController newProductController;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private Scene scene;

    public MainController(StartController startController) {
        this.startController = startController;

        mainView = new MainView();

        mainView.getMenuItemLaden().setOnAction(event -> bestandLadenButtonHandler());
        mainView.getMenuItemAfsluiten().setOnAction(event -> afsluitenButtonHandler());
        mainView.getMenuItemOpslaan().setOnAction(event -> opslaanButtonhandler());
        mainView.getMenuNewProduct().setOnAction(event -> changeButtonHandler());
        newProductController = new NewProductController(this);
        scene = new Scene(mainView.getRoot(), WIDTH, HEIGHT);
    }

    private void opslaanButtonhandler() {
        approvalAlert("Weet u zeker dat u deze data wilt opslaan? \n Dit overwrite de bestaande data.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            objectProductDAO.save();
        }else{}
    }

    private void afsluitenButtonHandler() {
        approvalAlert("Weet u zeker dat u de applicatie wilt afsluiten?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            approvalAlert("Heb je de nieuwe Data Opgeslagen?");
            Optional<ButtonType> result2 = alert.showAndWait();
            if (result2.isPresent() && result2.get() == ButtonType.OK) {
                Platform.exit();
            }

        }
    }

    private void changeButtonHandler() {
        newProductController.displayView();
    }

    private void bestandLadenButtonHandler() {

        approvalAlert("Weet u zeker dat u deze data wilt laden?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                mainView.getListView().getItems().clear();
                newProductController.getObjectProductDAO().load();
                objectProductDAO.load();
                newProductController.refreshData();
                refreshData();
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

    public void refreshData() {
        ObservableList<Product> productList = FXCollections.observableList(objectProductDAO.getAll());
        mainView.getListView().setItems(productList);

    }


    private void approvalAlert(String melding) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Let op!");
        alert.setHeaderText(null);
        alert.setContentText(melding);
        ButtonType cancelButtonType = new ButtonType("Nee", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);

    }

    public MainView getMainView() {
        return mainView;
    }

    public ObjectProductDAO getObjectProductDAO() {
        return objectProductDAO;
    }

    public void displayView() {
        MainApplication.setStage(scene);
    }

}
