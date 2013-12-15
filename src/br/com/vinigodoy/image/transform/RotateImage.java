/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.transform;

import br.com.vinigodoy.image.processing.AbstractProcessing;
import br.com.vinigodoy.image.util.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RotateImage extends AbstractProcessing {
    private Rotation rotation;

    public RotateImage(Rotation rotation) {
        if (rotation == null)
            throw new IllegalArgumentException("The rotation cannot be null!");
        this.rotation = rotation;
    }

    @Override
    public BufferedImage process(BufferedImage source) {
        fireProcessingStarted(-1, "rota��o");
        double angle = rotation.getAngle();

        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = source.getWidth(), h = source.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage result = Util.newOptimizedImage(neww, newh, source.getColorModel().hasAlpha(), source.getTransparency());
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(source, null);
        g.dispose();
        fireProcessingEnded(-1, result);
        return result;
    }
}
