package wc.entity;

public class ContractVO {
	private int id;
	private String no;
	private String customerName; 
	private String contactsName;
	private String username;
	private String ducTime;
	private String price;
	private String status;
	private int day;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactsName() {
		return contactsName;
	}
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDucTime() {
		return ducTime;
	}
	public void setDucTime(String ducTime) {
		this.ducTime = ducTime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "ContractVO [id=" + id + ", no=" + no + ", customerName=" + customerName + ", contactsName="
				+ contactsName + ", username=" + username + ", ducTime=" + ducTime + ", price=" + price + ", status="
				+ status + ", day=" + day + "]";
	}
	
}
