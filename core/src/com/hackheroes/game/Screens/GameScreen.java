package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.hackheroes.game.MainClass;

public class GameScreen extends AbstractScreen {

    private MainClass game;

    private Texture tmpTexture;

    public GameScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void show() {
        tmpTexture = game.assetsLoader.findTexture("badlogic");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
        game.gameBatch.begin();
        game.gameBatch.draw(tmpTexture, 0, 0);
        game.gameBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void dispose() {

    }
}