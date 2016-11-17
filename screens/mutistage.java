package com.game.only.screens;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.game.only.ai.Spawn;
import com.game.only.ai.TopOfGen;
import com.game.only.ai.boss_stage1;
import com.game.only.ai.boss_stage2;
import com.game.only.ai.boss_stage3;
import com.game.only.ai.boss_stage4;
import com.game.only.ai.boss_stage5;
import com.game.only.ai.enemy_atk;
import com.game.only.ai.enemy_def;
import com.game.only.ai.enemy_superjump;
import com.game.only.player.Player1;
import com.game.only.player.Player2;

public class mutistage extends AbstractScreen {
	private int modeindex;
	private int playerpoint;
	private String name;
	public mutistage(Game game, boolean b, int index) {
		super(game);
		single = b;
		stageindex = index;
		modeindex = 0;
		playerpoint = 0;
		name = "";
		// TODO Auto-generated constructor stub
	}
	public mutistage(Game game, boolean b, int index, int mode, int point, String name) {
		super(game);
		single = b;
		stageindex = index;
		modeindex = mode;
		playerpoint = point;
		this.name = name;
	}
	// stage index use
	String[] mapselect = {"maps/stage.tmx", "maps/stage2_edit.tmx", "maps/stage3_edit.tmx", "maps/stage4.tmx", "maps/stage5.tmx"};
	private float[][][] spawnpos = {{{3450.403f,5346.893f,6556.759f,7859.9917f},{543.0123f,543.0123f,543.0123f,543.0123f}}
	,{{3450.403f,5346.893f,6556.789f,7559.9917f},{543.0123f,543.0123f,543.0123f,543.0123f}},{{3450.403f,5346.893f,6556.789f,8059.9917f},{543.0123f,543.0123f,543.0123f,543.0123f}}
	,{{3450.403f,5346.893f,6556.789f,8200.908f},{543.0123f,543.0123f,543.0123f,543.0123f}},{{3450.403f,5346.893f,6556.789f,8200.908f},{543.0123f,543.0123f,543.0123f,543.0123f}}};
	//
	private int stageindex;
	private boolean single = false;
	private TiledMap map;
	private Batch batch = new SpriteBatch();
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player1 player;
	private Player2 player2;
	private TextureAtlas playerAtlas,playerAtlas1,playerAtlas2,playerAtlas3;
	private ArrayList<Texture> tex = new ArrayList<Texture>();
	private int x1, x2 = 300;
	
