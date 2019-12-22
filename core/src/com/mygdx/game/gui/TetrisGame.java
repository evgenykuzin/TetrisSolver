package com.mygdx.game.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TetrisGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public TetrisGameScreen gameScreen;
    public int deviceWidth;
    public int deviceHeight;
    @Override
    public void create () {
        deviceWidth = Gdx.graphics.getWidth();
        deviceHeight = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        font = new BitmapFont();
        gameScreen = new TetrisGameScreen();
        this.setScreen(gameScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        font.dispose();
        gameScreen.dispose();
    }
}
