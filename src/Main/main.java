package Main;
import javax.swing.JFrame;

/*
- Patterns -
Factory: Make Items/Weapons, Make Enemies
Singleton: Game
State: State, textState, etc...
Game Loop: Screen (OKAYED by BRUCE!)
Flyweight: TileFactory
 */

public class main {

    public static void main(String[] args){

        Game game = Game.getGame();

        game.newGame(true);

    }

}
