package com.game.only.screens;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinScreen extends AbstractScreen {
	private int player, player2, index;
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	SpriteBatch batch;
	private boolean single_;
	private Texture bg;
	private Texture button1;
	private Texture button2;
	private Texture button3;
	private int select = 1;
	selectstage st = new selectstage(null);
	SaveData sd = new SaveData();
	ReadData rd = new ReadData();
	public WinScreen(Game game, int i, int j, int stageindex, boolean single) {
		super(game);
		player = i;
		player2 = j;
		index = stageindex;
		single_ = single;
	}
	
	public void buttonOneSelect(){
		button1 = new Texture("end of stage\\button_menu_click.png");
		button2 = new Texture("end of stage\\button_stage.png");
		button3 = new Texture("end of stage\\button_next.png");
	}
	
	public void buttonThreeSelect(){
		button1 = new Texture("end of stage\\button_menu.png");
		button2 = new Texture("end of stage\\button_stage.png");
		button3 = new Texture("end of stage\\button_next_click.png");
	}
	
	public void buttonTwoSelect(){
		button1 = new Texture("end of stage\\button_menu.png");
		button2 = new Texture("end of stage\\button_stage_click.png");
		button3 = new Texture("end of stage\\button_next.png");
	}
	@Override
	public void dispose () {
		button1.dispose();
		button2.dispose();
		button3.dispose();
		bg.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (select % 3 ==  1 || select % 3 == -2){
			buttonOneSelect();
    	}
    	else if (select % 3 == 0){
    		buttonThreeSelect();
    	}
    	else if (select % 3 == -1 || select % 3 == 2){
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
        	select %= 3;
        	System.out.println(select+"mod");
        	if (select == 1 || select == -2){
        		game.setScreen(new mainmenu(game));
        		dispose();
        	}
        	else if (select == 0){
	        	game.setScreen(new mutistage(game, single_, index+1));
	        	dispose();
        	}
        	else if (select == -1 || select == 2){
        		game.setScreen(new selectstage(game));
        		dispose();
        	}}
		batch.draw(button1, (float) (Gdx.graphics.getWidth()/5.2), Gdx.graphics.getHeight()/2);
		batch.draw(button2, (float) (Gdx.graphics.getWidth()/2.4), Gdx.graphics.getHeight()/2);
		batch.draw(button3, (float) (Gdx.graphics.getWidth()/1.53), Gdx.graphics.getHeight()/2);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		sd.writeXML(player, player2, index);
		batch = new SpriteBatch();
		bg = new Texture("end of stage\\mission_sc_bg.png");
	}
}
