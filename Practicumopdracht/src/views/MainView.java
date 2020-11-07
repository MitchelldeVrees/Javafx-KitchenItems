package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainView extends View {

    private VBox mainview;
    private MenuItem menuItemOpslaan;
    private MenuItem menuItemLaden;
    private MenuItem menuItemAfsluiten;
    private MenuItem menuNewProduct;
    private MenuItem menuMainView;
    private MenuBar menuBar;
    private ListView listView;


    public MainView(){

        Menu menu = new Menu("Bestand");
        menuItemOpslaan = new MenuItem("Opslaan");
        menuItemLaden = new MenuItem("Laden");
        menuItemAfsluiten = new MenuItem("Afsluiten");
        menu.getItems().add(menuItemOpslaan);
        menu.getItems().add(menuItemLaden);
        menu.getItems().add(menuItemAfsluiten);

        Menu view = new Menu("View");
        menuMainView = new MenuItem("Main");
        menuNewProduct = new MenuItem("Nieuw Product");
        view.getItems().add(menuNewProduct);
        view.getItems().add(menuMainView);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu, view);

        Text producten = new Text("Producten");
        producten.setFont(Font.font(null, FontWeight.BOLD, 30));

        Text functies = new Text("Functies");
        functies.setFont(Font.font(null, FontWeight.BOLD, 30));

        Label sorteren1 = new Label("Sorteren1");

        Label sorteren2 = new Label("Sorteren2");

        Label sorteren3 = new Label("Sorteren3");

        Label sorteren4 = new Label("Sorteren4");

        listView = new ListView();

        mainview = new VBox();

        //set functies to edit listview
        GridPane gridPane = new GridPane();
        gridPane.add(sorteren1, 0,1);
        gridPane.add(sorteren2, 0,2);
        gridPane.add(sorteren3, 0,3);
        gridPane.add(sorteren4, 0,4);

        //All the names in the top
        HBox names = new HBox();
        names.getChildren().add(producten);
        names.getChildren().add(functies);
        names.setPadding(new Insets(10,10,10,10));
        names.setSpacing(500);


        //Listview and gridpane for functies
        HBox listFuncties = new HBox();
        listFuncties.getChildren().add(listView);
        listFuncties.getChildren().add(gridPane);
        listFuncties.setPadding(new Insets(10,10,10,10));
        listFuncties.setSpacing(50);

        //set everyting in vBox
        VBox vBox= new VBox();
        vBox.getChildren().add(names);
        vBox.getChildren().add(listFuncties);



        GridPane topGrid = new GridPane();
        topGrid.add(menuBar, 0, 0);

        menuBar.prefWidthProperty().bind(mainview.widthProperty().subtract(0));
        listView.setPrefWidth(600);


        mainview.getChildren().addAll(topGrid,vBox);


    }

    public MenuItem getMenuNewProduct() {
        return menuNewProduct;
    }

    public MenuItem getMenuMainView() {
        return menuMainView;
    }

    public MenuItem getMenuItemOpslaan() {
        return menuItemOpslaan;
    }

    public MenuItem getMenuItemLaden() {
        return menuItemLaden;
    }

    public MenuItem getMenuItemAfsluiten() {
        return menuItemAfsluiten;
    }



    @Override
    public Parent getRoot() {
        return mainview;
    }

}
