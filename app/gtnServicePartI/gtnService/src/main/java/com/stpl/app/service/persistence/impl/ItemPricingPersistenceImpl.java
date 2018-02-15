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

import com.stpl.app.exception.NoSuchItemPricingException;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.impl.ItemPricingImpl;
import com.stpl.app.model.impl.ItemPricingModelImpl;
import com.stpl.app.service.persistence.ItemPricingPersistence;

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
 * The persistence implementation for the item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingPersistence
 * @see com.stpl.app.service.persistence.ItemPricingUtil
 * @generated
 */
@ProviderType
public class ItemPricingPersistenceImpl extends BasePersistenceImpl<ItemPricing>
	implements ItemPricingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemPricingUtil} to access the item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemPricingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICING =
		new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemPricing",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING =
		new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByItemPricing",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Date.class.getName()
			},
			ItemPricingModelImpl.ITEMMASTERSID_COLUMN_BITMASK |
			ItemPricingModelImpl.ITEMUOM_COLUMN_BITMASK |
			ItemPricingModelImpl.ITEMPRICINGQUALIFIERSID_COLUMN_BITMASK |
			ItemPricingModelImpl.PRICINGCODESTATUS_COLUMN_BITMASK |
			ItemPricingModelImpl.STARTDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMPRICING = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByItemPricing",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				Date.class.getName()
			});

	/**
	 * Returns all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @return the matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate) {
		return findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @return the range of matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		int start, int end) {
		return findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		int start, int end, OrderByComparator<ItemPricing> orderByComparator) {
		return findByItemPricing(itemMasterSid, itemUom,
			itemPricingQualifierSid, pricingCodeStatus, startDate, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		int start, int end, OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING;
			finderArgs = new Object[] {
					itemMasterSid, itemUom, itemPricingQualifierSid,
					pricingCodeStatus, startDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICING;
			finderArgs = new Object[] {
					itemMasterSid, itemUom, itemPricingQualifierSid,
					pricingCodeStatus, startDate,
					
					start, end, orderByComparator
				};
		}

		List<ItemPricing> list = null;

		if (retrieveFromCache) {
			list = (List<ItemPricing>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemPricing itemPricing : list) {
					if ((itemMasterSid != itemPricing.getItemMasterSid()) ||
							(itemUom != itemPricing.getItemUom()) ||
							(itemPricingQualifierSid != itemPricing.getItemPricingQualifierSid()) ||
							(pricingCodeStatus != itemPricing.getPricingCodeStatus()) ||
							!Objects.equals(startDate,
								itemPricing.getStartDate())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_ITEMPRICING_WHERE);

			query.append(_FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2);

			query.append(_FINDER_COLUMN_ITEMPRICING_ITEMUOM_2);

			query.append(_FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2);

			query.append(_FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemMasterSid);

				qPos.add(itemUom);

				qPos.add(itemPricingQualifierSid);

				qPos.add(pricingCodeStatus);

				if (bindStartDate) {
					qPos.add(new Timestamp(startDate.getTime()));
				}

				if (!pagination) {
					list = (List<ItemPricing>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemPricing>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item pricing
	 * @throws NoSuchItemPricingException if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing findByItemPricing_First(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = fetchByItemPricing_First(itemMasterSid,
				itemUom, itemPricingQualifierSid, pricingCodeStatus, startDate,
				orderByComparator);

		if (itemPricing != null) {
			return itemPricing;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(", itemUom=");
		msg.append(itemUom);

		msg.append(", itemPricingQualifierSid=");
		msg.append(itemPricingQualifierSid);

		msg.append(", pricingCodeStatus=");
		msg.append(pricingCodeStatus);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemPricingException(msg.toString());
	}

	/**
	 * Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing fetchByItemPricing_First(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator) {
		List<ItemPricing> list = findByItemPricing(itemMasterSid, itemUom,
				itemPricingQualifierSid, pricingCodeStatus, startDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item pricing
	 * @throws NoSuchItemPricingException if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing findByItemPricing_Last(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = fetchByItemPricing_Last(itemMasterSid,
				itemUom, itemPricingQualifierSid, pricingCodeStatus, startDate,
				orderByComparator);

		if (itemPricing != null) {
			return itemPricing;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(", itemUom=");
		msg.append(itemUom);

		msg.append(", itemPricingQualifierSid=");
		msg.append(itemPricingQualifierSid);

		msg.append(", pricingCodeStatus=");
		msg.append(pricingCodeStatus);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemPricingException(msg.toString());
	}

	/**
	 * Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing fetchByItemPricing_Last(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator) {
		int count = countByItemPricing(itemMasterSid, itemUom,
				itemPricingQualifierSid, pricingCodeStatus, startDate);

		if (count == 0) {
			return null;
		}

		List<ItemPricing> list = findByItemPricing(itemMasterSid, itemUom,
				itemPricingQualifierSid, pricingCodeStatus, startDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemPricingSid the primary key of the current item pricing
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item pricing
	 * @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing[] findByItemPricing_PrevAndNext(int itemPricingSid,
		int itemMasterSid, int itemUom, int itemPricingQualifierSid,
		int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = findByPrimaryKey(itemPricingSid);

		Session session = null;

		try {
			session = openSession();

			ItemPricing[] array = new ItemPricingImpl[3];

			array[0] = getByItemPricing_PrevAndNext(session, itemPricing,
					itemMasterSid, itemUom, itemPricingQualifierSid,
					pricingCodeStatus, startDate, orderByComparator, true);

			array[1] = itemPricing;

			array[2] = getByItemPricing_PrevAndNext(session, itemPricing,
					itemMasterSid, itemUom, itemPricingQualifierSid,
					pricingCodeStatus, startDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemPricing getByItemPricing_PrevAndNext(Session session,
		ItemPricing itemPricing, int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate,
		OrderByComparator<ItemPricing> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(8 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(7);
		}

		query.append(_SQL_SELECT_ITEMPRICING_WHERE);

		query.append(_FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2);

		query.append(_FINDER_COLUMN_ITEMPRICING_ITEMUOM_2);

		query.append(_FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2);

		query.append(_FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2);

		boolean bindStartDate = false;

		if (startDate == null) {
			query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_1);
		}
		else {
			bindStartDate = true;

			query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_2);
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
			query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(itemMasterSid);

		qPos.add(itemUom);

		qPos.add(itemPricingQualifierSid);

		qPos.add(pricingCodeStatus);

		if (bindStartDate) {
			qPos.add(new Timestamp(startDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemPricing);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemPricing> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63; from the database.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 */
	@Override
	public void removeByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate) {
		for (ItemPricing itemPricing : findByItemPricing(itemMasterSid,
				itemUom, itemPricingQualifierSid, pricingCodeStatus, startDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemPricing);
		}
	}

	/**
	 * Returns the number of item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param itemUom the item uom
	 * @param itemPricingQualifierSid the item pricing qualifier sid
	 * @param pricingCodeStatus the pricing code status
	 * @param startDate the start date
	 * @return the number of matching item pricings
	 */
	@Override
	public int countByItemPricing(int itemMasterSid, int itemUom,
		int itemPricingQualifierSid, int pricingCodeStatus, Date startDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMPRICING;

		Object[] finderArgs = new Object[] {
				itemMasterSid, itemUom, itemPricingQualifierSid,
				pricingCodeStatus, startDate
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_ITEMPRICING_WHERE);

			query.append(_FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2);

			query.append(_FINDER_COLUMN_ITEMPRICING_ITEMUOM_2);

			query.append(_FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2);

			query.append(_FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2);

			boolean bindStartDate = false;

			if (startDate == null) {
				query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				query.append(_FINDER_COLUMN_ITEMPRICING_STARTDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemMasterSid);

				qPos.add(itemUom);

				qPos.add(itemPricingQualifierSid);

				qPos.add(pricingCodeStatus);

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

	private static final String _FINDER_COLUMN_ITEMPRICING_ITEMMASTERSID_2 = "itemPricing.itemMasterSid = ? AND ";
	private static final String _FINDER_COLUMN_ITEMPRICING_ITEMUOM_2 = "itemPricing.itemUom = ? AND ";
	private static final String _FINDER_COLUMN_ITEMPRICING_ITEMPRICINGQUALIFIERSID_2 =
		"itemPricing.itemPricingQualifierSid = ? AND ";
	private static final String _FINDER_COLUMN_ITEMPRICING_PRICINGCODESTATUS_2 = "itemPricing.pricingCodeStatus = ? AND ";
	private static final String _FINDER_COLUMN_ITEMPRICING_STARTDATE_1 = "itemPricing.startDate IS NULL";
	private static final String _FINDER_COLUMN_ITEMPRICING_STARTDATE_2 = "itemPricing.startDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICINGDETAILS =
		new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByItemPricingDetails",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS =
		new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, ItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByItemPricingDetails",
			new String[] { Integer.class.getName() },
			ItemPricingModelImpl.ITEMMASTERSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS = new FinderPath(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByItemPricingDetails",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the item pricings where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @return the matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricingDetails(int itemMasterSid) {
		return findByItemPricingDetails(itemMasterSid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item pricings where itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @return the range of matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricingDetails(int itemMasterSid,
		int start, int end) {
		return findByItemPricingDetails(itemMasterSid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the item pricings where itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricingDetails(int itemMasterSid,
		int start, int end, OrderByComparator<ItemPricing> orderByComparator) {
		return findByItemPricingDetails(itemMasterSid, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item pricings where itemMasterSid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param itemMasterSid the item master sid
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching item pricings
	 */
	@Override
	public List<ItemPricing> findByItemPricingDetails(int itemMasterSid,
		int start, int end, OrderByComparator<ItemPricing> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS;
			finderArgs = new Object[] { itemMasterSid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITEMPRICINGDETAILS;
			finderArgs = new Object[] {
					itemMasterSid,
					
					start, end, orderByComparator
				};
		}

		List<ItemPricing> list = null;

		if (retrieveFromCache) {
			list = (List<ItemPricing>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ItemPricing itemPricing : list) {
					if ((itemMasterSid != itemPricing.getItemMasterSid())) {
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

			query.append(_SQL_SELECT_ITEMPRICING_WHERE);

			query.append(_FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemMasterSid);

				if (!pagination) {
					list = (List<ItemPricing>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemPricing>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item pricing
	 * @throws NoSuchItemPricingException if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing findByItemPricingDetails_First(int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = fetchByItemPricingDetails_First(itemMasterSid,
				orderByComparator);

		if (itemPricing != null) {
			return itemPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemPricingException(msg.toString());
	}

	/**
	 * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing fetchByItemPricingDetails_First(int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator) {
		List<ItemPricing> list = findByItemPricingDetails(itemMasterSid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item pricing
	 * @throws NoSuchItemPricingException if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing findByItemPricingDetails_Last(int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = fetchByItemPricingDetails_Last(itemMasterSid,
				orderByComparator);

		if (itemPricing != null) {
			return itemPricing;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itemMasterSid=");
		msg.append(itemMasterSid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchItemPricingException(msg.toString());
	}

	/**
	 * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
	 */
	@Override
	public ItemPricing fetchByItemPricingDetails_Last(int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator) {
		int count = countByItemPricingDetails(itemMasterSid);

		if (count == 0) {
			return null;
		}

		List<ItemPricing> list = findByItemPricingDetails(itemMasterSid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63;.
	 *
	 * @param itemPricingSid the primary key of the current item pricing
	 * @param itemMasterSid the item master sid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next item pricing
	 * @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing[] findByItemPricingDetails_PrevAndNext(
		int itemPricingSid, int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = findByPrimaryKey(itemPricingSid);

		Session session = null;

		try {
			session = openSession();

			ItemPricing[] array = new ItemPricingImpl[3];

			array[0] = getByItemPricingDetails_PrevAndNext(session,
					itemPricing, itemMasterSid, orderByComparator, true);

			array[1] = itemPricing;

			array[2] = getByItemPricingDetails_PrevAndNext(session,
					itemPricing, itemMasterSid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ItemPricing getByItemPricingDetails_PrevAndNext(Session session,
		ItemPricing itemPricing, int itemMasterSid,
		OrderByComparator<ItemPricing> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ITEMPRICING_WHERE);

		query.append(_FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2);

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
			query.append(ItemPricingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(itemMasterSid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(itemPricing);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ItemPricing> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the item pricings where itemMasterSid = &#63; from the database.
	 *
	 * @param itemMasterSid the item master sid
	 */
	@Override
	public void removeByItemPricingDetails(int itemMasterSid) {
		for (ItemPricing itemPricing : findByItemPricingDetails(itemMasterSid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(itemPricing);
		}
	}

	/**
	 * Returns the number of item pricings where itemMasterSid = &#63;.
	 *
	 * @param itemMasterSid the item master sid
	 * @return the number of matching item pricings
	 */
	@Override
	public int countByItemPricingDetails(int itemMasterSid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS;

		Object[] finderArgs = new Object[] { itemMasterSid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMPRICING_WHERE);

			query.append(_FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(itemMasterSid);

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

	private static final String _FINDER_COLUMN_ITEMPRICINGDETAILS_ITEMMASTERSID_2 =
		"itemPricing.itemMasterSid = ?";

	public ItemPricingPersistenceImpl() {
		setModelClass(ItemPricing.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemPricingQualifierSid",
				"ITEM_PRICING_QUALIFIER_SID");
			dbColumnNames.put("itemPrice", "ITEM_PRICE");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemUom", "ITEM_UOM");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("itemPricingSid", "ITEM_PRICING_SID");
			dbColumnNames.put("pricingCodeStatus", "PRICING_CODE_STATUS");
			dbColumnNames.put("itemPricePrecision", "ITEM_PRICE_PRECISION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the item pricing in the entity cache if it is enabled.
	 *
	 * @param itemPricing the item pricing
	 */
	@Override
	public void cacheResult(ItemPricing itemPricing) {
		entityCache.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingImpl.class, itemPricing.getPrimaryKey(), itemPricing);

		itemPricing.resetOriginalValues();
	}

	/**
	 * Caches the item pricings in the entity cache if it is enabled.
	 *
	 * @param itemPricings the item pricings
	 */
	@Override
	public void cacheResult(List<ItemPricing> itemPricings) {
		for (ItemPricing itemPricing : itemPricings) {
			if (entityCache.getResult(
						ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
						ItemPricingImpl.class, itemPricing.getPrimaryKey()) == null) {
				cacheResult(itemPricing);
			}
			else {
				itemPricing.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item pricings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemPricingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item pricing.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemPricing itemPricing) {
		entityCache.removeResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingImpl.class, itemPricing.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ItemPricing> itemPricings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemPricing itemPricing : itemPricings) {
			entityCache.removeResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
				ItemPricingImpl.class, itemPricing.getPrimaryKey());
		}
	}

	/**
	 * Creates a new item pricing with the primary key. Does not add the item pricing to the database.
	 *
	 * @param itemPricingSid the primary key for the new item pricing
	 * @return the new item pricing
	 */
	@Override
	public ItemPricing create(int itemPricingSid) {
		ItemPricing itemPricing = new ItemPricingImpl();

		itemPricing.setNew(true);
		itemPricing.setPrimaryKey(itemPricingSid);

		return itemPricing;
	}

	/**
	 * Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemPricingSid the primary key of the item pricing
	 * @return the item pricing that was removed
	 * @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing remove(int itemPricingSid)
		throws NoSuchItemPricingException {
		return remove((Serializable)itemPricingSid);
	}

	/**
	 * Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item pricing
	 * @return the item pricing that was removed
	 * @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing remove(Serializable primaryKey)
		throws NoSuchItemPricingException {
		Session session = null;

		try {
			session = openSession();

			ItemPricing itemPricing = (ItemPricing)session.get(ItemPricingImpl.class,
					primaryKey);

			if (itemPricing == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemPricing);
		}
		catch (NoSuchItemPricingException nsee) {
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
	protected ItemPricing removeImpl(ItemPricing itemPricing) {
		itemPricing = toUnwrappedModel(itemPricing);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemPricing)) {
				itemPricing = (ItemPricing)session.get(ItemPricingImpl.class,
						itemPricing.getPrimaryKeyObj());
			}

			if (itemPricing != null) {
				session.delete(itemPricing);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemPricing != null) {
			clearCache(itemPricing);
		}

		return itemPricing;
	}

	@Override
	public ItemPricing updateImpl(ItemPricing itemPricing) {
		itemPricing = toUnwrappedModel(itemPricing);

		boolean isNew = itemPricing.isNew();

		ItemPricingModelImpl itemPricingModelImpl = (ItemPricingModelImpl)itemPricing;

		Session session = null;

		try {
			session = openSession();

			if (itemPricing.isNew()) {
				session.save(itemPricing);

				itemPricing.setNew(false);
			}
			else {
				itemPricing = (ItemPricing)session.merge(itemPricing);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemPricingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					itemPricingModelImpl.getItemMasterSid(),
					itemPricingModelImpl.getItemUom(),
					itemPricingModelImpl.getItemPricingQualifierSid(),
					itemPricingModelImpl.getPricingCodeStatus(),
					itemPricingModelImpl.getStartDate()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICING, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING,
				args);

			args = new Object[] { itemPricingModelImpl.getItemMasterSid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((itemPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemPricingModelImpl.getOriginalItemMasterSid(),
						itemPricingModelImpl.getOriginalItemUom(),
						itemPricingModelImpl.getOriginalItemPricingQualifierSid(),
						itemPricingModelImpl.getOriginalPricingCodeStatus(),
						itemPricingModelImpl.getOriginalStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICING, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING,
					args);

				args = new Object[] {
						itemPricingModelImpl.getItemMasterSid(),
						itemPricingModelImpl.getItemUom(),
						itemPricingModelImpl.getItemPricingQualifierSid(),
						itemPricingModelImpl.getPricingCodeStatus(),
						itemPricingModelImpl.getStartDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICING, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICING,
					args);
			}

			if ((itemPricingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						itemPricingModelImpl.getOriginalItemMasterSid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS,
					args);

				args = new Object[] { itemPricingModelImpl.getItemMasterSid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGDETAILS,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITEMPRICINGDETAILS,
					args);
			}
		}

		entityCache.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingImpl.class, itemPricing.getPrimaryKey(), itemPricing,
			false);

		itemPricing.resetOriginalValues();

		return itemPricing;
	}

	protected ItemPricing toUnwrappedModel(ItemPricing itemPricing) {
		if (itemPricing instanceof ItemPricingImpl) {
			return itemPricing;
		}

		ItemPricingImpl itemPricingImpl = new ItemPricingImpl();

		itemPricingImpl.setNew(itemPricing.isNew());
		itemPricingImpl.setPrimaryKey(itemPricing.getPrimaryKey());

		itemPricingImpl.setItemMasterSid(itemPricing.getItemMasterSid());
		itemPricingImpl.setItemPricingQualifierSid(itemPricing.getItemPricingQualifierSid());
		itemPricingImpl.setItemPrice(itemPricing.getItemPrice());
		itemPricingImpl.setEndDate(itemPricing.getEndDate());
		itemPricingImpl.setModifiedDate(itemPricing.getModifiedDate());
		itemPricingImpl.setEntityCode(itemPricing.getEntityCode());
		itemPricingImpl.setRecordLockStatus(itemPricing.isRecordLockStatus());
		itemPricingImpl.setStartDate(itemPricing.getStartDate());
		itemPricingImpl.setCreatedDate(itemPricing.getCreatedDate());
		itemPricingImpl.setCreatedBy(itemPricing.getCreatedBy());
		itemPricingImpl.setSource(itemPricing.getSource());
		itemPricingImpl.setBatchId(itemPricing.getBatchId());
		itemPricingImpl.setItemUom(itemPricing.getItemUom());
		itemPricingImpl.setModifiedBy(itemPricing.getModifiedBy());
		itemPricingImpl.setInboundStatus(itemPricing.getInboundStatus());
		itemPricingImpl.setItemPricingSid(itemPricing.getItemPricingSid());
		itemPricingImpl.setPricingCodeStatus(itemPricing.getPricingCodeStatus());
		itemPricingImpl.setItemPricePrecision(itemPricing.getItemPricePrecision());

		return itemPricingImpl;
	}

	/**
	 * Returns the item pricing with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item pricing
	 * @return the item pricing
	 * @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemPricingException {
		ItemPricing itemPricing = fetchByPrimaryKey(primaryKey);

		if (itemPricing == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemPricing;
	}

	/**
	 * Returns the item pricing with the primary key or throws a {@link NoSuchItemPricingException} if it could not be found.
	 *
	 * @param itemPricingSid the primary key of the item pricing
	 * @return the item pricing
	 * @throws NoSuchItemPricingException if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing findByPrimaryKey(int itemPricingSid)
		throws NoSuchItemPricingException {
		return findByPrimaryKey((Serializable)itemPricingSid);
	}

	/**
	 * Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item pricing
	 * @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
				ItemPricingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemPricing itemPricing = (ItemPricing)serializable;

		if (itemPricing == null) {
			Session session = null;

			try {
				session = openSession();

				itemPricing = (ItemPricing)session.get(ItemPricingImpl.class,
						primaryKey);

				if (itemPricing != null) {
					cacheResult(itemPricing);
				}
				else {
					entityCache.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
						ItemPricingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
					ItemPricingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemPricing;
	}

	/**
	 * Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemPricingSid the primary key of the item pricing
	 * @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
	 */
	@Override
	public ItemPricing fetchByPrimaryKey(int itemPricingSid) {
		return fetchByPrimaryKey((Serializable)itemPricingSid);
	}

	@Override
	public Map<Serializable, ItemPricing> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemPricing> map = new HashMap<Serializable, ItemPricing>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemPricing itemPricing = fetchByPrimaryKey(primaryKey);

			if (itemPricing != null) {
				map.put(primaryKey, itemPricing);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
					ItemPricingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemPricing)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMPRICING_WHERE_PKS_IN);

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

			for (ItemPricing itemPricing : (List<ItemPricing>)q.list()) {
				map.put(itemPricing.getPrimaryKeyObj(), itemPricing);

				cacheResult(itemPricing);

				uncachedPrimaryKeys.remove(itemPricing.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemPricingModelImpl.ENTITY_CACHE_ENABLED,
					ItemPricingImpl.class, primaryKey, nullModel);
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
	 * Returns all the item pricings.
	 *
	 * @return the item pricings
	 */
	@Override
	public List<ItemPricing> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @return the range of item pricings
	 */
	@Override
	public List<ItemPricing> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item pricings
	 */
	@Override
	public List<ItemPricing> findAll(int start, int end,
		OrderByComparator<ItemPricing> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item pricings
	 * @param end the upper bound of the range of item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item pricings
	 */
	@Override
	public List<ItemPricing> findAll(int start, int end,
		OrderByComparator<ItemPricing> orderByComparator,
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

		List<ItemPricing> list = null;

		if (retrieveFromCache) {
			list = (List<ItemPricing>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMPRICING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMPRICING;

				if (pagination) {
					sql = sql.concat(ItemPricingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemPricing>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemPricing>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the item pricings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemPricing itemPricing : findAll()) {
			remove(itemPricing);
		}
	}

	/**
	 * Returns the number of item pricings.
	 *
	 * @return the number of item pricings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMPRICING);

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
		return ItemPricingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item pricing persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemPricingImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMPRICING = "SELECT itemPricing FROM ItemPricing itemPricing";
	private static final String _SQL_SELECT_ITEMPRICING_WHERE_PKS_IN = "SELECT itemPricing FROM ItemPricing itemPricing WHERE ITEM_PRICING_SID IN (";
	private static final String _SQL_SELECT_ITEMPRICING_WHERE = "SELECT itemPricing FROM ItemPricing itemPricing WHERE ";
	private static final String _SQL_COUNT_ITEMPRICING = "SELECT COUNT(itemPricing) FROM ItemPricing itemPricing";
	private static final String _SQL_COUNT_ITEMPRICING_WHERE = "SELECT COUNT(itemPricing) FROM ItemPricing itemPricing WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemPricing.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemPricing exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemPricing exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemPricingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemMasterSid", "itemPricingQualifierSid", "itemPrice",
				"endDate", "modifiedDate", "entityCode", "recordLockStatus",
				"startDate", "createdDate", "createdBy", "source", "batchId",
				"itemUom", "modifiedBy", "inboundStatus", "itemPricingSid",
				"pricingCodeStatus", "itemPricePrecision"
			});
}