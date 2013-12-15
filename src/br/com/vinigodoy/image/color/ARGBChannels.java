/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.color;

public enum ARGBChannels implements ColorChannel {
    ALPHA(0xFF000000, 24), RED(0xFF0000, 16), GREEN(0xFF00, 8), BLUE(0xFF, 0);

    private int mask;
    private int shift;

    private ARGBChannels(int mask, int shift) {
        this.mask = mask;
        this.shift = shift;
    }

    @Override
    public int get(int argb) {
        return ((argb >> shift) & 0xFF);
    }

    @Override
    public int set(int value, int argb) {
        if (value < 0)
            value = 0;
        else if (value >= tones())
            value = tones() - 1;

        return ((argb & ~mask) | (value << shift));
    }

    @Override
    public int tones() {
        return 256;
    }

    public static ColorChannel[] colors() {
        return new ColorChannel[]{ARGBChannels.RED, ARGBChannels.GREEN, ARGBChannels.BLUE};
    }
}
