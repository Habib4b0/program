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

import com.stpl.app.model.BusinessroleMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the businessrole master service. This utility wraps {@link com.stpl.app.service.persistence.impl.BusinessroleMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleMasterPersistence
 * @see com.stpl.app.service.persistence.impl.BusinessroleMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class BusinessroleMasterUtil {
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
	public static void clearCache(BusinessroleMaster businessroleMaster) {
		getPersistence().clearCache(businessroleMaster);
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
	public static List<BusinessroleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BusinessroleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BusinessroleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BusinessroleMaster update(
		BusinessroleMaster businessroleMaster) {
		return getPersistence().update(businessroleMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BusinessroleMaster update(
		BusinessroleMaster businessroleMaster, ServiceContext serviceContext) {
		return getPersistence().update(businessroleMaster, serviceContext);
	}

	/**
	* Returns all the businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName) {
		return getPersistence().findByBusinessroleName(businessroleName);
	}

	/**
	* Returns a range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName, int start, int end) {
		return getPersistence()
				   .findByBusinessroleName(businessroleName, start, end);
	}

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .findByBusinessroleName(businessroleName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBusinessroleName(businessroleName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster findByBusinessroleName_First(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence()
				   .findByBusinessroleName_First(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster fetchByBusinessroleName_First(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByBusinessroleName_First(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster findByBusinessroleName_Last(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence()
				   .findByBusinessroleName_Last(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster fetchByBusinessroleName_Last(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByBusinessroleName_Last(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleMasterSid the primary key of the current businessrole master
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next businessrole master
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public static BusinessroleMaster[] findByBusinessroleName_PrevAndNext(
		int businessroleMasterSid, java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence()
				   .findByBusinessroleName_PrevAndNext(businessroleMasterSid,
			businessroleName, orderByComparator);
	}

	/**
	* Removes all the businessrole masters where businessroleName = &#63; from the database.
	*
	* @param businessroleName the businessrole name
	*/
	public static void removeByBusinessroleName(
		java.lang.String businessroleName) {
		getPersistence().removeByBusinessroleName(businessroleName);
	}

	/**
	* Returns the number of businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the number of matching businessrole masters
	*/
	public static int countByBusinessroleName(java.lang.String businessroleName) {
		return getPersistence().countByBusinessroleName(businessroleName);
	}

	/**
	* Returns all the businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName) {
		return getPersistence().findByBusinessRoleUnique(businessroleName);
	}

	/**
	* Returns a range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName, int start, int end) {
		return getPersistence()
				   .findByBusinessRoleUnique(businessroleName, start, end);
	}

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .findByBusinessRoleUnique(businessroleName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching businessrole masters
	*/
	public static List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBusinessRoleUnique(businessroleName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster findByBusinessRoleUnique_First(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence()
				   .findByBusinessRoleUnique_First(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster fetchByBusinessRoleUnique_First(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByBusinessRoleUnique_First(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster findByBusinessRoleUnique_Last(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence()
				   .findByBusinessRoleUnique_Last(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public static BusinessroleMaster fetchByBusinessRoleUnique_Last(
		java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByBusinessRoleUnique_Last(businessroleName,
			orderByComparator);
	}

	/**
	* Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleMasterSid the primary key of the current businessrole master
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next businessrole master
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public static BusinessroleMaster[] findByBusinessRoleUnique_PrevAndNext(
		int businessroleMasterSid, java.lang.String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence()
				   .findByBusinessRoleUnique_PrevAndNext(businessroleMasterSid,
			businessroleName, orderByComparator);
	}

	/**
	* Removes all the businessrole masters where businessroleName = &#63; from the database.
	*
	* @param businessroleName the businessrole name
	*/
	public static void removeByBusinessRoleUnique(
		java.lang.String businessroleName) {
		getPersistence().removeByBusinessRoleUnique(businessroleName);
	}

	/**
	* Returns the number of businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the number of matching businessrole masters
	*/
	public static int countByBusinessRoleUnique(
		java.lang.String businessroleName) {
		return getPersistence().countByBusinessRoleUnique(businessroleName);
	}

	/**
	* Caches the businessrole master in the entity cache if it is enabled.
	*
	* @param businessroleMaster the businessrole master
	*/
	public static void cacheResult(BusinessroleMaster businessroleMaster) {
		getPersistence().cacheResult(businessroleMaster);
	}

	/**
	* Caches the businessrole masters in the entity cache if it is enabled.
	*
	* @param businessroleMasters the businessrole masters
	*/
	public static void cacheResult(List<BusinessroleMaster> businessroleMasters) {
		getPersistence().cacheResult(businessroleMasters);
	}

	/**
	* Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
	*
	* @param businessroleMasterSid the primary key for the new businessrole master
	* @return the new businessrole master
	*/
	public static BusinessroleMaster create(int businessroleMasterSid) {
		return getPersistence().create(businessroleMasterSid);
	}

	/**
	* Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master that was removed
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public static BusinessroleMaster remove(int businessroleMasterSid)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence().remove(businessroleMasterSid);
	}

	public static BusinessroleMaster updateImpl(
		BusinessroleMaster businessroleMaster) {
		return getPersistence().updateImpl(businessroleMaster);
	}

	/**
	* Returns the businessrole master with the primary key or throws a {@link NoSuchBusinessroleMasterException} if it could not be found.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public static BusinessroleMaster findByPrimaryKey(int businessroleMasterSid)
		throws com.stpl.app.exception.NoSuchBusinessroleMasterException {
		return getPersistence().findByPrimaryKey(businessroleMasterSid);
	}

	/**
	* Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
	*/
	public static BusinessroleMaster fetchByPrimaryKey(
		int businessroleMasterSid) {
		return getPersistence().fetchByPrimaryKey(businessroleMasterSid);
	}

	public static java.util.Map<java.io.Serializable, BusinessroleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the businessrole masters.
	*
	* @return the businessrole masters
	*/
	public static List<BusinessroleMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of businessrole masters
	*/
	public static List<BusinessroleMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of businessrole masters
	*/
	public static List<BusinessroleMaster> findAll(int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of businessrole masters
	*/
	public static List<BusinessroleMaster> findAll(int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the businessrole masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of businessrole masters.
	*
	* @return the number of businessrole masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BusinessroleMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BusinessroleMasterPersistence, BusinessroleMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(BusinessroleMasterPersistence.class);
}