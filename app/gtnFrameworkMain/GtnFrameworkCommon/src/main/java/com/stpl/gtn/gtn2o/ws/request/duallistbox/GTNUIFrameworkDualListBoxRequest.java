
package com.stpl.gtn.gtn2o.ws.request.duallistbox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GTNUIFrameworkDualListBoxRequest implements GtnWSRequestData {

	public GTNUIFrameworkDualListBoxRequest() {
		super();
	}

	private List<String> dataList;
	private String filterColumnName;
	private List<String> recordHeader;
	private String filterValue;
	private Object helperData;
	private String moduleName;
	private String userId;
	private String leftTableSupportFileName;
	private GtnWsRecordBean dualListBoxRecord;

	public List<String> getDataList() {
		return dataList == null ? dataList : Collections.unmodifiableList(dataList);
	}

	public void setDataList(List<String> dataList) {
		this.dataList = dataList == null ? dataList : Collections.unmodifiableList(dataList);
	}

	public String getFilterColumnName() {
		return filterColumnName;
	}

	public void setFilterColumnName(String filterColumnName) {
		this.filterColumnName = filterColumnName;
	}

	public List<String> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<String> recordHeader) {
		this.recordHeader = recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public Object getHelperData() {
		return helperData;
	}

	public void setHelperData(Object helperData) {
		this.helperData = helperData;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLeftTableSupportFileName() {
		return leftTableSupportFileName;
	}

	public void setLeftTableSupportFileName(String leftTableSupportFileName) {
		this.leftTableSupportFileName = leftTableSupportFileName;
	}

	public GtnWsRecordBean getDualListBoxRecord() {
		return dualListBoxRecord;
	}

	public void setDualListBoxRecord(GtnWsRecordBean dualListBoxRecord) {
		this.dualListBoxRecord = dualListBoxRecord;
	}
        private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

}
