package edu.csus.csc131.euc.mainWindow;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.csus.csc131.euc.store.DailyData;
import edu.csus.csc131.euc.store.UsageInfo;

public class ShareData {
	
// Variable for daily Information processing
	// keep track of Current Rates.
	static public List<DailyData> dailyDataStore = new ArrayList<>();
	static public DailyData dailyData = new DailyData();
	static public LocalDate theDate;
	
//Variables for rate plans/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*To use rate plan hours or price in your file:
	 *   ShareData obj = new ShareData();
	 *   String[] x = ShareData.summer_peak.getHours();	//note if the hours have not been set for the summer peak rate, then default values will be used located in Rate.java
	 *   double y = ShareData.summer_peak.getPrice();	//note if the price has not been set for the summer peak rate, then default values will be used located in Rate.java
	 * */
	static public Rate summer_peak = new Rate(new String[]{ "5pm - 6pm", "6pm - 7pm", "7pm - 8pm" }, 0.2941);		//5pm - 8pm
	
	static public Rate summer_midPeak = new Rate(new String[] { "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm",		//noon - 5pm, 8pm - midnight
														 "8pm - 9pm", "9pm - 10pm", "10pm - 11pm", "11pm - 12am" }, 0.1671);	
	
	static public Rate summer_offPeak = new Rate(new String[]{ "12am - 1am", "1am - 2am", "2am - 3am", "3am - 4am",	//midnight-noon
														"4am - 5am", "5am - 6am", "6am - 7am", "7am - 8am",
														"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm"}, 0.1209);	
	
	static public Rate nonSummer_peak = new Rate(new String[]{ "5pm - 6pm", "6pm - 7pm", "7pm - 8pm" }, 0.1388);	//5pm - 8pm
	
	static public Rate nonSummer_midPeak = new Rate(new String[]{ "" }, 0.0);	
	
	static public Rate nonSummer_offPeak = new Rate(new String[]{ "12am - 1am", "1am - 2am", "2am - 3am", "3am - 4am",	//midnight - 5pm, 8pm - midnight
														   "4am - 5am", "5am - 6am", "6am - 7am", "7am - 8am",
														   "8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm", 
														   "12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm", "4pm - 5pm",
														   "8pm - 9pm", "9pm - 10pm", "10pm - 11pm", "11pm - 12am"}, 0.1006);
	
//List values from MainWindow.java///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*To use rate plan hours or price in your file:
	 *   ShareData obj = new ShareData();
	 *   String[] x = obj.periods;	//note if the hours have not been set for the summer peak rate, then default values will be used located in Rate.java
	 *   String[] y = obj.times;	//note if the price has not been set for the summer peak rate, then default values will be used located in Rate.java
	 * */
	private static String[] periods = new String[] { "Peak", "Mid-peak", "Off-peak" };
	
	private static String[] times = new String[] { "12am - 1am", "1am - 2am", "2am - 3am", "3am - 4am",	//midnight-noon in hour increments
									"4am - 5am", "5am - 6am", "6am - 7am", "7am - 8am",
									"8am - 9am", "9am - 10am", "10am - 11am", "11am - 12pm",
									
									"12pm - 1pm", "1pm - 2pm", "2pm - 3pm", "3pm - 4pm",	//noon-midnight in hour increments
									"4pm - 5pm", "5pm - 6pm", "6pm - 7pm", "7pm - 8pm",
									"8pm - 9pm", "9pm - 10pm", "10pm - 11pm", "11pm - 12am" };
	
//File choses for JSON input//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	static File file;

	/**
	 * @return the periods
	 */
	public static String[] getPeriods() {
		return periods;
	}
	
	/**
	 * @return the times
	 */
	public static String[] getTimes() {
		return times;
	}

	/**
	 * @param times the times to set
	 */
	public void setTimes(String[] times) {
		this.times = times;
	}
}
