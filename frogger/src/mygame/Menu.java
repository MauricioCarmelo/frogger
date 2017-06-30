package mygame;
	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameengine.Game;
	
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
	
	public Menu() {
		
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
	
	/*static final int WITHOUT_MENU = -1;
	static final int MENU0  = 0;
	static final int NUMBER_OF_ITEM = 4;
	static final int ITEM0 = 0;
	static final int ITEM1 = 1;
	static final int POSX = 50;
	static final int POSY = 50;
	static final int FONT_SIZE = 20;
	static final int PADDING = 15;
	
	int currentMenu;
	int selectedItem;
	
	String itens[];
	Graphics bbg;
	boolean isActive;
	
	Font font;
	Color colorForSelectedItem;
	Color colorForUnselectedItem;
	
	int posX;
	int posY;
	
	public Menu() {
		currentMenu = WITHOUT_MENU;
		selectedItem = ITEM1;
		
		font = new Font("Arial", Font.BOLD, FONT_SIZE);
		colorForSelectedItem = new Color(255, 0, 0);
		colorForUnselectedItem = new Color(0, 0, 0);
	}
	
	public Menu(int numeroDeItens, int x, int y, boolean ativo) {
		itens = new String[numeroDeItens];
		this.posX = x;
		this.posY = y;
		this.isActive = ativo;
	}
	
	public void update(KeyEvent e) {
		if (isActive) {
			controlMenu(e);
		}
	}
	
	private void controlMenu(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			selectedItem--;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			selectedItem++;
		}
		
		if (selectedItem >= itens.length) {
			selectedItem = ITEM0;
		}
		
		if (selectedItem < 0) {
			selectedItem = itens.length - 1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			currentMenu = selectedItem;
			isActive = false;
		}
	}
 
 
	public void voltarAoMenu(KeyEvent e){
	 
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			currentMenu = -1;  
			isActive = true;
		}
	}
 //esse método irá desenhar o nosso menu na tela!
 //a um loop (for) que irá listar todos os itens que está guardado em itens[]
 //um calculo é feito para a coordenada y de cada intem do nosso menu, para que eles fiquem
 //um distante do outro, então caso y = 10, o item 0 será: 10+(0*(20+15)) = 10
 //para o item 1 será: 10+(1*(20+15)) = 45
 //para o item 2 será: 10+(2*(20+15)) = 80
 //para o item 3 será: 10+(3*(20+15)) = 115, e assim por diante...
 public void desenharMenu() {
	  bbg.setFont(fonte);//seta a fonte que definimos bem acima na declaração de variáveis
	  for (int i = 0; i < itens.length; i++) {// aqui é o inicio do nosso loop
		   if(itemSelecionado == i){//se ele estiver selecionado muda a cor para vermelho e desenha o item na tela
			   bbg.setColor(corSelecionado);
			   bbg.drawString(itens[i], x, y+(i*(tamanhoDaFonte+distanciaEntreItens)));
		   }else{//se não estiver selecionado muda a cor para preto e desenha o item na tela
			   bbg.setColor(corNaoSelecionado);
			   bbg.drawString(itens[i], x, y+(i*(tamanhoDaFonte+distanciaEntreItens)));
		   }
	  	}
 	}*/
}