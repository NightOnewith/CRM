package net.yuanma.dto;

import java.awt.Menu;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MenuDto implements Serializable{
	private Integer menuId;

	private String menuName;

	private String menuUrl;

	private String pictures;

	private String status;

	private Date createTime;

	private Date updateTime;

	private Integer parentMenuId;

	private String parentMenuName;

	private List<Menu> sonMenus;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public List<Menu> getSonMenus() {
		return sonMenus;
	}

	public void setSonMenus(List<Menu> sonMenus) {
		this.sonMenus = sonMenus;
	}

	@Override
	public String toString() {
		return "MenuDto [menuId=" + menuId + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", pictures="
				+ pictures + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", parentMenuId=" + parentMenuId + ", parentMenuName=" + parentMenuName + ", sonMenus=" + sonMenus
				+ "]";
	}

}
