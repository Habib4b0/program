package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;

public class GtnFrameworkHierarchyQueryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int hierarchyLevelDefSid;

	private GtnFrameworkQueryGeneratorBean query;

	public GtnFrameworkHierarchyQueryBean() {
		super();
	}

	public int getHierarchyLevelDefSid() {
		return hierarchyLevelDefSid;
	}

	public void setHierarchyLevelDefSid(int hierarchyLevelDefSid) {
		this.hierarchyLevelDefSid = hierarchyLevelDefSid;
	}

	public GtnFrameworkQueryGeneratorBean getQuery() {
		return query;
	}

	public void setQuery(GtnFrameworkQueryGeneratorBean query) {
		this.query = query;
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

}
