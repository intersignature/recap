package com.game.only.player;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.only.ai.TopOfGen;
import com.game.only.move.Aim;
import com.game.only.move.Over;
import com.game.only.move.Speed;

import obj.ammotype;
import obj.box;

public class Player extends Sprite{
	private boolean online = false;
	private boolean canjump;
	private boolean oksound = true;
	public boolean isGgez() {
		return ggez;
	}
	public void setGgez(boolean ggez) {
		this.ggez = ggez;
	}
	private int time = 0;
	private boolean ggez = false;
	private Vector2 velocity = new Vector2();
	
	private float g = 100 * 10f, animationTime = 0;

	private ArrayList<Animation> barban;
	
	private Speed speed1 = new Speed();

	private Over collision; 
	
	private boolean fire , ctrl = false;
	
	private ArrayList<Texture> tex;
	
	private Texture tex1;

	private int stack = 0;
	
	private boolean wof = false; 
	
	public boolean isWof() {
		return wof;
	}
	public void setWof(boolean wof) {
		this.wof = wof;
	}
	private Rectangle ammo = new Rectangle();
	private ArrayList<Rectangle> allammo = new ArrayList<Rectangle>();
	private Integer ammocount = new Integer(0);
	private ArrayList<Integer> allammocount = new ArrayList<Integer>();
	private int[] ammodata = {0,0,0};
	private ArrayList<int[]> allammodata = new ArrayList<int[]>();

	private int hp = 99999, num = 0;

	private boolean[] arraykey = {false,false,false,false};
	
