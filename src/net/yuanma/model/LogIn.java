package net.yuanma.model;

public class LogIn {
	private Integer userId;

	private Integer employeeId;

	private String emmPassword;

	public LogIn() {
		
	}

	public LogIn(Integer employeeId, String emmPassword) {
		this.employeeId = employeeId;
		this.emmPassword = emmPassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmmPassword() {
		return emmPassword;
	}

	public void setEmmPassword(String emmPassword) {
		this.emmPassword = emmPassword == null ? null : emmPassword.trim();
	}

	@Override
	public String toString() {
		return "LogIn [userId=" + userId + ", employeeId=" + employeeId + ", emmPassword=" + emmPassword + "]";
	}

}