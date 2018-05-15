import java.awt.*;

public class Countdown implements Paintable {
    private long zeroTime;


    public Countdown(int timeLeft) {
        setTimeLeft(timeLeft);
    }

    int getTimeLeft() {
        int timeLeft = (int) (zeroTime - System.currentTimeMillis());
        return timeLeft > 0 ? timeLeft : 0;
    }

    void setTimeLeft(int time) {
        if (time < 0)
            time = 0;
        zeroTime = System.currentTimeMillis() + time * 1000;
    }

    public void paint(Graphics g) {
        int margin = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.BOLD, 30));
        g.drawString(String.format("%d", getTimeLeft()), margin, margin);
    }
}
