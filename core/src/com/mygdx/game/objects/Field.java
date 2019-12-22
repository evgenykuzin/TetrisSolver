package com.mygdx.game.objects;

import java.util.Arrays;
import static com.mygdx.game.consts.Const.FIELD_COLS;
import static com.mygdx.game.consts.Const.FIELD_ROWS;

public class Field {
    int[][] matrix;
    int rows;
    int cols;
    int score;

    public Field() {
        rows = FIELD_ROWS;
        cols = FIELD_COLS;
        score = 0;
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(matrix[i], 0);
        }
    }

    public Field(Field field) {
        rows = FIELD_ROWS;
        cols = FIELD_COLS;
        score = field.score;
        //this.matrix = Arrays.copyOf(field.matrix, ROWS);
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int v = field.matrix[i][j];
                matrix[i][j] = v;
            }
        }
    }

    public Field(int[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        score = 0;
        this.matrix = Arrays.copyOf(matrix, rows);
    }

    public boolean addFigure(Figure figure) {
        for (int i = 0; i < figure.ROWS; i++) {
            for (int j = 0; j < figure.COLS; j++) {
                int fi = figure.index() + i;
                int fj = figure.jndex() + j;
                int l = rows;
                int value = figure.matrix[i][j];
                if (value == 1) {
                    if (fi < l && fi >= 0 && fj < l && fj >= 0) {
                        matrix[fi][fj] = value;
                    }
                }
            }
        }
        return true;
    }

    public boolean update(Figure figure) {
        int[][] figureMatrix = figure.matrix;
        for (int i = 0; i < figure.ROWS; i++) {
            for (int j = 0; j < figure.COLS; j++) {
                int index = figure.index();
                int jndex = figure.jndex();
                if (figureMatrix[i][j] == 1) {
                    if (index + i == 0) {
                        return addFigure(figure);
                    } else if ((index + i - 1 < rows && jndex + j < cols)) {
                        if ((matrix[index + i - 1][jndex + j] == 1)) {
                            return addFigure(figure);
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkFullLine() {
        boolean lineIsFull = false;
        for (int i = 0; i < rows; i++) {
            int counter = 0;
            for (int j = 0; j < cols; j++) {
                if (lineIsFull && i + 1 < rows) {
                    matrix[i - 1][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    counter++;
                } else if (counter != 0) {
                    break;
                }
            }
            if (counter >= FIELD_COLS && !lineIsFull) {
                lineIsFull = true;
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (lineIsFull) score += FIELD_COLS;
        return lineIsFull;
    }

    public boolean checkGameOver(){
        for (int i = 0; i < cols; i++) {
            if(matrix[rows-1][i] == 1) return true;
        }
        return false;
    }

    public void setField(Field field) {
        this.matrix = field.matrix;
        this.rows = field.rows;
        this.cols = field.cols;
        this.score = field.score;
    }

    public void print() {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getScore() {
        return score;
    }
}
