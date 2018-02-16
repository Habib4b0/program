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
 * Provides a wrapper for {@link RebatePlanTierLocalService}.
 *
 * @author
 * @see RebatePlanTierLocalService
 * @generated
 */
@ProviderType
public class RebatePlanTierLocalServiceWrapper
	implements RebatePlanTierLocalService,
		ServiceWrapper<RebatePlanTierLocalService> {
	public RebatePlanTierLocalServiceWrapper(
		RebatePlanTierLocalService rebatePlanTierLocalService) {
		_rebatePlanTierLocalService = rebatePlanTierLocalService;
	}

	/**
	* Adds the rebate plan tier to the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTier the rebate plan tier
	* @return the rebate plan tier that was added
	*/
	@Override
	public com.stpl.app.model.RebatePlanTier addRebatePlanTier(
		com.stpl.app.model.RebatePlanTier rebatePlanTier) {
		return _rebatePlanTierLocalService.addRebatePlanTier(rebatePlanTier);
	}

	/**
	* Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
	*
	* @param rebatePlanTierSid the primary key for the new rebate plan tier
	* @return the new rebate plan tier
	*/
	@Override
	public com.stpl.app.model.RebatePlanTier createRebatePlanTier(
		int rebatePlanTierSid) {
		return _rebatePlanTierLocalService.createRebatePlanTier(rebatePlanTierSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanTierLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTierSid the primary key of the rebate plan tier
	* @return the rebate plan tier that was removed
	* @throws PortalException if a rebate plan tier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
		int rebatePlanTierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanTierLocalService.deleteRebatePlanTier(rebatePlanTierSid);
	}

	/**
	* Deletes the rebate plan tier from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTier the rebate plan tier
	* @return the rebate plan tier that was removed
	*/
	@Override
	public com.stpl.app.model.RebatePlanTier deleteRebatePlanTier(
		com.stpl.app.model.RebatePlanTier rebatePlanTier) {
		return _rebatePlanTierLocalService.deleteRebatePlanTier(rebatePlanTier);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _rebatePlanTierLocalService.dynamicQuery();
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
		return _rebatePlanTierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rebatePlanTierLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _rebatePlanTierLocalService.dynamicQuery(dynamicQuery, start,
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
		return _rebatePlanTierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _rebatePlanTierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.RebatePlanTier fetchRebatePlanTier(
		int rebatePlanTierSid) {
		return _rebatePlanTierLocalService.fetchRebatePlanTier(rebatePlanTierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _rebatePlanTierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _rebatePlanTierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rebatePlanTierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanTierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the rebate plan tier with the primary key.
	*
	* @param rebatePlanTierSid the primary key of the rebate plan tier
	* @return the rebate plan tier
	* @throws PortalException if a rebate plan tier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.RebatePlanTier getRebatePlanTier(
		int rebatePlanTierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _rebatePlanTierLocalService.getRebatePlanTier(rebatePlanTierSid);
	}

	/**
	* Returns a range of all the rebate plan tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan tiers
	* @param end the upper bound of the range of rebate plan tiers (not inclusive)
	* @return the range of rebate plan tiers
	*/
	@Override
	public java.util.List<com.stpl.app.model.RebatePlanTier> getRebatePlanTiers(
		int start, int end) {
		return _rebatePlanTierLocalService.getRebatePlanTiers(start, end);
	}

	/**
	* Returns the number of rebate plan tiers.
	*
	* @return the number of rebate plan tiers
	*/
	@Override
	public int getRebatePlanTiersCount() {
		return _rebatePlanTierLocalService.getRebatePlanTiersCount();
	}

	/**
	* Updates the rebate plan tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanTier the rebate plan tier
	* @return the rebate plan tier that was updated
	*/
	@Override
	public com.stpl.app.model.RebatePlanTier updateRebatePlanTier(
		com.stpl.app.model.RebatePlanTier rebatePlanTier) {
		return _rebatePlanTierLocalService.updateRebatePlanTier(rebatePlanTier);
	}

	@Override
	public RebatePlanTierLocalService getWrappedService() {
		return _rebatePlanTierLocalService;
	}

	@Override
	public void setWrappedService(
		RebatePlanTierLocalService rebatePlanTierLocalService) {
		_rebatePlanTierLocalService = rebatePlanTierLocalService;
	}

	private RebatePlanTierLocalService _rebatePlanTierLocalService;
}