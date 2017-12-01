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

import com.stpl.app.model.MailNotificationMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the mail notification master service. This utility wraps {@link com.stpl.app.service.persistence.impl.MailNotificationMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MailNotificationMasterPersistence
 * @see com.stpl.app.service.persistence.impl.MailNotificationMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class MailNotificationMasterUtil {
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
	public static void clearCache(MailNotificationMaster mailNotificationMaster) {
		getPersistence().clearCache(mailNotificationMaster);
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
	public static List<MailNotificationMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MailNotificationMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MailNotificationMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MailNotificationMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MailNotificationMaster update(
		MailNotificationMaster mailNotificationMaster) {
		return getPersistence().update(mailNotificationMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MailNotificationMaster update(
		MailNotificationMaster mailNotificationMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(mailNotificationMaster, serviceContext);
	}

	/**
	* Caches the mail notification master in the entity cache if it is enabled.
	*
	* @param mailNotificationMaster the mail notification master
	*/
	public static void cacheResult(
		MailNotificationMaster mailNotificationMaster) {
		getPersistence().cacheResult(mailNotificationMaster);
	}

	/**
	* Caches the mail notification masters in the entity cache if it is enabled.
	*
	* @param mailNotificationMasters the mail notification masters
	*/
	public static void cacheResult(
		List<MailNotificationMaster> mailNotificationMasters) {
		getPersistence().cacheResult(mailNotificationMasters);
	}

	/**
	* Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
	*
	* @param mailNotificationSid the primary key for the new mail notification master
	* @return the new mail notification master
	*/
	public static MailNotificationMaster create(int mailNotificationSid) {
		return getPersistence().create(mailNotificationSid);
	}

	/**
	* Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mailNotificationSid the primary key of the mail notification master
	* @return the mail notification master that was removed
	* @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	*/
	public static MailNotificationMaster remove(int mailNotificationSid)
		throws com.stpl.app.exception.NoSuchMailNotificationMasterException {
		return getPersistence().remove(mailNotificationSid);
	}

	public static MailNotificationMaster updateImpl(
		MailNotificationMaster mailNotificationMaster) {
		return getPersistence().updateImpl(mailNotificationMaster);
	}

	/**
	* Returns the mail notification master with the primary key or throws a {@link NoSuchMailNotificationMasterException} if it could not be found.
	*
	* @param mailNotificationSid the primary key of the mail notification master
	* @return the mail notification master
	* @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	*/
	public static MailNotificationMaster findByPrimaryKey(
		int mailNotificationSid)
		throws com.stpl.app.exception.NoSuchMailNotificationMasterException {
		return getPersistence().findByPrimaryKey(mailNotificationSid);
	}

	/**
	* Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mailNotificationSid the primary key of the mail notification master
	* @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
	*/
	public static MailNotificationMaster fetchByPrimaryKey(
		int mailNotificationSid) {
		return getPersistence().fetchByPrimaryKey(mailNotificationSid);
	}

	public static java.util.Map<java.io.Serializable, MailNotificationMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the mail notification masters.
	*
	* @return the mail notification masters
	*/
	public static List<MailNotificationMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the mail notification masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mail notification masters
	* @param end the upper bound of the range of mail notification masters (not inclusive)
	* @return the range of mail notification masters
	*/
	public static List<MailNotificationMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the mail notification masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mail notification masters
	* @param end the upper bound of the range of mail notification masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of mail notification masters
	*/
	public static List<MailNotificationMaster> findAll(int start, int end,
		OrderByComparator<MailNotificationMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the mail notification masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MailNotificationMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mail notification masters
	* @param end the upper bound of the range of mail notification masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of mail notification masters
	*/
	public static List<MailNotificationMaster> findAll(int start, int end,
		OrderByComparator<MailNotificationMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the mail notification masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of mail notification masters.
	*
	* @return the number of mail notification masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MailNotificationMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MailNotificationMasterPersistence, MailNotificationMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MailNotificationMasterPersistence.class);
}