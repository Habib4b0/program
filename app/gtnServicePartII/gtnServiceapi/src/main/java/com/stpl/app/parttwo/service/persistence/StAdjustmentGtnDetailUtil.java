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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st adjustment gtn detail service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.StAdjustmentGtnDetailPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetailPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.StAdjustmentGtnDetailPersistenceImpl
 * @generated
 */
@ProviderType
public class StAdjustmentGtnDetailUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		getPersistence().clearCache(stAdjustmentGtnDetail);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<StAdjustmentGtnDetail> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StAdjustmentGtnDetail> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StAdjustmentGtnDetail> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StAdjustmentGtnDetail> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StAdjustmentGtnDetail update(
		StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		return getPersistence().update(stAdjustmentGtnDetail);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StAdjustmentGtnDetail update(
		StAdjustmentGtnDetail stAdjustmentGtnDetail,
		ServiceContext serviceContext) {
		return getPersistence().update(stAdjustmentGtnDetail, serviceContext);
	}

	/**
	* Caches the st adjustment gtn detail in the entity cache if it is enabled.
	*
	* @param stAdjustmentGtnDetail the st adjustment gtn detail
	*/
	public static void cacheResult(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		getPersistence().cacheResult(stAdjustmentGtnDetail);
	}

	/**
	* Caches the st adjustment gtn details in the entity cache if it is enabled.
	*
	* @param stAdjustmentGtnDetails the st adjustment gtn details
	*/
	public static void cacheResult(
		List<StAdjustmentGtnDetail> stAdjustmentGtnDetails) {
		getPersistence().cacheResult(stAdjustmentGtnDetails);
	}

	/**
	* Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
	*
	* @param workflowId the primary key for the new st adjustment gtn detail
	* @return the new st adjustment gtn detail
	*/
	public static StAdjustmentGtnDetail create(java.lang.String workflowId) {
		return getPersistence().create(workflowId);
	}

	/**
	* Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowId the primary key of the st adjustment gtn detail
	* @return the st adjustment gtn detail that was removed
	* @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	*/
	public static StAdjustmentGtnDetail remove(java.lang.String workflowId)
		throws com.stpl.app.parttwo.exception.NoSuchStAdjustmentGtnDetailException {
		return getPersistence().remove(workflowId);
	}

	public static StAdjustmentGtnDetail updateImpl(
		StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		return getPersistence().updateImpl(stAdjustmentGtnDetail);
	}

	/**
	* Returns the st adjustment gtn detail with the primary key or throws a {@link NoSuchStAdjustmentGtnDetailException} if it could not be found.
	*
	* @param workflowId the primary key of the st adjustment gtn detail
	* @return the st adjustment gtn detail
	* @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	*/
	public static StAdjustmentGtnDetail findByPrimaryKey(
		java.lang.String workflowId)
		throws com.stpl.app.parttwo.exception.NoSuchStAdjustmentGtnDetailException {
		return getPersistence().findByPrimaryKey(workflowId);
	}

	/**
	* Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowId the primary key of the st adjustment gtn detail
	* @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
	*/
	public static StAdjustmentGtnDetail fetchByPrimaryKey(
		java.lang.String workflowId) {
		return getPersistence().fetchByPrimaryKey(workflowId);
	}

	public static java.util.Map<java.io.Serializable, StAdjustmentGtnDetail> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st adjustment gtn details.
	*
	* @return the st adjustment gtn details
	*/
	public static List<StAdjustmentGtnDetail> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st adjustment gtn details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment gtn details
	* @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	* @return the range of st adjustment gtn details
	*/
	public static List<StAdjustmentGtnDetail> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st adjustment gtn details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment gtn details
	* @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st adjustment gtn details
	*/
	public static List<StAdjustmentGtnDetail> findAll(int start, int end,
		OrderByComparator<StAdjustmentGtnDetail> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st adjustment gtn details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment gtn details
	* @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st adjustment gtn details
	*/
	public static List<StAdjustmentGtnDetail> findAll(int start, int end,
		OrderByComparator<StAdjustmentGtnDetail> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st adjustment gtn details from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st adjustment gtn details.
	*
	* @return the number of st adjustment gtn details
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StAdjustmentGtnDetailPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StAdjustmentGtnDetailPersistence, StAdjustmentGtnDetailPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StAdjustmentGtnDetailPersistence.class);
}