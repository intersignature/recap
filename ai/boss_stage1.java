package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class boss_stage1 extends TopOfGen{
	private Texture tex;
	private int hp = 50;
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	private boolean end = false;
	private TextureAtlas playerAtlas;
	private Animation boom1;
	public boss_stage1(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer, Texture tex, int i, int unittype,int size) {
		super(aaa, collisionLayer, tex, i, unittype, size);
		this.tex = tex;
		setAmmocooldown(10);
		setAmmoofgun(100);
		setReloadtime(150);
		setAllow(false);
		playerAtlas = new TextureAtlas("boom/boom.pack");
		boom1 = new Animation(1 / 15f, playerAtlas.findRegions("ex"));
		boom1.setPlayMode(Animation.PlayMode.NORMAL);
	}
	public void draw(Batch Batch, Player1 player,Player2 player2, ArrayList<TopOfGen> allenemy, int j) {
		hp = checkdeaddmg(hp);
		if (!isAllow()){
			setSpawn(10);
		}
		else{
		ai(allenemy, player, j, player2);
		super.draw(Batch, player, player2, allenemy, j);
		onairbomb(player, player2);
		for (Rectangle item : allbomb){
			Batch.draw(tex, item.x, item.y, 64, 64);
		}
		for (Rectangle item : allboom){
			Batch.draw(boom1.getKeyFrame(0), item.x, item.y, 128, 128);
		}
		allboom.clear();
		}
	}
	private void ai(ArrayList<TopOfGen> allenemy, Player1 player, int j, Player2 player2) {
		enemy_power(allenemy, player, player2);
		setUse_ammocooldown(getUse_ammocooldown()- 1);
	}
	private int setcd = 0;
	private int cd = 30;
	private ArrayList<Player> send= new ArrayList<Player>();
	private boolean tag;
	private int delay;
	private Rectangle ammo;
	private int bombcooldown = 1;
	public void enemy_power(ArrayList<TopOfGen> allenemy, Player1 player, Player2 player2){
		int x = 0;
		send.add(player);
		if (player2.isOnline()){
			tag = checkwho(player, player2);
			send.add(player2);
			if (tag)
				x = 1;
		}
		if (!isCanjump()){
			if (bombcooldown <= 0){
				bombcooldown = 1;
				createbomb();
			}
			delay = 200;
		}
		else if (delay()){
			getSpeed1().setSpeed(0);
			delay -= 1;
		}
		else{
			movetoatkbysuperjump(send.get(x), allenemy, cd);
		}
		if (isCanjump()){
			fire(send.get(x), setcd);
		}
		bombcooldown -= 1;
	}
	private ArrayList<Rectangle> allbomb = new ArrayList<Rectangle>();
	private Integer ammobombcount = new Integer(0);
	private ArrayList<Integer> allammobombcount = new ArrayList<Integer>();
	private boolean sw = false;
	private void createbomb() {
			ammo = new Rectangle();
			float y = 40 * -1;
			ammo.set(getX() , getY()+20+y, 64, 64);
			allbomb.add(ammo);
			allammobombcount.add(ammobombcount);
	}
	private ArrayList<Rectangle> allboom= new ArrayList<Rectangle>();
	private int[] rebomb = new int[100];
	private void onairbomb(Player1 player, Player2 player2){
		float delta = Gdx.graphics.getDeltaTime();
		int mem = -1;
		for (int i = 0;i < allbomb.size(); i += 1 ){
			// type of fire && collision
			allbomb.get(i).y -= 300 * delta;
			getCollision().inc(1);
			if (getCollision().collidesRL(16, allbomb.get(i).y, 0, allbomb.get(i).x)){
				mem = i;
			}
			else if(getCollision().collidesRL(16, allbomb.get(i).y, 16, allbomb.get(i).x)){
				mem = i;
			}
			getCollision().inc(2);
			if(
			getCollision().collidesTB(16, allbomb.get(i).y, 16, allbomb.get(i).x)){
				mem = i;
			}
			else if(getCollision().collidesTB(0, allbomb.get(i).y, 16, allbomb.get(i).x)){
				mem = i;
			}/*
			if (player.getHitbox().contains(allbomb.get(i))){
				mem = i;
				player.setDmg(1);
				System.out.println("2");
			}
			if (player2.isOnline()){
				if (player2.getHitbox().contains(allbomb.get(i))){
					mem = i;
					player2.setDmg(1);
				}
			}*/
			// set x, y
			// inc count
			if (player.getHitbox().overlaps(allbomb.get(i))){
				mem = i;
				player.setDmg(1);
			}
			if (player2.isOnline()){
				if (player2.getHitbox().overlaps(allbomb.get(i))){
					mem = i;
					player2.setDmg(1);
				}
			}
			allammobombcount.set(i, allammobombcount.get(i)+1);
			if (allammobombcount.get(i) == 200){
				mem = i;
			}
			if (mem >= 0){
				boom = new Rectangle(allbomb.get(i).x,allbomb.get(i).y, 128, 128);
				if (player.getHitbox().overlaps(boom)){
					mem = i;
					player.setDmg(1);
				}
				if (player2.isOnline()){
					if (player2.getHitbox().overlaps(boom)){
						mem = i;
						player2.setDmg(1);
					}
				}
				allboom.add(boom);
				rebomb[i] = 1;
			}
			mem = -1;
		}
		for (int x = 0; x < allbomb.size(); x++){
			if (rebomb[x] == 1){
				//System.out.println("x: "+x);
				allammobombcount.remove(x);
				allbomb.remove(x);
				rebomb[x] = 0;
			}
		}
	}
	private boolean delay() {
		if (delay >= 1){return true;}
		return false;
	}
	Rectangle boom = new Rectangle();
	private void movetoatkbysuperjump(Player player, ArrayList<TopOfGen> allenemy, int cd) {
		if (getX() > player.getX()){
			superjumpv2(-1);
		}
		else if (getX() < player.getX()){
			superjumpv2(1);
		}
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
}
