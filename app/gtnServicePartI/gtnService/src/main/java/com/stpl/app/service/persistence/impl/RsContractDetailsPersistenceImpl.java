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

import com.stpl.app.exception.NoSuchRsContractDetailsException;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.impl.RsContractDetailsImpl;
import com.stpl.app.model.impl.RsContractDetailsModelImpl;
import com.stpl.app.service.persistence.RsContractDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the rs contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractDetailsPersistence
 * @see com.stpl.app.service.persistence.RsContractDetailsUtil
 * @generated
 */
@ProviderType
public class RsContractDetailsPersistenceImpl extends BasePersistenceImpl<RsContractDetails>
	implements RsContractDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RsContractDetailsUtil} to access the rs contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RsContractDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			RsContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			RsContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS =
		new FinderPath(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			RsContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByRebateScheduleDetails",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS =
		new FinderPath(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			RsContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByRebateScheduleDetails",
			new String[] { Integer.class.getName(), Integer.class.getName() },
			RsContractDetailsModelImpl.RSCONTRACTSID_COLUMN_BITMASK |
			RsContractDetailsModelImpl.ITEMMASTERSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REBATESCHEDULEDETAILS = new FinderPath(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByRebateScheduleDetails",
			new String[] { Integer.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @return the matching rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid) {
		return findByRebateScheduleDetails(rsContractSid, itemMasterSid,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of rs contract detailses
	 * @param end the upper bound of the range of rs contract detailses (not inclusive)
	 * @return the range of matching rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end) {
		return findByRebateScheduleDetails(rsContractSid, itemMasterSid, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of rs contract detailses
	 * @param end the upper bound of the range of rs contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return findByRebateScheduleDetails(rsContractSid, itemMasterSid, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of rs contract detailses
	 * @param end the upper bound of the range of rs contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS;
			finderArgs = new Object[] { rsContractSid, itemMasterSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS;
			finderArgs = new Object[] {
					rsContractSid, itemMasterSid,
					
					start, end, orderByComparator
				};
		}

		List<RsContractDetails> list = null;

		if (retrieveFromCache) {
			list = (List<RsContractDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RsContractDetails rsContractDetails : list) {
					if ((rsContractSid != rsContractDetails.getRsContractSid()) ||
							(itemMasterSid != rsContractDetails.getItemMasterSid())) {
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

			query.append(_SQL_SELECT_RSCONTRACTDETAILS_WHERE);

			query.append(_FINDER_COLUMN_REBATESCHEDULEDETAILS_RSCONTRACTSID_2);

			query.append(_FINDER_COLUMN_REBATESCHEDULEDETAILS_ITEMMASTERSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RsContractDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(rsContractSid);

				qPos.add(itemMasterSid);

				if (!pagination) {
					list = (List<RsContractDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RsContractDetails>)QueryUtil.list(q,
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
	 * Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rs contract details
	 * @throws NoSuchRsContractDetailsException if a matching rs contract details could not be found
	 */
	@Override
	public RsContractDetails findByRebateScheduleDetails_First(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator)
		throws NoSuchRsContractDetailsException {
		RsContractDetails rsContractDetails = fetchByRebateScheduleDetails_First(rsContractSid,
				itemMasterSid, orderByComparator);

		if (rsContractDetails != null) {
			return rsContractDetails;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rsContractSid=");
		msg.append(rsContractSid);

		msg.append(", itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRsContractDetailsException(msg.toString());
	}

	/**
	 * Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
	 */
	@Override
	public RsContractDetails fetchByRebateScheduleDetails_First(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator) {
		List<RsContractDetails> list = findByRebateScheduleDetails(rsContractSid,
				itemMasterSid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rs contract details
	 * @throws NoSuchRsContractDetailsException if a matching rs contract details could not be found
	 */
	@Override
	public RsContractDetails findByRebateScheduleDetails_Last(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator)
		throws NoSuchRsContractDetailsException {
		RsContractDetails rsContractDetails = fetchByRebateScheduleDetails_Last(rsContractSid,
				itemMasterSid, orderByComparator);

		if (rsContractDetails != null) {
			return rsContractDetails;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rsContractSid=");
		msg.append(rsContractSid);

		msg.append(", itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRsContractDetailsException(msg.toString());
	}

	/**
	 * Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
	 */
	@Override
	public RsContractDetails fetchByRebateScheduleDetails_Last(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator) {
		int count = countByRebateScheduleDetails(rsContractSid, itemMasterSid);

		if (count == 0) {
			return null;
		}

		List<RsContractDetails> list = findByRebateScheduleDetails(rsContractSid,
				itemMasterSid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the rs contract detailses before and after the current rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractDetailsSid the primary key of the current rs contract details
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next rs contract details
	 * @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails[] findByRebateScheduleDetails_PrevAndNext(
		int rsContractDetailsSid, int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator)
		throws NoSuchRsContractDetailsException {
		RsContractDetails rsContractDetails = findByPrimaryKey(rsContractDetailsSid);

		Session session = null;

		try {
			session = openSession();

			RsContractDetails[] array = new RsContractDetailsImpl[3];

			array[0] = getByRebateScheduleDetails_PrevAndNext(session,
					rsContractDetails, rsContractSid, itemMasterSid,
					orderByComparator, true);

			array[1] = rsContractDetails;

			array[2] = getByRebateScheduleDetails_PrevAndNext(session,
					rsContractDetails, rsContractSid, itemMasterSid,
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

	protected RsContractDetails getByRebateScheduleDetails_PrevAndNext(
		Session session, RsContractDetails rsContractDetails,
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_RSCONTRACTDETAILS_WHERE);

		query.append(_FINDER_COLUMN_REBATESCHEDULEDETAILS_RSCONTRACTSID_2);

		query.append(_FINDER_COLUMN_REBATESCHEDULEDETAILS_ITEMMASTERSID_2);

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
			query.append(RsContractDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(rsContractSid);

		qPos.add(itemMasterSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(rsContractDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RsContractDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63; from the database.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 */
	@Override
	public void removeByRebateScheduleDetails(int rsContractSid,
		int itemMasterSid) {
		for (RsContractDetails rsContractDetails : findByRebateScheduleDetails(
				rsContractSid, itemMasterSid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(rsContractDetails);
		}
	}

	/**
	 * Returns the number of rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	 *
	 * @param rsContractSid the rs contract sid
	 * @param itemMasterSid the item master sid
	 * @return the number of matching rs contract detailses
	 */
	@Override
	public int countByRebateScheduleDetails(int rsContractSid, int itemMasterSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REBATESCHEDULEDETAILS;

		Object[] finderArgs = new Object[] { rsContractSid, itemMasterSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RSCONTRACTDETAILS_WHERE);

			query.append(_FINDER_COLUMN_REBATESCHEDULEDETAILS_RSCONTRACTSID_2);

			query.append(_FINDER_COLUMN_REBATESCHEDULEDETAILS_ITEMMASTERSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(rsContractSid);

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

	private static final String _FINDER_COLUMN_REBATESCHEDULEDETAILS_RSCONTRACTSID_2 =
		"rsContractDetails.rsContractSid = ? AND ";
	private static final String _FINDER_COLUMN_REBATESCHEDULEDETAILS_ITEMMASTERSID_2 =
		"rsContractDetails.itemMasterSid = ?";

	public RsContractDetailsPersistenceImpl() {
		setModelClass(RsContractDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("rebateAmount", "REBATE_AMOUNT");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("rebatePlanMasterSid", "REBATE_PLAN_MASTER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsContractDetailsSid", "RS_CONTRACT_DETAILS_SID");
			dbColumnNames.put("bundleNo", "BUNDLE_NO");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("rsContractSid", "RS_CONTRACT_SID");
			dbColumnNames.put("itemRebateEndDate", "ITEM_REBATE_END_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemRebateStartDate", "ITEM_REBATE_START_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("deductionCalendarMasterSid",
				"DEDUCTION_CALENDAR_MASTER_SID");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("formulaType", "FORMULA_TYPE");
			dbColumnNames.put("netSalesRule", "NET_SALES_RULE");
			dbColumnNames.put("evaluationRule", "EVALUATION_RULE");
			dbColumnNames.put("evaluationRuleBundle", "EVALUATION_RULE_BUNDLE");
			dbColumnNames.put("calculationRule", "CALCULATION_RULE");
			dbColumnNames.put("calculationRuleBundle", "CALCULATION_RULE_BUNDLE");
			dbColumnNames.put("rsContractAttachedDate",
				"RS_CONTRACT_ATTACHED_DATE");
			dbColumnNames.put("rsContractAttachedStatus",
				"RS_CONTRACT_ATTACHED_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rs contract details in the entity cache if it is enabled.
	 *
	 * @param rsContractDetails the rs contract details
	 */
	@Override
	public void cacheResult(RsContractDetails rsContractDetails) {
		entityCache.putResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsImpl.class, rsContractDetails.getPrimaryKey(),
			rsContractDetails);

		rsContractDetails.resetOriginalValues();
	}

	/**
	 * Caches the rs contract detailses in the entity cache if it is enabled.
	 *
	 * @param rsContractDetailses the rs contract detailses
	 */
	@Override
	public void cacheResult(List<RsContractDetails> rsContractDetailses) {
		for (RsContractDetails rsContractDetails : rsContractDetailses) {
			if (entityCache.getResult(
						RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
						RsContractDetailsImpl.class,
						rsContractDetails.getPrimaryKey()) == null) {
				cacheResult(rsContractDetails);
			}
			else {
				rsContractDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rs contract detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RsContractDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rs contract details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RsContractDetails rsContractDetails) {
		entityCache.removeResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsImpl.class, rsContractDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RsContractDetails> rsContractDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RsContractDetails rsContractDetails : rsContractDetailses) {
			entityCache.removeResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
				RsContractDetailsImpl.class, rsContractDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rs contract details with the primary key. Does not add the rs contract details to the database.
	 *
	 * @param rsContractDetailsSid the primary key for the new rs contract details
	 * @return the new rs contract details
	 */
	@Override
	public RsContractDetails create(int rsContractDetailsSid) {
		RsContractDetails rsContractDetails = new RsContractDetailsImpl();

		rsContractDetails.setNew(true);
		rsContractDetails.setPrimaryKey(rsContractDetailsSid);

		return rsContractDetails;
	}

	/**
	 * Removes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rsContractDetailsSid the primary key of the rs contract details
	 * @return the rs contract details that was removed
	 * @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails remove(int rsContractDetailsSid)
		throws NoSuchRsContractDetailsException {
		return remove((Serializable)rsContractDetailsSid);
	}

	/**
	 * Removes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rs contract details
	 * @return the rs contract details that was removed
	 * @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails remove(Serializable primaryKey)
		throws NoSuchRsContractDetailsException {
		Session session = null;

		try {
			session = openSession();

			RsContractDetails rsContractDetails = (RsContractDetails)session.get(RsContractDetailsImpl.class,
					primaryKey);

			if (rsContractDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRsContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rsContractDetails);
		}
		catch (NoSuchRsContractDetailsException nsee) {
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
	protected RsContractDetails removeImpl(RsContractDetails rsContractDetails) {
		rsContractDetails = toUnwrappedModel(rsContractDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rsContractDetails)) {
				rsContractDetails = (RsContractDetails)session.get(RsContractDetailsImpl.class,
						rsContractDetails.getPrimaryKeyObj());
			}

			if (rsContractDetails != null) {
				session.delete(rsContractDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rsContractDetails != null) {
			clearCache(rsContractDetails);
		}

		return rsContractDetails;
	}

	@Override
	public RsContractDetails updateImpl(RsContractDetails rsContractDetails) {
		rsContractDetails = toUnwrappedModel(rsContractDetails);

		boolean isNew = rsContractDetails.isNew();

		RsContractDetailsModelImpl rsContractDetailsModelImpl = (RsContractDetailsModelImpl)rsContractDetails;

		Session session = null;

		try {
			session = openSession();

			if (rsContractDetails.isNew()) {
				session.save(rsContractDetails);

				rsContractDetails.setNew(false);
			}
			else {
				rsContractDetails = (RsContractDetails)session.merge(rsContractDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!RsContractDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					rsContractDetailsModelImpl.getRsContractSid(),
					rsContractDetailsModelImpl.getItemMasterSid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULEDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((rsContractDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						rsContractDetailsModelImpl.getOriginalRsContractSid(),
						rsContractDetailsModelImpl.getOriginalItemMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULEDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS,
					args);

				args = new Object[] {
						rsContractDetailsModelImpl.getRsContractSid(),
						rsContractDetailsModelImpl.getItemMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_REBATESCHEDULEDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REBATESCHEDULEDETAILS,
					args);
			}
		}

		entityCache.putResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsImpl.class, rsContractDetails.getPrimaryKey(),
			rsContractDetails, false);

		rsContractDetails.resetOriginalValues();

		return rsContractDetails;
	}

	protected RsContractDetails toUnwrappedModel(
		RsContractDetails rsContractDetails) {
		if (rsContractDetails instanceof RsContractDetailsImpl) {
			return rsContractDetails;
		}

		RsContractDetailsImpl rsContractDetailsImpl = new RsContractDetailsImpl();

		rsContractDetailsImpl.setNew(rsContractDetails.isNew());
		rsContractDetailsImpl.setPrimaryKey(rsContractDetails.getPrimaryKey());

		rsContractDetailsImpl.setRebateAmount(rsContractDetails.getRebateAmount());
		rsContractDetailsImpl.setFormulaMethodId(rsContractDetails.getFormulaMethodId());
		rsContractDetailsImpl.setItemMasterSid(rsContractDetails.getItemMasterSid());
		rsContractDetailsImpl.setRebatePlanMasterSid(rsContractDetails.getRebatePlanMasterSid());
		rsContractDetailsImpl.setModifiedDate(rsContractDetails.getModifiedDate());
		rsContractDetailsImpl.setRsContractDetailsSid(rsContractDetails.getRsContractDetailsSid());
		rsContractDetailsImpl.setBundleNo(rsContractDetails.getBundleNo());
		rsContractDetailsImpl.setRecordLockStatus(rsContractDetails.isRecordLockStatus());
		rsContractDetailsImpl.setCreatedDate(rsContractDetails.getCreatedDate());
		rsContractDetailsImpl.setCreatedBy(rsContractDetails.getCreatedBy());
		rsContractDetailsImpl.setSource(rsContractDetails.getSource());
		rsContractDetailsImpl.setRsContractSid(rsContractDetails.getRsContractSid());
		rsContractDetailsImpl.setItemRebateEndDate(rsContractDetails.getItemRebateEndDate());
		rsContractDetailsImpl.setBatchId(rsContractDetails.getBatchId());
		rsContractDetailsImpl.setItemRebateStartDate(rsContractDetails.getItemRebateStartDate());
		rsContractDetailsImpl.setModifiedBy(rsContractDetails.getModifiedBy());
		rsContractDetailsImpl.setFormulaId(rsContractDetails.getFormulaId());
		rsContractDetailsImpl.setInboundStatus(rsContractDetails.getInboundStatus());
		rsContractDetailsImpl.setDeductionCalendarMasterSid(rsContractDetails.getDeductionCalendarMasterSid());
		rsContractDetailsImpl.setNetSalesFormulaMasterSid(rsContractDetails.getNetSalesFormulaMasterSid());
		rsContractDetailsImpl.setFormulaType(rsContractDetails.getFormulaType());
		rsContractDetailsImpl.setNetSalesRule(rsContractDetails.getNetSalesRule());
		rsContractDetailsImpl.setEvaluationRule(rsContractDetails.getEvaluationRule());
		rsContractDetailsImpl.setEvaluationRuleBundle(rsContractDetails.getEvaluationRuleBundle());
		rsContractDetailsImpl.setCalculationRule(rsContractDetails.getCalculationRule());
		rsContractDetailsImpl.setCalculationRuleBundle(rsContractDetails.getCalculationRuleBundle());
		rsContractDetailsImpl.setRsContractAttachedDate(rsContractDetails.getRsContractAttachedDate());
		rsContractDetailsImpl.setRsContractAttachedStatus(rsContractDetails.getRsContractAttachedStatus());

		return rsContractDetailsImpl;
	}

	/**
	 * Returns the rs contract details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs contract details
	 * @return the rs contract details
	 * @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRsContractDetailsException {
		RsContractDetails rsContractDetails = fetchByPrimaryKey(primaryKey);

		if (rsContractDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRsContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rsContractDetails;
	}

	/**
	 * Returns the rs contract details with the primary key or throws a {@link NoSuchRsContractDetailsException} if it could not be found.
	 *
	 * @param rsContractDetailsSid the primary key of the rs contract details
	 * @return the rs contract details
	 * @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails findByPrimaryKey(int rsContractDetailsSid)
		throws NoSuchRsContractDetailsException {
		return findByPrimaryKey((Serializable)rsContractDetailsSid);
	}

	/**
	 * Returns the rs contract details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs contract details
	 * @return the rs contract details, or <code>null</code> if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
				RsContractDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RsContractDetails rsContractDetails = (RsContractDetails)serializable;

		if (rsContractDetails == null) {
			Session session = null;

			try {
				session = openSession();

				rsContractDetails = (RsContractDetails)session.get(RsContractDetailsImpl.class,
						primaryKey);

				if (rsContractDetails != null) {
					cacheResult(rsContractDetails);
				}
				else {
					entityCache.putResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
						RsContractDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					RsContractDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rsContractDetails;
	}

	/**
	 * Returns the rs contract details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rsContractDetailsSid the primary key of the rs contract details
	 * @return the rs contract details, or <code>null</code> if a rs contract details with the primary key could not be found
	 */
	@Override
	public RsContractDetails fetchByPrimaryKey(int rsContractDetailsSid) {
		return fetchByPrimaryKey((Serializable)rsContractDetailsSid);
	}

	@Override
	public Map<Serializable, RsContractDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RsContractDetails> map = new HashMap<Serializable, RsContractDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RsContractDetails rsContractDetails = fetchByPrimaryKey(primaryKey);

			if (rsContractDetails != null) {
				map.put(primaryKey, rsContractDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					RsContractDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RsContractDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RSCONTRACTDETAILS_WHERE_PKS_IN);

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

			for (RsContractDetails rsContractDetails : (List<RsContractDetails>)q.list()) {
				map.put(rsContractDetails.getPrimaryKeyObj(), rsContractDetails);

				cacheResult(rsContractDetails);

				uncachedPrimaryKeys.remove(rsContractDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					RsContractDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the rs contract detailses.
	 *
	 * @return the rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rs contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contract detailses
	 * @param end the upper bound of the range of rs contract detailses (not inclusive)
	 * @return the range of rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rs contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contract detailses
	 * @param end the upper bound of the range of rs contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findAll(int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rs contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contract detailses
	 * @param end the upper bound of the range of rs contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rs contract detailses
	 */
	@Override
	public List<RsContractDetails> findAll(int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator,
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

		List<RsContractDetails> list = null;

		if (retrieveFromCache) {
			list = (List<RsContractDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RSCONTRACTDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RSCONTRACTDETAILS;

				if (pagination) {
					sql = sql.concat(RsContractDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RsContractDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RsContractDetails>)QueryUtil.list(q,
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
	 * Removes all the rs contract detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RsContractDetails rsContractDetails : findAll()) {
			remove(rsContractDetails);
		}
	}

	/**
	 * Returns the number of rs contract detailses.
	 *
	 * @return the number of rs contract detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RSCONTRACTDETAILS);

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
		return RsContractDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rs contract details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RsContractDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RSCONTRACTDETAILS = "SELECT rsContractDetails FROM RsContractDetails rsContractDetails";
	private static final String _SQL_SELECT_RSCONTRACTDETAILS_WHERE_PKS_IN = "SELECT rsContractDetails FROM RsContractDetails rsContractDetails WHERE RS_CONTRACT_DETAILS_SID IN (";
	private static final String _SQL_SELECT_RSCONTRACTDETAILS_WHERE = "SELECT rsContractDetails FROM RsContractDetails rsContractDetails WHERE ";
	private static final String _SQL_COUNT_RSCONTRACTDETAILS = "SELECT COUNT(rsContractDetails) FROM RsContractDetails rsContractDetails";
	private static final String _SQL_COUNT_RSCONTRACTDETAILS_WHERE = "SELECT COUNT(rsContractDetails) FROM RsContractDetails rsContractDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rsContractDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsContractDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RsContractDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RsContractDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"rebateAmount", "formulaMethodId", "itemMasterSid",
				"rebatePlanMasterSid", "modifiedDate", "rsContractDetailsSid",
				"bundleNo", "recordLockStatus", "createdDate", "createdBy",
				"source", "rsContractSid", "itemRebateEndDate", "batchId",
				"itemRebateStartDate", "modifiedBy", "formulaId",
				"inboundStatus", "deductionCalendarMasterSid",
				"netSalesFormulaMasterSid", "formulaType", "netSalesRule",
				"evaluationRule", "evaluationRuleBundle", "calculationRule",
				"calculationRuleBundle", "rsContractAttachedDate",
				"rsContractAttachedStatus"
			});
}