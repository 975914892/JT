package wc.entity;

public class PositionVO {
	private int id;
	//��λ
	private String pname;
	//�����ϼ���λ
	private String p2name;
	//��������
	private String dname;
	//����
	private String description;
	@Override
	public String toString() {
		return "PositionVO [id=" + id + ", pname=" + pname + ", p2name=" + p2name + ", dname=" + dname
				+ ", description=" + description + "]";
	}
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
	public String getP2name() {
		return p2name;
	}
	public void setP2name(String p2name) {
		this.p2name = p2name;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
