package GameObjects.Collectibles;
/*
    Coin Methods:                                                   |Return     | Parameter
    Coin    -> Constructor of Coin                                  |           | int,int,int,int
*/
public class Coin extends CollectibleObject{
    public Coin(int x, int y, int width, int height) {

        super(x, y, width, height, "coin.png");
    }

}
