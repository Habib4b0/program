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

import com.stpl.app.model.StMedicaidNewNdc;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st medicaid new ndc service. This utility wraps {@link com.stpl.app.service.persistence.impl.StMedicaidNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMedicaidNewNdcPersistence
 * @see com.stpl.app.service.persistence.impl.StMedicaidNewNdcPersistenceImpl
 * @generated
 */
@ProviderType
public class StMedicaidNewNdcUtil {
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
	public static void clearCache(StMedicaidNewNdc stMedicaidNewNdc) {
		getPersistence().clearCache(stMedicaidNewNdc);
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
	public static List<StMedicaidNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StMedicaidNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StMedicaidNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StMedicaidNewNdc> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StMedicaidNewNdc update(StMedicaidNewNdc stMedicaidNewNdc) {
		return getPersistence().update(stMedicaidNewNdc);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StMedicaidNewNdc update(StMedicaidNewNdc stMedicaidNewNdc,
		ServiceContext serviceContext) {
		return getPersistence().update(stMedicaidNewNdc, serviceContext);
	}

	/**
	* Caches the st medicaid new ndc in the entity cache if it is enabled.
	*
	* @param stMedicaidNewNdc the st medicaid new ndc
	*/
	public static void cacheResult(StMedicaidNewNdc stMedicaidNewNdc) {
		getPersistence().cacheResult(stMedicaidNewNdc);
	}

	/**
	* Caches the st medicaid new ndcs in the entity cache if it is enabled.
	*
	* @param stMedicaidNewNdcs the st medicaid new ndcs
	*/
	public static void cacheResult(List<StMedicaidNewNdc> stMedicaidNewNdcs) {
		getPersistence().cacheResult(stMedicaidNewNdcs);
	}

	/**
	* Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
	*
	* @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
	* @return the new st medicaid new ndc
	*/
	public static StMedicaidNewNdc create(StMedicaidNewNdcPK stMedicaidNewNdcPK) {
		return getPersistence().create(stMedicaidNewNdcPK);
	}

	/**
	* Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	* @return the st medicaid new ndc that was removed
	* @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	*/
	public static StMedicaidNewNdc remove(StMedicaidNewNdcPK stMedicaidNewNdcPK)
		throws com.stpl.app.exception.NoSuchStMedicaidNewNdcException {
		return getPersistence().remove(stMedicaidNewNdcPK);
	}

	public static StMedicaidNewNdc updateImpl(StMedicaidNewNdc stMedicaidNewNdc) {
		return getPersistence().updateImpl(stMedicaidNewNdc);
	}

	/**
	* Returns the st medicaid new ndc with the primary key or throws a {@link NoSuchStMedicaidNewNdcException} if it could not be found.
	*
	* @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	* @return the st medicaid new ndc
	* @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	*/
	public static StMedicaidNewNdc findByPrimaryKey(
		StMedicaidNewNdcPK stMedicaidNewNdcPK)
		throws com.stpl.app.exception.NoSuchStMedicaidNewNdcException {
		return getPersistence().findByPrimaryKey(stMedicaidNewNdcPK);
	}

	/**
	* Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	* @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
	*/
	public static StMedicaidNewNdc fetchByPrimaryKey(
		StMedicaidNewNdcPK stMedicaidNewNdcPK) {
		return getPersistence().fetchByPrimaryKey(stMedicaidNewNdcPK);
	}

	public static java.util.Map<java.io.Serializable, StMedicaidNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st medicaid new ndcs.
	*
	* @return the st medicaid new ndcs
	*/
	public static List<StMedicaidNewNdc> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st medicaid new ndcs
	* @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	* @return the range of st medicaid new ndcs
	*/
	public static List<StMedicaidNewNdc> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st medicaid new ndcs
	* @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st medicaid new ndcs
	*/
	public static List<StMedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<StMedicaidNewNdc> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st medicaid new ndcs
	* @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st medicaid new ndcs
	*/
	public static List<StMedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<StMedicaidNewNdc> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st medicaid new ndcs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st medicaid new ndcs.
	*
	* @return the number of st medicaid new ndcs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StMedicaidNewNdcPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StMedicaidNewNdcPersistence, StMedicaidNewNdcPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StMedicaidNewNdcPersistence.class);
}