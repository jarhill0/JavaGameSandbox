import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private int width = 400 * 3 / 2;
    private int height = 300 * 3 / 2;
    private Character character;
    private static Color bkd = Color.black;
    DirectionThread directionThread;


    public Game() {
        JFrame myFrame = new JFrame();
        myFrame.setSize(width, height);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);

        character = new Character(40, 40);

        myFrame.addKeyListener(new KeyboardMan(this));

        myFrame.add(this);
        myFrame.setVisible(true);

        directionThread = new DirectionThread(character, 1);
        directionThread.start();

        new Painter(this).start();
    }

    void handle(Directions a, boolean pressed) {

        switch (a) {
            case LEFT:
                directionThread.leftEnabled = pressed;
                break;
            case RIGHT:
                directionThread.rightEnabled = pressed;
                break;
            case DOWN:
                directionThread.downEnabled = pressed;
                break;
            case UP:
                directionThread.upEnabled = pressed;
                break;
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(bkd);
        g.fillRect(0, 0, width, height);
        g.drawImage(character.icon, character.getX(), character.getY(), null);
    }

    public static void main(String[] args) {
        new Game();
    }
}


