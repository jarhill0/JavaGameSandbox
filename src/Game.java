import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Main class
class Game extends JPanel {
    private Character primaryCharacter;
    private Character secondaryCharacter;
    private Explosion explosion;
    TurnTracker tracker;
    Countdown countdown;

    private List<Paintable> sprites = new ArrayList<Paintable>(3);

    private static Color backgroundDefault = Color.lightGray;
    private static Color backgroundHighlight = Color.yellow;
    private static Color backgroundPaused = Color.cyan;

    private boolean paused;

    // Construct and start a game.
    private Game() {
        int initialWidth = 400 * 3 / 2;
        int initialHeight = 300 * 3 / 2;

        // Configure window
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setResizable(true);
        myFrame.add(this);
        this.setPreferredSize(new Dimension(initialWidth, initialHeight));
        myFrame.pack();

        explosion = new Explosion();

        primaryCharacter = new Character(this, "WASD.png");
        secondaryCharacter = new Character(this, "IJKL.png");
        ArrayList<Character> characters = new ArrayList<Character>(2);
        characters.add(primaryCharacter);
        characters.add(secondaryCharacter);
        tracker = new TurnTracker(this, characters);
        countdown = new Countdown(this, 31);
        Scoreboard scoreboard = new Scoreboard(this, primaryCharacter, secondaryCharacter);

        sprites.add(tracker);
        sprites.add(countdown);
        sprites.add(scoreboard);
        sprites.add(explosion);  // added in "bottom up" order that they will be painted
        sprites.add(secondaryCharacter);
        sprites.add(primaryCharacter);

        myFrame.setVisible(true);
        GameLoop gameLoop = new GameLoop(this, characters);
        myFrame.addKeyListener(new KeyboardHandler(characters, this));
        restart();
        gameLoop.start(); // Starts infinite loop in painting/physics thread
    }

    // todo seems like some of this should be in the GameLoop
    public void paintComponent(Graphics g) {
        if (primaryCharacter == null || secondaryCharacter == null)
            return;

        boolean trackerValue = tracker.handle(primaryCharacter.isColliding(secondaryCharacter));
        if (primaryCharacter.isColliding(secondaryCharacter)) {
            g.setColor(backgroundHighlight);
            int[] collisionLocation = primaryCharacter.collisionCenter(secondaryCharacter);
            explosion.setVisible(collisionLocation[0], collisionLocation[1]);

        } else {
            g.setColor(isPaused() ? backgroundPaused : backgroundDefault);
            explosion.setInvisible();
        }

        g.fillRect(0, 0, this.getWidth(), this.getHeight()); // fill the screen with the background color
        if (isPaused()) {
            g.setColor(Color.BLACK);
            g.drawString("[ESC} to unpause\n[SPACE] to restart", getWidth() / 2 - 80, 150);
        }

        for (Paintable paintable : sprites)
            paintable.paint(g);

        if (trackerValue) { // we just ended a collision
            resetPlayerPosition();
            countdown.setTimeLeft(31);
        }
    }

    void resetPlayerPosition() {
        primaryCharacter.setPosition(0, 0);
        primaryCharacter.resetVelocity();

        secondaryCharacter.setPosition(this.getWidth() - secondaryCharacter.icon.getWidth(),
                this.getHeight() - secondaryCharacter.icon.getHeight());
        secondaryCharacter.resetVelocity();
    }

    public void restart() {
        primaryCharacter.resetScore();
        secondaryCharacter.resetScore();
        resetPlayerPosition();
        countdown.setTimeLeft(31);
        pause();
    }

    public void pause() {
        countdown.pause();
        paused = true;
    }

    public void unpause() {
        countdown.unpause();
        paused = false;
    }

    public void togglePause() {
        if (paused)
            unpause();
        else
            pause();
    }

    public void tryRestart() {// will only work if paused
        if (paused)
            restart();
    }

    public boolean isPaused() {
        return paused;
    }

    public static void main(String[] args) {
        new Game();
    }
}


