package mygame;

import java.awt.Image;
import java.awt.Point;


public class Vehicle {
	
	private Point posVehicle;
	private double velocity;
	private Image image;
	
	public Vehicle(double x, double y){
		this.posVehicle = new Point();
		this.posVehicle.setLocation(x, y);
		this.velocity = 0;
		this.image = null;
	}
	
	public Vehicle(double x, double y, double velocity){
		this.posVehicle = new Point();
		this.posVehicle.setLocation(x, y);
		this.velocity = velocity;
		this.image = null;
	}	
	
	public Vehicle(double x, double y, double velocity, Image image){
		this.posVehicle = new Point();
		this.posVehicle.setLocation(x, y);
		this.velocity = velocity;
		this.image = image;
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
	
	Image getImage(){
		return this.image;
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
	
	void setImage(Image image){
		this.image = image;
	}
	
	
	void move(){
		double x;
		x = this.getPosX() + this.getVelocity();
		this.posVehicle.setLocation(x, getPosY());
	}
	
	
}
