/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.color.lsh.LSHColor;
import br.com.vinigodoy.image.color.lsh.LSHImage;
import br.com.vinigodoy.image.processing.AbstractProcessing;
import br.com.vinigodoy.image.util.IntMatrix;
import br.com.vinigodoy.image.util.PixelPosition;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.SortedSet;

public class Reconstruction extends AbstractProcessing {
    private IntMatrix se;
    private LSHImage S; //I
    private IntMatrix rasterSE;
    private IntMatrix antiRasterSE;

    public Reconstruction(IntMatrix structuringElement, BufferedImage mask) {
        se = structuringElement;
        rasterSE = structuringElement.createRaster();
        antiRasterSE = structuringElement.createAntiRaster();
        this.S = new LSHImage(mask);
    }

    public Reconstruction(BufferedImage mask) {
        this(StructuringElement.SQUARE.getMatrix(), mask);
    }

    @Override
    public BufferedImage process(BufferedImage marker) {
        LSHImage Z = new LSHImage(marker);

        rasterDilation(Z);
        Queue<PixelPosition> queue = antiRasterDilation(Z);
        propagate(Z, queue);
        BufferedImage result = Z.toImageLike(marker);
        fireProcessingEnded(-1, result);
        return result;
    }

    private void rasterDilation(LSHImage Z) {
        fireProcessingStarted(S.getCount(),
                "iterative dilation (raster)");
        int count = 0;
        for (int y = 0; y < S.getHeight(); ++y)
            for (int x = 0; x < S.getWidth(); ++x) {
                Z.setPixel(x, y,
                        min(maxPixel(Z, rasterSE, x, y), S.getPixel(x, y)));
                fireProcessingProgress(count++, S.getCount());
            }
    }

    private Queue<PixelPosition> antiRasterDilation(LSHImage Z) {
        fireProcessingStarted(S.getCount(),
                "iterative dilation (anti-raster)");
        int count = 0;
        Queue<PixelPosition> queue = new LinkedList<PixelPosition>();
        for (int y = S.getHeight() - 1; y >= 0; --y)
            for (int x = S.getWidth() - 1; x >= 0; --x) {
                PixelPosition p = new PixelPosition(x, y);
                LSHColor Sxy = S.getPixel(p);
                Z.setPixel(x, y, min(maxPixel(Z, antiRasterSE, x, y), Sxy));

                //Separa��o da borda
                List<PixelPosition> pixels = Z.extractNeighbors(x, y, antiRasterSE);
                for (PixelPosition q : pixels) {
                    LSHColor Zq = Z.getPixel(q);
                    if (Zq.lesserThan(Z.getPixel(p)) && Zq.lesserThan(S.getPixel(q))) {
                        queue.add(p);
                        break;
                    }
                }
                fireProcessingProgress(count++, S.getCount());
            }
        return queue;
    }

    private void propagate(LSHImage Z, Queue<PixelPosition> queue) {
        fireProcessingStarted(-1, "propagation");
        while (!queue.isEmpty()) {
            PixelPosition xy = queue.poll();
            for (PixelPosition q : Z.extractNeighbors(xy.getX(), xy.getY(), se)) {
                LSHColor Zq = Z.getPixel(q);
                LSHColor Zxy = Z.getPixel(xy);
                LSHColor Sq = S.getPixel(q);

                if (Zq.lesserThan(Zxy) && !Zq.equals(Sq)) {
                    Z.setPixel(q, min(Zxy, Sq));
                    queue.add(q);
                }
            }
        }
    }


    private LSHColor maxPixel(LSHImage Z, IntMatrix raster, int x, int y) {
        SortedSet<LSHColor> BrgS = Z.extractPixels(x, y, raster);
        BrgS.add(Z.getPixel(x, y));
        return BrgS.last();
    }

    private LSHColor min(LSHColor c1, LSHColor c2) {
        return c1.lesserThan(c2) ? c1 : c2;
    }
}
