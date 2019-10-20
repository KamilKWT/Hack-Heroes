package com.hackheroes.game.Scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hackheroes.game.Components.StatusBar;
import com.hackheroes.game.MainClass;
import java.util.Map;
import java.util.TreeMap;

public class IndicatorsInfo {

    private class Background extends Actor {

        private int width, height;

        private Background(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw(Batch batch, float delta) {
            game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
            game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
            game.gameShapeRenderer.roundRect(xPos - 10, yPos - 10, width + 20, height + 20, 20);
            game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
            game.gameShapeRenderer.roundRect(xPos, yPos, width, height, 10);
            game.gameShapeRenderer.end();
        }
    }

    private class Button extends Actor {

        private int x, y, width, height;
        private Texture texture;

        private Button(int x, int y, int width, int height, Texture texture) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = texture;
        }

        @Override
        public void draw(Batch batch, float delta) {
            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameBatch.draw(texture, xPos + x - width / 2, yPos + y - height / 2, width, height);
            game.gameBatch.end();
        }

        void isClicked(int touchX, int touchY) {
            if (Math.sqrt(Math.pow((touchX - (xPos + x)), 2) + Math.pow((touchY - (yPos + y)), 2)) <= (width / 2)) {
                if (bigWindow && !hiding) {
                    hiding = true;
                    bigWindow = false;
                } else if (!bigWindow) {
                    setToBigWindow();
                    showing = true;
                }
            }
        }
    }

    private class SmallIndicatorLabel extends Actor {

        private int x, y;
        private Texture texture;
        private StatusBar statusBar;

        private SmallIndicatorLabel(int x, int y, Texture texture, StatusBar statusBar) {
            this.x = x;
            this.y = y;
            this.texture = texture;
            this.statusBar = statusBar;
        }

        @Override
        public void draw(Batch batch, float delta) {
            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameBatch.draw(texture, xPos + x, yPos + y + 15, 30, 30);
            game.gameBatch.end();

            statusBar.changeDimensions(xPos + x + 40, yPos + y, 20, 60, true);
            statusBar.render();
        }
    }

    private class BigIndicatorLabel extends Actor {

        private int x, y;
        private Texture texture;
        private StatusBar statusBar;
        private String description;

        private BigIndicatorLabel(int x, int y, Texture texture, StatusBar statusBar, String description) {
            this.x = x;
            this.y = y;
            this.texture = texture;
            this.statusBar = statusBar;
            this.description = description;
        }

        @Override
        public void draw(Batch batch, float delta) {
            game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
            game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.gameShapeRenderer.setColor(MainClass.GUI_MARKED_COLOR);
            game.gameShapeRenderer.roundRect(xPos + x - 20, yPos + y - 20, 440, 190, 20);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameBatch.draw(texture, xPos + x, yPos + y, 50, 50);
            game.gameFont.getData().setScale(1.0f);
            game.gameFont.draw(game.gameBatch, description, xPos + x, yPos + y + 150);
            game.gameBatch.end();

            statusBar.changeDimensions(xPos + x + 100, yPos + y, 300, 50, false);
            statusBar.render();
        }
    }

    private MainClass game;

    public Map<String, StatusBar> indicators = new TreeMap<>();
    public int money;
    private Stage stage;
    private Button button;
    private int xPos, yPos, width, height;
    public boolean bigWindow, hiding, showing = false;

    public IndicatorsInfo(MainClass game) {
        this.game = game;

        indicators.put("environment", new StatusBar(game, 50, 950, 20, 50, true, 100, 50));
        indicators.put("food", new StatusBar(game, 100, 950, 20, 50, true, 100, 50));
        indicators.put("population", new StatusBar(game, 150, 950, 20, 50, true, 100, 50));
        indicators.put("resources", new StatusBar(game, 200, 950, 20, 50, true, 100, 50));

        stage = new Stage(game.gameViewport);

        setToSmallWindow();
    }

    public void render(float delta) {
        if (hiding) {
            if (xPos > -510) {
                xPos -= 500f * delta;
            } else {
                hiding = false;
                setToSmallWindow();
            }
        } else if (showing) {
            if (xPos < (MainClass.V_WIDTH - width) / 2) {
                xPos += 500f * delta;
            } else {
                xPos = (MainClass.V_WIDTH - width) / 2;
                showing = false;
                bigWindow = true;
            }
        }

        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
        game.gameShapeRenderer.rect(420, 1210, 300, 70);
        game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
        game.gameShapeRenderer.rect(430, 1220, 300, 60);
        game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
        game.gameShapeRenderer.circle(420, 1245, 55);
        game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
        game.gameShapeRenderer.circle(420, 1245, 45);
        game.gameShapeRenderer.end();

        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
        game.gameBatch.begin();
        game.gameBatch.draw(game.assetsLoader.findTexture("money"), 395, 1220, 50, 50);
        game.gameFont.getData().setScale(0.75f);
        game.gameFont.draw(game.gameBatch, "" + money, 490, 1265);
        game.gameBatch.end();

        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    private void setToSmallWindow() {
        width = 90;
        height = 340;
        xPos = -10;
        yPos = (MainClass.V_HEIGHT - height) / 2;

        stage.clear();
        button = new Button(50, height + 40, 80, 80, game.assetsLoader.findTexture("rightBtn"));
        stage.addActor(button);
        stage.addActor(new Background(width, height));
        stage.addActor(new SmallIndicatorLabel(20, 260, game.assetsLoader.findTexture("environment"), indicators.get("environment")));
        stage.addActor(new SmallIndicatorLabel(20, 180, game.assetsLoader.findTexture("food"), indicators.get("food")));
        stage.addActor(new SmallIndicatorLabel(20, 100, game.assetsLoader.findTexture("population"), indicators.get("population")));
        stage.addActor(new SmallIndicatorLabel(20, 20, game.assetsLoader.findTexture("resources"), indicators.get("resources")));
    }

    private void setToBigWindow() {
        width = 500;
        height = 850;
        xPos = -510;
        yPos = (MainClass.V_HEIGHT - height) / 2;

        stage.clear();
        button = new Button(-40, height / 2, 80, 80, game.assetsLoader.findTexture("leftBtn"));
        stage.addActor(button);
        stage.addActor(new Background(width, height));
        stage.addActor(new BigIndicatorLabel(50, 650, game.assetsLoader.findTexture("environment"), indicators.get("environment"), "Środowisko"));
        stage.addActor(new BigIndicatorLabel(50, 450, game.assetsLoader.findTexture("food"), indicators.get("food"), "Pożywienie"));
        stage.addActor(new BigIndicatorLabel(50, 250, game.assetsLoader.findTexture("population"), indicators.get("population"), "Populacja"));
        stage.addActor(new BigIndicatorLabel(50, 50, game.assetsLoader.findTexture("resources"), indicators.get("resources"), "Surowce"));
    }

    public void isClicked(int touchX, int touchY) {
        button.isClicked(touchX, touchY);
    }
}