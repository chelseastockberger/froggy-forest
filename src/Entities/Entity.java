package Entities;
import Main.Screen;
import TileMap.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    public int x,y;
    int oX, oY;
    public int speed;
    Screen s;
    public int health;
    public int maxHealth;
    public BufferedImage currimage;
    public String dir;
    public Rectangle hitbox;
    public int animCount = 0;
    public int animStep = 1;
    public int damage;
    public boolean collision = false;
    int attackCount = 0;

    public void setPos(){
        Tile pos = s.map.getRandomPosition();
        this.x = pos.x;
        this.y = pos.y;

    }

    public boolean isDead(){
        if(health <= 0){
            return true;
        }else{
            return false;
        }
    }

    public void updateHealth(int amt){
        health = health+amt;
    }


}
