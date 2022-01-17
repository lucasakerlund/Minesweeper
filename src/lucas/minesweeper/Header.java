package lucas.minesweeper;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Header extends HBox {

    private Label bombCountLabel;

    public Header() {
        setPrefHeight(50);
        setAlignment(Pos.CENTER);
        setupContent();
    }

    private void setupContent() {
        StackPane resetPane = new StackPane();
        resetPane.setPrefHeight(getPrefHeight());
        resetPane.setPrefWidth(100);
        resetPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));
        Text resetText = new Text("RESET");
        resetText.setFill(Color.RED);
        resetText.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        addResetListener(resetPane);
        resetPane.getChildren().add(resetText);

        bombCountLabel = new Label("Bombs left: " + Minesweeper.BOMBS);
        bombCountLabel.setTextFill(Color.ORANGE);
        bombCountLabel.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        bombCountLabel.setPrefHeight(50);

        HBox box1 = new HBox();
        HBox box2 = new HBox();

        box1.setPadding(new Insets(10));

        HBox.setHgrow(box1, Priority.ALWAYS);
        HBox.setHgrow(box2, Priority.ALWAYS);

        box1.getChildren().add(bombCountLabel);
        box2.getChildren().add(resetPane);

        getChildren().addAll(box1, box2);
    }

    public void updateMarks(int value){
        bombCountLabel.setText("Bombs left: " + value);
    }

    private void addResetListener(Node node) {
        node.setOnMousePressed(e -> {
            Minesweeper.inst().setupGame();
        });
    }

}
