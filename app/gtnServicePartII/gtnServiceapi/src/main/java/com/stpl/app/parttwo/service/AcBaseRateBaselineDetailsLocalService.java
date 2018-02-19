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

package com.stpl.app.parttwo.service;

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

import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for AcBaseRateBaselineDetails. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see AcBaseRateBaselineDetailsLocalServiceUtil
 * @see com.stpl.app.parttwo.service.base.AcBaseRateBaselineDetailsLocalServiceBaseImpl
 * @see com.stpl.app.parttwo.service.impl.AcBaseRateBaselineDetailsLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AcBaseRateBaselineDetailsLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AcBaseRateBaselineDetailsLocalServiceUtil} to access the ac base rate baseline details local service. Add custom service methods to {@link com.stpl.app.parttwo.service.impl.AcBaseRateBaselineDetailsLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the ac base rate baseline details to the database. Also notifies the appropriate model listeners.
	*
	* @param acBaseRateBaselineDetails the ac base rate baseline details
	* @return the ac base rate baseline details that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AcBaseRateBaselineDetails addAcBaseRateBaselineDetails(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails);

	/**
	* Creates a new ac base rate baseline details with the primary key. Does not add the ac base rate baseline details to the database.
	*
	* @param acBrMethodologyDetailsSid the primary key for the new ac base rate baseline details
	* @return the new ac base rate baseline details
	*/
	public AcBaseRateBaselineDetails createAcBaseRateBaselineDetails(
		int acBrMethodologyDetailsSid);

	/**
	* Deletes the ac base rate baseline details from the database. Also notifies the appropriate model listeners.
	*
	* @param acBaseRateBaselineDetails the ac base rate baseline details
	* @return the ac base rate baseline details that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AcBaseRateBaselineDetails deleteAcBaseRateBaselineDetails(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails);

	/**
	* Deletes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
	* @return the ac base rate baseline details that was removed
	* @throws PortalException if a ac base rate baseline details with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AcBaseRateBaselineDetails deleteAcBaseRateBaselineDetails(
		int acBrMethodologyDetailsSid) throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public AcBaseRateBaselineDetails fetchAcBaseRateBaselineDetails(
		int acBrMethodologyDetailsSid);

	/**
	* Returns the ac base rate baseline details with the primary key.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
	* @return the ac base rate baseline details
	* @throws PortalException if a ac base rate baseline details with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AcBaseRateBaselineDetails getAcBaseRateBaselineDetails(
		int acBrMethodologyDetailsSid) throws PortalException;

	/**
	* Returns a range of all the ac base rate baseline detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac base rate baseline detailses
	* @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
	* @return the range of ac base rate baseline detailses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AcBaseRateBaselineDetails> getAcBaseRateBaselineDetailses(
		int start, int end);

	/**
	* Returns the number of ac base rate baseline detailses.
	*
	* @return the number of ac base rate baseline detailses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAcBaseRateBaselineDetailsesCount();

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
	* Updates the ac base rate baseline details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param acBaseRateBaselineDetails the ac base rate baseline details
	* @return the ac base rate baseline details that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AcBaseRateBaselineDetails updateAcBaseRateBaselineDetails(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails);
}