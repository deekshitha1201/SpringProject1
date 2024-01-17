package com.example.Entity;





import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BusDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String busName;
	private String fromdestination;
	private String todestination;
	private String filterDate;
	private String time;
	private double price;
	@ManyToOne
   // @JoinColumn(name = "admin_id")
    private AdminInfo admininfo;
	@ManyToOne
	private UserInfo userinfo;
	
	public BusDetails(AdminInfo admininfo) {
		super();
		this.admininfo = admininfo;
	}
	public AdminInfo getAdmininfo() {
		return admininfo;
	}
	public void setAdmininfo(AdminInfo admininfo) {
		this.admininfo = admininfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
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
	public BusDetails(int id, String busName, String fromdestination, String todestination, String filterDate,
			String time, double price) {
		super();
		this.id = id;
		this.busName = busName;
		this.fromdestination = fromdestination;
		this.todestination = todestination;
		this.filterDate = filterDate;
		this.time = time;
		this.price = price;
	}
	
	public BusDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BusDetails [id=" + id + ", busName=" + busName + ", fromdestination=" + fromdestination
				+ ", todestination=" + todestination + ", filterDate=" + filterDate + ", time=" + time + ", price="
				+ price + ", admininfo=" + admininfo + "]";
	}
	public void setUserInfo(UserInfo user) {
		// TODO Auto-generated method stub
		
	}
	

}
