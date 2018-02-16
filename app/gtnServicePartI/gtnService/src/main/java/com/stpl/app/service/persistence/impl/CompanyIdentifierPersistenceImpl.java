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

import com.stpl.app.exception.NoSuchCompanyIdentifierException;
import com.stpl.app.model.CompanyIdentifier;
import com.stpl.app.model.impl.CompanyIdentifierImpl;
import com.stpl.app.model.impl.CompanyIdentifierModelImpl;
import com.stpl.app.service.persistence.CompanyIdentifierPersistence;

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
 * The persistence implementation for the company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyIdentifierPersistence
 * @see com.stpl.app.service.persistence.CompanyIdentifierUtil
 * @generated
 */
@ProviderType
public class CompanyIdentifierPersistenceImpl extends BasePersistenceImpl<CompanyIdentifier>
	implements CompanyIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CompanyIdentifierUtil} to access the company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CompanyIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER =
		new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyCrtIdentifier",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER =
		new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyCrtIdentifier",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Date.class.getName()
			},
			CompanyIdentifierModelImpl.COMPANYSTRINGIDENTIFIERVALUE_COLUMN_BITMASK |
			CompanyIdentifierModelImpl.COMPANYQUALIFIERSID_COLUMN_BITMASK |
			CompanyIdentifierModelImpl.IDENTIFIERSTATUS_COLUMN_BITMASK |
			CompanyIdentifierModelImpl.STARTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER = new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyCrtIdentifier",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @return the matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifier(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate) {
		return findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @return the range of matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifier(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end) {
		return findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifier(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifier(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER;
			finderArgs = new Object[] {
					companyStringIdentifierValue, companyQualifierSid,
					identifierStatus, startDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER;
			finderArgs = new Object[] {
					companyStringIdentifierValue, companyQualifierSid,
					identifierStatus, startDate,
					
					start, end, orderByComparator
				};
		}

		List<CompanyIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyIdentifier companyIdentifier : list) {
					if (!Objects.equals(companyStringIdentifierValue,
								companyIdentifier.getCompanyStringIdentifierValue()) ||
							(companyQualifierSid != companyIdentifier.getCompanyQualifierSid()) ||
							(identifierStatus != companyIdentifier.getIdentifierStatus()) ||
							!Objects.equals(startDate,
								companyIdentifier.getStartDate())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

			boolean bindCompanyStringIdentifierValue = false;

			if (companyStringIdentifierValue == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_1);
			}
			else if (companyStringIdentifierValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_3);
			}
			else {
				bindCompanyStringIdentifierValue = true;

				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_2);
			}

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2);

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyStringIdentifierValue) {
					qPos.add(companyStringIdentifierValue);
				}

				qPos.add(companyQualifierSid);

				qPos.add(identifierStatus);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
				}

				if (!pagination) {
					list = (List<CompanyIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyIdentifier>)QueryUtil.list(q,
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
	 * Returns the first company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company identifier
	 * @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier findByCompanyCrtIdentifier_First(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifier_First(companyStringIdentifierValue,
				companyQualifierSid, identifierStatus, startDate,
				orderByComparator);

		if (companyIdentifier != null) {
			return companyIdentifier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringIdentifierValue=");
		msg.append(companyStringIdentifierValue);

		msg.append(", companyQualifierSid=");
		msg.append(companyQualifierSid);

		msg.append(", identifierStatus=");
		msg.append(identifierStatus);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyIdentifierException(msg.toString());
	}

	/**
	 * Returns the first company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier fetchByCompanyCrtIdentifier_First(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		List<CompanyIdentifier> list = findByCompanyCrtIdentifier(companyStringIdentifierValue,
				companyQualifierSid, identifierStatus, startDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company identifier
	 * @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier findByCompanyCrtIdentifier_Last(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifier_Last(companyStringIdentifierValue,
				companyQualifierSid, identifierStatus, startDate,
				orderByComparator);

		if (companyIdentifier != null) {
			return companyIdentifier;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyStringIdentifierValue=");
		msg.append(companyStringIdentifierValue);

		msg.append(", companyQualifierSid=");
		msg.append(companyQualifierSid);

		msg.append(", identifierStatus=");
		msg.append(identifierStatus);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyIdentifierException(msg.toString());
	}

	/**
	 * Returns the last company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier fetchByCompanyCrtIdentifier_Last(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		int count = countByCompanyCrtIdentifier(companyStringIdentifierValue,
				companyQualifierSid, identifierStatus, startDate);

		if (count == 0) {
			return null;
		}

		List<CompanyIdentifier> list = findByCompanyCrtIdentifier(companyStringIdentifierValue,
				companyQualifierSid, identifierStatus, startDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company identifiers before and after the current company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierSid the primary key of the current company identifier
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company identifier
	 * @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier[] findByCompanyCrtIdentifier_PrevAndNext(
		int companyStringIdentifierSid, String companyStringIdentifierValue,
		int companyQualifierSid, int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = findByPrimaryKey(companyStringIdentifierSid);

		Session session = null;

		try {
			session = openSession();

			CompanyIdentifier[] array = new CompanyIdentifierImpl[3];

			array[0] = getByCompanyCrtIdentifier_PrevAndNext(session,
					companyIdentifier, companyStringIdentifierValue,
					companyQualifierSid, identifierStatus, startDate,
					orderByComparator, true);

			array[1] = companyIdentifier;

			array[2] = getByCompanyCrtIdentifier_PrevAndNext(session,
					companyIdentifier, companyStringIdentifierValue,
					companyQualifierSid, identifierStatus, startDate,
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

	protected CompanyIdentifier getByCompanyCrtIdentifier_PrevAndNext(
		Session session, CompanyIdentifier companyIdentifier,
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(7 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

		boolean bindCompanyStringIdentifierValue = false;

		if (companyStringIdentifierValue == null) {
			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_1);
		}
		else if (companyStringIdentifierValue.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_3);
		}
		else {
			bindCompanyStringIdentifierValue = true;

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_2);
		}

		query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2);

		query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2);

		boolean bindStartDate = false;

		if (startDate == null) {
			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1);
		}
		else {
			bindStartDate = true;

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2);
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
			query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCompanyStringIdentifierValue) {
			qPos.add(companyStringIdentifierValue);
		}

		qPos.add(companyQualifierSid);

		qPos.add(identifierStatus);

		if (bindStartDate) {
			qPos.add(new Timestamp(startDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyIdentifier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyIdentifier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 */
	@Override
	public void removeByCompanyCrtIdentifier(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate) {
		for (CompanyIdentifier companyIdentifier : findByCompanyCrtIdentifier(
				companyStringIdentifierValue, companyQualifierSid,
				identifierStatus, startDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(companyIdentifier);
		}
	}

	/**
	 * Returns the number of company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	 *
	 * @param companyStringIdentifierValue the company string identifier value
	 * @param companyQualifierSid the company qualifier sid
	 * @param identifierStatus the identifier status
	 * @param startDate the start date
	 * @return the number of matching company identifiers
	 */
	@Override
	public int countByCompanyCrtIdentifier(
		String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER;

		Object[] finderArgs = new Object[] {
				companyStringIdentifierValue, companyQualifierSid,
				identifierStatus, startDate
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_COMPANYIDENTIFIER_WHERE);

			boolean bindCompanyStringIdentifierValue = false;

			if (companyStringIdentifierValue == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_1);
			}
			else if (companyStringIdentifierValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_3);
			}
			else {
				bindCompanyStringIdentifierValue = true;

				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_2);
			}

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2);

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCompanyStringIdentifierValue) {
					qPos.add(companyStringIdentifierValue);
				}

				qPos.add(companyQualifierSid);

				qPos.add(identifierStatus);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
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

	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_1 =
		"companyIdentifier.companyStringIdentifierValue IS NULL AND ";
	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_2 =
		"companyIdentifier.companyStringIdentifierValue = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYSTRINGIDENTIFIERVALUE_3 =
		"(companyIdentifier.companyStringIdentifierValue IS NULL OR companyIdentifier.companyStringIdentifierValue = '') AND ";
	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_COMPANYQUALIFIERSID_2 =
		"companyIdentifier.companyQualifierSid = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_IDENTIFIERSTATUS_2 =
		"companyIdentifier.identifierStatus = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_1 = "companyIdentifier.startDate IS NULL";
	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIER_STARTDATE_2 = "companyIdentifier.startDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS =
		new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyCrtIdentifierDetails",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS =
		new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			CompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyCrtIdentifierDetails",
			new String[] { Integer.class.getName() },
			CompanyIdentifierModelImpl.COMPANYMASTERSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS =
		new FinderPath(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyCrtIdentifierDetails",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the company identifiers where companyMasterSid = &#63;.
	 *
	 * @param companyMasterSid the company master sid
	 * @return the matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid) {
		return findByCompanyCrtIdentifierDetails(companyMasterSid,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company identifiers where companyMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyMasterSid the company master sid
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @return the range of matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end) {
		return findByCompanyCrtIdentifierDetails(companyMasterSid, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the company identifiers where companyMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyMasterSid the company master sid
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return findByCompanyCrtIdentifierDetails(companyMasterSid, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company identifiers where companyMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyMasterSid the company master sid
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS;
			finderArgs = new Object[] { companyMasterSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS;
			finderArgs = new Object[] {
					companyMasterSid,
					
					start, end, orderByComparator
				};
		}

		List<CompanyIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CompanyIdentifier companyIdentifier : list) {
					if ((companyMasterSid != companyIdentifier.getCompanyMasterSid())) {
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

			query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyMasterSid);

				if (!pagination) {
					list = (List<CompanyIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyIdentifier>)QueryUtil.list(q,
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
	 * Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
	 *
	 * @param companyMasterSid the company master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company identifier
	 * @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier findByCompanyCrtIdentifierDetails_First(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifierDetails_First(companyMasterSid,
				orderByComparator);

		if (companyIdentifier != null) {
			return companyIdentifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyMasterSid=");
		msg.append(companyMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyIdentifierException(msg.toString());
	}

	/**
	 * Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
	 *
	 * @param companyMasterSid the company master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier fetchByCompanyCrtIdentifierDetails_First(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		List<CompanyIdentifier> list = findByCompanyCrtIdentifierDetails(companyMasterSid,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
	 *
	 * @param companyMasterSid the company master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company identifier
	 * @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier findByCompanyCrtIdentifierDetails_Last(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = fetchByCompanyCrtIdentifierDetails_Last(companyMasterSid,
				orderByComparator);

		if (companyIdentifier != null) {
			return companyIdentifier;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyMasterSid=");
		msg.append(companyMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCompanyIdentifierException(msg.toString());
	}

	/**
	 * Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
	 *
	 * @param companyMasterSid the company master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
	 */
	@Override
	public CompanyIdentifier fetchByCompanyCrtIdentifierDetails_Last(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		int count = countByCompanyCrtIdentifierDetails(companyMasterSid);

		if (count == 0) {
			return null;
		}

		List<CompanyIdentifier> list = findByCompanyCrtIdentifierDetails(companyMasterSid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the company identifiers before and after the current company identifier in the ordered set where companyMasterSid = &#63;.
	 *
	 * @param companyStringIdentifierSid the primary key of the current company identifier
	 * @param companyMasterSid the company master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next company identifier
	 * @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier[] findByCompanyCrtIdentifierDetails_PrevAndNext(
		int companyStringIdentifierSid, int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = findByPrimaryKey(companyStringIdentifierSid);

		Session session = null;

		try {
			session = openSession();

			CompanyIdentifier[] array = new CompanyIdentifierImpl[3];

			array[0] = getByCompanyCrtIdentifierDetails_PrevAndNext(session,
					companyIdentifier, companyMasterSid, orderByComparator, true);

			array[1] = companyIdentifier;

			array[2] = getByCompanyCrtIdentifierDetails_PrevAndNext(session,
					companyIdentifier, companyMasterSid, orderByComparator,
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

	protected CompanyIdentifier getByCompanyCrtIdentifierDetails_PrevAndNext(
		Session session, CompanyIdentifier companyIdentifier,
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE);

		query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2);

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
			query.append(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyMasterSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(companyIdentifier);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CompanyIdentifier> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the company identifiers where companyMasterSid = &#63; from the database.
	 *
	 * @param companyMasterSid the company master sid
	 */
	@Override
	public void removeByCompanyCrtIdentifierDetails(int companyMasterSid) {
		for (CompanyIdentifier companyIdentifier : findByCompanyCrtIdentifierDetails(
				companyMasterSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(companyIdentifier);
		}
	}

	/**
	 * Returns the number of company identifiers where companyMasterSid = &#63;.
	 *
	 * @param companyMasterSid the company master sid
	 * @return the number of matching company identifiers
	 */
	@Override
	public int countByCompanyCrtIdentifierDetails(int companyMasterSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS;

		Object[] finderArgs = new Object[] { companyMasterSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COMPANYIDENTIFIER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyMasterSid);

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

	private static final String _FINDER_COLUMN_COMPANYCRTIDENTIFIERDETAILS_COMPANYMASTERSID_2 =
		"companyIdentifier.companyMasterSid = ?";

	public CompanyIdentifierPersistenceImpl() {
		setModelClass(CompanyIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("companyStringIdentifierSid",
				"COMPANY_IDENTIFIER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("identifierStatus", "IDENTIFIER_STATUS");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyStringIdentifierValue",
				"COMPANY_IDENTIFIER_VALUE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("companyQualifierSid", "COMPANY_QUALIFIER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the company identifier in the entity cache if it is enabled.
	 *
	 * @param companyIdentifier the company identifier
	 */
	@Override
	public void cacheResult(CompanyIdentifier companyIdentifier) {
		entityCache.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey(),
			companyIdentifier);

		companyIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the company identifiers in the entity cache if it is enabled.
	 *
	 * @param companyIdentifiers the company identifiers
	 */
	@Override
	public void cacheResult(List<CompanyIdentifier> companyIdentifiers) {
		for (CompanyIdentifier companyIdentifier : companyIdentifiers) {
			if (entityCache.getResult(
						CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						CompanyIdentifierImpl.class,
						companyIdentifier.getPrimaryKey()) == null) {
				cacheResult(companyIdentifier);
			}
			else {
				companyIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all company identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CompanyIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the company identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CompanyIdentifier companyIdentifier) {
		entityCache.removeResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CompanyIdentifier> companyIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CompanyIdentifier companyIdentifier : companyIdentifiers) {
			entityCache.removeResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new company identifier with the primary key. Does not add the company identifier to the database.
	 *
	 * @param companyStringIdentifierSid the primary key for the new company identifier
	 * @return the new company identifier
	 */
	@Override
	public CompanyIdentifier create(int companyStringIdentifierSid) {
		CompanyIdentifier companyIdentifier = new CompanyIdentifierImpl();

		companyIdentifier.setNew(true);
		companyIdentifier.setPrimaryKey(companyStringIdentifierSid);

		return companyIdentifier;
	}

	/**
	 * Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param companyStringIdentifierSid the primary key of the company identifier
	 * @return the company identifier that was removed
	 * @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier remove(int companyStringIdentifierSid)
		throws NoSuchCompanyIdentifierException {
		return remove((Serializable)companyStringIdentifierSid);
	}

	/**
	 * Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the company identifier
	 * @return the company identifier that was removed
	 * @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier remove(Serializable primaryKey)
		throws NoSuchCompanyIdentifierException {
		Session session = null;

		try {
			session = openSession();

			CompanyIdentifier companyIdentifier = (CompanyIdentifier)session.get(CompanyIdentifierImpl.class,
					primaryKey);

			if (companyIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(companyIdentifier);
		}
		catch (NoSuchCompanyIdentifierException nsee) {
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
	protected CompanyIdentifier removeImpl(CompanyIdentifier companyIdentifier) {
		companyIdentifier = toUnwrappedModel(companyIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(companyIdentifier)) {
				companyIdentifier = (CompanyIdentifier)session.get(CompanyIdentifierImpl.class,
						companyIdentifier.getPrimaryKeyObj());
			}

			if (companyIdentifier != null) {
				session.delete(companyIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (companyIdentifier != null) {
			clearCache(companyIdentifier);
		}

		return companyIdentifier;
	}

	@Override
	public CompanyIdentifier updateImpl(CompanyIdentifier companyIdentifier) {
		companyIdentifier = toUnwrappedModel(companyIdentifier);

		boolean isNew = companyIdentifier.isNew();

		CompanyIdentifierModelImpl companyIdentifierModelImpl = (CompanyIdentifierModelImpl)companyIdentifier;

		Session session = null;

		try {
			session = openSession();

			if (companyIdentifier.isNew()) {
				session.save(companyIdentifier);

				companyIdentifier.setNew(false);
			}
			else {
				companyIdentifier = (CompanyIdentifier)session.merge(companyIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CompanyIdentifierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					companyIdentifierModelImpl.getCompanyStringIdentifierValue(),
					companyIdentifierModelImpl.getCompanyQualifierSid(),
					companyIdentifierModelImpl.getIdentifierStatus(),
					companyIdentifierModelImpl.getStartDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER,
				args);

			args = new Object[] { companyIdentifierModelImpl.getCompanyMasterSid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((companyIdentifierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyIdentifierModelImpl.getOriginalCompanyStringIdentifierValue(),
						companyIdentifierModelImpl.getOriginalCompanyQualifierSid(),
						companyIdentifierModelImpl.getOriginalIdentifierStatus(),
						companyIdentifierModelImpl.getOriginalStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER,
					args);

				args = new Object[] {
						companyIdentifierModelImpl.getCompanyStringIdentifierValue(),
						companyIdentifierModelImpl.getCompanyQualifierSid(),
						companyIdentifierModelImpl.getIdentifierStatus(),
						companyIdentifierModelImpl.getStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIER,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIER,
					args);
			}

			if ((companyIdentifierModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						companyIdentifierModelImpl.getOriginalCompanyMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS,
					args);

				args = new Object[] {
						companyIdentifierModelImpl.getCompanyMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYCRTIDENTIFIERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYCRTIDENTIFIERDETAILS,
					args);
			}
		}

		entityCache.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			CompanyIdentifierImpl.class, companyIdentifier.getPrimaryKey(),
			companyIdentifier, false);

		companyIdentifier.resetOriginalValues();

		return companyIdentifier;
	}

	protected CompanyIdentifier toUnwrappedModel(
		CompanyIdentifier companyIdentifier) {
		if (companyIdentifier instanceof CompanyIdentifierImpl) {
			return companyIdentifier;
		}

		CompanyIdentifierImpl companyIdentifierImpl = new CompanyIdentifierImpl();

		companyIdentifierImpl.setNew(companyIdentifier.isNew());
		companyIdentifierImpl.setPrimaryKey(companyIdentifier.getPrimaryKey());

		companyIdentifierImpl.setEndDate(companyIdentifier.getEndDate());
		companyIdentifierImpl.setCompanyStringIdentifierSid(companyIdentifier.getCompanyStringIdentifierSid());
		companyIdentifierImpl.setModifiedDate(companyIdentifier.getModifiedDate());
		companyIdentifierImpl.setIdentifierStatus(companyIdentifier.getIdentifierStatus());
		companyIdentifierImpl.setEntityCode(companyIdentifier.getEntityCode());
		companyIdentifierImpl.setRecordLockStatus(companyIdentifier.isRecordLockStatus());
		companyIdentifierImpl.setStartDate(companyIdentifier.getStartDate());
		companyIdentifierImpl.setCreatedDate(companyIdentifier.getCreatedDate());
		companyIdentifierImpl.setSource(companyIdentifier.getSource());
		companyIdentifierImpl.setCreatedBy(companyIdentifier.getCreatedBy());
		companyIdentifierImpl.setCompanyStringIdentifierValue(companyIdentifier.getCompanyStringIdentifierValue());
		companyIdentifierImpl.setBatchId(companyIdentifier.getBatchId());
		companyIdentifierImpl.setCompanyQualifierSid(companyIdentifier.getCompanyQualifierSid());
		companyIdentifierImpl.setModifiedBy(companyIdentifier.getModifiedBy());
		companyIdentifierImpl.setInboundStatus(companyIdentifier.getInboundStatus());
		companyIdentifierImpl.setCompanyMasterSid(companyIdentifier.getCompanyMasterSid());

		return companyIdentifierImpl;
	}

	/**
	 * Returns the company identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the company identifier
	 * @return the company identifier
	 * @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCompanyIdentifierException {
		CompanyIdentifier companyIdentifier = fetchByPrimaryKey(primaryKey);

		if (companyIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return companyIdentifier;
	}

	/**
	 * Returns the company identifier with the primary key or throws a {@link NoSuchCompanyIdentifierException} if it could not be found.
	 *
	 * @param companyStringIdentifierSid the primary key of the company identifier
	 * @return the company identifier
	 * @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier findByPrimaryKey(int companyStringIdentifierSid)
		throws NoSuchCompanyIdentifierException {
		return findByPrimaryKey((Serializable)companyStringIdentifierSid);
	}

	/**
	 * Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the company identifier
	 * @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				CompanyIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CompanyIdentifier companyIdentifier = (CompanyIdentifier)serializable;

		if (companyIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				companyIdentifier = (CompanyIdentifier)session.get(CompanyIdentifierImpl.class,
						primaryKey);

				if (companyIdentifier != null) {
					cacheResult(companyIdentifier);
				}
				else {
					entityCache.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						CompanyIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					CompanyIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return companyIdentifier;
	}

	/**
	 * Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param companyStringIdentifierSid the primary key of the company identifier
	 * @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
	 */
	@Override
	public CompanyIdentifier fetchByPrimaryKey(int companyStringIdentifierSid) {
		return fetchByPrimaryKey((Serializable)companyStringIdentifierSid);
	}

	@Override
	public Map<Serializable, CompanyIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CompanyIdentifier> map = new HashMap<Serializable, CompanyIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CompanyIdentifier companyIdentifier = fetchByPrimaryKey(primaryKey);

			if (companyIdentifier != null) {
				map.put(primaryKey, companyIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					CompanyIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CompanyIdentifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_COMPANYIDENTIFIER_WHERE_PKS_IN);

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

			for (CompanyIdentifier companyIdentifier : (List<CompanyIdentifier>)q.list()) {
				map.put(companyIdentifier.getPrimaryKeyObj(), companyIdentifier);

				cacheResult(companyIdentifier);

				uncachedPrimaryKeys.remove(companyIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					CompanyIdentifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the company identifiers.
	 *
	 * @return the company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the company identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @return the range of company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the company identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findAll(int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the company identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of company identifiers
	 * @param end the upper bound of the range of company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of company identifiers
	 */
	@Override
	public List<CompanyIdentifier> findAll(int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator,
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

		List<CompanyIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<CompanyIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_COMPANYIDENTIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMPANYIDENTIFIER;

				if (pagination) {
					sql = sql.concat(CompanyIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CompanyIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CompanyIdentifier>)QueryUtil.list(q,
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
	 * Removes all the company identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CompanyIdentifier companyIdentifier : findAll()) {
			remove(companyIdentifier);
		}
	}

	/**
	 * Returns the number of company identifiers.
	 *
	 * @return the number of company identifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMPANYIDENTIFIER);

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
		return CompanyIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the company identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CompanyIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_COMPANYIDENTIFIER = "SELECT companyIdentifier FROM CompanyIdentifier companyIdentifier";
	private static final String _SQL_SELECT_COMPANYIDENTIFIER_WHERE_PKS_IN = "SELECT companyIdentifier FROM CompanyIdentifier companyIdentifier WHERE COMPANY_IDENTIFIER_SID IN (";
	private static final String _SQL_SELECT_COMPANYIDENTIFIER_WHERE = "SELECT companyIdentifier FROM CompanyIdentifier companyIdentifier WHERE ";
	private static final String _SQL_COUNT_COMPANYIDENTIFIER = "SELECT COUNT(companyIdentifier) FROM CompanyIdentifier companyIdentifier";
	private static final String _SQL_COUNT_COMPANYIDENTIFIER_WHERE = "SELECT COUNT(companyIdentifier) FROM CompanyIdentifier companyIdentifier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "companyIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CompanyIdentifier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CompanyIdentifier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CompanyIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"endDate", "companyStringIdentifierSid", "modifiedDate",
				"identifierStatus", "entityCode", "recordLockStatus",
				"startDate", "createdDate", "source", "createdBy",
				"companyStringIdentifierValue", "batchId", "companyQualifierSid",
				"modifiedBy", "inboundStatus", "companyMasterSid"
			});
}