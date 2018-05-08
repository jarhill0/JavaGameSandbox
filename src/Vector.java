class Vector {
    private double magnitude, direction; // direction is in radians
    static double EIGHTH = Math.PI / 4;

    Vector() {
        magnitude = direction = 0;
    }

    Vector(double magnitude, double direction) {
        if (magnitude < 0) {
            this.magnitude = -magnitude;
            setDirection(direction + Math.PI);
        } else if (magnitude == 0) {
            this.magnitude = magnitude;
            setDirection(0);
        } else {
            this.magnitude = magnitude;
            setDirection(direction);
        }
    }


    static Vector horizontalVector(double m) {
        return new Vector(m, 0);
    }

    static Vector verticalVector(double m) {
        return new Vector(m, Math.PI / 2);
    }


    // Set direction of the vector, normalized to be between 0 and 2pi.
    private void setDirection(double d) {
        direction = d % (2 * Math.PI);
    }

    double getX() {
        return Math.cos(direction) * magnitude;
    }

    double getY() {
        return Math.sin(direction) * magnitude;
    }


    // Adds vector to this in-place
    void add(Vector o) {
        double x = getX() + o.getX();
        double y = getY() + o.getY();

        setDirection(Math.atan2(y, x)); // fairly obvious conversion
        magnitude = Math.pow((x * x + y * y), 0.5); // pythagorean formula
    }

    void shortenInPlace(double by) {
        if (by == 0) {
            direction = 0;
        } else if (by < 0) {
            setDirection(direction + 180);
        }
        magnitude *= by;
    }
}
