package com.mygdx.felps.gameobjects;

import java.util.Random;

public class Clouds extends Scrollable {
    private int texture;

    Clouds(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed / 2.1f);
        setRandomTexture();
    }

    @Override
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    void setRandomTexture() {
        Random r = new Random();
        texture = r.nextInt(4) + 1;
    }

    public int getTexture() {
        return texture;
    }
}
