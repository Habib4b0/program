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

import com.stpl.app.service.persistence.StChDiscountProjMasterPK;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the StChDiscountProjMaster service. Represents a row in the &quot;ST_CH_DISCOUNT_PROJ_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.StChDiscountProjMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.StChDiscountProjMasterImpl}.
 * </p>
 *
 * @author
 * @see StChDiscountProjMaster
 * @see com.stpl.app.model.impl.StChDiscountProjMasterImpl
 * @see com.stpl.app.model.impl.StChDiscountProjMasterModelImpl
 * @generated
 */
@ProviderType
public interface StChDiscountProjMasterModel extends BaseModel<StChDiscountProjMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a st ch discount proj master model instance should use the {@link StChDiscountProjMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this st ch discount proj master.
	 *
	 * @return the primary key of this st ch discount proj master
	 */
	public StChDiscountProjMasterPK getPrimaryKey();

	/**
	 * Sets the primary key of this st ch discount proj master.
	 *
	 * @param primaryKey the primary key of this st ch discount proj master
	 */
	public void setPrimaryKey(StChDiscountProjMasterPK primaryKey);

	/**
	 * Returns the check record of this st ch discount proj master.
	 *
	 * @return the check record of this st ch discount proj master
	 */
	public boolean getCheckRecord();

	/**
	 * Returns <code>true</code> if this st ch discount proj master is check record.
	 *
	 * @return <code>true</code> if this st ch discount proj master is check record; <code>false</code> otherwise
	 */
	public boolean isCheckRecord();

	/**
	 * Sets whether this st ch discount proj master is check record.
	 *
	 * @param checkRecord the check record of this st ch discount proj master
	 */
	public void setCheckRecord(boolean checkRecord);

	/**
	 * Returns the selected periods of this st ch discount proj master.
	 *
	 * @return the selected periods of this st ch discount proj master
	 */
	@AutoEscape
	public String getSelectedPeriods();

	/**
	 * Sets the selected periods of this st ch discount proj master.
	 *
	 * @param selectedPeriods the selected periods of this st ch discount proj master
	 */
	public void setSelectedPeriods(String selectedPeriods);

	/**
	 * Returns the last modified date of this st ch discount proj master.
	 *
	 * @return the last modified date of this st ch discount proj master
	 */
	public Date getLastModifiedDate();

	/**
	 * Sets the last modified date of this st ch discount proj master.
	 *
	 * @param lastModifiedDate the last modified date of this st ch discount proj master
	 */
	public void setLastModifiedDate(Date lastModifiedDate);

	/**
	 * Returns the projection details sid of this st ch discount proj master.
	 *
	 * @return the projection details sid of this st ch discount proj master
	 */
	public int getProjectionDetailsSid();

	/**
	 * Sets the projection details sid of this st ch discount proj master.
	 *
	 * @param projectionDetailsSid the projection details sid of this st ch discount proj master
	 */
	public void setProjectionDetailsSid(int projectionDetailsSid);

	/**
	 * Returns the price group type of this st ch discount proj master.
	 *
	 * @return the price group type of this st ch discount proj master
	 */
	@AutoEscape
	public String getPriceGroupType();

	/**
	 * Sets the price group type of this st ch discount proj master.
	 *
	 * @param priceGroupType the price group type of this st ch discount proj master
	 */
	public void setPriceGroupType(String priceGroupType);

	/**
	 * Returns the user ID of this st ch discount proj master.
	 *
	 * @return the user ID of this st ch discount proj master
	 */
	public int getUserId();

	/**
	 * Sets the user ID of this st ch discount proj master.
	 *
	 * @param userId the user ID of this st ch discount proj master
	 */
	public void setUserId(int userId);

	/**
	 * Returns the net flag of this st ch discount proj master.
	 *
	 * @return the net flag of this st ch discount proj master
	 */
	@AutoEscape
	public String getNetFlag();

	/**
	 * Sets the net flag of this st ch discount proj master.
	 *
	 * @param netFlag the net flag of this st ch discount proj master
	 */
	public void setNetFlag(String netFlag);

	/**
	 * Returns the projected type of this st ch discount proj master.
	 *
	 * @return the projected type of this st ch discount proj master
	 */
	@AutoEscape
	public String getProjectedType();

	/**
	 * Sets the projected type of this st ch discount proj master.
	 *
	 * @param projectedType the projected type of this st ch discount proj master
	 */
	public void setProjectedType(String projectedType);

	/**
	 * Returns the baseline periods of this st ch discount proj master.
	 *
	 * @return the baseline periods of this st ch discount proj master
	 */
	@AutoEscape
	public String getBaselinePeriods();

	/**
	 * Sets the baseline periods of this st ch discount proj master.
	 *
	 * @param baselinePeriods the baseline periods of this st ch discount proj master
	 */
	public void setBaselinePeriods(String baselinePeriods);

	/**
	 * Returns the session ID of this st ch discount proj master.
	 *
	 * @return the session ID of this st ch discount proj master
	 */
	public int getSessionId();

	/**
	 * Sets the session ID of this st ch discount proj master.
	 *
	 * @param sessionId the session ID of this st ch discount proj master
	 */
	public void setSessionId(int sessionId);

	/**
	 * Returns the methodology of this st ch discount proj master.
	 *
	 * @return the methodology of this st ch discount proj master
	 */
	@AutoEscape
	public String getMethodology();

	/**
	 * Sets the methodology of this st ch discount proj master.
	 *
	 * @param methodology the methodology of this st ch discount proj master
	 */
	public void setMethodology(String methodology);

	/**
	 * Returns the rs model sid of this st ch discount proj master.
	 *
	 * @return the rs model sid of this st ch discount proj master
	 */
	public int getRsModelSid();

	/**
	 * Sets the rs model sid of this st ch discount proj master.
	 *
	 * @param rsModelSid the rs model sid of this st ch discount proj master
	 */
	public void setRsModelSid(int rsModelSid);

	/**
	 * Returns the discount type of this st ch discount proj master.
	 *
	 * @return the discount type of this st ch discount proj master
	 */
	@AutoEscape
	public String getDiscountType();

	/**
	 * Sets the discount type of this st ch discount proj master.
	 *
	 * @param discountType the discount type of this st ch discount proj master
	 */
	public void setDiscountType(String discountType);

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
	public int compareTo(StChDiscountProjMaster stChDiscountProjMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<StChDiscountProjMaster> toCacheModel();

	@Override
	public StChDiscountProjMaster toEscapedModel();

	@Override
	public StChDiscountProjMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}