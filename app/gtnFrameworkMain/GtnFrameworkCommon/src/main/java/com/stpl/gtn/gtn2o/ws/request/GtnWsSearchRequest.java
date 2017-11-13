package com.stpl.gtn.gtn2o.ws.request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordTypeBean;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;

public class GtnWsSearchRequest implements GtnWSRequestData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsSearchRequest() {
		super();
	}

	private String searchQueryName = "";
	private boolean isCount;
	private List<Object> searchColumnNameList;
	private List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList;
	private List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList;
	private int tableRecordStart;
	private int tableRecordOffset;
	private String searchModuleName = "";
	private List<Object> queryInputList = new ArrayList<>();
	private GtnWsSearchQueryConfigLoaderType searchConfigLodaderType;

	private GtnWsRecordTypeBean gtnWsRecordTypeBean;
	private GtnWsRecordBean parentBean;

	public String getSearchQueryName() {
		return searchQueryName;
	}

	public void setSearchQueryName(String searchQueryName) {
		this.searchQueryName = searchQueryName;
	}

	public boolean isCount() {
		return isCount;
	}

	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	public List<Object> getSearchColumnNameList() {
		return searchColumnNameList == null ? searchColumnNameList : Collections.unmodifiableList(searchColumnNameList);
	}

	public void setSearchColumnNameList(List<Object> searchColumnNameList) {
		this.searchColumnNameList = searchColumnNameList == null ? searchColumnNameList
				: Collections.unmodifiableList(searchColumnNameList);
	}

	public List<GtnWebServiceSearchCriteria> getGtnWebServiceSearchCriteriaList() {
		return gtnWebServiceSearchCriteriaList == null ? gtnWebServiceSearchCriteriaList
				: Collections.unmodifiableList(gtnWebServiceSearchCriteriaList);
	}

	public void setGtnWebServiceSearchCriteriaList(List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList) {
		this.gtnWebServiceSearchCriteriaList = gtnWebServiceSearchCriteriaList == null ? gtnWebServiceSearchCriteriaList
				: new ArrayList<>(gtnWebServiceSearchCriteriaList);
	}

	public List<GtnWebServiceOrderByCriteria> getGtnWebServiceOrderByCriteriaList() {
		return gtnWebServiceOrderByCriteriaList == null ? gtnWebServiceOrderByCriteriaList
				: Collections.unmodifiableList(gtnWebServiceOrderByCriteriaList);
	}

	public void setGtnWebServiceOrderByCriteriaList(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		this.gtnWebServiceOrderByCriteriaList = gtnWebServiceOrderByCriteriaList == null
				? gtnWebServiceOrderByCriteriaList
				: Collections.unmodifiableList(gtnWebServiceOrderByCriteriaList);
	}

	public int getTableRecordStart() {
		return tableRecordStart;
	}

	public void setTableRecordStart(int tableRecordStart) {
		this.tableRecordStart = tableRecordStart;
	}

	public int getTableRecordOffset() {
		return tableRecordOffset;
	}

	public void setTableRecordOffset(int tableRecordOffset) {
		this.tableRecordOffset = tableRecordOffset;
	}

	public String getSearchModuleName() {
		return searchModuleName;
	}

	public void setSearchModuleName(String searchModuleName) {
		this.searchModuleName = searchModuleName;
	}

	public List<Object> getQueryInputList() {
		return queryInputList == null ? queryInputList : Collections.unmodifiableList(queryInputList);
	}

	public void setQueryInputList(List<Object> queryInputList) {
		this.queryInputList = queryInputList == null ? queryInputList : Collections.unmodifiableList(queryInputList);
	}

	public GtnWsSearchQueryConfigLoaderType getSearchConfigLodaderType() {
		return searchConfigLodaderType;
	}

	public void setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType searchConfigLodaderType) {
		this.searchConfigLodaderType = searchConfigLodaderType;
	}

	public GtnWsRecordTypeBean getGtnWsRecordTypeBean() {
		return gtnWsRecordTypeBean;
	}

	public void setGtnWsRecordTypeBean(GtnWsRecordTypeBean gtnWsRecordTypeBean) {
		this.gtnWsRecordTypeBean = gtnWsRecordTypeBean;
	}

	public GtnWsRecordBean getParentBean() {
		return parentBean;
	}

	public void setParentBean(GtnWsRecordBean parentBean) {
		this.parentBean = parentBean;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

	public void removeGtnWebServiceSearchCriteriaList(int index) {
		if (this.gtnWebServiceSearchCriteriaList != null) {
			this.gtnWebServiceSearchCriteriaList.remove(index);
		}
	}

}
