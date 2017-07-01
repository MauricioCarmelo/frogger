package mygame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameengine.Game;

public class Street {
	
	public static final int STREET_HEIGHT = 50;
	public static final int ERROR = 10;
	public static final int LAST_STREET_POSY = 129;
	
	public static final int CAR = 0;
	public static final int MOTORCYCLE = 1;
	public static final int TRUCK = 2;
	
	public static final int VELOCITY_CAR = 5;
	public static final int VELOCITY_MOTORCYCLE = 6;
	public static final int VELOCITY_TRUCK = 7;
	
	Clock clock;
	int pastSecond;
	int currentSecond;
	
	ArrayList<Vehicle> vehicles;
	
	public Street() {
		clock = new Clock();
		currentSecond = clock.getCurrentHalfSecond();
		pastSecond = currentSecond;
		
		vehicles = new ArrayList<Vehicle>();
	}
	
	public void update(){
		clock.update();
		currentSecond = clock.getCurrentHalfSecond();
		
		if(currentSecond > pastSecond){
			int randomLane = ThreadLocalRandom.current().nextInt(1, 8 + 1);
			pastSecond = currentSecond;
			
			vehicles.add(generateVehicle(Game.FRAME_HEIGHT - ERROR - 
					Frogger.STREET_HEIGHT*(randomLane+1)));
		}
	}
	
	public Vehicle generateVehicle(int y) {
		int randomNumber = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		Vehicle vehicle;
		int x = Game.FRAME_WIDTH;
		
		if (randomNumber == CAR) {
			vehicle = new Car(x, y, VELOCITY_CAR);
		}
		
		else if (randomNumber == MOTORCYCLE) {
			vehicle = new Motorcycle(x, y, VELOCITY_MOTORCYCLE);
		}
		
		else{
			vehicle = new Truck(x, y, VELOCITY_TRUCK);
		}
		return vehicle;
	}
	
	ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
}
