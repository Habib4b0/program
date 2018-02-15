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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldCompanyIdentifier;
import com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyIdentifierPersistence;

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
 * The persistence implementation for the ivld company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldCompanyIdentifierUtil
 * @generated
 */
@ProviderType
public class IvldCompanyIdentifierPersistenceImpl extends BasePersistenceImpl<IvldCompanyIdentifier>
	implements IvldCompanyIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCompanyIdentifierUtil} to access the ivld company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCompanyIdentifierPersistenceImpl() {
		setModelClass(IvldCompanyIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("companyIdString", "COMPANY_ID");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("identifierStatus", "IDENTIFIER_STATUS");
			dbColumnNames.put("companyIdentifier", "COMPANY_IDENTIFIER");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("companyIdentifierIntfid",
				"COMPANY_IDENTIFIER_INTFID");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("identifierCodeQualifierName",
				"IDENTIFIER_CODE_QUALIFIER_NAME");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("ivldCompanyIdentifierSid",
				"IVLD_COMPANY_IDENTIFIER_SID");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("identifierCodeQualifier",
				"IDENTIFIER_CODE_QUALIFIER");
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
	 * Caches the ivld company identifier in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyIdentifier the ivld company identifier
	 */
	@Override
	public void cacheResult(IvldCompanyIdentifier ivldCompanyIdentifier) {
		entityCache.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyIdentifierImpl.class,
			ivldCompanyIdentifier.getPrimaryKey(), ivldCompanyIdentifier);

		ivldCompanyIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the ivld company identifiers in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyIdentifiers the ivld company identifiers
	 */
	@Override
	public void cacheResult(List<IvldCompanyIdentifier> ivldCompanyIdentifiers) {
		for (IvldCompanyIdentifier ivldCompanyIdentifier : ivldCompanyIdentifiers) {
			if (entityCache.getResult(
						IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyIdentifierImpl.class,
						ivldCompanyIdentifier.getPrimaryKey()) == null) {
				cacheResult(ivldCompanyIdentifier);
			}
			else {
				ivldCompanyIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld company identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCompanyIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld company identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCompanyIdentifier ivldCompanyIdentifier) {
		entityCache.removeResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyIdentifierImpl.class,
			ivldCompanyIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldCompanyIdentifier> ivldCompanyIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCompanyIdentifier ivldCompanyIdentifier : ivldCompanyIdentifiers) {
			entityCache.removeResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyIdentifierImpl.class,
				ivldCompanyIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
	 *
	 * @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
	 * @return the new ivld company identifier
	 */
	@Override
	public IvldCompanyIdentifier create(int ivldCompanyIdentifierSid) {
		IvldCompanyIdentifier ivldCompanyIdentifier = new IvldCompanyIdentifierImpl();

		ivldCompanyIdentifier.setNew(true);
		ivldCompanyIdentifier.setPrimaryKey(ivldCompanyIdentifierSid);

		return ivldCompanyIdentifier;
	}

	/**
	 * Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	 * @return the ivld company identifier that was removed
	 * @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	 */
	@Override
	public IvldCompanyIdentifier remove(int ivldCompanyIdentifierSid)
		throws NoSuchIvldCompanyIdentifierException {
		return remove((Serializable)ivldCompanyIdentifierSid);
	}

	/**
	 * Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld company identifier
	 * @return the ivld company identifier that was removed
	 * @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	 */
	@Override
	public IvldCompanyIdentifier remove(Serializable primaryKey)
		throws NoSuchIvldCompanyIdentifierException {
		Session session = null;

		try {
			session = openSession();

			IvldCompanyIdentifier ivldCompanyIdentifier = (IvldCompanyIdentifier)session.get(IvldCompanyIdentifierImpl.class,
					primaryKey);

			if (ivldCompanyIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCompanyIdentifier);
		}
		catch (NoSuchIvldCompanyIdentifierException nsee) {
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
	protected IvldCompanyIdentifier removeImpl(
		IvldCompanyIdentifier ivldCompanyIdentifier) {
		ivldCompanyIdentifier = toUnwrappedModel(ivldCompanyIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCompanyIdentifier)) {
				ivldCompanyIdentifier = (IvldCompanyIdentifier)session.get(IvldCompanyIdentifierImpl.class,
						ivldCompanyIdentifier.getPrimaryKeyObj());
			}

			if (ivldCompanyIdentifier != null) {
				session.delete(ivldCompanyIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCompanyIdentifier != null) {
			clearCache(ivldCompanyIdentifier);
		}

		return ivldCompanyIdentifier;
	}

	@Override
	public IvldCompanyIdentifier updateImpl(
		IvldCompanyIdentifier ivldCompanyIdentifier) {
		ivldCompanyIdentifier = toUnwrappedModel(ivldCompanyIdentifier);

		boolean isNew = ivldCompanyIdentifier.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCompanyIdentifier.isNew()) {
				session.save(ivldCompanyIdentifier);

				ivldCompanyIdentifier.setNew(false);
			}
			else {
				ivldCompanyIdentifier = (IvldCompanyIdentifier)session.merge(ivldCompanyIdentifier);
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

		entityCache.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyIdentifierImpl.class,
			ivldCompanyIdentifier.getPrimaryKey(), ivldCompanyIdentifier, false);

		ivldCompanyIdentifier.resetOriginalValues();

		return ivldCompanyIdentifier;
	}

	protected IvldCompanyIdentifier toUnwrappedModel(
		IvldCompanyIdentifier ivldCompanyIdentifier) {
		if (ivldCompanyIdentifier instanceof IvldCompanyIdentifierImpl) {
			return ivldCompanyIdentifier;
		}

		IvldCompanyIdentifierImpl ivldCompanyIdentifierImpl = new IvldCompanyIdentifierImpl();

		ivldCompanyIdentifierImpl.setNew(ivldCompanyIdentifier.isNew());
		ivldCompanyIdentifierImpl.setPrimaryKey(ivldCompanyIdentifier.getPrimaryKey());

		ivldCompanyIdentifierImpl.setReasonForFailure(ivldCompanyIdentifier.getReasonForFailure());
		ivldCompanyIdentifierImpl.setCompanyIdString(ivldCompanyIdentifier.getCompanyIdString());
		ivldCompanyIdentifierImpl.setCompanyName(ivldCompanyIdentifier.getCompanyName());
		ivldCompanyIdentifierImpl.setEndDate(ivldCompanyIdentifier.getEndDate());
		ivldCompanyIdentifierImpl.setModifiedDate(ivldCompanyIdentifier.getModifiedDate());
		ivldCompanyIdentifierImpl.setIdentifierStatus(ivldCompanyIdentifier.getIdentifierStatus());
		ivldCompanyIdentifierImpl.setCompanyIdentifier(ivldCompanyIdentifier.getCompanyIdentifier());
		ivldCompanyIdentifierImpl.setEntityCode(ivldCompanyIdentifier.getEntityCode());
		ivldCompanyIdentifierImpl.setCompanyIdentifierIntfid(ivldCompanyIdentifier.getCompanyIdentifierIntfid());
		ivldCompanyIdentifierImpl.setStartDate(ivldCompanyIdentifier.getStartDate());
		ivldCompanyIdentifierImpl.setSource(ivldCompanyIdentifier.getSource());
		ivldCompanyIdentifierImpl.setCreatedDate(ivldCompanyIdentifier.getCreatedDate());
		ivldCompanyIdentifierImpl.setCreatedBy(ivldCompanyIdentifier.getCreatedBy());
		ivldCompanyIdentifierImpl.setCompanyNo(ivldCompanyIdentifier.getCompanyNo());
		ivldCompanyIdentifierImpl.setAddChgDelIndicator(ivldCompanyIdentifier.getAddChgDelIndicator());
		ivldCompanyIdentifierImpl.setBatchId(ivldCompanyIdentifier.getBatchId());
		ivldCompanyIdentifierImpl.setErrorField(ivldCompanyIdentifier.getErrorField());
		ivldCompanyIdentifierImpl.setErrorCode(ivldCompanyIdentifier.getErrorCode());
		ivldCompanyIdentifierImpl.setIdentifierCodeQualifierName(ivldCompanyIdentifier.getIdentifierCodeQualifierName());
		ivldCompanyIdentifierImpl.setIntfInsertedDate(ivldCompanyIdentifier.getIntfInsertedDate());
		ivldCompanyIdentifierImpl.setModifiedBy(ivldCompanyIdentifier.getModifiedBy());
		ivldCompanyIdentifierImpl.setIvldCompanyIdentifierSid(ivldCompanyIdentifier.getIvldCompanyIdentifierSid());
		ivldCompanyIdentifierImpl.setReprocessedFlag(ivldCompanyIdentifier.getReprocessedFlag());
		ivldCompanyIdentifierImpl.setIdentifierCodeQualifier(ivldCompanyIdentifier.getIdentifierCodeQualifier());
		ivldCompanyIdentifierImpl.setCheckRecord(ivldCompanyIdentifier.isCheckRecord());

		return ivldCompanyIdentifierImpl;
	}

	/**
	 * Returns the ivld company identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company identifier
	 * @return the ivld company identifier
	 * @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	 */
	@Override
	public IvldCompanyIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCompanyIdentifierException {
		IvldCompanyIdentifier ivldCompanyIdentifier = fetchByPrimaryKey(primaryKey);

		if (ivldCompanyIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCompanyIdentifier;
	}

	/**
	 * Returns the ivld company identifier with the primary key or throws a {@link NoSuchIvldCompanyIdentifierException} if it could not be found.
	 *
	 * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	 * @return the ivld company identifier
	 * @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	 */
	@Override
	public IvldCompanyIdentifier findByPrimaryKey(int ivldCompanyIdentifierSid)
		throws NoSuchIvldCompanyIdentifierException {
		return findByPrimaryKey((Serializable)ivldCompanyIdentifierSid);
	}

	/**
	 * Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company identifier
	 * @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
	 */
	@Override
	public IvldCompanyIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCompanyIdentifier ivldCompanyIdentifier = (IvldCompanyIdentifier)serializable;

		if (ivldCompanyIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCompanyIdentifier = (IvldCompanyIdentifier)session.get(IvldCompanyIdentifierImpl.class,
						primaryKey);

				if (ivldCompanyIdentifier != null) {
					cacheResult(ivldCompanyIdentifier);
				}
				else {
					entityCache.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCompanyIdentifier;
	}

	/**
	 * Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	 * @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
	 */
	@Override
	public IvldCompanyIdentifier fetchByPrimaryKey(int ivldCompanyIdentifierSid) {
		return fetchByPrimaryKey((Serializable)ivldCompanyIdentifierSid);
	}

	@Override
	public Map<Serializable, IvldCompanyIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCompanyIdentifier> map = new HashMap<Serializable, IvldCompanyIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCompanyIdentifier ivldCompanyIdentifier = fetchByPrimaryKey(primaryKey);

			if (ivldCompanyIdentifier != null) {
				map.put(primaryKey, ivldCompanyIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCompanyIdentifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCOMPANYIDENTIFIER_WHERE_PKS_IN);

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

			for (IvldCompanyIdentifier ivldCompanyIdentifier : (List<IvldCompanyIdentifier>)q.list()) {
				map.put(ivldCompanyIdentifier.getPrimaryKeyObj(),
					ivldCompanyIdentifier);

				cacheResult(ivldCompanyIdentifier);

				uncachedPrimaryKeys.remove(ivldCompanyIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyIdentifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld company identifiers.
	 *
	 * @return the ivld company identifiers
	 */
	@Override
	public List<IvldCompanyIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld company identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company identifiers
	 * @param end the upper bound of the range of ivld company identifiers (not inclusive)
	 * @return the range of ivld company identifiers
	 */
	@Override
	public List<IvldCompanyIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld company identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company identifiers
	 * @param end the upper bound of the range of ivld company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld company identifiers
	 */
	@Override
	public List<IvldCompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldCompanyIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld company identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company identifiers
	 * @param end the upper bound of the range of ivld company identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld company identifiers
	 */
	@Override
	public List<IvldCompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldCompanyIdentifier> orderByComparator,
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

		List<IvldCompanyIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCompanyIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCOMPANYIDENTIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCOMPANYIDENTIFIER;

				if (pagination) {
					sql = sql.concat(IvldCompanyIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCompanyIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCompanyIdentifier>)QueryUtil.list(q,
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
	 * Removes all the ivld company identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCompanyIdentifier ivldCompanyIdentifier : findAll()) {
			remove(ivldCompanyIdentifier);
		}
	}

	/**
	 * Returns the number of ivld company identifiers.
	 *
	 * @return the number of ivld company identifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYIDENTIFIER);

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
		return IvldCompanyIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld company identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCompanyIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDCOMPANYIDENTIFIER = "SELECT ivldCompanyIdentifier FROM IvldCompanyIdentifier ivldCompanyIdentifier";
	private static final String _SQL_SELECT_IVLDCOMPANYIDENTIFIER_WHERE_PKS_IN = "SELECT ivldCompanyIdentifier FROM IvldCompanyIdentifier ivldCompanyIdentifier WHERE IVLD_COMPANY_IDENTIFIER_SID IN (";
	private static final String _SQL_COUNT_IVLDCOMPANYIDENTIFIER = "SELECT COUNT(ivldCompanyIdentifier) FROM IvldCompanyIdentifier ivldCompanyIdentifier";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyIdentifier exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCompanyIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"reasonForFailure", "companyIdString", "companyName", "endDate",
				"modifiedDate", "identifierStatus", "companyIdentifier",
				"entityCode", "companyIdentifierIntfid", "startDate", "source",
				"createdDate", "createdBy", "companyNo", "addChgDelIndicator",
				"batchId", "errorField", "errorCode",
				"identifierCodeQualifierName", "intfInsertedDate", "modifiedBy",
				"ivldCompanyIdentifierSid", "reprocessedFlag",
				"identifierCodeQualifier", "checkRecord"
			});
}