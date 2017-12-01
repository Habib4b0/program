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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CdrDetailsLocalService}.
 *
 * @author
 * @see CdrDetailsLocalService
 * @generated
 */
@ProviderType
public class CdrDetailsLocalServiceWrapper implements CdrDetailsLocalService,
	ServiceWrapper<CdrDetailsLocalService> {
	public CdrDetailsLocalServiceWrapper(
		CdrDetailsLocalService cdrDetailsLocalService) {
		_cdrDetailsLocalService = cdrDetailsLocalService;
	}

	/**
	* Adds the cdr details to the database. Also notifies the appropriate model listeners.
	*
	* @param cdrDetails the cdr details
	* @return the cdr details that was added
	*/
	@Override
	public com.stpl.app.model.CdrDetails addCdrDetails(
		com.stpl.app.model.CdrDetails cdrDetails) {
		return _cdrDetailsLocalService.addCdrDetails(cdrDetails);
	}

	/**
	* Creates a new cdr details with the primary key. Does not add the cdr details to the database.
	*
	* @param cdrDetailsSid the primary key for the new cdr details
	* @return the new cdr details
	*/
	@Override
	public com.stpl.app.model.CdrDetails createCdrDetails(int cdrDetailsSid) {
		return _cdrDetailsLocalService.createCdrDetails(cdrDetailsSid);
	}

	/**
	* Deletes the cdr details from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrDetails the cdr details
	* @return the cdr details that was removed
	*/
	@Override
	public com.stpl.app.model.CdrDetails deleteCdrDetails(
		com.stpl.app.model.CdrDetails cdrDetails) {
		return _cdrDetailsLocalService.deleteCdrDetails(cdrDetails);
	}

	/**
	* Deletes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details that was removed
	* @throws PortalException if a cdr details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CdrDetails deleteCdrDetails(int cdrDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrDetailsLocalService.deleteCdrDetails(cdrDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cdrDetailsLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _cdrDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _cdrDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _cdrDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _cdrDetailsLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _cdrDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CdrDetails fetchCdrDetails(int cdrDetailsSid) {
		return _cdrDetailsLocalService.fetchCdrDetails(cdrDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cdrDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cdr details with the primary key.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details
	* @throws PortalException if a cdr details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CdrDetails getCdrDetails(int cdrDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrDetailsLocalService.getCdrDetails(cdrDetailsSid);
	}

	/**
	* Returns a range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @return the range of cdr detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.CdrDetails> getCdrDetailses(
		int start, int end) {
		return _cdrDetailsLocalService.getCdrDetailses(start, end);
	}

	/**
	* Returns the number of cdr detailses.
	*
	* @return the number of cdr detailses
	*/
	@Override
	public int getCdrDetailsesCount() {
		return _cdrDetailsLocalService.getCdrDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cdrDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cdrDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cdr details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cdrDetails the cdr details
	* @return the cdr details that was updated
	*/
	@Override
	public com.stpl.app.model.CdrDetails updateCdrDetails(
		com.stpl.app.model.CdrDetails cdrDetails) {
		return _cdrDetailsLocalService.updateCdrDetails(cdrDetails);
	}

	@Override
	public CdrDetailsLocalService getWrappedService() {
		return _cdrDetailsLocalService;
	}

	@Override
	public void setWrappedService(CdrDetailsLocalService cdrDetailsLocalService) {
		_cdrDetailsLocalService = cdrDetailsLocalService;
	}

	private CdrDetailsLocalService _cdrDetailsLocalService;
}