class Vector {
    private double magnitude, direction; // direction is in radians
    static Vector ZERO = new Vector();
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

    private Vector(double[] coords) {
        if (coords.length != 2) {
            // invalid, just give a normal 0 vector. kinda dumb.
            magnitude = direction = 0;
        } else {
            double x = coords[0];
            double y = coords[1];

            magnitude = Math.pow((x * x + y * y), 0.5);
            setDirection(Math.atan2(y, x));
        }
    }

    static Vector horizontalVector(double m) {
        return new Vector(m, 0);
    }

    static Vector verticalVector(double m) {
        return new Vector(m, Math.PI / 2);
    }

    private void setDirection(double d) {
        direction = d % (2 * Math.PI);
    }

    double getX() {
        return Math.cos(direction) * magnitude;
    }

    double getY() {
        return Math.sin(direction) * magnitude;
    }

    Vector add(Vector o) {
        double x = getX() + o.getX();
        double y = getY() + o.getY();

        double[] coords = new double[2];
        coords[0] = x;
        coords[1] = y;

        return new Vector(coords);
    }

    void shortenInPlace(double by) {
        if (by == 0) {
            direction = 0;
        } else if (by < 0) {
            setDirection(direction + 180);
        }
        magnitude *= by;
    }

    private void print() {
        System.out.printf("m=%f d=%f\nx=%f y=%f\n", magnitude, direction, getX(), getY());
    }

}
