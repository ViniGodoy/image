/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.filters;

import br.com.vinigodoy.image.color.ARGBChannels;
import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.processing.AbstractProcessing;
import br.com.vinigodoy.image.util.Util;

import java.awt.image.BufferedImage;

public abstract class PixelByPixelFilter extends AbstractProcessing {
    private ColorChannel[] channels;

    public PixelByPixelFilter(ColorChannel... channels) {
        if (channels == null)
            throw new IllegalArgumentException("Os canais n�o podem ser nulos!");

        if (channels.length == 0)
            channels = ARGBChannels.colors();

        this.channels = channels;
    }

    @Override
    public BufferedImage process(BufferedImage img) {
        if (img == null)
            throw new IllegalArgumentException("Forne�a uma imagem!");

        int totalPixels = img.getWidth() * img.getHeight();
        fireProcessingStarted(totalPixels, "filtro");
        BufferedImage work = Util.newOptimizedImageLike(img);
        int currentPixel = 0;
        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                for (ColorChannel channel : channels)
                    pixel = channel.set(processChannel(img, channel, x, y, channel.get(pixel)), pixel);
                work.setRGB(x, y, pixel);
                fireProcessingProgress(currentPixel, totalPixels);
                currentPixel++;
            }

        fireProcessingEnded(totalPixels, work);
        return work;
    }

    protected abstract int processChannel(BufferedImage img, ColorChannel channel, int x, int y, int tone);
}
