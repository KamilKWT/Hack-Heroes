package com.hackheroes.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hackheroes.game.Screens.GameScreen;
import com.hackheroes.game.Tools.AssetsLoader;
import com.hackheroes.game.Tools.InputController;
import com.hackheroes.game.Tools.MyShapeRenderer;

public class MainClass extends Game {

	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 1280;

	public AssetsLoader assetsLoader;

	public OrthographicCamera gameCamera;
	public Viewport gameViewport;
	public SpriteBatch gameBatch;
	public MyShapeRenderer gameShapeRenderer;

	public GameScreen gameScreen;

	@Override
	public void create () {
		Gdx.input.setInputProcessor(new InputController(this));
		assetsLoader = AssetsLoader.loadAssets();

		gameCamera = new OrthographicCamera();
		gameCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gameViewport = new FitViewport(V_WIDTH, V_HEIGHT, gameCamera);
		gameBatch = new SpriteBatch();
		gameShapeRenderer = new MyShapeRenderer();

		gameScreen = new GameScreen(this);
		this.setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameViewport.update(width, height);
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		gameShapeRenderer.dispose();
	}
}