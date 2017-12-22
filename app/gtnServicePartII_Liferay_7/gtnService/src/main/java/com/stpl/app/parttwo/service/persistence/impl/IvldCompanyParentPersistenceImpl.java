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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyParentException;
import com.stpl.app.parttwo.model.IvldCompanyParent;
import com.stpl.app.parttwo.model.impl.IvldCompanyParentImpl;
import com.stpl.app.parttwo.model.impl.IvldCompanyParentModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCompanyParentPersistence;

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
 * The persistence implementation for the ivld company parent service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyParentPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldCompanyParentUtil
 * @generated
 */
@ProviderType
public class IvldCompanyParentPersistenceImpl extends BasePersistenceImpl<IvldCompanyParent>
	implements IvldCompanyParentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCompanyParentUtil} to access the ivld company parent persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCompanyParentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyParentModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyParentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyParentModelImpl.FINDER_CACHE_ENABLED,
			IvldCompanyParentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyParentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCompanyParentPersistenceImpl() {
		setModelClass(IvldCompanyParent.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("parentcompanyId", "PARENT_COMPANY_ID");
			dbColumnNames.put("priorParentcompanyId", "PRIOR_PARENT_COMPANY_ID");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("companyIdString", "COMPANY_ID");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("parentEndDate", "PARENT_END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("parentDetailsIntfid", "PARENT_DETAILS_INTFID");
			dbColumnNames.put("priorParentStartDate", "PRIOR_PARENT_START_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("ivldCompanyParentSid", "IVLD_COMPANY_PARENT_SID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("parentStartDate", "PARENT_START_DATE");
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
	 * Caches the ivld company parent in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyParent the ivld company parent
	 */
	@Override
	public void cacheResult(IvldCompanyParent ivldCompanyParent) {
		entityCache.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey(),
			ivldCompanyParent);

		ivldCompanyParent.resetOriginalValues();
	}

	/**
	 * Caches the ivld company parents in the entity cache if it is enabled.
	 *
	 * @param ivldCompanyParents the ivld company parents
	 */
	@Override
	public void cacheResult(List<IvldCompanyParent> ivldCompanyParents) {
		for (IvldCompanyParent ivldCompanyParent : ivldCompanyParents) {
			if (entityCache.getResult(
						IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyParentImpl.class,
						ivldCompanyParent.getPrimaryKey()) == null) {
				cacheResult(ivldCompanyParent);
			}
			else {
				ivldCompanyParent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld company parents.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCompanyParentImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld company parent.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCompanyParent ivldCompanyParent) {
		entityCache.removeResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldCompanyParent> ivldCompanyParents) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCompanyParent ivldCompanyParent : ivldCompanyParents) {
			entityCache.removeResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
	 *
	 * @param ivldCompanyParentSid the primary key for the new ivld company parent
	 * @return the new ivld company parent
	 */
	@Override
	public IvldCompanyParent create(int ivldCompanyParentSid) {
		IvldCompanyParent ivldCompanyParent = new IvldCompanyParentImpl();

		ivldCompanyParent.setNew(true);
		ivldCompanyParent.setPrimaryKey(ivldCompanyParentSid);

		return ivldCompanyParent;
	}

	/**
	 * Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCompanyParentSid the primary key of the ivld company parent
	 * @return the ivld company parent that was removed
	 * @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	 */
	@Override
	public IvldCompanyParent remove(int ivldCompanyParentSid)
		throws NoSuchIvldCompanyParentException {
		return remove((Serializable)ivldCompanyParentSid);
	}

	/**
	 * Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld company parent
	 * @return the ivld company parent that was removed
	 * @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	 */
	@Override
	public IvldCompanyParent remove(Serializable primaryKey)
		throws NoSuchIvldCompanyParentException {
		Session session = null;

		try {
			session = openSession();

			IvldCompanyParent ivldCompanyParent = (IvldCompanyParent)session.get(IvldCompanyParentImpl.class,
					primaryKey);

			if (ivldCompanyParent == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCompanyParentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCompanyParent);
		}
		catch (NoSuchIvldCompanyParentException nsee) {
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
	protected IvldCompanyParent removeImpl(IvldCompanyParent ivldCompanyParent) {
		ivldCompanyParent = toUnwrappedModel(ivldCompanyParent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCompanyParent)) {
				ivldCompanyParent = (IvldCompanyParent)session.get(IvldCompanyParentImpl.class,
						ivldCompanyParent.getPrimaryKeyObj());
			}

			if (ivldCompanyParent != null) {
				session.delete(ivldCompanyParent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCompanyParent != null) {
			clearCache(ivldCompanyParent);
		}

		return ivldCompanyParent;
	}

	@Override
	public IvldCompanyParent updateImpl(IvldCompanyParent ivldCompanyParent) {
		ivldCompanyParent = toUnwrappedModel(ivldCompanyParent);

		boolean isNew = ivldCompanyParent.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCompanyParent.isNew()) {
				session.save(ivldCompanyParent);

				ivldCompanyParent.setNew(false);
			}
			else {
				ivldCompanyParent = (IvldCompanyParent)session.merge(ivldCompanyParent);
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

		entityCache.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
			IvldCompanyParentImpl.class, ivldCompanyParent.getPrimaryKey(),
			ivldCompanyParent, false);

		ivldCompanyParent.resetOriginalValues();

		return ivldCompanyParent;
	}

	protected IvldCompanyParent toUnwrappedModel(
		IvldCompanyParent ivldCompanyParent) {
		if (ivldCompanyParent instanceof IvldCompanyParentImpl) {
			return ivldCompanyParent;
		}

		IvldCompanyParentImpl ivldCompanyParentImpl = new IvldCompanyParentImpl();

		ivldCompanyParentImpl.setNew(ivldCompanyParent.isNew());
		ivldCompanyParentImpl.setPrimaryKey(ivldCompanyParent.getPrimaryKey());

		ivldCompanyParentImpl.setParentcompanyId(ivldCompanyParent.getParentcompanyId());
		ivldCompanyParentImpl.setPriorParentcompanyId(ivldCompanyParent.getPriorParentcompanyId());
		ivldCompanyParentImpl.setReasonForFailure(ivldCompanyParent.getReasonForFailure());
		ivldCompanyParentImpl.setCompanyIdString(ivldCompanyParent.getCompanyIdString());
		ivldCompanyParentImpl.setLastUpdatedDate(ivldCompanyParent.getLastUpdatedDate());
		ivldCompanyParentImpl.setParentEndDate(ivldCompanyParent.getParentEndDate());
		ivldCompanyParentImpl.setModifiedDate(ivldCompanyParent.getModifiedDate());
		ivldCompanyParentImpl.setParentDetailsIntfid(ivldCompanyParent.getParentDetailsIntfid());
		ivldCompanyParentImpl.setPriorParentStartDate(ivldCompanyParent.getPriorParentStartDate());
		ivldCompanyParentImpl.setSource(ivldCompanyParent.getSource());
		ivldCompanyParentImpl.setCreatedBy(ivldCompanyParent.getCreatedBy());
		ivldCompanyParentImpl.setCreatedDate(ivldCompanyParent.getCreatedDate());
		ivldCompanyParentImpl.setAddChgDelIndicator(ivldCompanyParent.getAddChgDelIndicator());
		ivldCompanyParentImpl.setBatchId(ivldCompanyParent.getBatchId());
		ivldCompanyParentImpl.setIvldCompanyParentSid(ivldCompanyParent.getIvldCompanyParentSid());
		ivldCompanyParentImpl.setErrorField(ivldCompanyParent.getErrorField());
		ivldCompanyParentImpl.setErrorCode(ivldCompanyParent.getErrorCode());
		ivldCompanyParentImpl.setIntfInsertedDate(ivldCompanyParent.getIntfInsertedDate());
		ivldCompanyParentImpl.setModifiedBy(ivldCompanyParent.getModifiedBy());
		ivldCompanyParentImpl.setReprocessedFlag(ivldCompanyParent.getReprocessedFlag());
		ivldCompanyParentImpl.setParentStartDate(ivldCompanyParent.getParentStartDate());
		ivldCompanyParentImpl.setCheckRecord(ivldCompanyParent.isCheckRecord());

		return ivldCompanyParentImpl;
	}

	/**
	 * Returns the ivld company parent with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company parent
	 * @return the ivld company parent
	 * @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	 */
	@Override
	public IvldCompanyParent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCompanyParentException {
		IvldCompanyParent ivldCompanyParent = fetchByPrimaryKey(primaryKey);

		if (ivldCompanyParent == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCompanyParentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCompanyParent;
	}

	/**
	 * Returns the ivld company parent with the primary key or throws a {@link NoSuchIvldCompanyParentException} if it could not be found.
	 *
	 * @param ivldCompanyParentSid the primary key of the ivld company parent
	 * @return the ivld company parent
	 * @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	 */
	@Override
	public IvldCompanyParent findByPrimaryKey(int ivldCompanyParentSid)
		throws NoSuchIvldCompanyParentException {
		return findByPrimaryKey((Serializable)ivldCompanyParentSid);
	}

	/**
	 * Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld company parent
	 * @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
	 */
	@Override
	public IvldCompanyParent fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
				IvldCompanyParentImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCompanyParent ivldCompanyParent = (IvldCompanyParent)serializable;

		if (ivldCompanyParent == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCompanyParent = (IvldCompanyParent)session.get(IvldCompanyParentImpl.class,
						primaryKey);

				if (ivldCompanyParent != null) {
					cacheResult(ivldCompanyParent);
				}
				else {
					entityCache.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
						IvldCompanyParentImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyParentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCompanyParent;
	}

	/**
	 * Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCompanyParentSid the primary key of the ivld company parent
	 * @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
	 */
	@Override
	public IvldCompanyParent fetchByPrimaryKey(int ivldCompanyParentSid) {
		return fetchByPrimaryKey((Serializable)ivldCompanyParentSid);
	}

	@Override
	public Map<Serializable, IvldCompanyParent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCompanyParent> map = new HashMap<Serializable, IvldCompanyParent>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCompanyParent ivldCompanyParent = fetchByPrimaryKey(primaryKey);

			if (ivldCompanyParent != null) {
				map.put(primaryKey, ivldCompanyParent);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyParentImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCompanyParent)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCOMPANYPARENT_WHERE_PKS_IN);

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

			for (IvldCompanyParent ivldCompanyParent : (List<IvldCompanyParent>)q.list()) {
				map.put(ivldCompanyParent.getPrimaryKeyObj(), ivldCompanyParent);

				cacheResult(ivldCompanyParent);

				uncachedPrimaryKeys.remove(ivldCompanyParent.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCompanyParentModelImpl.ENTITY_CACHE_ENABLED,
					IvldCompanyParentImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld company parents.
	 *
	 * @return the ivld company parents
	 */
	@Override
	public List<IvldCompanyParent> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld company parents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company parents
	 * @param end the upper bound of the range of ivld company parents (not inclusive)
	 * @return the range of ivld company parents
	 */
	@Override
	public List<IvldCompanyParent> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld company parents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company parents
	 * @param end the upper bound of the range of ivld company parents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld company parents
	 */
	@Override
	public List<IvldCompanyParent> findAll(int start, int end,
		OrderByComparator<IvldCompanyParent> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld company parents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld company parents
	 * @param end the upper bound of the range of ivld company parents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld company parents
	 */
	@Override
	public List<IvldCompanyParent> findAll(int start, int end,
		OrderByComparator<IvldCompanyParent> orderByComparator,
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

		List<IvldCompanyParent> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCompanyParent>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCOMPANYPARENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCOMPANYPARENT;

				if (pagination) {
					sql = sql.concat(IvldCompanyParentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCompanyParent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCompanyParent>)QueryUtil.list(q,
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
	 * Removes all the ivld company parents from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCompanyParent ivldCompanyParent : findAll()) {
			remove(ivldCompanyParent);
		}
	}

	/**
	 * Returns the number of ivld company parents.
	 *
	 * @return the number of ivld company parents
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCOMPANYPARENT);

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
		return IvldCompanyParentModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld company parent persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCompanyParentImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDCOMPANYPARENT = "SELECT ivldCompanyParent FROM IvldCompanyParent ivldCompanyParent";
	private static final String _SQL_SELECT_IVLDCOMPANYPARENT_WHERE_PKS_IN = "SELECT ivldCompanyParent FROM IvldCompanyParent ivldCompanyParent WHERE IVLD_COMPANY_PARENT_SID IN (";
	private static final String _SQL_COUNT_IVLDCOMPANYPARENT = "SELECT COUNT(ivldCompanyParent) FROM IvldCompanyParent ivldCompanyParent";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCompanyParent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCompanyParent exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCompanyParentPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"parentcompanyId", "priorParentcompanyId", "reasonForFailure",
				"companyIdString", "lastUpdatedDate", "parentEndDate",
				"modifiedDate", "parentDetailsIntfid", "priorParentStartDate",
				"source", "createdBy", "createdDate", "addChgDelIndicator",
				"batchId", "ivldCompanyParentSid", "errorField", "errorCode",
				"intfInsertedDate", "modifiedBy", "reprocessedFlag",
				"parentStartDate", "checkRecord"
			});
}