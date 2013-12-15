/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image;

import br.com.vinigodoy.image.util.Util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Loads images from disk.
 *
 * @author Vin�cius G. Mendon�a
 */
public class ImageLoader {
    private boolean optimize = true;
    private GraphicsConfiguration destination = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getDefaultScreenDevice()
            .getDefaultConfiguration();

    /**
     * Creates a BufferedImage with a data layout and color model compatible
     * with the destination GraphicsConfiguration. Transparency will be set to
     * be closest as possible to the original image transparency capabilities.
     *
     * @param img The optimized image.
     * @return The optimized image.
     */
    public BufferedImage optimize(BufferedImage img) {
        BufferedImage optimized = Util.newOptimizedImageLike(destination, img);
        Graphics2D g2d = optimized.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return optimized;
    }

    /**
     * Loads an image.
     *
     * @param input Image source.
     * @return The loaded image. If this loader is optimizing images, the image
     * will be converted to the destination Graphics Configuration.
     * @throws IOException If it is not possible to load the input file as an image.
     * @see #isOptimizing()
     * @see #getDestination()
     */
    public BufferedImage load(File input) throws IOException {
        return isOptimizing() ? optimize(ImageIO.read(input)) : ImageIO
                .read(input);
    }

    /**
     * Loads an image.
     *
     * @param input Image source.
     * @return The loaded image. If this loader is optimizing images, the image
     * will be converted to the destination Graphics Configuration.
     * @throws IOException If it is not possible to load the input file as an image.
     * @see #isOptimizing()
     * @see #getDestination()
     */
    public BufferedImage load(URL input) throws IOException {
        return isOptimizing() ? optimize(ImageIO.read(input)) : ImageIO
                .read(input);
    }

    /**
     * Loads an image.
     *
     * @param input Image source.
     * @return The loaded image. If this loader is optimizing images, the image
     * will be converted to the destination Graphics Configuration.
     * @throws IOException If it is not possible to load the input file as an image.
     * @see #isOptimizing()
     * @see #getDestination()
     */
    public BufferedImage load(InputStream input) throws IOException {
        return isOptimizing() ? optimize(ImageIO.read(input)) : ImageIO
                .read(input);
    }

    /**
     * Loads an image.
     *
     * @param input Image source.
     * @return The loaded image. If this loader is optimizing images, the image
     * will be converted to the destination Graphics Configuration.
     * @throws IOException If it is not possible to load the input file as an image.
     * @see #isOptimizing()
     * @see #getDestination()
     */
    public BufferedImage load(ImageInputStream input) throws IOException {
        return isOptimizing() ? optimize(ImageIO.read(input)) : ImageIO
                .read(input);
    }

    /**
     * Indicate if an images will be optimized after loading or not.
     */
    public boolean isOptimizing() {
        return optimize;
    }

    /**
     * Indicate if an images will be optimized after loading or not.
     *
     * @param optimize True to optimize, false to return the image in it's original format.
     */
    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    /**
     * Changes the destination graphics configuration. All images will be
     * optimized for this configuration if the isOptimizing() property is set to
     * true.
     *
     * @param destination The destination graphics configuration. If the destination is
     *                    null, the default destination will be restored.
     */
    public void setDestination(GraphicsConfiguration destination) {
        if (destination == null)
            destination = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDefaultConfiguration();

        this.destination = destination;
    }

    /**
     * Gets destination graphics configuration. All images will be optimized for
     * this configuration if the isOptimizing() property is set to true.
     *
     * @return The destination graphics configuration.
     */
    public GraphicsConfiguration getDestination() {
        return destination;
    }
}
