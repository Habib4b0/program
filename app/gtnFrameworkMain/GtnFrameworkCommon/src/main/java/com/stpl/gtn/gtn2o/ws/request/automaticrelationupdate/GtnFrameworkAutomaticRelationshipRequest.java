package com.stpl.gtn.gtn2o.ws.request.automaticrelationupdate;

public class GtnFrameworkAutomaticRelationshipRequest {

	private Integer relationshipBuilderSid;
	private Integer hierarchyBuilderSid;
	private String userId;

	public GtnFrameworkAutomaticRelationshipRequest() {
		super();
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getRelationshipBuilderSid() {
		return relationshipBuilderSid;
	}

	public void setRelationshipBuilderSid(Integer relationshipBuilderSid) {
		this.relationshipBuilderSid = relationshipBuilderSid;
	}

	public Integer getHierarchyBuilderSid() {
		return hierarchyBuilderSid;
	}

	public void setHierarchyBuilderSid(Integer hierarchyBuilderSid) {
		this.hierarchyBuilderSid = hierarchyBuilderSid;
	}

}
