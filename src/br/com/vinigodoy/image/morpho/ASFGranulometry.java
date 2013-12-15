package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.color.ARGBChannels;
import br.com.vinigodoy.image.processing.Processing;
import br.com.vinigodoy.image.transform.Subtraction;
import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;

public class ASFGranulometry extends AbstractCombinedOperator {
    public ASFGranulometry(IntMatrix structuringElement, int iterations) {
        super(structuringElement, iterations);
    }

    @Override
    protected Processing[] createOperations(IntMatrix structuringElement,
                                            int iterations, BufferedImage source) {
        return new Processing[]
                {
                        new Opening(structuringElement, iterations),
                        new Reconstruction(source),
                        new Closing(structuringElement, iterations),
                        new Reconstruction(source),
                        new Subtraction(source, true, ARGBChannels.colors())
                };
    }
}
