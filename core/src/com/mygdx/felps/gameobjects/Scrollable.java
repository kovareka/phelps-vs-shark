package com.mygdx.felps.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
    Vector2 position;
    Vector2 velocity;
    protected int width;
    protected int height;
    boolean isScrolledLeft;

    Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        if (velocity.x < -520) {
            velocity.x = -520;
        } else if (velocity.x > -30) {
            velocity.x = -30;
        }

        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    public void setStop(boolean stop) {
        if (stop) {
            velocity.x += 5;
        } else {
            velocity.x -= 20;
        }
    }

    void stop() {
        velocity.x = 0;
    }

    void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
