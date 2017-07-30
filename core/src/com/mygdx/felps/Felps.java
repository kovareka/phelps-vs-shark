package com.mygdx.felps;

import com.badlogic.gdx.Game;
import com.mygdx.felps.helpers.AssetLoader;
import com.mygdx.felps.screens.GameScreen;

public class Felps extends Game {
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
