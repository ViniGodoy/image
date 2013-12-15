package br.com.vinigodoy.image.filters;

import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.util.IntMatrix;

public class MeanFilter extends NeighborhoodFilter {
    private int w;
    private int h;

    public MeanFilter(int w, int h, ColorChannel... channels) {
        super(channels);
        if (w < 2 || h < 2)
            throw new IllegalArgumentException("Invalid filter dimensions!");
        this.h = h;
        this.w = w;
    }

    @Override
    public int process(ColorChannel channel, IntMatrix neighboorhood) {
        return neighboorhood.getMean(channel);
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }
}
