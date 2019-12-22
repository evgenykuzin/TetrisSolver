package com.mygdx.game.objects;

import java.util.Random;
import static com.mygdx.game.consts.Const.FIELD_ROWS;

public class FigureGenerator {
    public Figure generate(){
        return new Figure(FIELD_ROWS, new Random().nextInt(20));
    }
    public Figure generate(int j){
        return new Figure(FIELD_ROWS, j);
    }

}
