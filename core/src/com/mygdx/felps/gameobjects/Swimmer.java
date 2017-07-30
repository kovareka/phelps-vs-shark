package com.mygdx.felps.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Swimmer {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private boolean isAlive;
    private boolean isDive;

    private int width;
    private int height;

    private Rectangle rectangle;

    public Swimmer(float x, float y, int width, int height) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 140);
        this.acceleration = new Vector2(0, 230);
        this.width = width;
        this.height = height;
        this.isAlive = true;
        this.isDive = false;
        this.rectangle = new Rectangle();
    }

    public void update(float delta){
        if (!isDive) {
            velocity.add(acceleration.cpy().scl(delta));

            if (velocity.y > 200) {
                velocity.y = 200;
            }
        } else {
            if (position.y >= 360) {
                velocity.y = -220;
            }

            velocity.add(acceleration.cpy().scl(delta));
        }

        position.add(velocity.cpy().scl(delta));

        if (position.y <= 260) {
            position.y = 260;
            isDive = false;
        }

        if (position.y >= 450) stop();

        rectangle.set(position.x + 2, position.y + 34, 220, 84);
    }

    public void onRestart() {
        position.x = 273;
        position.y = 260;
        velocity.y = 140;
        acceleration.y = 230;
        isAlive = true;
        isDive = false;
    }

    public void stop() {
        velocity.y = 0;
        acceleration.y = 0;
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void onClick() {
        velocity.y = -140;
    }

    public void dive() {
        velocity.y = 140;
        isDive = true;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean isDive() {
        return isDive;
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

    Rectangle getRectangle() {
        return rectangle;
    }
}
