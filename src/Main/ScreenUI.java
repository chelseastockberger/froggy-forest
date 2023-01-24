package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
SCREENUI
Displays the UI on the screen, such as the weapon, health, etc
 */

public class ScreenUI {

    Screen s;
    public BufferedImage weaponholder;
    public BufferedImage healthholder;
    public BufferedImage level;

    public ScreenUI(Screen s){
        this.s = s;

        try{

            // Level img
            File file = new File("resources/icons/level.png");
            level = ImageIO.read(file);

            // Weapon back img
            file = new File("resources/icons/weapon_holder.png");
            weaponholder = ImageIO.read(file);

            // Health  back img
            file = new File("resources/icons/health_holder.png");
            healthholder = ImageIO.read(file);

        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g){

        // Draw player health bar
        double scale = (double)320/s.player.maxHealth;
        double hpBar = scale*s.player.health;

            // Outline
            g.drawImage(healthholder,130,s.screenHeight-150, 340,s.tileSize/2,null);
            g.setColor(new Color(85,68,96));
            g.fillRect(135, s.screenHeight-145, 330, 30);


            // Fill red
            g.setColor(new Color(153, 50, 39));
            g.fillRect(140, s.screenHeight-145, (int)hpBar, 20);

        // Draw current level
        Font font = new Font("Serif", Font.PLAIN, 35);
        g.setFont(font);
        int lvl = s.game.level+1;
        g.setColor(new Color(143, 151, 74));
        g.drawString(""+lvl, 185,38);
        g.drawImage(level,5,3,s.tileSize*2,s.tileSize/2,null);

        // Draw weapon holder icon

        g.drawImage(weaponholder,32,s.screenHeight-180, s.tileSize,s.tileSize,null);

        // Draw weapon on icon

        g.drawImage(s.player.weapon.upAttack,32,s.screenHeight-177,s.tileSize,s.tileSize,null);


    }
}
