package com.stpl.gtn.gtn2o.ui.module.transaction.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnUIFrameworkTransactionComponentListForInvalidBean {
	

	public GtnUIFrameworkTransactionComponentListForInvalidBean() {
		super();
	}

	private List<GtnUIFrameworkComponentConfig> searchComponentList;
	private List<GtnUIFrameworkComponentConfig> listViewComponentList;
	private List<GtnUIFrameworkComponentConfig> defaultListViewComponentList;
	private List<GtnUIFrameworkComponentConfig> viewComponentList;
	private List<GtnUIFrameworkComponentConfig> staticComponent1List;
	private List<GtnUIFrameworkComponentConfig> staticComponent2List;
	private List<GtnUIFrameworkComponentConfig> searchAndResetComponentList;
	private List<GtnUIFrameworkComponentConfig> reprocessAndRemoveComponentList;
	private List<GtnUIFrameworkComponentConfig> excelButtonComponentList;

	public List<GtnUIFrameworkComponentConfig> getSearchComponentList() {
		return searchComponentList!=null ? Collections.unmodifiableList(searchComponentList) : searchComponentList;
	}

	public void setSearchComponentList(List<GtnUIFrameworkComponentConfig> searchComponentList) {
		this.searchComponentList = new ArrayList<>(searchComponentList);
	}

	public List<GtnUIFrameworkComponentConfig> getListViewComponentList() {
		return listViewComponentList!=null? Collections.unmodifiableList(listViewComponentList): listViewComponentList;
	}

	public void setListViewComponentList(List<GtnUIFrameworkComponentConfig> listViewComponentList) {
		this.listViewComponentList = new ArrayList<>(listViewComponentList);
	}

	public List<GtnUIFrameworkComponentConfig> getDefaultListViewComponentList() {
		return defaultListViewComponentList!=null ?Collections.unmodifiableList(defaultListViewComponentList):defaultListViewComponentList;
	}

	public void setDefaultListViewComponentList(List<GtnUIFrameworkComponentConfig> defaultListViewComponentList) {
		this.defaultListViewComponentList = new ArrayList<>(defaultListViewComponentList);
	}

	public List<GtnUIFrameworkComponentConfig> getViewComponentList() {
		return viewComponentList!=null ? Collections.unmodifiableList(viewComponentList):viewComponentList;
	}

	public void setViewComponentList(List<GtnUIFrameworkComponentConfig> viewComponentList) {
		this.viewComponentList = new ArrayList<>(viewComponentList);
	}

	public List<GtnUIFrameworkComponentConfig> getStaticComponent1List() {
		return staticComponent1List!=null ? Collections.unmodifiableList(staticComponent1List):staticComponent1List;
	}

	public void setStaticComponent1List(List<GtnUIFrameworkComponentConfig> staticComponent1List) {
		this.staticComponent1List = new ArrayList<>(staticComponent1List);
	}

	public List<GtnUIFrameworkComponentConfig> getStaticComponent2List() {
		return staticComponent2List!=null ? Collections.unmodifiableList(staticComponent2List):staticComponent2List;
	}

	public void setStaticComponent2List(List<GtnUIFrameworkComponentConfig> staticComponent2List) {
		this.staticComponent2List = new ArrayList<>(staticComponent2List);
	}

	public List<GtnUIFrameworkComponentConfig> getSearchAndResetComponentList() {
		return searchAndResetComponentList!=null ? Collections.unmodifiableList(searchAndResetComponentList):searchAndResetComponentList;
	}

	public void setSearchAndResetComponentList(List<GtnUIFrameworkComponentConfig> searchAndResetComponentList) {
		this.searchAndResetComponentList = new ArrayList<>(searchAndResetComponentList);
	}

	public List<GtnUIFrameworkComponentConfig> getReprocessAndRemoveComponentList() {
		return reprocessAndRemoveComponentList!=null ? Collections.unmodifiableList(reprocessAndRemoveComponentList):reprocessAndRemoveComponentList;
	}

	public void setReprocessAndRemoveComponentList(
			List<GtnUIFrameworkComponentConfig> reprocessAndRemoveComponentList) {
		this.reprocessAndRemoveComponentList = new ArrayList<>(reprocessAndRemoveComponentList);
	}

	public List<GtnUIFrameworkComponentConfig> getExcelButtonComponentList() {
		return excelButtonComponentList!=null ? Collections.unmodifiableList(excelButtonComponentList):excelButtonComponentList;
	}

	public void setExcelButtonComponentList(List<GtnUIFrameworkComponentConfig> excelButtonComponentList) {
		this.excelButtonComponentList = new ArrayList<>(excelButtonComponentList);
	}

}
