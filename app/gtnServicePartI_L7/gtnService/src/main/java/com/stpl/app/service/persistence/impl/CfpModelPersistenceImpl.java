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

import com.stpl.app.exception.NoSuchCfpModelException;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.impl.CfpModelImpl;
import com.stpl.app.model.impl.CfpModelModelImpl;
import com.stpl.app.service.persistence.CfpModelPersistence;

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
 * The persistence implementation for the cfp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpModelPersistence
 * @see com.stpl.app.service.persistence.CfpModelUtil
 * @generated
 */
@ProviderType
public class CfpModelPersistenceImpl extends BasePersistenceImpl<CfpModel>
	implements CfpModelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CfpModelUtil} to access the cfp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CfpModelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPID = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpId",
			new String[] { String.class.getName() },
			CfpModelModelImpl.CFPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPID = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cfp models where cfpId = &#63;.
	 *
	 * @param cfpId the cfp ID
	 * @return the matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpId(String cfpId) {
		return findByCfpId(cfpId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp models where cfpId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpId the cfp ID
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpId(String cfpId, int start, int end) {
		return findByCfpId(cfpId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpId the cfp ID
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpId(String cfpId, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return findByCfpId(cfpId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpId the cfp ID
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpId(String cfpId, int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID;
			finderArgs = new Object[] { cfpId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPID;
			finderArgs = new Object[] { cfpId, start, end, orderByComparator };
		}

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpModel cfpModel : list) {
					if (!Objects.equals(cfpId, cfpModel.getCfpId())) {
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

			query.append(_SQL_SELECT_CFPMODEL_WHERE);

			boolean bindCfpId = false;

			if (cfpId == null) {
				query.append(_FINDER_COLUMN_CFPID_CFPID_1);
			}
			else if (cfpId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CFPID_CFPID_3);
			}
			else {
				bindCfpId = true;

				query.append(_FINDER_COLUMN_CFPID_CFPID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCfpId) {
					qPos.add(cfpId);
				}

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp model in the ordered set where cfpId = &#63;.
	 *
	 * @param cfpId the cfp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpId_First(String cfpId,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpId_First(cfpId, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpId=");
		msg.append(cfpId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the first cfp model in the ordered set where cfpId = &#63;.
	 *
	 * @param cfpId the cfp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpId_First(String cfpId,
		OrderByComparator<CfpModel> orderByComparator) {
		List<CfpModel> list = findByCfpId(cfpId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpId = &#63;.
	 *
	 * @param cfpId the cfp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpId_Last(String cfpId,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpId_Last(cfpId, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpId=");
		msg.append(cfpId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpId = &#63;.
	 *
	 * @param cfpId the cfp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpId_Last(String cfpId,
		OrderByComparator<CfpModel> orderByComparator) {
		int count = countByCfpId(cfpId);

		if (count == 0) {
			return null;
		}

		List<CfpModel> list = findByCfpId(cfpId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp models before and after the current cfp model in the ordered set where cfpId = &#63;.
	 *
	 * @param cfpModelSid the primary key of the current cfp model
	 * @param cfpId the cfp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel[] findByCfpId_PrevAndNext(int cfpModelSid, String cfpId,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

		Session session = null;

		try {
			session = openSession();

			CfpModel[] array = new CfpModelImpl[3];

			array[0] = getByCfpId_PrevAndNext(session, cfpModel, cfpId,
					orderByComparator, true);

			array[1] = cfpModel;

			array[2] = getByCfpId_PrevAndNext(session, cfpModel, cfpId,
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

	protected CfpModel getByCfpId_PrevAndNext(Session session,
		CfpModel cfpModel, String cfpId,
		OrderByComparator<CfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPMODEL_WHERE);

		boolean bindCfpId = false;

		if (cfpId == null) {
			query.append(_FINDER_COLUMN_CFPID_CFPID_1);
		}
		else if (cfpId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CFPID_CFPID_3);
		}
		else {
			bindCfpId = true;

			query.append(_FINDER_COLUMN_CFPID_CFPID_2);
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
			query.append(CfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCfpId) {
			qPos.add(cfpId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp models where cfpId = &#63; from the database.
	 *
	 * @param cfpId the cfp ID
	 */
	@Override
	public void removeByCfpId(String cfpId) {
		for (CfpModel cfpModel : findByCfpId(cfpId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models where cfpId = &#63;.
	 *
	 * @param cfpId the cfp ID
	 * @return the number of matching cfp models
	 */
	@Override
	public int countByCfpId(String cfpId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPID;

		Object[] finderArgs = new Object[] { cfpId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPMODEL_WHERE);

			boolean bindCfpId = false;

			if (cfpId == null) {
				query.append(_FINDER_COLUMN_CFPID_CFPID_1);
			}
			else if (cfpId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CFPID_CFPID_3);
			}
			else {
				bindCfpId = true;

				query.append(_FINDER_COLUMN_CFPID_CFPID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCfpId) {
					qPos.add(cfpId);
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

	private static final String _FINDER_COLUMN_CFPID_CFPID_1 = "cfpModel.cfpId IS NULL";
	private static final String _FINDER_COLUMN_CFPID_CFPID_2 = "cfpModel.cfpId = ?";
	private static final String _FINDER_COLUMN_CFPID_CFPID_3 = "(cfpModel.cfpId IS NULL OR cfpModel.cfpId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNO = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpNo",
			new String[] { String.class.getName() },
			CfpModelModelImpl.CFPNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPNO = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cfp models where cfpNo = &#63;.
	 *
	 * @param cfpNo the cfp no
	 * @return the matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpNo(String cfpNo) {
		return findByCfpNo(cfpNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp models where cfpNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpNo the cfp no
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpNo(String cfpNo, int start, int end) {
		return findByCfpNo(cfpNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpNo the cfp no
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpNo(String cfpNo, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return findByCfpNo(cfpNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpNo the cfp no
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpNo(String cfpNo, int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO;
			finderArgs = new Object[] { cfpNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNO;
			finderArgs = new Object[] { cfpNo, start, end, orderByComparator };
		}

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpModel cfpModel : list) {
					if (!Objects.equals(cfpNo, cfpModel.getCfpNo())) {
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

			query.append(_SQL_SELECT_CFPMODEL_WHERE);

			boolean bindCfpNo = false;

			if (cfpNo == null) {
				query.append(_FINDER_COLUMN_CFPNO_CFPNO_1);
			}
			else if (cfpNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CFPNO_CFPNO_3);
			}
			else {
				bindCfpNo = true;

				query.append(_FINDER_COLUMN_CFPNO_CFPNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCfpNo) {
					qPos.add(cfpNo);
				}

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp model in the ordered set where cfpNo = &#63;.
	 *
	 * @param cfpNo the cfp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpNo_First(String cfpNo,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpNo_First(cfpNo, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpNo=");
		msg.append(cfpNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the first cfp model in the ordered set where cfpNo = &#63;.
	 *
	 * @param cfpNo the cfp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpNo_First(String cfpNo,
		OrderByComparator<CfpModel> orderByComparator) {
		List<CfpModel> list = findByCfpNo(cfpNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpNo = &#63;.
	 *
	 * @param cfpNo the cfp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpNo_Last(String cfpNo,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpNo_Last(cfpNo, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpNo=");
		msg.append(cfpNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpNo = &#63;.
	 *
	 * @param cfpNo the cfp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpNo_Last(String cfpNo,
		OrderByComparator<CfpModel> orderByComparator) {
		int count = countByCfpNo(cfpNo);

		if (count == 0) {
			return null;
		}

		List<CfpModel> list = findByCfpNo(cfpNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp models before and after the current cfp model in the ordered set where cfpNo = &#63;.
	 *
	 * @param cfpModelSid the primary key of the current cfp model
	 * @param cfpNo the cfp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel[] findByCfpNo_PrevAndNext(int cfpModelSid, String cfpNo,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

		Session session = null;

		try {
			session = openSession();

			CfpModel[] array = new CfpModelImpl[3];

			array[0] = getByCfpNo_PrevAndNext(session, cfpModel, cfpNo,
					orderByComparator, true);

			array[1] = cfpModel;

			array[2] = getByCfpNo_PrevAndNext(session, cfpModel, cfpNo,
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

	protected CfpModel getByCfpNo_PrevAndNext(Session session,
		CfpModel cfpModel, String cfpNo,
		OrderByComparator<CfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPMODEL_WHERE);

		boolean bindCfpNo = false;

		if (cfpNo == null) {
			query.append(_FINDER_COLUMN_CFPNO_CFPNO_1);
		}
		else if (cfpNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CFPNO_CFPNO_3);
		}
		else {
			bindCfpNo = true;

			query.append(_FINDER_COLUMN_CFPNO_CFPNO_2);
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
			query.append(CfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCfpNo) {
			qPos.add(cfpNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp models where cfpNo = &#63; from the database.
	 *
	 * @param cfpNo the cfp no
	 */
	@Override
	public void removeByCfpNo(String cfpNo) {
		for (CfpModel cfpModel : findByCfpNo(cfpNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models where cfpNo = &#63;.
	 *
	 * @param cfpNo the cfp no
	 * @return the number of matching cfp models
	 */
	@Override
	public int countByCfpNo(String cfpNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPNO;

		Object[] finderArgs = new Object[] { cfpNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPMODEL_WHERE);

			boolean bindCfpNo = false;

			if (cfpNo == null) {
				query.append(_FINDER_COLUMN_CFPNO_CFPNO_1);
			}
			else if (cfpNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CFPNO_CFPNO_3);
			}
			else {
				bindCfpNo = true;

				query.append(_FINDER_COLUMN_CFPNO_CFPNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCfpNo) {
					qPos.add(cfpNo);
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

	private static final String _FINDER_COLUMN_CFPNO_CFPNO_1 = "cfpModel.cfpNo IS NULL";
	private static final String _FINDER_COLUMN_CFPNO_CFPNO_2 = "cfpModel.cfpNo = ?";
	private static final String _FINDER_COLUMN_CFPNO_CFPNO_3 = "(cfpModel.cfpNo IS NULL OR cfpModel.cfpNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNAME = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME =
		new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpName",
			new String[] { String.class.getName() },
			CfpModelModelImpl.CFPNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPNAME = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the cfp models where cfpName = &#63;.
	 *
	 * @param cfpName the cfp name
	 * @return the matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpName(String cfpName) {
		return findByCfpName(cfpName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp models where cfpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpName the cfp name
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpName(String cfpName, int start, int end) {
		return findByCfpName(cfpName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpName the cfp name
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpName(String cfpName, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return findByCfpName(cfpName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpName the cfp name
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpName(String cfpName, int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME;
			finderArgs = new Object[] { cfpName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPNAME;
			finderArgs = new Object[] { cfpName, start, end, orderByComparator };
		}

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpModel cfpModel : list) {
					if (!Objects.equals(cfpName, cfpModel.getCfpName())) {
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

			query.append(_SQL_SELECT_CFPMODEL_WHERE);

			boolean bindCfpName = false;

			if (cfpName == null) {
				query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_1);
			}
			else if (cfpName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_3);
			}
			else {
				bindCfpName = true;

				query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCfpName) {
					qPos.add(cfpName);
				}

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp model in the ordered set where cfpName = &#63;.
	 *
	 * @param cfpName the cfp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpName_First(String cfpName,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpName_First(cfpName, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpName=");
		msg.append(cfpName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the first cfp model in the ordered set where cfpName = &#63;.
	 *
	 * @param cfpName the cfp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpName_First(String cfpName,
		OrderByComparator<CfpModel> orderByComparator) {
		List<CfpModel> list = findByCfpName(cfpName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpName = &#63;.
	 *
	 * @param cfpName the cfp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpName_Last(String cfpName,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpName_Last(cfpName, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpName=");
		msg.append(cfpName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpName = &#63;.
	 *
	 * @param cfpName the cfp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpName_Last(String cfpName,
		OrderByComparator<CfpModel> orderByComparator) {
		int count = countByCfpName(cfpName);

		if (count == 0) {
			return null;
		}

		List<CfpModel> list = findByCfpName(cfpName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp models before and after the current cfp model in the ordered set where cfpName = &#63;.
	 *
	 * @param cfpModelSid the primary key of the current cfp model
	 * @param cfpName the cfp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel[] findByCfpName_PrevAndNext(int cfpModelSid,
		String cfpName, OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

		Session session = null;

		try {
			session = openSession();

			CfpModel[] array = new CfpModelImpl[3];

			array[0] = getByCfpName_PrevAndNext(session, cfpModel, cfpName,
					orderByComparator, true);

			array[1] = cfpModel;

			array[2] = getByCfpName_PrevAndNext(session, cfpModel, cfpName,
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

	protected CfpModel getByCfpName_PrevAndNext(Session session,
		CfpModel cfpModel, String cfpName,
		OrderByComparator<CfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPMODEL_WHERE);

		boolean bindCfpName = false;

		if (cfpName == null) {
			query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_1);
		}
		else if (cfpName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_3);
		}
		else {
			bindCfpName = true;

			query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_2);
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
			query.append(CfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCfpName) {
			qPos.add(cfpName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp models where cfpName = &#63; from the database.
	 *
	 * @param cfpName the cfp name
	 */
	@Override
	public void removeByCfpName(String cfpName) {
		for (CfpModel cfpModel : findByCfpName(cfpName, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models where cfpName = &#63;.
	 *
	 * @param cfpName the cfp name
	 * @return the number of matching cfp models
	 */
	@Override
	public int countByCfpName(String cfpName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPNAME;

		Object[] finderArgs = new Object[] { cfpName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPMODEL_WHERE);

			boolean bindCfpName = false;

			if (cfpName == null) {
				query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_1);
			}
			else if (cfpName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_3);
			}
			else {
				bindCfpName = true;

				query.append(_FINDER_COLUMN_CFPNAME_CFPNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCfpName) {
					qPos.add(cfpName);
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

	private static final String _FINDER_COLUMN_CFPNAME_CFPNAME_1 = "cfpModel.cfpName IS NULL";
	private static final String _FINDER_COLUMN_CFPNAME_CFPNAME_2 = "cfpModel.cfpName = ?";
	private static final String _FINDER_COLUMN_CFPNAME_CFPNAME_3 = "(cfpModel.cfpName IS NULL OR cfpModel.cfpName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTYPE = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE =
		new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpType",
			new String[] { Integer.class.getName() },
			CfpModelModelImpl.CFPTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPTYPE = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the cfp models where cfpType = &#63;.
	 *
	 * @param cfpType the cfp type
	 * @return the matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpType(int cfpType) {
		return findByCfpType(cfpType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp models where cfpType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpType the cfp type
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpType(int cfpType, int start, int end) {
		return findByCfpType(cfpType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpType the cfp type
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpType(int cfpType, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return findByCfpType(cfpType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpType the cfp type
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpType(int cfpType, int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE;
			finderArgs = new Object[] { cfpType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTYPE;
			finderArgs = new Object[] { cfpType, start, end, orderByComparator };
		}

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpModel cfpModel : list) {
					if ((cfpType != cfpModel.getCfpType())) {
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

			query.append(_SQL_SELECT_CFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_CFPTYPE_CFPTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpType);

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp model in the ordered set where cfpType = &#63;.
	 *
	 * @param cfpType the cfp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpType_First(int cfpType,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpType_First(cfpType, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpType=");
		msg.append(cfpType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the first cfp model in the ordered set where cfpType = &#63;.
	 *
	 * @param cfpType the cfp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpType_First(int cfpType,
		OrderByComparator<CfpModel> orderByComparator) {
		List<CfpModel> list = findByCfpType(cfpType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpType = &#63;.
	 *
	 * @param cfpType the cfp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpType_Last(int cfpType,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpType_Last(cfpType, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpType=");
		msg.append(cfpType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpType = &#63;.
	 *
	 * @param cfpType the cfp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpType_Last(int cfpType,
		OrderByComparator<CfpModel> orderByComparator) {
		int count = countByCfpType(cfpType);

		if (count == 0) {
			return null;
		}

		List<CfpModel> list = findByCfpType(cfpType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp models before and after the current cfp model in the ordered set where cfpType = &#63;.
	 *
	 * @param cfpModelSid the primary key of the current cfp model
	 * @param cfpType the cfp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel[] findByCfpType_PrevAndNext(int cfpModelSid, int cfpType,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

		Session session = null;

		try {
			session = openSession();

			CfpModel[] array = new CfpModelImpl[3];

			array[0] = getByCfpType_PrevAndNext(session, cfpModel, cfpType,
					orderByComparator, true);

			array[1] = cfpModel;

			array[2] = getByCfpType_PrevAndNext(session, cfpModel, cfpType,
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

	protected CfpModel getByCfpType_PrevAndNext(Session session,
		CfpModel cfpModel, int cfpType,
		OrderByComparator<CfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPMODEL_WHERE);

		query.append(_FINDER_COLUMN_CFPTYPE_CFPTYPE_2);

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
			query.append(CfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(cfpType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp models where cfpType = &#63; from the database.
	 *
	 * @param cfpType the cfp type
	 */
	@Override
	public void removeByCfpType(int cfpType) {
		for (CfpModel cfpModel : findByCfpType(cfpType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models where cfpType = &#63;.
	 *
	 * @param cfpType the cfp type
	 * @return the number of matching cfp models
	 */
	@Override
	public int countByCfpType(int cfpType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPTYPE;

		Object[] finderArgs = new Object[] { cfpType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_CFPTYPE_CFPTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpType);

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

	private static final String _FINDER_COLUMN_CFPTYPE_CFPTYPE_2 = "cfpModel.cfpType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPSTATUS =
		new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS =
		new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpStatus",
			new String[] { Integer.class.getName() },
			CfpModelModelImpl.CFPSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPSTATUS = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the cfp models where cfpStatus = &#63;.
	 *
	 * @param cfpStatus the cfp status
	 * @return the matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpStatus(int cfpStatus) {
		return findByCfpStatus(cfpStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the cfp models where cfpStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpStatus the cfp status
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpStatus(int cfpStatus, int start, int end) {
		return findByCfpStatus(cfpStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpStatus the cfp status
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpStatus(int cfpStatus, int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return findByCfpStatus(cfpStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpStatus the cfp status
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpStatus(int cfpStatus, int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS;
			finderArgs = new Object[] { cfpStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPSTATUS;
			finderArgs = new Object[] { cfpStatus, start, end, orderByComparator };
		}

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpModel cfpModel : list) {
					if ((cfpStatus != cfpModel.getCfpStatus())) {
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

			query.append(_SQL_SELECT_CFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpStatus);

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
	 *
	 * @param cfpStatus the cfp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpStatus_First(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpStatus_First(cfpStatus, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpStatus=");
		msg.append(cfpStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the first cfp model in the ordered set where cfpStatus = &#63;.
	 *
	 * @param cfpStatus the cfp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpStatus_First(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator) {
		List<CfpModel> list = findByCfpStatus(cfpStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
	 *
	 * @param cfpStatus the cfp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpStatus_Last(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpStatus_Last(cfpStatus, orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpStatus=");
		msg.append(cfpStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpStatus = &#63;.
	 *
	 * @param cfpStatus the cfp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpStatus_Last(int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator) {
		int count = countByCfpStatus(cfpStatus);

		if (count == 0) {
			return null;
		}

		List<CfpModel> list = findByCfpStatus(cfpStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp models before and after the current cfp model in the ordered set where cfpStatus = &#63;.
	 *
	 * @param cfpModelSid the primary key of the current cfp model
	 * @param cfpStatus the cfp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel[] findByCfpStatus_PrevAndNext(int cfpModelSid,
		int cfpStatus, OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

		Session session = null;

		try {
			session = openSession();

			CfpModel[] array = new CfpModelImpl[3];

			array[0] = getByCfpStatus_PrevAndNext(session, cfpModel, cfpStatus,
					orderByComparator, true);

			array[1] = cfpModel;

			array[2] = getByCfpStatus_PrevAndNext(session, cfpModel, cfpStatus,
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

	protected CfpModel getByCfpStatus_PrevAndNext(Session session,
		CfpModel cfpModel, int cfpStatus,
		OrderByComparator<CfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPMODEL_WHERE);

		query.append(_FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2);

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
			query.append(CfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(cfpStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp models where cfpStatus = &#63; from the database.
	 *
	 * @param cfpStatus the cfp status
	 */
	@Override
	public void removeByCfpStatus(int cfpStatus) {
		for (CfpModel cfpModel : findByCfpStatus(cfpStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models where cfpStatus = &#63;.
	 *
	 * @param cfpStatus the cfp status
	 * @return the number of matching cfp models
	 */
	@Override
	public int countByCfpStatus(int cfpStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPSTATUS;

		Object[] finderArgs = new Object[] { cfpStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpStatus);

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

	private static final String _FINDER_COLUMN_CFPSTATUS_CFPSTATUS_2 = "cfpModel.cfpStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTRADECLASS =
		new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCfpTradeClass",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS =
		new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, CfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCfpTradeClass",
			new String[] { Integer.class.getName() },
			CfpModelModelImpl.CFPTRADECLASS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CFPTRADECLASS = new FinderPath(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCfpTradeClass",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the cfp models where cfpTradeClass = &#63;.
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @return the matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpTradeClass(int cfpTradeClass) {
		return findByCfpTradeClass(cfpTradeClass, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp models where cfpTradeClass = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpTradeClass(int cfpTradeClass, int start,
		int end) {
		return findByCfpTradeClass(cfpTradeClass, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpTradeClass = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpTradeClass(int cfpTradeClass, int start,
		int end, OrderByComparator<CfpModel> orderByComparator) {
		return findByCfpTradeClass(cfpTradeClass, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models where cfpTradeClass = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cfp models
	 */
	@Override
	public List<CfpModel> findByCfpTradeClass(int cfpTradeClass, int start,
		int end, OrderByComparator<CfpModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS;
			finderArgs = new Object[] { cfpTradeClass };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CFPTRADECLASS;
			finderArgs = new Object[] {
					cfpTradeClass,
					
					start, end, orderByComparator
				};
		}

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CfpModel cfpModel : list) {
					if ((cfpTradeClass != cfpModel.getCfpTradeClass())) {
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

			query.append(_SQL_SELECT_CFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpTradeClass);

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpTradeClass_First(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpTradeClass_First(cfpTradeClass,
				orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpTradeClass=");
		msg.append(cfpTradeClass);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the first cfp model in the ordered set where cfpTradeClass = &#63;.
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpTradeClass_First(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator) {
		List<CfpModel> list = findByCfpTradeClass(cfpTradeClass, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model
	 * @throws NoSuchCfpModelException if a matching cfp model could not be found
	 */
	@Override
	public CfpModel findByCfpTradeClass_Last(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByCfpTradeClass_Last(cfpTradeClass,
				orderByComparator);

		if (cfpModel != null) {
			return cfpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("cfpTradeClass=");
		msg.append(cfpTradeClass);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCfpModelException(msg.toString());
	}

	/**
	 * Returns the last cfp model in the ordered set where cfpTradeClass = &#63;.
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cfp model, or <code>null</code> if a matching cfp model could not be found
	 */
	@Override
	public CfpModel fetchByCfpTradeClass_Last(int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator) {
		int count = countByCfpTradeClass(cfpTradeClass);

		if (count == 0) {
			return null;
		}

		List<CfpModel> list = findByCfpTradeClass(cfpTradeClass, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cfp models before and after the current cfp model in the ordered set where cfpTradeClass = &#63;.
	 *
	 * @param cfpModelSid the primary key of the current cfp model
	 * @param cfpTradeClass the cfp trade class
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel[] findByCfpTradeClass_PrevAndNext(int cfpModelSid,
		int cfpTradeClass, OrderByComparator<CfpModel> orderByComparator)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = findByPrimaryKey(cfpModelSid);

		Session session = null;

		try {
			session = openSession();

			CfpModel[] array = new CfpModelImpl[3];

			array[0] = getByCfpTradeClass_PrevAndNext(session, cfpModel,
					cfpTradeClass, orderByComparator, true);

			array[1] = cfpModel;

			array[2] = getByCfpTradeClass_PrevAndNext(session, cfpModel,
					cfpTradeClass, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CfpModel getByCfpTradeClass_PrevAndNext(Session session,
		CfpModel cfpModel, int cfpTradeClass,
		OrderByComparator<CfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CFPMODEL_WHERE);

		query.append(_FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2);

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
			query.append(CfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(cfpTradeClass);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(cfpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cfp models where cfpTradeClass = &#63; from the database.
	 *
	 * @param cfpTradeClass the cfp trade class
	 */
	@Override
	public void removeByCfpTradeClass(int cfpTradeClass) {
		for (CfpModel cfpModel : findByCfpTradeClass(cfpTradeClass,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models where cfpTradeClass = &#63;.
	 *
	 * @param cfpTradeClass the cfp trade class
	 * @return the number of matching cfp models
	 */
	@Override
	public int countByCfpTradeClass(int cfpTradeClass) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CFPTRADECLASS;

		Object[] finderArgs = new Object[] { cfpTradeClass };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(cfpTradeClass);

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

	private static final String _FINDER_COLUMN_CFPTRADECLASS_CFPTRADECLASS_2 = "cfpModel.cfpTradeClass = ?";

	public CfpModelPersistenceImpl() {
		setModelClass(CfpModel.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("cfpType", "CFP_TYPE");
			dbColumnNames.put("cfpTradeClass", "CFP_TRADE_CLASS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("cfpNo", "CFP_NO");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");
			dbColumnNames.put("cfpDesignation", "CFP_DESIGNATION");
			dbColumnNames.put("salesInclusion", "SALES_INCLUSION");
			dbColumnNames.put("cfpName", "CFP_NAME");
			dbColumnNames.put("cfpCategory", "CFP_CATEGORY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("cfpId", "CFP_ID");
			dbColumnNames.put("cfpStatus", "CFP_STATUS");
			dbColumnNames.put("parentCfpId", "PARENT_CFP_ID");
			dbColumnNames.put("cfpStartDate", "CFP_START_DATE");
			dbColumnNames.put("cfpEndDate", "CFP_END_DATE");
			dbColumnNames.put("parentCfpName", "PARENT_CFP_NAME");
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
	 * Caches the cfp model in the entity cache if it is enabled.
	 *
	 * @param cfpModel the cfp model
	 */
	@Override
	public void cacheResult(CfpModel cfpModel) {
		entityCache.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelImpl.class, cfpModel.getPrimaryKey(), cfpModel);

		cfpModel.resetOriginalValues();
	}

	/**
	 * Caches the cfp models in the entity cache if it is enabled.
	 *
	 * @param cfpModels the cfp models
	 */
	@Override
	public void cacheResult(List<CfpModel> cfpModels) {
		for (CfpModel cfpModel : cfpModels) {
			if (entityCache.getResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
						CfpModelImpl.class, cfpModel.getPrimaryKey()) == null) {
				cacheResult(cfpModel);
			}
			else {
				cfpModel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cfp models.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CfpModelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cfp model.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CfpModel cfpModel) {
		entityCache.removeResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelImpl.class, cfpModel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CfpModel> cfpModels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CfpModel cfpModel : cfpModels) {
			entityCache.removeResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
				CfpModelImpl.class, cfpModel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cfp model with the primary key. Does not add the cfp model to the database.
	 *
	 * @param cfpModelSid the primary key for the new cfp model
	 * @return the new cfp model
	 */
	@Override
	public CfpModel create(int cfpModelSid) {
		CfpModel cfpModel = new CfpModelImpl();

		cfpModel.setNew(true);
		cfpModel.setPrimaryKey(cfpModelSid);

		return cfpModel;
	}

	/**
	 * Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cfpModelSid the primary key of the cfp model
	 * @return the cfp model that was removed
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel remove(int cfpModelSid) throws NoSuchCfpModelException {
		return remove((Serializable)cfpModelSid);
	}

	/**
	 * Removes the cfp model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cfp model
	 * @return the cfp model that was removed
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel remove(Serializable primaryKey)
		throws NoSuchCfpModelException {
		Session session = null;

		try {
			session = openSession();

			CfpModel cfpModel = (CfpModel)session.get(CfpModelImpl.class,
					primaryKey);

			if (cfpModel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cfpModel);
		}
		catch (NoSuchCfpModelException nsee) {
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
	protected CfpModel removeImpl(CfpModel cfpModel) {
		cfpModel = toUnwrappedModel(cfpModel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cfpModel)) {
				cfpModel = (CfpModel)session.get(CfpModelImpl.class,
						cfpModel.getPrimaryKeyObj());
			}

			if (cfpModel != null) {
				session.delete(cfpModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cfpModel != null) {
			clearCache(cfpModel);
		}

		return cfpModel;
	}

	@Override
	public CfpModel updateImpl(CfpModel cfpModel) {
		cfpModel = toUnwrappedModel(cfpModel);

		boolean isNew = cfpModel.isNew();

		CfpModelModelImpl cfpModelModelImpl = (CfpModelModelImpl)cfpModel;

		Session session = null;

		try {
			session = openSession();

			if (cfpModel.isNew()) {
				session.save(cfpModel);

				cfpModel.setNew(false);
			}
			else {
				cfpModel = (CfpModel)session.merge(cfpModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CfpModelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { cfpModelModelImpl.getCfpId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID,
				args);

			args = new Object[] { cfpModelModelImpl.getCfpNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO,
				args);

			args = new Object[] { cfpModelModelImpl.getCfpName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME,
				args);

			args = new Object[] { cfpModelModelImpl.getCfpType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE,
				args);

			args = new Object[] { cfpModelModelImpl.getCfpStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPSTATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS,
				args);

			args = new Object[] { cfpModelModelImpl.getCfpTradeClass() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPTRADECLASS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((cfpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpModelModelImpl.getOriginalCfpId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID,
					args);

				args = new Object[] { cfpModelModelImpl.getCfpId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPID,
					args);
			}

			if ((cfpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpModelModelImpl.getOriginalCfpNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO,
					args);

				args = new Object[] { cfpModelModelImpl.getCfpNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNO,
					args);
			}

			if ((cfpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpModelModelImpl.getOriginalCfpName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME,
					args);

				args = new Object[] { cfpModelModelImpl.getCfpName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPNAME,
					args);
			}

			if ((cfpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpModelModelImpl.getOriginalCfpType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE,
					args);

				args = new Object[] { cfpModelModelImpl.getCfpType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTYPE,
					args);
			}

			if ((cfpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpModelModelImpl.getOriginalCfpStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS,
					args);

				args = new Object[] { cfpModelModelImpl.getCfpStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPSTATUS,
					args);
			}

			if ((cfpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						cfpModelModelImpl.getOriginalCfpTradeClass()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPTRADECLASS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS,
					args);

				args = new Object[] { cfpModelModelImpl.getCfpTradeClass() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CFPTRADECLASS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CFPTRADECLASS,
					args);
			}
		}

		entityCache.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
			CfpModelImpl.class, cfpModel.getPrimaryKey(), cfpModel, false);

		cfpModel.resetOriginalValues();

		return cfpModel;
	}

	protected CfpModel toUnwrappedModel(CfpModel cfpModel) {
		if (cfpModel instanceof CfpModelImpl) {
			return cfpModel;
		}

		CfpModelImpl cfpModelImpl = new CfpModelImpl();

		cfpModelImpl.setNew(cfpModel.isNew());
		cfpModelImpl.setPrimaryKey(cfpModel.getPrimaryKey());

		cfpModelImpl.setCreatedBy(cfpModel.getCreatedBy());
		cfpModelImpl.setCfpType(cfpModel.getCfpType());
		cfpModelImpl.setCfpTradeClass(cfpModel.getCfpTradeClass());
		cfpModelImpl.setModifiedBy(cfpModel.getModifiedBy());
		cfpModelImpl.setCreatedDate(cfpModel.getCreatedDate());
		cfpModelImpl.setCfpNo(cfpModel.getCfpNo());
		cfpModelImpl.setCfpModelSid(cfpModel.getCfpModelSid());
		cfpModelImpl.setBatchId(cfpModel.getBatchId());
		cfpModelImpl.setModifiedDate(cfpModel.getModifiedDate());
		cfpModelImpl.setRecordLockStatus(cfpModel.isRecordLockStatus());
		cfpModelImpl.setInternalNotes(cfpModel.getInternalNotes());
		cfpModelImpl.setCfpDesignation(cfpModel.getCfpDesignation());
		cfpModelImpl.setSalesInclusion(cfpModel.getSalesInclusion());
		cfpModelImpl.setCfpName(cfpModel.getCfpName());
		cfpModelImpl.setCfpCategory(cfpModel.getCfpCategory());
		cfpModelImpl.setSource(cfpModel.getSource());
		cfpModelImpl.setCfpId(cfpModel.getCfpId());
		cfpModelImpl.setCfpStatus(cfpModel.getCfpStatus());
		cfpModelImpl.setParentCfpId(cfpModel.getParentCfpId());
		cfpModelImpl.setCfpStartDate(cfpModel.getCfpStartDate());
		cfpModelImpl.setCfpEndDate(cfpModel.getCfpEndDate());
		cfpModelImpl.setParentCfpName(cfpModel.getParentCfpName());
		cfpModelImpl.setInboundStatus(cfpModel.getInboundStatus());

		return cfpModelImpl;
	}

	/**
	 * Returns the cfp model with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cfp model
	 * @return the cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCfpModelException {
		CfpModel cfpModel = fetchByPrimaryKey(primaryKey);

		if (cfpModel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cfpModel;
	}

	/**
	 * Returns the cfp model with the primary key or throws a {@link NoSuchCfpModelException} if it could not be found.
	 *
	 * @param cfpModelSid the primary key of the cfp model
	 * @return the cfp model
	 * @throws NoSuchCfpModelException if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel findByPrimaryKey(int cfpModelSid)
		throws NoSuchCfpModelException {
		return findByPrimaryKey((Serializable)cfpModelSid);
	}

	/**
	 * Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cfp model
	 * @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
				CfpModelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CfpModel cfpModel = (CfpModel)serializable;

		if (cfpModel == null) {
			Session session = null;

			try {
				session = openSession();

				cfpModel = (CfpModel)session.get(CfpModelImpl.class, primaryKey);

				if (cfpModel != null) {
					cacheResult(cfpModel);
				}
				else {
					entityCache.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
						CfpModelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
					CfpModelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cfpModel;
	}

	/**
	 * Returns the cfp model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cfpModelSid the primary key of the cfp model
	 * @return the cfp model, or <code>null</code> if a cfp model with the primary key could not be found
	 */
	@Override
	public CfpModel fetchByPrimaryKey(int cfpModelSid) {
		return fetchByPrimaryKey((Serializable)cfpModelSid);
	}

	@Override
	public Map<Serializable, CfpModel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CfpModel> map = new HashMap<Serializable, CfpModel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CfpModel cfpModel = fetchByPrimaryKey(primaryKey);

			if (cfpModel != null) {
				map.put(primaryKey, cfpModel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
					CfpModelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CfpModel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFPMODEL_WHERE_PKS_IN);

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

			for (CfpModel cfpModel : (List<CfpModel>)q.list()) {
				map.put(cfpModel.getPrimaryKeyObj(), cfpModel);

				cacheResult(cfpModel);

				uncachedPrimaryKeys.remove(cfpModel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CfpModelModelImpl.ENTITY_CACHE_ENABLED,
					CfpModelImpl.class, primaryKey, nullModel);
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
	 * Returns all the cfp models.
	 *
	 * @return the cfp models
	 */
	@Override
	public List<CfpModel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @return the range of cfp models
	 */
	@Override
	public List<CfpModel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cfp models
	 */
	@Override
	public List<CfpModel> findAll(int start, int end,
		OrderByComparator<CfpModel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp models
	 * @param end the upper bound of the range of cfp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cfp models
	 */
	@Override
	public List<CfpModel> findAll(int start, int end,
		OrderByComparator<CfpModel> orderByComparator, boolean retrieveFromCache) {
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

		List<CfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<CfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFPMODEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFPMODEL;

				if (pagination) {
					sql = sql.concat(CfpModelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cfp models from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CfpModel cfpModel : findAll()) {
			remove(cfpModel);
		}
	}

	/**
	 * Returns the number of cfp models.
	 *
	 * @return the number of cfp models
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFPMODEL);

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
		return CfpModelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cfp model persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CfpModelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFPMODEL = "SELECT cfpModel FROM CfpModel cfpModel";
	private static final String _SQL_SELECT_CFPMODEL_WHERE_PKS_IN = "SELECT cfpModel FROM CfpModel cfpModel WHERE CFP_MODEL_SID IN (";
	private static final String _SQL_SELECT_CFPMODEL_WHERE = "SELECT cfpModel FROM CfpModel cfpModel WHERE ";
	private static final String _SQL_COUNT_CFPMODEL = "SELECT COUNT(cfpModel) FROM CfpModel cfpModel";
	private static final String _SQL_COUNT_CFPMODEL_WHERE = "SELECT COUNT(cfpModel) FROM CfpModel cfpModel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cfpModel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpModel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CfpModel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CfpModelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "cfpType", "cfpTradeClass", "modifiedBy",
				"createdDate", "cfpNo", "cfpModelSid", "batchId", "modifiedDate",
				"recordLockStatus", "internalNotes", "cfpDesignation",
				"salesInclusion", "cfpName", "cfpCategory", "source", "cfpId",
				"cfpStatus", "parentCfpId", "cfpStartDate", "cfpEndDate",
				"parentCfpName", "inboundStatus"
			});
}