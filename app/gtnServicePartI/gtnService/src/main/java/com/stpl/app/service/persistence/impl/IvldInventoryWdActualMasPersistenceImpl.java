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

import com.stpl.app.exception.NoSuchIvldInventoryWdActualMasException;
import com.stpl.app.model.IvldInventoryWdActualMas;
import com.stpl.app.model.impl.IvldInventoryWdActualMasImpl;
import com.stpl.app.model.impl.IvldInventoryWdActualMasModelImpl;
import com.stpl.app.service.persistence.IvldInventoryWdActualMasPersistence;

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
 * The persistence implementation for the ivld inventory wd actual mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldInventoryWdActualMasPersistence
 * @see com.stpl.app.service.persistence.IvldInventoryWdActualMasUtil
 * @generated
 */
@ProviderType
public class IvldInventoryWdActualMasPersistenceImpl extends BasePersistenceImpl<IvldInventoryWdActualMas>
	implements IvldInventoryWdActualMasPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldInventoryWdActualMasUtil} to access the ivld inventory wd actual mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldInventoryWdActualMasImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
			IvldInventoryWdActualMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED,
			IvldInventoryWdActualMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdActualMasModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldInventoryWdActualMasPersistenceImpl() {
		setModelClass(IvldInventoryWdActualMas.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("quantityOnOrder", "QUANTITY_ON_ORDER");
			dbColumnNames.put("week", "WEEK");
			dbColumnNames.put("amountOnHand", "AMOUNT_ON_HAND");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("ivldInventoryWdActualMasSid",
				"IVLD_INVENTORY_WD_ACTUAL_MAS_SID");
			dbColumnNames.put("day", "DAY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("unitsOnHand", "UNITS_ON_HAND");
			dbColumnNames.put("amountReceived", "AMOUNT_RECEIVED");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
			dbColumnNames.put("inventoryWdActualMasIntfId",
				"INVENTORY_WD_ACTUAL_MAS_INTF_ID");
			dbColumnNames.put("quantityReceived", "QUANTITY_RECEIVED");
			dbColumnNames.put("unitsWithdrawn", "UNITS_WITHDRAWN");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("amountOnOrder", "AMOUNT_ON_ORDER");
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
	 * Caches the ivld inventory wd actual mas in the entity cache if it is enabled.
	 *
	 * @param ivldInventoryWdActualMas the ivld inventory wd actual mas
	 */
	@Override
	public void cacheResult(IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		entityCache.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdActualMasImpl.class,
			ivldInventoryWdActualMas.getPrimaryKey(), ivldInventoryWdActualMas);

		ivldInventoryWdActualMas.resetOriginalValues();
	}

	/**
	 * Caches the ivld inventory wd actual mases in the entity cache if it is enabled.
	 *
	 * @param ivldInventoryWdActualMases the ivld inventory wd actual mases
	 */
	@Override
	public void cacheResult(
		List<IvldInventoryWdActualMas> ivldInventoryWdActualMases) {
		for (IvldInventoryWdActualMas ivldInventoryWdActualMas : ivldInventoryWdActualMases) {
			if (entityCache.getResult(
						IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
						IvldInventoryWdActualMasImpl.class,
						ivldInventoryWdActualMas.getPrimaryKey()) == null) {
				cacheResult(ivldInventoryWdActualMas);
			}
			else {
				ivldInventoryWdActualMas.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld inventory wd actual mases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldInventoryWdActualMasImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld inventory wd actual mas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		entityCache.removeResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdActualMasImpl.class,
			ivldInventoryWdActualMas.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<IvldInventoryWdActualMas> ivldInventoryWdActualMases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldInventoryWdActualMas ivldInventoryWdActualMas : ivldInventoryWdActualMases) {
			entityCache.removeResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
				IvldInventoryWdActualMasImpl.class,
				ivldInventoryWdActualMas.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld inventory wd actual mas with the primary key. Does not add the ivld inventory wd actual mas to the database.
	 *
	 * @param ivldInventoryWdActualMasSid the primary key for the new ivld inventory wd actual mas
	 * @return the new ivld inventory wd actual mas
	 */
	@Override
	public IvldInventoryWdActualMas create(int ivldInventoryWdActualMasSid) {
		IvldInventoryWdActualMas ivldInventoryWdActualMas = new IvldInventoryWdActualMasImpl();

		ivldInventoryWdActualMas.setNew(true);
		ivldInventoryWdActualMas.setPrimaryKey(ivldInventoryWdActualMasSid);

		return ivldInventoryWdActualMas;
	}

	/**
	 * Removes the ivld inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	 * @return the ivld inventory wd actual mas that was removed
	 * @throws NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdActualMas remove(int ivldInventoryWdActualMasSid)
		throws NoSuchIvldInventoryWdActualMasException {
		return remove((Serializable)ivldInventoryWdActualMasSid);
	}

	/**
	 * Removes the ivld inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld inventory wd actual mas
	 * @return the ivld inventory wd actual mas that was removed
	 * @throws NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdActualMas remove(Serializable primaryKey)
		throws NoSuchIvldInventoryWdActualMasException {
		Session session = null;

		try {
			session = openSession();

			IvldInventoryWdActualMas ivldInventoryWdActualMas = (IvldInventoryWdActualMas)session.get(IvldInventoryWdActualMasImpl.class,
					primaryKey);

			if (ivldInventoryWdActualMas == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldInventoryWdActualMas);
		}
		catch (NoSuchIvldInventoryWdActualMasException nsee) {
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
	protected IvldInventoryWdActualMas removeImpl(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		ivldInventoryWdActualMas = toUnwrappedModel(ivldInventoryWdActualMas);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldInventoryWdActualMas)) {
				ivldInventoryWdActualMas = (IvldInventoryWdActualMas)session.get(IvldInventoryWdActualMasImpl.class,
						ivldInventoryWdActualMas.getPrimaryKeyObj());
			}

			if (ivldInventoryWdActualMas != null) {
				session.delete(ivldInventoryWdActualMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldInventoryWdActualMas != null) {
			clearCache(ivldInventoryWdActualMas);
		}

		return ivldInventoryWdActualMas;
	}

	@Override
	public IvldInventoryWdActualMas updateImpl(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		ivldInventoryWdActualMas = toUnwrappedModel(ivldInventoryWdActualMas);

		boolean isNew = ivldInventoryWdActualMas.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldInventoryWdActualMas.isNew()) {
				session.save(ivldInventoryWdActualMas);

				ivldInventoryWdActualMas.setNew(false);
			}
			else {
				ivldInventoryWdActualMas = (IvldInventoryWdActualMas)session.merge(ivldInventoryWdActualMas);
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

		entityCache.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
			IvldInventoryWdActualMasImpl.class,
			ivldInventoryWdActualMas.getPrimaryKey(), ivldInventoryWdActualMas,
			false);

		ivldInventoryWdActualMas.resetOriginalValues();

		return ivldInventoryWdActualMas;
	}

	protected IvldInventoryWdActualMas toUnwrappedModel(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		if (ivldInventoryWdActualMas instanceof IvldInventoryWdActualMasImpl) {
			return ivldInventoryWdActualMas;
		}

		IvldInventoryWdActualMasImpl ivldInventoryWdActualMasImpl = new IvldInventoryWdActualMasImpl();

		ivldInventoryWdActualMasImpl.setNew(ivldInventoryWdActualMas.isNew());
		ivldInventoryWdActualMasImpl.setPrimaryKey(ivldInventoryWdActualMas.getPrimaryKey());

		ivldInventoryWdActualMasImpl.setQuantityOnOrder(ivldInventoryWdActualMas.getQuantityOnOrder());
		ivldInventoryWdActualMasImpl.setWeek(ivldInventoryWdActualMas.getWeek());
		ivldInventoryWdActualMasImpl.setAmountOnHand(ivldInventoryWdActualMas.getAmountOnHand());
		ivldInventoryWdActualMasImpl.setYear(ivldInventoryWdActualMas.getYear());
		ivldInventoryWdActualMasImpl.setItemId(ivldInventoryWdActualMas.getItemId());
		ivldInventoryWdActualMasImpl.setModifiedDate(ivldInventoryWdActualMas.getModifiedDate());
		ivldInventoryWdActualMasImpl.setOrganizationKey(ivldInventoryWdActualMas.getOrganizationKey());
		ivldInventoryWdActualMasImpl.setCreatedBy(ivldInventoryWdActualMas.getCreatedBy());
		ivldInventoryWdActualMasImpl.setCreatedDate(ivldInventoryWdActualMas.getCreatedDate());
		ivldInventoryWdActualMasImpl.setSource(ivldInventoryWdActualMas.getSource());
		ivldInventoryWdActualMasImpl.setIvldInventoryWdActualMasSid(ivldInventoryWdActualMas.getIvldInventoryWdActualMasSid());
		ivldInventoryWdActualMasImpl.setDay(ivldInventoryWdActualMas.getDay());
		ivldInventoryWdActualMasImpl.setAddChgDelIndicator(ivldInventoryWdActualMas.getAddChgDelIndicator());
		ivldInventoryWdActualMasImpl.setUnitsOnHand(ivldInventoryWdActualMas.getUnitsOnHand());
		ivldInventoryWdActualMasImpl.setAmountReceived(ivldInventoryWdActualMas.getAmountReceived());
		ivldInventoryWdActualMasImpl.setItemIdentifier(ivldInventoryWdActualMas.getItemIdentifier());
		ivldInventoryWdActualMasImpl.setErrorCode(ivldInventoryWdActualMas.getErrorCode());
		ivldInventoryWdActualMasImpl.setIntfInsertedDate(ivldInventoryWdActualMas.getIntfInsertedDate());
		ivldInventoryWdActualMasImpl.setModifiedBy(ivldInventoryWdActualMas.getModifiedBy());
		ivldInventoryWdActualMasImpl.setMonth(ivldInventoryWdActualMas.getMonth());
		ivldInventoryWdActualMasImpl.setReprocessedFlag(ivldInventoryWdActualMas.getReprocessedFlag());
		ivldInventoryWdActualMasImpl.setAmountWithdrawn(ivldInventoryWdActualMas.getAmountWithdrawn());
		ivldInventoryWdActualMasImpl.setInventoryWdActualMasIntfId(ivldInventoryWdActualMas.getInventoryWdActualMasIntfId());
		ivldInventoryWdActualMasImpl.setQuantityReceived(ivldInventoryWdActualMas.getQuantityReceived());
		ivldInventoryWdActualMasImpl.setUnitsWithdrawn(ivldInventoryWdActualMas.getUnitsWithdrawn());
		ivldInventoryWdActualMasImpl.setReasonForFailure(ivldInventoryWdActualMas.getReasonForFailure());
		ivldInventoryWdActualMasImpl.setCountry(ivldInventoryWdActualMas.getCountry());
		ivldInventoryWdActualMasImpl.setItemIdentifierCodeQualifier(ivldInventoryWdActualMas.getItemIdentifierCodeQualifier());
		ivldInventoryWdActualMasImpl.setBatchId(ivldInventoryWdActualMas.getBatchId());
		ivldInventoryWdActualMasImpl.setErrorField(ivldInventoryWdActualMas.getErrorField());
		ivldInventoryWdActualMasImpl.setAmountOnOrder(ivldInventoryWdActualMas.getAmountOnOrder());
		ivldInventoryWdActualMasImpl.setCheckRecord(ivldInventoryWdActualMas.isCheckRecord());

		return ivldInventoryWdActualMasImpl;
	}

	/**
	 * Returns the ivld inventory wd actual mas with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld inventory wd actual mas
	 * @return the ivld inventory wd actual mas
	 * @throws NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdActualMas findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldInventoryWdActualMasException {
		IvldInventoryWdActualMas ivldInventoryWdActualMas = fetchByPrimaryKey(primaryKey);

		if (ivldInventoryWdActualMas == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldInventoryWdActualMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldInventoryWdActualMas;
	}

	/**
	 * Returns the ivld inventory wd actual mas with the primary key or throws a {@link NoSuchIvldInventoryWdActualMasException} if it could not be found.
	 *
	 * @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	 * @return the ivld inventory wd actual mas
	 * @throws NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdActualMas findByPrimaryKey(
		int ivldInventoryWdActualMasSid)
		throws NoSuchIvldInventoryWdActualMasException {
		return findByPrimaryKey((Serializable)ivldInventoryWdActualMasSid);
	}

	/**
	 * Returns the ivld inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld inventory wd actual mas
	 * @return the ivld inventory wd actual mas, or <code>null</code> if a ivld inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdActualMas fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
				IvldInventoryWdActualMasImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldInventoryWdActualMas ivldInventoryWdActualMas = (IvldInventoryWdActualMas)serializable;

		if (ivldInventoryWdActualMas == null) {
			Session session = null;

			try {
				session = openSession();

				ivldInventoryWdActualMas = (IvldInventoryWdActualMas)session.get(IvldInventoryWdActualMasImpl.class,
						primaryKey);

				if (ivldInventoryWdActualMas != null) {
					cacheResult(ivldInventoryWdActualMas);
				}
				else {
					entityCache.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
						IvldInventoryWdActualMasImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
					IvldInventoryWdActualMasImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldInventoryWdActualMas;
	}

	/**
	 * Returns the ivld inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	 * @return the ivld inventory wd actual mas, or <code>null</code> if a ivld inventory wd actual mas with the primary key could not be found
	 */
	@Override
	public IvldInventoryWdActualMas fetchByPrimaryKey(
		int ivldInventoryWdActualMasSid) {
		return fetchByPrimaryKey((Serializable)ivldInventoryWdActualMasSid);
	}

	@Override
	public Map<Serializable, IvldInventoryWdActualMas> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldInventoryWdActualMas> map = new HashMap<Serializable, IvldInventoryWdActualMas>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldInventoryWdActualMas ivldInventoryWdActualMas = fetchByPrimaryKey(primaryKey);

			if (ivldInventoryWdActualMas != null) {
				map.put(primaryKey, ivldInventoryWdActualMas);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
					IvldInventoryWdActualMasImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldInventoryWdActualMas)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDINVENTORYWDACTUALMAS_WHERE_PKS_IN);

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

			for (IvldInventoryWdActualMas ivldInventoryWdActualMas : (List<IvldInventoryWdActualMas>)q.list()) {
				map.put(ivldInventoryWdActualMas.getPrimaryKeyObj(),
					ivldInventoryWdActualMas);

				cacheResult(ivldInventoryWdActualMas);

				uncachedPrimaryKeys.remove(ivldInventoryWdActualMas.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldInventoryWdActualMasModelImpl.ENTITY_CACHE_ENABLED,
					IvldInventoryWdActualMasImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld inventory wd actual mases.
	 *
	 * @return the ivld inventory wd actual mases
	 */
	@Override
	public List<IvldInventoryWdActualMas> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld inventory wd actual mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld inventory wd actual mases
	 * @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	 * @return the range of ivld inventory wd actual mases
	 */
	@Override
	public List<IvldInventoryWdActualMas> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld inventory wd actual mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld inventory wd actual mases
	 * @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld inventory wd actual mases
	 */
	@Override
	public List<IvldInventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<IvldInventoryWdActualMas> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld inventory wd actual mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld inventory wd actual mases
	 * @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld inventory wd actual mases
	 */
	@Override
	public List<IvldInventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<IvldInventoryWdActualMas> orderByComparator,
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

		List<IvldInventoryWdActualMas> list = null;

		if (retrieveFromCache) {
			list = (List<IvldInventoryWdActualMas>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDINVENTORYWDACTUALMAS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDINVENTORYWDACTUALMAS;

				if (pagination) {
					sql = sql.concat(IvldInventoryWdActualMasModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldInventoryWdActualMas>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldInventoryWdActualMas>)QueryUtil.list(q,
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
	 * Removes all the ivld inventory wd actual mases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldInventoryWdActualMas ivldInventoryWdActualMas : findAll()) {
			remove(ivldInventoryWdActualMas);
		}
	}

	/**
	 * Returns the number of ivld inventory wd actual mases.
	 *
	 * @return the number of ivld inventory wd actual mases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDINVENTORYWDACTUALMAS);

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
		return IvldInventoryWdActualMasModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld inventory wd actual mas persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldInventoryWdActualMasImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDINVENTORYWDACTUALMAS = "SELECT ivldInventoryWdActualMas FROM IvldInventoryWdActualMas ivldInventoryWdActualMas";
	private static final String _SQL_SELECT_IVLDINVENTORYWDACTUALMAS_WHERE_PKS_IN =
		"SELECT ivldInventoryWdActualMas FROM IvldInventoryWdActualMas ivldInventoryWdActualMas WHERE IVLD_INVENTORY_WD_ACTUAL_MAS_SID IN (";
	private static final String _SQL_COUNT_IVLDINVENTORYWDACTUALMAS = "SELECT COUNT(ivldInventoryWdActualMas) FROM IvldInventoryWdActualMas ivldInventoryWdActualMas";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldInventoryWdActualMas.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldInventoryWdActualMas exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldInventoryWdActualMasPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"quantityOnOrder", "week", "amountOnHand", "year", "itemId",
				"modifiedDate", "organizationKey", "createdBy", "createdDate",
				"source", "ivldInventoryWdActualMasSid", "day",
				"addChgDelIndicator", "unitsOnHand", "amountReceived",
				"itemIdentifier", "errorCode", "intfInsertedDate", "modifiedBy",
				"month", "reprocessedFlag", "amountWithdrawn",
				"inventoryWdActualMasIntfId", "quantityReceived",
				"unitsWithdrawn", "reasonForFailure", "country",
				"itemIdentifierCodeQualifier", "batchId", "errorField",
				"amountOnOrder", "checkRecord"
			});
}