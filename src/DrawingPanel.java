import GameObjects.Collectibles.Coin;
import GameObjects.GameObject;
import GameObjects.MovableObject.Enemy;
import GameObjects.Collectibles.CollectibleObject;
import GameObjects.MovableObject.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DrawingPanel extends JPanel {
    protected List<GameObject> objects;
    protected List<CollectibleObject> collectibles;
    protected Random random;
    protected Player player;
    protected Enemy enemy;
    private boolean collectiblesSpawned;
    protected TerrainGeneration terrainGeneration;
    protected BufferedImage backgroundImage;
    private Timer timer;

    public DrawingPanel() {
        initializeGame();
    }

    public void initializeGame() {
        collectibles = new ArrayList<>();
        objects = new ArrayList<>();
        random = new Random();
        collectiblesSpawned = false;
        terrainGeneration = new TerrainGeneration(10, 1000, 1000);
        player = new Player(0, 0, 100, 100);
        enemy = new Enemy(900, 900, 100, 100);

        String pathWay = "Recourses/Background/background_black.png";
        Path path = Paths.get(pathWay).toAbsolutePath();

        try {
            backgroundImage = ImageIO.read(new File(String.valueOf(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setBackground(Color.BLACK);

        player.setParentPanel(this);
        enemy.setParentPanel(this);

        objects.add(player);
        objects.add(enemy);
        objects.addAll(terrainGeneration.generateTerrain());
        addKeyListener(player);

        setFocusable(true);
        requestFocusInWindow();

        if (timer == null) {
            timer = new Timer(16, e -> update());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        objects.stream().filter(Objects::nonNull).forEach(i -> i.draw(g));
        collectibles.stream().filter(Objects::nonNull).forEach(i -> i.draw(g));

        if (!collectiblesSpawned && getWidth() > 0 && getHeight() > 0) {
            spawnCollectibles(5);
            collectiblesSpawned = true;
        }

        Font myFont = new Font("Serif", Font.BOLD, 50);
        g.setFont(myFont);
        g.setColor(Color.WHITE);
        g.drawString("" + Coin.colletedCoins, 30, 30);
    }

    public void update() {
        if (CollisionHandler.enemyIntersectsPlayer(player, enemy)) {
            stopGame();
            return;
        }
        objects.stream().filter(Objects::nonNull).forEach(GameObject::update);
        CollisionHandler.checkCollisions(objects);
        List<CollectibleObject> collected = CollisionHandler.checkCollectibleCollisions(player, collectibles);
        if (!collected.isEmpty()) {
            collectibles.removeAll(collected);
            spawnCollectibles(collected.size());
        }
        enemy.moveToPlayer(player);
        repaint();
    }

    private void spawnCollectibles(int count) {
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(getWidth() - 50); // 20 is the width of the collectible
            int y = random.nextInt(getHeight() - 50); // 20 is the height of the collectible
            CollectibleObject collectible = new Coin(x, y, 50, 50);
            if (!CollisionHandler.checkForCollisionBeforeSpawning(collectible, objects)) {
                collectibles.add(collectible);
            } else {
                i--;
            }
        }
    }

    private void stopGame() {
        Main.gameFrame.setVisible(false);
        timer.stop();
        GameFrame.collectedCoin = Coin.colletedCoins;
        Main.gameFrame.dispose();
    }

    public void reset() {
        timer.stop();
        initializeGame();
    }

    public void startGame() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void interrupt() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }

    public void resume() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }
}
