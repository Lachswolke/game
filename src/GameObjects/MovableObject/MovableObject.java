package GameObjects.MovableObject;
import GameObjects.GameObject;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.HashSet;
import javax.swing.*;

public class MovableObject extends GameObject implements KeyListener {

    protected Set<Integer> pressedKeys;
    public double velocityX, velocityY;
    protected double acceleration = 0.5;
    protected double friction = 0.1;
    protected double maxSpeed = 10.0;
    protected double gravity = 0.2; // Gravity force
    protected String direction = "left";
    protected JPanel parentPanel;

    public MovableObject(int x, int y, int width, int height , String texturePath) {
        super(x, y, width, height, "/Movable/"+texturePath);
        pressedKeys = new HashSet<>();
    }

    public void setParentPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x,y,width,height);
    }

    @Override
    public void update() {

        velocityY += gravity;

        if (pressedKeys.contains(KeyEvent.VK_S)) {
            velocityY += acceleration;
        }
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            velocityX -= acceleration;
            direction = "left";
        }
        if (pressedKeys.contains(KeyEvent.VK_D)) {
            velocityX += acceleration;
            direction = "right";
        }


        if (!pressedKeys.contains(KeyEvent.VK_A) && !pressedKeys.contains(KeyEvent.VK_D)) {
            if (velocityX > 0) {
                velocityX = Math.max(0, velocityX - friction);
            } else if (velocityX < 0) {
                velocityX = Math.min(0, velocityX + friction);
            }
        }

        velocityX = Math.max(-maxSpeed, Math.min(maxSpeed, velocityX));
        velocityY = Math.max(-maxSpeed, Math.min(maxSpeed, velocityY));

        int newX = x + (int) velocityX;
        int newY = y + (int) velocityY;

        if (parentPanel != null) {
            int panelWidth = parentPanel.getWidth();
            int panelHeight = parentPanel.getHeight();

            if (newX < 0) {
                newX = 0;
                velocityX = 0;
            } else if (newX + width > panelWidth) {
                newX = panelWidth - width;
                velocityX = 0;
            }

            if (newY < 0) {
                newY = 0;
                velocityY = 0;
            } else if (newY + height > panelHeight) {
                newY = panelHeight - height;
                velocityY = 0;
            }
        }

        x = newX;
        y = newY;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        if (pressedKeys.contains(KeyEvent.VK_SPACE)){
            gravity *= -1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
}
