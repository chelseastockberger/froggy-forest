package Main;

import java.awt.*;
import java.util.ArrayList;

/*
DIALOGUE
Shows text provided by given scenario, such as:
Obtaining item/weapon from an object

 */
public class Dialogue {

    Screen s;
    Graphics2D g;
    Font font, bigfont;
    public String text = "";
    public String currtext = "";
    public ArrayList<String> textArr;
    public int textIndex;

    public Dialogue(Screen s){
        this.s = s;

        font = new Font("Arial", Font.PLAIN, 20);
        bigfont = new Font("Arial", Font.BOLD,80);
    }

    public void draw(Graphics2D g){
        this.g = g;

        g.setFont(font);
        g.setColor(Color.white);

        s.state_.showDialogue();

    }

    // Sets variables for text to appear
    public void textInterface(){
        int y = s.screenHeight - s.tileSize*3;
        int width = s.screenWidth - (s.tileSize * 6);
        int height = s.tileSize*2;
        int x = s.screenWidth/2-(width/2);

        textWindow(x,y,width,height);

        g.setFont(g.getFont().deriveFont(Font.PLAIN));
        x+=s.tileSize;
        y+=s.tileSize;
        g.drawString(currtext,x,y);

    }

    // Draws on the text to the interface
    public void textWindow(int x,int y, int width, int height){

        Color c = new Color(10, 22, 5, 157);
        g.setColor(c);
        g.fillRoundRect(x,y,width,height,40,40);

        c = new Color(225, 247, 210);
        g.setColor(c);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(x+5,y+5,width-10,height-10,30,30);

    }

    public void pauseInterface(){
        g.setFont(g.getFont().deriveFont(Font.PLAIN,80F));
        String str = "| |";
        int x = s.screenWidth/2;
        int y = s.screenHeight/2;
        g.drawString(str,x,y);
    }



}
