package com.stpl.gtn.gtn2o.ws.bean;

import java.util.ArrayList;
import java.util.List;

public class GtnWsExcelHeaderBean {

	public GtnWsExcelHeaderBean() {
		super();
	}

	/**
	 * The single column.
	 */
	private List<Object> singleColumns = new ArrayList<>();

	/**
	 * The single header.
	 */
	private List<String> singleHeaders = new ArrayList<>();

	/**
	 * The double column.
	 */
	private List<Object> doubleColumns = new ArrayList<>();
	/**
	 * The double header.
	 */
	private List<String> doubleHeaders = new ArrayList<>();

	private List<Integer[]> excelSplitIndexList;

	private List<String> excelSplitWorksheetName;

	private int excelLeftTableEndIndex;

	public List<Integer[]> getExcelSplitIndexList() {
		return excelSplitIndexList != null ? new ArrayList<>(excelSplitIndexList) : excelSplitIndexList;
	}

	public void setExcelSplitIndexList(List<Integer[]> excelSplitIndexList) {
	        this.excelSplitIndexList = excelSplitIndexList != null ? new ArrayList<>(excelSplitIndexList) : excelSplitIndexList;
	}

	public List<String> getExcelSplitWorksheetName() {
		return excelSplitWorksheetName != null ? new ArrayList<>(excelSplitWorksheetName) : excelSplitWorksheetName;
	}

	public void setExcelSplitWorksheetName(List<String> excelSplitWorksheetName) {
		this.excelSplitWorksheetName = excelSplitWorksheetName != null ? new ArrayList<>(excelSplitWorksheetName) : excelSplitWorksheetName;
	}

	public int getExcelLeftTableEndIndex() {
		return excelLeftTableEndIndex;
	}

	public void setExcelLeftTableEndIndex(int excelLeftTableEndIndex) {
		this.excelLeftTableEndIndex = excelLeftTableEndIndex;
	}

	/**
	 * Adds the single column.
	 *
	 * @param column
	 *            the column
	 * @param header
	 *            the header
	 */
	public void addSingleColumn(Object column, String header) {
		this.singleColumns.add(column.toString());
		this.singleHeaders.add(header);
	}

	/**
	 * Adds the double column.
	 *
	 * @param column
	 *            the column
	 * @param header
	 *            the header
	 */
	public void addDoubleColumn(Object column, String header) {
		this.doubleColumns.add(column);
		this.doubleHeaders.add(header);
	}

	public List<Object> getSingleColumns() {
		return singleColumns != null ?  new ArrayList<>(singleColumns) : singleColumns;
	}

	public void setSingleColumns(List<Object> singleColumns) {
		this.singleColumns = (singleColumns != null ?  new ArrayList<>(singleColumns) : singleColumns);
	}

	public List<String> getSingleHeaders() {
		return singleHeaders != null ? new ArrayList<>(singleHeaders) : singleHeaders;
	}

	public void setSingleHeaders(List<String> singleHeaders) {
		this.singleHeaders = singleHeaders != null ? new ArrayList<>(singleHeaders) : singleHeaders;
	}

	public List<Object> getDoubleColumns() {
		return doubleColumns != null ? new ArrayList<>(doubleColumns) : doubleColumns;
	}

	public void setDoubleColumns(List<Object> doubleColumns) {
		this.doubleColumns = (doubleColumns != null ? new ArrayList<>(doubleColumns) : doubleColumns);
	}

	public List<String> getDoubleHeaders() {
		return doubleHeaders != null ? new ArrayList<>(doubleHeaders) : doubleHeaders;
	}

	public void setDoubleHeaders(List<String> doubleHeaders) {
		this.doubleHeaders = (doubleHeaders != null ? new ArrayList<>(doubleHeaders) : doubleHeaders);
	}

}
