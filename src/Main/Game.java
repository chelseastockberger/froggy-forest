package Main;
import Entities.Enemy;
import Entities.EnemyFactory;
import Objects.GameObject;
import Objects.ObjectFactory;


import javax.swing.*;
import java.util.ArrayList;

/*
GAME
Singleton for managing starting new game,
generating enemies and objects,
and new level
 */

public class Game {


    private static Game instance = null;

    public static Game getGame(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    Screen screen;
    JFrame window;
    public Audio audio;
    public int level = 0;
    // CHANGE THIS to change final level (like 10 or something)
    int finallevel = 1;
    public boolean lastLevel = false;


    // Make new screen
    public void newGame(boolean dostartmenu){

        audio = new Audio(false);
        lastLevel = false;

        if(dostartmenu) {
            StartMenu startMenu = new StartMenu();
            startMenu.draw();

            //Hacky way to wait until the start window is closed
            while (startMenu.frame.isDisplayable()) {

            }
        }

        // Setup window, screen, generate enemies and objects
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure");

        screen = new Screen(this);
        playMusic();
        screen.enemies = generateEnemies(1);
        screen.objects = generateObjects(1);;

        window.add(screen);
        window.pack();
        window.setVisible(true);
        screen.startThread();

    }

    public void restartGame(){

       newGame(false);

    }

    // Based on the level, generate enemies
    public ArrayList<Enemy> generateEnemies(int level){

        ArrayList<Enemy> enemies = new ArrayList<>();

        EnemyFactory ef = new EnemyFactory();

        if(level <= 1){
            for(int i=1; i<=2; i++) {
                Enemy e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
        }else if(level <= 3){
            Enemy e;
            for(int i=2; i<=4; i++) {
                e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
            e = ef.getEnemy(screen, 3);
            enemies.add(e);
        }else if(level <= 5){
            Enemy e;
            for(int i=3; i<=5; i++) {
                e = ef.getEnemy(screen, i);
                enemies.add(e);
            }
            e = ef.getEnemy(screen, 4);
            enemies.add(e);
            e = ef.getEnemy(screen, 5);
            enemies.add(e);
        }

        return enemies;

    }

    // Starts a new level, sets screen enemies, player pos, and has it load new map
    public void newLevel(){

        level++;

        if(level != finallevel) {
            screen.loadMap();
            screen.enemies = generateEnemies(level);
            screen.player.setPos();
            screen.objects = generateObjects(level);
        }else{
            lastLevel = true;
            finalLevel();
        }

    }

    // Generate objects
    public ArrayList<GameObject> generateObjects(int level){

        ArrayList<GameObject> objs = new ArrayList<>();
        ObjectFactory obj = new ObjectFactory();
        GameObject o = null;
        GameObject o2 = null;

        var rand = Math.random();
        var rand2 = Math.random();
        // 50% chance between just chest, or shroom with chance of chest as well
        if(rand < 0.5){
            o = obj.getObject(screen, "chest");
        }else{
            if(rand2 < 0.5) {
                o = obj.getObject(screen, "shroom");
            }else{
                o = obj.getObject(screen, "shroom");
                o2 = obj.getObject(screen, "chest");
            }
        }

        if(o != null)
            objs.add(o);
        if(o2 != null)
            objs.add(o2);

        return objs;

    }

    // Loads final level, setting variables
    public void finalLevel(){

        screen.boss.x = screen.screenWidth/2-(screen.tileSize*4/2);
        screen.boss.y = 100;
        audio.stop();
        audio = new Audio(true);
        playMusic();
        screen.loadMap();
        screen.enemies = null;
        screen.objects = null;
        screen.state_ = new bossState(this.screen);
        screen.player.x = screen.screenWidth/2;
        screen.player.y = screen.screenHeight-(screen.tileSize*2);

    }

    public void playMusic(){
        audio.play();
        audio.loop();
    }


}
