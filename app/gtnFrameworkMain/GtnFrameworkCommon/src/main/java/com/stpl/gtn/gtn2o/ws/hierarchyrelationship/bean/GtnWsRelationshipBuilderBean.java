package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean;

public class GtnWsRelationshipBuilderBean {

	private int relationshipBuilderSid;
	private String relationshipName;
	private int versionNo;

	public GtnWsRelationshipBuilderBean() {
		super();
	}

	public int getRelationshipBuilderSid() {
		return relationshipBuilderSid;
	}

	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		this.relationshipBuilderSid = relationshipBuilderSid;
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

}
