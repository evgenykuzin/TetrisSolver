package com.mygdx.game.bot;

import com.mygdx.game.objects.Field;
import com.mygdx.game.objects.Figure;
import java.util.ArrayList;
import static com.mygdx.game.consts.Const.FIELD_COLS;

public class Bot {

    public Figure choose(Field field, Figure figure) {
        ArrayList<Classifier> classifiers = new ArrayList<Classifier>();
        Figure newFigure = new Figure(figure);
        for (int i = 0; i < FIELD_COLS; i++) {
            for (int j = 0; j < 4; j++) {
                Field newField = new Field(field);
                Figure falling = new Figure(newFigure);
                while (!newField.update(falling)) {
                    falling.moveDown();
                }
                classifiers.add(new Classifier(field, newField, newFigure));
                newFigure.rotate();
            }
            newFigure.moveRight();
        }
        Classifier best = classifiers.get(0);
        for (int i = 1; i < classifiers.size(); i++) {
            best = classifiers.get(i).getBetter(best);
        }
        return best.getFigure();
    }

}
