package tw.brad.spring1.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad06")
public class Brad06 {
	@Value("${company.name}")
	private String companyName;
	
	@Value("${age}")
	private Integer age;
	
	@RequestMapping("/m1")
	public void m1() {
		System.out.println(companyName);
		System.out.println(age);
	}
	
}
