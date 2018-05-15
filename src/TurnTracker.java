import java.awt.*;
import java.util.List;

public class TurnTracker implements Paintable {
    private boolean inCollision = false;

    private Game game;
    private Character playerA;
    private Character playerB;
    private boolean flag = false;

    public TurnTracker(Game game, List<Character> characters) {
        this.game = game;
        playerA = characters.get(0);
        playerB = characters.get(1);
    }

    // returns true if we toggle the "it" this frame
    public boolean handle(boolean areColliding) {
        boolean retval = false;  // bad practice, I know, but I need to set inCollision after reading it
        if (inCollision && !areColliding) {  // we just left a collision
            switchTurn();
            retval = true;
        }
        inCollision = areColliding;
        return retval;
    }

    void switchTurn() {
        flag = !flag;
    }

    private Character getPlayerTurn() {
        return flag ? playerA : playerB;
    }

    public void paint(Graphics g) {
        int margin = 10; // px
        Character activePlayer = getPlayerTurn();
        int x = margin;
        int y = game.getHeight() - (activePlayer.icon.getHeight() + 3 * margin);

        g.setColor(Color.BLACK);
        g.drawString("You're it!", x, y);
        y += 2 * margin;
        g.fillRect(x - margin / 2, y - margin / 2, activePlayer.icon.getWidth() + margin, activePlayer.icon.getHeight() + margin);
        g.drawImage(activePlayer.icon, x, y, null);
    }
}
