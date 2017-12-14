import javax.swing.*;
import java.awt.*;

class Game extends JPanel {
    private static int initialWidth = 400 * 3 / 2;
    private static int initialHeight = 300 * 3 / 2;
    private Character character;
    private static Color bkd = Color.lightGray;
    private ArrowRepeater arrowRepeater;
    private Inertia inertia;
    private Friction friction;


    private Game() {
        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setResizable(true);
        myFrame.addKeyListener(new KeyboardMan(this));
        myFrame.add(this);
        this.setPreferredSize(new Dimension(initialWidth, initialHeight));
        myFrame.pack();
        myFrame.setVisible(true);

        character = new Character(this);
        inertia = new Inertia(character);
        inertia.start();
        friction = new Friction(character);
        friction.start();

        arrowRepeater = new ArrowRepeater(character, 0.07);
        arrowRepeater.start();

        new Painter(this).start();
    }

    void handle(Directions a, boolean pressed) {

        switch (a) {
            case LEFT:
                arrowRepeater.leftEnabled = pressed;
                break;
            case RIGHT:
                arrowRepeater.rightEnabled = pressed;
                break;
            case DOWN:
                arrowRepeater.downEnabled = pressed;
                break;
            case UP:
                arrowRepeater.upEnabled = pressed;
                break;
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(bkd);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (character != null)
            g.drawImage(character.icon, character.getX(), character.getY(), null);
    }

    public static void main(String[] args) {
        new Game();
    }
}


