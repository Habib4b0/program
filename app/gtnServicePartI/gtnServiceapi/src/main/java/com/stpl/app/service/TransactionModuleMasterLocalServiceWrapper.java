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
 * Provides a wrapper for {@link TransactionModuleMasterLocalService}.
 *
 * @author
 * @see TransactionModuleMasterLocalService
 * @generated
 */
@ProviderType
public class TransactionModuleMasterLocalServiceWrapper
	implements TransactionModuleMasterLocalService,
		ServiceWrapper<TransactionModuleMasterLocalService> {
	public TransactionModuleMasterLocalServiceWrapper(
		TransactionModuleMasterLocalService transactionModuleMasterLocalService) {
		_transactionModuleMasterLocalService = transactionModuleMasterLocalService;
	}

	/**
	* Adds the transaction module master to the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleMaster the transaction module master
	* @return the transaction module master that was added
	*/
	@Override
	public com.stpl.app.model.TransactionModuleMaster addTransactionModuleMaster(
		com.stpl.app.model.TransactionModuleMaster transactionModuleMaster) {
		return _transactionModuleMasterLocalService.addTransactionModuleMaster(transactionModuleMaster);
	}

	/**
	* Creates a new transaction module master with the primary key. Does not add the transaction module master to the database.
	*
	* @param transactionModuleMasterSid the primary key for the new transaction module master
	* @return the new transaction module master
	*/
	@Override
	public com.stpl.app.model.TransactionModuleMaster createTransactionModuleMaster(
		int transactionModuleMasterSid) {
		return _transactionModuleMasterLocalService.createTransactionModuleMaster(transactionModuleMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleMasterLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleMasterSid the primary key of the transaction module master
	* @return the transaction module master that was removed
	* @throws PortalException if a transaction module master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.TransactionModuleMaster deleteTransactionModuleMaster(
		int transactionModuleMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleMasterLocalService.deleteTransactionModuleMaster(transactionModuleMasterSid);
	}

	/**
	* Deletes the transaction module master from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleMaster the transaction module master
	* @return the transaction module master that was removed
	*/
	@Override
	public com.stpl.app.model.TransactionModuleMaster deleteTransactionModuleMaster(
		com.stpl.app.model.TransactionModuleMaster transactionModuleMaster) {
		return _transactionModuleMasterLocalService.deleteTransactionModuleMaster(transactionModuleMaster);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _transactionModuleMasterLocalService.dynamicQuery();
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
		return _transactionModuleMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _transactionModuleMasterLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _transactionModuleMasterLocalService.dynamicQuery(dynamicQuery,
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
		return _transactionModuleMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _transactionModuleMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.TransactionModuleMaster fetchTransactionModuleMaster(
		int transactionModuleMasterSid) {
		return _transactionModuleMasterLocalService.fetchTransactionModuleMaster(transactionModuleMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _transactionModuleMasterLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _transactionModuleMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _transactionModuleMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the transaction module master with the primary key.
	*
	* @param transactionModuleMasterSid the primary key of the transaction module master
	* @return the transaction module master
	* @throws PortalException if a transaction module master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.TransactionModuleMaster getTransactionModuleMaster(
		int transactionModuleMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleMasterLocalService.getTransactionModuleMaster(transactionModuleMasterSid);
	}

	/**
	* Returns a range of all the transaction module masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module masters
	* @param end the upper bound of the range of transaction module masters (not inclusive)
	* @return the range of transaction module masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.TransactionModuleMaster> getTransactionModuleMasters(
		int start, int end) {
		return _transactionModuleMasterLocalService.getTransactionModuleMasters(start,
			end);
	}

	/**
	* Returns the number of transaction module masters.
	*
	* @return the number of transaction module masters
	*/
	@Override
	public int getTransactionModuleMastersCount() {
		return _transactionModuleMasterLocalService.getTransactionModuleMastersCount();
	}

	/**
	* Updates the transaction module master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleMaster the transaction module master
	* @return the transaction module master that was updated
	*/
	@Override
	public com.stpl.app.model.TransactionModuleMaster updateTransactionModuleMaster(
		com.stpl.app.model.TransactionModuleMaster transactionModuleMaster) {
		return _transactionModuleMasterLocalService.updateTransactionModuleMaster(transactionModuleMaster);
	}

	@Override
	public TransactionModuleMasterLocalService getWrappedService() {
		return _transactionModuleMasterLocalService;
	}

	@Override
	public void setWrappedService(
		TransactionModuleMasterLocalService transactionModuleMasterLocalService) {
		_transactionModuleMasterLocalService = transactionModuleMasterLocalService;
	}

	private TransactionModuleMasterLocalService _transactionModuleMasterLocalService;
}