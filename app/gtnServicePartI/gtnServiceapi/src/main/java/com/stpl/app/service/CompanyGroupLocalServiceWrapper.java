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
 * Provides a wrapper for {@link CompanyGroupLocalService}.
 *
 * @author
 * @see CompanyGroupLocalService
 * @generated
 */
@ProviderType
public class CompanyGroupLocalServiceWrapper implements CompanyGroupLocalService,
	ServiceWrapper<CompanyGroupLocalService> {
	public CompanyGroupLocalServiceWrapper(
		CompanyGroupLocalService companyGroupLocalService) {
		_companyGroupLocalService = companyGroupLocalService;
	}

	/**
	* Adds the company group to the database. Also notifies the appropriate model listeners.
	*
	* @param companyGroup the company group
	* @return the company group that was added
	*/
	@Override
	public com.stpl.app.model.CompanyGroup addCompanyGroup(
		com.stpl.app.model.CompanyGroup companyGroup) {
		return _companyGroupLocalService.addCompanyGroup(companyGroup);
	}

	/**
	* Creates a new company group with the primary key. Does not add the company group to the database.
	*
	* @param companyGroupSid the primary key for the new company group
	* @return the new company group
	*/
	@Override
	public com.stpl.app.model.CompanyGroup createCompanyGroup(
		int companyGroupSid) {
		return _companyGroupLocalService.createCompanyGroup(companyGroupSid);
	}

	/**
	* Deletes the company group from the database. Also notifies the appropriate model listeners.
	*
	* @param companyGroup the company group
	* @return the company group that was removed
	*/
	@Override
	public com.stpl.app.model.CompanyGroup deleteCompanyGroup(
		com.stpl.app.model.CompanyGroup companyGroup) {
		return _companyGroupLocalService.deleteCompanyGroup(companyGroup);
	}

	/**
	* Deletes the company group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyGroupSid the primary key of the company group
	* @return the company group that was removed
	* @throws PortalException if a company group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyGroup deleteCompanyGroup(
		int companyGroupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyGroupLocalService.deleteCompanyGroup(companyGroupSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _companyGroupLocalService.dynamicQuery();
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
		return _companyGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyGroupLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _companyGroupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _companyGroupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CompanyGroup fetchCompanyGroup(
		int companyGroupSid) {
		return _companyGroupLocalService.fetchCompanyGroup(companyGroupSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _companyGroupLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the company group with the primary key.
	*
	* @param companyGroupSid the primary key of the company group
	* @return the company group
	* @throws PortalException if a company group with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyGroup getCompanyGroup(int companyGroupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyGroupLocalService.getCompanyGroup(companyGroupSid);
	}

	/**
	* Returns a range of all the company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company groups
	* @param end the upper bound of the range of company groups (not inclusive)
	* @return the range of company groups
	*/
	@Override
	public java.util.List<com.stpl.app.model.CompanyGroup> getCompanyGroups(
		int start, int end) {
		return _companyGroupLocalService.getCompanyGroups(start, end);
	}

	/**
	* Returns the number of company groups.
	*
	* @return the number of company groups
	*/
	@Override
	public int getCompanyGroupsCount() {
		return _companyGroupLocalService.getCompanyGroupsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _companyGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _companyGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the company group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param companyGroup the company group
	* @return the company group that was updated
	*/
	@Override
	public com.stpl.app.model.CompanyGroup updateCompanyGroup(
		com.stpl.app.model.CompanyGroup companyGroup) {
		return _companyGroupLocalService.updateCompanyGroup(companyGroup);
	}

	@Override
	public CompanyGroupLocalService getWrappedService() {
		return _companyGroupLocalService;
	}

	@Override
	public void setWrappedService(
		CompanyGroupLocalService companyGroupLocalService) {
		_companyGroupLocalService = companyGroupLocalService;
	}

	private CompanyGroupLocalService _companyGroupLocalService;
}