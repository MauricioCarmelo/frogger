package mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import gameengine.Game;
import gameengine.InputManager;

public class Frogger extends Game {
	
	public static final int HEADER_HEIGHT = 190; // 35 pixels estão escondidos pelo frame
	public static final int STREET_WIDTH = 50;
	public static final int ERROR = 10;
	
	
	// variáveis necessárias para o jogo (bastante coisa)	
	Frog frog;
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	MyClock myClock;
	
	// construtor
	public Frogger() {
		// passa pro sapo o tamanho da janela pra ele se localizar
		frog = new Frog(getWidth() - Frog.FROG_WIDTH-ERROR, getHeight() - Frog.FROG_HEIGHT-ERROR);
		
		vehicles.add(new Car(getWidth()- 50, getHeight() - 160, 5));
		vehicles.add(new Truck(getWidth()- 50, getHeight() - 260, 3));
		vehicles.add(new Motorcycle(getWidth()- 50, getHeight() - 360, 1));
		
		myClock = new MyClock();
	}	
	
	public void onLoad() {
		
	}  
    
	public void updateLogic() {
		
		//movimento dos NPCs
		vehiclesMove();
		
		myClock.update();
		
		// movimento do sapo DE ACORDO COM USUARIO
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
			endLoop();
		}
		
		//ajusta a posicao do sapo
		if(frog.getPosX() < 0){
			frog.setPosX(0+10);
		}
		if(frog.getPosY() < HEADER_HEIGHT) {
			frog.setPosY(HEADER_HEIGHT);
		}
		if(frog.getPosY() > Game.FRAME_HEIGHT - Frog.FROG_HEIGHT) {
			frog.setPosY(Game.FRAME_HEIGHT - Frog.FROG_HEIGHT-ERROR);			
		}
		if(frog.getPosX() > Game.FRAME_WIDTH - Frog.FROG_WIDTH) {
			frog.setPosX(Game.FRAME_WIDTH - Frog.FROG_WIDTH-ERROR);			
		}
	}
	
	public void onRender(Graphics2D g) throws IOException {		
		
		// AQUI VAMOS DESENHAR OS ELEMENTOS
		//g.drawImage(ImageManager.getObject().loadImage("frog.png", 11, 16, 11, 16), 
		//		(int)frog.getPosX(), (int)frog.getPosY(), null);
		//desenhar um quadrado com as coordenadas do sapo
		g.setColor(Color.green);
		g.draw(new Rectangle2D.Double(frog.getPosX(), frog.getPosY(), 
				Frog.FROG_WIDTH, Frog.FROG_HEIGHT));		
		vehiclesPaint(g);		
		drawBackgroud(g);
		
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, 12));
		g.drawString(Integer.toString(myClock.getCurrentSecond()), 300, 300);
		
	}
	
	public void drawBackgroud(Graphics2D g){		
		
	}
	
	public void finishGame() {
        // terminar algum som ou algo do tipo        
    }	
	
	// METODOS AUXILIARES	
	void vehiclesMove(){		
		for(int i=0; i<vehicles.size(); i++){
			vehicles.get(i).move();
		}
	}
	
	void vehiclesPaint(Graphics2D g){
		
		for(int i=0; i<vehicles.size(); i++){			
			if(vehicles.get(i) instanceof Car ){
				g.setColor(Color.blue);
			}
			else if(vehicles.get(i) instanceof Truck ){
				g.setColor(Color.red);
			}
			else if(vehicles.get(i) instanceof Motorcycle ){
				g.setColor(Color.orange);
			}
			g.draw(new Rectangle2D.Double(vehicles.get(i).getPosX(), vehicles.get(i).getPosY(), 75, 50));
		}
	}
}