package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.bot.Bot;
import com.mygdx.game.objects.Field;
import com.mygdx.game.objects.Figure;
import com.mygdx.game.objects.FigureGenerator;
import static com.mygdx.game.consts.Const.STEP;

public class TetrisGameScreen implements Screen {
    private FigureGenerator figureGenerator;
    private Figure figure;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Field field;
    private Painter painter;
    private Bot bot;

    TetrisGameScreen() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        this.camera.position.set(0, 0, 0);
        this.camera.update();
        figureGenerator = new FigureGenerator();
        field = new Field();
        painter = new Painter(batch);
        bot = new Bot();
        figure = bot.choose(field, figureGenerator.generate(0));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(10f, 20, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        painter.drawBorders();
        batch.begin();
        int speed = 1;
        bot.setSpeed(speed);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            figure.rotate();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (figure.inBorders(-1)) {
                figure.changeX(-STEP);
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (figure.inBorders(1)) {
                figure.changeX(STEP);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            speed = STEP;
            bot.setSpeed(speed);
        }
        bot.play(field, figure);
        painter.drawFigure(figure);
        painter.drawField(field);
        painter.drawScore(field);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