	private Aim aim = new Aim();
	private Texture hitthetag;
	private boolean wof2 = false;
	private int shoot;
	private ArrayList<Texture> boxammotype = new ArrayList<Texture>();
	private ArrayList<box> allitem = new ArrayList<box>();
	private boolean cnt_shoot = false;
	Animation boom;
	private TextureAtlas playerAtlas;
	public boolean isCnt_shoot() {
		return cnt_shoot;
	}
	public void setCnt_shoot(boolean cnt_shoot) {
		this.cnt_shoot = cnt_shoot;
	}
	public boolean isWof2() {
		return wof2;
	}
	public void setWof2(boolean wof2) {
		this.wof2 = wof2;
	}
	public Player(ArrayList<Animation> barban , TiledMapTileLayer collisionLayer, int num,ArrayList<Texture> tex){
		super(barban.get(1).getKeyFrame(0), 0, 0, 64, 64);
		this.barban = barban;
		collision = new Over(collisionLayer);
		this.num = num;
		this.tex = tex;
		boxammotype.add(new Texture("obj/auto.png"));
		boxammotype.add(new Texture("obj/laser.png"));
		boxammotype.add(new Texture("obj/rocket.png"));
		boxammotype.add(new Texture("obj/first_aid.png"));
		playerAtlas = new TextureAtlas("boom/boom.pack");
		boom = new Animation(1 / 15f, playerAtlas.findRegions("ex"));
		boom.setPlayMode(Animation.PlayMode.NORMAL);
		//boxammotype.add(new Texture("obj/ng123.png"));
		hitthetag = new Texture("img/boom_edit.png");
		firepic.add(new Texture("img/shoot_L.png"));
		firepic.add(new Texture("img/shoot_R.png"));
		tex1 = new Texture("img/b_11.png");
		playerAtlas = new TextureAtlas("boom/boom.pack");
		boom321 = new Animation(1 / 15f, playerAtlas.findRegions("ex"));
		boom321.setPlayMode(Animation.PlayMode.NORMAL);
		rev = new Texture("img/revive.png");
	}
	public void check(){
		stack = 0;
		for (boolean item : arraykey){
			if (item){
				stack += 1;
			}
		}
	}
	private int ammoauto = 0;
	private boolean autofire = false;
	private int ammocooldown = 10;
	private int use_ammocooldown = 0;
	private int use_ammoofgun = 10;
	public int getUse_ammoofgun() {
		return use_ammoofgun;
	}
	public void setUse_ammoofgun(int use_ammoofgun) {
		this.use_ammoofgun = use_ammoofgun;
	}
	private int reload = 0;
	private int showammo = 999;
	private Rectangle boom1;
	private boolean boomshow;
	private int timeboom;
	private float animationTime1;
	public void setcooldown(){
		int[] gun = {0, -5, 3, 10};
		use_ammocooldown = ammocooldown + gun[typecheck];
		use_ammoofgun -= 1;
		if (typecheck > 0){
			showammo -= 1;
		}
	}
	public void cooldowntime(){
		use_ammocooldown -= 1;
		reload -= 1;
	}
	public boolean checkcooldown(){
		return use_ammocooldown <= 0;
	}
	public void createammolazer(){
		if (!checkcooldown() || !checkreload()){
			cnt_shoot = true;
			return ;
		}
		cnt_shoot = false;
		setLazer(getLazer() - 1);
		ammo = new Rectangle();
		if (ctrl){
		float x = 40 * aim.getx();
		float y = 40 * aim.gety();
		ammo.set(getX() + 20 + x, getY()+20+y, 16, 16);
		ammodata = new int[3];
		ammodata[0] = aim.getx();
		ammodata[1] = aim.gety();
		ammodata[2] = 1;
		}
		else{
			int x1 = 1;
			if (fire){
				x1 = -1;
			}
			float x = 40 * x1;
			float y = 40 * 0;
			ammo.set(getX() + 20 + x, getY()+20+y, 16, 16);
			ammodata = new int[3];
			ammodata[0] = x1;
			ammodata[1] = 0;
			ammodata[2] = 1;
		}
		allammodata.add(ammodata);
		allammo.add(ammo);
		allammocount.add(ammocount);
		shoot = 30;
		setcooldown();
		checkammo();
	}
	public void createrocket(int power){
		if (!checkcooldown() || !checkreload()){
			cnt_shoot = true;
			return ;
		}
		cnt_shoot = false;
		setRocket(getRocket() - 1);
		ammo = new Rectangle();
		if (ctrl){
		float x = 40 * aim.getx() ;
		float y = 40 * aim.gety();
		ammo.set(getX() + 20 + x, getY()+20+y, 16, 16);
		ammodata = new int[3];
		ammodata[0] = aim.getx();
		ammodata[1] = aim.gety();
		ammodata[2] = power;
		}
		else{
			int x1 = 1;
			if (fire){
				x1 = -1;
			}
			float x = 40 * x1;
			float y = 40 * 0;
			ammo.set(getX() + 20 + x, getY()+20+y, 16, 16);
			ammodata = new int[3];
			ammodata[0] = x1;
			ammodata[1] = 0;
			ammodata[2] = power;
		}
		allammodata.add(ammodata);
		allammo.add(ammo);
		allammocount.add(ammocount);
		shoot = 30;
		setcooldown();
		checkammo();
	}
	public void createammo(){
		if (!checkcooldown() || !checkreload()){
			cnt_shoot = true;
			return ;
		}
		cnt_shoot = false;
		setAmmoauto(getAmmoauto() - 1);
		ammo = new Rectangle();
		if (ctrl){
		float x = 40 * aim.getx();
		float y = 40 * aim.gety();
		ammo.set(getX() + 20 + x, getY()+20+y, 16, 16);
		ammodata = new int[3];
		ammodata[0] = aim.getx();
		ammodata[1] = aim.gety();
		ammodata[2] = 0;
		}
		else{
			int x1 = 1;
			if (fire){
				x1 = -1;
			}
			float x = 40 * x1;
			float y = 40 * 0;
			ammo.set(getX() + 20 + x, getY()+20+y, 16, 16);
			ammodata = new int[3];
			ammodata[0] = x1;
			ammodata[1] = 0;
			ammodata[2] = 0;
		}
		allammodata.add(ammodata);
		allammo.add(ammo);
		allammocount.add(ammocount);
		shoot = 30;
		setcooldown();
		checkammo();
	}
	
