import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Character implements Paintable {
    BufferedImage icon;
    private double[] position = new double[2];
    private Game game;
    private Vector velocity = new Vector();

    private static double acceleration = 0.22;
    private static double bounciness = -0.1;
    private int score = 0;

    // remember which keys are being pressed
    private boolean down = false;
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;

    Character(Game game, String iconName) {
        // The method isColliding assumes the image is a 50x50px image. Deal with it.
        this(game, iconName, 0, 0);
    }

    Character(Game game, String iconName, int x, int y) {
        // The method isColliding assumes the image is a 50x50px image. Deal with it.
        position[0] = x;
        position[1] = y;
        this.game = game;
        getImage(iconName);
    }

    private void getImage(String iconName) {
        try {
            icon = ImageIO.read(getClass().getResource("/" + iconName));
        } catch (IOException e) {
            System.out.println("Couldn't get image");
            System.exit(1);
        }
    }

    /*

    For both getX() and getY():

    Location is stored at decimal precision but returned in int precision because we can only paint to integer
    coordinates, and I'm not about to get into antialiasing stuff.

    */

    int getX() {
        return round(position[0]);
    }

    int getY() {
        return round(position[1]);
    }

    public void paint(Graphics g) {
        g.drawImage(icon, getX(), getY(), null);

    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public void scorePoint() {
        score++;
    }

    private static int round(double num) {
        return num % 1 < 0.5 ? (int) num : (int) num + 1;
    }

    void step() {
        // integrate acceleration -> velocity
        accelerate();

        // integrate velocity -> position
        position[0] += velocity.getX();
        position[1] += velocity.getY();
        constrainToWindow();
        frict();
    }


    // Make sure the character isn't off the edge of the window and handle bounce-back.
    private void constrainToWindow() {
        int width = game.getWidth();
        int height = game.getHeight();


        if (position[0] + icon.getWidth() > width || position[0] < 0) { // if we're off the right or left edge
            // Bounce our x-velocity back
            velocity.shortenX(bounciness); // small amount in opposite direction
            position[0] = position[0] < 0 ? 0 : width - icon.getWidth(); // push back from the wall
        }

        // same as above, only for the top and bottom edges

        if (position[1] + icon.getHeight() > height || position[1] < 0) {
            velocity.shortenY(bounciness);
            position[1] = position[1] < 0 ? 0 : height - icon.getHeight();
        }
    }


    // Acceleration is an "all or nothing" thing -- no partial acceleration. Thus, up and down cancel out.
    //
    // Note: This is not TagPro-style acceleration. That is to say, accelerating down and right at the same time gives
    // the same magnitude of acceleration as accelerating right only. In other words, if a character is already
    // accelerating right, starting to accelerate up at the same time reduces the x-acceleration.
    private void accelerate() {
        if (right == left && down == up) {
            // no "active" arrows; no acceleration
            return;
        } else if (right == left) { // down != up
            velocity.add(acceleration, (up ? 1 : 3) * Vector.QUARTER);  // up or down only
        } else if (up == down) {
            velocity.add(acceleration, right ? 0 : Vector.HALF);
        } else {
            velocity.add(acceleration, Vector.EIGHTH * ((left ? 1 : 0) + (down ? 2 : 0)));  // weird math to find direction
        }
    }

    private void frict() {
        // slowly reduces the velocity of the character proportionally to velocity.
        // idk whether this is accurate to real world physics
        // I don't think so, because this also has the (nice) side effect of capping a maximum velocity
        velocity.shortenInPlace(0.993);
    }

    boolean isColliding(Character c) {
        return isColliding(c.getX(), c.getY());
    }

    boolean isColliding(int x, int y) {
        // This method assumes that the coordinates represent the centers of each circular sprite.
        // This assumption is false, but the math works out because the error is cancelled out.

        int distanceX = getX() - x;
        int distanceY = getY() - y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distance < 50; // diameter
    }

    // returns the center of mass of a collision between 2 circles as a length 2 array {x, y}
    int[] collisionCenter(Character c) {
        return collisionCenter(c.getX(), c.getY());
    }

    // returns the center of mass of a collision between 2 circles as a length 2 array {x, y}
    int[] collisionCenter(int x, int y) {
        int xSolution = (getX() + x) / 2;
        int ySolution = (getY() + y) / 2;
        return new int[]{xSolution + 25, ySolution + 25}; // offset for size of the images
    }

    void setUp(boolean up) {
        this.up = up;
    }

    void setDown(boolean down) {
        this.down = down;
    }

    void setLeft(boolean left) {
        this.left = left;
    }

    void setRight(boolean right) {
        this.right = right;
    }

    void setPosition(int x, int y) {
        position[0] = (double) x;
        position[1] = (double) y;
    }

    void resetVelocity() {
        velocity = new Vector();
    }

}


