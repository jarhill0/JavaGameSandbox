@SuppressWarnings("InfiniteLoopStatement")
class ArrowRepeater extends Thread {
    private double change;
    private Character character;

    public boolean leftEnabled = false;
    public boolean rightEnabled = false;
    public boolean downEnabled = false;
    public boolean upEnabled = false;


    public ArrowRepeater(Character character, double change) {
        this.change = change;
        this.character = character;
    }

    public void run() {
        while (true) {
            character.accelerate(change, rightEnabled, leftEnabled, downEnabled, upEnabled);

            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}


