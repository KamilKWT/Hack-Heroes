package com.hackheroes.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hackheroes.game.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Save Your World";
		config.width = 450;
		config.height = 800;
		config.resizable = false;
		new LwjglApplication(new MainClass(), config);
	}
}