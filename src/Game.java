import javax.swing.*;
import java.awt.*;

class Game extends JPanel {
    private static int initialWidth = 400 * 3 / 2;
    private static int initialHeight = 300 * 3 / 2;
    private Character character;
    private Character secondaryCharacter;
    private static Color bkd = Color.lightGray;
    private ArrowRepeater arrowRepeater;
    private ArrowRepeater secondaryArrowRepeater;


    private Game() {
        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setResizable(true);
        myFrame.addKeyListener(new KeyboardMan(this));
        myFrame.add(this);
        this.setPreferredSize(new Dimension(initialWidth, initialHeight));
        myFrame.pack();
        myFrame.setVisible(true);

        character = new Character(this, "WASD.png");
        character.startPhysics();
        secondaryCharacter = new Character(this, "IJKL.png", initialWidth, initialHeight);
        secondaryCharacter.startPhysics();

        arrowRepeater = new ArrowRepeater(character, 0.06);
        arrowRepeater.start();
        secondaryArrowRepeater = new ArrowRepeater(secondaryCharacter, 0.06);
        secondaryArrowRepeater.start();

        new Painter(this).start();
    }

    void handle(Directions a, boolean pressed, WhichChar whichChar) {

        ArrowRepeater repeater = null;

        switch (whichChar) {
            case MAIN:
                repeater = arrowRepeater;
                break;
            case SECONDARY:
                repeater = secondaryArrowRepeater;
                break;
        }

        switch (a) {
            case LEFT:
                repeater.leftEnabled = pressed;
                break;
            case RIGHT:
                repeater.rightEnabled = pressed;
                break;
            case DOWN:
                repeater.downEnabled = pressed;
                break;
            case UP:
                repeater.upEnabled = pressed;
                break;
        }
    }

    public void paintComponent(Graphics g) {
        if (character != null) {
            if (secondaryCharacter != null) {
                if (!character.isColliding(secondaryCharacter)) {
                    g.setColor(bkd);
                } else {
                    g.setColor(Color.YELLOW);

                }
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.drawImage(secondaryCharacter.icon, secondaryCharacter.getX(), secondaryCharacter.getY(), null);
            }
            g.drawImage(character.icon, character.getX(), character.getY(), null);
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}


