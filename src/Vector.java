class Vector {
    private double x, y;


    // Get the length of each non-hypotenuse side of a 45-degree right triangle with hypotenuse of length magnitude
    // Used for normalizing maximum acceleration to a constant value
    static double normalize(double magnitude) {
        return magnitude * Math.cos(Math.PI / 4);
    }

    Vector() {
        x = y = 0;
    }


    double getX() {
        return x;
    }

    double getY() {
        return y;
    }


    void addX(double x) {
        this.x += x;
    }

    void addY(double y) {
        this.y += y;
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
