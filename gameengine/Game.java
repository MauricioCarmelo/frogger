package gameengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

abstract public class Game implements WindowListener{
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 600;		
	
	private JFrame frame;
	
    private BufferStrategy bufferStrategy;
    
    boolean running;
	
	// construtor
	public Game() {	
		frame = new JFrame("Game name");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.addWindowListener(this);
		frame.addKeyListener(InputManager.getObject());		   
	}	
	
	public void loop() throws IOException {
		
		running = true;
		// carregar valores iniciais
		load();
		
		// loop principal do jogo
		while(running) {
			// atualizar buffer de entrada do teclado
			InputManager.getObject().update();
			// atualizar l�gica
			update();			
			// pintar na tela
			draw();
		}		
		//terminar o game
		finish();
	}
	
	public void load() {
		//frame.setUndecorated(true);
		//frame.setIgnoreRepaint(true);		
		frame.setLocation(100, 100);		
	    frame.setVisible(true);
	    frame.createBufferStrategy(2);        
        bufferStrategy = frame.getBufferStrategy();        
	}
	
	public void update() {
		updateLogic();
		Thread.yield();
	}
	
	public void draw() throws IOException {
		
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();        
        g.setColor(Color.black);        
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        
		onRender(g);
		
		g.dispose();
        bufferStrategy.show();
	}
	
	// terminar o loop
	public void endLoop() {
		this.running = false;
	}
	
	// sair do jogo
	public void finish() {
		
		finishGame();		
		// liberar buffer strategy.
        bufferStrategy.dispose();
        // liberar janela
        frame.dispose();
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	public int getHeight() {
		return frame.getHeight();
	}	
	
	// classes abstratas que precisam ser implementadas nas classes filhos.
	abstract public void onRender(Graphics2D g) throws IOException;
	abstract public void updateLogic();
	abstract public void finishGame();
	
	// m�todos que precisam ser implementados por causa do WindowListener
	public void windowClosing(WindowEvent e) {
		this.running = false;
    }
	
	JFrame getMainWindow() {
		return this.frame;
	}
	
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }   
    
    public void windowDeactivated(WindowEvent e) {
    }	
}