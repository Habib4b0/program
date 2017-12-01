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

import com.stpl.app.model.RsModel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the rs model service. This utility wraps {@link com.stpl.app.service.persistence.impl.RsModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsModelPersistence
 * @see com.stpl.app.service.persistence.impl.RsModelPersistenceImpl
 * @generated
 */
@ProviderType
public class RsModelUtil {
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
	public static void clearCache(RsModel rsModel) {
		getPersistence().clearCache(rsModel);
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
	public static List<RsModel> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RsModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RsModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RsModel update(RsModel rsModel) {
		return getPersistence().update(rsModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RsModel update(RsModel rsModel, ServiceContext serviceContext) {
		return getPersistence().update(rsModel, serviceContext);
	}

	/**
	* Returns all the rs models where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @return the matching rs models
	*/
	public static List<RsModel> findByRebateScheduleId(java.lang.String rsId) {
		return getPersistence().findByRebateScheduleId(rsId);
	}

	/**
	* Returns a range of all the rs models where rsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsId the rs ID
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleId(java.lang.String rsId,
		int start, int end) {
		return getPersistence().findByRebateScheduleId(rsId, start, end);
	}

	/**
	* Returns an ordered range of all the rs models where rsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsId the rs ID
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleId(java.lang.String rsId,
		int start, int end, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findByRebateScheduleId(rsId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models where rsId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsId the rs ID
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleId(java.lang.String rsId,
		int start, int end, OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateScheduleId(rsId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleId_First(java.lang.String rsId,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleId_First(rsId, orderByComparator);
	}

	/**
	* Returns the first rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleId_First(java.lang.String rsId,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleId_First(rsId, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleId_Last(java.lang.String rsId,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleId_Last(rsId, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleId_Last(java.lang.String rsId,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleId_Last(rsId, orderByComparator);
	}

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsId = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsId the rs ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel[] findByRebateScheduleId_PrevAndNext(int rsModelSid,
		java.lang.String rsId, OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleId_PrevAndNext(rsModelSid, rsId,
			orderByComparator);
	}

	/**
	* Removes all the rs models where rsId = &#63; from the database.
	*
	* @param rsId the rs ID
	*/
	public static void removeByRebateScheduleId(java.lang.String rsId) {
		getPersistence().removeByRebateScheduleId(rsId);
	}

	/**
	* Returns the number of rs models where rsId = &#63;.
	*
	* @param rsId the rs ID
	* @return the number of matching rs models
	*/
	public static int countByRebateScheduleId(java.lang.String rsId) {
		return getPersistence().countByRebateScheduleId(rsId);
	}

	/**
	* Returns all the rs models where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @return the matching rs models
	*/
	public static List<RsModel> findByRebateScheduleNo(java.lang.String rsNo) {
		return getPersistence().findByRebateScheduleNo(rsNo);
	}

	/**
	* Returns a range of all the rs models where rsNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsNo the rs no
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleNo(java.lang.String rsNo,
		int start, int end) {
		return getPersistence().findByRebateScheduleNo(rsNo, start, end);
	}

	/**
	* Returns an ordered range of all the rs models where rsNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsNo the rs no
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleNo(java.lang.String rsNo,
		int start, int end, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findByRebateScheduleNo(rsNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models where rsNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsNo the rs no
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleNo(java.lang.String rsNo,
		int start, int end, OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateScheduleNo(rsNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleNo_First(java.lang.String rsNo,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleNo_First(rsNo, orderByComparator);
	}

	/**
	* Returns the first rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleNo_First(java.lang.String rsNo,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleNo_First(rsNo, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleNo_Last(java.lang.String rsNo,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleNo_Last(rsNo, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleNo_Last(java.lang.String rsNo,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleNo_Last(rsNo, orderByComparator);
	}

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsNo = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsNo the rs no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel[] findByRebateScheduleNo_PrevAndNext(int rsModelSid,
		java.lang.String rsNo, OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleNo_PrevAndNext(rsModelSid, rsNo,
			orderByComparator);
	}

	/**
	* Removes all the rs models where rsNo = &#63; from the database.
	*
	* @param rsNo the rs no
	*/
	public static void removeByRebateScheduleNo(java.lang.String rsNo) {
		getPersistence().removeByRebateScheduleNo(rsNo);
	}

	/**
	* Returns the number of rs models where rsNo = &#63;.
	*
	* @param rsNo the rs no
	* @return the number of matching rs models
	*/
	public static int countByRebateScheduleNo(java.lang.String rsNo) {
		return getPersistence().countByRebateScheduleNo(rsNo);
	}

	/**
	* Returns all the rs models where rsName = &#63;.
	*
	* @param rsName the rs name
	* @return the matching rs models
	*/
	public static List<RsModel> findByRebateScheduleName(
		java.lang.String rsName) {
		return getPersistence().findByRebateScheduleName(rsName);
	}

	/**
	* Returns a range of all the rs models where rsName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsName the rs name
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleName(
		java.lang.String rsName, int start, int end) {
		return getPersistence().findByRebateScheduleName(rsName, start, end);
	}

	/**
	* Returns an ordered range of all the rs models where rsName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsName the rs name
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleName(
		java.lang.String rsName, int start, int end,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findByRebateScheduleName(rsName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models where rsName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsName the rs name
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleName(
		java.lang.String rsName, int start, int end,
		OrderByComparator<RsModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateScheduleName(rsName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleName_First(
		java.lang.String rsName, OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleName_First(rsName, orderByComparator);
	}

	/**
	* Returns the first rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleName_First(
		java.lang.String rsName, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleName_First(rsName, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleName_Last(
		java.lang.String rsName, OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleName_Last(rsName, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsName = &#63;.
	*
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleName_Last(
		java.lang.String rsName, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleName_Last(rsName, orderByComparator);
	}

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsName = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsName the rs name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel[] findByRebateScheduleName_PrevAndNext(
		int rsModelSid, java.lang.String rsName,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleName_PrevAndNext(rsModelSid, rsName,
			orderByComparator);
	}

	/**
	* Removes all the rs models where rsName = &#63; from the database.
	*
	* @param rsName the rs name
	*/
	public static void removeByRebateScheduleName(java.lang.String rsName) {
		getPersistence().removeByRebateScheduleName(rsName);
	}

	/**
	* Returns the number of rs models where rsName = &#63;.
	*
	* @param rsName the rs name
	* @return the number of matching rs models
	*/
	public static int countByRebateScheduleName(java.lang.String rsName) {
		return getPersistence().countByRebateScheduleName(rsName);
	}

	/**
	* Returns all the rs models where rsType = &#63;.
	*
	* @param rsType the rs type
	* @return the matching rs models
	*/
	public static List<RsModel> findByRebateScheduleType(int rsType) {
		return getPersistence().findByRebateScheduleType(rsType);
	}

	/**
	* Returns a range of all the rs models where rsType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsType the rs type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleType(int rsType, int start,
		int end) {
		return getPersistence().findByRebateScheduleType(rsType, start, end);
	}

	/**
	* Returns an ordered range of all the rs models where rsType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsType the rs type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleType(int rsType, int start,
		int end, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findByRebateScheduleType(rsType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models where rsType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsType the rs type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleType(int rsType, int start,
		int end, OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateScheduleType(rsType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleType_First(int rsType,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleType_First(rsType, orderByComparator);
	}

	/**
	* Returns the first rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleType_First(int rsType,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleType_First(rsType, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleType_Last(int rsType,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleType_Last(rsType, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsType = &#63;.
	*
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleType_Last(int rsType,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleType_Last(rsType, orderByComparator);
	}

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsType = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsType the rs type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel[] findByRebateScheduleType_PrevAndNext(
		int rsModelSid, int rsType, OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleType_PrevAndNext(rsModelSid, rsType,
			orderByComparator);
	}

	/**
	* Removes all the rs models where rsType = &#63; from the database.
	*
	* @param rsType the rs type
	*/
	public static void removeByRebateScheduleType(int rsType) {
		getPersistence().removeByRebateScheduleType(rsType);
	}

	/**
	* Returns the number of rs models where rsType = &#63;.
	*
	* @param rsType the rs type
	* @return the number of matching rs models
	*/
	public static int countByRebateScheduleType(int rsType) {
		return getPersistence().countByRebateScheduleType(rsType);
	}

	/**
	* Returns all the rs models where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @return the matching rs models
	*/
	public static List<RsModel> findByRebateScheduleStatus(int rsStatus) {
		return getPersistence().findByRebateScheduleStatus(rsStatus);
	}

	/**
	* Returns a range of all the rs models where rsStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsStatus the rs status
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleStatus(int rsStatus,
		int start, int end) {
		return getPersistence().findByRebateScheduleStatus(rsStatus, start, end);
	}

	/**
	* Returns an ordered range of all the rs models where rsStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsStatus the rs status
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleStatus(int rsStatus,
		int start, int end, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findByRebateScheduleStatus(rsStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models where rsStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsStatus the rs status
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateScheduleStatus(int rsStatus,
		int start, int end, OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateScheduleStatus(rsStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleStatus_First(int rsStatus,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleStatus_First(rsStatus, orderByComparator);
	}

	/**
	* Returns the first rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleStatus_First(int rsStatus,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleStatus_First(rsStatus,
			orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateScheduleStatus_Last(int rsStatus,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleStatus_Last(rsStatus, orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateScheduleStatus_Last(int rsStatus,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleStatus_Last(rsStatus, orderByComparator);
	}

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rsStatus = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rsStatus the rs status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel[] findByRebateScheduleStatus_PrevAndNext(
		int rsModelSid, int rsStatus,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateScheduleStatus_PrevAndNext(rsModelSid,
			rsStatus, orderByComparator);
	}

	/**
	* Removes all the rs models where rsStatus = &#63; from the database.
	*
	* @param rsStatus the rs status
	*/
	public static void removeByRebateScheduleStatus(int rsStatus) {
		getPersistence().removeByRebateScheduleStatus(rsStatus);
	}

	/**
	* Returns the number of rs models where rsStatus = &#63;.
	*
	* @param rsStatus the rs status
	* @return the number of matching rs models
	*/
	public static int countByRebateScheduleStatus(int rsStatus) {
		return getPersistence().countByRebateScheduleStatus(rsStatus);
	}

	/**
	* Returns all the rs models where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @return the matching rs models
	*/
	public static List<RsModel> findByRebateProgramType(int rebateProgramType) {
		return getPersistence().findByRebateProgramType(rebateProgramType);
	}

	/**
	* Returns a range of all the rs models where rebateProgramType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebateProgramType the rebate program type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of matching rs models
	*/
	public static List<RsModel> findByRebateProgramType(int rebateProgramType,
		int start, int end) {
		return getPersistence()
				   .findByRebateProgramType(rebateProgramType, start, end);
	}

	/**
	* Returns an ordered range of all the rs models where rebateProgramType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebateProgramType the rebate program type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateProgramType(int rebateProgramType,
		int start, int end, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .findByRebateProgramType(rebateProgramType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models where rebateProgramType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebateProgramType the rebate program type
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs models
	*/
	public static List<RsModel> findByRebateProgramType(int rebateProgramType,
		int start, int end, OrderByComparator<RsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateProgramType(rebateProgramType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateProgramType_First(int rebateProgramType,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateProgramType_First(rebateProgramType,
			orderByComparator);
	}

	/**
	* Returns the first rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateProgramType_First(
		int rebateProgramType, OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateProgramType_First(rebateProgramType,
			orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model
	* @throws NoSuchRsModelException if a matching rs model could not be found
	*/
	public static RsModel findByRebateProgramType_Last(int rebateProgramType,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateProgramType_Last(rebateProgramType,
			orderByComparator);
	}

	/**
	* Returns the last rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs model, or <code>null</code> if a matching rs model could not be found
	*/
	public static RsModel fetchByRebateProgramType_Last(int rebateProgramType,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence()
				   .fetchByRebateProgramType_Last(rebateProgramType,
			orderByComparator);
	}

	/**
	* Returns the rs models before and after the current rs model in the ordered set where rebateProgramType = &#63;.
	*
	* @param rsModelSid the primary key of the current rs model
	* @param rebateProgramType the rebate program type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel[] findByRebateProgramType_PrevAndNext(
		int rsModelSid, int rebateProgramType,
		OrderByComparator<RsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence()
				   .findByRebateProgramType_PrevAndNext(rsModelSid,
			rebateProgramType, orderByComparator);
	}

	/**
	* Removes all the rs models where rebateProgramType = &#63; from the database.
	*
	* @param rebateProgramType the rebate program type
	*/
	public static void removeByRebateProgramType(int rebateProgramType) {
		getPersistence().removeByRebateProgramType(rebateProgramType);
	}

	/**
	* Returns the number of rs models where rebateProgramType = &#63;.
	*
	* @param rebateProgramType the rebate program type
	* @return the number of matching rs models
	*/
	public static int countByRebateProgramType(int rebateProgramType) {
		return getPersistence().countByRebateProgramType(rebateProgramType);
	}

	/**
	* Caches the rs model in the entity cache if it is enabled.
	*
	* @param rsModel the rs model
	*/
	public static void cacheResult(RsModel rsModel) {
		getPersistence().cacheResult(rsModel);
	}

	/**
	* Caches the rs models in the entity cache if it is enabled.
	*
	* @param rsModels the rs models
	*/
	public static void cacheResult(List<RsModel> rsModels) {
		getPersistence().cacheResult(rsModels);
	}

	/**
	* Creates a new rs model with the primary key. Does not add the rs model to the database.
	*
	* @param rsModelSid the primary key for the new rs model
	* @return the new rs model
	*/
	public static RsModel create(int rsModelSid) {
		return getPersistence().create(rsModelSid);
	}

	/**
	* Removes the rs model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsModelSid the primary key of the rs model
	* @return the rs model that was removed
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel remove(int rsModelSid)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence().remove(rsModelSid);
	}

	public static RsModel updateImpl(RsModel rsModel) {
		return getPersistence().updateImpl(rsModel);
	}

	/**
	* Returns the rs model with the primary key or throws a {@link NoSuchRsModelException} if it could not be found.
	*
	* @param rsModelSid the primary key of the rs model
	* @return the rs model
	* @throws NoSuchRsModelException if a rs model with the primary key could not be found
	*/
	public static RsModel findByPrimaryKey(int rsModelSid)
		throws com.stpl.app.exception.NoSuchRsModelException {
		return getPersistence().findByPrimaryKey(rsModelSid);
	}

	/**
	* Returns the rs model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsModelSid the primary key of the rs model
	* @return the rs model, or <code>null</code> if a rs model with the primary key could not be found
	*/
	public static RsModel fetchByPrimaryKey(int rsModelSid) {
		return getPersistence().fetchByPrimaryKey(rsModelSid);
	}

	public static java.util.Map<java.io.Serializable, RsModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the rs models.
	*
	* @return the rs models
	*/
	public static List<RsModel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the rs models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @return the range of rs models
	*/
	public static List<RsModel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the rs models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs models
	*/
	public static List<RsModel> findAll(int start, int end,
		OrderByComparator<RsModel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs models
	* @param end the upper bound of the range of rs models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs models
	*/
	public static List<RsModel> findAll(int start, int end,
		OrderByComparator<RsModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the rs models from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of rs models.
	*
	* @return the number of rs models
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RsModelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RsModelPersistence, RsModelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RsModelPersistence.class);
}