package tw.brad.spring1.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController		// => Bean => Ioc => Server Starting
@RequestMapping("/brad01")
public class Brad01 {
	public Brad01() {
		System.out.println("Brad01()");
	}
	
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("Brad01:test1()");
	}
	
	@RequestMapping("/test2")
	public String test2() {
		System.out.println("Brad01:test2()");
		return "Test2";
	}
	
	
}
