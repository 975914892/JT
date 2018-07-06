package wc.entity;


public class LeadsVO {
	
	private int id;
	
	private String name;
	
	private String contactsName;
	
	private String saltname;
	
	private String mobile;
	
	private String nextstepTime;
	
	private String nextstep;
	
	private String ownerName;
	
	private String creatorName;
	
	private String haveTime;
	
	private Long day;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getSaltname() {
		return saltname;
	}

	public void setSaltname(String saltname) {
		this.saltname = saltname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNextstepTime() {
		return nextstepTime;
	}

	public void setNextstepTime(String nextstepTime) {
		this.nextstepTime = nextstepTime;
	}

	public String getNextstep() {
		return nextstep;
	}

	public void setNextstep(String nextstep) {
		this.nextstep = nextstep;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getHaveTime() {
		return haveTime;
	}

	public void setHaveTime(String haveTime) {
		this.haveTime = haveTime;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "LeadsVO [id=" + id + ", name=" + name + ", contactsName=" + contactsName + ", saltname=" + saltname
				+ ", mobile=" + mobile + ", nextstepTime=" + nextstepTime + ", nextstep=" + nextstep + ", ownerName="
				+ ownerName + ", creatorName=" + creatorName + ", haveTime=" + haveTime + ", day=" + day + "]";
	}

	

}
