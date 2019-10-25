package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackheroes.game.Components.Question;
import com.hackheroes.game.Components.StatusBar;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.Scenes.IndicatorsInfo;

import java.util.Map;
import java.util.TreeMap;

public class GameScreen extends AbstractScreen {

    private class QuestionField {
        void render(String question) {
            game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
            game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
            game.gameShapeRenderer.roundRect(140, 580, 530, 470, 20);
            game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
            game.gameShapeRenderer.roundRect(150, 590, 510, 450, 10);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameFont.getData().setScale(0.5f);
            game.gameFont.draw(game.gameBatch, question, 170, 1020, 470, 1, true);
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
            game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
            game.gameShapeRenderer.roundRect(x, y, width, height, 20);
            game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
            game.gameShapeRenderer.roundRect(x + 10, y + 10, width - 20, height - 20, 10);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameFont.getData().setScale(0.5f);
            game.gameFont.draw(game.gameBatch, label, x + 30, y + height - 30, width - 60, 1, true);

            int i = 0;
            for (String key : effects.keySet()) {
                if (!key.equals("money")) {
                    drawEffect((y + height - 140) - (i * 100), key);
                    i++;
                }
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
                Gdx.app.log("" + ((activeQuestion.getOnAccept().keySet().contains("money")) ? (activeQuestion.getOnAccept("money")) : 0),
                            "" + (indicatorsInfo.money + ((activeQuestion.getOnAccept().keySet().contains("money")) ? (activeQuestion.getOnAccept("money")) : 0))
                );
                Gdx.app.log("" + ((activeQuestion.getOnRefuse().keySet().contains("money")) ? (activeQuestion.getOnRefuse("money")) : 0),
                            "" + (indicatorsInfo.money + ((activeQuestion.getOnRefuse().keySet().contains("money")) ? (activeQuestion.getOnRefuse("money")) : 0))
                );

                if (indicatorsInfo.money + ((activeQuestion.getOnAccept().keySet().contains("money")) ? (activeQuestion.getOnAccept("money")) : 0) < 0 &&
                    indicatorsInfo.money + ((activeQuestion.getOnRefuse().keySet().contains("money")) ? (activeQuestion.getOnRefuse("money")) : 0) < 0) {
                    endReason = "money";
                    Gdx.app.log("ok", "ok");
                }

                Gdx.app.log("" + ((effects.keySet().contains("money")) ? (effects.get("money")) : 0),
                            "" + (indicatorsInfo.money + ((effects.keySet().contains("money")) ? (effects.get("money")) : 0)));

                if (indicatorsInfo.money + ((effects.keySet().contains("money")) ? (effects.get("money")) : 0) > 0) {
                    for (String key : effects.keySet()) {
                        if (key.equals("money")) {
                            indicatorsInfo.money += effects.get(key);
                        } else {
                            indicatorsInfo.indicators.get(key).changeBy(effects.get(key));
                        }
                    }
                    activeQuestion = game.questionsLoader.getRandomQuestion();
                    indicatorsInfo.score += 50 * difficulty;
                }
            }
        }
    }

    private MainClass game;

    int difficulty;
    private Question activeQuestion;

    private QuestionField questionField;
    private AnswerField acceptField, refuseField;
    private IndicatorsInfo indicatorsInfo;

    String endReason;

    public GameScreen(MainClass game) {
        this.game = game;

        questionField = new QuestionField();
        acceptField = new AnswerField(140, 50, 250, 500, "Akceptuj");
        refuseField = new AnswerField(420, 50, 250, 500, "Odrzuć");
        indicatorsInfo = new IndicatorsInfo(game);
    }

    @Override
    public void render(float delta) {
        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
        game.gameShapeRenderer.roundRect(140, 1000, 530, 140, 30);
        game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
        game.gameShapeRenderer.roundRect(150, 1010, 510, 120, 20);
        game.gameShapeRenderer.setColor(MainClass.GUI_MARKED_COLOR);
        game.gameShapeRenderer.roundRect(160, 1020, 490, 100, 10);
        game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
        game.gameShapeRenderer.rect(150, 1040, 510, 20);
        game.gameShapeRenderer.end();

        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
        game.gameBatch.begin();
        game.gameBatch.draw(game.assetsLoader.findTexture("menuBtn"), 0, 1180, 100, 100);
        game.gameBatch.end();

        if (!indicatorsInfo.bigWindow) {
            questionField.render(activeQuestion.getQuestion());
            acceptField.render(activeQuestion.getOnAccept());
            refuseField.render(activeQuestion.getOnRefuse());
        }

        indicatorsInfo.render(delta);
        checkValues();
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        indicatorsInfo.dispose();
    }

    @Override
    public void isClicked(int touchX, int touchY) {
        if (Math.sqrt(Math.pow(touchX - 50, 2) + Math.pow(touchY - 1230, 2)) <= 50) {
            game.menuScreen.setMainMenu(false);
            game.setScreen(game.menuScreen);
        }

        if (!(indicatorsInfo.showing || indicatorsInfo.hiding || indicatorsInfo.bigWindow)) {
            acceptField.isClicked(touchX, touchY);
            refuseField.isClicked(touchX, touchY);
        }

        indicatorsInfo.isClicked(touchX, touchY);
    }

    void newGame() {
        game.questionsLoader.resetQuestions();
        activeQuestion = game.questionsLoader.getRandomQuestion();

        endReason = "";

        indicatorsInfo.money = 100000 - (25000 * (difficulty - 1));
        indicatorsInfo.score = 0;
        for (StatusBar indicator : indicatorsInfo.indicators.values()) {
            indicator.setValue(50);
        }
    }

    private void checkValues() {
        for (String key : indicatorsInfo.indicators.keySet()) {
            if (indicatorsInfo.indicators.get(key).getValue(false) == 0) {
                endReason = key;
            } else if (key.equals("population") && indicatorsInfo.indicators.get("population").getValue(false) == indicatorsInfo.indicators.get("population").getMaxValue()) {
                endReason = key + "_high";
            }
        }
        if (!endReason.equals("")) {
            game.setScreen(game.endScreen);
        }
    }

    Map<String, Integer> getResult() {
        return new TreeMap<String, Integer>() {{
            put("environment", (int) indicatorsInfo.indicators.get("environment").getValue(false));
            put("food", (int) indicatorsInfo.indicators.get("food").getValue(false));
            put("population", (int) indicatorsInfo.indicators.get("population").getValue(false));
            put("resources", (int) indicatorsInfo.indicators.get("resources").getValue(false));
            put("money", indicatorsInfo.money);
            put("score", indicatorsInfo.score);
        }};
    }
}