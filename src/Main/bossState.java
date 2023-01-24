package Main;

import Entities.Enemy;
import Entities.Projectile;

import java.awt.event.KeyEvent;


public class bossState extends State {
    Screen s;

    bossState(Screen s) {
        this.s = s;
    }

    public void doUpdate() {

        s.player.update();

        // Check if the player is alive
        if (s.player.isDead()) {
            s.game.window.dispose();
            s.thread = null;
            EndMenu endMenu = new EndMenu(false);
            endMenu.draw();
            return;
        }

        // Check if boss died
        if( s.boss.isDead()){
            s.game.window.dispose();
            s.thread = null;
            EndMenu endMenu = new EndMenu(true);
            endMenu.draw();
            return;
        }

        s.boss.update();
        for(Projectile pj: s.boss.projs){
            pj.update();
        }


    }

    public void showDialogue(){

    }

    public void inputHandle(InputHandler i, int c){
        if (c == KeyEvent.VK_W) {
            i.upPress = true;
        }
        if (c == KeyEvent.VK_A) {
            i.leftPress = true;
        }
        if (c == KeyEvent.VK_S) {
            i.downPress = true;
        }
        if (c == KeyEvent.VK_D) {
            i.rightPress = true;
        }
        if (c == KeyEvent.VK_ENTER) {
            i.enterPress = true;
        }
        if (c == KeyEvent.VK_SHIFT) {
            i.shiftPress = true;
        }

    }



}



