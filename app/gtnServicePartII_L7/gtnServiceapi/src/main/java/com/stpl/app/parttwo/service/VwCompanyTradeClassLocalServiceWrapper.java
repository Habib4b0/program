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
 * Provides a wrapper for {@link VwCompanyTradeClassLocalService}.
 *
 * @author
 * @see VwCompanyTradeClassLocalService
 * @generated
 */
@ProviderType
public class VwCompanyTradeClassLocalServiceWrapper
	implements VwCompanyTradeClassLocalService,
		ServiceWrapper<VwCompanyTradeClassLocalService> {
	public VwCompanyTradeClassLocalServiceWrapper(
		VwCompanyTradeClassLocalService vwCompanyTradeClassLocalService) {
		_vwCompanyTradeClassLocalService = vwCompanyTradeClassLocalService;
	}

	/**
	* Adds the vw company trade class to the database. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyTradeClass the vw company trade class
	* @return the vw company trade class that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass addVwCompanyTradeClass(
		com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass) {
		return _vwCompanyTradeClassLocalService.addVwCompanyTradeClass(vwCompanyTradeClass);
	}

	/**
	* Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
	*
	* @param companyTradeClassSid the primary key for the new vw company trade class
	* @return the new vw company trade class
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass createVwCompanyTradeClass(
		int companyTradeClassSid) {
		return _vwCompanyTradeClassLocalService.createVwCompanyTradeClass(companyTradeClassSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyTradeClassLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class that was removed
	* @throws PortalException if a vw company trade class with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass deleteVwCompanyTradeClass(
		int companyTradeClassSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyTradeClassLocalService.deleteVwCompanyTradeClass(companyTradeClassSid);
	}

	/**
	* Deletes the vw company trade class from the database. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyTradeClass the vw company trade class
	* @return the vw company trade class that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass deleteVwCompanyTradeClass(
		com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass) {
		return _vwCompanyTradeClassLocalService.deleteVwCompanyTradeClass(vwCompanyTradeClass);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwCompanyTradeClassLocalService.dynamicQuery();
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
		return _vwCompanyTradeClassLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCompanyTradeClassLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwCompanyTradeClassLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _vwCompanyTradeClassLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwCompanyTradeClassLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass fetchVwCompanyTradeClass(
		int companyTradeClassSid) {
		return _vwCompanyTradeClassLocalService.fetchVwCompanyTradeClass(companyTradeClassSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwCompanyTradeClassLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwCompanyTradeClassLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwCompanyTradeClassLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyTradeClassLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw company trade class with the primary key.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class
	* @throws PortalException if a vw company trade class with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass getVwCompanyTradeClass(
		int companyTradeClassSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwCompanyTradeClassLocalService.getVwCompanyTradeClass(companyTradeClassSid);
	}

	/**
	* Returns a range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @return the range of vw company trade classes
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.VwCompanyTradeClass> getVwCompanyTradeClasses(
		int start, int end) {
		return _vwCompanyTradeClassLocalService.getVwCompanyTradeClasses(start,
			end);
	}

	/**
	* Returns the number of vw company trade classes.
	*
	* @return the number of vw company trade classes
	*/
	@Override
	public int getVwCompanyTradeClassesCount() {
		return _vwCompanyTradeClassLocalService.getVwCompanyTradeClassesCount();
	}

	/**
	* Updates the vw company trade class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwCompanyTradeClass the vw company trade class
	* @return the vw company trade class that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.VwCompanyTradeClass updateVwCompanyTradeClass(
		com.stpl.app.parttwo.model.VwCompanyTradeClass vwCompanyTradeClass) {
		return _vwCompanyTradeClassLocalService.updateVwCompanyTradeClass(vwCompanyTradeClass);
	}

	@Override
	public VwCompanyTradeClassLocalService getWrappedService() {
		return _vwCompanyTradeClassLocalService;
	}

	@Override
	public void setWrappedService(
		VwCompanyTradeClassLocalService vwCompanyTradeClassLocalService) {
		_vwCompanyTradeClassLocalService = vwCompanyTradeClassLocalService;
	}

	private VwCompanyTradeClassLocalService _vwCompanyTradeClassLocalService;
}