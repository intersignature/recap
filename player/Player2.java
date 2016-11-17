package com.game.only.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player2 extends Player implements InputProcessor{
	private boolean inputoff = true;
	//private Player player;
	public Player2(ArrayList<Animation> barban, TiledMapTileLayer collisionLayer, int num, ArrayList<Texture> tex) {
		super(barban, collisionLayer, num, tex);
		// TODO Auto-generated constructor stub
	}
	public boolean keyDown(int keycode) {
		if (!inputoff){
			
		}
		else{
		switch(keycode){
		case Keys.I:
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
		case Keys.J:
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
		case Keys.L:

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
		case Keys.K:
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
					int[] array = {500, 1500, 2000, 100, 3000, 4000}; //
					if (this.getPoint() >= array[getIndex()] && getAirsupport() <= 0){
						if (getIndex() == 5){
							this.setTime(4000);
						}
						else if (getIndex() == 4){
							setAirtime(30);
						}
						else {
							createitem(getIndex());
							this.setPoint(getPoint() - array[getIndex()]);
						}
						setAirsupport(500);
					}
				}
			}
			
			break;
		case Keys.U:
			positionofindex(-1);
			break;
		case Keys.O:
			positionofindex(1);
			break;
		case Keys.N:
			setBuyinterface(!isBuyinterface());
			break;
		case Keys.M:
			if (isAllow()){
				
			}else{
			setRes(true);
			}
			break;
		case Keys.ENTER:
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
		case Keys.CONTROL_RIGHT:
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
		case Keys.I:
			if (isCtrl()){
				getArraykey()[0] = false;
				setStack(getStack() - 1);
				break;
			}
			break;
		case Keys.J:
			if (isCtrl()){
				getArraykey()[1] = false;
				setStack(getStack() - 1);
				break;
			}
			getVelocity().x = getSpeed1().stepbreak(0);
		
			break;
		case Keys.L:
			if (isCtrl()){
				getArraykey()[2] = false;
				setStack(getStack() - 1);
				break;
			}
			getVelocity().x = getSpeed1().stepbreak(1);
			break;
		case Keys.K:
			if (isCtrl()){
				getArraykey()[3] = false;
				setStack(getStack() - 1);
				break;
			}
			break;
		case Keys.U:
			break;
		case Keys.O:
			break;
		case Keys.N:
			
			break;
		case Keys.M:
			setRes(false);
			setRestime(0);
			break;
			
		case Keys.ENTER:
			if (getRocket() > 0){
				createrocket(getPower());
				setPower(0);
				setRocketopen(false);
			}
			if (getAmmoauto() > 0){
				setAutofire(false);
			}
			break;
		case Keys.CONTROL_RIGHT:
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
