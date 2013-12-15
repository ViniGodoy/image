/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.transform;

import br.com.vinigodoy.image.processing.AbstractProcessing;
import br.com.vinigodoy.image.util.Util;

import java.awt.image.BufferedImage;

public class TransformImage extends AbstractProcessing {
    private boolean flipVertical = false;
    private boolean flipHorizontal = false;

    private int w = 0;
    private int h = 0;

    public TransformImage(boolean flipVertical, boolean flipHorizontal, int w, int h) {
        super();
        this.flipVertical = flipVertical;
        this.flipHorizontal = flipHorizontal;
        this.w = w;
        this.h = h;
    }

    public TransformImage(boolean flipVertical, boolean flipHorizontal) {
        this(flipVertical, flipHorizontal, 0, 0);
    }

    public TransformImage(int w, int h) {
        this(false, false, w, h);
    }

    public TransformImage(BufferedImage img, double scale) {
        w = img == null ? 0 : (int) (img.getWidth() * scale);
        h = img == null ? 0 : (int) (img.getHeight() * scale);
    }

    @Override
    public BufferedImage process(BufferedImage source) {
        fireProcessingStarted(-1, "transforma��o");

        int w = this.w == 0 ? source.getWidth() : this.w;
        int h = this.h == 0 ? source.getHeight() : this.h;

        BufferedImage out = Util.newTransformedImage(source, w, h, flipVertical, flipHorizontal);

        fireProcessingEnded(-1, out);
        return out;
    }
}
