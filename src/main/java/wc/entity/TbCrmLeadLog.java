package wc.entity;

public class TbCrmLeadLog {
	
	private int id;
	
	private int leadId;
	
	private String stepTime;
	
	private String step;
	
	private String updateTime;
	
	private int ownerUserId;
	
	private String ownerName;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLeadId() {
		return leadId;
	}

	public void setLeadId(int leadId) {
		this.leadId = leadId;
	}

	public String getStepTime() {
		return stepTime;
	}

	public void setStepTime(String stepTime) {
		this.stepTime = stepTime;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(int ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	@Override
	public String toString() {
		return "TbCrmLeadLog [id=" + id + ", leadId=" + leadId + ", stepTime=" + stepTime + ", step=" + step
				+ ", updateTime=" + updateTime + ", ownerUserId=" + ownerUserId + ", ownerName=" + ownerName + "]";
	}

	

}
