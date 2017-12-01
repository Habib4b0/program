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

import com.stpl.app.model.ItemHierarchyDefMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the item hierarchy def master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemHierarchyDefMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ItemHierarchyDefMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemHierarchyDefMasterUtil {
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
	public static void clearCache(ItemHierarchyDefMaster itemHierarchyDefMaster) {
		getPersistence().clearCache(itemHierarchyDefMaster);
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
	public static List<ItemHierarchyDefMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemHierarchyDefMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemHierarchyDefMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemHierarchyDefMaster update(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		return getPersistence().update(itemHierarchyDefMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemHierarchyDefMaster update(
		ItemHierarchyDefMaster itemHierarchyDefMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(itemHierarchyDefMaster, serviceContext);
	}

	/**
	* Returns all the item hierarchy def masters where member = &#63;.
	*
	* @param member the member
	* @return the matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member) {
		return getPersistence().findByMember(member);
	}

	/**
	* Returns a range of all the item hierarchy def masters where member = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member, int start, int end) {
		return getPersistence().findByMember(member, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence()
				   .findByMember(member, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByMember(
		java.lang.String member, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMember(member, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByMember_First(
		java.lang.String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByMember_First(member, orderByComparator);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByMember_First(
		java.lang.String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().fetchByMember_First(member, orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByMember_Last(
		java.lang.String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByMember_Last(member, orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByMember_Last(
		java.lang.String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().fetchByMember_Last(member, orderByComparator);
	}

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param member the member
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster[] findByMember_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence()
				   .findByMember_PrevAndNext(itemHierarchyDefMasterSid, member,
			orderByComparator);
	}

	/**
	* Removes all the item hierarchy def masters where member = &#63; from the database.
	*
	* @param member the member
	*/
	public static void removeByMember(java.lang.String member) {
		getPersistence().removeByMember(member);
	}

	/**
	* Returns the number of item hierarchy def masters where member = &#63;.
	*
	* @param member the member
	* @return the number of matching item hierarchy def masters
	*/
	public static int countByMember(java.lang.String member) {
		return getPersistence().countByMember(member);
	}

	/**
	* Returns all the item hierarchy def masters where alias = &#63;.
	*
	* @param alias the alias
	* @return the matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias) {
		return getPersistence().findByAlias(alias);
	}

	/**
	* Returns a range of all the item hierarchy def masters where alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias, int start, int end) {
		return getPersistence().findByAlias(alias, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().findByAlias(alias, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByAlias(
		java.lang.String alias, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAlias(alias, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByAlias_First(
		java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByAlias_First(alias, orderByComparator);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByAlias_First(
		java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().fetchByAlias_First(alias, orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByAlias_Last(
		java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByAlias_Last(alias, orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByAlias_Last(
		java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().fetchByAlias_Last(alias, orderByComparator);
	}

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where alias = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster[] findByAlias_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence()
				   .findByAlias_PrevAndNext(itemHierarchyDefMasterSid, alias,
			orderByComparator);
	}

	/**
	* Removes all the item hierarchy def masters where alias = &#63; from the database.
	*
	* @param alias the alias
	*/
	public static void removeByAlias(java.lang.String alias) {
		getPersistence().removeByAlias(alias);
	}

	/**
	* Returns the number of item hierarchy def masters where alias = &#63;.
	*
	* @param alias the alias
	* @return the number of matching item hierarchy def masters
	*/
	public static int countByAlias(java.lang.String alias) {
		return getPersistence().countByAlias(alias);
	}

	/**
	* Returns all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @return the matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl) {
		return getPersistence().findByBpiLvl(bpiLvl);
	}

	/**
	* Returns a range of all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bpiLvl the bpi lvl
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl, int start, int end) {
		return getPersistence().findByBpiLvl(bpiLvl, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bpiLvl the bpi lvl
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence()
				   .findByBpiLvl(bpiLvl, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bpiLvl the bpi lvl
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByBpiLvl(
		java.lang.String bpiLvl, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBpiLvl(bpiLvl, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByBpiLvl_First(
		java.lang.String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByBpiLvl_First(bpiLvl, orderByComparator);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByBpiLvl_First(
		java.lang.String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().fetchByBpiLvl_First(bpiLvl, orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByBpiLvl_Last(
		java.lang.String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByBpiLvl_Last(bpiLvl, orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByBpiLvl_Last(
		java.lang.String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().fetchByBpiLvl_Last(bpiLvl, orderByComparator);
	}

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where bpiLvl = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param bpiLvl the bpi lvl
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster[] findByBpiLvl_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence()
				   .findByBpiLvl_PrevAndNext(itemHierarchyDefMasterSid, bpiLvl,
			orderByComparator);
	}

	/**
	* Removes all the item hierarchy def masters where bpiLvl = &#63; from the database.
	*
	* @param bpiLvl the bpi lvl
	*/
	public static void removeByBpiLvl(java.lang.String bpiLvl) {
		getPersistence().removeByBpiLvl(bpiLvl);
	}

	/**
	* Returns the number of item hierarchy def masters where bpiLvl = &#63;.
	*
	* @param bpiLvl the bpi lvl
	* @return the number of matching item hierarchy def masters
	*/
	public static int countByBpiLvl(java.lang.String bpiLvl) {
		return getPersistence().countByBpiLvl(bpiLvl);
	}

	/**
	* Returns all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @return the matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias) {
		return getPersistence().findByItemHierarchyDefUnique(member, alias);
	}

	/**
	* Returns a range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias, int start, int end) {
		return getPersistence()
				   .findByItemHierarchyDefUnique(member, alias, start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence()
				   .findByItemHierarchyDefUnique(member, alias, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param member the member
	* @param alias the alias
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		java.lang.String member, java.lang.String alias, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemHierarchyDefUnique(member, alias, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByItemHierarchyDefUnique_First(
		java.lang.String member, java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence()
				   .findByItemHierarchyDefUnique_First(member, alias,
			orderByComparator);
	}

	/**
	* Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_First(
		java.lang.String member, java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemHierarchyDefUnique_First(member, alias,
			orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster findByItemHierarchyDefUnique_Last(
		java.lang.String member, java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence()
				   .findByItemHierarchyDefUnique_Last(member, alias,
			orderByComparator);
	}

	/**
	* Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	*/
	public static ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_Last(
		java.lang.String member, java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence()
				   .fetchByItemHierarchyDefUnique_Last(member, alias,
			orderByComparator);
	}

	/**
	* Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	*
	* @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	* @param member the member
	* @param alias the alias
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster[] findByItemHierarchyDefUnique_PrevAndNext(
		int itemHierarchyDefMasterSid, java.lang.String member,
		java.lang.String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence()
				   .findByItemHierarchyDefUnique_PrevAndNext(itemHierarchyDefMasterSid,
			member, alias, orderByComparator);
	}

	/**
	* Removes all the item hierarchy def masters where member = &#63; and alias = &#63; from the database.
	*
	* @param member the member
	* @param alias the alias
	*/
	public static void removeByItemHierarchyDefUnique(java.lang.String member,
		java.lang.String alias) {
		getPersistence().removeByItemHierarchyDefUnique(member, alias);
	}

	/**
	* Returns the number of item hierarchy def masters where member = &#63; and alias = &#63;.
	*
	* @param member the member
	* @param alias the alias
	* @return the number of matching item hierarchy def masters
	*/
	public static int countByItemHierarchyDefUnique(java.lang.String member,
		java.lang.String alias) {
		return getPersistence().countByItemHierarchyDefUnique(member, alias);
	}

	/**
	* Caches the item hierarchy def master in the entity cache if it is enabled.
	*
	* @param itemHierarchyDefMaster the item hierarchy def master
	*/
	public static void cacheResult(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		getPersistence().cacheResult(itemHierarchyDefMaster);
	}

	/**
	* Caches the item hierarchy def masters in the entity cache if it is enabled.
	*
	* @param itemHierarchyDefMasters the item hierarchy def masters
	*/
	public static void cacheResult(
		List<ItemHierarchyDefMaster> itemHierarchyDefMasters) {
		getPersistence().cacheResult(itemHierarchyDefMasters);
	}

	/**
	* Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
	*
	* @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
	* @return the new item hierarchy def master
	*/
	public static ItemHierarchyDefMaster create(int itemHierarchyDefMasterSid) {
		return getPersistence().create(itemHierarchyDefMasterSid);
	}

	/**
	* Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master that was removed
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster remove(int itemHierarchyDefMasterSid)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().remove(itemHierarchyDefMasterSid);
	}

	public static ItemHierarchyDefMaster updateImpl(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		return getPersistence().updateImpl(itemHierarchyDefMaster);
	}

	/**
	* Returns the item hierarchy def master with the primary key or throws a {@link NoSuchItemHierarchyDefMasterException} if it could not be found.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master
	* @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster findByPrimaryKey(
		int itemHierarchyDefMasterSid)
		throws com.stpl.app.exception.NoSuchItemHierarchyDefMasterException {
		return getPersistence().findByPrimaryKey(itemHierarchyDefMasterSid);
	}

	/**
	* Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	* @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
	*/
	public static ItemHierarchyDefMaster fetchByPrimaryKey(
		int itemHierarchyDefMasterSid) {
		return getPersistence().fetchByPrimaryKey(itemHierarchyDefMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ItemHierarchyDefMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item hierarchy def masters.
	*
	* @return the item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @return the range of item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item hierarchy def masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item hierarchy def masters
	* @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item hierarchy def masters
	*/
	public static List<ItemHierarchyDefMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item hierarchy def masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item hierarchy def masters.
	*
	* @return the number of item hierarchy def masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemHierarchyDefMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemHierarchyDefMasterPersistence, ItemHierarchyDefMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemHierarchyDefMasterPersistence.class);
}