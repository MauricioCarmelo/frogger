package gameengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

abstract public class Game implements WindowListener{
	
	public static final int FRAME_WIDTH = 816;
	public static final int FRAME_HEIGHT = 639;	
	public static final int TPS = 50;
	public static final int MAX_FRAME_SKIP = 10;
	
	private int expectedTPS;
    private double expectedNanosPerTick;
	
	private JFrame frame;
	
    private BufferStrategy bufferStrategy;
    GameSpeedManager gameSpeedManager;
    
    public static MouseInput mouseInput = new MouseInput();
    
    static boolean running;
	
	public Game() {	
		frame = new JFrame("Frogger");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.addWindowListener(this);
		frame.addKeyListener(InputManager.getObject());
		frame.addMouseListener(mouseInput);
	}
	
	public void loop() throws IOException  {
		
		running = true;	
		load();
		expectedTPS = TPS;
		int skippedFrames = 0;
        expectedNanosPerTick = GameSpeedManager.NANOS_IN_ONE_SECOND / expectedTPS;
        long nanoTimeAtNextTick = System.nanoTime();
		
		while(running) {
			
			gameSpeedManager.update();
			
			if (System.nanoTime() > nanoTimeAtNextTick && skippedFrames < MAX_FRAME_SKIP) {				
				
				nanoTimeAtNextTick += expectedNanosPerTick;
				InputManager.getObject().update();
				update();
				skippedFrames++;
            }
			else {
				draw();
				skippedFrames = 0;
			}
		}		
		finish();
	}
	
	public void load()  {		
		frame.setLocation(100, 100);
		frame.setLocationRelativeTo(null);
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
		
		g.dispose();
        bufferStrategy.show();
	}
	
	// terminar o loop
	public static void endLoop() {
		running = false;
	}
	
	public void finish() {
		finishGame();		
        bufferStrategy.dispose();
        frame.dispose();
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	public int getHeight() {
		return frame.getHeight();
	}	
	
	abstract public void onLoad() ;
	abstract public void onRender(Graphics2D g) throws IOException;
	abstract public void updateLogic();
	abstract public void finishGame();
	
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