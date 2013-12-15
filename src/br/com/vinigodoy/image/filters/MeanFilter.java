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
