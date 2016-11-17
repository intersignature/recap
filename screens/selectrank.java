package com.game.only.screens;

import com.badlogic.gdx.Game;

public class selectrank extends AbstractScreen{
	String name;
	int stageindex;
	int point;
	SaveData sd = new SaveData();
	int line = 0;
	public selectrank(Game game, String name) {
		super(game);
		this.name = name;
		this.stageindex = 0;
		this.point = 0;
	}
	public selectrank(Game game, int stageindex, int point,String name) {
		super(game);
		this.name = name;
		this.stageindex = stageindex;
		this.point = point;
	}

	@Override
	public void render(float delta) {
		if (stageindex < 5 && stageindex != -1){
			game.setScreen(new mutistage(game, true, stageindex, 1, point, name));
		}
		else {
			sd.appendTextFile(name+" "+point);
			game.setScreen(new leader(game, point, name));
		}
	}

}
