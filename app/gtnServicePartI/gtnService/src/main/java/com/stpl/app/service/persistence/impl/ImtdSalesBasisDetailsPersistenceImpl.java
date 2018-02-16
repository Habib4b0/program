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

import com.stpl.app.exception.NoSuchImtdSalesBasisDetailsException;
import com.stpl.app.model.ImtdSalesBasisDetails;
import com.stpl.app.model.impl.ImtdSalesBasisDetailsImpl;
import com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdSalesBasisDetailsPersistence;

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
 * The persistence implementation for the imtd sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdSalesBasisDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdSalesBasisDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdSalesBasisDetailsPersistenceImpl extends BasePersistenceImpl<ImtdSalesBasisDetails>
	implements ImtdSalesBasisDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdSalesBasisDetailsUtil} to access the imtd sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdSalesBasisDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdSalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdSalesBasisDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdSalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdSalesBasisDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdSalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ImtdSalesBasisDetailsPersistenceImpl() {
		setModelClass(ImtdSalesBasisDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("cfpNo", "CFP_NO");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("salesBasisDetailsSid", "SALES_BASIS_DETAILS_SID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("customerName", "CUSTOMER_NAME");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("customerNo", "CUSTOMER_NO");
			dbColumnNames.put("imtdSalesBasisDetailsSid",
				"IMTD_SALES_BASIS_DETAILS_SID");
			dbColumnNames.put("cfpName", "CFP_NAME");
			dbColumnNames.put("cdrModelSid", "CDR_MODEL_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("cfpContractDetailsSid",
				"CFP_CONTRACT_DETAILS_SID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the imtd sales basis details in the entity cache if it is enabled.
	 *
	 * @param imtdSalesBasisDetails the imtd sales basis details
	 */
	@Override
	public void cacheResult(ImtdSalesBasisDetails imtdSalesBasisDetails) {
		entityCache.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdSalesBasisDetailsImpl.class,
			imtdSalesBasisDetails.getPrimaryKey(), imtdSalesBasisDetails);

		imtdSalesBasisDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd sales basis detailses in the entity cache if it is enabled.
	 *
	 * @param imtdSalesBasisDetailses the imtd sales basis detailses
	 */
	@Override
	public void cacheResult(List<ImtdSalesBasisDetails> imtdSalesBasisDetailses) {
		for (ImtdSalesBasisDetails imtdSalesBasisDetails : imtdSalesBasisDetailses) {
			if (entityCache.getResult(
						ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdSalesBasisDetailsImpl.class,
						imtdSalesBasisDetails.getPrimaryKey()) == null) {
				cacheResult(imtdSalesBasisDetails);
			}
			else {
				imtdSalesBasisDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd sales basis detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdSalesBasisDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd sales basis details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdSalesBasisDetails imtdSalesBasisDetails) {
		entityCache.removeResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdSalesBasisDetailsImpl.class,
			imtdSalesBasisDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdSalesBasisDetails> imtdSalesBasisDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdSalesBasisDetails imtdSalesBasisDetails : imtdSalesBasisDetailses) {
			entityCache.removeResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdSalesBasisDetailsImpl.class,
				imtdSalesBasisDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd sales basis details with the primary key. Does not add the imtd sales basis details to the database.
	 *
	 * @param imtdSalesBasisDetailsSid the primary key for the new imtd sales basis details
	 * @return the new imtd sales basis details
	 */
	@Override
	public ImtdSalesBasisDetails create(int imtdSalesBasisDetailsSid) {
		ImtdSalesBasisDetails imtdSalesBasisDetails = new ImtdSalesBasisDetailsImpl();

		imtdSalesBasisDetails.setNew(true);
		imtdSalesBasisDetails.setPrimaryKey(imtdSalesBasisDetailsSid);

		return imtdSalesBasisDetails;
	}

	/**
	 * Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	 * @return the imtd sales basis details that was removed
	 * @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	 */
	@Override
	public ImtdSalesBasisDetails remove(int imtdSalesBasisDetailsSid)
		throws NoSuchImtdSalesBasisDetailsException {
		return remove((Serializable)imtdSalesBasisDetailsSid);
	}

	/**
	 * Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd sales basis details
	 * @return the imtd sales basis details that was removed
	 * @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	 */
	@Override
	public ImtdSalesBasisDetails remove(Serializable primaryKey)
		throws NoSuchImtdSalesBasisDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdSalesBasisDetails imtdSalesBasisDetails = (ImtdSalesBasisDetails)session.get(ImtdSalesBasisDetailsImpl.class,
					primaryKey);

			if (imtdSalesBasisDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdSalesBasisDetails);
		}
		catch (NoSuchImtdSalesBasisDetailsException nsee) {
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
	protected ImtdSalesBasisDetails removeImpl(
		ImtdSalesBasisDetails imtdSalesBasisDetails) {
		imtdSalesBasisDetails = toUnwrappedModel(imtdSalesBasisDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdSalesBasisDetails)) {
				imtdSalesBasisDetails = (ImtdSalesBasisDetails)session.get(ImtdSalesBasisDetailsImpl.class,
						imtdSalesBasisDetails.getPrimaryKeyObj());
			}

			if (imtdSalesBasisDetails != null) {
				session.delete(imtdSalesBasisDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdSalesBasisDetails != null) {
			clearCache(imtdSalesBasisDetails);
		}

		return imtdSalesBasisDetails;
	}

	@Override
	public ImtdSalesBasisDetails updateImpl(
		ImtdSalesBasisDetails imtdSalesBasisDetails) {
		imtdSalesBasisDetails = toUnwrappedModel(imtdSalesBasisDetails);

		boolean isNew = imtdSalesBasisDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdSalesBasisDetails.isNew()) {
				session.save(imtdSalesBasisDetails);

				imtdSalesBasisDetails.setNew(false);
			}
			else {
				imtdSalesBasisDetails = (ImtdSalesBasisDetails)session.merge(imtdSalesBasisDetails);
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

		entityCache.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdSalesBasisDetailsImpl.class,
			imtdSalesBasisDetails.getPrimaryKey(), imtdSalesBasisDetails, false);

		imtdSalesBasisDetails.resetOriginalValues();

		return imtdSalesBasisDetails;
	}

	protected ImtdSalesBasisDetails toUnwrappedModel(
		ImtdSalesBasisDetails imtdSalesBasisDetails) {
		if (imtdSalesBasisDetails instanceof ImtdSalesBasisDetailsImpl) {
			return imtdSalesBasisDetails;
		}

		ImtdSalesBasisDetailsImpl imtdSalesBasisDetailsImpl = new ImtdSalesBasisDetailsImpl();

		imtdSalesBasisDetailsImpl.setNew(imtdSalesBasisDetails.isNew());
		imtdSalesBasisDetailsImpl.setPrimaryKey(imtdSalesBasisDetails.getPrimaryKey());

		imtdSalesBasisDetailsImpl.setCreatedBy(imtdSalesBasisDetails.getCreatedBy());
		imtdSalesBasisDetailsImpl.setNetSalesFormulaMasterSid(imtdSalesBasisDetails.getNetSalesFormulaMasterSid());
		imtdSalesBasisDetailsImpl.setUsersSid(imtdSalesBasisDetails.getUsersSid());
		imtdSalesBasisDetailsImpl.setModifiedBy(imtdSalesBasisDetails.getModifiedBy());
		imtdSalesBasisDetailsImpl.setCreatedDate(imtdSalesBasisDetails.getCreatedDate());
		imtdSalesBasisDetailsImpl.setContractMasterSid(imtdSalesBasisDetails.getContractMasterSid());
		imtdSalesBasisDetailsImpl.setCfpNo(imtdSalesBasisDetails.getCfpNo());
		imtdSalesBasisDetailsImpl.setImtdCreatedDate(imtdSalesBasisDetails.getImtdCreatedDate());
		imtdSalesBasisDetailsImpl.setContractNo(imtdSalesBasisDetails.getContractNo());
		imtdSalesBasisDetailsImpl.setContractName(imtdSalesBasisDetails.getContractName());
		imtdSalesBasisDetailsImpl.setSalesBasisDetailsSid(imtdSalesBasisDetails.getSalesBasisDetailsSid());
		imtdSalesBasisDetailsImpl.setCheckRecord(imtdSalesBasisDetails.isCheckRecord());
		imtdSalesBasisDetailsImpl.setModifiedDate(imtdSalesBasisDetails.getModifiedDate());
		imtdSalesBasisDetailsImpl.setCustomerName(imtdSalesBasisDetails.getCustomerName());
		imtdSalesBasisDetailsImpl.setOperation(imtdSalesBasisDetails.getOperation());
		imtdSalesBasisDetailsImpl.setCustomerNo(imtdSalesBasisDetails.getCustomerNo());
		imtdSalesBasisDetailsImpl.setImtdSalesBasisDetailsSid(imtdSalesBasisDetails.getImtdSalesBasisDetailsSid());
		imtdSalesBasisDetailsImpl.setCfpName(imtdSalesBasisDetails.getCfpName());
		imtdSalesBasisDetailsImpl.setCdrModelSid(imtdSalesBasisDetails.getCdrModelSid());
		imtdSalesBasisDetailsImpl.setSessionId(imtdSalesBasisDetails.getSessionId());
		imtdSalesBasisDetailsImpl.setCfpContractDetailsSid(imtdSalesBasisDetails.getCfpContractDetailsSid());
		imtdSalesBasisDetailsImpl.setInboundStatus(imtdSalesBasisDetails.getInboundStatus());

		return imtdSalesBasisDetailsImpl;
	}

	/**
	 * Returns the imtd sales basis details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd sales basis details
	 * @return the imtd sales basis details
	 * @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	 */
	@Override
	public ImtdSalesBasisDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdSalesBasisDetailsException {
		ImtdSalesBasisDetails imtdSalesBasisDetails = fetchByPrimaryKey(primaryKey);

		if (imtdSalesBasisDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdSalesBasisDetails;
	}

	/**
	 * Returns the imtd sales basis details with the primary key or throws a {@link NoSuchImtdSalesBasisDetailsException} if it could not be found.
	 *
	 * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	 * @return the imtd sales basis details
	 * @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	 */
	@Override
	public ImtdSalesBasisDetails findByPrimaryKey(int imtdSalesBasisDetailsSid)
		throws NoSuchImtdSalesBasisDetailsException {
		return findByPrimaryKey((Serializable)imtdSalesBasisDetailsSid);
	}

	/**
	 * Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd sales basis details
	 * @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
	 */
	@Override
	public ImtdSalesBasisDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdSalesBasisDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdSalesBasisDetails imtdSalesBasisDetails = (ImtdSalesBasisDetails)serializable;

		if (imtdSalesBasisDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdSalesBasisDetails = (ImtdSalesBasisDetails)session.get(ImtdSalesBasisDetailsImpl.class,
						primaryKey);

				if (imtdSalesBasisDetails != null) {
					cacheResult(imtdSalesBasisDetails);
				}
				else {
					entityCache.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdSalesBasisDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdSalesBasisDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdSalesBasisDetails;
	}

	/**
	 * Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	 * @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
	 */
	@Override
	public ImtdSalesBasisDetails fetchByPrimaryKey(int imtdSalesBasisDetailsSid) {
		return fetchByPrimaryKey((Serializable)imtdSalesBasisDetailsSid);
	}

	@Override
	public Map<Serializable, ImtdSalesBasisDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdSalesBasisDetails> map = new HashMap<Serializable, ImtdSalesBasisDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdSalesBasisDetails imtdSalesBasisDetails = fetchByPrimaryKey(primaryKey);

			if (imtdSalesBasisDetails != null) {
				map.put(primaryKey, imtdSalesBasisDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdSalesBasisDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdSalesBasisDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDSALESBASISDETAILS_WHERE_PKS_IN);

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

			for (ImtdSalesBasisDetails imtdSalesBasisDetails : (List<ImtdSalesBasisDetails>)q.list()) {
				map.put(imtdSalesBasisDetails.getPrimaryKeyObj(),
					imtdSalesBasisDetails);

				cacheResult(imtdSalesBasisDetails);

				uncachedPrimaryKeys.remove(imtdSalesBasisDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdSalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdSalesBasisDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd sales basis detailses.
	 *
	 * @return the imtd sales basis detailses
	 */
	@Override
	public List<ImtdSalesBasisDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd sales basis detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd sales basis detailses
	 * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	 * @return the range of imtd sales basis detailses
	 */
	@Override
	public List<ImtdSalesBasisDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd sales basis detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd sales basis detailses
	 * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd sales basis detailses
	 */
	@Override
	public List<ImtdSalesBasisDetails> findAll(int start, int end,
		OrderByComparator<ImtdSalesBasisDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd sales basis detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd sales basis detailses
	 * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd sales basis detailses
	 */
	@Override
	public List<ImtdSalesBasisDetails> findAll(int start, int end,
		OrderByComparator<ImtdSalesBasisDetails> orderByComparator,
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

		List<ImtdSalesBasisDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdSalesBasisDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDSALESBASISDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDSALESBASISDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdSalesBasisDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdSalesBasisDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdSalesBasisDetails>)QueryUtil.list(q,
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
	 * Removes all the imtd sales basis detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdSalesBasisDetails imtdSalesBasisDetails : findAll()) {
			remove(imtdSalesBasisDetails);
		}
	}

	/**
	 * Returns the number of imtd sales basis detailses.
	 *
	 * @return the number of imtd sales basis detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDSALESBASISDETAILS);

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
		return ImtdSalesBasisDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd sales basis details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdSalesBasisDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDSALESBASISDETAILS = "SELECT imtdSalesBasisDetails FROM ImtdSalesBasisDetails imtdSalesBasisDetails";
	private static final String _SQL_SELECT_IMTDSALESBASISDETAILS_WHERE_PKS_IN = "SELECT imtdSalesBasisDetails FROM ImtdSalesBasisDetails imtdSalesBasisDetails WHERE IMTD_SALES_BASIS_DETAILS_SID IN (";
	private static final String _SQL_COUNT_IMTDSALESBASISDETAILS = "SELECT COUNT(imtdSalesBasisDetails) FROM ImtdSalesBasisDetails imtdSalesBasisDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdSalesBasisDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdSalesBasisDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdSalesBasisDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "netSalesFormulaMasterSid", "usersSid",
				"modifiedBy", "createdDate", "contractMasterSid", "cfpNo",
				"imtdCreatedDate", "contractNo", "contractName",
				"salesBasisDetailsSid", "checkRecord", "modifiedDate",
				"customerName", "operation", "customerNo",
				"imtdSalesBasisDetailsSid", "cfpName", "cdrModelSid",
				"sessionId", "cfpContractDetailsSid", "inboundStatus"
			});
}