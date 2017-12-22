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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchCffAdditionalInfoException;
import com.stpl.app.parttwo.model.CffAdditionalInfo;

/**
 * The persistence interface for the cff additional info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffAdditionalInfoPersistenceImpl
 * @see CffAdditionalInfoUtil
 * @generated
 */
@ProviderType
public interface CffAdditionalInfoPersistence extends BasePersistence<CffAdditionalInfo> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffAdditionalInfoUtil} to access the cff additional info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff additional info in the entity cache if it is enabled.
	*
	* @param cffAdditionalInfo the cff additional info
	*/
	public void cacheResult(CffAdditionalInfo cffAdditionalInfo);

	/**
	* Caches the cff additional infos in the entity cache if it is enabled.
	*
	* @param cffAdditionalInfos the cff additional infos
	*/
	public void cacheResult(
		java.util.List<CffAdditionalInfo> cffAdditionalInfos);

	/**
	* Creates a new cff additional info with the primary key. Does not add the cff additional info to the database.
	*
	* @param cffAdditionalInfoSid the primary key for the new cff additional info
	* @return the new cff additional info
	*/
	public CffAdditionalInfo create(int cffAdditionalInfoSid);

	/**
	* Removes the cff additional info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffAdditionalInfoSid the primary key of the cff additional info
	* @return the cff additional info that was removed
	* @throws NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
	*/
	public CffAdditionalInfo remove(int cffAdditionalInfoSid)
		throws NoSuchCffAdditionalInfoException;

	public CffAdditionalInfo updateImpl(CffAdditionalInfo cffAdditionalInfo);

	/**
	* Returns the cff additional info with the primary key or throws a {@link NoSuchCffAdditionalInfoException} if it could not be found.
	*
	* @param cffAdditionalInfoSid the primary key of the cff additional info
	* @return the cff additional info
	* @throws NoSuchCffAdditionalInfoException if a cff additional info with the primary key could not be found
	*/
	public CffAdditionalInfo findByPrimaryKey(int cffAdditionalInfoSid)
		throws NoSuchCffAdditionalInfoException;

	/**
	* Returns the cff additional info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffAdditionalInfoSid the primary key of the cff additional info
	* @return the cff additional info, or <code>null</code> if a cff additional info with the primary key could not be found
	*/
	public CffAdditionalInfo fetchByPrimaryKey(int cffAdditionalInfoSid);

	@Override
	public java.util.Map<java.io.Serializable, CffAdditionalInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff additional infos.
	*
	* @return the cff additional infos
	*/
	public java.util.List<CffAdditionalInfo> findAll();

	/**
	* Returns a range of all the cff additional infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff additional infos
	* @param end the upper bound of the range of cff additional infos (not inclusive)
	* @return the range of cff additional infos
	*/
	public java.util.List<CffAdditionalInfo> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff additional infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff additional infos
	* @param end the upper bound of the range of cff additional infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff additional infos
	*/
	public java.util.List<CffAdditionalInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffAdditionalInfo> orderByComparator);

	/**
	* Returns an ordered range of all the cff additional infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffAdditionalInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff additional infos
	* @param end the upper bound of the range of cff additional infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff additional infos
	*/
	public java.util.List<CffAdditionalInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffAdditionalInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff additional infos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff additional infos.
	*
	* @return the number of cff additional infos
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}