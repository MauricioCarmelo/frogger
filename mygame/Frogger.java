package mygame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import gameengine.Game;
import gameengine.ImageManager;
import gameengine.InputManager;

//public class Frogger extends Game implements KeyListener{
public class Frogger extends Game {
	
	public static final int HEADER_HEIGHT = 50;
	public static final int STREET_WIDTH = 50;
	
	
	// variáveis necessárias para o jogo (bastante coisa)	
	Frog frog;
	
	// construtor
	public Frogger() {
		// passa pro sapo o tamanho da janela pra ele se localizar
		frog = new Frog(getWidth() - 2*Frog.FROG_WIDTH, getHeight() - Frog.FROG_HEIGHT);
	}
	
	public void onLoad() {
		
	}
	
    public void finishGame() {
        // terminar algum som ou algo do tipo        
    }
    
	public void updateLogic() {
		
		// movimento do sapo
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_UP) ) {
			if( frog.posFrog.getY() > HEADER_HEIGHT ){
				frog.moveUp();
				System.out.println(frog.posFrog.getY());
			}			
		}
		
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_DOWN) ) {
			if( frog.posFrog.getY() < Game.FRAME_HEIGHT - Frog.FROG_HEIGHT) {
				frog.moveDown();
				System.out.println(frog.posFrog.getY());
			}
			
		}
		
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_RIGHT) ) {
			if( frog.posFrog.getX() < Game.FRAME_WIDTH - 2*Frog.FROG_WIDTH) {
				frog.moveRight();
			}
		}
		
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_LEFT) ) {
			if( frog.posFrog.getX() > Frog.FROG_WIDTH) {
				frog.moveLeft();
			}
		}
		
		if( InputManager.getObject().isJustPressed(KeyEvent.VK_ESCAPE) ) {
			endLoop();
		}
	}
	
	public void onRender(Graphics2D g) throws IOException {
		g.setColor(Color.green);
		//desenhar um quadrado com as coordenadas do sapo		
		//g.draw(new Rectangle2D.Double(frog.getPosX(), frog.getPosY(), 20, 20));
		
		// AQUI VAMOS DESENHAR OS ELEMENTOS
		g.drawImage(ImageManager.getObject().loadImage("frog.png", 11, 16, 11, 16), 
				(int)frog.getPosX(), (int)frog.getPosY(), null);
		
		drawBackgroud(g);
	}
	
	public void drawBackgroud(Graphics2D g){
		
		
	}
	
	/*public void keyTyped(KeyEvent e) {
		
	}	
	public void keyPressed(KeyEvent e) {
		keyPool.put(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) {
		keyPool.remove(e.getKeyCode());
	}*/
}
