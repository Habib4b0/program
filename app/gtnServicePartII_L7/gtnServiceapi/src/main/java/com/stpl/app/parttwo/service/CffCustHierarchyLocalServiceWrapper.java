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
 * Provides a wrapper for {@link CffCustHierarchyLocalService}.
 *
 * @author
 * @see CffCustHierarchyLocalService
 * @generated
 */
@ProviderType
public class CffCustHierarchyLocalServiceWrapper
	implements CffCustHierarchyLocalService,
		ServiceWrapper<CffCustHierarchyLocalService> {
	public CffCustHierarchyLocalServiceWrapper(
		CffCustHierarchyLocalService cffCustHierarchyLocalService) {
		_cffCustHierarchyLocalService = cffCustHierarchyLocalService;
	}

	/**
	* Adds the cff cust hierarchy to the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustHierarchy the cff cust hierarchy
	* @return the cff cust hierarchy that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy addCffCustHierarchy(
		com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy) {
		return _cffCustHierarchyLocalService.addCffCustHierarchy(cffCustHierarchy);
	}

	/**
	* Creates a new cff cust hierarchy with the primary key. Does not add the cff cust hierarchy to the database.
	*
	* @param cffCustHierarchySid the primary key for the new cff cust hierarchy
	* @return the new cff cust hierarchy
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy createCffCustHierarchy(
		int cffCustHierarchySid) {
		return _cffCustHierarchyLocalService.createCffCustHierarchy(cffCustHierarchySid);
	}

	/**
	* Deletes the cff cust hierarchy from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustHierarchy the cff cust hierarchy
	* @return the cff cust hierarchy that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy deleteCffCustHierarchy(
		com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy) {
		return _cffCustHierarchyLocalService.deleteCffCustHierarchy(cffCustHierarchy);
	}

	/**
	* Deletes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy that was removed
	* @throws PortalException if a cff cust hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy deleteCffCustHierarchy(
		int cffCustHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustHierarchyLocalService.deleteCffCustHierarchy(cffCustHierarchySid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustHierarchyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffCustHierarchyLocalService.dynamicQuery();
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
		return _cffCustHierarchyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffCustHierarchyLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffCustHierarchyLocalService.dynamicQuery(dynamicQuery, start,
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
		return _cffCustHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffCustHierarchyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy fetchCffCustHierarchy(
		int cffCustHierarchySid) {
		return _cffCustHierarchyLocalService.fetchCffCustHierarchy(cffCustHierarchySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffCustHierarchyLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @return the range of cff cust hierarchies
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffCustHierarchy> getCffCustHierarchies(
		int start, int end) {
		return _cffCustHierarchyLocalService.getCffCustHierarchies(start, end);
	}

	/**
	* Returns the number of cff cust hierarchies.
	*
	* @return the number of cff cust hierarchies
	*/
	@Override
	public int getCffCustHierarchiesCount() {
		return _cffCustHierarchyLocalService.getCffCustHierarchiesCount();
	}

	/**
	* Returns the cff cust hierarchy with the primary key.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy
	* @throws PortalException if a cff cust hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy getCffCustHierarchy(
		int cffCustHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustHierarchyLocalService.getCffCustHierarchy(cffCustHierarchySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffCustHierarchyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffCustHierarchyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffCustHierarchyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff cust hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffCustHierarchy the cff cust hierarchy
	* @return the cff cust hierarchy that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffCustHierarchy updateCffCustHierarchy(
		com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy) {
		return _cffCustHierarchyLocalService.updateCffCustHierarchy(cffCustHierarchy);
	}

	@Override
	public CffCustHierarchyLocalService getWrappedService() {
		return _cffCustHierarchyLocalService;
	}

	@Override
	public void setWrappedService(
		CffCustHierarchyLocalService cffCustHierarchyLocalService) {
		_cffCustHierarchyLocalService = cffCustHierarchyLocalService;
	}

	private CffCustHierarchyLocalService _cffCustHierarchyLocalService;
}