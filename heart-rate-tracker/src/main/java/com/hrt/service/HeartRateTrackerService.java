package com.hrt.service;

import java.util.List;

import com.hrt.bean.HeartRateTracker;

public interface HeartRateTrackerService {
	public List<HeartRateTracker> getHeartRate(int driverId, String timestamp);

	public List<HeartRateTracker> getHRForAllDrivers(String timestamp);

	public List<HeartRateTracker> abNormalVitals(int parseInt, String timestamp);
}
