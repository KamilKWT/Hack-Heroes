package com.hackheroes.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.Screens.EndScreen;
import com.hackheroes.game.Screens.GameScreen;
import com.hackheroes.game.Screens.HelpScreen;
import com.hackheroes.game.Screens.HighscoresScreen;
import com.hackheroes.game.Screens.MenuScreen;
import com.hackheroes.game.Screens.ResultsScreen;

public class InputController implements InputProcessor {

    private MainClass game;

    public InputController(MainClass game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        /*if (keycode == 19) {
            for (String key : game.gameScreen.indicatorsInfo.indicators.keySet()) {
                Gdx.app.log("" + key, "" + game.gameScreen.indicatorsInfo.indicators.get(key).getValue());
            }
            Gdx.app.log("---------", "-------------------");
        }*/
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int touchX = (int) (screenX * ((float) MainClass.V_WIDTH / (float) Gdx.graphics.getWidth()));
        int touchY = (int) (MainClass.V_HEIGHT - (screenY * ((float) MainClass.V_HEIGHT / (float) Gdx.graphics.getHeight())));

        if (game.getScreen() instanceof MenuScreen) {
            game.menuScreen.isClicked(touchX, touchY);

        } else if (game.getScreen() instanceof HelpScreen) {
            game.helpScreen.isClicked(touchX, touchY);

        } else if (game.getScreen() instanceof GameScreen) {
            game.gameScreen.isClicked(touchX, touchY);

        } else if (game.getScreen() instanceof EndScreen) {
            game.endScreen.isClicked(touchX, touchY);

        } else if (game.getScreen() instanceof ResultsScreen) {
            game.resultsScreen.isClicked(touchX, touchY);

        } else if (game.getScreen() instanceof HighscoresScreen) {
            game.highscoresScreen.isClicked(touchX, touchY);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}