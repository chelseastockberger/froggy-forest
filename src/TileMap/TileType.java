package TileMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileType {

    public BufferedImage image;
    public BufferedImage image1 = null;
    public BufferedImage image2 = null;
    public boolean collision;
    char type_;

    public TileType(char type){
        this.type_ = type;
        this.collision = true;
        setData();
    }

    public void setData(){

        File file;

        try {

            switch (type_) {

                case '-':
                    file = new File("resources/tiles/white.png");
                    break;
                case 'G':
                    this.collision = false;
                    file = new File("resources/tiles/blank_grass.png");
                    break;
                case 'T':
                    file = new File("resources/tiles/grass_edge-T.png");
                    break;
                case 'B':
                    file = new File("resources/tiles/grass_edge-B.png");
                    break;
                case 'L':
                    file = new File("resources/tiles/grass_edge-L.png");
                    break;
                case 'R':
                    file = new File("resources/tiles/grass_edge-R.png");
                    break;
                case 'Q':
                    file = new File("resources/tiles/TLouter.png");
                    break;
                case 'E':
                    file = new File("resources/tiles/TRouter.png");
                    break;
                case 'A':
                    file = new File("resources/tiles/BLouter.png");
                    break;
                case 'D':
                    file = new File("resources/tiles/BRouter.png");
                    break;
                case 'U':
                    file = new File("resources/tiles/TLinner.png");
                    break;
                case 'I':
                    file = new File("resources/tiles/TRinner.png");
                    break;
                case 'J':
                    file = new File("resources/tiles/BLinner.png");
                    break;
                case 'K':
                    file = new File("resources/tiles/BRinner.png");
                    break;
                case 'M':
                    file = new File("resources/tiles/midHori.png");
                    break;
                case 'V':
                    file = new File("resources/tiles/midVert.png");
                    break;
                case 'P':
                    collision = false;
                    file = new File("resources/tiles/portal.png");
                    break;
                default:
                    file = new File("resources/tiles/white.png");
                    break;

            }

            image = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
