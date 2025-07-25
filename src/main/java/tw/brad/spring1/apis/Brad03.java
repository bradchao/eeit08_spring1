package tw.brad.spring1.apis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller		// Bean
@RequestMapping("/brad03")
public class Brad03 {
	public Brad03() {System.out.println("Brad03()");}
	
	@RequestMapping("/test1")
	public String test1() {
		
		return "test1";
	}
	
}
