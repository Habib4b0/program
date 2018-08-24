package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkV8DualListBoxConfig {

	private String[] leftVisibleHeaders;
	private Object[] leftVisibleColumns;
	private String[] rightVisibleHeaders;
	private Object[] rightVisibleColumns;
	private List<String> recordHeader;
	private List<String> rightRecordHeader;
	private String leftTableURL;
	private String moveRightURL;
	private String moveLeftURL;
	private String moveAllDataURL;
	private int loadingLevel;
	private String moduleName;
	private String fileName;
	private String moduleType;

	public GtnUIFrameworkV8DualListBoxConfig() {
		super();
	}

	public int getLoadingLevel() {
		return loadingLevel;
	}

	public String[] getLeftVisibleHeaders() {
		return leftVisibleHeaders == null ? leftVisibleHeaders : leftVisibleHeaders.clone();
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setLoadingLevel(int loadingLevel) {
		this.loadingLevel = loadingLevel;
	}

	public void setLeftVisibleHeaders(String[] leftVisibleHeaders) {
		this.leftVisibleHeaders = leftVisibleHeaders.clone();
	}

	public String getModuleName() {
		return moduleName;
	}

	public Object[] getLeftVisibleColumns() {
		return leftVisibleColumns == null ? leftVisibleColumns : leftVisibleColumns.clone();
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setLeftVisibleColumns(Object[] leftVisibleColumns) {
		this.leftVisibleColumns = leftVisibleColumns.clone();
	}

	public String getLeftTableURL() {
		return leftTableURL;
	}

	public String[] getRightVisibleHeaders() {
		return rightVisibleHeaders == null ? rightVisibleHeaders : rightVisibleHeaders.clone();
	}

	public String getMoveAllDataURL() {
		return moveAllDataURL;
	}

	public void setMoveAllDataURL(String moveAllDataURL) {
		this.moveAllDataURL = moveAllDataURL;
	}

	public void setLeftTableURL(String leftTableURL) {
		this.leftTableURL = leftTableURL;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setRightVisibleHeaders(String[] rightVisibleHeaders) {
		this.rightVisibleHeaders = rightVisibleHeaders.clone();
	}

	public String getMoveRightURL() {
		return moveRightURL;
	}

	public String getFileName() {
		return fileName;
	}

	public Object[] getRightVisibleColumns() {
		return rightVisibleColumns == null ? rightVisibleColumns : rightVisibleColumns.clone();
	}

	public void setMoveRightURL(String moveRightURL) {
		this.moveRightURL = moveRightURL;
	}

	public String getMoveLeftURL() {
		return moveLeftURL;
	}

	public void setRightVisibleColumns(Object[] rightVisibleColumns) {
		this.rightVisibleColumns = rightVisibleColumns.clone();
	}

	public void setMoveLeftURL(String moveLeftURL) {
		this.moveLeftURL = moveLeftURL;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public List<String> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<String> recordHeader) {
		this.recordHeader = new ArrayList<>(recordHeader);
	}

	public List<String> getRightRecordHeader() {
		return rightRecordHeader;
	}

	public void setRightRecordHeader(List<String> rightRecordHeader) {
		this.rightRecordHeader = rightRecordHeader;
	}

}