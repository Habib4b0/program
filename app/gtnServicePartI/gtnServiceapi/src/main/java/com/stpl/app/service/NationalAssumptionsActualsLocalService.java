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

import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPK;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for NationalAssumptionsActuals. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see NationalAssumptionsActualsLocalServiceUtil
 * @see com.stpl.app.service.base.NationalAssumptionsActualsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NationalAssumptionsActualsLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface NationalAssumptionsActualsLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NationalAssumptionsActualsLocalServiceUtil} to access the national assumptions actuals local service. Add custom service methods to {@link com.stpl.app.service.impl.NationalAssumptionsActualsLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the national assumptions actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public NationalAssumptionsActuals addNationalAssumptionsActuals(
		NationalAssumptionsActuals nationalAssumptionsActuals);

	/**
	* Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
	*
	* @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
	* @return the new national assumptions actuals
	*/
	public NationalAssumptionsActuals createNationalAssumptionsActuals(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK);

	/**
	* Deletes the national assumptions actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public NationalAssumptionsActuals deleteNationalAssumptionsActuals(
		NationalAssumptionsActuals nationalAssumptionsActuals);

	/**
	* Deletes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals that was removed
	* @throws PortalException if a national assumptions actuals with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public NationalAssumptionsActuals deleteNationalAssumptionsActuals(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public NationalAssumptionsActuals fetchNationalAssumptionsActuals(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the national assumptions actuals with the primary key.
	*
	* @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	* @return the national assumptions actuals
	* @throws PortalException if a national assumptions actuals with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NationalAssumptionsActuals getNationalAssumptionsActuals(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws PortalException;

	/**
	* Returns a range of all the national assumptions actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions actualses
	* @param end the upper bound of the range of national assumptions actualses (not inclusive)
	* @return the range of national assumptions actualses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NationalAssumptionsActuals> getNationalAssumptionsActualses(
		int start, int end);

	/**
	* Returns the number of national assumptions actualses.
	*
	* @return the number of national assumptions actualses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNationalAssumptionsActualsesCount();

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
	* Updates the national assumptions actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsActuals the national assumptions actuals
	* @return the national assumptions actuals that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public NationalAssumptionsActuals updateNationalAssumptionsActuals(
		NationalAssumptionsActuals nationalAssumptionsActuals);
}