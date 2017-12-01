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

import com.stpl.app.exception.NoSuchMedicaidNewNdcException;
import com.stpl.app.model.MedicaidNewNdc;

/**
 * The persistence interface for the medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MedicaidNewNdcPersistenceImpl
 * @see MedicaidNewNdcUtil
 * @generated
 */
@ProviderType
public interface MedicaidNewNdcPersistence extends BasePersistence<MedicaidNewNdc> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MedicaidNewNdcUtil} to access the medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the medicaid new ndc in the entity cache if it is enabled.
	*
	* @param medicaidNewNdc the medicaid new ndc
	*/
	public void cacheResult(MedicaidNewNdc medicaidNewNdc);

	/**
	* Caches the medicaid new ndcs in the entity cache if it is enabled.
	*
	* @param medicaidNewNdcs the medicaid new ndcs
	*/
	public void cacheResult(java.util.List<MedicaidNewNdc> medicaidNewNdcs);

	/**
	* Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
	*
	* @param ndc9 the primary key for the new medicaid new ndc
	* @return the new medicaid new ndc
	*/
	public MedicaidNewNdc create(java.lang.String ndc9);

	/**
	* Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc that was removed
	* @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	*/
	public MedicaidNewNdc remove(java.lang.String ndc9)
		throws NoSuchMedicaidNewNdcException;

	public MedicaidNewNdc updateImpl(MedicaidNewNdc medicaidNewNdc);

	/**
	* Returns the medicaid new ndc with the primary key or throws a {@link NoSuchMedicaidNewNdcException} if it could not be found.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc
	* @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	*/
	public MedicaidNewNdc findByPrimaryKey(java.lang.String ndc9)
		throws NoSuchMedicaidNewNdcException;

	/**
	* Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ndc9 the primary key of the medicaid new ndc
	* @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
	*/
	public MedicaidNewNdc fetchByPrimaryKey(java.lang.String ndc9);

	@Override
	public java.util.Map<java.io.Serializable, MedicaidNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the medicaid new ndcs.
	*
	* @return the medicaid new ndcs
	*/
	public java.util.List<MedicaidNewNdc> findAll();

	/**
	* Returns a range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @return the range of medicaid new ndcs
	*/
	public java.util.List<MedicaidNewNdc> findAll(int start, int end);

	/**
	* Returns an ordered range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of medicaid new ndcs
	*/
	public java.util.List<MedicaidNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MedicaidNewNdc> orderByComparator);

	/**
	* Returns an ordered range of all the medicaid new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid new ndcs
	* @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of medicaid new ndcs
	*/
	public java.util.List<MedicaidNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MedicaidNewNdc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the medicaid new ndcs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of medicaid new ndcs.
	*
	* @return the number of medicaid new ndcs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}