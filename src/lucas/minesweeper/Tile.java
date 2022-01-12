package lucas.minesweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private int x;
    private int y;

    private boolean isBomb;
    private int number = 0;
    private boolean isExposed = false;
    private boolean isLocked = false;

    private Rectangle rec;
    private Text text;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;

        rec = new Rectangle(Grid.TILE_SIZE, Grid.TILE_SIZE, null);
        text = new Text();
        addClickListener();

        getChildren().addAll(rec, text);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isBomb(){
        return isBomb;
    }

    public void setBomb(boolean value){
        isBomb = value;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int value){
        number = value;
    }

    public boolean isExposed(){
        return isExposed;
    }

    public boolean isLocked(){
        return isLocked;
    }

    public void lock() {
        isLocked = true;
        draw(Color.RED, Color.LIGHTGRAY);
        text.setText("");
    }

    public void unlock(){
        isLocked = false;
        draw(Color.GRAY, Color.LIGHTGRAY);
    }

    public void showTile(){
        draw(Color.LIGHTGRAY, Color.BLACK);

        if(isBomb){
            text.setText("B");
        }else{
            text.setText(Integer.toString(number));
            switch(number){
                case 0:
                    text.setText("");
                case 1:
                    text.setFill(Color.BLUE);
                    break;
                case 2:
                    text.setFill(Color.GREEN);
                    break;
                case 3:
                    text.setFill(Color.RED);
                    break;
                case 4:
                    text.setFill(Color.PURPLE);
                    break;
                case 5:
                    text.setFill(Color.DARKRED);
                    break;
                case 6:
                    text.setFill(Color.CYAN);
                    break;
                case 7:
                    text.setFill(Color.GRAY);
                    break;
                case 8:
                    text.setFill(Color.ORANGE);
                    break;
            }
        }
        text.setFont(Font.font(null, FontWeight.BOLD, null, 30));
        isExposed = true;
    }

    public void hideTile() {
        draw(Color.GRAY, Color.LIGHTGRAY);
        text.setText("");
        isExposed = false;
    }

    private void addClickListener(){
        setOnMousePressed(e -> {
            showTile();
        });
    }

    private void draw(Color fill, Color stroke){
        rec.setFill(fill);
        rec.setStroke(stroke);
        rec.setStrokeWidth(1);
        rec.setStrokeType(StrokeType.INSIDE);
    }

}
