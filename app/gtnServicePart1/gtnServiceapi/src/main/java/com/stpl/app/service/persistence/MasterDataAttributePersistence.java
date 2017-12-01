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

import com.stpl.app.exception.NoSuchMasterDataAttributeException;
import com.stpl.app.model.MasterDataAttribute;

/**
 * The persistence interface for the master data attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MasterDataAttributePersistenceImpl
 * @see MasterDataAttributeUtil
 * @generated
 */
@ProviderType
public interface MasterDataAttributePersistence extends BasePersistence<MasterDataAttribute> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MasterDataAttributeUtil} to access the master data attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the master data attributes where masterType = &#63;.
	*
	* @param masterType the master type
	* @return the matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType);

	/**
	* Returns a range of all the master data attributes where masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType, int start, int end);

	/**
	* Returns an ordered range of all the master data attributes where masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns an ordered range of all the master data attributes where masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterType(
		java.lang.String masterType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterType_First(
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the first master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterType_First(
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the last master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterType_Last(
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the last master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterType_Last(
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterType = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute[] findByMasterType_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Removes all the master data attributes where masterType = &#63; from the database.
	*
	* @param masterType the master type
	*/
	public void removeByMasterType(java.lang.String masterType);

	/**
	* Returns the number of master data attributes where masterType = &#63;.
	*
	* @param masterType the master type
	* @return the number of matching master data attributes
	*/
	public int countByMasterType(java.lang.String masterType);

	/**
	* Returns all the master data attributes where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @return the matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute);

	/**
	* Returns a range of all the master data attributes where masterAttribute = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute, int start, int end);

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterAttribute(
		java.lang.String masterAttribute, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterAttribute_First(
		java.lang.String masterAttribute,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterAttribute_First(
		java.lang.String masterAttribute,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterAttribute_Last(
		java.lang.String masterAttribute,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterAttribute_Last(
		java.lang.String masterAttribute,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterAttribute the master attribute
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute[] findByMasterAttribute_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterAttribute,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Removes all the master data attributes where masterAttribute = &#63; from the database.
	*
	* @param masterAttribute the master attribute
	*/
	public void removeByMasterAttribute(java.lang.String masterAttribute);

	/**
	* Returns the number of master data attributes where masterAttribute = &#63;.
	*
	* @param masterAttribute the master attribute
	* @return the number of matching master data attributes
	*/
	public int countByMasterAttribute(java.lang.String masterAttribute);

	/**
	* Returns all the master data attributes where masterId = &#63;.
	*
	* @param masterId the master ID
	* @return the matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId);

	/**
	* Returns a range of all the master data attributes where masterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterId the master ID
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId, int start, int end);

	/**
	* Returns an ordered range of all the master data attributes where masterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterId the master ID
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns an ordered range of all the master data attributes where masterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterId the master ID
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterId(
		java.lang.String masterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterId_First(java.lang.String masterId,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the first master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterId_First(
		java.lang.String masterId,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the last master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterId_Last(java.lang.String masterId,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the last master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterId_Last(java.lang.String masterId,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterId = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterId the master ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute[] findByMasterId_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterId,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Removes all the master data attributes where masterId = &#63; from the database.
	*
	* @param masterId the master ID
	*/
	public void removeByMasterId(java.lang.String masterId);

	/**
	* Returns the number of master data attributes where masterId = &#63;.
	*
	* @param masterId the master ID
	* @return the number of matching master data attributes
	*/
	public int countByMasterId(java.lang.String masterId);

	/**
	* Returns all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @return the matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType);

	/**
	* Returns a range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType, int start, int end);

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns an ordered range of all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching master data attributes
	*/
	public java.util.List<MasterDataAttribute> findByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterDataAttributeUnique_First(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the first master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterDataAttributeUnique_First(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute
	* @throws NoSuchMasterDataAttributeException if a matching master data attribute could not be found
	*/
	public MasterDataAttribute findByMasterDataAttributeUnique_Last(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the last master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching master data attribute, or <code>null</code> if a matching master data attribute could not be found
	*/
	public MasterDataAttribute fetchByMasterDataAttributeUnique_Last(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns the master data attributes before and after the current master data attribute in the ordered set where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterDataAttributeSid the primary key of the current master data attribute
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute[] findByMasterDataAttributeUnique_PrevAndNext(
		int masterDataAttributeSid, java.lang.String masterAttribute,
		java.lang.String masterId, java.lang.String masterType,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator)
		throws NoSuchMasterDataAttributeException;

	/**
	* Removes all the master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63; from the database.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	*/
	public void removeByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType);

	/**
	* Returns the number of master data attributes where masterAttribute = &#63; and masterId = &#63; and masterType = &#63;.
	*
	* @param masterAttribute the master attribute
	* @param masterId the master ID
	* @param masterType the master type
	* @return the number of matching master data attributes
	*/
	public int countByMasterDataAttributeUnique(
		java.lang.String masterAttribute, java.lang.String masterId,
		java.lang.String masterType);

	/**
	* Caches the master data attribute in the entity cache if it is enabled.
	*
	* @param masterDataAttribute the master data attribute
	*/
	public void cacheResult(MasterDataAttribute masterDataAttribute);

	/**
	* Caches the master data attributes in the entity cache if it is enabled.
	*
	* @param masterDataAttributes the master data attributes
	*/
	public void cacheResult(
		java.util.List<MasterDataAttribute> masterDataAttributes);

	/**
	* Creates a new master data attribute with the primary key. Does not add the master data attribute to the database.
	*
	* @param masterDataAttributeSid the primary key for the new master data attribute
	* @return the new master data attribute
	*/
	public MasterDataAttribute create(int masterDataAttributeSid);

	/**
	* Removes the master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataAttributeSid the primary key of the master data attribute
	* @return the master data attribute that was removed
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute remove(int masterDataAttributeSid)
		throws NoSuchMasterDataAttributeException;

	public MasterDataAttribute updateImpl(
		MasterDataAttribute masterDataAttribute);

	/**
	* Returns the master data attribute with the primary key or throws a {@link NoSuchMasterDataAttributeException} if it could not be found.
	*
	* @param masterDataAttributeSid the primary key of the master data attribute
	* @return the master data attribute
	* @throws NoSuchMasterDataAttributeException if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute findByPrimaryKey(int masterDataAttributeSid)
		throws NoSuchMasterDataAttributeException;

	/**
	* Returns the master data attribute with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param masterDataAttributeSid the primary key of the master data attribute
	* @return the master data attribute, or <code>null</code> if a master data attribute with the primary key could not be found
	*/
	public MasterDataAttribute fetchByPrimaryKey(int masterDataAttributeSid);

	@Override
	public java.util.Map<java.io.Serializable, MasterDataAttribute> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the master data attributes.
	*
	* @return the master data attributes
	*/
	public java.util.List<MasterDataAttribute> findAll();

	/**
	* Returns a range of all the master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @return the range of master data attributes
	*/
	public java.util.List<MasterDataAttribute> findAll(int start, int end);

	/**
	* Returns an ordered range of all the master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of master data attributes
	*/
	public java.util.List<MasterDataAttribute> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator);

	/**
	* Returns an ordered range of all the master data attributes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data attributes
	* @param end the upper bound of the range of master data attributes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of master data attributes
	*/
	public java.util.List<MasterDataAttribute> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataAttribute> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the master data attributes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of master data attributes.
	*
	* @return the number of master data attributes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}