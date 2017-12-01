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

import com.stpl.app.exception.NoSuchImtdCfpDetailsException;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.model.impl.ImtdCfpDetailsImpl;
import com.stpl.app.model.impl.ImtdCfpDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdCfpDetailsPersistence;

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
 * The persistence implementation for the imtd cfp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdCfpDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdCfpDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdCfpDetailsPersistenceImpl extends BasePersistenceImpl<ImtdCfpDetails>
	implements ImtdCfpDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdCfpDetailsUtil} to access the imtd cfp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdCfpDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_IMTDCFPSEARCH =
		new FinderPath(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByImtdCfpSearch",
			new String[] {
				String.class.getName(), String.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IMTDCFPSEARCH =
		new FinderPath(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByImtdCfpSearch",
			new String[] {
				String.class.getName(), String.class.getName(),
				Date.class.getName()
			},
			ImtdCfpDetailsModelImpl.USERSSID_COLUMN_BITMASK |
			ImtdCfpDetailsModelImpl.SESSIONID_COLUMN_BITMASK |
			ImtdCfpDetailsModelImpl.IMTDCREATEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_IMTDCFPSEARCH = new FinderPath(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByImtdCfpSearch",
			new String[] {
				String.class.getName(), String.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @return the matching imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findByImtdCfpSearch(String usersSid,
		String sessionId, Date imtdCreatedDate) {
		return findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param start the lower bound of the range of imtd cfp detailses
	 * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	 * @return the range of matching imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findByImtdCfpSearch(String usersSid,
		String sessionId, Date imtdCreatedDate, int start, int end) {
		return findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param start the lower bound of the range of imtd cfp detailses
	 * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findByImtdCfpSearch(String usersSid,
		String sessionId, Date imtdCreatedDate, int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param start the lower bound of the range of imtd cfp detailses
	 * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findByImtdCfpSearch(String usersSid,
		String sessionId, Date imtdCreatedDate, int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IMTDCFPSEARCH;
			finderArgs = new Object[] { usersSid, sessionId, imtdCreatedDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_IMTDCFPSEARCH;
			finderArgs = new Object[] {
					usersSid, sessionId, imtdCreatedDate,
					
					start, end, orderByComparator
				};
		}

		List<ImtdCfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdCfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImtdCfpDetails imtdCfpDetails : list) {
					if (!Objects.equals(usersSid, imtdCfpDetails.getUsersSid()) ||
							!Objects.equals(sessionId,
								imtdCfpDetails.getSessionId()) ||
							!Objects.equals(imtdCreatedDate,
								imtdCfpDetails.getImtdCreatedDate())) {
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

			query.append(_SQL_SELECT_IMTDCFPDETAILS_WHERE);

			boolean bindUsersSid = false;

			if (usersSid == null) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_1);
			}
			else if (usersSid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_3);
			}
			else {
				bindUsersSid = true;

				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_2);
			}

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_1);
			}
			else if (sessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_2);
			}

			boolean bindImtdCreatedDate = false;

			if (imtdCreatedDate == null) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_1);
			}
			else {
				bindImtdCreatedDate = true;

				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ImtdCfpDetailsModelImpl.ORDER_BY_JPQL);
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
					list = (List<ImtdCfpDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdCfpDetails>)QueryUtil.list(q,
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
	 * Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching imtd cfp details
	 * @throws NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
	 */
	@Override
	public ImtdCfpDetails findByImtdCfpSearch_First(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws NoSuchImtdCfpDetailsException {
		ImtdCfpDetails imtdCfpDetails = fetchByImtdCfpSearch_First(usersSid,
				sessionId, imtdCreatedDate, orderByComparator);

		if (imtdCfpDetails != null) {
			return imtdCfpDetails;
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

		throw new NoSuchImtdCfpDetailsException(msg.toString());
	}

	/**
	 * Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
	 */
	@Override
	public ImtdCfpDetails fetchByImtdCfpSearch_First(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		List<ImtdCfpDetails> list = findByImtdCfpSearch(usersSid, sessionId,
				imtdCreatedDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching imtd cfp details
	 * @throws NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
	 */
	@Override
	public ImtdCfpDetails findByImtdCfpSearch_Last(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws NoSuchImtdCfpDetailsException {
		ImtdCfpDetails imtdCfpDetails = fetchByImtdCfpSearch_Last(usersSid,
				sessionId, imtdCreatedDate, orderByComparator);

		if (imtdCfpDetails != null) {
			return imtdCfpDetails;
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

		throw new NoSuchImtdCfpDetailsException(msg.toString());
	}

	/**
	 * Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
	 */
	@Override
	public ImtdCfpDetails fetchByImtdCfpSearch_Last(String usersSid,
		String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		int count = countByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);

		if (count == 0) {
			return null;
		}

		List<ImtdCfpDetails> list = findByImtdCfpSearch(usersSid, sessionId,
				imtdCreatedDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the imtd cfp detailses before and after the current imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param imtdCfpDetailsSid the primary key of the current imtd cfp details
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next imtd cfp details
	 * @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails[] findByImtdCfpSearch_PrevAndNext(
		int imtdCfpDetailsSid, String usersSid, String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws NoSuchImtdCfpDetailsException {
		ImtdCfpDetails imtdCfpDetails = findByPrimaryKey(imtdCfpDetailsSid);

		Session session = null;

		try {
			session = openSession();

			ImtdCfpDetails[] array = new ImtdCfpDetailsImpl[3];

			array[0] = getByImtdCfpSearch_PrevAndNext(session, imtdCfpDetails,
					usersSid, sessionId, imtdCreatedDate, orderByComparator,
					true);

			array[1] = imtdCfpDetails;

			array[2] = getByImtdCfpSearch_PrevAndNext(session, imtdCfpDetails,
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

	protected ImtdCfpDetails getByImtdCfpSearch_PrevAndNext(Session session,
		ImtdCfpDetails imtdCfpDetails, String usersSid, String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_IMTDCFPDETAILS_WHERE);

		boolean bindUsersSid = false;

		if (usersSid == null) {
			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_1);
		}
		else if (usersSid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_3);
		}
		else {
			bindUsersSid = true;

			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_2);
		}

		boolean bindSessionId = false;

		if (sessionId == null) {
			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_1);
		}
		else if (sessionId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_3);
		}
		else {
			bindSessionId = true;

			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_2);
		}

		boolean bindImtdCreatedDate = false;

		if (imtdCreatedDate == null) {
			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_1);
		}
		else {
			bindImtdCreatedDate = true;

			query.append(_FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_2);
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
			query.append(ImtdCfpDetailsModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(imtdCfpDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImtdCfpDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 */
	@Override
	public void removeByImtdCfpSearch(String usersSid, String sessionId,
		Date imtdCreatedDate) {
		for (ImtdCfpDetails imtdCfpDetails : findByImtdCfpSearch(usersSid,
				sessionId, imtdCreatedDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(imtdCfpDetails);
		}
	}

	/**
	 * Returns the number of imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	 *
	 * @param usersSid the users sid
	 * @param sessionId the session ID
	 * @param imtdCreatedDate the imtd created date
	 * @return the number of matching imtd cfp detailses
	 */
	@Override
	public int countByImtdCfpSearch(String usersSid, String sessionId,
		Date imtdCreatedDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_IMTDCFPSEARCH;

		Object[] finderArgs = new Object[] { usersSid, sessionId, imtdCreatedDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_IMTDCFPDETAILS_WHERE);

			boolean bindUsersSid = false;

			if (usersSid == null) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_1);
			}
			else if (usersSid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_3);
			}
			else {
				bindUsersSid = true;

				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_2);
			}

			boolean bindSessionId = false;

			if (sessionId == null) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_1);
			}
			else if (sessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_3);
			}
			else {
				bindSessionId = true;

				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_2);
			}

			boolean bindImtdCreatedDate = false;

			if (imtdCreatedDate == null) {
				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_1);
			}
			else {
				bindImtdCreatedDate = true;

				query.append(_FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_2);
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

	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_1 = "imtdCfpDetails.usersSid IS NULL AND ";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_2 = "imtdCfpDetails.usersSid = ? AND ";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_USERSSID_3 = "(imtdCfpDetails.usersSid IS NULL OR imtdCfpDetails.usersSid = '') AND ";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_1 = "imtdCfpDetails.sessionId IS NULL AND ";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_2 = "imtdCfpDetails.sessionId = ? AND ";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_SESSIONID_3 = "(imtdCfpDetails.sessionId IS NULL OR imtdCfpDetails.sessionId = '') AND ";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_1 = "imtdCfpDetails.imtdCreatedDate IS NULL";
	private static final String _FINDER_COLUMN_IMTDCFPSEARCH_IMTDCREATEDDATE_2 = "imtdCfpDetails.imtdCreatedDate = ?";

	public ImtdCfpDetailsPersistenceImpl() {
		setModelClass(ImtdCfpDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("imtdCfpDetailsSid", "IMTD_CFP_DETAILS_SID");
			dbColumnNames.put("cfpDetailsStartDate", "CFP_DETAILS_START_DATE");
			dbColumnNames.put("companyType", "COMPANY_TYPE");
			dbColumnNames.put("cfpDetailsTcStartDate",
				"CFP_DETAILS_TC_START_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("cfpDetailsTcEndDate", "CFP_DETAILS_TC_END_DATE");
			dbColumnNames.put("cfpDetailsCreatedDate",
				"CFP_DETAILS_CREATED_DATE");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("cfpDetailsModifiedDate",
				"CFP_DETAILS_MODIFIED_DATE");
			dbColumnNames.put("cfpDetailsAttachedStatus",
				"CFP_DETAILS_ATTACHED_STATUS");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("cfpDetailsAttachedDate",
				"CFP_DETAILS_ATTACHED_DATE");
			dbColumnNames.put("cfpDetailsEndDate", "CFP_DETAILS_END_DATE");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("cfpDetailsTradeClass", "CFP_DETAILS_TRADE_CLASS");
			dbColumnNames.put("tradingPartnerContractNo",
				"TRADING_PARTNER_CONTRACT_NO");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("companyStartDate", "COMPANY_START_DATE");
			dbColumnNames.put("cfpDetailsModifiedBy", "CFP_DETAILS_MODIFIED_BY");
			dbColumnNames.put("companyEndDate", "COMPANY_END_DATE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("cfpDetailsSid", "CFP_DETAILS_SID");
			dbColumnNames.put("companyStatus", "COMPANY_STATUS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("cfpDetailsCreatedBy", "CFP_DETAILS_CREATED_BY");
			dbColumnNames.put("sessionId", "SESSION_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the imtd cfp details in the entity cache if it is enabled.
	 *
	 * @param imtdCfpDetails the imtd cfp details
	 */
	@Override
	public void cacheResult(ImtdCfpDetails imtdCfpDetails) {
		entityCache.putResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class, imtdCfpDetails.getPrimaryKey(),
			imtdCfpDetails);

		imtdCfpDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd cfp detailses in the entity cache if it is enabled.
	 *
	 * @param imtdCfpDetailses the imtd cfp detailses
	 */
	@Override
	public void cacheResult(List<ImtdCfpDetails> imtdCfpDetailses) {
		for (ImtdCfpDetails imtdCfpDetails : imtdCfpDetailses) {
			if (entityCache.getResult(
						ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdCfpDetailsImpl.class, imtdCfpDetails.getPrimaryKey()) == null) {
				cacheResult(imtdCfpDetails);
			}
			else {
				imtdCfpDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd cfp detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdCfpDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd cfp details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdCfpDetails imtdCfpDetails) {
		entityCache.removeResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class, imtdCfpDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdCfpDetails> imtdCfpDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdCfpDetails imtdCfpDetails : imtdCfpDetailses) {
			entityCache.removeResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdCfpDetailsImpl.class, imtdCfpDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd cfp details with the primary key. Does not add the imtd cfp details to the database.
	 *
	 * @param imtdCfpDetailsSid the primary key for the new imtd cfp details
	 * @return the new imtd cfp details
	 */
	@Override
	public ImtdCfpDetails create(int imtdCfpDetailsSid) {
		ImtdCfpDetails imtdCfpDetails = new ImtdCfpDetailsImpl();

		imtdCfpDetails.setNew(true);
		imtdCfpDetails.setPrimaryKey(imtdCfpDetailsSid);

		return imtdCfpDetails;
	}

	/**
	 * Removes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdCfpDetailsSid the primary key of the imtd cfp details
	 * @return the imtd cfp details that was removed
	 * @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails remove(int imtdCfpDetailsSid)
		throws NoSuchImtdCfpDetailsException {
		return remove((Serializable)imtdCfpDetailsSid);
	}

	/**
	 * Removes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd cfp details
	 * @return the imtd cfp details that was removed
	 * @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails remove(Serializable primaryKey)
		throws NoSuchImtdCfpDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdCfpDetails imtdCfpDetails = (ImtdCfpDetails)session.get(ImtdCfpDetailsImpl.class,
					primaryKey);

			if (imtdCfpDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdCfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdCfpDetails);
		}
		catch (NoSuchImtdCfpDetailsException nsee) {
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
	protected ImtdCfpDetails removeImpl(ImtdCfpDetails imtdCfpDetails) {
		imtdCfpDetails = toUnwrappedModel(imtdCfpDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdCfpDetails)) {
				imtdCfpDetails = (ImtdCfpDetails)session.get(ImtdCfpDetailsImpl.class,
						imtdCfpDetails.getPrimaryKeyObj());
			}

			if (imtdCfpDetails != null) {
				session.delete(imtdCfpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdCfpDetails != null) {
			clearCache(imtdCfpDetails);
		}

		return imtdCfpDetails;
	}

	@Override
	public ImtdCfpDetails updateImpl(ImtdCfpDetails imtdCfpDetails) {
		imtdCfpDetails = toUnwrappedModel(imtdCfpDetails);

		boolean isNew = imtdCfpDetails.isNew();

		ImtdCfpDetailsModelImpl imtdCfpDetailsModelImpl = (ImtdCfpDetailsModelImpl)imtdCfpDetails;

		Session session = null;

		try {
			session = openSession();

			if (imtdCfpDetails.isNew()) {
				session.save(imtdCfpDetails);

				imtdCfpDetails.setNew(false);
			}
			else {
				imtdCfpDetails = (ImtdCfpDetails)session.merge(imtdCfpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ImtdCfpDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					imtdCfpDetailsModelImpl.getUsersSid(),
					imtdCfpDetailsModelImpl.getSessionId(),
					imtdCfpDetailsModelImpl.getImtdCreatedDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_IMTDCFPSEARCH, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IMTDCFPSEARCH,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((imtdCfpDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IMTDCFPSEARCH.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						imtdCfpDetailsModelImpl.getOriginalUsersSid(),
						imtdCfpDetailsModelImpl.getOriginalSessionId(),
						imtdCfpDetailsModelImpl.getOriginalImtdCreatedDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_IMTDCFPSEARCH,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IMTDCFPSEARCH,
					args);

				args = new Object[] {
						imtdCfpDetailsModelImpl.getUsersSid(),
						imtdCfpDetailsModelImpl.getSessionId(),
						imtdCfpDetailsModelImpl.getImtdCreatedDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_IMTDCFPSEARCH,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_IMTDCFPSEARCH,
					args);
			}
		}

		entityCache.putResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdCfpDetailsImpl.class, imtdCfpDetails.getPrimaryKey(),
			imtdCfpDetails, false);

		imtdCfpDetails.resetOriginalValues();

		return imtdCfpDetails;
	}

	protected ImtdCfpDetails toUnwrappedModel(ImtdCfpDetails imtdCfpDetails) {
		if (imtdCfpDetails instanceof ImtdCfpDetailsImpl) {
			return imtdCfpDetails;
		}

		ImtdCfpDetailsImpl imtdCfpDetailsImpl = new ImtdCfpDetailsImpl();

		imtdCfpDetailsImpl.setNew(imtdCfpDetails.isNew());
		imtdCfpDetailsImpl.setPrimaryKey(imtdCfpDetails.getPrimaryKey());

		imtdCfpDetailsImpl.setCompanyNo(imtdCfpDetails.getCompanyNo());
		imtdCfpDetailsImpl.setImtdCfpDetailsSid(imtdCfpDetails.getImtdCfpDetailsSid());
		imtdCfpDetailsImpl.setCfpDetailsStartDate(imtdCfpDetails.getCfpDetailsStartDate());
		imtdCfpDetailsImpl.setCompanyType(imtdCfpDetails.getCompanyType());
		imtdCfpDetailsImpl.setCfpDetailsTcStartDate(imtdCfpDetails.getCfpDetailsTcStartDate());
		imtdCfpDetailsImpl.setModifiedBy(imtdCfpDetails.getModifiedBy());
		imtdCfpDetailsImpl.setCreatedDate(imtdCfpDetails.getCreatedDate());
		imtdCfpDetailsImpl.setCfpDetailsTcEndDate(imtdCfpDetails.getCfpDetailsTcEndDate());
		imtdCfpDetailsImpl.setCfpDetailsCreatedDate(imtdCfpDetails.getCfpDetailsCreatedDate());
		imtdCfpDetailsImpl.setImtdCreatedDate(imtdCfpDetails.getImtdCreatedDate());
		imtdCfpDetailsImpl.setCfpDetailsModifiedDate(imtdCfpDetails.getCfpDetailsModifiedDate());
		imtdCfpDetailsImpl.setCfpDetailsAttachedStatus(imtdCfpDetails.getCfpDetailsAttachedStatus());
		imtdCfpDetailsImpl.setCheckRecord(imtdCfpDetails.isCheckRecord());
		imtdCfpDetailsImpl.setCfpDetailsAttachedDate(imtdCfpDetails.getCfpDetailsAttachedDate());
		imtdCfpDetailsImpl.setCfpDetailsEndDate(imtdCfpDetails.getCfpDetailsEndDate());
		imtdCfpDetailsImpl.setCompanyStringId(imtdCfpDetails.getCompanyStringId());
		imtdCfpDetailsImpl.setCfpDetailsTradeClass(imtdCfpDetails.getCfpDetailsTradeClass());
		imtdCfpDetailsImpl.setTradingPartnerContractNo(imtdCfpDetails.getTradingPartnerContractNo());
		imtdCfpDetailsImpl.setCreatedBy(imtdCfpDetails.getCreatedBy());
		imtdCfpDetailsImpl.setUsersSid(imtdCfpDetails.getUsersSid());
		imtdCfpDetailsImpl.setCompanyStartDate(imtdCfpDetails.getCompanyStartDate());
		imtdCfpDetailsImpl.setCfpDetailsModifiedBy(imtdCfpDetails.getCfpDetailsModifiedBy());
		imtdCfpDetailsImpl.setCompanyEndDate(imtdCfpDetails.getCompanyEndDate());
		imtdCfpDetailsImpl.setCompanyMasterSid(imtdCfpDetails.getCompanyMasterSid());
		imtdCfpDetailsImpl.setCfpModelSid(imtdCfpDetails.getCfpModelSid());
		imtdCfpDetailsImpl.setCfpDetailsSid(imtdCfpDetails.getCfpDetailsSid());
		imtdCfpDetailsImpl.setCompanyStatus(imtdCfpDetails.getCompanyStatus());
		imtdCfpDetailsImpl.setModifiedDate(imtdCfpDetails.getModifiedDate());
		imtdCfpDetailsImpl.setCompanyName(imtdCfpDetails.getCompanyName());
		imtdCfpDetailsImpl.setOperation(imtdCfpDetails.getOperation());
		imtdCfpDetailsImpl.setCfpDetailsCreatedBy(imtdCfpDetails.getCfpDetailsCreatedBy());
		imtdCfpDetailsImpl.setSessionId(imtdCfpDetails.getSessionId());

		return imtdCfpDetailsImpl;
	}

	/**
	 * Returns the imtd cfp details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd cfp details
	 * @return the imtd cfp details
	 * @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdCfpDetailsException {
		ImtdCfpDetails imtdCfpDetails = fetchByPrimaryKey(primaryKey);

		if (imtdCfpDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdCfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdCfpDetails;
	}

	/**
	 * Returns the imtd cfp details with the primary key or throws a {@link NoSuchImtdCfpDetailsException} if it could not be found.
	 *
	 * @param imtdCfpDetailsSid the primary key of the imtd cfp details
	 * @return the imtd cfp details
	 * @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails findByPrimaryKey(int imtdCfpDetailsSid)
		throws NoSuchImtdCfpDetailsException {
		return findByPrimaryKey((Serializable)imtdCfpDetailsSid);
	}

	/**
	 * Returns the imtd cfp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd cfp details
	 * @return the imtd cfp details, or <code>null</code> if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdCfpDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdCfpDetails imtdCfpDetails = (ImtdCfpDetails)serializable;

		if (imtdCfpDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdCfpDetails = (ImtdCfpDetails)session.get(ImtdCfpDetailsImpl.class,
						primaryKey);

				if (imtdCfpDetails != null) {
					cacheResult(imtdCfpDetails);
				}
				else {
					entityCache.putResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdCfpDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdCfpDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdCfpDetails;
	}

	/**
	 * Returns the imtd cfp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdCfpDetailsSid the primary key of the imtd cfp details
	 * @return the imtd cfp details, or <code>null</code> if a imtd cfp details with the primary key could not be found
	 */
	@Override
	public ImtdCfpDetails fetchByPrimaryKey(int imtdCfpDetailsSid) {
		return fetchByPrimaryKey((Serializable)imtdCfpDetailsSid);
	}

	@Override
	public Map<Serializable, ImtdCfpDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdCfpDetails> map = new HashMap<Serializable, ImtdCfpDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdCfpDetails imtdCfpDetails = fetchByPrimaryKey(primaryKey);

			if (imtdCfpDetails != null) {
				map.put(primaryKey, imtdCfpDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdCfpDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdCfpDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDCFPDETAILS_WHERE_PKS_IN);

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

			for (ImtdCfpDetails imtdCfpDetails : (List<ImtdCfpDetails>)q.list()) {
				map.put(imtdCfpDetails.getPrimaryKeyObj(), imtdCfpDetails);

				cacheResult(imtdCfpDetails);

				uncachedPrimaryKeys.remove(imtdCfpDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdCfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdCfpDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd cfp detailses.
	 *
	 * @return the imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd cfp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd cfp detailses
	 * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	 * @return the range of imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd cfp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd cfp detailses
	 * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd cfp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd cfp detailses
	 * @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd cfp detailses
	 */
	@Override
	public List<ImtdCfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator,
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

		List<ImtdCfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdCfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDCFPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDCFPDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdCfpDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdCfpDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdCfpDetails>)QueryUtil.list(q,
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
	 * Removes all the imtd cfp detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdCfpDetails imtdCfpDetails : findAll()) {
			remove(imtdCfpDetails);
		}
	}

	/**
	 * Returns the number of imtd cfp detailses.
	 *
	 * @return the number of imtd cfp detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDCFPDETAILS);

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
		return ImtdCfpDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd cfp details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdCfpDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDCFPDETAILS = "SELECT imtdCfpDetails FROM ImtdCfpDetails imtdCfpDetails";
	private static final String _SQL_SELECT_IMTDCFPDETAILS_WHERE_PKS_IN = "SELECT imtdCfpDetails FROM ImtdCfpDetails imtdCfpDetails WHERE IMTD_CFP_DETAILS_SID IN (";
	private static final String _SQL_SELECT_IMTDCFPDETAILS_WHERE = "SELECT imtdCfpDetails FROM ImtdCfpDetails imtdCfpDetails WHERE ";
	private static final String _SQL_COUNT_IMTDCFPDETAILS = "SELECT COUNT(imtdCfpDetails) FROM ImtdCfpDetails imtdCfpDetails";
	private static final String _SQL_COUNT_IMTDCFPDETAILS_WHERE = "SELECT COUNT(imtdCfpDetails) FROM ImtdCfpDetails imtdCfpDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdCfpDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdCfpDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImtdCfpDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ImtdCfpDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"companyNo", "imtdCfpDetailsSid", "cfpDetailsStartDate",
				"companyType", "cfpDetailsTcStartDate", "modifiedBy",
				"createdDate", "cfpDetailsTcEndDate", "cfpDetailsCreatedDate",
				"imtdCreatedDate", "cfpDetailsModifiedDate",
				"cfpDetailsAttachedStatus", "checkRecord",
				"cfpDetailsAttachedDate", "cfpDetailsEndDate", "companyStringId",
				"cfpDetailsTradeClass", "tradingPartnerContractNo", "createdBy",
				"usersSid", "companyStartDate", "cfpDetailsModifiedBy",
				"companyEndDate", "companyMasterSid", "cfpModelSid",
				"cfpDetailsSid", "companyStatus", "modifiedDate", "companyName",
				"operation", "cfpDetailsCreatedBy", "sessionId"
			});
}