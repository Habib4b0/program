package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnWsTreeNodeAttributeBean implements Serializable {

	public GtnWsTreeNodeAttributeBean() {
		super();
	}

	private List<GtnWsAttributeBean> attributeBeanList = null;

	public List<GtnWsAttributeBean> getAttributeBeanList() {
		return attributeBeanList == null ? attributeBeanList : Collections.unmodifiableList(attributeBeanList);
	}

	public void setAttributeBeanList(List<GtnWsAttributeBean> attributeBeanList) {
		this.attributeBeanList = attributeBeanList == null ? attributeBeanList
				: Collections.unmodifiableList(attributeBeanList);
	}

	public void addAttributeBeanToList(GtnWsAttributeBean attributeBean) {
		if (attributeBeanList == null) {
			attributeBeanList = new ArrayList<>();
		}
		this.attributeBeanList.add(attributeBean);
	}

	public void addAllAttributeBeanToList(List<GtnWsAttributeBean> attributeBean) {
		if (attributeBeanList == null) {
			attributeBeanList = new ArrayList<>();
		}
		this.attributeBeanList.addAll(attributeBean);
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}
