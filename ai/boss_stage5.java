package com.game.only.ai;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.game.only.move.Over;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class boss_stage5 extends TopOfGen {
	private Rectangle li ;
	private Over collision;
	private Texture lishow;
	private int hp = 50;
	private int powerdelay[] = {0, 450, 1250, 250};
	private int powerdelayget[] = {5, 450, 1250, 250};
	public boss_stage5(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i, int unittype,int size) {
		super(aaa, collisionLayer, tex, i, unittype, size);
		lishow = new Texture("img/b_8.png");
		collision = new Over(collisionLayer);
	}
	public void draw(Batch Batch, Player1 player, Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (!isAllow()){
			setSpawn(10);
		}else{
			ai(allenemy, player, j, player2);
		}
		super.draw(Batch, player, player2, allenemy, j);
		onairlastboss();
		updateraindrop(player, player2);
		Batch.draw(lishow, li.getX(), li.getY(), li.getWidth(), li.getHeight());
		for (Rectangle item:rain){
			Batch.draw(lishow, item.getX(), item.getY(), item.getWidth(), item.getHeight());
		}
	}
	private void onairlastboss() {
		// TODO Auto-generated method stub
		
	}
	private ArrayList<Player> send= new ArrayList<Player>();
	private boolean tag;
	private ArrayList<Rectangle> rain = new ArrayList<Rectangle>();
	private boolean collisionY;
	private void ai(ArrayList<TopOfGen> allenemy, Player1 player, int j, Player2 player2) {
		int x = 0;
		send.add(player);
		if (player2.isOnline()){
			tag = checkwho(player, player2);
			send.add(player2);
			if (tag)
				x = 1;
		}
		for (int i = 0; i <3 ; i++){
			if (powerdelay[i] <= 0){
				createammostyle(i, send, x);
				powerdelay[i] = powerdelayget[i];
			}
			powerdelay[i] -= 1;
		}
		if (open >= 1){
			createrain(send, x);
			open--;
		}
	}
	int open = 90;
	private void createammostyle(int i, ArrayList<Player> send2, int x) {
		if (i == 0){
			createball(0);
			createli(send2,x);
		}
		else if (i == 1){
			createball(1);
			open = 90;
		}
	}
	private void createli(ArrayList<Player> send2, int x) {
		int posrnd= randInt(0, 500);
		float posx = send2.get(x).getX() - 250 + posrnd;
		posrnd= randInt(0, 100);
		float posy = send2.get(x).getY() - 50 + posrnd;
		li = new Rectangle((float) posx ,posy, 64, 64);
		if (li.overlaps(send2.get(x).getHitbox())){
			send2.get(x).setDmg(1);
		}
	}
	private void createrain(ArrayList<Player> send2, int x) {
		int posrnd= randInt(0, 600);
		float posx = send2.get(x).getX() - 300 + posrnd;
		rain.add(new Rectangle((float) posx ,send2.get(x).getY() + 300, 32, 32));
	}
	int[] arrayrainremove = new int[100];
	private void updateraindrop(Player1 player, Player2 player2){
		for (int i = 0; i < rain.size(); i++){
			arrayrainremove[i] = 0;
			rain.get(i).setY(rain.get(i).getY() - 5);
			collision.inc(2);
			collisionY = collision.collidesTB(0, rain.get(i).getY(), rain.get(i).getWidth(),rain.get(i).getX());
			if (collisionY){
				arrayrainremove[i] = 1;
			}
			else if (rain.get(i).overlaps(player.getHitbox())){
				player.setDmg(1);
				arrayrainremove[i] = 1;
			}
			else if (rain.get(i).overlaps(player2.getHitbox())){
				player2.setDmg(1);
				arrayrainremove[i] = 1;
			}
		}
		for (int i = 0; i < rain.size(); i++){
			if (arrayrainremove[i] == 1){
				rain.remove(i);
				arrayrainremove[i] = 0;
			}
		}
	}
	Rectangle ball = new Rectangle();
	private int pos = 0;
	private void createball(int i) {
		ball = new Rectangle((this.getX() + this.getWidth()) / 2 ,(this.getY() + this.getWidth()) / 2, 64*3, 64*3);
		pos = i;
	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
