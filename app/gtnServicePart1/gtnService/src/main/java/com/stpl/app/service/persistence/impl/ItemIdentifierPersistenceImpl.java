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

package com.stpl.app.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchItemIdentifierException;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.model.impl.ItemIdentifierImpl;
import com.stpl.app.model.impl.ItemIdentifierModelImpl;
import com.stpl.app.service.persistence.ItemIdentifierPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemIdentifierPersistence
 * @see com.stpl.app.service.persistence.ItemIdentifierUtil
 * @generated
 */
@ProviderType
public class ItemIdentifierPersistenceImpl extends BasePersistenceImpl<ItemIdentifier>
	implements ItemIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemIdentifierUtil} to access the item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			ItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			ItemIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER =
		new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			ItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemIrtIdentifier",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER =
		new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			ItemIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemIrtIdentifier",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Date.class.getName()
			},
			ItemIdentifierModelImpl.ITEMIDENTIFIERVALUE_COLUMN_BITMASK |
			ItemIdentifierModelImpl.ITEMQUALIFIERSID_COLUMN_BITMASK |
			ItemIdentifierModelImpl.IDENTIFIERSTATUS_COLUMN_BITMASK |
			ItemIdentifierModelImpl.STARTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER = new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemIrtIdentifier",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @return the matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifier(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate) {
		return findByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
			identifierStatus, startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @return the range of matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifier(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, int start, int end) {
		return findByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
			identifierStatus, startDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifier(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return findByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
			identifierStatus, startDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifier(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER;
			finderArgs = new Object[] {
					itemIdentifierValue, itemQualifierSid, identifierStatus,
					startDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER;
			finderArgs = new Object[] {
					itemIdentifierValue, itemQualifierSid, identifierStatus,
					startDate,
					
					start, end, orderByComparator
				};
		}

		List<ItemIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<ItemIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemIdentifier itemIdentifier : list) {
					if (!Objects.equals(itemIdentifierValue,
								itemIdentifier.getItemIdentifierValue()) ||
							(itemQualifierSid != itemIdentifier.getItemQualifierSid()) ||
							(identifierStatus != itemIdentifier.getIdentifierStatus()) ||
							!Objects.equals(startDate,
								itemIdentifier.getStartDate())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

			boolean bindItemIdentifierValue = false;

			if (itemIdentifierValue == null) {
				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1);
			}
			else if (itemIdentifierValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3);
			}
			else {
				bindItemIdentifierValue = true;

				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2);
			}

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2);

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemIdentifierValue) {
					qPos.add(itemIdentifierValue);
				}

				qPos.add(itemQualifierSid);

				qPos.add(identifierStatus);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
				}

				if (!pagination) {
					list = (List<ItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item identifier
	 * @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier findByItemIrtIdentifier_First(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = fetchByItemIrtIdentifier_First(itemIdentifierValue,
				itemQualifierSid, identifierStatus, startDate, orderByComparator);

		if (itemIdentifier != null) {
			return itemIdentifier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemIdentifierValue=");
		msg.append(itemIdentifierValue);

		msg.append(", itemQualifierSid=");
		msg.append(itemQualifierSid);

		msg.append(", identifierStatus=");
		msg.append(identifierStatus);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemIdentifierException(msg.toString());
	}

	/**
	 * Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier fetchByItemIrtIdentifier_First(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, OrderByComparator<ItemIdentifier> orderByComparator) {
		List<ItemIdentifier> list = findByItemIrtIdentifier(itemIdentifierValue,
				itemQualifierSid, identifierStatus, startDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item identifier
	 * @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier findByItemIrtIdentifier_Last(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = fetchByItemIrtIdentifier_Last(itemIdentifierValue,
				itemQualifierSid, identifierStatus, startDate, orderByComparator);

		if (itemIdentifier != null) {
			return itemIdentifier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemIdentifierValue=");
		msg.append(itemIdentifierValue);

		msg.append(", itemQualifierSid=");
		msg.append(itemQualifierSid);

		msg.append(", identifierStatus=");
		msg.append(identifierStatus);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemIdentifierException(msg.toString());
	}

	/**
	 * Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier fetchByItemIrtIdentifier_Last(
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, OrderByComparator<ItemIdentifier> orderByComparator) {
		int count = countByItemIrtIdentifier(itemIdentifierValue,
				itemQualifierSid, identifierStatus, startDate);

		if (count == 0) {
			return null;
		}

		List<ItemIdentifier> list = findByItemIrtIdentifier(itemIdentifierValue,
				itemQualifierSid, identifierStatus, startDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item identifiers before and after the current item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierSid the primary key of the current item identifier
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item identifier
	 * @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier[] findByItemIrtIdentifier_PrevAndNext(
		int itemIdentifierSid, String itemIdentifierValue,
		int itemQualifierSid, int identifierStatus, Date startDate,
		OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = findByPrimaryKey(itemIdentifierSid);

		Session session = null;

		try {
			session = openSession();

			ItemIdentifier[] array = new ItemIdentifierImpl[3];

			array[0] = getByItemIrtIdentifier_PrevAndNext(session,
					itemIdentifier, itemIdentifierValue, itemQualifierSid,
					identifierStatus, startDate, orderByComparator, true);

			array[1] = itemIdentifier;

			array[2] = getByItemIrtIdentifier_PrevAndNext(session,
					itemIdentifier, itemIdentifierValue, itemQualifierSid,
					identifierStatus, startDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemIdentifier getByItemIrtIdentifier_PrevAndNext(
		Session session, ItemIdentifier itemIdentifier,
		String itemIdentifierValue, int itemQualifierSid, int identifierStatus,
		Date startDate, OrderByComparator<ItemIdentifier> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

		boolean bindItemIdentifierValue = false;

		if (itemIdentifierValue == null) {
			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1);
		}
		else if (itemIdentifierValue.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3);
		}
		else {
			bindItemIdentifierValue = true;

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2);
		}

		query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2);

		query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2);

		boolean bindStartDate = false;

		if (startDate == null) {
			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1);
		}
		else {
			bindStartDate = true;

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindItemIdentifierValue) {
			qPos.add(itemIdentifierValue);
		}

		qPos.add(itemQualifierSid);

		qPos.add(identifierStatus);

		if (bindStartDate) {
			qPos.add(new Timestamp(startDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemIdentifier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemIdentifier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 */
	@Override
	public void removeByItemIrtIdentifier(String itemIdentifierValue,
		int itemQualifierSid, int identifierStatus, Date startDate) {
		for (ItemIdentifier itemIdentifier : findByItemIrtIdentifier(
				itemIdentifierValue, itemQualifierSid, identifierStatus,
				startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemIdentifier);
		}
	}

	/**
	 * Returns the number of item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemIdentifierValue the item identifier value
	 * @param itemQualifierSid the item qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @return the number of matching item identifiers
	 */
	@Override
	public int countByItemIrtIdentifier(String itemIdentifierValue,
		int itemQualifierSid, int identifierStatus, Date startDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER;

		Object[] finderArgs = new Object[] {
				itemIdentifierValue, itemQualifierSid, identifierStatus,
				startDate
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_ITEMIDENTIFIER_WHERE);

			boolean bindItemIdentifierValue = false;

			if (itemIdentifierValue == null) {
				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1);
			}
			else if (itemIdentifierValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3);
			}
			else {
				bindItemIdentifierValue = true;

				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2);
			}

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2);

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemIdentifierValue) {
					qPos.add(itemIdentifierValue);
				}

				qPos.add(itemQualifierSid);

				qPos.add(identifierStatus);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_1 =
		"itemIdentifier.itemIdentifierValue IS NULL AND ";
	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_2 =
		"itemIdentifier.itemIdentifierValue = ? AND ";
	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMIDENTIFIERVALUE_3 =
		"(itemIdentifier.itemIdentifierValue IS NULL OR itemIdentifier.itemIdentifierValue = '') AND ";
	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_ITEMQUALIFIERSID_2 =
		"itemIdentifier.itemQualifierSid = ? AND ";
	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_IDENTIFIERSTATUS_2 =
		"itemIdentifier.identifierStatus = ? AND ";
	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_1 = "itemIdentifier.startDate IS NULL";
	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIER_STARTDATE_2 = "itemIdentifier.startDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS =
		new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			ItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemIrtIdentifierDetails",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS =
		new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			ItemIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemIrtIdentifierDetails",
			new String[] { Integer.class.getName() },
			ItemIdentifierModelImpl.ITEMMASTERSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS =
		new FinderPath(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemIrtIdentifierDetails",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item identifiers where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @return the matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid) {
		return findByItemIrtIdentifierDetails(itemMasterSid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item identifiers where itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @return the range of matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end) {
		return findByItemIrtIdentifierDetails(itemMasterSid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item identifiers where itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return findByItemIrtIdentifierDetails(itemMasterSid, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item identifiers where itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item identifiers
	 */
	@Override
	public List<ItemIdentifier> findByItemIrtIdentifierDetails(
		int itemMasterSid, int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS;
			finderArgs = new Object[] { itemMasterSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS;
			finderArgs = new Object[] {
					itemMasterSid,
					
					start, end, orderByComparator
				};
		}

		List<ItemIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<ItemIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemIdentifier itemIdentifier : list) {
					if ((itemMasterSid != itemIdentifier.getItemMasterSid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemMasterSid);

				if (!pagination) {
					list = (List<ItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item identifier
	 * @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier findByItemIrtIdentifierDetails_First(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = fetchByItemIrtIdentifierDetails_First(itemMasterSid,
				orderByComparator);

		if (itemIdentifier != null) {
			return itemIdentifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemIdentifierException(msg.toString());
	}

	/**
	 * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier fetchByItemIrtIdentifierDetails_First(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator) {
		List<ItemIdentifier> list = findByItemIrtIdentifierDetails(itemMasterSid,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item identifier
	 * @throws NoSuchItemIdentifierException if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier findByItemIrtIdentifierDetails_Last(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = fetchByItemIrtIdentifierDetails_Last(itemMasterSid,
				orderByComparator);

		if (itemIdentifier != null) {
			return itemIdentifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemIdentifierException(msg.toString());
	}

	/**
	 * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
	 */
	@Override
	public ItemIdentifier fetchByItemIrtIdentifierDetails_Last(
		int itemMasterSid, OrderByComparator<ItemIdentifier> orderByComparator) {
		int count = countByItemIrtIdentifierDetails(itemMasterSid);

		if (count == 0) {
			return null;
		}

		List<ItemIdentifier> list = findByItemIrtIdentifierDetails(itemMasterSid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item identifiers before and after the current item identifier in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemIdentifierSid the primary key of the current item identifier
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item identifier
	 * @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier[] findByItemIrtIdentifierDetails_PrevAndNext(
		int itemIdentifierSid, int itemMasterSid,
		OrderByComparator<ItemIdentifier> orderByComparator)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = findByPrimaryKey(itemIdentifierSid);

		Session session = null;

		try {
			session = openSession();

			ItemIdentifier[] array = new ItemIdentifierImpl[3];

			array[0] = getByItemIrtIdentifierDetails_PrevAndNext(session,
					itemIdentifier, itemMasterSid, orderByComparator, true);

			array[1] = itemIdentifier;

			array[2] = getByItemIrtIdentifierDetails_PrevAndNext(session,
					itemIdentifier, itemMasterSid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemIdentifier getByItemIrtIdentifierDetails_PrevAndNext(
		Session session, ItemIdentifier itemIdentifier, int itemMasterSid,
		OrderByComparator<ItemIdentifier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE);

		query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ItemIdentifierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(itemMasterSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemIdentifier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemIdentifier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item identifiers where itemMasterSid = &#63; from the database.
	 *
	 * @param itemMasterSid the item master sid
	 */
	@Override
	public void removeByItemIrtIdentifierDetails(int itemMasterSid) {
		for (ItemIdentifier itemIdentifier : findByItemIrtIdentifierDetails(
				itemMasterSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemIdentifier);
		}
	}

	/**
	 * Returns the number of item identifiers where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @return the number of matching item identifiers
	 */
	@Override
	public int countByItemIrtIdentifierDetails(int itemMasterSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS;

		Object[] finderArgs = new Object[] { itemMasterSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMIDENTIFIER_WHERE);

			query.append(_FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemMasterSid);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ITEMIRTIDENTIFIERDETAILS_ITEMMASTERSID_2 =
		"itemIdentifier.itemMasterSid = ?";

	public ItemIdentifierPersistenceImpl() {
		setModelClass(ItemIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemIdentifierSid", "ITEM_IDENTIFIER_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("identifierStatus", "IDENTIFIER_STATUS");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("itemIdentifierValue", "ITEM_IDENTIFIER_VALUE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("itemQualifierSid", "ITEM_QUALIFIER_SID");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the item identifier in the entity cache if it is enabled.
	 *
	 * @param itemIdentifier the item identifier
	 */
	@Override
	public void cacheResult(ItemIdentifier itemIdentifier) {
		entityCache.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey(),
			itemIdentifier);

		itemIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the item identifiers in the entity cache if it is enabled.
	 *
	 * @param itemIdentifiers the item identifiers
	 */
	@Override
	public void cacheResult(List<ItemIdentifier> itemIdentifiers) {
		for (ItemIdentifier itemIdentifier : itemIdentifiers) {
			if (entityCache.getResult(
						ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey()) == null) {
				cacheResult(itemIdentifier);
			}
			else {
				itemIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemIdentifier itemIdentifier) {
		entityCache.removeResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ItemIdentifier> itemIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemIdentifier itemIdentifier : itemIdentifiers) {
			entityCache.removeResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new item identifier with the primary key. Does not add the item identifier to the database.
	 *
	 * @param itemIdentifierSid the primary key for the new item identifier
	 * @return the new item identifier
	 */
	@Override
	public ItemIdentifier create(int itemIdentifierSid) {
		ItemIdentifier itemIdentifier = new ItemIdentifierImpl();

		itemIdentifier.setNew(true);
		itemIdentifier.setPrimaryKey(itemIdentifierSid);

		return itemIdentifier;
	}

	/**
	 * Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemIdentifierSid the primary key of the item identifier
	 * @return the item identifier that was removed
	 * @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier remove(int itemIdentifierSid)
		throws NoSuchItemIdentifierException {
		return remove((Serializable)itemIdentifierSid);
	}

	/**
	 * Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item identifier
	 * @return the item identifier that was removed
	 * @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier remove(Serializable primaryKey)
		throws NoSuchItemIdentifierException {
		Session session = null;

		try {
			session = openSession();

			ItemIdentifier itemIdentifier = (ItemIdentifier)session.get(ItemIdentifierImpl.class,
					primaryKey);

			if (itemIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemIdentifier);
		}
		catch (NoSuchItemIdentifierException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ItemIdentifier removeImpl(ItemIdentifier itemIdentifier) {
		itemIdentifier = toUnwrappedModel(itemIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemIdentifier)) {
				itemIdentifier = (ItemIdentifier)session.get(ItemIdentifierImpl.class,
						itemIdentifier.getPrimaryKeyObj());
			}

			if (itemIdentifier != null) {
				session.delete(itemIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemIdentifier != null) {
			clearCache(itemIdentifier);
		}

		return itemIdentifier;
	}

	@Override
	public ItemIdentifier updateImpl(ItemIdentifier itemIdentifier) {
		itemIdentifier = toUnwrappedModel(itemIdentifier);

		boolean isNew = itemIdentifier.isNew();

		ItemIdentifierModelImpl itemIdentifierModelImpl = (ItemIdentifierModelImpl)itemIdentifier;

		Session session = null;

		try {
			session = openSession();

			if (itemIdentifier.isNew()) {
				session.save(itemIdentifier);

				itemIdentifier.setNew(false);
			}
			else {
				itemIdentifier = (ItemIdentifier)session.merge(itemIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemIdentifierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					itemIdentifierModelImpl.getItemIdentifierValue(),
					itemIdentifierModelImpl.getItemQualifierSid(),
					itemIdentifierModelImpl.getIdentifierStatus(),
					itemIdentifierModelImpl.getStartDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER,
				args);

			args = new Object[] { itemIdentifierModelImpl.getItemMasterSid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((itemIdentifierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemIdentifierModelImpl.getOriginalItemIdentifierValue(),
						itemIdentifierModelImpl.getOriginalItemQualifierSid(),
						itemIdentifierModelImpl.getOriginalIdentifierStatus(),
						itemIdentifierModelImpl.getOriginalStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER,
					args);

				args = new Object[] {
						itemIdentifierModelImpl.getItemIdentifierValue(),
						itemIdentifierModelImpl.getItemQualifierSid(),
						itemIdentifierModelImpl.getIdentifierStatus(),
						itemIdentifierModelImpl.getStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIER,
					args);
			}

			if ((itemIdentifierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemIdentifierModelImpl.getOriginalItemMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS,
					args);

				args = new Object[] { itemIdentifierModelImpl.getItemMasterSid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTIDENTIFIERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTIDENTIFIERDETAILS,
					args);
			}
		}

		entityCache.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemIdentifierImpl.class, itemIdentifier.getPrimaryKey(),
			itemIdentifier, false);

		itemIdentifier.resetOriginalValues();

		return itemIdentifier;
	}

	protected ItemIdentifier toUnwrappedModel(ItemIdentifier itemIdentifier) {
		if (itemIdentifier instanceof ItemIdentifierImpl) {
			return itemIdentifier;
		}

		ItemIdentifierImpl itemIdentifierImpl = new ItemIdentifierImpl();

		itemIdentifierImpl.setNew(itemIdentifier.isNew());
		itemIdentifierImpl.setPrimaryKey(itemIdentifier.getPrimaryKey());

		itemIdentifierImpl.setItemIdentifierSid(itemIdentifier.getItemIdentifierSid());
		itemIdentifierImpl.setItemMasterSid(itemIdentifier.getItemMasterSid());
		itemIdentifierImpl.setEndDate(itemIdentifier.getEndDate());
		itemIdentifierImpl.setModifiedDate(itemIdentifier.getModifiedDate());
		itemIdentifierImpl.setIdentifierStatus(itemIdentifier.getIdentifierStatus());
		itemIdentifierImpl.setEntityCode(itemIdentifier.getEntityCode());
		itemIdentifierImpl.setItemIdentifierValue(itemIdentifier.getItemIdentifierValue());
		itemIdentifierImpl.setRecordLockStatus(itemIdentifier.isRecordLockStatus());
		itemIdentifierImpl.setItemQualifierSid(itemIdentifier.getItemQualifierSid());
		itemIdentifierImpl.setStartDate(itemIdentifier.getStartDate());
		itemIdentifierImpl.setCreatedDate(itemIdentifier.getCreatedDate());
		itemIdentifierImpl.setSource(itemIdentifier.getSource());
		itemIdentifierImpl.setCreatedBy(itemIdentifier.getCreatedBy());
		itemIdentifierImpl.setBatchId(itemIdentifier.getBatchId());
		itemIdentifierImpl.setModifiedBy(itemIdentifier.getModifiedBy());
		itemIdentifierImpl.setInboundStatus(itemIdentifier.getInboundStatus());

		return itemIdentifierImpl;
	}

	/**
	 * Returns the item identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item identifier
	 * @return the item identifier
	 * @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemIdentifierException {
		ItemIdentifier itemIdentifier = fetchByPrimaryKey(primaryKey);

		if (itemIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemIdentifier;
	}

	/**
	 * Returns the item identifier with the primary key or throws a {@link NoSuchItemIdentifierException} if it could not be found.
	 *
	 * @param itemIdentifierSid the primary key of the item identifier
	 * @return the item identifier
	 * @throws NoSuchItemIdentifierException if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier findByPrimaryKey(int itemIdentifierSid)
		throws NoSuchItemIdentifierException {
		return findByPrimaryKey((Serializable)itemIdentifierSid);
	}

	/**
	 * Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item identifier
	 * @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				ItemIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemIdentifier itemIdentifier = (ItemIdentifier)serializable;

		if (itemIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				itemIdentifier = (ItemIdentifier)session.get(ItemIdentifierImpl.class,
						primaryKey);

				if (itemIdentifier != null) {
					cacheResult(itemIdentifier);
				}
				else {
					entityCache.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						ItemIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemIdentifier;
	}

	/**
	 * Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemIdentifierSid the primary key of the item identifier
	 * @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
	 */
	@Override
	public ItemIdentifier fetchByPrimaryKey(int itemIdentifierSid) {
		return fetchByPrimaryKey((Serializable)itemIdentifierSid);
	}

	@Override
	public Map<Serializable, ItemIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemIdentifier> map = new HashMap<Serializable, ItemIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemIdentifier itemIdentifier = fetchByPrimaryKey(primaryKey);

			if (itemIdentifier != null) {
				map.put(primaryKey, itemIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemIdentifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMIDENTIFIER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (ItemIdentifier itemIdentifier : (List<ItemIdentifier>)q.list()) {
				map.put(itemIdentifier.getPrimaryKeyObj(), itemIdentifier);

				cacheResult(itemIdentifier);

				uncachedPrimaryKeys.remove(itemIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemIdentifierImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the item identifiers.
	 *
	 * @return the item identifiers
	 */
	@Override
	public List<ItemIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @return the range of item identifiers
	 */
	@Override
	public List<ItemIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item identifiers
	 */
	@Override
	public List<ItemIdentifier> findAll(int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item identifiers
	 * @param end the upper bound of the range of item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item identifiers
	 */
	@Override
	public List<ItemIdentifier> findAll(int start, int end,
		OrderByComparator<ItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<ItemIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<ItemIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMIDENTIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMIDENTIFIER;

				if (pagination) {
					sql = sql.concat(ItemIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the item identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemIdentifier itemIdentifier : findAll()) {
			remove(itemIdentifier);
		}
	}

	/**
	 * Returns the number of item identifiers.
	 *
	 * @return the number of item identifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMIDENTIFIER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ItemIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMIDENTIFIER = "SELECT itemIdentifier FROM ItemIdentifier itemIdentifier";
	private static final String _SQL_SELECT_ITEMIDENTIFIER_WHERE_PKS_IN = "SELECT itemIdentifier FROM ItemIdentifier itemIdentifier WHERE ITEM_IDENTIFIER_SID IN (";
	private static final String _SQL_SELECT_ITEMIDENTIFIER_WHERE = "SELECT itemIdentifier FROM ItemIdentifier itemIdentifier WHERE ";
	private static final String _SQL_COUNT_ITEMIDENTIFIER = "SELECT COUNT(itemIdentifier) FROM ItemIdentifier itemIdentifier";
	private static final String _SQL_COUNT_ITEMIDENTIFIER_WHERE = "SELECT COUNT(itemIdentifier) FROM ItemIdentifier itemIdentifier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemIdentifier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemIdentifier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemIdentifierSid", "itemMasterSid", "endDate", "modifiedDate",
				"identifierStatus", "entityCode", "itemIdentifierValue",
				"recordLockStatus", "itemQualifierSid", "startDate",
				"createdDate", "source", "createdBy", "batchId", "modifiedBy",
				"inboundStatus"
			});
}