	private ArrayList<TopOfGen> allenemy = new ArrayList<TopOfGen>();
	private ArrayList<TopOfGen> allenemy2 = new ArrayList<TopOfGen>();
	private ArrayList<TopOfGen> bunkeruse = new ArrayList<TopOfGen>();
	private ArrayList<ArrayList<TopOfGen>> all = new ArrayList<ArrayList<TopOfGen>>();
	private ArrayList<Animation> berban = new ArrayList<Animation>();
	private ArrayList<Animation> attack = new ArrayList<Animation>();
	private ArrayList<Animation> def = new ArrayList<Animation>();
	private ArrayList<Animation> back = new ArrayList<Animation>();
	private ArrayList<Animation> boss12= new ArrayList<Animation>();
	private ArrayList<Animation> warp= new ArrayList<Animation>();
	private TopOfGen boss;
	private int count = 0;
	private Sound sound;
	private long id;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	// d
	private boolean paused = false;
	private boolean isPressESC = false;
	private Texture bg;
	private Texture button1;
	private Texture button2;
	private Texture button3;
	private Texture text_pause;
	private int select = 1;
	private boolean tab = false;
	private ArrayList<Texture> ui = new ArrayList<Texture>();
	private ArrayList<Animation> berban2= new ArrayList<Animation>();
	// d
	@Override
	public void show() {
		delay = 200;
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load(mapselect[stageindex]);
		bg = new Texture("pause\\bg.png");
		text_pause = new Texture("pause\\PAUSE.png");
		sound = Gdx.audio.newSound(Gdx.files.internal("sound\\stage"+stageindex+".mp3"));
		id = sound.play(1.0f);      // plays the sound a second time, this is treated as a different instance
		sound.setPan(id, -1, 1);    // sets the pan of the sound to the left side at full volume
		sound.setLooping(id, true);
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		playerAtlas = new TextureAtlas("barben/berban.pack");
		tex.add(new Texture("img/b_4.png"));
		tex.add(new Texture("img/b_5.png"));
		tex.add(new Texture("img/b_2.png"));
		tex.add(new Texture("img/b_11.png"));
		createui();
		createnumber();
		createammoshow();
		createwarp();
		createplayer();
		createplayer2();
		createattack();
		createdef();
		createback();
		createitemshow();
		createitemshow2();
		player = new Player1(berban, (TiledMapTileLayer) map.getLayers().get("stage"), 0, tex);
		player.setPosition(40 * player.getCollisionLayer().getTileWidth(), 25 * player.getCollisionLayer().getTileHeight());
		player.setthehitbox(player.getX(),player.getY(),64,64);
		createplayer2();
		player2 = new Player2(berban2, (TiledMapTileLayer) map.getLayers().get("stage"), 1, tex);
		player2.setPosition(42 * player2.getCollisionLayer().getTileWidth(), 25 * player2.getCollisionLayer().getTileHeight());
		player2.setthehitbox(player2.getX(),player2.getY(),64,64);
		player2.setOnline(false);
		createenemyteam(attack, def, back, allenemy);
		createenemyteam(attack, def, back, allenemy2);
		all.add(allenemy);
		all.add(allenemy2);
		createboss();
		if (stageindex == 0){
			boss = new boss_stage1(boss12, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), 10, 10, 1);
		}
		else if (stageindex == 1){
			boss = new boss_stage2(boss12, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), 10, 15, 2);
		}else if (stageindex == 2){
			boss = new boss_stage3(boss12, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), 10, 15, 2);
		}
		else if (stageindex == 3){
			boss = new boss_stage4(boss12, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), 10, 15, 1);
		}
		else if (stageindex == 4){
			boss = new boss_stage5(boss12, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), 10, 15, 3);
		}
		if (single){
		Gdx.input.setInputProcessor(player);
		}
		else
		{
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(player);
		inputMultiplexer.addProcessor(player2);
		Gdx.input.setInputProcessor(inputMultiplexer);
		player2.setOnline(true);
		}
		if (modeindex == 1){
			player.setPoint(playerpoint);
		}
		//delay();
	}
	private void createplayer2() {
		playerAtlas = new TextureAtlas("barban2/berban.pack");
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("Stayl")));
		berban2.get(0).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("Stay")));
		berban2.get(1).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanwalkL")));
		berban2.get(2).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanWalk")));
		berban2.get(3).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_east")));
		berban2.get(4).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_eastnorth")));
		berban2.get(5).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_north")));
		berban2.get(6).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_westnorth")));
		berban2.get(7).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_west")));
		berban2.get(8).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_westsouth")));
		berban2.get(9).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_south")));
		berban2.get(10).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_eastsouth")));
		berban2.get(11).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("deadl")));
		berban2.get(12).setPlayMode(Animation.PlayMode.NORMAL);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("dead")));
		berban2.get(13).setPlayMode(Animation.PlayMode.NORMAL);
		playerAtlas = new TextureAtlas("barban2/bshoot.pack");
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanShootL")));
		berban2.get(14).setPlayMode(Animation.PlayMode.LOOP);
		berban2.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanShoot")));
		berban2.get(15).setPlayMode(Animation.PlayMode.LOOP);
		
	}
	private void createui() {
		ui.add(new Texture("ui/box_1.png"));
		ui.add(new Texture("ui/box_2.png"));
		ui.add(new Texture("ui/point.png"));
		ui.add(new Texture("ui/window.png"));
		ui.add(new Texture("ui/aaa.png"));
	}
	private TextureAtlas number = new TextureAtlas();
	private void createnumber() {
		// red  green black white
		number = new TextureAtlas("obj/number.pack");
	}
	private ArrayList<TextureRegion> allammoshow = new ArrayList<TextureRegion>();
	private void createammoshow() {
		playerAtlas2 = new TextureAtlas("obj/item.pack");
		allammoshow.add(playerAtlas2.findRegion("single"));
		allammoshow.add(playerAtlas2.findRegion("auto"));
		allammoshow.add(playerAtlas2.findRegion("laser"));
		allammoshow.add(playerAtlas2.findRegion("rocket"));
	}
	private ArrayList<TextureRegion> allitemshow = new ArrayList<TextureRegion>();
	private void createitemshow() {
		playerAtlas2 = new TextureAtlas("obj/item.pack");
		allitemshow.add(playerAtlas2.findRegion("auto"));
		allitemshow.add(playerAtlas2.findRegion("laser"));
		allitemshow.add(playerAtlas2.findRegion("rocket"));
		allitemshow.add(playerAtlas2.findRegion("firstaid"));
		
	}
	private ArrayList<Texture> allitemshow2 = new ArrayList<Texture>();
	private void createitemshow2() {
		allitemshow2.add(new Texture("airdrop/auto_shop.png"));
		allitemshow2.add(new Texture("airdrop/laser_shop.png"));
		allitemshow2.add(new Texture("airdrop/rocket_shop.png"));
		allitemshow2.add(new Texture("airdrop/first_shop.png"));
		allitemshow2.add(new Texture("airdrop/air_shop.png"));
		allitemshow2.add(new Texture("airdrop/speed_shop.png"));
		allitemshow2.add(new Texture("airdrop/air_off.png"));
		allitemshow2.add(new Texture("airdrop/air_on.png"));
	}
	public void createattack() {
		playerAtlas1 = new TextureAtlas("attack/attack.pack");
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_stayL")));
		attack.get(0).setPlayMode(Animation.PlayMode.LOOP);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_stay")));
		attack.get(1).setPlayMode(Animation.PlayMode.LOOP);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_walkL")));
		attack.get(2).setPlayMode(Animation.PlayMode.LOOP);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_walk")));
		attack.get(3).setPlayMode(Animation.PlayMode.LOOP);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_shootL")));
		attack.get(4).setPlayMode(Animation.PlayMode.LOOP);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_shoot")));
		attack.get(5).setPlayMode(Animation.PlayMode.LOOP);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_deadL")));
		attack.get(6).setPlayMode(Animation.PlayMode.NORMAL);
		attack.add(new Animation(1 / 15f, playerAtlas1.findRegions("attack_dead")));
		attack.get(7).setPlayMode(Animation.PlayMode.NORMAL);
	}
	public void createdef() {
		playerAtlas2 = new TextureAtlas("def/def.pack");
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderstayL")));
		def.get(0).setPlayMode(Animation.PlayMode.LOOP);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("Leader_stay")));
		def.get(1).setPlayMode(Animation.PlayMode.LOOP);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderwalkL")));
		def.get(2).setPlayMode(Animation.PlayMode.LOOP);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderwalk")));
		def.get(3).setPlayMode(Animation.PlayMode.LOOP);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderShootL")));
		def.get(4).setPlayMode(Animation.PlayMode.LOOP);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderShoot")));
		def.get(5).setPlayMode(Animation.PlayMode.LOOP);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderdeadL")));
		def.get(6).setPlayMode(Animation.PlayMode.NORMAL);
		def.add(new Animation(1 / 15f, playerAtlas2.findRegions("leaderDead")));
		def.get(7).setPlayMode(Animation.PlayMode.NORMAL);
	}
	public void createback() {
		playerAtlas3 = new TextureAtlas("back/back.pack");
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorStayL")));
		back.get(0).setPlayMode(Animation.PlayMode.LOOP);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorStay")));
		back.get(1).setPlayMode(Animation.PlayMode.LOOP);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorWalkL")));
		back.get(2).setPlayMode(Animation.PlayMode.LOOP);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorWalk")));
		back.get(3).setPlayMode(Animation.PlayMode.LOOP);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorShootL")));
		back.get(4).setPlayMode(Animation.PlayMode.LOOP);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorshoot")));
		back.get(5).setPlayMode(Animation.PlayMode.LOOP);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorDeadL")));
		back.get(6).setPlayMode(Animation.PlayMode.NORMAL);
		back.add(new Animation(1 / 15f, playerAtlas3.findRegions("backdoorDead")));
		back.get(7).setPlayMode(Animation.PlayMode.NORMAL);
	}
	private String[] bossstageselect = {"booss/boss_stage1.pack", "booss/boss2.pack", "booss/boss3.pack",
			"booss/boss4.pack", "booss/lastbossisreal.pack"};
	// stage style
	private String[][] bossstyle = {{"boss_stayL","boss_stay","boss_walkL","boss_walk","boss_shootL","boss_shoot", "boss_stayL", "boss_stayLd"},
			{"boss3_stayL","boss3_stay","boss3_walkL","boss3_walk","boss3_walkL","boss3_walk", "boss3_deadL", "boss3_dead"},
			{"ship","shipL","ship","shipL","ship_shoot","ship_shootL", "ship", "shipL"},
			{"boss2_stayL","boss2_stay","boss2_walkL","boss2_walk","boss2_shootL","boss2_shoot", "boss2_deadL", "boss2_dead"},
			{"lastbossisrealL", "lastbossisreal","lastbossisrealL", "lastbossisreal","lastbossisrealL", "lastbossisreal", "lastbossisrealL", "lastbossisreal"}
			};
	public void createboss() {
		playerAtlas3 = new TextureAtlas(bossstageselect[stageindex]);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][0]))); // stay
		boss12.get(0).setPlayMode(Animation.PlayMode.LOOP);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][1])));
		boss12.get(1).setPlayMode(Animation.PlayMode.LOOP);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][2]))); //walk
		boss12.get(2).setPlayMode(Animation.PlayMode.LOOP);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][3])));
		boss12.get(3).setPlayMode(Animation.PlayMode.LOOP);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][4]))); //shoot
		boss12.get(4).setPlayMode(Animation.PlayMode.LOOP);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][5]))); 
		boss12.get(5).setPlayMode(Animation.PlayMode.LOOP);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][6]))); //dead
		boss12.get(6).setPlayMode(Animation.PlayMode.NORMAL);
		boss12.add(new Animation(1 / 15f, playerAtlas3.findRegions(bossstyle[stageindex][7])));
		boss12.get(7).setPlayMode(Animation.PlayMode.NORMAL);
	}
	public void createwarp() {
		playerAtlas3 = new TextureAtlas("warp/sound.pack");
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(0).setPlayMode(Animation.PlayMode.LOOP);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(1).setPlayMode(Animation.PlayMode.LOOP);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(2).setPlayMode(Animation.PlayMode.LOOP);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(3).setPlayMode(Animation.PlayMode.LOOP);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(4).setPlayMode(Animation.PlayMode.LOOP);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(5).setPlayMode(Animation.PlayMode.LOOP);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(6).setPlayMode(Animation.PlayMode.NORMAL);
		warp.add(new Animation(1 / 15f, playerAtlas3.findRegions("warp")));
		warp.get(7).setPlayMode(Animation.PlayMode.NORMAL);
	}
	public void createplayer(){
		playerAtlas = new TextureAtlas("barben/berban.pack");
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("Stayl")));
		berban.get(0).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("Stay")));
		berban.get(1).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanwalkL")));
		berban.get(2).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanWalk")));
		berban.get(3).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_east")));
		berban.get(4).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_eastnorth")));
		berban.get(5).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_north")));
		berban.get(6).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_westnorth")));
		berban.get(7).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_west")));
		berban.get(8).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_westsouth")));
		berban.get(9).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_south")));
		berban.get(10).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("aim_eastsouth")));
		berban.get(11).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("deadl")));
		berban.get(12).setPlayMode(Animation.PlayMode.NORMAL);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("dead")));
		berban.get(13).setPlayMode(Animation.PlayMode.NORMAL);
		playerAtlas = new TextureAtlas("barben/bshoot.pack");
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanShootL")));
		berban.get(14).setPlayMode(Animation.PlayMode.LOOP);
		berban.add(new Animation(1 / 15f, playerAtlas.findRegions("berbanShoot")));
		berban.get(15).setPlayMode(Animation.PlayMode.LOOP);
	}
	public Player1 getPlayer1() {
		return player;
	}
	public void setPlayer(Player1 player) {
		this.player = player;
	}
	public Player2 getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player2 player2) {
		this.player2 = player2;
	}
	public void createenemyteam(ArrayList<Animation> attack, ArrayList<Animation> def,ArrayList<Animation> back, ArrayList<TopOfGen> all){
		all.add(new Spawn(warp, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), 0, 0, 1));
		for (int i = 0 ; i < 2 ; i++){
			all.add(new enemy_atk(attack, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), i + 1, 1, 1));
		}
		for (int i = 2 ; i < 4 ; i++){
			all.add(new enemy_superjump(back, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), i + 1, 1, 1));
		}
		for (int i = 4 ; i < 6 ; i++){
			all.add(new enemy_def(def, (TiledMapTileLayer) map.getLayers().get("stage"), tex.get(1), i + 1, 2, 1));
		}
	}
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int delay;
	private int countbuy = -350;
	private int countbuy2 = gd.getDisplayMode().getWidth()+350;
	private boolean poweroff = false;
	public void paused(){
		player.setOksound(false);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//batch.draw(text_pause, Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/8);

		if (select % 3 ==  1 || select % 3 == -2){
			buttonOneSelect();
	    }
	    else if (select % 3 == 0){
	    	buttonThreeSelect();
	    }
	    else if (select % 3 == -1 || select % 3 == 2){
	    	buttonTwoSelect();
	    }
		if (Gdx.input.isKeyJustPressed(Keys.W)){
	        select -= 1;
	        System.out.println(select);
	    }
	    else if (Gdx.input.isKeyJustPressed(Keys.S)){
	    	select += 1;
	       	System.out.println(select);
	    }
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
	        select %= 3;
	        System.out.println(select+"mod");
	        if (select == 1 || select == -2){
	        	tab = true;
	        	resume();
	        }
	        else if (select == 0){
	        	dispose();
	        	game.setScreen(new mainmenu(game));
	        	
	        }
	        else if (select == -1 || select == 2){
	        	dispose();
	        	game.setScreen(new mutistage(game, single, stageindex));
	        	
	        }}
		batch.draw(button1, (float) (Gdx.graphics.getWidth()/2.3), Gdx.graphics.getHeight()/2+Gdx.graphics.getHeight()/20);
		batch.draw(button2, (float) (Gdx.graphics.getWidth()/2.3), Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/20);
		batch.draw(button3, (float) (Gdx.graphics.getWidth()/2.3), (float) (Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/6.7));
		batch.end();
	}
	@Override
	public void render(float delta) {
		System.out.println(delta);
		player.setOksound(true);
		if (delta > 0.1){
			return ;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			isPressESC = !isPressESC;
		}
		if (isPressESC){
			paused();
	}
		else if(isPressESC == false){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (single){updatecamera();}else{updatecamerafor2();}
		renderer.setView(camera);
		renderer.render();	
		renderer.getBatch().begin();
		int x = 1;
		for (ArrayList<TopOfGen> array : all){
			for (TopOfGen item : array){
				if ((!item.isAllow()) && item.getDct() <= 0 && array.get(0).getSpawn() < 4){
					item.setAllow(true); 
					item.setPosition(spawnpos[stageindex][0][array.get(0).getSpawn() + x - 1] , spawnpos[stageindex][1][array.get(0).getSpawn() + x - 1] + 20);
					item.setthehitbox(item.getX(), item.getY(), item.getWidth(), item.getHeight());
				}
					item.draw(renderer.getBatch(), player, player2, array, x);
			}
			x += 1;
		}
		player.draw(renderer.getBatch(), allenemy, allenemy2,boss, player2);
		if (!single) {player2.draw(renderer.getBatch(), allenemy, allenemy2,boss, player);}
		if (player.getTime() > 0){
			player.draw(renderer.getBatch(), allenemy, allenemy2,boss, player2);
		}
		if (!single && player2.getTime() > 0){
			if (!single) {player2.draw(renderer.getBatch(), allenemy, allenemy2,boss, player);}
		}
		if (boss.getSpawn() == 10){
			delaytimeend();
			if (delay <= 0 ){
				if (modeindex == 1){
					player.end();
					if (!single) {
						player2.end();
					}
					dispose();
					game.setScreen(new selectrank(game, stageindex + 1 , player.getPoint(), name));
					
				}
				else{
					player.end();
					if (!single) {
						player2.end();
					}
					dispose();
					game.setScreen(new WinScreen(game, player.getPoint(),player2.getPoint(), stageindex, single));
					
				}
			}
		}//
		else if (!boss.isAllow() && allenemy.get(0).getSpawn() == 4 && allenemy2.get(0).getSpawn() == 4){
			boss.setAllow(true);
			//System.out.println(1);
			boss.setPosition(player.getX(), player.getY() + 100);
			boss.setthehitbox(player.getX() - 1600, player.getY() + 100, boss.getWidth(), boss.getHeight());
		}
		if(boss.isAllow()){
			//System.out.println(1);
			boss.draw(renderer.getBatch(), player, player2, allenemy, 10);}
			//System.out.println(boss.isAllow());	
	
		// stage render *************************************************************
		if (stageindex == 0){
			stage_1render();
		}
		// **************************************************************************
		renderer.getBatch().end();
		if (!single && player.isGgez() && player2.isGgez()){
			delaytimeend();
			ggez();
			if (delay <= 0 ){
				player.end();
				if (!single) {
					player2.end();
				}
					dispose();
					game.setScreen(new FailScreen(game, stageindex, single));
			}
		}
		else if (single && player.isGgez()){
			delaytimeend();
			ggez();
			if (delay <= 0 ){
				if (modeindex == 1){
					player.end();
					if (!single) {
						player2.end();
					}
					dispose();
					game.setScreen(new selectrank(game, -1 , player.getPoint(), name));
				}
				else{
					player.end();
					if (!single) {
						player2.end();
					}
					dispose();
				game.setScreen(new FailScreen(game, stageindex, single));
				
				}
			}
		}
		if (boss.isAllow()){
			hpBarBoss();
		}
		hpBarPlayer();
		ammoshow();
		createpoint();
		point_buy();
		//System.out.println(player.getHp());
		}
		
		/*if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			batch.begin();
			batch.draw(text_pause, Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/8);

			if (select % 3 ==  1 || select % 3 == -2){
				buttonOneSelect();
	    	}
	    	else if (select % 3 == 0){
	    		buttonThreeSelect();
	    	}
	    	else if (select % 3 == -1 || select % 3 == 2){
	    		buttonTwoSelect();
	    	}
			batch.end();
		}*/
	}
	public boolean isPoweroff() {
		return poweroff;
	}
	public void setPoweroff(boolean poweroff) {
		this.poweroff = poweroff;
	}
	// stage create and render *********************************
	public void stage_1render(){
	}
	// **********************************************************
	private void ggez() {
		batch.begin();
		batch.draw(ui.get(4), 0, 0,gd.getDisplayMode().getWidth(),  gd.getDisplayMode().getHeight() / 4 + gd.getDisplayMode().getHeight() / 8);
		batch.end();
	}
	//private String[] namenumber = {"white","black","green","red"};
	//private int[] arrayset = new int[7];
	private void createpoint() {
		howmanypoint();
		batch.begin();
		int x = 0;
		batch.draw(ui.get(2), (float) (gd.getDisplayMode().getWidth()/2 - 60), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 , 128, 64);
		for (int i = 0; i < 11; i++){
			batch.draw(number.findRegion(namenumber[x], arrayset[i]), (float) (gd.getDisplayMode().getWidth()/2 - 200 +32*(i+1)), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 50 , 32, 32);
		}
		batch.end();
	}
	private void howmanypoint(){
		int x = 0;
		int y = 0;
		x = player.getPoint();
		if (!single){
			y = player2.getPoint();
		}
		arrayset[0] = (int) x/10000;
		arrayset[1] = (int) (x%10000 - x%1000)/1000;
		arrayset[2] = (int) (x%1000 - x%100)/100;
		arrayset[3] = (int) (x%100 - x%10)/10;
		arrayset[4] = (int) x%10;
		arrayset[5] = 10;
		arrayset[6] = (int) y/10000;
		arrayset[7] = (int) (y%10000 - y%1000)/1000;
		arrayset[8] = (int) (y%1000 - y%100)/100;
		arrayset[9] = (int) (y%100 - y%10)/10;
		arrayset[10] = (int) y%10;
		}
	private void point_buy() {
		int x1 , x2 = 0 ;
		batch.begin();
		int c = 1;
		if (player.getAirsupport() >= 1){
			c = 0;
		}
		batch.draw(allitemshow2.get(6+c), (float) (gd.getDisplayMode().getWidth()/24 + 32*9), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 75 , 300, 80);
		if (player.isBuyinterface()){
			countbuy += 5;	
		}
		else {
			countbuy -= 5;
		}
		x1 = Math.max(-350, countbuy);
		x1 = Math.min(50, countbuy);
		batch.draw(allitemshow2.get(player.getIndex()), x1, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/3, 356  , 256 - (128 / 2));
		if (!single) {
			c = 1;
			if (player2.getAirsupport() >= 1){
				c = 0;
			}
			batch.draw(allitemshow2.get(6+c), (float) (gd.getDisplayMode().getWidth()-gd.getDisplayMode().getWidth()/3.5 + 32*9) - 100, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 75 , 300, 80);
			if (player2.isBuyinterface()){
				countbuy2 -= 5;
			}
			else {
				countbuy2 += 5;
			}
			x2 = Math.max(gd.getDisplayMode().getWidth() - 350 , countbuy2);
			x2 = Math.min(gd.getDisplayMode().getWidth() + 10, countbuy2);
			batch.draw(allitemshow2.get(player2.getIndex()), x2, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/3, 356 , 256 - (128 / 2));
			}		
		if (x1 >= 50){
			countbuy = 50;
		}
		else if (x1 <= -350){
			countbuy = -350;
		}
		if (x2 <= gd.getDisplayMode().getWidth() - 350){
			countbuy2 = gd.getDisplayMode().getWidth() - 350;
		}
		else if (x2 >= gd.getDisplayMode().getWidth() + 10){
			countbuy2 = gd.getDisplayMode().getWidth() + 10;
		}
		batch.end();
	}
	private void hpBarPlayer() {
		batch.begin();
		if (player.getHp() < 0){
			Texture img = new Texture("hp_bar\\"+"hpbar"+0+".png") ;
			batch.draw(img, (float) (gd.getDisplayMode().getWidth()/24), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12, gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		else if (player.getHp() > 100){
			Texture img = new Texture("hp_bar\\"+"hpbar"+100+".png") ;
			batch.draw(img, (float) (gd.getDisplayMode().getWidth()/24), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12, gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		else{
			Texture img = new Texture("hp_bar\\"+"hpbar"+player.getHp()+".png") ;
			batch.draw(img, (float) (gd.getDisplayMode().getWidth()/24), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12, gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		if (!single) {
		if (player2.getHp() < 0){
			Texture img = new Texture("hp_bar\\"+"hpbar"+0+".png") ;
			batch.draw(img, (float) (gd.getDisplayMode().getWidth()-gd.getDisplayMode().getWidth()/3.5) - 100, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12, gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		else if (player2.getHp() > 100){
			Texture img = new Texture("hp_bar\\"+"hpbar"+100+".png") ;
			batch.draw(img, (float) (gd.getDisplayMode().getWidth()-gd.getDisplayMode().getWidth()/3.5) - 100, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12, gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		else{
			Texture img = new Texture("hp_bar\\"+"hpbar"+player2.getHp()+".png") ;
			batch.draw(img, (float) (gd.getDisplayMode().getWidth()-gd.getDisplayMode().getWidth()/3.5) - 100, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12, gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
			}
		}
		batch.end();
	}
	public void hpBarBoss(){
		batch.begin();
		if (boss.getHp() < 0){
			Texture imgB = new Texture("hp_bar\\"+"hpbar"+0+".png") ;
			batch.draw(imgB, (float) (7.3*gd.getDisplayMode().getWidth()/20), (float) (15.7*gd.getDisplayMode().getHeight()/20), gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		else if (boss.getHp() > 100){
			Texture imgB = new Texture("hp_bar\\"+"hpbar"+100+".png") ;
			batch.draw(imgB, (float) (7.3*gd.getDisplayMode().getWidth()/20), (float) (15.7*gd.getDisplayMode().getHeight()/20), gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		else{
			Texture imgB = new Texture("hp_bar\\"+"hpbar"+boss.getHp()*2+".png") ;
			batch.draw(imgB, (float) (7.3*gd.getDisplayMode().getWidth()/20), (float) (15.7*gd.getDisplayMode().getHeight()/20), gd.getDisplayMode().getWidth()/4, gd.getDisplayMode().getHeight()/16);
		}
		batch.end();
	}
	private String[] namenumber = {"white","black","green","red"};
	private int[] arrayset = new int[15];
	public void ammoshow(){
		arrayset[0] = (int) (Math.max(0, player.getUse_ammoofgun()) / 10);
		arrayset[1] = (int) (Math.max(0, player.getUse_ammoofgun()) % 10);
		arrayset[2] = 10;
		arrayset[3] = (int) (player.getShowammo() / 100);
		arrayset[4] = (int) ((player.getShowammo()%100 - player.getShowammo()%10)/10);
		arrayset[5]	 = (int) (player.getShowammo() % 10);
		batch.begin();
		batch.draw(allammoshow.get(player.getTypecheck()), (float) (gd.getDisplayMode().getWidth()/24), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 50 , 64, 64);
		for (int i = 2; i <= 7; i++){
			batch.draw(number.findRegion(namenumber[player.getTypecheck()], arrayset[i - 2]), (float) (gd.getDisplayMode().getWidth()/24 + 32*i), gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 50 , 32, 32);
		}
		if (!single) {
			arrayset[0] = (int) (Math.max(0, player2.getUse_ammoofgun()) / 10);
			arrayset[1] = (int) (Math.max(0, player2.getUse_ammoofgun()) % 10);
			arrayset[2] = 10;
			arrayset[3] = (int) (player2.getShowammo() / 100);
			arrayset[4] = (int) (int) ((player2.getShowammo()%100 -player2.getShowammo()%10)/10);
			arrayset[5]	 = (int) (player2.getShowammo() % 10);
			batch.draw(allammoshow.get(player2.getTypecheck()),(float) (gd.getDisplayMode().getWidth()-gd.getDisplayMode().getWidth()/3.5) - 100, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 50, 64, 64);
			for (int i = 2; i <= 7; i++){
				batch.draw(number.findRegion(namenumber[player2.getTypecheck()], arrayset[i - 2]), (float) (gd.getDisplayMode().getWidth()-gd.getDisplayMode().getWidth()/3.5 + 32*i) - 100, gd.getDisplayMode().getHeight()-gd.getDisplayMode().getHeight()/12 - 50 , 32, 32);
			}
		}
		batch.end();
	}
	public void ccc(){
		for (int i = 0; i < player.getAllammo().size(); i++){
		if (player.getAllammo().get(i).overlaps(player2.getBoundingRectangle()))
			System.out.println("hello");;
		}
	}
	/*public void updatecamera(){
		if (player.isFire()){
			x1 -= 75;
		}else {
			x1 += 75;
		}
		x1 = Math.min(300, x1);
		x1 = Math.max(-300, x1);
		camera.position.set(player.getX() + x1, player.getY(), 0);
		camera.zoom = 0.8f;
		camera.update();
	}*/
	public void delaytimeend(){
		delay -= 1;
	}
	public void updatecamera(){
		if (player.isFire()){
			x1 -= 1;
		}else {
			x1 += 1;
		}
		x1 = Math.min(300, x1);
		x1 = Math.max(-300, x1);
		camera.position.set(player.getX() + x1, player.getY(), 0);
		camera.zoom = 0.8f;
		camera.update();
	}
	public void updatecamerafor2(){
		camera.position.set((player.getX() + player2.getX())/2,(player.getY() + player2.getY())/2, 0);
		camera.zoom = 0.8f;
		camera.update();
	}
	/*
	public void updatecamera3(){
		camera.position.set(allenemy.get(3).getX(),allenemy.get(3).getY(), 0);
		camera.zoom = 0.8f;
		camera.update();
	}*/
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		player.setOksound(true);
		if (tab == true){
		isPressESC = false;
		tab = false;
		dispose_pause();
		Gdx.graphics.setContinuousRendering(true);
		}
	}
	public void dispose_pause(){
		button1.dispose();
		button2.dispose();
		button3.dispose();
		
	}
	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		sound.stop();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		map.dispose();
		//renderer.dispose();
		sound.dispose();
		playerAtlas.dispose();
		playerAtlas1.dispose();
		playerAtlas2.dispose();
		playerAtlas3.dispose();
		number.dispose();
		for(Texture item : tex){
			item.dispose();
		}
		bg.dispose();
		for (Texture item : ui){
			item.dispose();
		}
		for (Texture item : allitemshow2){
			item.dispose();
		}
	}
	public void buttonOneSelect(){
		button1 = new Texture("pause\\button_resume_click.png");
		button2 = new Texture("pause\\button_restart.png");
		button3 = new Texture("pause\\button_menu.png");
	}
	
	public void buttonTwoSelect(){
		button1 = new Texture("pause\\button_resume.png");
		button2 = new Texture("pause\\button_restart_click.png");
		button3 = new Texture("pause\\button_menu.png");
	}
	
	public void buttonThreeSelect(){
		button1 = new Texture("pause\\button_resume.png");
		button2 = new Texture("pause\\button_restart.png");
		button3 = new Texture("pause\\button_menu_click.png");
	}}
