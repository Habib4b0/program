package com.stpl.gtn.gtn2o.ws.authorization.bean;

import java.util.Collections;
import java.util.List;

public class GtnWsModuleComponentBean {
	private String componentId;
	private String componentDesc;
	private String componentType;
	private String screenName;
	private String moduleName;
	private List<GtnWsTablePropertyBean> tablePropertyList;

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentDesc() {
		return componentDesc;
	}

	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<GtnWsTablePropertyBean> getTablePropertyList() {
		return tablePropertyList == null ? tablePropertyList : Collections.unmodifiableList(tablePropertyList);
	}

	public void setTablePropertyList(List<GtnWsTablePropertyBean> tablePropertyList) {
		this.tablePropertyList = tablePropertyList == null ? tablePropertyList
				: Collections.unmodifiableList(tablePropertyList);
	}

	public GtnWsModuleComponentBean() {
		super();
	}

}
