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
 * Provides a wrapper for {@link BusinessroleMasterLocalService}.
 *
 * @author
 * @see BusinessroleMasterLocalService
 * @generated
 */
@ProviderType
public class BusinessroleMasterLocalServiceWrapper
	implements BusinessroleMasterLocalService,
		ServiceWrapper<BusinessroleMasterLocalService> {
	public BusinessroleMasterLocalServiceWrapper(
		BusinessroleMasterLocalService businessroleMasterLocalService) {
		_businessroleMasterLocalService = businessroleMasterLocalService;
	}

	/**
	* Adds the businessrole master to the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleMaster the businessrole master
	* @return the businessrole master that was added
	*/
	@Override
	public com.stpl.app.model.BusinessroleMaster addBusinessroleMaster(
		com.stpl.app.model.BusinessroleMaster businessroleMaster) {
		return _businessroleMasterLocalService.addBusinessroleMaster(businessroleMaster);
	}

	/**
	* Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
	*
	* @param businessroleMasterSid the primary key for the new businessrole master
	* @return the new businessrole master
	*/
	@Override
	public com.stpl.app.model.BusinessroleMaster createBusinessroleMaster(
		int businessroleMasterSid) {
		return _businessroleMasterLocalService.createBusinessroleMaster(businessroleMasterSid);
	}

	/**
	* Deletes the businessrole master from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleMaster the businessrole master
	* @return the businessrole master that was removed
	*/
	@Override
	public com.stpl.app.model.BusinessroleMaster deleteBusinessroleMaster(
		com.stpl.app.model.BusinessroleMaster businessroleMaster) {
		return _businessroleMasterLocalService.deleteBusinessroleMaster(businessroleMaster);
	}

	/**
	* Deletes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master that was removed
	* @throws PortalException if a businessrole master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BusinessroleMaster deleteBusinessroleMaster(
		int businessroleMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleMasterLocalService.deleteBusinessroleMaster(businessroleMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _businessroleMasterLocalService.dynamicQuery();
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
		return _businessroleMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _businessroleMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _businessroleMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _businessroleMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _businessroleMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.BusinessroleMaster fetchBusinessroleMaster(
		int businessroleMasterSid) {
		return _businessroleMasterLocalService.fetchBusinessroleMaster(businessroleMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _businessroleMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the businessrole master with the primary key.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master
	* @throws PortalException if a businessrole master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BusinessroleMaster getBusinessroleMaster(
		int businessroleMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleMasterLocalService.getBusinessroleMaster(businessroleMasterSid);
	}

	/**
	* Returns a range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of businessrole masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.BusinessroleMaster> getBusinessroleMasters(
		int start, int end) {
		return _businessroleMasterLocalService.getBusinessroleMasters(start, end);
	}

	/**
	* Returns the number of businessrole masters.
	*
	* @return the number of businessrole masters
	*/
	@Override
	public int getBusinessroleMastersCount() {
		return _businessroleMasterLocalService.getBusinessroleMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _businessroleMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _businessroleMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the businessrole master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param businessroleMaster the businessrole master
	* @return the businessrole master that was updated
	*/
	@Override
	public com.stpl.app.model.BusinessroleMaster updateBusinessroleMaster(
		com.stpl.app.model.BusinessroleMaster businessroleMaster) {
		return _businessroleMasterLocalService.updateBusinessroleMaster(businessroleMaster);
	}

	@Override
	public BusinessroleMasterLocalService getWrappedService() {
		return _businessroleMasterLocalService;
	}

	@Override
	public void setWrappedService(
		BusinessroleMasterLocalService businessroleMasterLocalService) {
		_businessroleMasterLocalService = businessroleMasterLocalService;
	}

	private BusinessroleMasterLocalService _businessroleMasterLocalService;
}