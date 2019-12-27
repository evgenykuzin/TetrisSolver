package com.mygdx.game.objects.gui_objects;

import static com.mygdx.game.consts.Const.BRICK_DIM;
import static com.mygdx.game.consts.Const.FIELD_HEIGHT;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private ActionSetter actionSetter;
    private String label;
    public Button(int x, int y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
        width = BRICK_DIM * 10;
        height = width / 4;
    }

    public boolean click(float cX, float cY){
        if (cX >= x && cX <= x+width && cY >= FIELD_HEIGHT-y*2 && cY <= FIELD_HEIGHT-y+height) {
            return actionSetter.action();
        }
        return false;
    }

    public void setAction(ActionSetter actionSetter){
        this.actionSetter = actionSetter;
    }

    public interface ActionSetter{
        boolean action();
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
