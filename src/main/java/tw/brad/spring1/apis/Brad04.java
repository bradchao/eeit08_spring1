package tw.brad.spring1.apis;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@RequestMapping("/m3")
	public void m3(@RequestBody User user) {
		System.out.println(user.getName());
		System.out.println(user.getGender());
		System.out.println(user.getAge());
	}
	
	@RequestMapping("/m4/{id}/{name}")
	public void m4(
			@PathVariable String name,
			@PathVariable Integer id) {
		System.out.printf("%d : %s\n", id, name);
	}
	
	@RequestMapping("/m5/{id}/{name}")
	public void m5(
			@PathVariable String name,
			@PathVariable Integer id,
			@RequestParam(required = false, defaultValue = "0") String x, 
			@RequestParam(required = false, defaultValue = "0") String y,
			@RequestBody User user			
			) {
		System.out.printf("%d : %s : %s : %s\n", id, name, x, y);
		System.out.println(user.getName());
		System.out.println(user.getGender());
		System.out.println(user.getAge());		
	}
	
	@RequestMapping("/m6")
	public void m6(
			@RequestHeader String x, 
			@RequestHeader Integer y,
			@RequestHeader(name = "Content-Type") String contentType
			) {
		System.out.printf("%s : %d : %s\n", x, y, contentType);
	}
	
	
	
}
