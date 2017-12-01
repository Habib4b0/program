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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchHistItemGroupException;
import com.stpl.app.model.HistItemGroup;
import com.stpl.app.service.persistence.HistItemGroupPK;
import com.stpl.app.service.persistence.HistItemGroupPersistence;
import com.stpl.app.service.persistence.HistItemGroupUtil;

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
public class HistItemGroupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistItemGroupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistItemGroup> iterator = _histItemGroups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistItemGroupPK pk = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistItemGroup histItemGroup = _persistence.create(pk);

		Assert.assertNotNull(histItemGroup);

		Assert.assertEquals(histItemGroup.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		_persistence.remove(newHistItemGroup);

		HistItemGroup existingHistItemGroup = _persistence.fetchByPrimaryKey(newHistItemGroup.getPrimaryKey());

		Assert.assertNull(existingHistItemGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistItemGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistItemGroupPK pk = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistItemGroup newHistItemGroup = _persistence.create(pk);

		newHistItemGroup.setLastModifiedDate(RandomTestUtil.nextDate());

		newHistItemGroup.setCreatedDate(RandomTestUtil.nextDate());

		newHistItemGroup.setCreatedBy(RandomTestUtil.nextInt());

		newHistItemGroup.setItemGroupNo(RandomTestUtil.randomString());

		newHistItemGroup.setModifiedBy(RandomTestUtil.nextInt());

		newHistItemGroup.setItemGroupDescription(RandomTestUtil.randomString());

		newHistItemGroup.setModifiedDate(RandomTestUtil.nextDate());

		newHistItemGroup.setItemGroupName(RandomTestUtil.randomString());

		newHistItemGroup.setCompanyMasterSid(RandomTestUtil.nextInt());

		_histItemGroups.add(_persistence.update(newHistItemGroup));

		HistItemGroup existingHistItemGroup = _persistence.findByPrimaryKey(newHistItemGroup.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingHistItemGroup.getLastModifiedDate()),
			Time.getShortTimestamp(newHistItemGroup.getLastModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistItemGroup.getCreatedDate()),
			Time.getShortTimestamp(newHistItemGroup.getCreatedDate()));
		Assert.assertEquals(existingHistItemGroup.getCreatedBy(),
			newHistItemGroup.getCreatedBy());
		Assert.assertEquals(existingHistItemGroup.getActionFlag(),
			newHistItemGroup.getActionFlag());
		Assert.assertEquals(existingHistItemGroup.getItemGroupNo(),
			newHistItemGroup.getItemGroupNo());
		Assert.assertEquals(existingHistItemGroup.getVersionNo(),
			newHistItemGroup.getVersionNo());
		Assert.assertEquals(existingHistItemGroup.getModifiedBy(),
			newHistItemGroup.getModifiedBy());
		Assert.assertEquals(existingHistItemGroup.getItemGroupDescription(),
			newHistItemGroup.getItemGroupDescription());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistItemGroup.getModifiedDate()),
			Time.getShortTimestamp(newHistItemGroup.getModifiedDate()));
		Assert.assertEquals(existingHistItemGroup.getItemGroupName(),
			newHistItemGroup.getItemGroupName());
		Assert.assertEquals(existingHistItemGroup.getItemGroupSid(),
			newHistItemGroup.getItemGroupSid());
		Assert.assertEquals(existingHistItemGroup.getCompanyMasterSid(),
			newHistItemGroup.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		HistItemGroup existingHistItemGroup = _persistence.findByPrimaryKey(newHistItemGroup.getPrimaryKey());

		Assert.assertEquals(existingHistItemGroup, newHistItemGroup);
	}

	@Test(expected = NoSuchHistItemGroupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistItemGroupPK pk = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		HistItemGroup existingHistItemGroup = _persistence.fetchByPrimaryKey(newHistItemGroup.getPrimaryKey());

		Assert.assertEquals(existingHistItemGroup, newHistItemGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistItemGroupPK pk = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistItemGroup missingHistItemGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistItemGroup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistItemGroup newHistItemGroup1 = addHistItemGroup();
		HistItemGroup newHistItemGroup2 = addHistItemGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistItemGroup1.getPrimaryKey());
		primaryKeys.add(newHistItemGroup2.getPrimaryKey());

		Map<Serializable, HistItemGroup> histItemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histItemGroups.size());
		Assert.assertEquals(newHistItemGroup1,
			histItemGroups.get(newHistItemGroup1.getPrimaryKey()));
		Assert.assertEquals(newHistItemGroup2,
			histItemGroups.get(newHistItemGroup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistItemGroupPK pk1 = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistItemGroupPK pk2 = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistItemGroup> histItemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histItemGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		HistItemGroupPK pk = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistItemGroup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistItemGroup> histItemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histItemGroups.size());
		Assert.assertEquals(newHistItemGroup,
			histItemGroups.get(newHistItemGroup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistItemGroup> histItemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histItemGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistItemGroup.getPrimaryKey());

		Map<Serializable, HistItemGroup> histItemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histItemGroups.size());
		Assert.assertEquals(newHistItemGroup,
			histItemGroups.get(newHistItemGroup.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistItemGroup.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistItemGroup.getVersionNo()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemGroupSid",
				newHistItemGroup.getItemGroupSid()));

		List<HistItemGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistItemGroup existingHistItemGroup = result.get(0);

		Assert.assertEquals(existingHistItemGroup, newHistItemGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemGroupSid",
				RandomTestUtil.nextInt()));

		List<HistItemGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistItemGroup newHistItemGroup = addHistItemGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		Object newActionFlag = newHistItemGroup.getActionFlag();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { newActionFlag }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActionFlag = result.get(0);

		Assert.assertEquals(existingActionFlag, newActionFlag);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistItemGroup addHistItemGroup() throws Exception {
		HistItemGroupPK pk = new HistItemGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistItemGroup histItemGroup = _persistence.create(pk);

		histItemGroup.setLastModifiedDate(RandomTestUtil.nextDate());

		histItemGroup.setCreatedDate(RandomTestUtil.nextDate());

		histItemGroup.setCreatedBy(RandomTestUtil.nextInt());

		histItemGroup.setItemGroupNo(RandomTestUtil.randomString());

		histItemGroup.setModifiedBy(RandomTestUtil.nextInt());

		histItemGroup.setItemGroupDescription(RandomTestUtil.randomString());

		histItemGroup.setModifiedDate(RandomTestUtil.nextDate());

		histItemGroup.setItemGroupName(RandomTestUtil.randomString());

		histItemGroup.setCompanyMasterSid(RandomTestUtil.nextInt());

		_histItemGroups.add(_persistence.update(histItemGroup));

		return histItemGroup;
	}

	private List<HistItemGroup> _histItemGroups = new ArrayList<HistItemGroup>();
	private HistItemGroupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}