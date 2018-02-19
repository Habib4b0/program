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

import com.stpl.app.exception.NoSuchActualsMasterException;
import com.stpl.app.model.ActualsMaster;
import com.stpl.app.model.impl.ActualsMasterImpl;
import com.stpl.app.model.impl.ActualsMasterModelImpl;
import com.stpl.app.service.persistence.ActualsMasterPersistence;

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
 * The persistence implementation for the actuals master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ActualsMasterPersistence
 * @see com.stpl.app.service.persistence.ActualsMasterUtil
 * @generated
 */
@ProviderType
public class ActualsMasterPersistenceImpl extends BasePersistenceImpl<ActualsMaster>
	implements ActualsMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ActualsMasterUtil} to access the actuals master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ActualsMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.ACCOUNTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountId(String accountId) {
		return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the actuals masters where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountId(String accountId, int start,
		int end) {
		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountId(String accountId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByAccountId(accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountId(String accountId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID;
			finderArgs = new Object[] { accountId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTID;
			finderArgs = new Object[] { accountId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(accountId, actualsMaster.getAccountId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindAccountId = false;

			if (accountId == null) {
				query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
			}
			else if (accountId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
			}
			else {
				bindAccountId = true;

				query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccountId) {
					qPos.add(accountId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByAccountId_First(String accountId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByAccountId_First(accountId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountId=");
		msg.append(accountId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByAccountId_First(String accountId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByAccountId(accountId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByAccountId_Last(String accountId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByAccountId_Last(accountId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountId=");
		msg.append(accountId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByAccountId_Last(String accountId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByAccountId(accountId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where accountId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByAccountId_PrevAndNext(int actualsMasterSid,
		String accountId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByAccountId_PrevAndNext(session, actualsMaster,
					accountId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByAccountId_PrevAndNext(session, actualsMaster,
					accountId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByAccountId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String accountId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindAccountId = false;

		if (accountId == null) {
			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
		}
		else if (accountId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
		}
		else {
			bindAccountId = true;

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAccountId) {
			qPos.add(accountId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(String accountId) {
		for (ActualsMaster actualsMaster : findByAccountId(accountId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByAccountId(String accountId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTID;

		Object[] finderArgs = new Object[] { accountId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindAccountId = false;

			if (accountId == null) {
				query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1);
			}
			else if (accountId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3);
			}
			else {
				bindAccountId = true;

				query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccountId) {
					qPos.add(accountId);
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

	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_1 = "actualsMaster.accountId IS NULL";
	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "actualsMaster.accountId = ?";
	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_3 = "(actualsMaster.accountId IS NULL OR actualsMaster.accountId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActualId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActualId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.ACTUALID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTUALID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActualId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualId(String actualId) {
		return findByActualId(actualId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the actuals masters where actualId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actualId the actual ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualId(String actualId, int start,
		int end) {
		return findByActualId(actualId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where actualId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actualId the actual ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualId(String actualId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByActualId(actualId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where actualId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actualId the actual ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualId(String actualId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID;
			finderArgs = new Object[] { actualId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALID;
			finderArgs = new Object[] { actualId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(actualId, actualsMaster.getActualId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindActualId = false;

			if (actualId == null) {
				query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_1);
			}
			else if (actualId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_3);
			}
			else {
				bindActualId = true;

				query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindActualId) {
					qPos.add(actualId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByActualId_First(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByActualId_First(actualId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actualId=");
		msg.append(actualId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByActualId_First(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByActualId(actualId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByActualId_Last(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByActualId_Last(actualId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actualId=");
		msg.append(actualId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByActualId_Last(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByActualId(actualId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByActualId(actualId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByActualId_PrevAndNext(int actualsMasterSid,
		String actualId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByActualId_PrevAndNext(session, actualsMaster,
					actualId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByActualId_PrevAndNext(session, actualsMaster,
					actualId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByActualId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String actualId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindActualId = false;

		if (actualId == null) {
			query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_1);
		}
		else if (actualId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_3);
		}
		else {
			bindActualId = true;

			query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindActualId) {
			qPos.add(actualId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where actualId = &#63; from the database.
	 *
	 * @param actualId the actual ID
	 */
	@Override
	public void removeByActualId(String actualId) {
		for (ActualsMaster actualsMaster : findByActualId(actualId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByActualId(String actualId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTUALID;

		Object[] finderArgs = new Object[] { actualId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindActualId = false;

			if (actualId == null) {
				query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_1);
			}
			else if (actualId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_3);
			}
			else {
				bindActualId = true;

				query.append(_FINDER_COLUMN_ACTUALID_ACTUALID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindActualId) {
					qPos.add(actualId);
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

	private static final String _FINDER_COLUMN_ACTUALID_ACTUALID_1 = "actualsMaster.actualId IS NULL";
	private static final String _FINDER_COLUMN_ACTUALID_ACTUALID_2 = "actualsMaster.actualId = ?";
	private static final String _FINDER_COLUMN_ACTUALID_ACTUALID_3 = "(actualsMaster.actualId IS NULL OR actualsMaster.actualId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DIVISIONID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByDivisionId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByDivisionId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.DIVISIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DIVISIONID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDivisionId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByDivisionId(String divisionId) {
		return findByDivisionId(divisionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where divisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param divisionId the division ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByDivisionId(String divisionId, int start,
		int end) {
		return findByDivisionId(divisionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where divisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param divisionId the division ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByDivisionId(String divisionId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByDivisionId(divisionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where divisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param divisionId the division ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByDivisionId(String divisionId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID;
			finderArgs = new Object[] { divisionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DIVISIONID;
			finderArgs = new Object[] { divisionId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(divisionId,
								actualsMaster.getDivisionId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindDivisionId = false;

			if (divisionId == null) {
				query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_1);
			}
			else if (divisionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_3);
			}
			else {
				bindDivisionId = true;

				query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDivisionId) {
					qPos.add(divisionId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByDivisionId_First(String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByDivisionId_First(divisionId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("divisionId=");
		msg.append(divisionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByDivisionId_First(String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByDivisionId(divisionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByDivisionId_Last(String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByDivisionId_Last(divisionId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("divisionId=");
		msg.append(divisionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByDivisionId_Last(String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByDivisionId(divisionId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByDivisionId(divisionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where divisionId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param divisionId the division ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByDivisionId_PrevAndNext(int actualsMasterSid,
		String divisionId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByDivisionId_PrevAndNext(session, actualsMaster,
					divisionId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByDivisionId_PrevAndNext(session, actualsMaster,
					divisionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByDivisionId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindDivisionId = false;

		if (divisionId == null) {
			query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_1);
		}
		else if (divisionId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_3);
		}
		else {
			bindDivisionId = true;

			query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindDivisionId) {
			qPos.add(divisionId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where divisionId = &#63; from the database.
	 *
	 * @param divisionId the division ID
	 */
	@Override
	public void removeByDivisionId(String divisionId) {
		for (ActualsMaster actualsMaster : findByDivisionId(divisionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where divisionId = &#63;.
	 *
	 * @param divisionId the division ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByDivisionId(String divisionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DIVISIONID;

		Object[] finderArgs = new Object[] { divisionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindDivisionId = false;

			if (divisionId == null) {
				query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_1);
			}
			else if (divisionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_3);
			}
			else {
				bindDivisionId = true;

				query.append(_FINDER_COLUMN_DIVISIONID_DIVISIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindDivisionId) {
					qPos.add(divisionId);
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

	private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_1 = "actualsMaster.divisionId IS NULL";
	private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_2 = "actualsMaster.divisionId = ?";
	private static final String _FINDER_COLUMN_DIVISIONID_DIVISIONID_3 = "(actualsMaster.divisionId IS NULL OR actualsMaster.divisionId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByContractId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByContractId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.CONTRACTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTRACTID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContractId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where contractId = &#63;.
	 *
	 * @param contractId the contract ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByContractId(String contractId) {
		return findByContractId(contractId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where contractId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractId the contract ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByContractId(String contractId, int start,
		int end) {
		return findByContractId(contractId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where contractId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractId the contract ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByContractId(String contractId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByContractId(contractId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where contractId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contractId the contract ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByContractId(String contractId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID;
			finderArgs = new Object[] { contractId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTRACTID;
			finderArgs = new Object[] { contractId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(contractId,
								actualsMaster.getContractId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindContractId = false;

			if (contractId == null) {
				query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_1);
			}
			else if (contractId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_3);
			}
			else {
				bindContractId = true;

				query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindContractId) {
					qPos.add(contractId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where contractId = &#63;.
	 *
	 * @param contractId the contract ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByContractId_First(String contractId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByContractId_First(contractId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractId=");
		msg.append(contractId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where contractId = &#63;.
	 *
	 * @param contractId the contract ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByContractId_First(String contractId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByContractId(contractId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where contractId = &#63;.
	 *
	 * @param contractId the contract ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByContractId_Last(String contractId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByContractId_Last(contractId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contractId=");
		msg.append(contractId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where contractId = &#63;.
	 *
	 * @param contractId the contract ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByContractId_Last(String contractId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByContractId(contractId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByContractId(contractId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where contractId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param contractId the contract ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByContractId_PrevAndNext(int actualsMasterSid,
		String contractId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByContractId_PrevAndNext(session, actualsMaster,
					contractId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByContractId_PrevAndNext(session, actualsMaster,
					contractId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByContractId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String contractId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindContractId = false;

		if (contractId == null) {
			query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_1);
		}
		else if (contractId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_3);
		}
		else {
			bindContractId = true;

			query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindContractId) {
			qPos.add(contractId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where contractId = &#63; from the database.
	 *
	 * @param contractId the contract ID
	 */
	@Override
	public void removeByContractId(String contractId) {
		for (ActualsMaster actualsMaster : findByContractId(contractId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where contractId = &#63;.
	 *
	 * @param contractId the contract ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByContractId(String contractId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTRACTID;

		Object[] finderArgs = new Object[] { contractId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindContractId = false;

			if (contractId == null) {
				query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_1);
			}
			else if (contractId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_3);
			}
			else {
				bindContractId = true;

				query.append(_FINDER_COLUMN_CONTRACTID_CONTRACTID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindContractId) {
					qPos.add(contractId);
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

	private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_1 = "actualsMaster.contractId IS NULL";
	private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_2 = "actualsMaster.contractId = ?";
	private static final String _FINDER_COLUMN_CONTRACTID_CONTRACTID_3 = "(actualsMaster.contractId IS NULL OR actualsMaster.contractId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROVISIONID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByProvisionId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByProvisionId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.PROVISIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROVISIONID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProvisionId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where provisionId = &#63;.
	 *
	 * @param provisionId the provision ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByProvisionId(String provisionId) {
		return findByProvisionId(provisionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where provisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param provisionId the provision ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByProvisionId(String provisionId, int start,
		int end) {
		return findByProvisionId(provisionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where provisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param provisionId the provision ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByProvisionId(String provisionId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByProvisionId(provisionId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where provisionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param provisionId the provision ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByProvisionId(String provisionId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID;
			finderArgs = new Object[] { provisionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROVISIONID;
			finderArgs = new Object[] { provisionId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(provisionId,
								actualsMaster.getProvisionId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindProvisionId = false;

			if (provisionId == null) {
				query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_1);
			}
			else if (provisionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_3);
			}
			else {
				bindProvisionId = true;

				query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProvisionId) {
					qPos.add(provisionId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where provisionId = &#63;.
	 *
	 * @param provisionId the provision ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByProvisionId_First(String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByProvisionId_First(provisionId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("provisionId=");
		msg.append(provisionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where provisionId = &#63;.
	 *
	 * @param provisionId the provision ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByProvisionId_First(String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByProvisionId(provisionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where provisionId = &#63;.
	 *
	 * @param provisionId the provision ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByProvisionId_Last(String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByProvisionId_Last(provisionId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("provisionId=");
		msg.append(provisionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where provisionId = &#63;.
	 *
	 * @param provisionId the provision ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByProvisionId_Last(String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByProvisionId(provisionId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByProvisionId(provisionId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where provisionId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param provisionId the provision ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByProvisionId_PrevAndNext(int actualsMasterSid,
		String provisionId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByProvisionId_PrevAndNext(session, actualsMaster,
					provisionId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByProvisionId_PrevAndNext(session, actualsMaster,
					provisionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByProvisionId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindProvisionId = false;

		if (provisionId == null) {
			query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_1);
		}
		else if (provisionId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_3);
		}
		else {
			bindProvisionId = true;

			query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindProvisionId) {
			qPos.add(provisionId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where provisionId = &#63; from the database.
	 *
	 * @param provisionId the provision ID
	 */
	@Override
	public void removeByProvisionId(String provisionId) {
		for (ActualsMaster actualsMaster : findByProvisionId(provisionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where provisionId = &#63;.
	 *
	 * @param provisionId the provision ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByProvisionId(String provisionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROVISIONID;

		Object[] finderArgs = new Object[] { provisionId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindProvisionId = false;

			if (provisionId == null) {
				query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_1);
			}
			else if (provisionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_3);
			}
			else {
				bindProvisionId = true;

				query.append(_FINDER_COLUMN_PROVISIONID_PROVISIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProvisionId) {
					qPos.add(provisionId);
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

	private static final String _FINDER_COLUMN_PROVISIONID_PROVISIONID_1 = "actualsMaster.provisionId IS NULL";
	private static final String _FINDER_COLUMN_PROVISIONID_PROVISIONID_2 = "actualsMaster.provisionId = ?";
	private static final String _FINDER_COLUMN_PROVISIONID_PROVISIONID_3 = "(actualsMaster.provisionId IS NULL OR actualsMaster.provisionId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.ITEMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemId(String itemId) {
		return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemId(String itemId, int start, int end) {
		return findByItemId(itemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemId(String itemId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return findByItemId(itemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemId(String itemId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
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

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(itemId, actualsMaster.getItemId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByItemId_First(String itemId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByItemId_First(itemId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemId=");
		msg.append(itemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByItemId_First(String itemId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByItemId(itemId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByItemId_Last(String itemId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByItemId_Last(itemId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemId=");
		msg.append(itemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByItemId_Last(String itemId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByItemId(itemId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByItemId(itemId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where itemId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByItemId_PrevAndNext(int actualsMasterSid,
		String itemId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByItemId_PrevAndNext(session, actualsMaster, itemId,
					orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByItemId_PrevAndNext(session, actualsMaster, itemId,
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

	protected ActualsMaster getByItemId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String itemId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	@Override
	public void removeByItemId(String itemId) {
		for (ActualsMaster actualsMaster : findByItemId(itemId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByItemId(String itemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMID;

		Object[] finderArgs = new Object[] { itemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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

	private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "actualsMaster.itemId IS NULL";
	private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "actualsMaster.itemId = ?";
	private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(actualsMaster.itemId IS NULL OR actualsMaster.itemId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMNO = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemNo", new String[] { String.class.getName() },
			ActualsMasterModelImpl.ITEMNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMNO = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemNo(String itemNo) {
		return findByItemNo(itemNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where itemNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemNo the item no
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemNo(String itemNo, int start, int end) {
		return findByItemNo(itemNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where itemNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemNo the item no
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemNo(String itemNo, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return findByItemNo(itemNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where itemNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemNo the item no
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByItemNo(String itemNo, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
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

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(itemNo, actualsMaster.getItemNo())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByItemNo_First(String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByItemNo_First(itemNo,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemNo=");
		msg.append(itemNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByItemNo_First(String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByItemNo(itemNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByItemNo_Last(String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByItemNo_Last(itemNo,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemNo=");
		msg.append(itemNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByItemNo_Last(String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByItemNo(itemNo);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByItemNo(itemNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where itemNo = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param itemNo the item no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByItemNo_PrevAndNext(int actualsMasterSid,
		String itemNo, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByItemNo_PrevAndNext(session, actualsMaster, itemNo,
					orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByItemNo_PrevAndNext(session, actualsMaster, itemNo,
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

	protected ActualsMaster getByItemNo_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where itemNo = &#63; from the database.
	 *
	 * @param itemNo the item no
	 */
	@Override
	public void removeByItemNo(String itemNo) {
		for (ActualsMaster actualsMaster : findByItemNo(itemNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where itemNo = &#63;.
	 *
	 * @param itemNo the item no
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByItemNo(String itemNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMNO;

		Object[] finderArgs = new Object[] { itemNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

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

	private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_1 = "actualsMaster.itemNo IS NULL";
	private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_2 = "actualsMaster.itemNo = ?";
	private static final String _FINDER_COLUMN_ITEMNO_ITEMNO_3 = "(actualsMaster.itemNo IS NULL OR actualsMaster.itemNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAccountNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAccountNo", new String[] { String.class.getName() },
			ActualsMasterModelImpl.ACCOUNTNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTNO = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where accountNo = &#63;.
	 *
	 * @param accountNo the account no
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountNo(String accountNo) {
		return findByAccountNo(accountNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the actuals masters where accountNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountNo the account no
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountNo(String accountNo, int start,
		int end) {
		return findByAccountNo(accountNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where accountNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountNo the account no
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountNo(String accountNo, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByAccountNo(accountNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where accountNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param accountNo the account no
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByAccountNo(String accountNo, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO;
			finderArgs = new Object[] { accountNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACCOUNTNO;
			finderArgs = new Object[] { accountNo, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(accountNo, actualsMaster.getAccountNo())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindAccountNo = false;

			if (accountNo == null) {
				query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
			}
			else if (accountNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
			}
			else {
				bindAccountNo = true;

				query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccountNo) {
					qPos.add(accountNo);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where accountNo = &#63;.
	 *
	 * @param accountNo the account no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByAccountNo_First(String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByAccountNo_First(accountNo,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountNo=");
		msg.append(accountNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where accountNo = &#63;.
	 *
	 * @param accountNo the account no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByAccountNo_First(String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByAccountNo(accountNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where accountNo = &#63;.
	 *
	 * @param accountNo the account no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByAccountNo_Last(String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByAccountNo_Last(accountNo,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("accountNo=");
		msg.append(accountNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where accountNo = &#63;.
	 *
	 * @param accountNo the account no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByAccountNo_Last(String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByAccountNo(accountNo);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByAccountNo(accountNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where accountNo = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param accountNo the account no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByAccountNo_PrevAndNext(int actualsMasterSid,
		String accountNo, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByAccountNo_PrevAndNext(session, actualsMaster,
					accountNo, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByAccountNo_PrevAndNext(session, actualsMaster,
					accountNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByAccountNo_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindAccountNo = false;

		if (accountNo == null) {
			query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
		}
		else if (accountNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
		}
		else {
			bindAccountNo = true;

			query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAccountNo) {
			qPos.add(accountNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where accountNo = &#63; from the database.
	 *
	 * @param accountNo the account no
	 */
	@Override
	public void removeByAccountNo(String accountNo) {
		for (ActualsMaster actualsMaster : findByAccountNo(accountNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where accountNo = &#63;.
	 *
	 * @param accountNo the account no
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByAccountNo(String accountNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCOUNTNO;

		Object[] finderArgs = new Object[] { accountNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindAccountNo = false;

			if (accountNo == null) {
				query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1);
			}
			else if (accountNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3);
			}
			else {
				bindAccountNo = true;

				query.append(_FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccountNo) {
					qPos.add(accountNo);
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

	private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_1 = "actualsMaster.accountNo IS NULL";
	private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_2 = "actualsMaster.accountNo = ?";
	private static final String _FINDER_COLUMN_ACCOUNTNO_ACCOUNTNO_3 = "(actualsMaster.accountNo IS NULL OR actualsMaster.accountNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMarketId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMarketId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.MARKETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MARKETID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMarketId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where marketId = &#63;.
	 *
	 * @param marketId the market ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByMarketId(String marketId) {
		return findByMarketId(marketId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the actuals masters where marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketId the market ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByMarketId(String marketId, int start,
		int end) {
		return findByMarketId(marketId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketId the market ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByMarketId(String marketId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByMarketId(marketId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where marketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param marketId the market ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByMarketId(String marketId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID;
			finderArgs = new Object[] { marketId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MARKETID;
			finderArgs = new Object[] { marketId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(marketId, actualsMaster.getMarketId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindMarketId = false;

			if (marketId == null) {
				query.append(_FINDER_COLUMN_MARKETID_MARKETID_1);
			}
			else if (marketId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MARKETID_MARKETID_3);
			}
			else {
				bindMarketId = true;

				query.append(_FINDER_COLUMN_MARKETID_MARKETID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMarketId) {
					qPos.add(marketId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where marketId = &#63;.
	 *
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByMarketId_First(String marketId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByMarketId_First(marketId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketId=");
		msg.append(marketId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where marketId = &#63;.
	 *
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByMarketId_First(String marketId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByMarketId(marketId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where marketId = &#63;.
	 *
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByMarketId_Last(String marketId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByMarketId_Last(marketId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("marketId=");
		msg.append(marketId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where marketId = &#63;.
	 *
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByMarketId_Last(String marketId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByMarketId(marketId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByMarketId(marketId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where marketId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param marketId the market ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByMarketId_PrevAndNext(int actualsMasterSid,
		String marketId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByMarketId_PrevAndNext(session, actualsMaster,
					marketId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByMarketId_PrevAndNext(session, actualsMaster,
					marketId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByMarketId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String marketId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindMarketId = false;

		if (marketId == null) {
			query.append(_FINDER_COLUMN_MARKETID_MARKETID_1);
		}
		else if (marketId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_MARKETID_MARKETID_3);
		}
		else {
			bindMarketId = true;

			query.append(_FINDER_COLUMN_MARKETID_MARKETID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindMarketId) {
			qPos.add(marketId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where marketId = &#63; from the database.
	 *
	 * @param marketId the market ID
	 */
	@Override
	public void removeByMarketId(String marketId) {
		for (ActualsMaster actualsMaster : findByMarketId(marketId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where marketId = &#63;.
	 *
	 * @param marketId the market ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByMarketId(String marketId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MARKETID;

		Object[] finderArgs = new Object[] { marketId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindMarketId = false;

			if (marketId == null) {
				query.append(_FINDER_COLUMN_MARKETID_MARKETID_1);
			}
			else if (marketId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MARKETID_MARKETID_3);
			}
			else {
				bindMarketId = true;

				query.append(_FINDER_COLUMN_MARKETID_MARKETID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMarketId) {
					qPos.add(marketId);
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

	private static final String _FINDER_COLUMN_MARKETID_MARKETID_1 = "actualsMaster.marketId IS NULL";
	private static final String _FINDER_COLUMN_MARKETID_MARKETID_2 = "actualsMaster.marketId = ?";
	private static final String _FINDER_COLUMN_MARKETID_MARKETID_3 = "(actualsMaster.marketId IS NULL OR actualsMaster.marketId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BRANDID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBrandId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBrandId", new String[] { String.class.getName() },
			ActualsMasterModelImpl.BRANDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BRANDID = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBrandId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where brandId = &#63;.
	 *
	 * @param brandId the brand ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByBrandId(String brandId) {
		return findByBrandId(brandId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where brandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param brandId the brand ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByBrandId(String brandId, int start, int end) {
		return findByBrandId(brandId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where brandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param brandId the brand ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByBrandId(String brandId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByBrandId(brandId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where brandId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param brandId the brand ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByBrandId(String brandId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID;
			finderArgs = new Object[] { brandId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BRANDID;
			finderArgs = new Object[] { brandId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(brandId, actualsMaster.getBrandId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindBrandId = false;

			if (brandId == null) {
				query.append(_FINDER_COLUMN_BRANDID_BRANDID_1);
			}
			else if (brandId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BRANDID_BRANDID_3);
			}
			else {
				bindBrandId = true;

				query.append(_FINDER_COLUMN_BRANDID_BRANDID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBrandId) {
					qPos.add(brandId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where brandId = &#63;.
	 *
	 * @param brandId the brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByBrandId_First(String brandId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByBrandId_First(brandId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("brandId=");
		msg.append(brandId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where brandId = &#63;.
	 *
	 * @param brandId the brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByBrandId_First(String brandId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByBrandId(brandId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where brandId = &#63;.
	 *
	 * @param brandId the brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByBrandId_Last(String brandId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByBrandId_Last(brandId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("brandId=");
		msg.append(brandId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where brandId = &#63;.
	 *
	 * @param brandId the brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByBrandId_Last(String brandId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByBrandId(brandId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByBrandId(brandId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where brandId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param brandId the brand ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByBrandId_PrevAndNext(int actualsMasterSid,
		String brandId, OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByBrandId_PrevAndNext(session, actualsMaster,
					brandId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByBrandId_PrevAndNext(session, actualsMaster,
					brandId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByBrandId_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String brandId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindBrandId = false;

		if (brandId == null) {
			query.append(_FINDER_COLUMN_BRANDID_BRANDID_1);
		}
		else if (brandId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BRANDID_BRANDID_3);
		}
		else {
			bindBrandId = true;

			query.append(_FINDER_COLUMN_BRANDID_BRANDID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindBrandId) {
			qPos.add(brandId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where brandId = &#63; from the database.
	 *
	 * @param brandId the brand ID
	 */
	@Override
	public void removeByBrandId(String brandId) {
		for (ActualsMaster actualsMaster : findByBrandId(brandId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where brandId = &#63;.
	 *
	 * @param brandId the brand ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByBrandId(String brandId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BRANDID;

		Object[] finderArgs = new Object[] { brandId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindBrandId = false;

			if (brandId == null) {
				query.append(_FINDER_COLUMN_BRANDID_BRANDID_1);
			}
			else if (brandId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BRANDID_BRANDID_3);
			}
			else {
				bindBrandId = true;

				query.append(_FINDER_COLUMN_BRANDID_BRANDID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBrandId) {
					qPos.add(brandId);
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

	private static final String _FINDER_COLUMN_BRANDID_BRANDID_1 = "actualsMaster.brandId IS NULL";
	private static final String _FINDER_COLUMN_BRANDID_BRANDID_2 = "actualsMaster.brandId = ?";
	private static final String _FINDER_COLUMN_BRANDID_BRANDID_3 = "(actualsMaster.brandId IS NULL OR actualsMaster.brandId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALSUNIQUE =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByActualsUnique",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE =
		new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED,
			ActualsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByActualsUnique", new String[] { String.class.getName() },
			ActualsMasterModelImpl.ACTUALID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACTUALSUNIQUE = new FinderPath(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActualsUnique",
			new String[] { String.class.getName() });

	/**
	 * Returns all the actuals masters where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @return the matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualsUnique(String actualId) {
		return findByActualsUnique(actualId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters where actualId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actualId the actual ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualsUnique(String actualId, int start,
		int end) {
		return findByActualsUnique(actualId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters where actualId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actualId the actual ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualsUnique(String actualId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return findByActualsUnique(actualId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters where actualId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param actualId the actual ID
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching actuals masters
	 */
	@Override
	public List<ActualsMaster> findByActualsUnique(String actualId, int start,
		int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE;
			finderArgs = new Object[] { actualId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTUALSUNIQUE;
			finderArgs = new Object[] { actualId, start, end, orderByComparator };
		}

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ActualsMaster actualsMaster : list) {
					if (!Objects.equals(actualId, actualsMaster.getActualId())) {
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

			query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

			boolean bindActualId = false;

			if (actualId == null) {
				query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1);
			}
			else if (actualId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3);
			}
			else {
				bindActualId = true;

				query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindActualId) {
					qPos.add(actualId);
				}

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByActualsUnique_First(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByActualsUnique_First(actualId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actualId=");
		msg.append(actualId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the first actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByActualsUnique_First(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		List<ActualsMaster> list = findByActualsUnique(actualId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master
	 * @throws NoSuchActualsMasterException if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster findByActualsUnique_Last(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByActualsUnique_Last(actualId,
				orderByComparator);

		if (actualsMaster != null) {
			return actualsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("actualId=");
		msg.append(actualId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActualsMasterException(msg.toString());
	}

	/**
	 * Returns the last actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	 */
	@Override
	public ActualsMaster fetchByActualsUnique_Last(String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		int count = countByActualsUnique(actualId);

		if (count == 0) {
			return null;
		}

		List<ActualsMaster> list = findByActualsUnique(actualId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
	 *
	 * @param actualsMasterSid the primary key of the current actuals master
	 * @param actualId the actual ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster[] findByActualsUnique_PrevAndNext(
		int actualsMasterSid, String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = findByPrimaryKey(actualsMasterSid);

		Session session = null;

		try {
			session = openSession();

			ActualsMaster[] array = new ActualsMasterImpl[3];

			array[0] = getByActualsUnique_PrevAndNext(session, actualsMaster,
					actualId, orderByComparator, true);

			array[1] = actualsMaster;

			array[2] = getByActualsUnique_PrevAndNext(session, actualsMaster,
					actualId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ActualsMaster getByActualsUnique_PrevAndNext(Session session,
		ActualsMaster actualsMaster, String actualId,
		OrderByComparator<ActualsMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE);

		boolean bindActualId = false;

		if (actualId == null) {
			query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1);
		}
		else if (actualId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3);
		}
		else {
			bindActualId = true;

			query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2);
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
			query.append(ActualsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindActualId) {
			qPos.add(actualId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(actualsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ActualsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the actuals masters where actualId = &#63; from the database.
	 *
	 * @param actualId the actual ID
	 */
	@Override
	public void removeByActualsUnique(String actualId) {
		for (ActualsMaster actualsMaster : findByActualsUnique(actualId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters where actualId = &#63;.
	 *
	 * @param actualId the actual ID
	 * @return the number of matching actuals masters
	 */
	@Override
	public int countByActualsUnique(String actualId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTUALSUNIQUE;

		Object[] finderArgs = new Object[] { actualId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ACTUALSMASTER_WHERE);

			boolean bindActualId = false;

			if (actualId == null) {
				query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1);
			}
			else if (actualId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3);
			}
			else {
				bindActualId = true;

				query.append(_FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindActualId) {
					qPos.add(actualId);
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

	private static final String _FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_1 = "actualsMaster.actualId IS NULL";
	private static final String _FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_2 = "actualsMaster.actualId = ?";
	private static final String _FINDER_COLUMN_ACTUALSUNIQUE_ACTUALID_3 = "(actualsMaster.actualId IS NULL OR actualsMaster.actualId = '')";

	public ActualsMasterPersistenceImpl() {
		setModelClass(ActualsMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("quantityInclusion", "QUANTITY_INCLUSION");
			dbColumnNames.put("mandatedDiscountAmount",
				"MANDATED_DISCOUNT_AMOUNT");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("analysisCode", "ANALYSIS_CODE");
			dbColumnNames.put("recordSequence", "RECORD_SEQUENCE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("settlementMethodNo", "SETTLEMENT_METHOD_NO");
			dbColumnNames.put("quantity", "QUANTITY");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("provisionClaimIndicator",
				"PROVISION_CLAIM_INDICATOR");
			dbColumnNames.put("dispensedDate", "DISPENSED_DATE");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("accrualActualEndDate", "ACCRUAL_ACTUAL_END_DATE");
			dbColumnNames.put("marketId", "MARKET_ID");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("accountName", "ACCOUNT_NAME");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("actualsMasterSid", "ACTUALS_MASTER_SID");
			dbColumnNames.put("acctIdentifierCodeQualifier",
				"ACCT_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("accrualProcessed", "ACCRUAL_PROCESSED");
			dbColumnNames.put("parentcomDivmktBrandProdkey",
				"PARENTCOM_DIVMKT_BRAND_PRODKEY");
			dbColumnNames.put("cashPaidDate", "CASH_PAID_DATE");
			dbColumnNames.put("salesAmount", "SALES_AMOUNT");
			dbColumnNames.put("accrualActualStartDate",
				"ACCRUAL_ACTUAL_START_DATE");
			dbColumnNames.put("settlementNo", "SETTLEMENT_NO");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("uploadDate", "UPLOAD_DATE");
			dbColumnNames.put("claimIndicator", "CLAIM_INDICATOR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("priceAdjustmentName", "PRICE_ADJUSTMENT_NAME");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("actualId", "ACTUAL_ID");
			dbColumnNames.put("provisionId", "PROVISION_ID");
			dbColumnNames.put("sentOut", "SENT_OUT");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("divisionId", "DIVISION_ID");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("programStateCode", "PROGRAM_STATE_CODE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("invoiceLineNo", "INVOICE_LINE_NO");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("comDivMktBrandProdKey",
				"COM_DIV_MKT_BRAND_PROD_KEY");
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
	 * Caches the actuals master in the entity cache if it is enabled.
	 *
	 * @param actualsMaster the actuals master
	 */
	@Override
	public void cacheResult(ActualsMaster actualsMaster) {
		entityCache.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterImpl.class, actualsMaster.getPrimaryKey(),
			actualsMaster);

		actualsMaster.resetOriginalValues();
	}

	/**
	 * Caches the actuals masters in the entity cache if it is enabled.
	 *
	 * @param actualsMasters the actuals masters
	 */
	@Override
	public void cacheResult(List<ActualsMaster> actualsMasters) {
		for (ActualsMaster actualsMaster : actualsMasters) {
			if (entityCache.getResult(
						ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
						ActualsMasterImpl.class, actualsMaster.getPrimaryKey()) == null) {
				cacheResult(actualsMaster);
			}
			else {
				actualsMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all actuals masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ActualsMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the actuals master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ActualsMaster actualsMaster) {
		entityCache.removeResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterImpl.class, actualsMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ActualsMaster> actualsMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ActualsMaster actualsMaster : actualsMasters) {
			entityCache.removeResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
				ActualsMasterImpl.class, actualsMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new actuals master with the primary key. Does not add the actuals master to the database.
	 *
	 * @param actualsMasterSid the primary key for the new actuals master
	 * @return the new actuals master
	 */
	@Override
	public ActualsMaster create(int actualsMasterSid) {
		ActualsMaster actualsMaster = new ActualsMasterImpl();

		actualsMaster.setNew(true);
		actualsMaster.setPrimaryKey(actualsMasterSid);

		return actualsMaster;
	}

	/**
	 * Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param actualsMasterSid the primary key of the actuals master
	 * @return the actuals master that was removed
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster remove(int actualsMasterSid)
		throws NoSuchActualsMasterException {
		return remove((Serializable)actualsMasterSid);
	}

	/**
	 * Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the actuals master
	 * @return the actuals master that was removed
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster remove(Serializable primaryKey)
		throws NoSuchActualsMasterException {
		Session session = null;

		try {
			session = openSession();

			ActualsMaster actualsMaster = (ActualsMaster)session.get(ActualsMasterImpl.class,
					primaryKey);

			if (actualsMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActualsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(actualsMaster);
		}
		catch (NoSuchActualsMasterException nsee) {
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
	protected ActualsMaster removeImpl(ActualsMaster actualsMaster) {
		actualsMaster = toUnwrappedModel(actualsMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(actualsMaster)) {
				actualsMaster = (ActualsMaster)session.get(ActualsMasterImpl.class,
						actualsMaster.getPrimaryKeyObj());
			}

			if (actualsMaster != null) {
				session.delete(actualsMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (actualsMaster != null) {
			clearCache(actualsMaster);
		}

		return actualsMaster;
	}

	@Override
	public ActualsMaster updateImpl(ActualsMaster actualsMaster) {
		actualsMaster = toUnwrappedModel(actualsMaster);

		boolean isNew = actualsMaster.isNew();

		ActualsMasterModelImpl actualsMasterModelImpl = (ActualsMasterModelImpl)actualsMaster;

		Session session = null;

		try {
			session = openSession();

			if (actualsMaster.isNew()) {
				session.save(actualsMaster);

				actualsMaster.setNew(false);
			}
			else {
				actualsMaster = (ActualsMaster)session.merge(actualsMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ActualsMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { actualsMasterModelImpl.getAccountId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
				args);

			args = new Object[] { actualsMasterModelImpl.getActualId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTUALID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID,
				args);

			args = new Object[] { actualsMasterModelImpl.getDivisionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_DIVISIONID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID,
				args);

			args = new Object[] { actualsMasterModelImpl.getContractId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
				args);

			args = new Object[] { actualsMasterModelImpl.getProvisionId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PROVISIONID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID,
				args);

			args = new Object[] { actualsMasterModelImpl.getItemId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
				args);

			args = new Object[] { actualsMasterModelImpl.getItemNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
				args);

			args = new Object[] { actualsMasterModelImpl.getAccountNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
				args);

			args = new Object[] { actualsMasterModelImpl.getMarketId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID,
				args);

			args = new Object[] { actualsMasterModelImpl.getBrandId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BRANDID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID,
				args);

			args = new Object[] { actualsMasterModelImpl.getActualId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTUALSUNIQUE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalAccountId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
					args);

				args = new Object[] { actualsMasterModelImpl.getAccountId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalActualId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTUALID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID,
					args);

				args = new Object[] { actualsMasterModelImpl.getActualId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTUALID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalDivisionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DIVISIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID,
					args);

				args = new Object[] { actualsMasterModelImpl.getDivisionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_DIVISIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DIVISIONID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalContractId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
					args);

				args = new Object[] { actualsMasterModelImpl.getContractId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTRACTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTRACTID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalProvisionId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROVISIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID,
					args);

				args = new Object[] { actualsMasterModelImpl.getProvisionId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROVISIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROVISIONID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
					args);

				args = new Object[] { actualsMasterModelImpl.getItemId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalItemNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
					args);

				args = new Object[] { actualsMasterModelImpl.getItemNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMNO,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalAccountNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
					args);

				args = new Object[] { actualsMasterModelImpl.getAccountNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACCOUNTNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACCOUNTNO,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalMarketId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID,
					args);

				args = new Object[] { actualsMasterModelImpl.getMarketId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MARKETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MARKETID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalBrandId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BRANDID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID,
					args);

				args = new Object[] { actualsMasterModelImpl.getBrandId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BRANDID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BRANDID,
					args);
			}

			if ((actualsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						actualsMasterModelImpl.getOriginalActualId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTUALSUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE,
					args);

				args = new Object[] { actualsMasterModelImpl.getActualId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ACTUALSUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTUALSUNIQUE,
					args);
			}
		}

		entityCache.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ActualsMasterImpl.class, actualsMaster.getPrimaryKey(),
			actualsMaster, false);

		actualsMaster.resetOriginalValues();

		return actualsMaster;
	}

	protected ActualsMaster toUnwrappedModel(ActualsMaster actualsMaster) {
		if (actualsMaster instanceof ActualsMasterImpl) {
			return actualsMaster;
		}

		ActualsMasterImpl actualsMasterImpl = new ActualsMasterImpl();

		actualsMasterImpl.setNew(actualsMaster.isNew());
		actualsMasterImpl.setPrimaryKey(actualsMaster.getPrimaryKey());

		actualsMasterImpl.setQuantityInclusion(actualsMaster.getQuantityInclusion());
		actualsMasterImpl.setMandatedDiscountAmount(actualsMaster.getMandatedDiscountAmount());
		actualsMasterImpl.setItemNo(actualsMaster.getItemNo());
		actualsMasterImpl.setAnalysisCode(actualsMaster.getAnalysisCode());
		actualsMasterImpl.setRecordSequence(actualsMaster.getRecordSequence());
		actualsMasterImpl.setModifiedBy(actualsMaster.getModifiedBy());
		actualsMasterImpl.setSettlementMethodNo(actualsMaster.getSettlementMethodNo());
		actualsMasterImpl.setQuantity(actualsMaster.getQuantity());
		actualsMasterImpl.setAccountId(actualsMaster.getAccountId());
		actualsMasterImpl.setCreatedDate(actualsMaster.getCreatedDate());
		actualsMasterImpl.setProvisionClaimIndicator(actualsMaster.getProvisionClaimIndicator());
		actualsMasterImpl.setDispensedDate(actualsMaster.getDispensedDate());
		actualsMasterImpl.setIsActive(actualsMaster.getIsActive());
		actualsMasterImpl.setBatchId(actualsMaster.getBatchId());
		actualsMasterImpl.setAccrualActualEndDate(actualsMaster.getAccrualActualEndDate());
		actualsMasterImpl.setMarketId(actualsMaster.getMarketId());
		actualsMasterImpl.setBrandId(actualsMaster.getBrandId());
		actualsMasterImpl.setAccountName(actualsMaster.getAccountName());
		actualsMasterImpl.setAmount(actualsMaster.getAmount());
		actualsMasterImpl.setActualsMasterSid(actualsMaster.getActualsMasterSid());
		actualsMasterImpl.setAcctIdentifierCodeQualifier(actualsMaster.getAcctIdentifierCodeQualifier());
		actualsMasterImpl.setOrganizationKey(actualsMaster.getOrganizationKey());
		actualsMasterImpl.setCreatedBy(actualsMaster.getCreatedBy());
		actualsMasterImpl.setAccrualProcessed(actualsMaster.getAccrualProcessed());
		actualsMasterImpl.setParentcomDivmktBrandProdkey(actualsMaster.getParentcomDivmktBrandProdkey());
		actualsMasterImpl.setCashPaidDate(actualsMaster.getCashPaidDate());
		actualsMasterImpl.setSalesAmount(actualsMaster.getSalesAmount());
		actualsMasterImpl.setAccrualActualStartDate(actualsMaster.getAccrualActualStartDate());
		actualsMasterImpl.setSettlementNo(actualsMaster.getSettlementNo());
		actualsMasterImpl.setPrice(actualsMaster.getPrice());
		actualsMasterImpl.setUploadDate(actualsMaster.getUploadDate());
		actualsMasterImpl.setClaimIndicator(actualsMaster.getClaimIndicator());
		actualsMasterImpl.setItemId(actualsMaster.getItemId());
		actualsMasterImpl.setPriceAdjustmentName(actualsMaster.getPriceAdjustmentName());
		actualsMasterImpl.setContractId(actualsMaster.getContractId());
		actualsMasterImpl.setModifiedDate(actualsMaster.getModifiedDate());
		actualsMasterImpl.setActualId(actualsMaster.getActualId());
		actualsMasterImpl.setProvisionId(actualsMaster.getProvisionId());
		actualsMasterImpl.setSentOut(actualsMaster.getSentOut());
		actualsMasterImpl.setRecordLockStatus(actualsMaster.isRecordLockStatus());
		actualsMasterImpl.setDivisionId(actualsMaster.getDivisionId());
		actualsMasterImpl.setItemIdentifierCodeQualifier(actualsMaster.getItemIdentifierCodeQualifier());
		actualsMasterImpl.setProgramStateCode(actualsMaster.getProgramStateCode());
		actualsMasterImpl.setSource(actualsMaster.getSource());
		actualsMasterImpl.setInvoiceLineNo(actualsMaster.getInvoiceLineNo());
		actualsMasterImpl.setAccountNo(actualsMaster.getAccountNo());
		actualsMasterImpl.setComDivMktBrandProdKey(actualsMaster.getComDivMktBrandProdKey());
		actualsMasterImpl.setInboundStatus(actualsMaster.getInboundStatus());

		return actualsMasterImpl;
	}

	/**
	 * Returns the actuals master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the actuals master
	 * @return the actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActualsMasterException {
		ActualsMaster actualsMaster = fetchByPrimaryKey(primaryKey);

		if (actualsMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActualsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return actualsMaster;
	}

	/**
	 * Returns the actuals master with the primary key or throws a {@link NoSuchActualsMasterException} if it could not be found.
	 *
	 * @param actualsMasterSid the primary key of the actuals master
	 * @return the actuals master
	 * @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster findByPrimaryKey(int actualsMasterSid)
		throws NoSuchActualsMasterException {
		return findByPrimaryKey((Serializable)actualsMasterSid);
	}

	/**
	 * Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the actuals master
	 * @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
				ActualsMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ActualsMaster actualsMaster = (ActualsMaster)serializable;

		if (actualsMaster == null) {
			Session session = null;

			try {
				session = openSession();

				actualsMaster = (ActualsMaster)session.get(ActualsMasterImpl.class,
						primaryKey);

				if (actualsMaster != null) {
					cacheResult(actualsMaster);
				}
				else {
					entityCache.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
						ActualsMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
					ActualsMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return actualsMaster;
	}

	/**
	 * Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param actualsMasterSid the primary key of the actuals master
	 * @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
	 */
	@Override
	public ActualsMaster fetchByPrimaryKey(int actualsMasterSid) {
		return fetchByPrimaryKey((Serializable)actualsMasterSid);
	}

	@Override
	public Map<Serializable, ActualsMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ActualsMaster> map = new HashMap<Serializable, ActualsMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ActualsMaster actualsMaster = fetchByPrimaryKey(primaryKey);

			if (actualsMaster != null) {
				map.put(primaryKey, actualsMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
					ActualsMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ActualsMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACTUALSMASTER_WHERE_PKS_IN);

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

			for (ActualsMaster actualsMaster : (List<ActualsMaster>)q.list()) {
				map.put(actualsMaster.getPrimaryKeyObj(), actualsMaster);

				cacheResult(actualsMaster);

				uncachedPrimaryKeys.remove(actualsMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ActualsMasterModelImpl.ENTITY_CACHE_ENABLED,
					ActualsMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the actuals masters.
	 *
	 * @return the actuals masters
	 */
	@Override
	public List<ActualsMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the actuals masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @return the range of actuals masters
	 */
	@Override
	public List<ActualsMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the actuals masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of actuals masters
	 */
	@Override
	public List<ActualsMaster> findAll(int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the actuals masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of actuals masters
	 * @param end the upper bound of the range of actuals masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of actuals masters
	 */
	@Override
	public List<ActualsMaster> findAll(int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
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

		List<ActualsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ActualsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACTUALSMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACTUALSMASTER;

				if (pagination) {
					sql = sql.concat(ActualsMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ActualsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the actuals masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ActualsMaster actualsMaster : findAll()) {
			remove(actualsMaster);
		}
	}

	/**
	 * Returns the number of actuals masters.
	 *
	 * @return the number of actuals masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACTUALSMASTER);

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
		return ActualsMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the actuals master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ActualsMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACTUALSMASTER = "SELECT actualsMaster FROM ActualsMaster actualsMaster";
	private static final String _SQL_SELECT_ACTUALSMASTER_WHERE_PKS_IN = "SELECT actualsMaster FROM ActualsMaster actualsMaster WHERE ACTUALS_MASTER_SID IN (";
	private static final String _SQL_SELECT_ACTUALSMASTER_WHERE = "SELECT actualsMaster FROM ActualsMaster actualsMaster WHERE ";
	private static final String _SQL_COUNT_ACTUALSMASTER = "SELECT COUNT(actualsMaster) FROM ActualsMaster actualsMaster";
	private static final String _SQL_COUNT_ACTUALSMASTER_WHERE = "SELECT COUNT(actualsMaster) FROM ActualsMaster actualsMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "actualsMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ActualsMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ActualsMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ActualsMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"quantityInclusion", "mandatedDiscountAmount", "itemNo",
				"analysisCode", "recordSequence", "modifiedBy",
				"settlementMethodNo", "quantity", "accountId", "createdDate",
				"provisionClaimIndicator", "dispensedDate", "isActive",
				"batchId", "accrualActualEndDate", "marketId", "brandId",
				"accountName", "amount", "actualsMasterSid",
				"acctIdentifierCodeQualifier", "organizationKey", "createdBy",
				"accrualProcessed", "parentcomDivmktBrandProdkey",
				"cashPaidDate", "salesAmount", "accrualActualStartDate",
				"settlementNo", "price", "uploadDate", "claimIndicator",
				"itemId", "priceAdjustmentName", "contractId", "modifiedDate",
				"actualId", "provisionId", "sentOut", "recordLockStatus",
				"divisionId", "itemIdentifierCodeQualifier", "programStateCode",
				"source", "invoiceLineNo", "accountNo", "comDivMktBrandProdKey",
				"inboundStatus"
			});
}