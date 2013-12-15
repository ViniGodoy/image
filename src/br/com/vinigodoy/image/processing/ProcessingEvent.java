package br.com.vinigodoy.image.processing;

import java.awt.image.BufferedImage;
import java.util.EventObject;


@SuppressWarnings("serial")
public class ProcessingEvent extends EventObject {
    private String name;
    private volatile int totalPixels = 0;
    private volatile int currentPixel = 0;
    private volatile BufferedImage result;

    public ProcessingEvent(Processing source, String name, int totalPixels, int currentPixel, BufferedImage result) {
        super(source);
        this.name = name;
        this.result = result;
        this.totalPixels = totalPixels;
        this.currentPixel = currentPixel;
    }

    @Override
    public Processing getSource() {
        return (Processing) super.getSource();
    }

    public int getTotalPixels() {
        return totalPixels;
    }

    public int getCurrentPixel() {
        return currentPixel;
    }

    public BufferedImage getResult() {
        return result;
    }

    public String getName() {
        return name;
    }
}
