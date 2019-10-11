package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.UI_Elements.StatusBar;

public class GameScreen extends AbstractScreen {

    private MainClass game;

    private Texture tmpTexture;
    public StatusBar statusBar1, statusBar2, statusBar3, statusBar4;

    public GameScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void show() {
        tmpTexture = game.assetsLoader.findTexture("badlogic");
        statusBar1 = new StatusBar(game, 50, 400, 50, 200, true, 100, 0);
        statusBar2 = new StatusBar(game, 50, 700, 50, 200, false, 100, 0);
        statusBar3 = new StatusBar(game, 150, 400, 200, 50, true, 100, 0);
        statusBar4 = new StatusBar(game, 150, 700, 200, 50, false, 100, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(200f / 255f, 200f / 255f, 200f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        statusBar1.render();
        statusBar2.render();
        statusBar3.render();
        statusBar4.render();

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