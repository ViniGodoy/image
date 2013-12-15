/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;

public class Closing extends AbstractCombinedOperator {

    public Closing(IntMatrix structuringElement, int iterations) {
        super(structuringElement, iterations);
    }

    @Override
    protected AbstractMorphoOperator[] createOperations(
            IntMatrix structuringElement, int iterations, BufferedImage source) {

        return new AbstractMorphoOperator[]{
                new Dilation(structuringElement, iterations),
                new Erosion(structuringElement, iterations)
        };
    }

}