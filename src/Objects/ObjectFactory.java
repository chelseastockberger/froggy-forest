package Objects;

import Main.Screen;

public class ObjectFactory {


    public GameObject getObject(Screen s, String obj){

        switch(obj){
            case "chest":
                return new Chest(s);
            case "shroom":
                return new Mushroom(s);

        }

        return null;

    }




}
