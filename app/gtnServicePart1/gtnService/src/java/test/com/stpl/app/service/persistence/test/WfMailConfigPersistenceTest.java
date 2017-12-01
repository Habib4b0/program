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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchWfMailConfigException;
import com.stpl.app.model.WfMailConfig;
import com.stpl.app.service.WfMailConfigLocalServiceUtil;
import com.stpl.app.service.persistence.WfMailConfigPersistence;
import com.stpl.app.service.persistence.WfMailConfigUtil;

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
public class WfMailConfigPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = WfMailConfigUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<WfMailConfig> iterator = _wfMailConfigs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WfMailConfig wfMailConfig = _persistence.create(pk);

		Assert.assertNotNull(wfMailConfig);

		Assert.assertEquals(wfMailConfig.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		_persistence.remove(newWfMailConfig);

		WfMailConfig existingWfMailConfig = _persistence.fetchByPrimaryKey(newWfMailConfig.getPrimaryKey());

		Assert.assertNull(existingWfMailConfig);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addWfMailConfig();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WfMailConfig newWfMailConfig = _persistence.create(pk);

		newWfMailConfig.setSmtpFlag(RandomTestUtil.randomString());

		newWfMailConfig.setCreatedBy(RandomTestUtil.nextInt());

		newWfMailConfig.setEmailAddress(RandomTestUtil.randomString());

		newWfMailConfig.setPassword(RandomTestUtil.randomString());

		newWfMailConfig.setModifiedBy(RandomTestUtil.nextInt());

		newWfMailConfig.setHostName(RandomTestUtil.randomString());

		newWfMailConfig.setCreatedDate(RandomTestUtil.nextDate());

		newWfMailConfig.setPortNumber(RandomTestUtil.randomString());

		newWfMailConfig.setModifiedDate(RandomTestUtil.nextDate());

		newWfMailConfig.setInboundStatus(RandomTestUtil.randomString());

		newWfMailConfig.setTestMailAddress(RandomTestUtil.randomString());

		_wfMailConfigs.add(_persistence.update(newWfMailConfig));

		WfMailConfig existingWfMailConfig = _persistence.findByPrimaryKey(newWfMailConfig.getPrimaryKey());

		Assert.assertEquals(existingWfMailConfig.getSmtpFlag(),
			newWfMailConfig.getSmtpFlag());
		Assert.assertEquals(existingWfMailConfig.getCreatedBy(),
			newWfMailConfig.getCreatedBy());
		Assert.assertEquals(existingWfMailConfig.getEmailAddress(),
			newWfMailConfig.getEmailAddress());
		Assert.assertEquals(existingWfMailConfig.getPassword(),
			newWfMailConfig.getPassword());
		Assert.assertEquals(existingWfMailConfig.getModifiedBy(),
			newWfMailConfig.getModifiedBy());
		Assert.assertEquals(existingWfMailConfig.getWfMailConfigSid(),
			newWfMailConfig.getWfMailConfigSid());
		Assert.assertEquals(existingWfMailConfig.getHostName(),
			newWfMailConfig.getHostName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWfMailConfig.getCreatedDate()),
			Time.getShortTimestamp(newWfMailConfig.getCreatedDate()));
		Assert.assertEquals(existingWfMailConfig.getPortNumber(),
			newWfMailConfig.getPortNumber());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWfMailConfig.getModifiedDate()),
			Time.getShortTimestamp(newWfMailConfig.getModifiedDate()));
		Assert.assertEquals(existingWfMailConfig.getInboundStatus(),
			newWfMailConfig.getInboundStatus());
		Assert.assertEquals(existingWfMailConfig.getTestMailAddress(),
			newWfMailConfig.getTestMailAddress());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		WfMailConfig existingWfMailConfig = _persistence.findByPrimaryKey(newWfMailConfig.getPrimaryKey());

		Assert.assertEquals(existingWfMailConfig, newWfMailConfig);
	}

	@Test(expected = NoSuchWfMailConfigException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<WfMailConfig> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("WF_MAIL_CONFIG",
			"smtpFlag", true, "createdBy", true, "emailAddress", true,
			"password", true, "modifiedBy", true, "wfMailConfigSid", true,
			"hostName", true, "createdDate", true, "portNumber", true,
			"modifiedDate", true, "inboundStatus", true, "testMailAddress", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		WfMailConfig existingWfMailConfig = _persistence.fetchByPrimaryKey(newWfMailConfig.getPrimaryKey());

		Assert.assertEquals(existingWfMailConfig, newWfMailConfig);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WfMailConfig missingWfMailConfig = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingWfMailConfig);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		WfMailConfig newWfMailConfig1 = addWfMailConfig();
		WfMailConfig newWfMailConfig2 = addWfMailConfig();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWfMailConfig1.getPrimaryKey());
		primaryKeys.add(newWfMailConfig2.getPrimaryKey());

		Map<Serializable, WfMailConfig> wfMailConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, wfMailConfigs.size());
		Assert.assertEquals(newWfMailConfig1,
			wfMailConfigs.get(newWfMailConfig1.getPrimaryKey()));
		Assert.assertEquals(newWfMailConfig2,
			wfMailConfigs.get(newWfMailConfig2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, WfMailConfig> wfMailConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(wfMailConfigs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWfMailConfig.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, WfMailConfig> wfMailConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, wfMailConfigs.size());
		Assert.assertEquals(newWfMailConfig,
			wfMailConfigs.get(newWfMailConfig.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, WfMailConfig> wfMailConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(wfMailConfigs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWfMailConfig.getPrimaryKey());

		Map<Serializable, WfMailConfig> wfMailConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, wfMailConfigs.size());
		Assert.assertEquals(newWfMailConfig,
			wfMailConfigs.get(newWfMailConfig.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = WfMailConfigLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WfMailConfig>() {
				@Override
				public void performAction(WfMailConfig wfMailConfig) {
					Assert.assertNotNull(wfMailConfig);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WfMailConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("wfMailConfigSid",
				newWfMailConfig.getWfMailConfigSid()));

		List<WfMailConfig> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		WfMailConfig existingWfMailConfig = result.get(0);

		Assert.assertEquals(existingWfMailConfig, newWfMailConfig);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WfMailConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("wfMailConfigSid",
				RandomTestUtil.nextInt()));

		List<WfMailConfig> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		WfMailConfig newWfMailConfig = addWfMailConfig();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WfMailConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"wfMailConfigSid"));

		Object newWfMailConfigSid = newWfMailConfig.getWfMailConfigSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("wfMailConfigSid",
				new Object[] { newWfMailConfigSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWfMailConfigSid = result.get(0);

		Assert.assertEquals(existingWfMailConfigSid, newWfMailConfigSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WfMailConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"wfMailConfigSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("wfMailConfigSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected WfMailConfig addWfMailConfig() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WfMailConfig wfMailConfig = _persistence.create(pk);

		wfMailConfig.setSmtpFlag(RandomTestUtil.randomString());

		wfMailConfig.setCreatedBy(RandomTestUtil.nextInt());

		wfMailConfig.setEmailAddress(RandomTestUtil.randomString());

		wfMailConfig.setPassword(RandomTestUtil.randomString());

		wfMailConfig.setModifiedBy(RandomTestUtil.nextInt());

		wfMailConfig.setHostName(RandomTestUtil.randomString());

		wfMailConfig.setCreatedDate(RandomTestUtil.nextDate());

		wfMailConfig.setPortNumber(RandomTestUtil.randomString());

		wfMailConfig.setModifiedDate(RandomTestUtil.nextDate());

		wfMailConfig.setInboundStatus(RandomTestUtil.randomString());

		wfMailConfig.setTestMailAddress(RandomTestUtil.randomString());

		_wfMailConfigs.add(_persistence.update(wfMailConfig));

		return wfMailConfig;
	}

	private List<WfMailConfig> _wfMailConfigs = new ArrayList<WfMailConfig>();
	private WfMailConfigPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}