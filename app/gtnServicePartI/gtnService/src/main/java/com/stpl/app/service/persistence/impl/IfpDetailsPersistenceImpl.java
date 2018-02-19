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

import com.stpl.app.exception.NoSuchIfpDetailsException;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.impl.IfpDetailsImpl;
import com.stpl.app.model.impl.IfpDetailsModelImpl;
import com.stpl.app.service.persistence.IfpDetailsPersistence;

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
 * The persistence implementation for the ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpDetailsPersistence
 * @see com.stpl.app.service.persistence.IfpDetailsUtil
 * @generated
 */
@ProviderType
public class IfpDetailsPersistenceImpl extends BasePersistenceImpl<IfpDetails>
	implements IfpDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IfpDetailsUtil} to access the ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IfpDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS =
		new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemFamilyPlanDetails",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS =
		new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsModelImpl.FINDER_CACHE_ENABLED, IfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemFamilyPlanDetails",
			new String[] { Integer.class.getName() },
			IfpDetailsModelImpl.IFPMODELSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS = new FinderPath(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemFamilyPlanDetails",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the ifp detailses where ifpModelSid = &#63;.
	 *
	 * @param ifpModelSid the ifp model sid
	 * @return the matching ifp detailses
	 */
	@Override
	public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid) {
		return findByItemFamilyPlanDetails(ifpModelSid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp detailses where ifpModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param start the lower bound of the range of ifp detailses
	 * @param end the upper bound of the range of ifp detailses (not inclusive)
	 * @return the range of matching ifp detailses
	 */
	@Override
	public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid,
		int start, int end) {
		return findByItemFamilyPlanDetails(ifpModelSid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param start the lower bound of the range of ifp detailses
	 * @param end the upper bound of the range of ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ifp detailses
	 */
	@Override
	public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid,
		int start, int end, OrderByComparator<IfpDetails> orderByComparator) {
		return findByItemFamilyPlanDetails(ifpModelSid, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param start the lower bound of the range of ifp detailses
	 * @param end the upper bound of the range of ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ifp detailses
	 */
	@Override
	public List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid,
		int start, int end, OrderByComparator<IfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS;
			finderArgs = new Object[] { ifpModelSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS;
			finderArgs = new Object[] { ifpModelSid, start, end, orderByComparator };
		}

		List<IfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<IfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IfpDetails ifpDetails : list) {
					if ((ifpModelSid != ifpDetails.getIfpModelSid())) {
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

			query.append(_SQL_SELECT_IFPDETAILS_WHERE);

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IfpDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ifpModelSid);

				if (!pagination) {
					list = (List<IfpDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpDetails>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp details
	 * @throws NoSuchIfpDetailsException if a matching ifp details could not be found
	 */
	@Override
	public IfpDetails findByItemFamilyPlanDetails_First(int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator)
		throws NoSuchIfpDetailsException {
		IfpDetails ifpDetails = fetchByItemFamilyPlanDetails_First(ifpModelSid,
				orderByComparator);

		if (ifpDetails != null) {
			return ifpDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpModelSid=");
		msg.append(ifpModelSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpDetailsException(msg.toString());
	}

	/**
	 * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp details, or <code>null</code> if a matching ifp details could not be found
	 */
	@Override
	public IfpDetails fetchByItemFamilyPlanDetails_First(int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator) {
		List<IfpDetails> list = findByItemFamilyPlanDetails(ifpModelSid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp details
	 * @throws NoSuchIfpDetailsException if a matching ifp details could not be found
	 */
	@Override
	public IfpDetails findByItemFamilyPlanDetails_Last(int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator)
		throws NoSuchIfpDetailsException {
		IfpDetails ifpDetails = fetchByItemFamilyPlanDetails_Last(ifpModelSid,
				orderByComparator);

		if (ifpDetails != null) {
			return ifpDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpModelSid=");
		msg.append(ifpModelSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpDetailsException(msg.toString());
	}

	/**
	 * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
	 *
	 * @param ifpModelSid the ifp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp details, or <code>null</code> if a matching ifp details could not be found
	 */
	@Override
	public IfpDetails fetchByItemFamilyPlanDetails_Last(int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator) {
		int count = countByItemFamilyPlanDetails(ifpModelSid);

		if (count == 0) {
			return null;
		}

		List<IfpDetails> list = findByItemFamilyPlanDetails(ifpModelSid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ifp detailses before and after the current ifp details in the ordered set where ifpModelSid = &#63;.
	 *
	 * @param ifpDetailsSid the primary key of the current ifp details
	 * @param ifpModelSid the ifp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ifp details
	 * @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails[] findByItemFamilyPlanDetails_PrevAndNext(
		int ifpDetailsSid, int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator)
		throws NoSuchIfpDetailsException {
		IfpDetails ifpDetails = findByPrimaryKey(ifpDetailsSid);

		Session session = null;

		try {
			session = openSession();

			IfpDetails[] array = new IfpDetailsImpl[3];

			array[0] = getByItemFamilyPlanDetails_PrevAndNext(session,
					ifpDetails, ifpModelSid, orderByComparator, true);

			array[1] = ifpDetails;

			array[2] = getByItemFamilyPlanDetails_PrevAndNext(session,
					ifpDetails, ifpModelSid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IfpDetails getByItemFamilyPlanDetails_PrevAndNext(
		Session session, IfpDetails ifpDetails, int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IFPDETAILS_WHERE);

		query.append(_FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2);

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
			query.append(IfpDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ifpModelSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ifpDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IfpDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ifp detailses where ifpModelSid = &#63; from the database.
	 *
	 * @param ifpModelSid the ifp model sid
	 */
	@Override
	public void removeByItemFamilyPlanDetails(int ifpModelSid) {
		for (IfpDetails ifpDetails : findByItemFamilyPlanDetails(ifpModelSid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ifpDetails);
		}
	}

	/**
	 * Returns the number of ifp detailses where ifpModelSid = &#63;.
	 *
	 * @param ifpModelSid the ifp model sid
	 * @return the number of matching ifp detailses
	 */
	@Override
	public int countByItemFamilyPlanDetails(int ifpModelSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS;

		Object[] finderArgs = new Object[] { ifpModelSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IFPDETAILS_WHERE);

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ifpModelSid);

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

	private static final String _FINDER_COLUMN_ITEMFAMILYPLANDETAILS_IFPMODELSID_2 =
		"ifpDetails.ifpModelSid = ?";

	public IfpDetailsPersistenceImpl() {
		setModelClass(IfpDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemIfpAttachedDate", "ITEM_IFP_ATTACHED_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("ifpDetailsSid", "IFP_DETAILS_SID");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("itemIfpAttachedStatus",
				"ITEM_IFP_ATTACHED_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ifp details in the entity cache if it is enabled.
	 *
	 * @param ifpDetails the ifp details
	 */
	@Override
	public void cacheResult(IfpDetails ifpDetails) {
		entityCache.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsImpl.class, ifpDetails.getPrimaryKey(), ifpDetails);

		ifpDetails.resetOriginalValues();
	}

	/**
	 * Caches the ifp detailses in the entity cache if it is enabled.
	 *
	 * @param ifpDetailses the ifp detailses
	 */
	@Override
	public void cacheResult(List<IfpDetails> ifpDetailses) {
		for (IfpDetails ifpDetails : ifpDetailses) {
			if (entityCache.getResult(
						IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						IfpDetailsImpl.class, ifpDetails.getPrimaryKey()) == null) {
				cacheResult(ifpDetails);
			}
			else {
				ifpDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ifp detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IfpDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ifp details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IfpDetails ifpDetails) {
		entityCache.removeResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsImpl.class, ifpDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IfpDetails> ifpDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IfpDetails ifpDetails : ifpDetailses) {
			entityCache.removeResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				IfpDetailsImpl.class, ifpDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ifp details with the primary key. Does not add the ifp details to the database.
	 *
	 * @param ifpDetailsSid the primary key for the new ifp details
	 * @return the new ifp details
	 */
	@Override
	public IfpDetails create(int ifpDetailsSid) {
		IfpDetails ifpDetails = new IfpDetailsImpl();

		ifpDetails.setNew(true);
		ifpDetails.setPrimaryKey(ifpDetailsSid);

		return ifpDetails;
	}

	/**
	 * Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ifpDetailsSid the primary key of the ifp details
	 * @return the ifp details that was removed
	 * @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails remove(int ifpDetailsSid)
		throws NoSuchIfpDetailsException {
		return remove((Serializable)ifpDetailsSid);
	}

	/**
	 * Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ifp details
	 * @return the ifp details that was removed
	 * @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails remove(Serializable primaryKey)
		throws NoSuchIfpDetailsException {
		Session session = null;

		try {
			session = openSession();

			IfpDetails ifpDetails = (IfpDetails)session.get(IfpDetailsImpl.class,
					primaryKey);

			if (ifpDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ifpDetails);
		}
		catch (NoSuchIfpDetailsException nsee) {
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
	protected IfpDetails removeImpl(IfpDetails ifpDetails) {
		ifpDetails = toUnwrappedModel(ifpDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ifpDetails)) {
				ifpDetails = (IfpDetails)session.get(IfpDetailsImpl.class,
						ifpDetails.getPrimaryKeyObj());
			}

			if (ifpDetails != null) {
				session.delete(ifpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ifpDetails != null) {
			clearCache(ifpDetails);
		}

		return ifpDetails;
	}

	@Override
	public IfpDetails updateImpl(IfpDetails ifpDetails) {
		ifpDetails = toUnwrappedModel(ifpDetails);

		boolean isNew = ifpDetails.isNew();

		IfpDetailsModelImpl ifpDetailsModelImpl = (IfpDetailsModelImpl)ifpDetails;

		Session session = null;

		try {
			session = openSession();

			if (ifpDetails.isNew()) {
				session.save(ifpDetails);

				ifpDetails.setNew(false);
			}
			else {
				ifpDetails = (IfpDetails)session.merge(ifpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!IfpDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { ifpDetailsModelImpl.getIfpModelSid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ifpDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ifpDetailsModelImpl.getOriginalIfpModelSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS,
					args);

				args = new Object[] { ifpDetailsModelImpl.getIfpModelSid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANDETAILS,
					args);
			}
		}

		entityCache.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IfpDetailsImpl.class, ifpDetails.getPrimaryKey(), ifpDetails, false);

		ifpDetails.resetOriginalValues();

		return ifpDetails;
	}

	protected IfpDetails toUnwrappedModel(IfpDetails ifpDetails) {
		if (ifpDetails instanceof IfpDetailsImpl) {
			return ifpDetails;
		}

		IfpDetailsImpl ifpDetailsImpl = new IfpDetailsImpl();

		ifpDetailsImpl.setNew(ifpDetails.isNew());
		ifpDetailsImpl.setPrimaryKey(ifpDetails.getPrimaryKey());

		ifpDetailsImpl.setItemMasterSid(ifpDetails.getItemMasterSid());
		ifpDetailsImpl.setEndDate(ifpDetails.getEndDate());
		ifpDetailsImpl.setModifiedDate(ifpDetails.getModifiedDate());
		ifpDetailsImpl.setRecordLockStatus(ifpDetails.isRecordLockStatus());
		ifpDetailsImpl.setStartDate(ifpDetails.getStartDate());
		ifpDetailsImpl.setCreatedDate(ifpDetails.getCreatedDate());
		ifpDetailsImpl.setSource(ifpDetails.getSource());
		ifpDetailsImpl.setCreatedBy(ifpDetails.getCreatedBy());
		ifpDetailsImpl.setItemIfpAttachedDate(ifpDetails.getItemIfpAttachedDate());
		ifpDetailsImpl.setBatchId(ifpDetails.getBatchId());
		ifpDetailsImpl.setModifiedBy(ifpDetails.getModifiedBy());
		ifpDetailsImpl.setInboundStatus(ifpDetails.getInboundStatus());
		ifpDetailsImpl.setIfpDetailsSid(ifpDetails.getIfpDetailsSid());
		ifpDetailsImpl.setIfpModelSid(ifpDetails.getIfpModelSid());
		ifpDetailsImpl.setItemIfpAttachedStatus(ifpDetails.getItemIfpAttachedStatus());

		return ifpDetailsImpl;
	}

	/**
	 * Returns the ifp details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ifp details
	 * @return the ifp details
	 * @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIfpDetailsException {
		IfpDetails ifpDetails = fetchByPrimaryKey(primaryKey);

		if (ifpDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ifpDetails;
	}

	/**
	 * Returns the ifp details with the primary key or throws a {@link NoSuchIfpDetailsException} if it could not be found.
	 *
	 * @param ifpDetailsSid the primary key of the ifp details
	 * @return the ifp details
	 * @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails findByPrimaryKey(int ifpDetailsSid)
		throws NoSuchIfpDetailsException {
		return findByPrimaryKey((Serializable)ifpDetailsSid);
	}

	/**
	 * Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ifp details
	 * @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				IfpDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IfpDetails ifpDetails = (IfpDetails)serializable;

		if (ifpDetails == null) {
			Session session = null;

			try {
				session = openSession();

				ifpDetails = (IfpDetails)session.get(IfpDetailsImpl.class,
						primaryKey);

				if (ifpDetails != null) {
					cacheResult(ifpDetails);
				}
				else {
					entityCache.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						IfpDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					IfpDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ifpDetails;
	}

	/**
	 * Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ifpDetailsSid the primary key of the ifp details
	 * @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
	 */
	@Override
	public IfpDetails fetchByPrimaryKey(int ifpDetailsSid) {
		return fetchByPrimaryKey((Serializable)ifpDetailsSid);
	}

	@Override
	public Map<Serializable, IfpDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IfpDetails> map = new HashMap<Serializable, IfpDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IfpDetails ifpDetails = fetchByPrimaryKey(primaryKey);

			if (ifpDetails != null) {
				map.put(primaryKey, ifpDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					IfpDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IfpDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IFPDETAILS_WHERE_PKS_IN);

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

			for (IfpDetails ifpDetails : (List<IfpDetails>)q.list()) {
				map.put(ifpDetails.getPrimaryKeyObj(), ifpDetails);

				cacheResult(ifpDetails);

				uncachedPrimaryKeys.remove(ifpDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					IfpDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ifp detailses.
	 *
	 * @return the ifp detailses
	 */
	@Override
	public List<IfpDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp detailses
	 * @param end the upper bound of the range of ifp detailses (not inclusive)
	 * @return the range of ifp detailses
	 */
	@Override
	public List<IfpDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp detailses
	 * @param end the upper bound of the range of ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ifp detailses
	 */
	@Override
	public List<IfpDetails> findAll(int start, int end,
		OrderByComparator<IfpDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp detailses
	 * @param end the upper bound of the range of ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ifp detailses
	 */
	@Override
	public List<IfpDetails> findAll(int start, int end,
		OrderByComparator<IfpDetails> orderByComparator,
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

		List<IfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<IfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IFPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IFPDETAILS;

				if (pagination) {
					sql = sql.concat(IfpDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IfpDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ifp detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IfpDetails ifpDetails : findAll()) {
			remove(ifpDetails);
		}
	}

	/**
	 * Returns the number of ifp detailses.
	 *
	 * @return the number of ifp detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IFPDETAILS);

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
		return IfpDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ifp details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IfpDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IFPDETAILS = "SELECT ifpDetails FROM IfpDetails ifpDetails";
	private static final String _SQL_SELECT_IFPDETAILS_WHERE_PKS_IN = "SELECT ifpDetails FROM IfpDetails ifpDetails WHERE IFP_DETAILS_SID IN (";
	private static final String _SQL_SELECT_IFPDETAILS_WHERE = "SELECT ifpDetails FROM IfpDetails ifpDetails WHERE ";
	private static final String _SQL_COUNT_IFPDETAILS = "SELECT COUNT(ifpDetails) FROM IfpDetails ifpDetails";
	private static final String _SQL_COUNT_IFPDETAILS_WHERE = "SELECT COUNT(ifpDetails) FROM IfpDetails ifpDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ifpDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IfpDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(IfpDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemMasterSid", "endDate", "modifiedDate", "recordLockStatus",
				"startDate", "createdDate", "source", "createdBy",
				"itemIfpAttachedDate", "batchId", "modifiedBy", "inboundStatus",
				"ifpDetailsSid", "ifpModelSid", "itemIfpAttachedStatus"
			});
}