package Objects;

import Main.Screen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mushroom extends GameObject {


    public Mushroom(Screen s){

        setItem();

        this.s = s;
        setPos();
        name = "mushroom";

        text[0] = "You destroyed a magic mushroom!";
        text[1] = "It did something interesting to you....";

        try {

            File file = new File("resources/objects/magicshroom.png");
            image = ImageIO.read(file);
            file = new File("resources/objects/magicshroom-broke.png");
            destroyedimage = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setItem(){

        // Get random item
        int rand = (int) ((Math.random() * 4) + 1);

        ItemWeaponFactory f = new ItemWeaponFactory();
        Items i = f.getItem(rand);

        this.item =  i;


    }

}
