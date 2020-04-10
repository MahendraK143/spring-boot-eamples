package com.hrt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrt.bean.HeartRateTracker;
import com.hrt.service.HeartRateTrackerService;

@RestController
public class HeartRateTrackerController {
	@Autowired(required = true)
	private HeartRateTrackerService heartRateTrackerService;
	
	@RequestMapping(value = "/api/heartrate/{driverId}/{currentTime}",method = RequestMethod.GET)
	public List<HeartRateTracker> heartRate(@PathVariable String driverId, @PathVariable String currentTime){
		return heartRateTrackerService.getHeartRate(Integer.parseInt(driverId), currentTime);
	}
	@RequestMapping(value = "/api/heartrateFromAll/{currentTime}",method = RequestMethod.GET)
	public List<HeartRateTracker> getHRForAllDrivers(@PathVariable String currentTime){
		return heartRateTrackerService.getHRForAllDrivers(currentTime);
	}
	@RequestMapping(value = "/api/abNormalVitals/{driverId}/{currentTime}",method = RequestMethod.GET)
	public List<HeartRateTracker> normalVitals(@PathVariable String driverId, @PathVariable String currentTime){
		return heartRateTrackerService.abNormalVitals(Integer.parseInt(driverId),currentTime);
	}
	@RequestMapping(value = "/api/heartrate",method = RequestMethod.GET)
	public String heartRate1(){
		return "working";
	}
	
}
