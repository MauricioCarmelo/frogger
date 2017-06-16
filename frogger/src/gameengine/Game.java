package gameengine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

abstract public class Game implements WindowListener{
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 600;	
	public static final int TPS = 50;
	public static final int MAX_FRAME_SKIP = 10;
	
	private int expectedTPS;
    private double expectedNanosPerTick;
	
	private JFrame frame;
	
    private BufferStrategy bufferStrategy;
    GameSpeedManager gameSpeedManager;
    
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
		load(); // carregar valores iniciais
		expectedTPS = TPS;
		int skippedFrames = 0;
        expectedNanosPerTick = GameSpeedManager.NANOS_IN_ONE_SECOND / expectedTPS;
        long nanoTimeAtNextTick = System.nanoTime();
		
		while(running) { // loop principal do jogo
			
			gameSpeedManager.update(); // atualizar gerenciador de velocidade
			
			if (System.nanoTime() > nanoTimeAtNextTick && skippedFrames < MAX_FRAME_SKIP) {				
				
				nanoTimeAtNextTick += expectedNanosPerTick;
				InputManager.getObject().update(); // atualizar buffer de entrada do teclado
				update(); // atualizar lógica
				skippedFrames++;
            }
			else {
				draw(); // pintar na tela
				skippedFrames = 0;
			}
		}		
		finish(); //terminar o jogo
	}
	
	public void load() {
		//frame.setUndecorated(true);
		//frame.setIgnoreRepaint(true);		
		frame.setLocation(100, 100);		
	    frame.setVisible(true);
	    frame.createBufferStrategy(2);        
        bufferStrategy = frame.getBufferStrategy();   
        gameSpeedManager = new GameSpeedManager();
        gameSpeedManager.start();
        onLoad();
	}
	
	public void update() {
		gameSpeedManager.countTick();
		updateLogic();
		Thread.yield();
	}
	
	public void draw() throws IOException {
		
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();        
        g.setColor(Color.black);        
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        
		onRender(g);
		
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, 12));
		g.drawString(gameSpeedManager.getTPS() + " tps", 200, 200);
		
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
	abstract public void onLoad();
	abstract public void onRender(Graphics2D g) throws IOException;
	abstract public void updateLogic();
	abstract public void finishGame();
	
	// métodos que precisam ser implementados por causa do WindowListener
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