package br.com.vinigodoy.image.color;

public interface ColorChannel {
    int get(int argb);

    int set(int value, int argb);

    int tones();
}
