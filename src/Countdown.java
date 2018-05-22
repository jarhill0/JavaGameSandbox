import java.awt.*;

public class Countdown implements Paintable {
    private long zeroTime;
    private long pauseTime = 0;
    private int cachedValue;
    private Game game;


    public Countdown(Game game, int timeLeft) {
        this.game = game;
        setTimeLeft(timeLeft);
    }

    int getTimeLeft() {
        if (pauseTime != 0)
            return cachedValue;
        int timeLeft = (int) (zeroTime - System.currentTimeMillis()) / 1000;
        return timeLeft > 0 ? timeLeft : 0;
    }

    void setTimeLeft(int time) {
        if (time < 0)
            time = 0;
        zeroTime = System.currentTimeMillis() + time * 1000;
    }

    void pause() {
        cachedValue = getTimeLeft();
        pauseTime = System.currentTimeMillis();
    }

    void unpause() {
        if (pauseTime != 0)
            return; // invalid call
        long diff = System.currentTimeMillis() - pauseTime;
        zeroTime += diff;
        pauseTime = 0;
    }

    public void paint(Graphics g) {
        int margin = 20;
        int y = 2 * margin;
        int x = game.getWidth() - 3 * margin;
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.BOLD, 30));
        g.drawString(String.format("%d", getTimeLeft()), x, y);
    }
}
