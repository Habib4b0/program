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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchIvldcompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldcompanyIdentifier;
import com.stpl.app.parttwo.model.impl.IvldcompanyIdentifierImpl;
import com.stpl.app.parttwo.model.impl.IvldcompanyIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldcompanyIdentifierPersistence;

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
 * The persistence implementation for the ivldcompany identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldcompanyIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldcompanyIdentifierUtil
 * @generated
 */
@ProviderType
public class IvldcompanyIdentifierPersistenceImpl extends BasePersistenceImpl<IvldcompanyIdentifier>
	implements IvldcompanyIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldcompanyIdentifierUtil} to access the ivldcompany identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldcompanyIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldcompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			IvldcompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldcompanyIdentifierModelImpl.FINDER_CACHE_ENABLED,
			IvldcompanyIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldcompanyIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldcompanyIdentifierPersistenceImpl() {
		setModelClass(IvldcompanyIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("companyId", "COMPANY_ID");
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
			dbColumnNames.put("ivldcompanyIdentifierSid",
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
	 * Caches the ivldcompany identifier in the entity cache if it is enabled.
	 *
	 * @param ivldcompanyIdentifier the ivldcompany identifier
	 */
	@Override
	public void cacheResult(IvldcompanyIdentifier ivldcompanyIdentifier) {
		entityCache.putResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldcompanyIdentifierImpl.class,
			ivldcompanyIdentifier.getPrimaryKey(), ivldcompanyIdentifier);

		ivldcompanyIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the ivldcompany identifiers in the entity cache if it is enabled.
	 *
	 * @param ivldcompanyIdentifiers the ivldcompany identifiers
	 */
	@Override
	public void cacheResult(List<IvldcompanyIdentifier> ivldcompanyIdentifiers) {
		for (IvldcompanyIdentifier ivldcompanyIdentifier : ivldcompanyIdentifiers) {
			if (entityCache.getResult(
						IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						IvldcompanyIdentifierImpl.class,
						ivldcompanyIdentifier.getPrimaryKey()) == null) {
				cacheResult(ivldcompanyIdentifier);
			}
			else {
				ivldcompanyIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivldcompany identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldcompanyIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivldcompany identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldcompanyIdentifier ivldcompanyIdentifier) {
		entityCache.removeResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldcompanyIdentifierImpl.class,
			ivldcompanyIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldcompanyIdentifier> ivldcompanyIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldcompanyIdentifier ivldcompanyIdentifier : ivldcompanyIdentifiers) {
			entityCache.removeResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				IvldcompanyIdentifierImpl.class,
				ivldcompanyIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivldcompany identifier with the primary key. Does not add the ivldcompany identifier to the database.
	 *
	 * @param ivldcompanyIdentifierSid the primary key for the new ivldcompany identifier
	 * @return the new ivldcompany identifier
	 */
	@Override
	public IvldcompanyIdentifier create(int ivldcompanyIdentifierSid) {
		IvldcompanyIdentifier ivldcompanyIdentifier = new IvldcompanyIdentifierImpl();

		ivldcompanyIdentifier.setNew(true);
		ivldcompanyIdentifier.setPrimaryKey(ivldcompanyIdentifierSid);

		ivldcompanyIdentifier.setCompanyId(companyProvider.getCompanyId());

		return ivldcompanyIdentifier;
	}

	/**
	 * Removes the ivldcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	 * @return the ivldcompany identifier that was removed
	 * @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	 */
	@Override
	public IvldcompanyIdentifier remove(int ivldcompanyIdentifierSid)
		throws NoSuchIvldcompanyIdentifierException {
		return remove((Serializable)ivldcompanyIdentifierSid);
	}

	/**
	 * Removes the ivldcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivldcompany identifier
	 * @return the ivldcompany identifier that was removed
	 * @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	 */
	@Override
	public IvldcompanyIdentifier remove(Serializable primaryKey)
		throws NoSuchIvldcompanyIdentifierException {
		Session session = null;

		try {
			session = openSession();

			IvldcompanyIdentifier ivldcompanyIdentifier = (IvldcompanyIdentifier)session.get(IvldcompanyIdentifierImpl.class,
					primaryKey);

			if (ivldcompanyIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldcompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldcompanyIdentifier);
		}
		catch (NoSuchIvldcompanyIdentifierException nsee) {
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
	protected IvldcompanyIdentifier removeImpl(
		IvldcompanyIdentifier ivldcompanyIdentifier) {
		ivldcompanyIdentifier = toUnwrappedModel(ivldcompanyIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldcompanyIdentifier)) {
				ivldcompanyIdentifier = (IvldcompanyIdentifier)session.get(IvldcompanyIdentifierImpl.class,
						ivldcompanyIdentifier.getPrimaryKeyObj());
			}

			if (ivldcompanyIdentifier != null) {
				session.delete(ivldcompanyIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldcompanyIdentifier != null) {
			clearCache(ivldcompanyIdentifier);
		}

		return ivldcompanyIdentifier;
	}

	@Override
	public IvldcompanyIdentifier updateImpl(
		IvldcompanyIdentifier ivldcompanyIdentifier) {
		ivldcompanyIdentifier = toUnwrappedModel(ivldcompanyIdentifier);

		boolean isNew = ivldcompanyIdentifier.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldcompanyIdentifier.isNew()) {
				session.save(ivldcompanyIdentifier);

				ivldcompanyIdentifier.setNew(false);
			}
			else {
				ivldcompanyIdentifier = (IvldcompanyIdentifier)session.merge(ivldcompanyIdentifier);
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

		entityCache.putResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			IvldcompanyIdentifierImpl.class,
			ivldcompanyIdentifier.getPrimaryKey(), ivldcompanyIdentifier, false);

		ivldcompanyIdentifier.resetOriginalValues();

		return ivldcompanyIdentifier;
	}

	protected IvldcompanyIdentifier toUnwrappedModel(
		IvldcompanyIdentifier ivldcompanyIdentifier) {
		if (ivldcompanyIdentifier instanceof IvldcompanyIdentifierImpl) {
			return ivldcompanyIdentifier;
		}

		IvldcompanyIdentifierImpl ivldcompanyIdentifierImpl = new IvldcompanyIdentifierImpl();

		ivldcompanyIdentifierImpl.setNew(ivldcompanyIdentifier.isNew());
		ivldcompanyIdentifierImpl.setPrimaryKey(ivldcompanyIdentifier.getPrimaryKey());

		ivldcompanyIdentifierImpl.setReasonForFailure(ivldcompanyIdentifier.getReasonForFailure());
		ivldcompanyIdentifierImpl.setCompanyId(ivldcompanyIdentifier.getCompanyId());
		ivldcompanyIdentifierImpl.setCompanyName(ivldcompanyIdentifier.getCompanyName());
		ivldcompanyIdentifierImpl.setEndDate(ivldcompanyIdentifier.getEndDate());
		ivldcompanyIdentifierImpl.setModifiedDate(ivldcompanyIdentifier.getModifiedDate());
		ivldcompanyIdentifierImpl.setIdentifierStatus(ivldcompanyIdentifier.getIdentifierStatus());
		ivldcompanyIdentifierImpl.setCompanyIdentifier(ivldcompanyIdentifier.getCompanyIdentifier());
		ivldcompanyIdentifierImpl.setEntityCode(ivldcompanyIdentifier.getEntityCode());
		ivldcompanyIdentifierImpl.setCompanyIdentifierIntfid(ivldcompanyIdentifier.getCompanyIdentifierIntfid());
		ivldcompanyIdentifierImpl.setStartDate(ivldcompanyIdentifier.getStartDate());
		ivldcompanyIdentifierImpl.setSource(ivldcompanyIdentifier.getSource());
		ivldcompanyIdentifierImpl.setCreatedDate(ivldcompanyIdentifier.getCreatedDate());
		ivldcompanyIdentifierImpl.setCreatedBy(ivldcompanyIdentifier.getCreatedBy());
		ivldcompanyIdentifierImpl.setCompanyNo(ivldcompanyIdentifier.getCompanyNo());
		ivldcompanyIdentifierImpl.setAddChgDelIndicator(ivldcompanyIdentifier.getAddChgDelIndicator());
		ivldcompanyIdentifierImpl.setBatchId(ivldcompanyIdentifier.getBatchId());
		ivldcompanyIdentifierImpl.setErrorField(ivldcompanyIdentifier.getErrorField());
		ivldcompanyIdentifierImpl.setErrorCode(ivldcompanyIdentifier.getErrorCode());
		ivldcompanyIdentifierImpl.setIdentifierCodeQualifierName(ivldcompanyIdentifier.getIdentifierCodeQualifierName());
		ivldcompanyIdentifierImpl.setIntfInsertedDate(ivldcompanyIdentifier.getIntfInsertedDate());
		ivldcompanyIdentifierImpl.setModifiedBy(ivldcompanyIdentifier.getModifiedBy());
		ivldcompanyIdentifierImpl.setIvldcompanyIdentifierSid(ivldcompanyIdentifier.getIvldcompanyIdentifierSid());
		ivldcompanyIdentifierImpl.setReprocessedFlag(ivldcompanyIdentifier.getReprocessedFlag());
		ivldcompanyIdentifierImpl.setIdentifierCodeQualifier(ivldcompanyIdentifier.getIdentifierCodeQualifier());
		ivldcompanyIdentifierImpl.setCheckRecord(ivldcompanyIdentifier.isCheckRecord());

		return ivldcompanyIdentifierImpl;
	}

	/**
	 * Returns the ivldcompany identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivldcompany identifier
	 * @return the ivldcompany identifier
	 * @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	 */
	@Override
	public IvldcompanyIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldcompanyIdentifierException {
		IvldcompanyIdentifier ivldcompanyIdentifier = fetchByPrimaryKey(primaryKey);

		if (ivldcompanyIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldcompanyIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldcompanyIdentifier;
	}

	/**
	 * Returns the ivldcompany identifier with the primary key or throws a {@link NoSuchIvldcompanyIdentifierException} if it could not be found.
	 *
	 * @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	 * @return the ivldcompany identifier
	 * @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	 */
	@Override
	public IvldcompanyIdentifier findByPrimaryKey(int ivldcompanyIdentifierSid)
		throws NoSuchIvldcompanyIdentifierException {
		return findByPrimaryKey((Serializable)ivldcompanyIdentifierSid);
	}

	/**
	 * Returns the ivldcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivldcompany identifier
	 * @return the ivldcompany identifier, or <code>null</code> if a ivldcompany identifier with the primary key could not be found
	 */
	@Override
	public IvldcompanyIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				IvldcompanyIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldcompanyIdentifier ivldcompanyIdentifier = (IvldcompanyIdentifier)serializable;

		if (ivldcompanyIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				ivldcompanyIdentifier = (IvldcompanyIdentifier)session.get(IvldcompanyIdentifierImpl.class,
						primaryKey);

				if (ivldcompanyIdentifier != null) {
					cacheResult(ivldcompanyIdentifier);
				}
				else {
					entityCache.putResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						IvldcompanyIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldcompanyIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldcompanyIdentifier;
	}

	/**
	 * Returns the ivldcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	 * @return the ivldcompany identifier, or <code>null</code> if a ivldcompany identifier with the primary key could not be found
	 */
	@Override
	public IvldcompanyIdentifier fetchByPrimaryKey(int ivldcompanyIdentifierSid) {
		return fetchByPrimaryKey((Serializable)ivldcompanyIdentifierSid);
	}

	@Override
	public Map<Serializable, IvldcompanyIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldcompanyIdentifier> map = new HashMap<Serializable, IvldcompanyIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldcompanyIdentifier ivldcompanyIdentifier = fetchByPrimaryKey(primaryKey);

			if (ivldcompanyIdentifier != null) {
				map.put(primaryKey, ivldcompanyIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldcompanyIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldcompanyIdentifier)serializable);
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

			for (IvldcompanyIdentifier ivldcompanyIdentifier : (List<IvldcompanyIdentifier>)q.list()) {
				map.put(ivldcompanyIdentifier.getPrimaryKeyObj(),
					ivldcompanyIdentifier);

				cacheResult(ivldcompanyIdentifier);

				uncachedPrimaryKeys.remove(ivldcompanyIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldcompanyIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					IvldcompanyIdentifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivldcompany identifiers.
	 *
	 * @return the ivldcompany identifiers
	 */
	@Override
	public List<IvldcompanyIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivldcompany identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivldcompany identifiers
	 * @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	 * @return the range of ivldcompany identifiers
	 */
	@Override
	public List<IvldcompanyIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivldcompany identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivldcompany identifiers
	 * @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivldcompany identifiers
	 */
	@Override
	public List<IvldcompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldcompanyIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivldcompany identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivldcompany identifiers
	 * @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivldcompany identifiers
	 */
	@Override
	public List<IvldcompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldcompanyIdentifier> orderByComparator,
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

		List<IvldcompanyIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<IvldcompanyIdentifier>)finderCache.getResult(finderPath,
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
					sql = sql.concat(IvldcompanyIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldcompanyIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldcompanyIdentifier>)QueryUtil.list(q,
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
	 * Removes all the ivldcompany identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldcompanyIdentifier ivldcompanyIdentifier : findAll()) {
			remove(ivldcompanyIdentifier);
		}
	}

	/**
	 * Returns the number of ivldcompany identifiers.
	 *
	 * @return the number of ivldcompany identifiers
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
		return IvldcompanyIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivldcompany identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldcompanyIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDCOMPANYIDENTIFIER = "SELECT ivldcompanyIdentifier FROM IvldcompanyIdentifier ivldcompanyIdentifier";
	private static final String _SQL_SELECT_IVLDCOMPANYIDENTIFIER_WHERE_PKS_IN = "SELECT ivldcompanyIdentifier FROM IvldcompanyIdentifier ivldcompanyIdentifier WHERE IVLD_COMPANY_IDENTIFIER_SID IN (";
	private static final String _SQL_COUNT_IVLDCOMPANYIDENTIFIER = "SELECT COUNT(ivldcompanyIdentifier) FROM IvldcompanyIdentifier ivldcompanyIdentifier";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldcompanyIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldcompanyIdentifier exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldcompanyIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"reasonForFailure", "companyId", "companyName", "endDate",
				"modifiedDate", "identifierStatus", "companyIdentifier",
				"entityCode", "companyIdentifierIntfid", "startDate", "source",
				"createdDate", "createdBy", "companyNo", "addChgDelIndicator",
				"batchId", "errorField", "errorCode",
				"identifierCodeQualifierName", "intfInsertedDate", "modifiedBy",
				"ivldcompanyIdentifierSid", "reprocessedFlag",
				"identifierCodeQualifier", "checkRecord"
			});
}