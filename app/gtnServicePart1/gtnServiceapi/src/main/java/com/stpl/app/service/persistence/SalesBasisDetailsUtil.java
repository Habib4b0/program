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

import com.stpl.app.model.SalesBasisDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sales basis details service. This utility wraps {@link com.stpl.app.service.persistence.impl.SalesBasisDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesBasisDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.SalesBasisDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class SalesBasisDetailsUtil {
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
	public static void clearCache(SalesBasisDetails salesBasisDetails) {
		getPersistence().clearCache(salesBasisDetails);
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
	public static List<SalesBasisDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SalesBasisDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SalesBasisDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SalesBasisDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SalesBasisDetails update(SalesBasisDetails salesBasisDetails) {
		return getPersistence().update(salesBasisDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SalesBasisDetails update(
		SalesBasisDetails salesBasisDetails, ServiceContext serviceContext) {
		return getPersistence().update(salesBasisDetails, serviceContext);
	}

	/**
	* Caches the sales basis details in the entity cache if it is enabled.
	*
	* @param salesBasisDetails the sales basis details
	*/
	public static void cacheResult(SalesBasisDetails salesBasisDetails) {
		getPersistence().cacheResult(salesBasisDetails);
	}

	/**
	* Caches the sales basis detailses in the entity cache if it is enabled.
	*
	* @param salesBasisDetailses the sales basis detailses
	*/
	public static void cacheResult(List<SalesBasisDetails> salesBasisDetailses) {
		getPersistence().cacheResult(salesBasisDetailses);
	}

	/**
	* Creates a new sales basis details with the primary key. Does not add the sales basis details to the database.
	*
	* @param salesBasisDetailsSid the primary key for the new sales basis details
	* @return the new sales basis details
	*/
	public static SalesBasisDetails create(int salesBasisDetailsSid) {
		return getPersistence().create(salesBasisDetailsSid);
	}

	/**
	* Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param salesBasisDetailsSid the primary key of the sales basis details
	* @return the sales basis details that was removed
	* @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	*/
	public static SalesBasisDetails remove(int salesBasisDetailsSid)
		throws com.stpl.app.exception.NoSuchSalesBasisDetailsException {
		return getPersistence().remove(salesBasisDetailsSid);
	}

	public static SalesBasisDetails updateImpl(
		SalesBasisDetails salesBasisDetails) {
		return getPersistence().updateImpl(salesBasisDetails);
	}

	/**
	* Returns the sales basis details with the primary key or throws a {@link NoSuchSalesBasisDetailsException} if it could not be found.
	*
	* @param salesBasisDetailsSid the primary key of the sales basis details
	* @return the sales basis details
	* @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	*/
	public static SalesBasisDetails findByPrimaryKey(int salesBasisDetailsSid)
		throws com.stpl.app.exception.NoSuchSalesBasisDetailsException {
		return getPersistence().findByPrimaryKey(salesBasisDetailsSid);
	}

	/**
	* Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param salesBasisDetailsSid the primary key of the sales basis details
	* @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
	*/
	public static SalesBasisDetails fetchByPrimaryKey(int salesBasisDetailsSid) {
		return getPersistence().fetchByPrimaryKey(salesBasisDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, SalesBasisDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sales basis detailses.
	*
	* @return the sales basis detailses
	*/
	public static List<SalesBasisDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales basis detailses
	* @param end the upper bound of the range of sales basis detailses (not inclusive)
	* @return the range of sales basis detailses
	*/
	public static List<SalesBasisDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales basis detailses
	* @param end the upper bound of the range of sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sales basis detailses
	*/
	public static List<SalesBasisDetails> findAll(int start, int end,
		OrderByComparator<SalesBasisDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales basis detailses
	* @param end the upper bound of the range of sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sales basis detailses
	*/
	public static List<SalesBasisDetails> findAll(int start, int end,
		OrderByComparator<SalesBasisDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sales basis detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sales basis detailses.
	*
	* @return the number of sales basis detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SalesBasisDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SalesBasisDetailsPersistence, SalesBasisDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SalesBasisDetailsPersistence.class);
}