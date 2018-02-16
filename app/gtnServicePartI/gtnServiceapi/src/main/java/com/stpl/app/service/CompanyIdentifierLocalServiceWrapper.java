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
 * Provides a wrapper for {@link CompanyIdentifierLocalService}.
 *
 * @author
 * @see CompanyIdentifierLocalService
 * @generated
 */
@ProviderType
public class CompanyIdentifierLocalServiceWrapper
	implements CompanyIdentifierLocalService,
		ServiceWrapper<CompanyIdentifierLocalService> {
	public CompanyIdentifierLocalServiceWrapper(
		CompanyIdentifierLocalService companyIdentifierLocalService) {
		_companyIdentifierLocalService = companyIdentifierLocalService;
	}

	/**
	* Adds the company identifier to the database. Also notifies the appropriate model listeners.
	*
	* @param companyIdentifier the company identifier
	* @return the company identifier that was added
	*/
	@Override
	public com.stpl.app.model.CompanyIdentifier addCompanyIdentifier(
		com.stpl.app.model.CompanyIdentifier companyIdentifier) {
		return _companyIdentifierLocalService.addCompanyIdentifier(companyIdentifier);
	}

	/**
	* Creates a new company identifier with the primary key. Does not add the company identifier to the database.
	*
	* @param companyStringIdentifierSid the primary key for the new company identifier
	* @return the new company identifier
	*/
	@Override
	public com.stpl.app.model.CompanyIdentifier createCompanyIdentifier(
		int companyStringIdentifierSid) {
		return _companyIdentifierLocalService.createCompanyIdentifier(companyStringIdentifierSid);
	}

	/**
	* Deletes the company identifier from the database. Also notifies the appropriate model listeners.
	*
	* @param companyIdentifier the company identifier
	* @return the company identifier that was removed
	*/
	@Override
	public com.stpl.app.model.CompanyIdentifier deleteCompanyIdentifier(
		com.stpl.app.model.CompanyIdentifier companyIdentifier) {
		return _companyIdentifierLocalService.deleteCompanyIdentifier(companyIdentifier);
	}

	/**
	* Deletes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier that was removed
	* @throws PortalException if a company identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyIdentifier deleteCompanyIdentifier(
		int companyStringIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyIdentifierLocalService.deleteCompanyIdentifier(companyStringIdentifierSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyIdentifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _companyIdentifierLocalService.dynamicQuery();
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
		return _companyIdentifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyIdentifierLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _companyIdentifierLocalService.dynamicQuery(dynamicQuery, start,
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
		return _companyIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _companyIdentifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CompanyIdentifier fetchCompanyIdentifier(
		int companyStringIdentifierSid) {
		return _companyIdentifierLocalService.fetchCompanyIdentifier(companyStringIdentifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _companyIdentifierLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the company identifier with the primary key.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier
	* @throws PortalException if a company identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CompanyIdentifier getCompanyIdentifier(
		int companyStringIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyIdentifierLocalService.getCompanyIdentifier(companyStringIdentifierSid);
	}

	/**
	* Returns a range of all the company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @return the range of company identifiers
	*/
	@Override
	public java.util.List<com.stpl.app.model.CompanyIdentifier> getCompanyIdentifiers(
		int start, int end) {
		return _companyIdentifierLocalService.getCompanyIdentifiers(start, end);
	}

	/**
	* Returns the number of company identifiers.
	*
	* @return the number of company identifiers
	*/
	@Override
	public int getCompanyIdentifiersCount() {
		return _companyIdentifierLocalService.getCompanyIdentifiersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _companyIdentifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _companyIdentifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _companyIdentifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the company identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param companyIdentifier the company identifier
	* @return the company identifier that was updated
	*/
	@Override
	public com.stpl.app.model.CompanyIdentifier updateCompanyIdentifier(
		com.stpl.app.model.CompanyIdentifier companyIdentifier) {
		return _companyIdentifierLocalService.updateCompanyIdentifier(companyIdentifier);
	}

	@Override
	public CompanyIdentifierLocalService getWrappedService() {
		return _companyIdentifierLocalService;
	}

	@Override
	public void setWrappedService(
		CompanyIdentifierLocalService companyIdentifierLocalService) {
		_companyIdentifierLocalService = companyIdentifierLocalService;
	}

	private CompanyIdentifierLocalService _companyIdentifierLocalService;
}