package net.yuanma.dto;

import java.util.Date;

public class BusinessDto {
	private Integer businessId;

	private Integer customerId;

	private Integer linkMainId;

	private String businessName;

	private Integer businessTypeId;

	private String status;

	private Integer businessSourceId;

	private Long persalePrice;

	private Short probability;

	private Date visitTime;

	private String visitContent;

	private Date updateTime;

	private Integer employeeId;

	private Integer creater;

	private String employeeName;

	private String name;

	private String customerName;

	private String typeName;

	private String sourceName;

	private String sex;

	private String nickname;

	private String phonenum;

	private String email;

	private String qq;

	private Date startTime;

	private Date endTime;

	private Date openTime;

	private Date overTime;

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getLinkMainId() {
		return linkMainId;
	}

	public void setLinkMainId(Integer linkMainId) {
		this.linkMainId = linkMainId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBusinessSourceId() {
		return businessSourceId;
	}

	public void setBusinessSourceId(Integer businessSourceId) {
		this.businessSourceId = businessSourceId;
	}

	public Long getPersalePrice() {
		return persalePrice;
	}

	public void setPersalePrice(Long persalePrice) {
		this.persalePrice = persalePrice;
	}

	public Short getProbability() {
		return probability;
	}

	public void setProbability(Short probability) {
		this.probability = probability;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitContent() {
		return visitContent;
	}

	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "BusinessDto [businessId=" + businessId + ", customerId=" + customerId + ", linkMainId=" + linkMainId
				+ ", businessName=" + businessName + ", businessTypeId=" + businessTypeId + ", status=" + status
				+ ", businessSourceId=" + businessSourceId + ", persalePrice=" + persalePrice + ", probability="
				+ probability + ", visitTime=" + visitTime + ", visitContent=" + visitContent + ", updateTime="
				+ updateTime + ", employeeId=" + employeeId + ", creater=" + creater + ", employeeName=" + employeeName
				+ ", name=" + name + ", customerName=" + customerName + ", typeName=" + typeName + ", sourceName="
				+ sourceName + ", sex=" + sex + ", nickname=" + nickname + ", phonenum=" + phonenum + ", email=" + email
				+ ", qq=" + qq + ", startTime=" + startTime + ", endTime=" + endTime + ", openTime=" + openTime
				+ ", overTime=" + overTime + "]";
	}

}
