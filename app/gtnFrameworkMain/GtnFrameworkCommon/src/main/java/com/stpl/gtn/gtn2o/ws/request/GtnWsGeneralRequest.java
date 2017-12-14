package com.stpl.gtn.gtn2o.ws.request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnWsGeneralRequest implements GtnWSRequestData {

	public GtnWsGeneralRequest() {
		super();
	}

	private String comboBoxType = "COMPANY_TYPE";

	private String userId;

	private String sessionId;

	private String token;

	private List<Object> comboBoxWhereclauseParamList = null;

	private String moduleName;

	private Object extraParameter;

	private boolean isExcel;

	private List<String> tableColumnFormatList = null;

	public String getComboBoxType() {
		return comboBoxType;
	}

	public void setComboBoxType(String comboBoxType) {
		this.comboBoxType = comboBoxType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<Object> getComboBoxWhereclauseParamList() {
		return comboBoxWhereclauseParamList == null ? comboBoxWhereclauseParamList
				: Collections.unmodifiableList(comboBoxWhereclauseParamList);
	}

	public void setComboBoxWhereclauseParamList(List<Object> comboBoxWhereclauseParamList) {
		this.comboBoxWhereclauseParamList = comboBoxWhereclauseParamList == null ? comboBoxWhereclauseParamList
				: Collections.unmodifiableList(comboBoxWhereclauseParamList);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Object getExtraParameter() {
		return extraParameter;
	}

	public void setExtraParameter(Object extraParameter) {
		this.extraParameter = extraParameter;
	}

	public boolean isExcel() {
		return isExcel;
	}

	public void setExcel(boolean isExcel) {
		this.isExcel = isExcel;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

	public List<String> getTableColumnFormatList() {
		return tableColumnFormatList == null ? tableColumnFormatList
				: Collections.unmodifiableList(tableColumnFormatList);

	}

	public void setTableColumnFormatList(List<String> tableColumnFormatList) {
		this.tableColumnFormatList = tableColumnFormatList == null ? tableColumnFormatList
				: Collections.unmodifiableList(tableColumnFormatList);
	}

	public void removeTableColumFormatListByIndex(int index) {
		if (tableColumnFormatList != null) {
			List<String> tempList = new ArrayList<>(tableColumnFormatList);
			tempList.remove(index);
			tableColumnFormatList = tempList;
		}
	}

}
