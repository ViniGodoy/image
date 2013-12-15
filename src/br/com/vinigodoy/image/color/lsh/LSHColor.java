/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/

package br.com.vinigodoy.image.color.lsh;

import br.com.vinigodoy.image.util.Util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LSHColor implements Comparable<LSHColor> {
    private static Map<Color, LSHColor> colors = new HashMap<Color, LSHColor>();

    private static float TWOPI = (float) (2 * Math.PI);
    /**
     * Equivalente a 60 graus (2*PI/6 rad.).
     */
    private static float K = (float) (Math.PI / 3.0);

    private static float h0 = 0;

    public static LSHColor BLACK = new LSHColor(Color.BLACK);

    private float l;
    private float s;
    private float h;

    private Color color;

    public static LSHColor fromRGB(Color rgb) {
        if (!colors.containsKey(rgb))
            colors.put(rgb, new LSHColor(rgb));
        return colors.get(rgb);
    }

    public static LSHColor fromRGB(int rgb) {
        return fromRGB(new Color(rgb));
    }

    private LSHColor(Color rgb) {
        MinMaxMed c = new MinMaxMed(rgb);

        l = c.avg();
        s = 1.5f * ((l >= c.med) ? (c.max - l) : (l - c.min));
        h = K * (c.lambda + 0.5f - c.sign() * ((c.max + c.min - 2 * c.med) / 2 * s));
        this.color = rgb;
    }

    public Color toColor() {
        return color;
    }

    public int toRGB() {
        return toColor().getRGB();
    }

    public float getL() {
        return l;
    }

    public float getS() {
        return s;
    }

    public float getH() {
        return h;
    }

    private int intL() {
        return (int) (l * 255);
    }

    private int intS() {
        return (int) (s * 255);
    }

    public static float getH0() {
        return h0;
    }

    public void setH0(float h0) {
        LSHColor.h0 = h0;
    }

    @Override
    public String toString() {
        return String.format("l: %.2f s: %.2f h:%.2f degrees", l, s,
                Math.toDegrees(h));
    }

    private static class MinMaxMed {
        public int lambda;
        public float max;
        public float med;
        public float min;

        private void set(int lambda, int max, int med, int min) {
            this.lambda = lambda;
            this.max = max / 255.0f;
            this.med = med / 255.0f;
            this.min = min / 255.0f;
        }

        private MinMaxMed(Color rgb) {
            // r > g >= b
            if (rgb.getRed() > rgb.getGreen()
                    && rgb.getGreen() >= rgb.getBlue())
                set(0, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
                // g >= r > b
            else if (rgb.getGreen() >= rgb.getRed()
                    && rgb.getRed() > rgb.getBlue())
                set(1, rgb.getGreen(), rgb.getRed(), rgb.getBlue());
                // g > b >= r
            else if (rgb.getGreen() > rgb.getBlue()
                    && rgb.getBlue() >= rgb.getRed())
                set(2, rgb.getGreen(), rgb.getBlue(), rgb.getRed());
                // b >= g > r
            else if (rgb.getBlue() >= rgb.getGreen()
                    && rgb.getGreen() > rgb.getRed())
                set(3, rgb.getBlue(), rgb.getGreen(), rgb.getRed());
                // b > r >= g
            else if (rgb.getBlue() > rgb.getRed()
                    && rgb.getRed() >= rgb.getGreen())
                set(4, rgb.getBlue(), rgb.getRed(), rgb.getGreen());
            else
                // r >= b > g
                set(5, rgb.getRed(), rgb.getBlue(), rgb.getGreen());
        }

        public float avg() {
            return Util.avg(max, med, min);
        }

        public int sign() {
            // Como lambda � real, a conta abaixo � equivalente �
            // (-1)^lambda
            return (lambda & 1) == 0 ? 1 : -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&
                obj.getClass() == getClass() &&
                compareTo((LSHColor) obj) == 0;
    }

    @Override
    public int hashCode() {
        return intL() << 16 + intS() << 8 + (int) (h / TWOPI * 255);
    }

    /**
     * Sorting considers the lexicographic ordering
     */
    @Override
    public int compareTo(LSHColor o) {
        /*
         * A distancia em L e S leva em consideracao uma escala de 0 ate 255.
		 * Assim, garantimos que havera distribuicao levando em consideracao o
		 * segundo e terceiro componentes da tripla LSH (sem a escala, como L �
		 * float, existiriam "infinitos" possiveis valores de L, o que aumenta
		 * consideravelmente a chance da ordenacao parar no primeiro if).
		 */
        if (intL() < o.intL())
            return -1;
        if (intL() > o.intL())
            return 1;

        if (intS() < o.intS())
            return -1;
        if (intS() > o.intS())
            return 1;

		/*
         * A distancia em H e calculada com base na distancia relativa entre h e
		 * a variavel estatica h0.
		 */
        if (hueDist(h) < hueDist(o.h))
            return -1;
        if (hueDist(h) > hueDist(o.h))
            return 1;
        return 0;
    }

    private float hueDist(float h) {
        if (h - h0 < Math.PI)
            return h - h0;
        return TWOPI - h - h0;
    }

    public boolean lesserThan(LSHColor color) {
        return compareTo(color) < 0;
    }

    public boolean biggerThan(LSHColor color) {
        return compareTo(color) > 0;
    }
}
