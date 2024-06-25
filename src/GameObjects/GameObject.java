package GameObjects;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
    GameObject Methods:                                                 |Return     | Parameter
    GameObject  -> Constructor of GameObject                            |           | int,int,int,int,String
    draw        -> Is used to Draw the Game Texture                     | void      | Graphics
    getBounds   -> Is used to get the Coordinate of the Game object     |Rectangle  |
    update      -> Forces Instances to be able to update Coordinates    |           |
*/

public abstract class GameObject {
    public int x; //X position in Game
    public int y; //Y position in Game

    public int width; //Object width
    public int height; //Object height
    protected BufferedImage texture; //The Texture of Object

    public GameObject(int x, int y, int width, int height, String texturePath){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        String pathWay = "Recourses" + texturePath;
        Path path = Paths.get(pathWay).toAbsolutePath();

        try {
            texture = ImageIO.read(new File(String.valueOf(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width,height, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public abstract void update();
}
