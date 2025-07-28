package tw.brad.spring1.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/brad11")
@RestController
public class Brad11 {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private HotelRowMapper mapper;
	
	@GetMapping("/hotels")
	public List<Hotel> test1() {
		String sql = "SELECT id, name, addr, tel FROM hotel";
		
		return jdbc.query(sql, mapper);
	}
	
	@GetMapping("/hotel/{hotelId}")
	public Hotel test2(@PathVariable Long hotelId) {
		String sql = "SELECT id, name, addr, tel FROM hotel WHERE id = :id";
		Map<String, Long> params = new HashMap<>();
		params.put("id", hotelId);
		List<Hotel> hs = jdbc.query(sql, params, mapper);
		if (hs.size() > 0) {
			return hs.get(0);
		}else {
			Hotel hotel = new Hotel();
			hotel.setErrorCode(-1);
			return hotel;
		}
	}
	
	
}
