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
 * Provides a wrapper for {@link InventoryWdProjMasLocalService}.
 *
 * @author
 * @see InventoryWdProjMasLocalService
 * @generated
 */
@ProviderType
public class InventoryWdProjMasLocalServiceWrapper
	implements InventoryWdProjMasLocalService,
		ServiceWrapper<InventoryWdProjMasLocalService> {
	public InventoryWdProjMasLocalServiceWrapper(
		InventoryWdProjMasLocalService inventoryWdProjMasLocalService) {
		_inventoryWdProjMasLocalService = inventoryWdProjMasLocalService;
	}

	/**
	* Adds the inventory wd proj mas to the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdProjMas the inventory wd proj mas
	* @return the inventory wd proj mas that was added
	*/
	@Override
	public com.stpl.app.model.InventoryWdProjMas addInventoryWdProjMas(
		com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas) {
		return _inventoryWdProjMasLocalService.addInventoryWdProjMas(inventoryWdProjMas);
	}

	/**
	* Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
	*
	* @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
	* @return the new inventory wd proj mas
	*/
	@Override
	public com.stpl.app.model.InventoryWdProjMas createInventoryWdProjMas(
		int inventoryWdProjMasSid) {
		return _inventoryWdProjMasLocalService.createInventoryWdProjMas(inventoryWdProjMasSid);
	}

	/**
	* Deletes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas that was removed
	* @throws PortalException if a inventory wd proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.InventoryWdProjMas deleteInventoryWdProjMas(
		int inventoryWdProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdProjMasLocalService.deleteInventoryWdProjMas(inventoryWdProjMasSid);
	}

	/**
	* Deletes the inventory wd proj mas from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdProjMas the inventory wd proj mas
	* @return the inventory wd proj mas that was removed
	*/
	@Override
	public com.stpl.app.model.InventoryWdProjMas deleteInventoryWdProjMas(
		com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas) {
		return _inventoryWdProjMasLocalService.deleteInventoryWdProjMas(inventoryWdProjMas);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdProjMasLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _inventoryWdProjMasLocalService.dynamicQuery();
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
		return _inventoryWdProjMasLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _inventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _inventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
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
		return _inventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
		return _inventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.InventoryWdProjMas fetchInventoryWdProjMas(
		int inventoryWdProjMasSid) {
		return _inventoryWdProjMasLocalService.fetchInventoryWdProjMas(inventoryWdProjMasSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _inventoryWdProjMasLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _inventoryWdProjMasLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the inventory wd proj mas with the primary key.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas
	* @throws PortalException if a inventory wd proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.InventoryWdProjMas getInventoryWdProjMas(
		int inventoryWdProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdProjMasLocalService.getInventoryWdProjMas(inventoryWdProjMasSid);
	}

	/**
	* Returns a range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @return the range of inventory wd proj mases
	*/
	@Override
	public java.util.List<com.stpl.app.model.InventoryWdProjMas> getInventoryWdProjMases(
		int start, int end) {
		return _inventoryWdProjMasLocalService.getInventoryWdProjMases(start,
			end);
	}

	/**
	* Returns the number of inventory wd proj mases.
	*
	* @return the number of inventory wd proj mases
	*/
	@Override
	public int getInventoryWdProjMasesCount() {
		return _inventoryWdProjMasLocalService.getInventoryWdProjMasesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _inventoryWdProjMasLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdProjMasLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the inventory wd proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdProjMas the inventory wd proj mas
	* @return the inventory wd proj mas that was updated
	*/
	@Override
	public com.stpl.app.model.InventoryWdProjMas updateInventoryWdProjMas(
		com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas) {
		return _inventoryWdProjMasLocalService.updateInventoryWdProjMas(inventoryWdProjMas);
	}

	@Override
	public InventoryWdProjMasLocalService getWrappedService() {
		return _inventoryWdProjMasLocalService;
	}

	@Override
	public void setWrappedService(
		InventoryWdProjMasLocalService inventoryWdProjMasLocalService) {
		_inventoryWdProjMasLocalService = inventoryWdProjMasLocalService;
	}

	private InventoryWdProjMasLocalService _inventoryWdProjMasLocalService;
}