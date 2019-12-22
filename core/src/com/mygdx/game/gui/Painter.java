package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.consts.RTextures;
import com.mygdx.game.objects.Brick;
import com.mygdx.game.objects.Field;
import com.mygdx.game.objects.Figure;

import java.util.ArrayList;

import static com.mygdx.game.consts.Const.BRICK_DIMENTION;
import static com.mygdx.game.consts.Const.FIELD_HEIGHT;
import static com.mygdx.game.consts.Const.FIELD_WIDTH;

public class Painter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture texture;
    private ArrayList<Brick> bricks;
    private BitmapFont font;
    public Painter(SpriteBatch batch) {
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.SLATE);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    public void setTexture(){
        texture = RTextures.purple_block;
    }

    public void drawBrick(Brick brick) {
        if (texture == null) setTexture();
        batch.draw(texture, brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
    }

    public void drawFigure(Figure figure) {
        if (bricks == null) setBricks();
        int x = figure.getX();
        int y = figure.getY();
        int width = BRICK_DIMENTION;
        int height = BRICK_DIMENTION;
        for (int i = 0; i < figure.getMatrix().length; i++) {
            for (int j = 0; j < figure.getMatrix().length; j++) {
                if (figure.getMatrix()[i][j] == 1) {
                    bricks.get(i).setPosition(x, y);
                    drawBrick(bricks.get(i));
                }
                x += width;
            }
            y += height;
            x = figure.getX();
        }
    }
    public void setBricks(){
        bricks = new ArrayList<Brick>();
        for (int i = 0; i < 4; i++) {
            Brick brick = new Brick(0, 0);
            //brick.setColor(figureType);
            bricks.add(brick);
        }
    }

    public void drawField(Field field) {
        for (int i = 0; i < field.getRows(); i++) {
            for (int j = 0; j < field.getCols(); j++) {
                if (field.getMatrix()[i][j] == 1) {
                    Brick brick = new Brick(i*BRICK_DIMENTION, j*BRICK_DIMENTION);
                    this.drawBrick(brick);
                }
            }
        }
    }

    public void drawBorders(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(FIELD_WIDTH, 0, FIELD_WIDTH, FIELD_HEIGHT*BRICK_DIMENTION);
        shapeRenderer.line(0, FIELD_HEIGHT, FIELD_WIDTH*BRICK_DIMENTION, FIELD_HEIGHT);
        for (int i = FIELD_HEIGHT; i < Gdx.graphics.getHeight(); i++) {
            shapeRenderer.line(0, i, FIELD_WIDTH*BRICK_DIMENTION, i);
        }
        for (int i = FIELD_WIDTH; i < Gdx.graphics.getWidth(); i++) {
            shapeRenderer.line(i, 0, i, FIELD_HEIGHT*BRICK_DIMENTION);
        }
        shapeRenderer.end();
    }

    public void drawScore(Field field) {
        font.draw(batch, "Score: "+field.getScore(), FIELD_WIDTH+10, FIELD_HEIGHT+10);
    }

}
