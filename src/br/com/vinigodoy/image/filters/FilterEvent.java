package br.com.vinigodoy.image.filters;

import java.util.EventObject;

@SuppressWarnings("serial")
public class FilterEvent extends EventObject {
    private volatile int totalPixels = 0;
    private volatile int currentPixel = 0;

    public FilterEvent(Filter source, int totalPixels, int currentPixel) {
        super(source);
        this.totalPixels = totalPixels;
        this.currentPixel = currentPixel;
    }

    @Override
    public Filter getSource() {
        return (Filter) super.getSource();
    }

    public int getTotalPixels() {
        return totalPixels;
    }

    public int getCurrentPixel() {
        return currentPixel;
    }
}
