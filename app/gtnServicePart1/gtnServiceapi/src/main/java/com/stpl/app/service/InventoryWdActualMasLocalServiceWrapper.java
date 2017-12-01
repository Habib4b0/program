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
 * Provides a wrapper for {@link InventoryWdActualMasLocalService}.
 *
 * @author
 * @see InventoryWdActualMasLocalService
 * @generated
 */
@ProviderType
public class InventoryWdActualMasLocalServiceWrapper
	implements InventoryWdActualMasLocalService,
		ServiceWrapper<InventoryWdActualMasLocalService> {
	public InventoryWdActualMasLocalServiceWrapper(
		InventoryWdActualMasLocalService inventoryWdActualMasLocalService) {
		_inventoryWdActualMasLocalService = inventoryWdActualMasLocalService;
	}

	/**
	* Adds the inventory wd actual mas to the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualMas the inventory wd actual mas
	* @return the inventory wd actual mas that was added
	*/
	@Override
	public com.stpl.app.model.InventoryWdActualMas addInventoryWdActualMas(
		com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas) {
		return _inventoryWdActualMasLocalService.addInventoryWdActualMas(inventoryWdActualMas);
	}

	/**
	* Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
	*
	* @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
	* @return the new inventory wd actual mas
	*/
	@Override
	public com.stpl.app.model.InventoryWdActualMas createInventoryWdActualMas(
		int inventoryWdActualMasSid) {
		return _inventoryWdActualMasLocalService.createInventoryWdActualMas(inventoryWdActualMasSid);
	}

	/**
	* Deletes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas that was removed
	* @throws PortalException if a inventory wd actual mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.InventoryWdActualMas deleteInventoryWdActualMas(
		int inventoryWdActualMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdActualMasLocalService.deleteInventoryWdActualMas(inventoryWdActualMasSid);
	}

	/**
	* Deletes the inventory wd actual mas from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualMas the inventory wd actual mas
	* @return the inventory wd actual mas that was removed
	*/
	@Override
	public com.stpl.app.model.InventoryWdActualMas deleteInventoryWdActualMas(
		com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas) {
		return _inventoryWdActualMasLocalService.deleteInventoryWdActualMas(inventoryWdActualMas);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdActualMasLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _inventoryWdActualMasLocalService.dynamicQuery();
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
		return _inventoryWdActualMasLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _inventoryWdActualMasLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _inventoryWdActualMasLocalService.dynamicQuery(dynamicQuery,
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
		return _inventoryWdActualMasLocalService.dynamicQueryCount(dynamicQuery);
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
		return _inventoryWdActualMasLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.InventoryWdActualMas fetchInventoryWdActualMas(
		int inventoryWdActualMasSid) {
		return _inventoryWdActualMasLocalService.fetchInventoryWdActualMas(inventoryWdActualMasSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _inventoryWdActualMasLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _inventoryWdActualMasLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the inventory wd actual mas with the primary key.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas
	* @throws PortalException if a inventory wd actual mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.InventoryWdActualMas getInventoryWdActualMas(
		int inventoryWdActualMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdActualMasLocalService.getInventoryWdActualMas(inventoryWdActualMasSid);
	}

	/**
	* Returns a range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @return the range of inventory wd actual mases
	*/
	@Override
	public java.util.List<com.stpl.app.model.InventoryWdActualMas> getInventoryWdActualMases(
		int start, int end) {
		return _inventoryWdActualMasLocalService.getInventoryWdActualMases(start,
			end);
	}

	/**
	* Returns the number of inventory wd actual mases.
	*
	* @return the number of inventory wd actual mases
	*/
	@Override
	public int getInventoryWdActualMasesCount() {
		return _inventoryWdActualMasLocalService.getInventoryWdActualMasesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _inventoryWdActualMasLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _inventoryWdActualMasLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the inventory wd actual mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualMas the inventory wd actual mas
	* @return the inventory wd actual mas that was updated
	*/
	@Override
	public com.stpl.app.model.InventoryWdActualMas updateInventoryWdActualMas(
		com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas) {
		return _inventoryWdActualMasLocalService.updateInventoryWdActualMas(inventoryWdActualMas);
	}

	@Override
	public InventoryWdActualMasLocalService getWrappedService() {
		return _inventoryWdActualMasLocalService;
	}

	@Override
	public void setWrappedService(
		InventoryWdActualMasLocalService inventoryWdActualMasLocalService) {
		_inventoryWdActualMasLocalService = inventoryWdActualMasLocalService;
	}

	private InventoryWdActualMasLocalService _inventoryWdActualMasLocalService;
}