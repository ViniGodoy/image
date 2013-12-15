/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.color.lsh.LSHColor;
import br.com.vinigodoy.image.color.lsh.LSHImage;
import br.com.vinigodoy.image.processing.AbstractProcessing;
import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;
import java.util.SortedSet;

public abstract class AbstractMorphoOperator extends AbstractProcessing {

    private String name;
    private IntMatrix structuringElement;
    private int iterations;

    protected AbstractMorphoOperator(String name, IntMatrix structuringElement,
                                     int iterations) {
        if (structuringElement == null)
            throw new IllegalArgumentException(
                    "The structuring cannot be null!");
        if (iterations <= 0)
            throw new IllegalArgumentException(
                    "The minimum number of iterations is 1!");

        this.structuringElement = structuringElement;
        this.iterations = iterations;
        this.name = name;
    }

    @Override
    public BufferedImage process(BufferedImage source) {
        if (source == null)
            throw new IllegalArgumentException("Forne�a uma imagem!");

        int totalPixels = source.getWidth() * source.getHeight();
        fireProcessingStarted(totalPixels * iterations, name);
        LSHImage img = new LSHImage(source);
        LSHImage work = new LSHImage(img.getWidth(), img.getHeight());

        int currentPixel = 0;

        for (int i = 0; i < iterations; i++) {
            for (int y = 0; y < img.getHeight(); y++)
                for (int x = 0; x < img.getWidth(); x++) {
                    LSHColor result = processOperator(img.extractPixels(x, y, structuringElement));
                    work.setPixel(x, y, result);
                    fireProcessingProgress(currentPixel, totalPixels);
                    currentPixel++;
                }
            img = work;
            work = new LSHImage(img.getWidth(), img.getHeight());
        }

        BufferedImage result = img.toImageLike(source);
        fireProcessingEnded(totalPixels, result);
        return result;
    }

    protected abstract LSHColor processOperator(SortedSet<LSHColor> colors);
}
