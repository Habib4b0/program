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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TransactionModuleDetails. This utility wraps
 * {@link com.stpl.app.service.impl.TransactionModuleDetailsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see TransactionModuleDetailsLocalService
 * @see com.stpl.app.service.base.TransactionModuleDetailsLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.TransactionModuleDetailsLocalServiceImpl
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.TransactionModuleDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the transaction module details to the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetails the transaction module details
	* @return the transaction module details that was added
	*/
	public static com.stpl.app.model.TransactionModuleDetails addTransactionModuleDetails(
		com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
		return getService().addTransactionModuleDetails(transactionModuleDetails);
	}

	/**
	* Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
	*
	* @param transactionModuleDetailsSid the primary key for the new transaction module details
	* @return the new transaction module details
	*/
	public static com.stpl.app.model.TransactionModuleDetails createTransactionModuleDetails(
		int transactionModuleDetailsSid) {
		return getService()
				   .createTransactionModuleDetails(transactionModuleDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details that was removed
	* @throws PortalException if a transaction module details with the primary key could not be found
	*/
	public static com.stpl.app.model.TransactionModuleDetails deleteTransactionModuleDetails(
		int transactionModuleDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteTransactionModuleDetails(transactionModuleDetailsSid);
	}

	/**
	* Deletes the transaction module details from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetails the transaction module details
	* @return the transaction module details that was removed
	*/
	public static com.stpl.app.model.TransactionModuleDetails deleteTransactionModuleDetails(
		com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
		return getService()
				   .deleteTransactionModuleDetails(transactionModuleDetails);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.stpl.app.model.TransactionModuleDetails fetchTransactionModuleDetails(
		int transactionModuleDetailsSid) {
		return getService()
				   .fetchTransactionModuleDetails(transactionModuleDetailsSid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the transaction module details with the primary key.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details
	* @throws PortalException if a transaction module details with the primary key could not be found
	*/
	public static com.stpl.app.model.TransactionModuleDetails getTransactionModuleDetails(
		int transactionModuleDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getTransactionModuleDetails(transactionModuleDetailsSid);
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
	public static java.util.List<com.stpl.app.model.TransactionModuleDetails> getTransactionModuleDetailses(
		int start, int end) {
		return getService().getTransactionModuleDetailses(start, end);
	}

	/**
	* Returns the number of transaction module detailses.
	*
	* @return the number of transaction module detailses
	*/
	public static int getTransactionModuleDetailsesCount() {
		return getService().getTransactionModuleDetailsesCount();
	}

	/**
	* Updates the transaction module details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetails the transaction module details
	* @return the transaction module details that was updated
	*/
	public static com.stpl.app.model.TransactionModuleDetails updateTransactionModuleDetails(
		com.stpl.app.model.TransactionModuleDetails transactionModuleDetails) {
		return getService()
				   .updateTransactionModuleDetails(transactionModuleDetails);
	}

	public static TransactionModuleDetailsLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TransactionModuleDetailsLocalService, TransactionModuleDetailsLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TransactionModuleDetailsLocalService.class);
}