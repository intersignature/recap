package com.game.only.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player1 extends Player implements InputProcessor{
	private boolean inputoff = true;
	//private Player player;
	public Player1(ArrayList<Animation> barban, TiledMapTileLayer collisionLayer, int num, ArrayList<Texture> tex) {
		super(barban, collisionLayer, num, tex);
		// TODO Auto-generated constructor stub
	}
	public boolean keyDown(int keycode) {
		if (!inputoff){
			
		}
		else{
		switch(keycode){
		case Keys.W:
			check();
			if (isCtrl()){
				if (getStack() <= 1){
					getArraykey()[0] = true;
					break;
				}
				break;
			}
			if (isCanjump()){
				getVelocity().y = getSpeed1().jump();
				setCanjump(false);
			}
			break;
		case Keys.A:
			check();
			setFire(true);
			if (isCtrl()){
				if (getStack() <= 1){
					getArraykey()[1] = true;
					break;
				}
				break;
			}
			getSpeed1().step(0, !isCanjump());
			break;
		case Keys.D:

			setFire(false);
			check();
			if (isCtrl()){
				if (getStack() <= 1){
					getArraykey()[2] = true;
					break;
				}
				break;
			}
			getSpeed1().step(1, !isCanjump());
			break;
		case Keys.S:
			if (isAllow()){
				
			}else{
				check();
				if (isCtrl()){
					if (getStack() <= 1){
						getArraykey()[3] = true;
						break;
					}
					break;
				}
				if (isBuyinterface()){
					int[] array = {0, 0, 0, 0, 0, 0}; //
					if (this.getPoint() >= array[getIndex()] && getAirsupport() <= 0){
						if (getIndex() == 5){
							this.setTime(4000);
						}
						else if (getIndex() == 4){
							setAirtime(30);
						}
						else {
							createitem(getIndex());
						}
						this.setPoint(getPoint() - array[getIndex()]);
						setAirsupport(500);
					}
				}
			}
			
			break;
		case Keys.Q:
			positionofindex(-1);
			break;
		case Keys.E:
			positionofindex(1);
			break;
		case Keys.C:
			setBuyinterface(!isBuyinterface());
			break;
		case Keys.Z:
			if (isAllow()){
				
			}else{
			setRes(true);
			}
			break;
		case Keys.SPACE:
			if (isAllow()){
				
			}else{
				if (getRocket() > 0){
					setRocketopen(true);
					setShot(true);
				
				}
				else if (getLazer() > 0){
					createammolazer();
					setShot(true);
				
				}
				else if (getAmmoauto() > 0){
					setAutofire(true);
				
				}
				else{
					createammo();
					setShot(true);
					
				}
			}
			System.out.println(getX()+" "+getY());
			//System.out.println(getX()+" "+getY());
			break;
		case Keys.CONTROL_LEFT:
			getVelocity().x = getSpeed1().stepbreak(0);
			getVelocity().x = getSpeed1().stepbreak(1);
			setCtrl(!isCtrl());
		}
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.W:
			if (isCtrl()){
				getArraykey()[0] = false;
				setStack(getStack() - 1);
				break;
			}
			break;
		case Keys.A:
			if (isCtrl()){
				getArraykey()[1] = false;
				setStack(getStack() - 1);
				break;
			}
			getVelocity().x = getSpeed1().stepbreak(0);
		
			break;
		case Keys.D:
			if (isCtrl()){
				getArraykey()[2] = false;
				setStack(getStack() - 1);
				break;
			}
			getVelocity().x = getSpeed1().stepbreak(1);
			break;
		case Keys.S:
			if (isCtrl()){
				getArraykey()[3] = false;
				setStack(getStack() - 1);
				break;
			}
			break;
		case Keys.Q:
			break;
		case Keys.E:
			break;
		case Keys.C:
			
			break;
		case Keys.Z:
			setRes(false);
			setRestime(0);
			break;
			
		case Keys.SPACE:
			if (getRocket() > 0){
				createrocket(getPower());
				setPower(0);
				setRocketopen(false);
			}
			if (getAmmoauto() > 0){
				setAutofire(false);
			}

			break;
		case Keys.CONTROL_LEFT:
		}
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public void end() {
		// TODO Auto-generated method stub
		//sound_rifle.dispose();
		inputoff  = false;
	}
}
