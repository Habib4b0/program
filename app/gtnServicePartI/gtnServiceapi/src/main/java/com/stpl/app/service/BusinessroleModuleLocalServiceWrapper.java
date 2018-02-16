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
 * Provides a wrapper for {@link BusinessroleModuleLocalService}.
 *
 * @author
 * @see BusinessroleModuleLocalService
 * @generated
 */
@ProviderType
public class BusinessroleModuleLocalServiceWrapper
	implements BusinessroleModuleLocalService,
		ServiceWrapper<BusinessroleModuleLocalService> {
	public BusinessroleModuleLocalServiceWrapper(
		BusinessroleModuleLocalService businessroleModuleLocalService) {
		_businessroleModuleLocalService = businessroleModuleLocalService;
	}

	/**
	* Adds the businessrole module to the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleModule the businessrole module
	* @return the businessrole module that was added
	*/
	@Override
	public com.stpl.app.model.BusinessroleModule addBusinessroleModule(
		com.stpl.app.model.BusinessroleModule businessroleModule) {
		return _businessroleModuleLocalService.addBusinessroleModule(businessroleModule);
	}

	/**
	* Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
	*
	* @param businessroleModuleSid the primary key for the new businessrole module
	* @return the new businessrole module
	*/
	@Override
	public com.stpl.app.model.BusinessroleModule createBusinessroleModule(
		int businessroleModuleSid) {
		return _businessroleModuleLocalService.createBusinessroleModule(businessroleModuleSid);
	}

	/**
	* Deletes the businessrole module from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleModule the businessrole module
	* @return the businessrole module that was removed
	*/
	@Override
	public com.stpl.app.model.BusinessroleModule deleteBusinessroleModule(
		com.stpl.app.model.BusinessroleModule businessroleModule) {
		return _businessroleModuleLocalService.deleteBusinessroleModule(businessroleModule);
	}

	/**
	* Deletes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module that was removed
	* @throws PortalException if a businessrole module with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BusinessroleModule deleteBusinessroleModule(
		int businessroleModuleSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleModuleLocalService.deleteBusinessroleModule(businessroleModuleSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleModuleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _businessroleModuleLocalService.dynamicQuery();
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
		return _businessroleModuleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _businessroleModuleLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _businessroleModuleLocalService.dynamicQuery(dynamicQuery,
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
		return _businessroleModuleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _businessroleModuleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.BusinessroleModule fetchBusinessroleModule(
		int businessroleModuleSid) {
		return _businessroleModuleLocalService.fetchBusinessroleModule(businessroleModuleSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _businessroleModuleLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the businessrole module with the primary key.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module
	* @throws PortalException if a businessrole module with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BusinessroleModule getBusinessroleModule(
		int businessroleModuleSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleModuleLocalService.getBusinessroleModule(businessroleModuleSid);
	}

	/**
	* Returns a range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @return the range of businessrole modules
	*/
	@Override
	public java.util.List<com.stpl.app.model.BusinessroleModule> getBusinessroleModules(
		int start, int end) {
		return _businessroleModuleLocalService.getBusinessroleModules(start, end);
	}

	/**
	* Returns the number of businessrole modules.
	*
	* @return the number of businessrole modules
	*/
	@Override
	public int getBusinessroleModulesCount() {
		return _businessroleModuleLocalService.getBusinessroleModulesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _businessroleModuleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _businessroleModuleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _businessroleModuleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the businessrole module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param businessroleModule the businessrole module
	* @return the businessrole module that was updated
	*/
	@Override
	public com.stpl.app.model.BusinessroleModule updateBusinessroleModule(
		com.stpl.app.model.BusinessroleModule businessroleModule) {
		return _businessroleModuleLocalService.updateBusinessroleModule(businessroleModule);
	}

	@Override
	public BusinessroleModuleLocalService getWrappedService() {
		return _businessroleModuleLocalService;
	}

	@Override
	public void setWrappedService(
		BusinessroleModuleLocalService businessroleModuleLocalService) {
		_businessroleModuleLocalService = businessroleModuleLocalService;
	}

	private BusinessroleModuleLocalService _businessroleModuleLocalService;
}