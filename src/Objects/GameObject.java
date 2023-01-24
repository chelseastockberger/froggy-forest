package Objects;

import Entities.Player;
import Main.Screen;
import TileMap.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
GAMEOBJECT
Interactable objects places on the map.
Once interacted with, player obtains weapon/item that the object held.
 */

public class GameObject {

    public int x,y;
    public BufferedImage image;
    public BufferedImage destroyedimage;
    public String name;
    public boolean collision = true;
    Screen s;
    public boolean isDestroyed = false;
    String text[] = new String[5];

    Weapons weapon = null;
    Items item = null;

    public void setPos(){
        Tile pos = s.map.getRandomPosition();
        this.x = pos.x;
        this.y = pos.y;
    }


    public void draw(Graphics2D g){

        g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);

    }

    // Set object as destroyed, changes image, can collide now
    public void setDestroyed(){

        this.isDestroyed = true;
        this.image = destroyedimage;
        this.collision = false;


    }

    // Gives the item produced from the object to player
    public void giveItemToPlayer(Player player){
        if(weapon != null){
            player.setWeapon(weapon);
            text[2] = weapon.name;
            text[3] = weapon.caption;
        }else if(item != null){
            player.addItem(item);
            item.player = player;
            item.giveEffect();
            text[2] = item.name;
            text[3] = item.caption;
        }
    }

    // Sets dialogue text
    public void write(){
        s.ui.currtext = text[0];
        s.ui.textIndex = 0;
        s.ui.textArr = new ArrayList<>();

        for(String str : text){
            s.ui.textArr.add(str);
        }

    }



}