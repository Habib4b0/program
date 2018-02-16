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

import com.stpl.app.exception.NoSuchMailNotificationMasterException;
import com.stpl.app.model.MailNotificationMaster;

/**
 * The persistence interface for the mail notification master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MailNotificationMasterPersistenceImpl
 * @see MailNotificationMasterUtil
 * @generated
 */
@ProviderType
public interface MailNotificationMasterPersistence extends BasePersistence<MailNotificationMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MailNotificationMasterUtil} to access the mail notification master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the mail notification master in the entity cache if it is enabled.
	*
	* @param mailNotificationMaster the mail notification master
	*/
	public void cacheResult(MailNotificationMaster mailNotificationMaster);

	/**
	* Caches the mail notification masters in the entity cache if it is enabled.
	*
	* @param mailNotificationMasters the mail notification masters
	*/
	public void cacheResult(
		java.util.List<MailNotificationMaster> mailNotificationMasters);

	/**
	* Creates a new mail notification master with the primary key. Does not add the mail notification master to the database.
	*
	* @param mailNotificationSid the primary key for the new mail notification master
	* @return the new mail notification master
	*/
	public MailNotificationMaster create(int mailNotificationSid);

	/**
	* Removes the mail notification master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mailNotificationSid the primary key of the mail notification master
	* @return the mail notification master that was removed
	* @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	*/
	public MailNotificationMaster remove(int mailNotificationSid)
		throws NoSuchMailNotificationMasterException;

	public MailNotificationMaster updateImpl(
		MailNotificationMaster mailNotificationMaster);

	/**
	* Returns the mail notification master with the primary key or throws a {@link NoSuchMailNotificationMasterException} if it could not be found.
	*
	* @param mailNotificationSid the primary key of the mail notification master
	* @return the mail notification master
	* @throws NoSuchMailNotificationMasterException if a mail notification master with the primary key could not be found
	*/
	public MailNotificationMaster findByPrimaryKey(int mailNotificationSid)
		throws NoSuchMailNotificationMasterException;

	/**
	* Returns the mail notification master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mailNotificationSid the primary key of the mail notification master
	* @return the mail notification master, or <code>null</code> if a mail notification master with the primary key could not be found
	*/
	public MailNotificationMaster fetchByPrimaryKey(int mailNotificationSid);

	@Override
	public java.util.Map<java.io.Serializable, MailNotificationMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the mail notification masters.
	*
	* @return the mail notification masters
	*/
	public java.util.List<MailNotificationMaster> findAll();

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
	public java.util.List<MailNotificationMaster> findAll(int start, int end);

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
	public java.util.List<MailNotificationMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MailNotificationMaster> orderByComparator);

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
	public java.util.List<MailNotificationMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MailNotificationMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the mail notification masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of mail notification masters.
	*
	* @return the number of mail notification masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}