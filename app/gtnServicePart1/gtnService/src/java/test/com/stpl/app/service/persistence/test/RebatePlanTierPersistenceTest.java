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
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchRebatePlanTierException;
import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;
import com.stpl.app.service.persistence.RebatePlanTierPersistence;
import com.stpl.app.service.persistence.RebatePlanTierUtil;

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
public class RebatePlanTierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RebatePlanTierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RebatePlanTier> iterator = _rebatePlanTiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanTier rebatePlanTier = _persistence.create(pk);

		Assert.assertNotNull(rebatePlanTier);

		Assert.assertEquals(rebatePlanTier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		_persistence.remove(newRebatePlanTier);

		RebatePlanTier existingRebatePlanTier = _persistence.fetchByPrimaryKey(newRebatePlanTier.getPrimaryKey());

		Assert.assertNull(existingRebatePlanTier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRebatePlanTier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanTier newRebatePlanTier = _persistence.create(pk);

		newRebatePlanTier.setTierValue(RandomTestUtil.nextDouble());

		newRebatePlanTier.setReturnRateSid(RandomTestUtil.randomString());

		newRebatePlanTier.setRebatePlanMasterSid(RandomTestUtil.nextInt());

		newRebatePlanTier.setItemPricingQualifierSid(RandomTestUtil.randomString());

		newRebatePlanTier.setModifiedDate(RandomTestUtil.nextDate());

		newRebatePlanTier.setTierTolerance(RandomTestUtil.nextDouble());

		newRebatePlanTier.setTierFrom(RandomTestUtil.randomString());

		newRebatePlanTier.setTierOperator(RandomTestUtil.randomString());

		newRebatePlanTier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRebatePlanTier.setCreatedDate(RandomTestUtil.nextDate());

		newRebatePlanTier.setCreatedBy(RandomTestUtil.nextInt());

		newRebatePlanTier.setSource(RandomTestUtil.randomString());

		newRebatePlanTier.setTierTo(RandomTestUtil.randomString());

		newRebatePlanTier.setBatchId(RandomTestUtil.randomString());

		newRebatePlanTier.setRebatePlanTierId(RandomTestUtil.randomString());

		newRebatePlanTier.setFreeAmount(RandomTestUtil.nextDouble());

		newRebatePlanTier.setModifiedBy(RandomTestUtil.nextInt());

		newRebatePlanTier.setInboundStatus(RandomTestUtil.randomString());

		newRebatePlanTier.setTierLevel(RandomTestUtil.randomString());

		newRebatePlanTier.setFormulaNo(RandomTestUtil.randomString());

		newRebatePlanTier.setFormulaName(RandomTestUtil.randomString());

		newRebatePlanTier.setSecondaryRebatePlanNo(RandomTestUtil.randomString());

		newRebatePlanTier.setSecondaryRebatePlanName(RandomTestUtil.randomString());

		newRebatePlanTier.setSecondaryRebatePlanSid(RandomTestUtil.randomString());

		_rebatePlanTiers.add(_persistence.update(newRebatePlanTier));

		RebatePlanTier existingRebatePlanTier = _persistence.findByPrimaryKey(newRebatePlanTier.getPrimaryKey());

		AssertUtils.assertEquals(existingRebatePlanTier.getTierValue(),
			newRebatePlanTier.getTierValue());
		Assert.assertEquals(existingRebatePlanTier.getReturnRateSid(),
			newRebatePlanTier.getReturnRateSid());
		Assert.assertEquals(existingRebatePlanTier.getRebatePlanMasterSid(),
			newRebatePlanTier.getRebatePlanMasterSid());
		Assert.assertEquals(existingRebatePlanTier.getRebatePlanTierSid(),
			newRebatePlanTier.getRebatePlanTierSid());
		Assert.assertEquals(existingRebatePlanTier.getItemPricingQualifierSid(),
			newRebatePlanTier.getItemPricingQualifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanTier.getModifiedDate()),
			Time.getShortTimestamp(newRebatePlanTier.getModifiedDate()));
		AssertUtils.assertEquals(existingRebatePlanTier.getTierTolerance(),
			newRebatePlanTier.getTierTolerance());
		Assert.assertEquals(existingRebatePlanTier.getTierFrom(),
			newRebatePlanTier.getTierFrom());
		Assert.assertEquals(existingRebatePlanTier.getTierOperator(),
			newRebatePlanTier.getTierOperator());
		Assert.assertEquals(existingRebatePlanTier.getRecordLockStatus(),
			newRebatePlanTier.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanTier.getCreatedDate()),
			Time.getShortTimestamp(newRebatePlanTier.getCreatedDate()));
		Assert.assertEquals(existingRebatePlanTier.getCreatedBy(),
			newRebatePlanTier.getCreatedBy());
		Assert.assertEquals(existingRebatePlanTier.getSource(),
			newRebatePlanTier.getSource());
		Assert.assertEquals(existingRebatePlanTier.getTierTo(),
			newRebatePlanTier.getTierTo());
		Assert.assertEquals(existingRebatePlanTier.getBatchId(),
			newRebatePlanTier.getBatchId());
		Assert.assertEquals(existingRebatePlanTier.getRebatePlanTierId(),
			newRebatePlanTier.getRebatePlanTierId());
		AssertUtils.assertEquals(existingRebatePlanTier.getFreeAmount(),
			newRebatePlanTier.getFreeAmount());
		Assert.assertEquals(existingRebatePlanTier.getModifiedBy(),
			newRebatePlanTier.getModifiedBy());
		Assert.assertEquals(existingRebatePlanTier.getInboundStatus(),
			newRebatePlanTier.getInboundStatus());
		Assert.assertEquals(existingRebatePlanTier.getTierLevel(),
			newRebatePlanTier.getTierLevel());
		Assert.assertEquals(existingRebatePlanTier.getFormulaNo(),
			newRebatePlanTier.getFormulaNo());
		Assert.assertEquals(existingRebatePlanTier.getFormulaName(),
			newRebatePlanTier.getFormulaName());
		Assert.assertEquals(existingRebatePlanTier.getSecondaryRebatePlanNo(),
			newRebatePlanTier.getSecondaryRebatePlanNo());
		Assert.assertEquals(existingRebatePlanTier.getSecondaryRebatePlanName(),
			newRebatePlanTier.getSecondaryRebatePlanName());
		Assert.assertEquals(existingRebatePlanTier.getSecondaryRebatePlanSid(),
			newRebatePlanTier.getSecondaryRebatePlanSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		RebatePlanTier existingRebatePlanTier = _persistence.findByPrimaryKey(newRebatePlanTier.getPrimaryKey());

		Assert.assertEquals(existingRebatePlanTier, newRebatePlanTier);
	}

	@Test(expected = NoSuchRebatePlanTierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RebatePlanTier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("REBATE_PLAN_TIER",
			"tierValue", true, "returnRateSid", true, "rebatePlanMasterSid",
			true, "rebatePlanTierSid", true, "itemPricingQualifierSid", true,
			"modifiedDate", true, "tierTolerance", true, "tierFrom", true,
			"tierOperator", true, "recordLockStatus", true, "createdDate",
			true, "createdBy", true, "source", true, "tierTo", true, "batchId",
			true, "rebatePlanTierId", true, "freeAmount", true, "modifiedBy",
			true, "inboundStatus", true, "tierLevel", true, "formulaNo", true,
			"formulaName", true, "secondaryRebatePlanNo", true,
			"secondaryRebatePlanName", true, "secondaryRebatePlanSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		RebatePlanTier existingRebatePlanTier = _persistence.fetchByPrimaryKey(newRebatePlanTier.getPrimaryKey());

		Assert.assertEquals(existingRebatePlanTier, newRebatePlanTier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanTier missingRebatePlanTier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRebatePlanTier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RebatePlanTier newRebatePlanTier1 = addRebatePlanTier();
		RebatePlanTier newRebatePlanTier2 = addRebatePlanTier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebatePlanTier1.getPrimaryKey());
		primaryKeys.add(newRebatePlanTier2.getPrimaryKey());

		Map<Serializable, RebatePlanTier> rebatePlanTiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rebatePlanTiers.size());
		Assert.assertEquals(newRebatePlanTier1,
			rebatePlanTiers.get(newRebatePlanTier1.getPrimaryKey()));
		Assert.assertEquals(newRebatePlanTier2,
			rebatePlanTiers.get(newRebatePlanTier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RebatePlanTier> rebatePlanTiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rebatePlanTiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebatePlanTier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RebatePlanTier> rebatePlanTiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rebatePlanTiers.size());
		Assert.assertEquals(newRebatePlanTier,
			rebatePlanTiers.get(newRebatePlanTier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RebatePlanTier> rebatePlanTiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rebatePlanTiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebatePlanTier.getPrimaryKey());

		Map<Serializable, RebatePlanTier> rebatePlanTiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rebatePlanTiers.size());
		Assert.assertEquals(newRebatePlanTier,
			rebatePlanTiers.get(newRebatePlanTier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RebatePlanTierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RebatePlanTier>() {
				@Override
				public void performAction(RebatePlanTier rebatePlanTier) {
					Assert.assertNotNull(rebatePlanTier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanTierSid",
				newRebatePlanTier.getRebatePlanTierSid()));

		List<RebatePlanTier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RebatePlanTier existingRebatePlanTier = result.get(0);

		Assert.assertEquals(existingRebatePlanTier, newRebatePlanTier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanTierSid",
				RandomTestUtil.nextInt()));

		List<RebatePlanTier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RebatePlanTier newRebatePlanTier = addRebatePlanTier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rebatePlanTierSid"));

		Object newRebatePlanTierSid = newRebatePlanTier.getRebatePlanTierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rebatePlanTierSid",
				new Object[] { newRebatePlanTierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRebatePlanTierSid = result.get(0);

		Assert.assertEquals(existingRebatePlanTierSid, newRebatePlanTierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanTier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rebatePlanTierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rebatePlanTierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RebatePlanTier addRebatePlanTier() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanTier rebatePlanTier = _persistence.create(pk);

		rebatePlanTier.setTierValue(RandomTestUtil.nextDouble());

		rebatePlanTier.setReturnRateSid(RandomTestUtil.randomString());

		rebatePlanTier.setRebatePlanMasterSid(RandomTestUtil.nextInt());

		rebatePlanTier.setItemPricingQualifierSid(RandomTestUtil.randomString());

		rebatePlanTier.setModifiedDate(RandomTestUtil.nextDate());

		rebatePlanTier.setTierTolerance(RandomTestUtil.nextDouble());

		rebatePlanTier.setTierFrom(RandomTestUtil.randomString());

		rebatePlanTier.setTierOperator(RandomTestUtil.randomString());

		rebatePlanTier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rebatePlanTier.setCreatedDate(RandomTestUtil.nextDate());

		rebatePlanTier.setCreatedBy(RandomTestUtil.nextInt());

		rebatePlanTier.setSource(RandomTestUtil.randomString());

		rebatePlanTier.setTierTo(RandomTestUtil.randomString());

		rebatePlanTier.setBatchId(RandomTestUtil.randomString());

		rebatePlanTier.setRebatePlanTierId(RandomTestUtil.randomString());

		rebatePlanTier.setFreeAmount(RandomTestUtil.nextDouble());

		rebatePlanTier.setModifiedBy(RandomTestUtil.nextInt());

		rebatePlanTier.setInboundStatus(RandomTestUtil.randomString());

		rebatePlanTier.setTierLevel(RandomTestUtil.randomString());

		rebatePlanTier.setFormulaNo(RandomTestUtil.randomString());

		rebatePlanTier.setFormulaName(RandomTestUtil.randomString());

		rebatePlanTier.setSecondaryRebatePlanNo(RandomTestUtil.randomString());

		rebatePlanTier.setSecondaryRebatePlanName(RandomTestUtil.randomString());

		rebatePlanTier.setSecondaryRebatePlanSid(RandomTestUtil.randomString());

		_rebatePlanTiers.add(_persistence.update(rebatePlanTier));

		return rebatePlanTier;
	}

	private List<RebatePlanTier> _rebatePlanTiers = new ArrayList<RebatePlanTier>();
	private RebatePlanTierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}