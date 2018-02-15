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

import com.stpl.app.exception.NoSuchItemHierarchyMasterException;
import com.stpl.app.model.ItemHierarchyMaster;
import com.stpl.app.model.impl.ItemHierarchyMasterImpl;
import com.stpl.app.model.impl.ItemHierarchyMasterModelImpl;
import com.stpl.app.service.persistence.ItemHierarchyMasterPersistence;

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
 * The persistence implementation for the item hierarchy master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyMasterPersistence
 * @see com.stpl.app.service.persistence.ItemHierarchyMasterUtil
 * @generated
 */
@ProviderType
public class ItemHierarchyMasterPersistenceImpl extends BasePersistenceImpl<ItemHierarchyMaster>
	implements ItemHierarchyMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemHierarchyMasterUtil} to access the item hierarchy master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemHierarchyMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0 = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLevel0",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0 =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLevel0",
			new String[] { String.class.getName() },
			ItemHierarchyMasterModelImpl.LEVEL0_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LEVEL0 = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLevel0",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy masters where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @return the matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0(String level0) {
		return findByLevel0(level0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy masters where level0 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0 the level0
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @return the range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0(String level0, int start,
		int end) {
		return findByLevel0(level0, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0 the level0
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0(String level0, int start,
		int end, OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return findByLevel0(level0, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0 the level0
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0(String level0, int start,
		int end, OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0;
			finderArgs = new Object[] { level0 };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0;
			finderArgs = new Object[] { level0, start, end, orderByComparator };
		}

		List<ItemHierarchyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyMaster itemHierarchyMaster : list) {
					if (!Objects.equals(level0, itemHierarchyMaster.getLevel0())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0 = false;

			if (level0 == null) {
				query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_1);
			}
			else if (level0.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_3);
			}
			else {
				bindLevel0 = true;

				query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0) {
					qPos.add(level0);
				}

				if (!pagination) {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByLevel0_First(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0_First(level0,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0=");
		msg.append(level0);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByLevel0_First(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		List<ItemHierarchyMaster> list = findByLevel0(level0, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByLevel0_Last(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0_Last(level0,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0=");
		msg.append(level0);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByLevel0_Last(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		int count = countByLevel0(level0);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyMaster> list = findByLevel0(level0, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster[] findByLevel0_PrevAndNext(
		int itemHierarchyMasterSid, String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

			array[0] = getByLevel0_PrevAndNext(session, itemHierarchyMaster,
					level0, orderByComparator, true);

			array[1] = itemHierarchyMaster;

			array[2] = getByLevel0_PrevAndNext(session, itemHierarchyMaster,
					level0, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyMaster getByLevel0_PrevAndNext(Session session,
		ItemHierarchyMaster itemHierarchyMaster, String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

		boolean bindLevel0 = false;

		if (level0 == null) {
			query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_1);
		}
		else if (level0.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_3);
		}
		else {
			bindLevel0 = true;

			query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_2);
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
			query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLevel0) {
			qPos.add(level0);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy masters where level0 = &#63; from the database.
	 *
	 * @param level0 the level0
	 */
	@Override
	public void removeByLevel0(String level0) {
		for (ItemHierarchyMaster itemHierarchyMaster : findByLevel0(level0,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy masters where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @return the number of matching item hierarchy masters
	 */
	@Override
	public int countByLevel0(String level0) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LEVEL0;

		Object[] finderArgs = new Object[] { level0 };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0 = false;

			if (level0 == null) {
				query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_1);
			}
			else if (level0.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_3);
			}
			else {
				bindLevel0 = true;

				query.append(_FINDER_COLUMN_LEVEL0_LEVEL0_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0) {
					qPos.add(level0);
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

	private static final String _FINDER_COLUMN_LEVEL0_LEVEL0_1 = "itemHierarchyMaster.level0 IS NULL";
	private static final String _FINDER_COLUMN_LEVEL0_LEVEL0_2 = "itemHierarchyMaster.level0 = ?";
	private static final String _FINDER_COLUMN_LEVEL0_LEVEL0_3 = "(itemHierarchyMaster.level0 IS NULL OR itemHierarchyMaster.level0 = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0ALIAS =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLevel0Alias",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLevel0Alias",
			new String[] { String.class.getName() },
			ItemHierarchyMasterModelImpl.LEVEL0ALIAS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LEVEL0ALIAS = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLevel0Alias",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy masters where level0Alias = &#63;.
	 *
	 * @param level0Alias the level0 alias
	 * @return the matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias) {
		return findByLevel0Alias(level0Alias, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy masters where level0Alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0Alias the level0 alias
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @return the range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias,
		int start, int end) {
		return findByLevel0Alias(level0Alias, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0Alias the level0 alias
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias,
		int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return findByLevel0Alias(level0Alias, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0Alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0Alias the level0 alias
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Alias(String level0Alias,
		int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS;
			finderArgs = new Object[] { level0Alias };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0ALIAS;
			finderArgs = new Object[] { level0Alias, start, end, orderByComparator };
		}

		List<ItemHierarchyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyMaster itemHierarchyMaster : list) {
					if (!Objects.equals(level0Alias,
								itemHierarchyMaster.getLevel0Alias())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0Alias = false;

			if (level0Alias == null) {
				query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1);
			}
			else if (level0Alias.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3);
			}
			else {
				bindLevel0Alias = true;

				query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0Alias) {
					qPos.add(level0Alias);
				}

				if (!pagination) {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
	 *
	 * @param level0Alias the level0 alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByLevel0Alias_First(String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Alias_First(level0Alias,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0Alias=");
		msg.append(level0Alias);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy master in the ordered set where level0Alias = &#63;.
	 *
	 * @param level0Alias the level0 alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByLevel0Alias_First(String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		List<ItemHierarchyMaster> list = findByLevel0Alias(level0Alias, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
	 *
	 * @param level0Alias the level0 alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByLevel0Alias_Last(String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Alias_Last(level0Alias,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0Alias=");
		msg.append(level0Alias);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0Alias = &#63;.
	 *
	 * @param level0Alias the level0 alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByLevel0Alias_Last(String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		int count = countByLevel0Alias(level0Alias);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyMaster> list = findByLevel0Alias(level0Alias,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Alias = &#63;.
	 *
	 * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	 * @param level0Alias the level0 alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster[] findByLevel0Alias_PrevAndNext(
		int itemHierarchyMasterSid, String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

			array[0] = getByLevel0Alias_PrevAndNext(session,
					itemHierarchyMaster, level0Alias, orderByComparator, true);

			array[1] = itemHierarchyMaster;

			array[2] = getByLevel0Alias_PrevAndNext(session,
					itemHierarchyMaster, level0Alias, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyMaster getByLevel0Alias_PrevAndNext(
		Session session, ItemHierarchyMaster itemHierarchyMaster,
		String level0Alias,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

		boolean bindLevel0Alias = false;

		if (level0Alias == null) {
			query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1);
		}
		else if (level0Alias.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3);
		}
		else {
			bindLevel0Alias = true;

			query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2);
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
			query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLevel0Alias) {
			qPos.add(level0Alias);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy masters where level0Alias = &#63; from the database.
	 *
	 * @param level0Alias the level0 alias
	 */
	@Override
	public void removeByLevel0Alias(String level0Alias) {
		for (ItemHierarchyMaster itemHierarchyMaster : findByLevel0Alias(
				level0Alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy masters where level0Alias = &#63;.
	 *
	 * @param level0Alias the level0 alias
	 * @return the number of matching item hierarchy masters
	 */
	@Override
	public int countByLevel0Alias(String level0Alias) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LEVEL0ALIAS;

		Object[] finderArgs = new Object[] { level0Alias };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0Alias = false;

			if (level0Alias == null) {
				query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1);
			}
			else if (level0Alias.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3);
			}
			else {
				bindLevel0Alias = true;

				query.append(_FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0Alias) {
					qPos.add(level0Alias);
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

	private static final String _FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_1 = "itemHierarchyMaster.level0Alias IS NULL";
	private static final String _FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_2 = "itemHierarchyMaster.level0Alias = ?";
	private static final String _FINDER_COLUMN_LEVEL0ALIAS_LEVEL0ALIAS_3 = "(itemHierarchyMaster.level0Alias IS NULL OR itemHierarchyMaster.level0Alias = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0TAG =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLevel0Tag",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLevel0Tag",
			new String[] { String.class.getName() },
			ItemHierarchyMasterModelImpl.LEVEL0TAG_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LEVEL0TAG = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLevel0Tag",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy masters where level0Tag = &#63;.
	 *
	 * @param level0Tag the level0 tag
	 * @return the matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag) {
		return findByLevel0Tag(level0Tag, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the item hierarchy masters where level0Tag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0Tag the level0 tag
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @return the range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag,
		int start, int end) {
		return findByLevel0Tag(level0Tag, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0Tag the level0 tag
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag,
		int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return findByLevel0Tag(level0Tag, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0Tag = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0Tag the level0 tag
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByLevel0Tag(String level0Tag,
		int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG;
			finderArgs = new Object[] { level0Tag };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LEVEL0TAG;
			finderArgs = new Object[] { level0Tag, start, end, orderByComparator };
		}

		List<ItemHierarchyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyMaster itemHierarchyMaster : list) {
					if (!Objects.equals(level0Tag,
								itemHierarchyMaster.getLevel0Tag())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0Tag = false;

			if (level0Tag == null) {
				query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1);
			}
			else if (level0Tag.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3);
			}
			else {
				bindLevel0Tag = true;

				query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0Tag) {
					qPos.add(level0Tag);
				}

				if (!pagination) {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
	 *
	 * @param level0Tag the level0 tag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByLevel0Tag_First(String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Tag_First(level0Tag,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0Tag=");
		msg.append(level0Tag);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy master in the ordered set where level0Tag = &#63;.
	 *
	 * @param level0Tag the level0 tag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByLevel0Tag_First(String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		List<ItemHierarchyMaster> list = findByLevel0Tag(level0Tag, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
	 *
	 * @param level0Tag the level0 tag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByLevel0Tag_Last(String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByLevel0Tag_Last(level0Tag,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0Tag=");
		msg.append(level0Tag);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0Tag = &#63;.
	 *
	 * @param level0Tag the level0 tag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByLevel0Tag_Last(String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		int count = countByLevel0Tag(level0Tag);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyMaster> list = findByLevel0Tag(level0Tag, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0Tag = &#63;.
	 *
	 * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	 * @param level0Tag the level0 tag
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster[] findByLevel0Tag_PrevAndNext(
		int itemHierarchyMasterSid, String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

			array[0] = getByLevel0Tag_PrevAndNext(session, itemHierarchyMaster,
					level0Tag, orderByComparator, true);

			array[1] = itemHierarchyMaster;

			array[2] = getByLevel0Tag_PrevAndNext(session, itemHierarchyMaster,
					level0Tag, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyMaster getByLevel0Tag_PrevAndNext(Session session,
		ItemHierarchyMaster itemHierarchyMaster, String level0Tag,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

		boolean bindLevel0Tag = false;

		if (level0Tag == null) {
			query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1);
		}
		else if (level0Tag.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3);
		}
		else {
			bindLevel0Tag = true;

			query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2);
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
			query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLevel0Tag) {
			qPos.add(level0Tag);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy masters where level0Tag = &#63; from the database.
	 *
	 * @param level0Tag the level0 tag
	 */
	@Override
	public void removeByLevel0Tag(String level0Tag) {
		for (ItemHierarchyMaster itemHierarchyMaster : findByLevel0Tag(
				level0Tag, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy masters where level0Tag = &#63;.
	 *
	 * @param level0Tag the level0 tag
	 * @return the number of matching item hierarchy masters
	 */
	@Override
	public int countByLevel0Tag(String level0Tag) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LEVEL0TAG;

		Object[] finderArgs = new Object[] { level0Tag };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0Tag = false;

			if (level0Tag == null) {
				query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1);
			}
			else if (level0Tag.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3);
			}
			else {
				bindLevel0Tag = true;

				query.append(_FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0Tag) {
					qPos.add(level0Tag);
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

	private static final String _FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_1 = "itemHierarchyMaster.level0Tag IS NULL";
	private static final String _FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_2 = "itemHierarchyMaster.level0Tag = ?";
	private static final String _FINDER_COLUMN_LEVEL0TAG_LEVEL0TAG_3 = "(itemHierarchyMaster.level0Tag IS NULL OR itemHierarchyMaster.level0Tag = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemHierarchyUnique",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE =
		new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemHierarchyUnique",
			new String[] { String.class.getName() },
			ItemHierarchyMasterModelImpl.LEVEL0_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE = new FinderPath(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemHierarchyUnique",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy masters where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @return the matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0) {
		return findByItemHierarchyUnique(level0, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy masters where level0 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0 the level0
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @return the range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0,
		int start, int end) {
		return findByItemHierarchyUnique(level0, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0 the level0
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0,
		int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return findByItemHierarchyUnique(level0, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters where level0 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param level0 the level0
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findByItemHierarchyUnique(String level0,
		int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE;
			finderArgs = new Object[] { level0 };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE;
			finderArgs = new Object[] { level0, start, end, orderByComparator };
		}

		List<ItemHierarchyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyMaster itemHierarchyMaster : list) {
					if (!Objects.equals(level0, itemHierarchyMaster.getLevel0())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0 = false;

			if (level0 == null) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1);
			}
			else if (level0.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3);
			}
			else {
				bindLevel0 = true;

				query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0) {
					qPos.add(level0);
				}

				if (!pagination) {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByItemHierarchyUnique_First(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByItemHierarchyUnique_First(level0,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0=");
		msg.append(level0);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByItemHierarchyUnique_First(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		List<ItemHierarchyMaster> list = findByItemHierarchyUnique(level0, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster findByItemHierarchyUnique_Last(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByItemHierarchyUnique_Last(level0,
				orderByComparator);

		if (itemHierarchyMaster != null) {
			return itemHierarchyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("level0=");
		msg.append(level0);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy master, or <code>null</code> if a matching item hierarchy master could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByItemHierarchyUnique_Last(String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		int count = countByItemHierarchyUnique(level0);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyMaster> list = findByItemHierarchyUnique(level0,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy masters before and after the current item hierarchy master in the ordered set where level0 = &#63;.
	 *
	 * @param itemHierarchyMasterSid the primary key of the current item hierarchy master
	 * @param level0 the level0
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster[] findByItemHierarchyUnique_PrevAndNext(
		int itemHierarchyMasterSid, String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = findByPrimaryKey(itemHierarchyMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyMaster[] array = new ItemHierarchyMasterImpl[3];

			array[0] = getByItemHierarchyUnique_PrevAndNext(session,
					itemHierarchyMaster, level0, orderByComparator, true);

			array[1] = itemHierarchyMaster;

			array[2] = getByItemHierarchyUnique_PrevAndNext(session,
					itemHierarchyMaster, level0, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyMaster getByItemHierarchyUnique_PrevAndNext(
		Session session, ItemHierarchyMaster itemHierarchyMaster,
		String level0,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE);

		boolean bindLevel0 = false;

		if (level0 == null) {
			query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1);
		}
		else if (level0.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3);
		}
		else {
			bindLevel0 = true;

			query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2);
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
			query.append(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLevel0) {
			qPos.add(level0);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy masters where level0 = &#63; from the database.
	 *
	 * @param level0 the level0
	 */
	@Override
	public void removeByItemHierarchyUnique(String level0) {
		for (ItemHierarchyMaster itemHierarchyMaster : findByItemHierarchyUnique(
				level0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy masters where level0 = &#63;.
	 *
	 * @param level0 the level0
	 * @return the number of matching item hierarchy masters
	 */
	@Override
	public int countByItemHierarchyUnique(String level0) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE;

		Object[] finderArgs = new Object[] { level0 };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYMASTER_WHERE);

			boolean bindLevel0 = false;

			if (level0 == null) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1);
			}
			else if (level0.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3);
			}
			else {
				bindLevel0 = true;

				query.append(_FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLevel0) {
					qPos.add(level0);
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

	private static final String _FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_1 = "itemHierarchyMaster.level0 IS NULL";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_2 = "itemHierarchyMaster.level0 = ?";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYUNIQUE_LEVEL0_3 = "(itemHierarchyMaster.level0 IS NULL OR itemHierarchyMaster.level0 = '')";

	public ItemHierarchyMasterPersistenceImpl() {
		setModelClass(ItemHierarchyMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("level1Alias", "LEVEL1_ALIAS");
			dbColumnNames.put("level11Alias", "LEVEL11_ALIAS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("level8Alias", "LEVEL8_ALIAS");
			dbColumnNames.put("level14Alias", "LEVEL14_ALIAS");
			dbColumnNames.put("level5Alias", "LEVEL5_ALIAS");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("level10Alias", "LEVEL10_ALIAS");
			dbColumnNames.put("itemHierarchyMasterSid",
				"ITEM_HIERARCHY_MASTER_SID");
			dbColumnNames.put("level17Alias", "LEVEL17_ALIAS");
			dbColumnNames.put("level9Alias", "LEVEL9_ALIAS");
			dbColumnNames.put("level0Alias", "LEVEL0_ALIAS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("level13Alias", "LEVEL13_ALIAS");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("level6Alias", "LEVEL6_ALIAS");
			dbColumnNames.put("gtnBrand", "GTN_BRAND");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("level3Alias", "LEVEL3_ALIAS");
			dbColumnNames.put("level16Alias", "LEVEL16_ALIAS");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("level19Alias", "LEVEL19_ALIAS");
			dbColumnNames.put("level20", "LEVEL20");
			dbColumnNames.put("level2Alias", "LEVEL2_ALIAS");
			dbColumnNames.put("level20Alias", "LEVEL20_ALIAS");
			dbColumnNames.put("gtnTherapeuticClass", "GTN_THERAPEUTIC_CLASS");
			dbColumnNames.put("level7Alias", "LEVEL7_ALIAS");
			dbColumnNames.put("level0", "LEVEL0");
			dbColumnNames.put("level1", "LEVEL1");
			dbColumnNames.put("level2", "LEVEL2");
			dbColumnNames.put("level3", "LEVEL3");
			dbColumnNames.put("level12Alias", "LEVEL12_ALIAS");
			dbColumnNames.put("level8", "LEVEL8");
			dbColumnNames.put("level11", "LEVEL11");
			dbColumnNames.put("level4Alias", "LEVEL4_ALIAS");
			dbColumnNames.put("level9", "LEVEL9");
			dbColumnNames.put("level12", "LEVEL12");
			dbColumnNames.put("level13", "LEVEL13");
			dbColumnNames.put("level14", "LEVEL14");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("level0Tag", "LEVEL0_TAG");
			dbColumnNames.put("level4", "LEVEL4");
			dbColumnNames.put("level5", "LEVEL5");
			dbColumnNames.put("level6", "LEVEL6");
			dbColumnNames.put("level15Alias", "LEVEL15_ALIAS");
			dbColumnNames.put("level7", "LEVEL7");
			dbColumnNames.put("level10", "LEVEL10");
			dbColumnNames.put("level19", "LEVEL19");
			dbColumnNames.put("level15", "LEVEL15");
			dbColumnNames.put("level16", "LEVEL16");
			dbColumnNames.put("gtnCompany", "GTN_COMPANY");
			dbColumnNames.put("level17", "LEVEL17");
			dbColumnNames.put("level18Alias", "LEVEL18_ALIAS");
			dbColumnNames.put("level18", "LEVEL18");
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
	 * Caches the item hierarchy master in the entity cache if it is enabled.
	 *
	 * @param itemHierarchyMaster the item hierarchy master
	 */
	@Override
	public void cacheResult(ItemHierarchyMaster itemHierarchyMaster) {
		entityCache.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class, itemHierarchyMaster.getPrimaryKey(),
			itemHierarchyMaster);

		itemHierarchyMaster.resetOriginalValues();
	}

	/**
	 * Caches the item hierarchy masters in the entity cache if it is enabled.
	 *
	 * @param itemHierarchyMasters the item hierarchy masters
	 */
	@Override
	public void cacheResult(List<ItemHierarchyMaster> itemHierarchyMasters) {
		for (ItemHierarchyMaster itemHierarchyMaster : itemHierarchyMasters) {
			if (entityCache.getResult(
						ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
						ItemHierarchyMasterImpl.class,
						itemHierarchyMaster.getPrimaryKey()) == null) {
				cacheResult(itemHierarchyMaster);
			}
			else {
				itemHierarchyMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item hierarchy masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemHierarchyMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item hierarchy master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemHierarchyMaster itemHierarchyMaster) {
		entityCache.removeResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class, itemHierarchyMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ItemHierarchyMaster> itemHierarchyMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemHierarchyMaster itemHierarchyMaster : itemHierarchyMasters) {
			entityCache.removeResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
				ItemHierarchyMasterImpl.class,
				itemHierarchyMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
	 *
	 * @param itemHierarchyMasterSid the primary key for the new item hierarchy master
	 * @return the new item hierarchy master
	 */
	@Override
	public ItemHierarchyMaster create(int itemHierarchyMasterSid) {
		ItemHierarchyMaster itemHierarchyMaster = new ItemHierarchyMasterImpl();

		itemHierarchyMaster.setNew(true);
		itemHierarchyMaster.setPrimaryKey(itemHierarchyMasterSid);

		return itemHierarchyMaster;
	}

	/**
	 * Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemHierarchyMasterSid the primary key of the item hierarchy master
	 * @return the item hierarchy master that was removed
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster remove(int itemHierarchyMasterSid)
		throws NoSuchItemHierarchyMasterException {
		return remove((Serializable)itemHierarchyMasterSid);
	}

	/**
	 * Removes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item hierarchy master
	 * @return the item hierarchy master that was removed
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster remove(Serializable primaryKey)
		throws NoSuchItemHierarchyMasterException {
		Session session = null;

		try {
			session = openSession();

			ItemHierarchyMaster itemHierarchyMaster = (ItemHierarchyMaster)session.get(ItemHierarchyMasterImpl.class,
					primaryKey);

			if (itemHierarchyMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemHierarchyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemHierarchyMaster);
		}
		catch (NoSuchItemHierarchyMasterException nsee) {
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
	protected ItemHierarchyMaster removeImpl(
		ItemHierarchyMaster itemHierarchyMaster) {
		itemHierarchyMaster = toUnwrappedModel(itemHierarchyMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemHierarchyMaster)) {
				itemHierarchyMaster = (ItemHierarchyMaster)session.get(ItemHierarchyMasterImpl.class,
						itemHierarchyMaster.getPrimaryKeyObj());
			}

			if (itemHierarchyMaster != null) {
				session.delete(itemHierarchyMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemHierarchyMaster != null) {
			clearCache(itemHierarchyMaster);
		}

		return itemHierarchyMaster;
	}

	@Override
	public ItemHierarchyMaster updateImpl(
		ItemHierarchyMaster itemHierarchyMaster) {
		itemHierarchyMaster = toUnwrappedModel(itemHierarchyMaster);

		boolean isNew = itemHierarchyMaster.isNew();

		ItemHierarchyMasterModelImpl itemHierarchyMasterModelImpl = (ItemHierarchyMasterModelImpl)itemHierarchyMaster;

		Session session = null;

		try {
			session = openSession();

			if (itemHierarchyMaster.isNew()) {
				session.save(itemHierarchyMaster);

				itemHierarchyMaster.setNew(false);
			}
			else {
				itemHierarchyMaster = (ItemHierarchyMaster)session.merge(itemHierarchyMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemHierarchyMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					itemHierarchyMasterModelImpl.getLevel0()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0,
				args);

			args = new Object[] { itemHierarchyMasterModelImpl.getLevel0Alias() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0ALIAS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS,
				args);

			args = new Object[] { itemHierarchyMasterModelImpl.getLevel0Tag() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0TAG, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG,
				args);

			args = new Object[] { itemHierarchyMasterModelImpl.getLevel0() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyMasterModelImpl.getOriginalLevel0()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0,
					args);

				args = new Object[] { itemHierarchyMasterModelImpl.getLevel0() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0,
					args);
			}

			if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyMasterModelImpl.getOriginalLevel0Alias()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0ALIAS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS,
					args);

				args = new Object[] {
						itemHierarchyMasterModelImpl.getLevel0Alias()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0ALIAS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0ALIAS,
					args);
			}

			if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyMasterModelImpl.getOriginalLevel0Tag()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0TAG, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG,
					args);

				args = new Object[] { itemHierarchyMasterModelImpl.getLevel0Tag() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LEVEL0TAG, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LEVEL0TAG,
					args);
			}

			if ((itemHierarchyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyMasterModelImpl.getOriginalLevel0()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE,
					args);

				args = new Object[] { itemHierarchyMasterModelImpl.getLevel0() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYUNIQUE,
					args);
			}
		}

		entityCache.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyMasterImpl.class, itemHierarchyMaster.getPrimaryKey(),
			itemHierarchyMaster, false);

		itemHierarchyMaster.resetOriginalValues();

		return itemHierarchyMaster;
	}

	protected ItemHierarchyMaster toUnwrappedModel(
		ItemHierarchyMaster itemHierarchyMaster) {
		if (itemHierarchyMaster instanceof ItemHierarchyMasterImpl) {
			return itemHierarchyMaster;
		}

		ItemHierarchyMasterImpl itemHierarchyMasterImpl = new ItemHierarchyMasterImpl();

		itemHierarchyMasterImpl.setNew(itemHierarchyMaster.isNew());
		itemHierarchyMasterImpl.setPrimaryKey(itemHierarchyMaster.getPrimaryKey());

		itemHierarchyMasterImpl.setLevel1Alias(itemHierarchyMaster.getLevel1Alias());
		itemHierarchyMasterImpl.setLevel11Alias(itemHierarchyMaster.getLevel11Alias());
		itemHierarchyMasterImpl.setCreatedDate(itemHierarchyMaster.getCreatedDate());
		itemHierarchyMasterImpl.setLevel8Alias(itemHierarchyMaster.getLevel8Alias());
		itemHierarchyMasterImpl.setLevel14Alias(itemHierarchyMaster.getLevel14Alias());
		itemHierarchyMasterImpl.setLevel5Alias(itemHierarchyMaster.getLevel5Alias());
		itemHierarchyMasterImpl.setCreatedBy(itemHierarchyMaster.getCreatedBy());
		itemHierarchyMasterImpl.setLevel10Alias(itemHierarchyMaster.getLevel10Alias());
		itemHierarchyMasterImpl.setItemHierarchyMasterSid(itemHierarchyMaster.getItemHierarchyMasterSid());
		itemHierarchyMasterImpl.setLevel17Alias(itemHierarchyMaster.getLevel17Alias());
		itemHierarchyMasterImpl.setLevel9Alias(itemHierarchyMaster.getLevel9Alias());
		itemHierarchyMasterImpl.setLevel0Alias(itemHierarchyMaster.getLevel0Alias());
		itemHierarchyMasterImpl.setModifiedDate(itemHierarchyMaster.getModifiedDate());
		itemHierarchyMasterImpl.setLevel13Alias(itemHierarchyMaster.getLevel13Alias());
		itemHierarchyMasterImpl.setSource(itemHierarchyMaster.getSource());
		itemHierarchyMasterImpl.setLevel6Alias(itemHierarchyMaster.getLevel6Alias());
		itemHierarchyMasterImpl.setGtnBrand(itemHierarchyMaster.getGtnBrand());
		itemHierarchyMasterImpl.setModifiedBy(itemHierarchyMaster.getModifiedBy());
		itemHierarchyMasterImpl.setLevel3Alias(itemHierarchyMaster.getLevel3Alias());
		itemHierarchyMasterImpl.setLevel16Alias(itemHierarchyMaster.getLevel16Alias());
		itemHierarchyMasterImpl.setBatchId(itemHierarchyMaster.getBatchId());
		itemHierarchyMasterImpl.setLevel19Alias(itemHierarchyMaster.getLevel19Alias());
		itemHierarchyMasterImpl.setLevel20(itemHierarchyMaster.getLevel20());
		itemHierarchyMasterImpl.setLevel2Alias(itemHierarchyMaster.getLevel2Alias());
		itemHierarchyMasterImpl.setLevel20Alias(itemHierarchyMaster.getLevel20Alias());
		itemHierarchyMasterImpl.setGtnTherapeuticClass(itemHierarchyMaster.getGtnTherapeuticClass());
		itemHierarchyMasterImpl.setLevel7Alias(itemHierarchyMaster.getLevel7Alias());
		itemHierarchyMasterImpl.setLevel0(itemHierarchyMaster.getLevel0());
		itemHierarchyMasterImpl.setLevel1(itemHierarchyMaster.getLevel1());
		itemHierarchyMasterImpl.setLevel2(itemHierarchyMaster.getLevel2());
		itemHierarchyMasterImpl.setLevel3(itemHierarchyMaster.getLevel3());
		itemHierarchyMasterImpl.setLevel12Alias(itemHierarchyMaster.getLevel12Alias());
		itemHierarchyMasterImpl.setLevel8(itemHierarchyMaster.getLevel8());
		itemHierarchyMasterImpl.setLevel11(itemHierarchyMaster.getLevel11());
		itemHierarchyMasterImpl.setLevel4Alias(itemHierarchyMaster.getLevel4Alias());
		itemHierarchyMasterImpl.setLevel9(itemHierarchyMaster.getLevel9());
		itemHierarchyMasterImpl.setLevel12(itemHierarchyMaster.getLevel12());
		itemHierarchyMasterImpl.setLevel13(itemHierarchyMaster.getLevel13());
		itemHierarchyMasterImpl.setLevel14(itemHierarchyMaster.getLevel14());
		itemHierarchyMasterImpl.setRecordLockStatus(itemHierarchyMaster.isRecordLockStatus());
		itemHierarchyMasterImpl.setLevel0Tag(itemHierarchyMaster.getLevel0Tag());
		itemHierarchyMasterImpl.setLevel4(itemHierarchyMaster.getLevel4());
		itemHierarchyMasterImpl.setLevel5(itemHierarchyMaster.getLevel5());
		itemHierarchyMasterImpl.setLevel6(itemHierarchyMaster.getLevel6());
		itemHierarchyMasterImpl.setLevel15Alias(itemHierarchyMaster.getLevel15Alias());
		itemHierarchyMasterImpl.setLevel7(itemHierarchyMaster.getLevel7());
		itemHierarchyMasterImpl.setLevel10(itemHierarchyMaster.getLevel10());
		itemHierarchyMasterImpl.setLevel19(itemHierarchyMaster.getLevel19());
		itemHierarchyMasterImpl.setLevel15(itemHierarchyMaster.getLevel15());
		itemHierarchyMasterImpl.setLevel16(itemHierarchyMaster.getLevel16());
		itemHierarchyMasterImpl.setGtnCompany(itemHierarchyMaster.getGtnCompany());
		itemHierarchyMasterImpl.setLevel17(itemHierarchyMaster.getLevel17());
		itemHierarchyMasterImpl.setLevel18Alias(itemHierarchyMaster.getLevel18Alias());
		itemHierarchyMasterImpl.setLevel18(itemHierarchyMaster.getLevel18());
		itemHierarchyMasterImpl.setInboundStatus(itemHierarchyMaster.getInboundStatus());

		return itemHierarchyMasterImpl;
	}

	/**
	 * Returns the item hierarchy master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item hierarchy master
	 * @return the item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemHierarchyMasterException {
		ItemHierarchyMaster itemHierarchyMaster = fetchByPrimaryKey(primaryKey);

		if (itemHierarchyMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemHierarchyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemHierarchyMaster;
	}

	/**
	 * Returns the item hierarchy master with the primary key or throws a {@link NoSuchItemHierarchyMasterException} if it could not be found.
	 *
	 * @param itemHierarchyMasterSid the primary key of the item hierarchy master
	 * @return the item hierarchy master
	 * @throws NoSuchItemHierarchyMasterException if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster findByPrimaryKey(int itemHierarchyMasterSid)
		throws NoSuchItemHierarchyMasterException {
		return findByPrimaryKey((Serializable)itemHierarchyMasterSid);
	}

	/**
	 * Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item hierarchy master
	 * @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
				ItemHierarchyMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemHierarchyMaster itemHierarchyMaster = (ItemHierarchyMaster)serializable;

		if (itemHierarchyMaster == null) {
			Session session = null;

			try {
				session = openSession();

				itemHierarchyMaster = (ItemHierarchyMaster)session.get(ItemHierarchyMasterImpl.class,
						primaryKey);

				if (itemHierarchyMaster != null) {
					cacheResult(itemHierarchyMaster);
				}
				else {
					entityCache.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
						ItemHierarchyMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemHierarchyMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemHierarchyMaster;
	}

	/**
	 * Returns the item hierarchy master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemHierarchyMasterSid the primary key of the item hierarchy master
	 * @return the item hierarchy master, or <code>null</code> if a item hierarchy master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyMaster fetchByPrimaryKey(int itemHierarchyMasterSid) {
		return fetchByPrimaryKey((Serializable)itemHierarchyMasterSid);
	}

	@Override
	public Map<Serializable, ItemHierarchyMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemHierarchyMaster> map = new HashMap<Serializable, ItemHierarchyMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemHierarchyMaster itemHierarchyMaster = fetchByPrimaryKey(primaryKey);

			if (itemHierarchyMaster != null) {
				map.put(primaryKey, itemHierarchyMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemHierarchyMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemHierarchyMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMHIERARCHYMASTER_WHERE_PKS_IN);

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

			for (ItemHierarchyMaster itemHierarchyMaster : (List<ItemHierarchyMaster>)q.list()) {
				map.put(itemHierarchyMaster.getPrimaryKeyObj(),
					itemHierarchyMaster);

				cacheResult(itemHierarchyMaster);

				uncachedPrimaryKeys.remove(itemHierarchyMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemHierarchyMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemHierarchyMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the item hierarchy masters.
	 *
	 * @return the item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @return the range of item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item hierarchy masters
	 * @param end the upper bound of the range of item hierarchy masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item hierarchy masters
	 */
	@Override
	public List<ItemHierarchyMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyMaster> orderByComparator,
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

		List<ItemHierarchyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMHIERARCHYMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMHIERARCHYMASTER;

				if (pagination) {
					sql = sql.concat(ItemHierarchyMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyMaster>)QueryUtil.list(q,
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
	 * Removes all the item hierarchy masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemHierarchyMaster itemHierarchyMaster : findAll()) {
			remove(itemHierarchyMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy masters.
	 *
	 * @return the number of item hierarchy masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMHIERARCHYMASTER);

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
		return ItemHierarchyMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item hierarchy master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemHierarchyMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMHIERARCHYMASTER = "SELECT itemHierarchyMaster FROM ItemHierarchyMaster itemHierarchyMaster";
	private static final String _SQL_SELECT_ITEMHIERARCHYMASTER_WHERE_PKS_IN = "SELECT itemHierarchyMaster FROM ItemHierarchyMaster itemHierarchyMaster WHERE ITEM_HIERARCHY_MASTER_SID IN (";
	private static final String _SQL_SELECT_ITEMHIERARCHYMASTER_WHERE = "SELECT itemHierarchyMaster FROM ItemHierarchyMaster itemHierarchyMaster WHERE ";
	private static final String _SQL_COUNT_ITEMHIERARCHYMASTER = "SELECT COUNT(itemHierarchyMaster) FROM ItemHierarchyMaster itemHierarchyMaster";
	private static final String _SQL_COUNT_ITEMHIERARCHYMASTER_WHERE = "SELECT COUNT(itemHierarchyMaster) FROM ItemHierarchyMaster itemHierarchyMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemHierarchyMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemHierarchyMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemHierarchyMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemHierarchyMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"level1Alias", "level11Alias", "createdDate", "level8Alias",
				"level14Alias", "level5Alias", "createdBy", "level10Alias",
				"itemHierarchyMasterSid", "level17Alias", "level9Alias",
				"level0Alias", "modifiedDate", "level13Alias", "source",
				"level6Alias", "gtnBrand", "modifiedBy", "level3Alias",
				"level16Alias", "batchId", "level19Alias", "level20",
				"level2Alias", "level20Alias", "gtnTherapeuticClass",
				"level7Alias", "level0", "level1", "level2", "level3",
				"level12Alias", "level8", "level11", "level4Alias", "level9",
				"level12", "level13", "level14", "recordLockStatus", "level0Tag",
				"level4", "level5", "level6", "level15Alias", "level7",
				"level10", "level19", "level15", "level16", "gtnCompany",
				"level17", "level18Alias", "level18", "inboundStatus"
			});
}