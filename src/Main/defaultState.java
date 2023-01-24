package Main;

import Entities.Enemy;

import java.awt.event.KeyEvent;

public class defaultState extends State {
    Screen s;

    defaultState(Screen s) {
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

        for (Enemy e : s.enemies) {
            if (!e.isDead()) {
                e.update();
            }
        }

        // If all enemies are defeated, add the portal
        if (s.checkEnemiesDefeated()) {
            if (!s.portaladded)
                s.addPortal();
        }
        // If the portal is added, constantly check if the player walks over it
        if (s.portaladded) {
            if (s.player.x <= s.map.portal.x && s.player.x + s.tileSize >= s.map.portal.x && s.player.y <= s.map.portal.y && s.player.y + s.tileSize >= s.map.portal.y) {

                s.portaladded = false;
                s.game.newLevel();

            }
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
