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
        Arrows arrow;

        if (key == 37) {
            arrow = Arrows.LEFT;
        } else if (key == 39) {
            arrow = Arrows.RIGHT;
        } else if (key == 38) {
            arrow = Arrows.UP;
        } else if (key == 40) {
            arrow = Arrows.DOWN;
        } else {
            return;
        }
        parent.handle(arrow, pressed);

    }

}

enum Arrows {
    LEFT, RIGHT, UP, DOWN
}