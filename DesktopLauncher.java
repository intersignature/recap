package com.game.only.desktop;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.only.gameloop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width = gd.getDisplayMode().getWidth();
		config.height = gd.getDisplayMode().getHeight();
		new LwjglApplication(new gameloop(), config);
	}
}
