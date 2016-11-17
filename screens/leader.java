package com.game.only.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class leader extends AbstractScreen {
	private Sound sound;
	private long id;
	private String name;
	private int point;
	private ReadData rd = new ReadData();
	private ArrayList<String> rank = new ArrayList<String>();
	private ArrayList<String> arr_name = new ArrayList<String>();
	private ArrayList<Integer> arr_point = new ArrayList<Integer>();
	BitmapFont font = new BitmapFont();
	BitmapFont font2 = new BitmapFont();
	BitmapFont font3= new BitmapFont();
	BitmapFont font4 = new BitmapFont();
	BitmapFont font5 = new BitmapFont();
	BitmapFont font6 = new BitmapFont();
	BitmapFont font7 = new BitmapFont();
	BitmapFont font8 = new BitmapFont();
	BitmapFont font9 = new BitmapFont();
	BitmapFont font10 = new BitmapFont();
	BitmapFont font11 = new BitmapFont();
	BitmapFont font12 = new BitmapFont();
	private Texture bg;
	SpriteBatch batch;
	int[] aaa = new int[3];
	public leader(Game game) {
		super(game);
		
	}
	public leader(Game game, int point, String name) {
		super(game);
		this.name = name;
		this.point = point;
		
	}
	@Override
	public void dispose() {
		bg.dispose();
		font.dispose();
		font2.dispose();
		font3.dispose();
		font4.dispose();
		font5.dispose();
		font6.dispose();
		font7.dispose();
		font8.dispose();
		font9.dispose();
		font10.dispose();
		font11.dispose();
		font12.dispose();
		super.dispose();
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
	public void render(float delta) {
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setScreen(new mainmenu(game));
			dispose();
		}
		font.draw(batch, rank.get(aaa[0]).split("\\s+")[0], (float) (7*Gdx.graphics.getWidth()/20), 13*Gdx.graphics.getHeight()/20);
		font2.draw(batch, rank.get(aaa[0]).split("\\s+")[1], (float) (12*Gdx.graphics.getWidth()/20), 13*Gdx.graphics.getHeight()/20);
		font3.draw(batch, rank.get(aaa[1]).split("\\s+")[0], (float) (7*Gdx.graphics.getWidth()/20), 10*Gdx.graphics.getHeight()/20);
		font4.draw(batch, rank.get(aaa[1]).split("\\s+")[1], (float) (12*Gdx.graphics.getWidth()/20), 10*Gdx.graphics.getHeight()/20);
		font5.draw(batch, rank.get(aaa[2]).split("\\s+")[0], (float) (7*Gdx.graphics.getWidth()/20), 7*Gdx.graphics.getHeight()/20);
		font6.draw(batch, rank.get(aaa[2]).split("\\s+")[1], (float) (12*Gdx.graphics.getWidth()/20), 7*Gdx.graphics.getHeight()/20);
		font7.draw(batch, "1ST", (float) (4.5*Gdx.graphics.getWidth()/20), 13*Gdx.graphics.getHeight()/20);
		font8.draw(batch, "2ND", (float) (4.5*Gdx.graphics.getWidth()/20), 10*Gdx.graphics.getHeight()/20);
		font9.draw(batch, "3RD", (float) (4.5*Gdx.graphics.getWidth()/20), 7*Gdx.graphics.getHeight()/20);
		font10.draw(batch, "Last Play", (float) (4.5*Gdx.graphics.getWidth()/20), 4*Gdx.graphics.getHeight()/20);
		font11.draw(batch, rank.get(rank.size() - 1).split("\\s+")[0], (float) (7*Gdx.graphics.getWidth()/20), 4*Gdx.graphics.getHeight()/20);
		font12.draw(batch, rank.get(rank.size() - 1).split("\\s+")[1], (float) (12*Gdx.graphics.getWidth()/20), 4*Gdx.graphics.getHeight()/20);
		batch.end();
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}
	@Override
	public void show() {
		sound = Gdx.audio.newSound(Gdx.files.internal("sound\\sound1.mp3"));
		id = sound.play(1.0f);      // plays the sound a second time, this is treated as a different instance
		sound.setPan(id, -1, 1);    // sets the pan of the sound to the left side at full volume
		sound.setLooping(id, true); // keeps the sound looping
		super.show();
		batch = new SpriteBatch();
		bg = new Texture("leader/leader.png");
		rd.readTxt_(rank);
		for (String item : rank){
			System.out.println(item);
			arr_name.add(item.split("\\s+")[0]);
			arr_point.add(Integer.parseInt(item.split("\\s+")[1]));
		}
		int mem = 0;
		int pos = 0;
		for (int i = 0; i < arr_point.size(); i++){
			if (mem < arr_point.get(i)){
				mem = arr_point.get(i);
				pos = i;
			}
		}
		aaa[0] = pos;
		mem = 0;
		pos = 0;
		for (int i = 0; i < arr_point.size(); i++){
			if (i == aaa[0]){
				
			}
			else if (mem < arr_point.get(i)){
				mem = arr_point.get(i);
				pos = i;
			}
		}
		aaa[1] = pos;
		mem = 0;
		pos = 0;
		for (int i = 0; i < arr_point.size(); i++){
			if (i == aaa[0] || i == aaa[1]){
				
			}
			else if (mem < arr_point.get(i)){
				mem = arr_point.get(i);
				pos = i;
			}
		}
		aaa[2] = pos;
		System.out.println(rank.get(aaa[0])+" "+rank.get(aaa[1])+" "+rank.get(aaa[2]));
		System.out.println(rank.get(rank.size() - 1));
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().setScale(3);
		font2.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font2.getData().setScale(3);
		font3.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font3.getData().setScale(3);
		font4.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font4.getData().setScale(3);
		font5.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font5.getData().setScale(3);
		font6.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font6.getData().setScale(3);
		font7.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font7.getData().setScale(3);
		font8.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font8.getData().setScale(3);
		font9.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font9.getData().setScale(3);
		font10.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font10.getData().setScale(3);
		font11.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font11.getData().setScale(3);
		font12.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font12.getData().setScale(3);
		super.show();
	}
}
