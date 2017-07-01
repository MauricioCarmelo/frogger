package mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Clock {
	
	public static final int MILLISECONDS_IN_A_SECOND = 1000;
	public static final int FONT_SIZE = 50;
	public static final int CLOCK_LIMIT_IN_SECONDS = 5;
	public static final int CLOCK_POSX = 25;
	public static final int CLOCK_POSY = 75;

	long initialSecond;
	long pastSecond;	
	
	public Clock(){
		initialSecond = System.currentTimeMillis()/MILLISECONDS_IN_A_SECOND;
		pastSecond = initialSecond;
	}
	
	public void update(){
		long currentSecond = System.currentTimeMillis()/MILLISECONDS_IN_A_SECOND;
		
		if(currentSecond > pastSecond){
			pastSecond = currentSecond;
		}
	}
	
	public int getCurrentSecond(){
		return CLOCK_LIMIT_IN_SECONDS - (int)(pastSecond - initialSecond);
	}
	
	public int getCurrentHalfSecond(){
		return (int)(pastSecond - initialSecond);
	}
	
	public void reset() {
		initialSecond = System.currentTimeMillis()/MILLISECONDS_IN_A_SECOND;
		update();		
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, FONT_SIZE));
		g.drawString(Integer.toString(this.getCurrentSecond()), CLOCK_POSX, CLOCK_POSY);
	}
}