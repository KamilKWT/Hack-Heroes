package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.Scenes.IndicatorsInfo;

public class GameScreen extends AbstractScreen {

    private MainClass game;

    public IndicatorsInfo indicatorsInfo;

    public GameScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void show() {
        indicatorsInfo = new IndicatorsInfo(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(200f / 255f, 200f / 255f, 200f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        indicatorsInfo.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        indicatorsInfo.dispose();
    }

    public void isClicked(int touchX, int touchY) {
        indicatorsInfo.isClicked(touchX, touchY);
    }
}