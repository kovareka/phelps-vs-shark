package com.mygdx.felps.gameobjects;

public class WaveBackground extends Scrollable {
    WaveBackground(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed / 1.5f);
    }

    @Override
    public void update(float delta) {
        if (velocity.x < -520 / 1.5f) {
            velocity.x = -520 / 1.5f;
        } else if (velocity.x > -30 / 1.5f) {
            velocity.x = -30 / 1.5f;
        }

        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    @Override
    public void setStop(boolean stop) {
        if (stop) {
            velocity.x += 5 / 1.5f;
        } else {
            velocity.x -= 20 / 1.5f;
        }
    }
}
