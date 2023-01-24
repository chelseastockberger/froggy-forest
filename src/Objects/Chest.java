package Objects;

import Main.Screen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Chest extends GameObject {


    public Chest(Screen s){

        setTreasure();

        name = "chest";
        this.s = s;
        setPos();

        text[0] = "You opened a chest!";
        text[1] = "Inside, you found....";

        try {

            File file = new File("resources/objects/chest.png");
            image = ImageIO.read(file);
            file = new File("resources/objects/chest-open.png");
            destroyedimage = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setTreasure(){

        // Chance of weapon or item
        int c = (int) ((Math.random() * 2) + 1);

        // CHANGE variables here to make magic wand only in chest (c=7, 3)
        if(c == 1) {
            // Get random item
            int rand = (int) ((Math.random() * 4) + 1);

            ItemWeaponFactory f = new ItemWeaponFactory();
            Items i = f.getItem(rand);

            this.item = i;
        }else{
            // Get random weapon
            int rand = (int) ((Math.random() * 3) + 1);

            ItemWeaponFactory f = new ItemWeaponFactory();
            Weapons i = f.getWeapon(rand);

            this.weapon = i;
        }

    }


}
