package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.Components.StatusBar;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends AbstractScreen {

    private MainClass game;

    private final Map<String, StatusBar> bars = new HashMap<>();

    public GameScreen(MainClass game) {
        this.game = game;
        bars.put("money", new StatusBar(game, 50, 700, 50, 200, true, 100, 0, "money.png"));
        bars.put("population", new StatusBar(game, 110, 700, 50, 200, true, 100, 0, "population.png"));
        bars.put("food", new StatusBar(game, 170, 700, 50, 200, true, 100, 0, "food.png"));
        bars.put("environment", new StatusBar(game, 230, 700, 50, 200, true, 100, 0, "env.png"));
        bars.put("materials", new StatusBar(game, 290, 700, 50, 200, true, 100, 0, "ore.png"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);

        game.gameBatch.begin();
        for (StatusBar bar : bars.values()) bar.render();
        game.gameBatch.end();

        game.gameBatch.begin();
        for (StatusBar bar : bars.values()) bar.drawIcon();
        game.gameBatch.end();
    }

    public void changeBar(String name, float amount) {
        if (!bars.containsKey(name) && name != "all") return;

        if (name == "all") for (StatusBar bar : bars.values()) bar.addValue(amount);
        else bars.get(name).addValue(amount);
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void dispose() {

    }
}