package mygame;
	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import gameengine.Game;
import gameengine.MouseInput;
	
public class Menu implements ImageObserver {
	static final int MENU_WIDTH = 120;
	static final int MENU_POSY = 150;
	static final int BUTTON_DISTANCE = 75;
	static final int BUTTON_WIDTH = 200;
	static final int BUTTON_HEIGHT = 50;
	static final int MENU_FONT_SIZE = 50;
	static final int NAMES_FONT_SIZE = 15;
	static final int NAMES_POSX = 150;
	static final int NAMES_POSY = 350;
	static final int NAMES_PADDING = 35;
	static final int BUTTON_FONT_SIZE = 30;
	static final int BUTTON_PADDINGX = 19;
	static final int BUTTON_PADDINGY = 30;
	private static final String INITIAL = "initial.png";
	
	public Rectangle playButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH, 
			MENU_POSY  + BUTTON_DISTANCE*1, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public Rectangle scoresButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH, 
			MENU_POSY  + BUTTON_DISTANCE*2, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public Rectangle aboutButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH,
			MENU_POSY  + BUTTON_DISTANCE*3, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public Rectangle quitButton = new Rectangle(Game.FRAME_WIDTH/2 + MENU_WIDTH,
			MENU_POSY  + BUTTON_DISTANCE*4, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	boolean isActive;
	boolean printAbout;
	
	public boolean isActive() {
		return isActive;
	}
	
	public void togglePrintAbout() {
		this.printAbout = !this.printAbout;
	}
	
	public Menu() {
		isActive = true;
		printAbout = false;
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
			
			togglePrintAbout();
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
		ImageIcon ii = new ImageIcon(INITIAL);
		Image source = ii.getImage();
		int errorX = 8;
		int errorY = 31;
		int x = errorX;
		int y = errorY;
		int frameX = 0;
		int frameY = 0;
		int width = 802;
		int height = 601;

		
		g2d.drawImage(source, x, y, x+width, y+height, frameX, frameY, frameX+width, frameY+height,this);
		
		Font font0 = new Font("arial", Font.BOLD, MENU_FONT_SIZE);
		g.setFont(font0);
		g.setColor(Color.GREEN);
		g.drawString("Frogger Game", Game.FRAME_WIDTH / 2, MENU_POSY);
		
		
		
		Font font1 = new Font("arial", Font.BOLD, BUTTON_FONT_SIZE);
		g.setFont(font1);
	
		Rectangle2D rect = new Rectangle2D.Double(playButton.x, playButton.y , BUTTON_WIDTH , BUTTON_HEIGHT);
		g.setColor(Color.WHITE);
		g.setColor(Color.red);
		((Graphics2D) g).fill(rect);
		g.drawString("New Game", playButton.x + BUTTON_PADDINGX, playButton.y + BUTTON_PADDINGY);
		g.drawString("High Scores", scoresButton.x + BUTTON_PADDINGX, scoresButton.y + BUTTON_PADDINGY);
		g.drawString("About", aboutButton.x + BUTTON_PADDINGX, aboutButton.y + BUTTON_PADDINGY);
		g.drawString("Quit", quitButton.x + BUTTON_PADDINGX, quitButton.y + BUTTON_PADDINGY);
		
		g2d.draw(playButton);
		g2d.draw(scoresButton);
		g2d.draw(aboutButton);
		g2d.draw(quitButton);
		
		if(printAbout == true) {
			renderAboutInformation(g);
		}
	}
	
	void renderAboutInformation(Graphics g){
		
		String nameAfonso = "Afonso 252856";
		String nameIsadora = "Isadora 264109";
		String nameMauricio = "Mauricio 273165";
		String nameVictoria = "Victoria 261575";
		
		g.setColor(Color.WHITE);
		Font fontDevelopers = new Font("arial", Font.ITALIC, NAMES_FONT_SIZE + 5);
		g.setFont(fontDevelopers);
		g.drawString("Developers", NAMES_POSX, NAMES_POSY + NAMES_PADDING*0);
		
		Font fontNames = new Font("arial", Font.PLAIN, NAMES_FONT_SIZE);
		g.setFont(fontNames);
		
		g.drawString(nameAfonso, NAMES_POSX, NAMES_POSY + NAMES_PADDING*1);
		g.drawString(nameIsadora, NAMES_POSX, NAMES_POSY + NAMES_PADDING*2);
		g.drawString(nameMauricio, NAMES_POSX, NAMES_POSY + NAMES_PADDING*3);
		g.drawString(nameVictoria, NAMES_POSX, NAMES_POSY + NAMES_PADDING*4);
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
	
}