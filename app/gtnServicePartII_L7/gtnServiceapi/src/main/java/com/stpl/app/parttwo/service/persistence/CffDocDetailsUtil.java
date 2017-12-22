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

import com.stpl.app.parttwo.model.CffDocDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff doc details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffDocDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDocDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffDocDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CffDocDetailsUtil {
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
	public static void clearCache(CffDocDetails cffDocDetails) {
		getPersistence().clearCache(cffDocDetails);
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
	public static List<CffDocDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffDocDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffDocDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffDocDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffDocDetails update(CffDocDetails cffDocDetails) {
		return getPersistence().update(cffDocDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffDocDetails update(CffDocDetails cffDocDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(cffDocDetails, serviceContext);
	}

	/**
	* Caches the cff doc details in the entity cache if it is enabled.
	*
	* @param cffDocDetails the cff doc details
	*/
	public static void cacheResult(CffDocDetails cffDocDetails) {
		getPersistence().cacheResult(cffDocDetails);
	}

	/**
	* Caches the cff doc detailses in the entity cache if it is enabled.
	*
	* @param cffDocDetailses the cff doc detailses
	*/
	public static void cacheResult(List<CffDocDetails> cffDocDetailses) {
		getPersistence().cacheResult(cffDocDetailses);
	}

	/**
	* Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
	*
	* @param cffDocDetailsSid the primary key for the new cff doc details
	* @return the new cff doc details
	*/
	public static CffDocDetails create(int cffDocDetailsSid) {
		return getPersistence().create(cffDocDetailsSid);
	}

	/**
	* Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffDocDetailsSid the primary key of the cff doc details
	* @return the cff doc details that was removed
	* @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	*/
	public static CffDocDetails remove(int cffDocDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffDocDetailsException {
		return getPersistence().remove(cffDocDetailsSid);
	}

	public static CffDocDetails updateImpl(CffDocDetails cffDocDetails) {
		return getPersistence().updateImpl(cffDocDetails);
	}

	/**
	* Returns the cff doc details with the primary key or throws a {@link NoSuchCffDocDetailsException} if it could not be found.
	*
	* @param cffDocDetailsSid the primary key of the cff doc details
	* @return the cff doc details
	* @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	*/
	public static CffDocDetails findByPrimaryKey(int cffDocDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffDocDetailsException {
		return getPersistence().findByPrimaryKey(cffDocDetailsSid);
	}

	/**
	* Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffDocDetailsSid the primary key of the cff doc details
	* @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
	*/
	public static CffDocDetails fetchByPrimaryKey(int cffDocDetailsSid) {
		return getPersistence().fetchByPrimaryKey(cffDocDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CffDocDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff doc detailses.
	*
	* @return the cff doc detailses
	*/
	public static List<CffDocDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff doc detailses
	* @param end the upper bound of the range of cff doc detailses (not inclusive)
	* @return the range of cff doc detailses
	*/
	public static List<CffDocDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff doc detailses
	* @param end the upper bound of the range of cff doc detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff doc detailses
	*/
	public static List<CffDocDetails> findAll(int start, int end,
		OrderByComparator<CffDocDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff doc detailses
	* @param end the upper bound of the range of cff doc detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff doc detailses
	*/
	public static List<CffDocDetails> findAll(int start, int end,
		OrderByComparator<CffDocDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff doc detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff doc detailses.
	*
	* @return the number of cff doc detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffDocDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffDocDetailsPersistence, CffDocDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffDocDetailsPersistence.class);
}