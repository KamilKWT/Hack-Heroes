package com.hackheroes.game.Tools;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyShapeRenderer extends ShapeRenderer {

    public MyShapeRenderer() {
        super();
    }

    public void roundRect(float x, float y, float width, float height, float radius) {
        if (width > 0 && height > 0) {
            if (width > 2 * radius && height > 2 * radius) {
                this.rect(x + radius, y, width - 2 * radius, height);
                this.rect(x, y + radius, width, height - 2 * radius);
                this.circle(x + radius, y + radius, radius);
                this.circle(x + radius, y + height - radius, radius);
                this.circle(x + width - radius, y + radius, radius);
                this.circle(x + width - radius, y + height - radius, radius);

            } else if (width == 2 * radius && height > 2 * radius) {
                this.rect(x, y + radius, width, height - 2 * radius);
                this.circle(x + radius, y + radius, radius);
                this.circle(x + radius, y + height - radius, radius);

            } else if (width > 2 * radius && height == 2 * radius) {
                this.rect(x + radius, y, width - 2 * radius, height);
                this.circle(x + radius, y + radius, radius);
                this.circle(x + width - radius, y + radius, radius);

            } else {
                this.circle(x + radius, y + radius, radius);
            }
        }
    }
}