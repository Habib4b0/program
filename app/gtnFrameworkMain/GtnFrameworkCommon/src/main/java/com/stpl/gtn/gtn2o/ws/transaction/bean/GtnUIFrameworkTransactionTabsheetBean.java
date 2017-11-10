package com.stpl.gtn.gtn2o.ws.transaction.bean;

public class GtnUIFrameworkTransactionTabsheetBean {

	public GtnUIFrameworkTransactionTabsheetBean() {
		super();
	}

	private int noOfTabs;
	private String[] tabNameArray;
	private int[] noOfElementsInTabArray;
	private int[] noOfInvalidElementsInTabArray;

	public int getNoOfTabs() {
		return noOfTabs;
	}

	public void setNoOfTabs(int noOfTabs) {
		this.noOfTabs = noOfTabs;
	}

	public String[] getTabNameArray() {
		return tabNameArray != null ?  tabNameArray.clone() : tabNameArray;
	}

	public void setTabNameArray(String[] tabNameArray) {
		this.tabNameArray = tabNameArray != null ?  tabNameArray.clone() : tabNameArray;
	}

	public int[] getNoOfElementsInTabArray() {
		return noOfElementsInTabArray!= null ? noOfElementsInTabArray.clone() : noOfElementsInTabArray;
	}

	public void setNoOfElementsInTabArray(int[] noOfElementsInTabArray) {
		this.noOfElementsInTabArray = noOfElementsInTabArray!= null ?  noOfElementsInTabArray.clone() : noOfElementsInTabArray;
	}

	public int[] getNoOfInvalidElementsInTabArray() {
		return noOfInvalidElementsInTabArray!= null ?  noOfInvalidElementsInTabArray.clone() : noOfInvalidElementsInTabArray;
	}

	public void setNoOfInvalidElementsInTabArray(int[] noOfInvalidElementsInTabArray) {
		this.noOfInvalidElementsInTabArray = noOfInvalidElementsInTabArray!= null ?  noOfInvalidElementsInTabArray.clone() : noOfInvalidElementsInTabArray;
	}

}
