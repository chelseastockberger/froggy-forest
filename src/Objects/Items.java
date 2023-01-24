package Objects;

import Entities.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
ITEMS
Items that the player obtains and affects the player in
distinct ways
 */

public abstract class Items {

    Player player;
    public BufferedImage image;
    public String name = "";
    public String caption = "";
    int timeHeld;

    public abstract void giveEffect();


}

// Heal 40 health
class Potion extends Items {

    public Potion(){
        name = "Potion";
        caption = "You gained 40 health!";
    }

    public void giveEffect(){

        if(player.health + 40 > player.maxHealth){
            player.health = player.maxHealth;
        }else{
            player.health = player.health+40;
        }

    }



}

// Damage received reduced by 2
class Hat extends Items {

    public Hat(){
        name = "Cool Hat";
        caption = "You put on a cool hat. Damage received reduced by 2 pts!";

        try {
            File file = new File("resources/objects/hat.png");
            image = ImageIO.read(file);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void giveEffect(){

        player.defense = player.defense+2;

    }


}

// Take 20 damage
class Poison extends Items {

    public Poison(){
        name = "Poison";
        caption = "Yuck! You took 20 damage.";
    }

    public void giveEffect(){

        if(player.health - 20 <= 0){
            player.health = 0;
        }else{
            player.health = player.health-20;
        }

    }


}

// Raises damage given by 5
class UltraStrength extends Items {

    public UltraStrength(){
        name = "Ultra Strength";
        caption = "Feeling pumped! You now deal 5 extra damage to enemies.";
    }

    public void giveEffect(){

       player.damage = player.damage+5;

    }



}

