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
import br.com.vinigodoy.image.util.DoubleMatrix;
import br.com.vinigodoy.image.util.IntMatrix;

public class LinearFilter extends NeighborhoodFilter {
    private DoubleMatrix kernel;

    public LinearFilter(DoubleMatrix kernel, ColorChannel... channels) {
        super(channels);
        if (kernel == null)
            throw new IllegalArgumentException(
                    "The kernel matrix cannot be null!");

        this.kernel = kernel;
    }

    public static LinearFilter createF(int i, ColorChannel... channels) {
        if (i < 1 || i > 4)
            throw new IllegalArgumentException("N�o h� filtros para o valor de i=" + i);

        if (i == 1)
            return new LinearFilter(DoubleMatrix.createAverages(3, 3,
                    0, 1, 0,
                    1, 1, 1,
                    0, 1, 0), channels);
        if (i == 2)
            return new LinearFilter(DoubleMatrix.createAverages(3, 3,
                    1, 1, 1,
                    1, 1, 1,
                    1, 1, 1), channels);
        if (i == 3)
            return new LinearFilter(DoubleMatrix.createAverages(3, 3,
                    1, 1, 1,
                    1, 2, 1,
                    1, 1, 1));

        return new LinearFilter(DoubleMatrix.createAverages(3, 3,
                1, 2, 1,
                2, 4, 2,
                1, 2, 1), channels);

    }

    public static LinearFilter createLaplace(ColorChannel... channels) {
        return new LinearFilter(new DoubleMatrix(3, 3,
                0, 1, 0,
                1, -4, 1,
                0, 1, 0), channels);
    }

    @Override
    public int process(ColorChannel channel, IntMatrix matrix) {
        return matrix.convolute(kernel, channel);
    }

    @Override
    public int getWidth() {
        return kernel.getWidth();
    }

    @Override
    public int getHeight() {
        return kernel.getHeight();
    }
}