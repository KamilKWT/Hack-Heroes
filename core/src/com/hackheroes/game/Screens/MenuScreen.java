package com.hackheroes.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.hackheroes.game.MainClass;

public class MenuScreen extends AbstractScreen {

    private class Button {

        private int x = 0, y = 0, width, height;
        private String text;

        private Button(int width, int height, String text) {
            this.width = width;
            this.height = height;
            this.text = text;
        }

        void draw() {
            game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
            game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
            game.gameShapeRenderer.roundRect(x, y, width, height, 20);
            game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
            game.gameShapeRenderer.roundRect(x + 10, y + 10, width - 20, height - 20, 10);
            game.gameShapeRenderer.end();

            game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
            game.gameBatch.begin();
            game.gameFont.getData().setScale((0.5f * (height - 20f)) / 48f);
            game.gameFont.draw(game.gameBatch, text, x + 10, y + (height + (0.5f * (height - 20f))) / 2, width - 20, 1, false);
            game.gameBatch.end();
        }

        void moveTo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void setText(String text) {
            this.text = text;
        }

        int getWidth() {
            return width;
        }

        int getHeight() {
            return height;
        }

        void isClicked(int touchX, int touchY) {
            if (touchX >= x && touchX <= x + width && touchY >= y && touchY <= y + height) {
                action();
            }
        }

        void action() {}
    }

    private MainClass game;

    private  boolean mainMenu;
    private final Array<Button> mainMenuButtons = new Array<>();
    private final Array<Button> gameMenuButtons = new Array<>();
    private final Array<Button> difficultyButtons = new Array<>();
    private  Array<Button> activeButtons = new Array<>();


    public MenuScreen(MainClass game) {
        this.game = game;
        mainMenu = true;

        createButtons();
    }

    @Override
    public void show() {
        activeButtons = mainMenu ? mainMenuButtons : gameMenuButtons;
    }

    @Override
    public void render(float delta) {
        for (Button button : activeButtons) {
            button.moveTo((MainClass.V_WIDTH - button.getWidth()) / 2, ((MainClass.V_HEIGHT + (totalButtonsHeight(activeButtons.size - 1) + ((activeButtons.size - 1) * 50))) / 2) - totalButtonsHeight(activeButtons.indexOf(button, true)) - (activeButtons.indexOf(button, true) * 50));
            button.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void dispose() {

    }

    public void setMainMenu(boolean mainMenu) {
        this.mainMenu = mainMenu;
    }

    private int totalButtonsHeight(int last) {
        int tmp = 0;

        for (int i = 0; i <= last; i++) {
            tmp += activeButtons.get(i).getHeight();
        }

        return tmp;
    }

    private void createButtons() {
        mainMenuButtons.add(new Button(350, 100, "Start gry") {
            @Override
            void action() {
                activeButtons = difficultyButtons;
            }
        });

        mainMenuButtons.add(new Button(350, 100, "Pomoc") {
            @Override
            void action() {

            }
        });

        mainMenuButtons.add(new Button(350, 100, "Wyjście") {
            @Override
            void action() {
                Gdx.app.exit();
            }
        });

        gameMenuButtons.add(new Button(350, 100, "Wznów") {
            @Override
            void action() {
                game.setScreen(game.gameScreen);
            }
        });

        gameMenuButtons.add(new Button(350, 100, "Od nowa") {
            @Override
            void action() {
                game.gameScreen.newGame();
                game.setScreen(game.gameScreen);
            }
        });

        gameMenuButtons.add(new Button(350, 100, "Menu główne") {
            @Override
            void action() {
                setMainMenu(true);
                game.setScreen(game.menuScreen);
            }
        });

        difficultyButtons.add(new Button(350, 100, "Łatwy") {
            @Override
            void action() {
                game.gameScreen.difficulty = MainClass.EASY;
                game.gameScreen.newGame();
                game.setScreen(game.gameScreen);
            }
        });

        difficultyButtons.add(new Button(350, 100, "Średni") {
            @Override
            void action() {
                game.gameScreen.difficulty = MainClass.MEDIUM;
                game.gameScreen.newGame();
                game.setScreen(game.gameScreen);
            }
        });

        difficultyButtons.add(new Button(350, 100, "Trudny") {
            @Override
            void action() {
                game.gameScreen.difficulty = MainClass.HARD;
                game.gameScreen.newGame();
                game.setScreen(game.gameScreen);
            }
        });
    }

    public void isClicked(int touchX, int touchY) {
        for (Button button : activeButtons) {
            button.isClicked(touchX, touchY);
        }
    }
}