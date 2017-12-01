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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchCompanyQualifierException;
import com.stpl.app.model.CompanyQualifier;
import com.stpl.app.model.impl.CompanyQualifierImpl;
import com.stpl.app.model.impl.CompanyQualifierModelImpl;
import com.stpl.app.service.persistence.CompanyQualifierPersistence;

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
 * The persistence implementation for the company qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyQualifierPersistence
 * @see com.stpl.app.service.persistence.CompanyQualifierUtil
 * @generated
 */
@ProviderType
public class CompanyQualifierPersistenceImpl extends BasePersistenceImpl<CompanyQualifier>
	implements CompanyQualifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyQualifierUtil} to access the company qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyQualifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyQualifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME =
		new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyQualifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyCrtQualifierName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME =
		new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyQualifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyCrtQualifierName",
			new String[] { String.class.getName() },
			CompanyQualifierModelImpl.COMPANYQUALIFIERNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME = new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyCrtQualifierName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the company qualifiers where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @return the matching company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findByCompanyCrtQualifierName(
		String companyQualifierName) {
		return findByCompanyCrtQualifierName(companyQualifierName,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company qualifiers where companyQualifierName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param start the lower bound of the range of company qualifiers
	 * @param end the upper bound of the range of company qualifiers (not inclusive)
	 * @return the range of matching company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findByCompanyCrtQualifierName(
		String companyQualifierName, int start, int end) {
		return findByCompanyCrtQualifierName(companyQualifierName, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the company qualifiers where companyQualifierName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param start the lower bound of the range of company qualifiers
	 * @param end the upper bound of the range of company qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findByCompanyCrtQualifierName(
		String companyQualifierName, int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return findByCompanyCrtQualifierName(companyQualifierName, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company qualifiers where companyQualifierName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param start the lower bound of the range of company qualifiers
	 * @param end the upper bound of the range of company qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findByCompanyCrtQualifierName(
		String companyQualifierName, int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME;
			finderArgs = new Object[] { companyQualifierName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME;
			finderArgs = new Object[] {
					companyQualifierName,
					
					start, end, orderByComparator
				};
		}

		List<CompanyQualifier> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyQualifier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyQualifier companyQualifier : list) {
					if (!Objects.equals(companyQualifierName,
								companyQualifier.getCompanyQualifierName())) {
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

			query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE);

			boolean bindCompanyQualifierName = false;

			if (companyQualifierName == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1);
			}
			else if (companyQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3);
			}
			else {
				bindCompanyQualifierName = true;

				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyQualifierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyQualifierName) {
					qPos.add(companyQualifierName);
				}

				if (!pagination) {
					list = (List<CompanyQualifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyQualifier>)QueryUtil.list(q,
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
	 * Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company qualifier
	 * @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier findByCompanyCrtQualifierName_First(
		String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator)
		throws NoSuchCompanyQualifierException {
		CompanyQualifier companyQualifier = fetchByCompanyCrtQualifierName_First(companyQualifierName,
				orderByComparator);

		if (companyQualifier != null) {
			return companyQualifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyQualifierName=");
		msg.append(companyQualifierName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyQualifierException(msg.toString());
	}

	/**
	 * Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier fetchByCompanyCrtQualifierName_First(
		String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		List<CompanyQualifier> list = findByCompanyCrtQualifierName(companyQualifierName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company qualifier
	 * @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier findByCompanyCrtQualifierName_Last(
		String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator)
		throws NoSuchCompanyQualifierException {
		CompanyQualifier companyQualifier = fetchByCompanyCrtQualifierName_Last(companyQualifierName,
				orderByComparator);

		if (companyQualifier != null) {
			return companyQualifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyQualifierName=");
		msg.append(companyQualifierName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyQualifierException(msg.toString());
	}

	/**
	 * Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier fetchByCompanyCrtQualifierName_Last(
		String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		int count = countByCompanyCrtQualifierName(companyQualifierName);

		if (count == 0) {
			return null;
		}

		List<CompanyQualifier> list = findByCompanyCrtQualifierName(companyQualifierName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company qualifiers before and after the current company qualifier in the ordered set where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierSid the primary key of the current company qualifier
	 * @param companyQualifierName the company qualifier name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company qualifier
	 * @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier[] findByCompanyCrtQualifierName_PrevAndNext(
		int companyQualifierSid, String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator)
		throws NoSuchCompanyQualifierException {
		CompanyQualifier companyQualifier = findByPrimaryKey(companyQualifierSid);

		Session session = null;

		try {
			session = openSession();

			CompanyQualifier[] array = new CompanyQualifierImpl[3];

			array[0] = getByCompanyCrtQualifierName_PrevAndNext(session,
					companyQualifier, companyQualifierName, orderByComparator,
					true);

			array[1] = companyQualifier;

			array[2] = getByCompanyCrtQualifierName_PrevAndNext(session,
					companyQualifier, companyQualifierName, orderByComparator,
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

	protected CompanyQualifier getByCompanyCrtQualifierName_PrevAndNext(
		Session session, CompanyQualifier companyQualifier,
		String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE);

		boolean bindCompanyQualifierName = false;

		if (companyQualifierName == null) {
			query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1);
		}
		else if (companyQualifierName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3);
		}
		else {
			bindCompanyQualifierName = true;

			query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2);
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
			query.append(CompanyQualifierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCompanyQualifierName) {
			qPos.add(companyQualifierName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyQualifier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyQualifier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company qualifiers where companyQualifierName = &#63; from the database.
	 *
	 * @param companyQualifierName the company qualifier name
	 */
	@Override
	public void removeByCompanyCrtQualifierName(String companyQualifierName) {
		for (CompanyQualifier companyQualifier : findByCompanyCrtQualifierName(
				companyQualifierName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyQualifier);
		}
	}

	/**
	 * Returns the number of company qualifiers where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @return the number of matching company qualifiers
	 */
	@Override
	public int countByCompanyCrtQualifierName(String companyQualifierName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME;

		Object[] finderArgs = new Object[] { companyQualifierName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYQUALIFIER_WHERE);

			boolean bindCompanyQualifierName = false;

			if (companyQualifierName == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1);
			}
			else if (companyQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3);
			}
			else {
				bindCompanyQualifierName = true;

				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyQualifierName) {
					qPos.add(companyQualifierName);
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

	private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_1 =
		"companyQualifier.companyQualifierName IS NULL";
	private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_2 =
		"companyQualifier.companyQualifierName = ?";
	private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERNAME_COMPANYQUALIFIERNAME_3 =
		"(companyQualifier.companyQualifierName IS NULL OR companyQualifier.companyQualifierName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME =
		new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyQualifierImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByCompanyCrtQualifierByName",
			new String[] { String.class.getName() },
			CompanyQualifierModelImpl.COMPANYQUALIFIERNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME =
		new FinderPath(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyCrtQualifierByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the company qualifier where companyQualifierName = &#63; or throws a {@link NoSuchCompanyQualifierException} if it could not be found.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @return the matching company qualifier
	 * @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier findByCompanyCrtQualifierByName(
		String companyQualifierName) throws NoSuchCompanyQualifierException {
		CompanyQualifier companyQualifier = fetchByCompanyCrtQualifierByName(companyQualifierName);

		if (companyQualifier == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyQualifierName=");
			msg.append(companyQualifierName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCompanyQualifierException(msg.toString());
		}

		return companyQualifier;
	}

	/**
	 * Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier fetchByCompanyCrtQualifierByName(
		String companyQualifierName) {
		return fetchByCompanyCrtQualifierByName(companyQualifierName, true);
	}

	/**
	 * Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	 */
	@Override
	public CompanyQualifier fetchByCompanyCrtQualifierByName(
		String companyQualifierName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyQualifierName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
					finderArgs, this);
		}

		if (result instanceof CompanyQualifier) {
			CompanyQualifier companyQualifier = (CompanyQualifier)result;

			if (!Objects.equals(companyQualifierName,
						companyQualifier.getCompanyQualifierName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE);

			boolean bindCompanyQualifierName = false;

			if (companyQualifierName == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_1);
			}
			else if (companyQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_3);
			}
			else {
				bindCompanyQualifierName = true;

				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyQualifierName) {
					qPos.add(companyQualifierName);
				}

				List<CompanyQualifier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CompanyQualifierPersistenceImpl.fetchByCompanyCrtQualifierByName(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CompanyQualifier companyQualifier = list.get(0);

					result = companyQualifier;

					cacheResult(companyQualifier);

					if ((companyQualifier.getCompanyQualifierName() == null) ||
							!companyQualifier.getCompanyQualifierName()
												 .equals(companyQualifierName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
							finderArgs, companyQualifier);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CompanyQualifier)result;
		}
	}

	/**
	 * Removes the company qualifier where companyQualifierName = &#63; from the database.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @return the company qualifier that was removed
	 */
	@Override
	public CompanyQualifier removeByCompanyCrtQualifierByName(
		String companyQualifierName) throws NoSuchCompanyQualifierException {
		CompanyQualifier companyQualifier = findByCompanyCrtQualifierByName(companyQualifierName);

		return remove(companyQualifier);
	}

	/**
	 * Returns the number of company qualifiers where companyQualifierName = &#63;.
	 *
	 * @param companyQualifierName the company qualifier name
	 * @return the number of matching company qualifiers
	 */
	@Override
	public int countByCompanyCrtQualifierByName(String companyQualifierName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME;

		Object[] finderArgs = new Object[] { companyQualifierName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYQUALIFIER_WHERE);

			boolean bindCompanyQualifierName = false;

			if (companyQualifierName == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_1);
			}
			else if (companyQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_3);
			}
			else {
				bindCompanyQualifierName = true;

				query.append(_FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyQualifierName) {
					qPos.add(companyQualifierName);
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

	private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_1 =
		"companyQualifier.companyQualifierName IS NULL";
	private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_2 =
		"companyQualifier.companyQualifierName = ?";
	private static final String _FINDER_COLUMN_COMPANYCRTQUALIFIERBYNAME_COMPANYQUALIFIERNAME_3 =
		"(companyQualifier.companyQualifierName IS NULL OR companyQualifier.companyQualifierName = '')";

	public CompanyQualifierPersistenceImpl() {
		setModelClass(CompanyQualifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("companyQualifierValue", "COMPANY_QUALIFIER_VALUE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("companyQualifierSid", "COMPANY_QUALIFIER_SID");
			dbColumnNames.put("companyQualifierName", "COMPANY_QUALIFIER_NAME");
			dbColumnNames.put("effectiveDates", "EFFECTIVE_DATES");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the company qualifier in the entity cache if it is enabled.
	 *
	 * @param companyQualifier the company qualifier
	 */
	@Override
	public void cacheResult(CompanyQualifier companyQualifier) {
		entityCache.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierImpl.class, companyQualifier.getPrimaryKey(),
			companyQualifier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
			new Object[] { companyQualifier.getCompanyQualifierName() },
			companyQualifier);

		companyQualifier.resetOriginalValues();
	}

	/**
	 * Caches the company qualifiers in the entity cache if it is enabled.
	 *
	 * @param companyQualifiers the company qualifiers
	 */
	@Override
	public void cacheResult(List<CompanyQualifier> companyQualifiers) {
		for (CompanyQualifier companyQualifier : companyQualifiers) {
			if (entityCache.getResult(
						CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
						CompanyQualifierImpl.class,
						companyQualifier.getPrimaryKey()) == null) {
				cacheResult(companyQualifier);
			}
			else {
				companyQualifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company qualifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyQualifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company qualifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyQualifier companyQualifier) {
		entityCache.removeResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierImpl.class, companyQualifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CompanyQualifierModelImpl)companyQualifier,
			true);
	}

	@Override
	public void clearCache(List<CompanyQualifier> companyQualifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyQualifier companyQualifier : companyQualifiers) {
			entityCache.removeResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
				CompanyQualifierImpl.class, companyQualifier.getPrimaryKey());

			clearUniqueFindersCache((CompanyQualifierModelImpl)companyQualifier,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		CompanyQualifierModelImpl companyQualifierModelImpl) {
		Object[] args = new Object[] {
				companyQualifierModelImpl.getCompanyQualifierName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
			args, companyQualifierModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CompanyQualifierModelImpl companyQualifierModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					companyQualifierModelImpl.getCompanyQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
				args);
		}

		if ((companyQualifierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					companyQualifierModelImpl.getOriginalCompanyQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERBYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_COMPANYCRTQUALIFIERBYNAME,
				args);
		}
	}

	/**
	 * Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
	 *
	 * @param companyQualifierSid the primary key for the new company qualifier
	 * @return the new company qualifier
	 */
	@Override
	public CompanyQualifier create(int companyQualifierSid) {
		CompanyQualifier companyQualifier = new CompanyQualifierImpl();

		companyQualifier.setNew(true);
		companyQualifier.setPrimaryKey(companyQualifierSid);

		return companyQualifier;
	}

	/**
	 * Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyQualifierSid the primary key of the company qualifier
	 * @return the company qualifier that was removed
	 * @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier remove(int companyQualifierSid)
		throws NoSuchCompanyQualifierException {
		return remove((Serializable)companyQualifierSid);
	}

	/**
	 * Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company qualifier
	 * @return the company qualifier that was removed
	 * @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier remove(Serializable primaryKey)
		throws NoSuchCompanyQualifierException {
		Session session = null;

		try {
			session = openSession();

			CompanyQualifier companyQualifier = (CompanyQualifier)session.get(CompanyQualifierImpl.class,
					primaryKey);

			if (companyQualifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyQualifier);
		}
		catch (NoSuchCompanyQualifierException nsee) {
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
	protected CompanyQualifier removeImpl(CompanyQualifier companyQualifier) {
		companyQualifier = toUnwrappedModel(companyQualifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyQualifier)) {
				companyQualifier = (CompanyQualifier)session.get(CompanyQualifierImpl.class,
						companyQualifier.getPrimaryKeyObj());
			}

			if (companyQualifier != null) {
				session.delete(companyQualifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyQualifier != null) {
			clearCache(companyQualifier);
		}

		return companyQualifier;
	}

	@Override
	public CompanyQualifier updateImpl(CompanyQualifier companyQualifier) {
		companyQualifier = toUnwrappedModel(companyQualifier);

		boolean isNew = companyQualifier.isNew();

		CompanyQualifierModelImpl companyQualifierModelImpl = (CompanyQualifierModelImpl)companyQualifier;

		Session session = null;

		try {
			session = openSession();

			if (companyQualifier.isNew()) {
				session.save(companyQualifier);

				companyQualifier.setNew(false);
			}
			else {
				companyQualifier = (CompanyQualifier)session.merge(companyQualifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CompanyQualifierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					companyQualifierModelImpl.getCompanyQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((companyQualifierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyQualifierModelImpl.getOriginalCompanyQualifierName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME,
					args);

				args = new Object[] {
						companyQualifierModelImpl.getCompanyQualifierName()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTQUALIFIERNAME,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTQUALIFIERNAME,
					args);
			}
		}

		entityCache.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyQualifierImpl.class, companyQualifier.getPrimaryKey(),
			companyQualifier, false);

		clearUniqueFindersCache(companyQualifierModelImpl, false);
		cacheUniqueFindersCache(companyQualifierModelImpl);

		companyQualifier.resetOriginalValues();

		return companyQualifier;
	}

	protected CompanyQualifier toUnwrappedModel(
		CompanyQualifier companyQualifier) {
		if (companyQualifier instanceof CompanyQualifierImpl) {
			return companyQualifier;
		}

		CompanyQualifierImpl companyQualifierImpl = new CompanyQualifierImpl();

		companyQualifierImpl.setNew(companyQualifier.isNew());
		companyQualifierImpl.setPrimaryKey(companyQualifier.getPrimaryKey());

		companyQualifierImpl.setRecordLockStatus(companyQualifier.isRecordLockStatus());
		companyQualifierImpl.setCreatedDate(companyQualifier.getCreatedDate());
		companyQualifierImpl.setCreatedBy(companyQualifier.getCreatedBy());
		companyQualifierImpl.setSource(companyQualifier.getSource());
		companyQualifierImpl.setCompanyQualifierValue(companyQualifier.getCompanyQualifierValue());
		companyQualifierImpl.setBatchId(companyQualifier.getBatchId());
		companyQualifierImpl.setCompanyQualifierSid(companyQualifier.getCompanyQualifierSid());
		companyQualifierImpl.setCompanyQualifierName(companyQualifier.getCompanyQualifierName());
		companyQualifierImpl.setEffectiveDates(companyQualifier.getEffectiveDates());
		companyQualifierImpl.setNotes(companyQualifier.getNotes());
		companyQualifierImpl.setModifiedBy(companyQualifier.getModifiedBy());
		companyQualifierImpl.setInboundStatus(companyQualifier.getInboundStatus());
		companyQualifierImpl.setModifiedDate(companyQualifier.getModifiedDate());

		return companyQualifierImpl;
	}

	/**
	 * Returns the company qualifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company qualifier
	 * @return the company qualifier
	 * @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyQualifierException {
		CompanyQualifier companyQualifier = fetchByPrimaryKey(primaryKey);

		if (companyQualifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyQualifier;
	}

	/**
	 * Returns the company qualifier with the primary key or throws a {@link NoSuchCompanyQualifierException} if it could not be found.
	 *
	 * @param companyQualifierSid the primary key of the company qualifier
	 * @return the company qualifier
	 * @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier findByPrimaryKey(int companyQualifierSid)
		throws NoSuchCompanyQualifierException {
		return findByPrimaryKey((Serializable)companyQualifierSid);
	}

	/**
	 * Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company qualifier
	 * @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
				CompanyQualifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyQualifier companyQualifier = (CompanyQualifier)serializable;

		if (companyQualifier == null) {
			Session session = null;

			try {
				session = openSession();

				companyQualifier = (CompanyQualifier)session.get(CompanyQualifierImpl.class,
						primaryKey);

				if (companyQualifier != null) {
					cacheResult(companyQualifier);
				}
				else {
					entityCache.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
						CompanyQualifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
					CompanyQualifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyQualifier;
	}

	/**
	 * Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyQualifierSid the primary key of the company qualifier
	 * @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
	 */
	@Override
	public CompanyQualifier fetchByPrimaryKey(int companyQualifierSid) {
		return fetchByPrimaryKey((Serializable)companyQualifierSid);
	}

	@Override
	public Map<Serializable, CompanyQualifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyQualifier> map = new HashMap<Serializable, CompanyQualifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyQualifier companyQualifier = fetchByPrimaryKey(primaryKey);

			if (companyQualifier != null) {
				map.put(primaryKey, companyQualifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
					CompanyQualifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyQualifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYQUALIFIER_WHERE_PKS_IN);

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

			for (CompanyQualifier companyQualifier : (List<CompanyQualifier>)q.list()) {
				map.put(companyQualifier.getPrimaryKeyObj(), companyQualifier);

				cacheResult(companyQualifier);

				uncachedPrimaryKeys.remove(companyQualifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyQualifierModelImpl.ENTITY_CACHE_ENABLED,
					CompanyQualifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the company qualifiers.
	 *
	 * @return the company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company qualifiers
	 * @param end the upper bound of the range of company qualifiers (not inclusive)
	 * @return the range of company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company qualifiers
	 * @param end the upper bound of the range of company qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findAll(int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company qualifiers
	 * @param end the upper bound of the range of company qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company qualifiers
	 */
	@Override
	public List<CompanyQualifier> findAll(int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator,
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

		List<CompanyQualifier> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyQualifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYQUALIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYQUALIFIER;

				if (pagination) {
					sql = sql.concat(CompanyQualifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyQualifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyQualifier>)QueryUtil.list(q,
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
	 * Removes all the company qualifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyQualifier companyQualifier : findAll()) {
			remove(companyQualifier);
		}
	}

	/**
	 * Returns the number of company qualifiers.
	 *
	 * @return the number of company qualifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYQUALIFIER);

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
		return CompanyQualifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company qualifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyQualifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYQUALIFIER = "SELECT companyQualifier FROM CompanyQualifier companyQualifier";
	private static final String _SQL_SELECT_COMPANYQUALIFIER_WHERE_PKS_IN = "SELECT companyQualifier FROM CompanyQualifier companyQualifier WHERE COMPANY_QUALIFIER_SID IN (";
	private static final String _SQL_SELECT_COMPANYQUALIFIER_WHERE = "SELECT companyQualifier FROM CompanyQualifier companyQualifier WHERE ";
	private static final String _SQL_COUNT_COMPANYQUALIFIER = "SELECT COUNT(companyQualifier) FROM CompanyQualifier companyQualifier";
	private static final String _SQL_COUNT_COMPANYQUALIFIER_WHERE = "SELECT COUNT(companyQualifier) FROM CompanyQualifier companyQualifier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyQualifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyQualifier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CompanyQualifier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CompanyQualifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"recordLockStatus", "createdDate", "createdBy", "source",
				"companyQualifierValue", "batchId", "companyQualifierSid",
				"companyQualifierName", "effectiveDates", "notes", "modifiedBy",
				"inboundStatus", "modifiedDate"
			});
}