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

import com.stpl.app.parttwo.model.CffAdditionalInfo;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff additional info service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffAdditionalInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffAdditionalInfoPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffAdditionalInfoPersistenceImpl
 * @generated
 */
@ProviderType
public class CffAdditionalInfoUtil {
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
	public static void clearCache(CffAdditionalInfo cffAdditionalInfo) {
		getPersistence().clearCache(cffAdditionalInfo);
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
	public static List<CffAdditionalInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffAdditionalInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffAdditionalInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffAdditionalInfo> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffAdditionalInfo update(CffAdditionalInfo cffAdditionalInfo) {
		return getPersistence().update(cffAdditionalInfo);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffAdditionalInfo update(
		CffAdditionalInfo cffAdditionalInfo, ServiceContext serviceContext) {
		return getPersistence().update(cffAdditionalInfo, serviceContext);
	}

	/**
	* Caches the cff additional info in the entity cache if it is enabled.
	*
	* @param cffAdditionalInfo the cff additional info
	*/
	public static void cacheResult(CffAdditionalInfo cffAdditionalInfo) {
		getPersistence().cacheResult(cffAdditionalInfo);
	}

	/**
	* Caches the cff additional infos in the entity cache if it is enabled.
	*
	* @param cffAdditionalInfos the cff additional infos
	*/
	public static void cacheResult(List<CffAdditionalInfo> cffAdditionalInfos) {
		getPersistence().cacheResult(cffAdditionalInfos);
	}

	/**
	* Creates a new cff additional info with the primary key. Does not add the cff additional info to the database.
	*
	* @param cffAdditionalInfoSid the primary key for the new cff additional info
	* @return the new cff additional info
	*/
	public static CffAdditionalInfo create(int cffAdditionalInfoSid) {
		return getPersistence().create(cffAdditionalInfoSid);
	}

	/**
	* Removes the cff additional info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffAdditionalInfoSid the primary key of the cff additional info
	* @return the cff additional info that was removed
	* @throws NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
	*/
	public static CffAdditionalInfo remove(int cffAdditionalInfoSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffAdditionalInfoException {
		return getPersistence().remove(cffAdditionalInfoSid);
	}

	public static CffAdditionalInfo updateImpl(
		CffAdditionalInfo cffAdditionalInfo) {
		return getPersistence().updateImpl(cffAdditionalInfo);
	}

	/**
	* Returns the cff additional info with the primary key or throws a {@link NoSuchCffAdditionalInfoException} if it could not be found.
	*
	* @param cffAdditionalInfoSid the primary key of the cff additional info
	* @return the cff additional info
	* @throws NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
	*/
	public static CffAdditionalInfo findByPrimaryKey(int cffAdditionalInfoSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffAdditionalInfoException {
		return getPersistence().findByPrimaryKey(cffAdditionalInfoSid);
	}

	/**
	* Returns the cff additional info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffAdditionalInfoSid the primary key of the cff additional info
	* @return the cff additional info, or <code>null</code> if a cff additional info with the primary key could not be found
	*/
	public static CffAdditionalInfo fetchByPrimaryKey(int cffAdditionalInfoSid) {
		return getPersistence().fetchByPrimaryKey(cffAdditionalInfoSid);
	}

	public static java.util.Map<java.io.Serializable, CffAdditionalInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff additional infos.
	*
	* @return the cff additional infos
	*/
	public static List<CffAdditionalInfo> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff additional infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff additional infos
	* @param end the upper bound of the range of cff additional infos (not inclusive)
	* @return the range of cff additional infos
	*/
	public static List<CffAdditionalInfo> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff additional infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff additional infos
	* @param end the upper bound of the range of cff additional infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff additional infos
	*/
	public static List<CffAdditionalInfo> findAll(int start, int end,
		OrderByComparator<CffAdditionalInfo> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff additional infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff additional infos
	* @param end the upper bound of the range of cff additional infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff additional infos
	*/
	public static List<CffAdditionalInfo> findAll(int start, int end,
		OrderByComparator<CffAdditionalInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff additional infos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff additional infos.
	*
	* @return the number of cff additional infos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffAdditionalInfoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffAdditionalInfoPersistence, CffAdditionalInfoPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffAdditionalInfoPersistence.class);
}