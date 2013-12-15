package br.com.vinigodoy.image.transform;

import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.filters.PixelByPixelFilter;

import java.awt.image.BufferedImage;

public class Negation extends PixelByPixelFilter {
    public Negation(ColorChannel... channels) {
        super(channels);
    }

    @Override
    protected int processChannel(BufferedImage img, ColorChannel channel, int x,
                                 int y, int tone) {
        return channel.tones() - tone - 1;
    }
}
