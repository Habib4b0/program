/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.bean;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsRecordTypeBean {

	public GtnWsRecordTypeBean() {
		super();
	}

	private boolean current;
	private boolean history;
	private boolean future;
	private boolean pending;
	private String startDateColumn;
	private String endDateColumn;

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public boolean isHistory() {
		return history;
	}

	public void setHistory(boolean history) {
		this.history = history;
	}

	public boolean isFuture() {
		return future;
	}

	public void setFuture(boolean future) {
		this.future = future;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	public String getStartDateColumn() {
		return startDateColumn;
	}

	public void setStartDateColumn(String startDateColumn) {
		this.startDateColumn = startDateColumn;
	}

	public String getEndDateColumn() {
		return endDateColumn;
	}

	public void setEndDateColumn(String endDateColumn) {
		this.endDateColumn = endDateColumn;
	}

	@Override
	public String toString() {
		return "GtnWsRecordTypeBean{" + "current=" + current + ", history=" + history + ", future=" + future
				+ ", pending=" + pending + ", startDateColumn=" + startDateColumn + ", endDateColumn=" + endDateColumn
				+ '}';
	}

}
