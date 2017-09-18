package net.yuanma.model;

import java.util.Date;

public class Task {
	private String topic;

	private String status;

	private Date endTime;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Task [topic=" + topic + ", status=" + status + ", endTime=" + endTime + "]";
	}

}
