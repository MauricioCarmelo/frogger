package mygame;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameengine.Game;

public class Street {
	
	public static final int STREET_HEIGHT = 50;
	public static final int ERROR = 10;
	public static final int LAST_STREET_POSY = 129;
	public static final int NUMBER_OF_STREETS = 8;
	
	public static final int CAR = 0;
	public static final int MOTORCYCLE = 1;
	public static final int TRUCK = 2;
	
	public static final int MIN_VELOCITY_LEVEL1 = 10;
	public static final int MAX_VELOCITY_LEVEL1 = 10;
	public static final int MAX_VELOCITY_LEVEL2 = 10;
	public static final int MIN_VELOCITY_LEVEL2 = 10;
	public static final int MAX_VELOCITY_LEVEL3 = 10;
	public static final int MIN_VELOCITY_LEVEL3 = 10;
	
	Clock clock;
	int pastSecond;
	int currentSecond;
	int[] streetVelocities;
	
	ArrayList<Vehicle> vehicles;
	
	public Street() {
		clock = new Clock();
		currentSecond = clock.getCurrentHalfSecond();
		pastSecond = currentSecond;

		vehicles = new ArrayList<Vehicle>();
		
		streetVelocities = new int[NUMBER_OF_STREETS];
		generateVelocities();
	}
	
	public void update(){
		clock.update();
		currentSecond = clock.getCurrentHalfSecond();
		
		if(currentSecond > pastSecond){
			int randomLane = ThreadLocalRandom.current().nextInt(1, (NUMBER_OF_STREETS-1)+1);
			pastSecond = currentSecond;
			
			vehicles.add(generateVehicle(Game.FRAME_HEIGHT - ERROR - 
					Frogger.STREET_HEIGHT*(randomLane+1), randomLane));
		}
	}
	
	public Vehicle generateVehicle(int y, int laneIndex) {
		int randomNumber = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		Vehicle vehicle;
		int x = Game.FRAME_WIDTH;
		
		if (randomNumber == CAR) {
			vehicle = new Car(x, y, streetVelocities[laneIndex]);
		}
		
		else if (randomNumber == MOTORCYCLE) {
			vehicle = new Motorcycle(x, y, streetVelocities[laneIndex]);
		}
		
		else{
			vehicle = new Truck(x, y, streetVelocities[laneIndex]);
		}
		return vehicle;
	}
	
	ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	
	void clearVehicles() {
		vehicles.clear();
	}
	
	void generateVelocities() {
		
		int minVelocity;
		int maxVelocity;
		
		if (Frog.getCurrentLevel() == Level.LEVEL_1) {
			minVelocity = 1;
			maxVelocity = 10;
		}
		
		else if (Frog.getCurrentLevel() == Level.LEVEL_2) {
			minVelocity = 6;
			maxVelocity = 15;
		}
		else {
			minVelocity = 10;
			maxVelocity = 79;
		}
		
		for(int i=0; i < streetVelocities.length; i++) {
			int velocity = ThreadLocalRandom.current().nextInt(minVelocity, maxVelocity);
			streetVelocities[i] = velocity;
		}
	}
}
