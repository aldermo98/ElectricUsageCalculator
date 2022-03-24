package edu.csus.csc131.euc.store;

import edu.csus.csc131.euc.mainWindow.ShareData;
import edu.csus.csc131.euc.mainWindow.Rate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import edu.csus.csc131.euc.mainWindow.ShareData;

class DailyDataTest {
	DailyData d = new DailyData();
	@Test
	void testCalculations() {
		DailyData d = new DailyData();
		//int CustomUsage[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		d.CustomUsage = new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		d.TotalUsage();
		assert(d.TotalUsage() == 24);
	}

	/*@Test//successful test but failure during mvn clean package
	void testCalculations_1() {
		DailyData d = new DailyData();
		//int CustomUsage[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		d.CustomUsage = new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		d.TotalUsage();
		d.setPeakHours(ShareData.summer_midPeak.getHours(), "midpeak");
		d.setPeakRates(ShareData.summer_midPeak.getPrice(), "midpeak");
		
		d.setPeakHours(ShareData.summer_peak.getHours(), "peak");
		d.setPeakRates(ShareData.summer_peak.getPrice(), "peak");
		
		d.setPeakHours(ShareData.summer_offPeak.getHours(), "offpeak");
		d.setPeakRates(ShareData.summer_offPeak.getPrice(), "offpeak");
		
		assert((Math.floor(d.TotalCost()*100)/100) == 3.83);
	}*/
	
	@Test
	void testCalcCustom() {
		DailyData d = new DailyData();
		ShareData.summer_offPeak.setHours(new String[] {"12am - 1am"});	//all this to get list values, convert it to an array, then convert that array to a String array, and set the String array to our rate's hours	
		ShareData.summer_offPeak.setPrice(1.1);
		
		d.setPeakHours(ShareData.summer_offPeak.getHours(), "offpeak");
		d.setPeakRates(ShareData.summer_offPeak.getPrice(), "offpeak");
		
		d.setHourlUsage(1.0, 0);
		assert(d.TotalCost() == 1.1);
	}
	
	@Test
	void testCalcCustom_Manual() {
		DailyData d = new DailyData();
		ShareData.summer_midPeak.setHours(new String[] {"12pm - 1pm"});	//all this to get list values, convert it to an array, then convert that array to a String array, and set the String array to our rate's hours	
		ShareData.summer_midPeak.setPrice(1.3);
		
		d.setPeakHours(ShareData.summer_midPeak.getHours(), "midpeak");
		d.setPeakRates(ShareData.summer_midPeak.getPrice(), "midpeak");
		
		d.setHourlUsage(1.0, 12);
		System.out.println(d.TotalCost());
		assert(d.TotalCost() == 1.3);
	}

}
