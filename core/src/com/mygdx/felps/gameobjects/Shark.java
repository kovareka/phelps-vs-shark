package com.mygdx.felps.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shark {
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;

    private Rectangle rectangle;

    public Shark(float x, float y, int width, int height) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(30, 0);
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle();
    }

    public void update(float delta){
        if (velocity.x < -60) {
            velocity.x = -60;
        }

        position.add(velocity.cpy().scl(delta));
        rectangle.set(position.x, position.y, 300, 180);
    }

    public void onRestart() {
        velocity.x = 30;
        position.x = -600;
    }

    public void move(boolean move) {
        if (move) {
            velocity.x += 20; // 20
        } else {
            velocity.x -= 150;
        }
    }

    public void slow() {
        velocity.x -= 15;
    }

    public void stop() {
        velocity.x = 0;
    }

    public boolean collides(Swimmer swimmer) {
        return position.x + width > swimmer.getX() && Intersector.overlaps(swimmer.getRectangle(), rectangle);
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
