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

import com.stpl.app.exception.NoSuchCompanyGroupException;
import com.stpl.app.model.CompanyGroup;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyGroupPersistence;
import com.stpl.app.service.persistence.CompanyGroupUtil;

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
public class CompanyGroupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyGroupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyGroup> iterator = _companyGroups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroup companyGroup = _persistence.create(pk);

		Assert.assertNotNull(companyGroup);

		Assert.assertEquals(companyGroup.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		_persistence.remove(newCompanyGroup);

		CompanyGroup existingCompanyGroup = _persistence.fetchByPrimaryKey(newCompanyGroup.getPrimaryKey());

		Assert.assertNull(existingCompanyGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroup newCompanyGroup = _persistence.create(pk);

		newCompanyGroup.setCompanyGroupNo(RandomTestUtil.randomString());

		newCompanyGroup.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyGroup.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyGroup.setCompanyFilter(RandomTestUtil.randomString());

		newCompanyGroup.setCompanyGroupDescription(RandomTestUtil.randomString());

		newCompanyGroup.setVersionNo(RandomTestUtil.nextInt());

		newCompanyGroup.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyGroup.setModifiedDate(RandomTestUtil.nextDate());

		newCompanyGroup.setCompanyGroupName(RandomTestUtil.randomString());

		_companyGroups.add(_persistence.update(newCompanyGroup));

		CompanyGroup existingCompanyGroup = _persistence.findByPrimaryKey(newCompanyGroup.getPrimaryKey());

		Assert.assertEquals(existingCompanyGroup.getCompanyGroupNo(),
			newCompanyGroup.getCompanyGroupNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyGroup.getCreatedDate()),
			Time.getShortTimestamp(newCompanyGroup.getCreatedDate()));
		Assert.assertEquals(existingCompanyGroup.getCreatedBy(),
			newCompanyGroup.getCreatedBy());
		Assert.assertEquals(existingCompanyGroup.getCompanyFilter(),
			newCompanyGroup.getCompanyFilter());
		Assert.assertEquals(existingCompanyGroup.getCompanyGroupSid(),
			newCompanyGroup.getCompanyGroupSid());
		Assert.assertEquals(existingCompanyGroup.getCompanyGroupDescription(),
			newCompanyGroup.getCompanyGroupDescription());
		Assert.assertEquals(existingCompanyGroup.getVersionNo(),
			newCompanyGroup.getVersionNo());
		Assert.assertEquals(existingCompanyGroup.getModifiedBy(),
			newCompanyGroup.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyGroup.getModifiedDate()),
			Time.getShortTimestamp(newCompanyGroup.getModifiedDate()));
		Assert.assertEquals(existingCompanyGroup.getCompanyGroupName(),
			newCompanyGroup.getCompanyGroupName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		CompanyGroup existingCompanyGroup = _persistence.findByPrimaryKey(newCompanyGroup.getPrimaryKey());

		Assert.assertEquals(existingCompanyGroup, newCompanyGroup);
	}

	@Test(expected = NoSuchCompanyGroupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyGroup> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_GROUP",
			"companyGroupNo", true, "createdDate", true, "createdBy", true,
			"companyFilter", true, "companyGroupSid", true,
			"companyGroupDescription", true, "versionNo", true, "modifiedBy",
			true, "modifiedDate", true, "companyGroupName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		CompanyGroup existingCompanyGroup = _persistence.fetchByPrimaryKey(newCompanyGroup.getPrimaryKey());

		Assert.assertEquals(existingCompanyGroup, newCompanyGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroup missingCompanyGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyGroup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyGroup newCompanyGroup1 = addCompanyGroup();
		CompanyGroup newCompanyGroup2 = addCompanyGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyGroup1.getPrimaryKey());
		primaryKeys.add(newCompanyGroup2.getPrimaryKey());

		Map<Serializable, CompanyGroup> companyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyGroups.size());
		Assert.assertEquals(newCompanyGroup1,
			companyGroups.get(newCompanyGroup1.getPrimaryKey()));
		Assert.assertEquals(newCompanyGroup2,
			companyGroups.get(newCompanyGroup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyGroup> companyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyGroup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyGroup> companyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyGroups.size());
		Assert.assertEquals(newCompanyGroup,
			companyGroups.get(newCompanyGroup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyGroup> companyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyGroup.getPrimaryKey());

		Map<Serializable, CompanyGroup> companyGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyGroups.size());
		Assert.assertEquals(newCompanyGroup,
			companyGroups.get(newCompanyGroup.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyGroupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyGroup>() {
				@Override
				public void performAction(CompanyGroup companyGroup) {
					Assert.assertNotNull(companyGroup);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid",
				newCompanyGroup.getCompanyGroupSid()));

		List<CompanyGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyGroup existingCompanyGroup = result.get(0);

		Assert.assertEquals(existingCompanyGroup, newCompanyGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid",
				RandomTestUtil.nextInt()));

		List<CompanyGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyGroup newCompanyGroup = addCompanyGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyGroupSid"));

		Object newCompanyGroupSid = newCompanyGroup.getCompanyGroupSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyGroupSid",
				new Object[] { newCompanyGroupSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyGroupSid = result.get(0);

		Assert.assertEquals(existingCompanyGroupSid, newCompanyGroupSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyGroupSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyGroupSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CompanyGroup addCompanyGroup() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyGroup companyGroup = _persistence.create(pk);

		companyGroup.setCompanyGroupNo(RandomTestUtil.randomString());

		companyGroup.setCreatedDate(RandomTestUtil.nextDate());

		companyGroup.setCreatedBy(RandomTestUtil.nextInt());

		companyGroup.setCompanyFilter(RandomTestUtil.randomString());

		companyGroup.setCompanyGroupDescription(RandomTestUtil.randomString());

		companyGroup.setVersionNo(RandomTestUtil.nextInt());

		companyGroup.setModifiedBy(RandomTestUtil.nextInt());

		companyGroup.setModifiedDate(RandomTestUtil.nextDate());

		companyGroup.setCompanyGroupName(RandomTestUtil.randomString());

		_companyGroups.add(_persistence.update(companyGroup));

		return companyGroup;
	}

	private List<CompanyGroup> _companyGroups = new ArrayList<CompanyGroup>();
	private CompanyGroupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}