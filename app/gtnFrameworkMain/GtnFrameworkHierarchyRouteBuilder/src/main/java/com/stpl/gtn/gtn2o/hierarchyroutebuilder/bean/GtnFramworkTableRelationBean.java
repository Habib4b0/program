package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

public class GtnFramworkTableRelationBean {
	private String leftColumnName;
	private String rightColumnName;
	private int lefTableMasterSid;
	private int rightTableMasterSid;

	public GtnFramworkTableRelationBean(int lefTableMasterSid, int rightTableMasterSid, String leftTableId,
			String rightTableId) {
		super();
		this.leftColumnName = leftTableId;
		this.rightColumnName = rightTableId;
		this.lefTableMasterSid = lefTableMasterSid;
		this.rightTableMasterSid = rightTableMasterSid;
	}

	public GtnFramworkTableRelationBean() {
		super();
	}

	public String getLeftColumnName() {
		return leftColumnName;
	}

	public void setLeftColumnName(String leftColumnName) {
		this.leftColumnName = leftColumnName;
	}

	public String getRightColumnName() {
		return rightColumnName;
	}

	public void setRightColumnName(String rightColumnName) {
		this.rightColumnName = rightColumnName;
	}

	public int getLefTableMasterSid() {
		return lefTableMasterSid;
	}

	public void setLefTableMasterSid(int lefTableMasterSid) {
		this.lefTableMasterSid = lefTableMasterSid;
	}

	public int getRightTableMasterSid() {
		return rightTableMasterSid;
	}

	public void setRightTableMasterSid(int rightTableMasterSid) {
		this.rightTableMasterSid = rightTableMasterSid;
	}

	

}
