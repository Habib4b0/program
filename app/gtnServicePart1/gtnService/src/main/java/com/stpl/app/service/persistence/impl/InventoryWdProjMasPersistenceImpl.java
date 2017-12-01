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

import com.stpl.app.exception.NoSuchInventoryWdProjMasException;
import com.stpl.app.model.InventoryWdProjMas;
import com.stpl.app.model.impl.InventoryWdProjMasImpl;
import com.stpl.app.model.impl.InventoryWdProjMasModelImpl;
import com.stpl.app.service.persistence.InventoryWdProjMasPersistence;

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
 * The persistence implementation for the inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdProjMasPersistence
 * @see com.stpl.app.service.persistence.InventoryWdProjMasUtil
 * @generated
 */
@ProviderType
public class InventoryWdProjMasPersistenceImpl extends BasePersistenceImpl<InventoryWdProjMas>
	implements InventoryWdProjMasPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InventoryWdProjMasUtil} to access the inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = InventoryWdProjMasImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
			InventoryWdProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
			InventoryWdProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public InventoryWdProjMasPersistenceImpl() {
		setModelClass(InventoryWdProjMas.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("week", "WEEK");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("unitsWithdrawn", "UNITS_WITHDRAWN");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("inventoryWdProjMasSid",
				"INVENTORY_WD_PROJ_MAS_SID");
			dbColumnNames.put("day", "DAY");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("amountWithdrawn", "AMOUNT_WITHDRAWN");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the inventory wd proj mas in the entity cache if it is enabled.
	 *
	 * @param inventoryWdProjMas the inventory wd proj mas
	 */
	@Override
	public void cacheResult(InventoryWdProjMas inventoryWdProjMas) {
		entityCache.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey(),
			inventoryWdProjMas);

		inventoryWdProjMas.resetOriginalValues();
	}

	/**
	 * Caches the inventory wd proj mases in the entity cache if it is enabled.
	 *
	 * @param inventoryWdProjMases the inventory wd proj mases
	 */
	@Override
	public void cacheResult(List<InventoryWdProjMas> inventoryWdProjMases) {
		for (InventoryWdProjMas inventoryWdProjMas : inventoryWdProjMases) {
			if (entityCache.getResult(
						InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
						InventoryWdProjMasImpl.class,
						inventoryWdProjMas.getPrimaryKey()) == null) {
				cacheResult(inventoryWdProjMas);
			}
			else {
				inventoryWdProjMas.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all inventory wd proj mases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(InventoryWdProjMasImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the inventory wd proj mas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(InventoryWdProjMas inventoryWdProjMas) {
		entityCache.removeResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<InventoryWdProjMas> inventoryWdProjMases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (InventoryWdProjMas inventoryWdProjMas : inventoryWdProjMases) {
			entityCache.removeResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
				InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey());
		}
	}

	/**
	 * Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
	 *
	 * @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
	 * @return the new inventory wd proj mas
	 */
	@Override
	public InventoryWdProjMas create(int inventoryWdProjMasSid) {
		InventoryWdProjMas inventoryWdProjMas = new InventoryWdProjMasImpl();

		inventoryWdProjMas.setNew(true);
		inventoryWdProjMas.setPrimaryKey(inventoryWdProjMasSid);

		return inventoryWdProjMas;
	}

	/**
	 * Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	 * @return the inventory wd proj mas that was removed
	 * @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public InventoryWdProjMas remove(int inventoryWdProjMasSid)
		throws NoSuchInventoryWdProjMasException {
		return remove((Serializable)inventoryWdProjMasSid);
	}

	/**
	 * Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the inventory wd proj mas
	 * @return the inventory wd proj mas that was removed
	 * @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public InventoryWdProjMas remove(Serializable primaryKey)
		throws NoSuchInventoryWdProjMasException {
		Session session = null;

		try {
			session = openSession();

			InventoryWdProjMas inventoryWdProjMas = (InventoryWdProjMas)session.get(InventoryWdProjMasImpl.class,
					primaryKey);

			if (inventoryWdProjMas == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(inventoryWdProjMas);
		}
		catch (NoSuchInventoryWdProjMasException nsee) {
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
	protected InventoryWdProjMas removeImpl(
		InventoryWdProjMas inventoryWdProjMas) {
		inventoryWdProjMas = toUnwrappedModel(inventoryWdProjMas);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(inventoryWdProjMas)) {
				inventoryWdProjMas = (InventoryWdProjMas)session.get(InventoryWdProjMasImpl.class,
						inventoryWdProjMas.getPrimaryKeyObj());
			}

			if (inventoryWdProjMas != null) {
				session.delete(inventoryWdProjMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (inventoryWdProjMas != null) {
			clearCache(inventoryWdProjMas);
		}

		return inventoryWdProjMas;
	}

	@Override
	public InventoryWdProjMas updateImpl(InventoryWdProjMas inventoryWdProjMas) {
		inventoryWdProjMas = toUnwrappedModel(inventoryWdProjMas);

		boolean isNew = inventoryWdProjMas.isNew();

		Session session = null;

		try {
			session = openSession();

			if (inventoryWdProjMas.isNew()) {
				session.save(inventoryWdProjMas);

				inventoryWdProjMas.setNew(false);
			}
			else {
				inventoryWdProjMas = (InventoryWdProjMas)session.merge(inventoryWdProjMas);
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

		entityCache.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			InventoryWdProjMasImpl.class, inventoryWdProjMas.getPrimaryKey(),
			inventoryWdProjMas, false);

		inventoryWdProjMas.resetOriginalValues();

		return inventoryWdProjMas;
	}

	protected InventoryWdProjMas toUnwrappedModel(
		InventoryWdProjMas inventoryWdProjMas) {
		if (inventoryWdProjMas instanceof InventoryWdProjMasImpl) {
			return inventoryWdProjMas;
		}

		InventoryWdProjMasImpl inventoryWdProjMasImpl = new InventoryWdProjMasImpl();

		inventoryWdProjMasImpl.setNew(inventoryWdProjMas.isNew());
		inventoryWdProjMasImpl.setPrimaryKey(inventoryWdProjMas.getPrimaryKey());

		inventoryWdProjMasImpl.setWeek(inventoryWdProjMas.getWeek());
		inventoryWdProjMasImpl.setItemMasterSid(inventoryWdProjMas.getItemMasterSid());
		inventoryWdProjMasImpl.setUnitsWithdrawn(inventoryWdProjMas.getUnitsWithdrawn());
		inventoryWdProjMasImpl.setCountry(inventoryWdProjMas.getCountry());
		inventoryWdProjMasImpl.setYear(inventoryWdProjMas.getYear());
		inventoryWdProjMasImpl.setItemId(inventoryWdProjMas.getItemId());
		inventoryWdProjMasImpl.setModifiedDate(inventoryWdProjMas.getModifiedDate());
		inventoryWdProjMasImpl.setOrganizationKey(inventoryWdProjMas.getOrganizationKey());
		inventoryWdProjMasImpl.setRecordLockStatus(inventoryWdProjMas.isRecordLockStatus());
		inventoryWdProjMasImpl.setItemIdentifierCodeQualifier(inventoryWdProjMas.getItemIdentifierCodeQualifier());
		inventoryWdProjMasImpl.setSource(inventoryWdProjMas.getSource());
		inventoryWdProjMasImpl.setCreatedDate(inventoryWdProjMas.getCreatedDate());
		inventoryWdProjMasImpl.setCreatedBy(inventoryWdProjMas.getCreatedBy());
		inventoryWdProjMasImpl.setInventoryWdProjMasSid(inventoryWdProjMas.getInventoryWdProjMasSid());
		inventoryWdProjMasImpl.setDay(inventoryWdProjMas.getDay());
		inventoryWdProjMasImpl.setForecastVer(inventoryWdProjMas.getForecastVer());
		inventoryWdProjMasImpl.setBatchId(inventoryWdProjMas.getBatchId());
		inventoryWdProjMasImpl.setItemIdentifier(inventoryWdProjMas.getItemIdentifier());
		inventoryWdProjMasImpl.setInboundStatus(inventoryWdProjMas.getInboundStatus());
		inventoryWdProjMasImpl.setModifiedBy(inventoryWdProjMas.getModifiedBy());
		inventoryWdProjMasImpl.setMonth(inventoryWdProjMas.getMonth());
		inventoryWdProjMasImpl.setForecastName(inventoryWdProjMas.getForecastName());
		inventoryWdProjMasImpl.setAmountWithdrawn(inventoryWdProjMas.getAmountWithdrawn());

		return inventoryWdProjMasImpl;
	}

	/**
	 * Returns the inventory wd proj mas with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the inventory wd proj mas
	 * @return the inventory wd proj mas
	 * @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public InventoryWdProjMas findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInventoryWdProjMasException {
		InventoryWdProjMas inventoryWdProjMas = fetchByPrimaryKey(primaryKey);

		if (inventoryWdProjMas == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return inventoryWdProjMas;
	}

	/**
	 * Returns the inventory wd proj mas with the primary key or throws a {@link NoSuchInventoryWdProjMasException} if it could not be found.
	 *
	 * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	 * @return the inventory wd proj mas
	 * @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public InventoryWdProjMas findByPrimaryKey(int inventoryWdProjMasSid)
		throws NoSuchInventoryWdProjMasException {
		return findByPrimaryKey((Serializable)inventoryWdProjMasSid);
	}

	/**
	 * Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the inventory wd proj mas
	 * @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public InventoryWdProjMas fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
				InventoryWdProjMasImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		InventoryWdProjMas inventoryWdProjMas = (InventoryWdProjMas)serializable;

		if (inventoryWdProjMas == null) {
			Session session = null;

			try {
				session = openSession();

				inventoryWdProjMas = (InventoryWdProjMas)session.get(InventoryWdProjMasImpl.class,
						primaryKey);

				if (inventoryWdProjMas != null) {
					cacheResult(inventoryWdProjMas);
				}
				else {
					entityCache.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
						InventoryWdProjMasImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
					InventoryWdProjMasImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return inventoryWdProjMas;
	}

	/**
	 * Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	 * @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public InventoryWdProjMas fetchByPrimaryKey(int inventoryWdProjMasSid) {
		return fetchByPrimaryKey((Serializable)inventoryWdProjMasSid);
	}

	@Override
	public Map<Serializable, InventoryWdProjMas> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, InventoryWdProjMas> map = new HashMap<Serializable, InventoryWdProjMas>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			InventoryWdProjMas inventoryWdProjMas = fetchByPrimaryKey(primaryKey);

			if (inventoryWdProjMas != null) {
				map.put(primaryKey, inventoryWdProjMas);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
					InventoryWdProjMasImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (InventoryWdProjMas)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_INVENTORYWDPROJMAS_WHERE_PKS_IN);

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

			for (InventoryWdProjMas inventoryWdProjMas : (List<InventoryWdProjMas>)q.list()) {
				map.put(inventoryWdProjMas.getPrimaryKeyObj(),
					inventoryWdProjMas);

				cacheResult(inventoryWdProjMas);

				uncachedPrimaryKeys.remove(inventoryWdProjMas.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(InventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
					InventoryWdProjMasImpl.class, primaryKey, nullModel);
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
	 * Returns all the inventory wd proj mases.
	 *
	 * @return the inventory wd proj mases
	 */
	@Override
	public List<InventoryWdProjMas> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the inventory wd proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of inventory wd proj mases
	 * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	 * @return the range of inventory wd proj mases
	 */
	@Override
	public List<InventoryWdProjMas> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the inventory wd proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of inventory wd proj mases
	 * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of inventory wd proj mases
	 */
	@Override
	public List<InventoryWdProjMas> findAll(int start, int end,
		OrderByComparator<InventoryWdProjMas> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the inventory wd proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of inventory wd proj mases
	 * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of inventory wd proj mases
	 */
	@Override
	public List<InventoryWdProjMas> findAll(int start, int end,
		OrderByComparator<InventoryWdProjMas> orderByComparator,
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

		List<InventoryWdProjMas> list = null;

		if (retrieveFromCache) {
			list = (List<InventoryWdProjMas>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_INVENTORYWDPROJMAS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INVENTORYWDPROJMAS;

				if (pagination) {
					sql = sql.concat(InventoryWdProjMasModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<InventoryWdProjMas>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<InventoryWdProjMas>)QueryUtil.list(q,
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
	 * Removes all the inventory wd proj mases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (InventoryWdProjMas inventoryWdProjMas : findAll()) {
			remove(inventoryWdProjMas);
		}
	}

	/**
	 * Returns the number of inventory wd proj mases.
	 *
	 * @return the number of inventory wd proj mases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_INVENTORYWDPROJMAS);

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
		return InventoryWdProjMasModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the inventory wd proj mas persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(InventoryWdProjMasImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_INVENTORYWDPROJMAS = "SELECT inventoryWdProjMas FROM InventoryWdProjMas inventoryWdProjMas";
	private static final String _SQL_SELECT_INVENTORYWDPROJMAS_WHERE_PKS_IN = "SELECT inventoryWdProjMas FROM InventoryWdProjMas inventoryWdProjMas WHERE INVENTORY_WD_PROJ_MAS_SID IN (";
	private static final String _SQL_COUNT_INVENTORYWDPROJMAS = "SELECT COUNT(inventoryWdProjMas) FROM InventoryWdProjMas inventoryWdProjMas";
	private static final String _ORDER_BY_ENTITY_ALIAS = "inventoryWdProjMas.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InventoryWdProjMas exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(InventoryWdProjMasPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"week", "itemMasterSid", "unitsWithdrawn", "country", "year",
				"itemId", "modifiedDate", "organizationKey", "recordLockStatus",
				"itemIdentifierCodeQualifier", "source", "createdDate",
				"createdBy", "inventoryWdProjMasSid", "day", "forecastVer",
				"batchId", "itemIdentifier", "inboundStatus", "modifiedBy",
				"month", "forecastName", "amountWithdrawn"
			});
}