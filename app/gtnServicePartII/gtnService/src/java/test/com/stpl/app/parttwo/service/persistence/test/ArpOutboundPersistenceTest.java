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

import com.stpl.app.parttwo.exception.NoSuchArpOutboundException;
import com.stpl.app.parttwo.model.ArpOutbound;
import com.stpl.app.parttwo.service.ArpOutboundLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.ArpOutboundPK;
import com.stpl.app.parttwo.service.persistence.ArpOutboundPersistence;
import com.stpl.app.parttwo.service.persistence.ArpOutboundUtil;

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
public class ArpOutboundPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = ArpOutboundUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ArpOutbound> iterator = _arpOutbounds.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ArpOutboundPK pk = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ArpOutbound arpOutbound = _persistence.create(pk);

		Assert.assertNotNull(arpOutbound);

		Assert.assertEquals(arpOutbound.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		_persistence.remove(newArpOutbound);

		ArpOutbound existingArpOutbound = _persistence.fetchByPrimaryKey(newArpOutbound.getPrimaryKey());

		Assert.assertNull(existingArpOutbound);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addArpOutbound();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ArpOutboundPK pk = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ArpOutbound newArpOutbound = _persistence.create(pk);

		newArpOutbound.setSalesUnitsRate(RandomTestUtil.nextDouble());

		newArpOutbound.setAccountType(RandomTestUtil.randomString());

		newArpOutbound.setOriginalBatchId(RandomTestUtil.randomString());

		newArpOutbound.setBrandMasterSid(RandomTestUtil.nextInt());

		newArpOutbound.setArpApprovalDate(RandomTestUtil.nextDate());

		newArpOutbound.setArpCreationDate(RandomTestUtil.nextDate());

		newArpOutbound.setCheckRecord(RandomTestUtil.randomBoolean());

		newArpOutbound.setAccount(RandomTestUtil.randomString());

		newArpOutbound.setOutboundStatus(RandomTestUtil.randomBoolean());

		_arpOutbounds.add(_persistence.update(newArpOutbound));

		ArpOutbound existingArpOutbound = _persistence.findByPrimaryKey(newArpOutbound.getPrimaryKey());

		AssertUtils.assertEquals(existingArpOutbound.getSalesUnitsRate(),
			newArpOutbound.getSalesUnitsRate());
		Assert.assertEquals(existingArpOutbound.getAccountType(),
			newArpOutbound.getAccountType());
		Assert.assertEquals(existingArpOutbound.getOriginalBatchId(),
			newArpOutbound.getOriginalBatchId());
		Assert.assertEquals(existingArpOutbound.getCompanyMasterSid(),
			newArpOutbound.getCompanyMasterSid());
		Assert.assertEquals(existingArpOutbound.getBrandMasterSid(),
			newArpOutbound.getBrandMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingArpOutbound.getArpApprovalDate()),
			Time.getShortTimestamp(newArpOutbound.getArpApprovalDate()));
		Assert.assertEquals(existingArpOutbound.getArpMasterSid(),
			newArpOutbound.getArpMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingArpOutbound.getArpCreationDate()),
			Time.getShortTimestamp(newArpOutbound.getArpCreationDate()));
		Assert.assertEquals(existingArpOutbound.getCheckRecord(),
			newArpOutbound.getCheckRecord());
		Assert.assertEquals(existingArpOutbound.getArpId(),
			newArpOutbound.getArpId());
		Assert.assertEquals(existingArpOutbound.getAccount(),
			newArpOutbound.getAccount());
		Assert.assertEquals(existingArpOutbound.getOutboundStatus(),
			newArpOutbound.getOutboundStatus());
		Assert.assertEquals(existingArpOutbound.getPeriodSid(),
			newArpOutbound.getPeriodSid());
		Assert.assertEquals(existingArpOutbound.getItemMasterSid(),
			newArpOutbound.getItemMasterSid());
		Assert.assertEquals(existingArpOutbound.getRsModelSid(),
			newArpOutbound.getRsModelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		ArpOutbound existingArpOutbound = _persistence.findByPrimaryKey(newArpOutbound.getPrimaryKey());

		Assert.assertEquals(existingArpOutbound, newArpOutbound);
	}

	@Test(expected = NoSuchArpOutboundException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ArpOutboundPK pk = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		ArpOutbound existingArpOutbound = _persistence.fetchByPrimaryKey(newArpOutbound.getPrimaryKey());

		Assert.assertEquals(existingArpOutbound, newArpOutbound);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ArpOutboundPK pk = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ArpOutbound missingArpOutbound = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingArpOutbound);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ArpOutbound newArpOutbound1 = addArpOutbound();
		ArpOutbound newArpOutbound2 = addArpOutbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newArpOutbound1.getPrimaryKey());
		primaryKeys.add(newArpOutbound2.getPrimaryKey());

		Map<Serializable, ArpOutbound> arpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, arpOutbounds.size());
		Assert.assertEquals(newArpOutbound1,
			arpOutbounds.get(newArpOutbound1.getPrimaryKey()));
		Assert.assertEquals(newArpOutbound2,
			arpOutbounds.get(newArpOutbound2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		ArpOutboundPK pk1 = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ArpOutboundPK pk2 = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ArpOutbound> arpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(arpOutbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		ArpOutboundPK pk = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newArpOutbound.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ArpOutbound> arpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, arpOutbounds.size());
		Assert.assertEquals(newArpOutbound,
			arpOutbounds.get(newArpOutbound.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ArpOutbound> arpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(arpOutbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newArpOutbound.getPrimaryKey());

		Map<Serializable, ArpOutbound> arpOutbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, arpOutbounds.size());
		Assert.assertEquals(newArpOutbound,
			arpOutbounds.get(newArpOutbound.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ArpOutboundLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ArpOutbound>() {
				@Override
				public void performAction(ArpOutbound arpOutbound) {
					Assert.assertNotNull(arpOutbound);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				newArpOutbound.getCompanyMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.arpMasterSid",
				newArpOutbound.getArpMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.arpId",
				newArpOutbound.getArpId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newArpOutbound.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newArpOutbound.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newArpOutbound.getRsModelSid()));

		List<ArpOutbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ArpOutbound existingArpOutbound = result.get(0);

		Assert.assertEquals(existingArpOutbound, newArpOutbound);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
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

		List<ArpOutbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ArpOutbound newArpOutbound = addArpOutbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.companyMasterSid"));

		Object newCompanyMasterSid = newArpOutbound.getCompanyMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.companyMasterSid",
				new Object[] { newCompanyMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyMasterSid = result.get(0);

		Assert.assertEquals(existingCompanyMasterSid, newCompanyMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ArpOutbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.companyMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.companyMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ArpOutbound addArpOutbound() throws Exception {
		ArpOutboundPK pk = new ArpOutboundPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ArpOutbound arpOutbound = _persistence.create(pk);

		arpOutbound.setSalesUnitsRate(RandomTestUtil.nextDouble());

		arpOutbound.setAccountType(RandomTestUtil.randomString());

		arpOutbound.setOriginalBatchId(RandomTestUtil.randomString());

		arpOutbound.setBrandMasterSid(RandomTestUtil.nextInt());

		arpOutbound.setArpApprovalDate(RandomTestUtil.nextDate());

		arpOutbound.setArpCreationDate(RandomTestUtil.nextDate());

		arpOutbound.setCheckRecord(RandomTestUtil.randomBoolean());

		arpOutbound.setAccount(RandomTestUtil.randomString());

		arpOutbound.setOutboundStatus(RandomTestUtil.randomBoolean());

		_arpOutbounds.add(_persistence.update(arpOutbound));

		return arpOutbound;
	}

	private List<ArpOutbound> _arpOutbounds = new ArrayList<ArpOutbound>();
	private ArpOutboundPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}