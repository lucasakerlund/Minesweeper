package lucas.minesweeper;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid extends Pane {

    public static final int TILE_SIZE = 50;

    private int width;
    private int height;

    private Tile[][] grid;

    private int bombs = 99;
    private boolean isGameOver = false;

    private int[] xOffsets = {1, 0, -1};
    private int[] yOffsets = {1, 0, -1};

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Tile[width][height];
    }

    public void generateContent(){
        generateGrid();
        generateBombs();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(!grid[x][y].isBomb()){
                    calcNumber(x,y);
                }
                grid[x][y].hideTile();
            }
        }
    }

    private void generateGrid(){
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Tile tile = new Tile(x, y);
                grid[x][y] = tile;
                tile.setTranslateX(x*TILE_SIZE);
                tile.setTranslateY(y*TILE_SIZE);
                addTileListener(tile);
                getChildren().add(tile);
            }
        }
    }

    private void generateBombs(){
        Random r = new Random();
        for(int i = 0; i < bombs;) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            if(!grid[x][y].isBomb()){
                grid[x][y].setBomb(true);
                i++;
            }
        }
    }

    private void calcNumber(int x, int y){
        int bombNeighbours = 0;
        for(int xOffset = 0; xOffset < xOffsets.length; xOffset++){
            for(int yOffset = 0; yOffset < yOffsets.length; yOffset++){
                if(xOffsets[xOffset] == 0 && yOffsets[yOffset] == 0){
                    continue;
                }
                if(x+xOffsets[xOffset] >= width || x+xOffsets[xOffset] < 0 || y+yOffsets[yOffset] >= height || y+yOffsets[yOffset] < 0){
                    continue;
                }
                if(grid[x+xOffsets[xOffset]][y+yOffsets[yOffset]].isBomb()){
                    bombNeighbours++;
                }

            }
        }
        grid[x][y].setNumber(bombNeighbours);
    }

    private void addTileListener(Tile tile) {
        tile.setOnMousePressed(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                if(!tile.isLocked() && !tile.isExposed()){
                    exposeTile(tile);
                }
            }else if(e.getButton() == MouseButton.SECONDARY) {
                if(tile.isLocked()){
                    tile.unlock();
                }else{
                    if(!tile.isExposed()){
                        tile.lock();
                    }
                }
            }
        });
    }

    private void exposeTile(Tile tile){
        if(isGameOver){
            return;
        }
        if(!tile.isLocked()){
            tile.showTile();
        }

        if(tile.isBomb()){
            isGameOver = true;
            gameOver();
        }

        /*exposes all 9 tiles if center is 0*/
        if(tile.getNumber() == 0  && !tile.isBomb()){
            for(int xOffset = 0; xOffset < xOffsets.length; xOffset++) {
                for (int yOffset = 0; yOffset < yOffsets.length; yOffset++) {
                    int newX = tile.getX() + xOffsets[xOffset];
                    int newY = tile.getY() + yOffsets[yOffset];

                    if(newX >= width || newX < 0 || newY >= height || newY < 0){
                        continue;
                    }
                    if(newX == tile.getX() && newY == tile.getY()){
                        continue;
                    }
                    if(grid[newX][newY].getNumber() == 0){
                        if(grid[newX][newY].isExposed()){
                            continue;
                        }
                        if(!grid[newX][newY].isLocked()){
                            exposeTile(grid[newX][newY]);
                        }
                    }
                    if(!grid[newX][newY].isLocked()){
                        grid[newX][newY].showTile();
                    }
                }
            }
        }
    }

    private void gameOver(){
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(grid[x][y].isBomb()){
                    grid[x][y].showTile();
                }
            }
        }
    }


}
