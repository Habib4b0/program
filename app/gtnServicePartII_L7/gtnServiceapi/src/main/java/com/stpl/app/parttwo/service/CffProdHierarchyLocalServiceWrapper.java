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
 * Provides a wrapper for {@link CffProdHierarchyLocalService}.
 *
 * @author
 * @see CffProdHierarchyLocalService
 * @generated
 */
@ProviderType
public class CffProdHierarchyLocalServiceWrapper
	implements CffProdHierarchyLocalService,
		ServiceWrapper<CffProdHierarchyLocalService> {
	public CffProdHierarchyLocalServiceWrapper(
		CffProdHierarchyLocalService cffProdHierarchyLocalService) {
		_cffProdHierarchyLocalService = cffProdHierarchyLocalService;
	}

	/**
	* Adds the cff prod hierarchy to the database. Also notifies the appropriate model listeners.
	*
	* @param cffProdHierarchy the cff prod hierarchy
	* @return the cff prod hierarchy that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy addCffProdHierarchy(
		com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy) {
		return _cffProdHierarchyLocalService.addCffProdHierarchy(cffProdHierarchy);
	}

	/**
	* Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
	*
	* @param cffProdHierarchySid the primary key for the new cff prod hierarchy
	* @return the new cff prod hierarchy
	*/
	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy createCffProdHierarchy(
		int cffProdHierarchySid) {
		return _cffProdHierarchyLocalService.createCffProdHierarchy(cffProdHierarchySid);
	}

	/**
	* Deletes the cff prod hierarchy from the database. Also notifies the appropriate model listeners.
	*
	* @param cffProdHierarchy the cff prod hierarchy
	* @return the cff prod hierarchy that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy deleteCffProdHierarchy(
		com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy) {
		return _cffProdHierarchyLocalService.deleteCffProdHierarchy(cffProdHierarchy);
	}

	/**
	* Deletes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy that was removed
	* @throws PortalException if a cff prod hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy deleteCffProdHierarchy(
		int cffProdHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffProdHierarchyLocalService.deleteCffProdHierarchy(cffProdHierarchySid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffProdHierarchyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cffProdHierarchyLocalService.dynamicQuery();
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
		return _cffProdHierarchyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffProdHierarchyLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cffProdHierarchyLocalService.dynamicQuery(dynamicQuery, start,
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
		return _cffProdHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cffProdHierarchyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy fetchCffProdHierarchy(
		int cffProdHierarchySid) {
		return _cffProdHierarchyLocalService.fetchCffProdHierarchy(cffProdHierarchySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cffProdHierarchyLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns a range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @return the range of cff prod hierarchies
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> getCffProdHierarchies(
		int start, int end) {
		return _cffProdHierarchyLocalService.getCffProdHierarchies(start, end);
	}

	/**
	* Returns the number of cff prod hierarchies.
	*
	* @return the number of cff prod hierarchies
	*/
	@Override
	public int getCffProdHierarchiesCount() {
		return _cffProdHierarchyLocalService.getCffProdHierarchiesCount();
	}

	/**
	* Returns the cff prod hierarchy with the primary key.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy
	* @throws PortalException if a cff prod hierarchy with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy getCffProdHierarchy(
		int cffProdHierarchySid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffProdHierarchyLocalService.getCffProdHierarchy(cffProdHierarchySid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cffProdHierarchyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cffProdHierarchyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cffProdHierarchyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cff prod hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cffProdHierarchy the cff prod hierarchy
	* @return the cff prod hierarchy that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.CffProdHierarchy updateCffProdHierarchy(
		com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy) {
		return _cffProdHierarchyLocalService.updateCffProdHierarchy(cffProdHierarchy);
	}

	@Override
	public CffProdHierarchyLocalService getWrappedService() {
		return _cffProdHierarchyLocalService;
	}

	@Override
	public void setWrappedService(
		CffProdHierarchyLocalService cffProdHierarchyLocalService) {
		_cffProdHierarchyLocalService = cffProdHierarchyLocalService;
	}

	private CffProdHierarchyLocalService _cffProdHierarchyLocalService;
}