package com.game.only.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HowToPlay extends AbstractScreen{

	public HowToPlay(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	private Sound sound;
	private long id;
	SpriteBatch batch;
	private Texture bg;
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setScreen(new mainmenu(game));
		}
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		batch = new SpriteBatch();
		bg = new Texture("howtoplay\\howtoplay.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("sound\\sound4.mp3"));
		id = sound.play(1.0f);      // plays the sound a second time, this is treated as a different instance
		sound.setPan(id, -1, 1);    // sets the pan of the sound to the left side at full volume
		sound.setLooping(id, true); // keeps the sound looping
		bg = new Texture("howtoplay\\howtoplay.png");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		sound.stop();
		super.hide();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		bg.dispose();
		super.dispose();
	}

}
