package mygame;

import java.util.ArrayList;

public class Colision {
	
	
	public static boolean check(ArrayList<Vehicle> vehicles, double frogXi, double frogYi) {
		
		double frogXf;
		
		double vehicleYi;
		double vehicleXi;
		double vehicleXf;
		
		int i=0;
	
		frogXf = frogXi + Frog.FROG_WIDTH;
		
		while( i < vehicles.size() ) {
			
			vehicleYi = vehicles.get(i).getPosY();
			
			if(vehicleYi == frogYi) {
				
				vehicleXi = vehicles.get(i).getPosX();
				
				if(vehicles.get(i) instanceof Car){
					vehicleXf= vehicleXi + Car.WIDTH;
				}
				else if( vehicles.get(i) instanceof Motorcycle ){
					vehicleXf= vehicleXi + Motorcycle.WIDTH;
				}
				else {
					vehicleXf= vehicleXi + Truck.WIDTH;
				}
				
				if( (vehicleXi <= frogXi && frogXi <= vehicleXf) || 
						(vehicleXi <= frogXf && frogXf <= vehicleXf)  ){
					return true;
				}
				
			}
			i++;
		}
		return false;
	
	}
}
