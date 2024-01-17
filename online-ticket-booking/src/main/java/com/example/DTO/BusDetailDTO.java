package com.example.DTO;

public class BusDetailDTO {
	private String fromdestination;
	private String todestination;
	private String filterDate;
	private String time;
	private double price;
	public String getFromdestination() {
		return fromdestination;
	}
	public void setFromdestination(String fromdestination) {
		this.fromdestination = fromdestination;
	}
	public String getTodestination() {
		return todestination;
	}
	public void setTodestination(String todestination) {
		this.todestination = todestination;
	}
	public String getFilterDate() {
		return filterDate;
	}
	public void setFilterDate(String filterDate) {
		this.filterDate = filterDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public BusDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BusDetailDTO(String fromdestination, String todestination, String filterDate, String time, double price) {
		super();
		this.fromdestination = fromdestination;
		this.todestination = todestination;
		this.filterDate = filterDate;
		this.time = time;
		this.price = price;
	}
	@Override
	public String toString() {
		return "BusDetailDTO [fromdestination=" + fromdestination + ", todestination=" + todestination + ", filterDate="
				+ filterDate + ", time=" + time + ", price=" + price + "]";
	}
	
}
