package com.stpl.app.adminconsole.quartz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzUtil.class);

    private QuartzUtil()
    {
       LOGGER.debug("Entering QuartzUtil method "); 
    }

	public static List<String> calculateCronStringForInterval(int interval) {
		int minuteInADay = 0;
		Map<Integer, List<Integer>> hourlyMap = new HashMap<>();
		Map<Integer, List<Integer>> minutelyMap = new HashMap<>();
		while (minuteInADay <= 24*60) {
			minuteInADay = minuteInADay + interval;

			if (minuteInADay <= 24*60) {
				addToHourlyMap(hourlyMap, minuteInADay);
				addToMinutelyMap(minutelyMap, minuteInADay);
			}
		}

		
		List<String> cronStringList = new ArrayList<>();
		convertMapToCronList(minutelyMap, cronStringList);
		return cronStringList;

	}

	private static void convertMapToCronList(Map<Integer, List<Integer>> minutelyMap, List<String> cronStringList) {

		for (int i = 0; i < 60; i++) {
			List<Integer> hourList = minutelyMap.get(i);
			if (hourList != null) {
				String cronhour = concateHours(hourList);
				if (cronhour != null) {
					cronStringList.add("0 " + i + " " + cronhour + " * * ? *");
				}
			}
		}

	}

	

	public static void addToHourlyMap(Map<Integer, List<Integer>> hourMap, int minuteInADay ) {
		
		int hour = getHour(minuteInADay);
		int minute = minuteInADay - ( hour*60);
		List<Integer> minuteList = hourMap.get(hour % 24);
		if (minuteList == null) {
			minuteList = new ArrayList<>();
			hourMap.put(hour, minuteList);

		}
		minuteList.add(minute);

	}

	public static void addToMinutelyMap(Map<Integer, List<Integer>> minutelyMap, int minuteInADay) {

		int hour = getHour(minuteInADay);
		int minute = minuteInADay - ( hour*60);		
		List<Integer> hourList = minutelyMap.get(minute);
		if (hourList == null) {
			hourList = new ArrayList<>();
			minutelyMap.put(minute, hourList);

		}
		hourList.add(hour % 24);

	}

	public static int getHour ( int minuteInADay ){
		for ( int i=0 ; i<=24;i++){
			if ( minuteInADay < i*60 ){
				return i-1;
			}
		}
		return 24;
	}
	
	public static String concateHours(List<Integer> hourlist) {
		if (hourlist == null) {
			return null;
		}

		if (hourlist.isEmpty()) {
			return null;
		}
		Collections.sort(hourlist);
		StringBuilder output = new StringBuilder();
                output.append(hourlist.get(0) );
		if (hourlist.size() == 1) {
			return output.toString();
		}
		for (int i = 1; i < hourlist.size(); i++) {
			output.append(',' ).append( hourlist.get(i));
		}
		return output.toString();
	}	
}
