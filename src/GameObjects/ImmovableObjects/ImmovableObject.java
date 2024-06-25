package GameObjects.ImmovableObjects;

import GameObjects.GameObject;

/*
    ImmovableObject Methods:                                       |Return     | Parameter
    ImmovableObject     -> Constructor of CollectibleObject        |           | int,int,int,int,String
    update              -> can be used to change Coordinates       |           |
*/
public class ImmovableObject extends GameObject {
    public ImmovableObject(int x, int y, int width, int height, String texturePath) {
        super(x, y, width, height, "/Blöcke/" + texturePath);
    }

    @Override
    public void update() {
    }
}
