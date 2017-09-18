package net.yuanma.dto;

import java.util.Date;

public class CustomerDto {
	  private Integer customerId;

	    private String customerName;

	    private String postCode;

	    private Integer fieldId;

	    private Integer sourceId;

	    private Date createTime;
	    
	    private Date startTime;
	    
	    private Date endTime;

	  

		private Date updateTime;

	    private String employeeNumbers;

	    private Integer prinpical;

	    private String address;

	    private String tag;

	    private String remarks;

	    private String busubessVolume;
	    
	    private Integer fieldIds;

	    private String fieldName;
	    private Integer sourceIds;

	    private String sourceName;
	    
	    private String employeeName;
	    

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
		
		public String getCustomerName() {
			return customerName;
		}

		public Integer getCustomerId() {
			return customerId;
		}

		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		public Integer getFieldId() {
			return fieldId;
		}

		public void setFieldId(Integer fieldId) {
			this.fieldId = fieldId;
		}

		public Integer getSourceId() {
			return sourceId;
		}

		public void setSourceId(Integer sourceId) {
			this.sourceId = sourceId;
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

		public String getEmployeeNumbers() {
			return employeeNumbers;
		}

		public void setEmployeeNumbers(String employeeNumbers) {
			this.employeeNumbers = employeeNumbers;
		}

		public Integer getPrinpical() {
			return prinpical;
		}

		public void setPrinpical(Integer prinpical) {
			this.prinpical = prinpical;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public String getBusubessVolume() {
			return busubessVolume;
		}

		public void setBusubessVolume(String busubessVolume) {
			this.busubessVolume = busubessVolume;
		}

		public Integer getFieldIds() {
			return fieldIds;
		}

		public void setFieldIds(Integer fieldIds) {
			this.fieldIds = fieldIds;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public Integer getSourceIds() {
			return sourceIds;
		}

		public void setSourceIds(Integer sourceIds) {
			this.sourceIds = sourceIds;
		}

		public String getSourceName() {
			return sourceName;
		}

		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}

		public String getEmployeeName() {
			return employeeName;
		}

		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}

		@Override
		public String toString() {
			return "CustomerDto [customerId=" + customerId + ", customerName="
					+ customerName + ", postCode=" + postCode + ", fieldId="
					+ fieldId + ", sourceId=" + sourceId + ", createTime="
					+ createTime + ", startTime=" + startTime + ", endTime="
					+ endTime + ", updateTime=" + updateTime
					+ ", employeeNumbers=" + employeeNumbers + ", prinpical="
					+ prinpical + ", address=" + address + ", tag=" + tag
					+ ", remarks=" + remarks + ", busubessVolume="
					+ busubessVolume + ", fieldIds=" + fieldIds
					+ ", fieldName=" + fieldName + ", sourceIds=" + sourceIds
					+ ", sourceName=" + sourceName + ", employeeName="
					+ employeeName + ", getStartTime()=" + getStartTime()
					+ ", getEndTime()=" + getEndTime() + ", getCustomerName()="
					+ getCustomerName() + ", getCustomerId()="
					+ getCustomerId() + ", getPostCode()=" + getPostCode()
					+ ", getFieldId()=" + getFieldId() + ", getSourceId()="
					+ getSourceId() + ", getCreateTime()=" + getCreateTime()
					+ ", getUpdateTime()=" + getUpdateTime()
					+ ", getEmployeeNumbers()=" + getEmployeeNumbers()
					+ ", getPrinpical()=" + getPrinpical() + ", getAddress()="
					+ getAddress() + ", getTag()=" + getTag()
					+ ", getRemarks()=" + getRemarks()
					+ ", getBusubessVolume()=" + getBusubessVolume()
					+ ", getFieldIds()=" + getFieldIds() + ", getFieldName()="
					+ getFieldName() + ", getSourceIds()=" + getSourceIds()
					+ ", getSourceName()=" + getSourceName()
					+ ", getEmployeeName()=" + getEmployeeName()
					+ ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}

		

		
}
