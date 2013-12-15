/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.color.lsh;

import br.com.vinigodoy.image.util.IntMatrix;
import br.com.vinigodoy.image.util.PixelPosition;
import br.com.vinigodoy.image.util.Util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class LSHImage {
    private LSHColor[][] pixels;

    public LSHImage(int w, int h) {
        pixels = new LSHColor[w][h];
        for (int x = 0; w < pixels.length; x++)
            for (int y = 0; y < pixels[0].length; y++)
                pixels[x][y] = LSHColor.BLACK;
    }

    public LSHImage(BufferedImage img) {
        pixels = new LSHColor[img.getWidth()][img.getHeight()];

        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++)
                pixels[x][y] = LSHColor.fromRGB(img.getRGB(x, y));
    }

    public LSHColor getPixel(int x, int y) {
        return pixels[x][y];
    }

    public LSHColor setPixel(int x, int y, LSHColor color) {
        return pixels[x][y] = color;
    }

    public LSHImage copyBlank() {
        return new LSHImage(getWidth(), getHeight());
    }

    public BufferedImage toImageLike(BufferedImage source) {
        BufferedImage img = Util.newOptimizedImageLike(source);
        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++)
                img.setRGB(x, y, pixels[x][y].toRGB());
        return img;
    }

    public int getWidth() {
        return pixels.length;
    }

    public int getHeight() {
        return pixels[0].length;
    }

    public int getCount() {
        return getWidth() * getHeight();
    }

    public SortedSet<LSHColor> extractPixels(int px, int py, IntMatrix structuringElement) {
        SortedSet<LSHColor> colors = new TreeSet<LSHColor>();
        for (PixelPosition xy : extractNeighbors(px, py, structuringElement))
            colors.add(getPixel(xy));

        return colors;
    }

    public List<PixelPosition> extractNeighbors(int px, int py, IntMatrix structuringElement) {
        List<PixelPosition> positions = new ArrayList<PixelPosition>();
        for (int sy = 0; sy < structuringElement.getHeight(); sy++) {
            int y = py + sy - structuringElement.centerY();
            if (y < 0 || y >= getHeight())
                continue;
            for (int sx = 0; sx < structuringElement.getWidth(); sx++) {
                int x = px + sx - structuringElement.centerX();
                if (x < 0 || x >= getWidth())
                    continue;
                if (structuringElement.get(sx, sy) == 1)
                    positions.add(new PixelPosition(x, y));
            }
        }

        return positions;
    }


    public LSHColor getPixel(PixelPosition pos) {
        return getPixel(pos.getX(), pos.getY());
    }

    public void setPixel(PixelPosition pos, LSHColor color) {
        setPixel(pos.getX(), pos.getY(), color);
    }

}
