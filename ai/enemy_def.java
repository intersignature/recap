package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class enemy_def extends TopOfGen{

	private int hp = 5;
	private int setcd = 0;
	private float cd = 30;
	public enemy_def(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i,int unittype, int size) {
		super(aaa, collisionLayer, tex, i, unittype, size);
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
			if (!player.isWof2() && !player2.isWof2()){
				enemy_wait(allenemy, player);
			}else {
				enemy_power(allenemy, player, player2);
			}
		}
		setUse_ammocooldown(getUse_ammocooldown()- 1);
	}
	private ArrayList<Player> send= new ArrayList<Player>();
	private boolean tag;
	public void enemy_power(ArrayList<TopOfGen> allenemy, Player1 player, Player2 player2){
		int x = 0;
		send.add(player);
		if (player2.isOnline()){
			tag = checkwho(player, player2);
			send.add(player2);
			if (tag)
				x = 1;
		}
		movetodef(allenemy, send.get(x));
		float max = Math.max(getX(), send.get(x).getX());
		float min = Math.min(getX(), send.get(x).getX());
		float mmm = max - min;
		setcd = (int) ((mmm * cd)/750);
		fire(send.get(x), setcd);
	}
	private void movetodef(ArrayList<TopOfGen> allenemy, Player player) {
		boolean left = false;
		/*if (getX() > player.getX()+player.getWidth()){
			left = true;
		}
		else if (getX() < player.getX()){
			left = false;
		}*/
		if (allenemy.get(0).getX() > player.getX()+player.getWidth()){
			left = true;
		}
		else if (allenemy.get(0).getX() < player.getX()){
			left = false;
		}
		float pos = setdefline(left, allenemy);
		if (getX() < (pos + 30) && getX() > (pos - 30)){
			getSpeed1().goto0();
		}
		else if (checkxblockleft() || checkxblockright()){
			jump(); 
		}
		else if (getX() > pos){
			setCanjump(true);
			getSpeed1().stl();
		}
		else if (getX() < pos){
			setCanjump(true);
			getSpeed1().str();
		}
	}
	private float setdefline(boolean left, ArrayList<TopOfGen> allenemy) {
		int x = 1;
		if (left){
			x = -1;
		}
		float spawn = allenemy.get(0).getX();
		int count = 0;
		for (TopOfGen item : allenemy){
			if (item.getUnittype() == 2){
				count += 1;
			}
		}
		int setpos = getPosition() - (allenemy.size() - count);
		return spawn + 125*(setpos+1)*x;
	}
	@Override
	public void draw(Batch Batch, Player1 player,Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (isAllow()){
			ai(allenemy, player, j, player2);
		}
		super.draw(Batch, player,player2, allenemy, j);
	}
}
