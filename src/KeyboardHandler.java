import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

class KeyboardHandler extends KeyAdapter {
    private List<Character> characters;

    KeyboardHandler(List<Character> characters) {
        this.characters = characters;
    }

    private Character getCharacter(int key) {
        return characters.get(key >= 73 && key <= 76 ? 1 : 0);
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
        Character character = getCharacter(key);

        if (key == 37 || key == 65 || key == 74) {
            character.setLeft(pressed);
        } else if (key == 39 || key == 68 || key == 76) {
            character.setRight(pressed);
        } else if (key == 38 || key == 87 || key == 73) {
            character.setUp(pressed);
        } else if (key == 40 || key == 83 || key == 75) {
            character.setDown(pressed);
        }
    }
}