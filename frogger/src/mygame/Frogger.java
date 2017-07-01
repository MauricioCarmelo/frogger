package mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import gameengine.Game;
import gameengine.InputManager;

public class Frogger extends Game {
	
	public static final int HEADER_HEIGHT = 100; // 35 pixels estão escondidos pelo frame
	public static final int STREET_WIDTH = 50;
	public static final int STREET_HEIGHT = 50;
	public static final int ERROR = 10;
	
	private enum STATE {
		MENU,
		GAME
	};
	
	Menu menu;
	Frog frog;
	Street street;
	Clock clock;
	private STATE state;
	int score;
	
	public Frogger() {
		menu = new Menu();
		state = STATE.MENU;
		score = 0;
	}	
	
	public void onLoad() {
		System.out.println(getHeight());
		frog = new Frog(getWidth()/2, getHeight() - Frog.FROG_HEIGHT - ERROR);
		clock = new Clock();
		street = new Street();
	}  
    
	public void updateLogic() {
		if(state == STATE.GAME) {
			
			vehiclesMove();
			if (Colision.check(street.getVehicles(), frog.getPosX(), frog.getPosY())) {
				loseLife();
				frog.putInitialPosition();
			}
			
			if(frog.getPosY() == Street.LAST_STREET_POSY) {
				score += clock.getCurrentSecond();
				frog.putInitialPosition();
			}
		
			clock.update();
			if(clock.getCurrentSecond() < 0) {
				clock.reset();
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
		
		System.out.println(score);
	}
	
	void verifyInput() {
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_UP) ) {
			if( frog.getPosY() > HEADER_HEIGHT + ERROR){
				frog.moveUp();
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
			g.setColor(Color.green);
			g.draw(new Rectangle2D.Double(frog.getPosX(), frog.getPosY(), 
					Frog.FROG_WIDTH, Frog.FROG_HEIGHT));		
			vehiclesPaint(g);		
			drawBackgroud(g);
			
			clock.draw(g);
			frog.drawLife(g);
			street.update();
		}
		
		else if(state == STATE.MENU) {
			menu.render(g);
		}
	}
	
	public void drawBackgroud(Graphics2D g){		
		
	}
	
	public void finishGame() {
        // terminar algum som ou algo do tipo
    }	
	
	void vehiclesMove(){		
		for(int i=0; i<street.getVehicles().size(); i++){
			street.getVehicles().get(i).move();
		}
	}
	
	void vehiclesPaint(Graphics2D g){
		
		for(int i=0; i<street.getVehicles().size(); i++){			
			if(street.getVehicles().get(i) instanceof Car ){
				g.setColor(Color.blue);
				g.draw(new Rectangle2D.Double(street.getVehicles().get(i).getPosX(),
						street.getVehicles().get(i).getPosY(), 
						Car.WIDTH, STREET_HEIGHT));
			}
			else if(street.getVehicles().get(i) instanceof Motorcycle ){
				g.setColor(Color.orange);
				g.draw(new Rectangle2D.Double(street.getVehicles().get(i).getPosX(),
						street.getVehicles().get(i).getPosY(), 
						Motorcycle.WIDTH, STREET_HEIGHT));
			}
			
			else if(street.getVehicles().get(i) instanceof Truck ){
				g.setColor(Color.red);
				g.draw(new Rectangle2D.Double(street.getVehicles().get(i).getPosX(), 
						street.getVehicles().get(i).getPosY(), 
						Truck.WIDTH, STREET_HEIGHT));
			}
			
		}
	}
}