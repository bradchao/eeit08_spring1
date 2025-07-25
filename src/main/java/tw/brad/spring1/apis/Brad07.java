package tw.brad.spring1.apis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@DeleteMapping("/member/{id}")
	public Response delMember(@PathVariable Integer id) {
		String sql = """
				DELETE FROM member 
				WHERE id = :id
				""";
		Map<String,Integer> params = new HashMap<>();
		params.put("id", id);
		
		jdbc.update(sql, params);
		
		response.setError(0);
		response.setMesg("Success");
		return response;
	}
	
	@PutMapping("/member")
	public Response updateMember(@RequestBody Member member) {
		String sql = """
				UPDATE member
				SET account = :account, name = :name 
				WHERE id = :id
				""";
		Map<String,Object> params = new HashMap<>();
		params.put("id", member.getId());
		params.put("account", member.getAccount());
		params.put("name", member.getName());
		
		int n = jdbc.update(sql, params);
		if (n > 0) {
			response.setError(0);
			response.setMesg("Success");
		}else {
			response.setError(1);
			response.setMesg("Update Failure");
		}
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
}
