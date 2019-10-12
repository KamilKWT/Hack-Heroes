package com.hackheroes.game.UI_Elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackheroes.game.MainClass;

public class StatusBar {

    private MainClass game;

    private boolean vertical;
    private int xPos, yPos, width, height;
    private float maxValue, value, startValue, targetValue, border;

    public StatusBar(MainClass game, int xPos, int yPos, int width, int height, boolean vertical, float maxValue, float value) {
        this.game = game;

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.vertical = vertical;
        this.border = (float) Math.ceil(0.1f * Math.min(width, height));

        this.maxValue = maxValue;
        this.value = value;
        this.startValue = value;
        this.targetValue = value;
    }

    public void render() {
        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Obwódka
        game.gameShapeRenderer.setColor(Color.DARK_GRAY);
        game.gameShapeRenderer.rect(xPos, yPos, width, height);
        //Tło
        game.gameShapeRenderer.setColor(Color.GRAY);
        game.gameShapeRenderer.rect(xPos + border, yPos + border, width - 2 * border, height - 2 * border);

        this.drawStatusBar();
        game.gameShapeRenderer.end();
    }

    private class Rect {
        public final float x, y, dx, dy;

        public Rect(float x, float y, float dx, float dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }

    private void drawStatusBar() {
        final ShapeRenderer sr = game.gameShapeRenderer;
        final byte step = (this.targetValue > this.value) ? (byte) 1 : -1;
        final Rect valueRect = getValueRect(step);
        final Rect startingRect = getStartingRect();

        sr.setColor(Color.WHITE);
        sr.rect(startingRect.x, startingRect.y, startingRect.dx, startingRect.dy);
        if (this.targetValue == this.value) {
            this.value = this.targetValue;
            this.startValue = this.targetValue;
        } else {
            this.value += step;
            if (step == 1) sr.setColor(Color.GREEN);
            else sr.setColor(Color.RED);
            sr.rect(valueRect.x, valueRect.y, valueRect.dx, valueRect.dy);
        }

    }

    // Zwraca prostokat uzywany do pokazywania ile ubylo lub przybylo do poczatkowej wartosci
    private Rect getValueRect(final byte step) {
        final float x, y, width, height;

        if (step == 1) {
            if (this.vertical) {
                x = this.xPos + this.border;
                y = this.yPos + ((this.height - 2 * this.border) * (this.startValue / this.maxValue)) + this.border;
                width = this.width - 2 * this.border;
                height = ((this.height - 2 * this.border) * (this.value - this.startValue) / this.maxValue);
            } else {
                x = (this.xPos + ((this.width - 2 * this.border) * (this.startValue / this.maxValue))) + this.border;
                y = this.yPos + border;
                width = ((this.width - 2 * border) * ((this.value - this.startValue) / this.maxValue));
                height = this.height - 2 * this.border;
            }
        } else {
            if (this.vertical) {
                x = this.xPos + this.border;
                y = (this.yPos + ((this.height - 2 * this.border) * (this.startValue / this.maxValue)) - ((this.height - 2 * this.border) * ((this.startValue - this.value) / this.maxValue))) + this.border;
                width = this.width - 2 * this.border;
                height = (this.height - 2 * this.border) * ((this.startValue - this.value) / this.maxValue);
            } else {
                x = ((this.xPos + ((this.width - 2 * this.border) * (this.startValue / this.maxValue))) - ((this.width - 2 * this.border) * ((this.startValue - this.value) / this.maxValue))) + this.border;
                y = this.yPos + border;
                width = ((this.width - 2 * border) * ((this.startValue - this.value) / this.maxValue));
                height = this.height - 2 * this.border;
            }
        }
        return new Rect(x, y, width, height);
    }

    // Zwraca bialy prostokat czyli aktualna wartosc przed zmiana
    private Rect getStartingRect() {
        final float x = this.xPos + this.border,
                y = this.yPos + this.border,
                width, height;

        if (this.vertical) {
            width = this.width - 2 * this.border;
            height = (this.height - 2 * this.border) * (this.startValue / this.maxValue);
        } else {
            width = (this.width - 2 * this.border) * (this.startValue / this.maxValue);
            height = this.height - 2 * this.border;
        }
        return new Rect(x, y, width, height);
    }


    public void setValue(float amount) {
        if (amount < 0) amount = 0;
        else if (amount > this.maxValue) amount = this.maxValue;

        this.value = amount;
        this.startValue = amount;
        this.targetValue = amount;
    }

    public void addValue(float amount) {
        if (this.value + amount < 0) amount -= this.value - Math.abs(amount);
        else if (this.value + amount > this.maxValue) amount = this.maxValue - this.value;
        this.targetValue = this.value + amount;
    }

    public float getValue() {
        return value;
    }
}