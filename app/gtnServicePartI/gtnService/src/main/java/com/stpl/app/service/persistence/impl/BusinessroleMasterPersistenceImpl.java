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

import com.stpl.app.exception.NoSuchBusinessroleMasterException;
import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.impl.BusinessroleMasterImpl;
import com.stpl.app.model.impl.BusinessroleMasterModelImpl;
import com.stpl.app.service.persistence.BusinessroleMasterPersistence;

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
 * The persistence implementation for the businessrole master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleMasterPersistence
 * @see com.stpl.app.service.persistence.BusinessroleMasterUtil
 * @generated
 */
@ProviderType
public class BusinessroleMasterPersistenceImpl extends BasePersistenceImpl<BusinessroleMaster>
	implements BusinessroleMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BusinessroleMasterUtil} to access the businessrole master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BusinessroleMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLENAME =
		new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBusinessroleName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME =
		new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBusinessroleName", new String[] { String.class.getName() },
			BusinessroleMasterModelImpl.BUSINESSROLENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUSINESSROLENAME = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBusinessroleName", new String[] { String.class.getName() });

	/**
	 * Returns all the businessrole masters where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @return the matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessroleName(
		String businessroleName) {
		return findByBusinessroleName(businessroleName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the businessrole masters where businessroleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param businessroleName the businessrole name
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @return the range of matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessroleName(
		String businessroleName, int start, int end) {
		return findByBusinessroleName(businessroleName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param businessroleName the businessrole name
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessroleName(
		String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return findByBusinessroleName(businessroleName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param businessroleName the businessrole name
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessroleName(
		String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME;
			finderArgs = new Object[] { businessroleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLENAME;
			finderArgs = new Object[] {
					businessroleName,
					
					start, end, orderByComparator
				};
		}

		List<BusinessroleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<BusinessroleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BusinessroleMaster businessroleMaster : list) {
					if (!Objects.equals(businessroleName,
								businessroleMaster.getBusinessroleName())) {
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

			query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

			boolean bindBusinessroleName = false;

			if (businessroleName == null) {
				query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1);
			}
			else if (businessroleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3);
			}
			else {
				bindBusinessroleName = true;

				query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBusinessroleName) {
					qPos.add(businessroleName);
				}

				if (!pagination) {
					list = (List<BusinessroleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BusinessroleMaster>)QueryUtil.list(q,
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
	 * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching businessrole master
	 * @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster findByBusinessroleName_First(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = fetchByBusinessroleName_First(businessroleName,
				orderByComparator);

		if (businessroleMaster != null) {
			return businessroleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessroleName=");
		msg.append(businessroleName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBusinessroleMasterException(msg.toString());
	}

	/**
	 * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster fetchByBusinessroleName_First(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		List<BusinessroleMaster> list = findByBusinessroleName(businessroleName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching businessrole master
	 * @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster findByBusinessroleName_Last(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = fetchByBusinessroleName_Last(businessroleName,
				orderByComparator);

		if (businessroleMaster != null) {
			return businessroleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessroleName=");
		msg.append(businessroleName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBusinessroleMasterException(msg.toString());
	}

	/**
	 * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster fetchByBusinessroleName_Last(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		int count = countByBusinessroleName(businessroleName);

		if (count == 0) {
			return null;
		}

		List<BusinessroleMaster> list = findByBusinessroleName(businessroleName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleMasterSid the primary key of the current businessrole master
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next businessrole master
	 * @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster[] findByBusinessroleName_PrevAndNext(
		int businessroleMasterSid, String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = findByPrimaryKey(businessroleMasterSid);

		Session session = null;

		try {
			session = openSession();

			BusinessroleMaster[] array = new BusinessroleMasterImpl[3];

			array[0] = getByBusinessroleName_PrevAndNext(session,
					businessroleMaster, businessroleName, orderByComparator,
					true);

			array[1] = businessroleMaster;

			array[2] = getByBusinessroleName_PrevAndNext(session,
					businessroleMaster, businessroleName, orderByComparator,
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

	protected BusinessroleMaster getByBusinessroleName_PrevAndNext(
		Session session, BusinessroleMaster businessroleMaster,
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

		boolean bindBusinessroleName = false;

		if (businessroleName == null) {
			query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1);
		}
		else if (businessroleName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3);
		}
		else {
			bindBusinessroleName = true;

			query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2);
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
			query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindBusinessroleName) {
			qPos.add(businessroleName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(businessroleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BusinessroleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the businessrole masters where businessroleName = &#63; from the database.
	 *
	 * @param businessroleName the businessrole name
	 */
	@Override
	public void removeByBusinessroleName(String businessroleName) {
		for (BusinessroleMaster businessroleMaster : findByBusinessroleName(
				businessroleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(businessroleMaster);
		}
	}

	/**
	 * Returns the number of businessrole masters where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @return the number of matching businessrole masters
	 */
	@Override
	public int countByBusinessroleName(String businessroleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUSINESSROLENAME;

		Object[] finderArgs = new Object[] { businessroleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUSINESSROLEMASTER_WHERE);

			boolean bindBusinessroleName = false;

			if (businessroleName == null) {
				query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1);
			}
			else if (businessroleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3);
			}
			else {
				bindBusinessroleName = true;

				query.append(_FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBusinessroleName) {
					qPos.add(businessroleName);
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

	private static final String _FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_1 =
		"businessroleMaster.businessroleName IS NULL";
	private static final String _FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_2 =
		"businessroleMaster.businessroleName = ?";
	private static final String _FINDER_COLUMN_BUSINESSROLENAME_BUSINESSROLENAME_3 =
		"(businessroleMaster.businessroleName IS NULL OR businessroleMaster.businessroleName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE =
		new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBusinessRoleUnique",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE =
		new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED,
			BusinessroleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBusinessRoleUnique",
			new String[] { String.class.getName() },
			BusinessroleMasterModelImpl.BUSINESSROLENAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE = new FinderPath(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBusinessRoleUnique", new String[] { String.class.getName() });

	/**
	 * Returns all the businessrole masters where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @return the matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessRoleUnique(
		String businessroleName) {
		return findByBusinessRoleUnique(businessroleName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the businessrole masters where businessroleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param businessroleName the businessrole name
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @return the range of matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessRoleUnique(
		String businessroleName, int start, int end) {
		return findByBusinessRoleUnique(businessroleName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param businessroleName the businessrole name
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessRoleUnique(
		String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return findByBusinessRoleUnique(businessroleName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param businessroleName the businessrole name
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findByBusinessRoleUnique(
		String businessroleName, int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE;
			finderArgs = new Object[] { businessroleName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE;
			finderArgs = new Object[] {
					businessroleName,
					
					start, end, orderByComparator
				};
		}

		List<BusinessroleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<BusinessroleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BusinessroleMaster businessroleMaster : list) {
					if (!Objects.equals(businessroleName,
								businessroleMaster.getBusinessroleName())) {
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

			query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

			boolean bindBusinessroleName = false;

			if (businessroleName == null) {
				query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1);
			}
			else if (businessroleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3);
			}
			else {
				bindBusinessroleName = true;

				query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBusinessroleName) {
					qPos.add(businessroleName);
				}

				if (!pagination) {
					list = (List<BusinessroleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BusinessroleMaster>)QueryUtil.list(q,
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
	 * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching businessrole master
	 * @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster findByBusinessRoleUnique_First(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = fetchByBusinessRoleUnique_First(businessroleName,
				orderByComparator);

		if (businessroleMaster != null) {
			return businessroleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessroleName=");
		msg.append(businessroleName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBusinessroleMasterException(msg.toString());
	}

	/**
	 * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster fetchByBusinessRoleUnique_First(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		List<BusinessroleMaster> list = findByBusinessRoleUnique(businessroleName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching businessrole master
	 * @throws NoSuchBusinessroleMasterException if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster findByBusinessRoleUnique_Last(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = fetchByBusinessRoleUnique_Last(businessroleName,
				orderByComparator);

		if (businessroleMaster != null) {
			return businessroleMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessroleName=");
		msg.append(businessroleName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBusinessroleMasterException(msg.toString());
	}

	/**
	 * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
	 */
	@Override
	public BusinessroleMaster fetchByBusinessRoleUnique_Last(
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		int count = countByBusinessRoleUnique(businessroleName);

		if (count == 0) {
			return null;
		}

		List<BusinessroleMaster> list = findByBusinessRoleUnique(businessroleName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
	 *
	 * @param businessroleMasterSid the primary key of the current businessrole master
	 * @param businessroleName the businessrole name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next businessrole master
	 * @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster[] findByBusinessRoleUnique_PrevAndNext(
		int businessroleMasterSid, String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = findByPrimaryKey(businessroleMasterSid);

		Session session = null;

		try {
			session = openSession();

			BusinessroleMaster[] array = new BusinessroleMasterImpl[3];

			array[0] = getByBusinessRoleUnique_PrevAndNext(session,
					businessroleMaster, businessroleName, orderByComparator,
					true);

			array[1] = businessroleMaster;

			array[2] = getByBusinessRoleUnique_PrevAndNext(session,
					businessroleMaster, businessroleName, orderByComparator,
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

	protected BusinessroleMaster getByBusinessRoleUnique_PrevAndNext(
		Session session, BusinessroleMaster businessroleMaster,
		String businessroleName,
		OrderByComparator<BusinessroleMaster> orderByComparator,
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

		query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE);

		boolean bindBusinessroleName = false;

		if (businessroleName == null) {
			query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1);
		}
		else if (businessroleName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3);
		}
		else {
			bindBusinessroleName = true;

			query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2);
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
			query.append(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindBusinessroleName) {
			qPos.add(businessroleName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(businessroleMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BusinessroleMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the businessrole masters where businessroleName = &#63; from the database.
	 *
	 * @param businessroleName the businessrole name
	 */
	@Override
	public void removeByBusinessRoleUnique(String businessroleName) {
		for (BusinessroleMaster businessroleMaster : findByBusinessRoleUnique(
				businessroleName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(businessroleMaster);
		}
	}

	/**
	 * Returns the number of businessrole masters where businessroleName = &#63;.
	 *
	 * @param businessroleName the businessrole name
	 * @return the number of matching businessrole masters
	 */
	@Override
	public int countByBusinessRoleUnique(String businessroleName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE;

		Object[] finderArgs = new Object[] { businessroleName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BUSINESSROLEMASTER_WHERE);

			boolean bindBusinessroleName = false;

			if (businessroleName == null) {
				query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1);
			}
			else if (businessroleName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3);
			}
			else {
				bindBusinessroleName = true;

				query.append(_FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBusinessroleName) {
					qPos.add(businessroleName);
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

	private static final String _FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_1 =
		"businessroleMaster.businessroleName IS NULL";
	private static final String _FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_2 =
		"businessroleMaster.businessroleName = ?";
	private static final String _FINDER_COLUMN_BUSINESSROLEUNIQUE_BUSINESSROLENAME_3 =
		"(businessroleMaster.businessroleName IS NULL OR businessroleMaster.businessroleName = '')";

	public BusinessroleMasterPersistenceImpl() {
		setModelClass(BusinessroleMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("businessroleMasterSid", "BUSINESSROLE_MASTER_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("hierarchyLevel", "HIERARCHY_LEVEL");
			dbColumnNames.put("processed", "PROCESSED");
			dbColumnNames.put("businessroleName", "BUSINESSROLE_NAME");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("businessroleDesc", "BUSINESSROLE_DESC");
			dbColumnNames.put("isActive", "IS_ACTIVE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the businessrole master in the entity cache if it is enabled.
	 *
	 * @param businessroleMaster the businessrole master
	 */
	@Override
	public void cacheResult(BusinessroleMaster businessroleMaster) {
		entityCache.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey(),
			businessroleMaster);

		businessroleMaster.resetOriginalValues();
	}

	/**
	 * Caches the businessrole masters in the entity cache if it is enabled.
	 *
	 * @param businessroleMasters the businessrole masters
	 */
	@Override
	public void cacheResult(List<BusinessroleMaster> businessroleMasters) {
		for (BusinessroleMaster businessroleMaster : businessroleMasters) {
			if (entityCache.getResult(
						BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
						BusinessroleMasterImpl.class,
						businessroleMaster.getPrimaryKey()) == null) {
				cacheResult(businessroleMaster);
			}
			else {
				businessroleMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all businessrole masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BusinessroleMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the businessrole master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BusinessroleMaster businessroleMaster) {
		entityCache.removeResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BusinessroleMaster> businessroleMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BusinessroleMaster businessroleMaster : businessroleMasters) {
			entityCache.removeResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
				BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
	 *
	 * @param businessroleMasterSid the primary key for the new businessrole master
	 * @return the new businessrole master
	 */
	@Override
	public BusinessroleMaster create(int businessroleMasterSid) {
		BusinessroleMaster businessroleMaster = new BusinessroleMasterImpl();

		businessroleMaster.setNew(true);
		businessroleMaster.setPrimaryKey(businessroleMasterSid);

		return businessroleMaster;
	}

	/**
	 * Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param businessroleMasterSid the primary key of the businessrole master
	 * @return the businessrole master that was removed
	 * @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster remove(int businessroleMasterSid)
		throws NoSuchBusinessroleMasterException {
		return remove((Serializable)businessroleMasterSid);
	}

	/**
	 * Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the businessrole master
	 * @return the businessrole master that was removed
	 * @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster remove(Serializable primaryKey)
		throws NoSuchBusinessroleMasterException {
		Session session = null;

		try {
			session = openSession();

			BusinessroleMaster businessroleMaster = (BusinessroleMaster)session.get(BusinessroleMasterImpl.class,
					primaryKey);

			if (businessroleMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBusinessroleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(businessroleMaster);
		}
		catch (NoSuchBusinessroleMasterException nsee) {
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
	protected BusinessroleMaster removeImpl(
		BusinessroleMaster businessroleMaster) {
		businessroleMaster = toUnwrappedModel(businessroleMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(businessroleMaster)) {
				businessroleMaster = (BusinessroleMaster)session.get(BusinessroleMasterImpl.class,
						businessroleMaster.getPrimaryKeyObj());
			}

			if (businessroleMaster != null) {
				session.delete(businessroleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (businessroleMaster != null) {
			clearCache(businessroleMaster);
		}

		return businessroleMaster;
	}

	@Override
	public BusinessroleMaster updateImpl(BusinessroleMaster businessroleMaster) {
		businessroleMaster = toUnwrappedModel(businessroleMaster);

		boolean isNew = businessroleMaster.isNew();

		BusinessroleMasterModelImpl businessroleMasterModelImpl = (BusinessroleMasterModelImpl)businessroleMaster;

		Session session = null;

		try {
			session = openSession();

			if (businessroleMaster.isNew()) {
				session.save(businessroleMaster);

				businessroleMaster.setNew(false);
			}
			else {
				businessroleMaster = (BusinessroleMaster)session.merge(businessroleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BusinessroleMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					businessroleMasterModelImpl.getBusinessroleName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLENAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME,
				args);

			args = new Object[] {
					businessroleMasterModelImpl.getBusinessroleName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((businessroleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						businessroleMasterModelImpl.getOriginalBusinessroleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME,
					args);

				args = new Object[] {
						businessroleMasterModelImpl.getBusinessroleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLENAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLENAME,
					args);
			}

			if ((businessroleMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						businessroleMasterModelImpl.getOriginalBusinessroleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE,
					args);

				args = new Object[] {
						businessroleMasterModelImpl.getBusinessroleName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_BUSINESSROLEUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUSINESSROLEUNIQUE,
					args);
			}
		}

		entityCache.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
			BusinessroleMasterImpl.class, businessroleMaster.getPrimaryKey(),
			businessroleMaster, false);

		businessroleMaster.resetOriginalValues();

		return businessroleMaster;
	}

	protected BusinessroleMaster toUnwrappedModel(
		BusinessroleMaster businessroleMaster) {
		if (businessroleMaster instanceof BusinessroleMasterImpl) {
			return businessroleMaster;
		}

		BusinessroleMasterImpl businessroleMasterImpl = new BusinessroleMasterImpl();

		businessroleMasterImpl.setNew(businessroleMaster.isNew());
		businessroleMasterImpl.setPrimaryKey(businessroleMaster.getPrimaryKey());

		businessroleMasterImpl.setBusinessroleMasterSid(businessroleMaster.getBusinessroleMasterSid());
		businessroleMasterImpl.setCreatedDate(businessroleMaster.getCreatedDate());
		businessroleMasterImpl.setCreatedBy(businessroleMaster.getCreatedBy());
		businessroleMasterImpl.setUsersSid(businessroleMaster.getUsersSid());
		businessroleMasterImpl.setHierarchyLevel(businessroleMaster.getHierarchyLevel());
		businessroleMasterImpl.setProcessed(businessroleMaster.getProcessed());
		businessroleMasterImpl.setBusinessroleName(businessroleMaster.getBusinessroleName());
		businessroleMasterImpl.setVersionNo(businessroleMaster.getVersionNo());
		businessroleMasterImpl.setModifiedBy(businessroleMaster.getModifiedBy());
		businessroleMasterImpl.setModifiedDate(businessroleMaster.getModifiedDate());
		businessroleMasterImpl.setBusinessroleDesc(businessroleMaster.getBusinessroleDesc());
		businessroleMasterImpl.setIsActive(businessroleMaster.getIsActive());

		return businessroleMasterImpl;
	}

	/**
	 * Returns the businessrole master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the businessrole master
	 * @return the businessrole master
	 * @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBusinessroleMasterException {
		BusinessroleMaster businessroleMaster = fetchByPrimaryKey(primaryKey);

		if (businessroleMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBusinessroleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return businessroleMaster;
	}

	/**
	 * Returns the businessrole master with the primary key or throws a {@link NoSuchBusinessroleMasterException} if it could not be found.
	 *
	 * @param businessroleMasterSid the primary key of the businessrole master
	 * @return the businessrole master
	 * @throws NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster findByPrimaryKey(int businessroleMasterSid)
		throws NoSuchBusinessroleMasterException {
		return findByPrimaryKey((Serializable)businessroleMasterSid);
	}

	/**
	 * Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the businessrole master
	 * @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
				BusinessroleMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		BusinessroleMaster businessroleMaster = (BusinessroleMaster)serializable;

		if (businessroleMaster == null) {
			Session session = null;

			try {
				session = openSession();

				businessroleMaster = (BusinessroleMaster)session.get(BusinessroleMasterImpl.class,
						primaryKey);

				if (businessroleMaster != null) {
					cacheResult(businessroleMaster);
				}
				else {
					entityCache.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
						BusinessroleMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
					BusinessroleMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return businessroleMaster;
	}

	/**
	 * Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param businessroleMasterSid the primary key of the businessrole master
	 * @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
	 */
	@Override
	public BusinessroleMaster fetchByPrimaryKey(int businessroleMasterSid) {
		return fetchByPrimaryKey((Serializable)businessroleMasterSid);
	}

	@Override
	public Map<Serializable, BusinessroleMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BusinessroleMaster> map = new HashMap<Serializable, BusinessroleMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BusinessroleMaster businessroleMaster = fetchByPrimaryKey(primaryKey);

			if (businessroleMaster != null) {
				map.put(primaryKey, businessroleMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
					BusinessroleMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (BusinessroleMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BUSINESSROLEMASTER_WHERE_PKS_IN);

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

			for (BusinessroleMaster businessroleMaster : (List<BusinessroleMaster>)q.list()) {
				map.put(businessroleMaster.getPrimaryKeyObj(),
					businessroleMaster);

				cacheResult(businessroleMaster);

				uncachedPrimaryKeys.remove(businessroleMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(BusinessroleMasterModelImpl.ENTITY_CACHE_ENABLED,
					BusinessroleMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the businessrole masters.
	 *
	 * @return the businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the businessrole masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @return the range of businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the businessrole masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findAll(int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the businessrole masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of businessrole masters
	 * @param end the upper bound of the range of businessrole masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of businessrole masters
	 */
	@Override
	public List<BusinessroleMaster> findAll(int start, int end,
		OrderByComparator<BusinessroleMaster> orderByComparator,
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

		List<BusinessroleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<BusinessroleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BUSINESSROLEMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BUSINESSROLEMASTER;

				if (pagination) {
					sql = sql.concat(BusinessroleMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BusinessroleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BusinessroleMaster>)QueryUtil.list(q,
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
	 * Removes all the businessrole masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BusinessroleMaster businessroleMaster : findAll()) {
			remove(businessroleMaster);
		}
	}

	/**
	 * Returns the number of businessrole masters.
	 *
	 * @return the number of businessrole masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BUSINESSROLEMASTER);

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
		return BusinessroleMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the businessrole master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(BusinessroleMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_BUSINESSROLEMASTER = "SELECT businessroleMaster FROM BusinessroleMaster businessroleMaster";
	private static final String _SQL_SELECT_BUSINESSROLEMASTER_WHERE_PKS_IN = "SELECT businessroleMaster FROM BusinessroleMaster businessroleMaster WHERE BUSINESSROLE_MASTER_SID IN (";
	private static final String _SQL_SELECT_BUSINESSROLEMASTER_WHERE = "SELECT businessroleMaster FROM BusinessroleMaster businessroleMaster WHERE ";
	private static final String _SQL_COUNT_BUSINESSROLEMASTER = "SELECT COUNT(businessroleMaster) FROM BusinessroleMaster businessroleMaster";
	private static final String _SQL_COUNT_BUSINESSROLEMASTER_WHERE = "SELECT COUNT(businessroleMaster) FROM BusinessroleMaster businessroleMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "businessroleMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BusinessroleMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BusinessroleMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(BusinessroleMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"businessroleMasterSid", "createdDate", "createdBy", "usersSid",
				"hierarchyLevel", "processed", "businessroleName", "versionNo",
				"modifiedBy", "modifiedDate", "businessroleDesc", "isActive"
			});
}