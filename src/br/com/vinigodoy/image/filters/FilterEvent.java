/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
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
