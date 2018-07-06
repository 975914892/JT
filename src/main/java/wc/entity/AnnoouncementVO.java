package wc.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AnnoouncementVO {
	private Long id;
	private Long orderId;
	private String username;
	private String title;
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private String createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private String updateTime;
	private String color;
	private String department;
	private Short status;
	private Short isshow;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getIsshow() {
		return isshow;
	}
	public void setIsshow(Short isshow) {
		this.isshow = isshow;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "AnnoouncementVO [id=" + id + ", orderId=" + orderId + ", username=" + username + ", title=" + title
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", color=" + color + ", department="
				+ department + ", status=" + status + ", isshow=" + isshow + ", content=" + content + "]";
	}
	 
	 
}
