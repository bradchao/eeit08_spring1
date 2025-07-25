package tw.brad.spring1.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad02")
public class Brad02 {
	@Autowired
	private Bike bike1;
	
	@Autowired
	private Bike bike2;

	@Autowired
	@Qualifier("bike1")
	private Bike bike3;
	
	@Autowired
	private Bike3 myBike;

	public Brad02() {
		System.out.println("Brad02()");
	}
	
	@RequestMapping("/test1")
	public void test1() {
		bike1.upSpeed();
	}
	
	@RequestMapping("/test2")
	public void test2() {
		bike2.upSpeed();
	}

	@RequestMapping("/test3")
	public void test3() {
		bike3.upSpeed();
	}

	@RequestMapping("/test4")
	public void test4() {
		myBike.upSpeed();
	}

}
