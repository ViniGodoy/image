/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.filters;

import br.com.vinigodoy.image.color.ColorChannel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class HistogramRedistributionFilter extends PixelByPixelFilter {
    private Map<ColorChannel, Histogram> histograms = new HashMap<ColorChannel, Histogram>();

    public HistogramRedistributionFilter(ColorChannel... channels) {
        super(channels);
    }

    @Override
    protected int processChannel(BufferedImage img, ColorChannel channel, int x,
                                 int y, int tone) {
        Histogram histogram = getHistogram(channel, img);
        double Xl = channel.tones() - 1; //X(l-1)
        return (int) (Xl - Xl * histogram.getInverseCumulativeDistribution(tone));
    }

    private Histogram getHistogram(ColorChannel channel, BufferedImage img) {
        Histogram histogram = histograms.get(channel);
        if (histogram != null)
            return histogram;

        histogram = new Histogram(channel).calculate(img);
        histograms.put(channel, histogram);
        return histogram;
    }
}
