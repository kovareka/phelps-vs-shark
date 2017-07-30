package com.mygdx.felps.gameworld;


import com.mygdx.felps.gameobjects.ScrollHandler;
import com.mygdx.felps.gameobjects.Shark;
import com.mygdx.felps.gameobjects.Swimmer;
import com.mygdx.felps.helpers.AssetLoader;

public class GameWorld {
    private Swimmer swimmer;
    private ScrollHandler scroller;
    private Shark shark;

    private GameState currentState;
    private int score;

    public GameWorld() {
        this.swimmer = new Swimmer(273, 260, 255, 136);
        this.scroller = new ScrollHandler(340);
        this.shark = new Shark(-600, 240, 301,193);
        this.score = 0;
        this.currentState = GameState.READY;
    }

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                scroller.update(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateRunning(float delta) {
        swimmer.update(delta);
        scroller.update(delta);
        shark.update(delta);

        if (swimmer.isFalling()) {
            scroller.setStop(true);
            shark.move(true);
        }

        if (swimmer.isDive()) {
            scroller.setStop(false);
            shark.slow();
        }

        if (shark.collides(swimmer) || scroller.collides(swimmer) || !swimmer.isAlive()) {
            scroller.stop();
            shark.stop();
            swimmer.stop();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public void start() {
        currentState = GameState.RUNNING;
        scroller.onRestart();
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        swimmer.onRestart();
        shark.onRestart();
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public Shark getShark() {
        return shark;
    }

    int getScore() {
        return score;
    }

    void addScore() {
        score += 1;
    }
}
