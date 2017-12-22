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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchIvldItemIdentifierException;
import com.stpl.app.parttwo.model.IvldItemIdentifier;
import com.stpl.app.parttwo.model.impl.IvldItemIdentifierImpl;
import com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldItemIdentifierPersistence;

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
 * The persistence implementation for the ivld item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldItemIdentifierUtil
 * @generated
 */
@ProviderType
public class IvldItemIdentifierPersistenceImpl extends BasePersistenceImpl<IvldItemIdentifier>
	implements IvldItemIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldItemIdentifierUtil} to access the ivld item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldItemIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			IvldItemIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			IvldItemIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldItemIdentifierPersistenceImpl() {
		setModelClass(IvldItemIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("identifierCodeQualifierName",
				"IDENTIFIER_CODE_QUALIFIER_NAME");
			dbColumnNames.put("ivldItemIdentifierSid",
				"IVLD_ITEM_IDENTIFIER_SID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("identifierCodeQualifier",
				"IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("itemIdentifierIntfid", "ITEM_IDENTIFIER_INTFID");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
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
	 * Caches the ivld item identifier in the entity cache if it is enabled.
	 *
	 * @param ivldItemIdentifier the ivld item identifier
	 */
	@Override
	public void cacheResult(IvldItemIdentifier ivldItemIdentifier) {
		entityCache.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey(),
			ivldItemIdentifier);

		ivldItemIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the ivld item identifiers in the entity cache if it is enabled.
	 *
	 * @param ivldItemIdentifiers the ivld item identifiers
	 */
	@Override
	public void cacheResult(List<IvldItemIdentifier> ivldItemIdentifiers) {
		for (IvldItemIdentifier ivldItemIdentifier : ivldItemIdentifiers) {
			if (entityCache.getResult(
						IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemIdentifierImpl.class,
						ivldItemIdentifier.getPrimaryKey()) == null) {
				cacheResult(ivldItemIdentifier);
			}
			else {
				ivldItemIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld item identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldItemIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld item identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldItemIdentifier ivldItemIdentifier) {
		entityCache.removeResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldItemIdentifier> ivldItemIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldItemIdentifier ivldItemIdentifier : ivldItemIdentifiers) {
			entityCache.removeResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
	 *
	 * @param ivldItemIdentifierSid the primary key for the new ivld item identifier
	 * @return the new ivld item identifier
	 */
	@Override
	public IvldItemIdentifier create(int ivldItemIdentifierSid) {
		IvldItemIdentifier ivldItemIdentifier = new IvldItemIdentifierImpl();

		ivldItemIdentifier.setNew(true);
		ivldItemIdentifier.setPrimaryKey(ivldItemIdentifierSid);

		return ivldItemIdentifier;
	}

	/**
	 * Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldItemIdentifierSid the primary key of the ivld item identifier
	 * @return the ivld item identifier that was removed
	 * @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	 */
	@Override
	public IvldItemIdentifier remove(int ivldItemIdentifierSid)
		throws NoSuchIvldItemIdentifierException {
		return remove((Serializable)ivldItemIdentifierSid);
	}

	/**
	 * Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld item identifier
	 * @return the ivld item identifier that was removed
	 * @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	 */
	@Override
	public IvldItemIdentifier remove(Serializable primaryKey)
		throws NoSuchIvldItemIdentifierException {
		Session session = null;

		try {
			session = openSession();

			IvldItemIdentifier ivldItemIdentifier = (IvldItemIdentifier)session.get(IvldItemIdentifierImpl.class,
					primaryKey);

			if (ivldItemIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldItemIdentifier);
		}
		catch (NoSuchIvldItemIdentifierException nsee) {
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
	protected IvldItemIdentifier removeImpl(
		IvldItemIdentifier ivldItemIdentifier) {
		ivldItemIdentifier = toUnwrappedModel(ivldItemIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldItemIdentifier)) {
				ivldItemIdentifier = (IvldItemIdentifier)session.get(IvldItemIdentifierImpl.class,
						ivldItemIdentifier.getPrimaryKeyObj());
			}

			if (ivldItemIdentifier != null) {
				session.delete(ivldItemIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldItemIdentifier != null) {
			clearCache(ivldItemIdentifier);
		}

		return ivldItemIdentifier;
	}

	@Override
	public IvldItemIdentifier updateImpl(IvldItemIdentifier ivldItemIdentifier) {
		ivldItemIdentifier = toUnwrappedModel(ivldItemIdentifier);

		boolean isNew = ivldItemIdentifier.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldItemIdentifier.isNew()) {
				session.save(ivldItemIdentifier);

				ivldItemIdentifier.setNew(false);
			}
			else {
				ivldItemIdentifier = (IvldItemIdentifier)session.merge(ivldItemIdentifier);
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

		entityCache.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemIdentifierImpl.class, ivldItemIdentifier.getPrimaryKey(),
			ivldItemIdentifier, false);

		ivldItemIdentifier.resetOriginalValues();

		return ivldItemIdentifier;
	}

	protected IvldItemIdentifier toUnwrappedModel(
		IvldItemIdentifier ivldItemIdentifier) {
		if (ivldItemIdentifier instanceof IvldItemIdentifierImpl) {
			return ivldItemIdentifier;
		}

		IvldItemIdentifierImpl ivldItemIdentifierImpl = new IvldItemIdentifierImpl();

		ivldItemIdentifierImpl.setNew(ivldItemIdentifier.isNew());
		ivldItemIdentifierImpl.setPrimaryKey(ivldItemIdentifier.getPrimaryKey());

		ivldItemIdentifierImpl.setCreatedBy(ivldItemIdentifier.getCreatedBy());
		ivldItemIdentifierImpl.setIdentifierCodeQualifierName(ivldItemIdentifier.getIdentifierCodeQualifierName());
		ivldItemIdentifierImpl.setIvldItemIdentifierSid(ivldItemIdentifier.getIvldItemIdentifierSid());
		ivldItemIdentifierImpl.setItemNo(ivldItemIdentifier.getItemNo());
		ivldItemIdentifierImpl.setModifiedBy(ivldItemIdentifier.getModifiedBy());
		ivldItemIdentifierImpl.setCreatedDate(ivldItemIdentifier.getCreatedDate());
		ivldItemIdentifierImpl.setIdentifierCodeQualifier(ivldItemIdentifier.getIdentifierCodeQualifier());
		ivldItemIdentifierImpl.setItemId(ivldItemIdentifier.getItemId());
		ivldItemIdentifierImpl.setEndDate(ivldItemIdentifier.getEndDate());
		ivldItemIdentifierImpl.setErrorField(ivldItemIdentifier.getErrorField());
		ivldItemIdentifierImpl.setStartDate(ivldItemIdentifier.getStartDate());
		ivldItemIdentifierImpl.setBatchId(ivldItemIdentifier.getBatchId());
		ivldItemIdentifierImpl.setModifiedDate(ivldItemIdentifier.getModifiedDate());
		ivldItemIdentifierImpl.setItemName(ivldItemIdentifier.getItemName());
		ivldItemIdentifierImpl.setErrorCode(ivldItemIdentifier.getErrorCode());
		ivldItemIdentifierImpl.setReprocessedFlag(ivldItemIdentifier.getReprocessedFlag());
		ivldItemIdentifierImpl.setItemIdentifier(ivldItemIdentifier.getItemIdentifier());
		ivldItemIdentifierImpl.setItemStatus(ivldItemIdentifier.getItemStatus());
		ivldItemIdentifierImpl.setReasonForFailure(ivldItemIdentifier.getReasonForFailure());
		ivldItemIdentifierImpl.setSource(ivldItemIdentifier.getSource());
		ivldItemIdentifierImpl.setAddChgDelIndicator(ivldItemIdentifier.getAddChgDelIndicator());
		ivldItemIdentifierImpl.setEntityCode(ivldItemIdentifier.getEntityCode());
		ivldItemIdentifierImpl.setItemIdentifierIntfid(ivldItemIdentifier.getItemIdentifierIntfid());
		ivldItemIdentifierImpl.setIntfInsertedDate(ivldItemIdentifier.getIntfInsertedDate());
		ivldItemIdentifierImpl.setCheckRecord(ivldItemIdentifier.isCheckRecord());

		return ivldItemIdentifierImpl;
	}

	/**
	 * Returns the ivld item identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item identifier
	 * @return the ivld item identifier
	 * @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	 */
	@Override
	public IvldItemIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldItemIdentifierException {
		IvldItemIdentifier ivldItemIdentifier = fetchByPrimaryKey(primaryKey);

		if (ivldItemIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldItemIdentifier;
	}

	/**
	 * Returns the ivld item identifier with the primary key or throws a {@link NoSuchIvldItemIdentifierException} if it could not be found.
	 *
	 * @param ivldItemIdentifierSid the primary key of the ivld item identifier
	 * @return the ivld item identifier
	 * @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	 */
	@Override
	public IvldItemIdentifier findByPrimaryKey(int ivldItemIdentifierSid)
		throws NoSuchIvldItemIdentifierException {
		return findByPrimaryKey((Serializable)ivldItemIdentifierSid);
	}

	/**
	 * Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item identifier
	 * @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
	 */
	@Override
	public IvldItemIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldItemIdentifier ivldItemIdentifier = (IvldItemIdentifier)serializable;

		if (ivldItemIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				ivldItemIdentifier = (IvldItemIdentifier)session.get(IvldItemIdentifierImpl.class,
						primaryKey);

				if (ivldItemIdentifier != null) {
					cacheResult(ivldItemIdentifier);
				}
				else {
					entityCache.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldItemIdentifier;
	}

	/**
	 * Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldItemIdentifierSid the primary key of the ivld item identifier
	 * @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
	 */
	@Override
	public IvldItemIdentifier fetchByPrimaryKey(int ivldItemIdentifierSid) {
		return fetchByPrimaryKey((Serializable)ivldItemIdentifierSid);
	}

	@Override
	public Map<Serializable, IvldItemIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldItemIdentifier> map = new HashMap<Serializable, IvldItemIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldItemIdentifier ivldItemIdentifier = fetchByPrimaryKey(primaryKey);

			if (ivldItemIdentifier != null) {
				map.put(primaryKey, ivldItemIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldItemIdentifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDITEMIDENTIFIER_WHERE_PKS_IN);

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

			for (IvldItemIdentifier ivldItemIdentifier : (List<IvldItemIdentifier>)q.list()) {
				map.put(ivldItemIdentifier.getPrimaryKeyObj(),
					ivldItemIdentifier);

				cacheResult(ivldItemIdentifier);

				uncachedPrimaryKeys.remove(ivldItemIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemIdentifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld item identifiers.
	 *
	 * @return the ivld item identifiers
	 */
	@Override
	public List<IvldItemIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item identifiers
	 * @param end the upper bound of the range of ivld item identifiers (not inclusive)
	 * @return the range of ivld item identifiers
	 */
	@Override
	public List<IvldItemIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item identifiers
	 * @param end the upper bound of the range of ivld item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld item identifiers
	 */
	@Override
	public List<IvldItemIdentifier> findAll(int start, int end,
		OrderByComparator<IvldItemIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item identifiers
	 * @param end the upper bound of the range of ivld item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld item identifiers
	 */
	@Override
	public List<IvldItemIdentifier> findAll(int start, int end,
		OrderByComparator<IvldItemIdentifier> orderByComparator,
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

		List<IvldItemIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<IvldItemIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDITEMIDENTIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDITEMIDENTIFIER;

				if (pagination) {
					sql = sql.concat(IvldItemIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldItemIdentifier>)QueryUtil.list(q,
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
	 * Removes all the ivld item identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldItemIdentifier ivldItemIdentifier : findAll()) {
			remove(ivldItemIdentifier);
		}
	}

	/**
	 * Returns the number of ivld item identifiers.
	 *
	 * @return the number of ivld item identifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDITEMIDENTIFIER);

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
		return IvldItemIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld item identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldItemIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDITEMIDENTIFIER = "SELECT ivldItemIdentifier FROM IvldItemIdentifier ivldItemIdentifier";
	private static final String _SQL_SELECT_IVLDITEMIDENTIFIER_WHERE_PKS_IN = "SELECT ivldItemIdentifier FROM IvldItemIdentifier ivldItemIdentifier WHERE IVLD_ITEM_IDENTIFIER_SID IN (";
	private static final String _SQL_COUNT_IVLDITEMIDENTIFIER = "SELECT COUNT(ivldItemIdentifier) FROM IvldItemIdentifier ivldItemIdentifier";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemIdentifier exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldItemIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "identifierCodeQualifierName",
				"ivldItemIdentifierSid", "itemNo", "modifiedBy", "createdDate",
				"identifierCodeQualifier", "itemId", "endDate", "errorField",
				"startDate", "batchId", "modifiedDate", "itemName", "errorCode",
				"reprocessedFlag", "itemIdentifier", "itemStatus",
				"reasonForFailure", "source", "addChgDelIndicator", "entityCode",
				"itemIdentifierIntfid", "intfInsertedDate", "checkRecord"
			});
}