package edu.csus.csc131.euc.json;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

//TODO: verify data types...Should we use String for siteTimeZoneId? List<Map<String,String> for reads? 
public class JSONdata {
	private String userId;
	private String unit;
	private String siteTimeZoneId;
	private List<Map<String, String>> reads;
	private String seriesComponents; 	//whats this?
	private String ratePlans;			//what data type for this?

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<Map<String, String>> getReads() {
		return reads;
	}
	public void setReads(List<Map<String, String>> reads) {
		this.reads = reads;
	}
	public String getSiteTimeZoneId() {
		return siteTimeZoneId;
	}
	public void setSiteTimeZoneId(String siteTimeZoneId) {
		this.siteTimeZoneId = siteTimeZoneId;
	}
	public String getSeriesComponents() {
		return seriesComponents;
	}
	public void setSeriesComponents(String seriesComponents) {
		this.seriesComponents = seriesComponents;
	}
	public String getRatePlans() {
		return ratePlans;
	}
	public void setRatePlans(String ratePlans) {
		this.ratePlans = ratePlans;
	}
	@Override
	public String toString() {
		return "ImportData [userId=" + userId + ", unit=" + unit + ", siteTimeZoneId=" + siteTimeZoneId + ", reads="
				+ reads + ", seriesComponents=" + seriesComponents + ", ratePlans=" + ratePlans + "]";
	}
	
}
