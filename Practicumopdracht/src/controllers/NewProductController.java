package controllers;

import comparators.DateComparator;
import data.ObjectProductDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import models.Product;
import practicumopdracht.MainApplication;
import views.MainView;
import views.NewProductView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

public class NewProductController extends Controller {

    private NewProductView newProductView;
    private MainView mainView;
    private static final int WIDTH = 810;
    private static final int HEIGHT = 800;
    private MainController mainController;

    private int getId;
    private String getNaamProduct;
    private String getMerk;
    private String getToevoeging;
    private String getSoortVoedsel;
    private String getSoortVoedselSorteren;
    private ObservableList productitems;
    private LocalDate getHoudbaarheidsDatum;
    private LocalDate getAankoopDatum;
    private Alert alert;
    private String errorTextProduct;
    private String errorTextDatum;
    private String errorTextMerk;
    private String errorTextVoedsel;
    private int errorCounter;
    public static final int SHOW_ERROR_MINIMUM = 1;


    private Product product;
    private ObjectProductDAO objectProductDAO = new ObjectProductDAO();

    private Scene scene;

    public NewProductController(MainController mainController) {
        this.mainController = mainController;
        newProductView = new NewProductView();
        newProductView.getOpslaan().setOnAction(event -> opslaanButtonHandler());
        newProductView.getMenuMainView().setOnAction(event -> changeButtonHandler());
        newProductView.getMenuItemOpslaan().setOnAction(event -> opslaandMenuHandler());
        newProductView.getMenuItemLaden().setOnAction(event -> ladenMenuHandler());
        newProductView.getWijzig().setOnAction(event -> wijzigButtonHandler());
        newProductView.getVerwijderen().setOnAction(event -> verwijderenButtonHandler());
        newProductView.getMenuItemAfsluiten().setOnAction(event -> afsluitenMenuHandler());
        newProductView.getZoeken().setOnAction(event -> zoekenButtonhandler());


        scene = new Scene(newProductView.getRoot(), WIDTH, HEIGHT);

        newProductView.getDatumSorterenToggleGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton) newProductView.getDatumSorterenToggleGroup().getSelectedToggle();

                if (rb != null) {
                    String selected = rb.getText();
                    switch (selected) {
                        case "Datum Aflopend":
                            refreshDatumAflopend();
                            break;
                        case "Datum Oplopend":
                            refreshDatumOplopend();
                            break;
                    }
                }
            }
        });


    }

    private void zoekenButtonhandler() {
//        FilteredList<ObjectProductDAO> filter = new FilteredList<ObjectProductDAO>( p -> true);
        Product selectedProduct = (Product) newProductView.getListView().getItems();
        System.out.println(selectedProduct.getMerkProduct());

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
        approvalAlert("Weet u zeker dat u deze data wilt laden?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                newProductView.getListView().getItems().clear();
                mainController.getObjectProductDAO().load();
                objectProductDAO.load();
                mainController.refreshData();
                refreshData();
                succesAlert("Data geladen");
            } catch (Exception e) {
                succesAlert("Fout met laden");
            }

        }
    }

    private void opslaandMenuHandler() {
        approvalAlert("Weet u zeker dat u deze data wilt opslaan? \n Dit overwrite de bestaande data.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            objectProductDAO.save();
        } else {
        }
    }

    public boolean checkEmpty(String checker) {
        return !checker.equals("");
    }


    public boolean checkNull(LocalDate checker) {
        return checker != null;
    }


    private void opslaanButtonHandler() {
        getNaamProduct = newProductView.getNaamProductField().getText();
        getMerk = newProductView.getMerkField().getText();
        getHoudbaarheidsDatum = newProductView.getHoudbaarheidsDatumField().getValue();
        getAankoopDatum = newProductView.getAankoopDatumField().getValue();
        getSoortVoedsel = newProductView.getSoortVoedsel().getValue();
        getToevoeging = newProductView.getToevoegingField().getText();

        if (!checkEmpty(getNaamProduct)) {
            newProductView.getNaamProductField().setStyle(("-fx-text-box-border: red"));
            errorTextProduct = "- Naam is verplicht! \n";
            errorCounter++;
        } else {
            newProductView.getNaamProductField().setStyle("-fx-text-box-border: lightgrey");
            errorTextProduct = "";
        }

        if (!checkEmpty(getMerk)) {
            newProductView.getMerkField().setStyle(("-fx-text-box-border: red"));
            errorTextMerk = "- Merk is verplicht! \n";
            errorCounter++;
        } else {
            newProductView.getMerkField().setStyle("-fx-text-box-border: lightgrey");
            errorTextMerk = "";
        }

        if (!checkNull(getHoudbaarheidsDatum)) {
            newProductView.getHoudbaarheidsDatumField().setStyle(("-fx-text-box-border: red"));
            errorTextDatum = "- Houdbaarheids Datum is verplicht! \n";
            errorCounter++;
        } else {
            newProductView.getHoudbaarheidsDatumField().setStyle("-fx-text-box-border: lightgrey");
            errorTextDatum = "";
        }

        if (getSoortVoedsel == null) {
            newProductView.getSoortVoedsel().setStyle(("-fx-text-box-border: red"));
            errorTextVoedsel = "- Soort voedsel is verplicht! \n";
            errorCounter++;
        } else {
            newProductView.getSoortVoedsel().setStyle("-fx-text-box-border: lightgrey");
            errorTextVoedsel = "";
        }


        if (errorCounter >= SHOW_ERROR_MINIMUM) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("FOUTE Invoer");
            alert.setHeaderText(null);
            alert.setContentText("De volgende fouten zijn gevonden: \n" + errorTextProduct + errorTextMerk + errorTextDatum + errorTextVoedsel);
            alert.showAndWait();
            errorCounter = 0;
        } else {


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
    }

    public void refreshData() {
        ObservableList<Product> productList = FXCollections.observableList(objectProductDAO.getAll());
        newProductView.getListView().setItems(productList);

    }

    public void refreshDatumAflopend() {
        ObservableList<Product> productList = FXCollections.observableList(objectProductDAO.getAll());
        productList.sort(new DateComparator().reversed());
        newProductView.getListView().setItems(productList);
    }

    public void refreshDatumOplopend() {
        ObservableList<Product> productList = FXCollections.observableList(objectProductDAO.getAll());
        productList.sort(new DateComparator());
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

    public NewProductView getNewProductView() {
        return newProductView;
    }

    public ObjectProductDAO getObjectProductDAO() {
        return objectProductDAO;
    }


}
