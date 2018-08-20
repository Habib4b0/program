package com.stpl.gtn.gtn2o.ws.processscheduler.bean;

public class GtnCffOutBoundBean {

	public GtnCffOutBoundBean() {
		super();
	}

	private int rsModelSid;
	private int periodSid;
	private int cffDetailsSid;
	private boolean checkedRecord;

	public int getRsModelSid() {
		return rsModelSid;
	}

	public void setRsModelSid(int rsModelSid) {
		this.rsModelSid = rsModelSid;
	}

	public int getPeriodSid() {
		return periodSid;
	}

	public void setPeriodSid(int periodSid) {
		this.periodSid = periodSid;
	}

	public int getCffDetailsSid() {
		return cffDetailsSid;
	}

	public void setCffDetailsSid(int cffDetailsSid) {
		this.cffDetailsSid = cffDetailsSid;
	}

	public boolean isCheckedRecord() {
		return checkedRecord;
	}

	public void setCheckedRecord(boolean checkRecord) {
		this.checkedRecord = checkRecord;
	}

}
