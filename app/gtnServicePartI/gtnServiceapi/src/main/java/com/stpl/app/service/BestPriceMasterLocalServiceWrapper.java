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
 * Provides a wrapper for {@link BestPriceMasterLocalService}.
 *
 * @author
 * @see BestPriceMasterLocalService
 * @generated
 */
@ProviderType
public class BestPriceMasterLocalServiceWrapper
	implements BestPriceMasterLocalService,
		ServiceWrapper<BestPriceMasterLocalService> {
	public BestPriceMasterLocalServiceWrapper(
		BestPriceMasterLocalService bestPriceMasterLocalService) {
		_bestPriceMasterLocalService = bestPriceMasterLocalService;
	}

	/**
	* Adds the best price master to the database. Also notifies the appropriate model listeners.
	*
	* @param bestPriceMaster the best price master
	* @return the best price master that was added
	*/
	@Override
	public com.stpl.app.model.BestPriceMaster addBestPriceMaster(
		com.stpl.app.model.BestPriceMaster bestPriceMaster) {
		return _bestPriceMasterLocalService.addBestPriceMaster(bestPriceMaster);
	}

	/**
	* Creates a new best price master with the primary key. Does not add the best price master to the database.
	*
	* @param bestPriceMasterSid the primary key for the new best price master
	* @return the new best price master
	*/
	@Override
	public com.stpl.app.model.BestPriceMaster createBestPriceMaster(
		int bestPriceMasterSid) {
		return _bestPriceMasterLocalService.createBestPriceMaster(bestPriceMasterSid);
	}

	/**
	* Deletes the best price master from the database. Also notifies the appropriate model listeners.
	*
	* @param bestPriceMaster the best price master
	* @return the best price master that was removed
	*/
	@Override
	public com.stpl.app.model.BestPriceMaster deleteBestPriceMaster(
		com.stpl.app.model.BestPriceMaster bestPriceMaster) {
		return _bestPriceMasterLocalService.deleteBestPriceMaster(bestPriceMaster);
	}

	/**
	* Deletes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bestPriceMasterSid the primary key of the best price master
	* @return the best price master that was removed
	* @throws PortalException if a best price master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BestPriceMaster deleteBestPriceMaster(
		int bestPriceMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bestPriceMasterLocalService.deleteBestPriceMaster(bestPriceMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bestPriceMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bestPriceMasterLocalService.dynamicQuery();
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
		return _bestPriceMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _bestPriceMasterLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _bestPriceMasterLocalService.dynamicQuery(dynamicQuery, start,
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
		return _bestPriceMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _bestPriceMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.BestPriceMaster fetchBestPriceMaster(
		int bestPriceMasterSid) {
		return _bestPriceMasterLocalService.fetchBestPriceMaster(bestPriceMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _bestPriceMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the best price master with the primary key.
	*
	* @param bestPriceMasterSid the primary key of the best price master
	* @return the best price master
	* @throws PortalException if a best price master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BestPriceMaster getBestPriceMaster(
		int bestPriceMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bestPriceMasterLocalService.getBestPriceMaster(bestPriceMasterSid);
	}

	/**
	* Returns a range of all the best price masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of best price masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.BestPriceMaster> getBestPriceMasters(
		int start, int end) {
		return _bestPriceMasterLocalService.getBestPriceMasters(start, end);
	}

	/**
	* Returns the number of best price masters.
	*
	* @return the number of best price masters
	*/
	@Override
	public int getBestPriceMastersCount() {
		return _bestPriceMasterLocalService.getBestPriceMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _bestPriceMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _bestPriceMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bestPriceMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the best price master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bestPriceMaster the best price master
	* @return the best price master that was updated
	*/
	@Override
	public com.stpl.app.model.BestPriceMaster updateBestPriceMaster(
		com.stpl.app.model.BestPriceMaster bestPriceMaster) {
		return _bestPriceMasterLocalService.updateBestPriceMaster(bestPriceMaster);
	}

	@Override
	public BestPriceMasterLocalService getWrappedService() {
		return _bestPriceMasterLocalService;
	}

	@Override
	public void setWrappedService(
		BestPriceMasterLocalService bestPriceMasterLocalService) {
		_bestPriceMasterLocalService = bestPriceMasterLocalService;
	}

	private BestPriceMasterLocalService _bestPriceMasterLocalService;
}