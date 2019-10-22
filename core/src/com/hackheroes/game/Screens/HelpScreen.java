package com.hackheroes.game.Screens;

import com.hackheroes.game.MainClass;

public class HelpScreen extends AbstractScreen {

    private MainClass game;

    private int page;

    public HelpScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void show() {
        page = 1;
    }

    @Override
    public void render(float delta) {
        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
        game.gameBatch.begin();
        game.gameBatch.draw(game.assetsLoader.findTexture("page" + page), 0, 0, MainClass.V_WIDTH, MainClass.V_HEIGHT);
        game.gameBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    public void isClicked(int touchX, int touchY) {
        if (touchY <= 190 && touchX <= 360) {
            if (page > 1) page--;
            else game.setScreen(game.menuScreen);

        } else if (touchY <= 190 && touchX > 380) {
            if (page < 10) page++;
            else game.setScreen(game.menuScreen);
        }
    }
}