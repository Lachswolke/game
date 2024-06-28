import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static GameFrame gameFrame;
    public static int overAllCollectedCoins = 0;

    public static void main(String[] args) {
        JFrame menu = new JFrame("Astronauten Spiel");
        menu.setSize(500, 500);
        menu.setLocation(1000, 200);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setLayout(null);
        menu.setVisible(true);

        JButton startGameButton = new JButton("Starte das Spiel");
        startGameButton.setBounds(100, 100, 300, 100);
        startGameButton.addActionListener(e -> {
            if (gameFrame != null) {
                gameFrame.reset();
                gameFrame.start();
                gameFrame.setVisible(true);
            } else {
                gameFrame = new GameFrame();
                gameFrame.setVisible(true);
                gameFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        System.out.println("Coins collected: " + GameFrame.collectedCoin);
                        overAllCollectedCoins += GameFrame.collectedCoin;
                        System.out.println("Overall: " + overAllCollectedCoins);
                    }
                });
            }
        });

        menu.add(startGameButton);
    }
}
