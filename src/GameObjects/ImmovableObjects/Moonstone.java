package GameObjects.ImmovableObjects;
/*
    Moonstone Methods:                                              |Return     | Parameter
    Grass    -> Constructor of Moonstone                            |           | int,int,int,int
*/
public class Moonstone extends ImmovableObject{
    public Moonstone(int x, int y, int width, int height) {
        super(x, y, width, height, "moonstone.png");
    }
}
