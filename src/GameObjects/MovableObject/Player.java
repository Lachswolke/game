package GameObjects.MovableObject;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends MovableObject implements KeyListener {
    protected int animationX = 0;
    protected int animationY = 0;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, "Player/player.png");
    }

    @Override
    public void draw(Graphics g) {
        if (texture != null) {
            if (gravity > 0) {
                BufferedImage subImage = texture.getSubimage(0, 0, width, height);
                g.drawImage(mirrorImage(subImage), x, y, width, height, null);
            } else {
                animationX += width;
                if (animationX >= texture.getWidth()) {
                    animationX = 0;
                    animationY += height;
                    if (animationY >= texture.getHeight()) {
                        animationY = 0;
                    }
                }

                BufferedImage subImage = texture.getSubimage(animationX, animationY, width, height);
                g.drawImage(mirrorImage(subImage), x, y, width, height, null);
            }
        }
    }

    private Image mirrorImage(BufferedImage image) {
        if (direction.equals("right")) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = op.filter(image, null);
        }
        return image;
    }

    @Override
    public void update() {
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
        super.update();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
            gravity *= -1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
}
