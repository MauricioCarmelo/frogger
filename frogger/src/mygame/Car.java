package mygame;

import java.awt.Image;

public class Car extends Vehicle {

	public static final int WIDTH = 100;
	
	public Car(double x, double y){
		super(x, y);
	}
	
	public Car(double x, double y, double velocity){
		super(x, y, velocity);
	}
	
	public Car(double x, double y, double velocity, Image image){
		super(x, y, velocity, image);
	}
	
}
