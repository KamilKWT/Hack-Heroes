package com.hackheroes.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hackheroes.game.Screens.GameScreen;
import com.hackheroes.game.Screens.MenuScreen;
import com.hackheroes.game.Tools.AssetsLoader;
import com.hackheroes.game.Tools.InputController;
import com.hackheroes.game.Tools.MyShapeRenderer;
import com.hackheroes.game.Tools.QuestionsLoader;

public class MainClass extends Game {

    public static final int V_WIDTH = 720;
    public static final int V_HEIGHT = 1280;
    public static final Color GAME_BACKGROUND_COLOR = new Color(0x545657ff);
    public static final Color GUI_BORDER_COLOR = new Color(0x282828ff);
    public static final Color GUI_MARKED_COLOR = new Color(0x313335ff);
    public static final Color GUI_BACKGROUND_COLOR = new Color(0x3c3f41ff);
    public static final Color GUI_FONT_COLOR = Color.WHITE;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;
    public static final int MEDIUM_EFFECT_THRESHOLD = 15;
    public static final int LARGE_EFFECT_THRESHOLD = 30;

    public AssetsLoader assetsLoader;
    public QuestionsLoader questionsLoader;

    public OrthographicCamera gameCamera;
    public Viewport gameViewport;
    public SpriteBatch gameBatch;
    public MyShapeRenderer gameShapeRenderer;
    public BitmapFont gameFont;

    public MenuScreen menuScreen;
    public GameScreen gameScreen;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(new InputController(this));
        assetsLoader = AssetsLoader.loadAssets();
        questionsLoader = new QuestionsLoader();

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
        gameViewport = new FitViewport(V_WIDTH, V_HEIGHT, gameCamera);
        gameBatch = new SpriteBatch();
        gameShapeRenderer = new MyShapeRenderer();
        gameFont = new BitmapFont(Gdx.files.internal("fonts/Arial.fnt"));
        gameFont.setColor(GUI_FONT_COLOR);

        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);

        //this.setScreen(gameScreen);
        this.setScreen(menuScreen);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameShapeRenderer.setProjectionMatrix(gameCamera.combined);
        gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        gameShapeRenderer.setColor(GAME_BACKGROUND_COLOR);
        gameShapeRenderer.rect(0, 0, V_WIDTH, V_HEIGHT);
        gameShapeRenderer.end();

        super.render();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void dispose() {
        gameBatch.dispose();
        gameShapeRenderer.dispose();
        gameFont.dispose();
    }
}