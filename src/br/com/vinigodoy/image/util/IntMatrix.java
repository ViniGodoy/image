/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.util;

import br.com.vinigodoy.image.color.ColorChannel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class IntMatrix {
    private int[] values;
    private int w;

    public IntMatrix(int w, int h) {
        if (w <= 0 || h <= 0)
            throw new IllegalArgumentException(
                    "Dimensions must be bigger than 0!");

        this.w = w;
        values = new int[w * h];
    }

    public IntMatrix(int w, int h, int... values) {
        if (w <= 0 || h <= 0)
            throw new IllegalArgumentException(
                    "Dimensions must be bigger than 0!");

        this.w = w;
        this.values = Arrays.copyOf(values, w * h);
    }

    private int indexOf(int x, int y) {
        return x + y * w;
    }

    public int get(int x, int y) {
        return values[indexOf(x, y)];
    }

    public void set(int x, int y, int value) {
        values[indexOf(x, y)] = value;
    }

    public void set(int x, int y, Color color) {
        set(x, y, color.getRGB());
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return values.length / w;
    }

    public int getCount() {
        return values.length;
    }

    public void set(BufferedImage img, int imgX, int imgY) {
        int x1 = imgX - getWidth() / 2;
        int y1 = imgY - getHeight() / 2;
        int x2 = imgX + getWidth() / 2;
        int y2 = imgY + getHeight() / 2;

        int l = 0;

        for (int y = y1; y <= y2; y++) {
            int c = 0;
            for (int x = x1; x <= x2; x++) {
                int px = x < 0 ? 0 : (x >= img.getWidth() ? img.getWidth() - 1
                        : x);
                int py = y < 0 ? 0
                        : (y >= img.getHeight() ? img.getHeight() - 1 : y);

                set(c, l, img.getRGB(px, py));
                c++;
            }
            l++;
        }
    }

    public int getMean(ColorChannel channel) {
        int[] pixels = new int[values.length];
        for (int i = 0; i < values.length; i++)
            pixels[i] = channel.get(values[i]);
        Arrays.sort(pixels);
        return pixels[values.length / 2];
    }

    public int convolute(DoubleMatrix kernel, ColorChannel channel) {
        double color = 0;

        for (int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y++)
                color += channel.get(get(x, y)) * kernel.get(x, y);

        return (int) color;
    }

    public int centerX() {
        return (int) (getWidth() / 2.0f);

    }

    public int centerY() {
        return (int) (getHeight() / 2.0f);
    }

    public IntMatrix createRaster() {
        IntMatrix raster = new IntMatrix(w, getHeight());

        for (int i = 0; i < raster.getWidth(); i++)
            for (int j = 0; j < raster.getHeight(); j++)
                if (indexOf(i, j) < (getCount() / 2))
                    raster.set(i, j, get(i, j));
        return raster;
    }

    public IntMatrix createAntiRaster() {
        IntMatrix antiraster = new IntMatrix(w, getHeight());

        for (int i = 0; i < antiraster.getWidth(); i++)
            for (int j = 0; j < antiraster.getHeight(); j++)
                if (indexOf(i, j) > (getCount() / 2))
                    antiraster.set(i, j, get(i, j));
        return antiraster;
    }
}
