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

import com.stpl.app.exception.NoSuchWorkflowProfileException;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.app.service.persistence.WorkflowProfilePersistence;
import com.stpl.app.service.persistence.WorkflowProfileUtil;

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
public class WorkflowProfilePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = WorkflowProfileUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<WorkflowProfile> iterator = _workflowProfiles.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProfile workflowProfile = _persistence.create(pk);

		Assert.assertNotNull(workflowProfile);

		Assert.assertEquals(workflowProfile.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		_persistence.remove(newWorkflowProfile);

		WorkflowProfile existingWorkflowProfile = _persistence.fetchByPrimaryKey(newWorkflowProfile.getPrimaryKey());

		Assert.assertNull(existingWorkflowProfile);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addWorkflowProfile();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProfile newWorkflowProfile = _persistence.create(pk);

		newWorkflowProfile.setStartHour(RandomTestUtil.nextInt());

		newWorkflowProfile.setFrequency(RandomTestUtil.randomString());

		newWorkflowProfile.setProcessName(RandomTestUtil.randomString());

		newWorkflowProfile.setStartMinutes1(RandomTestUtil.nextInt());

		newWorkflowProfile.setEndDate(RandomTestUtil.nextDate());

		newWorkflowProfile.setEmailNotificationFailureCc(RandomTestUtil.randomString());

		newWorkflowProfile.setFailureMailSubject(RandomTestUtil.randomString());

		newWorkflowProfile.setModifiedDate(RandomTestUtil.nextDate());

		newWorkflowProfile.setSchemaName(RandomTestUtil.randomString());

		newWorkflowProfile.setCreatedBy(RandomTestUtil.nextInt());

		newWorkflowProfile.setCreatedDate(RandomTestUtil.nextDate());

		newWorkflowProfile.setScheduleLastRun(RandomTestUtil.nextDate());

		newWorkflowProfile.setEmailNotificationSuccessTo(RandomTestUtil.randomString());

		newWorkflowProfile.setStartMinutes3(RandomTestUtil.nextInt());

		newWorkflowProfile.setStartMinutes2(RandomTestUtil.nextInt());

		newWorkflowProfile.setSuccessMailBody(RandomTestUtil.randomString());

		newWorkflowProfile.setInboundStatus(RandomTestUtil.randomString());

		newWorkflowProfile.setModifiedBy(RandomTestUtil.nextInt());

		newWorkflowProfile.setEmailNotificationSuccessCc(RandomTestUtil.randomString());

		newWorkflowProfile.setEmailNotificationFailureTo(RandomTestUtil.randomString());

		newWorkflowProfile.setFailureMailBody(RandomTestUtil.randomString());

		newWorkflowProfile.setActiveFlag(RandomTestUtil.randomString());

		newWorkflowProfile.setProcessDisplayName(RandomTestUtil.randomString());

		newWorkflowProfile.setStartMinutes(RandomTestUtil.nextInt());

		newWorkflowProfile.setManualLastRun(RandomTestUtil.nextDate());

		newWorkflowProfile.setStartDate(RandomTestUtil.nextDate());

		newWorkflowProfile.setSlaCalendarMasterSid(RandomTestUtil.nextInt());

		newWorkflowProfile.setSuccessMailSubject(RandomTestUtil.randomString());

		newWorkflowProfile.setStartHour3(RandomTestUtil.nextInt());

		newWorkflowProfile.setStartHour2(RandomTestUtil.nextInt());

		newWorkflowProfile.setUserSid(RandomTestUtil.nextInt());

		newWorkflowProfile.setStartHour1(RandomTestUtil.nextInt());

		newWorkflowProfile.setProcessType(RandomTestUtil.nextInt());

		newWorkflowProfile.setScriptName(RandomTestUtil.randomString());

		_workflowProfiles.add(_persistence.update(newWorkflowProfile));

		WorkflowProfile existingWorkflowProfile = _persistence.findByPrimaryKey(newWorkflowProfile.getPrimaryKey());

		Assert.assertEquals(existingWorkflowProfile.getStartHour(),
			newWorkflowProfile.getStartHour());
		Assert.assertEquals(existingWorkflowProfile.getFrequency(),
			newWorkflowProfile.getFrequency());
		Assert.assertEquals(existingWorkflowProfile.getProcessName(),
			newWorkflowProfile.getProcessName());
		Assert.assertEquals(existingWorkflowProfile.getStartMinutes1(),
			newWorkflowProfile.getStartMinutes1());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowProfile.getEndDate()),
			Time.getShortTimestamp(newWorkflowProfile.getEndDate()));
		Assert.assertEquals(existingWorkflowProfile.getEmailNotificationFailureCc(),
			newWorkflowProfile.getEmailNotificationFailureCc());
		Assert.assertEquals(existingWorkflowProfile.getFailureMailSubject(),
			newWorkflowProfile.getFailureMailSubject());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowProfile.getModifiedDate()),
			Time.getShortTimestamp(newWorkflowProfile.getModifiedDate()));
		Assert.assertEquals(existingWorkflowProfile.getSchemaName(),
			newWorkflowProfile.getSchemaName());
		Assert.assertEquals(existingWorkflowProfile.getCreatedBy(),
			newWorkflowProfile.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowProfile.getCreatedDate()),
			Time.getShortTimestamp(newWorkflowProfile.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowProfile.getScheduleLastRun()),
			Time.getShortTimestamp(newWorkflowProfile.getScheduleLastRun()));
		Assert.assertEquals(existingWorkflowProfile.getEmailNotificationSuccessTo(),
			newWorkflowProfile.getEmailNotificationSuccessTo());
		Assert.assertEquals(existingWorkflowProfile.getStartMinutes3(),
			newWorkflowProfile.getStartMinutes3());
		Assert.assertEquals(existingWorkflowProfile.getStartMinutes2(),
			newWorkflowProfile.getStartMinutes2());
		Assert.assertEquals(existingWorkflowProfile.getProcessSid(),
			newWorkflowProfile.getProcessSid());
		Assert.assertEquals(existingWorkflowProfile.getSuccessMailBody(),
			newWorkflowProfile.getSuccessMailBody());
		Assert.assertEquals(existingWorkflowProfile.getInboundStatus(),
			newWorkflowProfile.getInboundStatus());
		Assert.assertEquals(existingWorkflowProfile.getModifiedBy(),
			newWorkflowProfile.getModifiedBy());
		Assert.assertEquals(existingWorkflowProfile.getEmailNotificationSuccessCc(),
			newWorkflowProfile.getEmailNotificationSuccessCc());
		Assert.assertEquals(existingWorkflowProfile.getEmailNotificationFailureTo(),
			newWorkflowProfile.getEmailNotificationFailureTo());
		Assert.assertEquals(existingWorkflowProfile.getFailureMailBody(),
			newWorkflowProfile.getFailureMailBody());
		Assert.assertEquals(existingWorkflowProfile.getActiveFlag(),
			newWorkflowProfile.getActiveFlag());
		Assert.assertEquals(existingWorkflowProfile.getProcessDisplayName(),
			newWorkflowProfile.getProcessDisplayName());
		Assert.assertEquals(existingWorkflowProfile.getStartMinutes(),
			newWorkflowProfile.getStartMinutes());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowProfile.getManualLastRun()),
			Time.getShortTimestamp(newWorkflowProfile.getManualLastRun()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowProfile.getStartDate()),
			Time.getShortTimestamp(newWorkflowProfile.getStartDate()));
		Assert.assertEquals(existingWorkflowProfile.getSlaCalendarMasterSid(),
			newWorkflowProfile.getSlaCalendarMasterSid());
		Assert.assertEquals(existingWorkflowProfile.getSuccessMailSubject(),
			newWorkflowProfile.getSuccessMailSubject());
		Assert.assertEquals(existingWorkflowProfile.getStartHour3(),
			newWorkflowProfile.getStartHour3());
		Assert.assertEquals(existingWorkflowProfile.getStartHour2(),
			newWorkflowProfile.getStartHour2());
		Assert.assertEquals(existingWorkflowProfile.getUserSid(),
			newWorkflowProfile.getUserSid());
		Assert.assertEquals(existingWorkflowProfile.getStartHour1(),
			newWorkflowProfile.getStartHour1());
		Assert.assertEquals(existingWorkflowProfile.getProcessType(),
			newWorkflowProfile.getProcessType());
		Assert.assertEquals(existingWorkflowProfile.getScriptName(),
			newWorkflowProfile.getScriptName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		WorkflowProfile existingWorkflowProfile = _persistence.findByPrimaryKey(newWorkflowProfile.getPrimaryKey());

		Assert.assertEquals(existingWorkflowProfile, newWorkflowProfile);
	}

	@Test(expected = NoSuchWorkflowProfileException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<WorkflowProfile> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("WORKFLOW_PROFILE",
			"startHour", true, "frequency", true, "processName", true,
			"startMinutes1", true, "endDate", true,
			"emailNotificationFailureCc", true, "failureMailSubject", true,
			"modifiedDate", true, "schemaName", true, "createdBy", true,
			"createdDate", true, "scheduleLastRun", true,
			"emailNotificationSuccessTo", true, "startMinutes3", true,
			"startMinutes2", true, "processSid", true, "successMailBody", true,
			"inboundStatus", true, "modifiedBy", true,
			"emailNotificationSuccessCc", true, "emailNotificationFailureTo",
			true, "failureMailBody", true, "activeFlag", true,
			"processDisplayName", true, "startMinutes", true, "manualLastRun",
			true, "startDate", true, "slaCalendarMasterSid", true,
			"successMailSubject", true, "startHour3", true, "startHour2", true,
			"userSid", true, "startHour1", true, "processType", true,
			"scriptName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		WorkflowProfile existingWorkflowProfile = _persistence.fetchByPrimaryKey(newWorkflowProfile.getPrimaryKey());

		Assert.assertEquals(existingWorkflowProfile, newWorkflowProfile);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProfile missingWorkflowProfile = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingWorkflowProfile);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		WorkflowProfile newWorkflowProfile1 = addWorkflowProfile();
		WorkflowProfile newWorkflowProfile2 = addWorkflowProfile();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowProfile1.getPrimaryKey());
		primaryKeys.add(newWorkflowProfile2.getPrimaryKey());

		Map<Serializable, WorkflowProfile> workflowProfiles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, workflowProfiles.size());
		Assert.assertEquals(newWorkflowProfile1,
			workflowProfiles.get(newWorkflowProfile1.getPrimaryKey()));
		Assert.assertEquals(newWorkflowProfile2,
			workflowProfiles.get(newWorkflowProfile2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, WorkflowProfile> workflowProfiles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(workflowProfiles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowProfile.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, WorkflowProfile> workflowProfiles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, workflowProfiles.size());
		Assert.assertEquals(newWorkflowProfile,
			workflowProfiles.get(newWorkflowProfile.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, WorkflowProfile> workflowProfiles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(workflowProfiles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowProfile.getPrimaryKey());

		Map<Serializable, WorkflowProfile> workflowProfiles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, workflowProfiles.size());
		Assert.assertEquals(newWorkflowProfile,
			workflowProfiles.get(newWorkflowProfile.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = WorkflowProfileLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WorkflowProfile>() {
				@Override
				public void performAction(WorkflowProfile workflowProfile) {
					Assert.assertNotNull(workflowProfile);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProfile.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("processSid",
				newWorkflowProfile.getProcessSid()));

		List<WorkflowProfile> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		WorkflowProfile existingWorkflowProfile = result.get(0);

		Assert.assertEquals(existingWorkflowProfile, newWorkflowProfile);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProfile.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("processSid",
				RandomTestUtil.nextInt()));

		List<WorkflowProfile> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		WorkflowProfile newWorkflowProfile = addWorkflowProfile();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProfile.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("processSid"));

		Object newProcessSid = newWorkflowProfile.getProcessSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("processSid",
				new Object[] { newProcessSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProcessSid = result.get(0);

		Assert.assertEquals(existingProcessSid, newProcessSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowProfile.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("processSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("processSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected WorkflowProfile addWorkflowProfile() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowProfile workflowProfile = _persistence.create(pk);

		workflowProfile.setStartHour(RandomTestUtil.nextInt());

		workflowProfile.setFrequency(RandomTestUtil.randomString());

		workflowProfile.setProcessName(RandomTestUtil.randomString());

		workflowProfile.setStartMinutes1(RandomTestUtil.nextInt());

		workflowProfile.setEndDate(RandomTestUtil.nextDate());

		workflowProfile.setEmailNotificationFailureCc(RandomTestUtil.randomString());

		workflowProfile.setFailureMailSubject(RandomTestUtil.randomString());

		workflowProfile.setModifiedDate(RandomTestUtil.nextDate());

		workflowProfile.setSchemaName(RandomTestUtil.randomString());

		workflowProfile.setCreatedBy(RandomTestUtil.nextInt());

		workflowProfile.setCreatedDate(RandomTestUtil.nextDate());

		workflowProfile.setScheduleLastRun(RandomTestUtil.nextDate());

		workflowProfile.setEmailNotificationSuccessTo(RandomTestUtil.randomString());

		workflowProfile.setStartMinutes3(RandomTestUtil.nextInt());

		workflowProfile.setStartMinutes2(RandomTestUtil.nextInt());

		workflowProfile.setSuccessMailBody(RandomTestUtil.randomString());

		workflowProfile.setInboundStatus(RandomTestUtil.randomString());

		workflowProfile.setModifiedBy(RandomTestUtil.nextInt());

		workflowProfile.setEmailNotificationSuccessCc(RandomTestUtil.randomString());

		workflowProfile.setEmailNotificationFailureTo(RandomTestUtil.randomString());

		workflowProfile.setFailureMailBody(RandomTestUtil.randomString());

		workflowProfile.setActiveFlag(RandomTestUtil.randomString());

		workflowProfile.setProcessDisplayName(RandomTestUtil.randomString());

		workflowProfile.setStartMinutes(RandomTestUtil.nextInt());

		workflowProfile.setManualLastRun(RandomTestUtil.nextDate());

		workflowProfile.setStartDate(RandomTestUtil.nextDate());

		workflowProfile.setSlaCalendarMasterSid(RandomTestUtil.nextInt());

		workflowProfile.setSuccessMailSubject(RandomTestUtil.randomString());

		workflowProfile.setStartHour3(RandomTestUtil.nextInt());

		workflowProfile.setStartHour2(RandomTestUtil.nextInt());

		workflowProfile.setUserSid(RandomTestUtil.nextInt());

		workflowProfile.setStartHour1(RandomTestUtil.nextInt());

		workflowProfile.setProcessType(RandomTestUtil.nextInt());

		workflowProfile.setScriptName(RandomTestUtil.randomString());

		_workflowProfiles.add(_persistence.update(workflowProfile));

		return workflowProfile;
	}

	private List<WorkflowProfile> _workflowProfiles = new ArrayList<WorkflowProfile>();
	private WorkflowProfilePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}