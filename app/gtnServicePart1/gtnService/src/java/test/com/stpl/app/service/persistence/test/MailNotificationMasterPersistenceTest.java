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

import com.stpl.app.exception.NoSuchMailNotificationMasterException;
import com.stpl.app.model.MailNotificationMaster;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;
import com.stpl.app.service.persistence.MailNotificationMasterPersistence;
import com.stpl.app.service.persistence.MailNotificationMasterUtil;

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
public class MailNotificationMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MailNotificationMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MailNotificationMaster> iterator = _mailNotificationMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MailNotificationMaster mailNotificationMaster = _persistence.create(pk);

		Assert.assertNotNull(mailNotificationMaster);

		Assert.assertEquals(mailNotificationMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		_persistence.remove(newMailNotificationMaster);

		MailNotificationMaster existingMailNotificationMaster = _persistence.fetchByPrimaryKey(newMailNotificationMaster.getPrimaryKey());

		Assert.assertNull(existingMailNotificationMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMailNotificationMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MailNotificationMaster newMailNotificationMaster = _persistence.create(pk);

		newMailNotificationMaster.setSubject(RandomTestUtil.randomString());

		newMailNotificationMaster.setCreatedDate(RandomTestUtil.nextDate());

		newMailNotificationMaster.setCreatedBy(RandomTestUtil.nextInt());

		newMailNotificationMaster.setToMailIds(RandomTestUtil.randomString());

		newMailNotificationMaster.setNotificationCategoryId(RandomTestUtil.nextInt());

		newMailNotificationMaster.setNotificationModule(RandomTestUtil.randomString());

		newMailNotificationMaster.setBody(RandomTestUtil.randomString());

		newMailNotificationMaster.setFromMailId(RandomTestUtil.randomString());

		newMailNotificationMaster.setCcMailIds(RandomTestUtil.randomString());

		newMailNotificationMaster.setVersionNo(RandomTestUtil.nextInt());

		newMailNotificationMaster.setModifiedBy(RandomTestUtil.nextInt());

		newMailNotificationMaster.setModifiedDate(RandomTestUtil.nextDate());

		_mailNotificationMasters.add(_persistence.update(
				newMailNotificationMaster));

		MailNotificationMaster existingMailNotificationMaster = _persistence.findByPrimaryKey(newMailNotificationMaster.getPrimaryKey());

		Assert.assertEquals(existingMailNotificationMaster.getSubject(),
			newMailNotificationMaster.getSubject());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMailNotificationMaster.getCreatedDate()),
			Time.getShortTimestamp(newMailNotificationMaster.getCreatedDate()));
		Assert.assertEquals(existingMailNotificationMaster.getCreatedBy(),
			newMailNotificationMaster.getCreatedBy());
		Assert.assertEquals(existingMailNotificationMaster.getToMailIds(),
			newMailNotificationMaster.getToMailIds());
		Assert.assertEquals(existingMailNotificationMaster.getNotificationCategoryId(),
			newMailNotificationMaster.getNotificationCategoryId());
		Assert.assertEquals(existingMailNotificationMaster.getNotificationModule(),
			newMailNotificationMaster.getNotificationModule());
		Assert.assertEquals(existingMailNotificationMaster.getBody(),
			newMailNotificationMaster.getBody());
		Assert.assertEquals(existingMailNotificationMaster.getFromMailId(),
			newMailNotificationMaster.getFromMailId());
		Assert.assertEquals(existingMailNotificationMaster.getCcMailIds(),
			newMailNotificationMaster.getCcMailIds());
		Assert.assertEquals(existingMailNotificationMaster.getVersionNo(),
			newMailNotificationMaster.getVersionNo());
		Assert.assertEquals(existingMailNotificationMaster.getModifiedBy(),
			newMailNotificationMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMailNotificationMaster.getModifiedDate()),
			Time.getShortTimestamp(newMailNotificationMaster.getModifiedDate()));
		Assert.assertEquals(existingMailNotificationMaster.getMailNotificationSid(),
			newMailNotificationMaster.getMailNotificationSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		MailNotificationMaster existingMailNotificationMaster = _persistence.findByPrimaryKey(newMailNotificationMaster.getPrimaryKey());

		Assert.assertEquals(existingMailNotificationMaster,
			newMailNotificationMaster);
	}

	@Test(expected = NoSuchMailNotificationMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MailNotificationMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MAIL_NOTIFICATION_MASTER",
			"subject", true, "createdDate", true, "createdBy", true,
			"toMailIds", true, "notificationCategoryId", true,
			"notificationModule", true, "body", true, "fromMailId", true,
			"ccMailIds", true, "versionNo", true, "modifiedBy", true,
			"modifiedDate", true, "mailNotificationSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		MailNotificationMaster existingMailNotificationMaster = _persistence.fetchByPrimaryKey(newMailNotificationMaster.getPrimaryKey());

		Assert.assertEquals(existingMailNotificationMaster,
			newMailNotificationMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MailNotificationMaster missingMailNotificationMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMailNotificationMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MailNotificationMaster newMailNotificationMaster1 = addMailNotificationMaster();
		MailNotificationMaster newMailNotificationMaster2 = addMailNotificationMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMailNotificationMaster1.getPrimaryKey());
		primaryKeys.add(newMailNotificationMaster2.getPrimaryKey());

		Map<Serializable, MailNotificationMaster> mailNotificationMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mailNotificationMasters.size());
		Assert.assertEquals(newMailNotificationMaster1,
			mailNotificationMasters.get(
				newMailNotificationMaster1.getPrimaryKey()));
		Assert.assertEquals(newMailNotificationMaster2,
			mailNotificationMasters.get(
				newMailNotificationMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MailNotificationMaster> mailNotificationMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mailNotificationMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMailNotificationMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MailNotificationMaster> mailNotificationMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mailNotificationMasters.size());
		Assert.assertEquals(newMailNotificationMaster,
			mailNotificationMasters.get(
				newMailNotificationMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MailNotificationMaster> mailNotificationMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mailNotificationMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMailNotificationMaster.getPrimaryKey());

		Map<Serializable, MailNotificationMaster> mailNotificationMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mailNotificationMasters.size());
		Assert.assertEquals(newMailNotificationMaster,
			mailNotificationMasters.get(
				newMailNotificationMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MailNotificationMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MailNotificationMaster>() {
				@Override
				public void performAction(
					MailNotificationMaster mailNotificationMaster) {
					Assert.assertNotNull(mailNotificationMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MailNotificationMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mailNotificationSid",
				newMailNotificationMaster.getMailNotificationSid()));

		List<MailNotificationMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MailNotificationMaster existingMailNotificationMaster = result.get(0);

		Assert.assertEquals(existingMailNotificationMaster,
			newMailNotificationMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MailNotificationMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mailNotificationSid",
				RandomTestUtil.nextInt()));

		List<MailNotificationMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MailNotificationMaster newMailNotificationMaster = addMailNotificationMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MailNotificationMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mailNotificationSid"));

		Object newMailNotificationSid = newMailNotificationMaster.getMailNotificationSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("mailNotificationSid",
				new Object[] { newMailNotificationSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMailNotificationSid = result.get(0);

		Assert.assertEquals(existingMailNotificationSid, newMailNotificationSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MailNotificationMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mailNotificationSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("mailNotificationSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MailNotificationMaster addMailNotificationMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		MailNotificationMaster mailNotificationMaster = _persistence.create(pk);

		mailNotificationMaster.setSubject(RandomTestUtil.randomString());

		mailNotificationMaster.setCreatedDate(RandomTestUtil.nextDate());

		mailNotificationMaster.setCreatedBy(RandomTestUtil.nextInt());

		mailNotificationMaster.setToMailIds(RandomTestUtil.randomString());

		mailNotificationMaster.setNotificationCategoryId(RandomTestUtil.nextInt());

		mailNotificationMaster.setNotificationModule(RandomTestUtil.randomString());

		mailNotificationMaster.setBody(RandomTestUtil.randomString());

		mailNotificationMaster.setFromMailId(RandomTestUtil.randomString());

		mailNotificationMaster.setCcMailIds(RandomTestUtil.randomString());

		mailNotificationMaster.setVersionNo(RandomTestUtil.nextInt());

		mailNotificationMaster.setModifiedBy(RandomTestUtil.nextInt());

		mailNotificationMaster.setModifiedDate(RandomTestUtil.nextDate());

		_mailNotificationMasters.add(_persistence.update(mailNotificationMaster));

		return mailNotificationMaster;
	}

	private List<MailNotificationMaster> _mailNotificationMasters = new ArrayList<MailNotificationMaster>();
	private MailNotificationMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}