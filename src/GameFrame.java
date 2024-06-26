import javax.swing.*;

public class GameFrame extends JFrame {
    protected DrawingPanel panel;

    public GameFrame(){
        setTitle("DrawingPanel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(1000,0);
        setSize(1000,1000);


        panel = new DrawingPanel();
        add(panel);

        setupGameLoop();
    }


    private void setupGameLoop() {
        int fps = 60;
        int delay = 1000 / fps; // Millisekunden pro Frame

        Timer timer = new Timer(delay, e -> panel.update());
        timer.start();
    }
}
