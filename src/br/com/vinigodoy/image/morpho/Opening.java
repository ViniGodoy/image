package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;

public class Opening extends AbstractCombinedOperator {

    public Opening(IntMatrix structuringElement, int iterations) {
        super(structuringElement, iterations);
    }

    @Override
    protected AbstractMorphoOperator[] createOperations(
            IntMatrix structuringElement, int iterations, BufferedImage source) {

        return new AbstractMorphoOperator[]{
                new Erosion(structuringElement, iterations),
                new Dilation(structuringElement, iterations)};
    }

}
