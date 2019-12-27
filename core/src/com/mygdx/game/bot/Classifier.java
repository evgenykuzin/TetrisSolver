package com.mygdx.game.bot;

import com.mygdx.game.objects.Field;
import com.mygdx.game.objects.Figure;

import java.util.ArrayList;

import static com.mygdx.game.consts.Const.FIELD_COLS;
import static com.mygdx.game.consts.Const.FIELD_ROWS;

public class Classifier {
    private ArrayList<Integer> markList;
    private Field oldField;
    private Field newField;
    private Figure figure;
    private int[][] oldMatrix;
    private int[][] newMatrix;

    public Classifier(Field oldField, Field newField, Figure figure) {
        this.oldField = new Field(oldField);
        this.newField = new Field(newField);
        this.figure = new Figure(figure);
        oldMatrix = oldField.getMatrix();
        newMatrix = newField.getMatrix();
        markList = new ArrayList<Integer>();
        setHolesMark();
        setFullLineMark();
        setHeightMark();
//        setHeightMark();
//        setHolesMark();
//        //setHollowsMark();
//        setFullLineMark();
    }

    public Classifier(ArrayList<Integer> markList) {
        this.markList = markList;
    }

    public Field getNewField() {
        return newField;
    }

    public Field getOldField() {
        return oldField;
    }

    public Figure getFigure() {
        return figure;
    }

    public ArrayList<Integer> getMarkList() {
        return markList;
    }

//    public Classifier getBetter(Classifier anotherMarks) {
//        Classifier best = this;
//        int bestDiff = 0;
//        ArrayList<Integer> anotherList = anotherMarks.getMarkList();
//        int sum1 = 0;
//        int sum2 = 0;
//        for (int i = 0; i < markList.size(); i++) {
//            int diff = anotherList.get(i) - markList.get(i);
//            if (diff > bestDiff) {
//                bestDiff = diff;
//                best = anotherMarks;
//            }
//            sum1+=markList.get(i);
//            sum2+=anotherList.get(i);
//        }
//       // best = sum1/markList.size() < sum2/anotherList.size()? this : anotherMarks;
//        return best;
//    }

    public Classifier getBetter(Classifier anotherMarks) {
        Classifier best = this;
        int bestDiff = 0;
        ArrayList<Integer> anotherList = anotherMarks.getMarkList();
       // int diff = 0;
        int diff1 = anotherList.get(0) - markList.get(0);
        int diff2 = anotherList.get(1) - markList.get(1);
        int diff3 = anotherList.get(2) - markList.get(2);

        if(diff1 > 0){
           return anotherMarks;
        } else if (diff1 == 0) {
            if(diff2 > 0) {
                return anotherMarks;
            } else if (diff2 == 0 ) {
                if (diff3 > 0) {
                    return anotherMarks;
                }
            }
        }
        return this;
    }

    public void setFullLineMark() {
        int oldLines = countLines(oldMatrix);
        int newLines = countLines(newMatrix);
        int diff = newLines - oldLines;
        markList.add(diff);
    }

    private int countLines(int[][] matrix) {
        int lines = 0;
        for (int i = 0; i < FIELD_ROWS; i++) {
            int cells = 0;
            for (int j = 0; j < FIELD_COLS; j++) {
                if (matrix[i][j] == 1) cells++;
            }
            if (cells >= FIELD_COLS) lines++;
        }
        return lines;
    }

    public void setHeightMark() {
        int oldHeight = getHeight(oldMatrix);
        int newHeight = getHeight(newMatrix);
        int diff = oldHeight - newHeight;
        markList.add(diff);
    }

    private int getHeight(int[][] matrix) {
        int h = 0;
        for (int i = FIELD_ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < FIELD_COLS; j++) {
                if (matrix[i][j] == 1) {
                    return FIELD_ROWS - h;
                }
            }
            h++;
        }
        return 0;
    }

    public void setHollowsMark() {
        int oldHollowsCount = countHollows(oldMatrix);
        int newHollowsCount = countHollows(newMatrix);
        markList.add(oldHollowsCount - newHollowsCount);
    }

    private int countHollows(int[][] matrix) {
        int hollowsCount = 0;
        for (int i = 0; i < FIELD_ROWS; i++) {
            for (int j = 0; j < FIELD_COLS; j++) {
                if (matrix[i][j] == 0) {
                    if (i - 1 >= 0) {
                        if (matrix[i - 1][j] == 1) {
                            if (j - 1 >= 0) {
                                if (matrix[i][j - 1] == 1) {
                                    hollowsCount++;
                                }
                            }
                            if (j + 1 < FIELD_COLS) {
                                if (matrix[i][j + 1] == 1) {
                                    hollowsCount++;
                                }
                            }
                        }
                    }
                }

            }
        }
        return hollowsCount;
    }

    public void setHolesMark() {
        int oldHolesCount = countHoles(oldMatrix);
        int newHolesCount = countHoles(newMatrix);
        int diff = oldHolesCount - newHolesCount;
        markList.add(diff);
    }

    private int countHoles(int[][] matrix) {
        int holesCount = 0;
        for (int i = 0; i < FIELD_ROWS; i++) {
            boolean holeExist = false;
            for (int j = FIELD_COLS - 2; j >= 0; j--) {
                if (i + 1 < FIELD_COLS) {
                    if ((matrix[i + 1][j] == 1 || holeExist) && matrix[i][j] == 0) {
                        holeExist = true;
                        holesCount++;
                    }
                }
            }
        }
        return holesCount;
    }

    public void setMarkList(ArrayList<Integer> markList) {
        this.markList = markList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classifier {\n");
        sb.append(" fullLinesMark: " + markList.get(0) + "\n");
        sb.append(" holesMark: " + markList.get(1) + "\n");
        sb.append(" hightMark: " + markList.get(2) + "\n");
//        sb.append(" hollows: "+ markList.get(3) +"\n");
        sb.append("\n}");
        return sb.toString();
    }
}
