package tw.brad.spring1.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad04")
public class Brad04 {
	@RequestMapping("/m1")
	public void m1(
			@RequestParam(required = false, defaultValue = "0") String x, 
			@RequestParam(required = false, defaultValue = "0") String y) {
		try {
			Integer xx = Integer.parseInt(x);
			Integer yy = Integer.parseInt(y);
			int rr = xx + yy;
			System.out.printf("%d + %d = %d\n", xx, yy, rr);
		}catch(Exception e) {
			System.out.println("ERROR:" + e);
		}
	}
	
	@RequestMapping("/m2")
	public String m2(
			@RequestParam(required = false, defaultValue = "0") Integer x, 
			@RequestParam(required = false, defaultValue = "0") Integer y) {
		int r = x + y;
		System.out.printf("%d + %d + %d\n", x, y, r);
		return r+"";
	}
	
	
	
}
