package com.mygdx.felps.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.felps.gameobjects.*;
import com.mygdx.felps.helpers.AssetLoader;

public class GameRenderer {
    private static final String TITLE = "PHELPS vs SHARK!";
    private static final String START = "Press SPACE for start";
    private static final String GAMEOVER = "Game Over!";
    private static final String TRY_AGAIN = "Press SPACE for restart";
    private static final String CONTROLS = "LEFT + RIGHT - move, SPACE - dive";

    private GameWorld world;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batch;

    private Swimmer swimmer;
    private WaveBackground bg1, bg2, bg3;
    private Wave front1, front2, front3;
    private Shark shark;
    private Bouy bouy1, bouy2, bouy3;
    private Clouds clouds1, clouds2, clouds3, clouds4;

    private TextureRegion bg, waves;
    private Animation swimmerAnimation;
    private TextureRegion swimmerMid;
    private TextureRegion sharkMid;
    private TextureRegion bouyTexture;
    private TextureRegion clouds1Texture, clouds2Texture, clouds3Texture, clouds4Texture;

    public GameRenderer(GameWorld world) {
        this.world = world;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, 1000, 600);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        swimmer = world.getSwimmer();
        ScrollHandler scroller = world.getScroller();
        bg1 = scroller.getBg1();
        bg2 = scroller.getBg2();
        bg3 = scroller.getBg3();
        front1 = scroller.getFront1();
        front2 = scroller.getFront2();
        front3 = scroller.getFront3();
        shark = world.getShark();
        bouy1 = scroller.getBouy1();
        bouy2 = scroller.getBouy2();
        bouy3 = scroller.getBouy3();
        clouds1 = scroller.getClouds1();
        clouds2 = scroller.getClouds2();
        clouds3 = scroller.getClouds3();
        clouds4 = scroller.getClouds4();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        waves = AssetLoader.waves;
        swimmerAnimation = AssetLoader.swimmerAnimation;
        swimmerMid = AssetLoader.swimmer;
        sharkMid = AssetLoader.shark;
        bouyTexture = AssetLoader.bouy;
        clouds1Texture = AssetLoader.clouds1;
        clouds2Texture = AssetLoader.clouds2;
        clouds3Texture = AssetLoader.clouds3;
        clouds4Texture = AssetLoader.clouds4;
    }

    private void drawBackgroundWaves() {
        batch.draw(bg, bg1.getX(), bg1.getY(), bg1.getWidth(), bg1.getHeight());
        batch.draw(bg, bg2.getX(), bg2.getY(), bg2.getWidth(), bg2.getHeight());
        batch.draw(bg, bg3.getX(), bg3.getY(), bg3.getWidth(), bg3.getHeight());
    }

    private void drawFrontWaves() {
        batch.draw(waves, front1.getX(), front1.getY(), front1.getWidth(), front1.getHeight());
        batch.draw(waves, front2.getX(), front2.getY(), front2.getWidth(), front2.getHeight());
        batch.draw(waves, front3.getX(), front3.getY(), front3.getWidth(), front3.getHeight());
    }

    private void drawClouds() {
        batch.draw(getCloudsTexture(clouds1.getTexture()), clouds1.getX(), clouds1.getY(), clouds1.getWidth(), clouds1.getHeight());
        batch.draw(getCloudsTexture(clouds2.getTexture()), clouds2.getX(), clouds2.getY(), clouds2.getWidth(), clouds2.getHeight());
        batch.draw(getCloudsTexture(clouds3.getTexture()), clouds3.getX(), clouds3.getY(), clouds3.getWidth(), clouds3.getHeight());
        batch.draw(getCloudsTexture(clouds4.getTexture()), clouds4.getX(), clouds4.getY(), clouds4.getWidth(), clouds4.getHeight());
    }

    private TextureRegion getCloudsTexture(int val) {
        if (val == 1) {
            return clouds1Texture;
        } else if (val == 2) {
            return clouds2Texture;
        } else if (val == 3) {
            return clouds3Texture;
        } else {
            return clouds4Texture;
        }
    }

    private void drawBouys() {
        batch.draw(bouyTexture, bouy1.getX(), bouy1.getY(),
                bouy1.getWidth() / 2.0f,bouy1.getHeight() / 2.0f,
                bouy1.getWidth(), bouy1.getHeight(), 1, 1,
                bouy1.getRotation());
        batch.draw(bouyTexture, bouy2.getX(), bouy2.getY(),
                bouy2.getWidth() / 2.0f,bouy2.getHeight() / 2.0f,
                bouy2.getWidth(), bouy2.getHeight(), 1, 1,
                bouy2.getRotation());
        batch.draw(bouyTexture, bouy3.getX(), bouy3.getY(),
                bouy3.getWidth() / 2.0f,bouy3.getHeight() / 2.0f,
                bouy3.getWidth(), bouy3.getHeight(), 1, 1,
                bouy3.getRotation());
    }

    public void render(float runTime){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(174 / 255.0f, 220 / 255.0f, 242 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 1000, 400);
        shapeRenderer.end();

        batch.begin();
        drawBackgroundWaves();

        if (!world.isReady()) {
            if (swimmer.isFalling() || !swimmer.isAlive()) {
                batch.draw(swimmerMid, swimmer.getX(), swimmer.getY(), swimmer.getWidth(), swimmer.getHeight());
            } else {
                batch.draw((TextureRegion) swimmerAnimation.getKeyFrame(runTime), swimmer.getX(), swimmer.getY(),
                        swimmer.getWidth(), swimmer.getHeight());
                world.addScore();
            }

            batch.draw(sharkMid, shark.getX(), shark.getY(), shark.getWidth(), shark.getHeight());

            AssetLoader.shadow.draw(batch, String.valueOf(world.getScore()), 0, 0);
            AssetLoader.fontText.draw(batch, String.valueOf(world.getScore()), 0, 0);
        }

        drawBouys();

        drawFrontWaves();

        drawClouds();

        if (world.isReady()) {
            AssetLoader.shadow.getData().setScale(1.5f, -1.5f);
            AssetLoader.shadow.draw(batch, TITLE, 500 - TITLE.length() * 40 / 2, 110);
            AssetLoader.fontHeaders.draw(batch, TITLE, 500 - TITLE.length() * 40 / 2, 110);
            AssetLoader.shadow.getData().setScale(1f, -1f);

            AssetLoader.shadow.draw(batch, START, 500 - START.length() * 26 / 2, 190);
            AssetLoader.fontText.draw(batch, START, 500 - START.length() * 26 / 2, 190);

            AssetLoader.shadow.getData().setScale(0.5f, -0.5f);
            AssetLoader.fontText.getData().setScale(0.5f, -0.5f);
            AssetLoader.shadow.draw(batch, CONTROLS, 500 - CONTROLS.length() * 13 / 2, 300);
            AssetLoader.fontText.draw(batch, CONTROLS, 500 - CONTROLS.length() * 13 / 2, 300);
            AssetLoader.shadow.getData().setScale(1f, -1f);
            AssetLoader.fontText.getData().setScale(1f, -1f);
        } else {
            if (world.isGameOver() || world.isHighScore()) {
                AssetLoader.shadow.draw(batch, GAMEOVER, 500 - GAMEOVER.length() * 26 / 2, 90);
                AssetLoader.fontText.draw(batch, GAMEOVER, 500 - GAMEOVER.length() * 26 / 2, 90);

                String score = "Score: " + world.getScore() + " Top: " + AssetLoader.getHighScore();

                AssetLoader.shadow.draw(batch, score, 500 - score.length() * 26 / 2, 130);
                AssetLoader.fontText.draw(batch, score, 500 - score.length() * 26 / 2, 130);

                AssetLoader.shadow.draw(batch, TRY_AGAIN, 500 - TRY_AGAIN.length() * 26 / 2, 170);
                AssetLoader.fontText.draw(batch, TRY_AGAIN, 500 - TRY_AGAIN.length() * 26 / 2, 170);
            }
        }

        batch.end();
    }
}
