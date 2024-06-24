package GameObjects.Collectibles;


import GameObjects.GameObject;

import java.awt.*;

public class CollectibleObject extends GameObject {

    public CollectibleObject(int x, int y, int width, int height, String texturePath) {
        super(x, y, width, height, "/Collectibles/"+texturePath);
    }


    @Override
    public void update() {
        // No movement for collectible objects
    }
}
