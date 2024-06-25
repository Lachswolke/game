package GameObjects.Collectibles;
import GameObjects.GameObject;
/*
    CollectibleObject Methods:                                      |Return     | Parameter
    CollectibleObject    -> Constructor of CollectibleObject        |           | int,int,int,int,String
    update               -> can be used to change Coordinates       |           |
*/

public class CollectibleObject extends GameObject {

    public CollectibleObject(int x, int y, int width, int height, String texturePath) {
        super(x, y, width, height, "/Collectibles/"+texturePath);
    }


    @Override
    public void update() {}
}
