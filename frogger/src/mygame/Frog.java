package mygame;

import java.awt.Point;

public class Frog {
	
	public static final int FROG_WIDTH = 11;
	public static final int FROG_HEIGHT = 16;
	
	Point posFrog;
	
	// construtor (1)
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
	
	// métodos de movimento
	void moveUp(){
		//this.posY = this.posY - frameHeight/5;
		setPosY(this.posFrog.getY() - Frogger.STREET_WIDTH);
	}
	
	void moveDown(){
		//this.posY = this.posY + frameHeight/5;
		setPosY(this.posFrog.getY() + Frogger.STREET_WIDTH);
	}
	
	void moveRight() {
		//this.posX = this.posX + frameWidth/5;
		setPosX(this.posFrog.getX() + Frogger.STREET_WIDTH);
	}
	
	void moveLeft() {
		// blabla
		// BLABLABLABLABLA
		//this.posX = this.posX - frameWidth/5;
		setPosX(this.posFrog.getX() - Frogger.STREET_WIDTH);
	}	
}