package com.hackheroes.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hackheroes.game.MainClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = 540;
		//config.height = 960;
		config.width = 936;
		config.height = 1664;
		new LwjglApplication(new MainClass(), config);
	}
}