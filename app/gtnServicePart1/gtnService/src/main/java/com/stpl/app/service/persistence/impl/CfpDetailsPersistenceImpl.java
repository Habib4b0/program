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

import com.stpl.app.exception.NoSuchCfpDetailsException;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.model.impl.CfpDetailsImpl;
import com.stpl.app.model.impl.CfpDetailsModelImpl;
import com.stpl.app.service.persistence.CfpDetailsPersistence;

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
 * The persistence implementation for the cfp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpDetailsPersistence
 * @see com.stpl.app.service.persistence.CfpDetailsUtil
 * @generated
 */
@ProviderType
public class CfpDetailsPersistenceImpl extends BasePersistenceImpl<CfpDetails>
	implements CfpDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CfpDetailsUtil} to access the cfp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CfpDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPMODELSID =
		new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpModelSid",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID =
		new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsModelImpl.FINDER_CACHE_ENABLED, CfpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpModelSid",
			new String[] { Integer.class.getName() },
			CfpDetailsModelImpl.CFPMODELSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPMODELSID = new FinderPath(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpModelSid",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the cfp detailses where cfpModelSid = &#63;.
	 *
	 * @param cfpModelSid the cfp model sid
	 * @return the matching cfp detailses
	 */
	@Override
	public List<CfpDetails> findByCfpModelSid(int cfpModelSid) {
		return findByCfpModelSid(cfpModelSid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp detailses where cfpModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param start the lower bound of the range of cfp detailses
	 * @param end the upper bound of the range of cfp detailses (not inclusive)
	 * @return the range of matching cfp detailses
	 */
	@Override
	public List<CfpDetails> findByCfpModelSid(int cfpModelSid, int start,
		int end) {
		return findByCfpModelSid(cfpModelSid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param start the lower bound of the range of cfp detailses
	 * @param end the upper bound of the range of cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp detailses
	 */
	@Override
	public List<CfpDetails> findByCfpModelSid(int cfpModelSid, int start,
		int end, OrderByComparator<CfpDetails> orderByComparator) {
		return findByCfpModelSid(cfpModelSid, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param start the lower bound of the range of cfp detailses
	 * @param end the upper bound of the range of cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp detailses
	 */
	@Override
	public List<CfpDetails> findByCfpModelSid(int cfpModelSid, int start,
		int end, OrderByComparator<CfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID;
			finderArgs = new Object[] { cfpModelSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPMODELSID;
			finderArgs = new Object[] { cfpModelSid, start, end, orderByComparator };
		}

		List<CfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpDetails cfpDetails : list) {
					if ((cfpModelSid != cfpDetails.getCfpModelSid())) {
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

			query.append(_SQL_SELECT_CFPDETAILS_WHERE);

			query.append(_FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpModelSid);

				if (!pagination) {
					list = (List<CfpDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpDetails>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp details
	 * @throws NoSuchCfpDetailsException if a matching cfp details could not be found
	 */
	@Override
	public CfpDetails findByCfpModelSid_First(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator)
		throws NoSuchCfpDetailsException {
		CfpDetails cfpDetails = fetchByCfpModelSid_First(cfpModelSid,
				orderByComparator);

		if (cfpDetails != null) {
			return cfpDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpModelSid=");
		msg.append(cfpModelSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpDetailsException(msg.toString());
	}

	/**
	 * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp details, or <code>null</code> if a matching cfp details could not be found
	 */
	@Override
	public CfpDetails fetchByCfpModelSid_First(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator) {
		List<CfpDetails> list = findByCfpModelSid(cfpModelSid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp details
	 * @throws NoSuchCfpDetailsException if a matching cfp details could not be found
	 */
	@Override
	public CfpDetails findByCfpModelSid_Last(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator)
		throws NoSuchCfpDetailsException {
		CfpDetails cfpDetails = fetchByCfpModelSid_Last(cfpModelSid,
				orderByComparator);

		if (cfpDetails != null) {
			return cfpDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpModelSid=");
		msg.append(cfpModelSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpDetailsException(msg.toString());
	}

	/**
	 * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
	 *
	 * @param cfpModelSid the cfp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp details, or <code>null</code> if a matching cfp details could not be found
	 */
	@Override
	public CfpDetails fetchByCfpModelSid_Last(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator) {
		int count = countByCfpModelSid(cfpModelSid);

		if (count == 0) {
			return null;
		}

		List<CfpDetails> list = findByCfpModelSid(cfpModelSid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp detailses before and after the current cfp details in the ordered set where cfpModelSid = &#63;.
	 *
	 * @param cfpDetailsSid the primary key of the current cfp details
	 * @param cfpModelSid the cfp model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp details
	 * @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails[] findByCfpModelSid_PrevAndNext(int cfpDetailsSid,
		int cfpModelSid, OrderByComparator<CfpDetails> orderByComparator)
		throws NoSuchCfpDetailsException {
		CfpDetails cfpDetails = findByPrimaryKey(cfpDetailsSid);

		Session session = null;

		try {
			session = openSession();

			CfpDetails[] array = new CfpDetailsImpl[3];

			array[0] = getByCfpModelSid_PrevAndNext(session, cfpDetails,
					cfpModelSid, orderByComparator, true);

			array[1] = cfpDetails;

			array[2] = getByCfpModelSid_PrevAndNext(session, cfpDetails,
					cfpModelSid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CfpDetails getByCfpModelSid_PrevAndNext(Session session,
		CfpDetails cfpDetails, int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPDETAILS_WHERE);

		query.append(_FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2);

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
			query.append(CfpDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(cfpModelSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp detailses where cfpModelSid = &#63; from the database.
	 *
	 * @param cfpModelSid the cfp model sid
	 */
	@Override
	public void removeByCfpModelSid(int cfpModelSid) {
		for (CfpDetails cfpDetails : findByCfpModelSid(cfpModelSid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cfpDetails);
		}
	}

	/**
	 * Returns the number of cfp detailses where cfpModelSid = &#63;.
	 *
	 * @param cfpModelSid the cfp model sid
	 * @return the number of matching cfp detailses
	 */
	@Override
	public int countByCfpModelSid(int cfpModelSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPMODELSID;

		Object[] finderArgs = new Object[] { cfpModelSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPDETAILS_WHERE);

			query.append(_FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpModelSid);

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

	private static final String _FINDER_COLUMN_CFPMODELSID_CFPMODELSID_2 = "cfpDetails.cfpModelSid = ?";

	public CfpDetailsPersistenceImpl() {
		setModelClass(CfpDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyCfpAttachedStatus",
				"COMPANY_CFP_ATTACHED_STATUS");
			dbColumnNames.put("tradeClass", "TRADE_CLASS");
			dbColumnNames.put("tradeClassEndDate", "TRADE_CLASS_END_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("companyStartDate", "COMPANY_START_DATE");
			dbColumnNames.put("tradeClassStartDate", "TRADE_CLASS_START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("companyEndDate", "COMPANY_END_DATE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("companyCfpAttachedDate",
				"COMPANY_CFP_ATTACHED_DATE");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("cfpDetailsSid", "CFP_DETAILS_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
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
	 * Caches the cfp details in the entity cache if it is enabled.
	 *
	 * @param cfpDetails the cfp details
	 */
	@Override
	public void cacheResult(CfpDetails cfpDetails) {
		entityCache.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsImpl.class, cfpDetails.getPrimaryKey(), cfpDetails);

		cfpDetails.resetOriginalValues();
	}

	/**
	 * Caches the cfp detailses in the entity cache if it is enabled.
	 *
	 * @param cfpDetailses the cfp detailses
	 */
	@Override
	public void cacheResult(List<CfpDetails> cfpDetailses) {
		for (CfpDetails cfpDetails : cfpDetailses) {
			if (entityCache.getResult(
						CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CfpDetailsImpl.class, cfpDetails.getPrimaryKey()) == null) {
				cacheResult(cfpDetails);
			}
			else {
				cfpDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cfp detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CfpDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cfp details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CfpDetails cfpDetails) {
		entityCache.removeResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsImpl.class, cfpDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CfpDetails> cfpDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CfpDetails cfpDetails : cfpDetailses) {
			entityCache.removeResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CfpDetailsImpl.class, cfpDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cfp details with the primary key. Does not add the cfp details to the database.
	 *
	 * @param cfpDetailsSid the primary key for the new cfp details
	 * @return the new cfp details
	 */
	@Override
	public CfpDetails create(int cfpDetailsSid) {
		CfpDetails cfpDetails = new CfpDetailsImpl();

		cfpDetails.setNew(true);
		cfpDetails.setPrimaryKey(cfpDetailsSid);

		return cfpDetails;
	}

	/**
	 * Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cfpDetailsSid the primary key of the cfp details
	 * @return the cfp details that was removed
	 * @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails remove(int cfpDetailsSid)
		throws NoSuchCfpDetailsException {
		return remove((Serializable)cfpDetailsSid);
	}

	/**
	 * Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cfp details
	 * @return the cfp details that was removed
	 * @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails remove(Serializable primaryKey)
		throws NoSuchCfpDetailsException {
		Session session = null;

		try {
			session = openSession();

			CfpDetails cfpDetails = (CfpDetails)session.get(CfpDetailsImpl.class,
					primaryKey);

			if (cfpDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cfpDetails);
		}
		catch (NoSuchCfpDetailsException nsee) {
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
	protected CfpDetails removeImpl(CfpDetails cfpDetails) {
		cfpDetails = toUnwrappedModel(cfpDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cfpDetails)) {
				cfpDetails = (CfpDetails)session.get(CfpDetailsImpl.class,
						cfpDetails.getPrimaryKeyObj());
			}

			if (cfpDetails != null) {
				session.delete(cfpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cfpDetails != null) {
			clearCache(cfpDetails);
		}

		return cfpDetails;
	}

	@Override
	public CfpDetails updateImpl(CfpDetails cfpDetails) {
		cfpDetails = toUnwrappedModel(cfpDetails);

		boolean isNew = cfpDetails.isNew();

		CfpDetailsModelImpl cfpDetailsModelImpl = (CfpDetailsModelImpl)cfpDetails;

		Session session = null;

		try {
			session = openSession();

			if (cfpDetails.isNew()) {
				session.save(cfpDetails);

				cfpDetails.setNew(false);
			}
			else {
				cfpDetails = (CfpDetails)session.merge(cfpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CfpDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { cfpDetailsModelImpl.getCfpModelSid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPMODELSID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cfpDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpDetailsModelImpl.getOriginalCfpModelSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPMODELSID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID,
					args);

				args = new Object[] { cfpDetailsModelImpl.getCfpModelSid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPMODELSID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPMODELSID,
					args);
			}
		}

		entityCache.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CfpDetailsImpl.class, cfpDetails.getPrimaryKey(), cfpDetails, false);

		cfpDetails.resetOriginalValues();

		return cfpDetails;
	}

	protected CfpDetails toUnwrappedModel(CfpDetails cfpDetails) {
		if (cfpDetails instanceof CfpDetailsImpl) {
			return cfpDetails;
		}

		CfpDetailsImpl cfpDetailsImpl = new CfpDetailsImpl();

		cfpDetailsImpl.setNew(cfpDetails.isNew());
		cfpDetailsImpl.setPrimaryKey(cfpDetails.getPrimaryKey());

		cfpDetailsImpl.setCreatedBy(cfpDetails.getCreatedBy());
		cfpDetailsImpl.setCompanyCfpAttachedStatus(cfpDetails.getCompanyCfpAttachedStatus());
		cfpDetailsImpl.setTradeClass(cfpDetails.getTradeClass());
		cfpDetailsImpl.setTradeClassEndDate(cfpDetails.getTradeClassEndDate());
		cfpDetailsImpl.setModifiedBy(cfpDetails.getModifiedBy());
		cfpDetailsImpl.setCompanyStartDate(cfpDetails.getCompanyStartDate());
		cfpDetailsImpl.setTradeClassStartDate(cfpDetails.getTradeClassStartDate());
		cfpDetailsImpl.setCreatedDate(cfpDetails.getCreatedDate());
		cfpDetailsImpl.setCompanyEndDate(cfpDetails.getCompanyEndDate());
		cfpDetailsImpl.setCompanyMasterSid(cfpDetails.getCompanyMasterSid());
		cfpDetailsImpl.setCompanyCfpAttachedDate(cfpDetails.getCompanyCfpAttachedDate());
		cfpDetailsImpl.setCfpModelSid(cfpDetails.getCfpModelSid());
		cfpDetailsImpl.setBatchId(cfpDetails.getBatchId());
		cfpDetailsImpl.setCfpDetailsSid(cfpDetails.getCfpDetailsSid());
		cfpDetailsImpl.setModifiedDate(cfpDetails.getModifiedDate());
		cfpDetailsImpl.setRecordLockStatus(cfpDetails.isRecordLockStatus());
		cfpDetailsImpl.setSource(cfpDetails.getSource());
		cfpDetailsImpl.setInboundStatus(cfpDetails.getInboundStatus());

		return cfpDetailsImpl;
	}

	/**
	 * Returns the cfp details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cfp details
	 * @return the cfp details
	 * @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCfpDetailsException {
		CfpDetails cfpDetails = fetchByPrimaryKey(primaryKey);

		if (cfpDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCfpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cfpDetails;
	}

	/**
	 * Returns the cfp details with the primary key or throws a {@link NoSuchCfpDetailsException} if it could not be found.
	 *
	 * @param cfpDetailsSid the primary key of the cfp details
	 * @return the cfp details
	 * @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails findByPrimaryKey(int cfpDetailsSid)
		throws NoSuchCfpDetailsException {
		return findByPrimaryKey((Serializable)cfpDetailsSid);
	}

	/**
	 * Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cfp details
	 * @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CfpDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CfpDetails cfpDetails = (CfpDetails)serializable;

		if (cfpDetails == null) {
			Session session = null;

			try {
				session = openSession();

				cfpDetails = (CfpDetails)session.get(CfpDetailsImpl.class,
						primaryKey);

				if (cfpDetails != null) {
					cacheResult(cfpDetails);
				}
				else {
					entityCache.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CfpDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CfpDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cfpDetails;
	}

	/**
	 * Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cfpDetailsSid the primary key of the cfp details
	 * @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
	 */
	@Override
	public CfpDetails fetchByPrimaryKey(int cfpDetailsSid) {
		return fetchByPrimaryKey((Serializable)cfpDetailsSid);
	}

	@Override
	public Map<Serializable, CfpDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CfpDetails> map = new HashMap<Serializable, CfpDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CfpDetails cfpDetails = fetchByPrimaryKey(primaryKey);

			if (cfpDetails != null) {
				map.put(primaryKey, cfpDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CfpDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CfpDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFPDETAILS_WHERE_PKS_IN);

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

			for (CfpDetails cfpDetails : (List<CfpDetails>)q.list()) {
				map.put(cfpDetails.getPrimaryKeyObj(), cfpDetails);

				cacheResult(cfpDetails);

				uncachedPrimaryKeys.remove(cfpDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CfpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CfpDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the cfp detailses.
	 *
	 * @return the cfp detailses
	 */
	@Override
	public List<CfpDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp detailses
	 * @param end the upper bound of the range of cfp detailses (not inclusive)
	 * @return the range of cfp detailses
	 */
	@Override
	public List<CfpDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp detailses
	 * @param end the upper bound of the range of cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cfp detailses
	 */
	@Override
	public List<CfpDetails> findAll(int start, int end,
		OrderByComparator<CfpDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp detailses
	 * @param end the upper bound of the range of cfp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cfp detailses
	 */
	@Override
	public List<CfpDetails> findAll(int start, int end,
		OrderByComparator<CfpDetails> orderByComparator,
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

		List<CfpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CfpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFPDETAILS;

				if (pagination) {
					sql = sql.concat(CfpDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CfpDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cfp detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CfpDetails cfpDetails : findAll()) {
			remove(cfpDetails);
		}
	}

	/**
	 * Returns the number of cfp detailses.
	 *
	 * @return the number of cfp detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFPDETAILS);

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
		return CfpDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cfp details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CfpDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFPDETAILS = "SELECT cfpDetails FROM CfpDetails cfpDetails";
	private static final String _SQL_SELECT_CFPDETAILS_WHERE_PKS_IN = "SELECT cfpDetails FROM CfpDetails cfpDetails WHERE CFP_DETAILS_SID IN (";
	private static final String _SQL_SELECT_CFPDETAILS_WHERE = "SELECT cfpDetails FROM CfpDetails cfpDetails WHERE ";
	private static final String _SQL_COUNT_CFPDETAILS = "SELECT COUNT(cfpDetails) FROM CfpDetails cfpDetails";
	private static final String _SQL_COUNT_CFPDETAILS_WHERE = "SELECT COUNT(cfpDetails) FROM CfpDetails cfpDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cfpDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CfpDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CfpDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "companyCfpAttachedStatus", "tradeClass",
				"tradeClassEndDate", "modifiedBy", "companyStartDate",
				"tradeClassStartDate", "createdDate", "companyEndDate",
				"companyMasterSid", "companyCfpAttachedDate", "cfpModelSid",
				"batchId", "cfpDetailsSid", "modifiedDate", "recordLockStatus",
				"source", "inboundStatus"
			});
}