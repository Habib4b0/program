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

import com.stpl.app.parttwo.exception.NoSuchCffMasterException;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.service.CffMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffMasterPersistence;
import com.stpl.app.parttwo.service.persistence.CffMasterUtil;

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
public class CffMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffMaster> iterator = _cffMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffMaster cffMaster = _persistence.create(pk);

		Assert.assertNotNull(cffMaster);

		Assert.assertEquals(cffMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffMaster newCffMaster = addCffMaster();

		_persistence.remove(newCffMaster);

		CffMaster existingCffMaster = _persistence.fetchByPrimaryKey(newCffMaster.getPrimaryKey());

		Assert.assertNull(existingCffMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffMaster newCffMaster = _persistence.create(pk);

		newCffMaster.setProductHierarchyLevel(RandomTestUtil.nextInt());

		newCffMaster.setActiveFromDate(RandomTestUtil.nextDate());

		newCffMaster.setCffType(RandomTestUtil.nextInt());

		newCffMaster.setCffOfficial(RandomTestUtil.randomBoolean());

		newCffMaster.setProductHierVersionNo(RandomTestUtil.nextInt());

		newCffMaster.setActiveToDate(RandomTestUtil.nextDate());

		newCffMaster.setCustomerHierVersionNo(RandomTestUtil.nextInt());

		newCffMaster.setModifiedDate(RandomTestUtil.nextDate());

		newCffMaster.setCustomerHierarchyLevel(RandomTestUtil.nextInt());

		newCffMaster.setProductHierarchySid(RandomTestUtil.nextInt());

		newCffMaster.setCffName(RandomTestUtil.randomString());

		newCffMaster.setCustomerHierarchyInnerLevel(RandomTestUtil.nextInt());

		newCffMaster.setCreatedDate(RandomTestUtil.nextDate());

		newCffMaster.setCreatedBy(RandomTestUtil.nextInt());

		newCffMaster.setCustomerHierarchySid(RandomTestUtil.nextInt());

		newCffMaster.setCompanyGroupSid(RandomTestUtil.nextInt());

		newCffMaster.setProdRelationshipBuilderSid(RandomTestUtil.nextInt());

		newCffMaster.setModifiedBy(RandomTestUtil.nextInt());

		newCffMaster.setInboundStatus(RandomTestUtil.randomString());

		newCffMaster.setProductHierarchyInnerLevel(RandomTestUtil.nextInt());

		newCffMaster.setItemGroupSid(RandomTestUtil.nextInt());

		newCffMaster.setCustRelationshipBuilderSid(RandomTestUtil.nextInt());

		newCffMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		_cffMasters.add(_persistence.update(newCffMaster));

		CffMaster existingCffMaster = _persistence.findByPrimaryKey(newCffMaster.getPrimaryKey());

		Assert.assertEquals(existingCffMaster.getProductHierarchyLevel(),
			newCffMaster.getProductHierarchyLevel());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffMaster.getActiveFromDate()),
			Time.getShortTimestamp(newCffMaster.getActiveFromDate()));
		Assert.assertEquals(existingCffMaster.getCffType(),
			newCffMaster.getCffType());
		Assert.assertEquals(existingCffMaster.getCffOfficial(),
			newCffMaster.getCffOfficial());
		Assert.assertEquals(existingCffMaster.getCffMasterSid(),
			newCffMaster.getCffMasterSid());
		Assert.assertEquals(existingCffMaster.getProductHierVersionNo(),
			newCffMaster.getProductHierVersionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffMaster.getActiveToDate()),
			Time.getShortTimestamp(newCffMaster.getActiveToDate()));
		Assert.assertEquals(existingCffMaster.getCustomerHierVersionNo(),
			newCffMaster.getCustomerHierVersionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffMaster.getModifiedDate()),
			Time.getShortTimestamp(newCffMaster.getModifiedDate()));
		Assert.assertEquals(existingCffMaster.getCustomerHierarchyLevel(),
			newCffMaster.getCustomerHierarchyLevel());
		Assert.assertEquals(existingCffMaster.getProductHierarchySid(),
			newCffMaster.getProductHierarchySid());
		Assert.assertEquals(existingCffMaster.getCffName(),
			newCffMaster.getCffName());
		Assert.assertEquals(existingCffMaster.getCustomerHierarchyInnerLevel(),
			newCffMaster.getCustomerHierarchyInnerLevel());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffMaster.getCreatedDate()),
			Time.getShortTimestamp(newCffMaster.getCreatedDate()));
		Assert.assertEquals(existingCffMaster.getCreatedBy(),
			newCffMaster.getCreatedBy());
		Assert.assertEquals(existingCffMaster.getCustomerHierarchySid(),
			newCffMaster.getCustomerHierarchySid());
		Assert.assertEquals(existingCffMaster.getCompanyGroupSid(),
			newCffMaster.getCompanyGroupSid());
		Assert.assertEquals(existingCffMaster.getProdRelationshipBuilderSid(),
			newCffMaster.getProdRelationshipBuilderSid());
		Assert.assertEquals(existingCffMaster.getModifiedBy(),
			newCffMaster.getModifiedBy());
		Assert.assertEquals(existingCffMaster.getInboundStatus(),
			newCffMaster.getInboundStatus());
		Assert.assertEquals(existingCffMaster.getProductHierarchyInnerLevel(),
			newCffMaster.getProductHierarchyInnerLevel());
		Assert.assertEquals(existingCffMaster.getItemGroupSid(),
			newCffMaster.getItemGroupSid());
		Assert.assertEquals(existingCffMaster.getCustRelationshipBuilderSid(),
			newCffMaster.getCustRelationshipBuilderSid());
		Assert.assertEquals(existingCffMaster.getCompanyMasterSid(),
			newCffMaster.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffMaster newCffMaster = addCffMaster();

		CffMaster existingCffMaster = _persistence.findByPrimaryKey(newCffMaster.getPrimaryKey());

		Assert.assertEquals(existingCffMaster, newCffMaster);
	}

	@Test(expected = NoSuchCffMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_MASTER",
			"productHierarchyLevel", true, "activeFromDate", true, "cffType",
			true, "cffOfficial", true, "cffMasterSid", true,
			"productHierVersionNo", true, "activeToDate", true,
			"customerHierVersionNo", true, "modifiedDate", true,
			"customerHierarchyLevel", true, "productHierarchySid", true,
			"cffName", true, "customerHierarchyInnerLevel", true,
			"createdDate", true, "createdBy", true, "customerHierarchySid",
			true, "companyGroupSid", true, "prodRelationshipBuilderSid", true,
			"modifiedBy", true, "inboundStatus", true,
			"productHierarchyInnerLevel", true, "itemGroupSid", true,
			"custRelationshipBuilderSid", true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffMaster newCffMaster = addCffMaster();

		CffMaster existingCffMaster = _persistence.fetchByPrimaryKey(newCffMaster.getPrimaryKey());

		Assert.assertEquals(existingCffMaster, newCffMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffMaster missingCffMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffMaster newCffMaster1 = addCffMaster();
		CffMaster newCffMaster2 = addCffMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffMaster1.getPrimaryKey());
		primaryKeys.add(newCffMaster2.getPrimaryKey());

		Map<Serializable, CffMaster> cffMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffMasters.size());
		Assert.assertEquals(newCffMaster1,
			cffMasters.get(newCffMaster1.getPrimaryKey()));
		Assert.assertEquals(newCffMaster2,
			cffMasters.get(newCffMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffMaster> cffMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffMaster newCffMaster = addCffMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffMaster> cffMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffMasters.size());
		Assert.assertEquals(newCffMaster,
			cffMasters.get(newCffMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffMaster> cffMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffMaster newCffMaster = addCffMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffMaster.getPrimaryKey());

		Map<Serializable, CffMaster> cffMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffMasters.size());
		Assert.assertEquals(newCffMaster,
			cffMasters.get(newCffMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffMaster>() {
				@Override
				public void performAction(CffMaster cffMaster) {
					Assert.assertNotNull(cffMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffMaster newCffMaster = addCffMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid",
				newCffMaster.getCffMasterSid()));

		List<CffMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffMaster existingCffMaster = result.get(0);

		Assert.assertEquals(existingCffMaster, newCffMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid",
				RandomTestUtil.nextInt()));

		List<CffMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffMaster newCffMaster = addCffMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffMasterSid"));

		Object newCffMasterSid = newCffMaster.getCffMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffMasterSid",
				new Object[] { newCffMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffMasterSid = result.get(0);

		Assert.assertEquals(existingCffMasterSid, newCffMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffMaster addCffMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffMaster cffMaster = _persistence.create(pk);

		cffMaster.setProductHierarchyLevel(RandomTestUtil.nextInt());

		cffMaster.setActiveFromDate(RandomTestUtil.nextDate());

		cffMaster.setCffType(RandomTestUtil.nextInt());

		cffMaster.setCffOfficial(RandomTestUtil.randomBoolean());

		cffMaster.setProductHierVersionNo(RandomTestUtil.nextInt());

		cffMaster.setActiveToDate(RandomTestUtil.nextDate());

		cffMaster.setCustomerHierVersionNo(RandomTestUtil.nextInt());

		cffMaster.setModifiedDate(RandomTestUtil.nextDate());

		cffMaster.setCustomerHierarchyLevel(RandomTestUtil.nextInt());

		cffMaster.setProductHierarchySid(RandomTestUtil.nextInt());

		cffMaster.setCffName(RandomTestUtil.randomString());

		cffMaster.setCustomerHierarchyInnerLevel(RandomTestUtil.nextInt());

		cffMaster.setCreatedDate(RandomTestUtil.nextDate());

		cffMaster.setCreatedBy(RandomTestUtil.nextInt());

		cffMaster.setCustomerHierarchySid(RandomTestUtil.nextInt());

		cffMaster.setCompanyGroupSid(RandomTestUtil.nextInt());

		cffMaster.setProdRelationshipBuilderSid(RandomTestUtil.nextInt());

		cffMaster.setModifiedBy(RandomTestUtil.nextInt());

		cffMaster.setInboundStatus(RandomTestUtil.randomString());

		cffMaster.setProductHierarchyInnerLevel(RandomTestUtil.nextInt());

		cffMaster.setItemGroupSid(RandomTestUtil.nextInt());

		cffMaster.setCustRelationshipBuilderSid(RandomTestUtil.nextInt());

		cffMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		_cffMasters.add(_persistence.update(cffMaster));

		return cffMaster;
	}

	private List<CffMaster> _cffMasters = new ArrayList<CffMaster>();
	private CffMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}