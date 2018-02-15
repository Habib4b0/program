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

package com.stpl.app.parttwo.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchStArpOutboundException;
import com.stpl.app.parttwo.model.StArpOutbound;
import com.stpl.app.parttwo.service.StArpOutboundLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPK;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPersistence;
import com.stpl.app.parttwo.service.persistence.StArpOutboundUtil;

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
public class StArpOutboundPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = StArpOutboundUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StArpOutbound> iterator = _stArpOutbounds.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StArpOutboundPK pk = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		StArpOutbound stArpOutbound = _persistence.create(pk);

		Assert.assertNotNull(stArpOutbound);

		Assert.assertEquals(stArpOutbound.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		_persistence.remove(newStArpOutbound);

		StArpOutbound existingStArpOutbound = _persistence.fetchByPrimaryKey(newStArpOutbound.getPrimaryKey());

		Assert.assertNull(existingStArpOutbound);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStArpOutbound();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StArpOutboundPK pk = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		StArpOutbound newStArpOutbound = _persistence.create(pk);

		newStArpOutbound.setSalesUnitsRate(RandomTestUtil.nextDouble());

		newStArpOutbound.setAccountType(RandomTestUtil.randomString());

		newStArpOutbound.setOriginalBatchId(RandomTestUtil.randomString());

		newStArpOutbound.setBrandMasterSid(RandomTestUtil.nextInt());

		newStArpOutbound.setArpApprovalDate(RandomTestUtil.nextDate());

		newStArpOutbound.setArpCreationDate(RandomTestUtil.nextDate());

		newStArpOutbound.setCheckRecord(RandomTestUtil.randomBoolean());

		newStArpOutbound.setAccount(RandomTestUtil.randomString());

		newStArpOutbound.setOutboundStatus(RandomTestUtil.randomBoolean());

		_stArpOutbounds.add(_persistence.update(newStArpOutbound));

		StArpOutbound existingStArpOutbound = _persistence.findByPrimaryKey(newStArpOutbound.getPrimaryKey());

		AssertUtils.assertEquals(existingStArpOutbound.getSalesUnitsRate(),
			newStArpOutbound.getSalesUnitsRate());
		Assert.assertEquals(existingStArpOutbound.getAccountType(),
			newStArpOutbound.getAccountType());
		Assert.assertEquals(existingStArpOutbound.getOriginalBatchId(),
			newStArpOutbound.getOriginalBatchId());
		Assert.assertEquals(existingStArpOutbound.getCompanyMasterSid(),
			newStArpOutbound.getCompanyMasterSid());
		Assert.assertEquals(existingStArpOutbound.getBrandMasterSid(),
			newStArpOutbound.getBrandMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStArpOutbound.getArpApprovalDate()),
			Time.getShortTimestamp(newStArpOutbound.getArpApprovalDate()));
		Assert.assertEquals(existingStArpOutbound.getUserId(),
			newStArpOutbound.getUserId());
		Assert.assertEquals(existingStArpOutbound.getArpMasterSid(),
			newStArpOutbound.getArpMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStArpOutbound.getArpCreationDate()),
			Time.getShortTimestamp(newStArpOutbound.getArpCreationDate()));
		Assert.assertEquals(existingStArpOutbound.getCheckRecord(),
			newStArpOutbound.getCheckRecord());
		Assert.assertEquals(existingStArpOutbound.getArpId(),
			newStArpOutbound.getArpId());
		Assert.assertEquals(existingStArpOutbound.getAccount(),
			newStArpOutbound.getAccount());
		Assert.assertEquals(existingStArpOutbound.getOutboundStatus(),
			newStArpOutbound.getOutboundStatus());
		Assert.assertEquals(existingStArpOutbound.getPeriodSid(),
			newStArpOutbound.getPeriodSid());
		Assert.assertEquals(existingStArpOutbound.getItemMasterSid(),
			newStArpOutbound.getItemMasterSid());
		Assert.assertEquals(existingStArpOutbound.getRsModelSid(),
			newStArpOutbound.getRsModelSid());
		Assert.assertEquals(existingStArpOutbound.getSessionId(),
			newStArpOutbound.getSessionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		StArpOutbound existingStArpOutbound = _persistence.findByPrimaryKey(newStArpOutbound.getPrimaryKey());

		Assert.assertEquals(existingStArpOutbound, newStArpOutbound);
	}

	@Test(expected = NoSuchStArpOutboundException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StArpOutboundPK pk = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		StArpOutbound existingStArpOutbound = _persistence.fetchByPrimaryKey(newStArpOutbound.getPrimaryKey());

		Assert.assertEquals(existingStArpOutbound, newStArpOutbound);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StArpOutboundPK pk = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		StArpOutbound missingStArpOutbound = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStArpOutbound);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StArpOutbound newStArpOutbound1 = addStArpOutbound();
		StArpOutbound newStArpOutbound2 = addStArpOutbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStArpOutbound1.getPrimaryKey());
		primaryKeys.add(newStArpOutbound2.getPrimaryKey());

		Map<Serializable, StArpOutbound> stArpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stArpOutbounds.size());
		Assert.assertEquals(newStArpOutbound1,
			stArpOutbounds.get(newStArpOutbound1.getPrimaryKey()));
		Assert.assertEquals(newStArpOutbound2,
			stArpOutbounds.get(newStArpOutbound2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StArpOutboundPK pk1 = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		StArpOutboundPK pk2 = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StArpOutbound> stArpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stArpOutbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		StArpOutboundPK pk = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStArpOutbound.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StArpOutbound> stArpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stArpOutbounds.size());
		Assert.assertEquals(newStArpOutbound,
			stArpOutbounds.get(newStArpOutbound.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StArpOutbound> stArpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stArpOutbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStArpOutbound.getPrimaryKey());

		Map<Serializable, StArpOutbound> stArpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stArpOutbounds.size());
		Assert.assertEquals(newStArpOutbound,
			stArpOutbounds.get(newStArpOutbound.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StArpOutboundLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StArpOutbound>() {
				@Override
				public void performAction(StArpOutbound stArpOutbound) {
					Assert.assertNotNull(stArpOutbound);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				newStArpOutbound.getCompanyMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStArpOutbound.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.arpMasterSid",
				newStArpOutbound.getArpMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.arpId",
				newStArpOutbound.getArpId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStArpOutbound.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newStArpOutbound.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStArpOutbound.getRsModelSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStArpOutbound.getSessionId()));

		List<StArpOutbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StArpOutbound existingStArpOutbound = result.get(0);

		Assert.assertEquals(existingStArpOutbound, newStArpOutbound);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.arpMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.arpId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.randomString()));

		List<StArpOutbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StArpOutbound newStArpOutbound = addStArpOutbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.companyMasterSid"));

		Object newCompanyMasterSid = newStArpOutbound.getCompanyMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.companyMasterSid",
				new Object[] { newCompanyMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyMasterSid = result.get(0);

		Assert.assertEquals(existingCompanyMasterSid, newCompanyMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.companyMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.companyMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StArpOutbound addStArpOutbound() throws Exception {
		StArpOutboundPK pk = new StArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString());

		StArpOutbound stArpOutbound = _persistence.create(pk);

		stArpOutbound.setSalesUnitsRate(RandomTestUtil.nextDouble());

		stArpOutbound.setAccountType(RandomTestUtil.randomString());

		stArpOutbound.setOriginalBatchId(RandomTestUtil.randomString());

		stArpOutbound.setBrandMasterSid(RandomTestUtil.nextInt());

		stArpOutbound.setArpApprovalDate(RandomTestUtil.nextDate());

		stArpOutbound.setArpCreationDate(RandomTestUtil.nextDate());

		stArpOutbound.setCheckRecord(RandomTestUtil.randomBoolean());

		stArpOutbound.setAccount(RandomTestUtil.randomString());

		stArpOutbound.setOutboundStatus(RandomTestUtil.randomBoolean());

		_stArpOutbounds.add(_persistence.update(stArpOutbound));

		return stArpOutbound;
	}

	private List<StArpOutbound> _stArpOutbounds = new ArrayList<StArpOutbound>();
	private StArpOutboundPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}