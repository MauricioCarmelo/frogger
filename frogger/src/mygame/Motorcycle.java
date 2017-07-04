package mygame;

import java.awt.Image;

public class Motorcycle extends Vehicle {

	public static final int WIDTH = 50;
	
	public Motorcycle(double x, double y){
		super(x, y);
	}
	
	public Motorcycle(double x, double y, double velocity){
		super(x, y, velocity);
	}
	
	public Motorcycle(double x, double y, double velocity, Image image){
		super(x, y, velocity, image);
	}
	
}
