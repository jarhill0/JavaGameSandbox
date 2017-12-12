import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private int width = 400 * 3 / 2;
    private int height = 300 * 3 / 2;
    private Character character;
    private static Color bkd = Color.black;
    DirectionThread left;
    DirectionThread right;
    DirectionThread up;
    DirectionThread down;


    public Game() {
        JFrame myFrame = new JFrame();
        myFrame.setSize(width, height);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);

        character = new Character(40, 40);

        myFrame.addKeyListener(new KeyboardMan(this));

        myFrame.add(this);
        myFrame.setVisible(true);

        left = new DirectionThread(character, 1, Directions.LEFT);
        right = new DirectionThread(character, 1, Directions.RIGHT);
        up = new DirectionThread(character, 1, Directions.UP);
        down = new DirectionThread(character, 1, Directions.DOWN);

        left.start();
        right.start();
        up.start();
        down.start();

        new Painter(this).start();
    }

    void handle(Directions a, boolean pressed) {

        switch (a) {
            case LEFT:
                left.enabled = pressed;
                break;
            case RIGHT:
                right.enabled = pressed;
                break;
            case DOWN:
                down.enabled = pressed;
                break;
            case UP:
                up.enabled = pressed;
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


