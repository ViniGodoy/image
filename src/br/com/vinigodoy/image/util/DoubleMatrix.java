/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.util;

import java.util.Arrays;

public class DoubleMatrix {
    private double[] values;
    private int w;

    public DoubleMatrix(int w, int h) {
        if (w == 0 || h == 0)
            throw new IllegalArgumentException(
                    "Dimensions must be bigger than 0!");

        this.w = w;
        values = new double[w * h];
    }

    public DoubleMatrix(int w, int h, double... values) {
        if (w == 0 || h == 0)
            throw new IllegalArgumentException(
                    "Dimensions must be bigger than 0!");
        if (values.length < w * h)
            throw new IllegalArgumentException("Values array too small!");

        this.w = w;
        this.values = Arrays.copyOf(values, w * h);
    }

    public static DoubleMatrix createAverages(int w, int h, double... values) {
        if (w == 0 || h == 0)
            throw new IllegalArgumentException(
                    "Dimensions must be bigger than 0!");

        double sum = 0;
        for (double value : values)
            sum += value;

        double avgs[] = new double[values.length];
        for (int i = 0; i < avgs.length; i++)
            avgs[i] = values[i] / sum;

        if (sum == 0)
            throw new IllegalArgumentException(
                    "The sum of the values cannot be zero!");

        return new DoubleMatrix(w, h, avgs);
    }

    private int indexOf(int x, int y) {
        return x + y * w;
    }

    public double get(int x, int y) {
        return values[indexOf(x, y)];
    }

    public void set(int x, int y, double value) {
        values[indexOf(x, y)] = value;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return values.length / w;
    }

    public int getCount() {
        return values.length;
    }

    public DoubleMatrix fill(double value) {
        Arrays.fill(values, value);
        return this;
    }
}
