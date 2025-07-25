package tw.brad.spring1.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int n = jdbc.update(sql, new MapSqlParameterSource(params) , keyHolder);
		int id = keyHolder.getKey().intValue();
		
		if (n > 0) {
			response.setError(0);
			response.setMesg("Insert Success");
			response.setInsertId(isGetId?id:0);
			
		}else {
			response.setError(-1);
			response.setMesg("Insert Failure");
		}
		
		
		
		return response;
	}
	
	@PostMapping("/members")
	public Response test2(@RequestBody List<Member> members) {
		String sql = """
				INSERT INTO member (account,passwd,name)
				VALUES (:account, :passwd, :name)
				""";
		MapSqlParameterSource[] params = 
			new MapSqlParameterSource[members.size()];
		for (int i=0; i<members.size(); i++) {
			params[i] = new MapSqlParameterSource();
			params[i].addValue("account", members.get(i).getAccount());
			params[i].addValue("passwd", BCrypt.hashpw(members.get(i).getPasswd(), BCrypt.gensalt()));
			params[i].addValue("name", members.get(i).getName());
		}
		
		int[] n = jdbc.batchUpdate(sql, params);
		System.out.println(n.length);
		
		
		return response;
	}
	
	
}
