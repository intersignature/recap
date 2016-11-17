package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class boss_stage4 extends TopOfGen {
	private int hp = 50;
	private int setcd = 0;
	private int cd = 50;
	private boolean tag;
	private ArrayList<Player> send= new ArrayList<Player>();
	private boolean use;
	private int delay = 0;
	public boss_stage4(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i, int unittype,int size) {
		super(aaa, collisionLayer, tex, i, unittype, size);
		setAmmocooldown(10);
		setAmmoofgun(7);
		setReloadtime(200);
	}
	public void draw(Batch Batch, Player1 player, Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (!isAllow()){
			setSpawn(10);
		}
		else {
			ai(allenemy, player, j, player2);
		}
		super.draw(Batch, player, player2, allenemy, j);
	}
	public void ai(ArrayList<TopOfGen> allenemy, Player1 player, int j, Player2 player2){
		enemy_power(allenemy, player, player2);
		setUse_ammocooldown(getUse_ammocooldown()- 1);
	}
	public void enemy_power(ArrayList<TopOfGen> allenemy, Player1 player, Player2 player2){
		int x = 0;
		send.add(player);
		if (player2.isOnline()){
			tag = checkwho(player, player2);
			send.add(player2);
			if (tag)
				x = 1;
		}
		if (delay <= 0){
			delay = 1000;
			if (getX() > send.get(x).getX()+send.get(x).getWidth()){
				setFire(true);
			}
			else if (getX() < send.get(x).getX()){
				setFire(false);
			}
			int i = 1;
			if (!isFire()){ i = -1;}
			this.getHitbox().setX(send.get(x).getX() + (50 * i));
			this.getHitbox().setY(send.get(x).getY() + 10);
			send.get(x).setDmg(25);
		}
		else {
			setcd = movetoatkset(send.get(x), allenemy, cd);
			fire(send.get(x), setcd );
		}
		delay -= 1;
	}
	public int movetoatkset(Player player, ArrayList<TopOfGen> allenemy, int cd){
		int powercd = cd;
		if ((getX() > (player.getX() - 20) && getX() < (player.getX() + 20)) || checkenemy(allenemy, player)){
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
		else if ((getX() > (player.getX() - 1000)) && (getX() < (player.getX() + 1000))){
			float max = Math.max(getX(), player.getX());
			float min = Math.min(getX(), player.getX());
			float mmm = max - min;
			float x = ((mmm * 200)/1000)/2;
			if (getX() > player.getX()){
				setCanjump(true);
				getSpeed1().stlsetspeed(x);
			}
			else {
				setCanjump(true);
				getSpeed1().strsetspeed(x);
			}
			powercd = (int) ((mmm * cd)/100);
		}
		else if (getX() > player.getX()){
			setCanjump(true);
			getSpeed1().stl();
		}
		else if (getX() < player.getX()){
			setCanjump(true);
			getSpeed1().str();
		}
		return powercd;
	}
	
}
