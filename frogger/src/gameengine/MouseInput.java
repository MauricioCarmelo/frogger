package gameengine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	
	
	int mouseX;
	int mouseY;
	
	public MouseInput(){
		
	}
	
	public int getX() {
		return this.mouseX;
	}
	
	public int getY() {
		return this.mouseY;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.mouseX = 0;
		this.mouseY = 0;
	}

}
