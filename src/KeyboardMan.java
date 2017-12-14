import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyboardMan extends KeyAdapter {
    private Game parent;

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
        WhichChar character;

        character = (key >= 73 && key <= 76) ? WhichChar.SECONDARY : WhichChar.MAIN;
        if (key == 37 || key == 65 || key == 74) {
            arrow = Directions.LEFT;
        } else if (key == 39 || key == 68 || key == 76) {
            arrow = Directions.RIGHT;
        } else if (key == 38 || key == 87 || key == 73) {
            arrow = Directions.UP;
        } else if (key == 40 || key == 83 || key == 75) {
            arrow = Directions.DOWN;
        } else {
            return;
        }


        parent.handle(arrow, pressed, character);

    }

}

enum Directions {
    LEFT, RIGHT, UP, DOWN
}

enum WhichChar {
    MAIN, SECONDARY
}