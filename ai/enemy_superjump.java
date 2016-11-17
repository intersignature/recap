package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class enemy_superjump extends TopOfGen{
	private int hp = 5;
	private int setcd = 0;
	private int cd = 30;
	public enemy_superjump(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i, int unittype, int size) {
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
		setcd = movetoatkbysuperjump(send.get(x), allenemy, cd);
		fire(send.get(x), setcd);
	}
	private int movetoatkbysuperjump(Player player, ArrayList<TopOfGen> allenemy, int cd) {
		int powercd = cd;
		if (getX() > player.getX() + 1000 && cansuperjumptoleft(allenemy)){
			superjump(-1);
		}
		else if (getX() < player.getX() - 1000 && cansuperjumptoright(allenemy)){
			superjump(1);
		}
		else if (((getX() > (player.getX() - 20) && getX() < (player.getX() + 20)) || checkenemy(allenemy, player)) && isCanjump()){
			getSpeed1().goto0();
		}
		else if (checkxblockleft() || checkxblockright()){
			if (getSpeed1().getSpeed() == 0){
				if (checkxblockleft()){
					setCanjump(true);
					getSpeed1().stl();
				}
				else {
					setCanjump(true);
					getSpeed1().str();
				}
			}
			jump();
		}
		else if ((getX() > (player.getX() - 750)) && (getX() < (player.getX() + 750)) && isCanjump()){
			float max = Math.max(getX(), player.getX());
			float min = Math.min(getX(), player.getX());
			float mmm = max - min;
			float x = (mmm * 200)/750;
			if (getX() > player.getX()){
				setCanjump(true);
				getSpeed1().stlsetspeed(x);
			}
			else {
				setCanjump(true);
				getSpeed1().strsetspeed(x);
			}
			powercd = (int) ((mmm * cd)/750);
		}
		else if (getX() > player.getX() && isCanjump()){
			setCanjump(true);
			getSpeed1().stl();
		}
		else if (getX() < player.getX() && isCanjump()){
			setCanjump(true);
			getSpeed1().str();
		}
		return powercd;
	}
	private boolean cansuperjumptoleft(ArrayList<TopOfGen> allenemy) {
		if (allenemy.get(0).getX() > getX()){
			return true;
		}
		return false;
	}private boolean cansuperjumptoright(ArrayList<TopOfGen> allenemy) {
		if (allenemy.get(0).getX() < getX()){
			return true;
		}
		return false;
	}
	@Override
	public void draw(Batch Batch, Player1 player,Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (isAllow()){
			ai(allenemy, player, j, player2);
		}
		super.draw(Batch, player, player2, allenemy, j);
	}
}
