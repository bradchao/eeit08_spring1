package tw.brad.spring1.apis;

import org.springframework.stereotype.Component;

@Component
public class Bike3 {
	public Bike3() {System.out.println("Bike3()");}
	public void upSpeed() {
		System.out.println("Bike3:upSpeed()");
	}
	public void downSpeed() {
		System.out.println("Bike3:downSpeed()");
	}
}
