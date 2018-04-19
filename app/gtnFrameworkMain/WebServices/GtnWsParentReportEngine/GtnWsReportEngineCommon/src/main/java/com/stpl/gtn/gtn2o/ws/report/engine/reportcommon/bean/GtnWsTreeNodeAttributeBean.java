package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GtnWsTreeNodeAttributeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnWsTreeNodeAttributeBean() {
		super();
	}

	List<GtnWsAttributeBean> attributeBeanList;

	public List<GtnWsAttributeBean> getAttributeBeanList() {
		return attributeBeanList;
	}

	public void addAttributeBeanToList(GtnWsAttributeBean attributeBean) {
		if (attributeBeanList == null) {
			attributeBeanList = new ArrayList<>();
		}
		this.attributeBeanList.add(attributeBean);
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}
