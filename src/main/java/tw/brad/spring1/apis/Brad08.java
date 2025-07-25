package tw.brad.spring1.apis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/brad08")
@RestController
public class Brad08 {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private Response response;
	
	@PostMapping(value= {"/member", "/member/{isGetId}"})
	public Response test1(
			@RequestBody Member member, 
			@PathVariable(required = false)Boolean isGetId) {
		
		isGetId = isGetId == null ? false : isGetId;
		System.out.println(isGetId);
		
		String sql = """
				INSERT INTO member (account,passwd,name)
				VALUES (:account, :passwd, :name)
				""";
		Map<String,String> params = new HashMap<>();
		params.put("account", member.getAccount());
		params.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
		params.put("name", member.getName());
		
		jdbc.update(sql, params);		
		
		
		return response;
	}
	
	
	
	
}
