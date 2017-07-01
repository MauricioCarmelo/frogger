package mygame;

import java.awt.Point;

import gameengine.Game;

public class Frog {
	
	public static final int FROG_WIDTH = 50;
	public static final int FROG_HEIGHT = 50;
	
	Point posFrog;
	int life;
	int initialX, initialY;
	
	public Frog(int x, int y) {
		this.posFrog = new Point();
		this.posFrog.setLocation(x, y);
		initialX = x;
		initialY = y;
		this.life = 3;
	}
	
	double getPosX() {
		return this.posFrog.getX();	
	}
	
	double getPosY() {
		return this.posFrog.getY();
	}
	
	int getLife(){
		return this.life;
	}
	void setPosX(double x) {
		this.posFrog.setLocation(x, posFrog.getY());
	}
	
	void setPosY(double y) {
		this.posFrog.setLocation(posFrog.getX(), y);
	}
	
	void setLife(int life) {
		this.life = life;
	}
	
	void moveUp(){
		setPosY(this.posFrog.getY() - Frogger.STREET_WIDTH);
	}
	
	void moveDown(){
		setPosY(this.posFrog.getY() + Frogger.STREET_WIDTH);
	}
	
	void moveRight() {
		setPosX(this.posFrog.getX() + Frogger.STREET_WIDTH);
	}
	
	void moveLeft() {
		setPosX(this.posFrog.getX() - Frogger.STREET_WIDTH);
	}
	
	void putInitialPosition() {
		setPosX(initialX);
		setPosY(initialY);
	}
	
	void adjustPosition() {
		if(getPosX() < 0){
			setPosX(0+10);
		}
		if(getPosY() < Frogger.HEADER_HEIGHT) {
			setPosY(Frogger.HEADER_HEIGHT);
		}
		if(getPosY() > Game.FRAME_HEIGHT - Frog.FROG_HEIGHT) {
			setPosY(Game.FRAME_HEIGHT - Frog.FROG_HEIGHT-Frogger.ERROR);			
		}
		if(getPosX() > Game.FRAME_WIDTH - Frog.FROG_WIDTH) {
			setPosX(Game.FRAME_WIDTH - Frog.FROG_WIDTH-Frogger.ERROR);			
		}
	}
}