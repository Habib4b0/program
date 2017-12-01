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

import com.stpl.app.exception.NoSuchUdcsException;
import com.stpl.app.model.Udcs;
import com.stpl.app.service.UdcsLocalServiceUtil;
import com.stpl.app.service.persistence.UdcsPersistence;
import com.stpl.app.service.persistence.UdcsUtil;

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
public class UdcsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = UdcsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Udcs> iterator = _udcses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Udcs udcs = _persistence.create(pk);

		Assert.assertNotNull(udcs);

		Assert.assertEquals(udcs.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Udcs newUdcs = addUdcs();

		_persistence.remove(newUdcs);

		Udcs existingUdcs = _persistence.fetchByPrimaryKey(newUdcs.getPrimaryKey());

		Assert.assertNull(existingUdcs);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUdcs();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Udcs newUdcs = _persistence.create(pk);

		newUdcs.setUdc1(RandomTestUtil.nextInt());

		newUdcs.setUdc2(RandomTestUtil.nextInt());

		newUdcs.setMasterType(RandomTestUtil.randomString());

		newUdcs.setUdc3(RandomTestUtil.nextInt());

		newUdcs.setUdc12(RandomTestUtil.nextInt());

		newUdcs.setUdc11(RandomTestUtil.nextInt());

		newUdcs.setMasterSid(RandomTestUtil.nextInt());

		newUdcs.setUdc10(RandomTestUtil.nextInt());

		newUdcs.setUdc9(RandomTestUtil.nextInt());

		newUdcs.setUdc8(RandomTestUtil.nextInt());

		newUdcs.setUdc7(RandomTestUtil.nextInt());

		newUdcs.setUdc6(RandomTestUtil.nextInt());

		newUdcs.setUdc5(RandomTestUtil.nextInt());

		newUdcs.setUdc4(RandomTestUtil.nextInt());

		_udcses.add(_persistence.update(newUdcs));

		Udcs existingUdcs = _persistence.findByPrimaryKey(newUdcs.getPrimaryKey());

		Assert.assertEquals(existingUdcs.getUdc1(), newUdcs.getUdc1());
		Assert.assertEquals(existingUdcs.getUdc2(), newUdcs.getUdc2());
		Assert.assertEquals(existingUdcs.getMasterType(),
			newUdcs.getMasterType());
		Assert.assertEquals(existingUdcs.getUdc3(), newUdcs.getUdc3());
		Assert.assertEquals(existingUdcs.getUdc12(), newUdcs.getUdc12());
		Assert.assertEquals(existingUdcs.getUdc11(), newUdcs.getUdc11());
		Assert.assertEquals(existingUdcs.getUdcsSid(), newUdcs.getUdcsSid());
		Assert.assertEquals(existingUdcs.getMasterSid(), newUdcs.getMasterSid());
		Assert.assertEquals(existingUdcs.getUdc10(), newUdcs.getUdc10());
		Assert.assertEquals(existingUdcs.getUdc9(), newUdcs.getUdc9());
		Assert.assertEquals(existingUdcs.getUdc8(), newUdcs.getUdc8());
		Assert.assertEquals(existingUdcs.getUdc7(), newUdcs.getUdc7());
		Assert.assertEquals(existingUdcs.getUdc6(), newUdcs.getUdc6());
		Assert.assertEquals(existingUdcs.getUdc5(), newUdcs.getUdc5());
		Assert.assertEquals(existingUdcs.getUdc4(), newUdcs.getUdc4());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Udcs newUdcs = addUdcs();

		Udcs existingUdcs = _persistence.findByPrimaryKey(newUdcs.getPrimaryKey());

		Assert.assertEquals(existingUdcs, newUdcs);
	}

	@Test(expected = NoSuchUdcsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Udcs> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("UDCS", "udc1", true,
			"udc2", true, "masterType", true, "udc3", true, "udc12", true,
			"udc11", true, "udcsSid", true, "masterSid", true, "udc10", true,
			"udc9", true, "udc8", true, "udc7", true, "udc6", true, "udc5",
			true, "udc4", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Udcs newUdcs = addUdcs();

		Udcs existingUdcs = _persistence.fetchByPrimaryKey(newUdcs.getPrimaryKey());

		Assert.assertEquals(existingUdcs, newUdcs);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Udcs missingUdcs = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUdcs);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Udcs newUdcs1 = addUdcs();
		Udcs newUdcs2 = addUdcs();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUdcs1.getPrimaryKey());
		primaryKeys.add(newUdcs2.getPrimaryKey());

		Map<Serializable, Udcs> udcses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, udcses.size());
		Assert.assertEquals(newUdcs1, udcses.get(newUdcs1.getPrimaryKey()));
		Assert.assertEquals(newUdcs2, udcses.get(newUdcs2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Udcs> udcses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(udcses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Udcs newUdcs = addUdcs();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUdcs.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Udcs> udcses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, udcses.size());
		Assert.assertEquals(newUdcs, udcses.get(newUdcs.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Udcs> udcses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(udcses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Udcs newUdcs = addUdcs();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUdcs.getPrimaryKey());

		Map<Serializable, Udcs> udcses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, udcses.size());
		Assert.assertEquals(newUdcs, udcses.get(newUdcs.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UdcsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Udcs>() {
				@Override
				public void performAction(Udcs udcs) {
					Assert.assertNotNull(udcs);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Udcs newUdcs = addUdcs();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("udcsSid",
				newUdcs.getUdcsSid()));

		List<Udcs> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Udcs existingUdcs = result.get(0);

		Assert.assertEquals(existingUdcs, newUdcs);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("udcsSid",
				RandomTestUtil.nextInt()));

		List<Udcs> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Udcs newUdcs = addUdcs();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("udcsSid"));

		Object newUdcsSid = newUdcs.getUdcsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("udcsSid",
				new Object[] { newUdcsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUdcsSid = result.get(0);

		Assert.assertEquals(existingUdcsSid, newUdcsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Udcs.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("udcsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("udcsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Udcs addUdcs() throws Exception {
		int pk = RandomTestUtil.nextInt();

		Udcs udcs = _persistence.create(pk);

		udcs.setUdc1(RandomTestUtil.nextInt());

		udcs.setUdc2(RandomTestUtil.nextInt());

		udcs.setMasterType(RandomTestUtil.randomString());

		udcs.setUdc3(RandomTestUtil.nextInt());

		udcs.setUdc12(RandomTestUtil.nextInt());

		udcs.setUdc11(RandomTestUtil.nextInt());

		udcs.setMasterSid(RandomTestUtil.nextInt());

		udcs.setUdc10(RandomTestUtil.nextInt());

		udcs.setUdc9(RandomTestUtil.nextInt());

		udcs.setUdc8(RandomTestUtil.nextInt());

		udcs.setUdc7(RandomTestUtil.nextInt());

		udcs.setUdc6(RandomTestUtil.nextInt());

		udcs.setUdc5(RandomTestUtil.nextInt());

		udcs.setUdc4(RandomTestUtil.nextInt());

		_udcses.add(_persistence.update(udcs));

		return udcs;
	}

	private List<Udcs> _udcses = new ArrayList<Udcs>();
	private UdcsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}