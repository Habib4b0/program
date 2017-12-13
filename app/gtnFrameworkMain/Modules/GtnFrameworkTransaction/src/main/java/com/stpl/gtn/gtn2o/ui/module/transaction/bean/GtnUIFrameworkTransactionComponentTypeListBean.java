package com.stpl.gtn.gtn2o.ui.module.transaction.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;

public class GtnUIFrameworkTransactionComponentTypeListBean {

	public GtnUIFrameworkTransactionComponentTypeListBean() {
		super();
	}

	private List<GtnWSTransactionColumnBean> listViewComponent;
	private List<GtnWSTransactionColumnBean> defaultListViewComponent;
	private List<GtnWSTransactionColumnBean> searchComponent;
	private List<GtnWSTransactionColumnBean> viewModeComponents;
	private List<GtnWSTransactionColumnBean> viewModeOrderComponents;
	private List<GtnWSTransactionColumnBean> staticComponent;
	private boolean viewIndexFlag = false;
	private boolean outBoundModule = false;
	private String reprocessingWebServiceURL;
	private Object[] stagingUpdateColumnsValues = null;
	private Object[] stagingUpdateColumns = null;
	private Object[] stagingInsertColumns = null;
	private Map<String, String> formatterMap = null;

	public List<GtnWSTransactionColumnBean> getListViewComponent() {
		return Collections.unmodifiableList(listViewComponent);
	}

	public void setListViewComponent(List<GtnWSTransactionColumnBean> listViewComponent) {
		this.listViewComponent = new ArrayList<>(listViewComponent);
	}

	public List<GtnWSTransactionColumnBean> getDefaultListViewComponent() {
		return Collections.unmodifiableList(defaultListViewComponent);
	}

	public void setDefaultListViewComponent(List<GtnWSTransactionColumnBean> defaultListViewComponent) {
		this.defaultListViewComponent = new ArrayList<>(defaultListViewComponent);
	}

	public List<GtnWSTransactionColumnBean> getSearchComponent() {
		return Collections.unmodifiableList(searchComponent);
	}

	public void setSearchComponent(List<GtnWSTransactionColumnBean> searchComponent) {
		this.searchComponent = new ArrayList<>(searchComponent);
	}

	public List<GtnWSTransactionColumnBean> getViewModeComponents() {
		return Collections.unmodifiableList(viewModeComponents);
	}

	public void setViewModeComponents(List<GtnWSTransactionColumnBean> viewModeComponents) {
		this.viewModeComponents = new ArrayList<>(viewModeComponents);
	}

	public List<GtnWSTransactionColumnBean> getViewModeOrderComponents() {
		return Collections.unmodifiableList(viewModeOrderComponents);
	}

	public void setViewModeOrderComponents(List<GtnWSTransactionColumnBean> viewModeOrderComponents) {
		this.viewModeOrderComponents = new ArrayList<>(viewModeOrderComponents);
	}

	public List<GtnWSTransactionColumnBean> getStaticComponent() {
		return Collections.unmodifiableList(staticComponent);
	}

	public void setStaticComponent(List<GtnWSTransactionColumnBean> staticComponent) {
		this.staticComponent = new ArrayList<>(staticComponent);
	}

	public boolean isViewIndexFlag() {
		return viewIndexFlag;
	}

	public void setViewIndexFlag(boolean viewIndexFlag) {
		this.viewIndexFlag = viewIndexFlag;
	}

	public boolean isOutBoundModule() {
		return outBoundModule;
	}

	public void setOutBoundModule(boolean outBoundModule) {
		this.outBoundModule = outBoundModule;
	}

	public String getReprocessingWebServiceURL() {
		return reprocessingWebServiceURL;
	}

	public void setReprocessingWebServiceURL(String reprocessingWebServiceURL) {
		this.reprocessingWebServiceURL = reprocessingWebServiceURL;
	}

	public Object[] getStagingUpdateColumnsValues() {
		return stagingUpdateColumnsValues.length == 0 ? stagingUpdateColumnsValues : stagingUpdateColumnsValues.clone();
	}

	public Object[] getStagingInsertColumns() {
		return stagingInsertColumns.length == 0 ? stagingInsertColumns : stagingInsertColumns.clone();
	}

	public Object[] getStagingUpdateColumns() {
		return stagingUpdateColumns.length == 0 ? stagingUpdateColumns : stagingUpdateColumns.clone();
	}

	public void setStagingUpdateColumnsValues(Object[] stagingUpdateColumnsValues) {
		this.stagingUpdateColumnsValues = stagingUpdateColumnsValues.clone();
	}

	public void setStagingInsertColumns(Object[] stagingInsertColumns) {
		this.stagingInsertColumns = stagingInsertColumns.clone();
	}

	public void setStagingUpdateColumns(Object[] stagingUpdateColumns) {
		this.stagingUpdateColumns = stagingUpdateColumns.clone();
	}

	public String getFormatterMap(String property) {
		return formatterMap.get(property);
	}

	public void putFormatterMap(String propertyId, String pattern) {
		if (formatterMap == null) {
			formatterMap = new HashMap<>();
		}
		formatterMap.put(propertyId, pattern);
	}

}
