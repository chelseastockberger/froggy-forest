package Entities;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import Main.*;
import Objects.GameObject;
import Objects.ItemWeaponFactory;
import Objects.Items;
import Objects.Weapons;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
PLAYER
Holds all data related to the Player
Takes user input to move player/do actions
Handles attacking and blocking
Includes design pattern: Game Loop
 */


public class Player extends Entity{

    InputHandler k;
    BufferedImage levelimg;
    public BufferedImage up1,down1,left1,right1,up2,down2,left2,right2;
    public BufferedImage upBlock, block;
    public Projectile currproj;
    int blockCount = 0;
    boolean attacking;
    boolean blocking;
    public int defense;
    ArrayList<Items> items;
    public Weapons weapon;


    public Player(Screen s, InputHandler k){

        health = 100;
        maxHealth = 100;
        defense = 0;
        this.s = s;
        this.k = k;
        currproj = null;

        ItemWeaponFactory w = new ItemWeaponFactory();
        weapon = w.getWeapon(4);
        this.damage = weapon.damage;
        this.items= new ArrayList<>();

        setPos();

        speed=4;
        dir = "down";

        hitbox = new Rectangle(9*3,15*3,13*3,17*3);

        getImage();
    }

    // Set weapon, only update if weapon is better than previous one
    public void setWeapon(Weapons w){
        if(weapon.damage < w.damage)
            this.weapon = w;
    }

    // Add item to player. Only add hat or extra strength if they don't already have it.
    public void addItem(Items i){
        boolean canAdd = true;
        if(!items.isEmpty()){
            for(Items it: items){
                if(it.name == i.name ){
                    if(i.name == "Cool Hat" || i.name == "Ultra Strength"){
                        canAdd = false;
                    }
                }
            }
        }
        if(canAdd) {
            items.add(i);
        }
    }

    public void update(){

        if(attacking == true){
            doAttack();
        }
        if(blocking == true){
            doBlock();
        }

        getMove();
        setAttacking();
        setBlocking();

        if(currproj != null){
            currproj.update();
        }

    }

