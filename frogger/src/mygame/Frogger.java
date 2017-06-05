package mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import gameengine.Game;
import gameengine.InputManager;

//public class Frogger extends Game implements KeyListener{
public class Frogger extends Game {
	
	public static final int HEADER_HEIGHT = 20;
	public static final int STREET_WIDTH = 20;	
	
	// variáveis necessárias para o jogo (bastante coisa)	
	Frog frog;
	
	// construtor
	public Frogger() {
		// passa pro sapo o tamanho da janela pra ele se localizar
		frog = new Frog(getWidth() - Frog.FROG_WIDTH-10, getHeight() - Frog.FROG_HEIGHT-10);
	}
	
	public void onLoad() {
		
	}
	
    public void finishGame() {
        // terminar algum som ou algo do tipo        
    }
    
	public void updateLogic() {
		
		// movimento do sapo
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_UP) ) {
			if( frog.posFrog.getY() > HEADER_HEIGHT + 10){
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
	}
	
	public void onRender(Graphics2D g) throws IOException {
		g.setColor(Color.green);
		//desenhar um quadrado com as coordenadas do sapo		
		g.draw(new Rectangle2D.Double(frog.getPosX(), frog.getPosY(), Frog.FROG_WIDTH, Frog.FROG_HEIGHT));
		
		// AQUI VAMOS DESENHAR OS ELEMENTOS
		//g.drawImage(ImageManager.getObject().loadImage("frog.png", 11, 16, 11, 16), 
		//		(int)frog.getPosX(), (int)frog.getPosY(), null);
		
		drawBackgroud(g);
	}
	
	public void drawBackgroud(Graphics2D g){
		
		
	}
}
