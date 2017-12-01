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

import com.stpl.app.model.ItemHierarchyMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the item hierarchy master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemHierarchyMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ItemHierarchyMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemHierarchyMasterUtil {
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
	public static void clearCache(ItemHierarchyMaster itemHierarchyMaster) {
		getPersistence().clearCache(itemHierarchyMaster);
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
	public static List<ItemHierarchyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemHierarchyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemHierarchyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemHierarchyMaster update(
		ItemHierarchyMaster itemHierarchyMaster) {
		return getPersistence().update(itemHierarchyMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemHierarchyMaster update(
		ItemHierarchyMaster itemHierarchyMaster, ServiceContext serviceContext) {
		return getPersistence().update(itemHierarchyMaster, serviceContext);
	}

	/**
	* Returns all the item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0) {
		return getPersistence().findByLevel0(level0);
	}

	/**
	* Returns a range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0, int start, int end) {
		return getPersistence().findByLevel0(level0, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .findByLevel0(level0, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0(
		java.lang.String level0, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLevel0(level0, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByLevel0_First(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence().findByLevel0_First(level0, orderByComparator);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByLevel0_First(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence().fetchByLevel0_First(level0, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByLevel0_Last(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence().findByLevel0_Last(level0, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByLevel0_Last(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence().fetchByLevel0_Last(level0, orderByComparator);
	}

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster[] findByLevel0_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0_PrevAndNext(itemHierarchyMasterSid, level0,
			orderByComparator);
	}

	/**
	* Removes all the item hierarchy masters where level0 = &#63; from the database.
	*
	* @param level0 the level0
	*/
	public static void removeByLevel0(java.lang.String level0) {
		getPersistence().removeByLevel0(level0);
	}

	/**
	* Returns the number of item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the number of matching item hierarchy masters
	*/
	public static int countByLevel0(java.lang.String level0) {
		return getPersistence().countByLevel0(level0);
	}

	/**
	* Returns all the item hierarchy masters where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @return the matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias) {
		return getPersistence().findByLevel0Alias(level0Alias);
	}

	/**
	* Returns a range of all the item hierarchy masters where level0Alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Alias the level0 alias
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias, int start, int end) {
		return getPersistence().findByLevel0Alias(level0Alias, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Alias the level0 alias
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .findByLevel0Alias(level0Alias, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Alias the level0 alias
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Alias(
		java.lang.String level0Alias, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLevel0Alias(level0Alias, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByLevel0Alias_First(
		java.lang.String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0Alias_First(level0Alias, orderByComparator);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByLevel0Alias_First(
		java.lang.String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLevel0Alias_First(level0Alias, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByLevel0Alias_Last(
		java.lang.String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0Alias_Last(level0Alias, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByLevel0Alias_Last(
		java.lang.String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLevel0Alias_Last(level0Alias, orderByComparator);
	}

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Alias = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0Alias the level0 alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster[] findByLevel0Alias_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0Alias_PrevAndNext(itemHierarchyMasterSid,
			level0Alias, orderByComparator);
	}

	/**
	* Removes all the item hierarchy masters where level0Alias = &#63; from the database.
	*
	* @param level0Alias the level0 alias
	*/
	public static void removeByLevel0Alias(java.lang.String level0Alias) {
		getPersistence().removeByLevel0Alias(level0Alias);
	}

	/**
	* Returns the number of item hierarchy masters where level0Alias = &#63;.
	*
	* @param level0Alias the level0 alias
	* @return the number of matching item hierarchy masters
	*/
	public static int countByLevel0Alias(java.lang.String level0Alias) {
		return getPersistence().countByLevel0Alias(level0Alias);
	}

	/**
	* Returns all the item hierarchy masters where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @return the matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag) {
		return getPersistence().findByLevel0Tag(level0Tag);
	}

	/**
	* Returns a range of all the item hierarchy masters where level0Tag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Tag the level0 tag
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag, int start, int end) {
		return getPersistence().findByLevel0Tag(level0Tag, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Tag the level0 tag
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .findByLevel0Tag(level0Tag, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0Tag the level0 tag
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByLevel0Tag(
		java.lang.String level0Tag, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLevel0Tag(level0Tag, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByLevel0Tag_First(
		java.lang.String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0Tag_First(level0Tag, orderByComparator);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByLevel0Tag_First(
		java.lang.String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLevel0Tag_First(level0Tag, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByLevel0Tag_Last(
		java.lang.String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0Tag_Last(level0Tag, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByLevel0Tag_Last(
		java.lang.String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLevel0Tag_Last(level0Tag, orderByComparator);
	}

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Tag = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0Tag the level0 tag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster[] findByLevel0Tag_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByLevel0Tag_PrevAndNext(itemHierarchyMasterSid,
			level0Tag, orderByComparator);
	}

	/**
	* Removes all the item hierarchy masters where level0Tag = &#63; from the database.
	*
	* @param level0Tag the level0 tag
	*/
	public static void removeByLevel0Tag(java.lang.String level0Tag) {
		getPersistence().removeByLevel0Tag(level0Tag);
	}

	/**
	* Returns the number of item hierarchy masters where level0Tag = &#63;.
	*
	* @param level0Tag the level0 tag
	* @return the number of matching item hierarchy masters
	*/
	public static int countByLevel0Tag(java.lang.String level0Tag) {
		return getPersistence().countByLevel0Tag(level0Tag);
	}

	/**
	* Returns all the item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0) {
		return getPersistence().findByItemHierarchyUnique(level0);
	}

	/**
	* Returns a range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0, int start, int end) {
		return getPersistence().findByItemHierarchyUnique(level0, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .findByItemHierarchyUnique(level0, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param level0 the level0
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findByItemHierarchyUnique(
		java.lang.String level0, int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemHierarchyUnique(level0, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByItemHierarchyUnique_First(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByItemHierarchyUnique_First(level0, orderByComparator);
	}

	/**
	* Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByItemHierarchyUnique_First(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemHierarchyUnique_First(level0, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster findByItemHierarchyUnique_Last(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByItemHierarchyUnique_Last(level0, orderByComparator);
	}

	/**
	* Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	*/
	public static ItemHierarchyMaster fetchByItemHierarchyUnique_Last(
		java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemHierarchyUnique_Last(level0, orderByComparator);
	}

	/**
	* Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
	*
	* @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	* @param level0 the level0
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster[] findByItemHierarchyUnique_PrevAndNext(
		int itemHierarchyMasterSid, java.lang.String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence()
				   .findByItemHierarchyUnique_PrevAndNext(itemHierarchyMasterSid,
			level0, orderByComparator);
	}

	/**
	* Removes all the item hierarchy masters where level0 = &#63; from the database.
	*
	* @param level0 the level0
	*/
	public static void removeByItemHierarchyUnique(java.lang.String level0) {
		getPersistence().removeByItemHierarchyUnique(level0);
	}

	/**
	* Returns the number of item hierarchy masters where level0 = &#63;.
	*
	* @param level0 the level0
	* @return the number of matching item hierarchy masters
	*/
	public static int countByItemHierarchyUnique(java.lang.String level0) {
		return getPersistence().countByItemHierarchyUnique(level0);
	}

	/**
	* Caches the item hierarchy master in the entity cache if it is enabled.
	*
	* @param itemHierarchyMaster the item hierarchy master
	*/
	public static void cacheResult(ItemHierarchyMaster itemHierarchyMaster) {
		getPersistence().cacheResult(itemHierarchyMaster);
	}

	/**
	* Caches the item hierarchy masters in the entity cache if it is enabled.
	*
	* @param itemHierarchyMasters the item hierarchy masters
	*/
	public static void cacheResult(
		List<ItemHierarchyMaster> itemHierarchyMasters) {
		getPersistence().cacheResult(itemHierarchyMasters);
	}

	/**
	* Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
	*
	* @param itemHierarchyMasterSid the primary key for the new item hierarchy master
	* @return the new item hierarchy master
	*/
	public static ItemHierarchyMaster create(int itemHierarchyMasterSid) {
		return getPersistence().create(itemHierarchyMasterSid);
	}

	/**
	* Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master that was removed
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster remove(int itemHierarchyMasterSid)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence().remove(itemHierarchyMasterSid);
	}

	public static ItemHierarchyMaster updateImpl(
		ItemHierarchyMaster itemHierarchyMaster) {
		return getPersistence().updateImpl(itemHierarchyMaster);
	}

	/**
	* Returns the item hierarchy master with the primary key or throws a {@link NoSuchItemHierarchyMasterException} if it could not be found.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master
	* @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster findByPrimaryKey(
		int itemHierarchyMasterSid)
		throws com.stpl.app.exception.NoSuchItemHierarchyMasterException {
		return getPersistence().findByPrimaryKey(itemHierarchyMasterSid);
	}

	/**
	* Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemHierarchyMasterSid the primary key of the item hierarchy master
	* @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
	*/
	public static ItemHierarchyMaster fetchByPrimaryKey(
		int itemHierarchyMasterSid) {
		return getPersistence().fetchByPrimaryKey(itemHierarchyMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ItemHierarchyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item hierarchy masters.
	*
	* @return the item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @return the range of item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy masters
	* @param end the upper bound of the range of item hierarchy masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item hierarchy masters
	*/
	public static List<ItemHierarchyMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item hierarchy masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item hierarchy masters.
	*
	* @return the number of item hierarchy masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemHierarchyMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemHierarchyMasterPersistence, ItemHierarchyMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemHierarchyMasterPersistence.class);
}