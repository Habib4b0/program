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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchGcmCompanyLinkException;
import com.stpl.app.model.GcmCompanyLink;
import com.stpl.app.service.GcmCompanyLinkLocalServiceUtil;
import com.stpl.app.service.persistence.GcmCompanyLinkPersistence;
import com.stpl.app.service.persistence.GcmCompanyLinkUtil;

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
public class GcmCompanyLinkPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GcmCompanyLinkUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GcmCompanyLink> iterator = _gcmCompanyLinks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyLink gcmCompanyLink = _persistence.create(pk);

		Assert.assertNotNull(gcmCompanyLink);

		Assert.assertEquals(gcmCompanyLink.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		_persistence.remove(newGcmCompanyLink);

		GcmCompanyLink existingGcmCompanyLink = _persistence.fetchByPrimaryKey(newGcmCompanyLink.getPrimaryKey());

		Assert.assertNull(existingGcmCompanyLink);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGcmCompanyLink();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyLink newGcmCompanyLink = _persistence.create(pk);

		newGcmCompanyLink.setCheckRecord(RandomTestUtil.randomBoolean());

		newGcmCompanyLink.setUserId(RandomTestUtil.nextInt());

		newGcmCompanyLink.setCompanyNo(RandomTestUtil.randomString());

		newGcmCompanyLink.setCompanyStringId(RandomTestUtil.randomString());

		newGcmCompanyLink.setSessionId(RandomTestUtil.randomString());

		newGcmCompanyLink.setCompanyName(RandomTestUtil.randomString());

		newGcmCompanyLink.setLinkId(RandomTestUtil.randomString());

		newGcmCompanyLink.setCompanyMasterSid(RandomTestUtil.nextInt());

		_gcmCompanyLinks.add(_persistence.update(newGcmCompanyLink));

		GcmCompanyLink existingGcmCompanyLink = _persistence.findByPrimaryKey(newGcmCompanyLink.getPrimaryKey());

		Assert.assertEquals(existingGcmCompanyLink.getCheckRecord(),
			newGcmCompanyLink.getCheckRecord());
		Assert.assertEquals(existingGcmCompanyLink.getUserId(),
			newGcmCompanyLink.getUserId());
		Assert.assertEquals(existingGcmCompanyLink.getCompanyNo(),
			newGcmCompanyLink.getCompanyNo());
		Assert.assertEquals(existingGcmCompanyLink.getCompanyStringId(),
			newGcmCompanyLink.getCompanyStringId());
		Assert.assertEquals(existingGcmCompanyLink.getGcmCompanyLinkSid(),
			newGcmCompanyLink.getGcmCompanyLinkSid());
		Assert.assertEquals(existingGcmCompanyLink.getSessionId(),
			newGcmCompanyLink.getSessionId());
		Assert.assertEquals(existingGcmCompanyLink.getCompanyName(),
			newGcmCompanyLink.getCompanyName());
		Assert.assertEquals(existingGcmCompanyLink.getLinkId(),
			newGcmCompanyLink.getLinkId());
		Assert.assertEquals(existingGcmCompanyLink.getCompanyMasterSid(),
			newGcmCompanyLink.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		GcmCompanyLink existingGcmCompanyLink = _persistence.findByPrimaryKey(newGcmCompanyLink.getPrimaryKey());

		Assert.assertEquals(existingGcmCompanyLink, newGcmCompanyLink);
	}

	@Test(expected = NoSuchGcmCompanyLinkException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GcmCompanyLink> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GCM_COMPANY_LINK",
			"checkRecord", true, "userId", true, "companyNo", true,
			"companyStringId", true, "gcmCompanyLinkSid", true, "sessionId",
			true, "companyName", true, "linkId", true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		GcmCompanyLink existingGcmCompanyLink = _persistence.fetchByPrimaryKey(newGcmCompanyLink.getPrimaryKey());

		Assert.assertEquals(existingGcmCompanyLink, newGcmCompanyLink);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyLink missingGcmCompanyLink = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGcmCompanyLink);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GcmCompanyLink newGcmCompanyLink1 = addGcmCompanyLink();
		GcmCompanyLink newGcmCompanyLink2 = addGcmCompanyLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmCompanyLink1.getPrimaryKey());
		primaryKeys.add(newGcmCompanyLink2.getPrimaryKey());

		Map<Serializable, GcmCompanyLink> gcmCompanyLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gcmCompanyLinks.size());
		Assert.assertEquals(newGcmCompanyLink1,
			gcmCompanyLinks.get(newGcmCompanyLink1.getPrimaryKey()));
		Assert.assertEquals(newGcmCompanyLink2,
			gcmCompanyLinks.get(newGcmCompanyLink2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GcmCompanyLink> gcmCompanyLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmCompanyLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmCompanyLink.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GcmCompanyLink> gcmCompanyLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmCompanyLinks.size());
		Assert.assertEquals(newGcmCompanyLink,
			gcmCompanyLinks.get(newGcmCompanyLink.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GcmCompanyLink> gcmCompanyLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmCompanyLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmCompanyLink.getPrimaryKey());

		Map<Serializable, GcmCompanyLink> gcmCompanyLinks = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmCompanyLinks.size());
		Assert.assertEquals(newGcmCompanyLink,
			gcmCompanyLinks.get(newGcmCompanyLink.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GcmCompanyLinkLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GcmCompanyLink>() {
				@Override
				public void performAction(GcmCompanyLink gcmCompanyLink) {
					Assert.assertNotNull(gcmCompanyLink);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmCompanyLinkSid",
				newGcmCompanyLink.getGcmCompanyLinkSid()));

		List<GcmCompanyLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GcmCompanyLink existingGcmCompanyLink = result.get(0);

		Assert.assertEquals(existingGcmCompanyLink, newGcmCompanyLink);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmCompanyLinkSid",
				RandomTestUtil.nextInt()));

		List<GcmCompanyLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GcmCompanyLink newGcmCompanyLink = addGcmCompanyLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmCompanyLinkSid"));

		Object newGcmCompanyLinkSid = newGcmCompanyLink.getGcmCompanyLinkSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmCompanyLinkSid",
				new Object[] { newGcmCompanyLinkSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGcmCompanyLinkSid = result.get(0);

		Assert.assertEquals(existingGcmCompanyLinkSid, newGcmCompanyLinkSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmCompanyLinkSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmCompanyLinkSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GcmCompanyLink addGcmCompanyLink() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyLink gcmCompanyLink = _persistence.create(pk);

		gcmCompanyLink.setCheckRecord(RandomTestUtil.randomBoolean());

		gcmCompanyLink.setUserId(RandomTestUtil.nextInt());

		gcmCompanyLink.setCompanyNo(RandomTestUtil.randomString());

		gcmCompanyLink.setCompanyStringId(RandomTestUtil.randomString());

		gcmCompanyLink.setSessionId(RandomTestUtil.randomString());

		gcmCompanyLink.setCompanyName(RandomTestUtil.randomString());

		gcmCompanyLink.setLinkId(RandomTestUtil.randomString());

		gcmCompanyLink.setCompanyMasterSid(RandomTestUtil.nextInt());

		_gcmCompanyLinks.add(_persistence.update(gcmCompanyLink));

		return gcmCompanyLink;
	}

	private List<GcmCompanyLink> _gcmCompanyLinks = new ArrayList<GcmCompanyLink>();
	private GcmCompanyLinkPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}