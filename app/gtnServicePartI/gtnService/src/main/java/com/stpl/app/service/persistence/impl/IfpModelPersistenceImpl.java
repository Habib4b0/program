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

import com.stpl.app.exception.NoSuchIfpModelException;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.impl.IfpModelImpl;
import com.stpl.app.model.impl.IfpModelModelImpl;
import com.stpl.app.service.persistence.IfpModelPersistence;

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
 * The persistence implementation for the ifp model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpModelPersistence
 * @see com.stpl.app.service.persistence.IfpModelUtil
 * @generated
 */
@ProviderType
public class IfpModelPersistenceImpl extends BasePersistenceImpl<IfpModel>
	implements IfpModelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IfpModelUtil} to access the ifp model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IfpModelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANID =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemFamilyPlanId", new String[] { String.class.getName() },
			IfpModelModelImpl.IFPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemFamilyPlanId", new String[] { String.class.getName() });

	/**
	 * Returns all the ifp models where ifpId = &#63;.
	 *
	 * @param ifpId the ifp ID
	 * @return the matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanId(String ifpId) {
		return findByItemFamilyPlanId(ifpId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp models where ifpId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpId the ifp ID
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @return the range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanId(String ifpId, int start,
		int end) {
		return findByItemFamilyPlanId(ifpId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpId the ifp ID
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanId(String ifpId, int start,
		int end, OrderByComparator<IfpModel> orderByComparator) {
		return findByItemFamilyPlanId(ifpId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpId the ifp ID
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanId(String ifpId, int start,
		int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID;
			finderArgs = new Object[] { ifpId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANID;
			finderArgs = new Object[] { ifpId, start, end, orderByComparator };
		}

		List<IfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<IfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IfpModel ifpModel : list) {
					if (!Objects.equals(ifpId, ifpModel.getIfpId())) {
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

			query.append(_SQL_SELECT_IFPMODEL_WHERE);

			boolean bindIfpId = false;

			if (ifpId == null) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1);
			}
			else if (ifpId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3);
			}
			else {
				bindIfpId = true;

				query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIfpId) {
					qPos.add(ifpId);
				}

				if (!pagination) {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ifp model in the ordered set where ifpId = &#63;.
	 *
	 * @param ifpId the ifp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanId_First(String ifpId,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanId_First(ifpId,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpId=");
		msg.append(ifpId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the first ifp model in the ordered set where ifpId = &#63;.
	 *
	 * @param ifpId the ifp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanId_First(String ifpId,
		OrderByComparator<IfpModel> orderByComparator) {
		List<IfpModel> list = findByItemFamilyPlanId(ifpId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpId = &#63;.
	 *
	 * @param ifpId the ifp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanId_Last(String ifpId,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanId_Last(ifpId,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpId=");
		msg.append(ifpId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpId = &#63;.
	 *
	 * @param ifpId the ifp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanId_Last(String ifpId,
		OrderByComparator<IfpModel> orderByComparator) {
		int count = countByItemFamilyPlanId(ifpId);

		if (count == 0) {
			return null;
		}

		List<IfpModel> list = findByItemFamilyPlanId(ifpId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ifp models before and after the current ifp model in the ordered set where ifpId = &#63;.
	 *
	 * @param ifpModelSid the primary key of the current ifp model
	 * @param ifpId the ifp ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel[] findByItemFamilyPlanId_PrevAndNext(int ifpModelSid,
		String ifpId, OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

		Session session = null;

		try {
			session = openSession();

			IfpModel[] array = new IfpModelImpl[3];

			array[0] = getByItemFamilyPlanId_PrevAndNext(session, ifpModel,
					ifpId, orderByComparator, true);

			array[1] = ifpModel;

			array[2] = getByItemFamilyPlanId_PrevAndNext(session, ifpModel,
					ifpId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IfpModel getByItemFamilyPlanId_PrevAndNext(Session session,
		IfpModel ifpModel, String ifpId,
		OrderByComparator<IfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IFPMODEL_WHERE);

		boolean bindIfpId = false;

		if (ifpId == null) {
			query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1);
		}
		else if (ifpId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3);
		}
		else {
			bindIfpId = true;

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2);
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
			query.append(IfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIfpId) {
			qPos.add(ifpId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ifp models where ifpId = &#63; from the database.
	 *
	 * @param ifpId the ifp ID
	 */
	@Override
	public void removeByItemFamilyPlanId(String ifpId) {
		for (IfpModel ifpModel : findByItemFamilyPlanId(ifpId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ifpModel);
		}
	}

	/**
	 * Returns the number of ifp models where ifpId = &#63;.
	 *
	 * @param ifpId the ifp ID
	 * @return the number of matching ifp models
	 */
	@Override
	public int countByItemFamilyPlanId(String ifpId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID;

		Object[] finderArgs = new Object[] { ifpId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IFPMODEL_WHERE);

			boolean bindIfpId = false;

			if (ifpId == null) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1);
			}
			else if (ifpId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3);
			}
			else {
				bindIfpId = true;

				query.append(_FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIfpId) {
					qPos.add(ifpId);
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

	private static final String _FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_1 = "ifpModel.ifpId IS NULL";
	private static final String _FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_2 = "ifpModel.ifpId = ?";
	private static final String _FINDER_COLUMN_ITEMFAMILYPLANID_IFPID_3 = "(ifpModel.ifpId IS NULL OR ifpModel.ifpId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNO =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemFamilyPlanNo", new String[] { String.class.getName() },
			IfpModelModelImpl.IFPNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemFamilyPlanNo", new String[] { String.class.getName() });

	/**
	 * Returns all the ifp models where ifpNo = &#63;.
	 *
	 * @param ifpNo the ifp no
	 * @return the matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanNo(String ifpNo) {
		return findByItemFamilyPlanNo(ifpNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp models where ifpNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpNo the ifp no
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @return the range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanNo(String ifpNo, int start,
		int end) {
		return findByItemFamilyPlanNo(ifpNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpNo the ifp no
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanNo(String ifpNo, int start,
		int end, OrderByComparator<IfpModel> orderByComparator) {
		return findByItemFamilyPlanNo(ifpNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpNo the ifp no
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanNo(String ifpNo, int start,
		int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO;
			finderArgs = new Object[] { ifpNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNO;
			finderArgs = new Object[] { ifpNo, start, end, orderByComparator };
		}

		List<IfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<IfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IfpModel ifpModel : list) {
					if (!Objects.equals(ifpNo, ifpModel.getIfpNo())) {
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

			query.append(_SQL_SELECT_IFPMODEL_WHERE);

			boolean bindIfpNo = false;

			if (ifpNo == null) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1);
			}
			else if (ifpNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3);
			}
			else {
				bindIfpNo = true;

				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIfpNo) {
					qPos.add(ifpNo);
				}

				if (!pagination) {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ifp model in the ordered set where ifpNo = &#63;.
	 *
	 * @param ifpNo the ifp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanNo_First(String ifpNo,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanNo_First(ifpNo,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpNo=");
		msg.append(ifpNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the first ifp model in the ordered set where ifpNo = &#63;.
	 *
	 * @param ifpNo the ifp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanNo_First(String ifpNo,
		OrderByComparator<IfpModel> orderByComparator) {
		List<IfpModel> list = findByItemFamilyPlanNo(ifpNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpNo = &#63;.
	 *
	 * @param ifpNo the ifp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanNo_Last(String ifpNo,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanNo_Last(ifpNo,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpNo=");
		msg.append(ifpNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpNo = &#63;.
	 *
	 * @param ifpNo the ifp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanNo_Last(String ifpNo,
		OrderByComparator<IfpModel> orderByComparator) {
		int count = countByItemFamilyPlanNo(ifpNo);

		if (count == 0) {
			return null;
		}

		List<IfpModel> list = findByItemFamilyPlanNo(ifpNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ifp models before and after the current ifp model in the ordered set where ifpNo = &#63;.
	 *
	 * @param ifpModelSid the primary key of the current ifp model
	 * @param ifpNo the ifp no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel[] findByItemFamilyPlanNo_PrevAndNext(int ifpModelSid,
		String ifpNo, OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

		Session session = null;

		try {
			session = openSession();

			IfpModel[] array = new IfpModelImpl[3];

			array[0] = getByItemFamilyPlanNo_PrevAndNext(session, ifpModel,
					ifpNo, orderByComparator, true);

			array[1] = ifpModel;

			array[2] = getByItemFamilyPlanNo_PrevAndNext(session, ifpModel,
					ifpNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IfpModel getByItemFamilyPlanNo_PrevAndNext(Session session,
		IfpModel ifpModel, String ifpNo,
		OrderByComparator<IfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IFPMODEL_WHERE);

		boolean bindIfpNo = false;

		if (ifpNo == null) {
			query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1);
		}
		else if (ifpNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3);
		}
		else {
			bindIfpNo = true;

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2);
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
			query.append(IfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIfpNo) {
			qPos.add(ifpNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ifp models where ifpNo = &#63; from the database.
	 *
	 * @param ifpNo the ifp no
	 */
	@Override
	public void removeByItemFamilyPlanNo(String ifpNo) {
		for (IfpModel ifpModel : findByItemFamilyPlanNo(ifpNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ifpModel);
		}
	}

	/**
	 * Returns the number of ifp models where ifpNo = &#63;.
	 *
	 * @param ifpNo the ifp no
	 * @return the number of matching ifp models
	 */
	@Override
	public int countByItemFamilyPlanNo(String ifpNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO;

		Object[] finderArgs = new Object[] { ifpNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IFPMODEL_WHERE);

			boolean bindIfpNo = false;

			if (ifpNo == null) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1);
			}
			else if (ifpNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3);
			}
			else {
				bindIfpNo = true;

				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIfpNo) {
					qPos.add(ifpNo);
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

	private static final String _FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_1 = "ifpModel.ifpNo IS NULL";
	private static final String _FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_2 = "ifpModel.ifpNo = ?";
	private static final String _FINDER_COLUMN_ITEMFAMILYPLANNO_IFPNO_3 = "(ifpModel.ifpNo IS NULL OR ifpModel.ifpNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemFamilyPlanName",
			new String[] { String.class.getName() },
			IfpModelModelImpl.IFPNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemFamilyPlanName", new String[] { String.class.getName() });

	/**
	 * Returns all the ifp models where ifpName = &#63;.
	 *
	 * @param ifpName the ifp name
	 * @return the matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanName(String ifpName) {
		return findByItemFamilyPlanName(ifpName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp models where ifpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpName the ifp name
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @return the range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanName(String ifpName, int start,
		int end) {
		return findByItemFamilyPlanName(ifpName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpName the ifp name
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanName(String ifpName, int start,
		int end, OrderByComparator<IfpModel> orderByComparator) {
		return findByItemFamilyPlanName(ifpName, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpName the ifp name
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanName(String ifpName, int start,
		int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME;
			finderArgs = new Object[] { ifpName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME;
			finderArgs = new Object[] { ifpName, start, end, orderByComparator };
		}

		List<IfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<IfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IfpModel ifpModel : list) {
					if (!Objects.equals(ifpName, ifpModel.getIfpName())) {
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

			query.append(_SQL_SELECT_IFPMODEL_WHERE);

			boolean bindIfpName = false;

			if (ifpName == null) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1);
			}
			else if (ifpName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3);
			}
			else {
				bindIfpName = true;

				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIfpName) {
					qPos.add(ifpName);
				}

				if (!pagination) {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ifp model in the ordered set where ifpName = &#63;.
	 *
	 * @param ifpName the ifp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanName_First(String ifpName,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanName_First(ifpName,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpName=");
		msg.append(ifpName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the first ifp model in the ordered set where ifpName = &#63;.
	 *
	 * @param ifpName the ifp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanName_First(String ifpName,
		OrderByComparator<IfpModel> orderByComparator) {
		List<IfpModel> list = findByItemFamilyPlanName(ifpName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpName = &#63;.
	 *
	 * @param ifpName the ifp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanName_Last(String ifpName,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanName_Last(ifpName,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpName=");
		msg.append(ifpName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpName = &#63;.
	 *
	 * @param ifpName the ifp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanName_Last(String ifpName,
		OrderByComparator<IfpModel> orderByComparator) {
		int count = countByItemFamilyPlanName(ifpName);

		if (count == 0) {
			return null;
		}

		List<IfpModel> list = findByItemFamilyPlanName(ifpName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ifp models before and after the current ifp model in the ordered set where ifpName = &#63;.
	 *
	 * @param ifpModelSid the primary key of the current ifp model
	 * @param ifpName the ifp name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel[] findByItemFamilyPlanName_PrevAndNext(int ifpModelSid,
		String ifpName, OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

		Session session = null;

		try {
			session = openSession();

			IfpModel[] array = new IfpModelImpl[3];

			array[0] = getByItemFamilyPlanName_PrevAndNext(session, ifpModel,
					ifpName, orderByComparator, true);

			array[1] = ifpModel;

			array[2] = getByItemFamilyPlanName_PrevAndNext(session, ifpModel,
					ifpName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IfpModel getByItemFamilyPlanName_PrevAndNext(Session session,
		IfpModel ifpModel, String ifpName,
		OrderByComparator<IfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IFPMODEL_WHERE);

		boolean bindIfpName = false;

		if (ifpName == null) {
			query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1);
		}
		else if (ifpName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3);
		}
		else {
			bindIfpName = true;

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2);
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
			query.append(IfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindIfpName) {
			qPos.add(ifpName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ifp models where ifpName = &#63; from the database.
	 *
	 * @param ifpName the ifp name
	 */
	@Override
	public void removeByItemFamilyPlanName(String ifpName) {
		for (IfpModel ifpModel : findByItemFamilyPlanName(ifpName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ifpModel);
		}
	}

	/**
	 * Returns the number of ifp models where ifpName = &#63;.
	 *
	 * @param ifpName the ifp name
	 * @return the number of matching ifp models
	 */
	@Override
	public int countByItemFamilyPlanName(String ifpName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME;

		Object[] finderArgs = new Object[] { ifpName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IFPMODEL_WHERE);

			boolean bindIfpName = false;

			if (ifpName == null) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1);
			}
			else if (ifpName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3);
			}
			else {
				bindIfpName = true;

				query.append(_FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindIfpName) {
					qPos.add(ifpName);
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

	private static final String _FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_1 = "ifpModel.ifpName IS NULL";
	private static final String _FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_2 = "ifpModel.ifpName = ?";
	private static final String _FINDER_COLUMN_ITEMFAMILYPLANNAME_IFPNAME_3 = "(ifpModel.ifpName IS NULL OR ifpModel.ifpName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemFamilyPlanType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemFamilyPlanType",
			new String[] { Integer.class.getName() },
			IfpModelModelImpl.IFPTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemFamilyPlanType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the ifp models where ifpType = &#63;.
	 *
	 * @param ifpType the ifp type
	 * @return the matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanType(int ifpType) {
		return findByItemFamilyPlanType(ifpType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp models where ifpType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpType the ifp type
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @return the range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanType(int ifpType, int start,
		int end) {
		return findByItemFamilyPlanType(ifpType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpType the ifp type
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanType(int ifpType, int start,
		int end, OrderByComparator<IfpModel> orderByComparator) {
		return findByItemFamilyPlanType(ifpType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpType the ifp type
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanType(int ifpType, int start,
		int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE;
			finderArgs = new Object[] { ifpType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE;
			finderArgs = new Object[] { ifpType, start, end, orderByComparator };
		}

		List<IfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<IfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IfpModel ifpModel : list) {
					if ((ifpType != ifpModel.getIfpType())) {
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

			query.append(_SQL_SELECT_IFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ifpType);

				if (!pagination) {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ifp model in the ordered set where ifpType = &#63;.
	 *
	 * @param ifpType the ifp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanType_First(int ifpType,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanType_First(ifpType,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpType=");
		msg.append(ifpType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the first ifp model in the ordered set where ifpType = &#63;.
	 *
	 * @param ifpType the ifp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanType_First(int ifpType,
		OrderByComparator<IfpModel> orderByComparator) {
		List<IfpModel> list = findByItemFamilyPlanType(ifpType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpType = &#63;.
	 *
	 * @param ifpType the ifp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanType_Last(int ifpType,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanType_Last(ifpType,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpType=");
		msg.append(ifpType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpType = &#63;.
	 *
	 * @param ifpType the ifp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanType_Last(int ifpType,
		OrderByComparator<IfpModel> orderByComparator) {
		int count = countByItemFamilyPlanType(ifpType);

		if (count == 0) {
			return null;
		}

		List<IfpModel> list = findByItemFamilyPlanType(ifpType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ifp models before and after the current ifp model in the ordered set where ifpType = &#63;.
	 *
	 * @param ifpModelSid the primary key of the current ifp model
	 * @param ifpType the ifp type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel[] findByItemFamilyPlanType_PrevAndNext(int ifpModelSid,
		int ifpType, OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

		Session session = null;

		try {
			session = openSession();

			IfpModel[] array = new IfpModelImpl[3];

			array[0] = getByItemFamilyPlanType_PrevAndNext(session, ifpModel,
					ifpType, orderByComparator, true);

			array[1] = ifpModel;

			array[2] = getByItemFamilyPlanType_PrevAndNext(session, ifpModel,
					ifpType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IfpModel getByItemFamilyPlanType_PrevAndNext(Session session,
		IfpModel ifpModel, int ifpType,
		OrderByComparator<IfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IFPMODEL_WHERE);

		query.append(_FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2);

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
			query.append(IfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ifpType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ifp models where ifpType = &#63; from the database.
	 *
	 * @param ifpType the ifp type
	 */
	@Override
	public void removeByItemFamilyPlanType(int ifpType) {
		for (IfpModel ifpModel : findByItemFamilyPlanType(ifpType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ifpModel);
		}
	}

	/**
	 * Returns the number of ifp models where ifpType = &#63;.
	 *
	 * @param ifpType the ifp type
	 * @return the number of matching ifp models
	 */
	@Override
	public int countByItemFamilyPlanType(int ifpType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE;

		Object[] finderArgs = new Object[] { ifpType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ifpType);

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

	private static final String _FINDER_COLUMN_ITEMFAMILYPLANTYPE_IFPTYPE_2 = "ifpModel.ifpType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByItemFamilyPlanStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS =
		new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, IfpModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemFamilyPlanStatus",
			new String[] { Integer.class.getName() },
			IfpModelModelImpl.IFPSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS = new FinderPath(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemFamilyPlanStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the ifp models where ifpStatus = &#63;.
	 *
	 * @param ifpStatus the ifp status
	 * @return the matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus) {
		return findByItemFamilyPlanStatus(ifpStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp models where ifpStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpStatus the ifp status
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @return the range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus, int start,
		int end) {
		return findByItemFamilyPlanStatus(ifpStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpStatus the ifp status
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus, int start,
		int end, OrderByComparator<IfpModel> orderByComparator) {
		return findByItemFamilyPlanStatus(ifpStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp models where ifpStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param ifpStatus the ifp status
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ifp models
	 */
	@Override
	public List<IfpModel> findByItemFamilyPlanStatus(int ifpStatus, int start,
		int end, OrderByComparator<IfpModel> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS;
			finderArgs = new Object[] { ifpStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS;
			finderArgs = new Object[] { ifpStatus, start, end, orderByComparator };
		}

		List<IfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<IfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (IfpModel ifpModel : list) {
					if ((ifpStatus != ifpModel.getIfpStatus())) {
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

			query.append(_SQL_SELECT_IFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(IfpModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ifpStatus);

				if (!pagination) {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ifp model in the ordered set where ifpStatus = &#63;.
	 *
	 * @param ifpStatus the ifp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanStatus_First(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanStatus_First(ifpStatus,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpStatus=");
		msg.append(ifpStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the first ifp model in the ordered set where ifpStatus = &#63;.
	 *
	 * @param ifpStatus the ifp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanStatus_First(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator) {
		List<IfpModel> list = findByItemFamilyPlanStatus(ifpStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpStatus = &#63;.
	 *
	 * @param ifpStatus the ifp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model
	 * @throws NoSuchIfpModelException if a matching ifp model could not be found
	 */
	@Override
	public IfpModel findByItemFamilyPlanStatus_Last(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByItemFamilyPlanStatus_Last(ifpStatus,
				orderByComparator);

		if (ifpModel != null) {
			return ifpModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ifpStatus=");
		msg.append(ifpStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIfpModelException(msg.toString());
	}

	/**
	 * Returns the last ifp model in the ordered set where ifpStatus = &#63;.
	 *
	 * @param ifpStatus the ifp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ifp model, or <code>null</code> if a matching ifp model could not be found
	 */
	@Override
	public IfpModel fetchByItemFamilyPlanStatus_Last(int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator) {
		int count = countByItemFamilyPlanStatus(ifpStatus);

		if (count == 0) {
			return null;
		}

		List<IfpModel> list = findByItemFamilyPlanStatus(ifpStatus, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ifp models before and after the current ifp model in the ordered set where ifpStatus = &#63;.
	 *
	 * @param ifpModelSid the primary key of the current ifp model
	 * @param ifpStatus the ifp status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel[] findByItemFamilyPlanStatus_PrevAndNext(int ifpModelSid,
		int ifpStatus, OrderByComparator<IfpModel> orderByComparator)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = findByPrimaryKey(ifpModelSid);

		Session session = null;

		try {
			session = openSession();

			IfpModel[] array = new IfpModelImpl[3];

			array[0] = getByItemFamilyPlanStatus_PrevAndNext(session, ifpModel,
					ifpStatus, orderByComparator, true);

			array[1] = ifpModel;

			array[2] = getByItemFamilyPlanStatus_PrevAndNext(session, ifpModel,
					ifpStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected IfpModel getByItemFamilyPlanStatus_PrevAndNext(Session session,
		IfpModel ifpModel, int ifpStatus,
		OrderByComparator<IfpModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IFPMODEL_WHERE);

		query.append(_FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2);

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
			query.append(IfpModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(ifpStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(ifpModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<IfpModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ifp models where ifpStatus = &#63; from the database.
	 *
	 * @param ifpStatus the ifp status
	 */
	@Override
	public void removeByItemFamilyPlanStatus(int ifpStatus) {
		for (IfpModel ifpModel : findByItemFamilyPlanStatus(ifpStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(ifpModel);
		}
	}

	/**
	 * Returns the number of ifp models where ifpStatus = &#63;.
	 *
	 * @param ifpStatus the ifp status
	 * @return the number of matching ifp models
	 */
	@Override
	public int countByItemFamilyPlanStatus(int ifpStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS;

		Object[] finderArgs = new Object[] { ifpStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IFPMODEL_WHERE);

			query.append(_FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(ifpStatus);

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

	private static final String _FINDER_COLUMN_ITEMFAMILYPLANSTATUS_IFPSTATUS_2 = "ifpModel.ifpStatus = ?";

	public IfpModelPersistenceImpl() {
		setModelClass(IfpModel.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("totalDollarCommitment", "TOTAL_DOLLAR_COMMITMENT");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("ifpStatus", "IFP_STATUS");
			dbColumnNames.put("totalVolumeCommitment", "TOTAL_VOLUME_COMMITMENT");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");
			dbColumnNames.put("ifpId", "IFP_ID");
			dbColumnNames.put("totalMarketshareCommitment",
				"TOTAL_MARKETSHARE_COMMITMENT");
			dbColumnNames.put("ifpCategory", "IFP_CATEGORY");
			dbColumnNames.put("parentIfpName", "PARENT_IFP_NAME");
			dbColumnNames.put("ifpEndDate", "IFP_END_DATE");
			dbColumnNames.put("ifpDesignation", "IFP_DESIGNATION");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("ifpStartDate", "IFP_START_DATE");
			dbColumnNames.put("parentIfpId", "PARENT_IFP_ID");
			dbColumnNames.put("commitmentPeriod", "COMMITMENT_PERIOD");
			dbColumnNames.put("ifpType", "IFP_TYPE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("ifpName", "IFP_NAME");
			dbColumnNames.put("ifpNo", "IFP_NO");
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
	 * Caches the ifp model in the entity cache if it is enabled.
	 *
	 * @param ifpModel the ifp model
	 */
	@Override
	public void cacheResult(IfpModel ifpModel) {
		entityCache.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelImpl.class, ifpModel.getPrimaryKey(), ifpModel);

		ifpModel.resetOriginalValues();
	}

	/**
	 * Caches the ifp models in the entity cache if it is enabled.
	 *
	 * @param ifpModels the ifp models
	 */
	@Override
	public void cacheResult(List<IfpModel> ifpModels) {
		for (IfpModel ifpModel : ifpModels) {
			if (entityCache.getResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
						IfpModelImpl.class, ifpModel.getPrimaryKey()) == null) {
				cacheResult(ifpModel);
			}
			else {
				ifpModel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ifp models.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IfpModelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ifp model.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IfpModel ifpModel) {
		entityCache.removeResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelImpl.class, ifpModel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IfpModel> ifpModels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IfpModel ifpModel : ifpModels) {
			entityCache.removeResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
				IfpModelImpl.class, ifpModel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ifp model with the primary key. Does not add the ifp model to the database.
	 *
	 * @param ifpModelSid the primary key for the new ifp model
	 * @return the new ifp model
	 */
	@Override
	public IfpModel create(int ifpModelSid) {
		IfpModel ifpModel = new IfpModelImpl();

		ifpModel.setNew(true);
		ifpModel.setPrimaryKey(ifpModelSid);

		return ifpModel;
	}

	/**
	 * Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ifpModelSid the primary key of the ifp model
	 * @return the ifp model that was removed
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel remove(int ifpModelSid) throws NoSuchIfpModelException {
		return remove((Serializable)ifpModelSid);
	}

	/**
	 * Removes the ifp model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ifp model
	 * @return the ifp model that was removed
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel remove(Serializable primaryKey)
		throws NoSuchIfpModelException {
		Session session = null;

		try {
			session = openSession();

			IfpModel ifpModel = (IfpModel)session.get(IfpModelImpl.class,
					primaryKey);

			if (ifpModel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ifpModel);
		}
		catch (NoSuchIfpModelException nsee) {
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
	protected IfpModel removeImpl(IfpModel ifpModel) {
		ifpModel = toUnwrappedModel(ifpModel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ifpModel)) {
				ifpModel = (IfpModel)session.get(IfpModelImpl.class,
						ifpModel.getPrimaryKeyObj());
			}

			if (ifpModel != null) {
				session.delete(ifpModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ifpModel != null) {
			clearCache(ifpModel);
		}

		return ifpModel;
	}

	@Override
	public IfpModel updateImpl(IfpModel ifpModel) {
		ifpModel = toUnwrappedModel(ifpModel);

		boolean isNew = ifpModel.isNew();

		IfpModelModelImpl ifpModelModelImpl = (IfpModelModelImpl)ifpModel;

		Session session = null;

		try {
			session = openSession();

			if (ifpModel.isNew()) {
				session.save(ifpModel);

				ifpModel.setNew(false);
			}
			else {
				ifpModel = (IfpModel)session.merge(ifpModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!IfpModelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { ifpModelModelImpl.getIfpId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID,
				args);

			args = new Object[] { ifpModelModelImpl.getIfpNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO,
				args);

			args = new Object[] { ifpModelModelImpl.getIfpName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME,
				args);

			args = new Object[] { ifpModelModelImpl.getIfpType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE,
				args);

			args = new Object[] { ifpModelModelImpl.getIfpStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((ifpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ifpModelModelImpl.getOriginalIfpId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID,
					args);

				args = new Object[] { ifpModelModelImpl.getIfpId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANID,
					args);
			}

			if ((ifpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ifpModelModelImpl.getOriginalIfpNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO,
					args);

				args = new Object[] { ifpModelModelImpl.getIfpNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNO,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNO,
					args);
			}

			if ((ifpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ifpModelModelImpl.getOriginalIfpName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME,
					args);

				args = new Object[] { ifpModelModelImpl.getIfpName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANNAME,
					args);
			}

			if ((ifpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ifpModelModelImpl.getOriginalIfpType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE,
					args);

				args = new Object[] { ifpModelModelImpl.getIfpType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANTYPE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANTYPE,
					args);
			}

			if ((ifpModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						ifpModelModelImpl.getOriginalIfpStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS,
					args);

				args = new Object[] { ifpModelModelImpl.getIfpStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMFAMILYPLANSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMFAMILYPLANSTATUS,
					args);
			}
		}

		entityCache.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
			IfpModelImpl.class, ifpModel.getPrimaryKey(), ifpModel, false);

		ifpModel.resetOriginalValues();

		return ifpModel;
	}

	protected IfpModel toUnwrappedModel(IfpModel ifpModel) {
		if (ifpModel instanceof IfpModelImpl) {
			return ifpModel;
		}

		IfpModelImpl ifpModelImpl = new IfpModelImpl();

		ifpModelImpl.setNew(ifpModel.isNew());
		ifpModelImpl.setPrimaryKey(ifpModel.getPrimaryKey());

		ifpModelImpl.setModifiedBy(ifpModel.getModifiedBy());
		ifpModelImpl.setTotalDollarCommitment(ifpModel.getTotalDollarCommitment());
		ifpModelImpl.setCreatedDate(ifpModel.getCreatedDate());
		ifpModelImpl.setIfpStatus(ifpModel.getIfpStatus());
		ifpModelImpl.setTotalVolumeCommitment(ifpModel.getTotalVolumeCommitment());
		ifpModelImpl.setBatchId(ifpModel.getBatchId());
		ifpModelImpl.setInternalNotes(ifpModel.getInternalNotes());
		ifpModelImpl.setIfpId(ifpModel.getIfpId());
		ifpModelImpl.setTotalMarketshareCommitment(ifpModel.getTotalMarketshareCommitment());
		ifpModelImpl.setIfpCategory(ifpModel.getIfpCategory());
		ifpModelImpl.setParentIfpName(ifpModel.getParentIfpName());
		ifpModelImpl.setIfpEndDate(ifpModel.getIfpEndDate());
		ifpModelImpl.setIfpDesignation(ifpModel.getIfpDesignation());
		ifpModelImpl.setCreatedBy(ifpModel.getCreatedBy());
		ifpModelImpl.setIfpStartDate(ifpModel.getIfpStartDate());
		ifpModelImpl.setParentIfpId(ifpModel.getParentIfpId());
		ifpModelImpl.setCommitmentPeriod(ifpModel.getCommitmentPeriod());
		ifpModelImpl.setIfpType(ifpModel.getIfpType());
		ifpModelImpl.setModifiedDate(ifpModel.getModifiedDate());
		ifpModelImpl.setIfpModelSid(ifpModel.getIfpModelSid());
		ifpModelImpl.setRecordLockStatus(ifpModel.isRecordLockStatus());
		ifpModelImpl.setSource(ifpModel.getSource());
		ifpModelImpl.setIfpName(ifpModel.getIfpName());
		ifpModelImpl.setIfpNo(ifpModel.getIfpNo());
		ifpModelImpl.setInboundStatus(ifpModel.getInboundStatus());

		return ifpModelImpl;
	}

	/**
	 * Returns the ifp model with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ifp model
	 * @return the ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIfpModelException {
		IfpModel ifpModel = fetchByPrimaryKey(primaryKey);

		if (ifpModel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIfpModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ifpModel;
	}

	/**
	 * Returns the ifp model with the primary key or throws a {@link NoSuchIfpModelException} if it could not be found.
	 *
	 * @param ifpModelSid the primary key of the ifp model
	 * @return the ifp model
	 * @throws NoSuchIfpModelException if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel findByPrimaryKey(int ifpModelSid)
		throws NoSuchIfpModelException {
		return findByPrimaryKey((Serializable)ifpModelSid);
	}

	/**
	 * Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ifp model
	 * @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
				IfpModelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IfpModel ifpModel = (IfpModel)serializable;

		if (ifpModel == null) {
			Session session = null;

			try {
				session = openSession();

				ifpModel = (IfpModel)session.get(IfpModelImpl.class, primaryKey);

				if (ifpModel != null) {
					cacheResult(ifpModel);
				}
				else {
					entityCache.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
						IfpModelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
					IfpModelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ifpModel;
	}

	/**
	 * Returns the ifp model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ifpModelSid the primary key of the ifp model
	 * @return the ifp model, or <code>null</code> if a ifp model with the primary key could not be found
	 */
	@Override
	public IfpModel fetchByPrimaryKey(int ifpModelSid) {
		return fetchByPrimaryKey((Serializable)ifpModelSid);
	}

	@Override
	public Map<Serializable, IfpModel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IfpModel> map = new HashMap<Serializable, IfpModel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IfpModel ifpModel = fetchByPrimaryKey(primaryKey);

			if (ifpModel != null) {
				map.put(primaryKey, ifpModel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
					IfpModelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IfpModel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IFPMODEL_WHERE_PKS_IN);

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

			for (IfpModel ifpModel : (List<IfpModel>)q.list()) {
				map.put(ifpModel.getPrimaryKeyObj(), ifpModel);

				cacheResult(ifpModel);

				uncachedPrimaryKeys.remove(ifpModel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IfpModelModelImpl.ENTITY_CACHE_ENABLED,
					IfpModelImpl.class, primaryKey, nullModel);
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
	 * Returns all the ifp models.
	 *
	 * @return the ifp models
	 */
	@Override
	public List<IfpModel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @return the range of ifp models
	 */
	@Override
	public List<IfpModel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ifp models
	 */
	@Override
	public List<IfpModel> findAll(int start, int end,
		OrderByComparator<IfpModel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp models
	 * @param end the upper bound of the range of ifp models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ifp models
	 */
	@Override
	public List<IfpModel> findAll(int start, int end,
		OrderByComparator<IfpModel> orderByComparator, boolean retrieveFromCache) {
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

		List<IfpModel> list = null;

		if (retrieveFromCache) {
			list = (List<IfpModel>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IFPMODEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IFPMODEL;

				if (pagination) {
					sql = sql.concat(IfpModelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpModel>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ifp models from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IfpModel ifpModel : findAll()) {
			remove(ifpModel);
		}
	}

	/**
	 * Returns the number of ifp models.
	 *
	 * @return the number of ifp models
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IFPMODEL);

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
		return IfpModelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ifp model persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IfpModelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IFPMODEL = "SELECT ifpModel FROM IfpModel ifpModel";
	private static final String _SQL_SELECT_IFPMODEL_WHERE_PKS_IN = "SELECT ifpModel FROM IfpModel ifpModel WHERE IFP_MODEL_SID IN (";
	private static final String _SQL_SELECT_IFPMODEL_WHERE = "SELECT ifpModel FROM IfpModel ifpModel WHERE ";
	private static final String _SQL_COUNT_IFPMODEL = "SELECT COUNT(ifpModel) FROM IfpModel ifpModel";
	private static final String _SQL_COUNT_IFPMODEL_WHERE = "SELECT COUNT(ifpModel) FROM IfpModel ifpModel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ifpModel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpModel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IfpModel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(IfpModelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"modifiedBy", "totalDollarCommitment", "createdDate",
				"ifpStatus", "totalVolumeCommitment", "batchId", "internalNotes",
				"ifpId", "totalMarketshareCommitment", "ifpCategory",
				"parentIfpName", "ifpEndDate", "ifpDesignation", "createdBy",
				"ifpStartDate", "parentIfpId", "commitmentPeriod", "ifpType",
				"modifiedDate", "ifpModelSid", "recordLockStatus", "source",
				"ifpName", "ifpNo", "inboundStatus"
			});
}