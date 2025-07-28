package tw.brad.spring1.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad10")
public class Brad10 {

	@Autowired
	private Scooter scooter;
	
	public Brad10() {
		System.out.println("Brad10()");
	}
	
	@RequestMapping("/test1")
	public void test1() {
		scooter.m1();
	}
	
}
