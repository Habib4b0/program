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

import com.stpl.app.model.IvldMasterDataAttribute;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld master data attribute service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldMasterDataAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldMasterDataAttributePersistence
 * @see com.stpl.app.service.persistence.impl.IvldMasterDataAttributePersistenceImpl
 * @generated
 */
@ProviderType
public class IvldMasterDataAttributeUtil {
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
	public static void clearCache(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		getPersistence().clearCache(ivldMasterDataAttribute);
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
	public static List<IvldMasterDataAttribute> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldMasterDataAttribute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldMasterDataAttribute> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldMasterDataAttribute> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldMasterDataAttribute update(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		return getPersistence().update(ivldMasterDataAttribute);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldMasterDataAttribute update(
		IvldMasterDataAttribute ivldMasterDataAttribute,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldMasterDataAttribute, serviceContext);
	}

	/**
	* Caches the ivld master data attribute in the entity cache if it is enabled.
	*
	* @param ivldMasterDataAttribute the ivld master data attribute
	*/
	public static void cacheResult(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		getPersistence().cacheResult(ivldMasterDataAttribute);
	}

	/**
	* Caches the ivld master data attributes in the entity cache if it is enabled.
	*
	* @param ivldMasterDataAttributes the ivld master data attributes
	*/
	public static void cacheResult(
		List<IvldMasterDataAttribute> ivldMasterDataAttributes) {
		getPersistence().cacheResult(ivldMasterDataAttributes);
	}

	/**
	* Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
	*
	* @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
	* @return the new ivld master data attribute
	*/
	public static IvldMasterDataAttribute create(int ivldMasterDataAtbteSid) {
		return getPersistence().create(ivldMasterDataAtbteSid);
	}

	/**
	* Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	* @return the ivld master data attribute that was removed
	* @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	*/
	public static IvldMasterDataAttribute remove(int ivldMasterDataAtbteSid)
		throws com.stpl.app.exception.NoSuchIvldMasterDataAttributeException {
		return getPersistence().remove(ivldMasterDataAtbteSid);
	}

	public static IvldMasterDataAttribute updateImpl(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		return getPersistence().updateImpl(ivldMasterDataAttribute);
	}

	/**
	* Returns the ivld master data attribute with the primary key or throws a {@link NoSuchIvldMasterDataAttributeException} if it could not be found.
	*
	* @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	* @return the ivld master data attribute
	* @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	*/
	public static IvldMasterDataAttribute findByPrimaryKey(
		int ivldMasterDataAtbteSid)
		throws com.stpl.app.exception.NoSuchIvldMasterDataAttributeException {
		return getPersistence().findByPrimaryKey(ivldMasterDataAtbteSid);
	}

	/**
	* Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	* @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
	*/
	public static IvldMasterDataAttribute fetchByPrimaryKey(
		int ivldMasterDataAtbteSid) {
		return getPersistence().fetchByPrimaryKey(ivldMasterDataAtbteSid);
	}

	public static java.util.Map<java.io.Serializable, IvldMasterDataAttribute> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld master data attributes.
	*
	* @return the ivld master data attributes
	*/
	public static List<IvldMasterDataAttribute> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld master data attributes
	* @param end the upper bound of the range of ivld master data attributes (not inclusive)
	* @return the range of ivld master data attributes
	*/
	public static List<IvldMasterDataAttribute> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld master data attributes
	* @param end the upper bound of the range of ivld master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld master data attributes
	*/
	public static List<IvldMasterDataAttribute> findAll(int start, int end,
		OrderByComparator<IvldMasterDataAttribute> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld master data attributes
	* @param end the upper bound of the range of ivld master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld master data attributes
	*/
	public static List<IvldMasterDataAttribute> findAll(int start, int end,
		OrderByComparator<IvldMasterDataAttribute> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld master data attributes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld master data attributes.
	*
	* @return the number of ivld master data attributes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldMasterDataAttributePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldMasterDataAttributePersistence, IvldMasterDataAttributePersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldMasterDataAttributePersistence.class);
}