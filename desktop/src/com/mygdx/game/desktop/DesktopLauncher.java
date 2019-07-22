package com.mygdx.game.desktop;

import BlackJackGameAssets.BlackJackGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1024;
		config.height=768;
		new LwjglApplication(new BlackJackGame(), config);
	}
}
