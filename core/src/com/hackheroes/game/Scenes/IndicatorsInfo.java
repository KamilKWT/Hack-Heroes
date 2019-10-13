package com.hackheroes.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hackheroes.game.Components.StatusBar;
import com.hackheroes.game.MainClass;

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
            game.gameShapeRenderer.setColor(Color.BROWN);
            game.gameShapeRenderer.roundRect(xPos - 10, yPos - 10, width + 20, height + 20, 20);
            game.gameShapeRenderer.setColor(Color.GOLD);
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
            game.gameBatch.draw(texture, xPos + x - width / 2, yPos + y - height / 2, 80, 80);
            game.gameBatch.end();
        }

        public void isClicked(int touchX, int touchY) {
            Gdx.app.log("" + touchX, "" + touchY);
            Gdx.app.log("" + x, "" + y);
            if (distance(xPos + x, yPos + y, touchX, touchY) <= (width / 2)) {
                if (bigWindow && !hiding) {
                    hiding = true;
                } else if (!bigWindow) {
                    setToBigWindow();
                }
            }
        }

        private int distance(int x1, int y1, int x2, int y2) {
            return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
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
            game.gameBatch.draw(texture, xPos + x, yPos + y + 5, 30, 30);
            game.gameBatch.end();

            statusBar.changeDimensions(xPos + x + 40, yPos + y, 20, 40, true);
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
            game.gameShapeRenderer.setColor(Color.ORANGE);
            game.gameShapeRenderer.roundRect(xPos + x - 20, yPos + y - 20, 440, 190, 20);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameBatch.draw(texture, xPos + x, yPos + y, 50, 50);
            font.draw(game.gameBatch, description, xPos + x, yPos + y + 150);
            game.gameBatch.end();

            statusBar.changeDimensions(xPos + x + 100, yPos + y, 300, 50, false);
            statusBar.render();
        }
    }

    private MainClass game;

    public StatusBar environmentIndicator, foodIndicator, populationIndicator, resourcesIndicator;
    private BitmapFont font;
    private Stage stage;
    private Button button;
    private int xPos, yPos, width, height;
    private boolean bigWindow, hiding = false;

    public IndicatorsInfo(MainClass game) {
        this.game = game;

        environmentIndicator = new StatusBar(game, 50, 950, 20, 50, true, 100, 0);
        foodIndicator = new StatusBar(game, 100, 950, 20, 50, true, 100, 0);
        populationIndicator = new StatusBar(game, 150, 950, 20, 50, true, 100, 0);
        resourcesIndicator = new StatusBar(game, 200, 950, 20, 50, true, 100, 0);

        font = new BitmapFont(Gdx.files.internal("fonts/Arial.fnt"));
        font.setColor(Color.BLACK);

        stage = new Stage(game.gameViewport);

        environmentIndicator.changeBy(100);
        foodIndicator.changeBy(100);
        populationIndicator.changeBy(100);
        resourcesIndicator.changeBy(100);

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
        } else if (bigWindow) {
            if (xPos < (MainClass.V_WIDTH - width) / 2) {
                xPos += 500f * delta;
            } else {
                xPos = (MainClass.V_WIDTH - width) / 2;
            }
        }

        stage.draw();
    }

    public void dispose() {
        font.dispose();
        stage.dispose();
    }

    private void setToSmallWindow() {
        bigWindow = false;
        width = 90;
        height = 260;
        xPos = -10;
        yPos = (MainClass.V_HEIGHT - height) / 2;

        stage.clear();
        button = new Button(50, height + 40, 80, 80, game.assetsLoader.findTexture("rightBtn"));
        stage.addActor(button);
        stage.addActor(new Background(width, height));
        stage.addActor(new SmallIndicatorLabel(20, 200, game.assetsLoader.findTexture("environment"), environmentIndicator));
        stage.addActor(new SmallIndicatorLabel(20, 140, game.assetsLoader.findTexture("food"), foodIndicator));
        stage.addActor(new SmallIndicatorLabel(20, 80, game.assetsLoader.findTexture("population"), populationIndicator));
        stage.addActor(new SmallIndicatorLabel(20, 20, game.assetsLoader.findTexture("resources"), resourcesIndicator));
    }

    private void setToBigWindow() {
        bigWindow = true;
        width = 500;
        height = 850;
        xPos = -510;
        yPos = (MainClass.V_HEIGHT - height) / 2;

        stage.clear();
        button = new Button(-40, height / 2, 80, 80, game.assetsLoader.findTexture("leftBtn"));
        stage.addActor(button);
        stage.addActor(new Background(width, height));
        stage.addActor(new BigIndicatorLabel(50, 650, game.assetsLoader.findTexture("environment"), environmentIndicator, "Środowisko"));
        stage.addActor(new BigIndicatorLabel(50, 450, game.assetsLoader.findTexture("food"), foodIndicator, "Pożywienie"));
        stage.addActor(new BigIndicatorLabel(50, 250, game.assetsLoader.findTexture("population"), populationIndicator, "Populacja"));
        stage.addActor(new BigIndicatorLabel(50, 50, game.assetsLoader.findTexture("resources"), resourcesIndicator, "Surowce"));
    }

    public void isClicked(int touchX, int touchY) {
        button.isClicked(touchX, touchY);
    }
}