package wc.entity;

public class LeadsFieldVO {
	
	private int id;
	
	private String fieldName;
	
	private String fieldValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	@Override
	public String toString() {
		return "LeadsFieldVO [id=" + id + ", fieldName=" + fieldName + ", fieldValue=" + fieldValue + "]";
	}
	
	
	
	
}
