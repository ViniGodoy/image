package br.com.vinigodoy.image.transform;

public enum Rotation {
    R90(Math.PI / 2, true),
    R180(Math.PI, false),
    R270(3 * Math.PI / 2, true);

    private double angle;
    private boolean inverseWidthAndHeight;

    private Rotation(double angle, boolean inverseWidthAndHeight) {
        this.angle = angle;
        this.inverseWidthAndHeight = inverseWidthAndHeight;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isInverseWidthAndHeight() {
        return inverseWidthAndHeight;
    }

};
