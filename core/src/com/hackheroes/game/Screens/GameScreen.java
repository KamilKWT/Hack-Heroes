package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackheroes.game.Components.Question;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.Scenes.IndicatorsInfo;
import com.hackheroes.game.Tools.QuestionLoader;

import java.util.Map;

public class GameScreen extends AbstractScreen {

    private class QuestionField {
        void render(String question) {
            game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
            game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.gameShapeRenderer.setColor(Color.BROWN);
            game.gameShapeRenderer.roundRect(140, 625, 530, 470, 20);
            game.gameShapeRenderer.setColor(Color.GOLD);
            game.gameShapeRenderer.roundRect(150, 635, 510, 450, 10);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameFont.getData().setScale(0.5f);
            game.gameFont.draw(game.gameBatch, question, 170, 1065, 470, 1, true);
            game.gameBatch.end();
        }
    }

    private class AnswerField {

        private int x, y, width, height;
        private String label;
        private Map<String, Integer> effects;

        private AnswerField(int x, int y, int width, int height, String label) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.label = label;
        }

        void render(Map<String, Integer> effects) {
            this.effects = effects;

            game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
            game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.gameShapeRenderer.setColor(Color.BROWN);
            game.gameShapeRenderer.roundRect(x, y, width, height, 20);
            game.gameShapeRenderer.setColor(Color.GOLD);
            game.gameShapeRenderer.roundRect(x + 10, y + 10, width - 20, height - 20, 10);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameFont.getData().setScale(0.5f);
            game.gameFont.draw(game.gameBatch, label, x + 30, y + height - 30, width - 60, 1, true);

            int i = 0;
            for (String key : effects.keySet()) {
                if (key.equals("money")) continue;
                drawEffect((y + height - 140) - (i * 100), key);
                i++;
            }
            game.gameBatch.end();
        }

        void drawEffect(int y, String key) {
            if (difficulty == MainClass.EASY) {
                game.gameBatch.draw(game.assetsLoader.findTexture(key), x + 40, y, 50, 50);
                if (effects.get(key) > 0) {
                    game.gameBatch.draw(game.assetsLoader.findTexture("plus-full"), x + 110, y + 10, 10, 10);
                    if (Math.abs(effects.get(key)) >= MainClass.MEDIUM_EFFECT_THRESHOLD)
                        game.gameBatch.draw(game.assetsLoader.findTexture("plus-full"), x + 140, y + 5, 20, 20);
                    else
                        game.gameBatch.draw(game.assetsLoader.findTexture("plus-empty"), x + 140, y + 5, 20, 20);
                    if (Math.abs(effects.get(key)) >= MainClass.LARGE_EFFECT_THRESHOLD)
                        game.gameBatch.draw(game.assetsLoader.findTexture("plus-full"), x + 180, y, 30, 30);
                    else
                        game.gameBatch.draw(game.assetsLoader.findTexture("plus-empty"), x + 180, y, 30, 30);
                } else {
                    game.gameBatch.draw(game.assetsLoader.findTexture("minus-full"), x + 110, y + 10, 10, 10);
                    if (Math.abs(effects.get(key)) >= MainClass.MEDIUM_EFFECT_THRESHOLD)
                        game.gameBatch.draw(game.assetsLoader.findTexture("minus-full"), x + 140, y + 5, 20, 20);
                    else
                        game.gameBatch.draw(game.assetsLoader.findTexture("minus-empty"), x + 140, y + 5, 20, 20);
                    if (Math.abs(effects.get(key)) >= MainClass.LARGE_EFFECT_THRESHOLD)
                        game.gameBatch.draw(game.assetsLoader.findTexture("minus-full"), x + 180, y, 30, 30);
                    else
                        game.gameBatch.draw(game.assetsLoader.findTexture("minus-empty"), x + 180, y, 30, 30);
                }

            } else if (difficulty == MainClass.MEDIUM) {
                game.gameBatch.draw(game.assetsLoader.findTexture(key), x + 40, y, 50, 50);
                game.gameBatch.draw(game.assetsLoader.findTexture("dot-full"), x + 110, y + 10, 10, 10);
                if (Math.abs(effects.get(key)) >= MainClass.MEDIUM_EFFECT_THRESHOLD)
                    game.gameBatch.draw(game.assetsLoader.findTexture("dot-full"), x + 140, y + 5, 20, 20);
                else
                    game.gameBatch.draw(game.assetsLoader.findTexture("dot-empty"), x + 140, y + 5, 20, 20);
                if (Math.abs(effects.get(key)) >= MainClass.LARGE_EFFECT_THRESHOLD)
                    game.gameBatch.draw(game.assetsLoader.findTexture("dot-full"), x + 180, y, 30, 30);
                else
                    game.gameBatch.draw(game.assetsLoader.findTexture("dot-empty"), x + 180, y, 30, 30);

            } else if (difficulty == MainClass.HARD) {
                game.gameBatch.draw(game.assetsLoader.findTexture(key), x + 100, y, 50, 50);
            }
        }

        void isClicked(int touchX, int touchY) {
            if (touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height) {
                for (String key : effects.keySet()) {
                    if (key.equals("money")) {
                        indicatorsInfo.money += effects.get(key);
                    } else {
                        indicatorsInfo.indicators.get(key).changeBy(effects.get(key));
                    }
                }
            }
        }
    }

    private MainClass game;

    private int difficulty = MainClass.EASY;
    private QuestionField questionField;
    private AnswerField acceptField, refuseField;
    public IndicatorsInfo indicatorsInfo;
    private final QuestionLoader ql = new QuestionLoader();
    private final Question q = ql.getQuestion();
//private String question = "Bardzo długi tekst, który nie zmieści się w jednej linijce, więc będzie musiał być rozbity na kilka linijek. Jednakże w całym polu tekstowym powinien się zmieścić.";
    /*private Map<String, Integer> accept = new TreeMap<String, Integer>() {{
        put("environment", 10);
        put("food", 15);
        put("population", -20);
        put("resources", -15);
        put("money", 2150);
    }};
    private Map<String, Integer> refuse = new TreeMap<String, Integer>() {{
        put("environment", 20);
        put("food", -5);
        put("population", -10);
        put("resources", +35);
        put("money", -1250);
    }};*/

    public GameScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void show() {
        questionField = new QuestionField();
        acceptField = new AnswerField(140, 95, 250, 500, "Akceptuj");
        refuseField = new AnswerField(420, 95, 250, 500, "Odmów");
        indicatorsInfo = new IndicatorsInfo(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(200f / 255f, 200f / 255f, 200f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!indicatorsInfo.bigWindow) {
            questionField.render(q.getQuestion());
            acceptField.render(q.getOnAccept());
            refuseField.render(q.getOnRefuse());
        }

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
        if (!(indicatorsInfo.showing || indicatorsInfo.hiding || indicatorsInfo.bigWindow)) {
            acceptField.isClicked(touchX, touchY);
            refuseField.isClicked(touchX, touchY);
        }
        indicatorsInfo.isClicked(touchX, touchY);
    }
}