package Main;

import Entities.Enemy;
import Entities.Entity;
import Objects.GameObject;
import TileMap.Tile;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/*
COLLISION
Handles collision.
Checks if entity colliding with tile, player colliding with monster,
player colliding with object

 */

public class Collision {

    Screen s;
    public Collision(Screen s){
        this.s = s;
    }

    // Given an entity, sees if the tile they are going on is collidable
    public void checkTile(Entity entity){

        if (s.map.getTileAtPos(entity.x+(s.tileSize/2), entity.y+(60)).type.collision) {
            entity.collision = true;
        }

    }

    // Given the player, sees if colliding with monster and returns the monster
    public Enemy getCollidingMonster(Entity player){

        int dist = s.tileSize;

        for(Enemy e: s.enemies){
            double currDist = sqrt(pow((player.x - e.x),2) + pow((player.y - e.y),2));
            if(currDist <= dist){
                return e;
            }
        }

        return null;

    }

        // Given an entity, sees if the player is colliding with it
    public boolean getIsPlayerColliding(Entity e){

        int dist = s.tileSize;
        double currDist = sqrt(pow((s.player.x - e.x),2) + pow((s.player.y - e.y),2));
        if(currDist <= dist){
            return true;
        }else{
            return false;
        }

    }

    // Given an entity, checks all the objects and returns the one it is colliding with if so
    public GameObject checkObjects(Entity player){

        int dist = s.tileSize;

        for(GameObject o: s.objects){
            double currDist = sqrt(pow((player.x - o.x),2) + pow((player.y - o.y),2));
            if(currDist <= dist && o.collision){
                return o;
            }
        }

        return null;

    }

    public Enemy getEnemyAtPos(int x, int y){

        int dist = s.tileSize;

        if(!s.enemies.isEmpty()) {

            for (Enemy e : s.enemies) {
                double currDist = sqrt(pow((x - e.x), 2) + pow((y - e.y), 2));

                if (currDist <= dist) {
                    return e;
                }

            }

        }

        return null;

    }



}
