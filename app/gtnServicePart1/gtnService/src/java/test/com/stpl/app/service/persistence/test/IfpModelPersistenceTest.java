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

import com.stpl.app.exception.NoSuchIfpModelException;
import com.stpl.app.model.IfpModel;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.persistence.IfpModelPersistence;
import com.stpl.app.service.persistence.IfpModelUtil;

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
public class IfpModelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IfpModelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IfpModel> iterator = _ifpModels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpModel ifpModel = _persistence.create(pk);

		Assert.assertNotNull(ifpModel);

		Assert.assertEquals(ifpModel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IfpModel newIfpModel = addIfpModel();

		_persistence.remove(newIfpModel);

		IfpModel existingIfpModel = _persistence.fetchByPrimaryKey(newIfpModel.getPrimaryKey());

		Assert.assertNull(existingIfpModel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIfpModel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpModel newIfpModel = _persistence.create(pk);

		newIfpModel.setModifiedBy(RandomTestUtil.nextInt());

		newIfpModel.setTotalDollarCommitment(RandomTestUtil.randomString());

		newIfpModel.setCreatedDate(RandomTestUtil.nextDate());

		newIfpModel.setIfpStatus(RandomTestUtil.nextInt());

		newIfpModel.setTotalVolumeCommitment(RandomTestUtil.randomString());

		newIfpModel.setBatchId(RandomTestUtil.randomString());

		newIfpModel.setInternalNotes(RandomTestUtil.randomString());

		newIfpModel.setIfpId(RandomTestUtil.randomString());

		newIfpModel.setTotalMarketshareCommitment(RandomTestUtil.randomString());

		newIfpModel.setIfpCategory(RandomTestUtil.nextInt());

		newIfpModel.setParentIfpName(RandomTestUtil.randomString());

		newIfpModel.setIfpEndDate(RandomTestUtil.nextDate());

		newIfpModel.setIfpDesignation(RandomTestUtil.randomString());

		newIfpModel.setCreatedBy(RandomTestUtil.nextInt());

		newIfpModel.setIfpStartDate(RandomTestUtil.nextDate());

		newIfpModel.setParentIfpId(RandomTestUtil.randomString());

		newIfpModel.setCommitmentPeriod(RandomTestUtil.randomString());

		newIfpModel.setIfpType(RandomTestUtil.nextInt());

		newIfpModel.setModifiedDate(RandomTestUtil.nextDate());

		newIfpModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newIfpModel.setSource(RandomTestUtil.randomString());

		newIfpModel.setIfpName(RandomTestUtil.randomString());

		newIfpModel.setIfpNo(RandomTestUtil.randomString());

		newIfpModel.setInboundStatus(RandomTestUtil.randomString());

		_ifpModels.add(_persistence.update(newIfpModel));

		IfpModel existingIfpModel = _persistence.findByPrimaryKey(newIfpModel.getPrimaryKey());

		Assert.assertEquals(existingIfpModel.getModifiedBy(),
			newIfpModel.getModifiedBy());
		Assert.assertEquals(existingIfpModel.getTotalDollarCommitment(),
			newIfpModel.getTotalDollarCommitment());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpModel.getCreatedDate()),
			Time.getShortTimestamp(newIfpModel.getCreatedDate()));
		Assert.assertEquals(existingIfpModel.getIfpStatus(),
			newIfpModel.getIfpStatus());
		Assert.assertEquals(existingIfpModel.getTotalVolumeCommitment(),
			newIfpModel.getTotalVolumeCommitment());
		Assert.assertEquals(existingIfpModel.getBatchId(),
			newIfpModel.getBatchId());
		Assert.assertEquals(existingIfpModel.getInternalNotes(),
			newIfpModel.getInternalNotes());
		Assert.assertEquals(existingIfpModel.getIfpId(), newIfpModel.getIfpId());
		Assert.assertEquals(existingIfpModel.getTotalMarketshareCommitment(),
			newIfpModel.getTotalMarketshareCommitment());
		Assert.assertEquals(existingIfpModel.getIfpCategory(),
			newIfpModel.getIfpCategory());
		Assert.assertEquals(existingIfpModel.getParentIfpName(),
			newIfpModel.getParentIfpName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpModel.getIfpEndDate()),
			Time.getShortTimestamp(newIfpModel.getIfpEndDate()));
		Assert.assertEquals(existingIfpModel.getIfpDesignation(),
			newIfpModel.getIfpDesignation());
		Assert.assertEquals(existingIfpModel.getCreatedBy(),
			newIfpModel.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpModel.getIfpStartDate()),
			Time.getShortTimestamp(newIfpModel.getIfpStartDate()));
		Assert.assertEquals(existingIfpModel.getParentIfpId(),
			newIfpModel.getParentIfpId());
		Assert.assertEquals(existingIfpModel.getCommitmentPeriod(),
			newIfpModel.getCommitmentPeriod());
		Assert.assertEquals(existingIfpModel.getIfpType(),
			newIfpModel.getIfpType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpModel.getModifiedDate()),
			Time.getShortTimestamp(newIfpModel.getModifiedDate()));
		Assert.assertEquals(existingIfpModel.getIfpModelSid(),
			newIfpModel.getIfpModelSid());
		Assert.assertEquals(existingIfpModel.getRecordLockStatus(),
			newIfpModel.getRecordLockStatus());
		Assert.assertEquals(existingIfpModel.getSource(),
			newIfpModel.getSource());
		Assert.assertEquals(existingIfpModel.getIfpName(),
			newIfpModel.getIfpName());
		Assert.assertEquals(existingIfpModel.getIfpNo(), newIfpModel.getIfpNo());
		Assert.assertEquals(existingIfpModel.getInboundStatus(),
			newIfpModel.getInboundStatus());
	}

	@Test
	public void testCountByItemFamilyPlanId() throws Exception {
		_persistence.countByItemFamilyPlanId(StringPool.BLANK);

		_persistence.countByItemFamilyPlanId(StringPool.NULL);

		_persistence.countByItemFamilyPlanId((String)null);
	}

	@Test
	public void testCountByItemFamilyPlanNo() throws Exception {
		_persistence.countByItemFamilyPlanNo(StringPool.BLANK);

		_persistence.countByItemFamilyPlanNo(StringPool.NULL);

		_persistence.countByItemFamilyPlanNo((String)null);
	}

	@Test
	public void testCountByItemFamilyPlanName() throws Exception {
		_persistence.countByItemFamilyPlanName(StringPool.BLANK);

		_persistence.countByItemFamilyPlanName(StringPool.NULL);

		_persistence.countByItemFamilyPlanName((String)null);
	}

	@Test
	public void testCountByItemFamilyPlanType() throws Exception {
		_persistence.countByItemFamilyPlanType(RandomTestUtil.nextInt());

		_persistence.countByItemFamilyPlanType(0);
	}

	@Test
	public void testCountByItemFamilyPlanStatus() throws Exception {
		_persistence.countByItemFamilyPlanStatus(RandomTestUtil.nextInt());

		_persistence.countByItemFamilyPlanStatus(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IfpModel newIfpModel = addIfpModel();

		IfpModel existingIfpModel = _persistence.findByPrimaryKey(newIfpModel.getPrimaryKey());

		Assert.assertEquals(existingIfpModel, newIfpModel);
	}

	@Test(expected = NoSuchIfpModelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IfpModel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IFP_MODEL", "modifiedBy",
			true, "totalDollarCommitment", true, "createdDate", true,
			"ifpStatus", true, "totalVolumeCommitment", true, "batchId", true,
			"internalNotes", true, "ifpId", true, "totalMarketshareCommitment",
			true, "ifpCategory", true, "parentIfpName", true, "ifpEndDate",
			true, "ifpDesignation", true, "createdBy", true, "ifpStartDate",
			true, "parentIfpId", true, "commitmentPeriod", true, "ifpType",
			true, "modifiedDate", true, "ifpModelSid", true,
			"recordLockStatus", true, "source", true, "ifpName", true, "ifpNo",
			true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IfpModel newIfpModel = addIfpModel();

		IfpModel existingIfpModel = _persistence.fetchByPrimaryKey(newIfpModel.getPrimaryKey());

		Assert.assertEquals(existingIfpModel, newIfpModel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpModel missingIfpModel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIfpModel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IfpModel newIfpModel1 = addIfpModel();
		IfpModel newIfpModel2 = addIfpModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpModel1.getPrimaryKey());
		primaryKeys.add(newIfpModel2.getPrimaryKey());

		Map<Serializable, IfpModel> ifpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ifpModels.size());
		Assert.assertEquals(newIfpModel1,
			ifpModels.get(newIfpModel1.getPrimaryKey()));
		Assert.assertEquals(newIfpModel2,
			ifpModels.get(newIfpModel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IfpModel> ifpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IfpModel newIfpModel = addIfpModel();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpModel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IfpModel> ifpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpModels.size());
		Assert.assertEquals(newIfpModel,
			ifpModels.get(newIfpModel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IfpModel> ifpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IfpModel newIfpModel = addIfpModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpModel.getPrimaryKey());

		Map<Serializable, IfpModel> ifpModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpModels.size());
		Assert.assertEquals(newIfpModel,
			ifpModels.get(newIfpModel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IfpModelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IfpModel>() {
				@Override
				public void performAction(IfpModel ifpModel) {
					Assert.assertNotNull(ifpModel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IfpModel newIfpModel = addIfpModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpModelSid",
				newIfpModel.getIfpModelSid()));

		List<IfpModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IfpModel existingIfpModel = result.get(0);

		Assert.assertEquals(existingIfpModel, newIfpModel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpModelSid",
				RandomTestUtil.nextInt()));

		List<IfpModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IfpModel newIfpModel = addIfpModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ifpModelSid"));

		Object newIfpModelSid = newIfpModel.getIfpModelSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpModelSid",
				new Object[] { newIfpModelSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIfpModelSid = result.get(0);

		Assert.assertEquals(existingIfpModelSid, newIfpModelSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("ifpModelSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpModelSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IfpModel addIfpModel() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpModel ifpModel = _persistence.create(pk);

		ifpModel.setModifiedBy(RandomTestUtil.nextInt());

		ifpModel.setTotalDollarCommitment(RandomTestUtil.randomString());

		ifpModel.setCreatedDate(RandomTestUtil.nextDate());

		ifpModel.setIfpStatus(RandomTestUtil.nextInt());

		ifpModel.setTotalVolumeCommitment(RandomTestUtil.randomString());

		ifpModel.setBatchId(RandomTestUtil.randomString());

		ifpModel.setInternalNotes(RandomTestUtil.randomString());

		ifpModel.setIfpId(RandomTestUtil.randomString());

		ifpModel.setTotalMarketshareCommitment(RandomTestUtil.randomString());

		ifpModel.setIfpCategory(RandomTestUtil.nextInt());

		ifpModel.setParentIfpName(RandomTestUtil.randomString());

		ifpModel.setIfpEndDate(RandomTestUtil.nextDate());

		ifpModel.setIfpDesignation(RandomTestUtil.randomString());

		ifpModel.setCreatedBy(RandomTestUtil.nextInt());

		ifpModel.setIfpStartDate(RandomTestUtil.nextDate());

		ifpModel.setParentIfpId(RandomTestUtil.randomString());

		ifpModel.setCommitmentPeriod(RandomTestUtil.randomString());

		ifpModel.setIfpType(RandomTestUtil.nextInt());

		ifpModel.setModifiedDate(RandomTestUtil.nextDate());

		ifpModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		ifpModel.setSource(RandomTestUtil.randomString());

		ifpModel.setIfpName(RandomTestUtil.randomString());

		ifpModel.setIfpNo(RandomTestUtil.randomString());

		ifpModel.setInboundStatus(RandomTestUtil.randomString());

		_ifpModels.add(_persistence.update(ifpModel));

		return ifpModel;
	}

	private List<IfpModel> _ifpModels = new ArrayList<IfpModel>();
	private IfpModelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}