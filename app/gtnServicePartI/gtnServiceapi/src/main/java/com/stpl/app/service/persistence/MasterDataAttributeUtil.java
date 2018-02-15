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

import com.stpl.app.model.MasterDataAttribute;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the master data attribute service. This utility wraps {@link com.stpl.app.service.persistence.impl.MasterDataAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataAttributePersistence
 * @see com.stpl.app.service.persistence.impl.MasterDataAttributePersistenceImpl
 * @generated
 */
@ProviderType
public class MasterDataAttributeUtil {
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
	public static void clearCache(MasterDataAttribute masterDataAttribute) {
		getPersistence().clearCache(masterDataAttribute);
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
	public static List<MasterDataAttribute> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MasterDataAttribute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MasterDataAttribute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MasterDataAttribute update(
		MasterDataAttribute masterDataAttribute) {
		return getPersistence().update(masterDataAttribute);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MasterDataAttribute update(
		MasterDataAttribute masterDataAttribute, ServiceContext serviceContext) {
		return getPersistence().update(masterDataAttribute, serviceContext);
	}

	/**
	* Returns all the master data attributes where masterType = &#63;.
	*
	* @param masterType the master type
	* @return the matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType) {
		return getPersistence().findByMasterType(masterType);
	}

	/**
	* Returns a range of all the master data attributes where masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType, int start, int end) {
		return getPersistence().findByMasterType(masterType, start, end);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .findByMasterType(masterType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMasterType(masterType, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterType_First(
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterType_First(masterType, orderByComparator);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterType_First(
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterType_First(masterType, orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterType_Last(
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterType_Last(masterType, orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterType_Last(
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterType_Last(masterType, orderByComparator);
	}

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute[] findByMasterType_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterType_PrevAndNext(masterDataAttributeSid,
			masterType, orderByComparator);
	}

	/**
	* Removes all the master data attributes where masterType = &#63; from the database.
	*
	* @param masterType the master type
	*/
	public static void removeByMasterType(java.lang.String masterType) {
		getPersistence().removeByMasterType(masterType);
	}

	/**
	* Returns the number of master data attributes where masterType = &#63;.
	*
	* @param masterType the master type
	* @return the number of matching master data attributes
	*/
	public static int countByMasterType(java.lang.String masterType) {
		return getPersistence().countByMasterType(masterType);
	}

	/**
	* Returns all the master data attributes where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @return the matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute) {
		return getPersistence().findByMasterAttribute(masterAttribute);
	}

	/**
	* Returns a range of all the master data attributes where masterAttribute = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute, int start, int end) {
		return getPersistence()
				   .findByMasterAttribute(masterAttribute, start, end);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .findByMasterAttribute(masterAttribute, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMasterAttribute(masterAttribute, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterAttribute_First(
		java.lang.String masterAttribute,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterAttribute_First(masterAttribute,
			orderByComparator);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterAttribute_First(
		java.lang.String masterAttribute,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterAttribute_First(masterAttribute,
			orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterAttribute_Last(
		java.lang.String masterAttribute,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterAttribute_Last(masterAttribute,
			orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterAttribute_Last(
		java.lang.String masterAttribute,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterAttribute_Last(masterAttribute,
			orderByComparator);
	}

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute[] findByMasterAttribute_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterAttribute,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterAttribute_PrevAndNext(masterDataAttributeSid,
			masterAttribute, orderByComparator);
	}

	/**
	* Removes all the master data attributes where masterAttribute = &#63; from the database.
	*
	* @param masterAttribute the master attribute
	*/
	public static void removeByMasterAttribute(java.lang.String masterAttribute) {
		getPersistence().removeByMasterAttribute(masterAttribute);
	}

	/**
	* Returns the number of master data attributes where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @return the number of matching master data attributes
	*/
	public static int countByMasterAttribute(java.lang.String masterAttribute) {
		return getPersistence().countByMasterAttribute(masterAttribute);
	}

	/**
	* Returns all the master data attributes where masterId = &#63;.
	*
	* @param masterId the master ID
	* @return the matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId) {
		return getPersistence().findByMasterId(masterId);
	}

	/**
	* Returns a range of all the master data attributes where masterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterId the master ID
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId, int start, int end) {
		return getPersistence().findByMasterId(masterId, start, end);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterId the master ID
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .findByMasterId(masterId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterId the master ID
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMasterId(masterId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterId_First(
		java.lang.String masterId,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence().findByMasterId_First(masterId, orderByComparator);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterId_First(
		java.lang.String masterId,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterId_First(masterId, orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterId_Last(
		java.lang.String masterId,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence().findByMasterId_Last(masterId, orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterId_Last(
		java.lang.String masterId,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence().fetchByMasterId_Last(masterId, orderByComparator);
	}

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute[] findByMasterId_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterId,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterId_PrevAndNext(masterDataAttributeSid,
			masterId, orderByComparator);
	}

	/**
	* Removes all the master data attributes where masterId = &#63; from the database.
	*
	* @param masterId the master ID
	*/
	public static void removeByMasterId(java.lang.String masterId) {
		getPersistence().removeByMasterId(masterId);
	}

	/**
	* Returns the number of master data attributes where masterId = &#63;.
	*
	* @param masterId the master ID
	* @return the number of matching master data attributes
	*/
	public static int countByMasterId(java.lang.String masterId) {
		return getPersistence().countByMasterId(masterId);
	}

	/**
	* Returns all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @return the matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType) {
		return getPersistence()
				   .findByMasterDataAttributeUnique(masterAttribute, masterId,
			masterType);
	}

	/**
	* Returns a range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType, int start, int end) {
		return getPersistence()
				   .findByMasterDataAttributeUnique(masterAttribute, masterId,
			masterType, start, end);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .findByMasterDataAttributeUnique(masterAttribute, masterId,
			masterType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public static List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType, int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMasterDataAttributeUnique(masterAttribute, masterId,
			masterType, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterDataAttributeUnique_First(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterDataAttributeUnique_First(masterAttribute,
			masterId, masterType, orderByComparator);
	}

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterDataAttributeUnique_First(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterDataAttributeUnique_First(masterAttribute,
			masterId, masterType, orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute findByMasterDataAttributeUnique_Last(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterDataAttributeUnique_Last(masterAttribute,
			masterId, masterType, orderByComparator);
	}

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public static MasterDataAttribute fetchByMasterDataAttributeUnique_Last(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .fetchByMasterDataAttributeUnique_Last(masterAttribute,
			masterId, masterType, orderByComparator);
	}

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute[] findByMasterDataAttributeUnique_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterAttribute,
		java.lang.String masterId, java.lang.String masterType,
		OrderByComparator<MasterDataAttribute> orderByComparator)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence()
				   .findByMasterDataAttributeUnique_PrevAndNext(masterDataAttributeSid,
			masterAttribute, masterId, masterType, orderByComparator);
	}

	/**
	* Removes all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63; from the database.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	*/
	public static void removeByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType) {
		getPersistence()
			.removeByMasterDataAttributeUnique(masterAttribute, masterId,
			masterType);
	}

	/**
	* Returns the number of master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @return the number of matching master data attributes
	*/
	public static int countByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType) {
		return getPersistence()
				   .countByMasterDataAttributeUnique(masterAttribute, masterId,
			masterType);
	}

	/**
	* Caches the master data attribute in the entity cache if it is enabled.
	*
	* @param masterDataAttribute the master data attribute
	*/
	public static void cacheResult(MasterDataAttribute masterDataAttribute) {
		getPersistence().cacheResult(masterDataAttribute);
	}

	/**
	* Caches the master data attributes in the entity cache if it is enabled.
	*
	* @param masterDataAttributes the master data attributes
	*/
	public static void cacheResult(
		List<MasterDataAttribute> masterDataAttributes) {
		getPersistence().cacheResult(masterDataAttributes);
	}

	/**
	* Creates a new master data attribute with the primary key. Does not add the master data attribute to the database.
	*
	* @param masterDataAttributeSid the primary key for the new master data attribute
	* @return the new master data attribute
	*/
	public static MasterDataAttribute create(int masterDataAttributeSid) {
		return getPersistence().create(masterDataAttributeSid);
	}

	/**
	* Removes the master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataAttributeSid the primary key of the master data attribute
	* @return the master data attribute that was removed
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute remove(int masterDataAttributeSid)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence().remove(masterDataAttributeSid);
	}

	public static MasterDataAttribute updateImpl(
		MasterDataAttribute masterDataAttribute) {
		return getPersistence().updateImpl(masterDataAttribute);
	}

	/**
	* Returns the master data attribute with the primary key or throws a {@link NoSuchMasterDataAttributeException} if it could not be found.
	*
	* @param masterDataAttributeSid the primary key of the master data attribute
	* @return the master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute findByPrimaryKey(
		int masterDataAttributeSid)
		throws com.stpl.app.exception.NoSuchMasterDataAttributeException {
		return getPersistence().findByPrimaryKey(masterDataAttributeSid);
	}

	/**
	* Returns the master data attribute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param masterDataAttributeSid the primary key of the master data attribute
	* @return the master data attribute, or <code>null</code> if a master data attribute with the primary key could not be found
	*/
	public static MasterDataAttribute fetchByPrimaryKey(
		int masterDataAttributeSid) {
		return getPersistence().fetchByPrimaryKey(masterDataAttributeSid);
	}

	public static java.util.Map<java.io.Serializable, MasterDataAttribute> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the master data attributes.
	*
	* @return the master data attributes
	*/
	public static List<MasterDataAttribute> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of master data attributes
	*/
	public static List<MasterDataAttribute> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of master data attributes
	*/
	public static List<MasterDataAttribute> findAll(int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of master data attributes
	*/
	public static List<MasterDataAttribute> findAll(int start, int end,
		OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the master data attributes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of master data attributes.
	*
	* @return the number of master data attributes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MasterDataAttributePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MasterDataAttributePersistence, MasterDataAttributePersistence> _serviceTracker =
		ServiceTrackerFactory.open(MasterDataAttributePersistence.class);
}