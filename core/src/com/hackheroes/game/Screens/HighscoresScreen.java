package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.hackheroes.game.MainClass;

public class HighscoresScreen extends AbstractScreen {

    private MainClass game;

    private Preferences preferences;
    private Stage stage;
    private Table table;

    private String[] nicks = new String[10];
    private int[] scores = new int[10];
    private int position;

    private boolean canClick;

    public HighscoresScreen(MainClass game) {
        this.game = game;
        preferences = Gdx.app.getPreferences(MainClass.PREFERENCES_NAME);
        stage = new Stage(game.gameViewport);
        table = new Table();
        table.center();
        table.setSize(MainClass.V_WIDTH - 120, MainClass.V_HEIGHT - 420);
        table.setPosition(60, 210);
    }

    @Override
    public void show() {
        canClick = false;
        loadData();
        refreshTable(checkPosition());
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
        game.gameFont.draw(game.gameBatch, "Kliknij, aby wrócić do menu...", 0, 150, MainClass.V_WIDTH, 1, false);
        game.gameBatch.end();

        game.gameFont.getData().setScale(0.5f);
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
        Gdx.app.log("x", "" + stage.getActors().size);
        if (canClick) {
            game.menuScreen.setMainMenu(true);
            game.setScreen(game.menuScreen);
        }
    }

    private void resetData() {
        preferences.clear();
        preferences.flush();

        loadData();
        refreshStage();
    }

    private void saveData() {
        for (int i = 1; i <= 10; i++) {
            preferences.putString("nick" + i, nicks[i - 1]);
            preferences.putInteger("score" + i, scores[i - 1]);
        }

        preferences.flush();
    }

    private void loadData() {
        for (int i = 1; i <= 10; i++) {
            nicks[i - 1] = preferences.getString("nick" + i, "");
            scores[i - 1] = preferences.getInteger("score" + i, 0);
        }
    }

    private int checkPosition() {
        for (int i = 9; i > -1; i--) {
            if (game.resultsScreen.finalResult <= scores[i]) {
                return i + 1;
            }
        }
        return 0;
    }

    private void refreshTable(int position) {
        if (position < 10) {
            for (int i = 9; i > position; i--) {
                nicks[i] = nicks[i - 1];
                scores[i] = scores[i - 1];
            }
            this.position = position;
            enterPlayerData();
        } else {
            refreshStage();
            canClick = true;
        }
    }

    private void enterPlayerData() {
        Gdx.input.getTextInput(new TextInputListener() {
            @Override
            public void input(String text) {
                if (text.length() > 0) {
                    if (text.equals("~<resetData>")) {
                        resetData();
                    } else {
                        nicks[position] = text;
                        refreshStage();
                    }
                } else {
                    nicks[position] = "Nieznany";
                    refreshStage();
                }
                canClick = true;
            }

            @Override
            public void canceled() {
                nicks[position] = "Nieznany";
                refreshStage();
                canClick = true;
            }
        }, "Wprowadź swoje imie", null, "Twoje imie");
        scores[position] = game.resultsScreen.finalResult;
    }

    private void crateStage() {
        addTableCell(table, "Tablica wyników", 0, 3);
        table.row();
        for (int i = 0; i < 10; i++) {
            addTableCell(table, "" + (i + 1) + ".", (i == 0) ? (50) : (10));
            addTableCell(table, nicks[i], (i == 0) ? (50) : (10));
            addTableCell(table, (nicks[i].equals("")) ? ("") : ("" + scores[i]), (i == 0) ? (50) : (10));
            table.row();
        }
        addTableCell(table, " ", 25);
        addTableCell(table, "Twój wynik:", 25);
        addTableCell(table, "" + game.resultsScreen.finalResult, 25);

        stage.addActor(table);
    }

    private void addTableCell(Table table, String text, int padding, int colSpan) {
        table.add(new Label(text, new Label.LabelStyle(game.gameFont, MainClass.GUI_FONT_COLOR))).expandX().padTop(padding).colspan(colSpan);
    }

    private void addTableCell(Table table, String text, int padding) {
        addTableCell(table, text, padding, 1);
    }

    private void refreshStage() {
        table.clear();
        crateStage();
        saveData();
    }
}