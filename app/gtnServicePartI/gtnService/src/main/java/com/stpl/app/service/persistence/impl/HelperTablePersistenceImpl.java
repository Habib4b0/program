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

import com.stpl.app.exception.NoSuchHelperTableException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.impl.HelperTableImpl;
import com.stpl.app.model.impl.HelperTableModelImpl;
import com.stpl.app.service.persistence.HelperTablePersistence;

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
 * The persistence implementation for the helper table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HelperTablePersistence
 * @see com.stpl.app.service.persistence.HelperTableUtil
 * @generated
 */
@ProviderType
public class HelperTablePersistenceImpl extends BasePersistenceImpl<HelperTable>
	implements HelperTablePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HelperTableUtil} to access the helper table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HelperTableImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_HELPERTABLEDETAILS =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByHelperTableDetails",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByHelperTableDetails",
			new String[] { String.class.getName() },
			HelperTableModelImpl.LISTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS = new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHelperTableDetails", new String[] { String.class.getName() });

	/**
	 * Returns all the helper tables where listName = &#63;.
	 *
	 * @param listName the list name
	 * @return the matching helper tables
	 */
	@Override
	public List<HelperTable> findByHelperTableDetails(String listName) {
		return findByHelperTableDetails(listName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the helper tables where listName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param listName the list name
	 * @param start the lower bound of the range of helper tables
	 * @param end the upper bound of the range of helper tables (not inclusive)
	 * @return the range of matching helper tables
	 */
	@Override
	public List<HelperTable> findByHelperTableDetails(String listName,
		int start, int end) {
		return findByHelperTableDetails(listName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the helper tables where listName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param listName the list name
	 * @param start the lower bound of the range of helper tables
	 * @param end the upper bound of the range of helper tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching helper tables
	 */
	@Override
	public List<HelperTable> findByHelperTableDetails(String listName,
		int start, int end, OrderByComparator<HelperTable> orderByComparator) {
		return findByHelperTableDetails(listName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the helper tables where listName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param listName the list name
	 * @param start the lower bound of the range of helper tables
	 * @param end the upper bound of the range of helper tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching helper tables
	 */
	@Override
	public List<HelperTable> findByHelperTableDetails(String listName,
		int start, int end, OrderByComparator<HelperTable> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS;
			finderArgs = new Object[] { listName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_HELPERTABLEDETAILS;
			finderArgs = new Object[] { listName, start, end, orderByComparator };
		}

		List<HelperTable> list = null;

		if (retrieveFromCache) {
			list = (List<HelperTable>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (HelperTable helperTable : list) {
					if (!Objects.equals(listName, helperTable.getListName())) {
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

			query.append(_SQL_SELECT_HELPERTABLE_WHERE);

			boolean bindListName = false;

			if (listName == null) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1);
			}
			else if (listName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3);
			}
			else {
				bindListName = true;

				query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(HelperTableModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindListName) {
					qPos.add(listName);
				}

				if (!pagination) {
					list = (List<HelperTable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HelperTable>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first helper table in the ordered set where listName = &#63;.
	 *
	 * @param listName the list name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching helper table
	 * @throws NoSuchHelperTableException if a matching helper table could not be found
	 */
	@Override
	public HelperTable findByHelperTableDetails_First(String listName,
		OrderByComparator<HelperTable> orderByComparator)
		throws NoSuchHelperTableException {
		HelperTable helperTable = fetchByHelperTableDetails_First(listName,
				orderByComparator);

		if (helperTable != null) {
			return helperTable;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("listName=");
		msg.append(listName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHelperTableException(msg.toString());
	}

	/**
	 * Returns the first helper table in the ordered set where listName = &#63;.
	 *
	 * @param listName the list name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetails_First(String listName,
		OrderByComparator<HelperTable> orderByComparator) {
		List<HelperTable> list = findByHelperTableDetails(listName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last helper table in the ordered set where listName = &#63;.
	 *
	 * @param listName the list name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching helper table
	 * @throws NoSuchHelperTableException if a matching helper table could not be found
	 */
	@Override
	public HelperTable findByHelperTableDetails_Last(String listName,
		OrderByComparator<HelperTable> orderByComparator)
		throws NoSuchHelperTableException {
		HelperTable helperTable = fetchByHelperTableDetails_Last(listName,
				orderByComparator);

		if (helperTable != null) {
			return helperTable;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("listName=");
		msg.append(listName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHelperTableException(msg.toString());
	}

	/**
	 * Returns the last helper table in the ordered set where listName = &#63;.
	 *
	 * @param listName the list name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetails_Last(String listName,
		OrderByComparator<HelperTable> orderByComparator) {
		int count = countByHelperTableDetails(listName);

		if (count == 0) {
			return null;
		}

		List<HelperTable> list = findByHelperTableDetails(listName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the helper tables before and after the current helper table in the ordered set where listName = &#63;.
	 *
	 * @param helperTableSid the primary key of the current helper table
	 * @param listName the list name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next helper table
	 * @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable[] findByHelperTableDetails_PrevAndNext(
		int helperTableSid, String listName,
		OrderByComparator<HelperTable> orderByComparator)
		throws NoSuchHelperTableException {
		HelperTable helperTable = findByPrimaryKey(helperTableSid);

		Session session = null;

		try {
			session = openSession();

			HelperTable[] array = new HelperTableImpl[3];

			array[0] = getByHelperTableDetails_PrevAndNext(session,
					helperTable, listName, orderByComparator, true);

			array[1] = helperTable;

			array[2] = getByHelperTableDetails_PrevAndNext(session,
					helperTable, listName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected HelperTable getByHelperTableDetails_PrevAndNext(Session session,
		HelperTable helperTable, String listName,
		OrderByComparator<HelperTable> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HELPERTABLE_WHERE);

		boolean bindListName = false;

		if (listName == null) {
			query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1);
		}
		else if (listName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3);
		}
		else {
			bindListName = true;

			query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2);
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
			query.append(HelperTableModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindListName) {
			qPos.add(listName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(helperTable);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<HelperTable> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the helper tables where listName = &#63; from the database.
	 *
	 * @param listName the list name
	 */
	@Override
	public void removeByHelperTableDetails(String listName) {
		for (HelperTable helperTable : findByHelperTableDetails(listName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(helperTable);
		}
	}

	/**
	 * Returns the number of helper tables where listName = &#63;.
	 *
	 * @param listName the list name
	 * @return the number of matching helper tables
	 */
	@Override
	public int countByHelperTableDetails(String listName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS;

		Object[] finderArgs = new Object[] { listName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HELPERTABLE_WHERE);

			boolean bindListName = false;

			if (listName == null) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1);
			}
			else if (listName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3);
			}
			else {
				bindListName = true;

				query.append(_FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindListName) {
					qPos.add(listName);
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

	private static final String _FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_1 = "helperTable.listName IS NULL";
	private static final String _FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_2 = "helperTable.listName = ?";
	private static final String _FINDER_COLUMN_HELPERTABLEDETAILS_LISTNAME_3 = "(helperTable.listName IS NULL OR helperTable.listName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_ENTITY,
			"fetchByHelperTableDetailsByHelperTableSid",
			new String[] { Integer.class.getName() },
			HelperTableModelImpl.HELPERTABLESID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHelperTableDetailsByHelperTableSid",
			new String[] { Integer.class.getName() });

	/**
	 * Returns the helper table where helperTableSid = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the matching helper table
	 * @throws NoSuchHelperTableException if a matching helper table could not be found
	 */
	@Override
	public HelperTable findByHelperTableDetailsByHelperTableSid(
		int helperTableSid) throws NoSuchHelperTableException {
		HelperTable helperTable = fetchByHelperTableDetailsByHelperTableSid(helperTableSid);

		if (helperTable == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("helperTableSid=");
			msg.append(helperTableSid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchHelperTableException(msg.toString());
		}

		return helperTable;
	}

	/**
	 * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetailsByHelperTableSid(
		int helperTableSid) {
		return fetchByHelperTableDetailsByHelperTableSid(helperTableSid, true);
	}

	/**
	 * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param helperTableSid the helper table sid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetailsByHelperTableSid(
		int helperTableSid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { helperTableSid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
					finderArgs, this);
		}

		if (result instanceof HelperTable) {
			HelperTable helperTable = (HelperTable)result;

			if ((helperTableSid != helperTable.getHelperTableSid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HELPERTABLE_WHERE);

			query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYHELPERTABLESID_HELPERTABLESID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(helperTableSid);

				List<HelperTable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"HelperTablePersistenceImpl.fetchByHelperTableDetailsByHelperTableSid(int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					HelperTable helperTable = list.get(0);

					result = helperTable;

					cacheResult(helperTable);

					if ((helperTable.getHelperTableSid() != helperTableSid)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
							finderArgs, helperTable);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
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
			return (HelperTable)result;
		}
	}

	/**
	 * Removes the helper table where helperTableSid = &#63; from the database.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the helper table that was removed
	 */
	@Override
	public HelperTable removeByHelperTableDetailsByHelperTableSid(
		int helperTableSid) throws NoSuchHelperTableException {
		HelperTable helperTable = findByHelperTableDetailsByHelperTableSid(helperTableSid);

		return remove(helperTable);
	}

	/**
	 * Returns the number of helper tables where helperTableSid = &#63;.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the number of matching helper tables
	 */
	@Override
	public int countByHelperTableDetailsByHelperTableSid(int helperTableSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID;

		Object[] finderArgs = new Object[] { helperTableSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HELPERTABLE_WHERE);

			query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYHELPERTABLESID_HELPERTABLESID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(helperTableSid);

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

	private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYHELPERTABLESID_HELPERTABLESID_2 =
		"helperTable.helperTableSid = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByHelperTableDetailsByDesc",
			new String[] { String.class.getName() },
			HelperTableModelImpl.DESCRIPTION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHelperTableDetailsByDesc",
			new String[] { String.class.getName() });

	/**
	 * Returns the helper table where description = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	 *
	 * @param description the description
	 * @return the matching helper table
	 * @throws NoSuchHelperTableException if a matching helper table could not be found
	 */
	@Override
	public HelperTable findByHelperTableDetailsByDesc(String description)
		throws NoSuchHelperTableException {
		HelperTable helperTable = fetchByHelperTableDetailsByDesc(description);

		if (helperTable == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("description=");
			msg.append(description);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchHelperTableException(msg.toString());
		}

		return helperTable;
	}

	/**
	 * Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param description the description
	 * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetailsByDesc(String description) {
		return fetchByHelperTableDetailsByDesc(description, true);
	}

	/**
	 * Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param description the description
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetailsByDesc(String description,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { description };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
					finderArgs, this);
		}

		if (result instanceof HelperTable) {
			HelperTable helperTable = (HelperTable)result;

			if (!Objects.equals(description, helperTable.getDescription())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HELPERTABLE_WHERE);

			boolean bindDescription = false;

			if (description == null) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_1);
			}
			else if (description.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_3);
			}
			else {
				bindDescription = true;

				query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDescription) {
					qPos.add(description);
				}

				List<HelperTable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"HelperTablePersistenceImpl.fetchByHelperTableDetailsByDesc(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					HelperTable helperTable = list.get(0);

					result = helperTable;

					cacheResult(helperTable);

					if ((helperTable.getDescription() == null) ||
							!helperTable.getDescription().equals(description)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
							finderArgs, helperTable);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
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
			return (HelperTable)result;
		}
	}

	/**
	 * Removes the helper table where description = &#63; from the database.
	 *
	 * @param description the description
	 * @return the helper table that was removed
	 */
	@Override
	public HelperTable removeByHelperTableDetailsByDesc(String description)
		throws NoSuchHelperTableException {
		HelperTable helperTable = findByHelperTableDetailsByDesc(description);

		return remove(helperTable);
	}

	/**
	 * Returns the number of helper tables where description = &#63;.
	 *
	 * @param description the description
	 * @return the number of matching helper tables
	 */
	@Override
	public int countByHelperTableDetailsByDesc(String description) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC;

		Object[] finderArgs = new Object[] { description };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HELPERTABLE_WHERE);

			boolean bindDescription = false;

			if (description == null) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_1);
			}
			else if (description.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_3);
			}
			else {
				bindDescription = true;

				query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDescription) {
					qPos.add(description);
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

	private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_1 =
		"helperTable.description IS NULL";
	private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_2 =
		"helperTable.description = ?";
	private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYDESC_DESCRIPTION_3 =
		"(helperTable.description IS NULL OR helperTable.description = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, HelperTableImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByHelperTableDetailsByCode",
			new String[] { Integer.class.getName() },
			HelperTableModelImpl.HELPERTABLESID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE =
		new FinderPath(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByHelperTableDetailsByCode",
			new String[] { Integer.class.getName() });

	/**
	 * Returns the helper table where helperTableSid = &#63; or throws a {@link NoSuchHelperTableException} if it could not be found.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the matching helper table
	 * @throws NoSuchHelperTableException if a matching helper table could not be found
	 */
	@Override
	public HelperTable findByHelperTableDetailsByCode(int helperTableSid)
		throws NoSuchHelperTableException {
		HelperTable helperTable = fetchByHelperTableDetailsByCode(helperTableSid);

		if (helperTable == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("helperTableSid=");
			msg.append(helperTableSid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchHelperTableException(msg.toString());
		}

		return helperTable;
	}

	/**
	 * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetailsByCode(int helperTableSid) {
		return fetchByHelperTableDetailsByCode(helperTableSid, true);
	}

	/**
	 * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param helperTableSid the helper table sid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
	 */
	@Override
	public HelperTable fetchByHelperTableDetailsByCode(int helperTableSid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { helperTableSid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
					finderArgs, this);
		}

		if (result instanceof HelperTable) {
			HelperTable helperTable = (HelperTable)result;

			if ((helperTableSid != helperTable.getHelperTableSid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HELPERTABLE_WHERE);

			query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYCODE_HELPERTABLESID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(helperTableSid);

				List<HelperTable> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"HelperTablePersistenceImpl.fetchByHelperTableDetailsByCode(int, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					HelperTable helperTable = list.get(0);

					result = helperTable;

					cacheResult(helperTable);

					if ((helperTable.getHelperTableSid() != helperTableSid)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
							finderArgs, helperTable);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
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
			return (HelperTable)result;
		}
	}

	/**
	 * Removes the helper table where helperTableSid = &#63; from the database.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the helper table that was removed
	 */
	@Override
	public HelperTable removeByHelperTableDetailsByCode(int helperTableSid)
		throws NoSuchHelperTableException {
		HelperTable helperTable = findByHelperTableDetailsByCode(helperTableSid);

		return remove(helperTable);
	}

	/**
	 * Returns the number of helper tables where helperTableSid = &#63;.
	 *
	 * @param helperTableSid the helper table sid
	 * @return the number of matching helper tables
	 */
	@Override
	public int countByHelperTableDetailsByCode(int helperTableSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE;

		Object[] finderArgs = new Object[] { helperTableSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HELPERTABLE_WHERE);

			query.append(_FINDER_COLUMN_HELPERTABLEDETAILSBYCODE_HELPERTABLESID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(helperTableSid);

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

	private static final String _FINDER_COLUMN_HELPERTABLEDETAILSBYCODE_HELPERTABLESID_2 =
		"helperTable.helperTableSid = ?";

	public HelperTablePersistenceImpl() {
		setModelClass(HelperTable.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("description", "DESCRIPTION");
			dbColumnNames.put("refCount", "REF_COUNT");
			dbColumnNames.put("listName", "LIST_NAME");
			dbColumnNames.put("helperTableSid", "HELPER_TABLE_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("aliasName", "ALIAS_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the helper table in the entity cache if it is enabled.
	 *
	 * @param helperTable the helper table
	 */
	@Override
	public void cacheResult(HelperTable helperTable) {
		entityCache.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableImpl.class, helperTable.getPrimaryKey(), helperTable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
			new Object[] { helperTable.getHelperTableSid() }, helperTable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
			new Object[] { helperTable.getDescription() }, helperTable);

		finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
			new Object[] { helperTable.getHelperTableSid() }, helperTable);

		helperTable.resetOriginalValues();
	}

	/**
	 * Caches the helper tables in the entity cache if it is enabled.
	 *
	 * @param helperTables the helper tables
	 */
	@Override
	public void cacheResult(List<HelperTable> helperTables) {
		for (HelperTable helperTable : helperTables) {
			if (entityCache.getResult(
						HelperTableModelImpl.ENTITY_CACHE_ENABLED,
						HelperTableImpl.class, helperTable.getPrimaryKey()) == null) {
				cacheResult(helperTable);
			}
			else {
				helperTable.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all helper tables.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HelperTableImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the helper table.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HelperTable helperTable) {
		entityCache.removeResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableImpl.class, helperTable.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((HelperTableModelImpl)helperTable, true);
	}

	@Override
	public void clearCache(List<HelperTable> helperTables) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HelperTable helperTable : helperTables) {
			entityCache.removeResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
				HelperTableImpl.class, helperTable.getPrimaryKey());

			clearUniqueFindersCache((HelperTableModelImpl)helperTable, true);
		}
	}

	protected void cacheUniqueFindersCache(
		HelperTableModelImpl helperTableModelImpl) {
		Object[] args = new Object[] { helperTableModelImpl.getHelperTableSid() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
			args, helperTableModelImpl, false);

		args = new Object[] { helperTableModelImpl.getDescription() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
			args, helperTableModelImpl, false);

		args = new Object[] { helperTableModelImpl.getHelperTableSid() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
			args, helperTableModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		HelperTableModelImpl helperTableModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					helperTableModelImpl.getHelperTableSid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
				args);
		}

		if ((helperTableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					helperTableModelImpl.getOriginalHelperTableSid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYHELPERTABLESID,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] { helperTableModelImpl.getDescription() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
				args);
		}

		if ((helperTableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					helperTableModelImpl.getOriginalDescription()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYDESC,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYDESC,
				args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					helperTableModelImpl.getHelperTableSid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
				args);
		}

		if ((helperTableModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					helperTableModelImpl.getOriginalHelperTableSid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILSBYCODE,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_HELPERTABLEDETAILSBYCODE,
				args);
		}
	}

	/**
	 * Creates a new helper table with the primary key. Does not add the helper table to the database.
	 *
	 * @param helperTableSid the primary key for the new helper table
	 * @return the new helper table
	 */
	@Override
	public HelperTable create(int helperTableSid) {
		HelperTable helperTable = new HelperTableImpl();

		helperTable.setNew(true);
		helperTable.setPrimaryKey(helperTableSid);

		return helperTable;
	}

	/**
	 * Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helperTableSid the primary key of the helper table
	 * @return the helper table that was removed
	 * @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable remove(int helperTableSid)
		throws NoSuchHelperTableException {
		return remove((Serializable)helperTableSid);
	}

	/**
	 * Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the helper table
	 * @return the helper table that was removed
	 * @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable remove(Serializable primaryKey)
		throws NoSuchHelperTableException {
		Session session = null;

		try {
			session = openSession();

			HelperTable helperTable = (HelperTable)session.get(HelperTableImpl.class,
					primaryKey);

			if (helperTable == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHelperTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(helperTable);
		}
		catch (NoSuchHelperTableException nsee) {
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
	protected HelperTable removeImpl(HelperTable helperTable) {
		helperTable = toUnwrappedModel(helperTable);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(helperTable)) {
				helperTable = (HelperTable)session.get(HelperTableImpl.class,
						helperTable.getPrimaryKeyObj());
			}

			if (helperTable != null) {
				session.delete(helperTable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (helperTable != null) {
			clearCache(helperTable);
		}

		return helperTable;
	}

	@Override
	public HelperTable updateImpl(HelperTable helperTable) {
		helperTable = toUnwrappedModel(helperTable);

		boolean isNew = helperTable.isNew();

		HelperTableModelImpl helperTableModelImpl = (HelperTableModelImpl)helperTable;

		Session session = null;

		try {
			session = openSession();

			if (helperTable.isNew()) {
				session.save(helperTable);

				helperTable.setNew(false);
			}
			else {
				helperTable = (HelperTable)session.merge(helperTable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!HelperTableModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { helperTableModelImpl.getListName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((helperTableModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						helperTableModelImpl.getOriginalListName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS,
					args);

				args = new Object[] { helperTableModelImpl.getListName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_HELPERTABLEDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_HELPERTABLEDETAILS,
					args);
			}
		}

		entityCache.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
			HelperTableImpl.class, helperTable.getPrimaryKey(), helperTable,
			false);

		clearUniqueFindersCache(helperTableModelImpl, false);
		cacheUniqueFindersCache(helperTableModelImpl);

		helperTable.resetOriginalValues();

		return helperTable;
	}

	protected HelperTable toUnwrappedModel(HelperTable helperTable) {
		if (helperTable instanceof HelperTableImpl) {
			return helperTable;
		}

		HelperTableImpl helperTableImpl = new HelperTableImpl();

		helperTableImpl.setNew(helperTable.isNew());
		helperTableImpl.setPrimaryKey(helperTable.getPrimaryKey());

		helperTableImpl.setCreatedDate(helperTable.getCreatedDate());
		helperTableImpl.setCreatedBy(helperTable.getCreatedBy());
		helperTableImpl.setDescription(helperTable.getDescription());
		helperTableImpl.setRefCount(helperTable.getRefCount());
		helperTableImpl.setListName(helperTable.getListName());
		helperTableImpl.setHelperTableSid(helperTable.getHelperTableSid());
		helperTableImpl.setModifiedBy(helperTable.getModifiedBy());
		helperTableImpl.setModifiedDate(helperTable.getModifiedDate());
		helperTableImpl.setAliasName(helperTable.getAliasName());

		return helperTableImpl;
	}

	/**
	 * Returns the helper table with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the helper table
	 * @return the helper table
	 * @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHelperTableException {
		HelperTable helperTable = fetchByPrimaryKey(primaryKey);

		if (helperTable == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHelperTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return helperTable;
	}

	/**
	 * Returns the helper table with the primary key or throws a {@link NoSuchHelperTableException} if it could not be found.
	 *
	 * @param helperTableSid the primary key of the helper table
	 * @return the helper table
	 * @throws NoSuchHelperTableException if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable findByPrimaryKey(int helperTableSid)
		throws NoSuchHelperTableException {
		return findByPrimaryKey((Serializable)helperTableSid);
	}

	/**
	 * Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the helper table
	 * @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
				HelperTableImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HelperTable helperTable = (HelperTable)serializable;

		if (helperTable == null) {
			Session session = null;

			try {
				session = openSession();

				helperTable = (HelperTable)session.get(HelperTableImpl.class,
						primaryKey);

				if (helperTable != null) {
					cacheResult(helperTable);
				}
				else {
					entityCache.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
						HelperTableImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
					HelperTableImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return helperTable;
	}

	/**
	 * Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helperTableSid the primary key of the helper table
	 * @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
	 */
	@Override
	public HelperTable fetchByPrimaryKey(int helperTableSid) {
		return fetchByPrimaryKey((Serializable)helperTableSid);
	}

	@Override
	public Map<Serializable, HelperTable> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HelperTable> map = new HashMap<Serializable, HelperTable>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HelperTable helperTable = fetchByPrimaryKey(primaryKey);

			if (helperTable != null) {
				map.put(primaryKey, helperTable);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
					HelperTableImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HelperTable)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HELPERTABLE_WHERE_PKS_IN);

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

			for (HelperTable helperTable : (List<HelperTable>)q.list()) {
				map.put(helperTable.getPrimaryKeyObj(), helperTable);

				cacheResult(helperTable);

				uncachedPrimaryKeys.remove(helperTable.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HelperTableModelImpl.ENTITY_CACHE_ENABLED,
					HelperTableImpl.class, primaryKey, nullModel);
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
	 * Returns all the helper tables.
	 *
	 * @return the helper tables
	 */
	@Override
	public List<HelperTable> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the helper tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of helper tables
	 * @param end the upper bound of the range of helper tables (not inclusive)
	 * @return the range of helper tables
	 */
	@Override
	public List<HelperTable> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the helper tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of helper tables
	 * @param end the upper bound of the range of helper tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of helper tables
	 */
	@Override
	public List<HelperTable> findAll(int start, int end,
		OrderByComparator<HelperTable> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the helper tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of helper tables
	 * @param end the upper bound of the range of helper tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of helper tables
	 */
	@Override
	public List<HelperTable> findAll(int start, int end,
		OrderByComparator<HelperTable> orderByComparator,
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

		List<HelperTable> list = null;

		if (retrieveFromCache) {
			list = (List<HelperTable>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HELPERTABLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HELPERTABLE;

				if (pagination) {
					sql = sql.concat(HelperTableModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HelperTable>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HelperTable>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the helper tables from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HelperTable helperTable : findAll()) {
			remove(helperTable);
		}
	}

	/**
	 * Returns the number of helper tables.
	 *
	 * @return the number of helper tables
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HELPERTABLE);

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
		return HelperTableModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the helper table persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HelperTableImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HELPERTABLE = "SELECT helperTable FROM HelperTable helperTable";
	private static final String _SQL_SELECT_HELPERTABLE_WHERE_PKS_IN = "SELECT helperTable FROM HelperTable helperTable WHERE HELPER_TABLE_SID IN (";
	private static final String _SQL_SELECT_HELPERTABLE_WHERE = "SELECT helperTable FROM HelperTable helperTable WHERE ";
	private static final String _SQL_COUNT_HELPERTABLE = "SELECT COUNT(helperTable) FROM HelperTable helperTable";
	private static final String _SQL_COUNT_HELPERTABLE_WHERE = "SELECT COUNT(helperTable) FROM HelperTable helperTable WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "helperTable.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HelperTable exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HelperTable exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(HelperTablePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "description", "refCount",
				"listName", "helperTableSid", "modifiedBy", "modifiedDate",
				"aliasName"
			});
}