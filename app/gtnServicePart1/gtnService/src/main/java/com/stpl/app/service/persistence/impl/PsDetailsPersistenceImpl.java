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

import com.stpl.app.exception.NoSuchPsDetailsException;
import com.stpl.app.model.PsDetails;
import com.stpl.app.model.impl.PsDetailsImpl;
import com.stpl.app.model.impl.PsDetailsModelImpl;
import com.stpl.app.service.persistence.PsDetailsPersistence;

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
 * The persistence implementation for the ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsDetailsPersistence
 * @see com.stpl.app.service.persistence.PsDetailsUtil
 * @generated
 */
@ProviderType
public class PsDetailsPersistenceImpl extends BasePersistenceImpl<PsDetails>
	implements PsDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PsDetailsUtil} to access the ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PsDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS =
		new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPriceScheduleMasterDetails",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS =
		new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsModelImpl.FINDER_CACHE_ENABLED, PsDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPriceScheduleMasterDetails",
			new String[] { Integer.class.getName() },
			PsDetailsModelImpl.PSMODELSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS =
		new FinderPath(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPriceScheduleMasterDetails",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the ps detailses where psModelSid = &#63;.
	 *
	 * @param psModelSid the ps model sid
	 * @return the matching ps detailses
	 */
	@Override
	public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid) {
		return findByPriceScheduleMasterDetails(psModelSid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps detailses where psModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psModelSid the ps model sid
	 * @param start the lower bound of the range of ps detailses
	 * @param end the upper bound of the range of ps detailses (not inclusive)
	 * @return the range of matching ps detailses
	 */
	@Override
	public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid,
		int start, int end) {
		return findByPriceScheduleMasterDetails(psModelSid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps detailses where psModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psModelSid the ps model sid
	 * @param start the lower bound of the range of ps detailses
	 * @param end the upper bound of the range of ps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ps detailses
	 */
	@Override
	public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid,
		int start, int end, OrderByComparator<PsDetails> orderByComparator) {
		return findByPriceScheduleMasterDetails(psModelSid, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps detailses where psModelSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param psModelSid the ps model sid
	 * @param start the lower bound of the range of ps detailses
	 * @param end the upper bound of the range of ps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching ps detailses
	 */
	@Override
	public List<PsDetails> findByPriceScheduleMasterDetails(int psModelSid,
		int start, int end, OrderByComparator<PsDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS;
			finderArgs = new Object[] { psModelSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS;
			finderArgs = new Object[] { psModelSid, start, end, orderByComparator };
		}

		List<PsDetails> list = null;

		if (retrieveFromCache) {
			list = (List<PsDetails>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PsDetails psDetails : list) {
					if ((psModelSid != psDetails.getPsModelSid())) {
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

			query.append(_SQL_SELECT_PSDETAILS_WHERE);

			query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PsDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(psModelSid);

				if (!pagination) {
					list = (List<PsDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsDetails>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first ps details in the ordered set where psModelSid = &#63;.
	 *
	 * @param psModelSid the ps model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps details
	 * @throws NoSuchPsDetailsException if a matching ps details could not be found
	 */
	@Override
	public PsDetails findByPriceScheduleMasterDetails_First(int psModelSid,
		OrderByComparator<PsDetails> orderByComparator)
		throws NoSuchPsDetailsException {
		PsDetails psDetails = fetchByPriceScheduleMasterDetails_First(psModelSid,
				orderByComparator);

		if (psDetails != null) {
			return psDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psModelSid=");
		msg.append(psModelSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsDetailsException(msg.toString());
	}

	/**
	 * Returns the first ps details in the ordered set where psModelSid = &#63;.
	 *
	 * @param psModelSid the ps model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ps details, or <code>null</code> if a matching ps details could not be found
	 */
	@Override
	public PsDetails fetchByPriceScheduleMasterDetails_First(int psModelSid,
		OrderByComparator<PsDetails> orderByComparator) {
		List<PsDetails> list = findByPriceScheduleMasterDetails(psModelSid, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last ps details in the ordered set where psModelSid = &#63;.
	 *
	 * @param psModelSid the ps model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps details
	 * @throws NoSuchPsDetailsException if a matching ps details could not be found
	 */
	@Override
	public PsDetails findByPriceScheduleMasterDetails_Last(int psModelSid,
		OrderByComparator<PsDetails> orderByComparator)
		throws NoSuchPsDetailsException {
		PsDetails psDetails = fetchByPriceScheduleMasterDetails_Last(psModelSid,
				orderByComparator);

		if (psDetails != null) {
			return psDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("psModelSid=");
		msg.append(psModelSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPsDetailsException(msg.toString());
	}

	/**
	 * Returns the last ps details in the ordered set where psModelSid = &#63;.
	 *
	 * @param psModelSid the ps model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ps details, or <code>null</code> if a matching ps details could not be found
	 */
	@Override
	public PsDetails fetchByPriceScheduleMasterDetails_Last(int psModelSid,
		OrderByComparator<PsDetails> orderByComparator) {
		int count = countByPriceScheduleMasterDetails(psModelSid);

		if (count == 0) {
			return null;
		}

		List<PsDetails> list = findByPriceScheduleMasterDetails(psModelSid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the ps detailses before and after the current ps details in the ordered set where psModelSid = &#63;.
	 *
	 * @param psDetailsSid the primary key of the current ps details
	 * @param psModelSid the ps model sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ps details
	 * @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
		int psDetailsSid, int psModelSid,
		OrderByComparator<PsDetails> orderByComparator)
		throws NoSuchPsDetailsException {
		PsDetails psDetails = findByPrimaryKey(psDetailsSid);

		Session session = null;

		try {
			session = openSession();

			PsDetails[] array = new PsDetailsImpl[3];

			array[0] = getByPriceScheduleMasterDetails_PrevAndNext(session,
					psDetails, psModelSid, orderByComparator, true);

			array[1] = psDetails;

			array[2] = getByPriceScheduleMasterDetails_PrevAndNext(session,
					psDetails, psModelSid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PsDetails getByPriceScheduleMasterDetails_PrevAndNext(
		Session session, PsDetails psDetails, int psModelSid,
		OrderByComparator<PsDetails> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PSDETAILS_WHERE);

		query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2);

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
			query.append(PsDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(psModelSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(psDetails);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PsDetails> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the ps detailses where psModelSid = &#63; from the database.
	 *
	 * @param psModelSid the ps model sid
	 */
	@Override
	public void removeByPriceScheduleMasterDetails(int psModelSid) {
		for (PsDetails psDetails : findByPriceScheduleMasterDetails(
				psModelSid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(psDetails);
		}
	}

	/**
	 * Returns the number of ps detailses where psModelSid = &#63;.
	 *
	 * @param psModelSid the ps model sid
	 * @return the number of matching ps detailses
	 */
	@Override
	public int countByPriceScheduleMasterDetails(int psModelSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS;

		Object[] finderArgs = new Object[] { psModelSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PSDETAILS_WHERE);

			query.append(_FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(psModelSid);

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

	private static final String _FINDER_COLUMN_PRICESCHEDULEMASTERDETAILS_PSMODELSID_2 =
		"psDetails.psModelSid = ?";

	public PsDetailsPersistenceImpl() {
		setModelClass(PsDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("nepFormula", "NEP_FORMULA");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("resetType", "RESET_TYPE");
			dbColumnNames.put("priceProtectionStartDate",
				"PRICE_PROTECTION_START_DATE");
			dbColumnNames.put("resetDate", "RESET_DATE");
			dbColumnNames.put("basePrice", "BASE_PRICE");
			dbColumnNames.put("itemPsAttachedDate", "ITEM_PS_ATTACHED_DATE");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("itemPsAttachedStatus", "ITEM_PS_ATTACHED_STATUS");
			dbColumnNames.put("revisionDate", "REVISION_DATE");
			dbColumnNames.put("priceTolerance", "PRICE_TOLERANCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("psDetailsSid", "PS_DETAILS_SID");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("suggestedPrice", "SUGGESTED_PRICE");
			dbColumnNames.put("netPriceTypeFormula", "NET_PRICE_TYPE_FORMULA");
			dbColumnNames.put("priceProtectionPriceType",
				"PRICE_PROTECTION_PRICE_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("priceToleranceType", "PRICE_TOLERANCE_TYPE");
			dbColumnNames.put("maxIncrementalChange", "MAX_INCREMENTAL_CHANGE");
			dbColumnNames.put("itemPricingQualifierSid",
				"ITEM_PRICING_QUALIFIER_SID");
			dbColumnNames.put("contractPriceEndDate", "CONTRACT_PRICE_END_DATE");
			dbColumnNames.put("nep", "NEP");
			dbColumnNames.put("contractPriceStartDate",
				"CONTRACT_PRICE_START_DATE");
			dbColumnNames.put("priceToleranceFrequency",
				"PRICE_TOLERANCE_FREQUENCY");
			dbColumnNames.put("priceProtectionEndDate",
				"PRICE_PROTECTION_END_DATE");
			dbColumnNames.put("priceProtectionStatus", "PRICE_PROTECTION_STATUS");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("resetEligible", "RESET_ELIGIBLE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("priceToleranceInterval",
				"PRICE_TOLERANCE_INTERVAL");
			dbColumnNames.put("netPriceType", "NET_PRICE_TYPE");
			dbColumnNames.put("priceRevision", "PRICE_REVISION");
			dbColumnNames.put("resetFrequency", "RESET_FREQUENCY");
			dbColumnNames.put("resetInterval", "RESET_INTERVAL");
			dbColumnNames.put("basePriceType", "BASE_PRICE_TYPE");
			dbColumnNames.put("basePriceEntry", "BASE_PRICE_ENTRY");
			dbColumnNames.put("basePriceDate", "BASE_PRICE_DATE");
			dbColumnNames.put("netBasePrice", "NET_BASE_PRICE");
			dbColumnNames.put("basePriceDdlb", "BASE_PRICE_DDLB");
			dbColumnNames.put("subsequentPeriodPriceType",
				"SUBSEQUENT_PERIOD_PRICE_TYPE");
			dbColumnNames.put("netSubsequentPeriodPrice",
				"NET_SUBSEQUENT_PERIOD_PRICE");
			dbColumnNames.put("netSubsequentPriceFormulaId",
				"NET_SUBSEQUENT_PRICE_FORMULA_ID");
			dbColumnNames.put("resetPriceType", "RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceType", "NET_RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceFormulaId",
				"NET_RESET_PRICE_FORMULA_ID");
			dbColumnNames.put("netBasePriceFormulaId",
				"NET_BASE_PRICE_FORMULA_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ps details in the entity cache if it is enabled.
	 *
	 * @param psDetails the ps details
	 */
	@Override
	public void cacheResult(PsDetails psDetails) {
		entityCache.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsImpl.class, psDetails.getPrimaryKey(), psDetails);

		psDetails.resetOriginalValues();
	}

	/**
	 * Caches the ps detailses in the entity cache if it is enabled.
	 *
	 * @param psDetailses the ps detailses
	 */
	@Override
	public void cacheResult(List<PsDetails> psDetailses) {
		for (PsDetails psDetails : psDetailses) {
			if (entityCache.getResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						PsDetailsImpl.class, psDetails.getPrimaryKey()) == null) {
				cacheResult(psDetails);
			}
			else {
				psDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ps detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PsDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ps details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PsDetails psDetails) {
		entityCache.removeResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsImpl.class, psDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PsDetails> psDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PsDetails psDetails : psDetailses) {
			entityCache.removeResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				PsDetailsImpl.class, psDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ps details with the primary key. Does not add the ps details to the database.
	 *
	 * @param psDetailsSid the primary key for the new ps details
	 * @return the new ps details
	 */
	@Override
	public PsDetails create(int psDetailsSid) {
		PsDetails psDetails = new PsDetailsImpl();

		psDetails.setNew(true);
		psDetails.setPrimaryKey(psDetailsSid);

		return psDetails;
	}

	/**
	 * Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param psDetailsSid the primary key of the ps details
	 * @return the ps details that was removed
	 * @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails remove(int psDetailsSid) throws NoSuchPsDetailsException {
		return remove((Serializable)psDetailsSid);
	}

	/**
	 * Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ps details
	 * @return the ps details that was removed
	 * @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails remove(Serializable primaryKey)
		throws NoSuchPsDetailsException {
		Session session = null;

		try {
			session = openSession();

			PsDetails psDetails = (PsDetails)session.get(PsDetailsImpl.class,
					primaryKey);

			if (psDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(psDetails);
		}
		catch (NoSuchPsDetailsException nsee) {
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
	protected PsDetails removeImpl(PsDetails psDetails) {
		psDetails = toUnwrappedModel(psDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(psDetails)) {
				psDetails = (PsDetails)session.get(PsDetailsImpl.class,
						psDetails.getPrimaryKeyObj());
			}

			if (psDetails != null) {
				session.delete(psDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (psDetails != null) {
			clearCache(psDetails);
		}

		return psDetails;
	}

	@Override
	public PsDetails updateImpl(PsDetails psDetails) {
		psDetails = toUnwrappedModel(psDetails);

		boolean isNew = psDetails.isNew();

		PsDetailsModelImpl psDetailsModelImpl = (PsDetailsModelImpl)psDetails;

		Session session = null;

		try {
			session = openSession();

			if (psDetails.isNew()) {
				session.save(psDetails);

				psDetails.setNew(false);
			}
			else {
				psDetails = (PsDetails)session.merge(psDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!PsDetailsModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { psDetailsModelImpl.getPsModelSid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((psDetailsModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						psDetailsModelImpl.getOriginalPsModelSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
					args);

				args = new Object[] { psDetailsModelImpl.getPsModelSid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PRICESCHEDULEMASTERDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PRICESCHEDULEMASTERDETAILS,
					args);
			}
		}

		entityCache.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsDetailsImpl.class, psDetails.getPrimaryKey(), psDetails, false);

		psDetails.resetOriginalValues();

		return psDetails;
	}

	protected PsDetails toUnwrappedModel(PsDetails psDetails) {
		if (psDetails instanceof PsDetailsImpl) {
			return psDetails;
		}

		PsDetailsImpl psDetailsImpl = new PsDetailsImpl();

		psDetailsImpl.setNew(psDetails.isNew());
		psDetailsImpl.setPrimaryKey(psDetails.getPrimaryKey());

		psDetailsImpl.setNepFormula(psDetails.getNepFormula());
		psDetailsImpl.setPrice(psDetails.getPrice());
		psDetailsImpl.setItemMasterSid(psDetails.getItemMasterSid());
		psDetailsImpl.setResetType(psDetails.getResetType());
		psDetailsImpl.setPriceProtectionStartDate(psDetails.getPriceProtectionStartDate());
		psDetailsImpl.setResetDate(psDetails.getResetDate());
		psDetailsImpl.setBasePrice(psDetails.getBasePrice());
		psDetailsImpl.setItemPsAttachedDate(psDetails.getItemPsAttachedDate());
		psDetailsImpl.setBrandMasterSid(psDetails.getBrandMasterSid());
		psDetailsImpl.setStatus(psDetails.getStatus());
		psDetailsImpl.setModifiedDate(psDetails.getModifiedDate());
		psDetailsImpl.setItemPsAttachedStatus(psDetails.getItemPsAttachedStatus());
		psDetailsImpl.setRevisionDate(psDetails.getRevisionDate());
		psDetailsImpl.setPriceTolerance(psDetails.getPriceTolerance());
		psDetailsImpl.setCreatedDate(psDetails.getCreatedDate());
		psDetailsImpl.setCreatedBy(psDetails.getCreatedBy());
		psDetailsImpl.setSource(psDetails.getSource());
		psDetailsImpl.setPsDetailsSid(psDetails.getPsDetailsSid());
		psDetailsImpl.setPsModelSid(psDetails.getPsModelSid());
		psDetailsImpl.setSuggestedPrice(psDetails.getSuggestedPrice());
		psDetailsImpl.setNetPriceTypeFormula(psDetails.getNetPriceTypeFormula());
		psDetailsImpl.setPriceProtectionPriceType(psDetails.getPriceProtectionPriceType());
		psDetailsImpl.setModifiedBy(psDetails.getModifiedBy());
		psDetailsImpl.setInboundStatus(psDetails.getInboundStatus());
		psDetailsImpl.setContractPrice(psDetails.getContractPrice());
		psDetailsImpl.setIfpModelSid(psDetails.getIfpModelSid());
		psDetailsImpl.setPriceToleranceType(psDetails.getPriceToleranceType());
		psDetailsImpl.setMaxIncrementalChange(psDetails.getMaxIncrementalChange());
		psDetailsImpl.setItemPricingQualifierSid(psDetails.getItemPricingQualifierSid());
		psDetailsImpl.setContractPriceEndDate(psDetails.getContractPriceEndDate());
		psDetailsImpl.setNep(psDetails.getNep());
		psDetailsImpl.setContractPriceStartDate(psDetails.getContractPriceStartDate());
		psDetailsImpl.setPriceToleranceFrequency(psDetails.getPriceToleranceFrequency());
		psDetailsImpl.setPriceProtectionEndDate(psDetails.getPriceProtectionEndDate());
		psDetailsImpl.setPriceProtectionStatus(psDetails.getPriceProtectionStatus());
		psDetailsImpl.setRecordLockStatus(psDetails.isRecordLockStatus());
		psDetailsImpl.setResetEligible(psDetails.getResetEligible());
		psDetailsImpl.setBatchId(psDetails.getBatchId());
		psDetailsImpl.setPriceToleranceInterval(psDetails.getPriceToleranceInterval());
		psDetailsImpl.setNetPriceType(psDetails.getNetPriceType());
		psDetailsImpl.setPriceRevision(psDetails.getPriceRevision());
		psDetailsImpl.setResetFrequency(psDetails.getResetFrequency());
		psDetailsImpl.setResetInterval(psDetails.getResetInterval());
		psDetailsImpl.setBasePriceType(psDetails.getBasePriceType());
		psDetailsImpl.setBasePriceEntry(psDetails.getBasePriceEntry());
		psDetailsImpl.setBasePriceDate(psDetails.getBasePriceDate());
		psDetailsImpl.setNetBasePrice(psDetails.getNetBasePrice());
		psDetailsImpl.setBasePriceDdlb(psDetails.getBasePriceDdlb());
		psDetailsImpl.setSubsequentPeriodPriceType(psDetails.getSubsequentPeriodPriceType());
		psDetailsImpl.setNetSubsequentPeriodPrice(psDetails.getNetSubsequentPeriodPrice());
		psDetailsImpl.setNetSubsequentPriceFormulaId(psDetails.getNetSubsequentPriceFormulaId());
		psDetailsImpl.setResetPriceType(psDetails.getResetPriceType());
		psDetailsImpl.setNetResetPriceType(psDetails.getNetResetPriceType());
		psDetailsImpl.setNetResetPriceFormulaId(psDetails.getNetResetPriceFormulaId());
		psDetailsImpl.setNetBasePriceFormulaId(psDetails.getNetBasePriceFormulaId());

		return psDetailsImpl;
	}

	/**
	 * Returns the ps details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps details
	 * @return the ps details
	 * @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPsDetailsException {
		PsDetails psDetails = fetchByPrimaryKey(primaryKey);

		if (psDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return psDetails;
	}

	/**
	 * Returns the ps details with the primary key or throws a {@link NoSuchPsDetailsException} if it could not be found.
	 *
	 * @param psDetailsSid the primary key of the ps details
	 * @return the ps details
	 * @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails findByPrimaryKey(int psDetailsSid)
		throws NoSuchPsDetailsException {
		return findByPrimaryKey((Serializable)psDetailsSid);
	}

	/**
	 * Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps details
	 * @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				PsDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PsDetails psDetails = (PsDetails)serializable;

		if (psDetails == null) {
			Session session = null;

			try {
				session = openSession();

				psDetails = (PsDetails)session.get(PsDetailsImpl.class,
						primaryKey);

				if (psDetails != null) {
					cacheResult(psDetails);
				}
				else {
					entityCache.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						PsDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PsDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return psDetails;
	}

	/**
	 * Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param psDetailsSid the primary key of the ps details
	 * @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
	 */
	@Override
	public PsDetails fetchByPrimaryKey(int psDetailsSid) {
		return fetchByPrimaryKey((Serializable)psDetailsSid);
	}

	@Override
	public Map<Serializable, PsDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PsDetails> map = new HashMap<Serializable, PsDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PsDetails psDetails = fetchByPrimaryKey(primaryKey);

			if (psDetails != null) {
				map.put(primaryKey, psDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PsDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PsDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PSDETAILS_WHERE_PKS_IN);

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

			for (PsDetails psDetails : (List<PsDetails>)q.list()) {
				map.put(psDetails.getPrimaryKeyObj(), psDetails);

				cacheResult(psDetails);

				uncachedPrimaryKeys.remove(psDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PsDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ps detailses.
	 *
	 * @return the ps detailses
	 */
	@Override
	public List<PsDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps detailses
	 * @param end the upper bound of the range of ps detailses (not inclusive)
	 * @return the range of ps detailses
	 */
	@Override
	public List<PsDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps detailses
	 * @param end the upper bound of the range of ps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ps detailses
	 */
	@Override
	public List<PsDetails> findAll(int start, int end,
		OrderByComparator<PsDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps detailses
	 * @param end the upper bound of the range of ps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ps detailses
	 */
	@Override
	public List<PsDetails> findAll(int start, int end,
		OrderByComparator<PsDetails> orderByComparator,
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

		List<PsDetails> list = null;

		if (retrieveFromCache) {
			list = (List<PsDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PSDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PSDETAILS;

				if (pagination) {
					sql = sql.concat(PsDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PsDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ps detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PsDetails psDetails : findAll()) {
			remove(psDetails);
		}
	}

	/**
	 * Returns the number of ps detailses.
	 *
	 * @return the number of ps detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PSDETAILS);

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
		return PsDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ps details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PsDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PSDETAILS = "SELECT psDetails FROM PsDetails psDetails";
	private static final String _SQL_SELECT_PSDETAILS_WHERE_PKS_IN = "SELECT psDetails FROM PsDetails psDetails WHERE PS_DETAILS_SID IN (";
	private static final String _SQL_SELECT_PSDETAILS_WHERE = "SELECT psDetails FROM PsDetails psDetails WHERE ";
	private static final String _SQL_COUNT_PSDETAILS = "SELECT COUNT(psDetails) FROM PsDetails psDetails";
	private static final String _SQL_COUNT_PSDETAILS_WHERE = "SELECT COUNT(psDetails) FROM PsDetails psDetails WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "psDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PsDetails exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PsDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"nepFormula", "price", "itemMasterSid", "resetType",
				"priceProtectionStartDate", "resetDate", "basePrice",
				"itemPsAttachedDate", "brandMasterSid", "status", "modifiedDate",
				"itemPsAttachedStatus", "revisionDate", "priceTolerance",
				"createdDate", "createdBy", "source", "psDetailsSid",
				"psModelSid", "suggestedPrice", "netPriceTypeFormula",
				"priceProtectionPriceType", "modifiedBy", "inboundStatus",
				"contractPrice", "ifpModelSid", "priceToleranceType",
				"maxIncrementalChange", "itemPricingQualifierSid",
				"contractPriceEndDate", "nep", "contractPriceStartDate",
				"priceToleranceFrequency", "priceProtectionEndDate",
				"priceProtectionStatus", "recordLockStatus", "resetEligible",
				"batchId", "priceToleranceInterval", "netPriceType",
				"priceRevision", "resetFrequency", "resetInterval",
				"basePriceType", "basePriceEntry", "basePriceDate",
				"netBasePrice", "basePriceDdlb", "subsequentPeriodPriceType",
				"netSubsequentPeriodPrice", "netSubsequentPriceFormulaId",
				"resetPriceType", "netResetPriceType", "netResetPriceFormulaId",
				"netBasePriceFormulaId"
			});
}