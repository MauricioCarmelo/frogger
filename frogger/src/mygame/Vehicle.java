package mygame;

import java.awt.Point;

public class Vehicle {
	
	Point posVehicle;
	double velocity;
	
	public Vehicle(double x, double y){
		this.posVehicle = new Point();
		this.posVehicle.setLocation(x, y);
		this.velocity = 0;
	}
	
	public Vehicle(double x, double y, double velocity){
		this.posVehicle = new Point();
		this.posVehicle.setLocation(x, y);
		this.velocity = velocity;
	}	
	
	// getters and setters
	double getPosX(){
		return this.posVehicle.getX();
	}
	
	double getPosY(){
		return this.posVehicle.getY();
	}
	
	double getVelocity(){
		return this.velocity;
	}
	
	void setPosX(double x){
		this.posVehicle.setLocation(x, this.getPosY());
	}
	
	void setPosY(double y){
		this.posVehicle.setLocation(this.getPosX(), y);
	}
	
	void setVelocity(double velocity){
		this.velocity = velocity;
	}
	
	void move(){
		double x;
		x = this.getPosX() - this.getVelocity();
		this.posVehicle.setLocation(x, getPosY());
	}
	
}
