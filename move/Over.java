package com.game.only.move;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Over {
	private TiledMapTileLayer collisionLayer;
	private float increment;
	private boolean over;
	public Over(TiledMapTileLayer collisionLayer){
		this.collisionLayer = collisionLayer;
	}
	public void inc(int array){
		if (array == 1){
			increment = collisionLayer.getTileWidth();
			}
		else if(array == 2){
			increment = collisionLayer.getTileHeight();
		}
		increment = increment / 2;
	}
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	
	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("Blocked");
	}
	public boolean collidesRL(float getHeight, float getY, float getWidth, float getX) {
		for(float step = 0; step <= getHeight; step += increment)
			if(isCellBlocked(getX + getWidth, getY + step))
				return true;
		return false;
	}
	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}
	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	public boolean collidesTB(float getHeight, float getY, float getWidth, float getX) {
		for(float step = 0; step <= getWidth; step += increment)
			if(isCellBlocked(getX + step, getY + getHeight))
				return true;
		return false;
	}
	public boolean collidesSS(float getHeight, float getY, float getWidth, float getX) {
		for(float step = 0; step <= getHeight; step += increment)
		if(isCellSss(getX + getWidth, getY + step))
			return true;
		return false;
	}
	public boolean isCellSss(float x, float y) {
			Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
			return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("sss");
		}
	}
