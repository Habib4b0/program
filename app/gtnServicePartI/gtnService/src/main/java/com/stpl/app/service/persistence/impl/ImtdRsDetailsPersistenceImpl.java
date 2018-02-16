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

import com.stpl.app.exception.NoSuchImtdRsDetailsException;
import com.stpl.app.model.ImtdRsDetails;
import com.stpl.app.model.impl.ImtdRsDetailsImpl;
import com.stpl.app.model.impl.ImtdRsDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdRsDetailsPersistence;

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
 * The persistence implementation for the imtd rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdRsDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdRsDetailsPersistenceImpl extends BasePersistenceImpl<ImtdRsDetails>
	implements ImtdRsDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdRsDetailsUtil} to access the imtd rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdRsDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TEMPRSSEARCH =
		new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByTempRsSearch",
			new String[] {
				String.class.getName(), String.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH =
		new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByTempRsSearch",
			new String[] {
				String.class.getName(), String.class.getName(),
				Date.class.getName()
			},
			ImtdRsDetailsModelImpl.USERSSID_COLUMN_BITMASK |
			ImtdRsDetailsModelImpl.SESSIONID_COLUMN_BITMASK |
			ImtdRsDetailsModelImpl.IMTDCREATEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TEMPRSSEARCH = new FinderPath(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTempRsSearch",
			new String[] {
				String.class.getName(), String.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @return the matching imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
		String sessionId, Date imtdCreatedDate) {
		return findByTempRsSearch(usersSid, sessionId, imtdCreatedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param start the lower bound of the range of imtd rs detailses
	 * @param end the upper bound of the range of imtd rs detailses (not inclusive)
	 * @return the range of matching imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
		String sessionId, Date imtdCreatedDate, int start, int end) {
		return findByTempRsSearch(usersSid, sessionId, imtdCreatedDate, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param start the lower bound of the range of imtd rs detailses
	 * @param end the upper bound of the range of imtd rs detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
		String sessionId, Date imtdCreatedDate, int start, int end,
		OrderByComparator<ImtdRsDetails> orderByComparator) {
		return findByTempRsSearch(usersSid, sessionId, imtdCreatedDate, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param start the lower bound of the range of imtd rs detailses
	 * @param end the upper bound of the range of imtd rs detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findByTempRsSearch(String usersSid,
		String sessionId, Date imtdCreatedDate, int start, int end,
		OrderByComparator<ImtdRsDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH;
			finderArgs = new Object[] { usersSid, sessionId, imtdCreatedDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TEMPRSSEARCH;
			finderArgs = new Object[] {
					usersSid, sessionId, imtdCreatedDate,
					
					start, end, orderByComparator
				};
		}

		List<ImtdRsDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdRsDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImtdRsDetails imtdRsDetails : list) {
					if (!Objects.equals(usersSid, imtdRsDetails.getUsersSid()) ||
							!Objects.equals(sessionId,
								imtdRsDetails.getSessionId()) ||
							!Objects.equals(imtdCreatedDate,
								imtdRsDetails.getImtdCreatedDate())) {
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

			query.append(_SQL_SELECT_IMTDRSDETAILS_WHERE);

			boolean bindUsersSid = false;

			if (usersSid == null) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1);
			}
			else if (usersSid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3);
			}
			else {
				bindUsersSid = true;

				query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2);
			}

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1);
			}
			else if (sessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2);
			}

			boolean bindImtdCreatedDate = false;

			if (imtdCreatedDate == null) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1);
			}
			else {
				bindImtdCreatedDate = true;

				query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ImtdRsDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUsersSid) {
					qPos.add(usersSid);
				}

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindImtdCreatedDate) {
					qPos.add(new Timestamp(imtdCreatedDate.getTime()));
				}

				if (!pagination) {
					list = (List<ImtdRsDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdRsDetails>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching imtd rs details
	 * @throws NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
	 */
	@Override
	public ImtdRsDetails findByTempRsSearch_First(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdRsDetails> orderByComparator)
		throws NoSuchImtdRsDetailsException {
		ImtdRsDetails imtdRsDetails = fetchByTempRsSearch_First(usersSid,
				sessionId, imtdCreatedDate, orderByComparator);

		if (imtdRsDetails != null) {
			return imtdRsDetails;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("usersSid=");
		msg.append(usersSid);

		msg.append(", sessionId=");
		msg.append(sessionId);

		msg.append(", imtdCreatedDate=");
		msg.append(imtdCreatedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchImtdRsDetailsException(msg.toString());
	}

	/**
	 * Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
	 */
	@Override
	public ImtdRsDetails fetchByTempRsSearch_First(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdRsDetails> orderByComparator) {
		List<ImtdRsDetails> list = findByTempRsSearch(usersSid, sessionId,
				imtdCreatedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching imtd rs details
	 * @throws NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
	 */
	@Override
	public ImtdRsDetails findByTempRsSearch_Last(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdRsDetails> orderByComparator)
		throws NoSuchImtdRsDetailsException {
		ImtdRsDetails imtdRsDetails = fetchByTempRsSearch_Last(usersSid,
				sessionId, imtdCreatedDate, orderByComparator);

		if (imtdRsDetails != null) {
			return imtdRsDetails;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("usersSid=");
		msg.append(usersSid);

		msg.append(", sessionId=");
		msg.append(sessionId);

		msg.append(", imtdCreatedDate=");
		msg.append(imtdCreatedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchImtdRsDetailsException(msg.toString());
	}

	/**
	 * Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
	 */
	@Override
	public ImtdRsDetails fetchByTempRsSearch_Last(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdRsDetails> orderByComparator) {
		int count = countByTempRsSearch(usersSid, sessionId, imtdCreatedDate);

		if (count == 0) {
			return null;
		}

		List<ImtdRsDetails> list = findByTempRsSearch(usersSid, sessionId,
				imtdCreatedDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the imtd rs detailses before and after the current imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param imtdRsDetailsSid the primary key of the current imtd rs details
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next imtd rs details
	 * @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails[] findByTempRsSearch_PrevAndNext(
		int imtdRsDetailsSid, String usersSid, String sessionId,
		Date imtdCreatedDate, OrderByComparator<ImtdRsDetails> orderByComparator)
		throws NoSuchImtdRsDetailsException {
		ImtdRsDetails imtdRsDetails = findByPrimaryKey(imtdRsDetailsSid);

		Session session = null;

		try {
			session = openSession();

			ImtdRsDetails[] array = new ImtdRsDetailsImpl[3];

			array[0] = getByTempRsSearch_PrevAndNext(session, imtdRsDetails,
					usersSid, sessionId, imtdCreatedDate, orderByComparator,
					true);

			array[1] = imtdRsDetails;

			array[2] = getByTempRsSearch_PrevAndNext(session, imtdRsDetails,
					usersSid, sessionId, imtdCreatedDate, orderByComparator,
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

	protected ImtdRsDetails getByTempRsSearch_PrevAndNext(Session session,
		ImtdRsDetails imtdRsDetails, String usersSid, String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdRsDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_IMTDRSDETAILS_WHERE);

		boolean bindUsersSid = false;

		if (usersSid == null) {
			query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1);
		}
		else if (usersSid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3);
		}
		else {
			bindUsersSid = true;

			query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2);
		}

		boolean bindSessionId = false;

		if (sessionId == null) {
			query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1);
		}
		else if (sessionId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3);
		}
		else {
			bindSessionId = true;

			query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2);
		}

		boolean bindImtdCreatedDate = false;

		if (imtdCreatedDate == null) {
			query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1);
		}
		else {
			bindImtdCreatedDate = true;

			query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2);
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
			query.append(ImtdRsDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUsersSid) {
			qPos.add(usersSid);
		}

		if (bindSessionId) {
			qPos.add(sessionId);
		}

		if (bindImtdCreatedDate) {
			qPos.add(new Timestamp(imtdCreatedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(imtdRsDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImtdRsDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 */
	@Override
	public void removeByTempRsSearch(String usersSid, String sessionId,
		Date imtdCreatedDate) {
		for (ImtdRsDetails imtdRsDetails : findByTempRsSearch(usersSid,
				sessionId, imtdCreatedDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(imtdRsDetails);
		}
	}

	/**
	 * Returns the number of imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @return the number of matching imtd rs detailses
	 */
	@Override
	public int countByTempRsSearch(String usersSid, String sessionId,
		Date imtdCreatedDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TEMPRSSEARCH;

		Object[] finderArgs = new Object[] { usersSid, sessionId, imtdCreatedDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_IMTDRSDETAILS_WHERE);

			boolean bindUsersSid = false;

			if (usersSid == null) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1);
			}
			else if (usersSid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3);
			}
			else {
				bindUsersSid = true;

				query.append(_FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2);
			}

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1);
			}
			else if (sessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2);
			}

			boolean bindImtdCreatedDate = false;

			if (imtdCreatedDate == null) {
				query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1);
			}
			else {
				bindImtdCreatedDate = true;

				query.append(_FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUsersSid) {
					qPos.add(usersSid);
				}

				if (bindSessionId) {
					qPos.add(sessionId);
				}

				if (bindImtdCreatedDate) {
					qPos.add(new Timestamp(imtdCreatedDate.getTime()));
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

	private static final String _FINDER_COLUMN_TEMPRSSEARCH_USERSSID_1 = "imtdRsDetails.usersSid IS NULL AND ";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_USERSSID_2 = "imtdRsDetails.usersSid = ? AND ";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_USERSSID_3 = "(imtdRsDetails.usersSid IS NULL OR imtdRsDetails.usersSid = '') AND ";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_1 = "imtdRsDetails.sessionId IS NULL AND ";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_2 = "imtdRsDetails.sessionId = ? AND ";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_SESSIONID_3 = "(imtdRsDetails.sessionId IS NULL OR imtdRsDetails.sessionId = '') AND ";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_1 = "imtdRsDetails.imtdCreatedDate IS NULL";
	private static final String _FINDER_COLUMN_TEMPRSSEARCH_IMTDCREATEDDATE_2 = "imtdRsDetails.imtdCreatedDate = ?";

	public ImtdRsDetailsPersistenceImpl() {
		setModelClass(ImtdRsDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("rsDetailsModifiedDate",
				"RS_DETAILS_MODIFIED_DATE");
			dbColumnNames.put("rsDetailsBundleNo", "RS_DETAILS_BUNDLE_NO");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("imtdRsDetailsSid", "IMTD_RS_DETAILS_SID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("rsDetailsFormulaMethodId",
				"RS_DETAILS_FORMULA_METHOD_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("rsDetailsFormulaId", "RS_DETAILS_FORMULA_ID");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("rsDetailsCreatedDate", "RS_DETAILS_CREATED_DATE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("rsDetailsFormulaName", "RS_DETAILS_FORMULA_NAME");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("rsDetailsCreatedBy", "RS_DETAILS_CREATED_BY");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("rsDetailsFormulaNo", "RS_DETAILS_FORMULA_NO");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("rsId", "RS_ID");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("rsDetailsRebateAmount",
				"RS_DETAILS_REBATE_AMOUNT");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("rsDetailsModifiedBy", "RS_DETAILS_MODIFIED_BY");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("rebatePlanMasterSid", "REBATE_PLAN_MASTER_SID");
			dbColumnNames.put("rsDetailsAttachedDate",
				"RS_DETAILS_ATTACHED_DATE");
			dbColumnNames.put("itemRebateEndDate", "ITEM_REBATE_END_DATE");
			dbColumnNames.put("rsDetailsRebatePlanName",
				"RS_DETAILS_REBATE_PLAN_NAME");
			dbColumnNames.put("itemRebateStartDate", "ITEM_REBATE_START_DATE");
			dbColumnNames.put("rsDetailsFormulaType", "RS_DETAILS_FORMULA_TYPE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("rsDetailsSid", "RS_DETAILS_SID");
			dbColumnNames.put("rsDetailsAttachedStatus",
				"RS_DETAILS_ATTACHED_STATUS");
			dbColumnNames.put("rsDetailsNetSalesFormulaNo",
				"RS_DETAILS_NET_SALES_FORMULA_NO");
			dbColumnNames.put("rsDetailsNetSalesFormulaName",
				"RS_DETAILS_NET_SALES_FORMULA_NAME");
			dbColumnNames.put("rsDetailsDeductionCalendarNo",
				"RS_DETAILS_DEDUCTION_CALENDAR_NO");
			dbColumnNames.put("rsDetailsDeductionCalendarName",
				"RS_DETAILS_DEDUCTION_CALENDAR_NAME");
			dbColumnNames.put("deductionCalendarMasterSid",
				"DEDUCTION_CALENDAR_MASTER_SID");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("evaluationRule", "EVALUATION_RULE");
			dbColumnNames.put("netSalesRule", "NET_SALES_RULE");
			dbColumnNames.put("formulaType", "FORMULA_TYPE");
			dbColumnNames.put("calculationRule", "CALCULATION_RULE");
			dbColumnNames.put("calculationRuleBundle", "CALCULATION_RULE_BUNDLE");
			dbColumnNames.put("evaluationRuleBundle", "EVALUATION_RULE_BUNDLE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the imtd rs details in the entity cache if it is enabled.
	 *
	 * @param imtdRsDetails the imtd rs details
	 */
	@Override
	public void cacheResult(ImtdRsDetails imtdRsDetails) {
		entityCache.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey(),
			imtdRsDetails);

		imtdRsDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd rs detailses in the entity cache if it is enabled.
	 *
	 * @param imtdRsDetailses the imtd rs detailses
	 */
	@Override
	public void cacheResult(List<ImtdRsDetails> imtdRsDetailses) {
		for (ImtdRsDetails imtdRsDetails : imtdRsDetailses) {
			if (entityCache.getResult(
						ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey()) == null) {
				cacheResult(imtdRsDetails);
			}
			else {
				imtdRsDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd rs detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdRsDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd rs details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdRsDetails imtdRsDetails) {
		entityCache.removeResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdRsDetails> imtdRsDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdRsDetails imtdRsDetails : imtdRsDetailses) {
			entityCache.removeResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
	 *
	 * @param imtdRsDetailsSid the primary key for the new imtd rs details
	 * @return the new imtd rs details
	 */
	@Override
	public ImtdRsDetails create(int imtdRsDetailsSid) {
		ImtdRsDetails imtdRsDetails = new ImtdRsDetailsImpl();

		imtdRsDetails.setNew(true);
		imtdRsDetails.setPrimaryKey(imtdRsDetailsSid);

		return imtdRsDetails;
	}

	/**
	 * Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdRsDetailsSid the primary key of the imtd rs details
	 * @return the imtd rs details that was removed
	 * @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails remove(int imtdRsDetailsSid)
		throws NoSuchImtdRsDetailsException {
		return remove((Serializable)imtdRsDetailsSid);
	}

	/**
	 * Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd rs details
	 * @return the imtd rs details that was removed
	 * @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails remove(Serializable primaryKey)
		throws NoSuchImtdRsDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdRsDetails imtdRsDetails = (ImtdRsDetails)session.get(ImtdRsDetailsImpl.class,
					primaryKey);

			if (imtdRsDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdRsDetails);
		}
		catch (NoSuchImtdRsDetailsException nsee) {
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
	protected ImtdRsDetails removeImpl(ImtdRsDetails imtdRsDetails) {
		imtdRsDetails = toUnwrappedModel(imtdRsDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdRsDetails)) {
				imtdRsDetails = (ImtdRsDetails)session.get(ImtdRsDetailsImpl.class,
						imtdRsDetails.getPrimaryKeyObj());
			}

			if (imtdRsDetails != null) {
				session.delete(imtdRsDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdRsDetails != null) {
			clearCache(imtdRsDetails);
		}

		return imtdRsDetails;
	}

	@Override
	public ImtdRsDetails updateImpl(ImtdRsDetails imtdRsDetails) {
		imtdRsDetails = toUnwrappedModel(imtdRsDetails);

		boolean isNew = imtdRsDetails.isNew();

		ImtdRsDetailsModelImpl imtdRsDetailsModelImpl = (ImtdRsDetailsModelImpl)imtdRsDetails;

		Session session = null;

		try {
			session = openSession();

			if (imtdRsDetails.isNew()) {
				session.save(imtdRsDetails);

				imtdRsDetails.setNew(false);
			}
			else {
				imtdRsDetails = (ImtdRsDetails)session.merge(imtdRsDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ImtdRsDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					imtdRsDetailsModelImpl.getUsersSid(),
					imtdRsDetailsModelImpl.getSessionId(),
					imtdRsDetailsModelImpl.getImtdCreatedDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TEMPRSSEARCH, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((imtdRsDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						imtdRsDetailsModelImpl.getOriginalUsersSid(),
						imtdRsDetailsModelImpl.getOriginalSessionId(),
						imtdRsDetailsModelImpl.getOriginalImtdCreatedDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEMPRSSEARCH, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH,
					args);

				args = new Object[] {
						imtdRsDetailsModelImpl.getUsersSid(),
						imtdRsDetailsModelImpl.getSessionId(),
						imtdRsDetailsModelImpl.getImtdCreatedDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TEMPRSSEARCH, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TEMPRSSEARCH,
					args);
			}
		}

		entityCache.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsImpl.class, imtdRsDetails.getPrimaryKey(),
			imtdRsDetails, false);

		imtdRsDetails.resetOriginalValues();

		return imtdRsDetails;
	}

	protected ImtdRsDetails toUnwrappedModel(ImtdRsDetails imtdRsDetails) {
		if (imtdRsDetails instanceof ImtdRsDetailsImpl) {
			return imtdRsDetails;
		}

		ImtdRsDetailsImpl imtdRsDetailsImpl = new ImtdRsDetailsImpl();

		imtdRsDetailsImpl.setNew(imtdRsDetails.isNew());
		imtdRsDetailsImpl.setPrimaryKey(imtdRsDetails.getPrimaryKey());

		imtdRsDetailsImpl.setRsDetailsModifiedDate(imtdRsDetails.getRsDetailsModifiedDate());
		imtdRsDetailsImpl.setRsDetailsBundleNo(imtdRsDetails.getRsDetailsBundleNo());
		imtdRsDetailsImpl.setItemMasterSid(imtdRsDetails.getItemMasterSid());
		imtdRsDetailsImpl.setImtdRsDetailsSid(imtdRsDetails.getImtdRsDetailsSid());
		imtdRsDetailsImpl.setItemId(imtdRsDetails.getItemId());
		imtdRsDetailsImpl.setRsDetailsFormulaMethodId(imtdRsDetails.getRsDetailsFormulaMethodId());
		imtdRsDetailsImpl.setModifiedDate(imtdRsDetails.getModifiedDate());
		imtdRsDetailsImpl.setCreatedDate(imtdRsDetails.getCreatedDate());
		imtdRsDetailsImpl.setCreatedBy(imtdRsDetails.getCreatedBy());
		imtdRsDetailsImpl.setUsersSid(imtdRsDetails.getUsersSid());
		imtdRsDetailsImpl.setContractMasterSid(imtdRsDetails.getContractMasterSid());
		imtdRsDetailsImpl.setRsDetailsFormulaId(imtdRsDetails.getRsDetailsFormulaId());
		imtdRsDetailsImpl.setImtdCreatedDate(imtdRsDetails.getImtdCreatedDate());
		imtdRsDetailsImpl.setPsModelSid(imtdRsDetails.getPsModelSid());
		imtdRsDetailsImpl.setModifiedBy(imtdRsDetails.getModifiedBy());
		imtdRsDetailsImpl.setRsDetailsCreatedDate(imtdRsDetails.getRsDetailsCreatedDate());
		imtdRsDetailsImpl.setItemNo(imtdRsDetails.getItemNo());
		imtdRsDetailsImpl.setRsDetailsFormulaName(imtdRsDetails.getRsDetailsFormulaName());
		imtdRsDetailsImpl.setUdc6(imtdRsDetails.getUdc6());
		imtdRsDetailsImpl.setRsDetailsCreatedBy(imtdRsDetails.getRsDetailsCreatedBy());
		imtdRsDetailsImpl.setUdc5(imtdRsDetails.getUdc5());
		imtdRsDetailsImpl.setIfpModelSid(imtdRsDetails.getIfpModelSid());
		imtdRsDetailsImpl.setUdc4(imtdRsDetails.getUdc4());
		imtdRsDetailsImpl.setRsDetailsFormulaNo(imtdRsDetails.getRsDetailsFormulaNo());
		imtdRsDetailsImpl.setCheckRecord(imtdRsDetails.isCheckRecord());
		imtdRsDetailsImpl.setRsId(imtdRsDetails.getRsId());
		imtdRsDetailsImpl.setUdc1(imtdRsDetails.getUdc1());
		imtdRsDetailsImpl.setRsDetailsRebateAmount(imtdRsDetails.getRsDetailsRebateAmount());
		imtdRsDetailsImpl.setUdc2(imtdRsDetails.getUdc2());
		imtdRsDetailsImpl.setRsDetailsModifiedBy(imtdRsDetails.getRsDetailsModifiedBy());
		imtdRsDetailsImpl.setUdc3(imtdRsDetails.getUdc3());
		imtdRsDetailsImpl.setRebatePlanMasterSid(imtdRsDetails.getRebatePlanMasterSid());
		imtdRsDetailsImpl.setRsDetailsAttachedDate(imtdRsDetails.getRsDetailsAttachedDate());
		imtdRsDetailsImpl.setItemRebateEndDate(imtdRsDetails.getItemRebateEndDate());
		imtdRsDetailsImpl.setRsDetailsRebatePlanName(imtdRsDetails.getRsDetailsRebatePlanName());
		imtdRsDetailsImpl.setItemRebateStartDate(imtdRsDetails.getItemRebateStartDate());
		imtdRsDetailsImpl.setRsDetailsFormulaType(imtdRsDetails.getRsDetailsFormulaType());
		imtdRsDetailsImpl.setSessionId(imtdRsDetails.getSessionId());
		imtdRsDetailsImpl.setItemName(imtdRsDetails.getItemName());
		imtdRsDetailsImpl.setOperation(imtdRsDetails.getOperation());
		imtdRsDetailsImpl.setCfpModelSid(imtdRsDetails.getCfpModelSid());
		imtdRsDetailsImpl.setRsModelSid(imtdRsDetails.getRsModelSid());
		imtdRsDetailsImpl.setRsDetailsSid(imtdRsDetails.getRsDetailsSid());
		imtdRsDetailsImpl.setRsDetailsAttachedStatus(imtdRsDetails.getRsDetailsAttachedStatus());
		imtdRsDetailsImpl.setRsDetailsNetSalesFormulaNo(imtdRsDetails.getRsDetailsNetSalesFormulaNo());
		imtdRsDetailsImpl.setRsDetailsNetSalesFormulaName(imtdRsDetails.getRsDetailsNetSalesFormulaName());
		imtdRsDetailsImpl.setRsDetailsDeductionCalendarNo(imtdRsDetails.getRsDetailsDeductionCalendarNo());
		imtdRsDetailsImpl.setRsDetailsDeductionCalendarName(imtdRsDetails.getRsDetailsDeductionCalendarName());
		imtdRsDetailsImpl.setDeductionCalendarMasterSid(imtdRsDetails.getDeductionCalendarMasterSid());
		imtdRsDetailsImpl.setNetSalesFormulaMasterSid(imtdRsDetails.getNetSalesFormulaMasterSid());
		imtdRsDetailsImpl.setEvaluationRule(imtdRsDetails.getEvaluationRule());
		imtdRsDetailsImpl.setNetSalesRule(imtdRsDetails.getNetSalesRule());
		imtdRsDetailsImpl.setFormulaType(imtdRsDetails.getFormulaType());
		imtdRsDetailsImpl.setCalculationRule(imtdRsDetails.getCalculationRule());
		imtdRsDetailsImpl.setCalculationRuleBundle(imtdRsDetails.getCalculationRuleBundle());
		imtdRsDetailsImpl.setEvaluationRuleBundle(imtdRsDetails.getEvaluationRuleBundle());

		return imtdRsDetailsImpl;
	}

	/**
	 * Returns the imtd rs details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd rs details
	 * @return the imtd rs details
	 * @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdRsDetailsException {
		ImtdRsDetails imtdRsDetails = fetchByPrimaryKey(primaryKey);

		if (imtdRsDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdRsDetails;
	}

	/**
	 * Returns the imtd rs details with the primary key or throws a {@link NoSuchImtdRsDetailsException} if it could not be found.
	 *
	 * @param imtdRsDetailsSid the primary key of the imtd rs details
	 * @return the imtd rs details
	 * @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails findByPrimaryKey(int imtdRsDetailsSid)
		throws NoSuchImtdRsDetailsException {
		return findByPrimaryKey((Serializable)imtdRsDetailsSid);
	}

	/**
	 * Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd rs details
	 * @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdRsDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdRsDetails imtdRsDetails = (ImtdRsDetails)serializable;

		if (imtdRsDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdRsDetails = (ImtdRsDetails)session.get(ImtdRsDetailsImpl.class,
						primaryKey);

				if (imtdRsDetails != null) {
					cacheResult(imtdRsDetails);
				}
				else {
					entityCache.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdRsDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdRsDetails;
	}

	/**
	 * Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdRsDetailsSid the primary key of the imtd rs details
	 * @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
	 */
	@Override
	public ImtdRsDetails fetchByPrimaryKey(int imtdRsDetailsSid) {
		return fetchByPrimaryKey((Serializable)imtdRsDetailsSid);
	}

	@Override
	public Map<Serializable, ImtdRsDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdRsDetails> map = new HashMap<Serializable, ImtdRsDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdRsDetails imtdRsDetails = fetchByPrimaryKey(primaryKey);

			if (imtdRsDetails != null) {
				map.put(primaryKey, imtdRsDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdRsDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDRSDETAILS_WHERE_PKS_IN);

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

			for (ImtdRsDetails imtdRsDetails : (List<ImtdRsDetails>)q.list()) {
				map.put(imtdRsDetails.getPrimaryKeyObj(), imtdRsDetails);

				cacheResult(imtdRsDetails);

				uncachedPrimaryKeys.remove(imtdRsDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdRsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd rs detailses.
	 *
	 * @return the imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd rs detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs detailses
	 * @param end the upper bound of the range of imtd rs detailses (not inclusive)
	 * @return the range of imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd rs detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs detailses
	 * @param end the upper bound of the range of imtd rs detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findAll(int start, int end,
		OrderByComparator<ImtdRsDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd rs detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs detailses
	 * @param end the upper bound of the range of imtd rs detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd rs detailses
	 */
	@Override
	public List<ImtdRsDetails> findAll(int start, int end,
		OrderByComparator<ImtdRsDetails> orderByComparator,
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

		List<ImtdRsDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdRsDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDRSDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDRSDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdRsDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdRsDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdRsDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the imtd rs detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdRsDetails imtdRsDetails : findAll()) {
			remove(imtdRsDetails);
		}
	}

	/**
	 * Returns the number of imtd rs detailses.
	 *
	 * @return the number of imtd rs detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDRSDETAILS);

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
		return ImtdRsDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd rs details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdRsDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDRSDETAILS = "SELECT imtdRsDetails FROM ImtdRsDetails imtdRsDetails";
	private static final String _SQL_SELECT_IMTDRSDETAILS_WHERE_PKS_IN = "SELECT imtdRsDetails FROM ImtdRsDetails imtdRsDetails WHERE IMTD_RS_DETAILS_SID IN (";
	private static final String _SQL_SELECT_IMTDRSDETAILS_WHERE = "SELECT imtdRsDetails FROM ImtdRsDetails imtdRsDetails WHERE ";
	private static final String _SQL_COUNT_IMTDRSDETAILS = "SELECT COUNT(imtdRsDetails) FROM ImtdRsDetails imtdRsDetails";
	private static final String _SQL_COUNT_IMTDRSDETAILS_WHERE = "SELECT COUNT(imtdRsDetails) FROM ImtdRsDetails imtdRsDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdRsDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdRsDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImtdRsDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ImtdRsDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"rsDetailsModifiedDate", "rsDetailsBundleNo", "itemMasterSid",
				"imtdRsDetailsSid", "itemId", "rsDetailsFormulaMethodId",
				"modifiedDate", "createdDate", "createdBy", "usersSid",
				"contractMasterSid", "rsDetailsFormulaId", "imtdCreatedDate",
				"psModelSid", "modifiedBy", "rsDetailsCreatedDate", "itemNo",
				"rsDetailsFormulaName", "udc6", "rsDetailsCreatedBy", "udc5",
				"ifpModelSid", "udc4", "rsDetailsFormulaNo", "checkRecord",
				"rsId", "udc1", "rsDetailsRebateAmount", "udc2",
				"rsDetailsModifiedBy", "udc3", "rebatePlanMasterSid",
				"rsDetailsAttachedDate", "itemRebateEndDate",
				"rsDetailsRebatePlanName", "itemRebateStartDate",
				"rsDetailsFormulaType", "sessionId", "itemName", "operation",
				"cfpModelSid", "rsModelSid", "rsDetailsSid",
				"rsDetailsAttachedStatus", "rsDetailsNetSalesFormulaNo",
				"rsDetailsNetSalesFormulaName", "rsDetailsDeductionCalendarNo",
				"rsDetailsDeductionCalendarName", "deductionCalendarMasterSid",
				"netSalesFormulaMasterSid", "evaluationRule", "netSalesRule",
				"formulaType", "calculationRule", "calculationRuleBundle",
				"evaluationRuleBundle"
			});
}