package mygame;

import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


import gameengine.Game;
import gameengine.ImageManager;

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
	public static final String SPRITES = "sprites.png";
	
	Clock clock;
	int pastSecond;
	int currentSecond;
	int[] streetVelocities;
	

	ArrayList<Vehicle> vehicles;
	ArrayList<Image> cars = new ArrayList<Image>();
	ArrayList<Image> trucks = new ArrayList<Image>();
	ArrayList<Image> motorcycles = new ArrayList<Image>();
	
	public Street(){
		clock = new Clock();
		currentSecond = clock.getCurrentHalfSecond();
		pastSecond = currentSecond;

		vehicles = new ArrayList<Vehicle>();
		
		streetVelocities = new int[NUMBER_OF_STREETS];
		generateVelocities();
		getVehiclesImages();
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
		int randomindex;
		Vehicle vehicle;
		int x = 0; // Game.FRAME_WIDTH;
		
		if (randomNumber == CAR) {
			randomindex = ThreadLocalRandom.current().nextInt(0, cars.size());
			vehicle = new Car(x, y, streetVelocities[laneIndex], cars.get(randomindex));
		}
		
		else if (randomNumber == MOTORCYCLE) {
			randomindex = ThreadLocalRandom.current().nextInt(0, motorcycles.size());
			vehicle = new Motorcycle(x, y, streetVelocities[laneIndex], motorcycles.get(randomindex));
		}
		
		else{
			randomindex = ThreadLocalRandom.current().nextInt(0, trucks.size());
			vehicle = new Truck(x, y, streetVelocities[laneIndex], trucks.get(randomindex));
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
	
	public void getVehiclesImages(){
		ImageManager manager = new ImageManager();
		int motorcycle_initial_x = 0;
		int motorcycle_initial_y = 0;
		int motorcycle_rows = 1;
		int motorcycle_height = 44;
		int motorcycle_width = 50;
		int car_initial_x = 0;
		int car_initial_y = 45;
		int car_rows = 5;
		int car_height = 50;
		int car_width = 100;
		int truck_initial_x = 0;
		int truck_initial_y = 298;
		int truck_rows = 2;
		int truck_height = 50;
		int truck_width = 150;
		int columns = 7;
		
		manager.loadImage(SPRITES);
		motorcycles = manager.getImages(motorcycle_initial_x, motorcycle_initial_y, motorcycle_rows, columns, motorcycle_height, motorcycle_width);
		trucks = manager.getImages(truck_initial_x, truck_initial_y, truck_rows, columns, truck_height, truck_width);	
		cars = manager.getImages(car_initial_x, car_initial_y, car_rows, columns, car_height, car_width);

	}
	

}
