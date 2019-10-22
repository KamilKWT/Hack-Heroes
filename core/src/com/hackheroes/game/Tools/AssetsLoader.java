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
        assetsLoader.addTexture("menuBtn", new Texture(Gdx.files.internal("images/btn/menuBtn.png")));
        assetsLoader.addTexture("leftBtn", new Texture(Gdx.files.internal("images/btn/leftBtn.png")));
        assetsLoader.addTexture("rightBtn", new Texture(Gdx.files.internal("images/btn/rightBtn.png")));

        assetsLoader.addTexture("dot-empty", new Texture(Gdx.files.internal("images/elements/dot-empty.png")));
        assetsLoader.addTexture("dot-full", new Texture(Gdx.files.internal("images/elements/dot-full.png")));
        assetsLoader.addTexture("minus-empty", new Texture(Gdx.files.internal("images/elements/minus-empty.png")));
        assetsLoader.addTexture("minus-full", new Texture(Gdx.files.internal("images/elements/minus-full.png")));
        assetsLoader.addTexture("plus-empty", new Texture(Gdx.files.internal("images/elements/plus-empty.png")));
        assetsLoader.addTexture("plus-full", new Texture(Gdx.files.internal("images/elements/plus-full.png")));

        assetsLoader.addTexture("page1", new Texture(Gdx.files.internal("images/help/page1.png")));
        assetsLoader.addTexture("page2", new Texture(Gdx.files.internal("images/help/page2.png")));
        assetsLoader.addTexture("page3", new Texture(Gdx.files.internal("images/help/page3.png")));
        assetsLoader.addTexture("page4", new Texture(Gdx.files.internal("images/help/page4.png")));
        assetsLoader.addTexture("page5", new Texture(Gdx.files.internal("images/help/page5.png")));
        assetsLoader.addTexture("page6", new Texture(Gdx.files.internal("images/help/page6.png")));
        assetsLoader.addTexture("page7", new Texture(Gdx.files.internal("images/help/page7.png")));
        assetsLoader.addTexture("page8", new Texture(Gdx.files.internal("images/help/page8.png")));
        assetsLoader.addTexture("page9", new Texture(Gdx.files.internal("images/help/page9.png")));
        assetsLoader.addTexture("page10", new Texture(Gdx.files.internal("images/help/page10.png")));

        assetsLoader.addTexture("environment", new Texture(Gdx.files.internal("images/indicators/environment.png")));
        assetsLoader.addTexture("food", new Texture(Gdx.files.internal("images/indicators/food.png")));
        assetsLoader.addTexture("money", new Texture(Gdx.files.internal("images/indicators/money.png")));
        assetsLoader.addTexture("population", new Texture(Gdx.files.internal("images/indicators/population.png")));
        assetsLoader.addTexture("resources", new Texture(Gdx.files.internal("images/indicators/resources.png")));
    }

    private void addTexture(String name, Texture texture) {
        this.textures.put(name, texture);
    }

    public Texture findTexture(String name) {
        return textures.get(name);
    }
}