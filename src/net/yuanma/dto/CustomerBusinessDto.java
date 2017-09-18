package net.yuanma.dto;

import java.util.Date;

public class CustomerBusinessDto {
	private String ly;

	private String nameQc;

	private String nameer;

	private String sourceName;

	private Date updateTime;

	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	public String getNameQc() {
		return nameQc;
	}

	public void setNameQc(String nameQc) {
		this.nameQc = nameQc;
	}

	public String getNameer() {
		return nameer;
	}

	public void setNameer(String nameer) {
		this.nameer = nameer;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CustomerBusinessDto [ly=" + ly + ", nameQc=" + nameQc + ", nameer=" + nameer + ", sourceName="
				+ sourceName + ", updateTime=" + updateTime + "]";
	}

}
