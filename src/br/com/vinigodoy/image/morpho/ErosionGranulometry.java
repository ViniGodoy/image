package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.color.ARGBChannels;
import br.com.vinigodoy.image.processing.Processing;
import br.com.vinigodoy.image.transform.Subtraction;
import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;

public class ErosionGranulometry extends AbstractCombinedOperator {
    public ErosionGranulometry(IntMatrix structuringElement, int iterations) {
        super(structuringElement, iterations);
    }

    @Override
    protected Processing[] createOperations(IntMatrix structuringElement,
                                            int iterations, BufferedImage source) {
        return new Processing[]
                {
                        new Erosion(structuringElement, iterations),
                        new Reconstruction(source),
                        new Subtraction(source, true, ARGBChannels.colors())
                };
    }
}
