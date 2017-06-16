package mygame;

public class MyClock {
	
	//Clock clock;
	long initialSecond;
	long pastSecond;
	
	
	
	public MyClock(){
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
	
	
	
	
	
}
