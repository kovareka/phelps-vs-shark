package com.mygdx.felps.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.felps.gameobjects.ScrollHandler;
import com.mygdx.felps.gameobjects.Shark;
import com.mygdx.felps.gameobjects.Swimmer;
import com.mygdx.felps.gameworld.GameWorld;

public class InputHandler implements InputProcessor{
    private GameWorld world;

    private Swimmer swimmer;
    private ScrollHandler scroller;
    private Shark shark;
    private int lastKey;

    public InputHandler(GameWorld world) {
        this.world = world;
        this.swimmer = world.getSwimmer();
        this.scroller = world.getScroller();
        this.shark = world.getShark();
        this.lastKey = 0;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (world.isReady()) {
            if (keycode == Input.Keys.SPACE) {
                world.start();
                return true;
            }
        }

        if(world.isRunning()) {
            if (!swimmer.isDive()) {
                if (lastKey != keycode && (keycode == 22 || keycode == 21)) {
                    swimmer.onClick();
                    lastKey = keycode;
                    scroller.setStop(false);
                    shark.move(false);
                }

                if (keycode == Input.Keys.SPACE && swimmer.getY() <= 260) {
                    swimmer.dive();
                    shark.slow();
                }
            }
        }

        if (world.isGameOver() || world.isHighScore()) {
            if (keycode == Input.Keys.SPACE) {
                world.restart();
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
