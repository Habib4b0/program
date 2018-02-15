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

import com.stpl.app.exception.NoSuchCompanyMasterException;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.impl.CompanyMasterImpl;
import com.stpl.app.model.impl.CompanyMasterModelImpl;
import com.stpl.app.service.persistence.CompanyMasterPersistence;

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
 * The persistence implementation for the company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyMasterPersistence
 * @see com.stpl.app.service.persistence.CompanyMasterUtil
 * @generated
 */
@ProviderType
public class CompanyMasterPersistenceImpl extends BasePersistenceImpl<CompanyMaster>
	implements CompanyMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyMasterUtil} to access the company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNO =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyNo",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyNo", new String[] { String.class.getName() },
			CompanyMasterModelImpl.COMPANYNO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYNO = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyNo",
			new String[] { String.class.getName() });

	/**
	 * Returns all the company masters where companyNo = &#63;.
	 *
	 * @param companyNo the company no
	 * @return the matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyNo(String companyNo) {
		return findByCompanyNo(companyNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the company masters where companyNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyNo the company no
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyNo(String companyNo, int start,
		int end) {
		return findByCompanyNo(companyNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters where companyNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyNo the company no
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyNo(String companyNo, int start,
		int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return findByCompanyNo(companyNo, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company masters where companyNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyNo the company no
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyNo(String companyNo, int start,
		int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO;
			finderArgs = new Object[] { companyNo };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNO;
			finderArgs = new Object[] { companyNo, start, end, orderByComparator };
		}

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyMaster companyMaster : list) {
					if (!Objects.equals(companyNo, companyMaster.getCompanyNo())) {
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

			query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

			boolean bindCompanyNo = false;

			if (companyNo == null) {
				query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_1);
			}
			else if (companyNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_3);
			}
			else {
				bindCompanyNo = true;

				query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyNo) {
					qPos.add(companyNo);
				}

				if (!pagination) {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first company master in the ordered set where companyNo = &#63;.
	 *
	 * @param companyNo the company no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyNo_First(String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyNo_First(companyNo,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyNo=");
		msg.append(companyNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the first company master in the ordered set where companyNo = &#63;.
	 *
	 * @param companyNo the company no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyNo_First(String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator) {
		List<CompanyMaster> list = findByCompanyNo(companyNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company master in the ordered set where companyNo = &#63;.
	 *
	 * @param companyNo the company no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyNo_Last(String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyNo_Last(companyNo,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyNo=");
		msg.append(companyNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the last company master in the ordered set where companyNo = &#63;.
	 *
	 * @param companyNo the company no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyNo_Last(String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator) {
		int count = countByCompanyNo(companyNo);

		if (count == 0) {
			return null;
		}

		List<CompanyMaster> list = findByCompanyNo(companyNo, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company masters before and after the current company master in the ordered set where companyNo = &#63;.
	 *
	 * @param companyMasterSid the primary key of the current company master
	 * @param companyNo the company no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster[] findByCompanyNo_PrevAndNext(int companyMasterSid,
		String companyNo, OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

		Session session = null;

		try {
			session = openSession();

			CompanyMaster[] array = new CompanyMasterImpl[3];

			array[0] = getByCompanyNo_PrevAndNext(session, companyMaster,
					companyNo, orderByComparator, true);

			array[1] = companyMaster;

			array[2] = getByCompanyNo_PrevAndNext(session, companyMaster,
					companyNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CompanyMaster getByCompanyNo_PrevAndNext(Session session,
		CompanyMaster companyMaster, String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

		boolean bindCompanyNo = false;

		if (companyNo == null) {
			query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_1);
		}
		else if (companyNo.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_3);
		}
		else {
			bindCompanyNo = true;

			query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_2);
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
			query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCompanyNo) {
			qPos.add(companyNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company masters where companyNo = &#63; from the database.
	 *
	 * @param companyNo the company no
	 */
	@Override
	public void removeByCompanyNo(String companyNo) {
		for (CompanyMaster companyMaster : findByCompanyNo(companyNo,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters where companyNo = &#63;.
	 *
	 * @param companyNo the company no
	 * @return the number of matching company masters
	 */
	@Override
	public int countByCompanyNo(String companyNo) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYNO;

		Object[] finderArgs = new Object[] { companyNo };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

			boolean bindCompanyNo = false;

			if (companyNo == null) {
				query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_1);
			}
			else if (companyNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_3);
			}
			else {
				bindCompanyNo = true;

				query.append(_FINDER_COLUMN_COMPANYNO_COMPANYNO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyNo) {
					qPos.add(companyNo);
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

	private static final String _FINDER_COLUMN_COMPANYNO_COMPANYNO_1 = "companyMaster.companyNo IS NULL";
	private static final String _FINDER_COLUMN_COMPANYNO_COMPANYNO_2 = "companyMaster.companyNo = ?";
	private static final String _FINDER_COLUMN_COMPANYNO_COMPANYNO_3 = "(companyMaster.companyNo IS NULL OR companyMaster.companyNo = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyId", new String[] { String.class.getName() },
			CompanyMasterModelImpl.COMPANYSTRINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the company masters where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @return the matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyId(String companyStringId) {
		return findByCompanyId(companyStringId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyId(String companyStringId,
		int start, int end) {
		return findByCompanyId(companyStringId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyId(String companyStringId,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return findByCompanyId(companyStringId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the company masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyId(String companyStringId,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator,
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

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyMaster companyMaster : list) {
					if (!Objects.equals(companyStringId,
								companyMaster.getCompanyStringId())) {
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

			query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

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
				query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
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
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyId_First(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyId_First(companyStringId,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringId=");
		msg.append(companyStringId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the first company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyId_First(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		List<CompanyMaster> list = findByCompanyId(companyStringId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyId_Last(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyId_Last(companyStringId,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringId=");
		msg.append(companyStringId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the last company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyId_Last(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		int count = countByCompanyId(companyStringId);

		if (count == 0) {
			return null;
		}

		List<CompanyMaster> list = findByCompanyId(companyStringId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company masters before and after the current company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyMasterSid the primary key of the current company master
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster[] findByCompanyId_PrevAndNext(int companyMasterSid,
		String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

		Session session = null;

		try {
			session = openSession();

			CompanyMaster[] array = new CompanyMasterImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, companyMaster,
					companyStringId, orderByComparator, true);

			array[1] = companyMaster;

			array[2] = getByCompanyId_PrevAndNext(session, companyMaster,
					companyStringId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CompanyMaster getByCompanyId_PrevAndNext(Session session,
		CompanyMaster companyMaster, String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

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
			query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company masters where companyStringId = &#63; from the database.
	 *
	 * @param companyStringId the company string ID
	 */
	@Override
	public void removeByCompanyId(String companyStringId) {
		for (CompanyMaster companyMaster : findByCompanyId(companyStringId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @return the number of matching company masters
	 */
	@Override
	public int countByCompanyId(String companyStringId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyStringId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_1 = "companyMaster.companyStringId IS NULL";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_2 = "companyMaster.companyStringId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYSTRINGID_3 = "(companyMaster.companyStringId IS NULL OR companyMaster.companyStringId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNAME =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyName", new String[] { String.class.getName() },
			CompanyMasterModelImpl.COMPANYNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYNAME = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the company masters where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyName(String companyName) {
		return findByCompanyName(companyName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company masters where companyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyName the company name
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyName(String companyName, int start,
		int end) {
		return findByCompanyName(companyName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters where companyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyName the company name
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyName(String companyName, int start,
		int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return findByCompanyName(companyName, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the company masters where companyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyName the company name
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyName(String companyName, int start,
		int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME;
			finderArgs = new Object[] { companyName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYNAME;
			finderArgs = new Object[] { companyName, start, end, orderByComparator };
		}

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyMaster companyMaster : list) {
					if (!Objects.equals(companyName,
								companyMaster.getCompanyName())) {
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

			query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

			boolean bindCompanyName = false;

			if (companyName == null) {
				query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1);
			}
			else if (companyName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
			}
			else {
				bindCompanyName = true;

				query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyName) {
					qPos.add(companyName);
				}

				if (!pagination) {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first company master in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyName_First(String companyName,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyName_First(companyName,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyName=");
		msg.append(companyName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the first company master in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyName_First(String companyName,
		OrderByComparator<CompanyMaster> orderByComparator) {
		List<CompanyMaster> list = findByCompanyName(companyName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company master in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyName_Last(String companyName,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyName_Last(companyName,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyName=");
		msg.append(companyName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the last company master in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyName_Last(String companyName,
		OrderByComparator<CompanyMaster> orderByComparator) {
		int count = countByCompanyName(companyName);

		if (count == 0) {
			return null;
		}

		List<CompanyMaster> list = findByCompanyName(companyName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company masters before and after the current company master in the ordered set where companyName = &#63;.
	 *
	 * @param companyMasterSid the primary key of the current company master
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster[] findByCompanyName_PrevAndNext(int companyMasterSid,
		String companyName, OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

		Session session = null;

		try {
			session = openSession();

			CompanyMaster[] array = new CompanyMasterImpl[3];

			array[0] = getByCompanyName_PrevAndNext(session, companyMaster,
					companyName, orderByComparator, true);

			array[1] = companyMaster;

			array[2] = getByCompanyName_PrevAndNext(session, companyMaster,
					companyName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CompanyMaster getByCompanyName_PrevAndNext(Session session,
		CompanyMaster companyMaster, String companyName,
		OrderByComparator<CompanyMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

		boolean bindCompanyName = false;

		if (companyName == null) {
			query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1);
		}
		else if (companyName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
		}
		else {
			bindCompanyName = true;

			query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
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
			query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCompanyName) {
			qPos.add(companyName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company masters where companyName = &#63; from the database.
	 *
	 * @param companyName the company name
	 */
	@Override
	public void removeByCompanyName(String companyName) {
		for (CompanyMaster companyMaster : findByCompanyName(companyName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the number of matching company masters
	 */
	@Override
	public int countByCompanyName(String companyName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYNAME;

		Object[] finderArgs = new Object[] { companyName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

			boolean bindCompanyName = false;

			if (companyName == null) {
				query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1);
			}
			else if (companyName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
			}
			else {
				bindCompanyName = true;

				query.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyName) {
					qPos.add(companyName);
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

	private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_1 = "companyMaster.companyName IS NULL";
	private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2 = "companyMaster.companyName = ?";
	private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3 = "(companyMaster.companyName IS NULL OR companyMaster.companyName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYTYPE =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyType",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyType", new String[] { Integer.class.getName() },
			CompanyMasterModelImpl.COMPANYTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYTYPE = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyType",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the company masters where companyType = &#63;.
	 *
	 * @param companyType the company type
	 * @return the matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyType(int companyType) {
		return findByCompanyType(companyType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company masters where companyType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyType the company type
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyType(int companyType, int start,
		int end) {
		return findByCompanyType(companyType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters where companyType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyType the company type
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyType(int companyType, int start,
		int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return findByCompanyType(companyType, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the company masters where companyType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyType the company type
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyType(int companyType, int start,
		int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE;
			finderArgs = new Object[] { companyType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYTYPE;
			finderArgs = new Object[] { companyType, start, end, orderByComparator };
		}

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyMaster companyMaster : list) {
					if ((companyType != companyMaster.getCompanyType())) {
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

			query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyType);

				if (!pagination) {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first company master in the ordered set where companyType = &#63;.
	 *
	 * @param companyType the company type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyType_First(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyType_First(companyType,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyType=");
		msg.append(companyType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the first company master in the ordered set where companyType = &#63;.
	 *
	 * @param companyType the company type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyType_First(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator) {
		List<CompanyMaster> list = findByCompanyType(companyType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company master in the ordered set where companyType = &#63;.
	 *
	 * @param companyType the company type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyType_Last(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyType_Last(companyType,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyType=");
		msg.append(companyType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the last company master in the ordered set where companyType = &#63;.
	 *
	 * @param companyType the company type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyType_Last(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator) {
		int count = countByCompanyType(companyType);

		if (count == 0) {
			return null;
		}

		List<CompanyMaster> list = findByCompanyType(companyType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company masters before and after the current company master in the ordered set where companyType = &#63;.
	 *
	 * @param companyMasterSid the primary key of the current company master
	 * @param companyType the company type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster[] findByCompanyType_PrevAndNext(int companyMasterSid,
		int companyType, OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

		Session session = null;

		try {
			session = openSession();

			CompanyMaster[] array = new CompanyMasterImpl[3];

			array[0] = getByCompanyType_PrevAndNext(session, companyMaster,
					companyType, orderByComparator, true);

			array[1] = companyMaster;

			array[2] = getByCompanyType_PrevAndNext(session, companyMaster,
					companyType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CompanyMaster getByCompanyType_PrevAndNext(Session session,
		CompanyMaster companyMaster, int companyType,
		OrderByComparator<CompanyMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

		query.append(_FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2);

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
			query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company masters where companyType = &#63; from the database.
	 *
	 * @param companyType the company type
	 */
	@Override
	public void removeByCompanyType(int companyType) {
		for (CompanyMaster companyMaster : findByCompanyType(companyType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters where companyType = &#63;.
	 *
	 * @param companyType the company type
	 * @return the number of matching company masters
	 */
	@Override
	public int countByCompanyType(int companyType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYTYPE;

		Object[] finderArgs = new Object[] { companyType };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyType);

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

	private static final String _FINDER_COLUMN_COMPANYTYPE_COMPANYTYPE_2 = "companyMaster.companyType = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSTATUS =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyStatus", new String[] { Integer.class.getName() },
			CompanyMasterModelImpl.COMPANYSTATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYSTATUS = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the company masters where companyStatus = &#63;.
	 *
	 * @param companyStatus the company status
	 * @return the matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyStatus(int companyStatus) {
		return findByCompanyStatus(companyStatus, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company masters where companyStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStatus the company status
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyStatus(int companyStatus,
		int start, int end) {
		return findByCompanyStatus(companyStatus, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters where companyStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStatus the company status
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyStatus(int companyStatus,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return findByCompanyStatus(companyStatus, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company masters where companyStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStatus the company status
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyStatus(int companyStatus,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS;
			finderArgs = new Object[] { companyStatus };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYSTATUS;
			finderArgs = new Object[] {
					companyStatus,
					
					start, end, orderByComparator
				};
		}

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyMaster companyMaster : list) {
					if ((companyStatus != companyMaster.getCompanyStatus())) {
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

			query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyStatus);

				if (!pagination) {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first company master in the ordered set where companyStatus = &#63;.
	 *
	 * @param companyStatus the company status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyStatus_First(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyStatus_First(companyStatus,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStatus=");
		msg.append(companyStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the first company master in the ordered set where companyStatus = &#63;.
	 *
	 * @param companyStatus the company status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyStatus_First(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator) {
		List<CompanyMaster> list = findByCompanyStatus(companyStatus, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company master in the ordered set where companyStatus = &#63;.
	 *
	 * @param companyStatus the company status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyStatus_Last(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyStatus_Last(companyStatus,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStatus=");
		msg.append(companyStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the last company master in the ordered set where companyStatus = &#63;.
	 *
	 * @param companyStatus the company status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyStatus_Last(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator) {
		int count = countByCompanyStatus(companyStatus);

		if (count == 0) {
			return null;
		}

		List<CompanyMaster> list = findByCompanyStatus(companyStatus,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company masters before and after the current company master in the ordered set where companyStatus = &#63;.
	 *
	 * @param companyMasterSid the primary key of the current company master
	 * @param companyStatus the company status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster[] findByCompanyStatus_PrevAndNext(
		int companyMasterSid, int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

		Session session = null;

		try {
			session = openSession();

			CompanyMaster[] array = new CompanyMasterImpl[3];

			array[0] = getByCompanyStatus_PrevAndNext(session, companyMaster,
					companyStatus, orderByComparator, true);

			array[1] = companyMaster;

			array[2] = getByCompanyStatus_PrevAndNext(session, companyMaster,
					companyStatus, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CompanyMaster getByCompanyStatus_PrevAndNext(Session session,
		CompanyMaster companyMaster, int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

		query.append(_FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2);

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
			query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company masters where companyStatus = &#63; from the database.
	 *
	 * @param companyStatus the company status
	 */
	@Override
	public void removeByCompanyStatus(int companyStatus) {
		for (CompanyMaster companyMaster : findByCompanyStatus(companyStatus,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters where companyStatus = &#63;.
	 *
	 * @param companyStatus the company status
	 * @return the number of matching company masters
	 */
	@Override
	public int countByCompanyStatus(int companyStatus) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYSTATUS;

		Object[] finderArgs = new Object[] { companyStatus };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyStatus);

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

	private static final String _FINDER_COLUMN_COMPANYSTATUS_COMPANYSTATUS_2 = "companyMaster.companyStatus = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYUNIQUE =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyUnique",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE =
		new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED,
			CompanyMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyUnique", new String[] { String.class.getName() },
			CompanyMasterModelImpl.COMPANYSTRINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYUNIQUE = new FinderPath(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyUnique",
			new String[] { String.class.getName() });

	/**
	 * Returns all the company masters where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @return the matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyUnique(String companyStringId) {
		return findByCompanyUnique(companyStringId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyUnique(String companyStringId,
		int start, int end) {
		return findByCompanyUnique(companyStringId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyUnique(String companyStringId,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return findByCompanyUnique(companyStringId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company masters where companyStringId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringId the company string ID
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company masters
	 */
	@Override
	public List<CompanyMaster> findByCompanyUnique(String companyStringId,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE;
			finderArgs = new Object[] { companyStringId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYUNIQUE;
			finderArgs = new Object[] {
					companyStringId,
					
					start, end, orderByComparator
				};
		}

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyMaster companyMaster : list) {
					if (!Objects.equals(companyStringId,
								companyMaster.getCompanyStringId())) {
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

			query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

			boolean bindCompanyStringId = false;

			if (companyStringId == null) {
				query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_1);
			}
			else if (companyStringId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_3);
			}
			else {
				bindCompanyStringId = true;

				query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
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
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyUnique_First(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyUnique_First(companyStringId,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringId=");
		msg.append(companyStringId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the first company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyUnique_First(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		List<CompanyMaster> list = findByCompanyUnique(companyStringId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master
	 * @throws NoSuchCompanyMasterException if a matching company master could not be found
	 */
	@Override
	public CompanyMaster findByCompanyUnique_Last(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByCompanyUnique_Last(companyStringId,
				orderByComparator);

		if (companyMaster != null) {
			return companyMaster;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringId=");
		msg.append(companyStringId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyMasterException(msg.toString());
	}

	/**
	 * Returns the last company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company master, or <code>null</code> if a matching company master could not be found
	 */
	@Override
	public CompanyMaster fetchByCompanyUnique_Last(String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		int count = countByCompanyUnique(companyStringId);

		if (count == 0) {
			return null;
		}

		List<CompanyMaster> list = findByCompanyUnique(companyStringId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company masters before and after the current company master in the ordered set where companyStringId = &#63;.
	 *
	 * @param companyMasterSid the primary key of the current company master
	 * @param companyStringId the company string ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster[] findByCompanyUnique_PrevAndNext(
		int companyMasterSid, String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = findByPrimaryKey(companyMasterSid);

		Session session = null;

		try {
			session = openSession();

			CompanyMaster[] array = new CompanyMasterImpl[3];

			array[0] = getByCompanyUnique_PrevAndNext(session, companyMaster,
					companyStringId, orderByComparator, true);

			array[1] = companyMaster;

			array[2] = getByCompanyUnique_PrevAndNext(session, companyMaster,
					companyStringId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CompanyMaster getByCompanyUnique_PrevAndNext(Session session,
		CompanyMaster companyMaster, String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE);

		boolean bindCompanyStringId = false;

		if (companyStringId == null) {
			query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_1);
		}
		else if (companyStringId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_3);
		}
		else {
			bindCompanyStringId = true;

			query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_2);
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
			query.append(CompanyMasterModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(companyMaster);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyMaster> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company masters where companyStringId = &#63; from the database.
	 *
	 * @param companyStringId the company string ID
	 */
	@Override
	public void removeByCompanyUnique(String companyStringId) {
		for (CompanyMaster companyMaster : findByCompanyUnique(
				companyStringId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters where companyStringId = &#63;.
	 *
	 * @param companyStringId the company string ID
	 * @return the number of matching company masters
	 */
	@Override
	public int countByCompanyUnique(String companyStringId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYUNIQUE;

		Object[] finderArgs = new Object[] { companyStringId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYMASTER_WHERE);

			boolean bindCompanyStringId = false;

			if (companyStringId == null) {
				query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_1);
			}
			else if (companyStringId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_3);
			}
			else {
				bindCompanyStringId = true;

				query.append(_FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_2);
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

	private static final String _FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_1 = "companyMaster.companyStringId IS NULL";
	private static final String _FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_2 = "companyMaster.companyStringId = ?";
	private static final String _FINDER_COLUMN_COMPANYUNIQUE_COMPANYSTRINGID_3 = "(companyMaster.companyStringId IS NULL OR companyMaster.companyStringId = '')";

	public CompanyMasterPersistenceImpl() {
		setModelClass(CompanyMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("state", "STATE");
			dbColumnNames.put("financialSystem", "FINANCIAL_SYSTEM");
			dbColumnNames.put("companyGroup", "COMPANY_GROUP");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("companyCategory", "COMPANY_CATEGORY");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("lives", "LIVES");
			dbColumnNames.put("address2", "ADDRESS2");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("address1", "ADDRESS1");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("zipCode", "ZIP_CODE");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");
			dbColumnNames.put("orgKey", "ORGANIZATION_KEY");
			dbColumnNames.put("companyType", "COMPANY_TYPE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("companyStartDate", "COMPANY_START_DATE");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("companyStatus", "COMPANY_STATUS");
			dbColumnNames.put("companyEndDate", "COMPANY_END_DATE");
			dbColumnNames.put("city", "CITY");
			dbColumnNames.put("regionCode", "REGION_CODE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the company master in the entity cache if it is enabled.
	 *
	 * @param companyMaster the company master
	 */
	@Override
	public void cacheResult(CompanyMaster companyMaster) {
		entityCache.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterImpl.class, companyMaster.getPrimaryKey(),
			companyMaster);

		companyMaster.resetOriginalValues();
	}

	/**
	 * Caches the company masters in the entity cache if it is enabled.
	 *
	 * @param companyMasters the company masters
	 */
	@Override
	public void cacheResult(List<CompanyMaster> companyMasters) {
		for (CompanyMaster companyMaster : companyMasters) {
			if (entityCache.getResult(
						CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
						CompanyMasterImpl.class, companyMaster.getPrimaryKey()) == null) {
				cacheResult(companyMaster);
			}
			else {
				companyMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyMaster companyMaster) {
		entityCache.removeResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterImpl.class, companyMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CompanyMaster> companyMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyMaster companyMaster : companyMasters) {
			entityCache.removeResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
				CompanyMasterImpl.class, companyMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new company master with the primary key. Does not add the company master to the database.
	 *
	 * @param companyMasterSid the primary key for the new company master
	 * @return the new company master
	 */
	@Override
	public CompanyMaster create(int companyMasterSid) {
		CompanyMaster companyMaster = new CompanyMasterImpl();

		companyMaster.setNew(true);
		companyMaster.setPrimaryKey(companyMasterSid);

		return companyMaster;
	}

	/**
	 * Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyMasterSid the primary key of the company master
	 * @return the company master that was removed
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster remove(int companyMasterSid)
		throws NoSuchCompanyMasterException {
		return remove((Serializable)companyMasterSid);
	}

	/**
	 * Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company master
	 * @return the company master that was removed
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster remove(Serializable primaryKey)
		throws NoSuchCompanyMasterException {
		Session session = null;

		try {
			session = openSession();

			CompanyMaster companyMaster = (CompanyMaster)session.get(CompanyMasterImpl.class,
					primaryKey);

			if (companyMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyMaster);
		}
		catch (NoSuchCompanyMasterException nsee) {
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
	protected CompanyMaster removeImpl(CompanyMaster companyMaster) {
		companyMaster = toUnwrappedModel(companyMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyMaster)) {
				companyMaster = (CompanyMaster)session.get(CompanyMasterImpl.class,
						companyMaster.getPrimaryKeyObj());
			}

			if (companyMaster != null) {
				session.delete(companyMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyMaster != null) {
			clearCache(companyMaster);
		}

		return companyMaster;
	}

	@Override
	public CompanyMaster updateImpl(CompanyMaster companyMaster) {
		companyMaster = toUnwrappedModel(companyMaster);

		boolean isNew = companyMaster.isNew();

		CompanyMasterModelImpl companyMasterModelImpl = (CompanyMasterModelImpl)companyMaster;

		Session session = null;

		try {
			session = openSession();

			if (companyMaster.isNew()) {
				session.save(companyMaster);

				companyMaster.setNew(false);
			}
			else {
				companyMaster = (CompanyMaster)session.merge(companyMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CompanyMasterModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { companyMasterModelImpl.getCompanyNo() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYNO, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO,
				args);

			args = new Object[] { companyMasterModelImpl.getCompanyStringId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
				args);

			args = new Object[] { companyMasterModelImpl.getCompanyName() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYNAME, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME,
				args);

			args = new Object[] { companyMasterModelImpl.getCompanyType() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYTYPE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE,
				args);

			args = new Object[] { companyMasterModelImpl.getCompanyStatus() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSTATUS, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS,
				args);

			args = new Object[] { companyMasterModelImpl.getCompanyStringId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYUNIQUE, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((companyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyMasterModelImpl.getOriginalCompanyNo()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO,
					args);

				args = new Object[] { companyMasterModelImpl.getCompanyNo() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYNO, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNO,
					args);
			}

			if ((companyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyMasterModelImpl.getOriginalCompanyStringId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { companyMasterModelImpl.getCompanyStringId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((companyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyMasterModelImpl.getOriginalCompanyName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME,
					args);

				args = new Object[] { companyMasterModelImpl.getCompanyName() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYNAME, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYNAME,
					args);
			}

			if ((companyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyMasterModelImpl.getOriginalCompanyType()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE,
					args);

				args = new Object[] { companyMasterModelImpl.getCompanyType() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYTYPE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYTYPE,
					args);
			}

			if ((companyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyMasterModelImpl.getOriginalCompanyStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS,
					args);

				args = new Object[] { companyMasterModelImpl.getCompanyStatus() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYSTATUS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYSTATUS,
					args);
			}

			if ((companyMasterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyMasterModelImpl.getOriginalCompanyStringId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE,
					args);

				args = new Object[] { companyMasterModelImpl.getCompanyStringId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYUNIQUE,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYUNIQUE,
					args);
			}
		}

		entityCache.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
			CompanyMasterImpl.class, companyMaster.getPrimaryKey(),
			companyMaster, false);

		companyMaster.resetOriginalValues();

		return companyMaster;
	}

	protected CompanyMaster toUnwrappedModel(CompanyMaster companyMaster) {
		if (companyMaster instanceof CompanyMasterImpl) {
			return companyMaster;
		}

		CompanyMasterImpl companyMasterImpl = new CompanyMasterImpl();

		companyMasterImpl.setNew(companyMaster.isNew());
		companyMasterImpl.setPrimaryKey(companyMaster.getPrimaryKey());

		companyMasterImpl.setState(companyMaster.getState());
		companyMasterImpl.setFinancialSystem(companyMaster.getFinancialSystem());
		companyMasterImpl.setCompanyGroup(companyMaster.getCompanyGroup());
		companyMasterImpl.setCompanyName(companyMaster.getCompanyName());
		companyMasterImpl.setCompanyCategory(companyMaster.getCompanyCategory());
		companyMasterImpl.setLastUpdatedDate(companyMaster.getLastUpdatedDate());
		companyMasterImpl.setModifiedDate(companyMaster.getModifiedDate());
		companyMasterImpl.setLives(companyMaster.getLives());
		companyMasterImpl.setAddress2(companyMaster.getAddress2());
		companyMasterImpl.setCreatedDate(companyMaster.getCreatedDate());
		companyMasterImpl.setCreatedBy(companyMaster.getCreatedBy());
		companyMasterImpl.setSource(companyMaster.getSource());
		companyMasterImpl.setAddress1(companyMaster.getAddress1());
		companyMasterImpl.setModifiedBy(companyMaster.getModifiedBy());
		companyMasterImpl.setInboundStatus(companyMaster.getInboundStatus());
		companyMasterImpl.setCompanyMasterSid(companyMaster.getCompanyMasterSid());
		companyMasterImpl.setZipCode(companyMaster.getZipCode());
		companyMasterImpl.setCompanyStringId(companyMaster.getCompanyStringId());
		companyMasterImpl.setCountry(companyMaster.getCountry());
		companyMasterImpl.setInternalNotes(companyMaster.getInternalNotes());
		companyMasterImpl.setOrgKey(companyMaster.getOrgKey());
		companyMasterImpl.setCompanyType(companyMaster.getCompanyType());
		companyMasterImpl.setRecordLockStatus(companyMaster.isRecordLockStatus());
		companyMasterImpl.setCompanyStartDate(companyMaster.getCompanyStartDate());
		companyMasterImpl.setCompanyNo(companyMaster.getCompanyNo());
		companyMasterImpl.setBatchId(companyMaster.getBatchId());
		companyMasterImpl.setCompanyStatus(companyMaster.getCompanyStatus());
		companyMasterImpl.setCompanyEndDate(companyMaster.getCompanyEndDate());
		companyMasterImpl.setCity(companyMaster.getCity());
		companyMasterImpl.setRegionCode(companyMaster.getRegionCode());

		return companyMasterImpl;
	}

	/**
	 * Returns the company master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company master
	 * @return the company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyMasterException {
		CompanyMaster companyMaster = fetchByPrimaryKey(primaryKey);

		if (companyMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyMaster;
	}

	/**
	 * Returns the company master with the primary key or throws a {@link NoSuchCompanyMasterException} if it could not be found.
	 *
	 * @param companyMasterSid the primary key of the company master
	 * @return the company master
	 * @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster findByPrimaryKey(int companyMasterSid)
		throws NoSuchCompanyMasterException {
		return findByPrimaryKey((Serializable)companyMasterSid);
	}

	/**
	 * Returns the company master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company master
	 * @return the company master, or <code>null</code> if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
				CompanyMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyMaster companyMaster = (CompanyMaster)serializable;

		if (companyMaster == null) {
			Session session = null;

			try {
				session = openSession();

				companyMaster = (CompanyMaster)session.get(CompanyMasterImpl.class,
						primaryKey);

				if (companyMaster != null) {
					cacheResult(companyMaster);
				}
				else {
					entityCache.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
						CompanyMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					CompanyMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyMaster;
	}

	/**
	 * Returns the company master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyMasterSid the primary key of the company master
	 * @return the company master, or <code>null</code> if a company master with the primary key could not be found
	 */
	@Override
	public CompanyMaster fetchByPrimaryKey(int companyMasterSid) {
		return fetchByPrimaryKey((Serializable)companyMasterSid);
	}

	@Override
	public Map<Serializable, CompanyMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyMaster> map = new HashMap<Serializable, CompanyMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyMaster companyMaster = fetchByPrimaryKey(primaryKey);

			if (companyMaster != null) {
				map.put(primaryKey, companyMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					CompanyMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYMASTER_WHERE_PKS_IN);

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

			for (CompanyMaster companyMaster : (List<CompanyMaster>)q.list()) {
				map.put(companyMaster.getPrimaryKeyObj(), companyMaster);

				cacheResult(companyMaster);

				uncachedPrimaryKeys.remove(companyMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyMasterModelImpl.ENTITY_CACHE_ENABLED,
					CompanyMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the company masters.
	 *
	 * @return the company masters
	 */
	@Override
	public List<CompanyMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @return the range of company masters
	 */
	@Override
	public List<CompanyMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company masters
	 */
	@Override
	public List<CompanyMaster> findAll(int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company masters
	 * @param end the upper bound of the range of company masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company masters
	 */
	@Override
	public List<CompanyMaster> findAll(int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator,
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

		List<CompanyMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYMASTER;

				if (pagination) {
					sql = sql.concat(CompanyMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the company masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyMaster companyMaster : findAll()) {
			remove(companyMaster);
		}
	}

	/**
	 * Returns the number of company masters.
	 *
	 * @return the number of company masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYMASTER);

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
		return CompanyMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYMASTER = "SELECT companyMaster FROM CompanyMaster companyMaster";
	private static final String _SQL_SELECT_COMPANYMASTER_WHERE_PKS_IN = "SELECT companyMaster FROM CompanyMaster companyMaster WHERE COMPANY_MASTER_SID IN (";
	private static final String _SQL_SELECT_COMPANYMASTER_WHERE = "SELECT companyMaster FROM CompanyMaster companyMaster WHERE ";
	private static final String _SQL_COUNT_COMPANYMASTER = "SELECT COUNT(companyMaster) FROM CompanyMaster companyMaster";
	private static final String _SQL_COUNT_COMPANYMASTER_WHERE = "SELECT COUNT(companyMaster) FROM CompanyMaster companyMaster WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyMaster exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CompanyMaster exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CompanyMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"state", "financialSystem", "companyGroup", "companyName",
				"companyCategory", "lastUpdatedDate", "modifiedDate", "lives",
				"address2", "createdDate", "createdBy", "source", "address1",
				"modifiedBy", "inboundStatus", "companyMasterSid", "zipCode",
				"companyStringId", "country", "internalNotes", "orgKey",
				"companyType", "recordLockStatus", "companyStartDate",
				"companyNo", "batchId", "companyStatus", "companyEndDate",
				"city", "regionCode"
			});
}