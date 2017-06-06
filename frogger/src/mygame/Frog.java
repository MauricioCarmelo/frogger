package mygame;

import java.awt.Point;

//import gameengine.Game;

public class Frog {
	
	public static final int FROG_WIDTH = 50;
	public static final int FROG_HEIGHT = 50;
	
	Point posFrog;
	
	// construtor
	public Frog(int x, int y) {
		this.posFrog = new Point();
		this.posFrog.setLocation(x, y);
	}
	
	//getters and setters
	double getPosX() {
		return this.posFrog.getX();
		
	}
	
	double getPosY() {
		return this.posFrog.getY();
	}
	
	void setPosX(double x) {
		this.posFrog.setLocation(x, posFrog.getY());
	}
	
	void setPosY(double y) {
		this.posFrog.setLocation(posFrog.getX(), y);
	}		
	
	// m�todos de movimento
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
}