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

import com.stpl.app.exception.NoSuchWorkflowProcessInfoException;
import com.stpl.app.model.WorkflowProcessInfo;
import com.stpl.app.service.WorkflowProcessInfoLocalServiceUtil;
import com.stpl.app.service.persistence.WorkflowProcessInfoPersistence;
import com.stpl.app.service.persistence.WorkflowProcessInfoUtil;

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
public class WorkflowProcessInfoPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = WorkflowProcessInfoUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<WorkflowProcessInfo> iterator = _workflowProcessInfos.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProcessInfo workflowProcessInfo = _persistence.create(pk);

		Assert.assertNotNull(workflowProcessInfo);

		Assert.assertEquals(workflowProcessInfo.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		_persistence.remove(newWorkflowProcessInfo);

		WorkflowProcessInfo existingWorkflowProcessInfo = _persistence.fetchByPrimaryKey(newWorkflowProcessInfo.getPrimaryKey());

		Assert.assertNull(existingWorkflowProcessInfo);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addWorkflowProcessInfo();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProcessInfo newWorkflowProcessInfo = _persistence.create(pk);

		newWorkflowProcessInfo.setProcessInstanceId(RandomTestUtil.nextInt());

		newWorkflowProcessInfo.setProjectionMasterSid(RandomTestUtil.nextInt());

		newWorkflowProcessInfo.setAccClosureMasterSid(RandomTestUtil.nextInt());

		newWorkflowProcessInfo.setContractMasterSid(RandomTestUtil.nextInt());

		newWorkflowProcessInfo.setContractStructure(RandomTestUtil.randomString());

		_workflowProcessInfos.add(_persistence.update(newWorkflowProcessInfo));

		WorkflowProcessInfo existingWorkflowProcessInfo = _persistence.findByPrimaryKey(newWorkflowProcessInfo.getPrimaryKey());

		Assert.assertEquals(existingWorkflowProcessInfo.getProcessInstanceId(),
			newWorkflowProcessInfo.getProcessInstanceId());
		Assert.assertEquals(existingWorkflowProcessInfo.getProjectionMasterSid(),
			newWorkflowProcessInfo.getProjectionMasterSid());
		Assert.assertEquals(existingWorkflowProcessInfo.getWorkflowProcessInfoSid(),
			newWorkflowProcessInfo.getWorkflowProcessInfoSid());
		Assert.assertEquals(existingWorkflowProcessInfo.getAccClosureMasterSid(),
			newWorkflowProcessInfo.getAccClosureMasterSid());
		Assert.assertEquals(existingWorkflowProcessInfo.getContractMasterSid(),
			newWorkflowProcessInfo.getContractMasterSid());
		Assert.assertEquals(existingWorkflowProcessInfo.getContractStructure(),
			newWorkflowProcessInfo.getContractStructure());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		WorkflowProcessInfo existingWorkflowProcessInfo = _persistence.findByPrimaryKey(newWorkflowProcessInfo.getPrimaryKey());

		Assert.assertEquals(existingWorkflowProcessInfo, newWorkflowProcessInfo);
	}

	@Test(expected = NoSuchWorkflowProcessInfoException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<WorkflowProcessInfo> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("WORKFLOW_PROCESS_INFO",
			"processInstanceId", true, "projectionMasterSid", true,
			"workflowProcessInfoSid", true, "accClosureMasterSid", true,
			"contractMasterSid", true, "contractStructure", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		WorkflowProcessInfo existingWorkflowProcessInfo = _persistence.fetchByPrimaryKey(newWorkflowProcessInfo.getPrimaryKey());

		Assert.assertEquals(existingWorkflowProcessInfo, newWorkflowProcessInfo);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProcessInfo missingWorkflowProcessInfo = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingWorkflowProcessInfo);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo1 = addWorkflowProcessInfo();
		WorkflowProcessInfo newWorkflowProcessInfo2 = addWorkflowProcessInfo();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowProcessInfo1.getPrimaryKey());
		primaryKeys.add(newWorkflowProcessInfo2.getPrimaryKey());

		Map<Serializable, WorkflowProcessInfo> workflowProcessInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, workflowProcessInfos.size());
		Assert.assertEquals(newWorkflowProcessInfo1,
			workflowProcessInfos.get(newWorkflowProcessInfo1.getPrimaryKey()));
		Assert.assertEquals(newWorkflowProcessInfo2,
			workflowProcessInfos.get(newWorkflowProcessInfo2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, WorkflowProcessInfo> workflowProcessInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(workflowProcessInfos.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowProcessInfo.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, WorkflowProcessInfo> workflowProcessInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, workflowProcessInfos.size());
		Assert.assertEquals(newWorkflowProcessInfo,
			workflowProcessInfos.get(newWorkflowProcessInfo.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, WorkflowProcessInfo> workflowProcessInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(workflowProcessInfos.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowProcessInfo.getPrimaryKey());

		Map<Serializable, WorkflowProcessInfo> workflowProcessInfos = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, workflowProcessInfos.size());
		Assert.assertEquals(newWorkflowProcessInfo,
			workflowProcessInfos.get(newWorkflowProcessInfo.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = WorkflowProcessInfoLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WorkflowProcessInfo>() {
				@Override
				public void performAction(
					WorkflowProcessInfo workflowProcessInfo) {
					Assert.assertNotNull(workflowProcessInfo);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProcessInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowProcessInfoSid",
				newWorkflowProcessInfo.getWorkflowProcessInfoSid()));

		List<WorkflowProcessInfo> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		WorkflowProcessInfo existingWorkflowProcessInfo = result.get(0);

		Assert.assertEquals(existingWorkflowProcessInfo, newWorkflowProcessInfo);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProcessInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowProcessInfoSid",
				RandomTestUtil.nextInt()));

		List<WorkflowProcessInfo> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		WorkflowProcessInfo newWorkflowProcessInfo = addWorkflowProcessInfo();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProcessInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"workflowProcessInfoSid"));

		Object newWorkflowProcessInfoSid = newWorkflowProcessInfo.getWorkflowProcessInfoSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowProcessInfoSid",
				new Object[] { newWorkflowProcessInfoSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWorkflowProcessInfoSid = result.get(0);

		Assert.assertEquals(existingWorkflowProcessInfoSid,
			newWorkflowProcessInfoSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProcessInfo.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"workflowProcessInfoSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowProcessInfoSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected WorkflowProcessInfo addWorkflowProcessInfo()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProcessInfo workflowProcessInfo = _persistence.create(pk);

		workflowProcessInfo.setProcessInstanceId(RandomTestUtil.nextInt());

		workflowProcessInfo.setProjectionMasterSid(RandomTestUtil.nextInt());

		workflowProcessInfo.setAccClosureMasterSid(RandomTestUtil.nextInt());

		workflowProcessInfo.setContractMasterSid(RandomTestUtil.nextInt());

		workflowProcessInfo.setContractStructure(RandomTestUtil.randomString());

		_workflowProcessInfos.add(_persistence.update(workflowProcessInfo));

		return workflowProcessInfo;
	}

	private List<WorkflowProcessInfo> _workflowProcessInfos = new ArrayList<WorkflowProcessInfo>();
	private WorkflowProcessInfoPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}