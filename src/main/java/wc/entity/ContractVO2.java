package wc.entity;

public class ContractVO2 {
	public int tcbsId;
	public String tcbsName;
	public String tccrName;
	public String tccsName;
	public int getTcbsId() {
		return tcbsId;
	}
	public void setTcbsId(int tcbsId) {
		this.tcbsId = tcbsId;
	}
	public String getTcbsName() {
		return tcbsName;
	}
	public void setTcbsName(String tcbsName) {
		this.tcbsName = tcbsName;
	}
	public String getTccrName() {
		return tccrName;
	}
	public void setTccrName(String tccrName) {
		this.tccrName = tccrName;
	}
	public String getTccsName() {
		return tccsName;
	}
	public void setTccsName(String tccsName) {
		this.tccsName = tccsName;
	}
	@Override
	public String toString() {
		return "ContractVO2 [tcbsId=" + tcbsId + ", tcbsName=" + tcbsName + ", tccrName=" + tccrName + ", tccsName="
				+ tccsName + "]";
	}
	
}
