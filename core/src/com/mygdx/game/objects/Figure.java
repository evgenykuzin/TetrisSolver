package com.mygdx.game.objects;

import com.sun.org.glassfish.gmbal.Description;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import static com.mygdx.game.consts.Const.BRICK_DIMENTION;
import static com.mygdx.game.consts.Const.FIELD_COLS;
import static com.mygdx.game.consts.Const.STEP;

public class Figure {
    int[][] matrix;
    private int x;
    private int y;
    private int indx;
    private int jndx;
    public final int ROWS = 4;
    public final int COLS = 4;

    public enum type {
        T, O, S, L, I
    }

    public Enum<type> figureType;

    public Figure(int i, int j) {
        while (this.figureType == null) {
            for (Enum<type> e : type.values()) {
                if (new Random().nextInt(10) == 1) {
                    this.figureType = e;
                }
            }
        }
        init(i, j);
    }

    public Figure(int i, int j, Enum<type> figureType) {
        this.figureType = figureType;
        init(i, j);
    }

    public Figure(Figure figure) {
        indx = figure.index();
        jndx = figure.jndex();
        figureType = figure.figureType;
        x = jndx * BRICK_DIMENTION;
        y = indx * BRICK_DIMENTION;
        matrix = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = figure.getMatrix()[i][j];
            }
        }
    }

    private void init(int i, int j) {
        indx = i;
        jndx = j;
        x = j * BRICK_DIMENTION;
        y = i * BRICK_DIMENTION;
        if (figureType.equals(type.I)) {
            matrix = new int[][]{
                    new int[]{1, 0, 0, 0},
                    new int[]{1, 0, 0, 0},
                    new int[]{1, 0, 0, 0},
                    new int[]{1, 0, 0, 0}
            };
        } else if (figureType.equals(type.L)) {
            matrix = new int[][]{
                    new int[]{1, 0, 0, 0},
                    new int[]{1, 0, 0, 0},
                    new int[]{1, 1, 0, 0},
                    new int[]{0, 0, 0, 0}
            };
        } else if (figureType.equals(type.S)) {
            matrix = new int[][]{
                    new int[]{0, 1, 1, 0},
                    new int[]{1, 1, 0, 0},
                    new int[]{0, 0, 0, 0},
                    new int[]{0, 0, 0, 0}
            };
        } else if (figureType.equals(type.T)) {
            matrix = new int[][]{
                    new int[]{0, 1, 0, 0},
                    new int[]{1, 1, 1, 0},
                    new int[]{0, 0, 0, 0},
                    new int[]{0, 0, 0, 0}
            };
        } else if (figureType.equals(type.O)) {
            matrix = new int[][]{
                    new int[]{1, 1, 0, 0},
                    new int[]{1, 1, 0, 0},
                    new int[]{0, 0, 0, 0},
                    new int[]{0, 0, 0, 0}
            };
        }
        if (new Random().nextBoolean()) mirror();
    }

    public void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @Description("dir:\n" +
            "1 -> right\n" +
            "-1 -> left\n" +
            "2 -> up\n" +
            "-2 -> down")
    public void shiftElems(int dir, int[][] matrix) {
        for (int i = 0; i < ROWS; i++) {
            if (matrix[i][0] == 1 && dir == -1) return;
            if (matrix[i][COLS - 1] == 1 && dir == 1) {
                System.out.println("!!!!!!!!!!");
                return;
            }
        }
        for (int j = 0; j < COLS; j++) {
            if (matrix[0][j] == 1 && dir == -2) return;
            if (matrix[ROWS - 1][j] == 1 && dir == 2) return;
        }
        for (int i = 0; i < ROWS; i++) {

            for (int j = 0; j < COLS; j++) {

                if (dir == 1) {
                    int li = ROWS - i - 1;
                    int lj = COLS - j - 1;
                    if (lj - 1 >= 0) {
                        matrix[li][lj] = matrix[li][lj - 1];
                    } else matrix[li][lj] = 0;
                } else if (dir == -1) {
                    if (j + 1 < COLS) {
                        matrix[i][j] = matrix[i][j + 1];
                    } else matrix[i][j] = 0;
                } else if (dir == 2) {
                    int li = ROWS - i - 1;
                    int lj = COLS - j - 1;
                    if (li - 1 >= 0) {
                        matrix[li][lj] = matrix[li - 1][lj];
                    } else matrix[li][lj] = 0;
                } else if (dir == -2) {
                    if (i + 1 < ROWS) {
                        matrix[i][j] = matrix[i + 1][j];
                    } else matrix[i][j] = 0;
                }
            }
        }
    }

    private int[][] moveToCorner(int[][] matrix) {
        boolean shiftLeft = true;
        boolean shiftDown = true;
        while (shiftLeft || shiftDown) {
            for (int i = 0; i < ROWS; i++) {
                if (matrix[i][0] == 1) shiftLeft = false;
            }
            if (shiftLeft) shiftElems(-1, matrix);
            for (int j = 0; j < COLS; j++) {
                if (matrix[0][j] == 1) shiftDown = false;
            }
            if (shiftDown) shiftElems(-2, matrix);
        }
        return matrix;
    }

    public void mirror() {
        int[][] mirrored = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(matrix[ROWS - i - 1], 0, mirrored[i], 0, COLS);
        }
        matrix = moveToCorner(mirrored);
    }

    public void rotate() {
        int[][] rotMat = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                rotMat[COLS - 1 - j][i] = matrix[i][j];
            }
        }
        moveToCorner(rotMat);
        while (!inBorders(0, rotMat)) {
            for (int i = 1; i <= 4; i++) {
                if (inBorders(i, rotMat)) {
                    shiftElems(1, rotMat);
                    printMatrix(rotMat);
                    break;
                } else if (inBorders(-i, rotMat)) {
                    shiftElems(-1, rotMat);
                    printMatrix(rotMat);
                    break;
                }
            }
            changeX(-STEP);
        }
        matrix = rotMat;
    }

    public boolean inBorders(int jndxInc) {
        return inBorders(jndxInc, matrix);
    }

    private boolean inBorders(int jndxInc, int[][] matrix) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int js = jndx + jndxInc + j;
                if (matrix[i][j] == 1 && (js >= FIELD_COLS || js < 0)) return false;
            }
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public void changeX(int inc) {
        x += inc;
        int h = BRICK_DIMENTION;
        jndx = x / h;
    }

    public int getY() {
        return y;
    }

    public void changeY(int inc) {
        y += inc;
        int h = BRICK_DIMENTION;
        indx = y / h;
    }

    public void moveLeft() {
        if (inBorders(-1)) {
            jndx--;
            x = jndx * BRICK_DIMENTION;
        }
    }

    public void moveRight() {
        if (inBorders(1)) {
            jndx++;
            x = jndx * BRICK_DIMENTION;
        }
    }

    public void moveDown() {
        indx--;
        y = indx * BRICK_DIMENTION;
    }

    public int index() {
        return indx;
    }

    public int jndex() {
        return jndx;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return x == figure.x && y == figure.y &&
                indx == figure.indx && jndx == figure.jndx &&
                Arrays.equals(matrix, figure.matrix) &&
                figureType.equals(figure.figureType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y, indx, jndx, ROWS, COLS, figureType);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}
