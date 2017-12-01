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
 * Provides a wrapper for {@link MParityLookupLocalService}.
 *
 * @author
 * @see MParityLookupLocalService
 * @generated
 */
@ProviderType
public class MParityLookupLocalServiceWrapper
	implements MParityLookupLocalService,
		ServiceWrapper<MParityLookupLocalService> {
	public MParityLookupLocalServiceWrapper(
		MParityLookupLocalService mParityLookupLocalService) {
		_mParityLookupLocalService = mParityLookupLocalService;
	}

	/**
	* Adds the m parity lookup to the database. Also notifies the appropriate model listeners.
	*
	* @param mParityLookup the m parity lookup
	* @return the m parity lookup that was added
	*/
	@Override
	public com.stpl.app.model.MParityLookup addMParityLookup(
		com.stpl.app.model.MParityLookup mParityLookup) {
		return _mParityLookupLocalService.addMParityLookup(mParityLookup);
	}

	/**
	* Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
	*
	* @param mParityLookupSid the primary key for the new m parity lookup
	* @return the new m parity lookup
	*/
	@Override
	public com.stpl.app.model.MParityLookup createMParityLookup(
		int mParityLookupSid) {
		return _mParityLookupLocalService.createMParityLookup(mParityLookupSid);
	}

	/**
	* Deletes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup that was removed
	* @throws PortalException if a m parity lookup with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MParityLookup deleteMParityLookup(
		int mParityLookupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mParityLookupLocalService.deleteMParityLookup(mParityLookupSid);
	}

	/**
	* Deletes the m parity lookup from the database. Also notifies the appropriate model listeners.
	*
	* @param mParityLookup the m parity lookup
	* @return the m parity lookup that was removed
	*/
	@Override
	public com.stpl.app.model.MParityLookup deleteMParityLookup(
		com.stpl.app.model.MParityLookup mParityLookup) {
		return _mParityLookupLocalService.deleteMParityLookup(mParityLookup);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mParityLookupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mParityLookupLocalService.dynamicQuery();
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
		return _mParityLookupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mParityLookupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mParityLookupLocalService.dynamicQuery(dynamicQuery, start,
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
		return _mParityLookupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mParityLookupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MParityLookup fetchMParityLookup(
		int mParityLookupSid) {
		return _mParityLookupLocalService.fetchMParityLookup(mParityLookupSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mParityLookupLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mParityLookupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m parity lookup with the primary key.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup
	* @throws PortalException if a m parity lookup with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MParityLookup getMParityLookup(
		int mParityLookupSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mParityLookupLocalService.getMParityLookup(mParityLookupSid);
	}

	/**
	* Returns a range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @return the range of m parity lookups
	*/
	@Override
	public java.util.List<com.stpl.app.model.MParityLookup> getMParityLookups(
		int start, int end) {
		return _mParityLookupLocalService.getMParityLookups(start, end);
	}

	/**
	* Returns the number of m parity lookups.
	*
	* @return the number of m parity lookups
	*/
	@Override
	public int getMParityLookupsCount() {
		return _mParityLookupLocalService.getMParityLookupsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mParityLookupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mParityLookupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the m parity lookup in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mParityLookup the m parity lookup
	* @return the m parity lookup that was updated
	*/
	@Override
	public com.stpl.app.model.MParityLookup updateMParityLookup(
		com.stpl.app.model.MParityLookup mParityLookup) {
		return _mParityLookupLocalService.updateMParityLookup(mParityLookup);
	}

	@Override
	public MParityLookupLocalService getWrappedService() {
		return _mParityLookupLocalService;
	}

	@Override
	public void setWrappedService(
		MParityLookupLocalService mParityLookupLocalService) {
		_mParityLookupLocalService = mParityLookupLocalService;
	}

	private MParityLookupLocalService _mParityLookupLocalService;
}