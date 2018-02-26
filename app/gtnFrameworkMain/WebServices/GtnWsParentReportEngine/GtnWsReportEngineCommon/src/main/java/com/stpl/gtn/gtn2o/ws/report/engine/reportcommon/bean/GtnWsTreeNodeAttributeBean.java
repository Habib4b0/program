package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.util.ArrayList;
import java.util.List;

public class GtnWsTreeNodeAttributeBean {

	List<GtnWsAttributeBean> attributeBeanList = null;

	public List<GtnWsAttributeBean> getAttributeBeanList() {
		return attributeBeanList;
	}

	public void addAttributeBeanToList(GtnWsAttributeBean attributeBean) {
		if (attributeBeanList == null) {
			attributeBeanList = new ArrayList<>();
		}
		this.attributeBeanList.add(attributeBean);
	}

}
