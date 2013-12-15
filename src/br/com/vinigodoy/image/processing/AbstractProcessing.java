package br.com.vinigodoy.image.processing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractProcessing implements Processing {
    private List<ProcessingListener> listeners = new ArrayList<ProcessingListener>();

    @Override
    public void addListener(ProcessingListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ProcessingListener listener) {
        listeners.remove(listener);
    }

    protected void fireProcessingStarted(int totalPixels, String name) {
        ProcessingEvent evt = null;
        for (ProcessingListener l : listeners) {
            if (evt == null)
                evt = new ProcessingEvent(this, name, totalPixels, 0, null);
            l.operationStarted(evt);
        }
    }

    protected void fireProcessingProgress(int currentPixel, int totalPixels) {
        ProcessingEvent evt = null;
        for (ProcessingListener l : listeners) {
            if (evt == null)
                evt = new ProcessingEvent(this, "", totalPixels, currentPixel, null);
            l.operationProgress(evt);
        }
    }

    protected void fireProcessingEnded(int totalPixels, BufferedImage result) {
        ProcessingEvent evt = null;
        for (ProcessingListener l : listeners) {
            if (evt == null)
                evt = new ProcessingEvent(this, "", totalPixels, totalPixels, result);
            l.operationEnded(evt);
        }
    }
}
