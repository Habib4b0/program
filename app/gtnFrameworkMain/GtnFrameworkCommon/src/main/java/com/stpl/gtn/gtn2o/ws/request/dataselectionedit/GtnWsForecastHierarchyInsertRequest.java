package com.stpl.gtn.gtn2o.ws.request.dataselectionedit;

public class GtnWsForecastHierarchyInsertRequest {
	private String userId;

	private String sessionId;

	private String token;
	private int projectionMasterSid;
	private int custRelationShipBuilderSid;
	private int prodRelationShipBuilderSid;
	private int custHierarchySid;
	private int prodHierarchySid;
	private int projectionRelationshipCustVersionNo;
	private int projectionRelationshipProdVersionNo;
	private int projectionHierarchyProdVersionNo;
	private int projectionHierarchyCustVersionNo;

	public int getProjectionHierarchyProdVersionNo() {
		return projectionHierarchyProdVersionNo;
	}

	public void setProjectionHierarchyProdVersionNo(int projectionHierarchyProdVersionNo) {
		this.projectionHierarchyProdVersionNo = projectionHierarchyProdVersionNo;
	}

	public int getProjectionHierarchyCustVersionNo() {
		return projectionHierarchyCustVersionNo;
	}

	public void setProjectionHierarchyCustVersionNo(int projectionHierarchyCustVersionNo) {
		this.projectionHierarchyCustVersionNo = projectionHierarchyCustVersionNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getProjectionMasterSid() {
		return projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		this.projectionMasterSid = projectionMasterSid;
	}

	public int getCustRelationShipBuilderSid() {
		return custRelationShipBuilderSid;
	}

	public void setCustRelationShipBuilderSid(int custRelationShipBuilderSid) {
		this.custRelationShipBuilderSid = custRelationShipBuilderSid;
	}

	public int getProdRelationShipBuilderSid() {
		return prodRelationShipBuilderSid;
	}

	public void setProdRelationShipBuilderSid(int prodRelationShipBuilderSid) {
		this.prodRelationShipBuilderSid = prodRelationShipBuilderSid;
	}

	public int getProjectionRelationshipCustVersionNo() {
		return projectionRelationshipCustVersionNo;
	}
	public void setProjectionRelationshipCustVersionNo(int projectionRelationshipCustVersionNo) {
		this.projectionRelationshipCustVersionNo = projectionRelationshipCustVersionNo;
	}

	public int getProjectionRelationshipProdVersionNo() {
		return projectionRelationshipProdVersionNo;
	}

	public void setProjectionRelationshipProdVersionNo(int projectionRelationshipProdVersionNo) {
		this.projectionRelationshipProdVersionNo = projectionRelationshipProdVersionNo;
	}

	public int getCustHierarchySid() {
		return custHierarchySid;
	}

	public void setCustHierarchySid(int custHierarchySid) {
		this.custHierarchySid = custHierarchySid;
	}

	public int getProdHierarchySid() {
		return prodHierarchySid;
	}

	public void setProdHierarchySid(int prodHierarchySid) {
		this.prodHierarchySid = prodHierarchySid;
	}

}
