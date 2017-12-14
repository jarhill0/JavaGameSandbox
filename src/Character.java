import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Character {
    public BufferedImage icon;
    private int[] position = new int[2];
    private Game game;

    public Character(Game game) {
        position[0] = 0;
        position[1] = 0;
        this.game = game;
        getImage();
    }

    public Character(Game game, int x, int y) {
        position[0] = x;
        position[1] = y;
        this.game = game;
        getImage();
    }

    private void getImage() {
        try {
            File file = new File(new File(System.getProperty("user.dir"), "src"), "icon.png");
            icon = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Couldn't get image");
            System.exit(1);
        }
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }

    public void move(int by, Directions d) {
        game.updated = true;

        switch (d) {
            case RIGHT:
                position[0] += by;
                break;
            case LEFT:
                position[0] -= by;
                break;
            case DOWN:
                position[1] += by;
                break;
            case UP:
                position[1] -= by;
                break;
        }

        int width = game.getWidth();
        int height = game.getHeight();

        if (position[0] + icon.getWidth() > width) {
            position[0] = width - icon.getWidth();
        } else if (position[0] < 0) {
            position[0] = 0;
        }


        if (position[1] + icon.getHeight() > height) {
            position[1] = height - icon.getHeight();
        } else if (position[1] < 0) {
            position[1] = 0;
        }
    }


}
