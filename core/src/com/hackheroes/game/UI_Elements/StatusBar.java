package com.hackheroes.game.UI_Elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackheroes.game.MainClass;

public class StatusBar {

    private MainClass game;

    private int xPos, yPos, width, height;
    private boolean vertical;
    private float maxValue, value, startValue, targetValue, border;

    public StatusBar(MainClass game, int xPos, int yPos, int width, int height, boolean vertical, float maxValue, float value) {
        this.game = game;

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.vertical = vertical;
        this.maxValue = maxValue;
        this.value = value;
        startValue = value;
        targetValue = value;
        border = (float) Math.ceil(0.05f * Math.min(width, height));
    }

    public void render() {
        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        game.gameShapeRenderer.setColor(Color.DARK_GRAY);
        game.gameShapeRenderer.rect(xPos, yPos, width, height);

        game.gameShapeRenderer.setColor(Color.GRAY);
        game.gameShapeRenderer.rect(xPos + border, yPos + border, width - 2 * border, height - 2 * border);

        if (targetValue > value) {
            value = (targetValue - value >= 1f) ? (value + 1f) : (targetValue);
            startValue = (targetValue - value >= 1f) ? startValue : value;
            targetValue = (targetValue - value >= 1f) ? targetValue : value;

            game.gameShapeRenderer.setColor(Color.WHITE);
            game.gameShapeRenderer.rect(xPos + border, yPos + border,
                    (vertical) ? (width - 2 * border) : ((width - 2 * border) * (startValue / maxValue)),
                    (vertical) ? ((height - 2 * border) * (startValue / maxValue)) : (height - 2 * border));

            game.gameShapeRenderer.setColor(Color.GREEN);
            game.gameShapeRenderer.rect(
                    ((vertical) ? (xPos) : (xPos + ((width - 2 * border) * (startValue / maxValue)))) + border,
                    ((vertical) ? (yPos + ((height - 2 * border) * (startValue / maxValue))) : (yPos)) + border,
                    (vertical) ? (width - 2 * border) : ((width - 2 * border) * ((value - startValue) / maxValue)),
                    (vertical) ? ((height - 2 * border) * ((value - startValue) / maxValue)) : (height - 2 * border)
            );

        } else if (targetValue < value) {
            value = (value - targetValue >= 1f) ? (value - 1f) : (targetValue);
            startValue = (value - targetValue >= 1f) ? startValue : value;
            targetValue = (value - targetValue >= 1f) ? targetValue : value;

            game.gameShapeRenderer.setColor(Color.WHITE);
            game.gameShapeRenderer.rect(xPos + border, yPos + border,
                    (vertical) ? (width - 2 * border) : ((width - 2 * border) * (startValue / maxValue)),
                    (vertical) ? ((height - 2 * border) * (startValue / maxValue)) : (height - 2 * border));

            game.gameShapeRenderer.setColor(Color.RED);
            game.gameShapeRenderer.rect(
                    ((vertical) ? (xPos) : ((xPos + ((width - 2 * border) * (startValue / maxValue))) - ((width - 2 * border) * ((startValue - value) / maxValue)))) + border,
                    ((vertical) ? ((yPos + ((height - 2 * border) * (startValue / maxValue))) - ((height - 2 * border) * ((startValue - value) / maxValue))) : (yPos)) + border,
                    (vertical) ? (width - 2 * border) : ((width - 2 * border) * ((startValue - value) / maxValue)),
                    (vertical) ? ((height - 2 * border) * ((startValue - value) / maxValue)) : (height - 2 * border)
            );

        } else {
            game.gameShapeRenderer.setColor(Color.WHITE);
            game.gameShapeRenderer.rect(xPos + border, yPos + border,
                    (vertical) ? (width - 2 * border) : ((width - 2 * border) * (value / maxValue)),
                    (vertical) ? ((height - 2 * border) * (value / maxValue)) : (height - 2 * border));
        }

        game.gameShapeRenderer.end();
    }

    public void setValue(float value) {
        if (value < 0) value = 0;
        else if (value > maxValue) value = maxValue;

        this.value = value;
        this.startValue = value;
        this.targetValue = value;
    }

    public void changeBy(float amount) {
        startValue = value;

        if (value + amount < 0) targetValue = 0;
        else if (value + amount > maxValue) targetValue = maxValue;
        else targetValue = value + amount;
    }

    public float getValue() {
        return value;
    }
}