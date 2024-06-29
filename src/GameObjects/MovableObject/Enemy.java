package GameObjects.MovableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Enemy extends MovableObject {
    protected int animationX = 0;
    protected int animationY = 0;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height, "Enemy/enemy.png");
        maxSpeed = 5.0;
        acceleration = 0.5;
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

    public void moveToPlayer(Player player) {
        int playerX = player.x;
        int playerY = player.y;


        if (playerY > y && gravity < 0){
            gravity *= -1;
        }else if (playerY < y && gravity > 0){
            gravity *= -1;
        }

        if (playerX > x) {
            velocityX += acceleration;
        } else if (playerX < x) {
            velocityX -= acceleration;
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


}
