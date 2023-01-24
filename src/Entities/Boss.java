package Entities;

import Main.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
BOSS
The final boss. It shoots projectiles that
can be rebounded by the player.

 */

public class Boss extends Entity {

    public BufferedImage image, hurtimage;
    public Image currimage;
    public Image gifImage;
    public Projectile currproj;
    public ArrayList<Projectile> projs;
    int attackSpeed;
    int attackCount = 0;
    int hurtImgCount = 0;
    boolean isHurt = false;

    public Boss(Screen s){

        this.s = s;
        attackSpeed = 4;
        projs = new ArrayList<>();
        this.currproj = new Projectile(s, true);
        maxHealth = 300;
        health = maxHealth;

        try {

            File file = new File("resources/enemies/boss.png");
            image = ImageIO.read(file);
            file = new File("resources/enemies/bosshurt.png");
            hurtimage = ImageIO.read(file);
            gifImage = new ImageIcon("resources/enemies/boss.gif").getImage();
            currimage = gifImage;

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g){

        g.drawImage(currimage,x,y,s.tileSize*4,s.tileSize*4,null);

        // Draw health bar

        // Draw HP bars
        double scale = (double) s.tileSize*4 / maxHealth;
        double hpBar = scale * health;

        // Outline
        g.setColor(new Color(21, 35, 10));
        g.fillRect(x + 12, y - 30, s.tileSize*4, 40);

        // Fill red
        g.setColor(new Color(144, 50, 40));
        g.fillRect(x + 12, y - 30, (int) hpBar, 40);

    }

    public void update() {

        if(isHurt){
            currimage = hurtimage;
            hurtImgCount++;
            if(hurtImgCount >= 40){
                currimage = gifImage;
                hurtImgCount = 0;
                isHurt = false;
            }
        }else {
            attackCount++;
            doAttack(s.player);

            if (projs.size() >= 10) {
                projs.clear();
            }
        }

    }


    // Attack player
    public void doAttack(Player p){

        // Do attack
        if(attackCount >= 90){
            attackCount = 0;
            currproj = new Projectile(s, true);
            projs.add(currproj);
            currproj.start(this.x+(s.tileSize*2),this.y+(s.tileSize*2), false);
        }

    }



}
