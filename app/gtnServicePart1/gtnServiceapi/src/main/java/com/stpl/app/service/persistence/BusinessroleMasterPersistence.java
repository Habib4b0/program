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

import com.stpl.app.exception.NoSuchBusinessroleMasterException;
import com.stpl.app.model.BusinessroleMaster;

/**
 * The persistence interface for the businessrole master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.BusinessroleMasterPersistenceImpl
 * @see BusinessroleMasterUtil
 * @generated
 */
@ProviderType
public interface BusinessroleMasterPersistence extends BasePersistence<BusinessroleMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BusinessroleMasterUtil} to access the businessrole master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName);

	/**
	* Returns a range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName, int start, int end);

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessroleName(
		java.lang.String businessroleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public BusinessroleMaster findByBusinessroleName_First(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException;

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public BusinessroleMaster fetchByBusinessroleName_First(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public BusinessroleMaster findByBusinessroleName_Last(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException;

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public BusinessroleMaster fetchByBusinessroleName_Last(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleMasterSid the primary key of the current businessrole master
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next businessrole master
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public BusinessroleMaster[] findByBusinessroleName_PrevAndNext(
		int businessroleMasterSid, java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException;

	/**
	* Removes all the businessrole masters where businessroleName = &#63; from the database.
	*
	* @param businessroleName the businessrole name
	*/
	public void removeByBusinessroleName(java.lang.String businessroleName);

	/**
	* Returns the number of businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the number of matching businessrole masters
	*/
	public int countByBusinessroleName(java.lang.String businessroleName);

	/**
	* Returns all the businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName);

	/**
	* Returns a range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName, int start, int end);

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param businessroleName the businessrole name
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findByBusinessRoleUnique(
		java.lang.String businessroleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public BusinessroleMaster findByBusinessRoleUnique_First(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException;

	/**
	* Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public BusinessroleMaster fetchByBusinessRoleUnique_First(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master
	* @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	*/
	public BusinessroleMaster findByBusinessRoleUnique_Last(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException;

	/**
	* Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	*/
	public BusinessroleMaster fetchByBusinessRoleUnique_Last(
		java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
	*
	* @param businessroleMasterSid the primary key of the current businessrole master
	* @param businessroleName the businessrole name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next businessrole master
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public BusinessroleMaster[] findByBusinessRoleUnique_PrevAndNext(
		int businessroleMasterSid, java.lang.String businessroleName,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException;

	/**
	* Removes all the businessrole masters where businessroleName = &#63; from the database.
	*
	* @param businessroleName the businessrole name
	*/
	public void removeByBusinessRoleUnique(java.lang.String businessroleName);

	/**
	* Returns the number of businessrole masters where businessroleName = &#63;.
	*
	* @param businessroleName the businessrole name
	* @return the number of matching businessrole masters
	*/
	public int countByBusinessRoleUnique(java.lang.String businessroleName);

	/**
	* Caches the businessrole master in the entity cache if it is enabled.
	*
	* @param businessroleMaster the businessrole master
	*/
	public void cacheResult(BusinessroleMaster businessroleMaster);

	/**
	* Caches the businessrole masters in the entity cache if it is enabled.
	*
	* @param businessroleMasters the businessrole masters
	*/
	public void cacheResult(
		java.util.List<BusinessroleMaster> businessroleMasters);

	/**
	* Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
	*
	* @param businessroleMasterSid the primary key for the new businessrole master
	* @return the new businessrole master
	*/
	public BusinessroleMaster create(int businessroleMasterSid);

	/**
	* Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master that was removed
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public BusinessroleMaster remove(int businessroleMasterSid)
		throws NoSuchBusinessroleMasterException;

	public BusinessroleMaster updateImpl(BusinessroleMaster businessroleMaster);

	/**
	* Returns the businessrole master with the primary key or throws a {@link NoSuchBusinessroleMasterException} if it could not be found.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master
	* @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	*/
	public BusinessroleMaster findByPrimaryKey(int businessroleMasterSid)
		throws NoSuchBusinessroleMasterException;

	/**
	* Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param businessroleMasterSid the primary key of the businessrole master
	* @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
	*/
	public BusinessroleMaster fetchByPrimaryKey(int businessroleMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, BusinessroleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the businessrole masters.
	*
	* @return the businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findAll();

	/**
	* Returns a range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @return the range of businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the businessrole masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole masters
	* @param end the upper bound of the range of businessrole masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of businessrole masters
	*/
	public java.util.List<BusinessroleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the businessrole masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of businessrole masters.
	*
	* @return the number of businessrole masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}