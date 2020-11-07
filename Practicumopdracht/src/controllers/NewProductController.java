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
import views.NewProductView;

import java.time.LocalDate;
import java.util.Optional;

public class NewProductController extends Controller {

    private NewProductView newProductView;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private MainController mainController;

    private int getId;
    private String getNaamProduct;
    private String getMerk;
    private String getToevoeging;
    private String getSoortVoedsel;
    private LocalDate getHoudbaarheidsDatum;
    private LocalDate getAankoopDatum;
    private Alert alert;


    private Product product;
    private ObjectProductDAO objectProductDAO = new ObjectProductDAO();

    private Scene scene;

    public NewProductController(MainController mainController){
        this.mainController = mainController;
        newProductView = new NewProductView();
        newProductView.getOpslaan().setOnAction(event -> opslaanButtonHandler());
        newProductView.getMenuMainView().setOnAction(event -> changeButtonHandler());
        newProductView.getMenuItemOpslaan().setOnAction(event -> opslaandMenuHandler());
        newProductView.getMenuItemLaden().setOnAction(event -> ladenMenuHandler());
        newProductView.getWijzig().setOnAction(event -> wijzigButtonHandler());
        newProductView.getVerwijderen().setOnAction(event -> verwijderenButtonHandler());
        newProductView.getMenuItemAfsluiten().setOnAction(event -> afsluitenMenuHandler());

        scene = new Scene(newProductView.getRoot(), WIDTH, HEIGHT);




    }

    private void afsluitenMenuHandler() {
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

    private void succesAlert(String melding) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(melding);
        alert.showAndWait();
    }

    private void verwijderenButtonHandler() {
        Product selectedProduct = (Product) newProductView.getListView().getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Geen SELECTIE");
            alert.setHeaderText(null);
            alert.setContentText("U heeft geen selectie aangedrukt!");
            alert.showAndWait();
        } else {
            approvalAlert("Weet u zeker dat u deze wilt verwijderen?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                objectProductDAO.remove(selectedProduct);
                refreshData();
            }

        }
    }

    private void wijzigButtonHandler() {
        Product selectedProduct = (Product) newProductView.getListView().getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Geen SELECTIE");
            alert.setHeaderText(null);
            alert.setContentText("U heeft geen selectie aangedrukt!");
            alert.showAndWait();
        } else {
            getId = selectedProduct.getId();

            newProductView.getNaamProductField().setText(selectedProduct.getNaamProduct());
            newProductView.getMerkField().setText(selectedProduct.getMerkProduct());
            newProductView.getHoudbaarheidsDatumField().setValue(selectedProduct.getHoudbaarheidsDatum());
            newProductView.getAankoopDatumField().setValue(selectedProduct.getAankoopDatum());
            newProductView.getSoortVoedsel().setValue(selectedProduct.getSoortVoedsel());
            newProductView.getToevoegingField().setText(selectedProduct.getToevoeging());

        }
    }



    private void ladenMenuHandler() {

        newProductView.getListView().getItems().clear();
        objectProductDAO.load();
        refreshData();
    }

    private void opslaandMenuHandler() {
        objectProductDAO.save();
    }

    private void opslaanButtonHandler() {
        getNaamProduct = newProductView.getNaamProductField().getText();
        getMerk = newProductView.getMerkField().getText();
        getHoudbaarheidsDatum = newProductView.getHoudbaarheidsDatumField().getValue();
        getAankoopDatum = newProductView.getAankoopDatumField().getValue();
        getSoortVoedsel= newProductView.getSoortVoedsel().getValue();
        getToevoeging = newProductView.getToevoegingField().getText();

        Product selectedProduct = (Product) newProductView.getListView().getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            product = new Product(
                    getId, getNaamProduct, getMerk, getHoudbaarheidsDatum,
                    getAankoopDatum, getSoortVoedsel, getToevoeging
            );
        } else {
            product = new Product(
                    getId, getNaamProduct, getMerk, getHoudbaarheidsDatum,
                    getAankoopDatum, getSoortVoedsel, getToevoeging
            );
        }

        objectProductDAO.addOrUpdate(product);
        refreshData();

        newProductView.getNaamProductField().clear();
        newProductView.getMerkField().clear();
        newProductView.getHoudbaarheidsDatumField().setValue(null);
        newProductView.getAankoopDatumField().setValue(null);
        newProductView.getSoortVoedsel().setValue(null);
        newProductView.getToevoegingField().clear();
    }

    public void refreshData() {
        ObservableList<Product> productList = FXCollections.observableList(objectProductDAO.getAll());
        newProductView.getListView().setItems(productList);

    }

    private void approvalAlert(String melding) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Let op!");
        alert.setHeaderText(null);
        alert.setContentText(melding);
        ButtonType cancelButtonType = new ButtonType("Nee", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButtonType);
    }

    private void changeButtonHandler() {
        mainController.displayView();
    }

    public void displayView() {
        MainApplication.setStage(scene);
    }


}
