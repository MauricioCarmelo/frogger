package mygame;

import java.io.IOException;

import gameengine.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		Game game = new Frogger();
		game.loop();
	}		
}