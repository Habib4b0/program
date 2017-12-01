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

import com.stpl.app.exception.NoSuchIvldItemHierarchyException;
import com.stpl.app.model.IvldItemHierarchy;
import com.stpl.app.model.impl.IvldItemHierarchyImpl;
import com.stpl.app.model.impl.IvldItemHierarchyModelImpl;
import com.stpl.app.service.persistence.IvldItemHierarchyPersistence;

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
 * The persistence implementation for the ivld item hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyPersistence
 * @see com.stpl.app.service.persistence.IvldItemHierarchyUtil
 * @generated
 */
@ProviderType
public class IvldItemHierarchyPersistenceImpl extends BasePersistenceImpl<IvldItemHierarchy>
	implements IvldItemHierarchyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldItemHierarchyUtil} to access the ivld item hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldItemHierarchyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyModelImpl.FINDER_CACHE_ENABLED,
			IvldItemHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyModelImpl.FINDER_CACHE_ENABLED,
			IvldItemHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldItemHierarchyPersistenceImpl() {
		setModelClass(IvldItemHierarchy.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("level1Alias", "LEVEL1_ALIAS");
			dbColumnNames.put("level9Alias", "LEVEL9_ALIAS");
			dbColumnNames.put("level3Alias", "LEVEL3_ALIAS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("level13Alias", "LEVEL13_ALIAS");
			dbColumnNames.put("gtnCompany", "GTN_COMPANY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("level15Alias", "LEVEL15_ALIAS");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("ivldItemHierarchySid", "IVLD_ITEM_HIERARCHY_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("level6Alias", "LEVEL6_ALIAS");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("level10Alias", "LEVEL10_ALIAS");
			dbColumnNames.put("itemHierarchyIntfid", "ITEM_HIERARCHY_INTFID");
			dbColumnNames.put("level5Alias", "LEVEL5_ALIAS");
			dbColumnNames.put("level18Alias", "LEVEL18_ALIAS");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("gtnTherapeuticClass", "GTN_THERAPEUTIC_CLASS");
			dbColumnNames.put("level8", "LEVEL8");
			dbColumnNames.put("level9", "LEVEL9");
			dbColumnNames.put("level11Alias", "LEVEL11_ALIAS");
			dbColumnNames.put("level20", "LEVEL20");
			dbColumnNames.put("level4", "LEVEL4");
			dbColumnNames.put("level5", "LEVEL5");
			dbColumnNames.put("level6", "LEVEL6");
			dbColumnNames.put("level7", "LEVEL7");
			dbColumnNames.put("level16Alias", "LEVEL16_ALIAS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("gtnBrand", "GTN_BRAND");
			dbColumnNames.put("level2Alias", "LEVEL2_ALIAS");
			dbColumnNames.put("level1", "LEVEL1");
			dbColumnNames.put("level0", "LEVEL0");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("level3", "LEVEL3");
			dbColumnNames.put("level17Alias", "LEVEL17_ALIAS");
			dbColumnNames.put("level20Alias", "LEVEL20_ALIAS");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("level7Alias", "LEVEL7_ALIAS");
			dbColumnNames.put("level2", "LEVEL2");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("level8Alias", "LEVEL8_ALIAS");
			dbColumnNames.put("level10", "LEVEL10");
			dbColumnNames.put("level4Alias", "LEVEL4_ALIAS");
			dbColumnNames.put("level12", "LEVEL12");
			dbColumnNames.put("level11", "LEVEL11");
			dbColumnNames.put("level14", "LEVEL14");
			dbColumnNames.put("level0Tag", "LEVEL0_TAG");
			dbColumnNames.put("level13", "LEVEL13");
			dbColumnNames.put("level16", "LEVEL16");
			dbColumnNames.put("level15", "LEVEL15");
			dbColumnNames.put("level18", "LEVEL18");
			dbColumnNames.put("level17", "LEVEL17");
			dbColumnNames.put("level19", "LEVEL19");
			dbColumnNames.put("level12Alias", "LEVEL12_ALIAS");
			dbColumnNames.put("level19Alias", "LEVEL19_ALIAS");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("level0Alias", "LEVEL0_ALIAS");
			dbColumnNames.put("level14Alias", "LEVEL14_ALIAS");
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
	 * Caches the ivld item hierarchy in the entity cache if it is enabled.
	 *
	 * @param ivldItemHierarchy the ivld item hierarchy
	 */
	@Override
	public void cacheResult(IvldItemHierarchy ivldItemHierarchy) {
		entityCache.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey(),
			ivldItemHierarchy);

		ivldItemHierarchy.resetOriginalValues();
	}

	/**
	 * Caches the ivld item hierarchies in the entity cache if it is enabled.
	 *
	 * @param ivldItemHierarchies the ivld item hierarchies
	 */
	@Override
	public void cacheResult(List<IvldItemHierarchy> ivldItemHierarchies) {
		for (IvldItemHierarchy ivldItemHierarchy : ivldItemHierarchies) {
			if (entityCache.getResult(
						IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemHierarchyImpl.class,
						ivldItemHierarchy.getPrimaryKey()) == null) {
				cacheResult(ivldItemHierarchy);
			}
			else {
				ivldItemHierarchy.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld item hierarchies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldItemHierarchyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld item hierarchy.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldItemHierarchy ivldItemHierarchy) {
		entityCache.removeResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldItemHierarchy> ivldItemHierarchies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldItemHierarchy ivldItemHierarchy : ivldItemHierarchies) {
			entityCache.removeResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld item hierarchy with the primary key. Does not add the ivld item hierarchy to the database.
	 *
	 * @param ivldItemHierarchySid the primary key for the new ivld item hierarchy
	 * @return the new ivld item hierarchy
	 */
	@Override
	public IvldItemHierarchy create(int ivldItemHierarchySid) {
		IvldItemHierarchy ivldItemHierarchy = new IvldItemHierarchyImpl();

		ivldItemHierarchy.setNew(true);
		ivldItemHierarchy.setPrimaryKey(ivldItemHierarchySid);

		return ivldItemHierarchy;
	}

	/**
	 * Removes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
	 * @return the ivld item hierarchy that was removed
	 * @throws NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchy remove(int ivldItemHierarchySid)
		throws NoSuchIvldItemHierarchyException {
		return remove((Serializable)ivldItemHierarchySid);
	}

	/**
	 * Removes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld item hierarchy
	 * @return the ivld item hierarchy that was removed
	 * @throws NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchy remove(Serializable primaryKey)
		throws NoSuchIvldItemHierarchyException {
		Session session = null;

		try {
			session = openSession();

			IvldItemHierarchy ivldItemHierarchy = (IvldItemHierarchy)session.get(IvldItemHierarchyImpl.class,
					primaryKey);

			if (ivldItemHierarchy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldItemHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldItemHierarchy);
		}
		catch (NoSuchIvldItemHierarchyException nsee) {
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
	protected IvldItemHierarchy removeImpl(IvldItemHierarchy ivldItemHierarchy) {
		ivldItemHierarchy = toUnwrappedModel(ivldItemHierarchy);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldItemHierarchy)) {
				ivldItemHierarchy = (IvldItemHierarchy)session.get(IvldItemHierarchyImpl.class,
						ivldItemHierarchy.getPrimaryKeyObj());
			}

			if (ivldItemHierarchy != null) {
				session.delete(ivldItemHierarchy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldItemHierarchy != null) {
			clearCache(ivldItemHierarchy);
		}

		return ivldItemHierarchy;
	}

	@Override
	public IvldItemHierarchy updateImpl(IvldItemHierarchy ivldItemHierarchy) {
		ivldItemHierarchy = toUnwrappedModel(ivldItemHierarchy);

		boolean isNew = ivldItemHierarchy.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldItemHierarchy.isNew()) {
				session.save(ivldItemHierarchy);

				ivldItemHierarchy.setNew(false);
			}
			else {
				ivldItemHierarchy = (IvldItemHierarchy)session.merge(ivldItemHierarchy);
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

		entityCache.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemHierarchyImpl.class, ivldItemHierarchy.getPrimaryKey(),
			ivldItemHierarchy, false);

		ivldItemHierarchy.resetOriginalValues();

		return ivldItemHierarchy;
	}

	protected IvldItemHierarchy toUnwrappedModel(
		IvldItemHierarchy ivldItemHierarchy) {
		if (ivldItemHierarchy instanceof IvldItemHierarchyImpl) {
			return ivldItemHierarchy;
		}

		IvldItemHierarchyImpl ivldItemHierarchyImpl = new IvldItemHierarchyImpl();

		ivldItemHierarchyImpl.setNew(ivldItemHierarchy.isNew());
		ivldItemHierarchyImpl.setPrimaryKey(ivldItemHierarchy.getPrimaryKey());

		ivldItemHierarchyImpl.setLevel1Alias(ivldItemHierarchy.getLevel1Alias());
		ivldItemHierarchyImpl.setLevel9Alias(ivldItemHierarchy.getLevel9Alias());
		ivldItemHierarchyImpl.setLevel3Alias(ivldItemHierarchy.getLevel3Alias());
		ivldItemHierarchyImpl.setModifiedDate(ivldItemHierarchy.getModifiedDate());
		ivldItemHierarchyImpl.setLevel13Alias(ivldItemHierarchy.getLevel13Alias());
		ivldItemHierarchyImpl.setGtnCompany(ivldItemHierarchy.getGtnCompany());
		ivldItemHierarchyImpl.setSource(ivldItemHierarchy.getSource());
		ivldItemHierarchyImpl.setLevel15Alias(ivldItemHierarchy.getLevel15Alias());
		ivldItemHierarchyImpl.setAddChgDelIndicator(ivldItemHierarchy.getAddChgDelIndicator());
		ivldItemHierarchyImpl.setIvldItemHierarchySid(ivldItemHierarchy.getIvldItemHierarchySid());
		ivldItemHierarchyImpl.setModifiedBy(ivldItemHierarchy.getModifiedBy());
		ivldItemHierarchyImpl.setLevel6Alias(ivldItemHierarchy.getLevel6Alias());
		ivldItemHierarchyImpl.setReasonForFailure(ivldItemHierarchy.getReasonForFailure());
		ivldItemHierarchyImpl.setLevel10Alias(ivldItemHierarchy.getLevel10Alias());
		ivldItemHierarchyImpl.setItemHierarchyIntfid(ivldItemHierarchy.getItemHierarchyIntfid());
		ivldItemHierarchyImpl.setLevel5Alias(ivldItemHierarchy.getLevel5Alias());
		ivldItemHierarchyImpl.setLevel18Alias(ivldItemHierarchy.getLevel18Alias());
		ivldItemHierarchyImpl.setErrorField(ivldItemHierarchy.getErrorField());
		ivldItemHierarchyImpl.setGtnTherapeuticClass(ivldItemHierarchy.getGtnTherapeuticClass());
		ivldItemHierarchyImpl.setLevel8(ivldItemHierarchy.getLevel8());
		ivldItemHierarchyImpl.setLevel9(ivldItemHierarchy.getLevel9());
		ivldItemHierarchyImpl.setLevel11Alias(ivldItemHierarchy.getLevel11Alias());
		ivldItemHierarchyImpl.setLevel20(ivldItemHierarchy.getLevel20());
		ivldItemHierarchyImpl.setLevel4(ivldItemHierarchy.getLevel4());
		ivldItemHierarchyImpl.setLevel5(ivldItemHierarchy.getLevel5());
		ivldItemHierarchyImpl.setLevel6(ivldItemHierarchy.getLevel6());
		ivldItemHierarchyImpl.setLevel7(ivldItemHierarchy.getLevel7());
		ivldItemHierarchyImpl.setLevel16Alias(ivldItemHierarchy.getLevel16Alias());
		ivldItemHierarchyImpl.setCreatedDate(ivldItemHierarchy.getCreatedDate());
		ivldItemHierarchyImpl.setCreatedBy(ivldItemHierarchy.getCreatedBy());
		ivldItemHierarchyImpl.setGtnBrand(ivldItemHierarchy.getGtnBrand());
		ivldItemHierarchyImpl.setLevel2Alias(ivldItemHierarchy.getLevel2Alias());
		ivldItemHierarchyImpl.setLevel1(ivldItemHierarchy.getLevel1());
		ivldItemHierarchyImpl.setLevel0(ivldItemHierarchy.getLevel0());
		ivldItemHierarchyImpl.setErrorCode(ivldItemHierarchy.getErrorCode());
		ivldItemHierarchyImpl.setLevel3(ivldItemHierarchy.getLevel3());
		ivldItemHierarchyImpl.setLevel17Alias(ivldItemHierarchy.getLevel17Alias());
		ivldItemHierarchyImpl.setLevel20Alias(ivldItemHierarchy.getLevel20Alias());
		ivldItemHierarchyImpl.setIntfInsertedDate(ivldItemHierarchy.getIntfInsertedDate());
		ivldItemHierarchyImpl.setLevel7Alias(ivldItemHierarchy.getLevel7Alias());
		ivldItemHierarchyImpl.setLevel2(ivldItemHierarchy.getLevel2());
		ivldItemHierarchyImpl.setReprocessedFlag(ivldItemHierarchy.getReprocessedFlag());
		ivldItemHierarchyImpl.setLevel8Alias(ivldItemHierarchy.getLevel8Alias());
		ivldItemHierarchyImpl.setLevel10(ivldItemHierarchy.getLevel10());
		ivldItemHierarchyImpl.setLevel4Alias(ivldItemHierarchy.getLevel4Alias());
		ivldItemHierarchyImpl.setLevel12(ivldItemHierarchy.getLevel12());
		ivldItemHierarchyImpl.setLevel11(ivldItemHierarchy.getLevel11());
		ivldItemHierarchyImpl.setLevel14(ivldItemHierarchy.getLevel14());
		ivldItemHierarchyImpl.setLevel0Tag(ivldItemHierarchy.getLevel0Tag());
		ivldItemHierarchyImpl.setLevel13(ivldItemHierarchy.getLevel13());
		ivldItemHierarchyImpl.setLevel16(ivldItemHierarchy.getLevel16());
		ivldItemHierarchyImpl.setLevel15(ivldItemHierarchy.getLevel15());
		ivldItemHierarchyImpl.setLevel18(ivldItemHierarchy.getLevel18());
		ivldItemHierarchyImpl.setLevel17(ivldItemHierarchy.getLevel17());
		ivldItemHierarchyImpl.setLevel19(ivldItemHierarchy.getLevel19());
		ivldItemHierarchyImpl.setLevel12Alias(ivldItemHierarchy.getLevel12Alias());
		ivldItemHierarchyImpl.setLevel19Alias(ivldItemHierarchy.getLevel19Alias());
		ivldItemHierarchyImpl.setBatchId(ivldItemHierarchy.getBatchId());
		ivldItemHierarchyImpl.setLevel0Alias(ivldItemHierarchy.getLevel0Alias());
		ivldItemHierarchyImpl.setLevel14Alias(ivldItemHierarchy.getLevel14Alias());
		ivldItemHierarchyImpl.setCheckRecord(ivldItemHierarchy.isCheckRecord());

		return ivldItemHierarchyImpl;
	}

	/**
	 * Returns the ivld item hierarchy with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item hierarchy
	 * @return the ivld item hierarchy
	 * @throws NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldItemHierarchyException {
		IvldItemHierarchy ivldItemHierarchy = fetchByPrimaryKey(primaryKey);

		if (ivldItemHierarchy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldItemHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldItemHierarchy;
	}

	/**
	 * Returns the ivld item hierarchy with the primary key or throws a {@link NoSuchIvldItemHierarchyException} if it could not be found.
	 *
	 * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
	 * @return the ivld item hierarchy
	 * @throws NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchy findByPrimaryKey(int ivldItemHierarchySid)
		throws NoSuchIvldItemHierarchyException {
		return findByPrimaryKey((Serializable)ivldItemHierarchySid);
	}

	/**
	 * Returns the ivld item hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item hierarchy
	 * @return the ivld item hierarchy, or <code>null</code> if a ivld item hierarchy with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchy fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemHierarchyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldItemHierarchy ivldItemHierarchy = (IvldItemHierarchy)serializable;

		if (ivldItemHierarchy == null) {
			Session session = null;

			try {
				session = openSession();

				ivldItemHierarchy = (IvldItemHierarchy)session.get(IvldItemHierarchyImpl.class,
						primaryKey);

				if (ivldItemHierarchy != null) {
					cacheResult(ivldItemHierarchy);
				}
				else {
					entityCache.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemHierarchyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemHierarchyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldItemHierarchy;
	}

	/**
	 * Returns the ivld item hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
	 * @return the ivld item hierarchy, or <code>null</code> if a ivld item hierarchy with the primary key could not be found
	 */
	@Override
	public IvldItemHierarchy fetchByPrimaryKey(int ivldItemHierarchySid) {
		return fetchByPrimaryKey((Serializable)ivldItemHierarchySid);
	}

	@Override
	public Map<Serializable, IvldItemHierarchy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldItemHierarchy> map = new HashMap<Serializable, IvldItemHierarchy>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldItemHierarchy ivldItemHierarchy = fetchByPrimaryKey(primaryKey);

			if (ivldItemHierarchy != null) {
				map.put(primaryKey, ivldItemHierarchy);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemHierarchyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldItemHierarchy)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDITEMHIERARCHY_WHERE_PKS_IN);

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

			for (IvldItemHierarchy ivldItemHierarchy : (List<IvldItemHierarchy>)q.list()) {
				map.put(ivldItemHierarchy.getPrimaryKeyObj(), ivldItemHierarchy);

				cacheResult(ivldItemHierarchy);

				uncachedPrimaryKeys.remove(ivldItemHierarchy.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldItemHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemHierarchyImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld item hierarchies.
	 *
	 * @return the ivld item hierarchies
	 */
	@Override
	public List<IvldItemHierarchy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld item hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item hierarchies
	 * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
	 * @return the range of ivld item hierarchies
	 */
	@Override
	public List<IvldItemHierarchy> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld item hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item hierarchies
	 * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld item hierarchies
	 */
	@Override
	public List<IvldItemHierarchy> findAll(int start, int end,
		OrderByComparator<IvldItemHierarchy> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld item hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item hierarchies
	 * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld item hierarchies
	 */
	@Override
	public List<IvldItemHierarchy> findAll(int start, int end,
		OrderByComparator<IvldItemHierarchy> orderByComparator,
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

		List<IvldItemHierarchy> list = null;

		if (retrieveFromCache) {
			list = (List<IvldItemHierarchy>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDITEMHIERARCHY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDITEMHIERARCHY;

				if (pagination) {
					sql = sql.concat(IvldItemHierarchyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldItemHierarchy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldItemHierarchy>)QueryUtil.list(q,
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
	 * Removes all the ivld item hierarchies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldItemHierarchy ivldItemHierarchy : findAll()) {
			remove(ivldItemHierarchy);
		}
	}

	/**
	 * Returns the number of ivld item hierarchies.
	 *
	 * @return the number of ivld item hierarchies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDITEMHIERARCHY);

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
		return IvldItemHierarchyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld item hierarchy persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldItemHierarchyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDITEMHIERARCHY = "SELECT ivldItemHierarchy FROM IvldItemHierarchy ivldItemHierarchy";
	private static final String _SQL_SELECT_IVLDITEMHIERARCHY_WHERE_PKS_IN = "SELECT ivldItemHierarchy FROM IvldItemHierarchy ivldItemHierarchy WHERE IVLD_ITEM_HIERARCHY_SID IN (";
	private static final String _SQL_COUNT_IVLDITEMHIERARCHY = "SELECT COUNT(ivldItemHierarchy) FROM IvldItemHierarchy ivldItemHierarchy";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemHierarchy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemHierarchy exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldItemHierarchyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"level1Alias", "level9Alias", "level3Alias", "modifiedDate",
				"level13Alias", "gtnCompany", "source", "level15Alias",
				"addChgDelIndicator", "ivldItemHierarchySid", "modifiedBy",
				"level6Alias", "reasonForFailure", "level10Alias",
				"itemHierarchyIntfid", "level5Alias", "level18Alias",
				"errorField", "gtnTherapeuticClass", "level8", "level9",
				"level11Alias", "level20", "level4", "level5", "level6",
				"level7", "level16Alias", "createdDate", "createdBy", "gtnBrand",
				"level2Alias", "level1", "level0", "errorCode", "level3",
				"level17Alias", "level20Alias", "intfInsertedDate",
				"level7Alias", "level2", "reprocessedFlag", "level8Alias",
				"level10", "level4Alias", "level12", "level11", "level14",
				"level0Tag", "level13", "level16", "level15", "level18",
				"level17", "level19", "level12Alias", "level19Alias", "batchId",
				"level0Alias", "level14Alias", "checkRecord"
			});
}