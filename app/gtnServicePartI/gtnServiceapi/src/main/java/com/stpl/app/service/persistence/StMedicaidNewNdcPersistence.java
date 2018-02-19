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

import com.stpl.app.exception.NoSuchStMedicaidNewNdcException;
import com.stpl.app.model.StMedicaidNewNdc;

/**
 * The persistence interface for the st medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StMedicaidNewNdcPersistenceImpl
 * @see StMedicaidNewNdcUtil
 * @generated
 */
@ProviderType
public interface StMedicaidNewNdcPersistence extends BasePersistence<StMedicaidNewNdc> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StMedicaidNewNdcUtil} to access the st medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st medicaid new ndc in the entity cache if it is enabled.
	*
	* @param stMedicaidNewNdc the st medicaid new ndc
	*/
	public void cacheResult(StMedicaidNewNdc stMedicaidNewNdc);

	/**
	* Caches the st medicaid new ndcs in the entity cache if it is enabled.
	*
	* @param stMedicaidNewNdcs the st medicaid new ndcs
	*/
	public void cacheResult(java.util.List<StMedicaidNewNdc> stMedicaidNewNdcs);

	/**
	* Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
	*
	* @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
	* @return the new st medicaid new ndc
	*/
	public StMedicaidNewNdc create(StMedicaidNewNdcPK stMedicaidNewNdcPK);

	/**
	* Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	* @return the st medicaid new ndc that was removed
	* @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	*/
	public StMedicaidNewNdc remove(StMedicaidNewNdcPK stMedicaidNewNdcPK)
		throws NoSuchStMedicaidNewNdcException;

	public StMedicaidNewNdc updateImpl(StMedicaidNewNdc stMedicaidNewNdc);

	/**
	* Returns the st medicaid new ndc with the primary key or throws a {@link NoSuchStMedicaidNewNdcException} if it could not be found.
	*
	* @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	* @return the st medicaid new ndc
	* @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	*/
	public StMedicaidNewNdc findByPrimaryKey(
		StMedicaidNewNdcPK stMedicaidNewNdcPK)
		throws NoSuchStMedicaidNewNdcException;

	/**
	* Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	* @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
	*/
	public StMedicaidNewNdc fetchByPrimaryKey(
		StMedicaidNewNdcPK stMedicaidNewNdcPK);

	@Override
	public java.util.Map<java.io.Serializable, StMedicaidNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st medicaid new ndcs.
	*
	* @return the st medicaid new ndcs
	*/
	public java.util.List<StMedicaidNewNdc> findAll();

	/**
	* Returns a range of all the st medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st medicaid new ndcs
	* @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	* @return the range of st medicaid new ndcs
	*/
	public java.util.List<StMedicaidNewNdc> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st medicaid new ndcs
	* @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st medicaid new ndcs
	*/
	public java.util.List<StMedicaidNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StMedicaidNewNdc> orderByComparator);

	/**
	* Returns an ordered range of all the st medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st medicaid new ndcs
	* @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st medicaid new ndcs
	*/
	public java.util.List<StMedicaidNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StMedicaidNewNdc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st medicaid new ndcs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st medicaid new ndcs.
	*
	* @return the number of st medicaid new ndcs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}