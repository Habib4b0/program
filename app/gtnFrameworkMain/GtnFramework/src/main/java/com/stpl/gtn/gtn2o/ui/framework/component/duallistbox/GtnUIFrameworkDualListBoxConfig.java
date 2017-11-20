
package com.stpl.gtn.gtn2o.ui.framework.component.duallistbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkDualListBoxConfig {

	private String[] leftVisibleHeaders;
	private Object[] leftVisibleColumns;
	private String[] rightVisibleHeaders;
	private Object[] rightVisibleColumns;

	private List<String> recordHeader;

	private String leftTableURL;
	private String moveRightURL;
	private String moveLeftURL;

	private int loadingLevel;
	private String moduleName;
	private String fileName;

	private String moduleType;

	public GtnUIFrameworkDualListBoxConfig() {
		super();
	}

	public String[] getLeftVisibleHeaders() {
		return leftVisibleHeaders == null ? leftVisibleHeaders : leftVisibleHeaders.clone();
	}

	public void setLeftVisibleHeaders(String[] leftVisibleHeaders) {
		this.leftVisibleHeaders = leftVisibleHeaders.clone();
	}

	public Object[] getLeftVisibleColumns() {
		return leftVisibleColumns == null ? leftVisibleColumns : leftVisibleColumns.clone();
	}

	public void setLeftVisibleColumns(Object[] leftVisibleColumns) {
		this.leftVisibleColumns = leftVisibleColumns.clone();
	}

	public String[] getRightVisibleHeaders() {
		return rightVisibleHeaders == null ? rightVisibleHeaders : rightVisibleHeaders.clone();
	}

	public void setRightVisibleHeaders(String[] rightVisibleHeaders) {
		this.rightVisibleHeaders = rightVisibleHeaders.clone();
	}

	public Object[] getRightVisibleColumns() {
		return rightVisibleColumns == null ? rightVisibleHeaders : rightVisibleHeaders.clone();
	}

	public void setRightVisibleColumns(Object[] rightVisibleColumns) {
		this.rightVisibleColumns = rightVisibleColumns.clone();
	}

	public List<String> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<String> recordHeader) {
		this.recordHeader = new ArrayList<>(recordHeader);
	}

	public int getLoadingLevel() {
		return loadingLevel;
	}

	public void setLoadingLevel(int loadingLevel) {
		this.loadingLevel = loadingLevel;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getLeftTableURL() {
		return leftTableURL;
	}

	public void setLeftTableURL(String leftTableURL) {
		this.leftTableURL = leftTableURL;
	}

	public String getMoveRightURL() {
		return moveRightURL;
	}

	public void setMoveRightURL(String moveRightURL) {
		this.moveRightURL = moveRightURL;
	}

	public String getMoveLeftURL() {
		return moveLeftURL;
	}

	public void setMoveLeftURL(String moveLeftURL) {
		this.moveLeftURL = moveLeftURL;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

}