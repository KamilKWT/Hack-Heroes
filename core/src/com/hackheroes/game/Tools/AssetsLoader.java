package com.hackheroes.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class AssetsLoader {

    private Map<String, Texture> textures = new HashMap<>();

    public static AssetsLoader loadAssets() {
        AssetsLoader assetsLoader = new AssetsLoader();

        loadTextures(assetsLoader);

        return assetsLoader;
    }

    private static void loadTextures(AssetsLoader assetsLoader) {
        assetsLoader.addTexture("badlogic", new Texture(Gdx.files.internal("images/badlogic.jpg")));
        assetsLoader.addTexture("environment", new Texture(Gdx.files.internal("images/environment.png")));
        assetsLoader.addTexture("food", new Texture(Gdx.files.internal("images/food.png")));
        assetsLoader.addTexture("money", new Texture(Gdx.files.internal("images/money.png")));
        assetsLoader.addTexture("population", new Texture(Gdx.files.internal("images/population.png")));
        assetsLoader.addTexture("resources", new Texture(Gdx.files.internal("images/resources.png")));
        assetsLoader.addTexture("leftBtn", new Texture(Gdx.files.internal("images/leftBtn.png")));
        assetsLoader.addTexture("rightBtn", new Texture(Gdx.files.internal("images/rightBtn.png")));
        assetsLoader.addTexture("dot-empty", new Texture(Gdx.files.internal("images/dot-empty.png")));
        assetsLoader.addTexture("dot-full", new Texture(Gdx.files.internal("images/dot-full.png")));
        assetsLoader.addTexture("minus-empty", new Texture(Gdx.files.internal("images/minus-empty.png")));
        assetsLoader.addTexture("minus-full", new Texture(Gdx.files.internal("images/minus-full.png")));
        assetsLoader.addTexture("plus-empty", new Texture(Gdx.files.internal("images/plus-empty.png")));
        assetsLoader.addTexture("plus-full", new Texture(Gdx.files.internal("images/plus-full.png")));
    }

    private void addTexture(String name, Texture texture) {
        this.textures.put(name, texture);
    }

    public Texture findTexture(String name) {
        return textures.get(name);
    }
}