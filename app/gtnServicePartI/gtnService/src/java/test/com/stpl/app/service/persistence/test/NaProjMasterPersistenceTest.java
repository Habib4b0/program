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

import com.stpl.app.exception.NoSuchNaProjMasterException;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.NaProjMasterPersistence;
import com.stpl.app.service.persistence.NaProjMasterUtil;

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
public class NaProjMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NaProjMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NaProjMaster> iterator = _naProjMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjMaster naProjMaster = _persistence.create(pk);

		Assert.assertNotNull(naProjMaster);

		Assert.assertEquals(naProjMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		_persistence.remove(newNaProjMaster);

		NaProjMaster existingNaProjMaster = _persistence.fetchByPrimaryKey(newNaProjMaster.getPrimaryKey());

		Assert.assertNull(existingNaProjMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNaProjMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjMaster newNaProjMaster = _persistence.create(pk);

		newNaProjMaster.setNaProjName(RandomTestUtil.randomString());

		newNaProjMaster.setCreatedDate(RandomTestUtil.nextDate());

		newNaProjMaster.setCreatedBy(RandomTestUtil.nextInt());

		newNaProjMaster.setSaveFlag(RandomTestUtil.randomBoolean());

		newNaProjMaster.setModifiedBy(RandomTestUtil.nextInt());

		newNaProjMaster.setModifiedDate(RandomTestUtil.nextDate());

		newNaProjMaster.setItemGroupSid(RandomTestUtil.nextInt());

		newNaProjMaster.setTherapeuticClass(RandomTestUtil.nextInt());

		newNaProjMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		newNaProjMaster.setBusinessUnit(RandomTestUtil.nextInt());

		_naProjMasters.add(_persistence.update(newNaProjMaster));

		NaProjMaster existingNaProjMaster = _persistence.findByPrimaryKey(newNaProjMaster.getPrimaryKey());

		Assert.assertEquals(existingNaProjMaster.getNaProjName(),
			newNaProjMaster.getNaProjName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingNaProjMaster.getCreatedDate()),
			Time.getShortTimestamp(newNaProjMaster.getCreatedDate()));
		Assert.assertEquals(existingNaProjMaster.getCreatedBy(),
			newNaProjMaster.getCreatedBy());
		Assert.assertEquals(existingNaProjMaster.getSaveFlag(),
			newNaProjMaster.getSaveFlag());
		Assert.assertEquals(existingNaProjMaster.getModifiedBy(),
			newNaProjMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingNaProjMaster.getModifiedDate()),
			Time.getShortTimestamp(newNaProjMaster.getModifiedDate()));
		Assert.assertEquals(existingNaProjMaster.getNaProjMasterSid(),
			newNaProjMaster.getNaProjMasterSid());
		Assert.assertEquals(existingNaProjMaster.getItemGroupSid(),
			newNaProjMaster.getItemGroupSid());
		Assert.assertEquals(existingNaProjMaster.getTherapeuticClass(),
			newNaProjMaster.getTherapeuticClass());
		Assert.assertEquals(existingNaProjMaster.getCompanyMasterSid(),
			newNaProjMaster.getCompanyMasterSid());
		Assert.assertEquals(existingNaProjMaster.getBusinessUnit(),
			newNaProjMaster.getBusinessUnit());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		NaProjMaster existingNaProjMaster = _persistence.findByPrimaryKey(newNaProjMaster.getPrimaryKey());

		Assert.assertEquals(existingNaProjMaster, newNaProjMaster);
	}

	@Test(expected = NoSuchNaProjMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NaProjMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NA_PROJ_MASTER",
			"naProjName", true, "createdDate", true, "createdBy", true,
			"saveFlag", true, "modifiedBy", true, "modifiedDate", true,
			"naProjMasterSid", true, "itemGroupSid", true, "therapeuticClass",
			true, "companyMasterSid", true, "businessUnit", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		NaProjMaster existingNaProjMaster = _persistence.fetchByPrimaryKey(newNaProjMaster.getPrimaryKey());

		Assert.assertEquals(existingNaProjMaster, newNaProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjMaster missingNaProjMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNaProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NaProjMaster newNaProjMaster1 = addNaProjMaster();
		NaProjMaster newNaProjMaster2 = addNaProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjMaster1.getPrimaryKey());
		primaryKeys.add(newNaProjMaster2.getPrimaryKey());

		Map<Serializable, NaProjMaster> naProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, naProjMasters.size());
		Assert.assertEquals(newNaProjMaster1,
			naProjMasters.get(newNaProjMaster1.getPrimaryKey()));
		Assert.assertEquals(newNaProjMaster2,
			naProjMasters.get(newNaProjMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NaProjMaster> naProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(naProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NaProjMaster> naProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, naProjMasters.size());
		Assert.assertEquals(newNaProjMaster,
			naProjMasters.get(newNaProjMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NaProjMaster> naProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(naProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjMaster.getPrimaryKey());

		Map<Serializable, NaProjMaster> naProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, naProjMasters.size());
		Assert.assertEquals(newNaProjMaster,
			naProjMasters.get(newNaProjMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NaProjMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NaProjMaster>() {
				@Override
				public void performAction(NaProjMaster naProjMaster) {
					Assert.assertNotNull(naProjMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("naProjMasterSid",
				newNaProjMaster.getNaProjMasterSid()));

		List<NaProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NaProjMaster existingNaProjMaster = result.get(0);

		Assert.assertEquals(existingNaProjMaster, newNaProjMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("naProjMasterSid",
				RandomTestUtil.nextInt()));

		List<NaProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NaProjMaster newNaProjMaster = addNaProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"naProjMasterSid"));

		Object newNaProjMasterSid = newNaProjMaster.getNaProjMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("naProjMasterSid",
				new Object[] { newNaProjMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNaProjMasterSid = result.get(0);

		Assert.assertEquals(existingNaProjMasterSid, newNaProjMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"naProjMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("naProjMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NaProjMaster addNaProjMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjMaster naProjMaster = _persistence.create(pk);

		naProjMaster.setNaProjName(RandomTestUtil.randomString());

		naProjMaster.setCreatedDate(RandomTestUtil.nextDate());

		naProjMaster.setCreatedBy(RandomTestUtil.nextInt());

		naProjMaster.setSaveFlag(RandomTestUtil.randomBoolean());

		naProjMaster.setModifiedBy(RandomTestUtil.nextInt());

		naProjMaster.setModifiedDate(RandomTestUtil.nextDate());

		naProjMaster.setItemGroupSid(RandomTestUtil.nextInt());

		naProjMaster.setTherapeuticClass(RandomTestUtil.nextInt());

		naProjMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		naProjMaster.setBusinessUnit(RandomTestUtil.nextInt());

		_naProjMasters.add(_persistence.update(naProjMaster));

		return naProjMaster;
	}

	private List<NaProjMaster> _naProjMasters = new ArrayList<NaProjMaster>();
	private NaProjMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}