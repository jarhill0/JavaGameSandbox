import java.awt.*;
import java.util.List;

public class TurnMarker implements Paintable {
    private Game game;
    private Character playerA;
    private Character playerB;
    private boolean flag = false;

    public TurnMarker(Game game, List<Character> characters) {
        this.game = game;
        playerA = characters.get(0);
        playerB = characters.get(1);
    }

    public void switchTurn() {
        flag = !flag;
    }

    private Character getPlayerTurn() {
        return flag ? playerA : playerB;
    }

    public void paint(Graphics g) {
        int margin = 10; // px
        Character activePlayer = getPlayerTurn();
        int x = game.getWidth() - (activePlayer.icon.getWidth() + 2 * margin);
        int y = margin;

        g.setColor(Color.BLACK);
        g.drawString("You're it!", x, y);
        y += 2 * margin;
        g.fillRect(x - margin / 2, y - margin / 2, activePlayer.icon.getWidth() + margin, activePlayer.icon.getHeight() + margin);
        g.drawImage(activePlayer.icon, x, y, null);
    }
}
