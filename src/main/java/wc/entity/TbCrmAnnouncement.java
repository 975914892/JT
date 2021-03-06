package wc.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbCrmAnnouncement {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.ID
	 *
	 * @mbg.generated
	 */
	private Long id;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.ORDER_ID
	 *
	 * @mbg.generated
	 */
	private Long orderId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.USER_ID
	 *
	 * @mbg.generated
	 */
	private Long userId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.TITLE
	 *
	 * @mbg.generated
	 */
	private String title;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.CREATE_TIME
	 *
	 * @mbg.generated
	 */
	 @JsonFormat(pattern="yyyy-MM-dd HH-mm-ss")
	private String createTime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.UPDATE_TIME
	 *
	 * @mbg.generated
	 */
	 @JsonFormat(pattern="yyyy-MM-dd HH-mm-ss")
	private String updateTime;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.COLOR
	 *
	 * @mbg.generated
	 */
	private String color;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.DEPARTMENT
	 *
	 * @mbg.generated
	 */
	private String department;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.STATUS
	 *
	 * @mbg.generated
	 */
	private Short status;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.ISSHOW
	 *
	 * @mbg.generated
	 */
	private Short isshow;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column TB_CRM_ANNOUNCEMENT.CONTENT
	 *
	 * @mbg.generated
	 */
	private String content;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.ID
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.ID
	 *
	 * @mbg.generated
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.ID
	 *
	 * @param id
	 *            the value for TB_CRM_ANNOUNCEMENT.ID
	 *
	 * @mbg.generated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.ORDER_ID
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.ORDER_ID
	 *
	 * @mbg.generated
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.ORDER_ID
	 *
	 * @param orderId
	 *            the value for TB_CRM_ANNOUNCEMENT.ORDER_ID
	 *
	 * @mbg.generated
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.USER_ID
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.USER_ID
	 *
	 * @mbg.generated
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.USER_ID
	 *
	 * @param userId
	 *            the value for TB_CRM_ANNOUNCEMENT.USER_ID
	 *
	 * @mbg.generated
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.TITLE
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.TITLE
	 *
	 * @mbg.generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.TITLE
	 *
	 * @param title
	 *            the value for TB_CRM_ANNOUNCEMENT.TITLE
	 *
	 * @mbg.generated
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.CREATE_TIME
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.CREATE_TIME
	 *
	 * @mbg.generated
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.CREATE_TIME
	 *
	 * @param createTime
	 *            the value for TB_CRM_ANNOUNCEMENT.CREATE_TIME
	 *
	 * @mbg.generated
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.UPDATE_TIME
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.UPDATE_TIME
	 *
	 * @mbg.generated
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.UPDATE_TIME
	 *
	 * @param updateTime
	 *            the value for TB_CRM_ANNOUNCEMENT.UPDATE_TIME
	 *
	 * @mbg.generated
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.COLOR
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.COLOR
	 *
	 * @mbg.generated
	 */
	public String getColor() {
		return color;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.COLOR
	 *
	 * @param color
	 *            the value for TB_CRM_ANNOUNCEMENT.COLOR
	 *
	 * @mbg.generated
	 */
	public void setColor(String color) {
		this.color = color == null ? null : color.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.DEPARTMENT
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.DEPARTMENT
	 *
	 * @mbg.generated
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.DEPARTMENT
	 *
	 * @param department
	 *            the value for TB_CRM_ANNOUNCEMENT.DEPARTMENT
	 *
	 * @mbg.generated
	 */
	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.STATUS
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.STATUS
	 *
	 * @mbg.generated
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.STATUS
	 *
	 * @param status
	 *            the value for TB_CRM_ANNOUNCEMENT.STATUS
	 *
	 * @mbg.generated
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.ISSHOW
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.ISSHOW
	 *
	 * @mbg.generated
	 */
	public Short getIsshow() {
		return isshow;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.ISSHOW
	 *
	 * @param isshow
	 *            the value for TB_CRM_ANNOUNCEMENT.ISSHOW
	 *
	 * @mbg.generated
	 */
	public void setIsshow(Short isshow) {
		this.isshow = isshow;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column TB_CRM_ANNOUNCEMENT.CONTENT
	 *
	 * @return the value of TB_CRM_ANNOUNCEMENT.CONTENT
	 *
	 * @mbg.generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column TB_CRM_ANNOUNCEMENT.CONTENT
	 *
	 * @param content
	 *            the value for TB_CRM_ANNOUNCEMENT.CONTENT
	 *
	 * @mbg.generated
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "TbCrmAnnouncement [id=" + id + ", orderId=" + orderId + ", userId=" + userId + ", title=" + title
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", color=" + color + ", department="
				+ department + ", status=" + status + ", isshow=" + isshow + ", content=" + content + "]";
	}
	
}