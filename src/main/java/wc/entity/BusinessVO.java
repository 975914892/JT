package wc.entity;

import java.math.BigDecimal;

public class BusinessVO {
	
	private int id;
	
	private String ownerName;
	
	private String name;
	
	private String origin;
	
	private String customerName;
	
	private int customerId;
	
	private BigDecimal totalPrice;
	
	private String contactsName;
	
	private String contractAddress;
	
	private String type;
	
	private int statusId;
	
	private int estimatePrice;
	
	private int gainRate;
	
	private String nextstepTime;
	
	private String nextstep;
	
	private String description;
	
	private String creatorName;
	
	private String createTime;
	
	private String updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(int estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	public int getGainRate() {
		return gainRate;
	}

	public void setGainRate(int gainRate) {
		this.gainRate = gainRate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "BusinessVO [id=" + id + ", ownerName=" + ownerName + ", name=" + name + ", origin=" + origin
				+ ", customerName=" + customerName + ", customerId=" + customerId + ", totalPrice=" + totalPrice
				+ ", contactsName=" + contactsName + ", contractAddress=" + contractAddress + ", type=" + type
				+ ", statusId=" + statusId + ", estimatePrice=" + estimatePrice + ", gainRate=" + gainRate
				+ ", nextstepTime=" + nextstepTime + ", nextstep=" + nextstep + ", description=" + description
				+ ", creatorName=" + creatorName + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	
	

}
