package tw.brad.spring1.apis;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	/*
	 	 SELECT o.OrderID id, o.OrderDate odate,
	 	 	p.ProductName pname, od.UnitPrice price, od.Quantity qty
	 	 FROM orders o
		 JOIN orderdetails od ON o.OrderID = od.OrderID
		 JOIN products p ON od.ProductID = p.ProductID
		 
		 WHERE o.OrderID = :orderId
	 */
	@GetMapping(value = {"/orders", "/orders/{orderId}"})
	public List<Order> test2(@PathVariable(required = false) Integer orderId){
		String sql = """
		 	 SELECT o.OrderID id, o.OrderDate odate,
		 	 	p.ProductName pname, od.UnitPrice price, od.Quantity qty
		 	 FROM orders o
			 JOIN orderdetails od ON o.OrderID = od.OrderID
			 JOIN products p ON od.ProductID = p.ProductID				
				""";
		Map<String,Object> params = new HashMap<>();
		if (orderId != null) {
			sql += "WHERE o.OrderID = :orderId";
			params.put("orderId", orderId);
		}
		
		List<Map<String,Object>> orderMap = jdbc.queryForList(sql, params);
		//System.out.println(orders.size());
		// Output => []
		
		HashMap<Integer, Order> orders = new HashMap<>();
		
		for (Map<String,Object> row : orderMap) {
			int oid = (Integer)row.get("id");
			Order order = orders.get(oid);
			if (order == null) {
				order = new Order();
				order.setOrderId(oid);
				LocalDateTime odate =  (LocalDateTime)row.get("odate");
				order.setOrderDate(odate.toString());
				orders.put(oid, order);
			}
			
			OrderDetail detail = new OrderDetail();
			detail.setId(oid);
			detail.setPname((String)row.get("pname"));
			detail.setPrice(((BigDecimal)row.get("price")).doubleValue());
			detail.setQty((Integer)row.get("qty"));
			
			order.getOrderDetails().add(detail);
		}
		
		return new ArrayList<Order>(orders.values());
	}
	
	
}
