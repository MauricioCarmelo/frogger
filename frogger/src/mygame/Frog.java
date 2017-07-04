package mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import gameengine.Game;

public class Frog {
	
	public static final int FROG_WIDTH = 50;
	public static final int FROG_HEIGHT = 50;
	public static final int FONT_LIFE_SIZE = 50;
	public static final int LIFE_POSX = 550;
	public static final int LIFE_POSY = 75;
	
	
	Point posFrog;
	int life;
	int initialX, initialY;
	private static Level currentLevel;
	private boolean youwon = false;
	
	public Frog(int x, int y) {
		this.posFrog = new Point();
		this.posFrog.setLocation(x, y);
		initialX = x;
		initialY = y;
		this.life = 3;
		currentLevel = Level.LEVEL_1;
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
	
	static Level getCurrentLevel(){
		return currentLevel;
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
	
	void nextLevel(){
		
		if(currentLevel == Level.LEVEL_1){
			currentLevel = Level.LEVEL_2;
		}
		
		else if(currentLevel == Level.LEVEL_2){
			currentLevel = Level.LEVEL_3;
		}
		
		else if(currentLevel == Level.LEVEL_3){
			youwon = true;
		}
		putInitialPosition();
	}
	
	boolean youWon(){
		if (youwon){
			return true;
		}
		else
			return false;
	}
	
	void putInitialPosition() {
		setPosX(initialX);
		setPosY(initialY);
	}
	
	void adjustPosition() {
		
		if(getPosX() < 0){
			
			setPosX(0+Frogger.ERROR);
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
	
	void drawLife(Graphics2D g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("", Font.BOLD, FONT_LIFE_SIZE));
		g.drawString(Integer.toString(this.life), LIFE_POSX, LIFE_POSY);
	}
}





