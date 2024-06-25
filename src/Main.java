import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame menu = new JFrame("Astronauten Spiel");
        menu.setSize(500,500);
        menu.setLocation(1000,200);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menu.setVisible(true);

        JButton startGameButton = new JButton();
        startGameButton.setBounds(100,100,300,100);
        startGameButton.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
        startGameButton.setText("Starte das Spiel");

        menu.add(startGameButton);








    }
}