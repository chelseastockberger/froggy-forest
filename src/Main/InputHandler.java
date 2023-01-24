package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public boolean upPress, downPress, leftPress, rightPress, enterPress, shiftPress, endDialogue;
    public Screen s;

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        int c = e.getKeyCode();

       s.state_.inputHandle(this, c);


    }
    public void keyReleased(KeyEvent e){
        int c = e.getKeyCode();


        if (c == KeyEvent.VK_W) {
            upPress = false;
        }
        if (c == KeyEvent.VK_A) {
            leftPress = false;
        }
        if (c == KeyEvent.VK_S) {
            downPress = false;
        }
        if (c == KeyEvent.VK_D) {
            rightPress = false;
        }
        if (c == KeyEvent.VK_ENTER) {
            enterPress = false;
        }
        if (c == KeyEvent.VK_SHIFT) {
            shiftPress = false;
        }


    }

}
