package tw.brad.spring1.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/brad09")
public class Brad09 {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@RequestMapping("/test1")
	public void test1() {
		String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
		RestTemplate template = new RestTemplate();
		String content = template.getForObject(url, String.class);
		//System.out.println(content);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Hotel> hotels = mapper.readValue(content, new TypeReference<List<Hotel>>() {});
//			for (Hotel hotel: hotels) {
//				System.out.println(hotel.getName());
//			}
			
			jdbc.update("DELETE FROM hotel", new MapSqlParameterSource());
			jdbc.update("ALTER TABLE hotel auto_increment = 1", new MapSqlParameterSource());
			
			String sql = """
					INSERT INTO hotel (name,addr,tel)
					VALUES (:name, :addr, :tel)
					""";
			MapSqlParameterSource[] params = 
				new MapSqlParameterSource[hotels.size()];
			for (int i=0; i<hotels.size(); i++) {
				params[i] = new MapSqlParameterSource();
				params[i].addValue("name", hotels.get(i).getName());
				params[i].addValue("addr", hotels.get(i).getAddr());
				params[i].addValue("tel", hotels.get(i).getTel());
			}			
			int[] n = jdbc.batchUpdate(sql, params);
			
			System.out.printf("%d / %d\n", n.length, hotels.size());
			System.out.println("Finish");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@RequestMapping("/test2")
	public void test2() {
		String url = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx";
		RestTemplate template = new RestTemplate();
		
		Hotel[] hotels = template.getForObject(url, Hotel[].class);
		for(Hotel hotel : hotels) {
			System.out.println(hotel.getName());
		}
		
		
	}
	
	
}





