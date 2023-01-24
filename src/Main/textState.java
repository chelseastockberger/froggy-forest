package Main;

import java.awt.event.KeyEvent;

public class textState extends State {

    Screen s;

    public textState(Screen s) {
        this.s = s;
    }

    public void doUpdate() {

        if (s.input.endDialogue) {
            s.ui.textIndex = s.ui.textIndex + 1;
            if (s.ui.textArr.get(s.ui.textIndex) != null) {
                s.ui.currtext = s.ui.textArr.get(s.ui.textIndex);
            } else {
                s.state_ = new defaultState(this.s);
            }
            s.input.endDialogue = false;
        }


    }
    public void showDialogue(){
        s.ui.textInterface();
    }

    public void inputHandle(InputHandler i, int c){

        if(c == KeyEvent.VK_ENTER){
            i.endDialogue = true;
        }

    }




}
