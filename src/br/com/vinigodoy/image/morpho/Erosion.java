package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.color.lsh.LSHColor;
import br.com.vinigodoy.image.util.IntMatrix;

import java.util.SortedSet;

public class Erosion extends AbstractMorphoOperator {
    public Erosion(IntMatrix structuringElement, int iterations) {
        super("erosion", structuringElement, iterations);
    }

    @Override
    protected LSHColor processOperator(SortedSet<LSHColor> colors) {
        return colors.first();
    }
}
