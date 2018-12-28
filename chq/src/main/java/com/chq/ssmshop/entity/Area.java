package com.chq.ssmshop.entity;

import java.util.Date;

public class Area {
	// 地区id
	private Integer areaId;
	// 地区名字
	private String areaName;
	// 地区在显示时的优先级
	private Integer priority;
	// 该地区记录的创建时间
	private Date createTime;
	// 该地区最后一次编辑的时间
	private Date lastEditTime;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
