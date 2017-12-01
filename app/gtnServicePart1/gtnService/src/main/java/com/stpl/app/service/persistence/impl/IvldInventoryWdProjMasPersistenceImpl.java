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

import com.stpl.app.exception.NoSuchIvldInventoryWdProjMasException;
import com.stpl.app.model.IvldInventoryWdProjMas;
import com.stpl.app.model.impl.IvldInventoryWdProjMasImpl;
import com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl;
import com.stpl.app.service.persistence.IvldInventoryWdProjMasPersistence;

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
 * The persistence implementation for the ivld inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMasPersistence
 * @see com.stpl.app.service.persistence.IvldInventoryWdProjMasUtil
 * @generated
 */
@ProviderType
public class IvldInventoryWdProjMasPersistenceImpl extends BasePersistenceImpl<IvldInventoryWdProjMas>
	implements IvldInventoryWdProjMasPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldInventoryWdProjMasUtil} to access the ivld inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldInventoryWdProjMasImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
			IvldInventoryWdProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED,
			IvldInventoryWdProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdProjMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldInventoryWdProjMasPersistenceImpl() {
		setModelClass(IvldInventoryWdProjMas.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("inventoryWdProjMasIntfId",
				"INVENTORY_WD_PROJ_MAS_INTF_ID");
			dbColumnNames.put("week", "WEEK");
			dbColumnNames.put("unitsWithdrawn", "UNITS_WITHDRAWN");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("day", "DAY");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("ivldInventoryWdProjMasSid",
				"IVLD_INVENTORY_WD_PROJ_MAS_SID");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld inventory wd proj mas in the entity cache if it is enabled.
	 *
	 * @param ivldInventoryWdProjMas the ivld inventory wd proj mas
	 */
	@Override
	public void cacheResult(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		entityCache.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdProjMasImpl.class,
			ivldInventoryWdProjMas.getPrimaryKey(), ivldInventoryWdProjMas);

		ivldInventoryWdProjMas.resetOriginalValues();
	}

	/**
	 * Caches the ivld inventory wd proj mases in the entity cache if it is enabled.
	 *
	 * @param ivldInventoryWdProjMases the ivld inventory wd proj mases
	 */
	@Override
	public void cacheResult(
		List<IvldInventoryWdProjMas> ivldInventoryWdProjMases) {
		for (IvldInventoryWdProjMas ivldInventoryWdProjMas : ivldInventoryWdProjMases) {
			if (entityCache.getResult(
						IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
						IvldInventoryWdProjMasImpl.class,
						ivldInventoryWdProjMas.getPrimaryKey()) == null) {
				cacheResult(ivldInventoryWdProjMas);
			}
			else {
				ivldInventoryWdProjMas.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld inventory wd proj mases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldInventoryWdProjMasImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld inventory wd proj mas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		entityCache.removeResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdProjMasImpl.class,
			ivldInventoryWdProjMas.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<IvldInventoryWdProjMas> ivldInventoryWdProjMases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldInventoryWdProjMas ivldInventoryWdProjMas : ivldInventoryWdProjMases) {
			entityCache.removeResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
				IvldInventoryWdProjMasImpl.class,
				ivldInventoryWdProjMas.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld inventory wd proj mas with the primary key. Does not add the ivld inventory wd proj mas to the database.
	 *
	 * @param ivldInventoryWdProjMasSid the primary key for the new ivld inventory wd proj mas
	 * @return the new ivld inventory wd proj mas
	 */
	@Override
	public IvldInventoryWdProjMas create(int ivldInventoryWdProjMasSid) {
		IvldInventoryWdProjMas ivldInventoryWdProjMas = new IvldInventoryWdProjMasImpl();

		ivldInventoryWdProjMas.setNew(true);
		ivldInventoryWdProjMas.setPrimaryKey(ivldInventoryWdProjMasSid);

		return ivldInventoryWdProjMas;
	}

	/**
	 * Removes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	 * @return the ivld inventory wd proj mas that was removed
	 * @throws NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdProjMas remove(int ivldInventoryWdProjMasSid)
		throws NoSuchIvldInventoryWdProjMasException {
		return remove((Serializable)ivldInventoryWdProjMasSid);
	}

	/**
	 * Removes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld inventory wd proj mas
	 * @return the ivld inventory wd proj mas that was removed
	 * @throws NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdProjMas remove(Serializable primaryKey)
		throws NoSuchIvldInventoryWdProjMasException {
		Session session = null;

		try {
			session = openSession();

			IvldInventoryWdProjMas ivldInventoryWdProjMas = (IvldInventoryWdProjMas)session.get(IvldInventoryWdProjMasImpl.class,
					primaryKey);

			if (ivldInventoryWdProjMas == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldInventoryWdProjMas);
		}
		catch (NoSuchIvldInventoryWdProjMasException nsee) {
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
	protected IvldInventoryWdProjMas removeImpl(
		IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		ivldInventoryWdProjMas = toUnwrappedModel(ivldInventoryWdProjMas);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldInventoryWdProjMas)) {
				ivldInventoryWdProjMas = (IvldInventoryWdProjMas)session.get(IvldInventoryWdProjMasImpl.class,
						ivldInventoryWdProjMas.getPrimaryKeyObj());
			}

			if (ivldInventoryWdProjMas != null) {
				session.delete(ivldInventoryWdProjMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldInventoryWdProjMas != null) {
			clearCache(ivldInventoryWdProjMas);
		}

		return ivldInventoryWdProjMas;
	}

	@Override
	public IvldInventoryWdProjMas updateImpl(
		IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		ivldInventoryWdProjMas = toUnwrappedModel(ivldInventoryWdProjMas);

		boolean isNew = ivldInventoryWdProjMas.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldInventoryWdProjMas.isNew()) {
				session.save(ivldInventoryWdProjMas);

				ivldInventoryWdProjMas.setNew(false);
			}
			else {
				ivldInventoryWdProjMas = (IvldInventoryWdProjMas)session.merge(ivldInventoryWdProjMas);
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

		entityCache.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdProjMasImpl.class,
			ivldInventoryWdProjMas.getPrimaryKey(), ivldInventoryWdProjMas,
			false);

		ivldInventoryWdProjMas.resetOriginalValues();

		return ivldInventoryWdProjMas;
	}

	protected IvldInventoryWdProjMas toUnwrappedModel(
		IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		if (ivldInventoryWdProjMas instanceof IvldInventoryWdProjMasImpl) {
			return ivldInventoryWdProjMas;
		}

		IvldInventoryWdProjMasImpl ivldInventoryWdProjMasImpl = new IvldInventoryWdProjMasImpl();

		ivldInventoryWdProjMasImpl.setNew(ivldInventoryWdProjMas.isNew());
		ivldInventoryWdProjMasImpl.setPrimaryKey(ivldInventoryWdProjMas.getPrimaryKey());

		ivldInventoryWdProjMasImpl.setInventoryWdProjMasIntfId(ivldInventoryWdProjMas.getInventoryWdProjMasIntfId());
		ivldInventoryWdProjMasImpl.setWeek(ivldInventoryWdProjMas.getWeek());
		ivldInventoryWdProjMasImpl.setUnitsWithdrawn(ivldInventoryWdProjMas.getUnitsWithdrawn());
		ivldInventoryWdProjMasImpl.setReasonForFailure(ivldInventoryWdProjMas.getReasonForFailure());
		ivldInventoryWdProjMasImpl.setCountry(ivldInventoryWdProjMas.getCountry());
		ivldInventoryWdProjMasImpl.setYear(ivldInventoryWdProjMas.getYear());
		ivldInventoryWdProjMasImpl.setItemId(ivldInventoryWdProjMas.getItemId());
		ivldInventoryWdProjMasImpl.setModifiedDate(ivldInventoryWdProjMas.getModifiedDate());
		ivldInventoryWdProjMasImpl.setOrganizationKey(ivldInventoryWdProjMas.getOrganizationKey());
		ivldInventoryWdProjMasImpl.setItemIdentifierCodeQualifier(ivldInventoryWdProjMas.getItemIdentifierCodeQualifier());
		ivldInventoryWdProjMasImpl.setSource(ivldInventoryWdProjMas.getSource());
		ivldInventoryWdProjMasImpl.setCreatedDate(ivldInventoryWdProjMas.getCreatedDate());
		ivldInventoryWdProjMasImpl.setCreatedBy(ivldInventoryWdProjMas.getCreatedBy());
		ivldInventoryWdProjMasImpl.setDay(ivldInventoryWdProjMas.getDay());
		ivldInventoryWdProjMasImpl.setForecastVer(ivldInventoryWdProjMas.getForecastVer());
		ivldInventoryWdProjMasImpl.setBatchId(ivldInventoryWdProjMas.getBatchId());
		ivldInventoryWdProjMasImpl.setAddChgDelIndicator(ivldInventoryWdProjMas.getAddChgDelIndicator());
		ivldInventoryWdProjMasImpl.setItemIdentifier(ivldInventoryWdProjMas.getItemIdentifier());
		ivldInventoryWdProjMasImpl.setErrorField(ivldInventoryWdProjMas.getErrorField());
		ivldInventoryWdProjMasImpl.setErrorCode(ivldInventoryWdProjMas.getErrorCode());
		ivldInventoryWdProjMasImpl.setIntfInsertedDate(ivldInventoryWdProjMas.getIntfInsertedDate());
		ivldInventoryWdProjMasImpl.setModifiedBy(ivldInventoryWdProjMas.getModifiedBy());
		ivldInventoryWdProjMasImpl.setIvldInventoryWdProjMasSid(ivldInventoryWdProjMas.getIvldInventoryWdProjMasSid());
		ivldInventoryWdProjMasImpl.setMonth(ivldInventoryWdProjMas.getMonth());
		ivldInventoryWdProjMasImpl.setReprocessedFlag(ivldInventoryWdProjMas.getReprocessedFlag());
		ivldInventoryWdProjMasImpl.setForecastName(ivldInventoryWdProjMas.getForecastName());
		ivldInventoryWdProjMasImpl.setAmountWithdrawn(ivldInventoryWdProjMas.getAmountWithdrawn());
		ivldInventoryWdProjMasImpl.setCheckRecord(ivldInventoryWdProjMas.isCheckRecord());

		return ivldInventoryWdProjMasImpl;
	}

	/**
	 * Returns the ivld inventory wd proj mas with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld inventory wd proj mas
	 * @return the ivld inventory wd proj mas
	 * @throws NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdProjMas findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldInventoryWdProjMasException {
		IvldInventoryWdProjMas ivldInventoryWdProjMas = fetchByPrimaryKey(primaryKey);

		if (ivldInventoryWdProjMas == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldInventoryWdProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldInventoryWdProjMas;
	}

	/**
	 * Returns the ivld inventory wd proj mas with the primary key or throws a {@link NoSuchIvldInventoryWdProjMasException} if it could not be found.
	 *
	 * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	 * @return the ivld inventory wd proj mas
	 * @throws NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdProjMas findByPrimaryKey(
		int ivldInventoryWdProjMasSid)
		throws NoSuchIvldInventoryWdProjMasException {
		return findByPrimaryKey((Serializable)ivldInventoryWdProjMasSid);
	}

	/**
	 * Returns the ivld inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld inventory wd proj mas
	 * @return the ivld inventory wd proj mas, or <code>null</code> if a ivld inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdProjMas fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
				IvldInventoryWdProjMasImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldInventoryWdProjMas ivldInventoryWdProjMas = (IvldInventoryWdProjMas)serializable;

		if (ivldInventoryWdProjMas == null) {
			Session session = null;

			try {
				session = openSession();

				ivldInventoryWdProjMas = (IvldInventoryWdProjMas)session.get(IvldInventoryWdProjMasImpl.class,
						primaryKey);

				if (ivldInventoryWdProjMas != null) {
					cacheResult(ivldInventoryWdProjMas);
				}
				else {
					entityCache.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
						IvldInventoryWdProjMasImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
					IvldInventoryWdProjMasImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldInventoryWdProjMas;
	}

	/**
	 * Returns the ivld inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	 * @return the ivld inventory wd proj mas, or <code>null</code> if a ivld inventory wd proj mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdProjMas fetchByPrimaryKey(
		int ivldInventoryWdProjMasSid) {
		return fetchByPrimaryKey((Serializable)ivldInventoryWdProjMasSid);
	}

	@Override
	public Map<Serializable, IvldInventoryWdProjMas> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldInventoryWdProjMas> map = new HashMap<Serializable, IvldInventoryWdProjMas>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldInventoryWdProjMas ivldInventoryWdProjMas = fetchByPrimaryKey(primaryKey);

			if (ivldInventoryWdProjMas != null) {
				map.put(primaryKey, ivldInventoryWdProjMas);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
					IvldInventoryWdProjMasImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldInventoryWdProjMas)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDINVENTORYWDPROJMAS_WHERE_PKS_IN);

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

			for (IvldInventoryWdProjMas ivldInventoryWdProjMas : (List<IvldInventoryWdProjMas>)q.list()) {
				map.put(ivldInventoryWdProjMas.getPrimaryKeyObj(),
					ivldInventoryWdProjMas);

				cacheResult(ivldInventoryWdProjMas);

				uncachedPrimaryKeys.remove(ivldInventoryWdProjMas.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldInventoryWdProjMasModelImpl.ENTITY_CACHE_ENABLED,
					IvldInventoryWdProjMasImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld inventory wd proj mases.
	 *
	 * @return the ivld inventory wd proj mases
	 */
	@Override
	public List<IvldInventoryWdProjMas> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld inventory wd proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld inventory wd proj mases
	 * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	 * @return the range of ivld inventory wd proj mases
	 */
	@Override
	public List<IvldInventoryWdProjMas> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld inventory wd proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld inventory wd proj mases
	 * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld inventory wd proj mases
	 */
	@Override
	public List<IvldInventoryWdProjMas> findAll(int start, int end,
		OrderByComparator<IvldInventoryWdProjMas> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld inventory wd proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld inventory wd proj mases
	 * @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld inventory wd proj mases
	 */
	@Override
	public List<IvldInventoryWdProjMas> findAll(int start, int end,
		OrderByComparator<IvldInventoryWdProjMas> orderByComparator,
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

		List<IvldInventoryWdProjMas> list = null;

		if (retrieveFromCache) {
			list = (List<IvldInventoryWdProjMas>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDINVENTORYWDPROJMAS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDINVENTORYWDPROJMAS;

				if (pagination) {
					sql = sql.concat(IvldInventoryWdProjMasModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldInventoryWdProjMas>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldInventoryWdProjMas>)QueryUtil.list(q,
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
	 * Removes all the ivld inventory wd proj mases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldInventoryWdProjMas ivldInventoryWdProjMas : findAll()) {
			remove(ivldInventoryWdProjMas);
		}
	}

	/**
	 * Returns the number of ivld inventory wd proj mases.
	 *
	 * @return the number of ivld inventory wd proj mases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDINVENTORYWDPROJMAS);

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
		return IvldInventoryWdProjMasModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld inventory wd proj mas persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldInventoryWdProjMasImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDINVENTORYWDPROJMAS = "SELECT ivldInventoryWdProjMas FROM IvldInventoryWdProjMas ivldInventoryWdProjMas";
	private static final String _SQL_SELECT_IVLDINVENTORYWDPROJMAS_WHERE_PKS_IN = "SELECT ivldInventoryWdProjMas FROM IvldInventoryWdProjMas ivldInventoryWdProjMas WHERE IVLD_INVENTORY_WD_PROJ_MAS_SID IN (";
	private static final String _SQL_COUNT_IVLDINVENTORYWDPROJMAS = "SELECT COUNT(ivldInventoryWdProjMas) FROM IvldInventoryWdProjMas ivldInventoryWdProjMas";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldInventoryWdProjMas.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldInventoryWdProjMas exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldInventoryWdProjMasPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"inventoryWdProjMasIntfId", "week", "unitsWithdrawn",
				"reasonForFailure", "country", "year", "itemId", "modifiedDate",
				"organizationKey", "itemIdentifierCodeQualifier", "source",
				"createdDate", "createdBy", "day", "forecastVer", "batchId",
				"addChgDelIndicator", "itemIdentifier", "errorField",
				"errorCode", "intfInsertedDate", "modifiedBy",
				"ivldInventoryWdProjMasSid", "month", "reprocessedFlag",
				"forecastName", "amountWithdrawn", "checkRecord"
			});
}