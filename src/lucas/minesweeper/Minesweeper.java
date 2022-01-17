package lucas.minesweeper;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Minesweeper extends Application {

    public final static int WIDTH = 30;
    public final static int HEIGHT = 16;
    public final static int BOMBS = 99;

    private static Minesweeper instance;

    private Header header;
    private Grid grid;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        BorderPane root = new BorderPane();

        grid = new Grid(WIDTH,HEIGHT, BOMBS);
        header = new Header();

        root.setTop(header);
        root.setCenter(setupGame());
        stage.setScene(new Scene(root, Grid.TILE_SIZE*WIDTH, Grid.TILE_SIZE*HEIGHT + 50));
        System.out.println(header.getHeight());
        stage.show();
        stage.sizeToScene();
    }

    public static Minesweeper inst(){
        return instance;
    }

    public Grid setupGame() {
        grid.reset();
        header.updateMarks(BOMBS);
        grid.generateContent();
        return grid;
    }

    public Grid getGrid(){
        return grid;
    }

    public Header getHeader() {
        return header;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
