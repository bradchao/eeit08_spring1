package tw.brad.spring1.apis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad07")
public class Brad07 {
	/*
	 * NamedParameterJdbcTemplate
	 * update() => insert, delete, update
	 * query() => select
	 */
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private Response response;
	
	@PostMapping("/member")
	public Response addMember(@RequestBody Member member) {
		String sql = """
				INSERT INTO member (account,passwd,name)
				VALUES (:account, :passwd, :name)
				""";
		Map<String,String> params = new HashMap<>();
		params.put("account", member.getAccount());
		params.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
		params.put("name", member.getName());
		
		jdbc.update(sql, params);
		
		response.setError(0);
		response.setMesg("Success");
		
		return response;
	}
	
	
	
}
