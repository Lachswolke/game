package GameObjects.MovableObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends MovableObject{
    protected int animationX = 0;
    protected int animationY = 0;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, "player-sheet.png");
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

    private Image mirrorImage(BufferedImage image){
        System.out.println("MOIN");
        if (direction.equals("right")){
            AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth(),0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = op.filter(image,null);
        }
        return image;
    }
}
