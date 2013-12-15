package br.com.vinigodoy.image.filters;

import br.com.vinigodoy.image.color.ColorChannel;

import java.awt.image.BufferedImage;

public class NegativeFilter extends PixelByPixelFilter {
    public NegativeFilter(ColorChannel... channels) {
        super(channels);
    }

    @Override
    protected int processChannel(BufferedImage img, ColorChannel channel, int x,
                                 int y, int tone) {
        return channel.tones() - tone - 1;
    }
}
