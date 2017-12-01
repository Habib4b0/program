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

import com.stpl.app.exception.NoSuchCpiIndexMasterException;
import com.stpl.app.model.CpiIndexMaster;
import com.stpl.app.model.impl.CpiIndexMasterImpl;
import com.stpl.app.model.impl.CpiIndexMasterModelImpl;
import com.stpl.app.service.persistence.CpiIndexMasterPersistence;

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
 * The persistence implementation for the cpi index master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CpiIndexMasterPersistence
 * @see com.stpl.app.service.persistence.CpiIndexMasterUtil
 * @generated
 */
@ProviderType
public class CpiIndexMasterPersistenceImpl extends BasePersistenceImpl<CpiIndexMaster>
	implements CpiIndexMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CpiIndexMasterUtil} to access the cpi index master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CpiIndexMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByStatus",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { String.class.getName() },
			CpiIndexMasterModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cpi index masters where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByStatus(String status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpi index masters where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByStatus(String status, int start, int end) {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByStatus(String status, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findByStatus(status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByStatus(String status, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CpiIndexMaster cpiIndexMaster : list) {
					if (!Objects.equals(status, cpiIndexMaster.getStatus())) {
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

			query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_STATUS_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStatus) {
					qPos.add(status);
				}

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Returns the first cpi index master in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByStatus_First(String status,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByStatus_First(status,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the first cpi index master in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByStatus_First(String status,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		List<CpiIndexMaster> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpi index master in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByStatus_Last(String status,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByStatus_Last(status,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the last cpi index master in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByStatus_Last(String status,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<CpiIndexMaster> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpi index masters before and after the current cpi index master in the ordered set where status = &#63;.
	 *
	 * @param cpiIndexMasterSid the primary key of the current cpi index master
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster[] findByStatus_PrevAndNext(int cpiIndexMasterSid,
		String status, OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = findByPrimaryKey(cpiIndexMasterSid);

		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster[] array = new CpiIndexMasterImpl[3];

			array[0] = getByStatus_PrevAndNext(session, cpiIndexMaster, status,
					orderByComparator, true);

			array[1] = cpiIndexMaster;

			array[2] = getByStatus_PrevAndNext(session, cpiIndexMaster, status,
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

	protected CpiIndexMaster getByStatus_PrevAndNext(Session session,
		CpiIndexMaster cpiIndexMaster, String status,
		OrderByComparator<CpiIndexMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

		boolean bindStatus = false;

		if (status == null) {
			query.append(_FINDER_COLUMN_STATUS_STATUS_1);
		}
		else if (status.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_STATUS_STATUS_3);
		}
		else {
			bindStatus = true;

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);
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
			query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStatus) {
			qPos.add(status);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpiIndexMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CpiIndexMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpi index masters where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(String status) {
		for (CpiIndexMaster cpiIndexMaster : findByStatus(status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching cpi index masters
	 */
	@Override
	public int countByStatus(String status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPIINDEXMASTER_WHERE);

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_STATUS_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATUS_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_STATUS_STATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStatus) {
					qPos.add(status);
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

	private static final String _FINDER_COLUMN_STATUS_STATUS_1 = "cpiIndexMaster.status IS NULL";
	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "cpiIndexMaster.status = ?";
	private static final String _FINDER_COLUMN_STATUS_STATUS_3 = "(cpiIndexMaster.status IS NULL OR cpiIndexMaster.status = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_INDEXID = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByIndexId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXID =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIndexId",
			new String[] { String.class.getName() },
			CpiIndexMasterModelImpl.INDEXID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_INDEXID = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIndexId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cpi index masters where indexId = &#63;.
	 *
	 * @param indexId the index ID
	 * @return the matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexId(String indexId) {
		return findByIndexId(indexId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpi index masters where indexId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexId the index ID
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexId(String indexId, int start, int end) {
		return findByIndexId(indexId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexId the index ID
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexId(String indexId, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findByIndexId(indexId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexId the index ID
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexId(String indexId, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXID;
			finderArgs = new Object[] { indexId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_INDEXID;
			finderArgs = new Object[] { indexId, start, end, orderByComparator };
		}

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CpiIndexMaster cpiIndexMaster : list) {
					if (!Objects.equals(indexId, cpiIndexMaster.getIndexId())) {
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

			query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

			boolean bindIndexId = false;

			if (indexId == null) {
				query.append(_FINDER_COLUMN_INDEXID_INDEXID_1);
			}
			else if (indexId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INDEXID_INDEXID_3);
			}
			else {
				bindIndexId = true;

				query.append(_FINDER_COLUMN_INDEXID_INDEXID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexId) {
					qPos.add(indexId);
				}

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Returns the first cpi index master in the ordered set where indexId = &#63;.
	 *
	 * @param indexId the index ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByIndexId_First(String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByIndexId_First(indexId,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexId=");
		msg.append(indexId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the first cpi index master in the ordered set where indexId = &#63;.
	 *
	 * @param indexId the index ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByIndexId_First(String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		List<CpiIndexMaster> list = findByIndexId(indexId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexId = &#63;.
	 *
	 * @param indexId the index ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByIndexId_Last(String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByIndexId_Last(indexId,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexId=");
		msg.append(indexId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexId = &#63;.
	 *
	 * @param indexId the index ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByIndexId_Last(String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		int count = countByIndexId(indexId);

		if (count == 0) {
			return null;
		}

		List<CpiIndexMaster> list = findByIndexId(indexId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63;.
	 *
	 * @param cpiIndexMasterSid the primary key of the current cpi index master
	 * @param indexId the index ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster[] findByIndexId_PrevAndNext(int cpiIndexMasterSid,
		String indexId, OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = findByPrimaryKey(cpiIndexMasterSid);

		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster[] array = new CpiIndexMasterImpl[3];

			array[0] = getByIndexId_PrevAndNext(session, cpiIndexMaster,
					indexId, orderByComparator, true);

			array[1] = cpiIndexMaster;

			array[2] = getByIndexId_PrevAndNext(session, cpiIndexMaster,
					indexId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CpiIndexMaster getByIndexId_PrevAndNext(Session session,
		CpiIndexMaster cpiIndexMaster, String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

		boolean bindIndexId = false;

		if (indexId == null) {
			query.append(_FINDER_COLUMN_INDEXID_INDEXID_1);
		}
		else if (indexId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_INDEXID_INDEXID_3);
		}
		else {
			bindIndexId = true;

			query.append(_FINDER_COLUMN_INDEXID_INDEXID_2);
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
			query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIndexId) {
			qPos.add(indexId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpiIndexMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CpiIndexMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpi index masters where indexId = &#63; from the database.
	 *
	 * @param indexId the index ID
	 */
	@Override
	public void removeByIndexId(String indexId) {
		for (CpiIndexMaster cpiIndexMaster : findByIndexId(indexId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters where indexId = &#63;.
	 *
	 * @param indexId the index ID
	 * @return the number of matching cpi index masters
	 */
	@Override
	public int countByIndexId(String indexId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_INDEXID;

		Object[] finderArgs = new Object[] { indexId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPIINDEXMASTER_WHERE);

			boolean bindIndexId = false;

			if (indexId == null) {
				query.append(_FINDER_COLUMN_INDEXID_INDEXID_1);
			}
			else if (indexId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INDEXID_INDEXID_3);
			}
			else {
				bindIndexId = true;

				query.append(_FINDER_COLUMN_INDEXID_INDEXID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexId) {
					qPos.add(indexId);
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

	private static final String _FINDER_COLUMN_INDEXID_INDEXID_1 = "cpiIndexMaster.indexId IS NULL";
	private static final String _FINDER_COLUMN_INDEXID_INDEXID_2 = "cpiIndexMaster.indexId = ?";
	private static final String _FINDER_COLUMN_INDEXID_INDEXID_3 = "(cpiIndexMaster.indexId IS NULL OR cpiIndexMaster.indexId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_INDEXVALUE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByIndexValue",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXVALUE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIndexValue",
			new String[] { String.class.getName() },
			CpiIndexMasterModelImpl.INDEXVALUE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_INDEXVALUE = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIndexValue",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cpi index masters where indexValue = &#63;.
	 *
	 * @param indexValue the index value
	 * @return the matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexValue(String indexValue) {
		return findByIndexValue(indexValue, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpi index masters where indexValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexValue the index value
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexValue(String indexValue, int start,
		int end) {
		return findByIndexValue(indexValue, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexValue the index value
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexValue(String indexValue, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findByIndexValue(indexValue, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexValue the index value
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexValue(String indexValue, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXVALUE;
			finderArgs = new Object[] { indexValue };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_INDEXVALUE;
			finderArgs = new Object[] { indexValue, start, end, orderByComparator };
		}

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CpiIndexMaster cpiIndexMaster : list) {
					if (!Objects.equals(indexValue,
								cpiIndexMaster.getIndexValue())) {
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

			query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

			boolean bindIndexValue = false;

			if (indexValue == null) {
				query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_1);
			}
			else if (indexValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_3);
			}
			else {
				bindIndexValue = true;

				query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexValue) {
					qPos.add(indexValue);
				}

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Returns the first cpi index master in the ordered set where indexValue = &#63;.
	 *
	 * @param indexValue the index value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByIndexValue_First(String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByIndexValue_First(indexValue,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexValue=");
		msg.append(indexValue);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the first cpi index master in the ordered set where indexValue = &#63;.
	 *
	 * @param indexValue the index value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByIndexValue_First(String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		List<CpiIndexMaster> list = findByIndexValue(indexValue, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexValue = &#63;.
	 *
	 * @param indexValue the index value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByIndexValue_Last(String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByIndexValue_Last(indexValue,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexValue=");
		msg.append(indexValue);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexValue = &#63;.
	 *
	 * @param indexValue the index value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByIndexValue_Last(String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		int count = countByIndexValue(indexValue);

		if (count == 0) {
			return null;
		}

		List<CpiIndexMaster> list = findByIndexValue(indexValue, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexValue = &#63;.
	 *
	 * @param cpiIndexMasterSid the primary key of the current cpi index master
	 * @param indexValue the index value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster[] findByIndexValue_PrevAndNext(
		int cpiIndexMasterSid, String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = findByPrimaryKey(cpiIndexMasterSid);

		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster[] array = new CpiIndexMasterImpl[3];

			array[0] = getByIndexValue_PrevAndNext(session, cpiIndexMaster,
					indexValue, orderByComparator, true);

			array[1] = cpiIndexMaster;

			array[2] = getByIndexValue_PrevAndNext(session, cpiIndexMaster,
					indexValue, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CpiIndexMaster getByIndexValue_PrevAndNext(Session session,
		CpiIndexMaster cpiIndexMaster, String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

		boolean bindIndexValue = false;

		if (indexValue == null) {
			query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_1);
		}
		else if (indexValue.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_3);
		}
		else {
			bindIndexValue = true;

			query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_2);
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
			query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIndexValue) {
			qPos.add(indexValue);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpiIndexMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CpiIndexMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpi index masters where indexValue = &#63; from the database.
	 *
	 * @param indexValue the index value
	 */
	@Override
	public void removeByIndexValue(String indexValue) {
		for (CpiIndexMaster cpiIndexMaster : findByIndexValue(indexValue,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters where indexValue = &#63;.
	 *
	 * @param indexValue the index value
	 * @return the number of matching cpi index masters
	 */
	@Override
	public int countByIndexValue(String indexValue) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_INDEXVALUE;

		Object[] finderArgs = new Object[] { indexValue };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPIINDEXMASTER_WHERE);

			boolean bindIndexValue = false;

			if (indexValue == null) {
				query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_1);
			}
			else if (indexValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_3);
			}
			else {
				bindIndexValue = true;

				query.append(_FINDER_COLUMN_INDEXVALUE_INDEXVALUE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexValue) {
					qPos.add(indexValue);
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

	private static final String _FINDER_COLUMN_INDEXVALUE_INDEXVALUE_1 = "cpiIndexMaster.indexValue IS NULL";
	private static final String _FINDER_COLUMN_INDEXVALUE_INDEXVALUE_2 = "cpiIndexMaster.indexValue = ?";
	private static final String _FINDER_COLUMN_INDEXVALUE_INDEXVALUE_3 = "(cpiIndexMaster.indexValue IS NULL OR cpiIndexMaster.indexValue = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_INDEXTYPE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByIndexType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXTYPE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIndexType",
			new String[] { String.class.getName() },
			CpiIndexMasterModelImpl.INDEXTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_INDEXTYPE = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIndexType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cpi index masters where indexType = &#63;.
	 *
	 * @param indexType the index type
	 * @return the matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexType(String indexType) {
		return findByIndexType(indexType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cpi index masters where indexType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexType the index type
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexType(String indexType, int start,
		int end) {
		return findByIndexType(indexType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexType the index type
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexType(String indexType, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findByIndexType(indexType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexType the index type
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByIndexType(String indexType, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXTYPE;
			finderArgs = new Object[] { indexType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_INDEXTYPE;
			finderArgs = new Object[] { indexType, start, end, orderByComparator };
		}

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CpiIndexMaster cpiIndexMaster : list) {
					if (!Objects.equals(indexType, cpiIndexMaster.getIndexType())) {
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

			query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

			boolean bindIndexType = false;

			if (indexType == null) {
				query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_1);
			}
			else if (indexType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_3);
			}
			else {
				bindIndexType = true;

				query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexType) {
					qPos.add(indexType);
				}

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Returns the first cpi index master in the ordered set where indexType = &#63;.
	 *
	 * @param indexType the index type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByIndexType_First(String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByIndexType_First(indexType,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexType=");
		msg.append(indexType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the first cpi index master in the ordered set where indexType = &#63;.
	 *
	 * @param indexType the index type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByIndexType_First(String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		List<CpiIndexMaster> list = findByIndexType(indexType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexType = &#63;.
	 *
	 * @param indexType the index type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByIndexType_Last(String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByIndexType_Last(indexType,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexType=");
		msg.append(indexType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexType = &#63;.
	 *
	 * @param indexType the index type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByIndexType_Last(String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		int count = countByIndexType(indexType);

		if (count == 0) {
			return null;
		}

		List<CpiIndexMaster> list = findByIndexType(indexType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexType = &#63;.
	 *
	 * @param cpiIndexMasterSid the primary key of the current cpi index master
	 * @param indexType the index type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster[] findByIndexType_PrevAndNext(int cpiIndexMasterSid,
		String indexType, OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = findByPrimaryKey(cpiIndexMasterSid);

		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster[] array = new CpiIndexMasterImpl[3];

			array[0] = getByIndexType_PrevAndNext(session, cpiIndexMaster,
					indexType, orderByComparator, true);

			array[1] = cpiIndexMaster;

			array[2] = getByIndexType_PrevAndNext(session, cpiIndexMaster,
					indexType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CpiIndexMaster getByIndexType_PrevAndNext(Session session,
		CpiIndexMaster cpiIndexMaster, String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

		boolean bindIndexType = false;

		if (indexType == null) {
			query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_1);
		}
		else if (indexType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_3);
		}
		else {
			bindIndexType = true;

			query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_2);
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
			query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIndexType) {
			qPos.add(indexType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpiIndexMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CpiIndexMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpi index masters where indexType = &#63; from the database.
	 *
	 * @param indexType the index type
	 */
	@Override
	public void removeByIndexType(String indexType) {
		for (CpiIndexMaster cpiIndexMaster : findByIndexType(indexType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters where indexType = &#63;.
	 *
	 * @param indexType the index type
	 * @return the number of matching cpi index masters
	 */
	@Override
	public int countByIndexType(String indexType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_INDEXTYPE;

		Object[] finderArgs = new Object[] { indexType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPIINDEXMASTER_WHERE);

			boolean bindIndexType = false;

			if (indexType == null) {
				query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_1);
			}
			else if (indexType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_3);
			}
			else {
				bindIndexType = true;

				query.append(_FINDER_COLUMN_INDEXTYPE_INDEXTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexType) {
					qPos.add(indexType);
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

	private static final String _FINDER_COLUMN_INDEXTYPE_INDEXTYPE_1 = "cpiIndexMaster.indexType IS NULL";
	private static final String _FINDER_COLUMN_INDEXTYPE_INDEXTYPE_2 = "cpiIndexMaster.indexType = ?";
	private static final String _FINDER_COLUMN_INDEXTYPE_INDEXTYPE_3 = "(cpiIndexMaster.indexType IS NULL OR cpiIndexMaster.indexType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EFFECTIVEDATE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEffectiveDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EFFECTIVEDATE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEffectiveDate",
			new String[] { Date.class.getName() },
			CpiIndexMasterModelImpl.EFFECTIVEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_EFFECTIVEDATE = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEffectiveDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the cpi index masters where effectiveDate = &#63;.
	 *
	 * @param effectiveDate the effective date
	 * @return the matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate) {
		return findByEffectiveDate(effectiveDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpi index masters where effectiveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param effectiveDate the effective date
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate,
		int start, int end) {
		return findByEffectiveDate(effectiveDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param effectiveDate the effective date
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate,
		int start, int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findByEffectiveDate(effectiveDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param effectiveDate the effective date
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate,
		int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EFFECTIVEDATE;
			finderArgs = new Object[] { effectiveDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EFFECTIVEDATE;
			finderArgs = new Object[] {
					effectiveDate,
					
					start, end, orderByComparator
				};
		}

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CpiIndexMaster cpiIndexMaster : list) {
					if (!Objects.equals(effectiveDate,
								cpiIndexMaster.getEffectiveDate())) {
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

			query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

			boolean bindEffectiveDate = false;

			if (effectiveDate == null) {
				query.append(_FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_1);
			}
			else {
				bindEffectiveDate = true;

				query.append(_FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEffectiveDate) {
					qPos.add(new Timestamp(effectiveDate.getTime()));
				}

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
	 *
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByEffectiveDate_First(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByEffectiveDate_First(effectiveDate,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("effectiveDate=");
		msg.append(effectiveDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
	 *
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByEffectiveDate_First(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		List<CpiIndexMaster> list = findByEffectiveDate(effectiveDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
	 *
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByEffectiveDate_Last(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByEffectiveDate_Last(effectiveDate,
				orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("effectiveDate=");
		msg.append(effectiveDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
	 *
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByEffectiveDate_Last(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		int count = countByEffectiveDate(effectiveDate);

		if (count == 0) {
			return null;
		}

		List<CpiIndexMaster> list = findByEffectiveDate(effectiveDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpi index masters before and after the current cpi index master in the ordered set where effectiveDate = &#63;.
	 *
	 * @param cpiIndexMasterSid the primary key of the current cpi index master
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster[] findByEffectiveDate_PrevAndNext(
		int cpiIndexMasterSid, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = findByPrimaryKey(cpiIndexMasterSid);

		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster[] array = new CpiIndexMasterImpl[3];

			array[0] = getByEffectiveDate_PrevAndNext(session, cpiIndexMaster,
					effectiveDate, orderByComparator, true);

			array[1] = cpiIndexMaster;

			array[2] = getByEffectiveDate_PrevAndNext(session, cpiIndexMaster,
					effectiveDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CpiIndexMaster getByEffectiveDate_PrevAndNext(Session session,
		CpiIndexMaster cpiIndexMaster, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

		boolean bindEffectiveDate = false;

		if (effectiveDate == null) {
			query.append(_FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_1);
		}
		else {
			bindEffectiveDate = true;

			query.append(_FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_2);
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
			query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEffectiveDate) {
			qPos.add(new Timestamp(effectiveDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpiIndexMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CpiIndexMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpi index masters where effectiveDate = &#63; from the database.
	 *
	 * @param effectiveDate the effective date
	 */
	@Override
	public void removeByEffectiveDate(Date effectiveDate) {
		for (CpiIndexMaster cpiIndexMaster : findByEffectiveDate(
				effectiveDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters where effectiveDate = &#63;.
	 *
	 * @param effectiveDate the effective date
	 * @return the number of matching cpi index masters
	 */
	@Override
	public int countByEffectiveDate(Date effectiveDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_EFFECTIVEDATE;

		Object[] finderArgs = new Object[] { effectiveDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CPIINDEXMASTER_WHERE);

			boolean bindEffectiveDate = false;

			if (effectiveDate == null) {
				query.append(_FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_1);
			}
			else {
				bindEffectiveDate = true;

				query.append(_FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEffectiveDate) {
					qPos.add(new Timestamp(effectiveDate.getTime()));
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

	private static final String _FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_1 = "cpiIndexMaster.effectiveDate IS NULL";
	private static final String _FINDER_COLUMN_EFFECTIVEDATE_EFFECTIVEDATE_2 = "cpiIndexMaster.effectiveDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CPIINDEXUNIQUE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCpiIndexUnique",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPIINDEXUNIQUE =
		new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED,
			CpiIndexMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCpiIndexUnique",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName()
			},
			CpiIndexMasterModelImpl.INDEXID_COLUMN_BITMASK |
			CpiIndexMasterModelImpl.STATUS_COLUMN_BITMASK |
			CpiIndexMasterModelImpl.INDEXTYPE_COLUMN_BITMASK |
			CpiIndexMasterModelImpl.EFFECTIVEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CPIINDEXUNIQUE = new FinderPath(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCpiIndexUnique",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @return the matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByCpiIndexUnique(String indexId,
		String status, String indexType, Date effectiveDate) {
		return findByCpiIndexUnique(indexId, status, indexType, effectiveDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByCpiIndexUnique(String indexId,
		String status, String indexType, Date effectiveDate, int start, int end) {
		return findByCpiIndexUnique(indexId, status, indexType, effectiveDate,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByCpiIndexUnique(String indexId,
		String status, String indexType, Date effectiveDate, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findByCpiIndexUnique(indexId, status, indexType, effectiveDate,
			start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findByCpiIndexUnique(String indexId,
		String status, String indexType, Date effectiveDate, int start,
		int end, OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPIINDEXUNIQUE;
			finderArgs = new Object[] { indexId, status, indexType, effectiveDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CPIINDEXUNIQUE;
			finderArgs = new Object[] {
					indexId, status, indexType, effectiveDate,
					
					start, end, orderByComparator
				};
		}

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CpiIndexMaster cpiIndexMaster : list) {
					if (!Objects.equals(indexId, cpiIndexMaster.getIndexId()) ||
							!Objects.equals(status, cpiIndexMaster.getStatus()) ||
							!Objects.equals(indexType,
								cpiIndexMaster.getIndexType()) ||
							!Objects.equals(effectiveDate,
								cpiIndexMaster.getEffectiveDate())) {
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

			query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

			boolean bindIndexId = false;

			if (indexId == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_1);
			}
			else if (indexId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_3);
			}
			else {
				bindIndexId = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_2);
			}

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_2);
			}

			boolean bindIndexType = false;

			if (indexType == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_1);
			}
			else if (indexType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_3);
			}
			else {
				bindIndexType = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_2);
			}

			boolean bindEffectiveDate = false;

			if (effectiveDate == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_1);
			}
			else {
				bindEffectiveDate = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexId) {
					qPos.add(indexId);
				}

				if (bindStatus) {
					qPos.add(status);
				}

				if (bindIndexType) {
					qPos.add(indexType);
				}

				if (bindEffectiveDate) {
					qPos.add(new Timestamp(effectiveDate.getTime()));
				}

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByCpiIndexUnique_First(String indexId,
		String status, String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByCpiIndexUnique_First(indexId,
				status, indexType, effectiveDate, orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexId=");
		msg.append(indexId);

		msg.append(", status=");
		msg.append(status);

		msg.append(", indexType=");
		msg.append(indexType);

		msg.append(", effectiveDate=");
		msg.append(effectiveDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByCpiIndexUnique_First(String indexId,
		String status, String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		List<CpiIndexMaster> list = findByCpiIndexUnique(indexId, status,
				indexType, effectiveDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master
	 * @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster findByCpiIndexUnique_Last(String indexId,
		String status, String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByCpiIndexUnique_Last(indexId,
				status, indexType, effectiveDate, orderByComparator);

		if (cpiIndexMaster != null) {
			return cpiIndexMaster;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("indexId=");
		msg.append(indexId);

		msg.append(", status=");
		msg.append(status);

		msg.append(", indexType=");
		msg.append(indexType);

		msg.append(", effectiveDate=");
		msg.append(effectiveDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCpiIndexMasterException(msg.toString());
	}

	/**
	 * Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	 */
	@Override
	public CpiIndexMaster fetchByCpiIndexUnique_Last(String indexId,
		String status, String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		int count = countByCpiIndexUnique(indexId, status, indexType,
				effectiveDate);

		if (count == 0) {
			return null;
		}

		List<CpiIndexMaster> list = findByCpiIndexUnique(indexId, status,
				indexType, effectiveDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param cpiIndexMasterSid the primary key of the current cpi index master
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster[] findByCpiIndexUnique_PrevAndNext(
		int cpiIndexMasterSid, String indexId, String status, String indexType,
		Date effectiveDate, OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = findByPrimaryKey(cpiIndexMasterSid);

		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster[] array = new CpiIndexMasterImpl[3];

			array[0] = getByCpiIndexUnique_PrevAndNext(session, cpiIndexMaster,
					indexId, status, indexType, effectiveDate,
					orderByComparator, true);

			array[1] = cpiIndexMaster;

			array[2] = getByCpiIndexUnique_PrevAndNext(session, cpiIndexMaster,
					indexId, status, indexType, effectiveDate,
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

	protected CpiIndexMaster getByCpiIndexUnique_PrevAndNext(Session session,
		CpiIndexMaster cpiIndexMaster, String indexId, String status,
		String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE);

		boolean bindIndexId = false;

		if (indexId == null) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_1);
		}
		else if (indexId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_3);
		}
		else {
			bindIndexId = true;

			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_2);
		}

		boolean bindStatus = false;

		if (status == null) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_1);
		}
		else if (status.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_3);
		}
		else {
			bindStatus = true;

			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_2);
		}

		boolean bindIndexType = false;

		if (indexType == null) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_1);
		}
		else if (indexType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_3);
		}
		else {
			bindIndexType = true;

			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_2);
		}

		boolean bindEffectiveDate = false;

		if (effectiveDate == null) {
			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_1);
		}
		else {
			bindEffectiveDate = true;

			query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_2);
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
			query.append(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIndexId) {
			qPos.add(indexId);
		}

		if (bindStatus) {
			qPos.add(status);
		}

		if (bindIndexType) {
			qPos.add(indexType);
		}

		if (bindEffectiveDate) {
			qPos.add(new Timestamp(effectiveDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cpiIndexMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CpiIndexMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63; from the database.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 */
	@Override
	public void removeByCpiIndexUnique(String indexId, String status,
		String indexType, Date effectiveDate) {
		for (CpiIndexMaster cpiIndexMaster : findByCpiIndexUnique(indexId,
				status, indexType, effectiveDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	 *
	 * @param indexId the index ID
	 * @param status the status
	 * @param indexType the index type
	 * @param effectiveDate the effective date
	 * @return the number of matching cpi index masters
	 */
	@Override
	public int countByCpiIndexUnique(String indexId, String status,
		String indexType, Date effectiveDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CPIINDEXUNIQUE;

		Object[] finderArgs = new Object[] {
				indexId, status, indexType, effectiveDate
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_CPIINDEXMASTER_WHERE);

			boolean bindIndexId = false;

			if (indexId == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_1);
			}
			else if (indexId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_3);
			}
			else {
				bindIndexId = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_2);
			}

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_2);
			}

			boolean bindIndexType = false;

			if (indexType == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_1);
			}
			else if (indexType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_3);
			}
			else {
				bindIndexType = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_2);
			}

			boolean bindEffectiveDate = false;

			if (effectiveDate == null) {
				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_1);
			}
			else {
				bindEffectiveDate = true;

				query.append(_FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIndexId) {
					qPos.add(indexId);
				}

				if (bindStatus) {
					qPos.add(status);
				}

				if (bindIndexType) {
					qPos.add(indexType);
				}

				if (bindEffectiveDate) {
					qPos.add(new Timestamp(effectiveDate.getTime()));
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

	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_1 = "cpiIndexMaster.indexId IS NULL AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_2 = "cpiIndexMaster.indexId = ? AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_INDEXID_3 = "(cpiIndexMaster.indexId IS NULL OR cpiIndexMaster.indexId = '') AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_1 = "cpiIndexMaster.status IS NULL AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_2 = "cpiIndexMaster.status = ? AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_STATUS_3 = "(cpiIndexMaster.status IS NULL OR cpiIndexMaster.status = '') AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_1 = "cpiIndexMaster.indexType IS NULL AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_2 = "cpiIndexMaster.indexType = ? AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_INDEXTYPE_3 = "(cpiIndexMaster.indexType IS NULL OR cpiIndexMaster.indexType = '') AND ";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_1 = "cpiIndexMaster.effectiveDate IS NULL";
	private static final String _FINDER_COLUMN_CPIINDEXUNIQUE_EFFECTIVEDATE_2 = "cpiIndexMaster.effectiveDate = ?";

	public CpiIndexMasterPersistenceImpl() {
		setModelClass(CpiIndexMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("effectiveDate", "EFFECTIVE_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("cpiIndexMasterSid", "CPI_INDEX_MASTER_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("indexType", "INDEX_TYPE");
			dbColumnNames.put("indexId", "INDEX_ID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("indexValue", "INDEX_VALUE");
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
	 * Caches the cpi index master in the entity cache if it is enabled.
	 *
	 * @param cpiIndexMaster the cpi index master
	 */
	@Override
	public void cacheResult(CpiIndexMaster cpiIndexMaster) {
		entityCache.putResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterImpl.class, cpiIndexMaster.getPrimaryKey(),
			cpiIndexMaster);

		cpiIndexMaster.resetOriginalValues();
	}

	/**
	 * Caches the cpi index masters in the entity cache if it is enabled.
	 *
	 * @param cpiIndexMasters the cpi index masters
	 */
	@Override
	public void cacheResult(List<CpiIndexMaster> cpiIndexMasters) {
		for (CpiIndexMaster cpiIndexMaster : cpiIndexMasters) {
			if (entityCache.getResult(
						CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
						CpiIndexMasterImpl.class, cpiIndexMaster.getPrimaryKey()) == null) {
				cacheResult(cpiIndexMaster);
			}
			else {
				cpiIndexMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cpi index masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CpiIndexMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cpi index master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CpiIndexMaster cpiIndexMaster) {
		entityCache.removeResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterImpl.class, cpiIndexMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CpiIndexMaster> cpiIndexMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CpiIndexMaster cpiIndexMaster : cpiIndexMasters) {
			entityCache.removeResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
				CpiIndexMasterImpl.class, cpiIndexMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cpi index master with the primary key. Does not add the cpi index master to the database.
	 *
	 * @param cpiIndexMasterSid the primary key for the new cpi index master
	 * @return the new cpi index master
	 */
	@Override
	public CpiIndexMaster create(int cpiIndexMasterSid) {
		CpiIndexMaster cpiIndexMaster = new CpiIndexMasterImpl();

		cpiIndexMaster.setNew(true);
		cpiIndexMaster.setPrimaryKey(cpiIndexMasterSid);

		return cpiIndexMaster;
	}

	/**
	 * Removes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpiIndexMasterSid the primary key of the cpi index master
	 * @return the cpi index master that was removed
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster remove(int cpiIndexMasterSid)
		throws NoSuchCpiIndexMasterException {
		return remove((Serializable)cpiIndexMasterSid);
	}

	/**
	 * Removes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cpi index master
	 * @return the cpi index master that was removed
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster remove(Serializable primaryKey)
		throws NoSuchCpiIndexMasterException {
		Session session = null;

		try {
			session = openSession();

			CpiIndexMaster cpiIndexMaster = (CpiIndexMaster)session.get(CpiIndexMasterImpl.class,
					primaryKey);

			if (cpiIndexMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCpiIndexMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cpiIndexMaster);
		}
		catch (NoSuchCpiIndexMasterException nsee) {
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
	protected CpiIndexMaster removeImpl(CpiIndexMaster cpiIndexMaster) {
		cpiIndexMaster = toUnwrappedModel(cpiIndexMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cpiIndexMaster)) {
				cpiIndexMaster = (CpiIndexMaster)session.get(CpiIndexMasterImpl.class,
						cpiIndexMaster.getPrimaryKeyObj());
			}

			if (cpiIndexMaster != null) {
				session.delete(cpiIndexMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cpiIndexMaster != null) {
			clearCache(cpiIndexMaster);
		}

		return cpiIndexMaster;
	}

	@Override
	public CpiIndexMaster updateImpl(CpiIndexMaster cpiIndexMaster) {
		cpiIndexMaster = toUnwrappedModel(cpiIndexMaster);

		boolean isNew = cpiIndexMaster.isNew();

		CpiIndexMasterModelImpl cpiIndexMasterModelImpl = (CpiIndexMasterModelImpl)cpiIndexMaster;

		Session session = null;

		try {
			session = openSession();

			if (cpiIndexMaster.isNew()) {
				session.save(cpiIndexMaster);

				cpiIndexMaster.setNew(false);
			}
			else {
				cpiIndexMaster = (CpiIndexMaster)session.merge(cpiIndexMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CpiIndexMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { cpiIndexMasterModelImpl.getStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
				args);

			args = new Object[] { cpiIndexMasterModelImpl.getIndexId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXID,
				args);

			args = new Object[] { cpiIndexMasterModelImpl.getIndexValue() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXVALUE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXVALUE,
				args);

			args = new Object[] { cpiIndexMasterModelImpl.getIndexType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXTYPE,
				args);

			args = new Object[] { cpiIndexMasterModelImpl.getEffectiveDate() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_EFFECTIVEDATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EFFECTIVEDATE,
				args);

			args = new Object[] {
					cpiIndexMasterModelImpl.getIndexId(),
					cpiIndexMasterModelImpl.getStatus(),
					cpiIndexMasterModelImpl.getIndexType(),
					cpiIndexMasterModelImpl.getEffectiveDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CPIINDEXUNIQUE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPIINDEXUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cpiIndexMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpiIndexMasterModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { cpiIndexMasterModelImpl.getStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((cpiIndexMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpiIndexMasterModelImpl.getOriginalIndexId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXID,
					args);

				args = new Object[] { cpiIndexMasterModelImpl.getIndexId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXID,
					args);
			}

			if ((cpiIndexMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXVALUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpiIndexMasterModelImpl.getOriginalIndexValue()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXVALUE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXVALUE,
					args);

				args = new Object[] { cpiIndexMasterModelImpl.getIndexValue() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXVALUE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXVALUE,
					args);
			}

			if ((cpiIndexMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpiIndexMasterModelImpl.getOriginalIndexType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXTYPE,
					args);

				args = new Object[] { cpiIndexMasterModelImpl.getIndexType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_INDEXTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_INDEXTYPE,
					args);
			}

			if ((cpiIndexMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EFFECTIVEDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpiIndexMasterModelImpl.getOriginalEffectiveDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EFFECTIVEDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EFFECTIVEDATE,
					args);

				args = new Object[] { cpiIndexMasterModelImpl.getEffectiveDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_EFFECTIVEDATE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EFFECTIVEDATE,
					args);
			}

			if ((cpiIndexMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPIINDEXUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cpiIndexMasterModelImpl.getOriginalIndexId(),
						cpiIndexMasterModelImpl.getOriginalStatus(),
						cpiIndexMasterModelImpl.getOriginalIndexType(),
						cpiIndexMasterModelImpl.getOriginalEffectiveDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPIINDEXUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPIINDEXUNIQUE,
					args);

				args = new Object[] {
						cpiIndexMasterModelImpl.getIndexId(),
						cpiIndexMasterModelImpl.getStatus(),
						cpiIndexMasterModelImpl.getIndexType(),
						cpiIndexMasterModelImpl.getEffectiveDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CPIINDEXUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CPIINDEXUNIQUE,
					args);
			}
		}

		entityCache.putResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
			CpiIndexMasterImpl.class, cpiIndexMaster.getPrimaryKey(),
			cpiIndexMaster, false);

		cpiIndexMaster.resetOriginalValues();

		return cpiIndexMaster;
	}

	protected CpiIndexMaster toUnwrappedModel(CpiIndexMaster cpiIndexMaster) {
		if (cpiIndexMaster instanceof CpiIndexMasterImpl) {
			return cpiIndexMaster;
		}

		CpiIndexMasterImpl cpiIndexMasterImpl = new CpiIndexMasterImpl();

		cpiIndexMasterImpl.setNew(cpiIndexMaster.isNew());
		cpiIndexMasterImpl.setPrimaryKey(cpiIndexMaster.getPrimaryKey());

		cpiIndexMasterImpl.setEffectiveDate(cpiIndexMaster.getEffectiveDate());
		cpiIndexMasterImpl.setCreatedBy(cpiIndexMaster.getCreatedBy());
		cpiIndexMasterImpl.setModifiedBy(cpiIndexMaster.getModifiedBy());
		cpiIndexMasterImpl.setCreatedDate(cpiIndexMaster.getCreatedDate());
		cpiIndexMasterImpl.setCpiIndexMasterSid(cpiIndexMaster.getCpiIndexMasterSid());
		cpiIndexMasterImpl.setBatchId(cpiIndexMaster.getBatchId());
		cpiIndexMasterImpl.setModifiedDate(cpiIndexMaster.getModifiedDate());
		cpiIndexMasterImpl.setStatus(cpiIndexMaster.getStatus());
		cpiIndexMasterImpl.setIndexType(cpiIndexMaster.getIndexType());
		cpiIndexMasterImpl.setIndexId(cpiIndexMaster.getIndexId());
		cpiIndexMasterImpl.setRecordLockStatus(cpiIndexMaster.isRecordLockStatus());
		cpiIndexMasterImpl.setIndexValue(cpiIndexMaster.getIndexValue());
		cpiIndexMasterImpl.setSource(cpiIndexMaster.getSource());
		cpiIndexMasterImpl.setInboundStatus(cpiIndexMaster.getInboundStatus());

		return cpiIndexMasterImpl;
	}

	/**
	 * Returns the cpi index master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cpi index master
	 * @return the cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCpiIndexMasterException {
		CpiIndexMaster cpiIndexMaster = fetchByPrimaryKey(primaryKey);

		if (cpiIndexMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCpiIndexMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cpiIndexMaster;
	}

	/**
	 * Returns the cpi index master with the primary key or throws a {@link NoSuchCpiIndexMasterException} if it could not be found.
	 *
	 * @param cpiIndexMasterSid the primary key of the cpi index master
	 * @return the cpi index master
	 * @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster findByPrimaryKey(int cpiIndexMasterSid)
		throws NoSuchCpiIndexMasterException {
		return findByPrimaryKey((Serializable)cpiIndexMasterSid);
	}

	/**
	 * Returns the cpi index master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cpi index master
	 * @return the cpi index master, or <code>null</code> if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
				CpiIndexMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CpiIndexMaster cpiIndexMaster = (CpiIndexMaster)serializable;

		if (cpiIndexMaster == null) {
			Session session = null;

			try {
				session = openSession();

				cpiIndexMaster = (CpiIndexMaster)session.get(CpiIndexMasterImpl.class,
						primaryKey);

				if (cpiIndexMaster != null) {
					cacheResult(cpiIndexMaster);
				}
				else {
					entityCache.putResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
						CpiIndexMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
					CpiIndexMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cpiIndexMaster;
	}

	/**
	 * Returns the cpi index master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cpiIndexMasterSid the primary key of the cpi index master
	 * @return the cpi index master, or <code>null</code> if a cpi index master with the primary key could not be found
	 */
	@Override
	public CpiIndexMaster fetchByPrimaryKey(int cpiIndexMasterSid) {
		return fetchByPrimaryKey((Serializable)cpiIndexMasterSid);
	}

	@Override
	public Map<Serializable, CpiIndexMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CpiIndexMaster> map = new HashMap<Serializable, CpiIndexMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CpiIndexMaster cpiIndexMaster = fetchByPrimaryKey(primaryKey);

			if (cpiIndexMaster != null) {
				map.put(primaryKey, cpiIndexMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
					CpiIndexMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CpiIndexMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CPIINDEXMASTER_WHERE_PKS_IN);

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

			for (CpiIndexMaster cpiIndexMaster : (List<CpiIndexMaster>)q.list()) {
				map.put(cpiIndexMaster.getPrimaryKeyObj(), cpiIndexMaster);

				cacheResult(cpiIndexMaster);

				uncachedPrimaryKeys.remove(cpiIndexMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CpiIndexMasterModelImpl.ENTITY_CACHE_ENABLED,
					CpiIndexMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the cpi index masters.
	 *
	 * @return the cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cpi index masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @return the range of cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cpi index masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findAll(int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cpi index masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cpi index masters
	 * @param end the upper bound of the range of cpi index masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cpi index masters
	 */
	@Override
	public List<CpiIndexMaster> findAll(int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
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

		List<CpiIndexMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CpiIndexMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CPIINDEXMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CPIINDEXMASTER;

				if (pagination) {
					sql = sql.concat(CpiIndexMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CpiIndexMaster>)QueryUtil.list(q,
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
	 * Removes all the cpi index masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CpiIndexMaster cpiIndexMaster : findAll()) {
			remove(cpiIndexMaster);
		}
	}

	/**
	 * Returns the number of cpi index masters.
	 *
	 * @return the number of cpi index masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CPIINDEXMASTER);

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
		return CpiIndexMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cpi index master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CpiIndexMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CPIINDEXMASTER = "SELECT cpiIndexMaster FROM CpiIndexMaster cpiIndexMaster";
	private static final String _SQL_SELECT_CPIINDEXMASTER_WHERE_PKS_IN = "SELECT cpiIndexMaster FROM CpiIndexMaster cpiIndexMaster WHERE CPI_INDEX_MASTER_SID IN (";
	private static final String _SQL_SELECT_CPIINDEXMASTER_WHERE = "SELECT cpiIndexMaster FROM CpiIndexMaster cpiIndexMaster WHERE ";
	private static final String _SQL_COUNT_CPIINDEXMASTER = "SELECT COUNT(cpiIndexMaster) FROM CpiIndexMaster cpiIndexMaster";
	private static final String _SQL_COUNT_CPIINDEXMASTER_WHERE = "SELECT COUNT(cpiIndexMaster) FROM CpiIndexMaster cpiIndexMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cpiIndexMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CpiIndexMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CpiIndexMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CpiIndexMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"effectiveDate", "createdBy", "modifiedBy", "createdDate",
				"cpiIndexMasterSid", "batchId", "modifiedDate", "status",
				"indexType", "indexId", "recordLockStatus", "indexValue",
				"source", "inboundStatus"
			});
}