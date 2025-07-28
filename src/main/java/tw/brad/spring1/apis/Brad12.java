package tw.brad.spring1.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad12")
public class Brad12 {

	@Autowired
	@Qualifier("northJdbc")
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private OrderDetailRowMapper mapper;
	
	/*
	 SELECT o.OrderID id, p.ProductName pname,
	  			od.UnitPrice price, od.Quantity qty
	 FROM orders o
	 JOIN orderdetails od ON o.OrderID = od.OrderID
	 JOIN products p ON od.ProductID = p.ProductID
	 WHERE o.OrderID = :orderId
	 */
	@GetMapping("/order/{orderId}")
	public List<OrderDetail> test1(@PathVariable int orderId) {
		String sql = """
					 SELECT o.OrderID id, p.ProductName pname,
					  			od.UnitPrice price, od.Quantity qty
					 FROM orders o
					 JOIN orderdetails od ON o.OrderID = od.OrderID
					 JOIN products p ON od.ProductID = p.ProductID
					 WHERE o.OrderID = :orderId
				""";
		Map<String,Object> params = new HashMap<>();
		params.put("orderId", orderId);
		return jdbc.query(sql, params, mapper);
	}
	
	
}
