/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.jmx;

/**
 *
 * @author Nadhiya.Jayaram
 */
public class GtnWebServiceJmxMetrics {

	private long totalTime = 0L;
	private int count = 0;
	private long lastExecutionTime = 0L;
	private long queryTotalTime = 0L;
	private long queryCount = 0L;
	private long queryLastExecutionTime = 0L;

	public long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

	public int getCount() {
		return count;
	}

	public long getQueryTotalTime() {
		return queryTotalTime;
	}

	public void setQueryTotalTime(long queryTotalTime) {
		this.queryTotalTime = queryTotalTime;
	}

	public long getQueryCount() {
		return queryCount;
	}

	public void setQueryCount(long queryCount) {
		this.queryCount = queryCount;
	}

	public long getQueryLastExecutionTime() {
		return queryLastExecutionTime;
	}

	public void setQueryLastExecutionTime(long queryLastExecutionTime) {
		this.queryLastExecutionTime = queryLastExecutionTime;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getLastExecutionTime() {
		return lastExecutionTime;
	}

	public void setLastExecutionTime(long lastExecutionTime) {
		this.lastExecutionTime = lastExecutionTime;
	}

}
