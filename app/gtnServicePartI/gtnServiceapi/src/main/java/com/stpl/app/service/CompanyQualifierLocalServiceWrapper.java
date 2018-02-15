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
 * Provides a wrapper for {@link CompanyQualifierLocalService}.
 *
 * @author
 * @see CompanyQualifierLocalService
 * @generated
 */
@ProviderType
public class CompanyQualifierLocalServiceWrapper
	implements CompanyQualifierLocalService,
		ServiceWrapper<CompanyQualifierLocalService> {
	public CompanyQualifierLocalServiceWrapper(
		CompanyQualifierLocalService companyQualifierLocalService) {
		_companyQualifierLocalService = companyQualifierLocalService;
	}

	/**
	* Adds the company qualifier to the database. Also notifies the appropriate model listeners.
	*
	* @param companyQualifier the company qualifier
	* @return the company qualifier that was added
	*/
	@Override
	public com.stpl.app.model.CompanyQualifier addCompanyQualifier(
		com.stpl.app.model.CompanyQualifier companyQualifier) {
		return _companyQualifierLocalService.addCompanyQualifier(companyQualifier);
	}

	/**
	* Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
	*
	* @param companyQualifierSid the primary key for the new company qualifier
	* @return the new company qualifier
	*/
	@Override
	public com.stpl.app.model.CompanyQualifier createCompanyQualifier(
		int companyQualifierSid) {
		return _companyQualifierLocalService.createCompanyQualifier(companyQualifierSid);
	}

	/**
	* Deletes the company qualifier from the database. Also notifies the appropriate model listeners.
	*
	* @param companyQualifier the company qualifier
	* @return the company qualifier that was removed
	*/
	@Override
	public com.stpl.app.model.CompanyQualifier deleteCompanyQualifier(
		com.stpl.app.model.CompanyQualifier companyQualifier) {
		return _companyQualifierLocalService.deleteCompanyQualifier(companyQualifier);
	}

	/**
	* Deletes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier that was removed
	* @throws PortalException if a company qualifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyQualifier deleteCompanyQualifier(
		int companyQualifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyQualifierLocalService.deleteCompanyQualifier(companyQualifierSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyQualifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _companyQualifierLocalService.dynamicQuery();
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
		return _companyQualifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyQualifierLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyQualifierLocalService.dynamicQuery(dynamicQuery, start,
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
		return _companyQualifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _companyQualifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CompanyQualifier fetchCompanyQualifier(
		int companyQualifierSid) {
		return _companyQualifierLocalService.fetchCompanyQualifier(companyQualifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _companyQualifierLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the company qualifier with the primary key.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier
	* @throws PortalException if a company qualifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyQualifier getCompanyQualifier(
		int companyQualifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyQualifierLocalService.getCompanyQualifier(companyQualifierSid);
	}

	/**
	* Returns a range of all the company qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @return the range of company qualifiers
	*/
	@Override
	public java.util.List<com.stpl.app.model.CompanyQualifier> getCompanyQualifiers(
		int start, int end) {
		return _companyQualifierLocalService.getCompanyQualifiers(start, end);
	}

	/**
	* Returns the number of company qualifiers.
	*
	* @return the number of company qualifiers
	*/
	@Override
	public int getCompanyQualifiersCount() {
		return _companyQualifierLocalService.getCompanyQualifiersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _companyQualifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _companyQualifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyQualifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the company qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param companyQualifier the company qualifier
	* @return the company qualifier that was updated
	*/
	@Override
	public com.stpl.app.model.CompanyQualifier updateCompanyQualifier(
		com.stpl.app.model.CompanyQualifier companyQualifier) {
		return _companyQualifierLocalService.updateCompanyQualifier(companyQualifier);
	}

	@Override
	public CompanyQualifierLocalService getWrappedService() {
		return _companyQualifierLocalService;
	}

	@Override
	public void setWrappedService(
		CompanyQualifierLocalService companyQualifierLocalService) {
		_companyQualifierLocalService = companyQualifierLocalService;
	}

	private CompanyQualifierLocalService _companyQualifierLocalService;
}