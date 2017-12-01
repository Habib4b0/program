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

import com.stpl.app.exception.NoSuchRebatePlanMasterException;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.persistence.RebatePlanMasterPersistence;
import com.stpl.app.service.persistence.RebatePlanMasterUtil;

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
public class RebatePlanMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RebatePlanMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RebatePlanMaster> iterator = _rebatePlanMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanMaster rebatePlanMaster = _persistence.create(pk);

		Assert.assertNotNull(rebatePlanMaster);

		Assert.assertEquals(rebatePlanMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		_persistence.remove(newRebatePlanMaster);

		RebatePlanMaster existingRebatePlanMaster = _persistence.fetchByPrimaryKey(newRebatePlanMaster.getPrimaryKey());

		Assert.assertNull(existingRebatePlanMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRebatePlanMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanMaster newRebatePlanMaster = _persistence.create(pk);

		newRebatePlanMaster.setSelfGrowthIndicator(RandomTestUtil.randomString());

		newRebatePlanMaster.setRebateStructure(RandomTestUtil.randomString());

		newRebatePlanMaster.setMarketShareFrom(RandomTestUtil.nextDate());

		newRebatePlanMaster.setSecondaryRebatePlanNo(RandomTestUtil.randomString());

		newRebatePlanMaster.setModifiedDate(RandomTestUtil.nextDate());

		newRebatePlanMaster.setRebateRangeBasedOn(RandomTestUtil.nextInt());

		newRebatePlanMaster.setCdrModelSid(RandomTestUtil.randomString());

		newRebatePlanMaster.setRebateRule(RandomTestUtil.randomString());

		newRebatePlanMaster.setCreatedDate(RandomTestUtil.nextDate());

		newRebatePlanMaster.setCreatedBy(RandomTestUtil.nextInt());

		newRebatePlanMaster.setSource(RandomTestUtil.randomString());

		newRebatePlanMaster.setRebateBasedOn(RandomTestUtil.nextInt());

		newRebatePlanMaster.setRebatePlanType(RandomTestUtil.nextInt());

		newRebatePlanMaster.setRebatePlanId(RandomTestUtil.randomString());

		newRebatePlanMaster.setManfCompanyMasterSid(RandomTestUtil.nextInt());

		newRebatePlanMaster.setModifiedBy(RandomTestUtil.nextInt());

		newRebatePlanMaster.setInboundStatus(RandomTestUtil.randomString());

		newRebatePlanMaster.setSecondaryRebatePlanId(RandomTestUtil.randomString());

		newRebatePlanMaster.setMarketShareIndicator(RandomTestUtil.randomString());

		newRebatePlanMaster.setBogoEligible(RandomTestUtil.randomString());

		newRebatePlanMaster.setMarketShareTo(RandomTestUtil.nextDate());

		newRebatePlanMaster.setRebatePlanStatus(RandomTestUtil.nextInt());

		newRebatePlanMaster.setMarketShareReference(RandomTestUtil.randomString());

		newRebatePlanMaster.setNetSalesFormulaMasterSid(RandomTestUtil.randomString());

		newRebatePlanMaster.setSelfGrowthFrom(RandomTestUtil.nextDate());

		newRebatePlanMaster.setInternalNotes(RandomTestUtil.randomString());

		newRebatePlanMaster.setSecondaryRebatePlanName(RandomTestUtil.randomString());

		newRebatePlanMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRebatePlanMaster.setRebatePlanName(RandomTestUtil.randomString());

		newRebatePlanMaster.setSelfGrowthReference(RandomTestUtil.randomString());

		newRebatePlanMaster.setBatchId(RandomTestUtil.randomString());

		newRebatePlanMaster.setFormulaType(RandomTestUtil.nextInt());

		newRebatePlanMaster.setSelfGrowthTo(RandomTestUtil.nextDate());

		newRebatePlanMaster.setRebatePlanNo(RandomTestUtil.randomString());

		_rebatePlanMasters.add(_persistence.update(newRebatePlanMaster));

		RebatePlanMaster existingRebatePlanMaster = _persistence.findByPrimaryKey(newRebatePlanMaster.getPrimaryKey());

		Assert.assertEquals(existingRebatePlanMaster.getSelfGrowthIndicator(),
			newRebatePlanMaster.getSelfGrowthIndicator());
		Assert.assertEquals(existingRebatePlanMaster.getRebateStructure(),
			newRebatePlanMaster.getRebateStructure());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanMaster.getMarketShareFrom()),
			Time.getShortTimestamp(newRebatePlanMaster.getMarketShareFrom()));
		Assert.assertEquals(existingRebatePlanMaster.getSecondaryRebatePlanNo(),
			newRebatePlanMaster.getSecondaryRebatePlanNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanMaster.getModifiedDate()),
			Time.getShortTimestamp(newRebatePlanMaster.getModifiedDate()));
		Assert.assertEquals(existingRebatePlanMaster.getRebateRangeBasedOn(),
			newRebatePlanMaster.getRebateRangeBasedOn());
		Assert.assertEquals(existingRebatePlanMaster.getCdrModelSid(),
			newRebatePlanMaster.getCdrModelSid());
		Assert.assertEquals(existingRebatePlanMaster.getRebateRule(),
			newRebatePlanMaster.getRebateRule());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanMaster.getCreatedDate()),
			Time.getShortTimestamp(newRebatePlanMaster.getCreatedDate()));
		Assert.assertEquals(existingRebatePlanMaster.getCreatedBy(),
			newRebatePlanMaster.getCreatedBy());
		Assert.assertEquals(existingRebatePlanMaster.getSource(),
			newRebatePlanMaster.getSource());
		Assert.assertEquals(existingRebatePlanMaster.getRebateBasedOn(),
			newRebatePlanMaster.getRebateBasedOn());
		Assert.assertEquals(existingRebatePlanMaster.getRebatePlanType(),
			newRebatePlanMaster.getRebatePlanType());
		Assert.assertEquals(existingRebatePlanMaster.getRebatePlanId(),
			newRebatePlanMaster.getRebatePlanId());
		Assert.assertEquals(existingRebatePlanMaster.getManfCompanyMasterSid(),
			newRebatePlanMaster.getManfCompanyMasterSid());
		Assert.assertEquals(existingRebatePlanMaster.getModifiedBy(),
			newRebatePlanMaster.getModifiedBy());
		Assert.assertEquals(existingRebatePlanMaster.getInboundStatus(),
			newRebatePlanMaster.getInboundStatus());
		Assert.assertEquals(existingRebatePlanMaster.getSecondaryRebatePlanId(),
			newRebatePlanMaster.getSecondaryRebatePlanId());
		Assert.assertEquals(existingRebatePlanMaster.getMarketShareIndicator(),
			newRebatePlanMaster.getMarketShareIndicator());
		Assert.assertEquals(existingRebatePlanMaster.getBogoEligible(),
			newRebatePlanMaster.getBogoEligible());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanMaster.getMarketShareTo()),
			Time.getShortTimestamp(newRebatePlanMaster.getMarketShareTo()));
		Assert.assertEquals(existingRebatePlanMaster.getRebatePlanStatus(),
			newRebatePlanMaster.getRebatePlanStatus());
		Assert.assertEquals(existingRebatePlanMaster.getRebatePlanMasterSid(),
			newRebatePlanMaster.getRebatePlanMasterSid());
		Assert.assertEquals(existingRebatePlanMaster.getMarketShareReference(),
			newRebatePlanMaster.getMarketShareReference());
		Assert.assertEquals(existingRebatePlanMaster.getNetSalesFormulaMasterSid(),
			newRebatePlanMaster.getNetSalesFormulaMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanMaster.getSelfGrowthFrom()),
			Time.getShortTimestamp(newRebatePlanMaster.getSelfGrowthFrom()));
		Assert.assertEquals(existingRebatePlanMaster.getInternalNotes(),
			newRebatePlanMaster.getInternalNotes());
		Assert.assertEquals(existingRebatePlanMaster.getSecondaryRebatePlanName(),
			newRebatePlanMaster.getSecondaryRebatePlanName());
		Assert.assertEquals(existingRebatePlanMaster.getRecordLockStatus(),
			newRebatePlanMaster.getRecordLockStatus());
		Assert.assertEquals(existingRebatePlanMaster.getRebatePlanName(),
			newRebatePlanMaster.getRebatePlanName());
		Assert.assertEquals(existingRebatePlanMaster.getSelfGrowthReference(),
			newRebatePlanMaster.getSelfGrowthReference());
		Assert.assertEquals(existingRebatePlanMaster.getBatchId(),
			newRebatePlanMaster.getBatchId());
		Assert.assertEquals(existingRebatePlanMaster.getFormulaType(),
			newRebatePlanMaster.getFormulaType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRebatePlanMaster.getSelfGrowthTo()),
			Time.getShortTimestamp(newRebatePlanMaster.getSelfGrowthTo()));
		Assert.assertEquals(existingRebatePlanMaster.getRebatePlanNo(),
			newRebatePlanMaster.getRebatePlanNo());
	}

	@Test
	public void testCountByRebatePlanId() throws Exception {
		_persistence.countByRebatePlanId(StringPool.BLANK);

		_persistence.countByRebatePlanId(StringPool.NULL);

		_persistence.countByRebatePlanId((String)null);
	}

	@Test
	public void testCountByRebatePlanNo() throws Exception {
		_persistence.countByRebatePlanNo(StringPool.BLANK);

		_persistence.countByRebatePlanNo(StringPool.NULL);

		_persistence.countByRebatePlanNo((String)null);
	}

	@Test
	public void testCountByRebatePlanName() throws Exception {
		_persistence.countByRebatePlanName(StringPool.BLANK);

		_persistence.countByRebatePlanName(StringPool.NULL);

		_persistence.countByRebatePlanName((String)null);
	}

	@Test
	public void testCountByRebatePlanStatus() throws Exception {
		_persistence.countByRebatePlanStatus(RandomTestUtil.nextInt());

		_persistence.countByRebatePlanStatus(0);
	}

	@Test
	public void testCountByRebatePlanType() throws Exception {
		_persistence.countByRebatePlanType(RandomTestUtil.nextInt());

		_persistence.countByRebatePlanType(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		RebatePlanMaster existingRebatePlanMaster = _persistence.findByPrimaryKey(newRebatePlanMaster.getPrimaryKey());

		Assert.assertEquals(existingRebatePlanMaster, newRebatePlanMaster);
	}

	@Test(expected = NoSuchRebatePlanMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RebatePlanMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("REBATE_PLAN_MASTER",
			"selfGrowthIndicator", true, "rebateStructure", true,
			"marketShareFrom", true, "secondaryRebatePlanNo", true,
			"modifiedDate", true, "rebateRangeBasedOn", true, "cdrModelSid",
			true, "rebateRule", true, "createdDate", true, "createdBy", true,
			"source", true, "rebateBasedOn", true, "rebatePlanType", true,
			"rebatePlanId", true, "manfCompanyMasterSid", true, "modifiedBy",
			true, "inboundStatus", true, "secondaryRebatePlanId", true,
			"marketShareIndicator", true, "bogoEligible", true,
			"marketShareTo", true, "rebatePlanStatus", true,
			"rebatePlanMasterSid", true, "marketShareReference", true,
			"netSalesFormulaMasterSid", true, "selfGrowthFrom", true,
			"internalNotes", true, "secondaryRebatePlanName", true,
			"recordLockStatus", true, "rebatePlanName", true,
			"selfGrowthReference", true, "batchId", true, "formulaType", true,
			"selfGrowthTo", true, "rebatePlanNo", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		RebatePlanMaster existingRebatePlanMaster = _persistence.fetchByPrimaryKey(newRebatePlanMaster.getPrimaryKey());

		Assert.assertEquals(existingRebatePlanMaster, newRebatePlanMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanMaster missingRebatePlanMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRebatePlanMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RebatePlanMaster newRebatePlanMaster1 = addRebatePlanMaster();
		RebatePlanMaster newRebatePlanMaster2 = addRebatePlanMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebatePlanMaster1.getPrimaryKey());
		primaryKeys.add(newRebatePlanMaster2.getPrimaryKey());

		Map<Serializable, RebatePlanMaster> rebatePlanMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rebatePlanMasters.size());
		Assert.assertEquals(newRebatePlanMaster1,
			rebatePlanMasters.get(newRebatePlanMaster1.getPrimaryKey()));
		Assert.assertEquals(newRebatePlanMaster2,
			rebatePlanMasters.get(newRebatePlanMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RebatePlanMaster> rebatePlanMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rebatePlanMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebatePlanMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RebatePlanMaster> rebatePlanMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rebatePlanMasters.size());
		Assert.assertEquals(newRebatePlanMaster,
			rebatePlanMasters.get(newRebatePlanMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RebatePlanMaster> rebatePlanMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rebatePlanMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRebatePlanMaster.getPrimaryKey());

		Map<Serializable, RebatePlanMaster> rebatePlanMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rebatePlanMasters.size());
		Assert.assertEquals(newRebatePlanMaster,
			rebatePlanMasters.get(newRebatePlanMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RebatePlanMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RebatePlanMaster>() {
				@Override
				public void performAction(RebatePlanMaster rebatePlanMaster) {
					Assert.assertNotNull(rebatePlanMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanMasterSid",
				newRebatePlanMaster.getRebatePlanMasterSid()));

		List<RebatePlanMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RebatePlanMaster existingRebatePlanMaster = result.get(0);

		Assert.assertEquals(existingRebatePlanMaster, newRebatePlanMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rebatePlanMasterSid",
				RandomTestUtil.nextInt()));

		List<RebatePlanMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RebatePlanMaster newRebatePlanMaster = addRebatePlanMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rebatePlanMasterSid"));

		Object newRebatePlanMasterSid = newRebatePlanMaster.getRebatePlanMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rebatePlanMasterSid",
				new Object[] { newRebatePlanMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRebatePlanMasterSid = result.get(0);

		Assert.assertEquals(existingRebatePlanMasterSid, newRebatePlanMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RebatePlanMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rebatePlanMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rebatePlanMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RebatePlanMaster addRebatePlanMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RebatePlanMaster rebatePlanMaster = _persistence.create(pk);

		rebatePlanMaster.setSelfGrowthIndicator(RandomTestUtil.randomString());

		rebatePlanMaster.setRebateStructure(RandomTestUtil.randomString());

		rebatePlanMaster.setMarketShareFrom(RandomTestUtil.nextDate());

		rebatePlanMaster.setSecondaryRebatePlanNo(RandomTestUtil.randomString());

		rebatePlanMaster.setModifiedDate(RandomTestUtil.nextDate());

		rebatePlanMaster.setRebateRangeBasedOn(RandomTestUtil.nextInt());

		rebatePlanMaster.setCdrModelSid(RandomTestUtil.randomString());

		rebatePlanMaster.setRebateRule(RandomTestUtil.randomString());

		rebatePlanMaster.setCreatedDate(RandomTestUtil.nextDate());

		rebatePlanMaster.setCreatedBy(RandomTestUtil.nextInt());

		rebatePlanMaster.setSource(RandomTestUtil.randomString());

		rebatePlanMaster.setRebateBasedOn(RandomTestUtil.nextInt());

		rebatePlanMaster.setRebatePlanType(RandomTestUtil.nextInt());

		rebatePlanMaster.setRebatePlanId(RandomTestUtil.randomString());

		rebatePlanMaster.setManfCompanyMasterSid(RandomTestUtil.nextInt());

		rebatePlanMaster.setModifiedBy(RandomTestUtil.nextInt());

		rebatePlanMaster.setInboundStatus(RandomTestUtil.randomString());

		rebatePlanMaster.setSecondaryRebatePlanId(RandomTestUtil.randomString());

		rebatePlanMaster.setMarketShareIndicator(RandomTestUtil.randomString());

		rebatePlanMaster.setBogoEligible(RandomTestUtil.randomString());

		rebatePlanMaster.setMarketShareTo(RandomTestUtil.nextDate());

		rebatePlanMaster.setRebatePlanStatus(RandomTestUtil.nextInt());

		rebatePlanMaster.setMarketShareReference(RandomTestUtil.randomString());

		rebatePlanMaster.setNetSalesFormulaMasterSid(RandomTestUtil.randomString());

		rebatePlanMaster.setSelfGrowthFrom(RandomTestUtil.nextDate());

		rebatePlanMaster.setInternalNotes(RandomTestUtil.randomString());

		rebatePlanMaster.setSecondaryRebatePlanName(RandomTestUtil.randomString());

		rebatePlanMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rebatePlanMaster.setRebatePlanName(RandomTestUtil.randomString());

		rebatePlanMaster.setSelfGrowthReference(RandomTestUtil.randomString());

		rebatePlanMaster.setBatchId(RandomTestUtil.randomString());

		rebatePlanMaster.setFormulaType(RandomTestUtil.nextInt());

		rebatePlanMaster.setSelfGrowthTo(RandomTestUtil.nextDate());

		rebatePlanMaster.setRebatePlanNo(RandomTestUtil.randomString());

		_rebatePlanMasters.add(_persistence.update(rebatePlanMaster));

		return rebatePlanMaster;
	}

	private List<RebatePlanMaster> _rebatePlanMasters = new ArrayList<RebatePlanMaster>();
	private RebatePlanMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}