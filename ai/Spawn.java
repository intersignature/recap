package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class Spawn extends TopOfGen{
	private int hp = 10;
	private int deadcount = 0;

	public int getDeadcount() {
		return deadcount;
	}
	public void setDeadcount(int deadcount) {
		this.deadcount = deadcount;
	}
	public Spawn(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i,int unit, int size) {
		super(aaa, collisionLayer, tex, i, unit, size);
	}
	public void ai(){
		
	}
	@Override
	public void draw(Batch Batch, Player1 player,Player2 player2, ArrayList<TopOfGen> allenemy,int j) {
		checkdeaddmgspawn(player, j, player2);
		setSpawn(deadcount);
		super.draw(Batch, player, player2, allenemy,j);
	}
	private void checkdeaddmgspawn(Player1 player, int j, Player2 player2) {
		hp -= getDmg();
		resetDmg();
		if (hp <= 0){
			setAllow(false);
			hp = 5;
			setDct(10);
			deadcount += 2;
			if (j == 1){
				player.setWof(false);
				player2.setWof(false);
			}else if(j == 2){
				player.setWof2(false);
				player2.setWof2(false);
			}
		}
	}
}
