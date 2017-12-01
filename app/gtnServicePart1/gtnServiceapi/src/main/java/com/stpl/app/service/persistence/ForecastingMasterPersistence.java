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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchForecastingMasterException;
import com.stpl.app.model.ForecastingMaster;

import java.util.Date;

/**
 * The persistence interface for the forecasting master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ForecastingMasterPersistenceImpl
 * @see ForecastingMasterUtil
 * @generated
 */
@ProviderType
public interface ForecastingMasterPersistence extends BasePersistence<ForecastingMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ForecastingMasterUtil} to access the forecasting master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the forecasting masters where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @return the matching forecasting masters
	*/
	public java.util.List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear);

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
	public java.util.List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear, int start, int end);

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
	public java.util.List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByForecastYear(
		java.lang.String forecastYear, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByForecastYear_First(
		java.lang.String forecastYear,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the first forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByForecastYear_First(
		java.lang.String forecastYear,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the last forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByForecastYear_Last(
		java.lang.String forecastYear,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the last forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByForecastYear_Last(
		java.lang.String forecastYear,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastYear = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastYear the forecast year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster[] findByForecastYear_PrevAndNext(
		int forecastMasterSid, java.lang.String forecastYear,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where forecastYear = &#63; from the database.
	*
	* @param forecastYear the forecast year
	*/
	public void removeByForecastYear(java.lang.String forecastYear);

	/**
	* Returns the number of forecasting masters where forecastYear = &#63;.
	*
	* @param forecastYear the forecast year
	* @return the number of matching forecasting masters
	*/
	public int countByForecastYear(java.lang.String forecastYear);

	/**
	* Returns all the forecasting masters where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @return the matching forecasting masters
	*/
	public java.util.List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth);

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
	public java.util.List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth, int start, int end);

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
	public java.util.List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByForecastMonth(
		java.lang.String forecastMonth, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByForecastMonth_First(
		java.lang.String forecastMonth,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the first forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByForecastMonth_First(
		java.lang.String forecastMonth,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByForecastMonth_Last(
		java.lang.String forecastMonth,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the last forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByForecastMonth_Last(
		java.lang.String forecastMonth,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastMonth = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastMonth the forecast month
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster[] findByForecastMonth_PrevAndNext(
		int forecastMasterSid, java.lang.String forecastMonth,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where forecastMonth = &#63; from the database.
	*
	* @param forecastMonth the forecast month
	*/
	public void removeByForecastMonth(java.lang.String forecastMonth);

	/**
	* Returns the number of forecasting masters where forecastMonth = &#63;.
	*
	* @param forecastMonth the forecast month
	* @return the number of matching forecasting masters
	*/
	public int countByForecastMonth(java.lang.String forecastMonth);

	/**
	* Returns all the forecasting masters where country = &#63;.
	*
	* @param country the country
	* @return the matching forecasting masters
	*/
	public java.util.List<ForecastingMaster> findByCountry(
		java.lang.String country);

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
	public java.util.List<ForecastingMaster> findByCountry(
		java.lang.String country, int start, int end);

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
	public java.util.List<ForecastingMaster> findByCountry(
		java.lang.String country, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByCountry(
		java.lang.String country, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByCountry_First(java.lang.String country,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the first forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByCountry_First(java.lang.String country,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the last forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByCountry_Last(java.lang.String country,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the last forecasting master in the ordered set where country = &#63;.
	*
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByCountry_Last(java.lang.String country,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where country = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param country the country
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster[] findByCountry_PrevAndNext(
		int forecastMasterSid, java.lang.String country,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where country = &#63; from the database.
	*
	* @param country the country
	*/
	public void removeByCountry(java.lang.String country);

	/**
	* Returns the number of forecasting masters where country = &#63;.
	*
	* @param country the country
	* @return the number of matching forecasting masters
	*/
	public int countByCountry(java.lang.String country);

	/**
	* Returns all the forecasting masters where ndc = &#63;.
	*
	* @param ndc the ndc
	* @return the matching forecasting masters
	*/
	public java.util.List<ForecastingMaster> findByNdc(java.lang.String ndc);

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
	public java.util.List<ForecastingMaster> findByNdc(java.lang.String ndc,
		int start, int end);

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
	public java.util.List<ForecastingMaster> findByNdc(java.lang.String ndc,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByNdc(java.lang.String ndc,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByNdc_First(java.lang.String ndc,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the first forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByNdc_First(java.lang.String ndc,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the last forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByNdc_Last(java.lang.String ndc,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the last forecasting master in the ordered set where ndc = &#63;.
	*
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByNdc_Last(java.lang.String ndc,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where ndc = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param ndc the ndc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster[] findByNdc_PrevAndNext(int forecastMasterSid,
		java.lang.String ndc,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where ndc = &#63; from the database.
	*
	* @param ndc the ndc
	*/
	public void removeByNdc(java.lang.String ndc);

	/**
	* Returns the number of forecasting masters where ndc = &#63;.
	*
	* @param ndc the ndc
	* @return the number of matching forecasting masters
	*/
	public int countByNdc(java.lang.String ndc);

	/**
	* Returns all the forecasting masters where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @return the matching forecasting masters
	*/
	public java.util.List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate);

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
	public java.util.List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate, int start, int end);

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
	public java.util.List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByForecastStartDate(
		Date forecastStartDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByForecastStartDate_First(
		Date forecastStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the first forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByForecastStartDate_First(
		Date forecastStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByForecastStartDate_Last(
		Date forecastStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the last forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByForecastStartDate_Last(
		Date forecastStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where forecastStartDate = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param forecastStartDate the forecast start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster[] findByForecastStartDate_PrevAndNext(
		int forecastMasterSid, Date forecastStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where forecastStartDate = &#63; from the database.
	*
	* @param forecastStartDate the forecast start date
	*/
	public void removeByForecastStartDate(Date forecastStartDate);

	/**
	* Returns the number of forecasting masters where forecastStartDate = &#63;.
	*
	* @param forecastStartDate the forecast start date
	* @return the number of matching forecasting masters
	*/
	public int countByForecastStartDate(Date forecastStartDate);

	/**
	* Returns all the forecasting masters where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @return the matching forecasting masters
	*/
	public java.util.List<ForecastingMaster> findByCreatedDate(Date createdDate);

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
	public java.util.List<ForecastingMaster> findByCreatedDate(
		Date createdDate, int start, int end);

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
	public java.util.List<ForecastingMaster> findByCreatedDate(
		Date createdDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByCreatedDate(
		Date createdDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByCreatedDate_First(Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the first forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByCreatedDate_First(Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the last forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master
	* @throws NoSuchForecastingMasterException if a matching forecasting master could not be found
	*/
	public ForecastingMaster findByCreatedDate_Last(Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the last forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching forecasting master, or <code>null</code> if a matching forecasting master could not be found
	*/
	public ForecastingMaster fetchByCreatedDate_Last(Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

	/**
	* Returns the forecasting masters before and after the current forecasting master in the ordered set where createdDate = &#63;.
	*
	* @param forecastMasterSid the primary key of the current forecasting master
	* @param createdDate the created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster[] findByCreatedDate_PrevAndNext(
		int forecastMasterSid, Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where createdDate = &#63; from the database.
	*
	* @param createdDate the created date
	*/
	public void removeByCreatedDate(Date createdDate);

	/**
	* Returns the number of forecasting masters where createdDate = &#63;.
	*
	* @param createdDate the created date
	* @return the number of matching forecasting masters
	*/
	public int countByCreatedDate(Date createdDate);

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
	public java.util.List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate);

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
	public java.util.List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		int start, int end);

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
	public java.util.List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findByForecastingSalesUnique(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

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
	public ForecastingMaster findByForecastingSalesUnique_First(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

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
	public ForecastingMaster fetchByForecastingSalesUnique_First(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public ForecastingMaster findByForecastingSalesUnique_Last(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

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
	public ForecastingMaster fetchByForecastingSalesUnique_Last(
		java.lang.String forecastYear, java.lang.String forecastMonth,
		java.lang.String country, Date forecastStartDate, Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public ForecastingMaster[] findByForecastingSalesUnique_PrevAndNext(
		int forecastMasterSid, java.lang.String forecastYear,
		java.lang.String forecastMonth, java.lang.String country,
		Date forecastStartDate, Date createdDate,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator)
		throws NoSuchForecastingMasterException;

	/**
	* Removes all the forecasting masters where forecastYear = &#63; and forecastMonth = &#63; and country = &#63; and forecastStartDate = &#63; and createdDate = &#63; from the database.
	*
	* @param forecastYear the forecast year
	* @param forecastMonth the forecast month
	* @param country the country
	* @param forecastStartDate the forecast start date
	* @param createdDate the created date
	*/
	public void removeByForecastingSalesUnique(java.lang.String forecastYear,
		java.lang.String forecastMonth, java.lang.String country,
		Date forecastStartDate, Date createdDate);

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
	public int countByForecastingSalesUnique(java.lang.String forecastYear,
		java.lang.String forecastMonth, java.lang.String country,
		Date forecastStartDate, Date createdDate);

	/**
	* Caches the forecasting master in the entity cache if it is enabled.
	*
	* @param forecastingMaster the forecasting master
	*/
	public void cacheResult(ForecastingMaster forecastingMaster);

	/**
	* Caches the forecasting masters in the entity cache if it is enabled.
	*
	* @param forecastingMasters the forecasting masters
	*/
	public void cacheResult(
		java.util.List<ForecastingMaster> forecastingMasters);

	/**
	* Creates a new forecasting master with the primary key. Does not add the forecasting master to the database.
	*
	* @param forecastMasterSid the primary key for the new forecasting master
	* @return the new forecasting master
	*/
	public ForecastingMaster create(int forecastMasterSid);

	/**
	* Removes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master that was removed
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster remove(int forecastMasterSid)
		throws NoSuchForecastingMasterException;

	public ForecastingMaster updateImpl(ForecastingMaster forecastingMaster);

	/**
	* Returns the forecasting master with the primary key or throws a {@link NoSuchForecastingMasterException} if it could not be found.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master
	* @throws NoSuchForecastingMasterException if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster findByPrimaryKey(int forecastMasterSid)
		throws NoSuchForecastingMasterException;

	/**
	* Returns the forecasting master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param forecastMasterSid the primary key of the forecasting master
	* @return the forecasting master, or <code>null</code> if a forecasting master with the primary key could not be found
	*/
	public ForecastingMaster fetchByPrimaryKey(int forecastMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ForecastingMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the forecasting masters.
	*
	* @return the forecasting masters
	*/
	public java.util.List<ForecastingMaster> findAll();

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
	public java.util.List<ForecastingMaster> findAll(int start, int end);

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
	public java.util.List<ForecastingMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator);

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
	public java.util.List<ForecastingMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the forecasting masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of forecasting masters.
	*
	* @return the number of forecasting masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}