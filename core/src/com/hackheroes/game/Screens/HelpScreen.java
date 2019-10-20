package com.hackheroes.game.Screens;

import com.hackheroes.game.MainClass;

public class HelpScreen extends AbstractScreen {

    private MainClass game;

    public HelpScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    public void isClicked(int touchX, int touchY) {
        
    }
}