package com.mygdx.felps.gameobjects;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Bouy extends Scrollable {
    private float rotation;
    private boolean right;
    private Circle circle;

    Bouy(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        this.rotation = -1;
        this.right = true;
        this.circle = new Circle();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (right) {
            rotation += 20 * delta;
            if (rotation > 15) right = false;
        } else {
            rotation -= 20 * delta;
            if (rotation < -15) right = true;
        }

        circle.set(position.x + 35, position.y + 100, 35f);
    }

    public boolean collides(Swimmer swimmer) {
        return position.x > swimmer.getX() + 110 && Intersector.overlaps(circle, swimmer.getRectangle());
    }

    public float getRotation() {
        return rotation;
    }
}
