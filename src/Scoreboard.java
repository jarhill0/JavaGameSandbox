import java.awt.*;

public class Scoreboard implements Paintable {
    private Character playerOne;
    private Character playerTwo;
    private Game game;

    public Scoreboard(Game game, Character playerOne, Character playerTwo) {
        this.game = game;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void paint(Graphics g) {
        String scoreString = String.format("%d - %d", playerOne.getScore(), playerTwo.getScore());
        int fontSize = 40;
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.BOLD, fontSize));
        int x = (game.getWidth() / 2) - 40;
        int y = game.getHeight() - (fontSize + 10);
        g.drawString(scoreString, x, y);
    }
}
