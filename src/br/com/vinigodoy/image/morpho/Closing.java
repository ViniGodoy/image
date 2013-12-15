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