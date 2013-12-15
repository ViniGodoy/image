package br.com.vinigodoy.image.filters;

import java.awt.image.BufferedImage;

public interface Filter {
    BufferedImage process(BufferedImage source);

    void addListener(FilterListener listener);

    void removeListner(FilterListener listener);
}
