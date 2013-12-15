/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.processing.AbstractProcessing;
import br.com.vinigodoy.image.processing.Processing;
import br.com.vinigodoy.image.processing.ProcessingEvent;
import br.com.vinigodoy.image.processing.ProcessingListener;
import br.com.vinigodoy.image.util.IntMatrix;

import java.awt.image.BufferedImage;

public abstract class AbstractCombinedOperator extends AbstractProcessing {
    private IntMatrix structuringElement;
    private int iterations;

    public AbstractCombinedOperator(IntMatrix structuringElement, int iterations) {
        if (structuringElement == null)
            throw new IllegalArgumentException(
                    "The structuring cannot be null!");
        if (iterations <= 0)
            throw new IllegalArgumentException(
                    "The minimum number of iterations is 1!");

        this.structuringElement = structuringElement;
        this.iterations = iterations;
    }

    @Override
    public BufferedImage process(BufferedImage source) {
        EventDispatcher dispatcher = new EventDispatcher();

        BufferedImage src = source;
        for (Processing filter : createOperations(structuringElement, iterations, source)) {
            filter.addListener(dispatcher);
            src = filter.process(src);
            filter.removeListener(dispatcher);
        }

        ProcessingEvent last = dispatcher.getLastEnded();
        fireProcessingEnded(last.getTotalPixels(), last.getResult());
        return last.getResult();
    }

    protected abstract Processing[] createOperations(
            IntMatrix structuringElement, int iterations, BufferedImage source);

    private class EventDispatcher implements ProcessingListener {
        private ProcessingEvent lastEnded;

        @Override
        public void operationStarted(ProcessingEvent evt) {
            fireProcessingStarted(evt.getTotalPixels(), evt.getName());
        }

        @Override
        public void operationProgress(ProcessingEvent evt) {
            fireProcessingProgress(evt.getCurrentPixel(), evt.getTotalPixels());

        }

        @Override
        public void operationEnded(ProcessingEvent evt) {
            lastEnded = evt;
        }

        public ProcessingEvent getLastEnded() {
            return lastEnded;
        }
    }


}
