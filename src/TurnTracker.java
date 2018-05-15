public class TurnTracker {
    private TurnMarker marker;
    private boolean inCollision = false;

    public TurnTracker(TurnMarker marker) {
        this.marker = marker;
    }

    public void handle(boolean areColliding) {
        if (inCollision && !areColliding)  // we just left a collision
            marker.switchTurn();
        inCollision = areColliding;
    }
}
