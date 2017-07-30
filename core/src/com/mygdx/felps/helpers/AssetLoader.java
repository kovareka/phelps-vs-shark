package com.mygdx.felps.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    private static Texture texture;
    public static TextureRegion bg, waves;

    public static TextureRegion swimmer;
    public static Animation swimmerAnimation;

    public static TextureRegion shark;

    public static TextureRegion bouy;

    public static TextureRegion clouds1, clouds2, clouds3, clouds4;

    public static BitmapFont fontText, shadow, fontHeaders;

    private static Preferences prefs;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 519, 260);
        bg.flip(false, true);

        waves = new TextureRegion(texture, 0, 262, 519, 257);
        waves.flip(false, true);

        swimmer = new TextureRegion(texture, 519, 0, 255, 136);
        swimmer.flip(false, true);

        TextureRegion swimmerDown = new TextureRegion(texture, 519, 136, 255, 136);
        swimmerDown.flip(false, true);

        TextureRegion swimmerUp = new TextureRegion(texture, 519, 272, 255, 136);
        swimmerUp.flip(false, true);

        TextureRegion[] swimmers = {swimmerDown, swimmer, swimmerUp};
        swimmerAnimation = new Animation(0.08f, swimmers);
        swimmerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        shark = new TextureRegion(texture, 519, 408, 301, 193);
        shark.flip(false, true);

        bouy = new TextureRegion(texture, 0, 519, 70, 141);
        bouy.flip(false, true);

        clouds1 = new TextureRegion(texture,71, 519, 320, 100);
        clouds1.flip(false, true);
        clouds2 = new TextureRegion(texture,71, 619, 320, 100);
        clouds2.flip(false, true);
        clouds3 = new TextureRegion(texture,71, 719, 320, 100);
        clouds3.flip(false, true);
        clouds4 = new TextureRegion(texture,71, 819, 320, 100);
        clouds4.flip(false, true);

        fontText = new BitmapFont((Gdx.files.internal("data/whitetext.fnt")));
        fontText.getData().setScale(1f, -1f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(1f, -1f);
        fontHeaders = new BitmapFont(Gdx.files.internal("data/headers.fnt"));
        fontHeaders.getData().setScale(1.5f, -1.5f);

        prefs = Gdx.app.getPreferences("Felps");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        texture.dispose();
        fontText.dispose();
        fontHeaders.dispose();
        shadow.dispose();
    }
}
