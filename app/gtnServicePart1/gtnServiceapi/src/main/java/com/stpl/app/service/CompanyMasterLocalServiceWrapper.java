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
 * Provides a wrapper for {@link CompanyMasterLocalService}.
 *
 * @author
 * @see CompanyMasterLocalService
 * @generated
 */
@ProviderType
public class CompanyMasterLocalServiceWrapper
	implements CompanyMasterLocalService,
		ServiceWrapper<CompanyMasterLocalService> {
	public CompanyMasterLocalServiceWrapper(
		CompanyMasterLocalService companyMasterLocalService) {
		_companyMasterLocalService = companyMasterLocalService;
	}

	/**
	* Adds the company master to the database. Also notifies the appropriate model listeners.
	*
	* @param companyMaster the company master
	* @return the company master that was added
	*/
	@Override
	public com.stpl.app.model.CompanyMaster addCompanyMaster(
		com.stpl.app.model.CompanyMaster companyMaster) {
		return _companyMasterLocalService.addCompanyMaster(companyMaster);
	}

	/**
	* Creates a new company master with the primary key. Does not add the company master to the database.
	*
	* @param companyMasterSid the primary key for the new company master
	* @return the new company master
	*/
	@Override
	public com.stpl.app.model.CompanyMaster createCompanyMaster(
		int companyMasterSid) {
		return _companyMasterLocalService.createCompanyMaster(companyMasterSid);
	}

	/**
	* Deletes the company master from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMaster the company master
	* @return the company master that was removed
	*/
	@Override
	public com.stpl.app.model.CompanyMaster deleteCompanyMaster(
		com.stpl.app.model.CompanyMaster companyMaster) {
		return _companyMasterLocalService.deleteCompanyMaster(companyMaster);
	}

	/**
	* Deletes the company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master that was removed
	* @throws PortalException if a company master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyMaster deleteCompanyMaster(
		int companyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyMasterLocalService.deleteCompanyMaster(companyMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _companyMasterLocalService.dynamicQuery();
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
		return _companyMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyMasterLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _companyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _companyMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CompanyMaster fetchCompanyMaster(
		int companyMasterSid) {
		return _companyMasterLocalService.fetchCompanyMaster(companyMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _companyMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the company master with the primary key.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master
	* @throws PortalException if a company master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyMaster getCompanyMaster(
		int companyMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyMasterLocalService.getCompanyMaster(companyMasterSid);
	}

	/**
	* Returns a range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of company masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.CompanyMaster> getCompanyMasters(
		int start, int end) {
		return _companyMasterLocalService.getCompanyMasters(start, end);
	}

	/**
	* Returns the number of company masters.
	*
	* @return the number of company masters
	*/
	@Override
	public int getCompanyMastersCount() {
		return _companyMasterLocalService.getCompanyMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _companyMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _companyMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param companyMaster the company master
	* @return the company master that was updated
	*/
	@Override
	public com.stpl.app.model.CompanyMaster updateCompanyMaster(
		com.stpl.app.model.CompanyMaster companyMaster) {
		return _companyMasterLocalService.updateCompanyMaster(companyMaster);
	}

	@Override
	public CompanyMasterLocalService getWrappedService() {
		return _companyMasterLocalService;
	}

	@Override
	public void setWrappedService(
		CompanyMasterLocalService companyMasterLocalService) {
		_companyMasterLocalService = companyMasterLocalService;
	}

	private CompanyMasterLocalService _companyMasterLocalService;
}