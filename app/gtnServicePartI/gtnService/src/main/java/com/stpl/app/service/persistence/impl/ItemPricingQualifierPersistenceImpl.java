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

import com.stpl.app.exception.NoSuchItemPricingQualifierException;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.model.impl.ItemPricingQualifierImpl;
import com.stpl.app.model.impl.ItemPricingQualifierModelImpl;
import com.stpl.app.service.persistence.ItemPricingQualifierPersistence;

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
 * The persistence implementation for the item pricing qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingQualifierPersistence
 * @see com.stpl.app.service.persistence.ItemPricingQualifierUtil
 * @generated
 */
@ProviderType
public class ItemPricingQualifierPersistenceImpl extends BasePersistenceImpl<ItemPricingQualifier>
	implements ItemPricingQualifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ItemPricingQualifierUtil} to access the item pricing qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ItemPricingQualifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemPricingQualifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemPricingQualifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME =
		new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED,
			ItemPricingQualifierImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByitemPricingCodeQualifierByName",
			new String[] { String.class.getName() },
			ItemPricingQualifierModelImpl.ITEMPRICINGQUALIFIERNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME =
		new FinderPath(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByitemPricingCodeQualifierByName",
			new String[] { String.class.getName() });

	/**
	 * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or throws a {@link NoSuchItemPricingQualifierException} if it could not be found.
	 *
	 * @param itemPricingQualifierName the item pricing qualifier name
	 * @return the matching item pricing qualifier
	 * @throws NoSuchItemPricingQualifierException if a matching item pricing qualifier could not be found
	 */
	@Override
	public ItemPricingQualifier findByitemPricingCodeQualifierByName(
		String itemPricingQualifierName)
		throws NoSuchItemPricingQualifierException {
		ItemPricingQualifier itemPricingQualifier = fetchByitemPricingCodeQualifierByName(itemPricingQualifierName);

		if (itemPricingQualifier == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itemPricingQualifierName=");
			msg.append(itemPricingQualifierName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchItemPricingQualifierException(msg.toString());
		}

		return itemPricingQualifier;
	}

	/**
	 * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itemPricingQualifierName the item pricing qualifier name
	 * @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
	 */
	@Override
	public ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
		String itemPricingQualifierName) {
		return fetchByitemPricingCodeQualifierByName(itemPricingQualifierName,
			true);
	}

	/**
	 * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itemPricingQualifierName the item pricing qualifier name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
	 */
	@Override
	public ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
		String itemPricingQualifierName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { itemPricingQualifierName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
					finderArgs, this);
		}

		if (result instanceof ItemPricingQualifier) {
			ItemPricingQualifier itemPricingQualifier = (ItemPricingQualifier)result;

			if (!Objects.equals(itemPricingQualifierName,
						itemPricingQualifier.getItemPricingQualifierName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ITEMPRICINGQUALIFIER_WHERE);

			boolean bindItemPricingQualifierName = false;

			if (itemPricingQualifierName == null) {
				query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_1);
			}
			else if (itemPricingQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_3);
			}
			else {
				bindItemPricingQualifierName = true;

				query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemPricingQualifierName) {
					qPos.add(itemPricingQualifierName);
				}

				List<ItemPricingQualifier> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"ItemPricingQualifierPersistenceImpl.fetchByitemPricingCodeQualifierByName(String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ItemPricingQualifier itemPricingQualifier = list.get(0);

					result = itemPricingQualifier;

					cacheResult(itemPricingQualifier);

					if ((itemPricingQualifier.getItemPricingQualifierName() == null) ||
							!itemPricingQualifier.getItemPricingQualifierName()
													 .equals(itemPricingQualifierName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
							finderArgs, itemPricingQualifier);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
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
			return (ItemPricingQualifier)result;
		}
	}

	/**
	 * Removes the item pricing qualifier where itemPricingQualifierName = &#63; from the database.
	 *
	 * @param itemPricingQualifierName the item pricing qualifier name
	 * @return the item pricing qualifier that was removed
	 */
	@Override
	public ItemPricingQualifier removeByitemPricingCodeQualifierByName(
		String itemPricingQualifierName)
		throws NoSuchItemPricingQualifierException {
		ItemPricingQualifier itemPricingQualifier = findByitemPricingCodeQualifierByName(itemPricingQualifierName);

		return remove(itemPricingQualifier);
	}

	/**
	 * Returns the number of item pricing qualifiers where itemPricingQualifierName = &#63;.
	 *
	 * @param itemPricingQualifierName the item pricing qualifier name
	 * @return the number of matching item pricing qualifiers
	 */
	@Override
	public int countByitemPricingCodeQualifierByName(
		String itemPricingQualifierName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME;

		Object[] finderArgs = new Object[] { itemPricingQualifierName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ITEMPRICINGQUALIFIER_WHERE);

			boolean bindItemPricingQualifierName = false;

			if (itemPricingQualifierName == null) {
				query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_1);
			}
			else if (itemPricingQualifierName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_3);
			}
			else {
				bindItemPricingQualifierName = true;

				query.append(_FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindItemPricingQualifierName) {
					qPos.add(itemPricingQualifierName);
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

	private static final String _FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_1 =
		"itemPricingQualifier.itemPricingQualifierName IS NULL";
	private static final String _FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_2 =
		"itemPricingQualifier.itemPricingQualifierName = ?";
	private static final String _FINDER_COLUMN_ITEMPRICINGCODEQUALIFIERBYNAME_ITEMPRICINGQUALIFIERNAME_3 =
		"(itemPricingQualifier.itemPricingQualifierName IS NULL OR itemPricingQualifier.itemPricingQualifierName = '')";

	public ItemPricingQualifierPersistenceImpl() {
		setModelClass(ItemPricingQualifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemPricingQualifierSid",
				"ITEM_PRICING_QUALIFIER_SID");
			dbColumnNames.put("specificEntityCode", "SPECIFIC_ENTITY_CODE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("effectiveDates", "EFFECTIVE_DATES");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("pricingQualifier", "PRICING_QUALIFIER");
			dbColumnNames.put("itemPricingQualifierName",
				"ITEM_PRICING_QUALIFIER_NAME");
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
	 * Caches the item pricing qualifier in the entity cache if it is enabled.
	 *
	 * @param itemPricingQualifier the item pricing qualifier
	 */
	@Override
	public void cacheResult(ItemPricingQualifier itemPricingQualifier) {
		entityCache.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierImpl.class,
			itemPricingQualifier.getPrimaryKey(), itemPricingQualifier);

		finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
			new Object[] { itemPricingQualifier.getItemPricingQualifierName() },
			itemPricingQualifier);

		itemPricingQualifier.resetOriginalValues();
	}

	/**
	 * Caches the item pricing qualifiers in the entity cache if it is enabled.
	 *
	 * @param itemPricingQualifiers the item pricing qualifiers
	 */
	@Override
	public void cacheResult(List<ItemPricingQualifier> itemPricingQualifiers) {
		for (ItemPricingQualifier itemPricingQualifier : itemPricingQualifiers) {
			if (entityCache.getResult(
						ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
						ItemPricingQualifierImpl.class,
						itemPricingQualifier.getPrimaryKey()) == null) {
				cacheResult(itemPricingQualifier);
			}
			else {
				itemPricingQualifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all item pricing qualifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ItemPricingQualifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the item pricing qualifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ItemPricingQualifier itemPricingQualifier) {
		entityCache.removeResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierImpl.class, itemPricingQualifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((ItemPricingQualifierModelImpl)itemPricingQualifier,
			true);
	}

	@Override
	public void clearCache(List<ItemPricingQualifier> itemPricingQualifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ItemPricingQualifier itemPricingQualifier : itemPricingQualifiers) {
			entityCache.removeResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
				ItemPricingQualifierImpl.class,
				itemPricingQualifier.getPrimaryKey());

			clearUniqueFindersCache((ItemPricingQualifierModelImpl)itemPricingQualifier,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		ItemPricingQualifierModelImpl itemPricingQualifierModelImpl) {
		Object[] args = new Object[] {
				itemPricingQualifierModelImpl.getItemPricingQualifierName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
			args, itemPricingQualifierModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ItemPricingQualifierModelImpl itemPricingQualifierModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					itemPricingQualifierModelImpl.getItemPricingQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
				args);
		}

		if ((itemPricingQualifierModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					itemPricingQualifierModelImpl.getOriginalItemPricingQualifierName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_ITEMPRICINGCODEQUALIFIERBYNAME,
				args);
		}
	}

	/**
	 * Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
	 *
	 * @param itemPricingQualifierSid the primary key for the new item pricing qualifier
	 * @return the new item pricing qualifier
	 */
	@Override
	public ItemPricingQualifier create(int itemPricingQualifierSid) {
		ItemPricingQualifier itemPricingQualifier = new ItemPricingQualifierImpl();

		itemPricingQualifier.setNew(true);
		itemPricingQualifier.setPrimaryKey(itemPricingQualifierSid);

		return itemPricingQualifier;
	}

	/**
	 * Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemPricingQualifierSid the primary key of the item pricing qualifier
	 * @return the item pricing qualifier that was removed
	 * @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	 */
	@Override
	public ItemPricingQualifier remove(int itemPricingQualifierSid)
		throws NoSuchItemPricingQualifierException {
		return remove((Serializable)itemPricingQualifierSid);
	}

	/**
	 * Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the item pricing qualifier
	 * @return the item pricing qualifier that was removed
	 * @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	 */
	@Override
	public ItemPricingQualifier remove(Serializable primaryKey)
		throws NoSuchItemPricingQualifierException {
		Session session = null;

		try {
			session = openSession();

			ItemPricingQualifier itemPricingQualifier = (ItemPricingQualifier)session.get(ItemPricingQualifierImpl.class,
					primaryKey);

			if (itemPricingQualifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchItemPricingQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(itemPricingQualifier);
		}
		catch (NoSuchItemPricingQualifierException nsee) {
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
	protected ItemPricingQualifier removeImpl(
		ItemPricingQualifier itemPricingQualifier) {
		itemPricingQualifier = toUnwrappedModel(itemPricingQualifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(itemPricingQualifier)) {
				itemPricingQualifier = (ItemPricingQualifier)session.get(ItemPricingQualifierImpl.class,
						itemPricingQualifier.getPrimaryKeyObj());
			}

			if (itemPricingQualifier != null) {
				session.delete(itemPricingQualifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (itemPricingQualifier != null) {
			clearCache(itemPricingQualifier);
		}

		return itemPricingQualifier;
	}

	@Override
	public ItemPricingQualifier updateImpl(
		ItemPricingQualifier itemPricingQualifier) {
		itemPricingQualifier = toUnwrappedModel(itemPricingQualifier);

		boolean isNew = itemPricingQualifier.isNew();

		ItemPricingQualifierModelImpl itemPricingQualifierModelImpl = (ItemPricingQualifierModelImpl)itemPricingQualifier;

		Session session = null;

		try {
			session = openSession();

			if (itemPricingQualifier.isNew()) {
				session.save(itemPricingQualifier);

				itemPricingQualifier.setNew(false);
			}
			else {
				itemPricingQualifier = (ItemPricingQualifier)session.merge(itemPricingQualifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ItemPricingQualifierModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
			ItemPricingQualifierImpl.class,
			itemPricingQualifier.getPrimaryKey(), itemPricingQualifier, false);

		clearUniqueFindersCache(itemPricingQualifierModelImpl, false);
		cacheUniqueFindersCache(itemPricingQualifierModelImpl);

		itemPricingQualifier.resetOriginalValues();

		return itemPricingQualifier;
	}

	protected ItemPricingQualifier toUnwrappedModel(
		ItemPricingQualifier itemPricingQualifier) {
		if (itemPricingQualifier instanceof ItemPricingQualifierImpl) {
			return itemPricingQualifier;
		}

		ItemPricingQualifierImpl itemPricingQualifierImpl = new ItemPricingQualifierImpl();

		itemPricingQualifierImpl.setNew(itemPricingQualifier.isNew());
		itemPricingQualifierImpl.setPrimaryKey(itemPricingQualifier.getPrimaryKey());

		itemPricingQualifierImpl.setCreatedBy(itemPricingQualifier.getCreatedBy());
		itemPricingQualifierImpl.setItemPricingQualifierSid(itemPricingQualifier.getItemPricingQualifierSid());
		itemPricingQualifierImpl.setSpecificEntityCode(itemPricingQualifier.getSpecificEntityCode());
		itemPricingQualifierImpl.setModifiedBy(itemPricingQualifier.getModifiedBy());
		itemPricingQualifierImpl.setCreatedDate(itemPricingQualifier.getCreatedDate());
		itemPricingQualifierImpl.setBatchId(itemPricingQualifier.getBatchId());
		itemPricingQualifierImpl.setModifiedDate(itemPricingQualifier.getModifiedDate());
		itemPricingQualifierImpl.setEffectiveDates(itemPricingQualifier.getEffectiveDates());
		itemPricingQualifierImpl.setNotes(itemPricingQualifier.getNotes());
		itemPricingQualifierImpl.setRecordLockStatus(itemPricingQualifier.isRecordLockStatus());
		itemPricingQualifierImpl.setSource(itemPricingQualifier.getSource());
		itemPricingQualifierImpl.setPricingQualifier(itemPricingQualifier.getPricingQualifier());
		itemPricingQualifierImpl.setItemPricingQualifierName(itemPricingQualifier.getItemPricingQualifierName());
		itemPricingQualifierImpl.setInboundStatus(itemPricingQualifier.getInboundStatus());

		return itemPricingQualifierImpl;
	}

	/**
	 * Returns the item pricing qualifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the item pricing qualifier
	 * @return the item pricing qualifier
	 * @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	 */
	@Override
	public ItemPricingQualifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchItemPricingQualifierException {
		ItemPricingQualifier itemPricingQualifier = fetchByPrimaryKey(primaryKey);

		if (itemPricingQualifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchItemPricingQualifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return itemPricingQualifier;
	}

	/**
	 * Returns the item pricing qualifier with the primary key or throws a {@link NoSuchItemPricingQualifierException} if it could not be found.
	 *
	 * @param itemPricingQualifierSid the primary key of the item pricing qualifier
	 * @return the item pricing qualifier
	 * @throws NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
	 */
	@Override
	public ItemPricingQualifier findByPrimaryKey(int itemPricingQualifierSid)
		throws NoSuchItemPricingQualifierException {
		return findByPrimaryKey((Serializable)itemPricingQualifierSid);
	}

	/**
	 * Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the item pricing qualifier
	 * @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
	 */
	@Override
	public ItemPricingQualifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
				ItemPricingQualifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ItemPricingQualifier itemPricingQualifier = (ItemPricingQualifier)serializable;

		if (itemPricingQualifier == null) {
			Session session = null;

			try {
				session = openSession();

				itemPricingQualifier = (ItemPricingQualifier)session.get(ItemPricingQualifierImpl.class,
						primaryKey);

				if (itemPricingQualifier != null) {
					cacheResult(itemPricingQualifier);
				}
				else {
					entityCache.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
						ItemPricingQualifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemPricingQualifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return itemPricingQualifier;
	}

	/**
	 * Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemPricingQualifierSid the primary key of the item pricing qualifier
	 * @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
	 */
	@Override
	public ItemPricingQualifier fetchByPrimaryKey(int itemPricingQualifierSid) {
		return fetchByPrimaryKey((Serializable)itemPricingQualifierSid);
	}

	@Override
	public Map<Serializable, ItemPricingQualifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ItemPricingQualifier> map = new HashMap<Serializable, ItemPricingQualifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ItemPricingQualifier itemPricingQualifier = fetchByPrimaryKey(primaryKey);

			if (itemPricingQualifier != null) {
				map.put(primaryKey, itemPricingQualifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemPricingQualifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ItemPricingQualifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ITEMPRICINGQUALIFIER_WHERE_PKS_IN);

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

			for (ItemPricingQualifier itemPricingQualifier : (List<ItemPricingQualifier>)q.list()) {
				map.put(itemPricingQualifier.getPrimaryKeyObj(),
					itemPricingQualifier);

				cacheResult(itemPricingQualifier);

				uncachedPrimaryKeys.remove(itemPricingQualifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ItemPricingQualifierModelImpl.ENTITY_CACHE_ENABLED,
					ItemPricingQualifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the item pricing qualifiers.
	 *
	 * @return the item pricing qualifiers
	 */
	@Override
	public List<ItemPricingQualifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the item pricing qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item pricing qualifiers
	 * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	 * @return the range of item pricing qualifiers
	 */
	@Override
	public List<ItemPricingQualifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the item pricing qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item pricing qualifiers
	 * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of item pricing qualifiers
	 */
	@Override
	public List<ItemPricingQualifier> findAll(int start, int end,
		OrderByComparator<ItemPricingQualifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the item pricing qualifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of item pricing qualifiers
	 * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of item pricing qualifiers
	 */
	@Override
	public List<ItemPricingQualifier> findAll(int start, int end,
		OrderByComparator<ItemPricingQualifier> orderByComparator,
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

		List<ItemPricingQualifier> list = null;

		if (retrieveFromCache) {
			list = (List<ItemPricingQualifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ITEMPRICINGQUALIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ITEMPRICINGQUALIFIER;

				if (pagination) {
					sql = sql.concat(ItemPricingQualifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ItemPricingQualifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ItemPricingQualifier>)QueryUtil.list(q,
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
	 * Removes all the item pricing qualifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ItemPricingQualifier itemPricingQualifier : findAll()) {
			remove(itemPricingQualifier);
		}
	}

	/**
	 * Returns the number of item pricing qualifiers.
	 *
	 * @return the number of item pricing qualifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ITEMPRICINGQUALIFIER);

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
		return ItemPricingQualifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the item pricing qualifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ItemPricingQualifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ITEMPRICINGQUALIFIER = "SELECT itemPricingQualifier FROM ItemPricingQualifier itemPricingQualifier";
	private static final String _SQL_SELECT_ITEMPRICINGQUALIFIER_WHERE_PKS_IN = "SELECT itemPricingQualifier FROM ItemPricingQualifier itemPricingQualifier WHERE ITEM_PRICING_QUALIFIER_SID IN (";
	private static final String _SQL_SELECT_ITEMPRICINGQUALIFIER_WHERE = "SELECT itemPricingQualifier FROM ItemPricingQualifier itemPricingQualifier WHERE ";
	private static final String _SQL_COUNT_ITEMPRICINGQUALIFIER = "SELECT COUNT(itemPricingQualifier) FROM ItemPricingQualifier itemPricingQualifier";
	private static final String _SQL_COUNT_ITEMPRICINGQUALIFIER_WHERE = "SELECT COUNT(itemPricingQualifier) FROM ItemPricingQualifier itemPricingQualifier WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "itemPricingQualifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ItemPricingQualifier exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ItemPricingQualifier exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ItemPricingQualifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "itemPricingQualifierSid", "specificEntityCode",
				"modifiedBy", "createdDate", "batchId", "modifiedDate",
				"effectiveDates", "notes", "recordLockStatus", "source",
				"pricingQualifier", "itemPricingQualifierName", "inboundStatus"
			});
}