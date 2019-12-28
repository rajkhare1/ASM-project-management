package com.raj.pma.springExample;

public class Car {
	
	Engine e;
	Door d;
	Tires t;
	
	public Car(Engine e, Door d, Tires t) {
		
		this.e = e;
		this.d = d;
		this.t = t;
	}
	
	public void printCarDetails() {
		System.out.println(this.e+" "+this.d);
	}

}
