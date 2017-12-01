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

import com.stpl.app.model.MedicaidNewNdc;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the medicaid new ndc service. This utility wraps {@link com.stpl.app.service.persistence.impl.MedicaidNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidNewNdcPersistence
 * @see com.stpl.app.service.persistence.impl.MedicaidNewNdcPersistenceImpl
 * @generated
 */
@ProviderType
public class MedicaidNewNdcUtil {
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
	public static void clearCache(MedicaidNewNdc medicaidNewNdc) {
		getPersistence().clearCache(medicaidNewNdc);
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
	public static List<MedicaidNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MedicaidNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MedicaidNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MedicaidNewNdc> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MedicaidNewNdc update(MedicaidNewNdc medicaidNewNdc) {
		return getPersistence().update(medicaidNewNdc);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MedicaidNewNdc update(MedicaidNewNdc medicaidNewNdc,
		ServiceContext serviceContext) {
		return getPersistence().update(medicaidNewNdc, serviceContext);
	}

	/**
	* Caches the medicaid new ndc in the entity cache if it is enabled.
	*
	* @param medicaidNewNdc the medicaid new ndc
	*/
	public static void cacheResult(MedicaidNewNdc medicaidNewNdc) {
		getPersistence().cacheResult(medicaidNewNdc);
	}

	/**
	* Caches the medicaid new ndcs in the entity cache if it is enabled.
	*
	* @param medicaidNewNdcs the medicaid new ndcs
	*/
	public static void cacheResult(List<MedicaidNewNdc> medicaidNewNdcs) {
		getPersistence().cacheResult(medicaidNewNdcs);
	}

	/**
	* Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
	*
	* @param ndc9 the primary key for the new medicaid new ndc
	* @return the new medicaid new ndc
	*/
	public static MedicaidNewNdc create(java.lang.String ndc9) {
		return getPersistence().create(ndc9);
	}

	/**
	* Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc that was removed
	* @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	*/
	public static MedicaidNewNdc remove(java.lang.String ndc9)
		throws com.stpl.app.exception.NoSuchMedicaidNewNdcException {
		return getPersistence().remove(ndc9);
	}

	public static MedicaidNewNdc updateImpl(MedicaidNewNdc medicaidNewNdc) {
		return getPersistence().updateImpl(medicaidNewNdc);
	}

	/**
	* Returns the medicaid new ndc with the primary key or throws a {@link NoSuchMedicaidNewNdcException} if it could not be found.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc
	* @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	*/
	public static MedicaidNewNdc findByPrimaryKey(java.lang.String ndc9)
		throws com.stpl.app.exception.NoSuchMedicaidNewNdcException {
		return getPersistence().findByPrimaryKey(ndc9);
	}

	/**
	* Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
	*/
	public static MedicaidNewNdc fetchByPrimaryKey(java.lang.String ndc9) {
		return getPersistence().fetchByPrimaryKey(ndc9);
	}

	public static java.util.Map<java.io.Serializable, MedicaidNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the medicaid new ndcs.
	*
	* @return the medicaid new ndcs
	*/
	public static List<MedicaidNewNdc> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @return the range of medicaid new ndcs
	*/
	public static List<MedicaidNewNdc> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of medicaid new ndcs
	*/
	public static List<MedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<MedicaidNewNdc> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of medicaid new ndcs
	*/
	public static List<MedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<MedicaidNewNdc> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the medicaid new ndcs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of medicaid new ndcs.
	*
	* @return the number of medicaid new ndcs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MedicaidNewNdcPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MedicaidNewNdcPersistence, MedicaidNewNdcPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MedicaidNewNdcPersistence.class);
}