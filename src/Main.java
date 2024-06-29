import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private static GameFrame gameFrame;
    private static int overAllCollectedCoins = 0;
    private static int jetpackLevel = 1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createMenu);
    }

    private static void createMenu() {
        JFrame menu = new JFrame("Astronauten Spiel");
        menu.setSize(500, 500);
        menu.setLocation(1000, 200);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton settings = new JButton("Settings");
        JButton upgradeJetpack = new JButton("Upgrade Jetpack");
        JButton startGameButton = new JButton("Start Game");

        settings.addActionListener(e -> {
            // Add settings functionality here
        });

        upgradeJetpack.addActionListener(e -> {
            jetpackLevel++;
            System.out.println("Jetpack upgraded to level " + jetpackLevel);
        });

        startGameButton.addActionListener(e -> {
            if (gameFrame != null) {
                gameFrame.reset();
            } else {
                gameFrame = new GameFrame();
                gameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        System.out.println("Coins collected: " + GameFrame.collectedCoin);
                        overAllCollectedCoins += GameFrame.collectedCoin;
                        System.out.println("Overall collected coins: " + overAllCollectedCoins);
                    }
                });
            }
            gameFrame.start();
            gameFrame.setVisible(true);
        });

        menu.setLayout(new BoxLayout(menu.getContentPane(), BoxLayout.Y_AXIS));
        menu.add(Box.createVerticalGlue());
        menu.add(settings);
        menu.add(upgradeJetpack);
        menu.add(startGameButton);
        menu.add(Box.createVerticalGlue());

        menu.setVisible(true);
    }
}
