/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Util {
    public static BufferedImage newOptimizedImage(
            GraphicsConfiguration destination, int width, int height,
            boolean alpha, int transparency) {
        return alpha ? destination.createCompatibleImage(width, height,
                transparency) : destination
                .createCompatibleImage(width, height);
    }

    public static BufferedImage newOptimizedImage(int width, int height,
                                                  boolean alpha, int transparency) {
        return newOptimizedImage(getDefaultConfig(), width, height, alpha,
                transparency);
    }

    public static BufferedImage newOptimizedImageLike(
            GraphicsConfiguration destination, BufferedImage img) {
        return newOptimizedImage(destination, img.getWidth(), img.getHeight(),
                img.getColorModel().hasAlpha(), img.getTransparency());
    }

    public static BufferedImage newOptimizedImageLike(BufferedImage img) {
        return newOptimizedImageLike(getDefaultConfig(), img);
    }

    private static GraphicsConfiguration getDefaultConfig() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
    }

    public static BufferedImage newTransformedImage(BufferedImage source,
                                                    int w, int h, boolean flipVertical, boolean flipHorizontal) {
        BufferedImage out = Util.newOptimizedImage(w, h, source.getColorModel()
                .hasAlpha(), source.getTransparency());

        int x1 = flipVertical ? w : 0;
        int x2 = flipVertical ? 0 : w;

        int y1 = flipHorizontal ? h : 0;
        int y2 = flipHorizontal ? 0 : h;

        Graphics2D g2d = out.createGraphics();
        g2d.drawImage(source, x1, y1, x2, y2, 0, 0, source.getWidth(),
                source.getHeight(), null);
        g2d.dispose();
        return out;
    }

    public static float sum(float... numbers) {
        float sum = 0;
        for (float number : numbers)
            sum += number;
        return sum;
    }

    public static float avg(float... numbers) {
        return sum(numbers) / numbers.length;
    }
}
