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

import com.stpl.app.exception.NoSuchProjectionMasterException;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ProjectionMasterPersistence;
import com.stpl.app.service.persistence.ProjectionMasterUtil;

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
public class ProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ProjectionMaster> iterator = _projectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionMaster projectionMaster = _persistence.create(pk);

		Assert.assertNotNull(projectionMaster);

		Assert.assertEquals(projectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		_persistence.remove(newProjectionMaster);

		ProjectionMaster existingProjectionMaster = _persistence.fetchByPrimaryKey(newProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionMaster newProjectionMaster = _persistence.create(pk);

		newProjectionMaster.setProductHierarchyLevel(RandomTestUtil.nextInt());

		newProjectionMaster.setSaveFlag(RandomTestUtil.randomBoolean());

		newProjectionMaster.setProjectionName(RandomTestUtil.randomString());

		newProjectionMaster.setToDate(RandomTestUtil.nextDate());

		newProjectionMaster.setForecastingType(RandomTestUtil.randomString());

		newProjectionMaster.setProductHierVersionNo(RandomTestUtil.nextInt());

		newProjectionMaster.setCustomerHierVersionNo(RandomTestUtil.nextInt());

		newProjectionMaster.setModifiedDate(RandomTestUtil.nextDate());

		newProjectionMaster.setCustomerHierarchyLevel(RandomTestUtil.nextInt());

		newProjectionMaster.setFromDate(RandomTestUtil.nextDate());

		newProjectionMaster.setProductHierarchySid(RandomTestUtil.randomString());

		newProjectionMaster.setCreatedDate(RandomTestUtil.nextDate());

		newProjectionMaster.setCreatedBy(RandomTestUtil.nextInt());

		newProjectionMaster.setCustomerHierarchySid(RandomTestUtil.randomString());

		newProjectionMaster.setCompanyGroupSid(RandomTestUtil.randomString());

		newProjectionMaster.setBrandType(RandomTestUtil.randomBoolean());

		newProjectionMaster.setModifiedBy(RandomTestUtil.nextInt());

		newProjectionMaster.setProjectionDescription(RandomTestUtil.randomString());

		newProjectionMaster.setIsApproved(RandomTestUtil.randomString());

		newProjectionMaster.setItemGroupSid(RandomTestUtil.randomString());

		newProjectionMaster.setCompanyMasterSid(RandomTestUtil.randomString());

		newProjectionMaster.setCustomerHierarchyInnerLevel(RandomTestUtil.nextInt());

		newProjectionMaster.setProductHierarchyInnerLevel(RandomTestUtil.nextInt());

		newProjectionMaster.setCustRelationshipBuilderSid(RandomTestUtil.randomString());

		newProjectionMaster.setProdRelationshipBuilderSid(RandomTestUtil.randomString());

		newProjectionMaster.setDiscountType(RandomTestUtil.nextInt());

		newProjectionMaster.setBusinessUnit(RandomTestUtil.nextInt());

		newProjectionMaster.setDeductionHierarchySid(RandomTestUtil.randomString());

		newProjectionMaster.setDedRelationshipBuilderSid(RandomTestUtil.randomString());

		newProjectionMaster.setProjectionCustVersionNo(RandomTestUtil.nextInt());

		newProjectionMaster.setProjectionProdVersionNo(RandomTestUtil.nextInt());

		newProjectionMaster.setForecastEligibleDate(RandomTestUtil.nextDate());

		_projectionMasters.add(_persistence.update(newProjectionMaster));

		ProjectionMaster existingProjectionMaster = _persistence.findByPrimaryKey(newProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingProjectionMaster.getProductHierarchyLevel(),
			newProjectionMaster.getProductHierarchyLevel());
		Assert.assertEquals(existingProjectionMaster.getSaveFlag(),
			newProjectionMaster.getSaveFlag());
		Assert.assertEquals(existingProjectionMaster.getProjectionName(),
			newProjectionMaster.getProjectionName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionMaster.getToDate()),
			Time.getShortTimestamp(newProjectionMaster.getToDate()));
		Assert.assertEquals(existingProjectionMaster.getProjectionMasterSid(),
			newProjectionMaster.getProjectionMasterSid());
		Assert.assertEquals(existingProjectionMaster.getForecastingType(),
			newProjectionMaster.getForecastingType());
		Assert.assertEquals(existingProjectionMaster.getProductHierVersionNo(),
			newProjectionMaster.getProductHierVersionNo());
		Assert.assertEquals(existingProjectionMaster.getCustomerHierVersionNo(),
			newProjectionMaster.getCustomerHierVersionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionMaster.getModifiedDate()),
			Time.getShortTimestamp(newProjectionMaster.getModifiedDate()));
		Assert.assertEquals(existingProjectionMaster.getCustomerHierarchyLevel(),
			newProjectionMaster.getCustomerHierarchyLevel());
		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionMaster.getFromDate()),
			Time.getShortTimestamp(newProjectionMaster.getFromDate()));
		Assert.assertEquals(existingProjectionMaster.getProductHierarchySid(),
			newProjectionMaster.getProductHierarchySid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionMaster.getCreatedDate()),
			Time.getShortTimestamp(newProjectionMaster.getCreatedDate()));
		Assert.assertEquals(existingProjectionMaster.getCreatedBy(),
			newProjectionMaster.getCreatedBy());
		Assert.assertEquals(existingProjectionMaster.getCustomerHierarchySid(),
			newProjectionMaster.getCustomerHierarchySid());
		Assert.assertEquals(existingProjectionMaster.getCompanyGroupSid(),
			newProjectionMaster.getCompanyGroupSid());
		Assert.assertEquals(existingProjectionMaster.getBrandType(),
			newProjectionMaster.getBrandType());
		Assert.assertEquals(existingProjectionMaster.getModifiedBy(),
			newProjectionMaster.getModifiedBy());
		Assert.assertEquals(existingProjectionMaster.getProjectionDescription(),
			newProjectionMaster.getProjectionDescription());
		Assert.assertEquals(existingProjectionMaster.getIsApproved(),
			newProjectionMaster.getIsApproved());
		Assert.assertEquals(existingProjectionMaster.getItemGroupSid(),
			newProjectionMaster.getItemGroupSid());
		Assert.assertEquals(existingProjectionMaster.getCompanyMasterSid(),
			newProjectionMaster.getCompanyMasterSid());
		Assert.assertEquals(existingProjectionMaster.getCustomerHierarchyInnerLevel(),
			newProjectionMaster.getCustomerHierarchyInnerLevel());
		Assert.assertEquals(existingProjectionMaster.getProductHierarchyInnerLevel(),
			newProjectionMaster.getProductHierarchyInnerLevel());
		Assert.assertEquals(existingProjectionMaster.getCustRelationshipBuilderSid(),
			newProjectionMaster.getCustRelationshipBuilderSid());
		Assert.assertEquals(existingProjectionMaster.getProdRelationshipBuilderSid(),
			newProjectionMaster.getProdRelationshipBuilderSid());
		Assert.assertEquals(existingProjectionMaster.getDiscountType(),
			newProjectionMaster.getDiscountType());
		Assert.assertEquals(existingProjectionMaster.getBusinessUnit(),
			newProjectionMaster.getBusinessUnit());
		Assert.assertEquals(existingProjectionMaster.getDeductionHierarchySid(),
			newProjectionMaster.getDeductionHierarchySid());
		Assert.assertEquals(existingProjectionMaster.getDedRelationshipBuilderSid(),
			newProjectionMaster.getDedRelationshipBuilderSid());
		Assert.assertEquals(existingProjectionMaster.getProjectionCustVersionNo(),
			newProjectionMaster.getProjectionCustVersionNo());
		Assert.assertEquals(existingProjectionMaster.getProjectionProdVersionNo(),
			newProjectionMaster.getProjectionProdVersionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingProjectionMaster.getForecastEligibleDate()),
			Time.getShortTimestamp(
				newProjectionMaster.getForecastEligibleDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		ProjectionMaster existingProjectionMaster = _persistence.findByPrimaryKey(newProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingProjectionMaster, newProjectionMaster);
	}

	@Test(expected = NoSuchProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ProjectionMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PROJECTION_MASTER",
			"productHierarchyLevel", true, "saveFlag", true, "projectionName",
			true, "toDate", true, "projectionMasterSid", true,
			"forecastingType", true, "productHierVersionNo", true,
			"customerHierVersionNo", true, "modifiedDate", true,
			"customerHierarchyLevel", true, "fromDate", true,
			"productHierarchySid", true, "createdDate", true, "createdBy",
			true, "customerHierarchySid", true, "companyGroupSid", true,
			"brandType", true, "modifiedBy", true, "projectionDescription",
			true, "isApproved", true, "itemGroupSid", true, "companyMasterSid",
			true, "customerHierarchyInnerLevel", true,
			"productHierarchyInnerLevel", true, "custRelationshipBuilderSid",
			true, "prodRelationshipBuilderSid", true, "discountType", true,
			"businessUnit", true, "deductionHierarchySid", true,
			"dedRelationshipBuilderSid", true, "projectionCustVersionNo", true,
			"projectionProdVersionNo", true, "forecastEligibleDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		ProjectionMaster existingProjectionMaster = _persistence.fetchByPrimaryKey(newProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingProjectionMaster, newProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionMaster missingProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ProjectionMaster newProjectionMaster1 = addProjectionMaster();
		ProjectionMaster newProjectionMaster2 = addProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newProjectionMaster2.getPrimaryKey());

		Map<Serializable, ProjectionMaster> projectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, projectionMasters.size());
		Assert.assertEquals(newProjectionMaster1,
			projectionMasters.get(newProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newProjectionMaster2,
			projectionMasters.get(newProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ProjectionMaster> projectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ProjectionMaster> projectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionMasters.size());
		Assert.assertEquals(newProjectionMaster,
			projectionMasters.get(newProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ProjectionMaster> projectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(projectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newProjectionMaster.getPrimaryKey());

		Map<Serializable, ProjectionMaster> projectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, projectionMasters.size());
		Assert.assertEquals(newProjectionMaster,
			projectionMasters.get(newProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ProjectionMaster>() {
				@Override
				public void performAction(ProjectionMaster projectionMaster) {
					Assert.assertNotNull(projectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionMasterSid",
				newProjectionMaster.getProjectionMasterSid()));

		List<ProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ProjectionMaster existingProjectionMaster = result.get(0);

		Assert.assertEquals(existingProjectionMaster, newProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionMasterSid",
				RandomTestUtil.nextInt()));

		List<ProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ProjectionMaster newProjectionMaster = addProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionMasterSid"));

		Object newProjectionMasterSid = newProjectionMaster.getProjectionMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionMasterSid",
				new Object[] { newProjectionMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionMasterSid = result.get(0);

		Assert.assertEquals(existingProjectionMasterSid, newProjectionMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ProjectionMaster addProjectionMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ProjectionMaster projectionMaster = _persistence.create(pk);

		projectionMaster.setProductHierarchyLevel(RandomTestUtil.nextInt());

		projectionMaster.setSaveFlag(RandomTestUtil.randomBoolean());

		projectionMaster.setProjectionName(RandomTestUtil.randomString());

		projectionMaster.setToDate(RandomTestUtil.nextDate());

		projectionMaster.setForecastingType(RandomTestUtil.randomString());

		projectionMaster.setProductHierVersionNo(RandomTestUtil.nextInt());

		projectionMaster.setCustomerHierVersionNo(RandomTestUtil.nextInt());

		projectionMaster.setModifiedDate(RandomTestUtil.nextDate());

		projectionMaster.setCustomerHierarchyLevel(RandomTestUtil.nextInt());

		projectionMaster.setFromDate(RandomTestUtil.nextDate());

		projectionMaster.setProductHierarchySid(RandomTestUtil.randomString());

		projectionMaster.setCreatedDate(RandomTestUtil.nextDate());

		projectionMaster.setCreatedBy(RandomTestUtil.nextInt());

		projectionMaster.setCustomerHierarchySid(RandomTestUtil.randomString());

		projectionMaster.setCompanyGroupSid(RandomTestUtil.randomString());

		projectionMaster.setBrandType(RandomTestUtil.randomBoolean());

		projectionMaster.setModifiedBy(RandomTestUtil.nextInt());

		projectionMaster.setProjectionDescription(RandomTestUtil.randomString());

		projectionMaster.setIsApproved(RandomTestUtil.randomString());

		projectionMaster.setItemGroupSid(RandomTestUtil.randomString());

		projectionMaster.setCompanyMasterSid(RandomTestUtil.randomString());

		projectionMaster.setCustomerHierarchyInnerLevel(RandomTestUtil.nextInt());

		projectionMaster.setProductHierarchyInnerLevel(RandomTestUtil.nextInt());

		projectionMaster.setCustRelationshipBuilderSid(RandomTestUtil.randomString());

		projectionMaster.setProdRelationshipBuilderSid(RandomTestUtil.randomString());

		projectionMaster.setDiscountType(RandomTestUtil.nextInt());

		projectionMaster.setBusinessUnit(RandomTestUtil.nextInt());

		projectionMaster.setDeductionHierarchySid(RandomTestUtil.randomString());

		projectionMaster.setDedRelationshipBuilderSid(RandomTestUtil.randomString());

		projectionMaster.setProjectionCustVersionNo(RandomTestUtil.nextInt());

		projectionMaster.setProjectionProdVersionNo(RandomTestUtil.nextInt());

		projectionMaster.setForecastEligibleDate(RandomTestUtil.nextDate());

		_projectionMasters.add(_persistence.update(projectionMaster));

		return projectionMaster;
	}

	private List<ProjectionMaster> _projectionMasters = new ArrayList<ProjectionMaster>();
	private ProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}