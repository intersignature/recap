package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class boss_stage3 extends TopOfGen {
	private int hp = 50;
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public boss_stage3(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i, int unittype,int size) {
		super(aaa, collisionLayer, tex, i, unittype, size);
		setAmmocooldown(20);
		setAmmoofgun(50);
		setReloadtime(150);
		setAllow(false);
	}
	public void draw(Batch Batch, Player1 player,Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (!isAllow()){
			setSpawn(10);
		}
		else{
			ai(allenemy, player, j, player2);
			super.draw(Batch, player, player2, allenemy, j);
		}
	}
	private void ai(ArrayList<TopOfGen> allenemy, Player1 player, int j, Player2 player2) {
		enemy_power(allenemy, player, player2);
		setUse_ammocooldown(getUse_ammocooldown()- 1);
	}
	private int setcd = 0;
	private ArrayList<Player> send= new ArrayList<Player>();
	private boolean tag;
	private int delay = 0; 
	private void enemy_power(ArrayList<TopOfGen> allenemy, Player1 player, Player2 player2) {
		int x = 0;
		send.add(player);
		if (player2.isOnline()){
			tag = checkwho(player, player2);
			send.add(player2);
			if (tag)
				x = 1;
		}
		if (getX() > send.get(x).getX()+send.get(x).getWidth()){
			setFire(true);
		}
		else if (getX() < send.get(x).getX()){
			setFire(false);
		}
		int i = 1;
		if (!isFire()){
			i = -1;
		}
		if (send.get(x).getHitbox().overlaps(getHitbox())){
			getSpeed1().goto0();
			if (delay <= 0){
			send.get(x).setDmg(10);
			delay = 100;
			}
		}
		else if (checkxblockleft() || checkxblockright()){
			if (getSpeed1().getSpeed() == 0){
				if (checkxblockleft()){
					setCanjump(true);
					getSpeed1().stlboss3();
				}
				else {
					setCanjump(true);
					getSpeed1().strboss3();
				}
			}
			jump();
		}
		else if (getX() > send.get(x).getX()){
			setCanjump(true);
			getSpeed1().stlboss3();
		}
		else if (getX() < send.get(x).getX()){
			setCanjump(true);
			getSpeed1().strboss3();
		}
		fire(send.get(x), setcd);
		delay -= 1;
	}
}
