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

import com.stpl.app.exception.NoSuchHistCompanyGroupException;
import com.stpl.app.model.HistCompanyGroup;
import com.stpl.app.service.persistence.HistCompanyGroupPK;
import com.stpl.app.service.persistence.HistCompanyGroupPersistence;
import com.stpl.app.service.persistence.HistCompanyGroupUtil;

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
public class HistCompanyGroupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistCompanyGroupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistCompanyGroup> iterator = _histCompanyGroups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistCompanyGroupPK pk = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroup histCompanyGroup = _persistence.create(pk);

		Assert.assertNotNull(histCompanyGroup);

		Assert.assertEquals(histCompanyGroup.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		_persistence.remove(newHistCompanyGroup);

		HistCompanyGroup existingHistCompanyGroup = _persistence.fetchByPrimaryKey(newHistCompanyGroup.getPrimaryKey());

		Assert.assertNull(existingHistCompanyGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistCompanyGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistCompanyGroupPK pk = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroup newHistCompanyGroup = _persistence.create(pk);

		newHistCompanyGroup.setCompanyGroupNo(RandomTestUtil.randomString());

		newHistCompanyGroup.setCreatedDate(RandomTestUtil.nextDate());

		newHistCompanyGroup.setCreatedBy(RandomTestUtil.nextInt());

		newHistCompanyGroup.setActionDate(RandomTestUtil.nextDate());

		newHistCompanyGroup.setCompanyGroupDescription(RandomTestUtil.randomString());

		newHistCompanyGroup.setModifiedBy(RandomTestUtil.nextInt());

		newHistCompanyGroup.setModifiedDate(RandomTestUtil.nextDate());

		newHistCompanyGroup.setCompanyGroupName(RandomTestUtil.randomString());

		newHistCompanyGroup.setCompanyFilter(RandomTestUtil.randomString());

		_histCompanyGroups.add(_persistence.update(newHistCompanyGroup));

		HistCompanyGroup existingHistCompanyGroup = _persistence.findByPrimaryKey(newHistCompanyGroup.getPrimaryKey());

		Assert.assertEquals(existingHistCompanyGroup.getCompanyGroupNo(),
			newHistCompanyGroup.getCompanyGroupNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistCompanyGroup.getCreatedDate()),
			Time.getShortTimestamp(newHistCompanyGroup.getCreatedDate()));
		Assert.assertEquals(existingHistCompanyGroup.getCreatedBy(),
			newHistCompanyGroup.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistCompanyGroup.getActionDate()),
			Time.getShortTimestamp(newHistCompanyGroup.getActionDate()));
		Assert.assertEquals(existingHistCompanyGroup.getActionFlag(),
			newHistCompanyGroup.getActionFlag());
		Assert.assertEquals(existingHistCompanyGroup.getCompanyGroupSid(),
			newHistCompanyGroup.getCompanyGroupSid());
		Assert.assertEquals(existingHistCompanyGroup.getCompanyGroupDescription(),
			newHistCompanyGroup.getCompanyGroupDescription());
		Assert.assertEquals(existingHistCompanyGroup.getVersionNo(),
			newHistCompanyGroup.getVersionNo());
		Assert.assertEquals(existingHistCompanyGroup.getModifiedBy(),
			newHistCompanyGroup.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistCompanyGroup.getModifiedDate()),
			Time.getShortTimestamp(newHistCompanyGroup.getModifiedDate()));
		Assert.assertEquals(existingHistCompanyGroup.getCompanyGroupName(),
			newHistCompanyGroup.getCompanyGroupName());
		Assert.assertEquals(existingHistCompanyGroup.getCompanyFilter(),
			newHistCompanyGroup.getCompanyFilter());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		HistCompanyGroup existingHistCompanyGroup = _persistence.findByPrimaryKey(newHistCompanyGroup.getPrimaryKey());

		Assert.assertEquals(existingHistCompanyGroup, newHistCompanyGroup);
	}

	@Test(expected = NoSuchHistCompanyGroupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistCompanyGroupPK pk = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		HistCompanyGroup existingHistCompanyGroup = _persistence.fetchByPrimaryKey(newHistCompanyGroup.getPrimaryKey());

		Assert.assertEquals(existingHistCompanyGroup, newHistCompanyGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistCompanyGroupPK pk = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroup missingHistCompanyGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistCompanyGroup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistCompanyGroup newHistCompanyGroup1 = addHistCompanyGroup();
		HistCompanyGroup newHistCompanyGroup2 = addHistCompanyGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistCompanyGroup1.getPrimaryKey());
		primaryKeys.add(newHistCompanyGroup2.getPrimaryKey());

		Map<Serializable, HistCompanyGroup> histCompanyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histCompanyGroups.size());
		Assert.assertEquals(newHistCompanyGroup1,
			histCompanyGroups.get(newHistCompanyGroup1.getPrimaryKey()));
		Assert.assertEquals(newHistCompanyGroup2,
			histCompanyGroups.get(newHistCompanyGroup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistCompanyGroupPK pk1 = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroupPK pk2 = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistCompanyGroup> histCompanyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histCompanyGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		HistCompanyGroupPK pk = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistCompanyGroup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistCompanyGroup> histCompanyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histCompanyGroups.size());
		Assert.assertEquals(newHistCompanyGroup,
			histCompanyGroups.get(newHistCompanyGroup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistCompanyGroup> histCompanyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histCompanyGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistCompanyGroup.getPrimaryKey());

		Map<Serializable, HistCompanyGroup> histCompanyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histCompanyGroups.size());
		Assert.assertEquals(newHistCompanyGroup,
			histCompanyGroups.get(newHistCompanyGroup.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistCompanyGroup.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyGroupSid",
				newHistCompanyGroup.getCompanyGroupSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistCompanyGroup.getVersionNo()));

		List<HistCompanyGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistCompanyGroup existingHistCompanyGroup = result.get(0);

		Assert.assertEquals(existingHistCompanyGroup, newHistCompanyGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyGroupSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));

		List<HistCompanyGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistCompanyGroup newHistCompanyGroup = addHistCompanyGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		Object newActionFlag = newHistCompanyGroup.getActionFlag();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { newActionFlag }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActionFlag = result.get(0);

		Assert.assertEquals(existingActionFlag, newActionFlag);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistCompanyGroup addHistCompanyGroup() throws Exception {
		HistCompanyGroupPK pk = new HistCompanyGroupPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroup histCompanyGroup = _persistence.create(pk);

		histCompanyGroup.setCompanyGroupNo(RandomTestUtil.randomString());

		histCompanyGroup.setCreatedDate(RandomTestUtil.nextDate());

		histCompanyGroup.setCreatedBy(RandomTestUtil.nextInt());

		histCompanyGroup.setActionDate(RandomTestUtil.nextDate());

		histCompanyGroup.setCompanyGroupDescription(RandomTestUtil.randomString());

		histCompanyGroup.setModifiedBy(RandomTestUtil.nextInt());

		histCompanyGroup.setModifiedDate(RandomTestUtil.nextDate());

		histCompanyGroup.setCompanyGroupName(RandomTestUtil.randomString());

		histCompanyGroup.setCompanyFilter(RandomTestUtil.randomString());

		_histCompanyGroups.add(_persistence.update(histCompanyGroup));

		return histCompanyGroup;
	}

	private List<HistCompanyGroup> _histCompanyGroups = new ArrayList<HistCompanyGroup>();
	private HistCompanyGroupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}