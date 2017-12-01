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

import com.stpl.app.exception.NoSuchIvldMasterDataAttributeException;
import com.stpl.app.model.IvldMasterDataAttribute;
import com.stpl.app.model.impl.IvldMasterDataAttributeImpl;
import com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl;
import com.stpl.app.service.persistence.IvldMasterDataAttributePersistence;

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
 * The persistence implementation for the ivld master data attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldMasterDataAttributePersistence
 * @see com.stpl.app.service.persistence.IvldMasterDataAttributeUtil
 * @generated
 */
@ProviderType
public class IvldMasterDataAttributePersistenceImpl extends BasePersistenceImpl<IvldMasterDataAttribute>
	implements IvldMasterDataAttributePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldMasterDataAttributeUtil} to access the ivld master data attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldMasterDataAttributeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
			IvldMasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
			IvldMasterDataAttributeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
			IvldMasterDataAttributeModelImpl.FINDER_CACHE_ENABLED,
			IvldMasterDataAttributeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
			IvldMasterDataAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldMasterDataAttributePersistenceImpl() {
		setModelClass(IvldMasterDataAttribute.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("column19", "COLUMN_19");
			dbColumnNames.put("column18", "COLUMN_18");
			dbColumnNames.put("masterAttribute", "MASTER_ATTRIBUTE");
			dbColumnNames.put("masterType", "MASTER_TYPE");
			dbColumnNames.put("dataAttributeIntfid", "DATA_ATTRIBUTE_INTFID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("masterId", "MASTER_ID");
			dbColumnNames.put("column10", "COLUMN_10");
			dbColumnNames.put("column11", "COLUMN_11");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("column12", "COLUMN_12");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("column13", "COLUMN_13");
			dbColumnNames.put("column14", "COLUMN_14");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("column15", "COLUMN_15");
			dbColumnNames.put("column16", "COLUMN_16");
			dbColumnNames.put("column17", "COLUMN_17");
			dbColumnNames.put("column4", "COLUMN_4");
			dbColumnNames.put("column3", "COLUMN_3");
			dbColumnNames.put("column2", "COLUMN_2");
			dbColumnNames.put("column1", "COLUMN_1");
			dbColumnNames.put("column8", "COLUMN_8");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("column7", "COLUMN_7");
			dbColumnNames.put("column6", "COLUMN_6");
			dbColumnNames.put("column5", "COLUMN_5");
			dbColumnNames.put("column20", "COLUMN_20");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("column9", "COLUMN_9");
			dbColumnNames.put("ivldMasterDataAtbteSid",
				"IVLD_MASTER_DATA_ATBTE_SID");
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
	 * Caches the ivld master data attribute in the entity cache if it is enabled.
	 *
	 * @param ivldMasterDataAttribute the ivld master data attribute
	 */
	@Override
	public void cacheResult(IvldMasterDataAttribute ivldMasterDataAttribute) {
		entityCache.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
			IvldMasterDataAttributeImpl.class,
			ivldMasterDataAttribute.getPrimaryKey(), ivldMasterDataAttribute);

		ivldMasterDataAttribute.resetOriginalValues();
	}

	/**
	 * Caches the ivld master data attributes in the entity cache if it is enabled.
	 *
	 * @param ivldMasterDataAttributes the ivld master data attributes
	 */
	@Override
	public void cacheResult(
		List<IvldMasterDataAttribute> ivldMasterDataAttributes) {
		for (IvldMasterDataAttribute ivldMasterDataAttribute : ivldMasterDataAttributes) {
			if (entityCache.getResult(
						IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
						IvldMasterDataAttributeImpl.class,
						ivldMasterDataAttribute.getPrimaryKey()) == null) {
				cacheResult(ivldMasterDataAttribute);
			}
			else {
				ivldMasterDataAttribute.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld master data attributes.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldMasterDataAttributeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld master data attribute.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldMasterDataAttribute ivldMasterDataAttribute) {
		entityCache.removeResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
			IvldMasterDataAttributeImpl.class,
			ivldMasterDataAttribute.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<IvldMasterDataAttribute> ivldMasterDataAttributes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldMasterDataAttribute ivldMasterDataAttribute : ivldMasterDataAttributes) {
			entityCache.removeResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
				IvldMasterDataAttributeImpl.class,
				ivldMasterDataAttribute.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld master data attribute with the primary key. Does not add the ivld master data attribute to the database.
	 *
	 * @param ivldMasterDataAtbteSid the primary key for the new ivld master data attribute
	 * @return the new ivld master data attribute
	 */
	@Override
	public IvldMasterDataAttribute create(int ivldMasterDataAtbteSid) {
		IvldMasterDataAttribute ivldMasterDataAttribute = new IvldMasterDataAttributeImpl();

		ivldMasterDataAttribute.setNew(true);
		ivldMasterDataAttribute.setPrimaryKey(ivldMasterDataAtbteSid);

		return ivldMasterDataAttribute;
	}

	/**
	 * Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	 * @return the ivld master data attribute that was removed
	 * @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	 */
	@Override
	public IvldMasterDataAttribute remove(int ivldMasterDataAtbteSid)
		throws NoSuchIvldMasterDataAttributeException {
		return remove((Serializable)ivldMasterDataAtbteSid);
	}

	/**
	 * Removes the ivld master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld master data attribute
	 * @return the ivld master data attribute that was removed
	 * @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	 */
	@Override
	public IvldMasterDataAttribute remove(Serializable primaryKey)
		throws NoSuchIvldMasterDataAttributeException {
		Session session = null;

		try {
			session = openSession();

			IvldMasterDataAttribute ivldMasterDataAttribute = (IvldMasterDataAttribute)session.get(IvldMasterDataAttributeImpl.class,
					primaryKey);

			if (ivldMasterDataAttribute == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldMasterDataAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldMasterDataAttribute);
		}
		catch (NoSuchIvldMasterDataAttributeException nsee) {
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
	protected IvldMasterDataAttribute removeImpl(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		ivldMasterDataAttribute = toUnwrappedModel(ivldMasterDataAttribute);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldMasterDataAttribute)) {
				ivldMasterDataAttribute = (IvldMasterDataAttribute)session.get(IvldMasterDataAttributeImpl.class,
						ivldMasterDataAttribute.getPrimaryKeyObj());
			}

			if (ivldMasterDataAttribute != null) {
				session.delete(ivldMasterDataAttribute);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldMasterDataAttribute != null) {
			clearCache(ivldMasterDataAttribute);
		}

		return ivldMasterDataAttribute;
	}

	@Override
	public IvldMasterDataAttribute updateImpl(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		ivldMasterDataAttribute = toUnwrappedModel(ivldMasterDataAttribute);

		boolean isNew = ivldMasterDataAttribute.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldMasterDataAttribute.isNew()) {
				session.save(ivldMasterDataAttribute);

				ivldMasterDataAttribute.setNew(false);
			}
			else {
				ivldMasterDataAttribute = (IvldMasterDataAttribute)session.merge(ivldMasterDataAttribute);
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

		entityCache.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
			IvldMasterDataAttributeImpl.class,
			ivldMasterDataAttribute.getPrimaryKey(), ivldMasterDataAttribute,
			false);

		ivldMasterDataAttribute.resetOriginalValues();

		return ivldMasterDataAttribute;
	}

	protected IvldMasterDataAttribute toUnwrappedModel(
		IvldMasterDataAttribute ivldMasterDataAttribute) {
		if (ivldMasterDataAttribute instanceof IvldMasterDataAttributeImpl) {
			return ivldMasterDataAttribute;
		}

		IvldMasterDataAttributeImpl ivldMasterDataAttributeImpl = new IvldMasterDataAttributeImpl();

		ivldMasterDataAttributeImpl.setNew(ivldMasterDataAttribute.isNew());
		ivldMasterDataAttributeImpl.setPrimaryKey(ivldMasterDataAttribute.getPrimaryKey());

		ivldMasterDataAttributeImpl.setColumn19(ivldMasterDataAttribute.getColumn19());
		ivldMasterDataAttributeImpl.setColumn18(ivldMasterDataAttribute.getColumn18());
		ivldMasterDataAttributeImpl.setMasterAttribute(ivldMasterDataAttribute.getMasterAttribute());
		ivldMasterDataAttributeImpl.setMasterType(ivldMasterDataAttribute.getMasterType());
		ivldMasterDataAttributeImpl.setDataAttributeIntfid(ivldMasterDataAttribute.getDataAttributeIntfid());
		ivldMasterDataAttributeImpl.setModifiedDate(ivldMasterDataAttribute.getModifiedDate());
		ivldMasterDataAttributeImpl.setSource(ivldMasterDataAttribute.getSource());
		ivldMasterDataAttributeImpl.setCreatedDate(ivldMasterDataAttribute.getCreatedDate());
		ivldMasterDataAttributeImpl.setCreatedBy(ivldMasterDataAttribute.getCreatedBy());
		ivldMasterDataAttributeImpl.setAddChgDelIndicator(ivldMasterDataAttribute.getAddChgDelIndicator());
		ivldMasterDataAttributeImpl.setMasterId(ivldMasterDataAttribute.getMasterId());
		ivldMasterDataAttributeImpl.setColumn10(ivldMasterDataAttribute.getColumn10());
		ivldMasterDataAttributeImpl.setColumn11(ivldMasterDataAttribute.getColumn11());
		ivldMasterDataAttributeImpl.setErrorCode(ivldMasterDataAttribute.getErrorCode());
		ivldMasterDataAttributeImpl.setColumn12(ivldMasterDataAttribute.getColumn12());
		ivldMasterDataAttributeImpl.setIntfInsertedDate(ivldMasterDataAttribute.getIntfInsertedDate());
		ivldMasterDataAttributeImpl.setModifiedBy(ivldMasterDataAttribute.getModifiedBy());
		ivldMasterDataAttributeImpl.setColumn13(ivldMasterDataAttribute.getColumn13());
		ivldMasterDataAttributeImpl.setColumn14(ivldMasterDataAttribute.getColumn14());
		ivldMasterDataAttributeImpl.setReprocessedFlag(ivldMasterDataAttribute.getReprocessedFlag());
		ivldMasterDataAttributeImpl.setColumn15(ivldMasterDataAttribute.getColumn15());
		ivldMasterDataAttributeImpl.setColumn16(ivldMasterDataAttribute.getColumn16());
		ivldMasterDataAttributeImpl.setColumn17(ivldMasterDataAttribute.getColumn17());
		ivldMasterDataAttributeImpl.setColumn4(ivldMasterDataAttribute.getColumn4());
		ivldMasterDataAttributeImpl.setColumn3(ivldMasterDataAttribute.getColumn3());
		ivldMasterDataAttributeImpl.setColumn2(ivldMasterDataAttribute.getColumn2());
		ivldMasterDataAttributeImpl.setColumn1(ivldMasterDataAttribute.getColumn1());
		ivldMasterDataAttributeImpl.setColumn8(ivldMasterDataAttribute.getColumn8());
		ivldMasterDataAttributeImpl.setReasonForFailure(ivldMasterDataAttribute.getReasonForFailure());
		ivldMasterDataAttributeImpl.setColumn7(ivldMasterDataAttribute.getColumn7());
		ivldMasterDataAttributeImpl.setColumn6(ivldMasterDataAttribute.getColumn6());
		ivldMasterDataAttributeImpl.setColumn5(ivldMasterDataAttribute.getColumn5());
		ivldMasterDataAttributeImpl.setColumn20(ivldMasterDataAttribute.getColumn20());
		ivldMasterDataAttributeImpl.setBatchId(ivldMasterDataAttribute.getBatchId());
		ivldMasterDataAttributeImpl.setErrorField(ivldMasterDataAttribute.getErrorField());
		ivldMasterDataAttributeImpl.setColumn9(ivldMasterDataAttribute.getColumn9());
		ivldMasterDataAttributeImpl.setIvldMasterDataAtbteSid(ivldMasterDataAttribute.getIvldMasterDataAtbteSid());
		ivldMasterDataAttributeImpl.setCheckRecord(ivldMasterDataAttribute.isCheckRecord());

		return ivldMasterDataAttributeImpl;
	}

	/**
	 * Returns the ivld master data attribute with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld master data attribute
	 * @return the ivld master data attribute
	 * @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	 */
	@Override
	public IvldMasterDataAttribute findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldMasterDataAttributeException {
		IvldMasterDataAttribute ivldMasterDataAttribute = fetchByPrimaryKey(primaryKey);

		if (ivldMasterDataAttribute == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldMasterDataAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldMasterDataAttribute;
	}

	/**
	 * Returns the ivld master data attribute with the primary key or throws a {@link NoSuchIvldMasterDataAttributeException} if it could not be found.
	 *
	 * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	 * @return the ivld master data attribute
	 * @throws NoSuchIvldMasterDataAttributeException if a ivld master data attribute with the primary key could not be found
	 */
	@Override
	public IvldMasterDataAttribute findByPrimaryKey(int ivldMasterDataAtbteSid)
		throws NoSuchIvldMasterDataAttributeException {
		return findByPrimaryKey((Serializable)ivldMasterDataAtbteSid);
	}

	/**
	 * Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld master data attribute
	 * @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
	 */
	@Override
	public IvldMasterDataAttribute fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
				IvldMasterDataAttributeImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldMasterDataAttribute ivldMasterDataAttribute = (IvldMasterDataAttribute)serializable;

		if (ivldMasterDataAttribute == null) {
			Session session = null;

			try {
				session = openSession();

				ivldMasterDataAttribute = (IvldMasterDataAttribute)session.get(IvldMasterDataAttributeImpl.class,
						primaryKey);

				if (ivldMasterDataAttribute != null) {
					cacheResult(ivldMasterDataAttribute);
				}
				else {
					entityCache.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
						IvldMasterDataAttributeImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
					IvldMasterDataAttributeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldMasterDataAttribute;
	}

	/**
	 * Returns the ivld master data attribute with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldMasterDataAtbteSid the primary key of the ivld master data attribute
	 * @return the ivld master data attribute, or <code>null</code> if a ivld master data attribute with the primary key could not be found
	 */
	@Override
	public IvldMasterDataAttribute fetchByPrimaryKey(int ivldMasterDataAtbteSid) {
		return fetchByPrimaryKey((Serializable)ivldMasterDataAtbteSid);
	}

	@Override
	public Map<Serializable, IvldMasterDataAttribute> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldMasterDataAttribute> map = new HashMap<Serializable, IvldMasterDataAttribute>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldMasterDataAttribute ivldMasterDataAttribute = fetchByPrimaryKey(primaryKey);

			if (ivldMasterDataAttribute != null) {
				map.put(primaryKey, ivldMasterDataAttribute);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
					IvldMasterDataAttributeImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldMasterDataAttribute)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDMASTERDATAATTRIBUTE_WHERE_PKS_IN);

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

			for (IvldMasterDataAttribute ivldMasterDataAttribute : (List<IvldMasterDataAttribute>)q.list()) {
				map.put(ivldMasterDataAttribute.getPrimaryKeyObj(),
					ivldMasterDataAttribute);

				cacheResult(ivldMasterDataAttribute);

				uncachedPrimaryKeys.remove(ivldMasterDataAttribute.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldMasterDataAttributeModelImpl.ENTITY_CACHE_ENABLED,
					IvldMasterDataAttributeImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld master data attributes.
	 *
	 * @return the ivld master data attributes
	 */
	@Override
	public List<IvldMasterDataAttribute> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld master data attributes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld master data attributes
	 * @param end the upper bound of the range of ivld master data attributes (not inclusive)
	 * @return the range of ivld master data attributes
	 */
	@Override
	public List<IvldMasterDataAttribute> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld master data attributes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld master data attributes
	 * @param end the upper bound of the range of ivld master data attributes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld master data attributes
	 */
	@Override
	public List<IvldMasterDataAttribute> findAll(int start, int end,
		OrderByComparator<IvldMasterDataAttribute> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld master data attributes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldMasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld master data attributes
	 * @param end the upper bound of the range of ivld master data attributes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld master data attributes
	 */
	@Override
	public List<IvldMasterDataAttribute> findAll(int start, int end,
		OrderByComparator<IvldMasterDataAttribute> orderByComparator,
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

		List<IvldMasterDataAttribute> list = null;

		if (retrieveFromCache) {
			list = (List<IvldMasterDataAttribute>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDMASTERDATAATTRIBUTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDMASTERDATAATTRIBUTE;

				if (pagination) {
					sql = sql.concat(IvldMasterDataAttributeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldMasterDataAttribute>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldMasterDataAttribute>)QueryUtil.list(q,
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
	 * Removes all the ivld master data attributes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldMasterDataAttribute ivldMasterDataAttribute : findAll()) {
			remove(ivldMasterDataAttribute);
		}
	}

	/**
	 * Returns the number of ivld master data attributes.
	 *
	 * @return the number of ivld master data attributes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDMASTERDATAATTRIBUTE);

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
		return IvldMasterDataAttributeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld master data attribute persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldMasterDataAttributeImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDMASTERDATAATTRIBUTE = "SELECT ivldMasterDataAttribute FROM IvldMasterDataAttribute ivldMasterDataAttribute";
	private static final String _SQL_SELECT_IVLDMASTERDATAATTRIBUTE_WHERE_PKS_IN =
		"SELECT ivldMasterDataAttribute FROM IvldMasterDataAttribute ivldMasterDataAttribute WHERE IVLD_MASTER_DATA_ATBTE_SID IN (";
	private static final String _SQL_COUNT_IVLDMASTERDATAATTRIBUTE = "SELECT COUNT(ivldMasterDataAttribute) FROM IvldMasterDataAttribute ivldMasterDataAttribute";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldMasterDataAttribute.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldMasterDataAttribute exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldMasterDataAttributePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"column19", "column18", "masterAttribute", "masterType",
				"dataAttributeIntfid", "modifiedDate", "source", "createdDate",
				"createdBy", "addChgDelIndicator", "masterId", "column10",
				"column11", "errorCode", "column12", "intfInsertedDate",
				"modifiedBy", "column13", "column14", "reprocessedFlag",
				"column15", "column16", "column17", "column4", "column3",
				"column2", "column1", "column8", "reasonForFailure", "column7",
				"column6", "column5", "column20", "batchId", "errorField",
				"column9", "ivldMasterDataAtbteSid", "checkRecord"
			});
}