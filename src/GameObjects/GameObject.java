package GameObjects;

import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class GameObject {
    public int x;
    public int y;

    public int width;
    public int height;
    public abstract void update();
    protected BufferedImage image;

    public GameObject(int x, int y, int width, int height, String texturePath){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        String pathWay = "Reccourses" + texturePath;
        Path path = Paths.get(pathWay).toAbsolutePath();

        try {
            image = ImageIO.read(new File(String.valueOf(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void draw(Graphics g) {
        g.drawImage(image, x, y, width,height, null);
        if (image != null) {

        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
