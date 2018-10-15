package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean;

import java.util.List;
import java.util.Map;

public class GtnWsHierarchyDefinitionBean {

	private String hierarchyName;
	private int highLevel;
	private int lowestLevel;
	private String createdDate;
	private String modifiedDate;
	private String levelName;
	private int hierarchyVersion;
	private int hierarchyDefSid;
	private String hierarchyCategory;
	private String hierarchyType;
	private Map<Integer, String> hierarchyLevelValues;
	private Map<Integer, List<GtnWsRelationshipBuilderBean>> relationBean;

	public GtnWsHierarchyDefinitionBean() {
		super();
	}

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getHierarchyDefSid() {
		return hierarchyDefSid;
	}

	public void setHierarchyDefSid(int hierarchyDefSid) {
		this.hierarchyDefSid = hierarchyDefSid;
	}

	public String getHierarchyCategory() {
		return hierarchyCategory;
	}

	public void setHierarchyCategory(String hierarchyCategory) {
		this.hierarchyCategory = hierarchyCategory;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public Map<Integer, String> getHierarchyLevelValues() {
		return hierarchyLevelValues;
	}

	public void setHierarchyLevelValues(Map<Integer, String> hierarchyLevelValues) {
		this.hierarchyLevelValues = hierarchyLevelValues;
	}

	public Map<Integer, List<GtnWsRelationshipBuilderBean>> getRelationBean() {
		return relationBean;
	}

	public void setRelationBean(Map<Integer, List<GtnWsRelationshipBuilderBean>> relationBean) {
		this.relationBean = relationBean;
	}

	public int getHighLevel() {
		return highLevel;
	}

	public void setHighLevel(int highLevel) {
		this.highLevel = highLevel;
	}

	public int getLowestLevel() {
		return lowestLevel;
	}

	public void setLowestLevel(int lowestLevel) {
		this.lowestLevel = lowestLevel;
	}

	public int getHierarchyVersion() {
		return hierarchyVersion;
	}

	public void setHierarchyVersion(int hierarchyVersion) {
		this.hierarchyVersion = hierarchyVersion;
	}

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

        
}