    // If movement keys entered, set direction, move player if not colliding.
    public void getMove(){

        if(k.upPress == true || k.downPress == true || k.leftPress == true || k.rightPress == true) {

            collision = false;
            s.collision.checkTile(this);

            // Handle keyboard input
            if (k.upPress == true) {
                dir = "up";
            } else if (k.downPress == true) {
                dir = "down";
            } else if (k.leftPress == true) {
                dir = "left";
            } else if (k.rightPress == true) {
                dir = "right";
            }

            if (collision == false) {
                oX = x;
                oY = y;

                switch (dir) {
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

                // Do sprite animation
                animCount++;
                if (animCount > 15) {
                    if (animStep == 1) {
                        animStep = 2;
                    } else {
                        animStep = 1;
                    }
                    animCount = 0;
                }

            }else{

                // Bounce back!
                x = oX;
                y=oY;
            }

        }
    }

    // Draw sprite based on directions going/action
    public void draw(Graphics2D g){

        drawHat(g);
        drawMove(g);
        drawAttackorBlock(g);

    }

    // Draw player movings
    void drawMove(Graphics2D g){

        switch(dir){
            case "up":
                if(animStep == 1){
                    currimage = up1;
                }else{
                    currimage= up2;
                }
                break;
            case "down":
                if(animStep == 1){
                    currimage = down1;
                }else{
                    currimage = down2;
                }
                break;
            case "left":
                if(animStep == 1){
                    currimage = left1;
                }else{
                    currimage = left2;
                }
                break;
            case "right":
                if(animStep == 1){
                    currimage = right1;
                }else{
                    currimage = right2;
                }
                break;
        }
        g.drawImage(currimage,x,y,s.tileSize,s.tileSize,null);

    }

    // Draw weapon/shield on
    void drawAttackorBlock(Graphics2D g){

        // Draw weapon on player, if attacking, or shield if blocking
        if(attacking == true || blocking == true){
            switch(dir){
                case "down":
                    if(attacking) {
                        g.drawImage(weapon.downAttack, x + (22), y + (36), s.tileSize, s.tileSize, null);
                    }else{
                        g.drawImage(block, x + (37), y + (36), s.tileSize/2, s.tileSize/2, null);
                    }
                    break;
                case "up":
                    if(attacking) {
                        g.drawImage(weapon.upAttack, x + (22), y + (36), s.tileSize, s.tileSize, null);
                    }else{
                        g.drawImage(upBlock, x + (63), y+34, s.tileSize/2, s.tileSize/2, null);
                    }
                    break;
                case "left":
                    if(attacking) {
                        g.drawImage(weapon.leftAttack, x - (53), y + (9), s.tileSize, s.tileSize, null);
                    }else{
                        g.drawImage(block, x - (20), y + (36), s.tileSize/2, s.tileSize/2, null);
                    }
                    break;
                case "right":
                    if(attacking) {
                        g.drawImage(weapon.rightAttack, x + (49), y + 15, s.tileSize, s.tileSize, null);
                    }else{
                        g.drawImage(block, x + (45), y + (36), s.tileSize/2, s.tileSize/2, null);
                    }
                    break;
            }
        }
    }

    // Draw hat on
    void drawHat(Graphics2D g){
        // Draw on hat, if has hat
        boolean ownsHat = false;
        BufferedImage hat = null;
        if(!items.isEmpty()){
            for(Items i: items){
                if(i.name == "Cool Hat"){
                    ownsHat=true;
                    hat = i.image;
                }
            }
        }
        if(ownsHat){
            g.drawImage(hat, x+25,y-30,s.tileSize/2,s.tileSize/2,null);
        }

    }

    // Get images
    public void getImage(){

        try{

            // Moving images
            File file = new File("resources/player/frog_back1.png");
            up1 = ImageIO.read(file);
            file = new File("resources/player/frog_front1.png");
            down1 = ImageIO.read(file);
            file = new File("resources/player/frog_left1.png");
            left1 = ImageIO.read(file);
            file = new File("resources/player/frog_right1.png");
            right1 = ImageIO.read(file);

            file = new File("resources/player/frog_back2.png");
            up2 = ImageIO.read(file);
            file = new File("resources/player/frog_front2.png");
            down2 = ImageIO.read(file);
            file = new File("resources/player/frog_left2.png");
            left2 = ImageIO.read(file);
            file = new File("resources/player/frog_right2.png");
            right2 = ImageIO.read(file);

            // Level img
            file = new File("resources/icons/level.png");
            levelimg = ImageIO.read(file);

            // Blocking image
            file = new File("resources/weapons/shield-up.png");
            upBlock = ImageIO.read(file);
            file = new File("resources/weapons/shield.png");
            block = ImageIO.read(file);

            currimage = up1;


        }catch(IOException e){
            e.printStackTrace();
        }

    }

    // Set attacking if enter key pressed
    public void setAttacking(){
        if(k.enterPress == true){
            attacking = true;
        }
    }
    // Set blocking if shift key pressed
    public void setBlocking(){
        if(k.shiftPress == true){
            blocking = true;
        }
    }

    // Take damage
    public void getAttack(int dmg){
        // if not blocking, take damage
        if(!blocking) {
            updateHealth(-(dmg-defense));
        }
    }

    // Take damage or rebound projectile
    public void getAttackProjectile(int dmg, Projectile proj){

        if(!blocking) {
            health -= (dmg - defense);
            proj.isExist = false;
        }else{
            proj.start(this.x,this.y, true);
        }

    }

    // 25 frames of attack, runs when attacking is true, only does attack once 25 ms passed
    // EXAMPLE OF GAME LOOP
    public void doAttack(){

        attackCount++;

        if(attackCount > 25){

            if(!s.game.lastLevel) {

                // Shoot projectile if magic staff, otherwise normal attack
                if(weapon.name != "Magic Staff") {
                    // Check if hit monster
                    if (s.collision.getCollidingMonster(this) != null) {
                        Enemy e = s.collision.getCollidingMonster(this);
                        e.getHit();
                        e.updateHealth(-damage);
                    }
                }else{

                    // Shoot projectile
                    currproj = new Projectile(s, false);
                    currproj.start(this.x+(s.tileSize/2),this.y+(s.tileSize/2), false);
                }

                // Check if hit object
                if (s.collision.checkObjects(this) != null) {
                    interactObject();
                }

            }

            attackCount = 0;
            attacking = false;

        }
    }
    // Do 25 ms of blocking, then set to false
    public void doBlock(){
        blockCount++;
        if(blockCount > 25){
            blockCount = 0;
            blocking = false;
        }
    }

    // Interact with an object
    void interactObject(){
        GameObject o = s.collision.checkObjects(this);
        o.giveItemToPlayer(this);
        s.state_ = new textState(this.s);
        o.write();
        o.setDestroyed();

    }

}
