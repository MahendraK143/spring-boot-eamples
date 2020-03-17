package com.hrt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hrt.bean.HeartRateTracker;

@Service
public class HeartRateTrackerServiceImpl implements HeartRateTrackerService{
	public static List<HeartRateTracker> getHeartRateData(Integer driverId, Integer interval, String timestamp) {
		List<HeartRateTracker> heartRateTrackers = new ArrayList<HeartRateTracker>();
		for (int i=1; i<=5; i++) {
			heartRateTrackers.add(new HeartRateTracker(driverId, getRandomNumberInRange(80, 120), (Long.parseLong(timestamp))+(1000*i)*interval+""));
			heartRateTrackers.add(new HeartRateTracker(driverId, getRandomNumberInRange(120, 160), (Long.parseLong(timestamp))+(1000*i)*interval+""));
		}
		
		return heartRateTrackers;
	}
	public static List<HeartRateTracker> getHeartRateData(Integer interval, String timestamp) {
		List<HeartRateTracker> heartRateTrackers = new ArrayList<HeartRateTracker>();
		for (int driverId = 1; driverId < 10; driverId++) {
			for (int i=1; i<=5; i++) {
				heartRateTrackers.add(new HeartRateTracker(driverId, getRandomNumberInRange(80, 120), (Long.parseLong(timestamp))+(1000*i)*interval+""));
				heartRateTrackers.add(new HeartRateTracker(driverId, getRandomNumberInRange(120, 160), (Long.parseLong(timestamp))+(1000*i)*interval+""));
			}
		}
		
		return heartRateTrackers;
	}
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	@Override
	public List<HeartRateTracker> getHeartRate(int driverId, String timestamp){
		return getHeartRateData(driverId,1,timestamp);
	}
	@Override
	public List<HeartRateTracker> getHRForAllDrivers(String timestamp) {
		return getHeartRateData(1,timestamp);
	}
	@Override
	public List<HeartRateTracker> abNormalVitals(int driverId, String timestamp) {
		return getHeartRateData(driverId,1,timestamp).stream().filter(rate -> (rate.getHeartRate() < 80 || rate.getHeartRate() > 120)).collect(Collectors.toList());
	}

}
