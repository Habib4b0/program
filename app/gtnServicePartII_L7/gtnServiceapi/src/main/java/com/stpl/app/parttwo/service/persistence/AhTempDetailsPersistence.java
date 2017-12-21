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

import com.stpl.app.parttwo.exception.NoSuchAhTempDetailsException;
import com.stpl.app.parttwo.model.AhTempDetails;

/**
 * The persistence interface for the ah temp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.AhTempDetailsPersistenceImpl
 * @see AhTempDetailsUtil
 * @generated
 */
@ProviderType
public interface AhTempDetailsPersistence extends BasePersistence<AhTempDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AhTempDetailsUtil} to access the ah temp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ah temp details in the entity cache if it is enabled.
	*
	* @param ahTempDetails the ah temp details
	*/
	public void cacheResult(AhTempDetails ahTempDetails);

	/**
	* Caches the ah temp detailses in the entity cache if it is enabled.
	*
	* @param ahTempDetailses the ah temp detailses
	*/
	public void cacheResult(java.util.List<AhTempDetails> ahTempDetailses);

	/**
	* Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
	*
	* @param componentMasterSid the primary key for the new ah temp details
	* @return the new ah temp details
	*/
	public AhTempDetails create(int componentMasterSid);

	/**
	* Removes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details that was removed
	* @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	*/
	public AhTempDetails remove(int componentMasterSid)
		throws NoSuchAhTempDetailsException;

	public AhTempDetails updateImpl(AhTempDetails ahTempDetails);

	/**
	* Returns the ah temp details with the primary key or throws a {@link NoSuchAhTempDetailsException} if it could not be found.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details
	* @throws NoSuchAhTempDetailsException if a ah temp details with the primary key could not be found
	*/
	public AhTempDetails findByPrimaryKey(int componentMasterSid)
		throws NoSuchAhTempDetailsException;

	/**
	* Returns the ah temp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details, or <code>null</code> if a ah temp details with the primary key could not be found
	*/
	public AhTempDetails fetchByPrimaryKey(int componentMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, AhTempDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ah temp detailses.
	*
	* @return the ah temp detailses
	*/
	public java.util.List<AhTempDetails> findAll();

	/**
	* Returns a range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @return the range of ah temp detailses
	*/
	public java.util.List<AhTempDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ah temp detailses
	*/
	public java.util.List<AhTempDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AhTempDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ah temp detailses
	*/
	public java.util.List<AhTempDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AhTempDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ah temp detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ah temp detailses.
	*
	* @return the number of ah temp detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}