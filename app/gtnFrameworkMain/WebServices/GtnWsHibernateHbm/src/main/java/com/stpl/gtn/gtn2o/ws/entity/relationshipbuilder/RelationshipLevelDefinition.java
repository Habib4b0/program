package com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder;
// Generated Mar 17, 2017 2:10:27 PM by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * RelationshipLevelDefinition generated by hbm2java
 */
public class RelationshipLevelDefinition implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int relationshipLevelSid;
	private HierarchyLevelDefinition hierarchyLevelDefinition;
	private RelationshipBuilder relationshipBuilder;
	private String relationshipLevelValues;
	private String levelNo;
	private String levelName;
	private String parentNode;
	private String hierarchyNo;
	private String flag;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private int versionNo;
	private String parentHierarchyNo;

	public RelationshipLevelDefinition() {
	}

	public RelationshipLevelDefinition(int relationshipLevelSid, int createdBy, Date createdDate, int modifiedBy,
			Date modifiedDate, int versionNo) {
		this.relationshipLevelSid = relationshipLevelSid;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.versionNo = versionNo;
	}

	public RelationshipLevelDefinition(int relationshipLevelSid, HierarchyLevelDefinition hierarchyLevelDefinition,
			RelationshipBuilder relationshipBuilder, String relationshipLevelValues, String levelNo, String levelName,
			String parentNode, String hierarchyNo, String flag, int createdBy, Date createdDate, int modifiedBy,
			Date modifiedDate, int versionNo, String parentHierarchyNo) {
		this.relationshipLevelSid = relationshipLevelSid;
		this.hierarchyLevelDefinition = hierarchyLevelDefinition;
		this.relationshipBuilder = relationshipBuilder;
		this.relationshipLevelValues = relationshipLevelValues;
		this.levelNo = levelNo;
		this.levelName = levelName;
		this.parentNode = parentNode;
		this.hierarchyNo = hierarchyNo;
		this.flag = flag;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.versionNo = versionNo;
		this.parentHierarchyNo = parentHierarchyNo;
	}

	public int getRelationshipLevelSid() {
		return this.relationshipLevelSid;
	}

	public void setRelationshipLevelSid(int relationshipLevelSid) {
		this.relationshipLevelSid = relationshipLevelSid;
	}

	public HierarchyLevelDefinition getHierarchyLevelDefinition() {
		return this.hierarchyLevelDefinition;
	}

	public void setHierarchyLevelDefinition(HierarchyLevelDefinition hierarchyLevelDefinition) {
		this.hierarchyLevelDefinition = hierarchyLevelDefinition;
	}

	public RelationshipBuilder getRelationshipBuilder() {
		return this.relationshipBuilder;
	}

	public void setRelationshipBuilder(RelationshipBuilder relationshipBuilder) {
		this.relationshipBuilder = relationshipBuilder;
	}

	public String getRelationshipLevelValues() {
		return this.relationshipLevelValues;
	}

	public void setRelationshipLevelValues(String relationshipLevelValues) {
		this.relationshipLevelValues = relationshipLevelValues;
	}

	public String getLevelNo() {
		return this.levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getParentNode() {
		return this.parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getHierarchyNo() {
		return this.hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getVersionNo() {
		return this.versionNo;
	}

	public String getParentHierarchyNo() {
		return parentHierarchyNo;
	}

	public void setParentHierarchyNo(String parentHierarchyNo) {
		this.parentHierarchyNo = parentHierarchyNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "\nrelationshipLevelSid =" + relationshipLevelSid + "\nhierarchyLevelDefinition ="
				+ hierarchyLevelDefinition + "\nrelationshipBuilder =" + relationshipBuilder
				+ "\nrelationshipLevelValues =" + relationshipLevelValues + "\nlevelNo =" + levelNo + "\nlevelName ="
				+ levelName + "\nparentNode =" + parentNode + "\nhierarchyNo =" + hierarchyNo + "\nflag =" + flag
				+ "\ncreatedBy =" + createdBy + "\ncreatedDate =" + createdDate + "\nmodifiedBy =" + modifiedBy
				+ "\nmodifiedDate =" + modifiedDate + "\nversionNo =" + versionNo;
	}

}
