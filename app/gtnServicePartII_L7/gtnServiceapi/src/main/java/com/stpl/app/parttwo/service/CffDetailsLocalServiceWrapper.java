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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CffDetailsLocalService}.
 *
 * @author
 * @see CffDetailsLocalService
 * @generated
 */
@ProviderType
public class CffDetailsLocalServiceWrapper implements CffDetailsLocalService,
	ServiceWrapper<CffDetailsLocalService> {
	public CffDetailsLocalServiceWrapper(
		CffDetailsLocalService cffDetailsLocalService) {
		_cffDetailsLocalService = cffDetailsLocalService;
	}

	/**
	* Adds the cff details to the database. Also notifies the appropriate model listeners.
	*
	* @param cffDetails the cff details
	* @return the cff details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffDetails addCffDetails(
		com.stpl.app.parttwo.model.CffDetails cffDetails) {
		return _cffDetailsLocalService.addCffDetails(cffDetails);
	}

	/**
	* Creates a new cff details with the primary key. Does not add the cff details to the database.
	*
	* @param cffDetailsSid the primary key for the new cff details
	* @return the new cff details
	*/
	@Override
	public com.stpl.app.parttwo.model.CffDetails createCffDetails(
		int cffDetailsSid) {
		return _cffDetailsLocalService.createCffDetails(cffDetailsSid);
	}

	/**
	* Deletes the cff details from the database. Also notifies the appropriate model listeners.
	*
	* @param cffDetails the cff details
	* @return the cff details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffDetails deleteCffDetails(
		com.stpl.app.parttwo.model.CffDetails cffDetails) {
		return _cffDetailsLocalService.deleteCffDetails(cffDetails);
	}

	/**
	* Deletes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffDetailsSid the primary key of the cff details
	* @return the cff details that was removed
	* @throws PortalException if a cff details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffDetails deleteCffDetails(
		int cffDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffDetailsLocalService.deleteCffDetails(cffDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffDetailsLocalService.dynamicQuery();
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
		return _cffDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _cffDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffDetails fetchCffDetails(
		int cffDetailsSid) {
		return _cffDetailsLocalService.fetchCffDetails(cffDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cff details with the primary key.
	*
	* @param cffDetailsSid the primary key of the cff details
	* @return the cff details
	* @throws PortalException if a cff details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffDetails getCffDetails(
		int cffDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffDetailsLocalService.getCffDetails(cffDetailsSid);
	}

	/**
	* Returns a range of all the cff detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff detailses
	* @param end the upper bound of the range of cff detailses (not inclusive)
	* @return the range of cff detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffDetails> getCffDetailses(
		int start, int end) {
		return _cffDetailsLocalService.getCffDetailses(start, end);
	}

	/**
	* Returns the number of cff detailses.
	*
	* @return the number of cff detailses
	*/
	@Override
	public int getCffDetailsesCount() {
		return _cffDetailsLocalService.getCffDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffDetails the cff details
	* @return the cff details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffDetails updateCffDetails(
		com.stpl.app.parttwo.model.CffDetails cffDetails) {
		return _cffDetailsLocalService.updateCffDetails(cffDetails);
	}

	@Override
	public CffDetailsLocalService getWrappedService() {
		return _cffDetailsLocalService;
	}

	@Override
	public void setWrappedService(CffDetailsLocalService cffDetailsLocalService) {
		_cffDetailsLocalService = cffDetailsLocalService;
	}

	private CffDetailsLocalService _cffDetailsLocalService;
}