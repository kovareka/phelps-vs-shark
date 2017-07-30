package com.mygdx.felps.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.felps.Felps;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Phelps vs Shark";
		config.width = 1000;
		config.height = 600;
		new LwjglApplication(new Felps(), config);
	}
}
