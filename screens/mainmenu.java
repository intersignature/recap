package com.game.only.screens;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

	public class mainmenu extends AbstractScreen {
		private SpriteBatch batch;
		private Texture img;
		private Skin skin;
		private int select = 1;
		private ReadData rd = new ReadData();
		private SaveData sd = new SaveData();
		private Texture button1;
		private Texture button2;
		private Texture button3;
		private Texture button4;
		private Texture button5;
		private Texture bg;
		private Texture logo;
		private Sound sound;
		private long id;
		private TextField txfUsername;
		private Stage stage;
		Random rand;
		public mainmenu(Game game){
			super(game);
		}
		
		public void create () {
			batch = new SpriteBatch();
			
			
		}
		
		
		public void firstButtonSelect(){
			button1 = new Texture("mainmenu\\button_start_click.png");
			button2 = new Texture("mainmenu\\button_how.png");
			button3 = new Texture("mainmenu\\button_credit.png");
			button5 = new Texture("mainmenu\\button_exit.png");
			button4 = new Texture("mainmenu\\button_lead.png");
		}
		
		public void secondButtonSelect(){
			button1 = new Texture("mainmenu\\button_start.png");
			button2 = new Texture("mainmenu\\button_how_click.png");
			button3 = new Texture("mainmenu\\button_credit.png");
			button5 = new Texture("mainmenu\\button_exit.png");
			button4 = new Texture("mainmenu\\button_lead.png");
		}
		public void thirdButtonSelect(){
			button1 = new Texture("mainmenu\\button_start.png");
			button2 = new Texture("mainmenu\\button_how.png");
			button3 = new Texture("mainmenu\\button_credit_click.png");
			button5 = new Texture("mainmenu\\button_exit.png");
			button4 = new Texture("mainmenu\\button_lead.png");
		}
		public void forthButtonSelect(){
			button1 = new Texture("mainmenu\\button_start.png");
			button2 = new Texture("mainmenu\\button_how.png");
			button3 = new Texture("mainmenu\\button_credit.png");
			button5 = new Texture("mainmenu\\button_exit.png");
			button4 = new Texture("mainmenu\\button_lead_click.png");
		}
		public void fifthButtonSelect(){
			button1 = new Texture("mainmenu\\button_start.png");
			button2 = new Texture("mainmenu\\button_how.png");
			button3 = new Texture("mainmenu\\button_credit.png");
			button5 = new Texture("mainmenu\\button_exit_click.png");
			button4 = new Texture("mainmenu\\button_lead.png");
		}
		@Override
		public void dispose () {
			//batch.dispose();
			button1.dispose();
			button2.dispose();
			button3.dispose();
			button4.dispose();
			button5.dispose();
			logo.dispose();
			sound.dispose();
			for (Texture item :texselect){
				item.dispose();
			}
			stage.dispose();
			bg.dispose();
		}
		@Override
		public void show() {
			stage = new Stage();
			Gdx.input.setInputProcessor(stage);
			batch = new SpriteBatch();
			logo = new Texture("mainmenu\\RECAP.png");
			sound = Gdx.audio.newSound(Gdx.files.internal("sound\\bg_sound.mp3"));
			id = sound.play(1.0f);      // plays the sound a second time, this is treated as a different instance
			sound.setPan(id, -1, 1);    // sets the pan of the sound to the left side at full volume
			sound.setLooping(id, true); // keeps the sound looping
			//sound.stop(); 
			Skin skin = new Skin(Gdx.files.internal("mode\\uiskin.json"));
		    txfUsername = new TextField("",skin);
			txfUsername.setPosition((float) (11*Gdx.graphics.getWidth()/20), (float) (9.5*Gdx.graphics.getHeight()/20));
			txfUsername.setSize(300,40);
			stage.addActor(txfUsername);
			texselect.add(new Texture("mode/normal_c.png"));
			texselect.add(new Texture("mode/normal_b.png"));
			texselect.add(new Texture("mode/rank_c.png"));
			texselect.add(new Texture("mode/rank_b.png"));
			texselect.add(new Texture("mode/window_mode.png"));
			texselect.add(new Texture("mode/window_rank.png"));
		}
		int i=0;
		private int index = 0;
		private int index2 = 1;
		private ArrayList<Texture> texselect = new ArrayList<Texture>();
		private int mainselect;
		private int delaybg = 0;
		selectrank sr = new selectrank(game, 1, 2, null);
		@Override
		public void render(float delta) {	
			delaybg -= 1;
			Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        batch.begin();
	        if (delaybg <= 0){
	        int randomNum = randInt(1, 30);
	        bg = new Texture("mainmenu\\world_bg_" + randomNum + ".png");
	        delaybg = 20;
	        }
	        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        batch.draw(logo, Gdx.graphics.getWidth()/16, (float) (1.9*Gdx.graphics.getHeight()/3), Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/3);
	        
        	if (select % 5 == 1 || select % 5 ==-4){
        		firstButtonSelect();
        	}
        	else if (select % 5 == 2 || select % 5 ==-3){
        		secondButtonSelect();
        	}
        	else if (select % 5 == 3 || select % 5 ==-2){
        		thirdButtonSelect();
        	}
        	else if (select % 5 == 4 || select % 5 ==-1){
        		forthButtonSelect();
        	}
        	else if(select % 5 == 0){
        		fifthButtonSelect();
        	}
        	if (mainselect == 0){
	        if (Gdx.input.isKeyJustPressed(Keys.W)){
	        	select -= 1;
	        	//System.out.println(select%4);
	        }
	        else if (Gdx.input.isKeyJustPressed(Keys.S)){
	        	select += 1;
	        	//System.out.println(select%4);
	        }
	        if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
	        	select %= 5;
	        	System.out.println(select+"mod");
	        	if (select == 1 || select ==-4){
	        		mainselect = 1;
	        	}
	        	else if(select == 2 || select ==-3){
	        		game.setScreen(new HowToPlay(game));
	        		dispose();
	        	}
	        	else if(select == 3 || select ==-2){
	        		game.setScreen(new Credit(game));
	        		dispose();
	        	}
	        	else if (select == 4 || select ==-1){
	        		game.setScreen(new leader(game));
	        		dispose();
	        	}
	        	else if (select == 0){
	        		Gdx.app.exit();
	        		}
	        }
        	}else if(mainselect == 1){
        		 if (Gdx.input.isKeyJustPressed(Keys.A)){
     	        	index = 0;
     	        	index2 = 1;
     	        	}
        		 else if (Gdx.input.isKeyJustPressed(Keys.D)){
     	        	index = 1;
     	        	index2 = 0;
     	        	}
        		 else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
        			 mainselect = 0;
   	        		}
        		 else if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
        			 if (index == 0){
        				 game.setScreen(new selectstage(game));
        				 dispose();
        			 }else {
        				 mainselect = 2;
        			 }
  	        }
        	}else if(mainselect == 2){
        		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
       			 mainselect = 1;
  	        		}
       		 	if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
       		 		game.setScreen(new selectrank(game, txfUsername.getText()));
       		 		dispose();
       		 	}
        	}
			// TODO Auto-generated method stub
	        batch.draw(button1, Gdx.graphics.getWidth()/16, 11*Gdx.graphics.getHeight()/20);
	        batch.draw(button2, Gdx.graphics.getWidth()/16, 9*Gdx.graphics.getHeight()/20);
	        batch.draw(button3, Gdx.graphics.getWidth()/16, 7*Gdx.graphics.getHeight()/20);
	        batch.draw(button4, Gdx.graphics.getWidth()/16, 5*Gdx.graphics.getHeight()/20);
	        batch.draw(button5, Gdx.graphics.getWidth()/16, 3*Gdx.graphics.getHeight()/20);
	        if (mainselect == 1){
	        	batch.draw(texselect.get(4),8*Gdx.graphics.getWidth()/20, 4*Gdx.graphics.getHeight()/20);
    	    	batch.draw(texselect.get(0+index),(float) (21*Gdx.graphics.getWidth()/50) ,6*Gdx.graphics.getHeight()/20 ,(float)(3.5*Gdx.graphics.getWidth()/20), (float)3.5*Gdx.graphics.getHeight()/20);
    	    	batch.draw(texselect.get(2+index2),(float) (30.5*Gdx.graphics.getWidth()/50) ,6*Gdx.graphics.getHeight()/20  ,(float)(3.5*Gdx.graphics.getWidth()/20), (float)3.5*Gdx.graphics.getHeight()/20);
	        }
	        if (mainselect == 2){
	        	batch.draw(texselect.get(5),8*Gdx.graphics.getWidth()/20, 4*Gdx.graphics.getHeight()/20);
	        }
			batch.end();
			if (mainselect == 2){
				stage.act(delta);
				stage.draw();
	        }
			
		}
		@Override
		public void hide() {
			// TODO Auto-generated method stub
			sound.stop();

		}
		public static int randInt(int min, int max) {

		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum;
		}

	}
