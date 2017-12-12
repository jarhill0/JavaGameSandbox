import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character {
    public BufferedImage icon;
    private int[] position = new int[2];

    public Character() {
        position[0] = 0;
        position[1] = 0;
        getImage();
    }

    public Character(int x, int y) {
        position[0] = x;
        position[1] = y;
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
    }


}
