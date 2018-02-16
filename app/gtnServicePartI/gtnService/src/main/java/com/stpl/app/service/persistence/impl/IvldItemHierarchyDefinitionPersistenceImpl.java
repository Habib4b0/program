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

import com.stpl.app.exception.NoSuchIvldItemHierarchyDefinitionException;
import com.stpl.app.model.IvldItemHierarchyDefinition;
import com.stpl.app.model.impl.IvldItemHierarchyDefinitionImpl;
import com.stpl.app.model.impl.IvldItemHierarchyDefinitionModelImpl;
import com.stpl.app.service.persistence.IvldItemHierarchyDefinitionPersistence;

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
 * The persistence implementation for the ivld item hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinitionPersistence
 * @see com.stpl.app.service.persistence.IvldItemHierarchyDefinitionUtil
 * @generated
 */
@ProviderType
public class IvldItemHierarchyDefinitionPersistenceImpl
	extends BasePersistenceImpl<IvldItemHierarchyDefinition>
	implements IvldItemHierarchyDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldItemHierarchyDefinitionUtil} to access the ivld item hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldItemHierarchyDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			IvldItemHierarchyDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			IvldItemHierarchyDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public IvldItemHierarchyDefinitionPersistenceImpl() {
		setModelClass(IvldItemHierarchyDefinition.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("member", "MEMBER");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("itemHierarchyDefnIntfid",
				"ITEM_HIERARCHY_DEFN_INTFID");
			dbColumnNames.put("bpiLvl", "BPI_LVL");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("alias", "ALIAS");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("ivldItemHierarchyDefinitionSid",
				"IVLD_ITEM_HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
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
	 * Caches the ivld item hierarchy definition in the entity cache if it is enabled.
	 *
	 * @param ivldItemHierarchyDefinition the ivld item hierarchy definition
	 */
	@Override
	public void cacheResult(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		entityCache.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyDefinitionImpl.class,
			ivldItemHierarchyDefinition.getPrimaryKey(),
			ivldItemHierarchyDefinition);

		ivldItemHierarchyDefinition.resetOriginalValues();
	}

	/**
	 * Caches the ivld item hierarchy definitions in the entity cache if it is enabled.
	 *
	 * @param ivldItemHierarchyDefinitions the ivld item hierarchy definitions
	 */
	@Override
	public void cacheResult(
		List<IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions) {
		for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : ivldItemHierarchyDefinitions) {
			if (entityCache.getResult(
						IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemHierarchyDefinitionImpl.class,
						ivldItemHierarchyDefinition.getPrimaryKey()) == null) {
				cacheResult(ivldItemHierarchyDefinition);
			}
			else {
				ivldItemHierarchyDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld item hierarchy definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldItemHierarchyDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld item hierarchy definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		entityCache.removeResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyDefinitionImpl.class,
			ivldItemHierarchyDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : ivldItemHierarchyDefinitions) {
			entityCache.removeResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemHierarchyDefinitionImpl.class,
				ivldItemHierarchyDefinition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
	 *
	 * @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
	 * @return the new ivld item hierarchy definition
	 */
	@Override
	public IvldItemHierarchyDefinition create(
		int ivldItemHierarchyDefinitionSid) {
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition = new IvldItemHierarchyDefinitionImpl();

		ivldItemHierarchyDefinition.setNew(true);
		ivldItemHierarchyDefinition.setPrimaryKey(ivldItemHierarchyDefinitionSid);

		return ivldItemHierarchyDefinition;
	}

	/**
	 * Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	 * @return the ivld item hierarchy definition that was removed
	 * @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchyDefinition remove(
		int ivldItemHierarchyDefinitionSid)
		throws NoSuchIvldItemHierarchyDefinitionException {
		return remove((Serializable)ivldItemHierarchyDefinitionSid);
	}

	/**
	 * Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld item hierarchy definition
	 * @return the ivld item hierarchy definition that was removed
	 * @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchyDefinition remove(Serializable primaryKey)
		throws NoSuchIvldItemHierarchyDefinitionException {
		Session session = null;

		try {
			session = openSession();

			IvldItemHierarchyDefinition ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition)session.get(IvldItemHierarchyDefinitionImpl.class,
					primaryKey);

			if (ivldItemHierarchyDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldItemHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldItemHierarchyDefinition);
		}
		catch (NoSuchIvldItemHierarchyDefinitionException nsee) {
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
	protected IvldItemHierarchyDefinition removeImpl(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		ivldItemHierarchyDefinition = toUnwrappedModel(ivldItemHierarchyDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldItemHierarchyDefinition)) {
				ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition)session.get(IvldItemHierarchyDefinitionImpl.class,
						ivldItemHierarchyDefinition.getPrimaryKeyObj());
			}

			if (ivldItemHierarchyDefinition != null) {
				session.delete(ivldItemHierarchyDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldItemHierarchyDefinition != null) {
			clearCache(ivldItemHierarchyDefinition);
		}

		return ivldItemHierarchyDefinition;
	}

	@Override
	public IvldItemHierarchyDefinition updateImpl(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		ivldItemHierarchyDefinition = toUnwrappedModel(ivldItemHierarchyDefinition);

		boolean isNew = ivldItemHierarchyDefinition.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldItemHierarchyDefinition.isNew()) {
				session.save(ivldItemHierarchyDefinition);

				ivldItemHierarchyDefinition.setNew(false);
			}
			else {
				ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition)session.merge(ivldItemHierarchyDefinition);
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

		entityCache.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyDefinitionImpl.class,
			ivldItemHierarchyDefinition.getPrimaryKey(),
			ivldItemHierarchyDefinition, false);

		ivldItemHierarchyDefinition.resetOriginalValues();

		return ivldItemHierarchyDefinition;
	}

	protected IvldItemHierarchyDefinition toUnwrappedModel(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		if (ivldItemHierarchyDefinition instanceof IvldItemHierarchyDefinitionImpl) {
			return ivldItemHierarchyDefinition;
		}

		IvldItemHierarchyDefinitionImpl ivldItemHierarchyDefinitionImpl = new IvldItemHierarchyDefinitionImpl();

		ivldItemHierarchyDefinitionImpl.setNew(ivldItemHierarchyDefinition.isNew());
		ivldItemHierarchyDefinitionImpl.setPrimaryKey(ivldItemHierarchyDefinition.getPrimaryKey());

		ivldItemHierarchyDefinitionImpl.setMember(ivldItemHierarchyDefinition.getMember());
		ivldItemHierarchyDefinitionImpl.setReasonForFailure(ivldItemHierarchyDefinition.getReasonForFailure());
		ivldItemHierarchyDefinitionImpl.setItemHierarchyDefnIntfid(ivldItemHierarchyDefinition.getItemHierarchyDefnIntfid());
		ivldItemHierarchyDefinitionImpl.setBpiLvl(ivldItemHierarchyDefinition.getBpiLvl());
		ivldItemHierarchyDefinitionImpl.setModifiedDate(ivldItemHierarchyDefinition.getModifiedDate());
		ivldItemHierarchyDefinitionImpl.setAlias(ivldItemHierarchyDefinition.getAlias());
		ivldItemHierarchyDefinitionImpl.setCreatedBy(ivldItemHierarchyDefinition.getCreatedBy());
		ivldItemHierarchyDefinitionImpl.setCreatedDate(ivldItemHierarchyDefinition.getCreatedDate());
		ivldItemHierarchyDefinitionImpl.setSource(ivldItemHierarchyDefinition.getSource());
		ivldItemHierarchyDefinitionImpl.setBatchId(ivldItemHierarchyDefinition.getBatchId());
		ivldItemHierarchyDefinitionImpl.setAddChgDelIndicator(ivldItemHierarchyDefinition.getAddChgDelIndicator());
		ivldItemHierarchyDefinitionImpl.setIvldItemHierarchyDefinitionSid(ivldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid());
		ivldItemHierarchyDefinitionImpl.setErrorField(ivldItemHierarchyDefinition.getErrorField());
		ivldItemHierarchyDefinitionImpl.setErrorCode(ivldItemHierarchyDefinition.getErrorCode());
		ivldItemHierarchyDefinitionImpl.setIntfInsertedDate(ivldItemHierarchyDefinition.getIntfInsertedDate());
		ivldItemHierarchyDefinitionImpl.setModifiedBy(ivldItemHierarchyDefinition.getModifiedBy());
		ivldItemHierarchyDefinitionImpl.setReprocessedFlag(ivldItemHierarchyDefinition.getReprocessedFlag());
		ivldItemHierarchyDefinitionImpl.setCheckRecord(ivldItemHierarchyDefinition.isCheckRecord());

		return ivldItemHierarchyDefinitionImpl;
	}

	/**
	 * Returns the ivld item hierarchy definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item hierarchy definition
	 * @return the ivld item hierarchy definition
	 * @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchyDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldItemHierarchyDefinitionException {
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition = fetchByPrimaryKey(primaryKey);

		if (ivldItemHierarchyDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldItemHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldItemHierarchyDefinition;
	}

	/**
	 * Returns the ivld item hierarchy definition with the primary key or throws a {@link NoSuchIvldItemHierarchyDefinitionException} if it could not be found.
	 *
	 * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	 * @return the ivld item hierarchy definition
	 * @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchyDefinition findByPrimaryKey(
		int ivldItemHierarchyDefinitionSid)
		throws NoSuchIvldItemHierarchyDefinitionException {
		return findByPrimaryKey((Serializable)ivldItemHierarchyDefinitionSid);
	}

	/**
	 * Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item hierarchy definition
	 * @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchyDefinition fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemHierarchyDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldItemHierarchyDefinition ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition)serializable;

		if (ivldItemHierarchyDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				ivldItemHierarchyDefinition = (IvldItemHierarchyDefinition)session.get(IvldItemHierarchyDefinitionImpl.class,
						primaryKey);

				if (ivldItemHierarchyDefinition != null) {
					cacheResult(ivldItemHierarchyDefinition);
				}
				else {
					entityCache.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemHierarchyDefinitionImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemHierarchyDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldItemHierarchyDefinition;
	}

	/**
	 * Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	 * @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchyDefinition fetchByPrimaryKey(
		int ivldItemHierarchyDefinitionSid) {
		return fetchByPrimaryKey((Serializable)ivldItemHierarchyDefinitionSid);
	}

	@Override
	public Map<Serializable, IvldItemHierarchyDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldItemHierarchyDefinition> map = new HashMap<Serializable, IvldItemHierarchyDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldItemHierarchyDefinition ivldItemHierarchyDefinition = fetchByPrimaryKey(primaryKey);

			if (ivldItemHierarchyDefinition != null) {
				map.put(primaryKey, ivldItemHierarchyDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemHierarchyDefinitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(IvldItemHierarchyDefinition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDITEMHIERARCHYDEFINITION_WHERE_PKS_IN);

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

			for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : (List<IvldItemHierarchyDefinition>)q.list()) {
				map.put(ivldItemHierarchyDefinition.getPrimaryKeyObj(),
					ivldItemHierarchyDefinition);

				cacheResult(ivldItemHierarchyDefinition);

				uncachedPrimaryKeys.remove(ivldItemHierarchyDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldItemHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemHierarchyDefinitionImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld item hierarchy definitions.
	 *
	 * @return the ivld item hierarchy definitions
	 */
	@Override
	public List<IvldItemHierarchyDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld item hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item hierarchy definitions
	 * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	 * @return the range of ivld item hierarchy definitions
	 */
	@Override
	public List<IvldItemHierarchyDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld item hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item hierarchy definitions
	 * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld item hierarchy definitions
	 */
	@Override
	public List<IvldItemHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<IvldItemHierarchyDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld item hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item hierarchy definitions
	 * @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld item hierarchy definitions
	 */
	@Override
	public List<IvldItemHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<IvldItemHierarchyDefinition> orderByComparator,
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

		List<IvldItemHierarchyDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<IvldItemHierarchyDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDITEMHIERARCHYDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDITEMHIERARCHYDEFINITION;

				if (pagination) {
					sql = sql.concat(IvldItemHierarchyDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldItemHierarchyDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldItemHierarchyDefinition>)QueryUtil.list(q,
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
	 * Removes all the ivld item hierarchy definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldItemHierarchyDefinition ivldItemHierarchyDefinition : findAll()) {
			remove(ivldItemHierarchyDefinition);
		}
	}

	/**
	 * Returns the number of ivld item hierarchy definitions.
	 *
	 * @return the number of ivld item hierarchy definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDITEMHIERARCHYDEFINITION);

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
		return IvldItemHierarchyDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld item hierarchy definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldItemHierarchyDefinitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDITEMHIERARCHYDEFINITION = "SELECT ivldItemHierarchyDefinition FROM IvldItemHierarchyDefinition ivldItemHierarchyDefinition";
	private static final String _SQL_SELECT_IVLDITEMHIERARCHYDEFINITION_WHERE_PKS_IN =
		"SELECT ivldItemHierarchyDefinition FROM IvldItemHierarchyDefinition ivldItemHierarchyDefinition WHERE IVLD_ITEM_HIERARCHY_DEFINITION_SID IN (";
	private static final String _SQL_COUNT_IVLDITEMHIERARCHYDEFINITION = "SELECT COUNT(ivldItemHierarchyDefinition) FROM IvldItemHierarchyDefinition ivldItemHierarchyDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemHierarchyDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemHierarchyDefinition exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldItemHierarchyDefinitionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"member", "reasonForFailure", "itemHierarchyDefnIntfid",
				"bpiLvl", "modifiedDate", "alias", "createdBy", "createdDate",
				"source", "batchId", "addChgDelIndicator",
				"ivldItemHierarchyDefinitionSid", "errorField", "errorCode",
				"intfInsertedDate", "modifiedBy", "reprocessedFlag",
				"checkRecord"
			});
}