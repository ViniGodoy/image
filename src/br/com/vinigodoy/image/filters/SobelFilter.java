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

public class SobelFilter extends NeighborhoodFilter {
    public static interface Operator {
        public int process(ColorChannel channel, IntMatrix matrix);

        public DoubleMatrix getKernel();
    }

    public enum Operator1 implements Operator {
        G(null) {
            @Override
            public int process(ColorChannel channel, IntMatrix matrix) {
                int gx = Gx.process(channel, matrix);
                int gy = Gy.process(channel, matrix);
                return (int) Math.sqrt(gx * gx + gy * gy);
            }
        },
        Gx(new DoubleMatrix(3, 3,
                1, 0, -1,
                2, 0, -2,
                1, 0, -1)),
        Gy(new DoubleMatrix(3, 3,
                1, 2, 1,
                0, 0, 0,
                -1, -2, -1));

        private DoubleMatrix kernel;

        private Operator1(DoubleMatrix kernel) {
            this.kernel = kernel;
        }

        public int process(ColorChannel channel, IntMatrix matrix) {
            return matrix.convolute(kernel, channel);
        }

        public DoubleMatrix getKernel() {
            return kernel;
        }
    }

    public enum Operator2 implements Operator {
        G(null) {
            @Override
            public int process(ColorChannel channel, IntMatrix matrix) {
                int gx = Gx.process(channel, matrix);
                int gy = Gy.process(channel, matrix);
                return (int) Math.sqrt(gx * gx + gy * gy);
            }
        },
        Gx(new DoubleMatrix(3, 3,
                2, 1, 0,
                1, 0, -1,
                0, -1, -2)),
        Gy(new DoubleMatrix(3, 3,
                0, 1, 2,
                1, 0, -1,
                -2, -1, 0));

        private DoubleMatrix kernel;

        private Operator2(DoubleMatrix kernel) {
            this.kernel = kernel;
        }

        public int process(ColorChannel channel, IntMatrix matrix) {
            return matrix.convolute(kernel, channel);
        }

        public DoubleMatrix getKernel() {
            return kernel;
        }
    }

    private Operator operator;

    public SobelFilter(Operator operator, ColorChannel... channels) {
        super(channels);
        this.operator = operator;
    }

    @Override
    public int process(ColorChannel channel, IntMatrix neighboorhood) {
        return operator.process(channel, neighboorhood);
    }

    @Override
    public int getWidth() {
        return 3;
    }

    @Override
    public int getHeight() {
        return 3;
    }
}
