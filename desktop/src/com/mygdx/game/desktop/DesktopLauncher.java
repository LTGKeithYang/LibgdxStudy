package com.mygdx.game.desktop;

import Game30_CompleteMenu.Game30;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=500;
		config.height=700;
		new LwjglApplication(new Game30(), config);
	}
}
