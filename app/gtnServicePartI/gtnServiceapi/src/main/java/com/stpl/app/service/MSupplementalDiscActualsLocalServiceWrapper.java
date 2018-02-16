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
 * Provides a wrapper for {@link MSupplementalDiscActualsLocalService}.
 *
 * @author
 * @see MSupplementalDiscActualsLocalService
 * @generated
 */
@ProviderType
public class MSupplementalDiscActualsLocalServiceWrapper
	implements MSupplementalDiscActualsLocalService,
		ServiceWrapper<MSupplementalDiscActualsLocalService> {
	public MSupplementalDiscActualsLocalServiceWrapper(
		MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService) {
		_mSupplementalDiscActualsLocalService = mSupplementalDiscActualsLocalService;
	}

	/**
	* Adds the m supplemental disc actuals to the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscActuals the m supplemental disc actuals
	* @return the m supplemental disc actuals that was added
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscActuals addMSupplementalDiscActuals(
		com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals) {
		return _mSupplementalDiscActualsLocalService.addMSupplementalDiscActuals(mSupplementalDiscActuals);
	}

	/**
	* Creates a new m supplemental disc actuals with the primary key. Does not add the m supplemental disc actuals to the database.
	*
	* @param mSupplementalDiscActualsPK the primary key for the new m supplemental disc actuals
	* @return the new m supplemental disc actuals
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscActuals createMSupplementalDiscActuals(
		com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
		return _mSupplementalDiscActualsLocalService.createMSupplementalDiscActuals(mSupplementalDiscActualsPK);
	}

	/**
	* Deletes the m supplemental disc actuals from the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscActuals the m supplemental disc actuals
	* @return the m supplemental disc actuals that was removed
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscActuals deleteMSupplementalDiscActuals(
		com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals) {
		return _mSupplementalDiscActualsLocalService.deleteMSupplementalDiscActuals(mSupplementalDiscActuals);
	}

	/**
	* Deletes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals that was removed
	* @throws PortalException if a m supplemental disc actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscActuals deleteMSupplementalDiscActuals(
		com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscActualsLocalService.deleteMSupplementalDiscActuals(mSupplementalDiscActualsPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscActualsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mSupplementalDiscActualsLocalService.dynamicQuery();
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
		return _mSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery,
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
		return _mSupplementalDiscActualsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mSupplementalDiscActualsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MSupplementalDiscActuals fetchMSupplementalDiscActuals(
		com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
		return _mSupplementalDiscActualsLocalService.fetchMSupplementalDiscActuals(mSupplementalDiscActualsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mSupplementalDiscActualsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mSupplementalDiscActualsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m supplemental disc actuals with the primary key.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals
	* @throws PortalException if a m supplemental disc actuals with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscActuals getMSupplementalDiscActuals(
		com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscActualsLocalService.getMSupplementalDiscActuals(mSupplementalDiscActualsPK);
	}

	/**
	* Returns a range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @return the range of m supplemental disc actualses
	*/
	@Override
	public java.util.List<com.stpl.app.model.MSupplementalDiscActuals> getMSupplementalDiscActualses(
		int start, int end) {
		return _mSupplementalDiscActualsLocalService.getMSupplementalDiscActualses(start,
			end);
	}

	/**
	* Returns the number of m supplemental disc actualses.
	*
	* @return the number of m supplemental disc actualses
	*/
	@Override
	public int getMSupplementalDiscActualsesCount() {
		return _mSupplementalDiscActualsLocalService.getMSupplementalDiscActualsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mSupplementalDiscActualsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscActualsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the m supplemental disc actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscActuals the m supplemental disc actuals
	* @return the m supplemental disc actuals that was updated
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscActuals updateMSupplementalDiscActuals(
		com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals) {
		return _mSupplementalDiscActualsLocalService.updateMSupplementalDiscActuals(mSupplementalDiscActuals);
	}

	@Override
	public MSupplementalDiscActualsLocalService getWrappedService() {
		return _mSupplementalDiscActualsLocalService;
	}

	@Override
	public void setWrappedService(
		MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService) {
		_mSupplementalDiscActualsLocalService = mSupplementalDiscActualsLocalService;
	}

	private MSupplementalDiscActualsLocalService _mSupplementalDiscActualsLocalService;
}