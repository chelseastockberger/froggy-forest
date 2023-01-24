package TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public int x,y;
    public Rectangle rect;
    public TileType type;
    public BufferedImage image;

    public Tile(TileType type){
        this.type = type;
        this.image = this.type.image;
        rect = new Rectangle(0,0,32,32);
    }


}
