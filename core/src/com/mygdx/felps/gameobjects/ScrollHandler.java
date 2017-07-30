package com.mygdx.felps.gameobjects;

import java.util.Random;

public class ScrollHandler {
    private WaveBackground bg1, bg2, bg3;
    private Wave front1, front2, front3;
    private Bouy bouy1, bouy2, bouy3;
    private Clouds clouds1, clouds2, clouds3, clouds4;

    private static final int BOUY_GAP = 900;

    private Random r;

    public ScrollHandler(float yPos) {
        int scrollSpeed = -30;
        this.bg1 = new WaveBackground(0, yPos, 519, 260, scrollSpeed);
        this.bg2 = new WaveBackground(bg1.getTailX(), yPos, 519, 260, scrollSpeed);
        this.bg3 = new WaveBackground(bg2.getTailX(), yPos, 519, 260, scrollSpeed);
        this.front1 = new Wave(0, yPos, 519, 260, scrollSpeed);
        this.front2 = new Wave(front1.getTailX(), yPos, 519, 260, scrollSpeed);
        this.front3 = new Wave(front2.getTailX(), yPos, 519, 260, scrollSpeed);
        r = new Random();
        this.bouy1 = new Bouy(1400 + r.nextInt(400) + 15, 230, 70, 141, scrollSpeed);
        this.bouy2 = new Bouy(bouy1.getTailX() + BOUY_GAP + r.nextInt(400) + 15, 230, 70, 141, scrollSpeed);
        this.bouy3 = new Bouy(bouy2.getTailX() + + BOUY_GAP + r.nextInt(400) + 15, 230, 70, 141, scrollSpeed);
        this.clouds1 = new Clouds(0, 0, 320, 100, scrollSpeed);
        this.clouds2 = new Clouds(clouds1.getTailX(), 0, 320, 100, scrollSpeed);
        this.clouds3 = new Clouds(clouds2.getTailX(), 0, 320, 100, scrollSpeed);
        this.clouds4 = new Clouds(clouds3.getTailX(), 0, 320, 100, scrollSpeed);
    }

    public void update(float delta) {
        bg1.update(delta);
        bg2.update(delta);
        bg3.update(delta);
        front1.update(delta);
        front2.update(delta);
        front3.update(delta);
        bouy1.update(delta);
        bouy2.update(delta);
        bouy3.update(delta);
        clouds1.update(delta);
        clouds2.update(delta);
        clouds3.update(delta);
        clouds4.update(delta);

        if (bg1.isScrolledLeft()) {
            bg1.reset(bg3.getTailX());
        } else if (bg2.isScrolledLeft()) {
            bg2.reset(bg1.getTailX());
        } else if (bg3.isScrolledLeft()) {
            bg3.reset(bg2.getTailX());
        }

        if (front1.isScrolledLeft()) {
            front1.reset(front3.getTailX());
        } else if (front2.isScrolledLeft()) {
            front2.reset(front1.getTailX());
        } else if (front3.isScrolledLeft()) {
            front3.reset(front2.getTailX());
        }

        if (bouy1.isScrolledLeft()) {
            bouy1.reset(bouy3.getTailX() + BOUY_GAP + r.nextInt(400) + 15);
        } else if (bouy2.isScrolledLeft()) {
            bouy2.reset(bouy1.getTailX() + BOUY_GAP + r.nextInt(400) + 15);
        } else if (bouy3.isScrolledLeft()) {
            bouy3.reset(bouy2.getTailX() + BOUY_GAP + r.nextInt(400) + 15);
        }

        if (clouds1.isScrolledLeft()) {
            clouds1.reset(clouds4.getTailX());
            clouds1.setRandomTexture();
        } else if (clouds2.isScrolledLeft()) {
            clouds2.reset(clouds1.getTailX());
            clouds2.setRandomTexture();
        } else if (clouds3.isScrolledLeft()) {
            clouds3.reset(clouds2.getTailX());
            clouds3.setRandomTexture();
        } else if (clouds4.isScrolledLeft()) {
            clouds4.reset(clouds3.getTailX());
            clouds4.setRandomTexture();
        }
    }

    public boolean collides(Swimmer swimmer) {
        return bouy1.collides(swimmer) || bouy2.collides(swimmer) || bouy3.collides(swimmer);
    }

    public void setStop(boolean stop) {
        bg1.setStop(stop);
        bg2.setStop(stop);
        bg3.setStop(stop);
        front1.setStop(stop);
        front2.setStop(stop);
        front3.setStop(stop);
        bouy1.setStop(stop);
        bouy2.setStop(stop);
        bouy3.setStop(stop);
    }

    public void stop() {
        bg1.stop();
        bg2.stop();
        bg3.stop();
        front1.stop();
        front2.stop();
        front3.stop();
        bouy1.stop();
        bouy2.stop();
        bouy3.stop();
    }

    public void onRestart() {
        bouy1.reset(1400 + r.nextInt(400) + 15);
        bouy2.reset(bouy1.getTailX() + BOUY_GAP + r.nextInt(400) + 15);
        bouy3.reset(bouy2.getTailX() + BOUY_GAP + r.nextInt(400) + 15);
    }

    public WaveBackground getBg1() {
        return bg1;
    }

    public WaveBackground getBg2() {
        return bg2;
    }

    public WaveBackground getBg3() {
        return bg3;
    }

    public Wave getFront1() {
        return front1;
    }

    public Wave getFront2() {
        return front2;
    }

    public Wave getFront3() {
        return front3;
    }

    public Bouy getBouy1() {
        return bouy1;
    }

    public Bouy getBouy2() {
        return bouy2;
    }

    public Bouy getBouy3() {
        return bouy3;
    }

    public Clouds getClouds1() {
        return clouds1;
    }

    public Clouds getClouds2() {
        return clouds2;
    }

    public Clouds getClouds3() {
        return clouds3;
    }

    public Clouds getClouds4() {
        return clouds4;
    }
}
