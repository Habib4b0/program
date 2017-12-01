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
 * Provides a wrapper for {@link TransactionModuleDetailsLocalService}.
 *
 * @author
 * @see TransactionModuleDetailsLocalService
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsLocalServiceWrapper
	implements TransactionModuleDetailsLocalService,
		ServiceWrapper<TransactionModuleDetailsLocalService> {
	public TransactionModuleDetailsLocalServiceWrapper(
		TransactionModuleDetailsLocalService transactionModuleDetailsLocalService) {
		_transactionModuleDetailsLocalService = transactionModuleDetailsLocalService;
	}

	/**
	* Adds the transaction module details to the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetails the transaction module details
	* @return the transaction module details that was added
	*/
	@Override
	public com.stpl.app.model.TransactionModuleDetails addTransactionModuleDetails(
		com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
		return _transactionModuleDetailsLocalService.addTransactionModuleDetails(transactionModuleDetails);
	}

	/**
	* Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
	*
	* @param transactionModuleDetailsSid the primary key for the new transaction module details
	* @return the new transaction module details
	*/
	@Override
	public com.stpl.app.model.TransactionModuleDetails createTransactionModuleDetails(
		int transactionModuleDetailsSid) {
		return _transactionModuleDetailsLocalService.createTransactionModuleDetails(transactionModuleDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details that was removed
	* @throws PortalException if a transaction module details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.TransactionModuleDetails deleteTransactionModuleDetails(
		int transactionModuleDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleDetailsLocalService.deleteTransactionModuleDetails(transactionModuleDetailsSid);
	}

	/**
	* Deletes the transaction module details from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetails the transaction module details
	* @return the transaction module details that was removed
	*/
	@Override
	public com.stpl.app.model.TransactionModuleDetails deleteTransactionModuleDetails(
		com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
		return _transactionModuleDetailsLocalService.deleteTransactionModuleDetails(transactionModuleDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _transactionModuleDetailsLocalService.dynamicQuery();
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
		return _transactionModuleDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _transactionModuleDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _transactionModuleDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _transactionModuleDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _transactionModuleDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.TransactionModuleDetails fetchTransactionModuleDetails(
		int transactionModuleDetailsSid) {
		return _transactionModuleDetailsLocalService.fetchTransactionModuleDetails(transactionModuleDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _transactionModuleDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _transactionModuleDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _transactionModuleDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the transaction module details with the primary key.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details
	* @throws PortalException if a transaction module details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.TransactionModuleDetails getTransactionModuleDetails(
		int transactionModuleDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _transactionModuleDetailsLocalService.getTransactionModuleDetails(transactionModuleDetailsSid);
	}

	/**
	* Returns a range of all the transaction module detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module detailses
	* @param end the upper bound of the range of transaction module detailses (not inclusive)
	* @return the range of transaction module detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.TransactionModuleDetails> getTransactionModuleDetailses(
		int start, int end) {
		return _transactionModuleDetailsLocalService.getTransactionModuleDetailses(start,
			end);
	}

	/**
	* Returns the number of transaction module detailses.
	*
	* @return the number of transaction module detailses
	*/
	@Override
	public int getTransactionModuleDetailsesCount() {
		return _transactionModuleDetailsLocalService.getTransactionModuleDetailsesCount();
	}

	/**
	* Updates the transaction module details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetails the transaction module details
	* @return the transaction module details that was updated
	*/
	@Override
	public com.stpl.app.model.TransactionModuleDetails updateTransactionModuleDetails(
		com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
		return _transactionModuleDetailsLocalService.updateTransactionModuleDetails(transactionModuleDetails);
	}

	@Override
	public TransactionModuleDetailsLocalService getWrappedService() {
		return _transactionModuleDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		TransactionModuleDetailsLocalService transactionModuleDetailsLocalService) {
		_transactionModuleDetailsLocalService = transactionModuleDetailsLocalService;
	}

	private TransactionModuleDetailsLocalService _transactionModuleDetailsLocalService;
}