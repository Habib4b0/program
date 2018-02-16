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

import com.stpl.app.exception.NoSuchPsModelException;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.impl.PsModelImpl;
import com.stpl.app.model.impl.PsModelModelImpl;
import com.stpl.app.service.persistence.PsModelPersistence;

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
 * The persistence implementation for the ps model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsModelPersistence
 * @see com.stpl.app.service.persistence.PsModelUtil
 * @generated
 */
@ProviderType
public class PsModelPersistenceImpl extends BasePersistenceImpl<PsModel>
	implements PsModelPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PsModelUtil} to access the ps model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PsModelImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSID = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBypsId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSID = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBypsId",
			new String[] { String.class.getName() },
			PsModelModelImpl.PSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSID = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBypsId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the ps models where psId = &#63;.
	 *
	 * @param psId the ps ID
	 * @return the matching ps models
	 */
	@Override
	public List<PsModel> findBypsId(String psId) {
		return findBypsId(psId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps models where psId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psId the ps ID
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @return the range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsId(String psId, int start, int end) {
		return findBypsId(psId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps models where psId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psId the ps ID
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsId(String psId, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return findBypsId(psId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps models where psId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psId the ps ID
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsId(String psId, int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSID;
			finderArgs = new Object[] { psId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSID;
			finderArgs = new Object[] { psId, start, end, orderByComparator };
		}

		List<PsModel> list = null;

		if (retrieveFromCache) {
			list = (List<PsModel>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (PsModel psModel : list) {
					if (!Objects.equals(psId, psModel.getPsId())) {
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

			query.append(_SQL_SELECT_PSMODEL_WHERE);

			boolean bindPsId = false;

			if (psId == null) {
				query.append(_FINDER_COLUMN_PSID_PSID_1);
			}
			else if (psId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PSID_PSID_3);
			}
			else {
				bindPsId = true;

				query.append(_FINDER_COLUMN_PSID_PSID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PsModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPsId) {
					qPos.add(psId);
				}

				if (!pagination) {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ps model in the ordered set where psId = &#63;.
	 *
	 * @param psId the ps ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsId_First(String psId,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsId_First(psId, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psId=");
		msg.append(psId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the first ps model in the ordered set where psId = &#63;.
	 *
	 * @param psId the ps ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsId_First(String psId,
		OrderByComparator<PsModel> orderByComparator) {
		List<PsModel> list = findBypsId(psId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ps model in the ordered set where psId = &#63;.
	 *
	 * @param psId the ps ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsId_Last(String psId,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsId_Last(psId, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psId=");
		msg.append(psId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the last ps model in the ordered set where psId = &#63;.
	 *
	 * @param psId the ps ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsId_Last(String psId,
		OrderByComparator<PsModel> orderByComparator) {
		int count = countBypsId(psId);

		if (count == 0) {
			return null;
		}

		List<PsModel> list = findBypsId(psId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ps models before and after the current ps model in the ordered set where psId = &#63;.
	 *
	 * @param psModelSid the primary key of the current ps model
	 * @param psId the ps ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel[] findBypsId_PrevAndNext(int psModelSid, String psId,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = findByPrimaryKey(psModelSid);

		Session session = null;

		try {
			session = openSession();

			PsModel[] array = new PsModelImpl[3];

			array[0] = getBypsId_PrevAndNext(session, psModel, psId,
					orderByComparator, true);

			array[1] = psModel;

			array[2] = getBypsId_PrevAndNext(session, psModel, psId,
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

	protected PsModel getBypsId_PrevAndNext(Session session, PsModel psModel,
		String psId, OrderByComparator<PsModel> orderByComparator,
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

		query.append(_SQL_SELECT_PSMODEL_WHERE);

		boolean bindPsId = false;

		if (psId == null) {
			query.append(_FINDER_COLUMN_PSID_PSID_1);
		}
		else if (psId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PSID_PSID_3);
		}
		else {
			bindPsId = true;

			query.append(_FINDER_COLUMN_PSID_PSID_2);
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
			query.append(PsModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPsId) {
			qPos.add(psId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(psModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PsModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ps models where psId = &#63; from the database.
	 *
	 * @param psId the ps ID
	 */
	@Override
	public void removeBypsId(String psId) {
		for (PsModel psModel : findBypsId(psId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(psModel);
		}
	}

	/**
	 * Returns the number of ps models where psId = &#63;.
	 *
	 * @param psId the ps ID
	 * @return the number of matching ps models
	 */
	@Override
	public int countBypsId(String psId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSID;

		Object[] finderArgs = new Object[] { psId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PSMODEL_WHERE);

			boolean bindPsId = false;

			if (psId == null) {
				query.append(_FINDER_COLUMN_PSID_PSID_1);
			}
			else if (psId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PSID_PSID_3);
			}
			else {
				bindPsId = true;

				query.append(_FINDER_COLUMN_PSID_PSID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPsId) {
					qPos.add(psId);
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

	private static final String _FINDER_COLUMN_PSID_PSID_1 = "psModel.psId IS NULL";
	private static final String _FINDER_COLUMN_PSID_PSID_2 = "psModel.psId = ?";
	private static final String _FINDER_COLUMN_PSID_PSID_3 = "(psModel.psId IS NULL OR psModel.psId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSNO = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBypsNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNO = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBypsNo",
			new String[] { String.class.getName() },
			PsModelModelImpl.PSNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSNO = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBypsNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the ps models where psNo = &#63;.
	 *
	 * @param psNo the ps no
	 * @return the matching ps models
	 */
	@Override
	public List<PsModel> findBypsNo(String psNo) {
		return findBypsNo(psNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps models where psNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psNo the ps no
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @return the range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsNo(String psNo, int start, int end) {
		return findBypsNo(psNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps models where psNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psNo the ps no
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsNo(String psNo, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return findBypsNo(psNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps models where psNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psNo the ps no
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsNo(String psNo, int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNO;
			finderArgs = new Object[] { psNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSNO;
			finderArgs = new Object[] { psNo, start, end, orderByComparator };
		}

		List<PsModel> list = null;

		if (retrieveFromCache) {
			list = (List<PsModel>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (PsModel psModel : list) {
					if (!Objects.equals(psNo, psModel.getPsNo())) {
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

			query.append(_SQL_SELECT_PSMODEL_WHERE);

			boolean bindPsNo = false;

			if (psNo == null) {
				query.append(_FINDER_COLUMN_PSNO_PSNO_1);
			}
			else if (psNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PSNO_PSNO_3);
			}
			else {
				bindPsNo = true;

				query.append(_FINDER_COLUMN_PSNO_PSNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PsModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPsNo) {
					qPos.add(psNo);
				}

				if (!pagination) {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ps model in the ordered set where psNo = &#63;.
	 *
	 * @param psNo the ps no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsNo_First(String psNo,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsNo_First(psNo, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psNo=");
		msg.append(psNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the first ps model in the ordered set where psNo = &#63;.
	 *
	 * @param psNo the ps no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsNo_First(String psNo,
		OrderByComparator<PsModel> orderByComparator) {
		List<PsModel> list = findBypsNo(psNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ps model in the ordered set where psNo = &#63;.
	 *
	 * @param psNo the ps no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsNo_Last(String psNo,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsNo_Last(psNo, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psNo=");
		msg.append(psNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the last ps model in the ordered set where psNo = &#63;.
	 *
	 * @param psNo the ps no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsNo_Last(String psNo,
		OrderByComparator<PsModel> orderByComparator) {
		int count = countBypsNo(psNo);

		if (count == 0) {
			return null;
		}

		List<PsModel> list = findBypsNo(psNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ps models before and after the current ps model in the ordered set where psNo = &#63;.
	 *
	 * @param psModelSid the primary key of the current ps model
	 * @param psNo the ps no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel[] findBypsNo_PrevAndNext(int psModelSid, String psNo,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = findByPrimaryKey(psModelSid);

		Session session = null;

		try {
			session = openSession();

			PsModel[] array = new PsModelImpl[3];

			array[0] = getBypsNo_PrevAndNext(session, psModel, psNo,
					orderByComparator, true);

			array[1] = psModel;

			array[2] = getBypsNo_PrevAndNext(session, psModel, psNo,
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

	protected PsModel getBypsNo_PrevAndNext(Session session, PsModel psModel,
		String psNo, OrderByComparator<PsModel> orderByComparator,
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

		query.append(_SQL_SELECT_PSMODEL_WHERE);

		boolean bindPsNo = false;

		if (psNo == null) {
			query.append(_FINDER_COLUMN_PSNO_PSNO_1);
		}
		else if (psNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PSNO_PSNO_3);
		}
		else {
			bindPsNo = true;

			query.append(_FINDER_COLUMN_PSNO_PSNO_2);
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
			query.append(PsModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPsNo) {
			qPos.add(psNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(psModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PsModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ps models where psNo = &#63; from the database.
	 *
	 * @param psNo the ps no
	 */
	@Override
	public void removeBypsNo(String psNo) {
		for (PsModel psModel : findBypsNo(psNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(psModel);
		}
	}

	/**
	 * Returns the number of ps models where psNo = &#63;.
	 *
	 * @param psNo the ps no
	 * @return the number of matching ps models
	 */
	@Override
	public int countBypsNo(String psNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSNO;

		Object[] finderArgs = new Object[] { psNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PSMODEL_WHERE);

			boolean bindPsNo = false;

			if (psNo == null) {
				query.append(_FINDER_COLUMN_PSNO_PSNO_1);
			}
			else if (psNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PSNO_PSNO_3);
			}
			else {
				bindPsNo = true;

				query.append(_FINDER_COLUMN_PSNO_PSNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPsNo) {
					qPos.add(psNo);
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

	private static final String _FINDER_COLUMN_PSNO_PSNO_1 = "psModel.psNo IS NULL";
	private static final String _FINDER_COLUMN_PSNO_PSNO_2 = "psModel.psNo = ?";
	private static final String _FINDER_COLUMN_PSNO_PSNO_3 = "(psModel.psNo IS NULL OR psModel.psNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSNAME = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBypsName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNAME =
		new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBypsName",
			new String[] { String.class.getName() },
			PsModelModelImpl.PSNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSNAME = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBypsName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the ps models where psName = &#63;.
	 *
	 * @param psName the ps name
	 * @return the matching ps models
	 */
	@Override
	public List<PsModel> findBypsName(String psName) {
		return findBypsName(psName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps models where psName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psName the ps name
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @return the range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsName(String psName, int start, int end) {
		return findBypsName(psName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps models where psName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psName the ps name
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsName(String psName, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return findBypsName(psName, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps models where psName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psName the ps name
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsName(String psName, int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNAME;
			finderArgs = new Object[] { psName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSNAME;
			finderArgs = new Object[] { psName, start, end, orderByComparator };
		}

		List<PsModel> list = null;

		if (retrieveFromCache) {
			list = (List<PsModel>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (PsModel psModel : list) {
					if (!Objects.equals(psName, psModel.getPsName())) {
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

			query.append(_SQL_SELECT_PSMODEL_WHERE);

			boolean bindPsName = false;

			if (psName == null) {
				query.append(_FINDER_COLUMN_PSNAME_PSNAME_1);
			}
			else if (psName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PSNAME_PSNAME_3);
			}
			else {
				bindPsName = true;

				query.append(_FINDER_COLUMN_PSNAME_PSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PsModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPsName) {
					qPos.add(psName);
				}

				if (!pagination) {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ps model in the ordered set where psName = &#63;.
	 *
	 * @param psName the ps name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsName_First(String psName,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsName_First(psName, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psName=");
		msg.append(psName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the first ps model in the ordered set where psName = &#63;.
	 *
	 * @param psName the ps name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsName_First(String psName,
		OrderByComparator<PsModel> orderByComparator) {
		List<PsModel> list = findBypsName(psName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ps model in the ordered set where psName = &#63;.
	 *
	 * @param psName the ps name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsName_Last(String psName,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsName_Last(psName, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psName=");
		msg.append(psName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the last ps model in the ordered set where psName = &#63;.
	 *
	 * @param psName the ps name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsName_Last(String psName,
		OrderByComparator<PsModel> orderByComparator) {
		int count = countBypsName(psName);

		if (count == 0) {
			return null;
		}

		List<PsModel> list = findBypsName(psName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ps models before and after the current ps model in the ordered set where psName = &#63;.
	 *
	 * @param psModelSid the primary key of the current ps model
	 * @param psName the ps name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel[] findBypsName_PrevAndNext(int psModelSid, String psName,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = findByPrimaryKey(psModelSid);

		Session session = null;

		try {
			session = openSession();

			PsModel[] array = new PsModelImpl[3];

			array[0] = getBypsName_PrevAndNext(session, psModel, psName,
					orderByComparator, true);

			array[1] = psModel;

			array[2] = getBypsName_PrevAndNext(session, psModel, psName,
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

	protected PsModel getBypsName_PrevAndNext(Session session, PsModel psModel,
		String psName, OrderByComparator<PsModel> orderByComparator,
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

		query.append(_SQL_SELECT_PSMODEL_WHERE);

		boolean bindPsName = false;

		if (psName == null) {
			query.append(_FINDER_COLUMN_PSNAME_PSNAME_1);
		}
		else if (psName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PSNAME_PSNAME_3);
		}
		else {
			bindPsName = true;

			query.append(_FINDER_COLUMN_PSNAME_PSNAME_2);
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
			query.append(PsModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPsName) {
			qPos.add(psName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(psModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PsModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ps models where psName = &#63; from the database.
	 *
	 * @param psName the ps name
	 */
	@Override
	public void removeBypsName(String psName) {
		for (PsModel psModel : findBypsName(psName, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(psModel);
		}
	}

	/**
	 * Returns the number of ps models where psName = &#63;.
	 *
	 * @param psName the ps name
	 * @return the number of matching ps models
	 */
	@Override
	public int countBypsName(String psName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSNAME;

		Object[] finderArgs = new Object[] { psName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PSMODEL_WHERE);

			boolean bindPsName = false;

			if (psName == null) {
				query.append(_FINDER_COLUMN_PSNAME_PSNAME_1);
			}
			else if (psName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PSNAME_PSNAME_3);
			}
			else {
				bindPsName = true;

				query.append(_FINDER_COLUMN_PSNAME_PSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPsName) {
					qPos.add(psName);
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

	private static final String _FINDER_COLUMN_PSNAME_PSNAME_1 = "psModel.psName IS NULL";
	private static final String _FINDER_COLUMN_PSNAME_PSNAME_2 = "psModel.psName = ?";
	private static final String _FINDER_COLUMN_PSNAME_PSNAME_3 = "(psModel.psName IS NULL OR psModel.psName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSTYPE = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBypsType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSTYPE =
		new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBypsType",
			new String[] { Integer.class.getName() },
			PsModelModelImpl.PSTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSTYPE = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBypsType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the ps models where psType = &#63;.
	 *
	 * @param psType the ps type
	 * @return the matching ps models
	 */
	@Override
	public List<PsModel> findBypsType(int psType) {
		return findBypsType(psType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps models where psType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psType the ps type
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @return the range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsType(int psType, int start, int end) {
		return findBypsType(psType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps models where psType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psType the ps type
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsType(int psType, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return findBypsType(psType, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps models where psType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psType the ps type
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsType(int psType, int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSTYPE;
			finderArgs = new Object[] { psType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSTYPE;
			finderArgs = new Object[] { psType, start, end, orderByComparator };
		}

		List<PsModel> list = null;

		if (retrieveFromCache) {
			list = (List<PsModel>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (PsModel psModel : list) {
					if ((psType != psModel.getPsType())) {
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

			query.append(_SQL_SELECT_PSMODEL_WHERE);

			query.append(_FINDER_COLUMN_PSTYPE_PSTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PsModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(psType);

				if (!pagination) {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ps model in the ordered set where psType = &#63;.
	 *
	 * @param psType the ps type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsType_First(int psType,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsType_First(psType, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psType=");
		msg.append(psType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the first ps model in the ordered set where psType = &#63;.
	 *
	 * @param psType the ps type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsType_First(int psType,
		OrderByComparator<PsModel> orderByComparator) {
		List<PsModel> list = findBypsType(psType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ps model in the ordered set where psType = &#63;.
	 *
	 * @param psType the ps type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsType_Last(int psType,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsType_Last(psType, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psType=");
		msg.append(psType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the last ps model in the ordered set where psType = &#63;.
	 *
	 * @param psType the ps type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsType_Last(int psType,
		OrderByComparator<PsModel> orderByComparator) {
		int count = countBypsType(psType);

		if (count == 0) {
			return null;
		}

		List<PsModel> list = findBypsType(psType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ps models before and after the current ps model in the ordered set where psType = &#63;.
	 *
	 * @param psModelSid the primary key of the current ps model
	 * @param psType the ps type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel[] findBypsType_PrevAndNext(int psModelSid, int psType,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = findByPrimaryKey(psModelSid);

		Session session = null;

		try {
			session = openSession();

			PsModel[] array = new PsModelImpl[3];

			array[0] = getBypsType_PrevAndNext(session, psModel, psType,
					orderByComparator, true);

			array[1] = psModel;

			array[2] = getBypsType_PrevAndNext(session, psModel, psType,
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

	protected PsModel getBypsType_PrevAndNext(Session session, PsModel psModel,
		int psType, OrderByComparator<PsModel> orderByComparator,
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

		query.append(_SQL_SELECT_PSMODEL_WHERE);

		query.append(_FINDER_COLUMN_PSTYPE_PSTYPE_2);

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
			query.append(PsModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(psType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(psModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PsModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ps models where psType = &#63; from the database.
	 *
	 * @param psType the ps type
	 */
	@Override
	public void removeBypsType(int psType) {
		for (PsModel psModel : findBypsType(psType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(psModel);
		}
	}

	/**
	 * Returns the number of ps models where psType = &#63;.
	 *
	 * @param psType the ps type
	 * @return the number of matching ps models
	 */
	@Override
	public int countBypsType(int psType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSTYPE;

		Object[] finderArgs = new Object[] { psType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PSMODEL_WHERE);

			query.append(_FINDER_COLUMN_PSTYPE_PSTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(psType);

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

	private static final String _FINDER_COLUMN_PSTYPE_PSTYPE_2 = "psModel.psType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PSSTATUS = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBypsStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSSTATUS =
		new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, PsModelImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBypsStatus",
			new String[] { Integer.class.getName() },
			PsModelModelImpl.PSSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PSSTATUS = new FinderPath(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBypsStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the ps models where psStatus = &#63;.
	 *
	 * @param psStatus the ps status
	 * @return the matching ps models
	 */
	@Override
	public List<PsModel> findBypsStatus(int psStatus) {
		return findBypsStatus(psStatus, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the ps models where psStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psStatus the ps status
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @return the range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsStatus(int psStatus, int start, int end) {
		return findBypsStatus(psStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps models where psStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psStatus the ps status
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsStatus(int psStatus, int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return findBypsStatus(psStatus, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps models where psStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psStatus the ps status
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ps models
	 */
	@Override
	public List<PsModel> findBypsStatus(int psStatus, int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSSTATUS;
			finderArgs = new Object[] { psStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PSSTATUS;
			finderArgs = new Object[] { psStatus, start, end, orderByComparator };
		}

		List<PsModel> list = null;

		if (retrieveFromCache) {
			list = (List<PsModel>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (PsModel psModel : list) {
					if ((psStatus != psModel.getPsStatus())) {
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

			query.append(_SQL_SELECT_PSMODEL_WHERE);

			query.append(_FINDER_COLUMN_PSSTATUS_PSSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PsModelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(psStatus);

				if (!pagination) {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ps model in the ordered set where psStatus = &#63;.
	 *
	 * @param psStatus the ps status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsStatus_First(int psStatus,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsStatus_First(psStatus, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psStatus=");
		msg.append(psStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the first ps model in the ordered set where psStatus = &#63;.
	 *
	 * @param psStatus the ps status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsStatus_First(int psStatus,
		OrderByComparator<PsModel> orderByComparator) {
		List<PsModel> list = findBypsStatus(psStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ps model in the ordered set where psStatus = &#63;.
	 *
	 * @param psStatus the ps status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model
	 * @throws NoSuchPsModelException if a matching ps model could not be found
	 */
	@Override
	public PsModel findBypsStatus_Last(int psStatus,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = fetchBypsStatus_Last(psStatus, orderByComparator);

		if (psModel != null) {
			return psModel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psStatus=");
		msg.append(psStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsModelException(msg.toString());
	}

	/**
	 * Returns the last ps model in the ordered set where psStatus = &#63;.
	 *
	 * @param psStatus the ps status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps model, or <code>null</code> if a matching ps model could not be found
	 */
	@Override
	public PsModel fetchBypsStatus_Last(int psStatus,
		OrderByComparator<PsModel> orderByComparator) {
		int count = countBypsStatus(psStatus);

		if (count == 0) {
			return null;
		}

		List<PsModel> list = findBypsStatus(psStatus, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ps models before and after the current ps model in the ordered set where psStatus = &#63;.
	 *
	 * @param psModelSid the primary key of the current ps model
	 * @param psStatus the ps status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel[] findBypsStatus_PrevAndNext(int psModelSid, int psStatus,
		OrderByComparator<PsModel> orderByComparator)
		throws NoSuchPsModelException {
		PsModel psModel = findByPrimaryKey(psModelSid);

		Session session = null;

		try {
			session = openSession();

			PsModel[] array = new PsModelImpl[3];

			array[0] = getBypsStatus_PrevAndNext(session, psModel, psStatus,
					orderByComparator, true);

			array[1] = psModel;

			array[2] = getBypsStatus_PrevAndNext(session, psModel, psStatus,
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

	protected PsModel getBypsStatus_PrevAndNext(Session session,
		PsModel psModel, int psStatus,
		OrderByComparator<PsModel> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PSMODEL_WHERE);

		query.append(_FINDER_COLUMN_PSSTATUS_PSSTATUS_2);

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
			query.append(PsModelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(psStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(psModel);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PsModel> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ps models where psStatus = &#63; from the database.
	 *
	 * @param psStatus the ps status
	 */
	@Override
	public void removeBypsStatus(int psStatus) {
		for (PsModel psModel : findBypsStatus(psStatus, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(psModel);
		}
	}

	/**
	 * Returns the number of ps models where psStatus = &#63;.
	 *
	 * @param psStatus the ps status
	 * @return the number of matching ps models
	 */
	@Override
	public int countBypsStatus(int psStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PSSTATUS;

		Object[] finderArgs = new Object[] { psStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PSMODEL_WHERE);

			query.append(_FINDER_COLUMN_PSSTATUS_PSSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(psStatus);

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

	private static final String _FINDER_COLUMN_PSSTATUS_PSSTATUS_2 = "psModel.psStatus = ?";

	public PsModelPersistenceImpl() {
		setModelClass(PsModel.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("psId", "PS_ID");
			dbColumnNames.put("psName", "PS_NAME");
			dbColumnNames.put("psType", "PS_TYPE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("psCategory", "PS_CATEGORY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("psStatus", "PS_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("psNo", "PS_NO");
			dbColumnNames.put("psDesignation", "PS_DESIGNATION");
			dbColumnNames.put("parentPsId", "PARENT_PS_ID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("psEndDate", "PS_END_DATE");
			dbColumnNames.put("psTradeClass", "PS_TRADE_CLASS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("psStartDate", "PS_START_DATE");
			dbColumnNames.put("parentPsName", "PARENT_PS_NAME");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ps model in the entity cache if it is enabled.
	 *
	 * @param psModel the ps model
	 */
	@Override
	public void cacheResult(PsModel psModel) {
		entityCache.putResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelImpl.class, psModel.getPrimaryKey(), psModel);

		psModel.resetOriginalValues();
	}

	/**
	 * Caches the ps models in the entity cache if it is enabled.
	 *
	 * @param psModels the ps models
	 */
	@Override
	public void cacheResult(List<PsModel> psModels) {
		for (PsModel psModel : psModels) {
			if (entityCache.getResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
						PsModelImpl.class, psModel.getPrimaryKey()) == null) {
				cacheResult(psModel);
			}
			else {
				psModel.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ps models.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PsModelImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ps model.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PsModel psModel) {
		entityCache.removeResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelImpl.class, psModel.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PsModel> psModels) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PsModel psModel : psModels) {
			entityCache.removeResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
				PsModelImpl.class, psModel.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ps model with the primary key. Does not add the ps model to the database.
	 *
	 * @param psModelSid the primary key for the new ps model
	 * @return the new ps model
	 */
	@Override
	public PsModel create(int psModelSid) {
		PsModel psModel = new PsModelImpl();

		psModel.setNew(true);
		psModel.setPrimaryKey(psModelSid);

		return psModel;
	}

	/**
	 * Removes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param psModelSid the primary key of the ps model
	 * @return the ps model that was removed
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel remove(int psModelSid) throws NoSuchPsModelException {
		return remove((Serializable)psModelSid);
	}

	/**
	 * Removes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ps model
	 * @return the ps model that was removed
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel remove(Serializable primaryKey)
		throws NoSuchPsModelException {
		Session session = null;

		try {
			session = openSession();

			PsModel psModel = (PsModel)session.get(PsModelImpl.class, primaryKey);

			if (psModel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPsModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(psModel);
		}
		catch (NoSuchPsModelException nsee) {
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
	protected PsModel removeImpl(PsModel psModel) {
		psModel = toUnwrappedModel(psModel);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(psModel)) {
				psModel = (PsModel)session.get(PsModelImpl.class,
						psModel.getPrimaryKeyObj());
			}

			if (psModel != null) {
				session.delete(psModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (psModel != null) {
			clearCache(psModel);
		}

		return psModel;
	}

	@Override
	public PsModel updateImpl(PsModel psModel) {
		psModel = toUnwrappedModel(psModel);

		boolean isNew = psModel.isNew();

		PsModelModelImpl psModelModelImpl = (PsModelModelImpl)psModel;

		Session session = null;

		try {
			session = openSession();

			if (psModel.isNew()) {
				session.save(psModel);

				psModel.setNew(false);
			}
			else {
				psModel = (PsModel)session.merge(psModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PsModelModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { psModelModelImpl.getPsId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSID,
				args);

			args = new Object[] { psModelModelImpl.getPsNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNO,
				args);

			args = new Object[] { psModelModelImpl.getPsName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNAME,
				args);

			args = new Object[] { psModelModelImpl.getPsType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSTYPE,
				args);

			args = new Object[] { psModelModelImpl.getPsStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PSSTATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSSTATUS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((psModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { psModelModelImpl.getOriginalPsId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSID,
					args);

				args = new Object[] { psModelModelImpl.getPsId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSID,
					args);
			}

			if ((psModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { psModelModelImpl.getOriginalPsNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNO,
					args);

				args = new Object[] { psModelModelImpl.getPsNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNO,
					args);
			}

			if ((psModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						psModelModelImpl.getOriginalPsName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNAME,
					args);

				args = new Object[] { psModelModelImpl.getPsName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSNAME,
					args);
			}

			if ((psModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						psModelModelImpl.getOriginalPsType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSTYPE,
					args);

				args = new Object[] { psModelModelImpl.getPsType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSTYPE,
					args);
			}

			if ((psModelModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						psModelModelImpl.getOriginalPsStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSSTATUS,
					args);

				args = new Object[] { psModelModelImpl.getPsStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PSSTATUS, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PSSTATUS,
					args);
			}
		}

		entityCache.putResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
			PsModelImpl.class, psModel.getPrimaryKey(), psModel, false);

		psModel.resetOriginalValues();

		return psModel;
	}

	protected PsModel toUnwrappedModel(PsModel psModel) {
		if (psModel instanceof PsModelImpl) {
			return psModel;
		}

		PsModelImpl psModelImpl = new PsModelImpl();

		psModelImpl.setNew(psModel.isNew());
		psModelImpl.setPrimaryKey(psModel.getPrimaryKey());

		psModelImpl.setPsId(psModel.getPsId());
		psModelImpl.setPsName(psModel.getPsName());
		psModelImpl.setPsType(psModel.getPsType());
		psModelImpl.setModifiedDate(psModel.getModifiedDate());
		psModelImpl.setPsCategory(psModel.getPsCategory());
		psModelImpl.setRecordLockStatus(psModel.isRecordLockStatus());
		psModelImpl.setPsStatus(psModel.getPsStatus());
		psModelImpl.setCreatedDate(psModel.getCreatedDate());
		psModelImpl.setCreatedBy(psModel.getCreatedBy());
		psModelImpl.setSource(psModel.getSource());
		psModelImpl.setPsNo(psModel.getPsNo());
		psModelImpl.setPsDesignation(psModel.getPsDesignation());
		psModelImpl.setParentPsId(psModel.getParentPsId());
		psModelImpl.setBatchId(psModel.getBatchId());
		psModelImpl.setPsModelSid(psModel.getPsModelSid());
		psModelImpl.setPsEndDate(psModel.getPsEndDate());
		psModelImpl.setPsTradeClass(psModel.getPsTradeClass());
		psModelImpl.setModifiedBy(psModel.getModifiedBy());
		psModelImpl.setInboundStatus(psModel.getInboundStatus());
		psModelImpl.setPsStartDate(psModel.getPsStartDate());
		psModelImpl.setParentPsName(psModel.getParentPsName());
		psModelImpl.setInternalNotes(psModel.getInternalNotes());

		return psModelImpl;
	}

	/**
	 * Returns the ps model with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps model
	 * @return the ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPsModelException {
		PsModel psModel = fetchByPrimaryKey(primaryKey);

		if (psModel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPsModelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return psModel;
	}

	/**
	 * Returns the ps model with the primary key or throws a {@link NoSuchPsModelException} if it could not be found.
	 *
	 * @param psModelSid the primary key of the ps model
	 * @return the ps model
	 * @throws NoSuchPsModelException if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel findByPrimaryKey(int psModelSid)
		throws NoSuchPsModelException {
		return findByPrimaryKey((Serializable)psModelSid);
	}

	/**
	 * Returns the ps model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps model
	 * @return the ps model, or <code>null</code> if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
				PsModelImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PsModel psModel = (PsModel)serializable;

		if (psModel == null) {
			Session session = null;

			try {
				session = openSession();

				psModel = (PsModel)session.get(PsModelImpl.class, primaryKey);

				if (psModel != null) {
					cacheResult(psModel);
				}
				else {
					entityCache.putResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
						PsModelImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
					PsModelImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return psModel;
	}

	/**
	 * Returns the ps model with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param psModelSid the primary key of the ps model
	 * @return the ps model, or <code>null</code> if a ps model with the primary key could not be found
	 */
	@Override
	public PsModel fetchByPrimaryKey(int psModelSid) {
		return fetchByPrimaryKey((Serializable)psModelSid);
	}

	@Override
	public Map<Serializable, PsModel> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PsModel> map = new HashMap<Serializable, PsModel>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PsModel psModel = fetchByPrimaryKey(primaryKey);

			if (psModel != null) {
				map.put(primaryKey, psModel);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
					PsModelImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PsModel)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PSMODEL_WHERE_PKS_IN);

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

			for (PsModel psModel : (List<PsModel>)q.list()) {
				map.put(psModel.getPrimaryKeyObj(), psModel);

				cacheResult(psModel);

				uncachedPrimaryKeys.remove(psModel.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PsModelModelImpl.ENTITY_CACHE_ENABLED,
					PsModelImpl.class, primaryKey, nullModel);
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
	 * Returns all the ps models.
	 *
	 * @return the ps models
	 */
	@Override
	public List<PsModel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @return the range of ps models
	 */
	@Override
	public List<PsModel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ps models
	 */
	@Override
	public List<PsModel> findAll(int start, int end,
		OrderByComparator<PsModel> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps models.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps models
	 * @param end the upper bound of the range of ps models (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ps models
	 */
	@Override
	public List<PsModel> findAll(int start, int end,
		OrderByComparator<PsModel> orderByComparator, boolean retrieveFromCache) {
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

		List<PsModel> list = null;

		if (retrieveFromCache) {
			list = (List<PsModel>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PSMODEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PSMODEL;

				if (pagination) {
					sql = sql.concat(PsModelModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsModel>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ps models from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PsModel psModel : findAll()) {
			remove(psModel);
		}
	}

	/**
	 * Returns the number of ps models.
	 *
	 * @return the number of ps models
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PSMODEL);

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
		return PsModelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ps model persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PsModelImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PSMODEL = "SELECT psModel FROM PsModel psModel";
	private static final String _SQL_SELECT_PSMODEL_WHERE_PKS_IN = "SELECT psModel FROM PsModel psModel WHERE PS_MODEL_SID IN (";
	private static final String _SQL_SELECT_PSMODEL_WHERE = "SELECT psModel FROM PsModel psModel WHERE ";
	private static final String _SQL_COUNT_PSMODEL = "SELECT COUNT(psModel) FROM PsModel psModel";
	private static final String _SQL_COUNT_PSMODEL_WHERE = "SELECT COUNT(psModel) FROM PsModel psModel WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "psModel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsModel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PsModel exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PsModelPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"psId", "psName", "psType", "modifiedDate", "psCategory",
				"recordLockStatus", "psStatus", "createdDate", "createdBy",
				"source", "psNo", "psDesignation", "parentPsId", "batchId",
				"psModelSid", "psEndDate", "psTradeClass", "modifiedBy",
				"inboundStatus", "psStartDate", "parentPsName", "internalNotes"
			});
}