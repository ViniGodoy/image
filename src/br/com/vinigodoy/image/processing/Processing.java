package br.com.vinigodoy.image.processing;

import java.awt.image.BufferedImage;


public interface Processing {
    BufferedImage process(BufferedImage source);

    void addListener(ProcessingListener listener);

    void removeListener(ProcessingListener listener);
}
