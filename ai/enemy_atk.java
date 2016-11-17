package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class enemy_atk extends TopOfGen{
	private int hp = 5;
	private int setcd = 0;
	private int cd = 30;
	private boolean tag;
	public enemy_atk(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i,int unit,int size) {
		super(aaa, collisionLayer, tex, i, unit, size);
		setAmmocooldown(5);
		setAmmoofgun(10);
		setReloadtime(200);
	}
	public void ai(ArrayList<TopOfGen> allenemy, Player1 player, int j, Player2 player2){
		if (j == 1){
			if (!player.isWof() && !player2.isWof()){
				enemy_wait(allenemy, player);
			}else {
				enemy_power(allenemy, player, player2);
			}
		}else if (j == 2){
			if (!player.isWof2() && !player2.isWof()){
				enemy_wait(allenemy, player);
			}else {
				enemy_power(allenemy, player, player2);
			}
		}
		setUse_ammocooldown(getUse_ammocooldown()- 1);
	}
	private ArrayList<Player> send= new ArrayList<Player>();
	public void enemy_power(ArrayList<TopOfGen> allenemy, Player1 player, Player2 player2){
		int x = 0;
		send.add(player);
		if (player2.isOnline()){
			tag = checkwho(player, player2);
			send.add(player2);
			if (tag)
				x = 1;
		}
		setcd = movetoatk(send.get(x), allenemy, cd);
		fire(send.get(x), setcd );
	}
	@Override
	public void draw(Batch Batch, Player1 player, Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (isAllow()){
			ai(allenemy, player, j, player2);
		}
		super.draw(Batch, player, player2, allenemy, j);
	}
	
}
