package tw.brad.spring1.apis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
	
	public MyConfig() {
		System.out.println("MyConfig()");
	}
	
	@Bean	// bean => Ioc => Autowired
	public Scooter createScooter() {
		System.out.println("do something...");
		return new Scooter();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	
}
