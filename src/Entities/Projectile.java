package Entities;

import Main.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/*
PROJECTILE

Used by the boss, shoots towards the player
If blocked by player, rebounds towards boss
Used by player if they have magic wand. Shoots
in a straight line.

 */
public class Projectile extends Entity{

    public BufferedImage image;
    boolean isBoss;
    int maxTime = 300;
    int currTime = 0;
    boolean isExist = false;
    boolean rebounded = false;
    int startx;
    int starty;

    public Projectile(Screen s, boolean isBoss){

        this.s = s;
        this.isBoss = isBoss;

        try {
            File file;
            if(isBoss) {
                file = new File("resources/enemies/projectile.png");
            }else{
                file = new File("resources/enemies/projectile2.png");
            }
            image = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // Make/reset projectile
    public void start(int sx, int sy, boolean rebounded){

        this.rebounded = rebounded;

        this.startx = sx;
        this.starty = sy;

        currTime = maxTime;
        isExist = true;
        x = startx;
        y = starty;

    }

    // If there is a current projectile, update, see if hit player
    public void update(){

        if(isExist) {

            speed = 10;

            // Disapears after time
            currTime--;
            if (currTime <= 0) {
                isExist = false;
            }

            if(isBoss) {
                doBossUpdate();
            }else{
                doPlayerUpdate();
            }

        }

    }

    public void doBossUpdate(){
        // Move towards boss or player
        if(rebounded){
            moveToBoss(s.boss);
            if(checkHitEntity(s.boss)){
                s.boss.health = s.boss.health-50;
                s.boss.isHurt = true;
                isExist = false;
            }
        }else{
            moveToPlayer(s.player);
            if (checkHitEntity(s.player)) {
                s.player.getAttackProjectile(10, this);
            }
        }

    }

    public void doPlayerUpdate(){

        // Shoot projectile forwards, check to see if collides with enemy
        shootProjectile(s.player);
        Enemy e = s.collision.getEnemyAtPos(this.x,this.y);
        if(e != null){
            e.getHit();
            e.updateHealth(-13);
            isExist = false;
        }

    }

    // Check if collide with entity
    public boolean checkHitEntity(Entity e){

        int dist = s.tileSize;
        double currDist = sqrt(pow((e.x - x),2) + pow((e.y - y),2));

        if(currDist <= dist){
            return true;
        }else{
            return false;
        }
    }

    // Move towards player
    public void moveToPlayer(Player p){


        float xS = (p.x - x);
        float yS = (p.y - y);
        double factor = speed / Math.sqrt(xS * xS + yS * yS);
        xS *= factor;
        yS *= factor;

        x += xS;
        y += yS;


    }

    // Shoot straight
    public void shootProjectile(Player p){

        switch (p.dir) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;

        }

    }

    // Move towards boss
    public void moveToBoss(Boss b){

        float xS = ((b.x+s.tileSize/2) - x);
        float yS = ((b.y+s.tileSize/2) - y);
        double factor = speed / Math.sqrt(xS * xS + yS * yS);
        xS *= factor;
        yS *= factor;

        x += xS;
        y += yS;


    }

    public void draw(Graphics2D g){

        if(isExist)
            g.drawImage(image,x,y,s.tileSize,s.tileSize,null);

    }

}
