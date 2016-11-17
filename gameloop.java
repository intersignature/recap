package com.game.only;

import com.badlogic.gdx.Game;
import com.game.only.screens.mainmenu;

public class gameloop extends Game {
	@Override
	public void create () {
		setScreen(new mainmenu(this));
		super.pause();
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
		super.dispose();
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}
}
