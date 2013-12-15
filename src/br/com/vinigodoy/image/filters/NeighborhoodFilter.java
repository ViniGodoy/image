package br.com.vinigodoy.image.filters;

import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;

public abstract class NeighborhoodFilter extends PixelByPixelFilter {
    private IntMatrix matrix = null;

    public NeighborhoodFilter(ColorChannel... channels) {
        super(channels);
    }

    private IntMatrix getMatrix() {
        if (matrix == null)
            matrix = new IntMatrix(getWidth(), getHeight());
        return matrix;
    }

    @Override
    protected int processChannel(BufferedImage img, ColorChannel channel, int x,
                                 int y, int tone) {
        getMatrix().set(img, x, y);
        return process(channel, getMatrix());
    }

    public abstract int process(ColorChannel channel, IntMatrix neighboorhood);

    public abstract int getWidth();

    public abstract int getHeight();
}
