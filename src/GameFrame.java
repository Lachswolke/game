import GameObjects.Collectibles.Coin;

import javax.swing.*;

public class GameFrame extends JFrame {
    public DrawingPanel panel;
    public static int collectedCoin = 0;

    public GameFrame() {
        setTitle("DrawingPanel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500, 0);
        setSize(1000, 1000);

        panel = new DrawingPanel();
        add(panel);

        setupGameLoop();
    }

    public void reset() {
        collectedCoin = Coin.colletedCoins;
        Coin.colletedCoins = 0;
        panel.reset();
    }

    public void start() {
        panel.startGame();
    }

    private void setupGameLoop() {
        int fps = 60;
        int delay = 1000 / fps; // Milliseconds per frame

        Timer timer = new Timer(delay, e -> panel.update());
        timer.start();
    }
}
