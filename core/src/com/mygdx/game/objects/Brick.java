package com.mygdx.game.objects;

import static com.mygdx.game.consts.Const.BRICK_DIM;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    public Brick(int y, int x){
        this.x = x;
        this.y = y;
        width = BRICK_DIM;
        height = BRICK_DIM;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
