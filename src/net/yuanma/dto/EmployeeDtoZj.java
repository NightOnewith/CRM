package net.yuanma.dto;

import java.util.Date;

public class EmployeeDtoZj {
	private Integer employeeId;

	private String employeeName;

	private Integer departmentId;

	private String departmentName;

	private Integer positonId;

	private String positonName;

	private String phone;

	private String email;

	private String status;

	private Integer parentEmployeeId;

	private Date createTime;

	private Date updateTime;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositonName() {
		return positonName;
	}

	public void setPositonName(String positonName) {
		this.positonName = positonName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getParentEmployeeId() {
		return parentEmployeeId;
	}

	public void setParentEmployeeId(Integer parentEmployeeId) {
		this.parentEmployeeId = parentEmployeeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getPositonId() {
		return positonId;
	}

	public void setPositonId(Integer positonId) {
		this.positonId = positonId;
	}

	@Override
	public String toString() {
		return "EmployeeDto [employeeId=" + employeeId + ", employeeName=" + employeeName + ", departmentId="
				+ departmentId + ", departmentName=" + departmentName + ", positonId=" + positonId + ", positonName="
				+ positonName + ", phone=" + phone + ", email=" + email + ", status=" + status + ", parentEmployeeId="
				+ parentEmployeeId + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
