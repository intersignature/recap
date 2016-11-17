package com.game.only.screens;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class selectstage extends AbstractScreen{
	private Sound sound;
	private long id;
	private Texture texture, yon;
	private int i = 0;
	private ArrayList<Texture> all = new ArrayList<Texture>();
	private ArrayList<Texture> all_pass = new ArrayList<Texture>();
	private ArrayList<Texture> label = new ArrayList<Texture>();
	private ArrayList<Texture> allsorm = new ArrayList<Texture>();
	private ArrayList<Texture> arrow = new ArrayList<Texture>();
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private Batch batch = new SpriteBatch();
	private OrthographicCamera camera;
	private float x,y,width,height;
	private float x1,y1,width1,height1;
	private boolean sw = false;
	private float xs;
	private float ys;
	private float widths;
	private float heights;
	private float xtwo;
	private float ytwo;
	private float widthtwo;
	private float heighttwo;
	private Texture frame;
	private Texture select;
	private Texture window;
	private int j;
	private int k;
	private Texture bg;
	ReadData rd = new ReadData();
	private int current_stage;
	private ArrayList<Texture> all_lock = new ArrayList<Texture>();
	public selectstage(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void dispose() {
		frame.dispose();
		select.dispose();
		window.dispose();
		for (Texture item : arrow){
			item.dispose();
		}
		for (Texture item : all_lock){
			item.dispose();
		}
		for (Texture item : allsorm){
			item.dispose();
		}
		for (Texture item : label){
			item.dispose();
		}
		for (Texture item : all_pass){
			item.dispose();
		}
		for (Texture item : all){
			item.dispose();
		}
		super.dispose();
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
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
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//updatecamera();
		//batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(frame, x - 30, y - 30, width + 60, height + 60);
		if (i < current_stage){
			batch.draw(all_pass.get(i), x, y, width, height);
		}
		else if(i == current_stage){
			batch.draw(all.get(i), x, y, width, height);
		}
		else{
			batch.draw(all_lock.get(i), x, y, width, height);
		}
		if(i == 4){
			batch.draw(arrow.get(0), gd.getDisplayMode().getWidth() / 8, (gd.getDisplayMode().getHeight() / 2) -50, 100, 100);
			batch.draw(arrow.get(1), gd.getDisplayMode().getWidth() - gd.getDisplayMode().getWidth() / 8 - 100, (gd.getDisplayMode().getHeight() / 2) - 50, 0, 0);
		}
		else if (i == 0){
			batch.draw(arrow.get(0), gd.getDisplayMode().getWidth() / 8, (gd.getDisplayMode().getHeight() / 2) -50, 0, 0);
			batch.draw(arrow.get(1), gd.getDisplayMode().getWidth() - gd.getDisplayMode().getWidth() / 8 - 100, (gd.getDisplayMode().getHeight() / 2) - 50, 100, 100);
		}else{
			batch.draw(arrow.get(0), gd.getDisplayMode().getWidth() / 8, (gd.getDisplayMode().getHeight() / 2) -50, 100, 100);	
			batch.draw(arrow.get(1), gd.getDisplayMode().getWidth() - gd.getDisplayMode().getWidth() / 8 - 100, (gd.getDisplayMode().getHeight() / 2) - 50, 100, 100);
		}
		
			
		
			
		
		
		batch.draw(select,gd.getDisplayMode().getWidth() / 2 -100,gd.getDisplayMode().getHeight() / 8, 200, 100);
		if (sw){
			 
			if (Gdx.input.isKeyJustPressed(Keys.W)){
	        	j += 1;
	        }
	        else if (Gdx.input.isKeyJustPressed(Keys.S)){
	        	j += 1;
	        }
	        if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
	        	j %= 2;
	        	if (j == 1 || j == -1){
	        		game.setScreen(new mutistage(game, true, i));
	        		dispose();
	        	}else{
	        		game.setScreen(new mutistage(game, false, i));
	        		dispose();
	        	}
	        }
	        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
	        	batch.draw(window, x1, y1, 0, 0);
				batch.draw(allsorm.get(j%2), xs, ys -50 , 0, 0);
				batch.draw(allsorm.get(j%2), xtwo, ytwo , 0, 0 );
				sw = false;
	        }
	        if (j%2 == 1 || j%2 == -1){
	        	batch.draw(window, x1, y1, width1, height1);
				batch.draw(allsorm.get(2), xs, ys -50 , widths, heights+50);
				batch.draw(allsorm.get(1), xtwo, ytwo , widthtwo, heighttwo + 50 );
        	}else{
        		batch.draw(window, x1, y1, width1, height1);
    			batch.draw(allsorm.get(0), xs, ys -50 , widths, heights+50);
    			batch.draw(allsorm.get(3), xtwo, ytwo , widthtwo, heighttwo + 50 );

        	}
	       
		}
		else{
			if (Gdx.input.isKeyJustPressed(Keys.A) && i > 0){
	        	i -= 1;
	        }
	        else if (Gdx.input.isKeyJustPressed(Keys.D) &&i < 4){
	        	i += 1;
	        }

			batch.draw(arrow.get(1), gd.getDisplayMode().getWidth() - gd.getDisplayMode().getWidth() / 8 - 100, (gd.getDisplayMode().getHeight() / 2) - 50, 0, 0);

	        if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
	        	if (i < current_stage+1){
	        		sw = true;
	        		j = 1;
	        	}
	        	//k = 0;
	        }
	        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
	        	game.setScreen(new mainmenu(game));
	        	dispose();
	        }
		}
		batch.draw(label.get(i), 7*gd.getDisplayMode().getWidth()/20, 16*gd.getDisplayMode().getHeight()/20);
		batch.end();
	}
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	public void setI(int i) {
		this.i = i;
	}
	public void setJ(int j) {
		this.j = j;
	}
	private void setposition_map() {
		x = gd.getDisplayMode().getWidth() / 4;
		y = gd.getDisplayMode().getHeight() / 4;
		width = gd.getDisplayMode().getWidth() - gd.getDisplayMode().getWidth() / 2;
		height = gd.getDisplayMode().getHeight() - gd.getDisplayMode().getHeight() / 2;
	}
	private void setposition_select() {
		x1 = (gd.getDisplayMode().getWidth() / 4) + (gd.getDisplayMode().getWidth() / 8);
		y1 = (gd.getDisplayMode().getHeight() / 4) + (gd.getDisplayMode().getHeight() / 8);
		width1 = (gd.getDisplayMode().getWidth() / 4);
		height1 = (gd.getDisplayMode().getHeight() /4 );
	}
	private void setposition_single() {
		xs = x1 + ((x1 - width1) / 4);
		ys = y1 + (((y1 - height1)) + ((y1 - height1) / 4));
		widths = (width1/2) + (width1/4);
		heights = height1 / 4;
	}

	private void setposition_two() {
		xtwo = x1 + ((x1 - width1) / 4);
		ytwo = y1 + (((y1 - height1)) / 4);
		widthtwo  = (width1/2) + (width1/4);
		heighttwo = height1 / 4;
	}

	@Override
	public void show() {
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
		all.add(new Texture("stage/s1.png"));
		all.add(new Texture("stage/s2.png"));
		all.add(new Texture("stage/s3.png"));
		all.add(new Texture("stage/s4.png"));
		all.add(new Texture("stage/s5.png"));
		all_pass.add(new Texture("stage/s1_c.png"));
		all_pass.add(new Texture("stage/s2_c.png"));
		all_pass.add(new Texture("stage/s3_c.png"));
		all_pass.add(new Texture("stage/s4_c.png"));
		all_pass.add(new Texture("stage/s5_c.png"));
		all_lock.add(new Texture("stage/s1_l.png"));
		all_lock.add(new Texture("stage/s2_l.png"));
		all_lock.add(new Texture("stage/s3_l.png"));
		all_lock.add(new Texture("stage/s4_l.png"));
		all_lock.add(new Texture("stage/s5_l.png"));
		label.add(new Texture("stage/stage1_lable.png"));
		label.add(new Texture("stage/stage2_lable.png"));
		label.add(new Texture("stage/stage3_lable.png"));
		label.add(new Texture("stage/stage4_lable.png"));
		label.add(new Texture("stage/stage5_lable.png"));
		frame = new Texture("stage/window.png");
		select = new Texture("stage/button_select_click.png");
		window = new Texture("stage/window.png");
		allsorm.add(new Texture("stage/button_1p.png"));
		allsorm.add(new Texture("stage/button_2p.png"));
		allsorm.add(new Texture("stage/button_1p_click.png"));
		allsorm.add(new Texture("stage/button_2p_click.png"));
		arrow.add(new Texture("stage/ar_l_click.png"));
		arrow.add(new Texture("stage/ar_r_click.png"));
		setposition_map();
		setposition_select();
		setposition_single();
		setposition_two();
		sound = Gdx.audio.newSound(Gdx.files.internal("sound\\sound3.mp3"));
		id = sound.play(1.0f);      // plays the sound a second time, this is treated as a different instance
		sound.setPan(id, -1, 1);    // sets the pan of the sound to the left side at full volume
		sound.setLooping(id, true); // keeps the sound looping
		super.show();
		bg = new Texture("stage\\stage_select_bg.png");
		rd.readTxt();
		current_stage = rd.getStage();
		super.show();
	}

	public void updatecamera(){
		camera.position.set(0, 0, 0);
		camera.update();
	}

}
