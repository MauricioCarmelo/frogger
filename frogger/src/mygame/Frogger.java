package mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.swing.ImageIcon;

import gameengine.Game;
import gameengine.InputManager;

@SuppressWarnings({ })
public class Frogger extends Game implements ImageObserver {
	
	public static final int HEADER_HEIGHT = 100; // 35 pixels estão escondidos pelo frame
	public static final int STREET_WIDTH = 50;
	public static final int STREET_HEIGHT = 50;
	public static final int ERROR = 10;
	public static final int SIDEWALK_POS = 153;
	public static final int STREET_Y = 399;
	public static final int HEIGHTWIDTH_ERROR = 8;
	public static final int BOTTOM_STREET_POS = 51;
	public static final int TOP_STREET_POS = 102;
	public static final int MIDDLE_STREET_POS = 0;
	public static final String SPRITES = "sprites.png";
	public static final String HEADER = "header.png";
	public static final String WON = "won.png";
	public static final int SCORE_POSX = 200;
	public static final int SCORE_POSY = 75;
	public static final int FONT_SIZE = 50;

	
	private enum STATE {
		MENU,
		GAME,
		END
	};
	
	Menu menu;
	Frog frog;
	Street street;
	Clock clock;
	private STATE state;
	int score;
	ImageIcon ii = new ImageIcon(SPRITES);
	Image source = ii.getImage();
	ImageIcon icon = new ImageIcon(HEADER);
	Image header = icon.getImage();
	ImageIcon wonicon = new ImageIcon(WON);
	Image won = wonicon.getImage();
	int animationState = 0;
	int currentSecond;
	int pastSecond;
	double maxPlayerY =600;

	
	public Frogger() {
		menu = new Menu();
		state = STATE.MENU;
		score = 0;
		maxPlayerY = 600;
	}
	
	public void onLoad() {
		frog = new Frog(getWidth()/2, getHeight() - Frog.FROG_HEIGHT - ERROR);
		clock = new Clock();
		street = new Street();
		currentSecond = clock.getQuarterSecond();
		pastSecond = currentSecond;
	}  
    
	public void updateLogic()  {
		if(state == STATE.GAME) {
			
			vehiclesMove();
			if (Colision.check(street.getVehicles(), frog.getPosX(), frog.getPosY())) {
				loseLife();
				frog.putInitialPosition();
			}

			if(frog.getPosY() == Street.LAST_STREET_POSY) {
				score += clock.getCurrentSecond()*getCurrentLevelBonus();
				street.clearVehicles();
				street.generateVelocities();
				frog.nextLevel();
				if (frog.youWon()){
					state = STATE.END;
				}
			}
		
			clock.update();
			if(clock.getCurrentSecond() < 0) {
				state = STATE.MENU;
			}
			
			verifyInput();
			frog.adjustPosition();
		}
		
		else if(state == STATE.MENU){
			menu.update();
			if( !menu.isActive() ) {
				state = STATE.GAME;
				onLoad();
			}
		}
		else if (state == STATE.END){
			//checkScore();
		}
	}
	

	
	
	private int getCurrentLevelBonus() {
		int bonus;
		Level currentLevel = Frog.getCurrentLevel();
		if (currentLevel == Level.LEVEL_1){
			bonus = 1;
		}
		else if (currentLevel == Level.LEVEL_1){
			bonus = 2;
		}
		else{
			bonus = 4;
		}

		return bonus;
	}

