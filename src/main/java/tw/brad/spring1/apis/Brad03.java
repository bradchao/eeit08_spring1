package tw.brad.spring1.apis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller		// Bean
@RequestMapping("/brad03")
public class Brad03 {
	public Brad03() {System.out.println("Brad03()");}
	
	@RequestMapping("/test1")
	public String test1(Model model) {
		
		
		model.addAttribute("companyName", "Brad Big Company");
		model.addAttribute("companyTel", "0912-123456");
		return "test1";
	}
	
}
