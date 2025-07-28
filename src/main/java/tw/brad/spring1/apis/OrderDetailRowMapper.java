package tw.brad.spring1.apis;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailRowMapper implements RowMapper<OrderDetail>{

	@Override
	public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(rs.getInt("id"));
		orderDetail.setPname(rs.getString("pname"));
		orderDetail.setPrice(rs.getDouble("price"));
		orderDetail.setQty(rs.getInt("qty"));
		
		return orderDetail;
	}

}
