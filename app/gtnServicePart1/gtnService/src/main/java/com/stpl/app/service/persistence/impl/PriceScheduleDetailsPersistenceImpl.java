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

import com.stpl.app.exception.NoSuchPriceScheduleDetailsException;
import com.stpl.app.model.PriceScheduleDetails;
import com.stpl.app.model.impl.PriceScheduleDetailsImpl;
import com.stpl.app.model.impl.PriceScheduleDetailsModelImpl;
import com.stpl.app.service.persistence.PriceScheduleDetailsPersistence;

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
 * The persistence implementation for the price schedule details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PriceScheduleDetailsPersistence
 * @see com.stpl.app.service.persistence.PriceScheduleDetailsUtil
 * @generated
 */
@ProviderType
public class PriceScheduleDetailsPersistenceImpl extends BasePersistenceImpl<PriceScheduleDetails>
	implements PriceScheduleDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PriceScheduleDetailsUtil} to access the price schedule details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PriceScheduleDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS =
		new FinderPath(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPriceScheduleMasterDetails",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS =
		new FinderPath(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsModelImpl.FINDER_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPriceScheduleMasterDetails",
			new String[] { Integer.class.getName() },
			PriceScheduleDetailsModelImpl.PRICESCHEDULESYSTEMID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS =
		new FinderPath(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleMasterDetails",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the price schedule detailses where priceScheduleSystemId = &#63;.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @return the matching price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId) {
		return findByPriceScheduleMasterDetails(priceScheduleSystemId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param start the lower bound of the range of price schedule detailses
	 * @param end the upper bound of the range of price schedule detailses (not inclusive)
	 * @return the range of matching price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end) {
		return findByPriceScheduleMasterDetails(priceScheduleSystemId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param start the lower bound of the range of price schedule detailses
	 * @param end the upper bound of the range of price schedule detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return findByPriceScheduleMasterDetails(priceScheduleSystemId, start,
			end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param start the lower bound of the range of price schedule detailses
	 * @param end the upper bound of the range of price schedule detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS;
			finderArgs = new Object[] { priceScheduleSystemId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS;
			finderArgs = new Object[] {
					priceScheduleSystemId,
					
					start, end, orderByComparator
				};
		}

		List<PriceScheduleDetails> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PriceScheduleDetails priceScheduleDetails : list) {
					if ((priceScheduleSystemId != priceScheduleDetails.getPriceScheduleSystemId())) {
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

			query.append(_SQL_SELECT_PRICESCHEDULEDETAILS_WHERE);

			query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PRICESCHEDULESYSTEMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PriceScheduleDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(priceScheduleSystemId);

				if (!pagination) {
					list = (List<PriceScheduleDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleDetails>)QueryUtil.list(q,
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
	 * Returns the first price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule details
	 * @throws NoSuchPriceScheduleDetailsException if a matching price schedule details could not be found
	 */
	@Override
	public PriceScheduleDetails findByPriceScheduleMasterDetails_First(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws NoSuchPriceScheduleDetailsException {
		PriceScheduleDetails priceScheduleDetails = fetchByPriceScheduleMasterDetails_First(priceScheduleSystemId,
				orderByComparator);

		if (priceScheduleDetails != null) {
			return priceScheduleDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleSystemId=");
		msg.append(priceScheduleSystemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleDetailsException(msg.toString());
	}

	/**
	 * Returns the first price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching price schedule details, or <code>null</code> if a matching price schedule details could not be found
	 */
	@Override
	public PriceScheduleDetails fetchByPriceScheduleMasterDetails_First(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		List<PriceScheduleDetails> list = findByPriceScheduleMasterDetails(priceScheduleSystemId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule details
	 * @throws NoSuchPriceScheduleDetailsException if a matching price schedule details could not be found
	 */
	@Override
	public PriceScheduleDetails findByPriceScheduleMasterDetails_Last(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws NoSuchPriceScheduleDetailsException {
		PriceScheduleDetails priceScheduleDetails = fetchByPriceScheduleMasterDetails_Last(priceScheduleSystemId,
				orderByComparator);

		if (priceScheduleDetails != null) {
			return priceScheduleDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("priceScheduleSystemId=");
		msg.append(priceScheduleSystemId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPriceScheduleDetailsException(msg.toString());
	}

	/**
	 * Returns the last price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching price schedule details, or <code>null</code> if a matching price schedule details could not be found
	 */
	@Override
	public PriceScheduleDetails fetchByPriceScheduleMasterDetails_Last(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		int count = countByPriceScheduleMasterDetails(priceScheduleSystemId);

		if (count == 0) {
			return null;
		}

		List<PriceScheduleDetails> list = findByPriceScheduleMasterDetails(priceScheduleSystemId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the price schedule detailses before and after the current price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	 *
	 * @param psDetailsSystemId the primary key of the current price schedule details
	 * @param priceScheduleSystemId the price schedule system ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next price schedule details
	 * @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
		int psDetailsSystemId, int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws NoSuchPriceScheduleDetailsException {
		PriceScheduleDetails priceScheduleDetails = findByPrimaryKey(psDetailsSystemId);

		Session session = null;

		try {
			session = openSession();

			PriceScheduleDetails[] array = new PriceScheduleDetailsImpl[3];

			array[0] = getByPriceScheduleMasterDetails_PrevAndNext(session,
					priceScheduleDetails, priceScheduleSystemId,
					orderByComparator, true);

			array[1] = priceScheduleDetails;

			array[2] = getByPriceScheduleMasterDetails_PrevAndNext(session,
					priceScheduleDetails, priceScheduleSystemId,
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

	protected PriceScheduleDetails getByPriceScheduleMasterDetails_PrevAndNext(
		Session session, PriceScheduleDetails priceScheduleDetails,
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator,
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

		query.append(_SQL_SELECT_PRICESCHEDULEDETAILS_WHERE);

		query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PRICESCHEDULESYSTEMID_2);

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
			query.append(PriceScheduleDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(priceScheduleSystemId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(priceScheduleDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PriceScheduleDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the price schedule detailses where priceScheduleSystemId = &#63; from the database.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 */
	@Override
	public void removeByPriceScheduleMasterDetails(int priceScheduleSystemId) {
		for (PriceScheduleDetails priceScheduleDetails : findByPriceScheduleMasterDetails(
				priceScheduleSystemId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(priceScheduleDetails);
		}
	}

	/**
	 * Returns the number of price schedule detailses where priceScheduleSystemId = &#63;.
	 *
	 * @param priceScheduleSystemId the price schedule system ID
	 * @return the number of matching price schedule detailses
	 */
	@Override
	public int countByPriceScheduleMasterDetails(int priceScheduleSystemId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS;

		Object[] finderArgs = new Object[] { priceScheduleSystemId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PRICESCHEDULEDETAILS_WHERE);

			query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PRICESCHEDULESYSTEMID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(priceScheduleSystemId);

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

	private static final String _FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PRICESCHEDULESYSTEMID_2 =
		"priceScheduleDetails.priceScheduleSystemId = ?";

	public PriceScheduleDetailsPersistenceImpl() {
		setModelClass(PriceScheduleDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("psDetailsSystemId", "PS_DETAILS_SYSTEM_ID");
			dbColumnNames.put("companyFamilyplanSystemId",
				"COMPANY_FAMILYPLAN_SYSTEM_ID");
			dbColumnNames.put("itemSystemId", "ITEM_SYSTEM_ID");
			dbColumnNames.put("priceProtectionStartDate",
				"PRICE_PROTECTION_START_DATE");
			dbColumnNames.put("basePrice", "BASE_PRICE");
			dbColumnNames.put("revisionDate", "REVISION_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("priceScheduleSystemId",
				"PRICE_SCHEDULE_SYSTEM_ID");
			dbColumnNames.put("itemFamilyplanSystemId",
				"ITEM_FAMILYPLAN_SYSTEM_ID");
			dbColumnNames.put("priceTolerance", "PRICE_TOLERANCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("suggestedPrice", "SUGGESTED_PRICE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
			dbColumnNames.put("priceToleranceType", "PRICE_TOLERANCE_TYPE");
			dbColumnNames.put("memberFamilyplanSystemId",
				"MEMBER_FAMILYPLAN_SYSTEM_ID");
			dbColumnNames.put("contractPriceEndDate", "CONTRACT_PRICE_END_DATE");
			dbColumnNames.put("contractPriceStartDate",
				"CONTRACT_PRICE_START_DATE");
			dbColumnNames.put("priceToleranceFrequency",
				"PRICE_TOLERANCE_FREQUENCY");
			dbColumnNames.put("attachedDate", "ATTACHED_DATE");
			dbColumnNames.put("priceProtectionEndDate",
				"PRICE_PROTECTION_END_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("pricePlanId", "PRICE_PLAN_ID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("priceToleranceInterval",
				"PRICE_TOLERANCE_INTERVAL");
			dbColumnNames.put("priceRevision", "PRICE_REVISION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the price schedule details in the entity cache if it is enabled.
	 *
	 * @param priceScheduleDetails the price schedule details
	 */
	@Override
	public void cacheResult(PriceScheduleDetails priceScheduleDetails) {
		entityCache.putResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class,
			priceScheduleDetails.getPrimaryKey(), priceScheduleDetails);

		priceScheduleDetails.resetOriginalValues();
	}

	/**
	 * Caches the price schedule detailses in the entity cache if it is enabled.
	 *
	 * @param priceScheduleDetailses the price schedule detailses
	 */
	@Override
	public void cacheResult(List<PriceScheduleDetails> priceScheduleDetailses) {
		for (PriceScheduleDetails priceScheduleDetails : priceScheduleDetailses) {
			if (entityCache.getResult(
						PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
						PriceScheduleDetailsImpl.class,
						priceScheduleDetails.getPrimaryKey()) == null) {
				cacheResult(priceScheduleDetails);
			}
			else {
				priceScheduleDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all price schedule detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PriceScheduleDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the price schedule details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PriceScheduleDetails priceScheduleDetails) {
		entityCache.removeResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class, priceScheduleDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PriceScheduleDetails> priceScheduleDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PriceScheduleDetails priceScheduleDetails : priceScheduleDetailses) {
			entityCache.removeResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
				PriceScheduleDetailsImpl.class,
				priceScheduleDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new price schedule details with the primary key. Does not add the price schedule details to the database.
	 *
	 * @param psDetailsSystemId the primary key for the new price schedule details
	 * @return the new price schedule details
	 */
	@Override
	public PriceScheduleDetails create(int psDetailsSystemId) {
		PriceScheduleDetails priceScheduleDetails = new PriceScheduleDetailsImpl();

		priceScheduleDetails.setNew(true);
		priceScheduleDetails.setPrimaryKey(psDetailsSystemId);

		return priceScheduleDetails;
	}

	/**
	 * Removes the price schedule details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param psDetailsSystemId the primary key of the price schedule details
	 * @return the price schedule details that was removed
	 * @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails remove(int psDetailsSystemId)
		throws NoSuchPriceScheduleDetailsException {
		return remove((Serializable)psDetailsSystemId);
	}

	/**
	 * Removes the price schedule details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the price schedule details
	 * @return the price schedule details that was removed
	 * @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails remove(Serializable primaryKey)
		throws NoSuchPriceScheduleDetailsException {
		Session session = null;

		try {
			session = openSession();

			PriceScheduleDetails priceScheduleDetails = (PriceScheduleDetails)session.get(PriceScheduleDetailsImpl.class,
					primaryKey);

			if (priceScheduleDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPriceScheduleDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(priceScheduleDetails);
		}
		catch (NoSuchPriceScheduleDetailsException nsee) {
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
	protected PriceScheduleDetails removeImpl(
		PriceScheduleDetails priceScheduleDetails) {
		priceScheduleDetails = toUnwrappedModel(priceScheduleDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(priceScheduleDetails)) {
				priceScheduleDetails = (PriceScheduleDetails)session.get(PriceScheduleDetailsImpl.class,
						priceScheduleDetails.getPrimaryKeyObj());
			}

			if (priceScheduleDetails != null) {
				session.delete(priceScheduleDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (priceScheduleDetails != null) {
			clearCache(priceScheduleDetails);
		}

		return priceScheduleDetails;
	}

	@Override
	public PriceScheduleDetails updateImpl(
		PriceScheduleDetails priceScheduleDetails) {
		priceScheduleDetails = toUnwrappedModel(priceScheduleDetails);

		boolean isNew = priceScheduleDetails.isNew();

		PriceScheduleDetailsModelImpl priceScheduleDetailsModelImpl = (PriceScheduleDetailsModelImpl)priceScheduleDetails;

		Session session = null;

		try {
			session = openSession();

			if (priceScheduleDetails.isNew()) {
				session.save(priceScheduleDetails);

				priceScheduleDetails.setNew(false);
			}
			else {
				priceScheduleDetails = (PriceScheduleDetails)session.merge(priceScheduleDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PriceScheduleDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					priceScheduleDetailsModelImpl.getPriceScheduleSystemId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((priceScheduleDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						priceScheduleDetailsModelImpl.getOriginalPriceScheduleSystemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
					args);

				args = new Object[] {
						priceScheduleDetailsModelImpl.getPriceScheduleSystemId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
					args);
			}
		}

		entityCache.putResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PriceScheduleDetailsImpl.class,
			priceScheduleDetails.getPrimaryKey(), priceScheduleDetails, false);

		priceScheduleDetails.resetOriginalValues();

		return priceScheduleDetails;
	}

	protected PriceScheduleDetails toUnwrappedModel(
		PriceScheduleDetails priceScheduleDetails) {
		if (priceScheduleDetails instanceof PriceScheduleDetailsImpl) {
			return priceScheduleDetails;
		}

		PriceScheduleDetailsImpl priceScheduleDetailsImpl = new PriceScheduleDetailsImpl();

		priceScheduleDetailsImpl.setNew(priceScheduleDetails.isNew());
		priceScheduleDetailsImpl.setPrimaryKey(priceScheduleDetails.getPrimaryKey());

		priceScheduleDetailsImpl.setPrice(priceScheduleDetails.getPrice());
		priceScheduleDetailsImpl.setPsDetailsSystemId(priceScheduleDetails.getPsDetailsSystemId());
		priceScheduleDetailsImpl.setCompanyFamilyplanSystemId(priceScheduleDetails.getCompanyFamilyplanSystemId());
		priceScheduleDetailsImpl.setItemSystemId(priceScheduleDetails.getItemSystemId());
		priceScheduleDetailsImpl.setPriceProtectionStartDate(priceScheduleDetails.getPriceProtectionStartDate());
		priceScheduleDetailsImpl.setBasePrice(priceScheduleDetails.getBasePrice());
		priceScheduleDetailsImpl.setRevisionDate(priceScheduleDetails.getRevisionDate());
		priceScheduleDetailsImpl.setModifiedDate(priceScheduleDetails.getModifiedDate());
		priceScheduleDetailsImpl.setPriceScheduleSystemId(priceScheduleDetails.getPriceScheduleSystemId());
		priceScheduleDetailsImpl.setItemFamilyplanSystemId(priceScheduleDetails.getItemFamilyplanSystemId());
		priceScheduleDetailsImpl.setPriceTolerance(priceScheduleDetails.getPriceTolerance());
		priceScheduleDetailsImpl.setCreatedBy(priceScheduleDetails.getCreatedBy());
		priceScheduleDetailsImpl.setCreatedDate(priceScheduleDetails.getCreatedDate());
		priceScheduleDetailsImpl.setSuggestedPrice(priceScheduleDetails.getSuggestedPrice());
		priceScheduleDetailsImpl.setInboundStatus(priceScheduleDetails.getInboundStatus());
		priceScheduleDetailsImpl.setModifiedBy(priceScheduleDetails.getModifiedBy());
		priceScheduleDetailsImpl.setContractPrice(priceScheduleDetails.getContractPrice());
		priceScheduleDetailsImpl.setPriceToleranceType(priceScheduleDetails.getPriceToleranceType());
		priceScheduleDetailsImpl.setMemberFamilyplanSystemId(priceScheduleDetails.getMemberFamilyplanSystemId());
		priceScheduleDetailsImpl.setContractPriceEndDate(priceScheduleDetails.getContractPriceEndDate());
		priceScheduleDetailsImpl.setContractPriceStartDate(priceScheduleDetails.getContractPriceStartDate());
		priceScheduleDetailsImpl.setPriceToleranceFrequency(priceScheduleDetails.getPriceToleranceFrequency());
		priceScheduleDetailsImpl.setAttachedDate(priceScheduleDetails.getAttachedDate());
		priceScheduleDetailsImpl.setPriceProtectionEndDate(priceScheduleDetails.getPriceProtectionEndDate());
		priceScheduleDetailsImpl.setRecordLockStatus(priceScheduleDetails.getRecordLockStatus());
		priceScheduleDetailsImpl.setPricePlanId(priceScheduleDetails.getPricePlanId());
		priceScheduleDetailsImpl.setPriceType(priceScheduleDetails.getPriceType());
		priceScheduleDetailsImpl.setBatchId(priceScheduleDetails.getBatchId());
		priceScheduleDetailsImpl.setPriceToleranceInterval(priceScheduleDetails.getPriceToleranceInterval());
		priceScheduleDetailsImpl.setPriceRevision(priceScheduleDetails.getPriceRevision());

		return priceScheduleDetailsImpl;
	}

	/**
	 * Returns the price schedule details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the price schedule details
	 * @return the price schedule details
	 * @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPriceScheduleDetailsException {
		PriceScheduleDetails priceScheduleDetails = fetchByPrimaryKey(primaryKey);

		if (priceScheduleDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPriceScheduleDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return priceScheduleDetails;
	}

	/**
	 * Returns the price schedule details with the primary key or throws a {@link NoSuchPriceScheduleDetailsException} if it could not be found.
	 *
	 * @param psDetailsSystemId the primary key of the price schedule details
	 * @return the price schedule details
	 * @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails findByPrimaryKey(int psDetailsSystemId)
		throws NoSuchPriceScheduleDetailsException {
		return findByPrimaryKey((Serializable)psDetailsSystemId);
	}

	/**
	 * Returns the price schedule details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the price schedule details
	 * @return the price schedule details, or <code>null</code> if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
				PriceScheduleDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PriceScheduleDetails priceScheduleDetails = (PriceScheduleDetails)serializable;

		if (priceScheduleDetails == null) {
			Session session = null;

			try {
				session = openSession();

				priceScheduleDetails = (PriceScheduleDetails)session.get(PriceScheduleDetailsImpl.class,
						primaryKey);

				if (priceScheduleDetails != null) {
					cacheResult(priceScheduleDetails);
				}
				else {
					entityCache.putResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
						PriceScheduleDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PriceScheduleDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return priceScheduleDetails;
	}

	/**
	 * Returns the price schedule details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param psDetailsSystemId the primary key of the price schedule details
	 * @return the price schedule details, or <code>null</code> if a price schedule details with the primary key could not be found
	 */
	@Override
	public PriceScheduleDetails fetchByPrimaryKey(int psDetailsSystemId) {
		return fetchByPrimaryKey((Serializable)psDetailsSystemId);
	}

	@Override
	public Map<Serializable, PriceScheduleDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PriceScheduleDetails> map = new HashMap<Serializable, PriceScheduleDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PriceScheduleDetails priceScheduleDetails = fetchByPrimaryKey(primaryKey);

			if (priceScheduleDetails != null) {
				map.put(primaryKey, priceScheduleDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PriceScheduleDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PriceScheduleDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PRICESCHEDULEDETAILS_WHERE_PKS_IN);

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

			for (PriceScheduleDetails priceScheduleDetails : (List<PriceScheduleDetails>)q.list()) {
				map.put(priceScheduleDetails.getPrimaryKeyObj(),
					priceScheduleDetails);

				cacheResult(priceScheduleDetails);

				uncachedPrimaryKeys.remove(priceScheduleDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PriceScheduleDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PriceScheduleDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the price schedule detailses.
	 *
	 * @return the price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the price schedule detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of price schedule detailses
	 * @param end the upper bound of the range of price schedule detailses (not inclusive)
	 * @return the range of price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the price schedule detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of price schedule detailses
	 * @param end the upper bound of the range of price schedule detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findAll(int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the price schedule detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of price schedule detailses
	 * @param end the upper bound of the range of price schedule detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of price schedule detailses
	 */
	@Override
	public List<PriceScheduleDetails> findAll(int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator,
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

		List<PriceScheduleDetails> list = null;

		if (retrieveFromCache) {
			list = (List<PriceScheduleDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PRICESCHEDULEDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PRICESCHEDULEDETAILS;

				if (pagination) {
					sql = sql.concat(PriceScheduleDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PriceScheduleDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PriceScheduleDetails>)QueryUtil.list(q,
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
	 * Removes all the price schedule detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PriceScheduleDetails priceScheduleDetails : findAll()) {
			remove(priceScheduleDetails);
		}
	}

	/**
	 * Returns the number of price schedule detailses.
	 *
	 * @return the number of price schedule detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PRICESCHEDULEDETAILS);

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
		return PriceScheduleDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the price schedule details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PriceScheduleDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PRICESCHEDULEDETAILS = "SELECT priceScheduleDetails FROM PriceScheduleDetails priceScheduleDetails";
	private static final String _SQL_SELECT_PRICESCHEDULEDETAILS_WHERE_PKS_IN = "SELECT priceScheduleDetails FROM PriceScheduleDetails priceScheduleDetails WHERE PS_DETAILS_SYSTEM_ID IN (";
	private static final String _SQL_SELECT_PRICESCHEDULEDETAILS_WHERE = "SELECT priceScheduleDetails FROM PriceScheduleDetails priceScheduleDetails WHERE ";
	private static final String _SQL_COUNT_PRICESCHEDULEDETAILS = "SELECT COUNT(priceScheduleDetails) FROM PriceScheduleDetails priceScheduleDetails";
	private static final String _SQL_COUNT_PRICESCHEDULEDETAILS_WHERE = "SELECT COUNT(priceScheduleDetails) FROM PriceScheduleDetails priceScheduleDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "priceScheduleDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PriceScheduleDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PriceScheduleDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PriceScheduleDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"price", "psDetailsSystemId", "companyFamilyplanSystemId",
				"itemSystemId", "priceProtectionStartDate", "basePrice",
				"revisionDate", "modifiedDate", "priceScheduleSystemId",
				"itemFamilyplanSystemId", "priceTolerance", "createdBy",
				"createdDate", "suggestedPrice", "inboundStatus", "modifiedBy",
				"contractPrice", "priceToleranceType",
				"memberFamilyplanSystemId", "contractPriceEndDate",
				"contractPriceStartDate", "priceToleranceFrequency",
				"attachedDate", "priceProtectionEndDate", "recordLockStatus",
				"pricePlanId", "priceType", "batchId", "priceToleranceInterval",
				"priceRevision"
			});
}