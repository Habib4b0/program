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

import com.stpl.app.model.IfpModel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ifp model service. This utility wraps {@link com.stpl.app.service.persistence.impl.IfpModelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpModelPersistence
 * @see com.stpl.app.service.persistence.impl.IfpModelPersistenceImpl
 * @generated
 */
@ProviderType
public class IfpModelUtil {
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
	public static void clearCache(IfpModel ifpModel) {
		getPersistence().clearCache(ifpModel);
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
	public static List<IfpModel> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IfpModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IfpModel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IfpModel update(IfpModel ifpModel) {
		return getPersistence().update(ifpModel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IfpModel update(IfpModel ifpModel,
		ServiceContext serviceContext) {
		return getPersistence().update(ifpModel, serviceContext);
	}

	/**
	* Returns all the ifp models where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @return the matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanId(java.lang.String ifpId) {
		return getPersistence().findByItemFamilyPlanId(ifpId);
	}

	/**
	* Returns a range of all the ifp models where ifpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpId the ifp ID
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId, int start, int end) {
		return getPersistence().findByItemFamilyPlanId(ifpId, start, end);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpId the ifp ID
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId, int start, int end,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .findByItemFamilyPlanId(ifpId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpId the ifp ID
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanId(
		java.lang.String ifpId, int start, int end,
		OrderByComparator<IfpModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemFamilyPlanId(ifpId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanId_First(
		java.lang.String ifpId, OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanId_First(ifpId, orderByComparator);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanId_First(
		java.lang.String ifpId, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanId_First(ifpId, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanId_Last(java.lang.String ifpId,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanId_Last(ifpId, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanId_Last(
		java.lang.String ifpId, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanId_Last(ifpId, orderByComparator);
	}

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpId = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpId the ifp ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel[] findByItemFamilyPlanId_PrevAndNext(
		int ifpModelSid, java.lang.String ifpId,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanId_PrevAndNext(ifpModelSid, ifpId,
			orderByComparator);
	}

	/**
	* Removes all the ifp models where ifpId = &#63; from the database.
	*
	* @param ifpId the ifp ID
	*/
	public static void removeByItemFamilyPlanId(java.lang.String ifpId) {
		getPersistence().removeByItemFamilyPlanId(ifpId);
	}

	/**
	* Returns the number of ifp models where ifpId = &#63;.
	*
	* @param ifpId the ifp ID
	* @return the number of matching ifp models
	*/
	public static int countByItemFamilyPlanId(java.lang.String ifpId) {
		return getPersistence().countByItemFamilyPlanId(ifpId);
	}

	/**
	* Returns all the ifp models where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @return the matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanNo(java.lang.String ifpNo) {
		return getPersistence().findByItemFamilyPlanNo(ifpNo);
	}

	/**
	* Returns a range of all the ifp models where ifpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpNo the ifp no
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo, int start, int end) {
		return getPersistence().findByItemFamilyPlanNo(ifpNo, start, end);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpNo the ifp no
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo, int start, int end,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .findByItemFamilyPlanNo(ifpNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpNo the ifp no
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanNo(
		java.lang.String ifpNo, int start, int end,
		OrderByComparator<IfpModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemFamilyPlanNo(ifpNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanNo_First(
		java.lang.String ifpNo, OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanNo_First(ifpNo, orderByComparator);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanNo_First(
		java.lang.String ifpNo, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanNo_First(ifpNo, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanNo_Last(java.lang.String ifpNo,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanNo_Last(ifpNo, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanNo_Last(
		java.lang.String ifpNo, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanNo_Last(ifpNo, orderByComparator);
	}

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpNo = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpNo the ifp no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel[] findByItemFamilyPlanNo_PrevAndNext(
		int ifpModelSid, java.lang.String ifpNo,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanNo_PrevAndNext(ifpModelSid, ifpNo,
			orderByComparator);
	}

	/**
	* Removes all the ifp models where ifpNo = &#63; from the database.
	*
	* @param ifpNo the ifp no
	*/
	public static void removeByItemFamilyPlanNo(java.lang.String ifpNo) {
		getPersistence().removeByItemFamilyPlanNo(ifpNo);
	}

	/**
	* Returns the number of ifp models where ifpNo = &#63;.
	*
	* @param ifpNo the ifp no
	* @return the number of matching ifp models
	*/
	public static int countByItemFamilyPlanNo(java.lang.String ifpNo) {
		return getPersistence().countByItemFamilyPlanNo(ifpNo);
	}

	/**
	* Returns all the ifp models where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @return the matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName) {
		return getPersistence().findByItemFamilyPlanName(ifpName);
	}

	/**
	* Returns a range of all the ifp models where ifpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpName the ifp name
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName, int start, int end) {
		return getPersistence().findByItemFamilyPlanName(ifpName, start, end);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpName the ifp name
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName, int start, int end,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .findByItemFamilyPlanName(ifpName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpName the ifp name
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanName(
		java.lang.String ifpName, int start, int end,
		OrderByComparator<IfpModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemFamilyPlanName(ifpName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanName_First(
		java.lang.String ifpName, OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanName_First(ifpName, orderByComparator);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanName_First(
		java.lang.String ifpName, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanName_First(ifpName, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanName_Last(
		java.lang.String ifpName, OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanName_Last(ifpName, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanName_Last(
		java.lang.String ifpName, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanName_Last(ifpName, orderByComparator);
	}

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpName = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpName the ifp name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel[] findByItemFamilyPlanName_PrevAndNext(
		int ifpModelSid, java.lang.String ifpName,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanName_PrevAndNext(ifpModelSid, ifpName,
			orderByComparator);
	}

	/**
	* Removes all the ifp models where ifpName = &#63; from the database.
	*
	* @param ifpName the ifp name
	*/
	public static void removeByItemFamilyPlanName(java.lang.String ifpName) {
		getPersistence().removeByItemFamilyPlanName(ifpName);
	}

	/**
	* Returns the number of ifp models where ifpName = &#63;.
	*
	* @param ifpName the ifp name
	* @return the number of matching ifp models
	*/
	public static int countByItemFamilyPlanName(java.lang.String ifpName) {
		return getPersistence().countByItemFamilyPlanName(ifpName);
	}

	/**
	* Returns all the ifp models where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @return the matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanType(int ifpType) {
		return getPersistence().findByItemFamilyPlanType(ifpType);
	}

	/**
	* Returns a range of all the ifp models where ifpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpType the ifp type
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanType(int ifpType,
		int start, int end) {
		return getPersistence().findByItemFamilyPlanType(ifpType, start, end);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpType the ifp type
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanType(int ifpType,
		int start, int end, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .findByItemFamilyPlanType(ifpType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpType the ifp type
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanType(int ifpType,
		int start, int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemFamilyPlanType(ifpType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanType_First(int ifpType,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanType_First(ifpType, orderByComparator);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanType_First(int ifpType,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanType_First(ifpType, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanType_Last(int ifpType,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanType_Last(ifpType, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanType_Last(int ifpType,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanType_Last(ifpType, orderByComparator);
	}

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpType = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpType the ifp type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel[] findByItemFamilyPlanType_PrevAndNext(
		int ifpModelSid, int ifpType,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanType_PrevAndNext(ifpModelSid, ifpType,
			orderByComparator);
	}

	/**
	* Removes all the ifp models where ifpType = &#63; from the database.
	*
	* @param ifpType the ifp type
	*/
	public static void removeByItemFamilyPlanType(int ifpType) {
		getPersistence().removeByItemFamilyPlanType(ifpType);
	}

	/**
	* Returns the number of ifp models where ifpType = &#63;.
	*
	* @param ifpType the ifp type
	* @return the number of matching ifp models
	*/
	public static int countByItemFamilyPlanType(int ifpType) {
		return getPersistence().countByItemFamilyPlanType(ifpType);
	}

	/**
	* Returns all the ifp models where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @return the matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus) {
		return getPersistence().findByItemFamilyPlanStatus(ifpStatus);
	}

	/**
	* Returns a range of all the ifp models where ifpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpStatus the ifp status
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus,
		int start, int end) {
		return getPersistence().findByItemFamilyPlanStatus(ifpStatus, start, end);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpStatus the ifp status
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus,
		int start, int end, OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .findByItemFamilyPlanStatus(ifpStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp models where ifpStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpStatus the ifp status
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp models
	*/
	public static List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus,
		int start, int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemFamilyPlanStatus(ifpStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanStatus_First(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanStatus_First(ifpStatus,
			orderByComparator);
	}

	/**
	* Returns the first ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanStatus_First(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanStatus_First(ifpStatus,
			orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model
	* @throws NoSuchIfpModelException if a matching ifp model could not be found
	*/
	public static IfpModel findByItemFamilyPlanStatus_Last(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanStatus_Last(ifpStatus, orderByComparator);
	}

	/**
	* Returns the last ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	*/
	public static IfpModel fetchByItemFamilyPlanStatus_Last(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanStatus_Last(ifpStatus,
			orderByComparator);
	}

	/**
	* Returns the ifp models before and after the current ifp model in the ordered set where ifpStatus = &#63;.
	*
	* @param ifpModelSid the primary key of the current ifp model
	* @param ifpStatus the ifp status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel[] findByItemFamilyPlanStatus_PrevAndNext(
		int ifpModelSid, int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence()
				   .findByItemFamilyPlanStatus_PrevAndNext(ifpModelSid,
			ifpStatus, orderByComparator);
	}

	/**
	* Removes all the ifp models where ifpStatus = &#63; from the database.
	*
	* @param ifpStatus the ifp status
	*/
	public static void removeByItemFamilyPlanStatus(int ifpStatus) {
		getPersistence().removeByItemFamilyPlanStatus(ifpStatus);
	}

	/**
	* Returns the number of ifp models where ifpStatus = &#63;.
	*
	* @param ifpStatus the ifp status
	* @return the number of matching ifp models
	*/
	public static int countByItemFamilyPlanStatus(int ifpStatus) {
		return getPersistence().countByItemFamilyPlanStatus(ifpStatus);
	}

	/**
	* Caches the ifp model in the entity cache if it is enabled.
	*
	* @param ifpModel the ifp model
	*/
	public static void cacheResult(IfpModel ifpModel) {
		getPersistence().cacheResult(ifpModel);
	}

	/**
	* Caches the ifp models in the entity cache if it is enabled.
	*
	* @param ifpModels the ifp models
	*/
	public static void cacheResult(List<IfpModel> ifpModels) {
		getPersistence().cacheResult(ifpModels);
	}

	/**
	* Creates a new ifp model with the primary key. Does not add the ifp model to the database.
	*
	* @param ifpModelSid the primary key for the new ifp model
	* @return the new ifp model
	*/
	public static IfpModel create(int ifpModelSid) {
		return getPersistence().create(ifpModelSid);
	}

	/**
	* Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model that was removed
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel remove(int ifpModelSid)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence().remove(ifpModelSid);
	}

	public static IfpModel updateImpl(IfpModel ifpModel) {
		return getPersistence().updateImpl(ifpModel);
	}

	/**
	* Returns the ifp model with the primary key or throws a {@link NoSuchIfpModelException} if it could not be found.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model
	* @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	*/
	public static IfpModel findByPrimaryKey(int ifpModelSid)
		throws com.stpl.app.exception.NoSuchIfpModelException {
		return getPersistence().findByPrimaryKey(ifpModelSid);
	}

	/**
	* Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ifpModelSid the primary key of the ifp model
	* @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
	*/
	public static IfpModel fetchByPrimaryKey(int ifpModelSid) {
		return getPersistence().fetchByPrimaryKey(ifpModelSid);
	}

	public static java.util.Map<java.io.Serializable, IfpModel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ifp models.
	*
	* @return the ifp models
	*/
	public static List<IfpModel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @return the range of ifp models
	*/
	public static List<IfpModel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ifp models
	*/
	public static List<IfpModel> findAll(int start, int end,
		OrderByComparator<IfpModel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp models
	* @param end the upper bound of the range of ifp models (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ifp models
	*/
	public static List<IfpModel> findAll(int start, int end,
		OrderByComparator<IfpModel> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ifp models from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ifp models.
	*
	* @return the number of ifp models
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IfpModelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IfpModelPersistence, IfpModelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IfpModelPersistence.class);
}