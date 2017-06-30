package mygame;
	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameengine.Game;
import gameengine.MouseInput;
	
public class Menu {
	
	static final int MENU_WIDTH = 120;
	static final int MENU_POSY = 150;
	static final int BUTTON_DISTANCE = 75;
	static final int BUTTON_WIDTH = 200;
	static final int BUTTON_HEIGHT = 50;
	static final int MENU_FONT_SIZE = 50;
	static final int BUTTON_FONT_SIZE = 30;
	static final int BUTTON_PADDINGX = 19;
	static final int BUTTON_PADDINGY = 30;
	
	public Rectangle playButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH, 
			MENU_POSY  + BUTTON_DISTANCE*1, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public Rectangle scoresButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH, 
			MENU_POSY  + BUTTON_DISTANCE*2, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public Rectangle aboutButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH,
			MENU_POSY  + BUTTON_DISTANCE*3, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public Rectangle quitButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH,
			MENU_POSY  + BUTTON_DISTANCE*4, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	boolean isActive;
	
	public Menu() {
		isActive = true;
	}
	
	public void update() {
		
		isActive = true;
		
		if(Game.mouseInput.getX() >= (Game.FRAME_WIDTH/2 + MENU_WIDTH) 
				&& Game.mouseInput.getY() >= MENU_POSY  + BUTTON_DISTANCE*1
				&& Game.mouseInput.getX() <= Game.FRAME_WIDTH/2 + MENU_WIDTH + BUTTON_WIDTH
				&& Game.mouseInput.getY() <= MENU_POSY  + BUTTON_DISTANCE*1 + BUTTON_HEIGHT ) {
			
			isActive = false;
		}
		
		if(Game.mouseInput.getX() >= (Game.FRAME_WIDTH/2 + MENU_WIDTH) 
				&& Game.mouseInput.getY() >= MENU_POSY  + BUTTON_DISTANCE*3
				&& Game.mouseInput.getX() <= Game.FRAME_WIDTH/2 + MENU_WIDTH + BUTTON_WIDTH
				&& Game.mouseInput.getY() <= MENU_POSY  + BUTTON_DISTANCE*3 + BUTTON_HEIGHT ) {
			
			//printNames();
		}
		
		if(Game.mouseInput.getX() >= (Game.FRAME_WIDTH/2 + MENU_WIDTH) 
				&& Game.mouseInput.getY() >= MENU_POSY  + BUTTON_DISTANCE*4
				&& Game.mouseInput.getX() <= Game.FRAME_WIDTH/2 + MENU_WIDTH + BUTTON_WIDTH
				&& Game.mouseInput.getY() <= MENU_POSY  + BUTTON_DISTANCE*4 + BUTTON_HEIGHT ) {
			
			// sair do jogo
			Game.endLoop();
		}
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, MENU_FONT_SIZE);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Frogger Game", Game.FRAME_WIDTH / 2, MENU_POSY);
		
		Font font1 = new Font("arial", Font.BOLD, BUTTON_FONT_SIZE);
		g.setFont(font1);
		g.drawString("New Game", playButton.x + BUTTON_PADDINGX, playButton.y + BUTTON_PADDINGY);
		g.drawString("High Scores", scoresButton.x + BUTTON_PADDINGX, scoresButton.y + BUTTON_PADDINGY);
		g.drawString("About", aboutButton.x + BUTTON_PADDINGX, aboutButton.y + BUTTON_PADDINGY);
		g.drawString("Quit", quitButton.x + BUTTON_PADDINGX, quitButton.y + BUTTON_PADDINGY);
		
		g2d.draw(playButton);
		g2d.draw(scoresButton);
		g2d.draw(aboutButton);
		g2d.draw(quitButton);
		
	}

	public boolean isActive() {
		return isActive;
	}
}