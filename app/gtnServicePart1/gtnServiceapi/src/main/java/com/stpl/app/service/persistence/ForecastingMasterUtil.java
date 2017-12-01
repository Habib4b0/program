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

import com.stpl.app.model.ForecastingMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the forecasting master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ForecastingMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ForecastingMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ForecastingMasterUtil {
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
	public static void clearCache(ForecastingMaster forecastingMaster) {
		getPersistence().clearCache(forecastingMaster);
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
	public static List<ForecastingMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ForecastingMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ForecastingMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ForecastingMaster update(ForecastingMaster forecastingMaster) {
		return getPersistence().update(forecastingMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ForecastingMaster update(
		ForecastingMaster forecastingMaster, ServiceContext serviceContext) {
		return getPersistence().update(forecastingMaster, serviceContext);
	}

	/**
	* Returns all the forecasting masters where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear) {
		return getPersistence().findByForecastYear(forecastYear);
	}

	/**
	* Returns a range of all the forecasting masters where forecastYear = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastYear the forecast year
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear, int start, int end) {
		return getPersistence().findByForecastYear(forecastYear, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastYear = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastYear the forecast year
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findByForecastYear(forecastYear, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastYear = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastYear the forecast year
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByForecastYear(forecastYear, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastYear_First(
		java.lang.String forecastYear,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastYear_First(forecastYear, orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastYear_First(
		java.lang.String forecastYear,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastYear_First(forecastYear, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastYear_Last(
		java.lang.String forecastYear,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastYear_Last(forecastYear, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastYear_Last(
		java.lang.String forecastYear,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastYear_Last(forecastYear, orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByForecastYear_PrevAndNext(
		int forecastMasterSid, java.lang.String forecastYear,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastYear_PrevAndNext(forecastMasterSid,
			forecastYear, orderByComparator);
	}

	/**
	* Removes all the forecasting masters where forecastYear = &#63; from the database.
	*
	* @param forecastYear the forecast year
	*/
	public static void removeByForecastYear(java.lang.String forecastYear) {
		getPersistence().removeByForecastYear(forecastYear);
	}

	/**
	* Returns the number of forecasting masters where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @return the number of matching forecasting masters
	*/
	public static int countByForecastYear(java.lang.String forecastYear) {
		return getPersistence().countByForecastYear(forecastYear);
	}

	/**
	* Returns all the forecasting masters where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth) {
		return getPersistence().findByForecastMonth(forecastMonth);
	}

	/**
	* Returns a range of all the forecasting masters where forecastMonth = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastMonth the forecast month
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth, int start, int end) {
		return getPersistence().findByForecastMonth(forecastMonth, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastMonth = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastMonth the forecast month
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findByForecastMonth(forecastMonth, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastMonth = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastMonth the forecast month
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByForecastMonth(forecastMonth, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastMonth_First(
		java.lang.String forecastMonth,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastMonth_First(forecastMonth, orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastMonth_First(
		java.lang.String forecastMonth,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastMonth_First(forecastMonth, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastMonth_Last(
		java.lang.String forecastMonth,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastMonth_Last(forecastMonth, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastMonth_Last(
		java.lang.String forecastMonth,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastMonth_Last(forecastMonth, orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByForecastMonth_PrevAndNext(
		int forecastMasterSid, java.lang.String forecastMonth,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastMonth_PrevAndNext(forecastMasterSid,
			forecastMonth, orderByComparator);
	}

	/**
	* Removes all the forecasting masters where forecastMonth = &#63; from the database.
	*
	* @param forecastMonth the forecast month
	*/
	public static void removeByForecastMonth(java.lang.String forecastMonth) {
		getPersistence().removeByForecastMonth(forecastMonth);
	}

	/**
	* Returns the number of forecasting masters where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @return the number of matching forecasting masters
	*/
	public static int countByForecastMonth(java.lang.String forecastMonth) {
		return getPersistence().countByForecastMonth(forecastMonth);
	}

	/**
	* Returns all the forecasting masters where country = &#63;.
	*
	* @param country the country
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCountry(
		java.lang.String country) {
		return getPersistence().findByCountry(country);
	}

	/**
	* Returns a range of all the forecasting masters where country = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param country the country
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCountry(
		java.lang.String country, int start, int end) {
		return getPersistence().findByCountry(country, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where country = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param country the country
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCountry(
		java.lang.String country, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findByCountry(country, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where country = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param country the country
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCountry(
		java.lang.String country, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCountry(country, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByCountry_First(
		java.lang.String country,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence().findByCountry_First(country, orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByCountry_First(
		java.lang.String country,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence().fetchByCountry_First(country, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByCountry_Last(
		java.lang.String country,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence().findByCountry_Last(country, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByCountry_Last(
		java.lang.String country,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence().fetchByCountry_Last(country, orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where country = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByCountry_PrevAndNext(
		int forecastMasterSid, java.lang.String country,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByCountry_PrevAndNext(forecastMasterSid, country,
			orderByComparator);
	}

	/**
	* Removes all the forecasting masters where country = &#63; from the database.
	*
	* @param country the country
	*/
	public static void removeByCountry(java.lang.String country) {
		getPersistence().removeByCountry(country);
	}

	/**
	* Returns the number of forecasting masters where country = &#63;.
	*
	* @param country the country
	* @return the number of matching forecasting masters
	*/
	public static int countByCountry(java.lang.String country) {
		return getPersistence().countByCountry(country);
	}

	/**
	* Returns all the forecasting masters where ndc = &#63;.
	*
	* @param ndc the ndc
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByNdc(java.lang.String ndc) {
		return getPersistence().findByNdc(ndc);
	}

	/**
	* Returns a range of all the forecasting masters where ndc = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc the ndc
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByNdc(java.lang.String ndc,
		int start, int end) {
		return getPersistence().findByNdc(ndc, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where ndc = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc the ndc
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByNdc(java.lang.String ndc,
		int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence().findByNdc(ndc, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where ndc = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc the ndc
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByNdc(java.lang.String ndc,
		int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNdc(ndc, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByNdc_First(java.lang.String ndc,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence().findByNdc_First(ndc, orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByNdc_First(java.lang.String ndc,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence().fetchByNdc_First(ndc, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByNdc_Last(java.lang.String ndc,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence().findByNdc_Last(ndc, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByNdc_Last(java.lang.String ndc,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence().fetchByNdc_Last(ndc, orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where ndc = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByNdc_PrevAndNext(
		int forecastMasterSid, java.lang.String ndc,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByNdc_PrevAndNext(forecastMasterSid, ndc,
			orderByComparator);
	}

	/**
	* Removes all the forecasting masters where ndc = &#63; from the database.
	*
	* @param ndc the ndc
	*/
	public static void removeByNdc(java.lang.String ndc) {
		getPersistence().removeByNdc(ndc);
	}

	/**
	* Returns the number of forecasting masters where ndc = &#63;.
	*
	* @param ndc the ndc
	* @return the number of matching forecasting masters
	*/
	public static int countByNdc(java.lang.String ndc) {
		return getPersistence().countByNdc(ndc);
	}

	/**
	* Returns all the forecasting masters where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate) {
		return getPersistence().findByForecastStartDate(forecastStartDate);
	}

	/**
	* Returns a range of all the forecasting masters where forecastStartDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastStartDate the forecast start date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate, int start, int end) {
		return getPersistence()
				   .findByForecastStartDate(forecastStartDate, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastStartDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastStartDate the forecast start date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findByForecastStartDate(forecastStartDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastStartDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastStartDate the forecast start date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate, int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByForecastStartDate(forecastStartDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastStartDate_First(
		Date forecastStartDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastStartDate_First(forecastStartDate,
			orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastStartDate_First(
		Date forecastStartDate,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastStartDate_First(forecastStartDate,
			orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastStartDate_Last(
		Date forecastStartDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastStartDate_Last(forecastStartDate,
			orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastStartDate_Last(
		Date forecastStartDate,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastStartDate_Last(forecastStartDate,
			orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByForecastStartDate_PrevAndNext(
		int forecastMasterSid, Date forecastStartDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastStartDate_PrevAndNext(forecastMasterSid,
			forecastStartDate, orderByComparator);
	}

	/**
	* Removes all the forecasting masters where forecastStartDate = &#63; from the database.
	*
	* @param forecastStartDate the forecast start date
	*/
	public static void removeByForecastStartDate(Date forecastStartDate) {
		getPersistence().removeByForecastStartDate(forecastStartDate);
	}

	/**
	* Returns the number of forecasting masters where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @return the number of matching forecasting masters
	*/
	public static int countByForecastStartDate(Date forecastStartDate) {
		return getPersistence().countByForecastStartDate(forecastStartDate);
	}

	/**
	* Returns all the forecasting masters where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCreatedDate(Date createdDate) {
		return getPersistence().findByCreatedDate(createdDate);
	}

	/**
	* Returns a range of all the forecasting masters where createdDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createdDate the created date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCreatedDate(Date createdDate,
		int start, int end) {
		return getPersistence().findByCreatedDate(createdDate, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where createdDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createdDate the created date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCreatedDate(Date createdDate,
		int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findByCreatedDate(createdDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where createdDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createdDate the created date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByCreatedDate(Date createdDate,
		int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCreatedDate(createdDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByCreatedDate_First(Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByCreatedDate_First(createdDate, orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByCreatedDate_First(Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCreatedDate_First(createdDate, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByCreatedDate_Last(Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByCreatedDate_Last(createdDate, orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByCreatedDate_Last(Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCreatedDate_Last(createdDate, orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByCreatedDate_PrevAndNext(
		int forecastMasterSid, Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByCreatedDate_PrevAndNext(forecastMasterSid,
			createdDate, orderByComparator);
	}

	/**
	* Removes all the forecasting masters where createdDate = &#63; from the database.
	*
	* @param createdDate the created date
	*/
	public static void removeByCreatedDate(Date createdDate) {
		getPersistence().removeByCreatedDate(createdDate);
	}

	/**
	* Returns the number of forecasting masters where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @return the number of matching forecasting masters
	*/
	public static int countByCreatedDate(Date createdDate) {
		return getPersistence().countByCreatedDate(createdDate);
	}

	/**
	* Returns all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @return the matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate) {
		return getPersistence()
				   .findByForecastingSalesUnique(forecastYear, forecastMonth,
			country, forecastStartDate, createdDate);
	}

	/**
	* Returns a range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		int start, int end) {
		return getPersistence()
				   .findByForecastingSalesUnique(forecastYear, forecastMonth,
			country, forecastStartDate, createdDate, start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .findByForecastingSalesUnique(forecastYear, forecastMonth,
			country, forecastStartDate, createdDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching forecasting masters
	*/
	public static List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByForecastingSalesUnique(forecastYear, forecastMonth,
			country, forecastStartDate, createdDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastingSalesUnique_First(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastingSalesUnique_First(forecastYear,
			forecastMonth, country, forecastStartDate, createdDate,
			orderByComparator);
	}

	/**
	* Returns the first forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastingSalesUnique_First(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastingSalesUnique_First(forecastYear,
			forecastMonth, country, forecastStartDate, createdDate,
			orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public static ForecastingMaster findByForecastingSalesUnique_Last(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastingSalesUnique_Last(forecastYear,
			forecastMonth, country, forecastStartDate, createdDate,
			orderByComparator);
	}

	/**
	* Returns the last forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public static ForecastingMaster fetchByForecastingSalesUnique_Last(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence()
				   .fetchByForecastingSalesUnique_Last(forecastYear,
			forecastMonth, country, forecastStartDate, createdDate,
			orderByComparator);
	}

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster[] findByForecastingSalesUnique_PrevAndNext(
		int forecastMasterSid, java.lang.String forecastYear,
		java.lang.String forecastMonth, java.lang.String country,
		Date forecastStartDate, Date createdDate,
		OrderByComparator<ForecastingMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence()
				   .findByForecastingSalesUnique_PrevAndNext(forecastMasterSid,
			forecastYear, forecastMonth, country, forecastStartDate,
			createdDate, orderByComparator);
	}

	/**
	* Removes all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63; from the database.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	*/
	public static void removeByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate) {
		getPersistence()
			.removeByForecastingSalesUnique(forecastYear, forecastMonth,
			country, forecastStartDate, createdDate);
	}

	/**
	* Returns the number of forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63;.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	* @return the number of matching forecasting masters
	*/
	public static int countByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate) {
		return getPersistence()
				   .countByForecastingSalesUnique(forecastYear, forecastMonth,
			country, forecastStartDate, createdDate);
	}

	/**
	* Caches the forecasting master in the entity cache if it is enabled.
	*
	* @param forecastingMaster the forecasting master
	*/
	public static void cacheResult(ForecastingMaster forecastingMaster) {
		getPersistence().cacheResult(forecastingMaster);
	}

	/**
	* Caches the forecasting masters in the entity cache if it is enabled.
	*
	* @param forecastingMasters the forecasting masters
	*/
	public static void cacheResult(List<ForecastingMaster> forecastingMasters) {
		getPersistence().cacheResult(forecastingMasters);
	}

	/**
	* Creates a new forecasting master with the primary key. Does not add the forecasting master to the database.
	*
	* @param forecastMasterSid the primary key for the new forecasting master
	* @return the new forecasting master
	*/
	public static ForecastingMaster create(int forecastMasterSid) {
		return getPersistence().create(forecastMasterSid);
	}

	/**
	* Removes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master that was removed
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster remove(int forecastMasterSid)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence().remove(forecastMasterSid);
	}

	public static ForecastingMaster updateImpl(
		ForecastingMaster forecastingMaster) {
		return getPersistence().updateImpl(forecastingMaster);
	}

	/**
	* Returns the forecasting master with the primary key or throws a {@link NoSuchForecastingMasterException} if it could not be found.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster findByPrimaryKey(int forecastMasterSid)
		throws com.stpl.app.exception.NoSuchForecastingMasterException {
		return getPersistence().findByPrimaryKey(forecastMasterSid);
	}

	/**
	* Returns the forecasting master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master, or <code>null</code> if a forecasting master with the primary key could not be found
	*/
	public static ForecastingMaster fetchByPrimaryKey(int forecastMasterSid) {
		return getPersistence().fetchByPrimaryKey(forecastMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ForecastingMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the forecasting masters.
	*
	* @return the forecasting masters
	*/
	public static List<ForecastingMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the forecasting masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @return the range of forecasting masters
	*/
	public static List<ForecastingMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the forecasting masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of forecasting masters
	*/
	public static List<ForecastingMaster> findAll(int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting masters
	* @param end the upper bound of the range of forecasting masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of forecasting masters
	*/
	public static List<ForecastingMaster> findAll(int start, int end,
		OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the forecasting masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of forecasting masters.
	*
	* @return the number of forecasting masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ForecastingMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ForecastingMasterPersistence, ForecastingMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ForecastingMasterPersistence.class);
}