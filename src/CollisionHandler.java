import GameObjects.Collectibles.Coin;
import GameObjects.GameObject;
import GameObjects.MovableObject.Enemy;
import GameObjects.MovableObject.MovableObject;
import GameObjects.Collectibles.CollectibleObject;
import GameObjects.MovableObject.Player;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class CollisionHandler {

    public static void checkCollisions(List<GameObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj1 = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject obj2 = objects.get(j);
                if (obj1.getBounds().intersects(obj2.getBounds())) {
                    handleCollision(obj1, obj2);
                }
            }
        }
    }

    private static void handleCollision(GameObject obj1, GameObject obj2) {
        if (obj1 instanceof MovableObject) {
            resolveCollision((MovableObject) obj1, obj2);
        }
        if (obj2 instanceof MovableObject) {
            resolveCollision((MovableObject) obj2, obj1);
        }
    }

    private static void resolveCollision(MovableObject movable, GameObject other) {
        Rectangle movableBounds = movable.getBounds();
        Rectangle otherBounds = other.getBounds();

        int dx = (movableBounds.x + movableBounds.width / 2) - (otherBounds.x + otherBounds.width / 2);
        int dy = (movableBounds.y + movableBounds.height / 2) - (otherBounds.y + otherBounds.height / 2);

        int overlapX = (movableBounds.width / 2 + otherBounds.width / 2) - Math.abs(dx);
        int overlapY = (movableBounds.height / 2 + otherBounds.height / 2) - Math.abs(dy);

        if (overlapX < overlapY) {
            if (dx > 0) {
                movable.x += overlapX;
            } else {
                movable.x -= overlapX;
            }
            movable.velocityX = 0; // Stop horizontal movement
        } else {
            if (dy > 0) {
                movable.y += overlapY;
            } else {
                movable.y -= overlapY;
            }
            movable.velocityY = 0; // Stop vertical movement
        }
    }

    public static List<CollectibleObject> checkCollectibleCollisions(MovableObject player, List<CollectibleObject> collectibles) {
        List<CollectibleObject> collected = new ArrayList<>();
        for (CollectibleObject collectible : collectibles) {
            if (player.getBounds().intersects(collectible.getBounds())) {
                Coin.colletedCoins++;
                collected.add(collectible);
            }
        }
        return collected;
    }

    public static boolean checkForCollisionBeforeSpawning(CollectibleObject collectibleObject, List<GameObject> immovableObjects){
        return immovableObjects.stream().anyMatch(c -> collectibleObject.getBounds().intersects(c.getBounds()));
    }

    public static boolean enemyIntersectsPlayer(Player player, Enemy enemy){
        int extendedHitbox = 10;
        Rectangle playerHitbox = new Rectangle(player.x+extendedHitbox,player.y+extendedHitbox,player.width+extendedHitbox,player.height+extendedHitbox);
        Rectangle enemyHitbox = new Rectangle(enemy.x+extendedHitbox,enemy.y+extendedHitbox,enemy.width+extendedHitbox,enemy.height+extendedHitbox);

        return playerHitbox.getBounds().intersects(enemyHitbox.getBounds());
    }
}
