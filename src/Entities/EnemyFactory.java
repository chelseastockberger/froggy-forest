package Entities;

import Main.Screen;

public class EnemyFactory {

    public Enemy getEnemy(Screen s, int level) {

        switch(level){
            case 1:
                return new Ghost(s);
            case 2:
                return new Blob(s);
            case 3:
                return new Isopod(s);
            case 4:
                return new Skull(s);
            case 5:
                return new Head(s);
        }

        return null;

    }


}
