/*===========================================================================
COPYRIGHT 2013 Vinícius G. Mendonça ALL RIGHTS RESERVED.

This software cannot be copied, stored, distributed without
Vinícius G. Mendonça prior authorization.

This file was made available on https://github.com/ViniGodoy and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.vinigodoy.image.morpho;

import br.com.vinigodoy.image.util.IntMatrix;

public enum StructuringElement {
    HORIZONTAL_LINE("Horizontal line") {
        @Override
        public IntMatrix getMatrix() {
            return new IntMatrix(3, 1,
                    1, 1, 1);
        }
    },
    VERTICAL_LINE("Vertical line") {
        @Override
        public IntMatrix getMatrix() {
            return new IntMatrix(1, 3,
                    1,
                    1,
                    1);
        }
    },
    CROSS("Cross") {
        @Override
        public IntMatrix getMatrix() {
            return new IntMatrix(3, 3,
                    0, 1, 0,
                    1, 1, 1,
                    0, 1, 0);
        }
    },
    SQUARE("Square") {
        @Override
        public IntMatrix getMatrix() {
            return new IntMatrix(3, 3,
                    1, 1, 1,
                    1, 1, 1,
                    1, 1, 1);
        }
    },
    RHOMBUS("Rhombus") {
        @Override
        public IntMatrix getMatrix() {
            return new IntMatrix(5, 5,
                    0, 1, 1, 1, 0,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1,
                    0, 1, 1, 1, 0);
        }
    };


    private String description;

    private StructuringElement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract IntMatrix getMatrix();
}
