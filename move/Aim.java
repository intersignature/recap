package com.game.only.move;

public class Aim {
	boolean w,a,s,d;
	int stats = 0;
	private int x;
	private int y;
	public Aim(){
	}
	public void aim(boolean getw, boolean geta, boolean getd, boolean gets){
		if (getw){
			stats = 3;
			x = 0;
			y = 1;
		}
		if (getd){
			stats = 1;
			x = 1;
			y = 0;
		}
		if (geta){
			stats = 5;
			x = -1;
			y = 0;
		}
		if (gets){
			stats = 7;
			x = 0;
			y = -1;
		}
		if (getw && geta){
			stats = 4;
			x = -1;
			y = 1;
		}
		if (getw && getd){
			stats = 2;
			x = 1;
			y = 1;
		}
		if (gets && geta){
			stats = 6;
			x = -1;
			y = -1;
		}
		if (gets && getd){
			stats = 8;
			x = 1;
			y = -1;
		}
	}
	public int getx() {
		// TODO Auto-generated method stub
		return x;
	}
	public int gety() {
		// TODO Auto-generated method stub
		return y;
	}
}
