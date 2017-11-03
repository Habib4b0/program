package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFrameworkEntityBean {

	private int entityMasterSid;
	private String entityname;
	private int masterTableId;

	public GtnFrameworkEntityBean(int entityId, String entityname, int masterTableId) {
		super();
		this.entityMasterSid = entityId;
		this.entityname = entityname;
		this.masterTableId = masterTableId;
	}

	public GtnFrameworkEntityBean() {
		
	}

	public int getEntityMasterSid() {
		return entityMasterSid;
	}

	public void setEntityMasterSid(int entityMasterSid) {
		this.entityMasterSid = entityMasterSid;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public int getMasterTableId() {
		return masterTableId;
	}

	public void setMasterTableId(int masterTableId) {
		this.masterTableId = masterTableId;
	}

}
