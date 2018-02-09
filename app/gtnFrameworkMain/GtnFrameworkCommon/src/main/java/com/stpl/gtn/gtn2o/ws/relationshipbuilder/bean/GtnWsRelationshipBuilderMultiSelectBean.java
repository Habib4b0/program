package com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnWsRelationshipBuilderMultiSelectBean {
	private static GtnWsRelationshipBuilderMultiSelectBean relationshipBuilderMultiSelectBean = null;

	private GtnWsRelationshipBuilderMultiSelectBean() {

	}

	public static GtnWsRelationshipBuilderMultiSelectBean getInstance() {
		if (relationshipBuilderMultiSelectBean == null)
			relationshipBuilderMultiSelectBean = new GtnWsRelationshipBuilderMultiSelectBean();

		return relationshipBuilderMultiSelectBean;
	}

	private List<GtnWsRecordBean> selectedItemsList;

	public List<GtnWsRecordBean> getSelectedItemsList() {
		return selectedItemsList;
	}

	public void setSelectedItemsList(List<GtnWsRecordBean> selectedItemsList) {
		this.selectedItemsList = selectedItemsList;
	}
}
