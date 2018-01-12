package com.stpl.gtn.gtn2o.ws.entity.role;
// Generated Feb 22, 2017 8:36:04 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.util.Date;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private long roleId;
	private long mvccVersion;
	private String uuid;
	private Long companyId;
	private Long userId;
	private String userName;
	private Date createDate;
	private Date modifiedDate;
	private Long classNameId;
	private Long classPk;
	private String name;
	private String title;
	private String description;
	private Integer type;
	private String subtype;

	public Role() {
	}

	public Role(long roleId, long mvccVersion) {
		this.roleId = roleId;
		this.mvccVersion = mvccVersion;
	}

	public Role(long roleId, long mvccVersion, String uuid, Long companyId, Long userId, String userName,
			Date createDate, Date modifiedDate, Long classNameId, Long classPk, String name, String title,
			String description, Integer type, String subtype) {
		this.roleId = roleId;
		this.mvccVersion = mvccVersion;
		this.uuid = uuid;
		this.companyId = companyId;
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.classNameId = classNameId;
		this.classPk = classPk;
		this.name = name;
		this.title = title;
		this.description = description;
		this.type = type;
		this.subtype = subtype;
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getMvccVersion() {
		return this.mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getClassNameId() {
		return this.classNameId;
	}

	public void setClassNameId(Long classNameId) {
		this.classNameId = classNameId;
	}

	public Long getClassPk() {
		return this.classPk;
	}

	public void setClassPk(Long classPk) {
		this.classPk = classPk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSubtype() {
		return this.subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

}
