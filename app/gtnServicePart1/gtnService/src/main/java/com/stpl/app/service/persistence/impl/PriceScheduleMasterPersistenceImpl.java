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

import com.stpl.app.exception.NoSuchPriceScheduleMasterException;
import com.stpl.app.model.PriceScheduleMaster;
import com.stpl.app.model.impl.PriceScheduleMasterImpl;
import com.stpl.app.model.impl.PriceScheduleMasterModelImpl;
import com.stpl.app.service.persistence.PriceScheduleMasterPersistence;

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
 * The persistence implementation for the price schedule master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PriceScheduleMasterPersistence
 * @see com.stpl.app.service.persistence.PriceScheduleMasterUtil
 * @generated
 */
@ProviderType
public class PriceScheduleMasterPersistenceImpl extends BasePersistenceImpl<PriceScheduleMaster>
	implements PriceScheduleMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PriceScheduleMasterUtil} to access the price schedule master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PriceScheduleMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEID =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPriceScheduleId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEID =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPriceScheduleId",
			new String[] { String.class.getName() },
			PriceScheduleMasterModelImpl.PRICESCHEDULEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULEID = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleId", new String[] { String.class.getName() });

	/**
	 * Returns all the price schedule masters where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleId the price schedule ID
	 * @return the matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleId(
		String priceScheduleId) {
		return findByPriceScheduleId(priceScheduleId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule masters where priceScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @return the range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleId(
		String priceScheduleId, int start, int end) {
		return findByPriceScheduleId(priceScheduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleId(
		String priceScheduleId, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return findByPriceScheduleId(priceScheduleId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleId(
		String priceScheduleId, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEID;
			finderArgs = new Object[] { priceScheduleId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEID;
			finderArgs = new Object[] {
					priceScheduleId,
					
					start, end, orderByComparator
				};
		}

		List<PriceScheduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PriceScheduleMaster priceScheduleMaster : list) {
					if (!Objects.equals(priceScheduleId,
								priceScheduleMaster.getPriceScheduleId())) {
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

			query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleId = false;

			if (priceScheduleId == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_1);
			}
			else if (priceScheduleId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_3);
			}
			else {
				bindPriceScheduleId = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleId) {
					qPos.add(priceScheduleId);
				}

				if (!pagination) {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
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
	 * Returns the first price schedule master in the ordered set where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleId_First(
		String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleId_First(priceScheduleId,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleId=");
		msg.append(priceScheduleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the first price schedule master in the ordered set where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleId_First(
		String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		List<PriceScheduleMaster> list = findByPriceScheduleId(priceScheduleId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleId_Last(
		String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleId_Last(priceScheduleId,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleId=");
		msg.append(priceScheduleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleId the price schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleId_Last(
		String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		int count = countByPriceScheduleId(priceScheduleId);

		if (count == 0) {
			return null;
		}

		List<PriceScheduleMaster> list = findByPriceScheduleId(priceScheduleId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleSystemId the primary key of the current price schedule master
	 * @param priceScheduleId the price schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster[] findByPriceScheduleId_PrevAndNext(
		int priceScheduleSystemId, String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = findByPrimaryKey(priceScheduleSystemId);

		Session session = null;

		try {
			session = openSession();

			PriceScheduleMaster[] array = new PriceScheduleMasterImpl[3];

			array[0] = getByPriceScheduleId_PrevAndNext(session,
					priceScheduleMaster, priceScheduleId, orderByComparator,
					true);

			array[1] = priceScheduleMaster;

			array[2] = getByPriceScheduleId_PrevAndNext(session,
					priceScheduleMaster, priceScheduleId, orderByComparator,
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

	protected PriceScheduleMaster getByPriceScheduleId_PrevAndNext(
		Session session, PriceScheduleMaster priceScheduleMaster,
		String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

		boolean bindPriceScheduleId = false;

		if (priceScheduleId == null) {
			query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_1);
		}
		else if (priceScheduleId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_3);
		}
		else {
			bindPriceScheduleId = true;

			query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_2);
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
			query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPriceScheduleId) {
			qPos.add(priceScheduleId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(priceScheduleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PriceScheduleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the price schedule masters where priceScheduleId = &#63; from the database.
	 *
	 * @param priceScheduleId the price schedule ID
	 */
	@Override
	public void removeByPriceScheduleId(String priceScheduleId) {
		for (PriceScheduleMaster priceScheduleMaster : findByPriceScheduleId(
				priceScheduleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(priceScheduleMaster);
		}
	}

	/**
	 * Returns the number of price schedule masters where priceScheduleId = &#63;.
	 *
	 * @param priceScheduleId the price schedule ID
	 * @return the number of matching price schedule masters
	 */
	@Override
	public int countByPriceScheduleId(String priceScheduleId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULEID;

		Object[] finderArgs = new Object[] { priceScheduleId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleId = false;

			if (priceScheduleId == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_1);
			}
			else if (priceScheduleId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_3);
			}
			else {
				bindPriceScheduleId = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleId) {
					qPos.add(priceScheduleId);
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

	private static final String _FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_1 =
		"priceScheduleMaster.priceScheduleId IS NULL";
	private static final String _FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_2 =
		"priceScheduleMaster.priceScheduleId = ?";
	private static final String _FINDER_COLUMN_PRICESCHEDULEID_PRICESCHEDULEID_3 =
		"(priceScheduleMaster.priceScheduleId IS NULL OR priceScheduleMaster.priceScheduleId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULENO =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPriceScheduleNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENO =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPriceScheduleNo",
			new String[] { String.class.getName() },
			PriceScheduleMasterModelImpl.PRICESCHEDULENO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULENO = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleNo", new String[] { String.class.getName() });

	/**
	 * Returns all the price schedule masters where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleNo the price schedule no
	 * @return the matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleNo(
		String priceScheduleNo) {
		return findByPriceScheduleNo(priceScheduleNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule masters where priceScheduleNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @return the range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleNo(
		String priceScheduleNo, int start, int end) {
		return findByPriceScheduleNo(priceScheduleNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleNo(
		String priceScheduleNo, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return findByPriceScheduleNo(priceScheduleNo, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleNo(
		String priceScheduleNo, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENO;
			finderArgs = new Object[] { priceScheduleNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULENO;
			finderArgs = new Object[] {
					priceScheduleNo,
					
					start, end, orderByComparator
				};
		}

		List<PriceScheduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PriceScheduleMaster priceScheduleMaster : list) {
					if (!Objects.equals(priceScheduleNo,
								priceScheduleMaster.getPriceScheduleNo())) {
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

			query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleNo = false;

			if (priceScheduleNo == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_1);
			}
			else if (priceScheduleNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_3);
			}
			else {
				bindPriceScheduleNo = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleNo) {
					qPos.add(priceScheduleNo);
				}

				if (!pagination) {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
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
	 * Returns the first price schedule master in the ordered set where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleNo_First(
		String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleNo_First(priceScheduleNo,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleNo=");
		msg.append(priceScheduleNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the first price schedule master in the ordered set where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleNo_First(
		String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		List<PriceScheduleMaster> list = findByPriceScheduleNo(priceScheduleNo,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleNo_Last(
		String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleNo_Last(priceScheduleNo,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleNo=");
		msg.append(priceScheduleNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleNo the price schedule no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleNo_Last(
		String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		int count = countByPriceScheduleNo(priceScheduleNo);

		if (count == 0) {
			return null;
		}

		List<PriceScheduleMaster> list = findByPriceScheduleNo(priceScheduleNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleSystemId the primary key of the current price schedule master
	 * @param priceScheduleNo the price schedule no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster[] findByPriceScheduleNo_PrevAndNext(
		int priceScheduleSystemId, String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = findByPrimaryKey(priceScheduleSystemId);

		Session session = null;

		try {
			session = openSession();

			PriceScheduleMaster[] array = new PriceScheduleMasterImpl[3];

			array[0] = getByPriceScheduleNo_PrevAndNext(session,
					priceScheduleMaster, priceScheduleNo, orderByComparator,
					true);

			array[1] = priceScheduleMaster;

			array[2] = getByPriceScheduleNo_PrevAndNext(session,
					priceScheduleMaster, priceScheduleNo, orderByComparator,
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

	protected PriceScheduleMaster getByPriceScheduleNo_PrevAndNext(
		Session session, PriceScheduleMaster priceScheduleMaster,
		String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

		boolean bindPriceScheduleNo = false;

		if (priceScheduleNo == null) {
			query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_1);
		}
		else if (priceScheduleNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_3);
		}
		else {
			bindPriceScheduleNo = true;

			query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_2);
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
			query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPriceScheduleNo) {
			qPos.add(priceScheduleNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(priceScheduleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PriceScheduleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the price schedule masters where priceScheduleNo = &#63; from the database.
	 *
	 * @param priceScheduleNo the price schedule no
	 */
	@Override
	public void removeByPriceScheduleNo(String priceScheduleNo) {
		for (PriceScheduleMaster priceScheduleMaster : findByPriceScheduleNo(
				priceScheduleNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(priceScheduleMaster);
		}
	}

	/**
	 * Returns the number of price schedule masters where priceScheduleNo = &#63;.
	 *
	 * @param priceScheduleNo the price schedule no
	 * @return the number of matching price schedule masters
	 */
	@Override
	public int countByPriceScheduleNo(String priceScheduleNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULENO;

		Object[] finderArgs = new Object[] { priceScheduleNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleNo = false;

			if (priceScheduleNo == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_1);
			}
			else if (priceScheduleNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_3);
			}
			else {
				bindPriceScheduleNo = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleNo) {
					qPos.add(priceScheduleNo);
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

	private static final String _FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_1 =
		"priceScheduleMaster.priceScheduleNo IS NULL";
	private static final String _FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_2 =
		"priceScheduleMaster.priceScheduleNo = ?";
	private static final String _FINDER_COLUMN_PRICESCHEDULENO_PRICESCHEDULENO_3 =
		"(priceScheduleMaster.priceScheduleNo IS NULL OR priceScheduleMaster.priceScheduleNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULENAME =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPriceScheduleName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENAME =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPriceScheduleName", new String[] { String.class.getName() },
			PriceScheduleMasterModelImpl.PRICESCHEDULENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULENAME = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleName", new String[] { String.class.getName() });

	/**
	 * Returns all the price schedule masters where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleName the price schedule name
	 * @return the matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleName(
		String priceScheduleName) {
		return findByPriceScheduleName(priceScheduleName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule masters where priceScheduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleName the price schedule name
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @return the range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleName(
		String priceScheduleName, int start, int end) {
		return findByPriceScheduleName(priceScheduleName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleName the price schedule name
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleName(
		String priceScheduleName, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return findByPriceScheduleName(priceScheduleName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleName the price schedule name
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleName(
		String priceScheduleName, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENAME;
			finderArgs = new Object[] { priceScheduleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULENAME;
			finderArgs = new Object[] {
					priceScheduleName,
					
					start, end, orderByComparator
				};
		}

		List<PriceScheduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PriceScheduleMaster priceScheduleMaster : list) {
					if (!Objects.equals(priceScheduleName,
								priceScheduleMaster.getPriceScheduleName())) {
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

			query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleName = false;

			if (priceScheduleName == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_1);
			}
			else if (priceScheduleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_3);
			}
			else {
				bindPriceScheduleName = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleName) {
					qPos.add(priceScheduleName);
				}

				if (!pagination) {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
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
	 * Returns the first price schedule master in the ordered set where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleName the price schedule name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleName_First(
		String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleName_First(priceScheduleName,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleName=");
		msg.append(priceScheduleName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the first price schedule master in the ordered set where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleName the price schedule name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleName_First(
		String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		List<PriceScheduleMaster> list = findByPriceScheduleName(priceScheduleName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleName the price schedule name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleName_Last(
		String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleName_Last(priceScheduleName,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleName=");
		msg.append(priceScheduleName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleName the price schedule name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleName_Last(
		String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		int count = countByPriceScheduleName(priceScheduleName);

		if (count == 0) {
			return null;
		}

		List<PriceScheduleMaster> list = findByPriceScheduleName(priceScheduleName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleSystemId the primary key of the current price schedule master
	 * @param priceScheduleName the price schedule name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster[] findByPriceScheduleName_PrevAndNext(
		int priceScheduleSystemId, String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = findByPrimaryKey(priceScheduleSystemId);

		Session session = null;

		try {
			session = openSession();

			PriceScheduleMaster[] array = new PriceScheduleMasterImpl[3];

			array[0] = getByPriceScheduleName_PrevAndNext(session,
					priceScheduleMaster, priceScheduleName, orderByComparator,
					true);

			array[1] = priceScheduleMaster;

			array[2] = getByPriceScheduleName_PrevAndNext(session,
					priceScheduleMaster, priceScheduleName, orderByComparator,
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

	protected PriceScheduleMaster getByPriceScheduleName_PrevAndNext(
		Session session, PriceScheduleMaster priceScheduleMaster,
		String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

		boolean bindPriceScheduleName = false;

		if (priceScheduleName == null) {
			query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_1);
		}
		else if (priceScheduleName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_3);
		}
		else {
			bindPriceScheduleName = true;

			query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_2);
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
			query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPriceScheduleName) {
			qPos.add(priceScheduleName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(priceScheduleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PriceScheduleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the price schedule masters where priceScheduleName = &#63; from the database.
	 *
	 * @param priceScheduleName the price schedule name
	 */
	@Override
	public void removeByPriceScheduleName(String priceScheduleName) {
		for (PriceScheduleMaster priceScheduleMaster : findByPriceScheduleName(
				priceScheduleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(priceScheduleMaster);
		}
	}

	/**
	 * Returns the number of price schedule masters where priceScheduleName = &#63;.
	 *
	 * @param priceScheduleName the price schedule name
	 * @return the number of matching price schedule masters
	 */
	@Override
	public int countByPriceScheduleName(String priceScheduleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULENAME;

		Object[] finderArgs = new Object[] { priceScheduleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleName = false;

			if (priceScheduleName == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_1);
			}
			else if (priceScheduleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_3);
			}
			else {
				bindPriceScheduleName = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleName) {
					qPos.add(priceScheduleName);
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

	private static final String _FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_1 =
		"priceScheduleMaster.priceScheduleName IS NULL";
	private static final String _FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_2 =
		"priceScheduleMaster.priceScheduleName = ?";
	private static final String _FINDER_COLUMN_PRICESCHEDULENAME_PRICESCHEDULENAME_3 =
		"(priceScheduleMaster.priceScheduleName IS NULL OR priceScheduleMaster.priceScheduleName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULETYPE =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPriceScheduleType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULETYPE =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPriceScheduleType", new String[] { String.class.getName() },
			PriceScheduleMasterModelImpl.PRICESCHEDULETYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULETYPE = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleType", new String[] { String.class.getName() });

	/**
	 * Returns all the price schedule masters where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleType the price schedule type
	 * @return the matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleType(
		String priceScheduleType) {
		return findByPriceScheduleType(priceScheduleType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule masters where priceScheduleType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleType the price schedule type
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @return the range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleType(
		String priceScheduleType, int start, int end) {
		return findByPriceScheduleType(priceScheduleType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleType the price schedule type
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleType(
		String priceScheduleType, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return findByPriceScheduleType(priceScheduleType, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleType the price schedule type
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleType(
		String priceScheduleType, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULETYPE;
			finderArgs = new Object[] { priceScheduleType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULETYPE;
			finderArgs = new Object[] {
					priceScheduleType,
					
					start, end, orderByComparator
				};
		}

		List<PriceScheduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PriceScheduleMaster priceScheduleMaster : list) {
					if (!Objects.equals(priceScheduleType,
								priceScheduleMaster.getPriceScheduleType())) {
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

			query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleType = false;

			if (priceScheduleType == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_1);
			}
			else if (priceScheduleType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_3);
			}
			else {
				bindPriceScheduleType = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleType) {
					qPos.add(priceScheduleType);
				}

				if (!pagination) {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
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
	 * Returns the first price schedule master in the ordered set where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleType the price schedule type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleType_First(
		String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleType_First(priceScheduleType,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleType=");
		msg.append(priceScheduleType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the first price schedule master in the ordered set where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleType the price schedule type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleType_First(
		String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		List<PriceScheduleMaster> list = findByPriceScheduleType(priceScheduleType,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleType the price schedule type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleType_Last(
		String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleType_Last(priceScheduleType,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleType=");
		msg.append(priceScheduleType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleType the price schedule type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleType_Last(
		String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		int count = countByPriceScheduleType(priceScheduleType);

		if (count == 0) {
			return null;
		}

		List<PriceScheduleMaster> list = findByPriceScheduleType(priceScheduleType,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleSystemId the primary key of the current price schedule master
	 * @param priceScheduleType the price schedule type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster[] findByPriceScheduleType_PrevAndNext(
		int priceScheduleSystemId, String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = findByPrimaryKey(priceScheduleSystemId);

		Session session = null;

		try {
			session = openSession();

			PriceScheduleMaster[] array = new PriceScheduleMasterImpl[3];

			array[0] = getByPriceScheduleType_PrevAndNext(session,
					priceScheduleMaster, priceScheduleType, orderByComparator,
					true);

			array[1] = priceScheduleMaster;

			array[2] = getByPriceScheduleType_PrevAndNext(session,
					priceScheduleMaster, priceScheduleType, orderByComparator,
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

	protected PriceScheduleMaster getByPriceScheduleType_PrevAndNext(
		Session session, PriceScheduleMaster priceScheduleMaster,
		String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

		boolean bindPriceScheduleType = false;

		if (priceScheduleType == null) {
			query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_1);
		}
		else if (priceScheduleType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_3);
		}
		else {
			bindPriceScheduleType = true;

			query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_2);
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
			query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPriceScheduleType) {
			qPos.add(priceScheduleType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(priceScheduleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PriceScheduleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the price schedule masters where priceScheduleType = &#63; from the database.
	 *
	 * @param priceScheduleType the price schedule type
	 */
	@Override
	public void removeByPriceScheduleType(String priceScheduleType) {
		for (PriceScheduleMaster priceScheduleMaster : findByPriceScheduleType(
				priceScheduleType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(priceScheduleMaster);
		}
	}

	/**
	 * Returns the number of price schedule masters where priceScheduleType = &#63;.
	 *
	 * @param priceScheduleType the price schedule type
	 * @return the number of matching price schedule masters
	 */
	@Override
	public int countByPriceScheduleType(String priceScheduleType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULETYPE;

		Object[] finderArgs = new Object[] { priceScheduleType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleType = false;

			if (priceScheduleType == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_1);
			}
			else if (priceScheduleType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_3);
			}
			else {
				bindPriceScheduleType = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleType) {
					qPos.add(priceScheduleType);
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

	private static final String _FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_1 =
		"priceScheduleMaster.priceScheduleType IS NULL";
	private static final String _FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_2 =
		"priceScheduleMaster.priceScheduleType = ?";
	private static final String _FINDER_COLUMN_PRICESCHEDULETYPE_PRICESCHEDULETYPE_3 =
		"(priceScheduleMaster.priceScheduleType IS NULL OR priceScheduleMaster.priceScheduleType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULESTATUS =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPriceScheduleStatus",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULESTATUS =
		new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPriceScheduleStatus",
			new String[] { String.class.getName() },
			PriceScheduleMasterModelImpl.PRICESCHEDULESTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULESTATUS = new FinderPath(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleStatus",
			new String[] { String.class.getName() });

	/**
	 * Returns all the price schedule masters where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @return the matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleStatus(
		String priceScheduleStatus) {
		return findByPriceScheduleStatus(priceScheduleStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule masters where priceScheduleStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @return the range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleStatus(
		String priceScheduleStatus, int start, int end) {
		return findByPriceScheduleStatus(priceScheduleStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleStatus(
		String priceScheduleStatus, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return findByPriceScheduleStatus(priceScheduleStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule masters where priceScheduleStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findByPriceScheduleStatus(
		String priceScheduleStatus, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULESTATUS;
			finderArgs = new Object[] { priceScheduleStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULESTATUS;
			finderArgs = new Object[] {
					priceScheduleStatus,
					
					start, end, orderByComparator
				};
		}

		List<PriceScheduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PriceScheduleMaster priceScheduleMaster : list) {
					if (!Objects.equals(priceScheduleStatus,
								priceScheduleMaster.getPriceScheduleStatus())) {
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

			query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleStatus = false;

			if (priceScheduleStatus == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_1);
			}
			else if (priceScheduleStatus.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_3);
			}
			else {
				bindPriceScheduleStatus = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleStatus) {
					qPos.add(priceScheduleStatus);
				}

				if (!pagination) {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
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
	 * Returns the first price schedule master in the ordered set where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleStatus_First(
		String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleStatus_First(priceScheduleStatus,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleStatus=");
		msg.append(priceScheduleStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the first price schedule master in the ordered set where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleStatus_First(
		String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		List<PriceScheduleMaster> list = findByPriceScheduleStatus(priceScheduleStatus,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster findByPriceScheduleStatus_Last(
		String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPriceScheduleStatus_Last(priceScheduleStatus,
				orderByComparator);

		if (priceScheduleMaster != null) {
			return priceScheduleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleStatus=");
		msg.append(priceScheduleStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleMasterException(msg.toString());
	}

	/**
	 * Returns the last price schedule master in the ordered set where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPriceScheduleStatus_Last(
		String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		int count = countByPriceScheduleStatus(priceScheduleStatus);

		if (count == 0) {
			return null;
		}

		List<PriceScheduleMaster> list = findByPriceScheduleStatus(priceScheduleStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleSystemId the primary key of the current price schedule master
	 * @param priceScheduleStatus the price schedule status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster[] findByPriceScheduleStatus_PrevAndNext(
		int priceScheduleSystemId, String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = findByPrimaryKey(priceScheduleSystemId);

		Session session = null;

		try {
			session = openSession();

			PriceScheduleMaster[] array = new PriceScheduleMasterImpl[3];

			array[0] = getByPriceScheduleStatus_PrevAndNext(session,
					priceScheduleMaster, priceScheduleStatus,
					orderByComparator, true);

			array[1] = priceScheduleMaster;

			array[2] = getByPriceScheduleStatus_PrevAndNext(session,
					priceScheduleMaster, priceScheduleStatus,
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

	protected PriceScheduleMaster getByPriceScheduleStatus_PrevAndNext(
		Session session, PriceScheduleMaster priceScheduleMaster,
		String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE);

		boolean bindPriceScheduleStatus = false;

		if (priceScheduleStatus == null) {
			query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_1);
		}
		else if (priceScheduleStatus.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_3);
		}
		else {
			bindPriceScheduleStatus = true;

			query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_2);
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
			query.append(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPriceScheduleStatus) {
			qPos.add(priceScheduleStatus);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(priceScheduleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PriceScheduleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the price schedule masters where priceScheduleStatus = &#63; from the database.
	 *
	 * @param priceScheduleStatus the price schedule status
	 */
	@Override
	public void removeByPriceScheduleStatus(String priceScheduleStatus) {
		for (PriceScheduleMaster priceScheduleMaster : findByPriceScheduleStatus(
				priceScheduleStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(priceScheduleMaster);
		}
	}

	/**
	 * Returns the number of price schedule masters where priceScheduleStatus = &#63;.
	 *
	 * @param priceScheduleStatus the price schedule status
	 * @return the number of matching price schedule masters
	 */
	@Override
	public int countByPriceScheduleStatus(String priceScheduleStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULESTATUS;

		Object[] finderArgs = new Object[] { priceScheduleStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRICESCHEDULEMASTER_WHERE);

			boolean bindPriceScheduleStatus = false;

			if (priceScheduleStatus == null) {
				query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_1);
			}
			else if (priceScheduleStatus.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_3);
			}
			else {
				bindPriceScheduleStatus = true;

				query.append(_FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPriceScheduleStatus) {
					qPos.add(priceScheduleStatus);
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

	private static final String _FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_1 =
		"priceScheduleMaster.priceScheduleStatus IS NULL";
	private static final String _FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_2 =
		"priceScheduleMaster.priceScheduleStatus = ?";
	private static final String _FINDER_COLUMN_PRICESCHEDULESTATUS_PRICESCHEDULESTATUS_3 =
		"(priceScheduleMaster.priceScheduleStatus IS NULL OR priceScheduleMaster.priceScheduleStatus = '')";

	public PriceScheduleMasterPersistenceImpl() {
		setModelClass(PriceScheduleMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("parentPriceScheduleName",
				"PARENT_PRICE_SCHEDULE_NAME");
			dbColumnNames.put("parentPriceScheduleId",
				"PARENT_PRICE_SCHEDULE_ID");
			dbColumnNames.put("priceScheduleStartDate",
				"PRICE_SCHEDULE_START_DATE");
			dbColumnNames.put("priceScheduleNo", "PRICE_SCHEDULE_NO");
			dbColumnNames.put("priceScheduleName", "PRICE_SCHEDULE_NAME");
			dbColumnNames.put("priceScheduleId", "PRICE_SCHEDULE_ID");
			dbColumnNames.put("priceScheduleType", "PRICE_SCHEDULE_TYPE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("priceScheduleSystemId",
				"PRICE_SCHEDULE_SYSTEM_ID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("priceScheduleDesignation",
				"PRICE_SCHEDULE_DESIGNATION");
			dbColumnNames.put("priceScheduleEndDate", "PRICE_SCHEDULE_END_DATE");
			dbColumnNames.put("priceScheduleStatus", "PRICE_SCHEDULE_STATUS");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("priceScheduleCategory", "PRICE_SCHEDULE_CATEGORY");
			dbColumnNames.put("tradeClass", "TRADE_CLASS");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the price schedule master in the entity cache if it is enabled.
	 *
	 * @param priceScheduleMaster the price schedule master
	 */
	@Override
	public void cacheResult(PriceScheduleMaster priceScheduleMaster) {
		entityCache.putResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterImpl.class, priceScheduleMaster.getPrimaryKey(),
			priceScheduleMaster);

		priceScheduleMaster.resetOriginalValues();
	}

	/**
	 * Caches the price schedule masters in the entity cache if it is enabled.
	 *
	 * @param priceScheduleMasters the price schedule masters
	 */
	@Override
	public void cacheResult(List<PriceScheduleMaster> priceScheduleMasters) {
		for (PriceScheduleMaster priceScheduleMaster : priceScheduleMasters) {
			if (entityCache.getResult(
						PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
						PriceScheduleMasterImpl.class,
						priceScheduleMaster.getPrimaryKey()) == null) {
				cacheResult(priceScheduleMaster);
			}
			else {
				priceScheduleMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all price schedule masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PriceScheduleMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the price schedule master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PriceScheduleMaster priceScheduleMaster) {
		entityCache.removeResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterImpl.class, priceScheduleMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PriceScheduleMaster> priceScheduleMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PriceScheduleMaster priceScheduleMaster : priceScheduleMasters) {
			entityCache.removeResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
				PriceScheduleMasterImpl.class,
				priceScheduleMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new price schedule master with the primary key. Does not add the price schedule master to the database.
	 *
	 * @param priceScheduleSystemId the primary key for the new price schedule master
	 * @return the new price schedule master
	 */
	@Override
	public PriceScheduleMaster create(int priceScheduleSystemId) {
		PriceScheduleMaster priceScheduleMaster = new PriceScheduleMasterImpl();

		priceScheduleMaster.setNew(true);
		priceScheduleMaster.setPrimaryKey(priceScheduleSystemId);

		return priceScheduleMaster;
	}

	/**
	 * Removes the price schedule master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param priceScheduleSystemId the primary key of the price schedule master
	 * @return the price schedule master that was removed
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster remove(int priceScheduleSystemId)
		throws NoSuchPriceScheduleMasterException {
		return remove((Serializable)priceScheduleSystemId);
	}

	/**
	 * Removes the price schedule master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the price schedule master
	 * @return the price schedule master that was removed
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster remove(Serializable primaryKey)
		throws NoSuchPriceScheduleMasterException {
		Session session = null;

		try {
			session = openSession();

			PriceScheduleMaster priceScheduleMaster = (PriceScheduleMaster)session.get(PriceScheduleMasterImpl.class,
					primaryKey);

			if (priceScheduleMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPriceScheduleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(priceScheduleMaster);
		}
		catch (NoSuchPriceScheduleMasterException nsee) {
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
	protected PriceScheduleMaster removeImpl(
		PriceScheduleMaster priceScheduleMaster) {
		priceScheduleMaster = toUnwrappedModel(priceScheduleMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(priceScheduleMaster)) {
				priceScheduleMaster = (PriceScheduleMaster)session.get(PriceScheduleMasterImpl.class,
						priceScheduleMaster.getPrimaryKeyObj());
			}

			if (priceScheduleMaster != null) {
				session.delete(priceScheduleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (priceScheduleMaster != null) {
			clearCache(priceScheduleMaster);
		}

		return priceScheduleMaster;
	}

	@Override
	public PriceScheduleMaster updateImpl(
		PriceScheduleMaster priceScheduleMaster) {
		priceScheduleMaster = toUnwrappedModel(priceScheduleMaster);

		boolean isNew = priceScheduleMaster.isNew();

		PriceScheduleMasterModelImpl priceScheduleMasterModelImpl = (PriceScheduleMasterModelImpl)priceScheduleMaster;

		Session session = null;

		try {
			session = openSession();

			if (priceScheduleMaster.isNew()) {
				session.save(priceScheduleMaster);

				priceScheduleMaster.setNew(false);
			}
			else {
				priceScheduleMaster = (PriceScheduleMaster)session.merge(priceScheduleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PriceScheduleMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					priceScheduleMasterModelImpl.getPriceScheduleId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEID,
				args);

			args = new Object[] {
					priceScheduleMasterModelImpl.getPriceScheduleNo()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULENO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENO,
				args);

			args = new Object[] {
					priceScheduleMasterModelImpl.getPriceScheduleName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULENAME,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENAME,
				args);

			args = new Object[] {
					priceScheduleMasterModelImpl.getPriceScheduleType()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULETYPE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULETYPE,
				args);

			args = new Object[] {
					priceScheduleMasterModelImpl.getPriceScheduleStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULESTATUS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULESTATUS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((priceScheduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						priceScheduleMasterModelImpl.getOriginalPriceScheduleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEID,
					args);

				args = new Object[] {
						priceScheduleMasterModelImpl.getPriceScheduleId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEID,
					args);
			}

			if ((priceScheduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						priceScheduleMasterModelImpl.getOriginalPriceScheduleNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULENO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENO,
					args);

				args = new Object[] {
						priceScheduleMasterModelImpl.getPriceScheduleNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULENO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENO,
					args);
			}

			if ((priceScheduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						priceScheduleMasterModelImpl.getOriginalPriceScheduleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENAME,
					args);

				args = new Object[] {
						priceScheduleMasterModelImpl.getPriceScheduleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULENAME,
					args);
			}

			if ((priceScheduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULETYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						priceScheduleMasterModelImpl.getOriginalPriceScheduleType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULETYPE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULETYPE,
					args);

				args = new Object[] {
						priceScheduleMasterModelImpl.getPriceScheduleType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULETYPE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULETYPE,
					args);
			}

			if ((priceScheduleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULESTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						priceScheduleMasterModelImpl.getOriginalPriceScheduleStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULESTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULESTATUS,
					args);

				args = new Object[] {
						priceScheduleMasterModelImpl.getPriceScheduleStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULESTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULESTATUS,
					args);
			}
		}

		entityCache.putResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleMasterImpl.class, priceScheduleMaster.getPrimaryKey(),
			priceScheduleMaster, false);

		priceScheduleMaster.resetOriginalValues();

		return priceScheduleMaster;
	}

	protected PriceScheduleMaster toUnwrappedModel(
		PriceScheduleMaster priceScheduleMaster) {
		if (priceScheduleMaster instanceof PriceScheduleMasterImpl) {
			return priceScheduleMaster;
		}

		PriceScheduleMasterImpl priceScheduleMasterImpl = new PriceScheduleMasterImpl();

		priceScheduleMasterImpl.setNew(priceScheduleMaster.isNew());
		priceScheduleMasterImpl.setPrimaryKey(priceScheduleMaster.getPrimaryKey());

		priceScheduleMasterImpl.setParentPriceScheduleName(priceScheduleMaster.getParentPriceScheduleName());
		priceScheduleMasterImpl.setParentPriceScheduleId(priceScheduleMaster.getParentPriceScheduleId());
		priceScheduleMasterImpl.setPriceScheduleStartDate(priceScheduleMaster.getPriceScheduleStartDate());
		priceScheduleMasterImpl.setPriceScheduleNo(priceScheduleMaster.getPriceScheduleNo());
		priceScheduleMasterImpl.setPriceScheduleName(priceScheduleMaster.getPriceScheduleName());
		priceScheduleMasterImpl.setPriceScheduleId(priceScheduleMaster.getPriceScheduleId());
		priceScheduleMasterImpl.setPriceScheduleType(priceScheduleMaster.getPriceScheduleType());
		priceScheduleMasterImpl.setModifiedDate(priceScheduleMaster.getModifiedDate());
		priceScheduleMasterImpl.setPriceScheduleSystemId(priceScheduleMaster.getPriceScheduleSystemId());
		priceScheduleMasterImpl.setRecordLockStatus(priceScheduleMaster.getRecordLockStatus());
		priceScheduleMasterImpl.setCreatedDate(priceScheduleMaster.getCreatedDate());
		priceScheduleMasterImpl.setCreatedBy(priceScheduleMaster.getCreatedBy());
		priceScheduleMasterImpl.setPriceScheduleDesignation(priceScheduleMaster.getPriceScheduleDesignation());
		priceScheduleMasterImpl.setPriceScheduleEndDate(priceScheduleMaster.getPriceScheduleEndDate());
		priceScheduleMasterImpl.setPriceScheduleStatus(priceScheduleMaster.getPriceScheduleStatus());
		priceScheduleMasterImpl.setBatchId(priceScheduleMaster.getBatchId());
		priceScheduleMasterImpl.setPriceScheduleCategory(priceScheduleMaster.getPriceScheduleCategory());
		priceScheduleMasterImpl.setTradeClass(priceScheduleMaster.getTradeClass());
		priceScheduleMasterImpl.setInboundStatus(priceScheduleMaster.getInboundStatus());
		priceScheduleMasterImpl.setModifiedBy(priceScheduleMaster.getModifiedBy());

		return priceScheduleMasterImpl;
	}

	/**
	 * Returns the price schedule master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the price schedule master
	 * @return the price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPriceScheduleMasterException {
		PriceScheduleMaster priceScheduleMaster = fetchByPrimaryKey(primaryKey);

		if (priceScheduleMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPriceScheduleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return priceScheduleMaster;
	}

	/**
	 * Returns the price schedule master with the primary key or throws a {@link NoSuchPriceScheduleMasterException} if it could not be found.
	 *
	 * @param priceScheduleSystemId the primary key of the price schedule master
	 * @return the price schedule master
	 * @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster findByPrimaryKey(int priceScheduleSystemId)
		throws NoSuchPriceScheduleMasterException {
		return findByPrimaryKey((Serializable)priceScheduleSystemId);
	}

	/**
	 * Returns the price schedule master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the price schedule master
	 * @return the price schedule master, or <code>null</code> if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
				PriceScheduleMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PriceScheduleMaster priceScheduleMaster = (PriceScheduleMaster)serializable;

		if (priceScheduleMaster == null) {
			Session session = null;

			try {
				session = openSession();

				priceScheduleMaster = (PriceScheduleMaster)session.get(PriceScheduleMasterImpl.class,
						primaryKey);

				if (priceScheduleMaster != null) {
					cacheResult(priceScheduleMaster);
				}
				else {
					entityCache.putResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
						PriceScheduleMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
					PriceScheduleMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return priceScheduleMaster;
	}

	/**
	 * Returns the price schedule master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param priceScheduleSystemId the primary key of the price schedule master
	 * @return the price schedule master, or <code>null</code> if a price schedule master with the primary key could not be found
	 */
	@Override
	public PriceScheduleMaster fetchByPrimaryKey(int priceScheduleSystemId) {
		return fetchByPrimaryKey((Serializable)priceScheduleSystemId);
	}

	@Override
	public Map<Serializable, PriceScheduleMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PriceScheduleMaster> map = new HashMap<Serializable, PriceScheduleMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PriceScheduleMaster priceScheduleMaster = fetchByPrimaryKey(primaryKey);

			if (priceScheduleMaster != null) {
				map.put(primaryKey, priceScheduleMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
					PriceScheduleMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PriceScheduleMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PRICESCHEDULEMASTER_WHERE_PKS_IN);

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

			for (PriceScheduleMaster priceScheduleMaster : (List<PriceScheduleMaster>)q.list()) {
				map.put(priceScheduleMaster.getPrimaryKeyObj(),
					priceScheduleMaster);

				cacheResult(priceScheduleMaster);

				uncachedPrimaryKeys.remove(priceScheduleMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PriceScheduleMasterModelImpl.ENTITY_CACHE_ENABLED,
					PriceScheduleMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the price schedule masters.
	 *
	 * @return the price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @return the range of price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findAll(int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of price schedule masters
	 * @param end the upper bound of the range of price schedule masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of price schedule masters
	 */
	@Override
	public List<PriceScheduleMaster> findAll(int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
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

		List<PriceScheduleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PRICESCHEDULEMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PRICESCHEDULEMASTER;

				if (pagination) {
					sql = sql.concat(PriceScheduleMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleMaster>)QueryUtil.list(q,
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
	 * Removes all the price schedule masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PriceScheduleMaster priceScheduleMaster : findAll()) {
			remove(priceScheduleMaster);
		}
	}

	/**
	 * Returns the number of price schedule masters.
	 *
	 * @return the number of price schedule masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PRICESCHEDULEMASTER);

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
		return PriceScheduleMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the price schedule master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PriceScheduleMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PRICESCHEDULEMASTER = "SELECT priceScheduleMaster FROM PriceScheduleMaster priceScheduleMaster";
	private static final String _SQL_SELECT_PRICESCHEDULEMASTER_WHERE_PKS_IN = "SELECT priceScheduleMaster FROM PriceScheduleMaster priceScheduleMaster WHERE PRICE_SCHEDULE_SYSTEM_ID IN (";
	private static final String _SQL_SELECT_PRICESCHEDULEMASTER_WHERE = "SELECT priceScheduleMaster FROM PriceScheduleMaster priceScheduleMaster WHERE ";
	private static final String _SQL_COUNT_PRICESCHEDULEMASTER = "SELECT COUNT(priceScheduleMaster) FROM PriceScheduleMaster priceScheduleMaster";
	private static final String _SQL_COUNT_PRICESCHEDULEMASTER_WHERE = "SELECT COUNT(priceScheduleMaster) FROM PriceScheduleMaster priceScheduleMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "priceScheduleMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PriceScheduleMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PriceScheduleMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PriceScheduleMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"parentPriceScheduleName", "parentPriceScheduleId",
				"priceScheduleStartDate", "priceScheduleNo", "priceScheduleName",
				"priceScheduleId", "priceScheduleType", "modifiedDate",
				"priceScheduleSystemId", "recordLockStatus", "createdDate",
				"createdBy", "priceScheduleDesignation", "priceScheduleEndDate",
				"priceScheduleStatus", "batchId", "priceScheduleCategory",
				"tradeClass", "inboundStatus", "modifiedBy"
			});
}