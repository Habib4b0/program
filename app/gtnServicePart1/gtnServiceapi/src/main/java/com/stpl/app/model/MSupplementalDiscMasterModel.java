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
 * The base model interface for the MSupplementalDiscMaster service. Represents a row in the &quot;M_SUPPLEMENTAL_DISC_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.MSupplementalDiscMasterImpl}.
 * </p>
 *
 * @author
 * @see MSupplementalDiscMaster
 * @see com.stpl.app.model.impl.MSupplementalDiscMasterImpl
 * @see com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl
 * @generated
 */
@ProviderType
public interface MSupplementalDiscMasterModel extends BaseModel<MSupplementalDiscMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a m supplemental disc master model instance should use the {@link MSupplementalDiscMaster} interface instead.
	 */

	/**
	 * Returns the primary key of this m supplemental disc master.
	 *
	 * @return the primary key of this m supplemental disc master
	 */
	public int getPrimaryKey();

	/**
	 * Sets the primary key of this m supplemental disc master.
	 *
	 * @param primaryKey the primary key of this m supplemental disc master
	 */
	public void setPrimaryKey(int primaryKey);

	/**
	 * Returns the actual discount rate2 of this m supplemental disc master.
	 *
	 * @return the actual discount rate2 of this m supplemental disc master
	 */
	public double getActualDiscountRate2();

	/**
	 * Sets the actual discount rate2 of this m supplemental disc master.
	 *
	 * @param actualDiscountRate2 the actual discount rate2 of this m supplemental disc master
	 */
	public void setActualDiscountRate2(double actualDiscountRate2);

	/**
	 * Returns the actual discount rate1 of this m supplemental disc master.
	 *
	 * @return the actual discount rate1 of this m supplemental disc master
	 */
	public double getActualDiscountRate1();

	/**
	 * Sets the actual discount rate1 of this m supplemental disc master.
	 *
	 * @param actualDiscountRate1 the actual discount rate1 of this m supplemental disc master
	 */
	public void setActualDiscountRate1(double actualDiscountRate1);

	/**
	 * Returns the market type of this m supplemental disc master.
	 *
	 * @return the market type of this m supplemental disc master
	 */
	@AutoEscape
	public String getMarketType();

	/**
	 * Sets the market type of this m supplemental disc master.
	 *
	 * @param marketType the market type of this m supplemental disc master
	 */
	public void setMarketType(String marketType);

	/**
	 * Returns the actual methodology of this m supplemental disc master.
	 *
	 * @return the actual methodology of this m supplemental disc master
	 */
	@AutoEscape
	public String getActualMethodology();

	/**
	 * Sets the actual methodology of this m supplemental disc master.
	 *
	 * @param actualMethodology the actual methodology of this m supplemental disc master
	 */
	public void setActualMethodology(String actualMethodology);

	/**
	 * Returns the actual contract price of this m supplemental disc master.
	 *
	 * @return the actual contract price of this m supplemental disc master
	 */
	public double getActualContractPrice();

	/**
	 * Sets the actual contract price of this m supplemental disc master.
	 *
	 * @param actualContractPrice the actual contract price of this m supplemental disc master
	 */
	public void setActualContractPrice(double actualContractPrice);

	/**
	 * Returns the projection details sid of this m supplemental disc master.
	 *
	 * @return the projection details sid of this m supplemental disc master
	 */
	public int getProjectionDetailsSid();

	/**
	 * Sets the projection details sid of this m supplemental disc master.
	 *
	 * @param projectionDetailsSid the projection details sid of this m supplemental disc master
	 */
	public void setProjectionDetailsSid(int projectionDetailsSid);

	/**
	 * Returns the actual discount of this m supplemental disc master.
	 *
	 * @return the actual discount of this m supplemental disc master
	 */
	public double getActualDiscount();

	/**
	 * Sets the actual discount of this m supplemental disc master.
	 *
	 * @param actualDiscount the actual discount of this m supplemental disc master
	 */
	public void setActualDiscount(double actualDiscount);

	/**
	 * Returns the check record of this m supplemental disc master.
	 *
	 * @return the check record of this m supplemental disc master
	 */
	public int getCheckRecord();

	/**
	 * Sets the check record of this m supplemental disc master.
	 *
	 * @param checkRecord the check record of this m supplemental disc master
	 */
	public void setCheckRecord(int checkRecord);

	/**
	 * Returns the contract end date of this m supplemental disc master.
	 *
	 * @return the contract end date of this m supplemental disc master
	 */
	public Date getContractEndDate();

	/**
	 * Sets the contract end date of this m supplemental disc master.
	 *
	 * @param contractEndDate the contract end date of this m supplemental disc master
	 */
	public void setContractEndDate(Date contractEndDate);

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
	public int compareTo(MSupplementalDiscMaster mSupplementalDiscMaster);

	@Override
	public int hashCode();

	@Override
	public CacheModel<MSupplementalDiscMaster> toCacheModel();

	@Override
	public MSupplementalDiscMaster toEscapedModel();

	@Override
	public MSupplementalDiscMaster toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}