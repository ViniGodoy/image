/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.color;

/**
 * Represents a gray scale channel.
 *
 * @author Vin�cius G. Mendon�a
 */
public enum GrayScaleChannel implements ColorChannel {
    INSTANCE;

    public int get(int argb) {
        double r = ARGBChannels.RED.get(argb) * 0.3;
        double g = ARGBChannels.GREEN.get(argb) * 0.59;
        double b = ARGBChannels.BLUE.get(argb) * 0.11f;
        int color = (int) (r + g + b + 0.05);
        return color > 255 ? 255 : color;
    }

    public int set(int value, int argb) {
        if (value < 0)
            value = 0;
        else if (value >= tones())
            value = tones() - 1;

        return (argb & 0xFF000000) | ((value << 16) | value << 8 | value);
    }

    @Override
    public int tones() {
        return 256;
    }
}
