package net.yuanma.dto;

import java.util.Date;

public class AdvanceLogDto {
	 private Integer logId;

	    private Integer businessId;

	    private Integer creator;

	    private String advanceContent;

	    private Date createTime;

	    private String advanceStatus;
	    
	    private Integer employeeId;

	    private String employeeName;

		public Integer getLogId() {
			return logId;
		}

		public void setLogId(Integer logId) {
			this.logId = logId;
		}

		public Integer getBusinessId() {
			return businessId;
		}

		public void setBusinessId(Integer businessId) {
			this.businessId = businessId;
		}

		public Integer getCreator() {
			return creator;
		}

		public void setCreator(Integer creator) {
			this.creator = creator;
		}

		public String getAdvanceContent() {
			return advanceContent;
		}

		public void setAdvanceContent(String advanceContent) {
			this.advanceContent = advanceContent;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getAdvanceStatus() {
			return advanceStatus;
		}

		public void setAdvanceStatus(String advanceStatus) {
			this.advanceStatus = advanceStatus;
		}

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

		@Override
		public String toString() {
			return "AdvanceLogDto [logId=" + logId + ", businessId="
					+ businessId + ", creator=" + creator + ", advanceContent="
					+ advanceContent + ", createTime=" + createTime
					+ ", advanceStatus=" + advanceStatus + ", employeeId="
					+ employeeId + ", employeeName=" + employeeName + "]";
		}
	    
}
