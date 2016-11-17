package com.game.only.ai;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.only.move.Aim;
import com.game.only.move.Over;
import com.game.only.move.Speed;
import com.game.only.player.Player;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class TopOfGen extends Sprite{
	private int deadside = 0;
	private int spawn = 0; 
	private int dct = 0 , dmg = 0;
	private boolean canjump;
	private Vector2 velocity = new Vector2();
	private float g = 100 * 10f, animationTime;
	private Speed speed1 = new Speed();
	private Over collision;
	private boolean fire , ctrl = false, allow = false;
	private Texture tex, hitboxcheck;
	ArrayList<Integer> sendindex = new ArrayList<Integer>();
	private Rectangle ammo = new Rectangle();
	private ArrayList<Rectangle> allammo = new ArrayList<Rectangle>();
	private Integer ammocount = new Integer(0);
	private ArrayList<Integer> allammocount = new ArrayList<Integer>();
	private int[] ammodata = {0,0,0}; 
	private ArrayList<int[]> allammodata = new ArrayList<int[]>();
	private boolean[] arraykey = {false,false,false,false};
	private Aim aim = new Aim();
	private int position;
	private int unittype = 0;
	private int ammocooldown = 0;
	private int ammoofgun = 10;
	private boolean reload = false;
	private int reloadtime = 100;
	private int use_ammocooldown;
	private int use_reloadtime;
	private int use_ammoofgun;
	private ArrayList<Animation> aaa = new ArrayList<Animation>();
	private Rectangle hitbox = new Rectangle();
	private int shoot = 100;
	private int count;
	private int hp = 50;
	private int hitboxx = 64;
	private int hitboxy = 64;
	private int delayshot;
	private ArrayList<Texture> firepic = new ArrayList<Texture>();
	public TopOfGen(ArrayList<Animation> aaa, TiledMapTileLayer collisionLayer,Texture tex, int i,int unittype,int size){
		super(aaa.get(1).getKeyFrame(0), 0, 0, 64*size, 64*size);
		this.aaa = aaa;
		collision = new Over(collisionLayer);
		this.tex = tex;
		position = i;
		this.unittype = unittype;
		use_ammocooldown = ammocooldown;
		use_reloadtime = reloadtime;
		setUse_ammoofgun(ammoofgun);
		hitboxcheck = new Texture("img/hitbox.png");
		int full = 1;
		if (unittype == 15){
			full = 2;
		}
		hitboxx *= size*full;
		hitboxy *= size;
		firepic.add(new Texture("img/shoot_L.png"));
		firepic.add(new Texture("img/shoot_R.png"));
	}
	public void animationset(float delta){
		animationTime += delta;
		int[][] all = {{1,1,0,-1,-1,-1,0,1},{0,1,1,1,0,-1,-1,-1}};
		if (ctrl){
			for (int j = 0; j < 8;j++){
				if (aim.getx() == all[0][j] && aim.gety() == all[1][j]){
					setRegion(aaa.get(j + 4).getKeyFrame(animationTime));
					if (j == 2){
						setthehitbox(hitbox.getX() ,hitbox.getY(),hitboxx,hitboxy / 2);
					}
					else{
					setthehitbox(hitbox.getX() ,hitbox.getY(), hitboxx / 2,hitboxy);
					}
					break;
				}
			}
		}
		else if (!canjump && fire){
			setRegion(aaa.get(2).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);// + (64 /2)
		}
		else if (!canjump && !fire){
			setRegion(aaa.get(3).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);
		}
		else if (velocity.x < 0){
			setRegion(aaa.get(2).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);// + (64 /2)
		}else if (velocity.x > 0){
			setRegion(aaa.get(3).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);
		}else if (fire && shoot >= 1){
			setRegion(aaa.get(4).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);// + 64 /2
		}
		else if (!fire && shoot >= 1){
			setRegion(aaa.get(5).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);
		}
		else if (fire){
			setRegion(aaa.get(0).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);// + 64 /2
		}
		else if (!fire){
			setRegion(aaa.get(1).getKeyFrame(animationTime));
			setthehitbox(hitbox.getX(),hitbox.getY(),hitboxx / 2,hitboxy);
		}
	}
	public void setthehitbox(float f, float h, float width, float height){
		hitbox.setX(f);
		hitbox.setY(h);
		hitbox.setWidth(width);
		hitbox.setHeight(height);
	}
	public int checkdeaddmg(int hp){
		hp -= getDmg();
		resetDmg();
		if (hp <= 0){
			animationTime = 0;
			if (!fire){
				deadside = 1;
			}
			else {
				deadside = 0;
			}
			setCount(-1);
			setY(hitbox.getY() - 10);
			setAllow(false);
			hp = 5;
			setDct(500);
			/*
			allammodata.clear();
			allammo.clear();
			allammocount.clear();
			*/
		}
		this.setHp(hp);
		return hp;
	}
	public boolean checkenemy(ArrayList<TopOfGen> allenemy, Player player) {
		if (getX() > player.getX()+player.getWidth()){
			for (TopOfGen item : allenemy){
				if (!item.isAllow()){
					
				}
				else if (getX() > item.getX() + 10 && getX() < item.getX()+item.getWidth()+10 && item.getUnittype() == 1){
					return true;
				}
			}
			return false;
		}
		else if (getX() < player.getX()){
			for (TopOfGen item : allenemy){
				if (!item.isAllow()){
					
				}
				else if (getX()+getWidth() > item.getX() - 10 && getX()+getWidth() < item.getX()+item.getWidth() - 10 && item.getUnittype() == 1){
					return true;
				}			
			}
			return false;
		}
		return false;
	}
	public boolean checkxblockleft(){
		getCollision().inc(1);
		return getCollision().collidesRL(hitbox.getHeight(), hitbox.getY(), 0, hitbox.getX()-10);
	}
	public boolean checkxblockright(){
		getCollision().inc(1);
		return getCollision().collidesRL(hitbox.getHeight(), hitbox.getY(), hitbox.getWidth()+10, hitbox.getX());
	}
	public void createammo(){
		delayshot = 5;
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
	}
	public void draw(Batch Batch, Player1 player, Player2 player2, ArrayList<TopOfGen> allenemy, int j){
		if (isAllow()){
			update(Gdx.graphics.getDeltaTime(), player, player2);
		}
		else {
			dead(Gdx.graphics.getDeltaTime());
			onair(Gdx.graphics.getDeltaTime(), player, player2);
			dct -= 1;
		}
		super.draw(Batch);
		for (int i = 0;i < allammo.size(); i++ ){
			Batch.draw(tex, allammo.get(i).x, allammo.get(i).y, 16, 16);
		}
		//Batch.draw(hitboxcheck, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		shoot -= 1;
		if (delayshot > 0){
			float setx = 0;
			setx = -13;
			int a = 1;
			if (!fire){
				setx = (getWidth() * 1);
				a = 0;
			}
			Batch.draw(firepic .get(a), getX() + setx, hitbox.getY() + 20,16,16);
		}
		delayshot -= 1;
	}
	public void dead(float delta){
		animationTime += delta;
		setRegion(aaa.get(6+deadside).getKeyFrame(animationTime));
	}
	public void enemy_wait(ArrayList<TopOfGen> allenemy, Player player){
		float pos = pos_set(allenemy);
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
	private int power = 0;
	public void fire(Player player, int setcd){
		if (getX() > player.getX()+player.getWidth()){
			setFire(true);
		}
		else if (getX() < player.getX()){
			setFire(false);
		}
		if (this.getBoundingRectangle().overlaps(player.getBoundingRectangle())){
			if (power == 0){
				player.setDmg(5);
				power = 1;
			}
		}
		else if (getX() > (player.getX() - 750) && getX() < (player.getX() + 750) && use_ammocooldown <= 0 && getUse_ammoofgun() > 0){
			createammo();
			use_ammocooldown = ammocooldown + setcd;
			setUse_ammoofgun(getUse_ammoofgun() - 1);
			power = 0;
		}
		else {
			power = 0;
		}
		
		if (getUse_ammoofgun() <= 0 && !reload){
			reload = true;
			use_reloadtime = reloadtime;
			}
		if (reload){
			use_reloadtime  -= 1;
			}
		if (use_reloadtime <= 0 && reload){
			setUse_ammoofgun(ammoofgun);
			reload = false;
		}
	}
	public ArrayList<Rectangle> getAllammo() {
		return allammo;
	}
	public int getAmmocooldown() {
		return ammocooldown;
	}

	public int getAmmoofgun() {
		return ammoofgun;
	}

	public Over getCollision() {
		return collision;
	}
	public TiledMapTileLayer getCollisionLayer() {
		return collision.getCollisionLayer();
	}
	public int getDct() {
		return dct;
	}
	public int getDmg() {
		return dmg;
	}
	public float getG() {
		return g;
	}
	public int getPosition() {
		return position;
	}
	public int getReloadtime() {
		return reloadtime;
	}
	public int getSpawn() {
		return spawn;
	}
	public Speed getSpeed1() {
		return speed1;
	}
	public int getUnittype() {
		return unittype;
	}
	public int getUse_ammocooldown() {
		return use_ammocooldown;
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public boolean isAllow() {
		return allow;
	}
	public boolean isCanjump() {
		return canjump;
	}
	public boolean isCtrl() {
		return ctrl;
	}
	public boolean isFire() {
		return fire;
	}
	public boolean isReload() {
		return reload;
	}
	public void jump(){
		if (isCanjump()){
			getVelocity().y = getSpeed1().jump();
			setCanjump(false);
		}
	}
	public void superjump(int send){
		if (isCanjump()){
			getSpeed1().setSpeed(send);
			getVelocity().y = getSpeed1().superjump();
			setCanjump(false);
		}
	}
	public void superjumpv2(int i) {
		if (isCanjump()){
			getSpeed1().setSpeed(i);
			getVelocity().y = getSpeed1().superjumpV2();
			setCanjump(false);
		}
		
	}
	public int movetoatk(Player player, ArrayList<TopOfGen> allenemy, int cd){
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
		else if ((getX() > (player.getX() - 750)) && (getX() < (player.getX() + 750))){
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
	public void onair(float delta, Player1 player, Player2 player2){
		int mem = -1;
		for (int i = 0;i < allammo.size(); i += 1 ){
			// type of fire && collision
			allammo.get(i).x += 450 *allammodata.get(i)[0]* delta;
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
			if (player.getHitbox().overlaps(allammo.get(i))){
				mem = i;
				player.setDmg(1);
			}
			if (player2.isOnline()){
				if (player2.getHitbox().overlaps(allammo.get(i))){
					mem = i;
					player2.setDmg(1);
				}
			}
			// set x, y
			// inc count
			allammocount.set(i, allammocount.get(i)+1);
			
			// check ammo time
			if (allammocount.get(i) == 250){
				mem = i;
			}
		}
		if (mem >= 0){
			allammo.remove(mem);
			allammocount.remove(mem);
			allammodata.remove(mem);
		}
	}

	public float pos_set(ArrayList<TopOfGen> allenemy){
		float spawn = allenemy.get(0).getX();
		int setpos = getPosition();
		if (setpos % 2 == 0){
			return spawn + (100*setpos);
		}else{
			return spawn - (75*setpos);
		}
	}

	public void resetDmg() {
		this.dmg = 0;
	}
	
	public void setAllammo(ArrayList<Rectangle> allammo) {
		this.allammo = allammo;
	}
	
	public void setAllow(boolean allow) {
		this.allow = allow;
	}
	public void setAmmocooldown(int ammocooldown) {
		this.ammocooldown = ammocooldown;
	}
	public void setAmmoofgun(int ammoofgun) {
		this.ammoofgun = ammoofgun;
	}
	
	public void setCanjump(boolean canjump) {
		this.canjump = canjump;
	}
	public void setCollision(Over collision) {
		this.collision = collision;
	}
	public void setCtrl(boolean ctrl) {
		this.ctrl = ctrl;
	}
	public void setDct(int dct) {
		this.dct = dct;
	}
	public void setDmg(int dmg) {
		this.dmg += dmg;
	}
	public void setFire(boolean fire) {
		this.fire = fire;
	}
	public void setG(float g) {
		this.g = g;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setReload(boolean reload) {
		this.reload = reload;
	}
	public void setReloadtime(int reloadtime) {
		this.reloadtime = reloadtime;
	}
	/*if (velocity.y > speed){
	velocity.y = speed;}
else if(velocity.y < -speed){
	velocity.y = -speed;}*/
	public void setSpawn(int spawn) {
		this.spawn = spawn;
	}

	public void setSpeed1(Speed speed1) {
		this.speed1 = speed1;
	}
	public void setUnittype(int unittype) {
		this.unittype = unittype;
	}
	public void setUse_ammocooldown(int use_ammocooldown) {
		this.use_ammocooldown = use_ammocooldown;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public void update(float delta, Player1 player, Player2 player2){
		if (hitbox.getY() < -100){
			hitbox.setY(750);
		}
		aim.aim(arraykey[0],arraykey[1],arraykey[2],arraykey[3]);
		velocity.y -= g * delta;
		//speed1.update(!canjump);
		velocity.x = speed1.getSpeed();
		float oldX = hitbox.getX(), oldY = hitbox.getY();
		boolean collisionX = false, collisionY = false;
		// move on x
		hitbox.setX(hitbox.getX() + velocity.x * delta);
			
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
			}else if(velocity.y < 0){
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
			onair(delta, player, player2);
	}
	public boolean checkwho(Player player ,Player2 player2){
		float isplayer2 = Math.max(player2.getX(), getX()) - Math.min(player2.getX(), getX());
		float isplayer = Math.max(player.getX(), getX()) - Math.min(player.getX(), getX());
		if (player.isAllow()){
			return true;
		}else if(player2.isAllow()){
			return false;
		}
		else if (isplayer2 < isplayer){
			return true;
		}
		else {
			return false;
		}
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getUse_ammoofgun() {
		return use_ammoofgun;
	}
	public void setUse_ammoofgun(int use_ammoofgun) {
		this.use_ammoofgun = use_ammoofgun;
	}

}