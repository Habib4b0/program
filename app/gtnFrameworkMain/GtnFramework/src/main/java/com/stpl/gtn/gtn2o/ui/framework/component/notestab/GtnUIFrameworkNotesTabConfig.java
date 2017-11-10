package com.stpl.gtn.gtn2o.ui.framework.component.notestab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GtnUIFrameworkNotesTabConfig {

	private String moduleName = "";
	private String saveServiceUrl = "";
	private String loadServiceUrl = "";
	private String mode = "";
	private String userName = "";
	private String userId = "";

	public GtnUIFrameworkNotesTabConfig() {
		/**
		 * empty constructor
		 */
	}

	private List<String> validFormats = Arrays.asList("doc", "docx", "ppt", "xls", "xlsx", "pdf", "txt", "csv", "jpg",
			"jpeg", "pptx");

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSaveServiceUrl() {
		return saveServiceUrl;
	}

	public void setSaveServiceUrl(String saveServiceUrl) {
		this.saveServiceUrl = saveServiceUrl;
	}

	public String getLoadServiceUrl() {
		return loadServiceUrl;
	}

	public void setLoadServiceUrl(String loadServiceUrl) {
		this.loadServiceUrl = loadServiceUrl;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<String> getValidFormats() {
		return validFormats == null ? validFormats : Collections.unmodifiableList(validFormats);
	}

	public void setValidFormats(List<String> validFormats) {
		this.validFormats = new ArrayList<>(validFormats);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
