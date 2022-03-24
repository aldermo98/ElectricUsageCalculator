/**
 * 
 */
package edu.csus.csc131.euc.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import edu.csus.csc131.euc.mainWindow.ShareData;

/**
 * @author Avenir Fleyshgauer
 *
 */
public class DailyData {

	   public Integer CustomHours[];
	   public Double CustomRates[];
	   public Double CustomUsage[];
	   public LocalDate dailyDate;
	   
	   // SetDefaultRates
	   public DailyData()
	   {
	      SetDefaultNumbers();
	   }
	   
	   public void SetDefaultNumbers()
	   {
	      // specify the type of hours they are.0 = off peak, 1 = mid peak, 2 = peak
	      CustomHours = new Integer[] {null,null,null,null,null,null,null, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
	      CustomRates = new Double[] {null,null,null};
	      CustomUsage = new Double[] {null,null,null,null,null,null,null, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
	   }
	   
	   // Input is an array of hours, 12am - 1am = 0; (use 0-24) and the peak, such as "peak", or "midpeak", default is "offpeak"
	   public void setPeakHours(String[] arrHours, String peak)    {
	      int type;
	      if (peak.toLowerCase() == "peak") {
	         type = 2;
	      }
	      else if (peak.toLowerCase() == "midpeak") {
	         type = 1;
	      }
	      else {
	         type = 0;
	      }
	      
	      // convert string hours to indices
	      ArrayList<String> timeslist = new ArrayList<>(Arrays.asList(ShareData.getTimes()));
	      for(int i=0; i<arrHours.length;i++) {
	    	  if(timeslist.indexOf(arrHours[i]) != -1) {
				CustomHours[timeslist.indexOf(arrHours[i])] = type;
	    	  }
	      }
	            
	   }
	   
	   public void setPeakRates(Double Rate, String peak)    {
		   int type;
	      if (peak.toLowerCase() == "peak") {
	         type = 2;
	      }
	      else if (peak.toLowerCase() == "midpeak") {
	         type = 1;
	      }
	      else {
	         type = 0;
	      }
	      
	      CustomRates[type] = Rate;
	   }
	   
	   // Utilize 1-24 to set hours
	   public void setHourlUsage(Double Usage, Integer hour)    {
		   CustomUsage[hour] = Usage;
	   }
	   
	   // get daily usage based on strings, based on a 24hr clock
	   public Double getHourlyUsage(Integer hour)    {
		   return CustomUsage[hour];
	   }
	   
	   public double TotalCost()
	   {
	      double dtotal = 0.0;
	      for(int i = 0; i < 24; i++) // loop through the 24 hours to specify usage and rate based on hours
	      {
	         if(CustomHours[i] != null && CustomUsage[i] != null)
	         {
	            dtotal += CustomUsage[i]*CustomRates[CustomHours[i]];
	         }
	      }
	      return dtotal;
	   }
	   
	   public double TotalUsage()
	   {
	      double usageTotal = 0.0;
	      for(int i =0; i<24; i++)
	      {
	         if(CustomUsage[i] != null)
	         {
	             usageTotal += CustomUsage[i];
	         }
	      }
	      return usageTotal;
	   }
	   
	   public void printTotals()
	   {
	      System.out.print("\nTotal Usage Today: " + TotalUsage()+" kWh");
	      System.out.print("\nTotal Cost Today: $" + TotalCost());
	   }
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// A test of the class.
		DailyData dt = new DailyData();
	    dt.printTotals();
	    
	    // Another Possible Test Case:
	    Integer [] hours = {0,1,2,3,4};
	    dt.setPeakHours(hours, "offpeak");
	    dt.setPeakRates(4.0, "offpeak"); // 4 dollar per kW
	    dt.setHourlUsage(3.4, 0); // set hour 1  to 3.4kW
	    dt.printTotals();
	    
	    // Tested working so far.
	    // Pseudo code for class integration
	    
	       //test of updating every hour for setting peak rates
	     * Integer hours[];
	     * int z = 0;
	     * while(int i = 0; i < 24; i++)
	     * {
	     * if(hourSelected[i] == true) {
	     * 	hours[z] = i;
	     * 	z++;
	     * }
	     * }
	     * 
	     * DailyData dt = new DailyData();
	     * dt.setPeakRates(4.0, "peak");
	     * 
	     * 
	     * 
	     * 
	     * 
	     * 
	}*/

}
