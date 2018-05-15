public class TurnTracker {
    private TurnMarker marker;
    private boolean inCollision = false;

    public TurnTracker(TurnMarker marker) {
        this.marker = marker;
    }

    // returns true if we toggle the "it" this frame
    public boolean handle(boolean areColliding) {
        boolean retval = false;  // bad practice, I know, but I need to set inCollision after reading it
        if (inCollision && !areColliding) {  // we just left a collision
            marker.switchTurn();
            retval = true;
        }
        inCollision = areColliding;
        return retval;
    }
}
