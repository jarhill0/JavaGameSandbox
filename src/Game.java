import javax.swing.*;
import java.awt.*;

class Game extends JPanel {
    private int width = 400 * 3 / 2;
    private int height = 300 * 3 / 2;
    private Character character;
    private static Color bkd = Color.lightGray;
    private DirectionThread directionThread;
    boolean updated = true;


    private Game() {
        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.addKeyListener(new KeyboardMan(this));
        myFrame.add(this);
        this.setPreferredSize(new Dimension(width, height));
        myFrame.pack();
        myFrame.setVisible(true);

        character = new Character(this);

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
        if (character != null)
            g.drawImage(character.icon, character.getX(), character.getY(), null);
    }

    public static void main(String[] args) {
        new Game();
    }
}


