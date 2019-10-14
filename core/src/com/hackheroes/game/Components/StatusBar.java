package com.hackheroes.game.Components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackheroes.game.MainClass;

public class StatusBar {

    private MainClass game;

    private int xPos, yPos, width, height;
    private boolean vertical;
    private float maxValue, value, startValue, targetValue, radius, shadow;

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
        //border = (float) Math.ceil(0.1f * Math.min(width, height));
        radius = Math.min(width, height) / 2;
        shadow = (float) Math.ceil(0.2f * Math.min(width, height));
    }

    public void render() {
        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        game.gameShapeRenderer.setColor(Color.DARK_GRAY);
        game.gameShapeRenderer.roundRect(xPos, yPos, width, height, radius);

        if (vertical) {
            game.gameShapeRenderer.setColor(Color.GRAY);
            game.gameShapeRenderer.roundRect(xPos, yPos, width - shadow, height, (width - shadow) / 2);

            if (targetValue > value) {
                value = (targetValue - value >= 1f) ? (value + 1f) : (targetValue);
                startValue = (targetValue - value >= 1f) ? startValue : value;
                targetValue = (targetValue - value >= 1f) ? targetValue : value;

                game.gameShapeRenderer.setColor(new Color(0x008000ff));
                game.gameShapeRenderer.roundRect(xPos, yPos, width, height * (value / maxValue), radius);
                game.gameShapeRenderer.setColor(Color.GREEN);
                game.gameShapeRenderer.roundRect(xPos, yPos, width - shadow, height * (value / maxValue), (width - shadow) / 2);

                if (height * (startValue / maxValue) > 2 * radius) {
                    game.gameShapeRenderer.setColor(Color.LIGHT_GRAY);
                    game.gameShapeRenderer.roundRect(xPos, yPos, width, height * (startValue / maxValue), radius);
                    game.gameShapeRenderer.setColor(Color.WHITE);
                    game.gameShapeRenderer.roundRect(xPos, yPos, width - shadow, height * (startValue / maxValue), (width - shadow) / 2);
                }

            } else if (targetValue < value) {
                value = (value - targetValue >= 1f) ? (value - 1f) : (targetValue);
                startValue = (value - targetValue >= 1f) ? startValue : value;
                targetValue = (value - targetValue >= 1f) ? targetValue : value;

                game.gameShapeRenderer.setColor(new Color(0x800000ff));
                game.gameShapeRenderer.roundRect(xPos, yPos, width, height * (startValue / maxValue), radius);
                game.gameShapeRenderer.setColor(Color.RED);
                game.gameShapeRenderer.roundRect(xPos, yPos, width - shadow, height * (startValue / maxValue), (width - shadow) / 2);

                if (height * (value / maxValue) > 2 * radius) {
                    game.gameShapeRenderer.setColor(Color.LIGHT_GRAY);
                    game.gameShapeRenderer.roundRect(xPos, yPos, width, height * (value / maxValue), radius);
                    game.gameShapeRenderer.setColor(Color.WHITE);
                    game.gameShapeRenderer.roundRect(xPos, yPos, width - shadow, height * (value / maxValue), (width - shadow) / 2);
                }

            } else {
                game.gameShapeRenderer.setColor(Color.LIGHT_GRAY);
                game.gameShapeRenderer.roundRect(xPos, yPos, width, height * (value / maxValue), radius);
                game.gameShapeRenderer.setColor(Color.WHITE);
                game.gameShapeRenderer.roundRect(xPos, yPos, width - shadow, height * (value / maxValue), (width - shadow) / 2);
            }

        } else {
            game.gameShapeRenderer.setColor(Color.GRAY);
            game.gameShapeRenderer.roundRect(xPos, yPos + shadow, width, height - shadow, (height - shadow) / 2);

            if (targetValue > value) {
                value = (targetValue - value >= 1f) ? (value + 1f) : (targetValue);
                startValue = (targetValue - value >= 1f) ? startValue : value;
                targetValue = (targetValue - value >= 1f) ? targetValue : value;

                game.gameShapeRenderer.setColor(new Color(0x008000ff));
                game.gameShapeRenderer.roundRect(xPos, yPos, width * (value / maxValue), height, radius);
                game.gameShapeRenderer.setColor(Color.GREEN);
                game.gameShapeRenderer.roundRect(xPos, yPos + shadow, width * (value / maxValue), height -shadow, (height - shadow) / 2);

                if (width * (startValue / maxValue) > 2 * radius) {
                    game.gameShapeRenderer.setColor(Color.LIGHT_GRAY);
                    game.gameShapeRenderer.roundRect(xPos, yPos, width * (startValue / maxValue), height, radius);
                    game.gameShapeRenderer.setColor(Color.WHITE);
                    game.gameShapeRenderer.roundRect(xPos, yPos + shadow, width * (startValue / maxValue), height - shadow, (height - shadow) / 2);
                }

            } else if (targetValue < value) {
                value = (value - targetValue >= 1f) ? (value - 1f) : (targetValue);
                startValue = (value - targetValue >= 1f) ? startValue : value;
                targetValue = (value - targetValue >= 1f) ? targetValue : value;

                game.gameShapeRenderer.setColor(new Color(0x800000ff));
                game.gameShapeRenderer.roundRect(xPos, yPos, width * (startValue / maxValue), height, radius);
                game.gameShapeRenderer.setColor(Color.RED);
                game.gameShapeRenderer.roundRect(xPos, yPos + shadow, width * (startValue / maxValue), height - shadow, (height - shadow) / 2);

                if (width * (value / maxValue) > 2 * radius) {
                    game.gameShapeRenderer.setColor(Color.LIGHT_GRAY);
                    game.gameShapeRenderer.roundRect(xPos, yPos, width * (value / maxValue), height, radius);
                    game.gameShapeRenderer.setColor(Color.WHITE);
                    game.gameShapeRenderer.roundRect(xPos, yPos + shadow, width * (value / maxValue), height -shadow, (height - shadow) / 2);
                }

            } else {
                game.gameShapeRenderer.setColor(Color.LIGHT_GRAY);
                game.gameShapeRenderer.roundRect(xPos, yPos, width * (value / maxValue), height, radius);
                game.gameShapeRenderer.setColor(Color.WHITE);
                game.gameShapeRenderer.roundRect(xPos, yPos + shadow, width * (value / maxValue), height - shadow, (height - shadow) / 2);
            }
        }

        game.gameShapeRenderer.end();
    }

    public void changeDimensions(int xPos, int yPos, int width, int height, boolean vertical) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.vertical = vertical;
        //border = (float) Math.ceil(0.1f * Math.min(width, height));
        radius = Math.min(width, height) / 2;
        shadow = (int) Math.ceil(0.2f * Math.min(width, height));
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

        if (targetValue + amount < 0) targetValue = 0;
        else if (targetValue + amount > maxValue) targetValue = maxValue;
        else targetValue += amount;
    }

    public float getValue() {
        return value;
    }
}