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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Histogram {
    private ColorChannel channel;
    private int totalPixels;
    private Map<Integer, Integer> toneCount = new TreeMap<Integer, Integer>();
    private Map<Integer, Double> distributions = new HashMap<Integer, Double>();

    public Histogram(ColorChannel channel) {
        if (channel == null)
            throw new IllegalArgumentException("Voc� deve fornecer um canal!");

        this.channel = channel;
    }

    public Histogram calculate(BufferedImage img) {
        distributions.clear();
        distributions.put(0, 1.0);

        for (int i = 0; i < channel.tones(); i++)
            toneCount.put(i, 0);

        totalPixels = img.getWidth() * img.getHeight();
        for (int y = 0; y < img.getHeight(); y++)
            for (int x = 0; x < img.getWidth(); x++) {
                int tone = channel.get(img.getRGB(x, y));
                toneCount.put(tone, toneCount.get(tone) + 1);
            }
        return this;
    }

    public int getCount(int tone) {
        if (!toneCount.containsKey(tone))
            return 0;
        return toneCount.get(tone);
    }

    public double getPercent(int tone) {
        return (double) getCount(tone) / totalPixels;
    }

    public double getInverseCumulativeDistribution(int tone) {
        if (!distributions.containsKey(tone)) {
            //Avoid double division to get more precision
            double sum = 0;
            for (int i = tone; i < channel.tones(); i++) {
                sum += getCount(i);
            }
            distributions.put(tone, sum / getTotalPixels());
        }
        return distributions.get(tone);
    }

    public int getMaximum() {
        int maximum = 0;
        for (int value : toneCount.values())
            if (value > maximum)
                maximum = value;
        return maximum;
    }

    public int getTotalPixels() {
        return totalPixels;
    }

    public Map<Integer, Integer> getToneCount() {
        return Collections.unmodifiableMap(toneCount);
    }
}