	private void checkammo() {
		int[] reloadset = {40, 60, 50, 10};
		if (use_ammoofgun <= 0){
			reload = reloadset[typecheck];
		}
	}
	private void setammo() {
		if (checkreload() && !(use_ammoofgun > 0)){
			int[] ammoset = {10, 25, 10, 1};
			use_ammoofgun = ammoset[typecheck];
			//
		}
	}
	private boolean checkreload() {
		return reload  <= 0;
	}
	public ArrayList<Rectangle> getAllammo() {
		return allammo;
	}
	public void setAllammo(ArrayList<Rectangle> allammo) {
		this.allammo = allammo;
	}
	public void onair(float delta, ArrayList<TopOfGen> allenemy, ArrayList<TopOfGen> allenemy2, TopOfGen boss){
		int mem = -1;
		for (int i = 0;i < allammo.size(); i += 1 ){
			// type of fire && collision
			//System.out.println(allammodata.get(i)[2]);
			allammo.get(i).x += 450 *allammodata.get(i)[0]* delta + (allammodata.get(i)[2]/10);
			allammo.get(i).y += 450 *allammodata.get(i)[1]* delta;
			collision.inc(1);
			if (collision.collidesRL(16, allammo.get(i).y, 0, allammo.get(i).x)){
				mem = i;
			}
			else if(collision.collidesRL(16, allammo.get(i).y, 16, allammo.get(i).x)){
				mem = i;
			}
			collision.inc(2);
			if(
			collision.collidesTB(16, allammo.get(i).y, 16, allammo.get(i).x)){
				mem = i;
			}
			else if(collision.collidesTB(0, allammo.get(i).y, 16, allammo.get(i).x)){
				mem = i;
			}
			for (TopOfGen item : allenemy){
					if (item.getHitbox().overlaps(allammo.get(i)) && item.isAllow()){
						if (allammodata.get(i)[2] == 1){
							
						}else{
						mem = i;
						}
						item.setDmg(1);
						point += 10;
				}
			}
			for (TopOfGen item : allenemy2){
					if (item.getHitbox().overlaps(allammo.get(i)) && item.isAllow()){
						if (allammodata.get(i)[2] == 1){
							
						}else{
						mem = i;
						}
						item.setDmg(1);
						point += 10;
					}
				}
			if (boss.getHitbox().overlaps(allammo.get(i)) && boss.isAllow()){
				if (allammodata.get(i)[2] == 1){
					
				}else{
				mem = i;
				}
				boss.setDmg(1);
				point += 25;
			}
			// set x, y
			
			// inc count
			allammocount.set(i, allammocount.get(i)+1);
			
			// check ammo time
			if (allammocount.get(i) >= 300){
				mem = i;
			}
			if (mem >= 0 && allammodata.get(i)[2] >= 2){
				animationTime1 = 0;
				boomshow = true;
				timeboom = 20;
				boom1 = new Rectangle(allammo.get(i).x - (256 / 2) ,allammo.get(i).y, 256, 156);
				for (TopOfGen item : allenemy){
					if (item.getHitbox().overlaps(boom1) && item.isAllow()){
						item.setDmg(20);
						point += 10;
				}
			}
			for (TopOfGen item : allenemy2){
					if (item.getHitbox().overlaps(boom1) && item.isAllow()){
						item.setDmg(20);
						point += 10;
					}
				}
			if (boss.getHitbox().overlaps(boom1) && boss.isAllow()){
				boss.setDmg(20);
				point += 25;
			}
			}
			else {
				boomshow = false;
			}
		}
		if (mem >= 0){
			delay = 5;
			hit[0] = allammo.get(mem).getX();
			hit[1] = allammo.get(mem).getY();
			hit[2] = 16;
			hit[3] = 16;
			hit[4] = 1;
			allammo.remove(mem);
			allammocount.remove(mem);
			allammodata.remove(mem);
		}else{
			hit[4] = 0;
		}
	}
	float[] hit = new float[5];
	private int delay;
	private int delayplayer;
	private float rndhit;
	private ArrayList<Texture> firepic = new ArrayList<Texture>();
	private int delayshot;
	public int getDelayshot() {
		return delayshot;
	}
	public void setDelayshot(int delayshot) {
		this.delayshot = delayshot;
	}
	public void createitem(int i){
		allitem.add(new ammotype(boxammotype.get(i), collision, this, i));
	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	private int restime = 0;
	private Animation boom321;
	private Texture rev;
	public void draw(Batch Batch, ArrayList<TopOfGen> allenemy, ArrayList<TopOfGen> allenemy2, TopOfGen boss, Player fri){
		rocketopen();
		setammo();
		checkdeaddmg();
		if (isAllow()){
			dead(Gdx.graphics.getDeltaTime());
			onair(Gdx.graphics.getDeltaTime(), allenemy, allenemy, boss);
		}
		else if(res && fri.allow){
			if (getRestime() >= 125){ 
				fri.allow = false;
				fri.hp = 50;
				fri.ggez = false;
			}
			setRestime(getRestime() + 1);
			Batch.draw(rev, hitbox.x, hitbox.y + 50, 32, 32);
		}
		else {
		update(Gdx.graphics.getDeltaTime(), allenemy, allenemy2,boss);
		}
		checkchangetype();
		checkrocket();
		checklazer();
		autofire();
		super.draw(Batch);//Batch.draw(tex, getX()+60, getY()+20, 16, 16);
		delay -= 1;
		for (int i = 0;i < allammo.size(); i++ ){
			int x = 0;
			if (allammodata.get(i)[2] == 1){
				x = 2;
			}
			if (allammodata.get(i)[2] > 2){
				x = 3;
			}
			Batch.draw(tex.get(x), allammo.get(i).x, allammo.get(i).y, 16*(x + 1), 16);
		}
		int[] item = new int[999];
		for (int i = 0; i < allitem.size(); i++){
			item[i] = 0;
			allitem.get(i).update(Gdx.graphics.getDeltaTime());
			Batch.draw(allitem.get(i).getTexture(), allitem.get(i).getX(), allitem.get(i).getY(), allitem.get(i).getWidth(), allitem.get(i).getHeight());
			if (allitem.get(i).checkplayer(this)){
				item[i] = 1;
			}
		}
		if (boomshow || timeboom > 0){
			animationTime1 += Gdx.graphics.getDeltaTime();
			Batch.draw(boom.getKeyFrame(animationTime1), boom1.x, boom1.y, boom1.width, boom1.height);
			boomshow = false;
		}
		timeboom -= 1;
		for (int i = 0; i < allitem.size() ;i++){
			if (item[i] == 1){
			allitem.remove(i);
			}
		}
		if (hit[4] == 1 || delay > 0){
			Batch.draw(hitthetag, hit[0] - 8, hit[1] - 8,32,32);
		}
		if (showhit || delayplayer > 0){
			Batch.draw(hitthetag, hitbox.getX() -4 + rndhit, hitbox.getY() + 8,32,32);
		}
		if (delayshot > 0){
			float setx = 0;
			setx = -13;
			int a = 1;
			if (!fire){
				setx = (getWidth() * 1);
				a = 0;
			}
			Batch.draw(firepic.get(a), getX() + setx, hitbox.getY() + 20,16,16);
		}
		createallbomb();
		onairbomb(allenemy, allenemy2,boss);
		for (Rectangle item2 : allbomb){
			Batch.draw(tex1, item2.x, item2.y, 64, 64);
		}
		for (Rectangle item2 : allboom){
			Batch.draw(boom321.getKeyFrame(0), item2.x, item2.y, 128, 128);
		}
		allboom.clear();
		delayshot -= 2;
		cooldowntime();
		airsupport -= 1;
		time -= 1;
		//Batch.draw(tex, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		//System.out.println(getX()+" "+getY()+" "+getWidth()+" "+getHeight());
		//System.out.println(hitbox.getX()+" "+hitbox.getY()+" "+hitbox.getWidth()+" "+hitbox.getHeight());
		//Batch.draw(tex, getX(), getY(), getWidth(), getHeight());
		// tem.out.println(velocity.y);
	}
	private void checklazer() {
		if (lazer <= 0 && getTypecheck() == 2){
			setTypenow(0);
		}
		
	}
	private void checkrocket() {
		if (rocket <= 0 && getTypecheck() == 3){
			setTypenow(0);
		}
		
	}
	protected void checkchangetype() {
		if (typenow == 4){
			hp += 50;
			hp = Math.min(100, hp);
			typenow = typecheck;
		}
		int[] guntype = {10, 25 ,10, 1};
		int[] allammo = {999, 250, 100, 30};
		if (checktype()){
			typecheck = typenow;
			use_ammoofgun = guntype[typecheck];
			showammo  = allammo[typecheck];
			}
	}
	public int getShowammo() {
		return showammo;
	}
	public void setShowammo(int showammo) {
		this.showammo = showammo;
	}
	private boolean checktype() {
		return typecheck != typenow;
	}
	private void dead(float deltaTime) {
		animationTime += deltaTime;
		setRegion(barban.get(12+deadside).getKeyFrame(animationTime));
		ggez = true;
	}
	public void checkwof(ArrayList<TopOfGen> allenemy, ArrayList<TopOfGen> allenemy2){
		if ((getX() > allenemy.get(0).getX() - 1500) && (getX() < allenemy.get(0).getX() + 1500) && allenemy.get(0).isAllow()){
			wof = true;
		}
		if ((getX() > allenemy2.get(0).getX() - 1500) && (getX() < allenemy2.get(0).getX() + 1500) && allenemy2.get(0).isAllow()){
			wof2 = true;
		}
	}
	public void update(float delta, ArrayList<TopOfGen> allenemy, ArrayList<TopOfGen> allenemy2, TopOfGen boss){
		if (hitbox.getY() < -100){
			hitbox.setY(750);
		}
		checkwof(allenemy, allenemy2);
		aim.aim(arraykey[0],arraykey[1],arraykey[2],arraykey[3]);
		velocity.y -= g * delta;
		speed1.update(!canjump);
		velocity.x = speed1.getSpeed();
		//System.out.println(velocity.x);
		// save old pos
		float oldX = hitbox.getX(), oldY = hitbox.getY();
		boolean collisionX = false, collisionY = false;
		// move on x
		//System.out.println(hitbox.getX());	
		hitbox.setX(hitbox.getX() + velocity.x * delta);
			//System.out.println(hitbox.getX());
			collision.inc(1);
			////////////////////////
			if (velocity.x < 0){
				collisionX = collision.collidesRL(hitbox.getHeight(), hitbox.getY(), 0, hitbox.getX());
			}else if(velocity.x > 0){
				collisionX = collision.collidesRL(hitbox.getHeight(), hitbox.getY(), hitbox.getWidth(), hitbox.getX());
			}
			//reaction 
			if(collisionX){
				hitbox.setX(oldX);
				velocity.x = 0;
			}
			////////////////////////
			// move on y
			hitbox.setY(hitbox.getY() + velocity.y * delta);
			
			collision.inc(2);
			
			if (velocity.y > 0){
				collisionY = collision.collidesTB(hitbox.getHeight(), hitbox.getY(), hitbox.getWidth(), hitbox.getX());
			}else if(velocity.y <= 0){
				collisionY = collision.collidesTB(0, hitbox.getY(), hitbox.getWidth(), hitbox.getX());
			}
			if(collisionY){
				hitbox.setY(oldY);
				if (velocity.y < 0){
					canjump = true;
				}
				velocity.y = 0;
			}
			///////////////////////////////
			// update animation
			animationset(delta);
			int w = 0;
			if (fire){
				w = 1;
			}
			setX(hitbox.getX() - 32*w);
			setY(hitbox.getY() - 3);
			onair(delta,allenemy,allenemy2,boss);
			shoot -= 1;
	}
	public void animationset(float delta){
		animationTime += delta;
		int[][] all = {{1,1,0,-1,-1,-1,0,1},{0,1,1,1,0,-1,-1,-1}};
		if (ctrl){
			for (int j = 0; j < 8;j++){
				if (aim.getx() == all[0][j] && aim.gety() == all[1][j]){
					setRegion(barban.get(j + 4).getKeyFrame(animationTime));
					if (j == 2){
						setthehitbox(hitbox.getX() ,hitbox.getY(), 64,64 / 2);
					}
					else{
					setthehitbox(hitbox.getX() ,hitbox.getY(), 64 / 2,64);
					}
					break;
				}
			}
		}
		else if (!canjump && fire){
			setRegion(barban.get(2).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);// + (64 /2)
		}
		else if (!canjump && !fire){
			setRegion(barban.get(3).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);
			
		}
		else if (velocity.x < 0){
			setRegion(barban.get(2).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);// + (64 /2)
		}else if (velocity.x > 0){
			setRegion(barban.get(3).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);
		}else if (fire && shoot >= 1){
			setRegion(barban.get(14).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);// + 64 /2
		}
		else if (!fire && shoot >= 1){
			setRegion(barban.get(15).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);
		}
		else if (fire){
			setRegion(barban.get(0).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);// + 64 /2
		}
		else if (!fire){
			setRegion(barban.get(1).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),64 / 2,64);
		}
	}
	public boolean isCanjump() {
		return canjump;
	}
	public void setCanjump(boolean canjump) {
		this.canjump = canjump;
	}
	public float getAnimationTime() {
		return animationTime;
	}
	public void setAnimationTime(float animationTime) {
		this.animationTime = animationTime;
	}
	public Speed getSpeed1() {
		return speed1;
	}
	public void setSpeed1(Speed speed1) {
		this.speed1 = speed1;
	}
	public Over getCollision() {
		return collision;
	}
	public void setCollision(Over collision) {
		this.collision = collision;
	}
	public boolean isCtrl() {
		return ctrl;
	}
	public void setCtrl(boolean ctrl) {
		this.ctrl = ctrl;
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}
	public Rectangle getAmmo() {
		return ammo;
	}
	public void setAmmo(Rectangle ammo) {
		this.ammo = ammo;
	}
	public Integer getAmmocount() {
		return ammocount;
	}
	public void setAmmocount(Integer ammocount) {
		this.ammocount = ammocount;
	}
	public ArrayList<Integer> getAllammocount() {
		return allammocount;
	}
	public void setAllammocount(ArrayList<Integer> allammocount) {
		this.allammocount = allammocount;
	}
	public int[] getAmmodata() {
		return ammodata;
	}
	public void setAmmodata(int[] ammodata) {
		this.ammodata = ammodata;
	}
	public ArrayList<int[]> getAllammodata() {
		return allammodata;
	}
	public void setAllammodata(ArrayList<int[]> allammodata) {
		this.allammodata = allammodata;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean[] getArraykey() {
		return arraykey;
	}
	public void setArraykey(boolean[] arraykey) {
		this.arraykey = arraykey;
	}
	public Aim getAim() {
		return aim;
	}
	public void setAim(Aim aim) {
		this.aim = aim;
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public float getG() {
		return g;
	}
	public void setG(float g) {
		this.g = g;
	}
	/*if (velocity.y > speed){
	velocity.y = speed;}
else if(velocity.y < -speed){
	velocity.y = -speed;}*/

	public TiledMapTileLayer getCollisionLayer() {
		return collision.getCollisionLayer();
	}
	public boolean isFire() {
		return fire;
	}
	public void setFire(boolean fire) {
		this.fire = fire;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	private Rectangle hitbox = new Rectangle();
	public void setthehitbox(float f, float h, float width, float height){
		hitbox.setX(f);
		hitbox.setY(h);
		hitbox.setWidth(width);
		hitbox.setHeight(height);
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	private int dmg = 0;
	private int deadside;
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg += dmg;
	}
	public void resetDmg() {
		this.dmg = 0;
	}
	private boolean allow = false;
	private boolean showhit = false;
	public void checkdeaddmg(){
		if (getDmg() > 0){
			rndhit= randInt(0, 8);
			showhit  = true;
			delayplayer = 5;
		}
		else {
			showhit = false;
		}
		hp -= getDmg();
		resetDmg();
		if (hp <= 0){
			if (!fire){
				deadside = 1;
			}
			else {
				deadside = 0;
			}
			setY(hitbox.getY() - 10);
			setAllow(true);
			//hp = 100;
			//setDct(500);
			/*
			allammodata.clear();
			allammo.clear();
			allammocount.clear();
			*/
		}
		delayplayer -= 1;
	}
	private int typecheck = 0;
	private int typenow = 0;
	public boolean isAllow() {
		return allow;
	}
	public void setAllow(boolean allow) {
		this.allow = allow;
	}
	public boolean isAutofire() {
		return autofire;
	}
	public void setAutofire(boolean autofire) {
		this.autofire = autofire;
	}
	public int getAmmoauto() {
		return ammoauto;
	}
	public void setAmmoauto(int ammoauto) {
		this.ammoauto = ammoauto;
	}
	private int lazer = 0;// allammo
	private int rocket = 0;
	public void setammobybox(int i){
		if (i < 4){
		if (i == 1){
			setAmmoauto(250);
			setLazer(0);
			setRocket(0);
			}
		if (i == 2){
			setLazer(100);
			setAmmoauto(0);
			setRocket(0);
			}
		if (i == 3){
			setAmmoauto(0);
			setLazer(0);
			setRocket(30);
		}
		int[] allammo = {999, 250, 100, 30};
		showammo  = allammo[i];
		}
	}
	private boolean shot = false; 
	public boolean isShot() {
		return shot;
	}
	public void setShot(boolean shot) {
		this.shot = shot;
		delayshot = 5;
	}
	public void auto(){
		if (getAmmoauto() > 0){
			setShot(true);
			createammo();
		}
		else{
			setAutofire(false);
			setTypenow(0);
		}
	}
	public void autofire(){
		if (isAutofire()){
			auto();
		}
	}
	public int getTypecheck() {
		return typecheck;
	}
	public void setTypecheck(int typecheck) {
		this.typecheck = typecheck;
	}
	public int getTypenow() {
		return typenow;
	}
	public void setTypenow(int typenow) {
		this.typenow = typenow;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	private int point = 0;
	private int index = 0;
	private int power = 0;
	private boolean rocketopen = false;

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void positionofindex(int inc){
		if (!isBuyinterface()){
			return ;
		}
		index += inc;
		if (index == -1){
			index = 5;
		}
		index %= 6;
		//System.out.println(index);
	}
	public int getLazer() {
		return lazer;
	}
	public void setLazer(int lazer) {
		this.lazer = lazer;
	}
	public int getRocket() {
		return rocket;
	}
	public void setRocket(int rocket) {
		this.rocket = rocket;
	}
	public void rocketopen(){
	if (isRocketopen() ){
		setPower(getPower() + 5);
		setPower(Math.min(getPower(), 200));
		}
	}
	public boolean isRocketopen() {
		return rocketopen;
	}
	public void setRocketopen(boolean rocketopen) {
		this.rocketopen = rocketopen;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public boolean isBuyinterface() {
		return buyinterface;
	}
	public void setBuyinterface(boolean buyinterface) {
		this.buyinterface = buyinterface;
	}
	public int getAirsupport() {
		return airsupport;
	}
	public void setAirsupport(int airsupport) {
		this.airsupport = airsupport;
	}
	public boolean isRes() {
		return res;
	}
	public void setRes(boolean res) {
		this.res = res;
	}
	public int getRestime() {
		return restime;
	}
	public void setRestime(int restime) {
		this.restime = restime;
	}
	public boolean isOksound() {
		return oksound;
	}
	public void setOksound(boolean oksound) {
		System.out.println(oksound);
		this.oksound = oksound;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	private boolean buyinterface = false;
	private int airsupport = 500; 
	private boolean res = false;
	private ArrayList<Rectangle> allbomb = new ArrayList<Rectangle>();
	private Integer ammobombcount = new Integer(0);
	private ArrayList<Integer> allammobombcount = new ArrayList<Integer>();
	private ArrayList<Rectangle> allboom= new ArrayList<Rectangle>();
	private int airtime = 0;
	public void createallbomb(){
		if (getAirtime()  > 0){
			createbomb();
			airtime -= 1;
		}
	}
	protected void createbomb() {
		ammo = new Rectangle();
		float y = 40 * -1;
		rndhit= randInt(0, 2000);
		float x = getX() - 500 + rndhit;
		ammo.set(x, getY()+300+y, 64, 64);
		allbomb.add(ammo);
		allammobombcount.add(ammobombcount);
	}
	private void onairbomb(ArrayList<TopOfGen> allenemy, ArrayList<TopOfGen> allenemy2, TopOfGen boss){
		float delta = Gdx.graphics.getDeltaTime();
		int mem = -1;
		for (int i = 0;i < allbomb.size(); i += 1 ){
			// type of fire && collision
			allbomb.get(i).y -= 200 * delta;
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
			}
			for (TopOfGen item : allenemy){
				if (item.getHitbox().overlaps(allbomb.get(i)) && item.isAllow()){
					item.setDmg(5);
					point += 10;
					mem = i;
				}
			}
			for (TopOfGen item : allenemy2){
				if (item.getHitbox().overlaps(allbomb.get(i)) && item.isAllow()){
					item.setDmg(5);
					point += 10;
					mem = i;
				}
			}
			if (boss.getHitbox().overlaps(allbomb.get(i)) && boss.isAllow()){
				boss.setDmg(5);
				point += 25;
				mem = i;
			}
			allammobombcount.set(i, allammobombcount.get(i)+1);
			if (allammobombcount.get(i) == 200){
				mem = i;
			}
			if (mem >= 0){
				boom32 = new Rectangle(allbomb.get(i).x,allbomb.get(i).y, 128, 128);
				for (TopOfGen item : allenemy){
					if (item.getHitbox().overlaps(boom32) && item.isAllow()){
						item.setDmg(10);
						point += 10;
					}
				}
				for (TopOfGen item : allenemy2){
					if (item.getHitbox().overlaps(boom32) && item.isAllow()){
						item.setDmg(10);
						point += 10;
					}
				}
				if (boss.getHitbox().overlaps(boom32) && boss.isAllow()){
					boss.setDmg(10);
					point += 25;
				} 
				allboom.add(boom32);
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
	public int getAirtime() {
		return airtime;
	}
	public void setAirtime(int airtime) {
		this.airtime = airtime;
	}
	Rectangle boom32 = new Rectangle();
	private int[] rebomb = new int[100];
}
