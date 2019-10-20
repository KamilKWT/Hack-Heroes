package com.hackheroes.game.Tools;

import com.badlogic.gdx.Screen;
import com.hackheroes.game.MainClass;
import com.hackheroes.game.Screens.GameScreen;
import com.hackheroes.game.Screens.HelpScreen;
import com.hackheroes.game.Screens.MenuScreen;

import java.util.HashMap;

/**
 * Created by BlackBird on 10/20/2019.
 */

public class ScreenManager {
    private MainClass game;
    private HashMap<MainClass.ScreenType, Screen> screens = new HashMap<>();

    public ScreenManager(MainClass game) {
        this.game = game;
    }

    public Screen getScreen(MainClass.ScreenType type) {
        if (screens.containsKey(type)) return screens.get(type);
        else return createScreen(type);
    }

    private Screen createScreen(MainClass.ScreenType type) {
        Screen screen;

        if (type == MainClass.ScreenType.GAME)
            screen = new GameScreen(game);
        else if (type == MainClass.ScreenType.MENU)
            screen = new MenuScreen(game);
        else if (type == MainClass.ScreenType.HELP)
            screen = new HelpScreen(game);
        else screen = screens.get(type);

        screens.put(type, screen);
        return screen;
    }
}
