package com.hrt.bean;

public class HeartRateTracker {
	private int id;
	private int heartRate;
	private String time;
	
	public HeartRateTracker(int id, int heartRate, String time) {
		super();
		this.id = id;
		this.heartRate = heartRate;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "HeartRateTracker [id=" + id + ", heartRate=" + heartRate + ", time=" + time + "]";
	}

	

}
