package lucas.minesweeper;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private Grid grid;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        root.setCenter(setupGame(30,16));
        System.out.println(((Parent)root).getTranslateX());
        stage.setScene(new Scene(root, Grid.TILE_SIZE*30, Grid.TILE_SIZE*16));
        stage.show();
    }

    private Grid setupGame(int width, int height){
        Grid grid = new Grid(width, height);
        grid.generateContent();
        return grid;
    }

}
