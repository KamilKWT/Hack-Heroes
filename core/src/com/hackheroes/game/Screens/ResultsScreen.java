package com.hackheroes.game.Screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.SnapshotArray;
import com.hackheroes.game.MainClass;

import java.util.Map;

public class ResultsScreen extends AbstractScreen {

    private MainClass game;

    int finalResult;
    private Stage stage;

    public ResultsScreen(MainClass game) {
        this.game = game;
        stage = new Stage(game.gameViewport);
    }

    @Override
    public void show() {
        finalResult = 0;
        Map<String, Integer> result = game.gameScreen.getResult();
        calcResult(result);

        Table table = new Table();
        table.center();
        table.setSize(MainClass.V_WIDTH - 120, MainClass.V_HEIGHT - 420);
        table.setPosition(60, 210);

        addTableCell(table, "Wynik:", 0, 2);
        table.row();
        addTableCell(table, "Punkty:", 50);
        addTableCell(table, "" + result.get("score"), 50);
        table.row();
        addTableCell(table, "Środowisko:", 25);
        addTableCell(table, "" + result.get("environment"), 25);
        table.row();
        addTableCell(table, "Pożywienie:", 10);
        addTableCell(table, "" + result.get("food"), 10);
        table.row();
        addTableCell(table, "Populacja:", 10);
        addTableCell(table, "" + result.get("population"), 10);
        table.row();
        addTableCell(table, "Surowce:", 10);
        addTableCell(table, "" + result.get("resources"), 10);
        table.row();
        addTableCell(table, "Pieniądze:", 25);
        addTableCell(table, "" + result.get("money"), 25);
        table.row();
        addTableCell(table, "Wynik końcowy:", 50);
        addTableCell(table, "" + finalResult, 50);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
        game.gameShapeRenderer.roundRect(50, 200, MainClass.V_WIDTH - 100, MainClass.V_HEIGHT - 400, 20);
        game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
        game.gameShapeRenderer.roundRect(60, 210, MainClass.V_WIDTH - 120, MainClass.V_HEIGHT - 420, 10);
        game.gameShapeRenderer.end();

        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
        game.gameBatch.begin();
        game.gameFont.getData().setScale(1.25f);
        game.gameFont.draw(game.gameBatch, "Save Your World", 0, MainClass.V_HEIGHT - 50, MainClass.V_WIDTH, 1, false);
        game.gameFont.getData().setScale(0.5f);
        game.gameFont.draw(game.gameBatch, "Kliknij, aby kontynuować...", 0, 150, MainClass.V_WIDTH, 1, false);
        game.gameBatch.end();

        game.gameFont.getData().setScale(0.75f);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void isClicked(int touchX, int touchY) {
        SnapshotArray<Actor> actors = new SnapshotArray<>(stage.getActors());
        for(Actor actor : actors) {
            actor.remove();
        }
        game.setScreen(game.highscoresScreen);
    }

    private void addTableCell(Table table, String text, int padding, int colSpan) {
        table.add(new Label(text, new Label.LabelStyle(game.gameFont, MainClass.GUI_FONT_COLOR))).expandX().padTop(padding).colspan(colSpan);
    }

    private void addTableCell(Table table, String text, int padding) {
        addTableCell(table, text, padding, 1);
    }

    private void calcResult(Map<String, Integer> result) {
        for (String key : result.keySet()) {
            if (key.equals("money")) {
                finalResult += result.get(key) / 100;
            } else {
                finalResult += result.get(key);
            }
        }
    }
}