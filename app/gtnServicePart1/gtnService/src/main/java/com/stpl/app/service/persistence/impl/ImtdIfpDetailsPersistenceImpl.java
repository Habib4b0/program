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

import com.stpl.app.exception.NoSuchImtdIfpDetailsException;
import com.stpl.app.model.ImtdIfpDetails;
import com.stpl.app.model.impl.ImtdIfpDetailsImpl;
import com.stpl.app.model.impl.ImtdIfpDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdIfpDetailsPersistence;

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
 * The persistence implementation for the imtd ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdIfpDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdIfpDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdIfpDetailsPersistenceImpl extends BasePersistenceImpl<ImtdIfpDetails>
	implements ImtdIfpDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdIfpDetailsUtil} to access the imtd ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdIfpDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEMPIFPSEARCH =
		new FinderPath(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTempIfpSearch",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPIFPSEARCH =
		new FinderPath(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTempIfpSearch",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Date.class.getName()
			},
			ImtdIfpDetailsModelImpl.USERSSID_COLUMN_BITMASK |
			ImtdIfpDetailsModelImpl.SESSIONID_COLUMN_BITMASK |
			ImtdIfpDetailsModelImpl.IMTDCREATEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEMPIFPSEARCH = new FinderPath(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTempIfpSearch",
			new String[] {
				Integer.class.getName(), String.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @return the matching imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		String sessionId, Date imtdCreateddate) {
		return findByTempIfpSearch(usersSid, sessionId, imtdCreateddate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param start the lower bound of the range of imtd ifp detailses
	 * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	 * @return the range of matching imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		String sessionId, Date imtdCreateddate, int start, int end) {
		return findByTempIfpSearch(usersSid, sessionId, imtdCreateddate, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param start the lower bound of the range of imtd ifp detailses
	 * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		String sessionId, Date imtdCreateddate, int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return findByTempIfpSearch(usersSid, sessionId, imtdCreateddate, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param start the lower bound of the range of imtd ifp detailses
	 * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		String sessionId, Date imtdCreateddate, int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPIFPSEARCH;
			finderArgs = new Object[] { usersSid, sessionId, imtdCreateddate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEMPIFPSEARCH;
			finderArgs = new Object[] {
					usersSid, sessionId, imtdCreateddate,
					
					start, end, orderByComparator
				};
		}

		List<ImtdIfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdIfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImtdIfpDetails imtdIfpDetails : list) {
					if ((usersSid != imtdIfpDetails.getUsersSid()) ||
							!Objects.equals(sessionId,
								imtdIfpDetails.getSessionId()) ||
							!Objects.equals(imtdCreateddate,
								imtdIfpDetails.getImtdCreateddate())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_IMTDIFPDETAILS_WHERE);

			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_USERSSID_2);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_1);
			}
			else if (sessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_2);
			}

			boolean bindImtdCreateddate = false;

			if (imtdCreateddate == null) {
				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_1);
			}
			else {
				bindImtdCreateddate = true;

				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ImtdIfpDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(usersSid);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindImtdCreateddate) {
					qPos.add(new Timestamp(imtdCreateddate.getTime()));
				}

				if (!pagination) {
					list = (List<ImtdIfpDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdIfpDetails>)QueryUtil.list(q,
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
	 * Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching imtd ifp details
	 * @throws NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
	 */
	@Override
	public ImtdIfpDetails findByTempIfpSearch_First(int usersSid,
		String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws NoSuchImtdIfpDetailsException {
		ImtdIfpDetails imtdIfpDetails = fetchByTempIfpSearch_First(usersSid,
				sessionId, imtdCreateddate, orderByComparator);

		if (imtdIfpDetails != null) {
			return imtdIfpDetails;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("usersSid=");
		msg.append(usersSid);

		msg.append(", sessionId=");
		msg.append(sessionId);

		msg.append(", imtdCreateddate=");
		msg.append(imtdCreateddate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchImtdIfpDetailsException(msg.toString());
	}

	/**
	 * Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
	 */
	@Override
	public ImtdIfpDetails fetchByTempIfpSearch_First(int usersSid,
		String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		List<ImtdIfpDetails> list = findByTempIfpSearch(usersSid, sessionId,
				imtdCreateddate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching imtd ifp details
	 * @throws NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
	 */
	@Override
	public ImtdIfpDetails findByTempIfpSearch_Last(int usersSid,
		String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws NoSuchImtdIfpDetailsException {
		ImtdIfpDetails imtdIfpDetails = fetchByTempIfpSearch_Last(usersSid,
				sessionId, imtdCreateddate, orderByComparator);

		if (imtdIfpDetails != null) {
			return imtdIfpDetails;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("usersSid=");
		msg.append(usersSid);

		msg.append(", sessionId=");
		msg.append(sessionId);

		msg.append(", imtdCreateddate=");
		msg.append(imtdCreateddate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchImtdIfpDetailsException(msg.toString());
	}

	/**
	 * Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
	 */
	@Override
	public ImtdIfpDetails fetchByTempIfpSearch_Last(int usersSid,
		String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		int count = countByTempIfpSearch(usersSid, sessionId, imtdCreateddate);

		if (count == 0) {
			return null;
		}

		List<ImtdIfpDetails> list = findByTempIfpSearch(usersSid, sessionId,
				imtdCreateddate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the imtd ifp detailses before and after the current imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param imtdIfpDetailsSid the primary key of the current imtd ifp details
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next imtd ifp details
	 * @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails[] findByTempIfpSearch_PrevAndNext(
		int imtdIfpDetailsSid, int usersSid, String sessionId,
		Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws NoSuchImtdIfpDetailsException {
		ImtdIfpDetails imtdIfpDetails = findByPrimaryKey(imtdIfpDetailsSid);

		Session session = null;

		try {
			session = openSession();

			ImtdIfpDetails[] array = new ImtdIfpDetailsImpl[3];

			array[0] = getByTempIfpSearch_PrevAndNext(session, imtdIfpDetails,
					usersSid, sessionId, imtdCreateddate, orderByComparator,
					true);

			array[1] = imtdIfpDetails;

			array[2] = getByTempIfpSearch_PrevAndNext(session, imtdIfpDetails,
					usersSid, sessionId, imtdCreateddate, orderByComparator,
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

	protected ImtdIfpDetails getByTempIfpSearch_PrevAndNext(Session session,
		ImtdIfpDetails imtdIfpDetails, int usersSid, String sessionId,
		Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_IMTDIFPDETAILS_WHERE);

		query.append(_FINDER_COLUMN_TEMPIFPSEARCH_USERSSID_2);

		boolean bindSessionId = false;

		if (sessionId == null) {
			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_1);
		}
		else if (sessionId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_3);
		}
		else {
			bindSessionId = true;

			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_2);
		}

		boolean bindImtdCreateddate = false;

		if (imtdCreateddate == null) {
			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_1);
		}
		else {
			bindImtdCreateddate = true;

			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_2);
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
			query.append(ImtdIfpDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(usersSid);

		if (bindSessionId) {
			qPos.add(sessionId);
		}

		if (bindImtdCreateddate) {
			qPos.add(new Timestamp(imtdCreateddate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(imtdIfpDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImtdIfpDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63; from the database.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 */
	@Override
	public void removeByTempIfpSearch(int usersSid, String sessionId,
		Date imtdCreateddate) {
		for (ImtdIfpDetails imtdIfpDetails : findByTempIfpSearch(usersSid,
				sessionId, imtdCreateddate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(imtdIfpDetails);
		}
	}

	/**
	 * Returns the number of imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreateddate the imtd createddate
	 * @return the number of matching imtd ifp detailses
	 */
	@Override
	public int countByTempIfpSearch(int usersSid, String sessionId,
		Date imtdCreateddate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEMPIFPSEARCH;

		Object[] finderArgs = new Object[] { usersSid, sessionId, imtdCreateddate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_IMTDIFPDETAILS_WHERE);

			query.append(_FINDER_COLUMN_TEMPIFPSEARCH_USERSSID_2);

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_1);
			}
			else if (sessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_2);
			}

			boolean bindImtdCreateddate = false;

			if (imtdCreateddate == null) {
				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_1);
			}
			else {
				bindImtdCreateddate = true;

				query.append(_FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(usersSid);

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindImtdCreateddate) {
					qPos.add(new Timestamp(imtdCreateddate.getTime()));
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

	private static final String _FINDER_COLUMN_TEMPIFPSEARCH_USERSSID_2 = "imtdIfpDetails.usersSid = ? AND ";
	private static final String _FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_1 = "imtdIfpDetails.sessionId IS NULL AND ";
	private static final String _FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_2 = "imtdIfpDetails.sessionId = ? AND ";
	private static final String _FINDER_COLUMN_TEMPIFPSEARCH_SESSIONID_3 = "(imtdIfpDetails.sessionId IS NULL OR imtdIfpDetails.sessionId = '') AND ";
	private static final String _FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_1 = "imtdIfpDetails.imtdCreateddate IS NULL";
	private static final String _FINDER_COLUMN_TEMPIFPSEARCH_IMTDCREATEDDATE_2 = "imtdIfpDetails.imtdCreateddate = ?";

	public ImtdIfpDetailsPersistenceImpl() {
		setModelClass(ImtdIfpDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("ifpDetailsEndDate", "IFP_DETAILS_END_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("imtdCreateddate", "IMTD_CREATEDDATE");
			dbColumnNames.put("itemPackageSize", "ITEM_PACKAGE_SIZE");
			dbColumnNames.put("ifpDetailsCreatedDate",
				"IFP_DETAILS_CREATED_DATE");
			dbColumnNames.put("totalDollarCommitment", "TOTAL_DOLLAR_COMMITMENT");
			dbColumnNames.put("ifpDetailsCreatedBy", "IFP_DETAILS_CREATED_BY");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("ifpDetailsModifiedBy", "IFP_DETAILS_MODIFIED_BY");
			dbColumnNames.put("ifpDetailsModifiedDate",
				"IFP_DETAILS_MODIFIED_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("itemDesc", "ITEM_DESC");
			dbColumnNames.put("itemStartDate", "ITEM_START_DATE");
			dbColumnNames.put("itemStrength", "ITEM_STRENGTH");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("commitmentPeriod", "COMMITMENT_PERIOD");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("ifpDetailsSid", "IFP_DETAILS_SID");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("itemTherapeuticClass", "ITEM_THERAPEUTIC_CLASS");
			dbColumnNames.put("ifpDetailsStartDate", "IFP_DETAILS_START_DATE");
			dbColumnNames.put("itemForm", "ITEM_FORM");
			dbColumnNames.put("totalVolumeCommitment", "TOTAL_VOLUME_COMMITMENT");
			dbColumnNames.put("itemEndDate", "ITEM_END_DATE");
			dbColumnNames.put("checkBox", "CHECK_BOX");
			dbColumnNames.put("ifpDetailsAttachedStatus",
				"IFP_DETAILS_ATTACHED_STATUS");
			dbColumnNames.put("totalMarketshareCommitment",
				"TOTAL_MARKETSHARE_COMMITMENT");
			dbColumnNames.put("ifpDetailsAttachedDate",
				"IFP_DETAILS_ATTACHED_DATE");
			dbColumnNames.put("imtdIfpDetailsSid", "IMTD_IFP_DETAILS_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("itemPrimaryUom", "ITEM_PRIMARY_UOM");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("itemBrand", "ITEM_BRAND");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the imtd ifp details in the entity cache if it is enabled.
	 *
	 * @param imtdIfpDetails the imtd ifp details
	 */
	@Override
	public void cacheResult(ImtdIfpDetails imtdIfpDetails) {
		entityCache.putResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class, imtdIfpDetails.getPrimaryKey(),
			imtdIfpDetails);

		imtdIfpDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd ifp detailses in the entity cache if it is enabled.
	 *
	 * @param imtdIfpDetailses the imtd ifp detailses
	 */
	@Override
	public void cacheResult(List<ImtdIfpDetails> imtdIfpDetailses) {
		for (ImtdIfpDetails imtdIfpDetails : imtdIfpDetailses) {
			if (entityCache.getResult(
						ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdIfpDetailsImpl.class, imtdIfpDetails.getPrimaryKey()) == null) {
				cacheResult(imtdIfpDetails);
			}
			else {
				imtdIfpDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd ifp detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdIfpDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd ifp details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdIfpDetails imtdIfpDetails) {
		entityCache.removeResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class, imtdIfpDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdIfpDetails> imtdIfpDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdIfpDetails imtdIfpDetails : imtdIfpDetailses) {
			entityCache.removeResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdIfpDetailsImpl.class, imtdIfpDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
	 *
	 * @param imtdIfpDetailsSid the primary key for the new imtd ifp details
	 * @return the new imtd ifp details
	 */
	@Override
	public ImtdIfpDetails create(int imtdIfpDetailsSid) {
		ImtdIfpDetails imtdIfpDetails = new ImtdIfpDetailsImpl();

		imtdIfpDetails.setNew(true);
		imtdIfpDetails.setPrimaryKey(imtdIfpDetailsSid);

		return imtdIfpDetails;
	}

	/**
	 * Removes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdIfpDetailsSid the primary key of the imtd ifp details
	 * @return the imtd ifp details that was removed
	 * @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails remove(int imtdIfpDetailsSid)
		throws NoSuchImtdIfpDetailsException {
		return remove((Serializable)imtdIfpDetailsSid);
	}

	/**
	 * Removes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd ifp details
	 * @return the imtd ifp details that was removed
	 * @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails remove(Serializable primaryKey)
		throws NoSuchImtdIfpDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdIfpDetails imtdIfpDetails = (ImtdIfpDetails)session.get(ImtdIfpDetailsImpl.class,
					primaryKey);

			if (imtdIfpDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdIfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdIfpDetails);
		}
		catch (NoSuchImtdIfpDetailsException nsee) {
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
	protected ImtdIfpDetails removeImpl(ImtdIfpDetails imtdIfpDetails) {
		imtdIfpDetails = toUnwrappedModel(imtdIfpDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdIfpDetails)) {
				imtdIfpDetails = (ImtdIfpDetails)session.get(ImtdIfpDetailsImpl.class,
						imtdIfpDetails.getPrimaryKeyObj());
			}

			if (imtdIfpDetails != null) {
				session.delete(imtdIfpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdIfpDetails != null) {
			clearCache(imtdIfpDetails);
		}

		return imtdIfpDetails;
	}

	@Override
	public ImtdIfpDetails updateImpl(ImtdIfpDetails imtdIfpDetails) {
		imtdIfpDetails = toUnwrappedModel(imtdIfpDetails);

		boolean isNew = imtdIfpDetails.isNew();

		ImtdIfpDetailsModelImpl imtdIfpDetailsModelImpl = (ImtdIfpDetailsModelImpl)imtdIfpDetails;

		Session session = null;

		try {
			session = openSession();

			if (imtdIfpDetails.isNew()) {
				session.save(imtdIfpDetails);

				imtdIfpDetails.setNew(false);
			}
			else {
				imtdIfpDetails = (ImtdIfpDetails)session.merge(imtdIfpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ImtdIfpDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					imtdIfpDetailsModelImpl.getUsersSid(),
					imtdIfpDetailsModelImpl.getSessionId(),
					imtdIfpDetailsModelImpl.getImtdCreateddate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEMPIFPSEARCH, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPIFPSEARCH,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((imtdIfpDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPIFPSEARCH.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						imtdIfpDetailsModelImpl.getOriginalUsersSid(),
						imtdIfpDetailsModelImpl.getOriginalSessionId(),
						imtdIfpDetailsModelImpl.getOriginalImtdCreateddate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEMPIFPSEARCH,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPIFPSEARCH,
					args);

				args = new Object[] {
						imtdIfpDetailsModelImpl.getUsersSid(),
						imtdIfpDetailsModelImpl.getSessionId(),
						imtdIfpDetailsModelImpl.getImtdCreateddate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEMPIFPSEARCH,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPIFPSEARCH,
					args);
			}
		}

		entityCache.putResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdIfpDetailsImpl.class, imtdIfpDetails.getPrimaryKey(),
			imtdIfpDetails, false);

		imtdIfpDetails.resetOriginalValues();

		return imtdIfpDetails;
	}

	protected ImtdIfpDetails toUnwrappedModel(ImtdIfpDetails imtdIfpDetails) {
		if (imtdIfpDetails instanceof ImtdIfpDetailsImpl) {
			return imtdIfpDetails;
		}

		ImtdIfpDetailsImpl imtdIfpDetailsImpl = new ImtdIfpDetailsImpl();

		imtdIfpDetailsImpl.setNew(imtdIfpDetails.isNew());
		imtdIfpDetailsImpl.setPrimaryKey(imtdIfpDetails.getPrimaryKey());

		imtdIfpDetailsImpl.setItemStatus(imtdIfpDetails.getItemStatus());
		imtdIfpDetailsImpl.setIfpDetailsEndDate(imtdIfpDetails.getIfpDetailsEndDate());
		imtdIfpDetailsImpl.setItemMasterSid(imtdIfpDetails.getItemMasterSid());
		imtdIfpDetailsImpl.setImtdCreateddate(imtdIfpDetails.getImtdCreateddate());
		imtdIfpDetailsImpl.setItemPackageSize(imtdIfpDetails.getItemPackageSize());
		imtdIfpDetailsImpl.setIfpDetailsCreatedDate(imtdIfpDetails.getIfpDetailsCreatedDate());
		imtdIfpDetailsImpl.setTotalDollarCommitment(imtdIfpDetails.getTotalDollarCommitment());
		imtdIfpDetailsImpl.setIfpDetailsCreatedBy(imtdIfpDetails.getIfpDetailsCreatedBy());
		imtdIfpDetailsImpl.setItemId(imtdIfpDetails.getItemId());
		imtdIfpDetailsImpl.setModifiedDate(imtdIfpDetails.getModifiedDate());
		imtdIfpDetailsImpl.setIfpDetailsModifiedBy(imtdIfpDetails.getIfpDetailsModifiedBy());
		imtdIfpDetailsImpl.setIfpDetailsModifiedDate(imtdIfpDetails.getIfpDetailsModifiedDate());
		imtdIfpDetailsImpl.setCreatedDate(imtdIfpDetails.getCreatedDate());
		imtdIfpDetailsImpl.setCreatedBy(imtdIfpDetails.getCreatedBy());
		imtdIfpDetailsImpl.setUsersSid(imtdIfpDetails.getUsersSid());
		imtdIfpDetailsImpl.setItemDesc(imtdIfpDetails.getItemDesc());
		imtdIfpDetailsImpl.setItemStartDate(imtdIfpDetails.getItemStartDate());
		imtdIfpDetailsImpl.setItemStrength(imtdIfpDetails.getItemStrength());
		imtdIfpDetailsImpl.setContractMasterSid(imtdIfpDetails.getContractMasterSid());
		imtdIfpDetailsImpl.setModifiedBy(imtdIfpDetails.getModifiedBy());
		imtdIfpDetailsImpl.setCommitmentPeriod(imtdIfpDetails.getCommitmentPeriod());
		imtdIfpDetailsImpl.setItemNo(imtdIfpDetails.getItemNo());
		imtdIfpDetailsImpl.setIfpDetailsSid(imtdIfpDetails.getIfpDetailsSid());
		imtdIfpDetailsImpl.setIfpModelSid(imtdIfpDetails.getIfpModelSid());
		imtdIfpDetailsImpl.setItemTherapeuticClass(imtdIfpDetails.getItemTherapeuticClass());
		imtdIfpDetailsImpl.setIfpDetailsStartDate(imtdIfpDetails.getIfpDetailsStartDate());
		imtdIfpDetailsImpl.setItemForm(imtdIfpDetails.getItemForm());
		imtdIfpDetailsImpl.setTotalVolumeCommitment(imtdIfpDetails.getTotalVolumeCommitment());
		imtdIfpDetailsImpl.setItemEndDate(imtdIfpDetails.getItemEndDate());
		imtdIfpDetailsImpl.setCheckBox(imtdIfpDetails.isCheckBox());
		imtdIfpDetailsImpl.setIfpDetailsAttachedStatus(imtdIfpDetails.getIfpDetailsAttachedStatus());
		imtdIfpDetailsImpl.setTotalMarketshareCommitment(imtdIfpDetails.getTotalMarketshareCommitment());
		imtdIfpDetailsImpl.setIfpDetailsAttachedDate(imtdIfpDetails.getIfpDetailsAttachedDate());
		imtdIfpDetailsImpl.setImtdIfpDetailsSid(imtdIfpDetails.getImtdIfpDetailsSid());
		imtdIfpDetailsImpl.setSessionId(imtdIfpDetails.getSessionId());
		imtdIfpDetailsImpl.setItemName(imtdIfpDetails.getItemName());
		imtdIfpDetailsImpl.setItemPrimaryUom(imtdIfpDetails.getItemPrimaryUom());
		imtdIfpDetailsImpl.setOperation(imtdIfpDetails.getOperation());
		imtdIfpDetailsImpl.setItemBrand(imtdIfpDetails.getItemBrand());
		imtdIfpDetailsImpl.setCfpModelSid(imtdIfpDetails.getCfpModelSid());

		return imtdIfpDetailsImpl;
	}

	/**
	 * Returns the imtd ifp details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd ifp details
	 * @return the imtd ifp details
	 * @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdIfpDetailsException {
		ImtdIfpDetails imtdIfpDetails = fetchByPrimaryKey(primaryKey);

		if (imtdIfpDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdIfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdIfpDetails;
	}

	/**
	 * Returns the imtd ifp details with the primary key or throws a {@link NoSuchImtdIfpDetailsException} if it could not be found.
	 *
	 * @param imtdIfpDetailsSid the primary key of the imtd ifp details
	 * @return the imtd ifp details
	 * @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails findByPrimaryKey(int imtdIfpDetailsSid)
		throws NoSuchImtdIfpDetailsException {
		return findByPrimaryKey((Serializable)imtdIfpDetailsSid);
	}

	/**
	 * Returns the imtd ifp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd ifp details
	 * @return the imtd ifp details, or <code>null</code> if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdIfpDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdIfpDetails imtdIfpDetails = (ImtdIfpDetails)serializable;

		if (imtdIfpDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdIfpDetails = (ImtdIfpDetails)session.get(ImtdIfpDetailsImpl.class,
						primaryKey);

				if (imtdIfpDetails != null) {
					cacheResult(imtdIfpDetails);
				}
				else {
					entityCache.putResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdIfpDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdIfpDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdIfpDetails;
	}

	/**
	 * Returns the imtd ifp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdIfpDetailsSid the primary key of the imtd ifp details
	 * @return the imtd ifp details, or <code>null</code> if a imtd ifp details with the primary key could not be found
	 */
	@Override
	public ImtdIfpDetails fetchByPrimaryKey(int imtdIfpDetailsSid) {
		return fetchByPrimaryKey((Serializable)imtdIfpDetailsSid);
	}

	@Override
	public Map<Serializable, ImtdIfpDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdIfpDetails> map = new HashMap<Serializable, ImtdIfpDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdIfpDetails imtdIfpDetails = fetchByPrimaryKey(primaryKey);

			if (imtdIfpDetails != null) {
				map.put(primaryKey, imtdIfpDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdIfpDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdIfpDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDIFPDETAILS_WHERE_PKS_IN);

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

			for (ImtdIfpDetails imtdIfpDetails : (List<ImtdIfpDetails>)q.list()) {
				map.put(imtdIfpDetails.getPrimaryKeyObj(), imtdIfpDetails);

				cacheResult(imtdIfpDetails);

				uncachedPrimaryKeys.remove(imtdIfpDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdIfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdIfpDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd ifp detailses.
	 *
	 * @return the imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd ifp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd ifp detailses
	 * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	 * @return the range of imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd ifp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd ifp detailses
	 * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd ifp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd ifp detailses
	 * @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd ifp detailses
	 */
	@Override
	public List<ImtdIfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator,
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

		List<ImtdIfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdIfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDIFPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDIFPDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdIfpDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdIfpDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdIfpDetails>)QueryUtil.list(q,
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
	 * Removes all the imtd ifp detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdIfpDetails imtdIfpDetails : findAll()) {
			remove(imtdIfpDetails);
		}
	}

	/**
	 * Returns the number of imtd ifp detailses.
	 *
	 * @return the number of imtd ifp detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDIFPDETAILS);

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
		return ImtdIfpDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd ifp details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdIfpDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDIFPDETAILS = "SELECT imtdIfpDetails FROM ImtdIfpDetails imtdIfpDetails";
	private static final String _SQL_SELECT_IMTDIFPDETAILS_WHERE_PKS_IN = "SELECT imtdIfpDetails FROM ImtdIfpDetails imtdIfpDetails WHERE IMTD_IFP_DETAILS_SID IN (";
	private static final String _SQL_SELECT_IMTDIFPDETAILS_WHERE = "SELECT imtdIfpDetails FROM ImtdIfpDetails imtdIfpDetails WHERE ";
	private static final String _SQL_COUNT_IMTDIFPDETAILS = "SELECT COUNT(imtdIfpDetails) FROM ImtdIfpDetails imtdIfpDetails";
	private static final String _SQL_COUNT_IMTDIFPDETAILS_WHERE = "SELECT COUNT(imtdIfpDetails) FROM ImtdIfpDetails imtdIfpDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdIfpDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdIfpDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImtdIfpDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ImtdIfpDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemStatus", "ifpDetailsEndDate", "itemMasterSid",
				"imtdCreateddate", "itemPackageSize", "ifpDetailsCreatedDate",
				"totalDollarCommitment", "ifpDetailsCreatedBy", "itemId",
				"modifiedDate", "ifpDetailsModifiedBy", "ifpDetailsModifiedDate",
				"createdDate", "createdBy", "usersSid", "itemDesc",
				"itemStartDate", "itemStrength", "contractMasterSid",
				"modifiedBy", "commitmentPeriod", "itemNo", "ifpDetailsSid",
				"ifpModelSid", "itemTherapeuticClass", "ifpDetailsStartDate",
				"itemForm", "totalVolumeCommitment", "itemEndDate", "checkBox",
				"ifpDetailsAttachedStatus", "totalMarketshareCommitment",
				"ifpDetailsAttachedDate", "imtdIfpDetailsSid", "sessionId",
				"itemName", "itemPrimaryUom", "operation", "itemBrand",
				"cfpModelSid"
			});
}