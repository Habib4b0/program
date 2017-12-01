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

import com.stpl.app.exception.NoSuchInventoryWdActualMasException;
import com.stpl.app.model.InventoryWdActualMas;
import com.stpl.app.model.impl.InventoryWdActualMasImpl;
import com.stpl.app.model.impl.InventoryWdActualMasModelImpl;
import com.stpl.app.service.persistence.InventoryWdActualMasPersistence;

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
 * The persistence implementation for the inventory wd actual mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdActualMasPersistence
 * @see com.stpl.app.service.persistence.InventoryWdActualMasUtil
 * @generated
 */
@ProviderType
public class InventoryWdActualMasPersistenceImpl extends BasePersistenceImpl<InventoryWdActualMas>
	implements InventoryWdActualMasPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InventoryWdActualMasUtil} to access the inventory wd actual mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InventoryWdActualMasImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
			InventoryWdActualMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
			InventoryWdActualMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public InventoryWdActualMasPersistenceImpl() {
		setModelClass(InventoryWdActualMas.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("quantityOnOrder", "QUANTITY_ON_ORDER");
			dbColumnNames.put("week", "WEEK");
			dbColumnNames.put("amountOnHand", "AMOUNT_ON_HAND");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("inventoryWdActualMasSid",
				"INVENTORY_WD_ACTUAL_MAS_SID");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("day", "DAY");
			dbColumnNames.put("unitsOnHand", "UNITS_ON_HAND");
			dbColumnNames.put("amountReceived", "AMOUNT_RECEIVED");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
			dbColumnNames.put("quantityReceived", "QUANTITY_RECEIVED");
			dbColumnNames.put("unitsWithdrawn", "UNITS_WITHDRAWN");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("amountOnOrder", "AMOUNT_ON_ORDER");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the inventory wd actual mas in the entity cache if it is enabled.
	 *
	 * @param inventoryWdActualMas the inventory wd actual mas
	 */
	@Override
	public void cacheResult(InventoryWdActualMas inventoryWdActualMas) {
		entityCache.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdActualMasImpl.class,
			inventoryWdActualMas.getPrimaryKey(), inventoryWdActualMas);

		inventoryWdActualMas.resetOriginalValues();
	}

	/**
	 * Caches the inventory wd actual mases in the entity cache if it is enabled.
	 *
	 * @param inventoryWdActualMases the inventory wd actual mases
	 */
	@Override
	public void cacheResult(List<InventoryWdActualMas> inventoryWdActualMases) {
		for (InventoryWdActualMas inventoryWdActualMas : inventoryWdActualMases) {
			if (entityCache.getResult(
						InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
						InventoryWdActualMasImpl.class,
						inventoryWdActualMas.getPrimaryKey()) == null) {
				cacheResult(inventoryWdActualMas);
			}
			else {
				inventoryWdActualMas.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all inventory wd actual mases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(InventoryWdActualMasImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the inventory wd actual mas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(InventoryWdActualMas inventoryWdActualMas) {
		entityCache.removeResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdActualMasImpl.class, inventoryWdActualMas.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<InventoryWdActualMas> inventoryWdActualMases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (InventoryWdActualMas inventoryWdActualMas : inventoryWdActualMases) {
			entityCache.removeResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
				InventoryWdActualMasImpl.class,
				inventoryWdActualMas.getPrimaryKey());
		}
	}

	/**
	 * Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
	 *
	 * @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
	 * @return the new inventory wd actual mas
	 */
	@Override
	public InventoryWdActualMas create(int inventoryWdActualMasSid) {
		InventoryWdActualMas inventoryWdActualMas = new InventoryWdActualMasImpl();

		inventoryWdActualMas.setNew(true);
		inventoryWdActualMas.setPrimaryKey(inventoryWdActualMasSid);

		return inventoryWdActualMas;
	}

	/**
	 * Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	 * @return the inventory wd actual mas that was removed
	 * @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public InventoryWdActualMas remove(int inventoryWdActualMasSid)
		throws NoSuchInventoryWdActualMasException {
		return remove((Serializable)inventoryWdActualMasSid);
	}

	/**
	 * Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the inventory wd actual mas
	 * @return the inventory wd actual mas that was removed
	 * @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public InventoryWdActualMas remove(Serializable primaryKey)
		throws NoSuchInventoryWdActualMasException {
		Session session = null;

		try {
			session = openSession();

			InventoryWdActualMas inventoryWdActualMas = (InventoryWdActualMas)session.get(InventoryWdActualMasImpl.class,
					primaryKey);

			if (inventoryWdActualMas == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(inventoryWdActualMas);
		}
		catch (NoSuchInventoryWdActualMasException nsee) {
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
	protected InventoryWdActualMas removeImpl(
		InventoryWdActualMas inventoryWdActualMas) {
		inventoryWdActualMas = toUnwrappedModel(inventoryWdActualMas);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(inventoryWdActualMas)) {
				inventoryWdActualMas = (InventoryWdActualMas)session.get(InventoryWdActualMasImpl.class,
						inventoryWdActualMas.getPrimaryKeyObj());
			}

			if (inventoryWdActualMas != null) {
				session.delete(inventoryWdActualMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (inventoryWdActualMas != null) {
			clearCache(inventoryWdActualMas);
		}

		return inventoryWdActualMas;
	}

	@Override
	public InventoryWdActualMas updateImpl(
		InventoryWdActualMas inventoryWdActualMas) {
		inventoryWdActualMas = toUnwrappedModel(inventoryWdActualMas);

		boolean isNew = inventoryWdActualMas.isNew();

		Session session = null;

		try {
			session = openSession();

			if (inventoryWdActualMas.isNew()) {
				session.save(inventoryWdActualMas);

				inventoryWdActualMas.setNew(false);
			}
			else {
				inventoryWdActualMas = (InventoryWdActualMas)session.merge(inventoryWdActualMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdActualMasImpl.class,
			inventoryWdActualMas.getPrimaryKey(), inventoryWdActualMas, false);

		inventoryWdActualMas.resetOriginalValues();

		return inventoryWdActualMas;
	}

	protected InventoryWdActualMas toUnwrappedModel(
		InventoryWdActualMas inventoryWdActualMas) {
		if (inventoryWdActualMas instanceof InventoryWdActualMasImpl) {
			return inventoryWdActualMas;
		}

		InventoryWdActualMasImpl inventoryWdActualMasImpl = new InventoryWdActualMasImpl();

		inventoryWdActualMasImpl.setNew(inventoryWdActualMas.isNew());
		inventoryWdActualMasImpl.setPrimaryKey(inventoryWdActualMas.getPrimaryKey());

		inventoryWdActualMasImpl.setQuantityOnOrder(inventoryWdActualMas.getQuantityOnOrder());
		inventoryWdActualMasImpl.setWeek(inventoryWdActualMas.getWeek());
		inventoryWdActualMasImpl.setAmountOnHand(inventoryWdActualMas.getAmountOnHand());
		inventoryWdActualMasImpl.setItemMasterSid(inventoryWdActualMas.getItemMasterSid());
		inventoryWdActualMasImpl.setInventoryWdActualMasSid(inventoryWdActualMas.getInventoryWdActualMasSid());
		inventoryWdActualMasImpl.setYear(inventoryWdActualMas.getYear());
		inventoryWdActualMasImpl.setItemId(inventoryWdActualMas.getItemId());
		inventoryWdActualMasImpl.setModifiedDate(inventoryWdActualMas.getModifiedDate());
		inventoryWdActualMasImpl.setOrganizationKey(inventoryWdActualMas.getOrganizationKey());
		inventoryWdActualMasImpl.setSource(inventoryWdActualMas.getSource());
		inventoryWdActualMasImpl.setCreatedBy(inventoryWdActualMas.getCreatedBy());
		inventoryWdActualMasImpl.setCreatedDate(inventoryWdActualMas.getCreatedDate());
		inventoryWdActualMasImpl.setDay(inventoryWdActualMas.getDay());
		inventoryWdActualMasImpl.setUnitsOnHand(inventoryWdActualMas.getUnitsOnHand());
		inventoryWdActualMasImpl.setAmountReceived(inventoryWdActualMas.getAmountReceived());
		inventoryWdActualMasImpl.setItemIdentifier(inventoryWdActualMas.getItemIdentifier());
		inventoryWdActualMasImpl.setModifiedBy(inventoryWdActualMas.getModifiedBy());
		inventoryWdActualMasImpl.setInboundStatus(inventoryWdActualMas.getInboundStatus());
		inventoryWdActualMasImpl.setMonth(inventoryWdActualMas.getMonth());
		inventoryWdActualMasImpl.setAmountWithdrawn(inventoryWdActualMas.getAmountWithdrawn());
		inventoryWdActualMasImpl.setQuantityReceived(inventoryWdActualMas.getQuantityReceived());
		inventoryWdActualMasImpl.setUnitsWithdrawn(inventoryWdActualMas.getUnitsWithdrawn());
		inventoryWdActualMasImpl.setCountry(inventoryWdActualMas.getCountry());
		inventoryWdActualMasImpl.setRecordLockStatus(inventoryWdActualMas.isRecordLockStatus());
		inventoryWdActualMasImpl.setItemIdentifierCodeQualifier(inventoryWdActualMas.getItemIdentifierCodeQualifier());
		inventoryWdActualMasImpl.setBatchId(inventoryWdActualMas.getBatchId());
		inventoryWdActualMasImpl.setAmountOnOrder(inventoryWdActualMas.getAmountOnOrder());

		return inventoryWdActualMasImpl;
	}

	/**
	 * Returns the inventory wd actual mas with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the inventory wd actual mas
	 * @return the inventory wd actual mas
	 * @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public InventoryWdActualMas findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInventoryWdActualMasException {
		InventoryWdActualMas inventoryWdActualMas = fetchByPrimaryKey(primaryKey);

		if (inventoryWdActualMas == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return inventoryWdActualMas;
	}

	/**
	 * Returns the inventory wd actual mas with the primary key or throws a {@link NoSuchInventoryWdActualMasException} if it could not be found.
	 *
	 * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	 * @return the inventory wd actual mas
	 * @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public InventoryWdActualMas findByPrimaryKey(int inventoryWdActualMasSid)
		throws NoSuchInventoryWdActualMasException {
		return findByPrimaryKey((Serializable)inventoryWdActualMasSid);
	}

	/**
	 * Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the inventory wd actual mas
	 * @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public InventoryWdActualMas fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
				InventoryWdActualMasImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		InventoryWdActualMas inventoryWdActualMas = (InventoryWdActualMas)serializable;

		if (inventoryWdActualMas == null) {
			Session session = null;

			try {
				session = openSession();

				inventoryWdActualMas = (InventoryWdActualMas)session.get(InventoryWdActualMasImpl.class,
						primaryKey);

				if (inventoryWdActualMas != null) {
					cacheResult(inventoryWdActualMas);
				}
				else {
					entityCache.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
						InventoryWdActualMasImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
					InventoryWdActualMasImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return inventoryWdActualMas;
	}

	/**
	 * Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	 * @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public InventoryWdActualMas fetchByPrimaryKey(int inventoryWdActualMasSid) {
		return fetchByPrimaryKey((Serializable)inventoryWdActualMasSid);
	}

	@Override
	public Map<Serializable, InventoryWdActualMas> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, InventoryWdActualMas> map = new HashMap<Serializable, InventoryWdActualMas>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			InventoryWdActualMas inventoryWdActualMas = fetchByPrimaryKey(primaryKey);

			if (inventoryWdActualMas != null) {
				map.put(primaryKey, inventoryWdActualMas);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
					InventoryWdActualMasImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (InventoryWdActualMas)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_INVENTORYWDACTUALMAS_WHERE_PKS_IN);

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

			for (InventoryWdActualMas inventoryWdActualMas : (List<InventoryWdActualMas>)q.list()) {
				map.put(inventoryWdActualMas.getPrimaryKeyObj(),
					inventoryWdActualMas);

				cacheResult(inventoryWdActualMas);

				uncachedPrimaryKeys.remove(inventoryWdActualMas.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(InventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
					InventoryWdActualMasImpl.class, primaryKey, nullModel);
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
	 * Returns all the inventory wd actual mases.
	 *
	 * @return the inventory wd actual mases
	 */
	@Override
	public List<InventoryWdActualMas> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the inventory wd actual mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of inventory wd actual mases
	 * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	 * @return the range of inventory wd actual mases
	 */
	@Override
	public List<InventoryWdActualMas> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the inventory wd actual mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of inventory wd actual mases
	 * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of inventory wd actual mases
	 */
	@Override
	public List<InventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<InventoryWdActualMas> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the inventory wd actual mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of inventory wd actual mases
	 * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of inventory wd actual mases
	 */
	@Override
	public List<InventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<InventoryWdActualMas> orderByComparator,
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

		List<InventoryWdActualMas> list = null;

		if (retrieveFromCache) {
			list = (List<InventoryWdActualMas>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_INVENTORYWDACTUALMAS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INVENTORYWDACTUALMAS;

				if (pagination) {
					sql = sql.concat(InventoryWdActualMasModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<InventoryWdActualMas>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<InventoryWdActualMas>)QueryUtil.list(q,
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
	 * Removes all the inventory wd actual mases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (InventoryWdActualMas inventoryWdActualMas : findAll()) {
			remove(inventoryWdActualMas);
		}
	}

	/**
	 * Returns the number of inventory wd actual mases.
	 *
	 * @return the number of inventory wd actual mases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_INVENTORYWDACTUALMAS);

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
		return InventoryWdActualMasModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the inventory wd actual mas persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(InventoryWdActualMasImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_INVENTORYWDACTUALMAS = "SELECT inventoryWdActualMas FROM InventoryWdActualMas inventoryWdActualMas";
	private static final String _SQL_SELECT_INVENTORYWDACTUALMAS_WHERE_PKS_IN = "SELECT inventoryWdActualMas FROM InventoryWdActualMas inventoryWdActualMas WHERE INVENTORY_WD_ACTUAL_MAS_SID IN (";
	private static final String _SQL_COUNT_INVENTORYWDACTUALMAS = "SELECT COUNT(inventoryWdActualMas) FROM InventoryWdActualMas inventoryWdActualMas";
	private static final String _ORDER_BY_ENTITY_ALIAS = "inventoryWdActualMas.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InventoryWdActualMas exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(InventoryWdActualMasPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"quantityOnOrder", "week", "amountOnHand", "itemMasterSid",
				"inventoryWdActualMasSid", "year", "itemId", "modifiedDate",
				"organizationKey", "source", "createdBy", "createdDate", "day",
				"unitsOnHand", "amountReceived", "itemIdentifier", "modifiedBy",
				"inboundStatus", "month", "amountWithdrawn", "quantityReceived",
				"unitsWithdrawn", "country", "recordLockStatus",
				"itemIdentifierCodeQualifier", "batchId", "amountOnOrder"
			});
}