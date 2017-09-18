package net.yuanma.dto;

public class EmployeeDto {
	private String employeeName;
	private int employeeId;
	private String phone;
	private String postionName;
	private int postionId;

	
public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public int getPostionId() {
	return postionId;
}
public void setPostionId(int postionId) {
	this.postionId = postionId;
}
public String getPostionName() {
	return postionName;
}
public void setPostionName(String postionName) {
	this.postionName = postionName;
}
@Override
public String toString() {
	return "EmployeeDto [employeeName=" + employeeName + ", employeeId="
			+ employeeId + ", phone=" + phone + ", postionName=" + postionName
			+ ", postionId=" + postionId + "]";
}


}
