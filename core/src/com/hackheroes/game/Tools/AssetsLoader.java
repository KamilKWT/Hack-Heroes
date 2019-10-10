package com.hackheroes.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class AssetsLoader {

    public Map<String, Texture> textures = new HashMap<>();

    public static AssetsLoader loadAssets() {
        AssetsLoader assetsLoader = new AssetsLoader();

        loadTextures(assetsLoader);

        return assetsLoader;
    }

    private static void loadTextures(AssetsLoader assetsLoader) {
        assetsLoader.addTexture("badlogic", new Texture(Gdx.files.internal("images/badlogic.jpg")));
    }

    private void addTexture(String name, Texture texture) {
        this.textures.put(name, texture);
    }

    public Texture findTexture(String name) {
        return textures.get(name);
    }
}