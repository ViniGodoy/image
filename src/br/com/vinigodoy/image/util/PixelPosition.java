package br.com.vinigodoy.image.util;


public class PixelPosition {
    private int x;
    private int y;

    public PixelPosition(int x, int y) {
        super();

        assert (x > 0 && y > 0);

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
