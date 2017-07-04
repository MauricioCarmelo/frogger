package mygame;

import java.awt.Image;

public class Truck extends Vehicle {

	public static final int WIDTH = 150;
	
	public Truck(double x, double y){
		super(x, y);
	}
	
	public Truck(double x, double y, double velocity){
		super(x, y, velocity);
	}
	
	public Truck(double x, double y, double velocity, Image image){
		super(x, y, velocity, image);
	}
	
}
