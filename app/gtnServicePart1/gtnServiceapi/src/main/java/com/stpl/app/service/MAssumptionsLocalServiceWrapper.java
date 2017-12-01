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
 * Provides a wrapper for {@link MAssumptionsLocalService}.
 *
 * @author
 * @see MAssumptionsLocalService
 * @generated
 */
@ProviderType
public class MAssumptionsLocalServiceWrapper implements MAssumptionsLocalService,
	ServiceWrapper<MAssumptionsLocalService> {
	public MAssumptionsLocalServiceWrapper(
		MAssumptionsLocalService mAssumptionsLocalService) {
		_mAssumptionsLocalService = mAssumptionsLocalService;
	}

	/**
	* Adds the m assumptions to the database. Also notifies the appropriate model listeners.
	*
	* @param mAssumptions the m assumptions
	* @return the m assumptions that was added
	*/
	@Override
	public com.stpl.app.model.MAssumptions addMAssumptions(
		com.stpl.app.model.MAssumptions mAssumptions) {
		return _mAssumptionsLocalService.addMAssumptions(mAssumptions);
	}

	/**
	* Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
	*
	* @param mAssumptionsSid the primary key for the new m assumptions
	* @return the new m assumptions
	*/
	@Override
	public com.stpl.app.model.MAssumptions createMAssumptions(
		int mAssumptionsSid) {
		return _mAssumptionsLocalService.createMAssumptions(mAssumptionsSid);
	}

	/**
	* Deletes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mAssumptionsSid the primary key of the m assumptions
	* @return the m assumptions that was removed
	* @throws PortalException if a m assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MAssumptions deleteMAssumptions(
		int mAssumptionsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mAssumptionsLocalService.deleteMAssumptions(mAssumptionsSid);
	}

	/**
	* Deletes the m assumptions from the database. Also notifies the appropriate model listeners.
	*
	* @param mAssumptions the m assumptions
	* @return the m assumptions that was removed
	*/
	@Override
	public com.stpl.app.model.MAssumptions deleteMAssumptions(
		com.stpl.app.model.MAssumptions mAssumptions) {
		return _mAssumptionsLocalService.deleteMAssumptions(mAssumptions);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mAssumptionsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mAssumptionsLocalService.dynamicQuery();
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
		return _mAssumptionsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mAssumptionsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mAssumptionsLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _mAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MAssumptions fetchMAssumptions(
		int mAssumptionsSid) {
		return _mAssumptionsLocalService.fetchMAssumptions(mAssumptionsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mAssumptionsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mAssumptionsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m assumptions with the primary key.
	*
	* @param mAssumptionsSid the primary key of the m assumptions
	* @return the m assumptions
	* @throws PortalException if a m assumptions with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MAssumptions getMAssumptions(int mAssumptionsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mAssumptionsLocalService.getMAssumptions(mAssumptionsSid);
	}

	/**
	* Returns a range of all the m assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m assumptionses
	* @param end the upper bound of the range of m assumptionses (not inclusive)
	* @return the range of m assumptionses
	*/
	@Override
	public java.util.List<com.stpl.app.model.MAssumptions> getMAssumptionses(
		int start, int end) {
		return _mAssumptionsLocalService.getMAssumptionses(start, end);
	}

	/**
	* Returns the number of m assumptionses.
	*
	* @return the number of m assumptionses
	*/
	@Override
	public int getMAssumptionsesCount() {
		return _mAssumptionsLocalService.getMAssumptionsesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mAssumptionsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mAssumptionsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the m assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mAssumptions the m assumptions
	* @return the m assumptions that was updated
	*/
	@Override
	public com.stpl.app.model.MAssumptions updateMAssumptions(
		com.stpl.app.model.MAssumptions mAssumptions) {
		return _mAssumptionsLocalService.updateMAssumptions(mAssumptions);
	}

	@Override
	public MAssumptionsLocalService getWrappedService() {
		return _mAssumptionsLocalService;
	}

	@Override
	public void setWrappedService(
		MAssumptionsLocalService mAssumptionsLocalService) {
		_mAssumptionsLocalService = mAssumptionsLocalService;
	}

	private MAssumptionsLocalService _mAssumptionsLocalService;
}