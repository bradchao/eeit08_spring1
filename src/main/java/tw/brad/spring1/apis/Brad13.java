package tw.brad.spring1.apis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/brad13")
public class Brad13 {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@PostMapping("/member/{id}")
	public void test1(@PathVariable Integer id, 
			@RequestParam MultipartFile upload) {
		
		try {
			byte[] bytes = upload.getBytes();
			String sql = "UPDATE member SET icon = :icon WHERE id = :id";
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("icon", bytes);
			params.put("id", id);
			
			int n = jdbc.update(sql, params);
			System.out.println(n > 0);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
