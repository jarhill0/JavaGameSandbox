class Vector {
    private double x, y; // direction is in radians
    static double EIGHTH = Math.PI / 4;
    static double QUARTER = Math.PI / 2;

    Vector() {
        x = y = 0;
    }

    Vector(double magnitude, double direction) {
        x = magnitude * Math.cos(direction);
        y = magnitude * Math.sin(direction);
    }


    double getX() {
        return x;
    }

    double getY() {
        return y;
    }


    void add(double magnitude, double direction) {
        x += magnitude * Math.cos(direction);
        y += magnitude * Math.sin(direction);
    }

    void shortenInPlace(double by) {
        shortenX(by);
        shortenY(by);
    }

    void shortenX(double by) {
        x *= by;
    }

    void shortenY(double by) {
        y *= by;
    }
}
