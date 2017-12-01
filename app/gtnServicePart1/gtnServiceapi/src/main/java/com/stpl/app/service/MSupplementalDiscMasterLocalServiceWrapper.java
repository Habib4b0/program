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
 * Provides a wrapper for {@link MSupplementalDiscMasterLocalService}.
 *
 * @author
 * @see MSupplementalDiscMasterLocalService
 * @generated
 */
@ProviderType
public class MSupplementalDiscMasterLocalServiceWrapper
	implements MSupplementalDiscMasterLocalService,
		ServiceWrapper<MSupplementalDiscMasterLocalService> {
	public MSupplementalDiscMasterLocalServiceWrapper(
		MSupplementalDiscMasterLocalService mSupplementalDiscMasterLocalService) {
		_mSupplementalDiscMasterLocalService = mSupplementalDiscMasterLocalService;
	}

	/**
	* Adds the m supplemental disc master to the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscMaster the m supplemental disc master
	* @return the m supplemental disc master that was added
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscMaster addMSupplementalDiscMaster(
		com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster) {
		return _mSupplementalDiscMasterLocalService.addMSupplementalDiscMaster(mSupplementalDiscMaster);
	}

	/**
	* Creates a new m supplemental disc master with the primary key. Does not add the m supplemental disc master to the database.
	*
	* @param projectionDetailsSid the primary key for the new m supplemental disc master
	* @return the new m supplemental disc master
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscMaster createMSupplementalDiscMaster(
		int projectionDetailsSid) {
		return _mSupplementalDiscMasterLocalService.createMSupplementalDiscMaster(projectionDetailsSid);
	}

	/**
	* Deletes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc master
	* @return the m supplemental disc master that was removed
	* @throws PortalException if a m supplemental disc master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscMaster deleteMSupplementalDiscMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscMasterLocalService.deleteMSupplementalDiscMaster(projectionDetailsSid);
	}

	/**
	* Deletes the m supplemental disc master from the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscMaster the m supplemental disc master
	* @return the m supplemental disc master that was removed
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscMaster deleteMSupplementalDiscMaster(
		com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster) {
		return _mSupplementalDiscMasterLocalService.deleteMSupplementalDiscMaster(mSupplementalDiscMaster);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mSupplementalDiscMasterLocalService.dynamicQuery();
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
		return _mSupplementalDiscMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mSupplementalDiscMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mSupplementalDiscMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _mSupplementalDiscMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mSupplementalDiscMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MSupplementalDiscMaster fetchMSupplementalDiscMaster(
		int projectionDetailsSid) {
		return _mSupplementalDiscMasterLocalService.fetchMSupplementalDiscMaster(projectionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mSupplementalDiscMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mSupplementalDiscMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m supplemental disc master with the primary key.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc master
	* @return the m supplemental disc master
	* @throws PortalException if a m supplemental disc master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscMaster getMSupplementalDiscMaster(
		int projectionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscMasterLocalService.getMSupplementalDiscMaster(projectionDetailsSid);
	}

	/**
	* Returns a range of all the m supplemental disc masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc masters
	* @param end the upper bound of the range of m supplemental disc masters (not inclusive)
	* @return the range of m supplemental disc masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.MSupplementalDiscMaster> getMSupplementalDiscMasters(
		int start, int end) {
		return _mSupplementalDiscMasterLocalService.getMSupplementalDiscMasters(start,
			end);
	}

	/**
	* Returns the number of m supplemental disc masters.
	*
	* @return the number of m supplemental disc masters
	*/
	@Override
	public int getMSupplementalDiscMastersCount() {
		return _mSupplementalDiscMasterLocalService.getMSupplementalDiscMastersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mSupplementalDiscMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mSupplementalDiscMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the m supplemental disc master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscMaster the m supplemental disc master
	* @return the m supplemental disc master that was updated
	*/
	@Override
	public com.stpl.app.model.MSupplementalDiscMaster updateMSupplementalDiscMaster(
		com.stpl.app.model.MSupplementalDiscMaster mSupplementalDiscMaster) {
		return _mSupplementalDiscMasterLocalService.updateMSupplementalDiscMaster(mSupplementalDiscMaster);
	}

	@Override
	public MSupplementalDiscMasterLocalService getWrappedService() {
		return _mSupplementalDiscMasterLocalService;
	}

	@Override
	public void setWrappedService(
		MSupplementalDiscMasterLocalService mSupplementalDiscMasterLocalService) {
		_mSupplementalDiscMasterLocalService = mSupplementalDiscMasterLocalService;
	}

	private MSupplementalDiscMasterLocalService _mSupplementalDiscMasterLocalService;
}