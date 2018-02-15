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
 * Provides a wrapper for {@link CompanyTradeClassLocalService}.
 *
 * @author
 * @see CompanyTradeClassLocalService
 * @generated
 */
@ProviderType
public class CompanyTradeClassLocalServiceWrapper
	implements CompanyTradeClassLocalService,
		ServiceWrapper<CompanyTradeClassLocalService> {
	public CompanyTradeClassLocalServiceWrapper(
		CompanyTradeClassLocalService companyTradeClassLocalService) {
		_companyTradeClassLocalService = companyTradeClassLocalService;
	}

	/**
	* Adds the company trade class to the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClass the company trade class
	* @return the company trade class that was added
	*/
	@Override
	public com.stpl.app.model.CompanyTradeClass addCompanyTradeClass(
		com.stpl.app.model.CompanyTradeClass companyTradeClass) {
		return _companyTradeClassLocalService.addCompanyTradeClass(companyTradeClass);
	}

	/**
	* Creates a new company trade class with the primary key. Does not add the company trade class to the database.
	*
	* @param companyTradeClassSid the primary key for the new company trade class
	* @return the new company trade class
	*/
	@Override
	public com.stpl.app.model.CompanyTradeClass createCompanyTradeClass(
		int companyTradeClassSid) {
		return _companyTradeClassLocalService.createCompanyTradeClass(companyTradeClassSid);
	}

	/**
	* Deletes the company trade class from the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClass the company trade class
	* @return the company trade class that was removed
	*/
	@Override
	public com.stpl.app.model.CompanyTradeClass deleteCompanyTradeClass(
		com.stpl.app.model.CompanyTradeClass companyTradeClass) {
		return _companyTradeClassLocalService.deleteCompanyTradeClass(companyTradeClass);
	}

	/**
	* Deletes the company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClassSid the primary key of the company trade class
	* @return the company trade class that was removed
	* @throws PortalException if a company trade class with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyTradeClass deleteCompanyTradeClass(
		int companyTradeClassSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyTradeClassLocalService.deleteCompanyTradeClass(companyTradeClassSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyTradeClassLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _companyTradeClassLocalService.dynamicQuery();
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
		return _companyTradeClassLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyTradeClassLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyTradeClassLocalService.dynamicQuery(dynamicQuery, start,
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
		return _companyTradeClassLocalService.dynamicQueryCount(dynamicQuery);
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
		return _companyTradeClassLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CompanyTradeClass fetchCompanyTradeClass(
		int companyTradeClassSid) {
		return _companyTradeClassLocalService.fetchCompanyTradeClass(companyTradeClassSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _companyTradeClassLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the company trade class with the primary key.
	*
	* @param companyTradeClassSid the primary key of the company trade class
	* @return the company trade class
	* @throws PortalException if a company trade class with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyTradeClass getCompanyTradeClass(
		int companyTradeClassSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyTradeClassLocalService.getCompanyTradeClass(companyTradeClassSid);
	}

	/**
	* Returns a range of all the company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company trade classes
	* @param end the upper bound of the range of company trade classes (not inclusive)
	* @return the range of company trade classes
	*/
	@Override
	public java.util.List<com.stpl.app.model.CompanyTradeClass> getCompanyTradeClasses(
		int start, int end) {
		return _companyTradeClassLocalService.getCompanyTradeClasses(start, end);
	}

	/**
	* Returns the number of company trade classes.
	*
	* @return the number of company trade classes
	*/
	@Override
	public int getCompanyTradeClassesCount() {
		return _companyTradeClassLocalService.getCompanyTradeClassesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _companyTradeClassLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _companyTradeClassLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyTradeClassLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the company trade class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClass the company trade class
	* @return the company trade class that was updated
	*/
	@Override
	public com.stpl.app.model.CompanyTradeClass updateCompanyTradeClass(
		com.stpl.app.model.CompanyTradeClass companyTradeClass) {
		return _companyTradeClassLocalService.updateCompanyTradeClass(companyTradeClass);
	}

	@Override
	public CompanyTradeClassLocalService getWrappedService() {
		return _companyTradeClassLocalService;
	}

	@Override
	public void setWrappedService(
		CompanyTradeClassLocalService companyTradeClassLocalService) {
		_companyTradeClassLocalService = companyTradeClassLocalService;
	}

	private CompanyTradeClassLocalService _companyTradeClassLocalService;
}