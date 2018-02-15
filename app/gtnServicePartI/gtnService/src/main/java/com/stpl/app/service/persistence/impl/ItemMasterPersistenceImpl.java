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

import com.stpl.app.exception.NoSuchItemMasterException;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.impl.ItemMasterImpl;
import com.stpl.app.model.impl.ItemMasterModelImpl;
import com.stpl.app.service.persistence.ItemMasterPersistence;

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
 * The persistence implementation for the item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemMasterPersistence
 * @see com.stpl.app.service.persistence.ItemMasterUtil
 * @generated
 */
@ProviderType
public class ItemMasterPersistenceImpl extends BasePersistenceImpl<ItemMaster>
	implements ItemMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemMasterUtil} to access the item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemNo",
			new String[] { String.class.getName() },
			ItemMasterModelImpl.ITEMNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMNO = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item masters where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemNo(String itemNo) {
		return findByItemNo(itemNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters where itemNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemNo the item no
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemNo(String itemNo, int start, int end) {
		return findByItemNo(itemNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where itemNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemNo the item no
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemNo(String itemNo, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findByItemNo(itemNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where itemNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemNo the item no
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemNo(String itemNo, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO;
			finderArgs = new Object[] { itemNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO;
			finderArgs = new Object[] { itemNo, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if (!Objects.equals(itemNo, itemMaster.getItemNo())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			boolean bindItemNo = false;

			if (itemNo == null) {
				query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_1);
			}
			else if (itemNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_3);
			}
			else {
				bindItemNo = true;

				query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemNo) {
					qPos.add(itemNo);
				}

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemNo_First(String itemNo,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemNo_First(itemNo, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemNo=");
		msg.append(itemNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemNo_First(String itemNo,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByItemNo(itemNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemNo_Last(String itemNo,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemNo_Last(itemNo, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemNo=");
		msg.append(itemNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemNo_Last(String itemNo,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByItemNo(itemNo);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByItemNo(itemNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByItemNo_PrevAndNext(int itemMasterSid,
		String itemNo, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByItemNo_PrevAndNext(session, itemMaster, itemNo,
					orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByItemNo_PrevAndNext(session, itemMaster, itemNo,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByItemNo_PrevAndNext(Session session,
		ItemMaster itemMaster, String itemNo,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		boolean bindItemNo = false;

		if (itemNo == null) {
			query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_1);
		}
		else if (itemNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_3);
		}
		else {
			bindItemNo = true;

			query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_2);
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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindItemNo) {
			qPos.add(itemNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where itemNo = &#63; from the database.
	 *
	 * @param itemNo the item no
	 */
	@Override
	public void removeByItemNo(String itemNo) {
		for (ItemMaster itemMaster : findByItemNo(itemNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @return the number of matching item masters
	 */
	@Override
	public int countByItemNo(String itemNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMNO;

		Object[] finderArgs = new Object[] { itemNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			boolean bindItemNo = false;

			if (itemNo == null) {
				query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_1);
			}
			else if (itemNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_3);
			}
			else {
				bindItemNo = true;

				query.append(_FINDER_COLUMN_ITEMNO_ITEMNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemNo) {
					qPos.add(itemNo);
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

	private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_1 = "itemMaster.itemNo IS NULL";
	private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_2 = "itemMaster.itemNo = ?";
	private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_3 = "(itemMaster.itemNo IS NULL OR itemMaster.itemNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemId",
			new String[] { String.class.getName() },
			ItemMasterModelImpl.ITEMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item masters where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemId(String itemId) {
		return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemId(String itemId, int start, int end) {
		return findByItemId(itemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemId(String itemId, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findByItemId(itemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemId(String itemId, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID;
			finderArgs = new Object[] { itemId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID;
			finderArgs = new Object[] { itemId, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if (!Objects.equals(itemId, itemMaster.getItemId())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			boolean bindItemId = false;

			if (itemId == null) {
				query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
			}
			else if (itemId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
			}
			else {
				bindItemId = true;

				query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemId) {
					qPos.add(itemId);
				}

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemId_First(String itemId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemId_First(itemId, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemId=");
		msg.append(itemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemId_First(String itemId,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByItemId(itemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemId_Last(String itemId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemId_Last(itemId, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemId=");
		msg.append(itemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemId_Last(String itemId,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByItemId(itemId);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByItemId(itemId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where itemId = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByItemId_PrevAndNext(int itemMasterSid,
		String itemId, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByItemId_PrevAndNext(session, itemMaster, itemId,
					orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByItemId_PrevAndNext(session, itemMaster, itemId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByItemId_PrevAndNext(Session session,
		ItemMaster itemMaster, String itemId,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		boolean bindItemId = false;

		if (itemId == null) {
			query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
		}
		else if (itemId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
		}
		else {
			bindItemId = true;

			query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindItemId) {
			qPos.add(itemId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	@Override
	public void removeByItemId(String itemId) {
		for (ItemMaster itemMaster : findByItemId(itemId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching item masters
	 */
	@Override
	public int countByItemId(String itemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMID;

		Object[] finderArgs = new Object[] { itemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			boolean bindItemId = false;

			if (itemId == null) {
				query.append(_FINDER_COLUMN_ITEMID_ITEMID_1);
			}
			else if (itemId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMID_ITEMID_3);
			}
			else {
				bindItemId = true;

				query.append(_FINDER_COLUMN_ITEMID_ITEMID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemId) {
					qPos.add(itemId);
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

	private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "itemMaster.itemId IS NULL";
	private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "itemMaster.itemId = ?";
	private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(itemMaster.itemId IS NULL OR itemMaster.itemId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNAME = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNAME =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemName",
			new String[] { String.class.getName() },
			ItemMasterModelImpl.ITEMNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMNAME = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item masters where itemName = &#63;.
	 *
	 * @param itemName the item name
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemName(String itemName) {
		return findByItemName(itemName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the item masters where itemName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemName the item name
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemName(String itemName, int start, int end) {
		return findByItemName(itemName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where itemName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemName the item name
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemName(String itemName, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findByItemName(itemName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where itemName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemName the item name
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemName(String itemName, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNAME;
			finderArgs = new Object[] { itemName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNAME;
			finderArgs = new Object[] { itemName, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if (!Objects.equals(itemName, itemMaster.getItemName())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			boolean bindItemName = false;

			if (itemName == null) {
				query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_1);
			}
			else if (itemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_3);
			}
			else {
				bindItemName = true;

				query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemName) {
					qPos.add(itemName);
				}

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where itemName = &#63;.
	 *
	 * @param itemName the item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemName_First(String itemName,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemName_First(itemName,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemName=");
		msg.append(itemName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where itemName = &#63;.
	 *
	 * @param itemName the item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemName_First(String itemName,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByItemName(itemName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where itemName = &#63;.
	 *
	 * @param itemName the item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemName_Last(String itemName,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemName_Last(itemName, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemName=");
		msg.append(itemName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where itemName = &#63;.
	 *
	 * @param itemName the item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemName_Last(String itemName,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByItemName(itemName);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByItemName(itemName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where itemName = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param itemName the item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByItemName_PrevAndNext(int itemMasterSid,
		String itemName, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByItemName_PrevAndNext(session, itemMaster, itemName,
					orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByItemName_PrevAndNext(session, itemMaster, itemName,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByItemName_PrevAndNext(Session session,
		ItemMaster itemMaster, String itemName,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		boolean bindItemName = false;

		if (itemName == null) {
			query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_1);
		}
		else if (itemName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_3);
		}
		else {
			bindItemName = true;

			query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_2);
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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindItemName) {
			qPos.add(itemName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where itemName = &#63; from the database.
	 *
	 * @param itemName the item name
	 */
	@Override
	public void removeByItemName(String itemName) {
		for (ItemMaster itemMaster : findByItemName(itemName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where itemName = &#63;.
	 *
	 * @param itemName the item name
	 * @return the number of matching item masters
	 */
	@Override
	public int countByItemName(String itemName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMNAME;

		Object[] finderArgs = new Object[] { itemName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			boolean bindItemName = false;

			if (itemName == null) {
				query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_1);
			}
			else if (itemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_3);
			}
			else {
				bindItemName = true;

				query.append(_FINDER_COLUMN_ITEMNAME_ITEMNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemName) {
					qPos.add(itemName);
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

	private static final String _FINDER_COLUMN_ITEMNAME_ITEMNAME_1 = "itemMaster.itemName IS NULL";
	private static final String _FINDER_COLUMN_ITEMNAME_ITEMNAME_2 = "itemMaster.itemName = ?";
	private static final String _FINDER_COLUMN_ITEMNAME_ITEMNAME_3 = "(itemMaster.itemName IS NULL OR itemMaster.itemName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMTYPE = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMTYPE =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemType",
			new String[] { Integer.class.getName() },
			ItemMasterModelImpl.ITEMTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMTYPE = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item masters where itemType = &#63;.
	 *
	 * @param itemType the item type
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemType(int itemType) {
		return findByItemType(itemType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the item masters where itemType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemType the item type
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemType(int itemType, int start, int end) {
		return findByItemType(itemType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where itemType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemType the item type
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemType(int itemType, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findByItemType(itemType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where itemType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemType the item type
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemType(int itemType, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMTYPE;
			finderArgs = new Object[] { itemType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMTYPE;
			finderArgs = new Object[] { itemType, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if ((itemType != itemMaster.getItemType())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_ITEMTYPE_ITEMTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemType);

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where itemType = &#63;.
	 *
	 * @param itemType the item type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemType_First(int itemType,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemType_First(itemType,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemType=");
		msg.append(itemType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where itemType = &#63;.
	 *
	 * @param itemType the item type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemType_First(int itemType,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByItemType(itemType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where itemType = &#63;.
	 *
	 * @param itemType the item type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemType_Last(int itemType,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemType_Last(itemType, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemType=");
		msg.append(itemType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where itemType = &#63;.
	 *
	 * @param itemType the item type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemType_Last(int itemType,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByItemType(itemType);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByItemType(itemType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where itemType = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param itemType the item type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByItemType_PrevAndNext(int itemMasterSid,
		int itemType, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByItemType_PrevAndNext(session, itemMaster, itemType,
					orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByItemType_PrevAndNext(session, itemMaster, itemType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByItemType_PrevAndNext(Session session,
		ItemMaster itemMaster, int itemType,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		query.append(_FINDER_COLUMN_ITEMTYPE_ITEMTYPE_2);

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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(itemType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where itemType = &#63; from the database.
	 *
	 * @param itemType the item type
	 */
	@Override
	public void removeByItemType(int itemType) {
		for (ItemMaster itemMaster : findByItemType(itemType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where itemType = &#63;.
	 *
	 * @param itemType the item type
	 * @return the number of matching item masters
	 */
	@Override
	public int countByItemType(int itemType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMTYPE;

		Object[] finderArgs = new Object[] { itemType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_ITEMTYPE_ITEMTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemType);

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

	private static final String _FINDER_COLUMN_ITEMTYPE_ITEMTYPE_2 = "itemMaster.itemType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMSTATUS =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMSTATUS =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemStatus",
			new String[] { Integer.class.getName() },
			ItemMasterModelImpl.ITEMSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMSTATUS = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item masters where itemStatus = &#63;.
	 *
	 * @param itemStatus the item status
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemStatus(int itemStatus) {
		return findByItemStatus(itemStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters where itemStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemStatus the item status
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemStatus(int itemStatus, int start, int end) {
		return findByItemStatus(itemStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where itemStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemStatus the item status
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemStatus(int itemStatus, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator) {
		return findByItemStatus(itemStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where itemStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemStatus the item status
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByItemStatus(int itemStatus, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMSTATUS;
			finderArgs = new Object[] { itemStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMSTATUS;
			finderArgs = new Object[] { itemStatus, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if ((itemStatus != itemMaster.getItemStatus())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_ITEMSTATUS_ITEMSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemStatus);

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where itemStatus = &#63;.
	 *
	 * @param itemStatus the item status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemStatus_First(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemStatus_First(itemStatus,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemStatus=");
		msg.append(itemStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where itemStatus = &#63;.
	 *
	 * @param itemStatus the item status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemStatus_First(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByItemStatus(itemStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where itemStatus = &#63;.
	 *
	 * @param itemStatus the item status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByItemStatus_Last(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByItemStatus_Last(itemStatus,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemStatus=");
		msg.append(itemStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where itemStatus = &#63;.
	 *
	 * @param itemStatus the item status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByItemStatus_Last(int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByItemStatus(itemStatus);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByItemStatus(itemStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where itemStatus = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param itemStatus the item status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByItemStatus_PrevAndNext(int itemMasterSid,
		int itemStatus, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByItemStatus_PrevAndNext(session, itemMaster,
					itemStatus, orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByItemStatus_PrevAndNext(session, itemMaster,
					itemStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByItemStatus_PrevAndNext(Session session,
		ItemMaster itemMaster, int itemStatus,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		query.append(_FINDER_COLUMN_ITEMSTATUS_ITEMSTATUS_2);

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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(itemStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where itemStatus = &#63; from the database.
	 *
	 * @param itemStatus the item status
	 */
	@Override
	public void removeByItemStatus(int itemStatus) {
		for (ItemMaster itemMaster : findByItemStatus(itemStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where itemStatus = &#63;.
	 *
	 * @param itemStatus the item status
	 * @return the number of matching item masters
	 */
	@Override
	public int countByItemStatus(int itemStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMSTATUS;

		Object[] finderArgs = new Object[] { itemStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_ITEMSTATUS_ITEMSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemStatus);

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

	private static final String _FINDER_COLUMN_ITEMSTATUS_ITEMSTATUS_2 = "itemMaster.itemStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MANUFACTURERNO =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByManufacturerNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MANUFACTURERNO =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByManufacturerNo",
			new String[] { String.class.getName() },
			ItemMasterModelImpl.MANUFACTURERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MANUFACTURERNO = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByManufacturerNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item masters where manufacturerId = &#63;.
	 *
	 * @param manufacturerId the manufacturer ID
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByManufacturerNo(String manufacturerId) {
		return findByManufacturerNo(manufacturerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters where manufacturerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByManufacturerNo(String manufacturerId,
		int start, int end) {
		return findByManufacturerNo(manufacturerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where manufacturerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByManufacturerNo(String manufacturerId,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator) {
		return findByManufacturerNo(manufacturerId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where manufacturerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByManufacturerNo(String manufacturerId,
		int start, int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MANUFACTURERNO;
			finderArgs = new Object[] { manufacturerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MANUFACTURERNO;
			finderArgs = new Object[] {
					manufacturerId,
					
					start, end, orderByComparator
				};
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if (!Objects.equals(manufacturerId,
								itemMaster.getManufacturerId())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			boolean bindManufacturerId = false;

			if (manufacturerId == null) {
				query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_1);
			}
			else if (manufacturerId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_3);
			}
			else {
				bindManufacturerId = true;

				query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindManufacturerId) {
					qPos.add(manufacturerId);
				}

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where manufacturerId = &#63;.
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByManufacturerNo_First(String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByManufacturerNo_First(manufacturerId,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("manufacturerId=");
		msg.append(manufacturerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where manufacturerId = &#63;.
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByManufacturerNo_First(String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByManufacturerNo(manufacturerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where manufacturerId = &#63;.
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByManufacturerNo_Last(String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByManufacturerNo_Last(manufacturerId,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("manufacturerId=");
		msg.append(manufacturerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where manufacturerId = &#63;.
	 *
	 * @param manufacturerId the manufacturer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByManufacturerNo_Last(String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByManufacturerNo(manufacturerId);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByManufacturerNo(manufacturerId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where manufacturerId = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param manufacturerId the manufacturer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByManufacturerNo_PrevAndNext(int itemMasterSid,
		String manufacturerId, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByManufacturerNo_PrevAndNext(session, itemMaster,
					manufacturerId, orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByManufacturerNo_PrevAndNext(session, itemMaster,
					manufacturerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByManufacturerNo_PrevAndNext(Session session,
		ItemMaster itemMaster, String manufacturerId,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		boolean bindManufacturerId = false;

		if (manufacturerId == null) {
			query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_1);
		}
		else if (manufacturerId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_3);
		}
		else {
			bindManufacturerId = true;

			query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_2);
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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindManufacturerId) {
			qPos.add(manufacturerId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where manufacturerId = &#63; from the database.
	 *
	 * @param manufacturerId the manufacturer ID
	 */
	@Override
	public void removeByManufacturerNo(String manufacturerId) {
		for (ItemMaster itemMaster : findByManufacturerNo(manufacturerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where manufacturerId = &#63;.
	 *
	 * @param manufacturerId the manufacturer ID
	 * @return the number of matching item masters
	 */
	@Override
	public int countByManufacturerNo(String manufacturerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MANUFACTURERNO;

		Object[] finderArgs = new Object[] { manufacturerId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			boolean bindManufacturerId = false;

			if (manufacturerId == null) {
				query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_1);
			}
			else if (manufacturerId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_3);
			}
			else {
				bindManufacturerId = true;

				query.append(_FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindManufacturerId) {
					qPos.add(manufacturerId);
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

	private static final String _FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_1 = "itemMaster.manufacturerId IS NULL";
	private static final String _FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_2 = "itemMaster.manufacturerId = ?";
	private static final String _FINDER_COLUMN_MANUFACTURERNO_MANUFACTURERID_3 = "(itemMaster.manufacturerId IS NULL OR itemMaster.manufacturerId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORM = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByForm",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORM = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByForm",
			new String[] { Integer.class.getName() },
			ItemMasterModelImpl.FORM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FORM = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByForm",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item masters where form = &#63;.
	 *
	 * @param form the form
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByForm(int form) {
		return findByForm(form, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters where form = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param form the form
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByForm(int form, int start, int end) {
		return findByForm(form, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where form = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param form the form
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByForm(int form, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findByForm(form, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where form = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param form the form
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByForm(int form, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORM;
			finderArgs = new Object[] { form };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORM;
			finderArgs = new Object[] { form, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if ((form != itemMaster.getForm())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_FORM_FORM_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(form);

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where form = &#63;.
	 *
	 * @param form the form
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByForm_First(int form,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByForm_First(form, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("form=");
		msg.append(form);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where form = &#63;.
	 *
	 * @param form the form
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByForm_First(int form,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByForm(form, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where form = &#63;.
	 *
	 * @param form the form
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByForm_Last(int form,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByForm_Last(form, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("form=");
		msg.append(form);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where form = &#63;.
	 *
	 * @param form the form
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByForm_Last(int form,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByForm(form);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByForm(form, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where form = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param form the form
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByForm_PrevAndNext(int itemMasterSid, int form,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByForm_PrevAndNext(session, itemMaster, form,
					orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByForm_PrevAndNext(session, itemMaster, form,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByForm_PrevAndNext(Session session,
		ItemMaster itemMaster, int form,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		query.append(_FINDER_COLUMN_FORM_FORM_2);

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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(form);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where form = &#63; from the database.
	 *
	 * @param form the form
	 */
	@Override
	public void removeByForm(int form) {
		for (ItemMaster itemMaster : findByForm(form, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where form = &#63;.
	 *
	 * @param form the form
	 * @return the number of matching item masters
	 */
	@Override
	public int countByForm(int form) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FORM;

		Object[] finderArgs = new Object[] { form };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_FORM_FORM_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(form);

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

	private static final String _FINDER_COLUMN_FORM_FORM_2 = "itemMaster.form = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STRENGTH = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStrength",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRENGTH =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStrength",
			new String[] { Integer.class.getName() },
			ItemMasterModelImpl.STRENGTH_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STRENGTH = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStrength",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item masters where strength = &#63;.
	 *
	 * @param strength the strength
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByStrength(int strength) {
		return findByStrength(strength, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the item masters where strength = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param strength the strength
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByStrength(int strength, int start, int end) {
		return findByStrength(strength, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where strength = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param strength the strength
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByStrength(int strength, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findByStrength(strength, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where strength = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param strength the strength
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByStrength(int strength, int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRENGTH;
			finderArgs = new Object[] { strength };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STRENGTH;
			finderArgs = new Object[] { strength, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if ((strength != itemMaster.getStrength())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_STRENGTH_STRENGTH_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(strength);

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where strength = &#63;.
	 *
	 * @param strength the strength
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByStrength_First(int strength,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByStrength_First(strength,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("strength=");
		msg.append(strength);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where strength = &#63;.
	 *
	 * @param strength the strength
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByStrength_First(int strength,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByStrength(strength, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where strength = &#63;.
	 *
	 * @param strength the strength
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByStrength_Last(int strength,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByStrength_Last(strength, orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("strength=");
		msg.append(strength);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where strength = &#63;.
	 *
	 * @param strength the strength
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByStrength_Last(int strength,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByStrength(strength);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByStrength(strength, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where strength = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param strength the strength
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByStrength_PrevAndNext(int itemMasterSid,
		int strength, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByStrength_PrevAndNext(session, itemMaster, strength,
					orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByStrength_PrevAndNext(session, itemMaster, strength,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByStrength_PrevAndNext(Session session,
		ItemMaster itemMaster, int strength,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		query.append(_FINDER_COLUMN_STRENGTH_STRENGTH_2);

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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(strength);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where strength = &#63; from the database.
	 *
	 * @param strength the strength
	 */
	@Override
	public void removeByStrength(int strength) {
		for (ItemMaster itemMaster : findByStrength(strength,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where strength = &#63;.
	 *
	 * @param strength the strength
	 * @return the number of matching item masters
	 */
	@Override
	public int countByStrength(int strength) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STRENGTH;

		Object[] finderArgs = new Object[] { strength };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_STRENGTH_STRENGTH_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(strength);

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

	private static final String _FINDER_COLUMN_STRENGTH_STRENGTH_2 = "itemMaster.strength = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRIMARYUOM =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPrimaryUom",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUOM =
		new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, ItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPrimaryUom",
			new String[] { Integer.class.getName() },
			ItemMasterModelImpl.PRIMARYUOM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRIMARYUOM = new FinderPath(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPrimaryUom",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item masters where primaryUom = &#63;.
	 *
	 * @param primaryUom the primary uom
	 * @return the matching item masters
	 */
	@Override
	public List<ItemMaster> findByPrimaryUom(int primaryUom) {
		return findByPrimaryUom(primaryUom, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters where primaryUom = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param primaryUom the primary uom
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByPrimaryUom(int primaryUom, int start, int end) {
		return findByPrimaryUom(primaryUom, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters where primaryUom = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param primaryUom the primary uom
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByPrimaryUom(int primaryUom, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator) {
		return findByPrimaryUom(primaryUom, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters where primaryUom = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param primaryUom the primary uom
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item masters
	 */
	@Override
	public List<ItemMaster> findByPrimaryUom(int primaryUom, int start,
		int end, OrderByComparator<ItemMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUOM;
			finderArgs = new Object[] { primaryUom };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRIMARYUOM;
			finderArgs = new Object[] { primaryUom, start, end, orderByComparator };
		}

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemMaster itemMaster : list) {
					if ((primaryUom != itemMaster.getPrimaryUom())) {
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

			query.append(_SQL_SELECT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_PRIMARYUOM_PRIMARYUOM_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(primaryUom);

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item master in the ordered set where primaryUom = &#63;.
	 *
	 * @param primaryUom the primary uom
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByPrimaryUom_First(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByPrimaryUom_First(primaryUom,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("primaryUom=");
		msg.append(primaryUom);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the first item master in the ordered set where primaryUom = &#63;.
	 *
	 * @param primaryUom the primary uom
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByPrimaryUom_First(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator) {
		List<ItemMaster> list = findByPrimaryUom(primaryUom, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item master in the ordered set where primaryUom = &#63;.
	 *
	 * @param primaryUom the primary uom
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master
	 * @throws NoSuchItemMasterException if a matching item master could not be found
	 */
	@Override
	public ItemMaster findByPrimaryUom_Last(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByPrimaryUom_Last(primaryUom,
				orderByComparator);

		if (itemMaster != null) {
			return itemMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("primaryUom=");
		msg.append(primaryUom);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemMasterException(msg.toString());
	}

	/**
	 * Returns the last item master in the ordered set where primaryUom = &#63;.
	 *
	 * @param primaryUom the primary uom
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item master, or <code>null</code> if a matching item master could not be found
	 */
	@Override
	public ItemMaster fetchByPrimaryUom_Last(int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator) {
		int count = countByPrimaryUom(primaryUom);

		if (count == 0) {
			return null;
		}

		List<ItemMaster> list = findByPrimaryUom(primaryUom, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item masters before and after the current item master in the ordered set where primaryUom = &#63;.
	 *
	 * @param itemMasterSid the primary key of the current item master
	 * @param primaryUom the primary uom
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster[] findByPrimaryUom_PrevAndNext(int itemMasterSid,
		int primaryUom, OrderByComparator<ItemMaster> orderByComparator)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = findByPrimaryKey(itemMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemMaster[] array = new ItemMasterImpl[3];

			array[0] = getByPrimaryUom_PrevAndNext(session, itemMaster,
					primaryUom, orderByComparator, true);

			array[1] = itemMaster;

			array[2] = getByPrimaryUom_PrevAndNext(session, itemMaster,
					primaryUom, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemMaster getByPrimaryUom_PrevAndNext(Session session,
		ItemMaster itemMaster, int primaryUom,
		OrderByComparator<ItemMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMMASTER_WHERE);

		query.append(_FINDER_COLUMN_PRIMARYUOM_PRIMARYUOM_2);

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
			query.append(ItemMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(primaryUom);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item masters where primaryUom = &#63; from the database.
	 *
	 * @param primaryUom the primary uom
	 */
	@Override
	public void removeByPrimaryUom(int primaryUom) {
		for (ItemMaster itemMaster : findByPrimaryUom(primaryUom,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters where primaryUom = &#63;.
	 *
	 * @param primaryUom the primary uom
	 * @return the number of matching item masters
	 */
	@Override
	public int countByPrimaryUom(int primaryUom) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRIMARYUOM;

		Object[] finderArgs = new Object[] { primaryUom };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMMASTER_WHERE);

			query.append(_FINDER_COLUMN_PRIMARYUOM_PRIMARYUOM_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(primaryUom);

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

	private static final String _FINDER_COLUMN_PRIMARYUOM_PRIMARYUOM_2 = "itemMaster.primaryUom = ?";

	public ItemMasterPersistenceImpl() {
		setModelClass(ItemMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("itemDesc", "ITEM_DESC");
			dbColumnNames.put("authorizedGenericStartDate",
				"AUTHORIZED_GENERIC_START_DATE");
			dbColumnNames.put("acquiredAmp", "ACQUIRED_AMP");
			dbColumnNames.put("newFormulationStartDate",
				"NEW_FORMULATION_START_DATE");
			dbColumnNames.put("marketTerminationDate", "MARKET_TERMINATION_DATE");
			dbColumnNames.put("obraBamp", "OBRA_BAMP");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("therapeuticClass", "THERAPEUTIC_CLASS");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("acquiredBamp", "ACQUIRED_BAMP");
			dbColumnNames.put("pediatricExclusiveEndDate",
				"PEDIATRIC_EXCLUSIVE_END_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("newFormulation", "NEW_FORMULATION");
			dbColumnNames.put("divestitureDate", "DIVESTITURE_DATE");
			dbColumnNames.put("primaryUom", "PRIMARY_UOM");
			dbColumnNames.put("newFormulationEndDate",
				"NEW_FORMULATION_END_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("packageSizeCode", "PACKAGE_SIZE_CODE");
			dbColumnNames.put("secondaryUom", "SECONDARY_UOM");
			dbColumnNames.put("discontinuationDate", "DISCONTINUATION_DATE");
			dbColumnNames.put("packageSizeIntroDate", "PACKAGE_SIZE_INTRO_DATE");
			dbColumnNames.put("manufacturerId", "MANUFACTURER_ID");
			dbColumnNames.put("itemEndDate", "ITEM_END_DATE");
			dbColumnNames.put("itemFamilyId", "ITEM_FAMILY_ID");
			dbColumnNames.put("strength", "STRENGTH");
			dbColumnNames.put("itemCategory", "ITEM_CATEGORY");
			dbColumnNames.put("upps", "UPPS");
			dbColumnNames.put("shelfLifeType", "SHELF_LIFE_TYPE");
			dbColumnNames.put("pediatricExclusiveIndicator",
				"PEDIATRIC_EXCLUSIVE_INDICATOR");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("itemTypeIndication", "ITEM_TYPE_INDICATION");
			dbColumnNames.put("acquisitionDate", "ACQUISITION_DATE");
			dbColumnNames.put("clottingFactorIndicator",
				"CLOTTING_FACTOR_INDICATOR");
			dbColumnNames.put("form", "FORM");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("shelfLife", "SHELF_LIFE");
			dbColumnNames.put("pediatricExclusiveStartDate",
				"PEDIATRIC_EXCLUSIVE_START_DATE");
			dbColumnNames.put("firstSaleDate", "FIRST_SALE_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemType", "ITEM_TYPE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("baselineAmp", "BASELINE_AMP");
			dbColumnNames.put("dualPricingIndicator", "DUAL_PRICING_INDICATOR");
			dbColumnNames.put("dosesPerUnit", "DOSES_PER_UNIT");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("authorizedGeneric", "AUTHORIZED_GENERIC");
			dbColumnNames.put("itemStartDate", "ITEM_START_DATE");
			dbColumnNames.put("ndc9", "NDC9");
			dbColumnNames.put("authorizedGenericEndDate",
				"AUTHORIZED_GENERIC_END_DATE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("packageSize", "PACKAGE_SIZE");
			dbColumnNames.put("ndc8", "NDC8");
			dbColumnNames.put("baseCpi", "BASE_CPI");
			dbColumnNames.put("labelerCode", "LABELER_CODE");
			dbColumnNames.put("itemClass", "ITEM_CLASS");
			dbColumnNames.put("clottingFactorEndDate",
				"CLOTTING_FACTOR_END_DATE");
			dbColumnNames.put("dra", "DRA");
			dbColumnNames.put("baseCpiPeriod", "BASE_CPI_PERIOD");
			dbColumnNames.put("newFormulationIndicator",
				"NEW_FORMULATION_INDICATOR");
			dbColumnNames.put("lastLotExpirationDate",
				"LAST_LOT_EXPIRATION_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemCode", "ITEM_CODE");
			dbColumnNames.put("clottingFactorStartDate",
				"CLOTTING_FACTOR_START_DATE");
			dbColumnNames.put("nonFederalExpirationDate",
				"NON_FEDERAL_EXPIRATION_DATE");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");
			dbColumnNames.put("baseCpiPrecision", "BASE_CPI_PRECISION");
			dbColumnNames.put("baselineAmpPrecision", "BASELINE_AMP_PRECISION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the item master in the entity cache if it is enabled.
	 *
	 * @param itemMaster the item master
	 */
	@Override
	public void cacheResult(ItemMaster itemMaster) {
		entityCache.putResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterImpl.class, itemMaster.getPrimaryKey(), itemMaster);

		itemMaster.resetOriginalValues();
	}

	/**
	 * Caches the item masters in the entity cache if it is enabled.
	 *
	 * @param itemMasters the item masters
	 */
	@Override
	public void cacheResult(List<ItemMaster> itemMasters) {
		for (ItemMaster itemMaster : itemMasters) {
			if (entityCache.getResult(
						ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
						ItemMasterImpl.class, itemMaster.getPrimaryKey()) == null) {
				cacheResult(itemMaster);
			}
			else {
				itemMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemMaster itemMaster) {
		entityCache.removeResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterImpl.class, itemMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ItemMaster> itemMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemMaster itemMaster : itemMasters) {
			entityCache.removeResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
				ItemMasterImpl.class, itemMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new item master with the primary key. Does not add the item master to the database.
	 *
	 * @param itemMasterSid the primary key for the new item master
	 * @return the new item master
	 */
	@Override
	public ItemMaster create(int itemMasterSid) {
		ItemMaster itemMaster = new ItemMasterImpl();

		itemMaster.setNew(true);
		itemMaster.setPrimaryKey(itemMasterSid);

		return itemMaster;
	}

	/**
	 * Removes the item master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemMasterSid the primary key of the item master
	 * @return the item master that was removed
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster remove(int itemMasterSid)
		throws NoSuchItemMasterException {
		return remove((Serializable)itemMasterSid);
	}

	/**
	 * Removes the item master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item master
	 * @return the item master that was removed
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster remove(Serializable primaryKey)
		throws NoSuchItemMasterException {
		Session session = null;

		try {
			session = openSession();

			ItemMaster itemMaster = (ItemMaster)session.get(ItemMasterImpl.class,
					primaryKey);

			if (itemMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemMaster);
		}
		catch (NoSuchItemMasterException nsee) {
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
	protected ItemMaster removeImpl(ItemMaster itemMaster) {
		itemMaster = toUnwrappedModel(itemMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemMaster)) {
				itemMaster = (ItemMaster)session.get(ItemMasterImpl.class,
						itemMaster.getPrimaryKeyObj());
			}

			if (itemMaster != null) {
				session.delete(itemMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemMaster != null) {
			clearCache(itemMaster);
		}

		return itemMaster;
	}

	@Override
	public ItemMaster updateImpl(ItemMaster itemMaster) {
		itemMaster = toUnwrappedModel(itemMaster);

		boolean isNew = itemMaster.isNew();

		ItemMasterModelImpl itemMasterModelImpl = (ItemMasterModelImpl)itemMaster;

		Session session = null;

		try {
			session = openSession();

			if (itemMaster.isNew()) {
				session.save(itemMaster);

				itemMaster.setNew(false);
			}
			else {
				itemMaster = (ItemMaster)session.merge(itemMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { itemMasterModelImpl.getItemNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
				args);

			args = new Object[] { itemMasterModelImpl.getItemId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
				args);

			args = new Object[] { itemMasterModelImpl.getItemName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNAME,
				args);

			args = new Object[] { itemMasterModelImpl.getItemType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMTYPE,
				args);

			args = new Object[] { itemMasterModelImpl.getItemStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMSTATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMSTATUS,
				args);

			args = new Object[] { itemMasterModelImpl.getManufacturerId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MANUFACTURERNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MANUFACTURERNO,
				args);

			args = new Object[] { itemMasterModelImpl.getForm() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FORM, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORM,
				args);

			args = new Object[] { itemMasterModelImpl.getStrength() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STRENGTH, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRENGTH,
				args);

			args = new Object[] { itemMasterModelImpl.getPrimaryUom() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRIMARYUOM, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUOM,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalItemNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
					args);

				args = new Object[] { itemMasterModelImpl.getItemNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
					args);

				args = new Object[] { itemMasterModelImpl.getItemId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalItemName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNAME,
					args);

				args = new Object[] { itemMasterModelImpl.getItemName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNAME,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalItemType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMTYPE,
					args);

				args = new Object[] { itemMasterModelImpl.getItemType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMTYPE,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalItemStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMSTATUS,
					args);

				args = new Object[] { itemMasterModelImpl.getItemStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMSTATUS,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MANUFACTURERNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalManufacturerId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MANUFACTURERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MANUFACTURERNO,
					args);

				args = new Object[] { itemMasterModelImpl.getManufacturerId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MANUFACTURERNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MANUFACTURERNO,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORM.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalForm()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORM, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORM,
					args);

				args = new Object[] { itemMasterModelImpl.getForm() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORM, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORM,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRENGTH.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalStrength()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STRENGTH, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRENGTH,
					args);

				args = new Object[] { itemMasterModelImpl.getStrength() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STRENGTH, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STRENGTH,
					args);
			}

			if ((itemMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUOM.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemMasterModelImpl.getOriginalPrimaryUom()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRIMARYUOM, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUOM,
					args);

				args = new Object[] { itemMasterModelImpl.getPrimaryUom() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRIMARYUOM, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRIMARYUOM,
					args);
			}
		}

		entityCache.putResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemMasterImpl.class, itemMaster.getPrimaryKey(), itemMaster, false);

		itemMaster.resetOriginalValues();

		return itemMaster;
	}

	protected ItemMaster toUnwrappedModel(ItemMaster itemMaster) {
		if (itemMaster instanceof ItemMasterImpl) {
			return itemMaster;
		}

		ItemMasterImpl itemMasterImpl = new ItemMasterImpl();

		itemMasterImpl.setNew(itemMaster.isNew());
		itemMasterImpl.setPrimaryKey(itemMaster.getPrimaryKey());

		itemMasterImpl.setItemStatus(itemMaster.getItemStatus());
		itemMasterImpl.setItemDesc(itemMaster.getItemDesc());
		itemMasterImpl.setAuthorizedGenericStartDate(itemMaster.getAuthorizedGenericStartDate());
		itemMasterImpl.setAcquiredAmp(itemMaster.getAcquiredAmp());
		itemMasterImpl.setNewFormulationStartDate(itemMaster.getNewFormulationStartDate());
		itemMasterImpl.setMarketTerminationDate(itemMaster.getMarketTerminationDate());
		itemMasterImpl.setObraBamp(itemMaster.getObraBamp());
		itemMasterImpl.setModifiedDate(itemMaster.getModifiedDate());
		itemMasterImpl.setTherapeuticClass(itemMaster.getTherapeuticClass());
		itemMasterImpl.setOrganizationKey(itemMaster.getOrganizationKey());
		itemMasterImpl.setAcquiredBamp(itemMaster.getAcquiredBamp());
		itemMasterImpl.setPediatricExclusiveEndDate(itemMaster.getPediatricExclusiveEndDate());
		itemMasterImpl.setSource(itemMaster.getSource());
		itemMasterImpl.setNewFormulation(itemMaster.getNewFormulation());
		itemMasterImpl.setDivestitureDate(itemMaster.getDivestitureDate());
		itemMasterImpl.setPrimaryUom(itemMaster.getPrimaryUom());
		itemMasterImpl.setNewFormulationEndDate(itemMaster.getNewFormulationEndDate());
		itemMasterImpl.setModifiedBy(itemMaster.getModifiedBy());
		itemMasterImpl.setInboundStatus(itemMaster.getInboundStatus());
		itemMasterImpl.setPackageSizeCode(itemMaster.getPackageSizeCode());
		itemMasterImpl.setSecondaryUom(itemMaster.getSecondaryUom());
		itemMasterImpl.setDiscontinuationDate(itemMaster.getDiscontinuationDate());
		itemMasterImpl.setPackageSizeIntroDate(itemMaster.getPackageSizeIntroDate());
		itemMasterImpl.setManufacturerId(itemMaster.getManufacturerId());
		itemMasterImpl.setItemEndDate(itemMaster.getItemEndDate());
		itemMasterImpl.setItemFamilyId(itemMaster.getItemFamilyId());
		itemMasterImpl.setStrength(itemMaster.getStrength());
		itemMasterImpl.setItemCategory(itemMaster.getItemCategory());
		itemMasterImpl.setUpps(itemMaster.getUpps());
		itemMasterImpl.setShelfLifeType(itemMaster.getShelfLifeType());
		itemMasterImpl.setPediatricExclusiveIndicator(itemMaster.getPediatricExclusiveIndicator());
		itemMasterImpl.setRecordLockStatus(itemMaster.isRecordLockStatus());
		itemMasterImpl.setItemTypeIndication(itemMaster.getItemTypeIndication());
		itemMasterImpl.setAcquisitionDate(itemMaster.getAcquisitionDate());
		itemMasterImpl.setClottingFactorIndicator(itemMaster.getClottingFactorIndicator());
		itemMasterImpl.setForm(itemMaster.getForm());
		itemMasterImpl.setItemName(itemMaster.getItemName());
		itemMasterImpl.setShelfLife(itemMaster.getShelfLife());
		itemMasterImpl.setPediatricExclusiveStartDate(itemMaster.getPediatricExclusiveStartDate());
		itemMasterImpl.setFirstSaleDate(itemMaster.getFirstSaleDate());
		itemMasterImpl.setItemMasterSid(itemMaster.getItemMasterSid());
		itemMasterImpl.setItemType(itemMaster.getItemType());
		itemMasterImpl.setItemId(itemMaster.getItemId());
		itemMasterImpl.setBrandMasterSid(itemMaster.getBrandMasterSid());
		itemMasterImpl.setBaselineAmp(itemMaster.getBaselineAmp());
		itemMasterImpl.setDualPricingIndicator(itemMaster.getDualPricingIndicator());
		itemMasterImpl.setDosesPerUnit(itemMaster.getDosesPerUnit());
		itemMasterImpl.setCreatedDate(itemMaster.getCreatedDate());
		itemMasterImpl.setCreatedBy(itemMaster.getCreatedBy());
		itemMasterImpl.setAuthorizedGeneric(itemMaster.getAuthorizedGeneric());
		itemMasterImpl.setItemStartDate(itemMaster.getItemStartDate());
		itemMasterImpl.setNdc9(itemMaster.getNdc9());
		itemMasterImpl.setAuthorizedGenericEndDate(itemMaster.getAuthorizedGenericEndDate());
		itemMasterImpl.setItemNo(itemMaster.getItemNo());
		itemMasterImpl.setPackageSize(itemMaster.getPackageSize());
		itemMasterImpl.setNdc8(itemMaster.getNdc8());
		itemMasterImpl.setBaseCpi(itemMaster.getBaseCpi());
		itemMasterImpl.setLabelerCode(itemMaster.getLabelerCode());
		itemMasterImpl.setItemClass(itemMaster.getItemClass());
		itemMasterImpl.setClottingFactorEndDate(itemMaster.getClottingFactorEndDate());
		itemMasterImpl.setDra(itemMaster.getDra());
		itemMasterImpl.setBaseCpiPeriod(itemMaster.getBaseCpiPeriod());
		itemMasterImpl.setNewFormulationIndicator(itemMaster.getNewFormulationIndicator());
		itemMasterImpl.setLastLotExpirationDate(itemMaster.getLastLotExpirationDate());
		itemMasterImpl.setBatchId(itemMaster.getBatchId());
		itemMasterImpl.setItemCode(itemMaster.getItemCode());
		itemMasterImpl.setClottingFactorStartDate(itemMaster.getClottingFactorStartDate());
		itemMasterImpl.setNonFederalExpirationDate(itemMaster.getNonFederalExpirationDate());
		itemMasterImpl.setInternalNotes(itemMaster.getInternalNotes());
		itemMasterImpl.setBaseCpiPrecision(itemMaster.getBaseCpiPrecision());
		itemMasterImpl.setBaselineAmpPrecision(itemMaster.getBaselineAmpPrecision());

		return itemMasterImpl;
	}

	/**
	 * Returns the item master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item master
	 * @return the item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemMasterException {
		ItemMaster itemMaster = fetchByPrimaryKey(primaryKey);

		if (itemMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemMaster;
	}

	/**
	 * Returns the item master with the primary key or throws a {@link NoSuchItemMasterException} if it could not be found.
	 *
	 * @param itemMasterSid the primary key of the item master
	 * @return the item master
	 * @throws NoSuchItemMasterException if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster findByPrimaryKey(int itemMasterSid)
		throws NoSuchItemMasterException {
		return findByPrimaryKey((Serializable)itemMasterSid);
	}

	/**
	 * Returns the item master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item master
	 * @return the item master, or <code>null</code> if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
				ItemMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemMaster itemMaster = (ItemMaster)serializable;

		if (itemMaster == null) {
			Session session = null;

			try {
				session = openSession();

				itemMaster = (ItemMaster)session.get(ItemMasterImpl.class,
						primaryKey);

				if (itemMaster != null) {
					cacheResult(itemMaster);
				}
				else {
					entityCache.putResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
						ItemMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemMaster;
	}

	/**
	 * Returns the item master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemMasterSid the primary key of the item master
	 * @return the item master, or <code>null</code> if a item master with the primary key could not be found
	 */
	@Override
	public ItemMaster fetchByPrimaryKey(int itemMasterSid) {
		return fetchByPrimaryKey((Serializable)itemMasterSid);
	}

	@Override
	public Map<Serializable, ItemMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemMaster> map = new HashMap<Serializable, ItemMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemMaster itemMaster = fetchByPrimaryKey(primaryKey);

			if (itemMaster != null) {
				map.put(primaryKey, itemMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMMASTER_WHERE_PKS_IN);

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

			for (ItemMaster itemMaster : (List<ItemMaster>)q.list()) {
				map.put(itemMaster.getPrimaryKeyObj(), itemMaster);

				cacheResult(itemMaster);

				uncachedPrimaryKeys.remove(itemMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the item masters.
	 *
	 * @return the item masters
	 */
	@Override
	public List<ItemMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @return the range of item masters
	 */
	@Override
	public List<ItemMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item masters
	 */
	@Override
	public List<ItemMaster> findAll(int start, int end,
		OrderByComparator<ItemMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item masters
	 * @param end the upper bound of the range of item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item masters
	 */
	@Override
	public List<ItemMaster> findAll(int start, int end,
		OrderByComparator<ItemMaster> orderByComparator,
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

		List<ItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMMASTER;

				if (pagination) {
					sql = sql.concat(ItemMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the item masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemMaster itemMaster : findAll()) {
			remove(itemMaster);
		}
	}

	/**
	 * Returns the number of item masters.
	 *
	 * @return the number of item masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMMASTER);

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
		return ItemMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMMASTER = "SELECT itemMaster FROM ItemMaster itemMaster";
	private static final String _SQL_SELECT_ITEMMASTER_WHERE_PKS_IN = "SELECT itemMaster FROM ItemMaster itemMaster WHERE ITEM_MASTER_SID IN (";
	private static final String _SQL_SELECT_ITEMMASTER_WHERE = "SELECT itemMaster FROM ItemMaster itemMaster WHERE ";
	private static final String _SQL_COUNT_ITEMMASTER = "SELECT COUNT(itemMaster) FROM ItemMaster itemMaster";
	private static final String _SQL_COUNT_ITEMMASTER_WHERE = "SELECT COUNT(itemMaster) FROM ItemMaster itemMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemStatus", "itemDesc", "authorizedGenericStartDate",
				"acquiredAmp", "newFormulationStartDate",
				"marketTerminationDate", "obraBamp", "modifiedDate",
				"therapeuticClass", "organizationKey", "acquiredBamp",
				"pediatricExclusiveEndDate", "source", "newFormulation",
				"divestitureDate", "primaryUom", "newFormulationEndDate",
				"modifiedBy", "inboundStatus", "packageSizeCode", "secondaryUom",
				"discontinuationDate", "packageSizeIntroDate", "manufacturerId",
				"itemEndDate", "itemFamilyId", "strength", "itemCategory",
				"upps", "shelfLifeType", "pediatricExclusiveIndicator",
				"recordLockStatus", "itemTypeIndication", "acquisitionDate",
				"clottingFactorIndicator", "form", "itemName", "shelfLife",
				"pediatricExclusiveStartDate", "firstSaleDate", "itemMasterSid",
				"itemType", "itemId", "brandMasterSid", "baselineAmp",
				"dualPricingIndicator", "dosesPerUnit", "createdDate",
				"createdBy", "authorizedGeneric", "itemStartDate", "ndc9",
				"authorizedGenericEndDate", "itemNo", "packageSize", "ndc8",
				"baseCpi", "labelerCode", "itemClass", "clottingFactorEndDate",
				"dra", "baseCpiPeriod", "newFormulationIndicator",
				"lastLotExpirationDate", "batchId", "itemCode",
				"clottingFactorStartDate", "nonFederalExpirationDate",
				"internalNotes", "baseCpiPrecision", "baselineAmpPrecision"
			});
}