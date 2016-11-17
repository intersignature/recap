package com.game.only.ai;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class boss_stage2 extends TopOfGen {
	private ArrayList<Rectangle> trap = new ArrayList<Rectangle>();
	private int hp = 50;
	private float setposx;
	private float oldx = 0;
	private int trapcooldown = 0;
	private Texture tex;
	private boolean setgood = true;
	public boss_stage2(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i, int unittype,
			int size) {
		super(aaa, collisionLayer, tex, i, unittype, size);
		this.tex = new Texture("img/b_8.png");
	}
	public void draw(Batch Batch, Player1 player,Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (!isAllow()){
			setSpawn(10);
		}
		else{
		ai(allenemy, player, j, player2);
		}
		trapupdate(player,player2);
		super.draw(Batch, player, player2, allenemy, j);
		for (Rectangle item : trap){
			Batch.draw(tex, item.getX(), item.getY(),item.getWidth(),item.getHeight());
		}
	}
	private void ai(ArrayList<TopOfGen> allenemy, Player1 player, int j, Player2 player2) {
		//System.out.println(getSpeed1().getSpeed());
		if (setgood){
			createpostogo(player,  player2);
			setgood = false;
		}
		else {
			moveteam();
		}
		if (trapcooldown <= 0){
			createtrap();
			trapcooldown = 30;
		}
		trapcooldown -= 1;
	}
	private void createtrap() {
		trap.add(new Rectangle(getX(),getY() + 5,32,16));
	}
	int[] arraytrapremove = new int[999];
	private ArrayList<Player> send = new ArrayList<Player>();
	private boolean tag;
	private void trapupdate(Player1 player, Player2 player2){
		for (int i = 0; i < trap.size(); i++){
			arraytrapremove[i] = 0;
			if (trap.get(i).overlaps(player.getHitbox())){
				player.setDmg(1);
				arraytrapremove[i] = 1;
			}
			else if (trap.get(i).overlaps(player2.getHitbox())){
				player2.setDmg(1);
				arraytrapremove[i] = 1;
			}
		}
		for (int i = 0; i < trap.size(); i++){
			if (arraytrapremove[i] == 1){
				trap.remove(i);
				arraytrapremove[i] = 0;
			}
		}
	}
	float[] posset = {3000, 7000};
	int x = 0;
	private void createpostogo(Player1 player, Player2 player2) {
		if (x == 0){
			x = 1;
		}
		else {
			x = 0;
		}
	}
	private void moveteam(){
		if (getX() < (posset[x] + 30) && getX() > (posset[x] - 30)){
			getSpeed1().goto0();
			setgood = true;
		}
		else if (checkxblockleft() || checkxblockright()){
			jump();
		}
		else if (getX() > posset[x]){
			setCanjump(true);
			getSpeed1().stl();
		}
		else if (getX() < posset[x]){
			setCanjump(true);
			getSpeed1().str();
		}
	}
}