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

import com.stpl.app.parttwo.model.SlaCalendarDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sla calendar details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.SlaCalendarDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.SlaCalendarDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class SlaCalendarDetailsUtil {
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
	public static void clearCache(SlaCalendarDetails slaCalendarDetails) {
		getPersistence().clearCache(slaCalendarDetails);
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
	public static List<SlaCalendarDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SlaCalendarDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SlaCalendarDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SlaCalendarDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SlaCalendarDetails update(
		SlaCalendarDetails slaCalendarDetails) {
		return getPersistence().update(slaCalendarDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SlaCalendarDetails update(
		SlaCalendarDetails slaCalendarDetails, ServiceContext serviceContext) {
		return getPersistence().update(slaCalendarDetails, serviceContext);
	}

	/**
	* Caches the sla calendar details in the entity cache if it is enabled.
	*
	* @param slaCalendarDetails the sla calendar details
	*/
	public static void cacheResult(SlaCalendarDetails slaCalendarDetails) {
		getPersistence().cacheResult(slaCalendarDetails);
	}

	/**
	* Caches the sla calendar detailses in the entity cache if it is enabled.
	*
	* @param slaCalendarDetailses the sla calendar detailses
	*/
	public static void cacheResult(
		List<SlaCalendarDetails> slaCalendarDetailses) {
		getPersistence().cacheResult(slaCalendarDetailses);
	}

	/**
	* Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
	*
	* @param slaCalendarDetailsSid the primary key for the new sla calendar details
	* @return the new sla calendar details
	*/
	public static SlaCalendarDetails create(int slaCalendarDetailsSid) {
		return getPersistence().create(slaCalendarDetailsSid);
	}

	/**
	* Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details that was removed
	* @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	*/
	public static SlaCalendarDetails remove(int slaCalendarDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchSlaCalendarDetailsException {
		return getPersistence().remove(slaCalendarDetailsSid);
	}

	public static SlaCalendarDetails updateImpl(
		SlaCalendarDetails slaCalendarDetails) {
		return getPersistence().updateImpl(slaCalendarDetails);
	}

	/**
	* Returns the sla calendar details with the primary key or throws a {@link NoSuchSlaCalendarDetailsException} if it could not be found.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details
	* @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	*/
	public static SlaCalendarDetails findByPrimaryKey(int slaCalendarDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchSlaCalendarDetailsException {
		return getPersistence().findByPrimaryKey(slaCalendarDetailsSid);
	}

	/**
	* Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
	*/
	public static SlaCalendarDetails fetchByPrimaryKey(
		int slaCalendarDetailsSid) {
		return getPersistence().fetchByPrimaryKey(slaCalendarDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, SlaCalendarDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sla calendar detailses.
	*
	* @return the sla calendar detailses
	*/
	public static List<SlaCalendarDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @return the range of sla calendar detailses
	*/
	public static List<SlaCalendarDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sla calendar detailses
	*/
	public static List<SlaCalendarDetails> findAll(int start, int end,
		OrderByComparator<SlaCalendarDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sla calendar detailses
	*/
	public static List<SlaCalendarDetails> findAll(int start, int end,
		OrderByComparator<SlaCalendarDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sla calendar detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sla calendar detailses.
	*
	* @return the number of sla calendar detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SlaCalendarDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SlaCalendarDetailsPersistence, SlaCalendarDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SlaCalendarDetailsPersistence.class);
}