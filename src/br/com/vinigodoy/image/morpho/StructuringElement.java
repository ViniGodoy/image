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
