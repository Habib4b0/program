/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the RebateTierFormula service. Represents a row in the &quot;REBATE_TIER_FORMULA&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.RebateTierFormulaImpl}.
 * </p>
 *
 * @author
 * @see RebateTierFormula
 * @see com.stpl.app.model.impl.RebateTierFormulaImpl
 * @see com.stpl.app.model.impl.RebateTierFormulaModelImpl
 * @generated
 */
@ProviderType
public interface RebateTierFormulaModel extends BaseModel<RebateTierFormula> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a rebate tier formula model instance should use the {@link RebateTierFormula} interface instead.
	 */

	/**
	 * Returns the primary key of this rebate tier formula.
	 *
	 * @return the primary key of this rebate tier formula
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this rebate tier formula.
	 *
	 * @param primaryKey the primary key of this rebate tier formula
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the rebate tier formula no of this rebate tier formula.
	 *
	 * @return the rebate tier formula no of this rebate tier formula
	 */
	@AutoEscape
	public String getRebateTierFormulaNo();

	/**
	 * Sets the rebate tier formula no of this rebate tier formula.
	 *
	 * @param rebateTierFormulaNo the rebate tier formula no of this rebate tier formula
	 */
	public void setRebateTierFormulaNo(String rebateTierFormulaNo);

	/**
	 * Returns the rebate tier formula name of this rebate tier formula.
	 *
	 * @return the rebate tier formula name of this rebate tier formula
	 */
	@AutoEscape
	public String getRebateTierFormulaName();

	/**
	 * Sets the rebate tier formula name of this rebate tier formula.
	 *
	 * @param rebateTierFormulaName the rebate tier formula name of this rebate tier formula
	 */
	public void setRebateTierFormulaName(String rebateTierFormulaName);

	/**
	 * Returns the rebate plan master sid of this rebate tier formula.
	 *
	 * @return the rebate plan master sid of this rebate tier formula
	 */
	public int getRebatePlanMasterSid();

	/**
	 * Sets the rebate plan master sid of this rebate tier formula.
	 *
	 * @param rebatePlanMasterSid the rebate plan master sid of this rebate tier formula
	 */
	public void setRebatePlanMasterSid(int rebatePlanMasterSid);

	/**
	 * Returns the modified date of this rebate tier formula.
	 *
	 * @return the modified date of this rebate tier formula
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this rebate tier formula.
	 *
	 * @param modifiedDate the modified date of this rebate tier formula
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the record lock status of this rebate tier formula.
	 *
	 * @return the record lock status of this rebate tier formula
	 */
	public boolean getRecordLockStatus();

	/**
	 * Returns <code>true</code> if this rebate tier formula is record lock status.
	 *
	 * @return <code>true</code> if this rebate tier formula is record lock status; <code>false</code> otherwise
	 */
	public boolean isRecordLockStatus();

	/**
	 * Sets whether this rebate tier formula is record lock status.
	 *
	 * @param recordLockStatus the record lock status of this rebate tier formula
	 */
	public void setRecordLockStatus(boolean recordLockStatus);

	/**
	 * Returns the source of this rebate tier formula.
	 *
	 * @return the source of this rebate tier formula
	 */
	@AutoEscape
	public String getSource();

	/**
	 * Sets the source of this rebate tier formula.
	 *
	 * @param source the source of this rebate tier formula
	 */
	public void setSource(String source);

	/**
	 * Returns the created by of this rebate tier formula.
	 *
	 * @return the created by of this rebate tier formula
	 */
	public int getCreatedBy();

	/**
	 * Sets the created by of this rebate tier formula.
	 *
	 * @param createdBy the created by of this rebate tier formula
	 */
	public void setCreatedBy(int createdBy);

	/**
	 * Returns the created date of this rebate tier formula.
	 *
	 * @return the created date of this rebate tier formula
	 */
	public Date getCreatedDate();

	/**
	 * Sets the created date of this rebate tier formula.
	 *
	 * @param createdDate the created date of this rebate tier formula
	 */
	public void setCreatedDate(Date createdDate);

	/**
	 * Returns the batch ID of this rebate tier formula.
	 *
	 * @return the batch ID of this rebate tier formula
	 */
	@AutoEscape
	public String getBatchId();

	/**
	 * Sets the batch ID of this rebate tier formula.
	 *
	 * @param batchId the batch ID of this rebate tier formula
	 */
	public void setBatchId(String batchId);

	/**
	 * Returns the rebate tier formula ID of this rebate tier formula.
	 *
	 * @return the rebate tier formula ID of this rebate tier formula
	 */
	@AutoEscape
	public String getRebateTierFormulaId();

	/**
	 * Sets the rebate tier formula ID of this rebate tier formula.
	 *
	 * @param rebateTierFormulaId the rebate tier formula ID of this rebate tier formula
	 */
	public void setRebateTierFormulaId(String rebateTierFormulaId);

	/**
	 * Returns the inbound status of this rebate tier formula.
	 *
	 * @return the inbound status of this rebate tier formula
	 */
	@AutoEscape
	public String getInboundStatus();

	/**
	 * Sets the inbound status of this rebate tier formula.
	 *
	 * @param inboundStatus the inbound status of this rebate tier formula
	 */
	public void setInboundStatus(String inboundStatus);

	/**
	 * Returns the modified by of this rebate tier formula.
	 *
	 * @return the modified by of this rebate tier formula
	 */
	public int getModifiedBy();

	/**
	 * Sets the modified by of this rebate tier formula.
	 *
	 * @param modifiedBy the modified by of this rebate tier formula
	 */
	public void setModifiedBy(int modifiedBy);

	/**
	 * Returns the rebate tier formula sid of this rebate tier formula.
	 *
	 * @return the rebate tier formula sid of this rebate tier formula
	 */
	public int getRebateTierFormulaSid();

	/**
	 * Sets the rebate tier formula sid of this rebate tier formula.
	 *
	 * @param rebateTierFormulaSid the rebate tier formula sid of this rebate tier formula
	 */
	public void setRebateTierFormulaSid(int rebateTierFormulaSid);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(RebateTierFormula rebateTierFormula);

	@Override
	public int hashCode();

	@Override
	public CacheModel<RebateTierFormula> toCacheModel();

	@Override
	public RebateTierFormula toEscapedModel();

	@Override
	public RebateTierFormula toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}