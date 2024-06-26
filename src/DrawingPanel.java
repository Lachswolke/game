import GameObjects.Collectibles.Coin;
import GameObjects.GameObject;
import GameObjects.MovableObject.MovableObject;
import GameObjects.Collectibles.CollectibleObject;
import GameObjects.MovableObject.Player;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DrawingPanel extends JPanel {

    protected List<GameObject> objects = new ArrayList<>();
    protected List<CollectibleObject> collectibles = new ArrayList<>();
    protected Random random = new Random();
    protected MovableObject player;
    private boolean collectiblesSpawned = false;
    protected TerrainGeneration terrainGeneration;

    public DrawingPanel() {
        Random rn = new Random();
        terrainGeneration = new TerrainGeneration(10, 1000,1000);
        player = new Player(0, 0, 100, 100);
        player.setParentPanel(this);

        objects.add(player);
        terrainGeneration.generateTerrain().stream().forEach(i -> objects.add(i));
        addKeyListener(player);

        setFocusable(true);

        // Start the game loop
        Timer timer = new Timer(16, e -> update());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        objects.stream().filter(Objects::nonNull).forEach(i -> i.draw(g));
        collectibles.stream().filter(Objects::nonNull).forEach(i -> i.draw(g));

        if (!collectiblesSpawned && getWidth() > 0 && getHeight() > 0) {
            spawnCollectibles(5);
            collectiblesSpawned = true;
        }
    }

    public void update() {
        objects.stream().filter(Objects::nonNull).forEach(GameObject::update);
        CollisionHandler.checkCollisions(objects);
        List<CollectibleObject> collected = CollisionHandler.checkCollectibleCollisions(player, collectibles);
        if (!collected.isEmpty()) {
            collectibles.removeAll(collected);
            spawnCollectibles(collected.size());
        }
        repaint();
    }

    private void spawnCollectibles(int count) {
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(getWidth() - 50); // 20 is the width of the collectible
            int y = random.nextInt(getHeight() - 50); // 20 is the height of the collectible
            CollectibleObject collectible = new Coin(x, y, 50, 50);
            collectibles.add(collectible);
        }
    }
}
