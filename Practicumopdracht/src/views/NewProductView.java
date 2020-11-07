package views;


import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class NewProductView extends View {

    private VBox mainview;
    private DatePicker houdbaarheidsDatumField;
    private DatePicker aankoopDatumField;
    private MenuItem menuItemOpslaan;
    private MenuItem menuItemLaden;
    private MenuItem menuItemAfsluiten;
    private MenuItem menuNewProduct;
    private MenuItem menuMainView;
    private MenuBar menuBar;
    private TextField naamProductField;
    private TextField merkField;
    private TextField toevoegingField;
    private ComboBox<String> soortVoedsel;
    private ListView listView;
    private Button opslaan;
    private Button wijzig;
    private Button verwijderen;

    private RadioButton datumSorterenOplopend;
    private RadioButton datumSorterenAflopend;
    private ToggleGroup sorterenToggleGroup;


    public NewProductView() {

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
        view.getItems().add(menuMainView);
        view.getItems().add(menuNewProduct);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu, view);

        GridPane topGrid = new GridPane();
        topGrid.add(menuBar, 0, 0);

        Label naamProductLabel = new Label("Product naam:");
        Label houdbaarheidsDatumLabel = new Label("Houdbaarheids Datum:");
        Label aankoopDatumLabel = new Label("Aankoop Datum:");
        Label merkLabel = new Label("Merk naam:");
        Label soortVoedselLabel = new Label("Soort voedsel:");
        Label toevoegingLabel = new Label("Toevoeging:");

        naamProductField = new TextField();
        houdbaarheidsDatumField = new DatePicker();
        aankoopDatumField = new DatePicker();
        merkField = new TextField();
        toevoegingField = new TextField();

        soortVoedsel = new ComboBox<>();

        soortVoedsel.getItems().addAll(
                "Sauzen",
                    "Kruiden",
                    "Koelkast Producten",
                    "Niet-Koelkast Producten"
        );

        wijzig = new Button("Wijzig");
        verwijderen = new Button("Verwijderen");
        opslaan = new Button("Opslaan");

         listView = new ListView();

        datumSorterenAflopend = new RadioButton("Datum Aflopend");
        datumSorterenOplopend = new RadioButton("Datum Oplopend");
        sorterenToggleGroup = new ToggleGroup();
        datumSorterenAflopend.setToggleGroup(sorterenToggleGroup);
        datumSorterenOplopend.setToggleGroup(sorterenToggleGroup);

        HBox sorterenHbox = new HBox();
        sorterenHbox.getChildren().addAll(datumSorterenAflopend);
        sorterenHbox.getChildren().addAll(datumSorterenOplopend);
        sorterenHbox.setSpacing(30);

        HBox hbox = new HBox();
        hbox.getChildren().add(wijzig);
        hbox.getChildren().add(verwijderen);
        hbox.setSpacing(5);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(opslaan);
        vbox.getChildren().addAll(listView);
        vbox.getChildren().addAll(hbox);
        vbox.getChildren().addAll(sorterenHbox);
        vbox.setSpacing(10);
        
        GridPane grid = new GridPane();

        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(naamProductLabel,0,0);
        grid.add(naamProductField,1,0);
        grid.add(merkLabel,0,1);
        grid.add(merkField,1,1);
        grid.add(houdbaarheidsDatumLabel,0,2);
        grid.add(houdbaarheidsDatumField,1,2);
        grid.add(aankoopDatumLabel,0,3);
        grid.add(aankoopDatumField,1,3);
        grid.add(soortVoedselLabel,0,4);
        grid.add(soortVoedsel,1,4);
        grid.add(toevoegingLabel,0,5);
        grid.add(toevoegingField,1,5);

        mainview = new VBox();
        grid.setPadding(new Insets(0, 10, 10, 10));
        vbox.setPadding(new Insets(0, 10, 10, 10));
        mainview.setSpacing(10);

        aankoopDatumField.prefWidthProperty().bind(mainview.widthProperty().subtract(100));
        houdbaarheidsDatumField.prefWidthProperty().bind(mainview.widthProperty().subtract(100));
        naamProductField.prefWidthProperty().bind(mainview.widthProperty().subtract(100));
        merkField.prefWidthProperty().bind(mainview.widthProperty().subtract(100));
        soortVoedsel.prefWidthProperty().bind(mainview.widthProperty().subtract(100));
        opslaan.prefWidthProperty().bind(mainview.widthProperty().subtract(20));
        menuBar.prefWidthProperty().bind(mainview.widthProperty().subtract(0));
        wijzig.prefWidthProperty().bind(mainview.widthProperty().subtract(70));
        verwijderen.prefWidthProperty().bind(mainview.widthProperty().subtract(70));

        mainview.getChildren().addAll(topGrid, grid,vbox);

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

    public MenuItem getMenuNewProduct() {
        return menuNewProduct;
    }

    public MenuItem getMenuMainView() {
        return menuMainView;
    }


    public TextField getNaamProductField() {
        return naamProductField;
    }

    public TextField getMerkField() {
        return merkField;
    }

    public TextField getToevoegingField() {
        return toevoegingField;
    }

    public ComboBox<String> getSoortVoedsel() {
        return soortVoedsel;
    }

    public DatePicker getHoudbaarheidsDatumField() {
        return houdbaarheidsDatumField;
    }

    public DatePicker getAankoopDatumField() {
        return aankoopDatumField;
    }

    public ListView getListView() {
        return listView;
    }

    public Button getOpslaan() {
        return opslaan;
    }

    public Button getWijzig() {
        return wijzig;
    }

    public Button getVerwijderen() {
        return verwijderen;
    }

    public RadioButton getDatumSorterenOplopend() {
        return datumSorterenOplopend;
    }

    public RadioButton getDatumSorterenAflopend() {
        return datumSorterenAflopend;
    }

    @Override
    public Parent getRoot() {
        return mainview;
    }


}
