/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.transform;

public enum Rotation {
    R90(Math.PI / 2, true),
    R180(Math.PI, false),
    R270(3 * Math.PI / 2, true);

    private double angle;
    private boolean inverseWidthAndHeight;

    private Rotation(double angle, boolean inverseWidthAndHeight) {
        this.angle = angle;
        this.inverseWidthAndHeight = inverseWidthAndHeight;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isInverseWidthAndHeight() {
        return inverseWidthAndHeight;
    }

}
