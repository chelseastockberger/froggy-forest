package TileMap;

import java.util.HashMap;

/**
 * FLYWEIGHT PATTERN
 *
 * Tile creation, setting tile data using flyweight pattern
 *
 */

public class TileFactory {

    private static final HashMap tileHashMap = new HashMap();

    public Tile getTile(char type){

        TileType tile = (TileType)tileHashMap.get(type);

        if(tile == null){
            tile = new TileType(type);
            tileHashMap.put(type, tile);
        }

         return new Tile(tile);
    }

}
