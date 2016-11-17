package com.game.only.screens;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FailScreen extends AbstractScreen{
	public FailScreen(Game game,int stage, boolean issingle) {
		super(game);
		// TODO Auto-generated constructor stub
		stage_ = stage;
		issingle_ = issingle;
	}
	int stage_;
	boolean issingle_;
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	SpriteBatch batch;
	Texture img;
	private Texture bg;
	private Texture button1;
	private Texture button2;
	private int select = 1;
	selectstage st = new selectstage(null);
	
	public void buttonOneSelect(){
		button1 = new Texture("end of stage\\button_menu_click.png");
		button2 = new Texture("end of stage\\button_retry.png");
		
	}
	
	public void buttonTwoSelect(){
		button1 = new Texture("end of stage\\button_menu.png");
		button2 = new Texture("end of stage\\button_retry_click.png");

	}

	@Override
	public void dispose () {
		bg.dispose();
		button1.dispose();
		button2.dispose();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		bg = new Texture("end of stage\\mission_fail_bg.png");

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (select % 2 ==  1 || select % 2 == -1){
			buttonOneSelect();
    	}
    	else if (select % 2 == 0){
    		buttonTwoSelect();
    	}
		if (Gdx.input.isKeyJustPressed(Keys.A)){
        	select -= 1;
        	System.out.println(select);
        }
        else if (Gdx.input.isKeyJustPressed(Keys.D)){
        	select += 1;
        	System.out.println(select);
        }
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
        	select %= 2;
        	System.out.println(select+"mod");
        	if (select == 1 || select == -1){
        		game.setScreen(new mainmenu(game));
        		dispose();
        	}
        	else if (select == 0){
	        	game.setScreen(new mutistage(game, issingle_, stage_));
	        	dispose();
        	}}
		batch.draw(button1, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2);
		batch.draw(button2, (float) (Gdx.graphics.getWidth()/1.7),  Gdx.graphics.getHeight()/2);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
