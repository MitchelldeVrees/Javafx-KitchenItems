package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartView extends View {

    private VBox mainview;
    private Button start;

    public StartView() {
        Text startText = new Text("Welkom klik op start om door te gaan.");
        startText.setFont(Font.font(30));
        start = new Button("Start");
        start.setFont(Font.font(30));

        VBox vBox = new VBox();

        vBox.getChildren().add(startText);
        vBox.getChildren().add(start);

        vBox.setAlignment(Pos.BASELINE_CENTER);

        mainview = new VBox();
        mainview.getChildren().addAll(vBox);



    }

    public Button getStart() {
        return start;
    }

    @Override
    public Parent getRoot() {
        return mainview;
    }
}
