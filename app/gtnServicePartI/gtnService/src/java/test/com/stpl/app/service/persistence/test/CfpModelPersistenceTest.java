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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchCfpModelException;
import com.stpl.app.model.CfpModel;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.persistence.CfpModelPersistence;
import com.stpl.app.service.persistence.CfpModelUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CfpModelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CfpModelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CfpModel> iterator = _cfpModels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpModel cfpModel = _persistence.create(pk);

		Assert.assertNotNull(cfpModel);

		Assert.assertEquals(cfpModel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CfpModel newCfpModel = addCfpModel();

		_persistence.remove(newCfpModel);

		CfpModel existingCfpModel = _persistence.fetchByPrimaryKey(newCfpModel.getPrimaryKey());

		Assert.assertNull(existingCfpModel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCfpModel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpModel newCfpModel = _persistence.create(pk);

		newCfpModel.setCreatedBy(RandomTestUtil.nextInt());

		newCfpModel.setCfpType(RandomTestUtil.nextInt());

		newCfpModel.setCfpTradeClass(RandomTestUtil.nextInt());

		newCfpModel.setModifiedBy(RandomTestUtil.nextInt());

		newCfpModel.setCreatedDate(RandomTestUtil.nextDate());

		newCfpModel.setCfpNo(RandomTestUtil.randomString());

		newCfpModel.setBatchId(RandomTestUtil.randomString());

		newCfpModel.setModifiedDate(RandomTestUtil.nextDate());

		newCfpModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCfpModel.setInternalNotes(RandomTestUtil.randomString());

		newCfpModel.setCfpDesignation(RandomTestUtil.randomString());

		newCfpModel.setSalesInclusion(RandomTestUtil.nextInt());

		newCfpModel.setCfpName(RandomTestUtil.randomString());

		newCfpModel.setCfpCategory(RandomTestUtil.nextInt());

		newCfpModel.setSource(RandomTestUtil.randomString());

		newCfpModel.setCfpId(RandomTestUtil.randomString());

		newCfpModel.setCfpStatus(RandomTestUtil.nextInt());

		newCfpModel.setParentCfpId(RandomTestUtil.nextInt());

		newCfpModel.setCfpStartDate(RandomTestUtil.nextDate());

		newCfpModel.setCfpEndDate(RandomTestUtil.nextDate());

		newCfpModel.setParentCfpName(RandomTestUtil.randomString());

		newCfpModel.setInboundStatus(RandomTestUtil.randomString());

		_cfpModels.add(_persistence.update(newCfpModel));

		CfpModel existingCfpModel = _persistence.findByPrimaryKey(newCfpModel.getPrimaryKey());

		Assert.assertEquals(existingCfpModel.getCreatedBy(),
			newCfpModel.getCreatedBy());
		Assert.assertEquals(existingCfpModel.getCfpType(),
			newCfpModel.getCfpType());
		Assert.assertEquals(existingCfpModel.getCfpTradeClass(),
			newCfpModel.getCfpTradeClass());
		Assert.assertEquals(existingCfpModel.getModifiedBy(),
			newCfpModel.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpModel.getCreatedDate()),
			Time.getShortTimestamp(newCfpModel.getCreatedDate()));
		Assert.assertEquals(existingCfpModel.getCfpNo(), newCfpModel.getCfpNo());
		Assert.assertEquals(existingCfpModel.getCfpModelSid(),
			newCfpModel.getCfpModelSid());
		Assert.assertEquals(existingCfpModel.getBatchId(),
			newCfpModel.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpModel.getModifiedDate()),
			Time.getShortTimestamp(newCfpModel.getModifiedDate()));
		Assert.assertEquals(existingCfpModel.getRecordLockStatus(),
			newCfpModel.getRecordLockStatus());
		Assert.assertEquals(existingCfpModel.getInternalNotes(),
			newCfpModel.getInternalNotes());
		Assert.assertEquals(existingCfpModel.getCfpDesignation(),
			newCfpModel.getCfpDesignation());
		Assert.assertEquals(existingCfpModel.getSalesInclusion(),
			newCfpModel.getSalesInclusion());
		Assert.assertEquals(existingCfpModel.getCfpName(),
			newCfpModel.getCfpName());
		Assert.assertEquals(existingCfpModel.getCfpCategory(),
			newCfpModel.getCfpCategory());
		Assert.assertEquals(existingCfpModel.getSource(),
			newCfpModel.getSource());
		Assert.assertEquals(existingCfpModel.getCfpId(), newCfpModel.getCfpId());
		Assert.assertEquals(existingCfpModel.getCfpStatus(),
			newCfpModel.getCfpStatus());
		Assert.assertEquals(existingCfpModel.getParentCfpId(),
			newCfpModel.getParentCfpId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpModel.getCfpStartDate()),
			Time.getShortTimestamp(newCfpModel.getCfpStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpModel.getCfpEndDate()),
			Time.getShortTimestamp(newCfpModel.getCfpEndDate()));
		Assert.assertEquals(existingCfpModel.getParentCfpName(),
			newCfpModel.getParentCfpName());
		Assert.assertEquals(existingCfpModel.getInboundStatus(),
			newCfpModel.getInboundStatus());
	}

	@Test
	public void testCountByCfpId() throws Exception {
		_persistence.countByCfpId(StringPool.BLANK);

		_persistence.countByCfpId(StringPool.NULL);

		_persistence.countByCfpId((String)null);
	}

	@Test
	public void testCountByCfpNo() throws Exception {
		_persistence.countByCfpNo(StringPool.BLANK);

		_persistence.countByCfpNo(StringPool.NULL);

		_persistence.countByCfpNo((String)null);
	}

	@Test
	public void testCountByCfpName() throws Exception {
		_persistence.countByCfpName(StringPool.BLANK);

		_persistence.countByCfpName(StringPool.NULL);

		_persistence.countByCfpName((String)null);
	}

	@Test
	public void testCountByCfpType() throws Exception {
		_persistence.countByCfpType(RandomTestUtil.nextInt());

		_persistence.countByCfpType(0);
	}

	@Test
	public void testCountByCfpStatus() throws Exception {
		_persistence.countByCfpStatus(RandomTestUtil.nextInt());

		_persistence.countByCfpStatus(0);
	}

	@Test
	public void testCountByCfpTradeClass() throws Exception {
		_persistence.countByCfpTradeClass(RandomTestUtil.nextInt());

		_persistence.countByCfpTradeClass(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CfpModel newCfpModel = addCfpModel();

		CfpModel existingCfpModel = _persistence.findByPrimaryKey(newCfpModel.getPrimaryKey());

		Assert.assertEquals(existingCfpModel, newCfpModel);
	}

	@Test(expected = NoSuchCfpModelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CfpModel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFP_MODEL", "createdBy",
			true, "cfpType", true, "cfpTradeClass", true, "modifiedBy", true,
			"createdDate", true, "cfpNo", true, "cfpModelSid", true, "batchId",
			true, "modifiedDate", true, "recordLockStatus", true,
			"internalNotes", true, "cfpDesignation", true, "salesInclusion",
			true, "cfpName", true, "cfpCategory", true, "source", true,
			"cfpId", true, "cfpStatus", true, "parentCfpId", true,
			"cfpStartDate", true, "cfpEndDate", true, "parentCfpName", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CfpModel newCfpModel = addCfpModel();

		CfpModel existingCfpModel = _persistence.fetchByPrimaryKey(newCfpModel.getPrimaryKey());

		Assert.assertEquals(existingCfpModel, newCfpModel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpModel missingCfpModel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCfpModel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CfpModel newCfpModel1 = addCfpModel();
		CfpModel newCfpModel2 = addCfpModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpModel1.getPrimaryKey());
		primaryKeys.add(newCfpModel2.getPrimaryKey());

		Map<Serializable, CfpModel> cfpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cfpModels.size());
		Assert.assertEquals(newCfpModel1,
			cfpModels.get(newCfpModel1.getPrimaryKey()));
		Assert.assertEquals(newCfpModel2,
			cfpModels.get(newCfpModel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CfpModel> cfpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CfpModel newCfpModel = addCfpModel();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpModel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CfpModel> cfpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpModels.size());
		Assert.assertEquals(newCfpModel,
			cfpModels.get(newCfpModel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CfpModel> cfpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CfpModel newCfpModel = addCfpModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpModel.getPrimaryKey());

		Map<Serializable, CfpModel> cfpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpModels.size());
		Assert.assertEquals(newCfpModel,
			cfpModels.get(newCfpModel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CfpModelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CfpModel>() {
				@Override
				public void performAction(CfpModel cfpModel) {
					Assert.assertNotNull(cfpModel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CfpModel newCfpModel = addCfpModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpModelSid",
				newCfpModel.getCfpModelSid()));

		List<CfpModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CfpModel existingCfpModel = result.get(0);

		Assert.assertEquals(existingCfpModel, newCfpModel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpModelSid",
				RandomTestUtil.nextInt()));

		List<CfpModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CfpModel newCfpModel = addCfpModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("cfpModelSid"));

		Object newCfpModelSid = newCfpModel.getCfpModelSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpModelSid",
				new Object[] { newCfpModelSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCfpModelSid = result.get(0);

		Assert.assertEquals(existingCfpModelSid, newCfpModelSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("cfpModelSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpModelSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CfpModel addCfpModel() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpModel cfpModel = _persistence.create(pk);

		cfpModel.setCreatedBy(RandomTestUtil.nextInt());

		cfpModel.setCfpType(RandomTestUtil.nextInt());

		cfpModel.setCfpTradeClass(RandomTestUtil.nextInt());

		cfpModel.setModifiedBy(RandomTestUtil.nextInt());

		cfpModel.setCreatedDate(RandomTestUtil.nextDate());

		cfpModel.setCfpNo(RandomTestUtil.randomString());

		cfpModel.setBatchId(RandomTestUtil.randomString());

		cfpModel.setModifiedDate(RandomTestUtil.nextDate());

		cfpModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		cfpModel.setInternalNotes(RandomTestUtil.randomString());

		cfpModel.setCfpDesignation(RandomTestUtil.randomString());

		cfpModel.setSalesInclusion(RandomTestUtil.nextInt());

		cfpModel.setCfpName(RandomTestUtil.randomString());

		cfpModel.setCfpCategory(RandomTestUtil.nextInt());

		cfpModel.setSource(RandomTestUtil.randomString());

		cfpModel.setCfpId(RandomTestUtil.randomString());

		cfpModel.setCfpStatus(RandomTestUtil.nextInt());

		cfpModel.setParentCfpId(RandomTestUtil.nextInt());

		cfpModel.setCfpStartDate(RandomTestUtil.nextDate());

		cfpModel.setCfpEndDate(RandomTestUtil.nextDate());

		cfpModel.setParentCfpName(RandomTestUtil.randomString());

		cfpModel.setInboundStatus(RandomTestUtil.randomString());

		_cfpModels.add(_persistence.update(cfpModel));

		return cfpModel;
	}

	private List<CfpModel> _cfpModels = new ArrayList<CfpModel>();
	private CfpModelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}