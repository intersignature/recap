package com.game.only.move;

public class Speed {
	private float speed = 0;
	private float acc = 12.5f;
	private float acc_boss = 1.5f;
	private int[] swlr = {0,0};
	public Speed(){
		speed = 0;
	}
	public float jump(){
		float send = Math.max(400, Math.abs(speed) * 1.5f);
		return send;
	}
	public float superjump(){
		float send = Math.max(600, Math.abs(speed) * 1.5f);
		int x = 1;
		if (speed < 0){
			x = -1;
		}
		speed = 1250*x;
		return send;
	}
	public float superjumpV2(){
		float send = Math.max(800, Math.abs(speed) * 1.5f);
		int x = 1;
		if (speed < 0){
			x = -1;
		}
		speed = 850*x;
		return send;
	}
	public void str(){
		speed = Math.abs(speed);
		if (speed <= 250){
		speed += acc;
		}
		else {
			speed = 250;
		}
		//System.out.println(speed);
	}
	public void stl(){
		speed = -Math.abs(speed);
		if (speed >= -250){
		speed -= acc;
		}
		else {
			speed = -250;
		}
		//System.out.println(speed);
	}
	public void goto0() {
		speed = 0;
	}
	public float stepbreak(int sw){
		if (swlr[0] == sw+1){
			swlr[0] = swlr[1];
			swlr[1] = 0;
		}else{
			swlr[1] = 0;
		}
		return speed;
	}
	public float step(int sw, boolean jump){
		swlr[1] = swlr[0];
		swlr[0] = sw+1;
		if (jump){
			return speed;
		}
		return speed = acc;
	}
	public void update(boolean jump){
		if (jump){
			speed = speed;
		}
		else if (swlr[0] > 0){
			if (swlr[0] == 1){
				stl();
			}
			else if (swlr[0] == 2){
				str();
			}
		}
		else if (swlr[0] == 0){
			speed = 0;
		}
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public void stlsetspeed(float max) {
		speed = -Math.abs(speed);
		if (speed >= -max){
		speed -= acc;
		}
		else {
			speed = -max;
		}
	}
	public void strsetspeed(float max) {
		speed = Math.abs(speed);
		if (speed <= max){
		speed += acc;
		}
		else {
			speed = max;
		}
	}
	public void strboss3(){
		speed = Math.abs(speed);
		if (speed <= 300){
		speed += acc_boss;
		}
		else {
			speed = 300;
		}
		//System.out.println(speed);
	}
	public void stlboss3(){
		speed = -Math.abs(speed);
		if (speed >= -300){
		speed -= acc_boss;
		}
		else {
			speed = -300;
		}
		//System.out.println(speed);
	}
}
