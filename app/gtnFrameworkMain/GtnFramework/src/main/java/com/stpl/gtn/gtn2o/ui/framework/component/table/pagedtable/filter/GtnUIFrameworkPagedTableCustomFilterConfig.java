package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class GtnUIFrameworkPagedTableCustomFilterConfig {

	private String propertId=null;

	private GtnUIFrameworkComponentType gtnComponentType=null;

	private GtnUIFrameworkComponentConfig gtnComponentConfig=null;

	public String getPropertId() {
		return propertId;
	}

	public void setPropertId(String propertId) {
		this.propertId = propertId;
	}

	public GtnUIFrameworkComponentType getGtnComponentType() {
		return gtnComponentType;
	}

	public void setGtnComponentType(GtnUIFrameworkComponentType gtnComponentType) {
		this.gtnComponentType = gtnComponentType;
	}

	public GtnUIFrameworkComponentConfig getGtnComponentConfig() {
		return gtnComponentConfig;
	}

	public void setGtnComponentConfig(GtnUIFrameworkComponentConfig gtnComponentConfig) {
		this.gtnComponentConfig = gtnComponentConfig;
	}

}
