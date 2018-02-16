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

package com.stpl.app.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.StChProjectionDiscount;
import com.stpl.app.service.persistence.StChProjectionDiscountPK;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for StChProjectionDiscount. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see StChProjectionDiscountLocalServiceUtil
 * @see com.stpl.app.service.base.StChProjectionDiscountLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.StChProjectionDiscountLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface StChProjectionDiscountLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StChProjectionDiscountLocalServiceUtil} to access the st ch projection discount local service. Add custom service methods to {@link com.stpl.app.service.impl.StChProjectionDiscountLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the st ch projection discount to the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscount the st ch projection discount
	* @return the st ch projection discount that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StChProjectionDiscount addStChProjectionDiscount(
		StChProjectionDiscount stChProjectionDiscount);

	/**
	* Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
	*
	* @param stChProjectionDiscountPK the primary key for the new st ch projection discount
	* @return the new st ch projection discount
	*/
	public StChProjectionDiscount createStChProjectionDiscount(
		StChProjectionDiscountPK stChProjectionDiscountPK);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the st ch projection discount from the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscount the st ch projection discount
	* @return the st ch projection discount that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public StChProjectionDiscount deleteStChProjectionDiscount(
		StChProjectionDiscount stChProjectionDiscount);

	/**
	* Deletes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount that was removed
	* @throws PortalException if a st ch projection discount with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public StChProjectionDiscount deleteStChProjectionDiscount(
		StChProjectionDiscountPK stChProjectionDiscountPK)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StChProjectionDiscount fetchStChProjectionDiscount(
		StChProjectionDiscountPK stChProjectionDiscountPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the st ch projection discount with the primary key.
	*
	* @param stChProjectionDiscountPK the primary key of the st ch projection discount
	* @return the st ch projection discount
	* @throws PortalException if a st ch projection discount with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StChProjectionDiscount getStChProjectionDiscount(
		StChProjectionDiscountPK stChProjectionDiscountPK)
		throws PortalException;

	/**
	* Returns a range of all the st ch projection discounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch projection discounts
	* @param end the upper bound of the range of st ch projection discounts (not inclusive)
	* @return the range of st ch projection discounts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StChProjectionDiscount> getStChProjectionDiscounts(int start,
		int end);

	/**
	* Returns the number of st ch projection discounts.
	*
	* @return the number of st ch projection discounts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getStChProjectionDiscountsCount();

	/**
	* Updates the st ch projection discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stChProjectionDiscount the st ch projection discount
	* @return the st ch projection discount that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StChProjectionDiscount updateStChProjectionDiscount(
		StChProjectionDiscount stChProjectionDiscount);
}