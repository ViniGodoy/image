package br.com.vinigodoy.image.filters;

import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.util.DoubleMatrix;
import br.com.vinigodoy.image.util.IntMatrix;

public class PrewittFilter extends NeighborhoodFilter {
    public enum Operator {
        G(null) {
            @Override
            public int process(ColorChannel channel, IntMatrix matrix) {
                int gx = Gx.process(channel, matrix);
                int gy = Gy.process(channel, matrix);
                return (int) Math.sqrt(gx * gx + gy * gy);
            }
        },
        Gx(new DoubleMatrix(3, 3,
                -1, 0, 1,
                -1, 0, 1,
                -1, 0, 1)),
        Gy(new DoubleMatrix(3, 3,
                -1, -1, -1,
                0, 0, 0,
                1, 1, 1));

        private DoubleMatrix kernel;

        private Operator(DoubleMatrix kernel) {
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

    public PrewittFilter(Operator operator, ColorChannel... channels) {
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
