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

import com.stpl.app.parttwo.model.AhTempDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ah temp details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.AhTempDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AhTempDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.AhTempDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class AhTempDetailsUtil {
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
	public static void clearCache(AhTempDetails ahTempDetails) {
		getPersistence().clearCache(ahTempDetails);
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
	public static List<AhTempDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AhTempDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AhTempDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AhTempDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AhTempDetails update(AhTempDetails ahTempDetails) {
		return getPersistence().update(ahTempDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AhTempDetails update(AhTempDetails ahTempDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(ahTempDetails, serviceContext);
	}

	/**
	* Caches the ah temp details in the entity cache if it is enabled.
	*
	* @param ahTempDetails the ah temp details
	*/
	public static void cacheResult(AhTempDetails ahTempDetails) {
		getPersistence().cacheResult(ahTempDetails);
	}

	/**
	* Caches the ah temp detailses in the entity cache if it is enabled.
	*
	* @param ahTempDetailses the ah temp detailses
	*/
	public static void cacheResult(List<AhTempDetails> ahTempDetailses) {
		getPersistence().cacheResult(ahTempDetailses);
	}

	/**
	* Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
	*
	* @param componentMasterSid the primary key for the new ah temp details
	* @return the new ah temp details
	*/
	public static AhTempDetails create(int componentMasterSid) {
		return getPersistence().create(componentMasterSid);
	}

	/**
	* Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details that was removed
	* @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	*/
	public static AhTempDetails remove(int componentMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchAhTempDetailsException {
		return getPersistence().remove(componentMasterSid);
	}

	public static AhTempDetails updateImpl(AhTempDetails ahTempDetails) {
		return getPersistence().updateImpl(ahTempDetails);
	}

	/**
	* Returns the ah temp details with the primary key or throws a {@link NoSuchAhTempDetailsException} if it could not be found.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details
	* @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	*/
	public static AhTempDetails findByPrimaryKey(int componentMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchAhTempDetailsException {
		return getPersistence().findByPrimaryKey(componentMasterSid);
	}

	/**
	* Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
	*/
	public static AhTempDetails fetchByPrimaryKey(int componentMasterSid) {
		return getPersistence().fetchByPrimaryKey(componentMasterSid);
	}

	public static java.util.Map<java.io.Serializable, AhTempDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ah temp detailses.
	*
	* @return the ah temp detailses
	*/
	public static List<AhTempDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @return the range of ah temp detailses
	*/
	public static List<AhTempDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ah temp detailses
	*/
	public static List<AhTempDetails> findAll(int start, int end,
		OrderByComparator<AhTempDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ah temp detailses
	*/
	public static List<AhTempDetails> findAll(int start, int end,
		OrderByComparator<AhTempDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ah temp detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ah temp detailses.
	*
	* @return the number of ah temp detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AhTempDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AhTempDetailsPersistence, AhTempDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AhTempDetailsPersistence.class);
}