package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.color.lsh.LSHColor;
import br.com.vinigodoy.image.util.IntMatrix;

import java.util.SortedSet;

public class Dilation extends AbstractMorphoOperator {
    public Dilation(IntMatrix structuringElement, int iterations) {
        super("dilation", structuringElement, iterations);
    }

    @Override
    protected LSHColor processOperator(SortedSet<LSHColor> colors) {
        return colors.last();
    }
}
