package Main;

/*
STATE

Abstract class to be able to do different actions based on
current state of the game
 */

public abstract class State {

    Screen s;

   public State(Screen s){
       this.s = s;
   }
   public State(){
   }


    public abstract void doUpdate();
    public abstract void showDialogue();
    public abstract void inputHandle(InputHandler i, int c);


}

