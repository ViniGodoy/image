package br.com.vinigodoy.image.transform;

import br.com.vinigodoy.image.color.ColorChannel;
import br.com.vinigodoy.image.filters.PixelByPixelFilter;

import java.awt.image.BufferedImage;

public class Subtraction extends PixelByPixelFilter {

    private BufferedImage img2;
    private boolean reverse;

    public Subtraction(BufferedImage img2, ColorChannel... channels) {
        super(channels);
        this.reverse = false;
        this.img2 = img2;
    }

    public Subtraction(BufferedImage img2, boolean reverse,
                       ColorChannel... channels) {
        super(channels);
        this.img2 = img2;
        this.reverse = reverse;
    }

    @Override
    protected int processChannel(BufferedImage img1, ColorChannel channel,
                                 int x, int y, int tone) {
        if (x >= img2.getWidth() || y >= img2.getHeight())
            return tone;
        return reverse ? Math.max(0, channel.get(img2.getRGB(x, y)) - tone)
                : Math.max(0, tone - channel.get(img2.getRGB(x, y)));
    }
}
