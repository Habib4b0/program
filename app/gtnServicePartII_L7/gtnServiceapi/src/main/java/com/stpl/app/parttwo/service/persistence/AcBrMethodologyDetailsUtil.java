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

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ac br methodology details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.AcBrMethodologyDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.AcBrMethodologyDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class AcBrMethodologyDetailsUtil {
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
	public static void clearCache(AcBrMethodologyDetails acBrMethodologyDetails) {
		getPersistence().clearCache(acBrMethodologyDetails);
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
	public static List<AcBrMethodologyDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AcBrMethodologyDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AcBrMethodologyDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AcBrMethodologyDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AcBrMethodologyDetails update(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		return getPersistence().update(acBrMethodologyDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AcBrMethodologyDetails update(
		AcBrMethodologyDetails acBrMethodologyDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(acBrMethodologyDetails, serviceContext);
	}

	/**
	* Caches the ac br methodology details in the entity cache if it is enabled.
	*
	* @param acBrMethodologyDetails the ac br methodology details
	*/
	public static void cacheResult(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		getPersistence().cacheResult(acBrMethodologyDetails);
	}

	/**
	* Caches the ac br methodology detailses in the entity cache if it is enabled.
	*
	* @param acBrMethodologyDetailses the ac br methodology detailses
	*/
	public static void cacheResult(
		List<AcBrMethodologyDetails> acBrMethodologyDetailses) {
		getPersistence().cacheResult(acBrMethodologyDetailses);
	}

	/**
	* Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
	*
	* @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
	* @return the new ac br methodology details
	*/
	public static AcBrMethodologyDetails create(int acBrMethodologyDetailsSid) {
		return getPersistence().create(acBrMethodologyDetailsSid);
	}

	/**
	* Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details that was removed
	* @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	*/
	public static AcBrMethodologyDetails remove(int acBrMethodologyDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchAcBrMethodologyDetailsException {
		return getPersistence().remove(acBrMethodologyDetailsSid);
	}

	public static AcBrMethodologyDetails updateImpl(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		return getPersistence().updateImpl(acBrMethodologyDetails);
	}

	/**
	* Returns the ac br methodology details with the primary key or throws a {@link NoSuchAcBrMethodologyDetailsException} if it could not be found.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details
	* @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	*/
	public static AcBrMethodologyDetails findByPrimaryKey(
		int acBrMethodologyDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchAcBrMethodologyDetailsException {
		return getPersistence().findByPrimaryKey(acBrMethodologyDetailsSid);
	}

	/**
	* Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
	*/
	public static AcBrMethodologyDetails fetchByPrimaryKey(
		int acBrMethodologyDetailsSid) {
		return getPersistence().fetchByPrimaryKey(acBrMethodologyDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, AcBrMethodologyDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ac br methodology detailses.
	*
	* @return the ac br methodology detailses
	*/
	public static List<AcBrMethodologyDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @return the range of ac br methodology detailses
	*/
	public static List<AcBrMethodologyDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ac br methodology detailses
	*/
	public static List<AcBrMethodologyDetails> findAll(int start, int end,
		OrderByComparator<AcBrMethodologyDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ac br methodology detailses
	*/
	public static List<AcBrMethodologyDetails> findAll(int start, int end,
		OrderByComparator<AcBrMethodologyDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ac br methodology detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ac br methodology detailses.
	*
	* @return the number of ac br methodology detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AcBrMethodologyDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AcBrMethodologyDetailsPersistence, AcBrMethodologyDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AcBrMethodologyDetailsPersistence.class);
}