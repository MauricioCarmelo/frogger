package mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import gameengine.Game;
import gameengine.InputManager;

//public class Frogger extends Game implements KeyListener{
public class Frogger extends Game {
	
	public static final int HEADER_HEIGHT = 150; // 35 pixels estão escondidos pelo frame
	public static final int STREET_WIDTH = 20;	
	
	// variáveis necessárias para o jogo (bastante coisa)	
	Frog frog;
	Car car;
	
	// construtor
	public Frogger() {
		// passa pro sapo o tamanho da janela pra ele se localizar
		frog = new Frog(getWidth() - Frog.FROG_WIDTH-10, getHeight() - Frog.FROG_HEIGHT-10);
		car = new Car(getWidth()- 50, getHeight() - 200, 5);

	}	
	
	public void onLoad() {
		
	}
	
    public void finishGame() {
        // terminar algum som ou algo do tipo        
    }
    
	public void updateLogic() {
		
		//movimento dos NPCs
		car.move();
		
		
		
		// movimento do sapo DE ACORDO COM USUARIO
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_UP) ) {
			if( frog.getPosY() > HEADER_HEIGHT + 10){
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
			if( frog.getPosX() > Frog.FROG_WIDTH) {
				frog.moveLeft();
			}
		}
		
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_ESCAPE) ) {
			endLoop();
		}
		
		//ajusta a posicao do sapo
		if(frog.getPosX() < 0){
			frog.setPosX(0);
		}
		if(frog.getPosY() < HEADER_HEIGHT) {
			frog.setPosY(HEADER_HEIGHT+10);
		}
		if(frog.getPosY() > Game.FRAME_HEIGHT - Frog.FROG_HEIGHT) {
			frog.setPosY(Game.FRAME_HEIGHT - 1.5*Frog.FROG_HEIGHT);
		}
		if(frog.getPosX() > Game.FRAME_WIDTH - Frog.FROG_WIDTH) {
			frog.setPosX(Game.FRAME_WIDTH - 1.5*Frog.FROG_WIDTH);
		}
		
		
		try {
            Thread.sleep(30);
        } catch (InterruptedException ex) {
        	System.out.println("erro na thread sleep");
        }
	}
	
	public void onRender(Graphics2D g) throws IOException {		
		
		// AQUI VAMOS DESENHAR OS ELEMENTOS
		//g.drawImage(ImageManager.getObject().loadImage("frog.png", 11, 16, 11, 16), 
		//		(int)frog.getPosX(), (int)frog.getPosY(), null);
		//desenhar um quadrado com as coordenadas do sapo
		g.setColor(Color.green);
		g.draw(new Rectangle2D.Double(frog.getPosX(), frog.getPosY(), Frog.FROG_WIDTH, Frog.FROG_HEIGHT));
		g.setColor(Color.red);
		g.draw(new Rectangle2D.Double(car.getPosX(), car.getPosY(), 50, 50));
		drawBackgroud(g);
	}
	
	public void drawBackgroud(Graphics2D g){
		
		
	}
}
