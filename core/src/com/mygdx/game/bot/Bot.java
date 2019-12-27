package com.mygdx.game.bot;

import com.mygdx.game.objects.Field;
import com.mygdx.game.objects.Figure;
import com.mygdx.game.objects.FigureGenerator;

import java.util.ArrayList;
import static com.mygdx.game.consts.Const.FIELD_COLS;

public class Bot {
    int speed;
    FigureGenerator fg;
    public Bot(){
        speed = 1;
        fg = new FigureGenerator();
    }

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

    public void play(Field field, Figure figure){
        if (!field.checkGameOver()) {
            figure.changeY(-speed);
            if (field.update(figure)) {
                figure.set(choose(field, fg.generate(0)));
            }
            field.checkFullLine();
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
