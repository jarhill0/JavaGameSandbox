import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardMan extends KeyAdapter {
    Game parent;

    KeyboardMan(Game parent) {
        this.parent = parent;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pass(e, false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pass(e, true);
    }

    private void pass(KeyEvent e, boolean pressed) {

        int key = e.getKeyCode();
        Directions arrow;

        if (key == 37 || key == 65) {
            arrow = Directions.LEFT;
        } else if (key == 39 || key == 68) {
            arrow = Directions.RIGHT;
        } else if (key == 38 || key == 87) {
            arrow = Directions.UP;
        } else if (key == 40 || key == 83) {
            arrow = Directions.DOWN;
        } else {
            return;
        }
        parent.handle(arrow, pressed);

    }

}

enum Directions {
    LEFT, RIGHT, UP, DOWN
}