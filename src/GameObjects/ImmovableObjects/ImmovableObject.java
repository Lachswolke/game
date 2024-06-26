package GameObjects.ImmovableObjects;

import GameObjects.GameObject;

import java.awt.*;

public class ImmovableObject extends GameObject {
    public ImmovableObject(int x, int y, int width, int height, String texturePath) {
        super(x, y, width, height, "/Blöcke/"+texturePath);
    }

    @Override
    public void update() {}
}
