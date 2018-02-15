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

import com.stpl.app.exception.NoSuchFormulaDetailsMasterException;
import com.stpl.app.model.FormulaDetailsMaster;
import com.stpl.app.model.impl.FormulaDetailsMasterImpl;
import com.stpl.app.model.impl.FormulaDetailsMasterModelImpl;
import com.stpl.app.service.persistence.FormulaDetailsMasterPersistence;

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
 * The persistence implementation for the formula details master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FormulaDetailsMasterPersistence
 * @see com.stpl.app.service.persistence.FormulaDetailsMasterUtil
 * @generated
 */
@ProviderType
public class FormulaDetailsMasterPersistenceImpl extends BasePersistenceImpl<FormulaDetailsMaster>
	implements FormulaDetailsMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FormulaDetailsMasterUtil} to access the formula details master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FormulaDetailsMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMULAID =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFormulaId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULAID =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFormulaId",
			new String[] { String.class.getName() },
			FormulaDetailsMasterModelImpl.FORMULAID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FORMULAID = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormulaId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the formula details masters where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaId(String formulaId) {
		return findByFormulaId(formulaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the formula details masters where formulaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaId the formula ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaId(String formulaId,
		int start, int end) {
		return findByFormulaId(formulaId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where formulaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaId the formula ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaId(String formulaId,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByFormulaId(formulaId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where formulaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaId the formula ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaId(String formulaId,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULAID;
			finderArgs = new Object[] { formulaId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMULAID;
			finderArgs = new Object[] { formulaId, start, end, orderByComparator };
		}

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(formulaId,
								formulaDetailsMaster.getFormulaId())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

			boolean bindFormulaId = false;

			if (formulaId == null) {
				query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_1);
			}
			else if (formulaId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_3);
			}
			else {
				bindFormulaId = true;

				query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFormulaId) {
					qPos.add(formulaId);
				}

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByFormulaId_First(String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByFormulaId_First(formulaId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formulaId=");
		msg.append(formulaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByFormulaId_First(String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByFormulaId(formulaId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByFormulaId_Last(String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByFormulaId_Last(formulaId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formulaId=");
		msg.append(formulaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByFormulaId_Last(String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByFormulaId(formulaId);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByFormulaId(formulaId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByFormulaId_PrevAndNext(
		int formulaDetailsMasterSid, String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByFormulaId_PrevAndNext(session,
					formulaDetailsMaster, formulaId, orderByComparator, true);

			array[1] = formulaDetailsMaster;

			array[2] = getByFormulaId_PrevAndNext(session,
					formulaDetailsMaster, formulaId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormulaDetailsMaster getByFormulaId_PrevAndNext(Session session,
		FormulaDetailsMaster formulaDetailsMaster, String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

		boolean bindFormulaId = false;

		if (formulaId == null) {
			query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_1);
		}
		else if (formulaId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_3);
		}
		else {
			bindFormulaId = true;

			query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_2);
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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindFormulaId) {
			qPos.add(formulaId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where formulaId = &#63; from the database.
	 *
	 * @param formulaId the formula ID
	 */
	@Override
	public void removeByFormulaId(String formulaId) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByFormulaId(
				formulaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByFormulaId(String formulaId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FORMULAID;

		Object[] finderArgs = new Object[] { formulaId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

			boolean bindFormulaId = false;

			if (formulaId == null) {
				query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_1);
			}
			else if (formulaId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_3);
			}
			else {
				bindFormulaId = true;

				query.append(_FINDER_COLUMN_FORMULAID_FORMULAID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFormulaId) {
					qPos.add(formulaId);
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

	private static final String _FINDER_COLUMN_FORMULAID_FORMULAID_1 = "formulaDetailsMaster.formulaId IS NULL";
	private static final String _FINDER_COLUMN_FORMULAID_FORMULAID_2 = "formulaDetailsMaster.formulaId = ?";
	private static final String _FINDER_COLUMN_FORMULAID_FORMULAID_3 = "(formulaDetailsMaster.formulaId IS NULL OR formulaDetailsMaster.formulaId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMULANO =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFormulaNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULANO =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFormulaNo",
			new String[] { String.class.getName() },
			FormulaDetailsMasterModelImpl.FORMULANO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FORMULANO = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormulaNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the formula details masters where formulaNo = &#63;.
	 *
	 * @param formulaNo the formula no
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaNo(String formulaNo) {
		return findByFormulaNo(formulaNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the formula details masters where formulaNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaNo the formula no
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaNo(String formulaNo,
		int start, int end) {
		return findByFormulaNo(formulaNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where formulaNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaNo the formula no
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaNo(String formulaNo,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByFormulaNo(formulaNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where formulaNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaNo the formula no
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaNo(String formulaNo,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULANO;
			finderArgs = new Object[] { formulaNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMULANO;
			finderArgs = new Object[] { formulaNo, start, end, orderByComparator };
		}

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(formulaNo,
								formulaDetailsMaster.getFormulaNo())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

			boolean bindFormulaNo = false;

			if (formulaNo == null) {
				query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_1);
			}
			else if (formulaNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_3);
			}
			else {
				bindFormulaNo = true;

				query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFormulaNo) {
					qPos.add(formulaNo);
				}

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where formulaNo = &#63;.
	 *
	 * @param formulaNo the formula no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByFormulaNo_First(String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByFormulaNo_First(formulaNo,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formulaNo=");
		msg.append(formulaNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where formulaNo = &#63;.
	 *
	 * @param formulaNo the formula no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByFormulaNo_First(String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByFormulaNo(formulaNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where formulaNo = &#63;.
	 *
	 * @param formulaNo the formula no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByFormulaNo_Last(String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByFormulaNo_Last(formulaNo,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formulaNo=");
		msg.append(formulaNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where formulaNo = &#63;.
	 *
	 * @param formulaNo the formula no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByFormulaNo_Last(String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByFormulaNo(formulaNo);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByFormulaNo(formulaNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where formulaNo = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param formulaNo the formula no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByFormulaNo_PrevAndNext(
		int formulaDetailsMasterSid, String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByFormulaNo_PrevAndNext(session,
					formulaDetailsMaster, formulaNo, orderByComparator, true);

			array[1] = formulaDetailsMaster;

			array[2] = getByFormulaNo_PrevAndNext(session,
					formulaDetailsMaster, formulaNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormulaDetailsMaster getByFormulaNo_PrevAndNext(Session session,
		FormulaDetailsMaster formulaDetailsMaster, String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

		boolean bindFormulaNo = false;

		if (formulaNo == null) {
			query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_1);
		}
		else if (formulaNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_3);
		}
		else {
			bindFormulaNo = true;

			query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_2);
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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindFormulaNo) {
			qPos.add(formulaNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where formulaNo = &#63; from the database.
	 *
	 * @param formulaNo the formula no
	 */
	@Override
	public void removeByFormulaNo(String formulaNo) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByFormulaNo(
				formulaNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where formulaNo = &#63;.
	 *
	 * @param formulaNo the formula no
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByFormulaNo(String formulaNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FORMULANO;

		Object[] finderArgs = new Object[] { formulaNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

			boolean bindFormulaNo = false;

			if (formulaNo == null) {
				query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_1);
			}
			else if (formulaNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_3);
			}
			else {
				bindFormulaNo = true;

				query.append(_FINDER_COLUMN_FORMULANO_FORMULANO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFormulaNo) {
					qPos.add(formulaNo);
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

	private static final String _FINDER_COLUMN_FORMULANO_FORMULANO_1 = "formulaDetailsMaster.formulaNo IS NULL";
	private static final String _FINDER_COLUMN_FORMULANO_FORMULANO_2 = "formulaDetailsMaster.formulaNo = ?";
	private static final String _FINDER_COLUMN_FORMULANO_FORMULANO_3 = "(formulaDetailsMaster.formulaNo IS NULL OR formulaDetailsMaster.formulaNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMID = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemId",
			new String[] { String.class.getName() },
			FormulaDetailsMasterModelImpl.ITEMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMID = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the formula details masters where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByItemId(String itemId) {
		return findByItemId(itemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the formula details masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByItemId(String itemId, int start,
		int end) {
		return findByItemId(itemId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByItemId(String itemId, int start,
		int end, OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByItemId(itemId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where itemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemId the item ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByItemId(String itemId, int start,
		int end, OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(itemId, formulaDetailsMaster.getItemId())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

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
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
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
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByItemId_First(String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByItemId_First(itemId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemId=");
		msg.append(itemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByItemId_First(String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByItemId(itemId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByItemId_Last(String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByItemId_Last(itemId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemId=");
		msg.append(itemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByItemId_Last(String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByItemId(itemId);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByItemId(itemId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where itemId = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param itemId the item ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByItemId_PrevAndNext(
		int formulaDetailsMasterSid, String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByItemId_PrevAndNext(session, formulaDetailsMaster,
					itemId, orderByComparator, true);

			array[1] = formulaDetailsMaster;

			array[2] = getByItemId_PrevAndNext(session, formulaDetailsMaster,
					itemId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormulaDetailsMaster getByItemId_PrevAndNext(Session session,
		FormulaDetailsMaster formulaDetailsMaster, String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where itemId = &#63; from the database.
	 *
	 * @param itemId the item ID
	 */
	@Override
	public void removeByItemId(String itemId) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByItemId(itemId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where itemId = &#63;.
	 *
	 * @param itemId the item ID
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByItemId(String itemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMID;

		Object[] finderArgs = new Object[] { itemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

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

	private static final String _FINDER_COLUMN_ITEMID_ITEMID_1 = "formulaDetailsMaster.itemId IS NULL";
	private static final String _FINDER_COLUMN_ITEMID_ITEMID_2 = "formulaDetailsMaster.itemId = ?";
	private static final String _FINDER_COLUMN_ITEMID_ITEMID_3 = "(formulaDetailsMaster.itemId IS NULL OR formulaDetailsMaster.itemId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { String.class.getName() },
			FormulaDetailsMasterModelImpl.COMPANYSTRINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the formula details masters where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByCompanyId(String companyStringId) {
		return findByCompanyId(companyStringId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the formula details masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByCompanyId(String companyStringId,
		int start, int end) {
		return findByCompanyId(companyStringId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByCompanyId(String companyStringId,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByCompanyId(companyStringId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByCompanyId(String companyStringId,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyStringId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] {
					companyStringId,
					
					start, end, orderByComparator
				};
		}

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(companyStringId,
								formulaDetailsMaster.getCompanyStringId())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

			boolean bindCompanyStringId = false;

			if (companyStringId == null) {
				query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_1);
			}
			else if (companyStringId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_3);
			}
			else {
				bindCompanyStringId = true;

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyStringId) {
					qPos.add(companyStringId);
				}

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByCompanyId_First(String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByCompanyId_First(companyStringId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringId=");
		msg.append(companyStringId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByCompanyId_First(String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByCompanyId(companyStringId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByCompanyId_Last(String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByCompanyId_Last(companyStringId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringId=");
		msg.append(companyStringId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByCompanyId_Last(String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByCompanyId(companyStringId);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByCompanyId(companyStringId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where companyStringId = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByCompanyId_PrevAndNext(
		int formulaDetailsMasterSid, String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					formulaDetailsMaster, companyStringId, orderByComparator,
					true);

			array[1] = formulaDetailsMaster;

			array[2] = getByCompanyId_PrevAndNext(session,
					formulaDetailsMaster, companyStringId, orderByComparator,
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

	protected FormulaDetailsMaster getByCompanyId_PrevAndNext(Session session,
		FormulaDetailsMaster formulaDetailsMaster, String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

		boolean bindCompanyStringId = false;

		if (companyStringId == null) {
			query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_1);
		}
		else if (companyStringId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_3);
		}
		else {
			bindCompanyStringId = true;

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_2);
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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCompanyStringId) {
			qPos.add(companyStringId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where companyStringId = &#63; from the database.
	 *
	 * @param companyStringId the company string ID
	 */
	@Override
	public void removeByCompanyId(String companyStringId) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByCompanyId(
				companyStringId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByCompanyId(String companyStringId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyStringId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

			boolean bindCompanyStringId = false;

			if (companyStringId == null) {
				query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_1);
			}
			else if (companyStringId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_3);
			}
			else {
				bindCompanyStringId = true;

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyStringId) {
					qPos.add(companyStringId);
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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_1 = "formulaDetailsMaster.companyStringId IS NULL";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_2 = "formulaDetailsMaster.companyStringId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_3 = "(formulaDetailsMaster.companyStringId IS NULL OR formulaDetailsMaster.companyStringId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STARTDATE =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStartDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STARTDATE =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStartDate",
			new String[] { Date.class.getName() },
			FormulaDetailsMasterModelImpl.STARTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STARTDATE = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStartDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the formula details masters where startDate = &#63;.
	 *
	 * @param startDate the start date
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByStartDate(Date startDate) {
		return findByStartDate(startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the formula details masters where startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startDate the start date
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByStartDate(Date startDate,
		int start, int end) {
		return findByStartDate(startDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startDate the start date
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByStartDate(Date startDate,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByStartDate(startDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param startDate the start date
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByStartDate(Date startDate,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STARTDATE;
			finderArgs = new Object[] { startDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STARTDATE;
			finderArgs = new Object[] { startDate, start, end, orderByComparator };
		}

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(startDate,
								formulaDetailsMaster.getStartDate())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_STARTDATE_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_STARTDATE_STARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
				}

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where startDate = &#63;.
	 *
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByStartDate_First(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByStartDate_First(startDate,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where startDate = &#63;.
	 *
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByStartDate_First(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByStartDate(startDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where startDate = &#63;.
	 *
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByStartDate_Last(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByStartDate_Last(startDate,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where startDate = &#63;.
	 *
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByStartDate_Last(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByStartDate(startDate);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByStartDate(startDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where startDate = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByStartDate_PrevAndNext(
		int formulaDetailsMasterSid, Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByStartDate_PrevAndNext(session,
					formulaDetailsMaster, startDate, orderByComparator, true);

			array[1] = formulaDetailsMaster;

			array[2] = getByStartDate_PrevAndNext(session,
					formulaDetailsMaster, startDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormulaDetailsMaster getByStartDate_PrevAndNext(Session session,
		FormulaDetailsMaster formulaDetailsMaster, Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

		boolean bindStartDate = false;

		if (startDate == null) {
			query.append(_FINDER_COLUMN_STARTDATE_STARTDATE_1);
		}
		else {
			bindStartDate = true;

			query.append(_FINDER_COLUMN_STARTDATE_STARTDATE_2);
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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindStartDate) {
			qPos.add(new Timestamp(startDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where startDate = &#63; from the database.
	 *
	 * @param startDate the start date
	 */
	@Override
	public void removeByStartDate(Date startDate) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByStartDate(
				startDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where startDate = &#63;.
	 *
	 * @param startDate the start date
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByStartDate(Date startDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STARTDATE;

		Object[] finderArgs = new Object[] { startDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_STARTDATE_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_STARTDATE_STARTDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
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

	private static final String _FINDER_COLUMN_STARTDATE_STARTDATE_1 = "formulaDetailsMaster.startDate IS NULL";
	private static final String _FINDER_COLUMN_STARTDATE_STARTDATE_2 = "formulaDetailsMaster.startDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ENDDATE = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEndDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENDDATE =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEndDate",
			new String[] { Date.class.getName() },
			FormulaDetailsMasterModelImpl.ENDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ENDDATE = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEndDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the formula details masters where endDate = &#63;.
	 *
	 * @param endDate the end date
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByEndDate(Date endDate) {
		return findByEndDate(endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the formula details masters where endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByEndDate(Date endDate, int start,
		int end) {
		return findByEndDate(endDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByEndDate(Date endDate, int start,
		int end, OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByEndDate(endDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param endDate the end date
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByEndDate(Date endDate, int start,
		int end, OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENDDATE;
			finderArgs = new Object[] { endDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ENDDATE;
			finderArgs = new Object[] { endDate, start, end, orderByComparator };
		}

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(endDate,
								formulaDetailsMaster.getEndDate())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
				}

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where endDate = &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByEndDate_First(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByEndDate_First(endDate,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where endDate = &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByEndDate_First(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByEndDate(endDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where endDate = &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByEndDate_Last(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByEndDate_Last(endDate,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where endDate = &#63;.
	 *
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByEndDate_Last(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByEndDate(endDate);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByEndDate(endDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where endDate = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByEndDate_PrevAndNext(
		int formulaDetailsMasterSid, Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByEndDate_PrevAndNext(session, formulaDetailsMaster,
					endDate, orderByComparator, true);

			array[1] = formulaDetailsMaster;

			array[2] = getByEndDate_PrevAndNext(session, formulaDetailsMaster,
					endDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormulaDetailsMaster getByEndDate_PrevAndNext(Session session,
		FormulaDetailsMaster formulaDetailsMaster, Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

		boolean bindEndDate = false;

		if (endDate == null) {
			query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_1);
		}
		else {
			bindEndDate = true;

			query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_2);
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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEndDate) {
			qPos.add(new Timestamp(endDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where endDate = &#63; from the database.
	 *
	 * @param endDate the end date
	 */
	@Override
	public void removeByEndDate(Date endDate) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByEndDate(
				endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where endDate = &#63;.
	 *
	 * @param endDate the end date
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByEndDate(Date endDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ENDDATE;

		Object[] finderArgs = new Object[] { endDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

			boolean bindEndDate = false;

			if (endDate == null) {
				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				query.append(_FINDER_COLUMN_ENDDATE_ENDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEndDate) {
					qPos.add(new Timestamp(endDate.getTime()));
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

	private static final String _FINDER_COLUMN_ENDDATE_ENDDATE_1 = "formulaDetailsMaster.endDate IS NULL";
	private static final String _FINDER_COLUMN_ENDDATE_ENDDATE_2 = "formulaDetailsMaster.endDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFormulaDetailsUnique",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE =
		new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFormulaDetailsUnique",
			new String[] { String.class.getName() },
			FormulaDetailsMasterModelImpl.FORMULAID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FORMULADETAILSUNIQUE = new FinderPath(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFormulaDetailsUnique",
			new String[] { String.class.getName() });

	/**
	 * Returns all the formula details masters where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @return the matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		String formulaId) {
		return findByFormulaDetailsUnique(formulaId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the formula details masters where formulaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaId the formula ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		String formulaId, int start, int end) {
		return findByFormulaDetailsUnique(formulaId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters where formulaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaId the formula ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		String formulaId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findByFormulaDetailsUnique(formulaId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters where formulaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formulaId the formula ID
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		String formulaId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE;
			finderArgs = new Object[] { formulaId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE;
			finderArgs = new Object[] { formulaId, start, end, orderByComparator };
		}

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormulaDetailsMaster formulaDetailsMaster : list) {
					if (!Objects.equals(formulaId,
								formulaDetailsMaster.getFormulaId())) {
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

			query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

			boolean bindFormulaId = false;

			if (formulaId == null) {
				query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_1);
			}
			else if (formulaId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_3);
			}
			else {
				bindFormulaId = true;

				query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFormulaId) {
					qPos.add(formulaId);
				}

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Returns the first formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByFormulaDetailsUnique_First(
		String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByFormulaDetailsUnique_First(formulaId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formulaId=");
		msg.append(formulaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the first formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByFormulaDetailsUnique_First(
		String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		List<FormulaDetailsMaster> list = findByFormulaDetailsUnique(formulaId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster findByFormulaDetailsUnique_Last(
		String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByFormulaDetailsUnique_Last(formulaId,
				orderByComparator);

		if (formulaDetailsMaster != null) {
			return formulaDetailsMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formulaId=");
		msg.append(formulaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormulaDetailsMasterException(msg.toString());
	}

	/**
	 * Returns the last formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByFormulaDetailsUnique_Last(
		String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		int count = countByFormulaDetailsUnique(formulaId);

		if (count == 0) {
			return null;
		}

		List<FormulaDetailsMaster> list = findByFormulaDetailsUnique(formulaId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
	 *
	 * @param formulaDetailsMasterSid the primary key of the current formula details master
	 * @param formulaId the formula ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster[] findByFormulaDetailsUnique_PrevAndNext(
		int formulaDetailsMasterSid, String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = findByPrimaryKey(formulaDetailsMasterSid);

		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster[] array = new FormulaDetailsMasterImpl[3];

			array[0] = getByFormulaDetailsUnique_PrevAndNext(session,
					formulaDetailsMaster, formulaId, orderByComparator, true);

			array[1] = formulaDetailsMaster;

			array[2] = getByFormulaDetailsUnique_PrevAndNext(session,
					formulaDetailsMaster, formulaId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormulaDetailsMaster getByFormulaDetailsUnique_PrevAndNext(
		Session session, FormulaDetailsMaster formulaDetailsMaster,
		String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE);

		boolean bindFormulaId = false;

		if (formulaId == null) {
			query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_1);
		}
		else if (formulaId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_3);
		}
		else {
			bindFormulaId = true;

			query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_2);
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
			query.append(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindFormulaId) {
			qPos.add(formulaId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formulaDetailsMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormulaDetailsMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the formula details masters where formulaId = &#63; from the database.
	 *
	 * @param formulaId the formula ID
	 */
	@Override
	public void removeByFormulaDetailsUnique(String formulaId) {
		for (FormulaDetailsMaster formulaDetailsMaster : findByFormulaDetailsUnique(
				formulaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters where formulaId = &#63;.
	 *
	 * @param formulaId the formula ID
	 * @return the number of matching formula details masters
	 */
	@Override
	public int countByFormulaDetailsUnique(String formulaId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FORMULADETAILSUNIQUE;

		Object[] finderArgs = new Object[] { formulaId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMULADETAILSMASTER_WHERE);

			boolean bindFormulaId = false;

			if (formulaId == null) {
				query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_1);
			}
			else if (formulaId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_3);
			}
			else {
				bindFormulaId = true;

				query.append(_FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFormulaId) {
					qPos.add(formulaId);
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

	private static final String _FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_1 = "formulaDetailsMaster.formulaId IS NULL";
	private static final String _FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_2 = "formulaDetailsMaster.formulaId = ?";
	private static final String _FINDER_COLUMN_FORMULADETAILSUNIQUE_FORMULAID_3 = "(formulaDetailsMaster.formulaId IS NULL OR formulaDetailsMaster.formulaId = '')";

	public FormulaDetailsMasterPersistenceImpl() {
		setModelClass(FormulaDetailsMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("contractPrice1", "CONTRACT_PRICE_1");
			dbColumnNames.put("contractPrice2", "CONTRACT_PRICE_2");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("formulaNo", "FORMULA_NO");
			dbColumnNames.put("formulaDetailsMasterSid",
				"FORMULA_DETAILS_MASTER_SID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("rebatePercent1", "REBATE_PERCENT_1");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("formulaDesc", "FORMULA_DESC");
			dbColumnNames.put("rebatePercent2", "REBATE_PERCENT_2");
			dbColumnNames.put("rebatePercent3", "REBATE_PERCENT_3");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("contractPrice3", "CONTRACT_PRICE_3");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("formulaId", "FORMULA_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the formula details master in the entity cache if it is enabled.
	 *
	 * @param formulaDetailsMaster the formula details master
	 */
	@Override
	public void cacheResult(FormulaDetailsMaster formulaDetailsMaster) {
		entityCache.putResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			formulaDetailsMaster.getPrimaryKey(), formulaDetailsMaster);

		formulaDetailsMaster.resetOriginalValues();
	}

	/**
	 * Caches the formula details masters in the entity cache if it is enabled.
	 *
	 * @param formulaDetailsMasters the formula details masters
	 */
	@Override
	public void cacheResult(List<FormulaDetailsMaster> formulaDetailsMasters) {
		for (FormulaDetailsMaster formulaDetailsMaster : formulaDetailsMasters) {
			if (entityCache.getResult(
						FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
						FormulaDetailsMasterImpl.class,
						formulaDetailsMaster.getPrimaryKey()) == null) {
				cacheResult(formulaDetailsMaster);
			}
			else {
				formulaDetailsMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all formula details masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormulaDetailsMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the formula details master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FormulaDetailsMaster formulaDetailsMaster) {
		entityCache.removeResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class, formulaDetailsMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FormulaDetailsMaster> formulaDetailsMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FormulaDetailsMaster formulaDetailsMaster : formulaDetailsMasters) {
			entityCache.removeResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
				FormulaDetailsMasterImpl.class,
				formulaDetailsMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new formula details master with the primary key. Does not add the formula details master to the database.
	 *
	 * @param formulaDetailsMasterSid the primary key for the new formula details master
	 * @return the new formula details master
	 */
	@Override
	public FormulaDetailsMaster create(int formulaDetailsMasterSid) {
		FormulaDetailsMaster formulaDetailsMaster = new FormulaDetailsMasterImpl();

		formulaDetailsMaster.setNew(true);
		formulaDetailsMaster.setPrimaryKey(formulaDetailsMasterSid);

		return formulaDetailsMaster;
	}

	/**
	 * Removes the formula details master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formulaDetailsMasterSid the primary key of the formula details master
	 * @return the formula details master that was removed
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster remove(int formulaDetailsMasterSid)
		throws NoSuchFormulaDetailsMasterException {
		return remove((Serializable)formulaDetailsMasterSid);
	}

	/**
	 * Removes the formula details master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the formula details master
	 * @return the formula details master that was removed
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster remove(Serializable primaryKey)
		throws NoSuchFormulaDetailsMasterException {
		Session session = null;

		try {
			session = openSession();

			FormulaDetailsMaster formulaDetailsMaster = (FormulaDetailsMaster)session.get(FormulaDetailsMasterImpl.class,
					primaryKey);

			if (formulaDetailsMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormulaDetailsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(formulaDetailsMaster);
		}
		catch (NoSuchFormulaDetailsMasterException nsee) {
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
	protected FormulaDetailsMaster removeImpl(
		FormulaDetailsMaster formulaDetailsMaster) {
		formulaDetailsMaster = toUnwrappedModel(formulaDetailsMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formulaDetailsMaster)) {
				formulaDetailsMaster = (FormulaDetailsMaster)session.get(FormulaDetailsMasterImpl.class,
						formulaDetailsMaster.getPrimaryKeyObj());
			}

			if (formulaDetailsMaster != null) {
				session.delete(formulaDetailsMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (formulaDetailsMaster != null) {
			clearCache(formulaDetailsMaster);
		}

		return formulaDetailsMaster;
	}

	@Override
	public FormulaDetailsMaster updateImpl(
		FormulaDetailsMaster formulaDetailsMaster) {
		formulaDetailsMaster = toUnwrappedModel(formulaDetailsMaster);

		boolean isNew = formulaDetailsMaster.isNew();

		FormulaDetailsMasterModelImpl formulaDetailsMasterModelImpl = (FormulaDetailsMasterModelImpl)formulaDetailsMaster;

		Session session = null;

		try {
			session = openSession();

			if (formulaDetailsMaster.isNew()) {
				session.save(formulaDetailsMaster);

				formulaDetailsMaster.setNew(false);
			}
			else {
				formulaDetailsMaster = (FormulaDetailsMaster)session.merge(formulaDetailsMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FormulaDetailsMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					formulaDetailsMasterModelImpl.getFormulaId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULAID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULAID,
				args);

			args = new Object[] { formulaDetailsMasterModelImpl.getFormulaNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULANO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULANO,
				args);

			args = new Object[] { formulaDetailsMasterModelImpl.getItemId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
				args);

			args = new Object[] {
					formulaDetailsMasterModelImpl.getCompanyStringId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] { formulaDetailsMasterModelImpl.getStartDate() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_STARTDATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STARTDATE,
				args);

			args = new Object[] { formulaDetailsMasterModelImpl.getEndDate() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ENDDATE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENDDATE,
				args);

			args = new Object[] { formulaDetailsMasterModelImpl.getFormulaId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULADETAILSUNIQUE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULAID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalFormulaId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULAID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULAID,
					args);

				args = new Object[] { formulaDetailsMasterModelImpl.getFormulaId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULAID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULAID,
					args);
			}

			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULANO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalFormulaNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULANO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULANO,
					args);

				args = new Object[] { formulaDetailsMasterModelImpl.getFormulaNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULANO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULANO,
					args);
			}

			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalItemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
					args);

				args = new Object[] { formulaDetailsMasterModelImpl.getItemId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMID,
					args);
			}

			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalCompanyStringId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						formulaDetailsMasterModelImpl.getCompanyStringId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STARTDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STARTDATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STARTDATE,
					args);

				args = new Object[] { formulaDetailsMasterModelImpl.getStartDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_STARTDATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STARTDATE,
					args);
			}

			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENDDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalEndDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENDDATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENDDATE,
					args);

				args = new Object[] { formulaDetailsMasterModelImpl.getEndDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ENDDATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ENDDATE,
					args);
			}

			if ((formulaDetailsMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formulaDetailsMasterModelImpl.getOriginalFormulaId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULADETAILSUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE,
					args);

				args = new Object[] { formulaDetailsMasterModelImpl.getFormulaId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMULADETAILSUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMULADETAILSUNIQUE,
					args);
			}
		}

		entityCache.putResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
			FormulaDetailsMasterImpl.class,
			formulaDetailsMaster.getPrimaryKey(), formulaDetailsMaster, false);

		formulaDetailsMaster.resetOriginalValues();

		return formulaDetailsMaster;
	}

	protected FormulaDetailsMaster toUnwrappedModel(
		FormulaDetailsMaster formulaDetailsMaster) {
		if (formulaDetailsMaster instanceof FormulaDetailsMasterImpl) {
			return formulaDetailsMaster;
		}

		FormulaDetailsMasterImpl formulaDetailsMasterImpl = new FormulaDetailsMasterImpl();

		formulaDetailsMasterImpl.setNew(formulaDetailsMaster.isNew());
		formulaDetailsMasterImpl.setPrimaryKey(formulaDetailsMaster.getPrimaryKey());

		formulaDetailsMasterImpl.setCompanyStringId(formulaDetailsMaster.getCompanyStringId());
		formulaDetailsMasterImpl.setContractPrice1(formulaDetailsMaster.getContractPrice1());
		formulaDetailsMasterImpl.setContractPrice2(formulaDetailsMaster.getContractPrice2());
		formulaDetailsMasterImpl.setEndDate(formulaDetailsMaster.getEndDate());
		formulaDetailsMasterImpl.setFormulaNo(formulaDetailsMaster.getFormulaNo());
		formulaDetailsMasterImpl.setFormulaDetailsMasterSid(formulaDetailsMaster.getFormulaDetailsMasterSid());
		formulaDetailsMasterImpl.setItemId(formulaDetailsMaster.getItemId());
		formulaDetailsMasterImpl.setRebatePercent1(formulaDetailsMaster.getRebatePercent1());
		formulaDetailsMasterImpl.setModifiedDate(formulaDetailsMaster.getModifiedDate());
		formulaDetailsMasterImpl.setFormulaDesc(formulaDetailsMaster.getFormulaDesc());
		formulaDetailsMasterImpl.setRebatePercent2(formulaDetailsMaster.getRebatePercent2());
		formulaDetailsMasterImpl.setRebatePercent3(formulaDetailsMaster.getRebatePercent3());
		formulaDetailsMasterImpl.setRecordLockStatus(formulaDetailsMaster.isRecordLockStatus());
		formulaDetailsMasterImpl.setStartDate(formulaDetailsMaster.getStartDate());
		formulaDetailsMasterImpl.setCreatedDate(formulaDetailsMaster.getCreatedDate());
		formulaDetailsMasterImpl.setCreatedBy(formulaDetailsMaster.getCreatedBy());
		formulaDetailsMasterImpl.setSource(formulaDetailsMaster.getSource());
		formulaDetailsMasterImpl.setBatchId(formulaDetailsMaster.getBatchId());
		formulaDetailsMasterImpl.setContractPrice3(formulaDetailsMaster.getContractPrice3());
		formulaDetailsMasterImpl.setModifiedBy(formulaDetailsMaster.getModifiedBy());
		formulaDetailsMasterImpl.setInboundStatus(formulaDetailsMaster.getInboundStatus());
		formulaDetailsMasterImpl.setFormulaId(formulaDetailsMaster.getFormulaId());

		return formulaDetailsMasterImpl;
	}

	/**
	 * Returns the formula details master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the formula details master
	 * @return the formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormulaDetailsMasterException {
		FormulaDetailsMaster formulaDetailsMaster = fetchByPrimaryKey(primaryKey);

		if (formulaDetailsMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormulaDetailsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return formulaDetailsMaster;
	}

	/**
	 * Returns the formula details master with the primary key or throws a {@link NoSuchFormulaDetailsMasterException} if it could not be found.
	 *
	 * @param formulaDetailsMasterSid the primary key of the formula details master
	 * @return the formula details master
	 * @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster findByPrimaryKey(int formulaDetailsMasterSid)
		throws NoSuchFormulaDetailsMasterException {
		return findByPrimaryKey((Serializable)formulaDetailsMasterSid);
	}

	/**
	 * Returns the formula details master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the formula details master
	 * @return the formula details master, or <code>null</code> if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
				FormulaDetailsMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FormulaDetailsMaster formulaDetailsMaster = (FormulaDetailsMaster)serializable;

		if (formulaDetailsMaster == null) {
			Session session = null;

			try {
				session = openSession();

				formulaDetailsMaster = (FormulaDetailsMaster)session.get(FormulaDetailsMasterImpl.class,
						primaryKey);

				if (formulaDetailsMaster != null) {
					cacheResult(formulaDetailsMaster);
				}
				else {
					entityCache.putResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
						FormulaDetailsMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
					FormulaDetailsMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return formulaDetailsMaster;
	}

	/**
	 * Returns the formula details master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formulaDetailsMasterSid the primary key of the formula details master
	 * @return the formula details master, or <code>null</code> if a formula details master with the primary key could not be found
	 */
	@Override
	public FormulaDetailsMaster fetchByPrimaryKey(int formulaDetailsMasterSid) {
		return fetchByPrimaryKey((Serializable)formulaDetailsMasterSid);
	}

	@Override
	public Map<Serializable, FormulaDetailsMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FormulaDetailsMaster> map = new HashMap<Serializable, FormulaDetailsMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FormulaDetailsMaster formulaDetailsMaster = fetchByPrimaryKey(primaryKey);

			if (formulaDetailsMaster != null) {
				map.put(primaryKey, formulaDetailsMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
					FormulaDetailsMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FormulaDetailsMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORMULADETAILSMASTER_WHERE_PKS_IN);

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

			for (FormulaDetailsMaster formulaDetailsMaster : (List<FormulaDetailsMaster>)q.list()) {
				map.put(formulaDetailsMaster.getPrimaryKeyObj(),
					formulaDetailsMaster);

				cacheResult(formulaDetailsMaster);

				uncachedPrimaryKeys.remove(formulaDetailsMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FormulaDetailsMasterModelImpl.ENTITY_CACHE_ENABLED,
					FormulaDetailsMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the formula details masters.
	 *
	 * @return the formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the formula details masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @return the range of formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the formula details masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findAll(int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the formula details masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of formula details masters
	 * @param end the upper bound of the range of formula details masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of formula details masters
	 */
	@Override
	public List<FormulaDetailsMaster> findAll(int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
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

		List<FormulaDetailsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<FormulaDetailsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORMULADETAILSMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORMULADETAILSMASTER;

				if (pagination) {
					sql = sql.concat(FormulaDetailsMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormulaDetailsMaster>)QueryUtil.list(q,
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
	 * Removes all the formula details masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormulaDetailsMaster formulaDetailsMaster : findAll()) {
			remove(formulaDetailsMaster);
		}
	}

	/**
	 * Returns the number of formula details masters.
	 *
	 * @return the number of formula details masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORMULADETAILSMASTER);

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
		return FormulaDetailsMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the formula details master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FormulaDetailsMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FORMULADETAILSMASTER = "SELECT formulaDetailsMaster FROM FormulaDetailsMaster formulaDetailsMaster";
	private static final String _SQL_SELECT_FORMULADETAILSMASTER_WHERE_PKS_IN = "SELECT formulaDetailsMaster FROM FormulaDetailsMaster formulaDetailsMaster WHERE FORMULA_DETAILS_MASTER_SID IN (";
	private static final String _SQL_SELECT_FORMULADETAILSMASTER_WHERE = "SELECT formulaDetailsMaster FROM FormulaDetailsMaster formulaDetailsMaster WHERE ";
	private static final String _SQL_COUNT_FORMULADETAILSMASTER = "SELECT COUNT(formulaDetailsMaster) FROM FormulaDetailsMaster formulaDetailsMaster";
	private static final String _SQL_COUNT_FORMULADETAILSMASTER_WHERE = "SELECT COUNT(formulaDetailsMaster) FROM FormulaDetailsMaster formulaDetailsMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "formulaDetailsMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FormulaDetailsMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FormulaDetailsMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FormulaDetailsMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"companyStringId", "contractPrice1", "contractPrice2", "endDate",
				"formulaNo", "formulaDetailsMasterSid", "itemId",
				"rebatePercent1", "modifiedDate", "formulaDesc",
				"rebatePercent2", "rebatePercent3", "recordLockStatus",
				"startDate", "createdDate", "createdBy", "source", "batchId",
				"contractPrice3", "modifiedBy", "inboundStatus", "formulaId"
			});
}