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
import com.mygdx.game.objects.gui_objects.Button;

import static com.mygdx.game.consts.Const.FIELD_WIDTH;
import static com.mygdx.game.consts.Const.STEP;

public class TetrisGameScreen implements Screen {
    private FigureGenerator figureGenerator;
    private Figure figure;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Field field;
    private Painter painter;
    private Bot bot;
    private Button speedButton;
    private Button botButton;
    private boolean botIsPlayer;
    private int speed = 1;
    private boolean touchOff = false;
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
        speedButton = new Button(FIELD_WIDTH, 200, "Boost");
        speedButton.setAction(new Button.ActionSetter() {
            @Override
            public boolean action() {
                if (bot.getSpeed() == 1) {
                    bot.setSpeed(STEP);
                    speedButton.setLabel("Slow");
                } else {
                    bot.setSpeed(1);
                    speedButton.setLabel("Boost");
                }
                return true;
            }
        });
        botIsPlayer = false;
        botButton = new Button(FIELD_WIDTH, 100, "Bot help");
        botButton.setAction(new Button.ActionSetter() {
            @Override
            public boolean action() {
                botIsPlayer = !botIsPlayer;
                if (!botIsPlayer) {
                    botButton.setLabel("Bot help!");
                } else {
                    botButton.setLabel("Myself!");
                }
                return true;
            }
        });
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
            if (botIsPlayer) {
                if (bot.getSpeed() == 1) {
                    bot.setSpeed(STEP);
                } else {
                    bot.setSpeed(1);
                }
            } else {
                speed = STEP;
            }
        } else {
            if (!botIsPlayer) {
                speed = 1;
            }
        }

//        if (Gdx.input.isTouched()) {
//            touchOff = true;
//        } else if (touchOff) {
//
//            touchOff = false;
//        }
        if (Gdx.input.isTouched()) {
           touchOff = true;
        } else if (touchOff) {
            botButton.click(Gdx.input.getX(), Gdx.input.getY());
            speedButton.click(Gdx.input.getX(), Gdx.input.getY());

            touchOff = false;
        }
        if (botIsPlayer) {
            bot.play(field, figure);
        } else {
            if (!field.checkGameOver()) {
                figure.changeY(-speed);
                if (field.update(figure)) {
                    figure = figureGenerator.generate();
                }
                field.checkFullLine();
            }
        }
        painter.drawFigure(figure);
        painter.drawField(field);
        painter.drawScore(field);
        painter.drawButton(speedButton);
        painter.drawButton(botButton);
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
