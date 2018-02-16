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

import com.stpl.app.model.PsModel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ps model service. This utility wraps {@link com.stpl.app.service.persistence.impl.PsModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsModelPersistence
 * @see com.stpl.app.service.persistence.impl.PsModelPersistenceImpl
 * @generated
 */
@ProviderType
public class PsModelUtil {
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
	public static void clearCache(PsModel psModel) {
		getPersistence().clearCache(psModel);
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
	public static List<PsModel> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PsModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PsModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PsModel update(PsModel psModel) {
		return getPersistence().update(psModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PsModel update(PsModel psModel, ServiceContext serviceContext) {
		return getPersistence().update(psModel, serviceContext);
	}

	/**
	* Returns all the ps models where psId = &#63;.
	*
	* @param psId the ps ID
	* @return the matching ps models
	*/
	public static List<PsModel> findBypsId(java.lang.String psId) {
		return getPersistence().findBypsId(psId);
	}

	/**
	* Returns a range of all the ps models where psId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psId the ps ID
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public static List<PsModel> findBypsId(java.lang.String psId, int start,
		int end) {
		return getPersistence().findBypsId(psId, start, end);
	}

	/**
	* Returns an ordered range of all the ps models where psId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psId the ps ID
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsId(java.lang.String psId, int start,
		int end, OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().findBypsId(psId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps models where psId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psId the ps ID
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsId(java.lang.String psId, int start,
		int end, OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBypsId(psId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsId_First(java.lang.String psId,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsId_First(psId, orderByComparator);
	}

	/**
	* Returns the first ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsId_First(java.lang.String psId,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsId_First(psId, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsId_Last(java.lang.String psId,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsId_Last(psId, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psId = &#63;.
	*
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsId_Last(java.lang.String psId,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsId_Last(psId, orderByComparator);
	}

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psId = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psId the ps ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel[] findBypsId_PrevAndNext(int psModelSid,
		java.lang.String psId, OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence()
				   .findBypsId_PrevAndNext(psModelSid, psId, orderByComparator);
	}

	/**
	* Removes all the ps models where psId = &#63; from the database.
	*
	* @param psId the ps ID
	*/
	public static void removeBypsId(java.lang.String psId) {
		getPersistence().removeBypsId(psId);
	}

	/**
	* Returns the number of ps models where psId = &#63;.
	*
	* @param psId the ps ID
	* @return the number of matching ps models
	*/
	public static int countBypsId(java.lang.String psId) {
		return getPersistence().countBypsId(psId);
	}

	/**
	* Returns all the ps models where psNo = &#63;.
	*
	* @param psNo the ps no
	* @return the matching ps models
	*/
	public static List<PsModel> findBypsNo(java.lang.String psNo) {
		return getPersistence().findBypsNo(psNo);
	}

	/**
	* Returns a range of all the ps models where psNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psNo the ps no
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public static List<PsModel> findBypsNo(java.lang.String psNo, int start,
		int end) {
		return getPersistence().findBypsNo(psNo, start, end);
	}

	/**
	* Returns an ordered range of all the ps models where psNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psNo the ps no
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsNo(java.lang.String psNo, int start,
		int end, OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().findBypsNo(psNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps models where psNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psNo the ps no
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsNo(java.lang.String psNo, int start,
		int end, OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBypsNo(psNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsNo_First(java.lang.String psNo,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsNo_First(psNo, orderByComparator);
	}

	/**
	* Returns the first ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsNo_First(java.lang.String psNo,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsNo_First(psNo, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsNo_Last(java.lang.String psNo,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsNo_Last(psNo, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psNo = &#63;.
	*
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsNo_Last(java.lang.String psNo,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsNo_Last(psNo, orderByComparator);
	}

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psNo = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psNo the ps no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel[] findBypsNo_PrevAndNext(int psModelSid,
		java.lang.String psNo, OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence()
				   .findBypsNo_PrevAndNext(psModelSid, psNo, orderByComparator);
	}

	/**
	* Removes all the ps models where psNo = &#63; from the database.
	*
	* @param psNo the ps no
	*/
	public static void removeBypsNo(java.lang.String psNo) {
		getPersistence().removeBypsNo(psNo);
	}

	/**
	* Returns the number of ps models where psNo = &#63;.
	*
	* @param psNo the ps no
	* @return the number of matching ps models
	*/
	public static int countBypsNo(java.lang.String psNo) {
		return getPersistence().countBypsNo(psNo);
	}

	/**
	* Returns all the ps models where psName = &#63;.
	*
	* @param psName the ps name
	* @return the matching ps models
	*/
	public static List<PsModel> findBypsName(java.lang.String psName) {
		return getPersistence().findBypsName(psName);
	}

	/**
	* Returns a range of all the ps models where psName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psName the ps name
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public static List<PsModel> findBypsName(java.lang.String psName,
		int start, int end) {
		return getPersistence().findBypsName(psName, start, end);
	}

	/**
	* Returns an ordered range of all the ps models where psName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psName the ps name
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsName(java.lang.String psName,
		int start, int end, OrderByComparator<PsModel> orderByComparator) {
		return getPersistence()
				   .findBypsName(psName, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps models where psName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psName the ps name
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsName(java.lang.String psName,
		int start, int end, OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBypsName(psName, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsName_First(java.lang.String psName,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsName_First(psName, orderByComparator);
	}

	/**
	* Returns the first ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsName_First(java.lang.String psName,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsName_First(psName, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsName_Last(java.lang.String psName,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsName_Last(psName, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psName = &#63;.
	*
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsName_Last(java.lang.String psName,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsName_Last(psName, orderByComparator);
	}

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psName = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psName the ps name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel[] findBypsName_PrevAndNext(int psModelSid,
		java.lang.String psName, OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence()
				   .findBypsName_PrevAndNext(psModelSid, psName,
			orderByComparator);
	}

	/**
	* Removes all the ps models where psName = &#63; from the database.
	*
	* @param psName the ps name
	*/
	public static void removeBypsName(java.lang.String psName) {
		getPersistence().removeBypsName(psName);
	}

	/**
	* Returns the number of ps models where psName = &#63;.
	*
	* @param psName the ps name
	* @return the number of matching ps models
	*/
	public static int countBypsName(java.lang.String psName) {
		return getPersistence().countBypsName(psName);
	}

	/**
	* Returns all the ps models where psType = &#63;.
	*
	* @param psType the ps type
	* @return the matching ps models
	*/
	public static List<PsModel> findBypsType(int psType) {
		return getPersistence().findBypsType(psType);
	}

	/**
	* Returns a range of all the ps models where psType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psType the ps type
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public static List<PsModel> findBypsType(int psType, int start, int end) {
		return getPersistence().findBypsType(psType, start, end);
	}

	/**
	* Returns an ordered range of all the ps models where psType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psType the ps type
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsType(int psType, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence()
				   .findBypsType(psType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps models where psType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psType the ps type
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsType(int psType, int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findBypsType(psType, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsType_First(int psType,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsType_First(psType, orderByComparator);
	}

	/**
	* Returns the first ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsType_First(int psType,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsType_First(psType, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsType_Last(int psType,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsType_Last(psType, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psType = &#63;.
	*
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsType_Last(int psType,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsType_Last(psType, orderByComparator);
	}

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psType = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psType the ps type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel[] findBypsType_PrevAndNext(int psModelSid,
		int psType, OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence()
				   .findBypsType_PrevAndNext(psModelSid, psType,
			orderByComparator);
	}

	/**
	* Removes all the ps models where psType = &#63; from the database.
	*
	* @param psType the ps type
	*/
	public static void removeBypsType(int psType) {
		getPersistence().removeBypsType(psType);
	}

	/**
	* Returns the number of ps models where psType = &#63;.
	*
	* @param psType the ps type
	* @return the number of matching ps models
	*/
	public static int countBypsType(int psType) {
		return getPersistence().countBypsType(psType);
	}

	/**
	* Returns all the ps models where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @return the matching ps models
	*/
	public static List<PsModel> findBypsStatus(int psStatus) {
		return getPersistence().findBypsStatus(psStatus);
	}

	/**
	* Returns a range of all the ps models where psStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psStatus the ps status
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of matching ps models
	*/
	public static List<PsModel> findBypsStatus(int psStatus, int start, int end) {
		return getPersistence().findBypsStatus(psStatus, start, end);
	}

	/**
	* Returns an ordered range of all the ps models where psStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psStatus the ps status
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsStatus(int psStatus, int start,
		int end, OrderByComparator<PsModel> orderByComparator) {
		return getPersistence()
				   .findBypsStatus(psStatus, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps models where psStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psStatus the ps status
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps models
	*/
	public static List<PsModel> findBypsStatus(int psStatus, int start,
		int end, OrderByComparator<PsModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBypsStatus(psStatus, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsStatus_First(int psStatus,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsStatus_First(psStatus, orderByComparator);
	}

	/**
	* Returns the first ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsStatus_First(int psStatus,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence()
				   .fetchBypsStatus_First(psStatus, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model
	* @throws NoSuchPsModelException if a matching ps model could not be found
	*/
	public static PsModel findBypsStatus_Last(int psStatus,
		OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findBypsStatus_Last(psStatus, orderByComparator);
	}

	/**
	* Returns the last ps model in the ordered set where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	*/
	public static PsModel fetchBypsStatus_Last(int psStatus,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().fetchBypsStatus_Last(psStatus, orderByComparator);
	}

	/**
	* Returns the ps models before and after the current ps model in the ordered set where psStatus = &#63;.
	*
	* @param psModelSid the primary key of the current ps model
	* @param psStatus the ps status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel[] findBypsStatus_PrevAndNext(int psModelSid,
		int psStatus, OrderByComparator<PsModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence()
				   .findBypsStatus_PrevAndNext(psModelSid, psStatus,
			orderByComparator);
	}

	/**
	* Removes all the ps models where psStatus = &#63; from the database.
	*
	* @param psStatus the ps status
	*/
	public static void removeBypsStatus(int psStatus) {
		getPersistence().removeBypsStatus(psStatus);
	}

	/**
	* Returns the number of ps models where psStatus = &#63;.
	*
	* @param psStatus the ps status
	* @return the number of matching ps models
	*/
	public static int countBypsStatus(int psStatus) {
		return getPersistence().countBypsStatus(psStatus);
	}

	/**
	* Caches the ps model in the entity cache if it is enabled.
	*
	* @param psModel the ps model
	*/
	public static void cacheResult(PsModel psModel) {
		getPersistence().cacheResult(psModel);
	}

	/**
	* Caches the ps models in the entity cache if it is enabled.
	*
	* @param psModels the ps models
	*/
	public static void cacheResult(List<PsModel> psModels) {
		getPersistence().cacheResult(psModels);
	}

	/**
	* Creates a new ps model with the primary key. Does not add the ps model to the database.
	*
	* @param psModelSid the primary key for the new ps model
	* @return the new ps model
	*/
	public static PsModel create(int psModelSid) {
		return getPersistence().create(psModelSid);
	}

	/**
	* Removes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model that was removed
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel remove(int psModelSid)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().remove(psModelSid);
	}

	public static PsModel updateImpl(PsModel psModel) {
		return getPersistence().updateImpl(psModel);
	}

	/**
	* Returns the ps model with the primary key or throws a {@link NoSuchPsModelException} if it could not be found.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model
	* @throws NoSuchPsModelException if a ps model with the primary key could not be found
	*/
	public static PsModel findByPrimaryKey(int psModelSid)
		throws com.stpl.app.exception.NoSuchPsModelException {
		return getPersistence().findByPrimaryKey(psModelSid);
	}

	/**
	* Returns the ps model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model, or <code>null</code> if a ps model with the primary key could not be found
	*/
	public static PsModel fetchByPrimaryKey(int psModelSid) {
		return getPersistence().fetchByPrimaryKey(psModelSid);
	}

	public static java.util.Map<java.io.Serializable, PsModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ps models.
	*
	* @return the ps models
	*/
	public static List<PsModel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of ps models
	*/
	public static List<PsModel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ps models
	*/
	public static List<PsModel> findAll(int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ps models
	*/
	public static List<PsModel> findAll(int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ps models from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ps models.
	*
	* @return the number of ps models
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PsModelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PsModelPersistence, PsModelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PsModelPersistence.class);
}