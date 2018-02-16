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

import com.stpl.app.exception.NoSuchFederalNewNdcException;
import com.stpl.app.model.FederalNewNdc;

/**
 * The persistence interface for the federal new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.FederalNewNdcPersistenceImpl
 * @see FederalNewNdcUtil
 * @generated
 */
@ProviderType
public interface FederalNewNdcPersistence extends BasePersistence<FederalNewNdc> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FederalNewNdcUtil} to access the federal new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the federal new ndc in the entity cache if it is enabled.
	*
	* @param federalNewNdc the federal new ndc
	*/
	public void cacheResult(FederalNewNdc federalNewNdc);

	/**
	* Caches the federal new ndcs in the entity cache if it is enabled.
	*
	* @param federalNewNdcs the federal new ndcs
	*/
	public void cacheResult(java.util.List<FederalNewNdc> federalNewNdcs);

	/**
	* Creates a new federal new ndc with the primary key. Does not add the federal new ndc to the database.
	*
	* @param itemMasterSid the primary key for the new federal new ndc
	* @return the new federal new ndc
	*/
	public FederalNewNdc create(int itemMasterSid);

	/**
	* Removes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the federal new ndc
	* @return the federal new ndc that was removed
	* @throws NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
	*/
	public FederalNewNdc remove(int itemMasterSid)
		throws NoSuchFederalNewNdcException;

	public FederalNewNdc updateImpl(FederalNewNdc federalNewNdc);

	/**
	* Returns the federal new ndc with the primary key or throws a {@link NoSuchFederalNewNdcException} if it could not be found.
	*
	* @param itemMasterSid the primary key of the federal new ndc
	* @return the federal new ndc
	* @throws NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
	*/
	public FederalNewNdc findByPrimaryKey(int itemMasterSid)
		throws NoSuchFederalNewNdcException;

	/**
	* Returns the federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemMasterSid the primary key of the federal new ndc
	* @return the federal new ndc, or <code>null</code> if a federal new ndc with the primary key could not be found
	*/
	public FederalNewNdc fetchByPrimaryKey(int itemMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, FederalNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the federal new ndcs.
	*
	* @return the federal new ndcs
	*/
	public java.util.List<FederalNewNdc> findAll();

	/**
	* Returns a range of all the federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of federal new ndcs
	* @param end the upper bound of the range of federal new ndcs (not inclusive)
	* @return the range of federal new ndcs
	*/
	public java.util.List<FederalNewNdc> findAll(int start, int end);

	/**
	* Returns an ordered range of all the federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of federal new ndcs
	* @param end the upper bound of the range of federal new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of federal new ndcs
	*/
	public java.util.List<FederalNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FederalNewNdc> orderByComparator);

	/**
	* Returns an ordered range of all the federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of federal new ndcs
	* @param end the upper bound of the range of federal new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of federal new ndcs
	*/
	public java.util.List<FederalNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FederalNewNdc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the federal new ndcs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of federal new ndcs.
	*
	* @return the number of federal new ndcs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}