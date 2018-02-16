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

import com.stpl.app.exception.NoSuchItemHierarchyDefMasterException;
import com.stpl.app.model.ItemHierarchyDefMaster;
import com.stpl.app.model.impl.ItemHierarchyDefMasterImpl;
import com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl;
import com.stpl.app.service.persistence.ItemHierarchyDefMasterPersistence;

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
 * The persistence implementation for the item hierarchy def master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMasterPersistence
 * @see com.stpl.app.service.persistence.ItemHierarchyDefMasterUtil
 * @generated
 */
@ProviderType
public class ItemHierarchyDefMasterPersistenceImpl extends BasePersistenceImpl<ItemHierarchyDefMaster>
	implements ItemHierarchyDefMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemHierarchyDefMasterUtil} to access the item hierarchy def master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemHierarchyDefMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MEMBER = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMember",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER =
		new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMember",
			new String[] { String.class.getName() },
			ItemHierarchyDefMasterModelImpl.MEMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MEMBER = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMember",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy def masters where member = &#63;.
	 *
	 * @param member the member
	 * @return the matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByMember(String member) {
		return findByMember(member, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy def masters where member = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param member the member
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @return the range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByMember(String member, int start,
		int end) {
		return findByMember(member, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where member = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param member the member
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByMember(String member, int start,
		int end, OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return findByMember(member, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where member = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param member the member
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByMember(String member, int start,
		int end, OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER;
			finderArgs = new Object[] { member };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MEMBER;
			finderArgs = new Object[] { member, start, end, orderByComparator };
		}

		List<ItemHierarchyDefMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyDefMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
					if (!Objects.equals(member,
								itemHierarchyDefMaster.getMember())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindMember = false;

			if (member == null) {
				query.append(_FINDER_COLUMN_MEMBER_MEMBER_1);
			}
			else if (member.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MEMBER_MEMBER_3);
			}
			else {
				bindMember = true;

				query.append(_FINDER_COLUMN_MEMBER_MEMBER_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMember) {
					qPos.add(member);
				}

				if (!pagination) {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy def master in the ordered set where member = &#63;.
	 *
	 * @param member the member
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByMember_First(String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByMember_First(member,
				orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("member=");
		msg.append(member);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy def master in the ordered set where member = &#63;.
	 *
	 * @param member the member
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByMember_First(String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		List<ItemHierarchyDefMaster> list = findByMember(member, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where member = &#63;.
	 *
	 * @param member the member
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByMember_Last(String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByMember_Last(member,
				orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("member=");
		msg.append(member);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where member = &#63;.
	 *
	 * @param member the member
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByMember_Last(String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		int count = countByMember(member);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyDefMaster> list = findByMember(member, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63;.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	 * @param member the member
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster[] findByMember_PrevAndNext(
		int itemHierarchyDefMasterSid, String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

			array[0] = getByMember_PrevAndNext(session, itemHierarchyDefMaster,
					member, orderByComparator, true);

			array[1] = itemHierarchyDefMaster;

			array[2] = getByMember_PrevAndNext(session, itemHierarchyDefMaster,
					member, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyDefMaster getByMember_PrevAndNext(Session session,
		ItemHierarchyDefMaster itemHierarchyDefMaster, String member,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
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

		query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

		boolean bindMember = false;

		if (member == null) {
			query.append(_FINDER_COLUMN_MEMBER_MEMBER_1);
		}
		else if (member.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_MEMBER_MEMBER_3);
		}
		else {
			bindMember = true;

			query.append(_FINDER_COLUMN_MEMBER_MEMBER_2);
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
			query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindMember) {
			qPos.add(member);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyDefMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy def masters where member = &#63; from the database.
	 *
	 * @param member the member
	 */
	@Override
	public void removeByMember(String member) {
		for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByMember(
				member, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyDefMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy def masters where member = &#63;.
	 *
	 * @param member the member
	 * @return the number of matching item hierarchy def masters
	 */
	@Override
	public int countByMember(String member) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MEMBER;

		Object[] finderArgs = new Object[] { member };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindMember = false;

			if (member == null) {
				query.append(_FINDER_COLUMN_MEMBER_MEMBER_1);
			}
			else if (member.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MEMBER_MEMBER_3);
			}
			else {
				bindMember = true;

				query.append(_FINDER_COLUMN_MEMBER_MEMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMember) {
					qPos.add(member);
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

	private static final String _FINDER_COLUMN_MEMBER_MEMBER_1 = "itemHierarchyDefMaster.member IS NULL";
	private static final String _FINDER_COLUMN_MEMBER_MEMBER_2 = "itemHierarchyDefMaster.member = ?";
	private static final String _FINDER_COLUMN_MEMBER_MEMBER_3 = "(itemHierarchyDefMaster.member IS NULL OR itemHierarchyDefMaster.member = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ALIAS = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAlias",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAlias",
			new String[] { String.class.getName() },
			ItemHierarchyDefMasterModelImpl.ALIAS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ALIAS = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAlias",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy def masters where alias = &#63;.
	 *
	 * @param alias the alias
	 * @return the matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByAlias(String alias) {
		return findByAlias(alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy def masters where alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param alias the alias
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @return the range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByAlias(String alias, int start,
		int end) {
		return findByAlias(alias, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param alias the alias
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByAlias(String alias, int start,
		int end, OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return findByAlias(alias, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param alias the alias
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByAlias(String alias, int start,
		int end, OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS;
			finderArgs = new Object[] { alias };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ALIAS;
			finderArgs = new Object[] { alias, start, end, orderByComparator };
		}

		List<ItemHierarchyDefMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyDefMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
					if (!Objects.equals(alias, itemHierarchyDefMaster.getAlias())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindAlias = false;

			if (alias == null) {
				query.append(_FINDER_COLUMN_ALIAS_ALIAS_1);
			}
			else if (alias.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ALIAS_ALIAS_3);
			}
			else {
				bindAlias = true;

				query.append(_FINDER_COLUMN_ALIAS_ALIAS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAlias) {
					qPos.add(alias);
				}

				if (!pagination) {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
	 *
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByAlias_First(String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByAlias_First(alias,
				orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("alias=");
		msg.append(alias);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy def master in the ordered set where alias = &#63;.
	 *
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByAlias_First(String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		List<ItemHierarchyDefMaster> list = findByAlias(alias, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
	 *
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByAlias_Last(String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByAlias_Last(alias,
				orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("alias=");
		msg.append(alias);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where alias = &#63;.
	 *
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByAlias_Last(String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		int count = countByAlias(alias);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyDefMaster> list = findByAlias(alias, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where alias = &#63;.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster[] findByAlias_PrevAndNext(
		int itemHierarchyDefMasterSid, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

			array[0] = getByAlias_PrevAndNext(session, itemHierarchyDefMaster,
					alias, orderByComparator, true);

			array[1] = itemHierarchyDefMaster;

			array[2] = getByAlias_PrevAndNext(session, itemHierarchyDefMaster,
					alias, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyDefMaster getByAlias_PrevAndNext(Session session,
		ItemHierarchyDefMaster itemHierarchyDefMaster, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
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

		query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

		boolean bindAlias = false;

		if (alias == null) {
			query.append(_FINDER_COLUMN_ALIAS_ALIAS_1);
		}
		else if (alias.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ALIAS_ALIAS_3);
		}
		else {
			bindAlias = true;

			query.append(_FINDER_COLUMN_ALIAS_ALIAS_2);
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
			query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAlias) {
			qPos.add(alias);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyDefMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy def masters where alias = &#63; from the database.
	 *
	 * @param alias the alias
	 */
	@Override
	public void removeByAlias(String alias) {
		for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByAlias(
				alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyDefMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy def masters where alias = &#63;.
	 *
	 * @param alias the alias
	 * @return the number of matching item hierarchy def masters
	 */
	@Override
	public int countByAlias(String alias) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ALIAS;

		Object[] finderArgs = new Object[] { alias };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindAlias = false;

			if (alias == null) {
				query.append(_FINDER_COLUMN_ALIAS_ALIAS_1);
			}
			else if (alias.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ALIAS_ALIAS_3);
			}
			else {
				bindAlias = true;

				query.append(_FINDER_COLUMN_ALIAS_ALIAS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAlias) {
					qPos.add(alias);
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

	private static final String _FINDER_COLUMN_ALIAS_ALIAS_1 = "itemHierarchyDefMaster.alias IS NULL";
	private static final String _FINDER_COLUMN_ALIAS_ALIAS_2 = "itemHierarchyDefMaster.alias = ?";
	private static final String _FINDER_COLUMN_ALIAS_ALIAS_3 = "(itemHierarchyDefMaster.alias IS NULL OR itemHierarchyDefMaster.alias = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BPILVL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBpiLvl",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL =
		new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBpiLvl",
			new String[] { String.class.getName() },
			ItemHierarchyDefMasterModelImpl.BPILVL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BPILVL = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBpiLvl",
			new String[] { String.class.getName() });

	/**
	 * Returns all the item hierarchy def masters where bpiLvl = &#63;.
	 *
	 * @param bpiLvl the bpi lvl
	 * @return the matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl) {
		return findByBpiLvl(bpiLvl, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy def masters where bpiLvl = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bpiLvl the bpi lvl
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @return the range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl, int start,
		int end) {
		return findByBpiLvl(bpiLvl, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bpiLvl the bpi lvl
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl, int start,
		int end, OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return findByBpiLvl(bpiLvl, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where bpiLvl = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bpiLvl the bpi lvl
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByBpiLvl(String bpiLvl, int start,
		int end, OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL;
			finderArgs = new Object[] { bpiLvl };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BPILVL;
			finderArgs = new Object[] { bpiLvl, start, end, orderByComparator };
		}

		List<ItemHierarchyDefMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyDefMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
					if (!Objects.equals(bpiLvl,
								itemHierarchyDefMaster.getBpiLvl())) {
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

			query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindBpiLvl = false;

			if (bpiLvl == null) {
				query.append(_FINDER_COLUMN_BPILVL_BPILVL_1);
			}
			else if (bpiLvl.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BPILVL_BPILVL_3);
			}
			else {
				bindBpiLvl = true;

				query.append(_FINDER_COLUMN_BPILVL_BPILVL_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBpiLvl) {
					qPos.add(bpiLvl);
				}

				if (!pagination) {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
	 *
	 * @param bpiLvl the bpi lvl
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByBpiLvl_First(String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByBpiLvl_First(bpiLvl,
				orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bpiLvl=");
		msg.append(bpiLvl);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy def master in the ordered set where bpiLvl = &#63;.
	 *
	 * @param bpiLvl the bpi lvl
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByBpiLvl_First(String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		List<ItemHierarchyDefMaster> list = findByBpiLvl(bpiLvl, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
	 *
	 * @param bpiLvl the bpi lvl
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByBpiLvl_Last(String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByBpiLvl_Last(bpiLvl,
				orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bpiLvl=");
		msg.append(bpiLvl);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where bpiLvl = &#63;.
	 *
	 * @param bpiLvl the bpi lvl
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByBpiLvl_Last(String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		int count = countByBpiLvl(bpiLvl);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyDefMaster> list = findByBpiLvl(bpiLvl, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where bpiLvl = &#63;.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	 * @param bpiLvl the bpi lvl
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster[] findByBpiLvl_PrevAndNext(
		int itemHierarchyDefMasterSid, String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

			array[0] = getByBpiLvl_PrevAndNext(session, itemHierarchyDefMaster,
					bpiLvl, orderByComparator, true);

			array[1] = itemHierarchyDefMaster;

			array[2] = getByBpiLvl_PrevAndNext(session, itemHierarchyDefMaster,
					bpiLvl, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyDefMaster getByBpiLvl_PrevAndNext(Session session,
		ItemHierarchyDefMaster itemHierarchyDefMaster, String bpiLvl,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
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

		query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

		boolean bindBpiLvl = false;

		if (bpiLvl == null) {
			query.append(_FINDER_COLUMN_BPILVL_BPILVL_1);
		}
		else if (bpiLvl.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BPILVL_BPILVL_3);
		}
		else {
			bindBpiLvl = true;

			query.append(_FINDER_COLUMN_BPILVL_BPILVL_2);
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
			query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindBpiLvl) {
			qPos.add(bpiLvl);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyDefMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy def masters where bpiLvl = &#63; from the database.
	 *
	 * @param bpiLvl the bpi lvl
	 */
	@Override
	public void removeByBpiLvl(String bpiLvl) {
		for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByBpiLvl(
				bpiLvl, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyDefMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy def masters where bpiLvl = &#63;.
	 *
	 * @param bpiLvl the bpi lvl
	 * @return the number of matching item hierarchy def masters
	 */
	@Override
	public int countByBpiLvl(String bpiLvl) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BPILVL;

		Object[] finderArgs = new Object[] { bpiLvl };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindBpiLvl = false;

			if (bpiLvl == null) {
				query.append(_FINDER_COLUMN_BPILVL_BPILVL_1);
			}
			else if (bpiLvl.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BPILVL_BPILVL_3);
			}
			else {
				bindBpiLvl = true;

				query.append(_FINDER_COLUMN_BPILVL_BPILVL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBpiLvl) {
					qPos.add(bpiLvl);
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

	private static final String _FINDER_COLUMN_BPILVL_BPILVL_1 = "itemHierarchyDefMaster.bpiLvl IS NULL";
	private static final String _FINDER_COLUMN_BPILVL_BPILVL_2 = "itemHierarchyDefMaster.bpiLvl = ?";
	private static final String _FINDER_COLUMN_BPILVL_BPILVL_3 = "(itemHierarchyDefMaster.bpiLvl IS NULL OR itemHierarchyDefMaster.bpiLvl = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE =
		new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemHierarchyDefUnique",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE =
		new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemHierarchyDefUnique",
			new String[] { String.class.getName(), String.class.getName() },
			ItemHierarchyDefMasterModelImpl.MEMBER_COLUMN_BITMASK |
			ItemHierarchyDefMasterModelImpl.ALIAS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE = new FinderPath(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemHierarchyDefUnique",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the item hierarchy def masters where member = &#63; and alias = &#63;.
	 *
	 * @param member the member
	 * @param alias the alias
	 * @return the matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		String member, String alias) {
		return findByItemHierarchyDefUnique(member, alias, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @return the range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		String member, String alias, int start, int end) {
		return findByItemHierarchyDefUnique(member, alias, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		String member, String alias, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return findByItemHierarchyDefUnique(member, alias, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters where member = &#63; and alias = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findByItemHierarchyDefUnique(
		String member, String alias, int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE;
			finderArgs = new Object[] { member, alias };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE;
			finderArgs = new Object[] {
					member, alias,
					
					start, end, orderByComparator
				};
		}

		List<ItemHierarchyDefMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyDefMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemHierarchyDefMaster itemHierarchyDefMaster : list) {
					if (!Objects.equals(member,
								itemHierarchyDefMaster.getMember()) ||
							!Objects.equals(alias,
								itemHierarchyDefMaster.getAlias())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindMember = false;

			if (member == null) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1);
			}
			else if (member.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3);
			}
			else {
				bindMember = true;

				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2);
			}

			boolean bindAlias = false;

			if (alias == null) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1);
			}
			else if (alias.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3);
			}
			else {
				bindAlias = true;

				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMember) {
					qPos.add(member);
				}

				if (bindAlias) {
					qPos.add(alias);
				}

				if (!pagination) {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
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
	 * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByItemHierarchyDefUnique_First(
		String member, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByItemHierarchyDefUnique_First(member,
				alias, orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("member=");
		msg.append(member);

		msg.append(", alias=");
		msg.append(alias);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the first item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_First(
		String member, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		List<ItemHierarchyDefMaster> list = findByItemHierarchyDefUnique(member,
				alias, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByItemHierarchyDefUnique_Last(
		String member, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByItemHierarchyDefUnique_Last(member,
				alias, orderByComparator);

		if (itemHierarchyDefMaster != null) {
			return itemHierarchyDefMaster;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("member=");
		msg.append(member);

		msg.append(", alias=");
		msg.append(alias);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemHierarchyDefMasterException(msg.toString());
	}

	/**
	 * Returns the last item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	 *
	 * @param member the member
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item hierarchy def master, or <code>null</code> if a matching item hierarchy def master could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByItemHierarchyDefUnique_Last(
		String member, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		int count = countByItemHierarchyDefUnique(member, alias);

		if (count == 0) {
			return null;
		}

		List<ItemHierarchyDefMaster> list = findByItemHierarchyDefUnique(member,
				alias, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item hierarchy def masters before and after the current item hierarchy def master in the ordered set where member = &#63; and alias = &#63;.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the current item hierarchy def master
	 * @param member the member
	 * @param alias the alias
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster[] findByItemHierarchyDefUnique_PrevAndNext(
		int itemHierarchyDefMasterSid, String member, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = findByPrimaryKey(itemHierarchyDefMasterSid);

		Session session = null;

		try {
			session = openSession();

			ItemHierarchyDefMaster[] array = new ItemHierarchyDefMasterImpl[3];

			array[0] = getByItemHierarchyDefUnique_PrevAndNext(session,
					itemHierarchyDefMaster, member, alias, orderByComparator,
					true);

			array[1] = itemHierarchyDefMaster;

			array[2] = getByItemHierarchyDefUnique_PrevAndNext(session,
					itemHierarchyDefMaster, member, alias, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemHierarchyDefMaster getByItemHierarchyDefUnique_PrevAndNext(
		Session session, ItemHierarchyDefMaster itemHierarchyDefMaster,
		String member, String alias,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE);

		boolean bindMember = false;

		if (member == null) {
			query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1);
		}
		else if (member.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3);
		}
		else {
			bindMember = true;

			query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2);
		}

		boolean bindAlias = false;

		if (alias == null) {
			query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1);
		}
		else if (alias.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3);
		}
		else {
			bindAlias = true;

			query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2);
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
			query.append(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindMember) {
			qPos.add(member);
		}

		if (bindAlias) {
			qPos.add(alias);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemHierarchyDefMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemHierarchyDefMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item hierarchy def masters where member = &#63; and alias = &#63; from the database.
	 *
	 * @param member the member
	 * @param alias the alias
	 */
	@Override
	public void removeByItemHierarchyDefUnique(String member, String alias) {
		for (ItemHierarchyDefMaster itemHierarchyDefMaster : findByItemHierarchyDefUnique(
				member, alias, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemHierarchyDefMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy def masters where member = &#63; and alias = &#63;.
	 *
	 * @param member the member
	 * @param alias the alias
	 * @return the number of matching item hierarchy def masters
	 */
	@Override
	public int countByItemHierarchyDefUnique(String member, String alias) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE;

		Object[] finderArgs = new Object[] { member, alias };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE);

			boolean bindMember = false;

			if (member == null) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1);
			}
			else if (member.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3);
			}
			else {
				bindMember = true;

				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2);
			}

			boolean bindAlias = false;

			if (alias == null) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1);
			}
			else if (alias.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3);
			}
			else {
				bindAlias = true;

				query.append(_FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMember) {
					qPos.add(member);
				}

				if (bindAlias) {
					qPos.add(alias);
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

	private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_1 = "itemHierarchyDefMaster.member IS NULL AND ";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_2 = "itemHierarchyDefMaster.member = ? AND ";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_MEMBER_3 = "(itemHierarchyDefMaster.member IS NULL OR itemHierarchyDefMaster.member = '') AND ";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_1 = "itemHierarchyDefMaster.alias IS NULL";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_2 = "itemHierarchyDefMaster.alias = ?";
	private static final String _FINDER_COLUMN_ITEMHIERARCHYDEFUNIQUE_ALIAS_3 = "(itemHierarchyDefMaster.alias IS NULL OR itemHierarchyDefMaster.alias = '')";

	public ItemHierarchyDefMasterPersistenceImpl() {
		setModelClass(ItemHierarchyDefMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemHierarchyDefMasterSid",
				"ITEM_HIERARCHY_DEF_MASTER_SID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("alias", "ALIAS");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("bpiLvl", "BPI_LVL");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("member", "MEMBER");
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
	 * Caches the item hierarchy def master in the entity cache if it is enabled.
	 *
	 * @param itemHierarchyDefMaster the item hierarchy def master
	 */
	@Override
	public void cacheResult(ItemHierarchyDefMaster itemHierarchyDefMaster) {
		entityCache.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			itemHierarchyDefMaster.getPrimaryKey(), itemHierarchyDefMaster);

		itemHierarchyDefMaster.resetOriginalValues();
	}

	/**
	 * Caches the item hierarchy def masters in the entity cache if it is enabled.
	 *
	 * @param itemHierarchyDefMasters the item hierarchy def masters
	 */
	@Override
	public void cacheResult(
		List<ItemHierarchyDefMaster> itemHierarchyDefMasters) {
		for (ItemHierarchyDefMaster itemHierarchyDefMaster : itemHierarchyDefMasters) {
			if (entityCache.getResult(
						ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
						ItemHierarchyDefMasterImpl.class,
						itemHierarchyDefMaster.getPrimaryKey()) == null) {
				cacheResult(itemHierarchyDefMaster);
			}
			else {
				itemHierarchyDefMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item hierarchy def masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemHierarchyDefMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item hierarchy def master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemHierarchyDefMaster itemHierarchyDefMaster) {
		entityCache.removeResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			itemHierarchyDefMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ItemHierarchyDefMaster> itemHierarchyDefMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemHierarchyDefMaster itemHierarchyDefMaster : itemHierarchyDefMasters) {
			entityCache.removeResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
				ItemHierarchyDefMasterImpl.class,
				itemHierarchyDefMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
	 *
	 * @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
	 * @return the new item hierarchy def master
	 */
	@Override
	public ItemHierarchyDefMaster create(int itemHierarchyDefMasterSid) {
		ItemHierarchyDefMaster itemHierarchyDefMaster = new ItemHierarchyDefMasterImpl();

		itemHierarchyDefMaster.setNew(true);
		itemHierarchyDefMaster.setPrimaryKey(itemHierarchyDefMasterSid);

		return itemHierarchyDefMaster;
	}

	/**
	 * Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	 * @return the item hierarchy def master that was removed
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster remove(int itemHierarchyDefMasterSid)
		throws NoSuchItemHierarchyDefMasterException {
		return remove((Serializable)itemHierarchyDefMasterSid);
	}

	/**
	 * Removes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item hierarchy def master
	 * @return the item hierarchy def master that was removed
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster remove(Serializable primaryKey)
		throws NoSuchItemHierarchyDefMasterException {
		Session session = null;

		try {
			session = openSession();

			ItemHierarchyDefMaster itemHierarchyDefMaster = (ItemHierarchyDefMaster)session.get(ItemHierarchyDefMasterImpl.class,
					primaryKey);

			if (itemHierarchyDefMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemHierarchyDefMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemHierarchyDefMaster);
		}
		catch (NoSuchItemHierarchyDefMasterException nsee) {
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
	protected ItemHierarchyDefMaster removeImpl(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		itemHierarchyDefMaster = toUnwrappedModel(itemHierarchyDefMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemHierarchyDefMaster)) {
				itemHierarchyDefMaster = (ItemHierarchyDefMaster)session.get(ItemHierarchyDefMasterImpl.class,
						itemHierarchyDefMaster.getPrimaryKeyObj());
			}

			if (itemHierarchyDefMaster != null) {
				session.delete(itemHierarchyDefMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemHierarchyDefMaster != null) {
			clearCache(itemHierarchyDefMaster);
		}

		return itemHierarchyDefMaster;
	}

	@Override
	public ItemHierarchyDefMaster updateImpl(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		itemHierarchyDefMaster = toUnwrappedModel(itemHierarchyDefMaster);

		boolean isNew = itemHierarchyDefMaster.isNew();

		ItemHierarchyDefMasterModelImpl itemHierarchyDefMasterModelImpl = (ItemHierarchyDefMasterModelImpl)itemHierarchyDefMaster;

		Session session = null;

		try {
			session = openSession();

			if (itemHierarchyDefMaster.isNew()) {
				session.save(itemHierarchyDefMaster);

				itemHierarchyDefMaster.setNew(false);
			}
			else {
				itemHierarchyDefMaster = (ItemHierarchyDefMaster)session.merge(itemHierarchyDefMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemHierarchyDefMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					itemHierarchyDefMasterModelImpl.getMember()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MEMBER, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER,
				args);

			args = new Object[] { itemHierarchyDefMasterModelImpl.getAlias() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ALIAS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS,
				args);

			args = new Object[] { itemHierarchyDefMasterModelImpl.getBpiLvl() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BPILVL, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL,
				args);

			args = new Object[] {
					itemHierarchyDefMasterModelImpl.getMember(),
					itemHierarchyDefMasterModelImpl.getAlias()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyDefMasterModelImpl.getOriginalMember()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MEMBER, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER,
					args);

				args = new Object[] { itemHierarchyDefMasterModelImpl.getMember() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MEMBER, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEMBER,
					args);
			}

			if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyDefMasterModelImpl.getOriginalAlias()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ALIAS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS,
					args);

				args = new Object[] { itemHierarchyDefMasterModelImpl.getAlias() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ALIAS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALIAS,
					args);
			}

			if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyDefMasterModelImpl.getOriginalBpiLvl()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BPILVL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL,
					args);

				args = new Object[] { itemHierarchyDefMasterModelImpl.getBpiLvl() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BPILVL, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BPILVL,
					args);
			}

			if ((itemHierarchyDefMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemHierarchyDefMasterModelImpl.getOriginalMember(),
						itemHierarchyDefMasterModelImpl.getOriginalAlias()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE,
					args);

				args = new Object[] {
						itemHierarchyDefMasterModelImpl.getMember(),
						itemHierarchyDefMasterModelImpl.getAlias()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMHIERARCHYDEFUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMHIERARCHYDEFUNIQUE,
					args);
			}
		}

		entityCache.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
			ItemHierarchyDefMasterImpl.class,
			itemHierarchyDefMaster.getPrimaryKey(), itemHierarchyDefMaster,
			false);

		itemHierarchyDefMaster.resetOriginalValues();

		return itemHierarchyDefMaster;
	}

	protected ItemHierarchyDefMaster toUnwrappedModel(
		ItemHierarchyDefMaster itemHierarchyDefMaster) {
		if (itemHierarchyDefMaster instanceof ItemHierarchyDefMasterImpl) {
			return itemHierarchyDefMaster;
		}

		ItemHierarchyDefMasterImpl itemHierarchyDefMasterImpl = new ItemHierarchyDefMasterImpl();

		itemHierarchyDefMasterImpl.setNew(itemHierarchyDefMaster.isNew());
		itemHierarchyDefMasterImpl.setPrimaryKey(itemHierarchyDefMaster.getPrimaryKey());

		itemHierarchyDefMasterImpl.setItemHierarchyDefMasterSid(itemHierarchyDefMaster.getItemHierarchyDefMasterSid());
		itemHierarchyDefMasterImpl.setCreatedBy(itemHierarchyDefMaster.getCreatedBy());
		itemHierarchyDefMasterImpl.setRecordLockStatus(itemHierarchyDefMaster.isRecordLockStatus());
		itemHierarchyDefMasterImpl.setModifiedBy(itemHierarchyDefMaster.getModifiedBy());
		itemHierarchyDefMasterImpl.setCreatedDate(itemHierarchyDefMaster.getCreatedDate());
		itemHierarchyDefMasterImpl.setAlias(itemHierarchyDefMaster.getAlias());
		itemHierarchyDefMasterImpl.setSource(itemHierarchyDefMaster.getSource());
		itemHierarchyDefMasterImpl.setBatchId(itemHierarchyDefMaster.getBatchId());
		itemHierarchyDefMasterImpl.setBpiLvl(itemHierarchyDefMaster.getBpiLvl());
		itemHierarchyDefMasterImpl.setModifiedDate(itemHierarchyDefMaster.getModifiedDate());
		itemHierarchyDefMasterImpl.setMember(itemHierarchyDefMaster.getMember());
		itemHierarchyDefMasterImpl.setInboundStatus(itemHierarchyDefMaster.getInboundStatus());

		return itemHierarchyDefMasterImpl;
	}

	/**
	 * Returns the item hierarchy def master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item hierarchy def master
	 * @return the item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemHierarchyDefMasterException {
		ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByPrimaryKey(primaryKey);

		if (itemHierarchyDefMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemHierarchyDefMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemHierarchyDefMaster;
	}

	/**
	 * Returns the item hierarchy def master with the primary key or throws a {@link NoSuchItemHierarchyDefMasterException} if it could not be found.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	 * @return the item hierarchy def master
	 * @throws NoSuchItemHierarchyDefMasterException if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster findByPrimaryKey(
		int itemHierarchyDefMasterSid)
		throws NoSuchItemHierarchyDefMasterException {
		return findByPrimaryKey((Serializable)itemHierarchyDefMasterSid);
	}

	/**
	 * Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item hierarchy def master
	 * @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
				ItemHierarchyDefMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemHierarchyDefMaster itemHierarchyDefMaster = (ItemHierarchyDefMaster)serializable;

		if (itemHierarchyDefMaster == null) {
			Session session = null;

			try {
				session = openSession();

				itemHierarchyDefMaster = (ItemHierarchyDefMaster)session.get(ItemHierarchyDefMasterImpl.class,
						primaryKey);

				if (itemHierarchyDefMaster != null) {
					cacheResult(itemHierarchyDefMaster);
				}
				else {
					entityCache.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
						ItemHierarchyDefMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemHierarchyDefMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemHierarchyDefMaster;
	}

	/**
	 * Returns the item hierarchy def master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
	 * @return the item hierarchy def master, or <code>null</code> if a item hierarchy def master with the primary key could not be found
	 */
	@Override
	public ItemHierarchyDefMaster fetchByPrimaryKey(
		int itemHierarchyDefMasterSid) {
		return fetchByPrimaryKey((Serializable)itemHierarchyDefMasterSid);
	}

	@Override
	public Map<Serializable, ItemHierarchyDefMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemHierarchyDefMaster> map = new HashMap<Serializable, ItemHierarchyDefMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemHierarchyDefMaster itemHierarchyDefMaster = fetchByPrimaryKey(primaryKey);

			if (itemHierarchyDefMaster != null) {
				map.put(primaryKey, itemHierarchyDefMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemHierarchyDefMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemHierarchyDefMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE_PKS_IN);

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

			for (ItemHierarchyDefMaster itemHierarchyDefMaster : (List<ItemHierarchyDefMaster>)q.list()) {
				map.put(itemHierarchyDefMaster.getPrimaryKeyObj(),
					itemHierarchyDefMaster);

				cacheResult(itemHierarchyDefMaster);

				uncachedPrimaryKeys.remove(itemHierarchyDefMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemHierarchyDefMasterModelImpl.ENTITY_CACHE_ENABLED,
					ItemHierarchyDefMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the item hierarchy def masters.
	 *
	 * @return the item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item hierarchy def masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @return the range of item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item hierarchy def masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item hierarchy def masters
	 * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item hierarchy def masters
	 */
	@Override
	public List<ItemHierarchyDefMaster> findAll(int start, int end,
		OrderByComparator<ItemHierarchyDefMaster> orderByComparator,
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

		List<ItemHierarchyDefMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ItemHierarchyDefMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMHIERARCHYDEFMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMHIERARCHYDEFMASTER;

				if (pagination) {
					sql = sql.concat(ItemHierarchyDefMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemHierarchyDefMaster>)QueryUtil.list(q,
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
	 * Removes all the item hierarchy def masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemHierarchyDefMaster itemHierarchyDefMaster : findAll()) {
			remove(itemHierarchyDefMaster);
		}
	}

	/**
	 * Returns the number of item hierarchy def masters.
	 *
	 * @return the number of item hierarchy def masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMHIERARCHYDEFMASTER);

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
		return ItemHierarchyDefMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item hierarchy def master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemHierarchyDefMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMHIERARCHYDEFMASTER = "SELECT itemHierarchyDefMaster FROM ItemHierarchyDefMaster itemHierarchyDefMaster";
	private static final String _SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE_PKS_IN = "SELECT itemHierarchyDefMaster FROM ItemHierarchyDefMaster itemHierarchyDefMaster WHERE ITEM_HIERARCHY_DEF_MASTER_SID IN (";
	private static final String _SQL_SELECT_ITEMHIERARCHYDEFMASTER_WHERE = "SELECT itemHierarchyDefMaster FROM ItemHierarchyDefMaster itemHierarchyDefMaster WHERE ";
	private static final String _SQL_COUNT_ITEMHIERARCHYDEFMASTER = "SELECT COUNT(itemHierarchyDefMaster) FROM ItemHierarchyDefMaster itemHierarchyDefMaster";
	private static final String _SQL_COUNT_ITEMHIERARCHYDEFMASTER_WHERE = "SELECT COUNT(itemHierarchyDefMaster) FROM ItemHierarchyDefMaster itemHierarchyDefMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemHierarchyDefMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemHierarchyDefMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemHierarchyDefMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemHierarchyDefMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemHierarchyDefMasterSid", "createdBy", "recordLockStatus",
				"modifiedBy", "createdDate", "alias", "source", "batchId",
				"bpiLvl", "modifiedDate", "member", "inboundStatus"
			});
}