	void verifyInput() {
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_UP) ) {
			if( frog.getPosY() > HEADER_HEIGHT + ERROR){
				frog.moveUp();
				if (frog.getPosY() < maxPlayerY){
					maxPlayerY = frog.getPosY();
					score += frog.getLife()*getCurrentLevelBonus();	
				}
			}			
		}
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_DOWN) ) {
			if( frog.getPosY() < Game.FRAME_HEIGHT - Frog.FROG_HEIGHT) {
				frog.moveDown();
			}			
		}
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_RIGHT) ) {
			if( frog.getPosX() < Game.FRAME_WIDTH - Frog.FROG_WIDTH) {
				frog.moveRight();
			}
		}
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_LEFT) ) {
			if( frog.getPosX() > 0) {
				frog.moveLeft();
			}
		}
		
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_ESCAPE) ) {
			state = STATE.MENU;
		}
	}
	
	void loseLife() {
		frog.setLife(frog.getLife()-1);
		if(frog.getLife() < 0){
			state = STATE.MENU;
		}
	}
	
	public void onRender(Graphics2D g) throws IOException {		
		
		// AQUI VAMOS DESENHAR OS ELEMENTOS
		//g.drawImage(ImageManager.getObject().loadImage("frog.png", 11, 16, 11, 16), 
		//		(int)frog.getPosX(), (int)frog.getPosY(), null);
		
		if(state == STATE.GAME) {
			//desenhar um quadrado com as coordenadas do sapo
			drawBackgroud(g);
			vehiclesPaint(g);
			printAnimatedFroguinho(g);
			drawScore(g);			

			clock.draw(g);
			frog.drawLife(g);
			street.update();
		}
		
		else if(state == STATE.MENU) {
			menu.render(g);
		}
		else if (state == STATE.END){
			end(g);
		}
	}
	
	public void drawScore(Graphics2D g){
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, FONT_SIZE));
		g.drawString(Integer.toString(score), SCORE_POSX, SCORE_POSY);
	}
	
	private void printAnimatedFroguinho(Graphics2D g) {
		final int maxAnimated = 3;
		currentSecond = clock.getQuarterSecond();
		
		if(currentSecond > pastSecond){
			pastSecond = currentSecond;
			if (animationState < maxAnimated)
				animationState++;
			else
				animationState = 0;
		}
		printFroguinho(g);
		
		
	}

	private void printFroguinho(Graphics2D g){
		
		Graphics2D g2d = (Graphics2D) g;
		int width = 31;
		int height = 45;
		int frameY = 451;
		int frameX = 0 + animationState*(width +1);
		
		
		g2d.drawImage(source, (int)frog.getPosX(), (int)frog.getPosY(), (int)frog.getPosX()+width, (int)frog.getPosY()+height,
				frameX, frameY, frameX+width, frameY+height, this);
		
	}
	
	

	
	public void drawBackgroud(Graphics2D g) throws IOException{	
		int columns = 17;
		int rows = 6;
		int width = STREET_WIDTH;
		int height = STREET_HEIGHT;
		int x = getWidth() - STREET_WIDTH ;
		int y = getHeight() - STREET_HEIGHT - HEIGHTWIDTH_ERROR;
		int yBottom = y - STREET_HEIGHT;
		int yStreet = yBottom - STREET_HEIGHT;
		int yTop = yStreet - STREET_HEIGHT*rows;
		int yTopSidewalk = yTop - STREET_HEIGHT;
		int YError = 31;
		int frameXsidewalk = SIDEWALK_POS; 
		int frameXbottom = BOTTOM_STREET_POS;
		int frameXtop = TOP_STREET_POS;
		int frameY = STREET_Y;
		int frameYmiddle = MIDDLE_STREET_POS;
		int frameXheader = 0;
		int frameYheader = 0;
		int widthHeader = 802;
		int heightHeader = 100;
		int initX = HEIGHTWIDTH_ERROR;
		int initY = YError;
		Graphics2D g2d = (Graphics2D) g;
		
			
		for (int i = 0; i < columns ; i++){
			g2d.drawImage(source, x, y, x+width, y+height,
					frameXsidewalk, frameY, frameXsidewalk+width, frameY+height, this);
			
			g2d.drawImage(source, x, yBottom, x+width, yBottom+height,
					frameXbottom, frameY, frameXbottom+width, frameY+height, this);
			
			g2d.drawImage(source, x, yTopSidewalk, x+width, yTopSidewalk+height,
					frameXsidewalk, frameY, frameXsidewalk+width, frameY+height, this);
			
			g2d.drawImage(source, x, yTop, x+width, yTop+height,
					frameXtop, frameY, frameXtop+width, frameY+height, this);
			
			for (int j = 0; j < rows; j++){
				g2d.drawImage(source, x, yStreet, x+width, yStreet+height,
						frameYmiddle, frameY, frameYmiddle+width, frameY+height, this);
				yStreet -= STREET_HEIGHT;
			}
			yStreet = yBottom - STREET_HEIGHT;
			x -= STREET_WIDTH;
		}
		
		g2d.drawImage(header, initX, initY, initX+widthHeader, initY+heightHeader, frameXheader,
					frameYheader, frameXheader + widthHeader, frameYheader+heightHeader, this);

	}
	
	public void end(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		int errorX = 8;
		int errorY = 31;
		int x = errorX;
		int y = errorY;
		int frameX = 0;
		int frameY = 0;
		int width = 802;
		int height = 601;
		g2d.drawImage(won, x, y, x+width, y+height, frameX, frameY, frameX+width, frameY+height, this);
    }	
	
	void vehiclesMove(){		
		for(int i=0; i<street.getVehicles().size(); i++){
			street.getVehicles().get(i).move();
		}
	}
	
	void vehiclesPaint(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g;
		int frameX = 0;
		int frameY = 0;
		int widthCar = 100;
		int heightCar = 50;
		int widthTruck = 150;
		int heightTruck = 50;
		int heightMotorcycle = 44;
		int widthMotorcycle = 50;
		
		for(int i=0; i<street.getVehicles().size(); i++){			
			if(street.getVehicles().get(i) instanceof Car ){
				g2d.drawImage(street.getVehicles().get(i).getImage(), (int)street.getVehicles().get(i).getPosX(), 
						(int)street.getVehicles().get(i).getPosY(), (int)street.getVehicles().get(i).getPosX() + Car.WIDTH,
						(int)street.getVehicles().get(i).getPosY() + STREET_HEIGHT,
						frameX, frameY, frameX+widthCar, frameY+heightCar, this);
			}
			else if(street.getVehicles().get(i) instanceof Motorcycle ){
				g2d.drawImage(street.getVehicles().get(i).getImage(), (int)street.getVehicles().get(i).getPosX(), 
						(int)street.getVehicles().get(i).getPosY(), (int)street.getVehicles().get(i).getPosX() + Motorcycle.WIDTH,
						(int)street.getVehicles().get(i).getPosY() + heightMotorcycle,
						frameX, frameY, frameX+widthMotorcycle, frameY+heightMotorcycle, this);
			}
			
			else if(street.getVehicles().get(i) instanceof Truck ){
				g2d.drawImage(street.getVehicles().get(i).getImage(), (int)street.getVehicles().get(i).getPosX(), 
						(int)street.getVehicles().get(i).getPosY(), (int)street.getVehicles().get(i).getPosX() + Truck.WIDTH,
						(int)street.getVehicles().get(i).getPosY() + heightTruck,
						frameX, frameY, frameX+widthTruck, frameY+heightTruck, this);
			}
		}
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finishGame() {
		// TODO Auto-generated method stub
		
	}
}