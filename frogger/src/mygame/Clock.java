package mygame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Clock {
		
	//Clock clock;
	long initialSecond;
	long pastSecond;	
	
	public Clock(){
		initialSecond = System.currentTimeMillis()/1000;
		pastSecond = initialSecond;
	}
	
	public void update(){
		long currentSecond = System.currentTimeMillis()/1000;
		
		if(currentSecond > pastSecond){
			pastSecond = currentSecond;
		}
	}
	
	public int getCurrentSecond(){
		return (int)(pastSecond - initialSecond);
	}
	
	public void draw(Graphics2D g, int x, int y){
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, 12));
		g.drawString(Integer.toString(this.getCurrentSecond()), 300, 300);
	}	
}