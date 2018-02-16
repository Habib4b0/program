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

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.NaProjDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the na proj details service. This utility wraps {@link com.stpl.app.service.persistence.impl.NaProjDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.NaProjDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class NaProjDetailsUtil {
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
	public static void clearCache(NaProjDetails naProjDetails) {
		getPersistence().clearCache(naProjDetails);
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
	public static List<NaProjDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NaProjDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NaProjDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NaProjDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NaProjDetails update(NaProjDetails naProjDetails) {
		return getPersistence().update(naProjDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NaProjDetails update(NaProjDetails naProjDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(naProjDetails, serviceContext);
	}

	/**
	* Caches the na proj details in the entity cache if it is enabled.
	*
	* @param naProjDetails the na proj details
	*/
	public static void cacheResult(NaProjDetails naProjDetails) {
		getPersistence().cacheResult(naProjDetails);
	}

	/**
	* Caches the na proj detailses in the entity cache if it is enabled.
	*
	* @param naProjDetailses the na proj detailses
	*/
	public static void cacheResult(List<NaProjDetails> naProjDetailses) {
		getPersistence().cacheResult(naProjDetailses);
	}

	/**
	* Creates a new na proj details with the primary key. Does not add the na proj details to the database.
	*
	* @param naProjDetailsSid the primary key for the new na proj details
	* @return the new na proj details
	*/
	public static NaProjDetails create(int naProjDetailsSid) {
		return getPersistence().create(naProjDetailsSid);
	}

	/**
	* Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param naProjDetailsSid the primary key of the na proj details
	* @return the na proj details that was removed
	* @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	*/
	public static NaProjDetails remove(int naProjDetailsSid)
		throws com.stpl.app.exception.NoSuchNaProjDetailsException {
		return getPersistence().remove(naProjDetailsSid);
	}

	public static NaProjDetails updateImpl(NaProjDetails naProjDetails) {
		return getPersistence().updateImpl(naProjDetails);
	}

	/**
	* Returns the na proj details with the primary key or throws a {@link NoSuchNaProjDetailsException} if it could not be found.
	*
	* @param naProjDetailsSid the primary key of the na proj details
	* @return the na proj details
	* @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	*/
	public static NaProjDetails findByPrimaryKey(int naProjDetailsSid)
		throws com.stpl.app.exception.NoSuchNaProjDetailsException {
		return getPersistence().findByPrimaryKey(naProjDetailsSid);
	}

	/**
	* Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param naProjDetailsSid the primary key of the na proj details
	* @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
	*/
	public static NaProjDetails fetchByPrimaryKey(int naProjDetailsSid) {
		return getPersistence().fetchByPrimaryKey(naProjDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, NaProjDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the na proj detailses.
	*
	* @return the na proj detailses
	*/
	public static List<NaProjDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the na proj detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj detailses
	* @param end the upper bound of the range of na proj detailses (not inclusive)
	* @return the range of na proj detailses
	*/
	public static List<NaProjDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the na proj detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj detailses
	* @param end the upper bound of the range of na proj detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of na proj detailses
	*/
	public static List<NaProjDetails> findAll(int start, int end,
		OrderByComparator<NaProjDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the na proj detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj detailses
	* @param end the upper bound of the range of na proj detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of na proj detailses
	*/
	public static List<NaProjDetails> findAll(int start, int end,
		OrderByComparator<NaProjDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the na proj detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of na proj detailses.
	*
	* @return the number of na proj detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NaProjDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NaProjDetailsPersistence, NaProjDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NaProjDetailsPersistence.class);
}