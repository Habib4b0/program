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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchItemQualifierException;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.model.impl.ItemQualifierImpl;
import com.stpl.app.model.impl.ItemQualifierModelImpl;
import com.stpl.app.service.persistence.ItemQualifierPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the item qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemQualifierPersistence
 * @see com.stpl.app.service.persistence.ItemQualifierUtil
 * @generated
 */
@ProviderType
public class ItemQualifierPersistenceImpl extends BasePersistenceImpl<ItemQualifier>
	implements ItemQualifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemQualifierUtil} to access the item qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemQualifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME =
		new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemIrtQualifierName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME =
		new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemIrtQualifierName",
			new String[] { String.class.getName() },
			ItemQualifierModelImpl.ITEMQUALIFIERVALUE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemIrtQualifierName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item qualifiers where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @return the matching item qualifiers
	 */
	@Override
	public List<ItemQualifier> findByItemIrtQualifierName(
		String itemQualifierValue) {
		return findByItemIrtQualifierName(itemQualifierValue,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item qualifiers where itemQualifierValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param start the lower bound of the range of item qualifiers
	 * @param end the upper bound of the range of item qualifiers (not inclusive)
	 * @return the range of matching item qualifiers
	 */
	@Override
	public List<ItemQualifier> findByItemIrtQualifierName(
		String itemQualifierValue, int start, int end) {
		return findByItemIrtQualifierName(itemQualifierValue, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item qualifiers where itemQualifierValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param start the lower bound of the range of item qualifiers
	 * @param end the upper bound of the range of item qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item qualifiers
	 */
	@Override
	public List<ItemQualifier> findByItemIrtQualifierName(
		String itemQualifierValue, int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return findByItemIrtQualifierName(itemQualifierValue, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item qualifiers where itemQualifierValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param start the lower bound of the range of item qualifiers
	 * @param end the upper bound of the range of item qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item qualifiers
	 */
	@Override
	public List<ItemQualifier> findByItemIrtQualifierName(
		String itemQualifierValue, int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME;
			finderArgs = new Object[] { itemQualifierValue };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME;
			finderArgs = new Object[] {
					itemQualifierValue,
					
					start, end, orderByComparator
				};
		}

		List<ItemQualifier> list = null;

		if (retrieveFromCache) {
			list = (List<ItemQualifier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemQualifier itemQualifier : list) {
					if (!Objects.equals(itemQualifierValue,
								itemQualifier.getItemQualifierValue())) {
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

			query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE);

			boolean bindItemQualifierValue = false;

			if (itemQualifierValue == null) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1);
			}
			else if (itemQualifierValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3);
			}
			else {
				bindItemQualifierValue = true;

				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemQualifierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemQualifierValue) {
					qPos.add(itemQualifierValue);
				}

				if (!pagination) {
					list = (List<ItemQualifier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemQualifier>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item qualifier
	 * @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier findByItemIrtQualifierName_First(
		String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator)
		throws NoSuchItemQualifierException {
		ItemQualifier itemQualifier = fetchByItemIrtQualifierName_First(itemQualifierValue,
				orderByComparator);

		if (itemQualifier != null) {
			return itemQualifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemQualifierValue=");
		msg.append(itemQualifierValue);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemQualifierException(msg.toString());
	}

	/**
	 * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier fetchByItemIrtQualifierName_First(
		String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator) {
		List<ItemQualifier> list = findByItemIrtQualifierName(itemQualifierValue,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item qualifier
	 * @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier findByItemIrtQualifierName_Last(
		String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator)
		throws NoSuchItemQualifierException {
		ItemQualifier itemQualifier = fetchByItemIrtQualifierName_Last(itemQualifierValue,
				orderByComparator);

		if (itemQualifier != null) {
			return itemQualifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemQualifierValue=");
		msg.append(itemQualifierValue);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemQualifierException(msg.toString());
	}

	/**
	 * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier fetchByItemIrtQualifierName_Last(
		String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator) {
		int count = countByItemIrtQualifierName(itemQualifierValue);

		if (count == 0) {
			return null;
		}

		List<ItemQualifier> list = findByItemIrtQualifierName(itemQualifierValue,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item qualifiers before and after the current item qualifier in the ordered set where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierSid the primary key of the current item qualifier
	 * @param itemQualifierValue the item qualifier value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item qualifier
	 * @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier[] findByItemIrtQualifierName_PrevAndNext(
		int itemQualifierSid, String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator)
		throws NoSuchItemQualifierException {
		ItemQualifier itemQualifier = findByPrimaryKey(itemQualifierSid);

		Session session = null;

		try {
			session = openSession();

			ItemQualifier[] array = new ItemQualifierImpl[3];

			array[0] = getByItemIrtQualifierName_PrevAndNext(session,
					itemQualifier, itemQualifierValue, orderByComparator, true);

			array[1] = itemQualifier;

			array[2] = getByItemIrtQualifierName_PrevAndNext(session,
					itemQualifier, itemQualifierValue, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemQualifier getByItemIrtQualifierName_PrevAndNext(
		Session session, ItemQualifier itemQualifier,
		String itemQualifierValue,
		OrderByComparator<ItemQualifier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE);

		boolean bindItemQualifierValue = false;

		if (itemQualifierValue == null) {
			query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1);
		}
		else if (itemQualifierValue.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3);
		}
		else {
			bindItemQualifierValue = true;

			query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2);
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
			query.append(ItemQualifierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindItemQualifierValue) {
			qPos.add(itemQualifierValue);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemQualifier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemQualifier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item qualifiers where itemQualifierValue = &#63; from the database.
	 *
	 * @param itemQualifierValue the item qualifier value
	 */
	@Override
	public void removeByItemIrtQualifierName(String itemQualifierValue) {
		for (ItemQualifier itemQualifier : findByItemIrtQualifierName(
				itemQualifierValue, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemQualifier);
		}
	}

	/**
	 * Returns the number of item qualifiers where itemQualifierValue = &#63;.
	 *
	 * @param itemQualifierValue the item qualifier value
	 * @return the number of matching item qualifiers
	 */
	@Override
	public int countByItemIrtQualifierName(String itemQualifierValue) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME;

		Object[] finderArgs = new Object[] { itemQualifierValue };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMQUALIFIER_WHERE);

			boolean bindItemQualifierValue = false;

			if (itemQualifierValue == null) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1);
			}
			else if (itemQualifierValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3);
			}
			else {
				bindItemQualifierValue = true;

				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemQualifierValue) {
					qPos.add(itemQualifierValue);
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

	private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_1 =
		"itemQualifier.itemQualifierValue IS NULL";
	private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_2 =
		"itemQualifier.itemQualifierValue = ?";
	private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERNAME_ITEMQUALIFIERVALUE_3 =
		"(itemQualifier.itemQualifierValue IS NULL OR itemQualifier.itemQualifierValue = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemQualifierImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByItemIrtQualifierByName",
			new String[] { String.class.getName() },
			ItemQualifierModelImpl.ITEMQUALIFIERNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME = new FinderPath(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemIrtQualifierByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the item qualifier where itemQualifierName = &#63; or throws a {@link NoSuchItemQualifierException} if it could not be found.
	 *
	 * @param itemQualifierName the item qualifier name
	 * @return the matching item qualifier
	 * @throws NoSuchItemQualifierException if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier findByItemIrtQualifierByName(String itemQualifierName)
		throws NoSuchItemQualifierException {
		ItemQualifier itemQualifier = fetchByItemIrtQualifierByName(itemQualifierName);

		if (itemQualifier == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itemQualifierName=");
			msg.append(itemQualifierName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchItemQualifierException(msg.toString());
		}

		return itemQualifier;
	}

	/**
	 * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemQualifierName the item qualifier name
	 * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier fetchByItemIrtQualifierByName(String itemQualifierName) {
		return fetchByItemIrtQualifierByName(itemQualifierName, true);
	}

	/**
	 * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemQualifierName the item qualifier name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
	 */
	@Override
	public ItemQualifier fetchByItemIrtQualifierByName(
		String itemQualifierName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemQualifierName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
					finderArgs, this);
		}

		if (result instanceof ItemQualifier) {
			ItemQualifier itemQualifier = (ItemQualifier)result;

			if (!Objects.equals(itemQualifierName,
						itemQualifier.getItemQualifierName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE);

			boolean bindItemQualifierName = false;

			if (itemQualifierName == null) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_1);
			}
			else if (itemQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_3);
			}
			else {
				bindItemQualifierName = true;

				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemQualifierName) {
					qPos.add(itemQualifierName);
				}

				List<ItemQualifier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ItemQualifierPersistenceImpl.fetchByItemIrtQualifierByName(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ItemQualifier itemQualifier = list.get(0);

					result = itemQualifier;

					cacheResult(itemQualifier);

					if ((itemQualifier.getItemQualifierName() == null) ||
							!itemQualifier.getItemQualifierName()
											  .equals(itemQualifierName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
							finderArgs, itemQualifier);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ItemQualifier)result;
		}
	}

	/**
	 * Removes the item qualifier where itemQualifierName = &#63; from the database.
	 *
	 * @param itemQualifierName the item qualifier name
	 * @return the item qualifier that was removed
	 */
	@Override
	public ItemQualifier removeByItemIrtQualifierByName(
		String itemQualifierName) throws NoSuchItemQualifierException {
		ItemQualifier itemQualifier = findByItemIrtQualifierByName(itemQualifierName);

		return remove(itemQualifier);
	}

	/**
	 * Returns the number of item qualifiers where itemQualifierName = &#63;.
	 *
	 * @param itemQualifierName the item qualifier name
	 * @return the number of matching item qualifiers
	 */
	@Override
	public int countByItemIrtQualifierByName(String itemQualifierName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME;

		Object[] finderArgs = new Object[] { itemQualifierName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMQUALIFIER_WHERE);

			boolean bindItemQualifierName = false;

			if (itemQualifierName == null) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_1);
			}
			else if (itemQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_3);
			}
			else {
				bindItemQualifierName = true;

				query.append(_FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemQualifierName) {
					qPos.add(itemQualifierName);
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

	private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_1 =
		"itemQualifier.itemQualifierName IS NULL";
	private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_2 =
		"itemQualifier.itemQualifierName = ?";
	private static final String _FINDER_COLUMN_ITEMIRTQUALIFIERBYNAME_ITEMQUALIFIERNAME_3 =
		"(itemQualifier.itemQualifierName IS NULL OR itemQualifier.itemQualifierName = '')";

	public ItemQualifierPersistenceImpl() {
		setModelClass(ItemQualifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemQualifierSid", "ITEM_QUALIFIER_SID");
			dbColumnNames.put("specificEntityCode", "SPECIFIC_ENTITY_CODE");
			dbColumnNames.put("itemQualifierName", "ITEM_QUALIFIER_NAME");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("effectiveDates", "EFFECTIVE_DATES");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("itemQualifierValue", "ITEM_QUALIFIER_VALUE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("source", "SOURCE");
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
	 * Caches the item qualifier in the entity cache if it is enabled.
	 *
	 * @param itemQualifier the item qualifier
	 */
	@Override
	public void cacheResult(ItemQualifier itemQualifier) {
		entityCache.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierImpl.class, itemQualifier.getPrimaryKey(),
			itemQualifier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
			new Object[] { itemQualifier.getItemQualifierName() }, itemQualifier);

		itemQualifier.resetOriginalValues();
	}

	/**
	 * Caches the item qualifiers in the entity cache if it is enabled.
	 *
	 * @param itemQualifiers the item qualifiers
	 */
	@Override
	public void cacheResult(List<ItemQualifier> itemQualifiers) {
		for (ItemQualifier itemQualifier : itemQualifiers) {
			if (entityCache.getResult(
						ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
						ItemQualifierImpl.class, itemQualifier.getPrimaryKey()) == null) {
				cacheResult(itemQualifier);
			}
			else {
				itemQualifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item qualifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemQualifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item qualifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemQualifier itemQualifier) {
		entityCache.removeResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierImpl.class, itemQualifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ItemQualifierModelImpl)itemQualifier, true);
	}

	@Override
	public void clearCache(List<ItemQualifier> itemQualifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemQualifier itemQualifier : itemQualifiers) {
			entityCache.removeResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
				ItemQualifierImpl.class, itemQualifier.getPrimaryKey());

			clearUniqueFindersCache((ItemQualifierModelImpl)itemQualifier, true);
		}
	}

	protected void cacheUniqueFindersCache(
		ItemQualifierModelImpl itemQualifierModelImpl) {
		Object[] args = new Object[] {
				itemQualifierModelImpl.getItemQualifierName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
			args, itemQualifierModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ItemQualifierModelImpl itemQualifierModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					itemQualifierModelImpl.getItemQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
				args);
		}

		if ((itemQualifierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					itemQualifierModelImpl.getOriginalItemQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERBYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ITEMIRTQUALIFIERBYNAME,
				args);
		}
	}

	/**
	 * Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
	 *
	 * @param itemQualifierSid the primary key for the new item qualifier
	 * @return the new item qualifier
	 */
	@Override
	public ItemQualifier create(int itemQualifierSid) {
		ItemQualifier itemQualifier = new ItemQualifierImpl();

		itemQualifier.setNew(true);
		itemQualifier.setPrimaryKey(itemQualifierSid);

		return itemQualifier;
	}

	/**
	 * Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemQualifierSid the primary key of the item qualifier
	 * @return the item qualifier that was removed
	 * @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier remove(int itemQualifierSid)
		throws NoSuchItemQualifierException {
		return remove((Serializable)itemQualifierSid);
	}

	/**
	 * Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item qualifier
	 * @return the item qualifier that was removed
	 * @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier remove(Serializable primaryKey)
		throws NoSuchItemQualifierException {
		Session session = null;

		try {
			session = openSession();

			ItemQualifier itemQualifier = (ItemQualifier)session.get(ItemQualifierImpl.class,
					primaryKey);

			if (itemQualifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemQualifier);
		}
		catch (NoSuchItemQualifierException nsee) {
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
	protected ItemQualifier removeImpl(ItemQualifier itemQualifier) {
		itemQualifier = toUnwrappedModel(itemQualifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemQualifier)) {
				itemQualifier = (ItemQualifier)session.get(ItemQualifierImpl.class,
						itemQualifier.getPrimaryKeyObj());
			}

			if (itemQualifier != null) {
				session.delete(itemQualifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemQualifier != null) {
			clearCache(itemQualifier);
		}

		return itemQualifier;
	}

	@Override
	public ItemQualifier updateImpl(ItemQualifier itemQualifier) {
		itemQualifier = toUnwrappedModel(itemQualifier);

		boolean isNew = itemQualifier.isNew();

		ItemQualifierModelImpl itemQualifierModelImpl = (ItemQualifierModelImpl)itemQualifier;

		Session session = null;

		try {
			session = openSession();

			if (itemQualifier.isNew()) {
				session.save(itemQualifier);

				itemQualifier.setNew(false);
			}
			else {
				itemQualifier = (ItemQualifier)session.merge(itemQualifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemQualifierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					itemQualifierModelImpl.getItemQualifierValue()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((itemQualifierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemQualifierModelImpl.getOriginalItemQualifierValue()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME,
					args);

				args = new Object[] {
						itemQualifierModelImpl.getItemQualifierValue()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMIRTQUALIFIERNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMIRTQUALIFIERNAME,
					args);
			}
		}

		entityCache.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemQualifierImpl.class, itemQualifier.getPrimaryKey(),
			itemQualifier, false);

		clearUniqueFindersCache(itemQualifierModelImpl, false);
		cacheUniqueFindersCache(itemQualifierModelImpl);

		itemQualifier.resetOriginalValues();

		return itemQualifier;
	}

	protected ItemQualifier toUnwrappedModel(ItemQualifier itemQualifier) {
		if (itemQualifier instanceof ItemQualifierImpl) {
			return itemQualifier;
		}

		ItemQualifierImpl itemQualifierImpl = new ItemQualifierImpl();

		itemQualifierImpl.setNew(itemQualifier.isNew());
		itemQualifierImpl.setPrimaryKey(itemQualifier.getPrimaryKey());

		itemQualifierImpl.setCreatedBy(itemQualifier.getCreatedBy());
		itemQualifierImpl.setItemQualifierSid(itemQualifier.getItemQualifierSid());
		itemQualifierImpl.setSpecificEntityCode(itemQualifier.getSpecificEntityCode());
		itemQualifierImpl.setItemQualifierName(itemQualifier.getItemQualifierName());
		itemQualifierImpl.setModifiedBy(itemQualifier.getModifiedBy());
		itemQualifierImpl.setCreatedDate(itemQualifier.getCreatedDate());
		itemQualifierImpl.setBatchId(itemQualifier.getBatchId());
		itemQualifierImpl.setModifiedDate(itemQualifier.getModifiedDate());
		itemQualifierImpl.setEffectiveDates(itemQualifier.getEffectiveDates());
		itemQualifierImpl.setNotes(itemQualifier.getNotes());
		itemQualifierImpl.setItemQualifierValue(itemQualifier.getItemQualifierValue());
		itemQualifierImpl.setRecordLockStatus(itemQualifier.isRecordLockStatus());
		itemQualifierImpl.setSource(itemQualifier.getSource());
		itemQualifierImpl.setInboundStatus(itemQualifier.getInboundStatus());

		return itemQualifierImpl;
	}

	/**
	 * Returns the item qualifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item qualifier
	 * @return the item qualifier
	 * @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemQualifierException {
		ItemQualifier itemQualifier = fetchByPrimaryKey(primaryKey);

		if (itemQualifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemQualifier;
	}

	/**
	 * Returns the item qualifier with the primary key or throws a {@link NoSuchItemQualifierException} if it could not be found.
	 *
	 * @param itemQualifierSid the primary key of the item qualifier
	 * @return the item qualifier
	 * @throws NoSuchItemQualifierException if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier findByPrimaryKey(int itemQualifierSid)
		throws NoSuchItemQualifierException {
		return findByPrimaryKey((Serializable)itemQualifierSid);
	}

	/**
	 * Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item qualifier
	 * @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
				ItemQualifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemQualifier itemQualifier = (ItemQualifier)serializable;

		if (itemQualifier == null) {
			Session session = null;

			try {
				session = openSession();

				itemQualifier = (ItemQualifier)session.get(ItemQualifierImpl.class,
						primaryKey);

				if (itemQualifier != null) {
					cacheResult(itemQualifier);
				}
				else {
					entityCache.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
						ItemQualifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemQualifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemQualifier;
	}

	/**
	 * Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemQualifierSid the primary key of the item qualifier
	 * @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
	 */
	@Override
	public ItemQualifier fetchByPrimaryKey(int itemQualifierSid) {
		return fetchByPrimaryKey((Serializable)itemQualifierSid);
	}

	@Override
	public Map<Serializable, ItemQualifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemQualifier> map = new HashMap<Serializable, ItemQualifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemQualifier itemQualifier = fetchByPrimaryKey(primaryKey);

			if (itemQualifier != null) {
				map.put(primaryKey, itemQualifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemQualifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemQualifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMQUALIFIER_WHERE_PKS_IN);

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

			for (ItemQualifier itemQualifier : (List<ItemQualifier>)q.list()) {
				map.put(itemQualifier.getPrimaryKeyObj(), itemQualifier);

				cacheResult(itemQualifier);

				uncachedPrimaryKeys.remove(itemQualifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemQualifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemQualifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the item qualifiers.
	 *
	 * @return the item qualifiers
	 */
	@Override
	public List<ItemQualifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item qualifiers
	 * @param end the upper bound of the range of item qualifiers (not inclusive)
	 * @return the range of item qualifiers
	 */
	@Override
	public List<ItemQualifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item qualifiers
	 * @param end the upper bound of the range of item qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item qualifiers
	 */
	@Override
	public List<ItemQualifier> findAll(int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item qualifiers
	 * @param end the upper bound of the range of item qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item qualifiers
	 */
	@Override
	public List<ItemQualifier> findAll(int start, int end,
		OrderByComparator<ItemQualifier> orderByComparator,
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

		List<ItemQualifier> list = null;

		if (retrieveFromCache) {
			list = (List<ItemQualifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMQUALIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMQUALIFIER;

				if (pagination) {
					sql = sql.concat(ItemQualifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemQualifier>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemQualifier>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the item qualifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemQualifier itemQualifier : findAll()) {
			remove(itemQualifier);
		}
	}

	/**
	 * Returns the number of item qualifiers.
	 *
	 * @return the number of item qualifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMQUALIFIER);

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
		return ItemQualifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item qualifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemQualifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMQUALIFIER = "SELECT itemQualifier FROM ItemQualifier itemQualifier";
	private static final String _SQL_SELECT_ITEMQUALIFIER_WHERE_PKS_IN = "SELECT itemQualifier FROM ItemQualifier itemQualifier WHERE ITEM_QUALIFIER_SID IN (";
	private static final String _SQL_SELECT_ITEMQUALIFIER_WHERE = "SELECT itemQualifier FROM ItemQualifier itemQualifier WHERE ";
	private static final String _SQL_COUNT_ITEMQUALIFIER = "SELECT COUNT(itemQualifier) FROM ItemQualifier itemQualifier";
	private static final String _SQL_COUNT_ITEMQUALIFIER_WHERE = "SELECT COUNT(itemQualifier) FROM ItemQualifier itemQualifier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemQualifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemQualifier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemQualifier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemQualifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "itemQualifierSid", "specificEntityCode",
				"itemQualifierName", "modifiedBy", "createdDate", "batchId",
				"modifiedDate", "effectiveDates", "notes", "itemQualifierValue",
				"recordLockStatus", "source", "inboundStatus"
			});
}