package tw.brad.spring1.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadProperties {
	@Value("${file.upload.dir}")
	private String uploadDir;
	
	public String getUploadDir() {return uploadDir;}
}
