/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
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
