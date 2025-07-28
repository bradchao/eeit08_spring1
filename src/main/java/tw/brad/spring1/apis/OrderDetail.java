package tw.brad.spring1.apis;

import org.springframework.stereotype.Component;

@Component
public class OrderDetail {
	private int id;
	private String pname;
	private double price;
	private int qty;
	private double total;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		this.total = this.price * this.qty;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
		this.total = this.price * this.qty;
	}
	public double getTotal() {
		return total;
	}

	
	
	
	
}
