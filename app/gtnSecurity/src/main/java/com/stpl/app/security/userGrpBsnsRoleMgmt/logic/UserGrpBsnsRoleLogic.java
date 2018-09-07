package com.stpl.app.security.userGrpBsnsRoleMgmt.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.stpl.app.audit.GtnAuditMessageProducer;
import com.stpl.app.model.BusinessroleMaster;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.stpl.app.security.dao.UserGrpBsnsRoleLogicDAO;
import com.stpl.app.security.dao.impl.UserGrpBsnsRoleLogicDAOImpl;
import com.stpl.app.security.userGrpBsnsRoleMgmt.dto.UserGrpBsnsRoleDTO;
import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.vaadin.v7.data.util.BeanItemContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserGrpBsnsRoleLogic extends BeanItemContainer<BusinessroleMasterDTO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserGrpBsnsRoleLogic.class.getName());
	private final GtnAuditMessageProducer auditMessageProducer = new GtnAuditMessageProducer();

	public UserGrpBsnsRoleLogic() {
		super(BusinessroleMasterDTO.class);

	}

	private final UserGrpBsnsRoleLogicDAO dao = new UserGrpBsnsRoleLogicDAOImpl();

	public UserGrpBsnsRoleDTO getBsnsRoles(UserGrpBsnsRoleDTO usrDto) {
		try {
			List<String> selList = new ArrayList<>();
			List brmIdList = new ArrayList();
			String userGroup = usrDto.getSelUserGroupId();
			Long userGroupId = usrDto.getUserGroupMap().get(userGroup);
			DynamicQuery ugbrmDynamicQuery = UsergroupBusinessroleLocalServiceUtil.dynamicQuery();
			ugbrmDynamicQuery.add(RestrictionsFactoryUtil.like(CommonUtils.USERGROUP_ID,
					Integer.valueOf(String.valueOf(userGroupId))));

			List<UsergroupBusinessrole> ugbrm = dao.getBsnsRoles(ugbrmDynamicQuery);
			Iterator<UsergroupBusinessrole> iterate1 = ugbrm.iterator();
			while (iterate1.hasNext()) {
				UsergroupBusinessrole ugbrmObj = (UsergroupBusinessrole) iterate1.next();
				brmIdList.add(ugbrmObj.getBusinessroleMasterSid());
			}
			if (ugbrm.size() > 0) {
				DynamicQuery brmDynamicQuery = BusinessroleMasterLocalServiceUtil.dynamicQuery();
				brmDynamicQuery.add(RestrictionsFactoryUtil.in(CommonUtils.BUSINESS_ROLE_MASTERSID, brmIdList));
				List<BusinessroleMaster> brm = dao.getBusinessroleMaster(brmDynamicQuery);
				Iterator<BusinessroleMaster> iterate2 = brm.iterator();
				while (iterate2.hasNext()) {
					BusinessroleMaster brmObj = (BusinessroleMaster) iterate2.next();
					selList.add(brmObj.getBusinessroleName());
				}

			}
			usrDto.setSelectedBusinessRole(selList);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return usrDto;
	}

	public UserGrpBsnsRoleDTO getUserGroups() {
		UserGrpBsnsRoleDTO userDto = new UserGrpBsnsRoleDTO();
		try {
			List<String> usrGrpList = new ArrayList<>();
			DynamicQuery ugDynamicQuery = UserGroupLocalServiceUtil.dynamicQuery();
			// Removed this where condition since there is no groupType column in UserGroup
			// Model
			List<UserGroup> usrGrp = dao.getUserGroups(ugDynamicQuery);
			HashMap<String, Long> map = new HashMap<>();
			Iterator<UserGroup> iterate = usrGrp.iterator();
			while (iterate.hasNext()) {
				UserGroup userGroup = (UserGroup) iterate.next();
				map.put(userGroup.getName(), userGroup.getUserGroupId());
			}
			userDto.setUserGroupMap(map);
			usrGrpList.addAll(map.keySet());
			userDto.setUserGroup(usrGrpList);

			List<String> brmList = new ArrayList<>();
			int count2 = dao.count();
			HashMap<String, Integer> map2 = new HashMap<>();
			List<BusinessroleMaster> businessRoleMaster = dao.getBusinessroleMasters(0, count2);
			Iterator<BusinessroleMaster> iterate1 = businessRoleMaster.iterator();
			while (iterate1.hasNext()) {
				BusinessroleMaster brm = (BusinessroleMaster) iterate1.next();
				map2.put(brm.getBusinessroleName(), brm.getBusinessroleMasterSid());
			}
			userDto.setBusinessRoleMap(map2);
			brmList.addAll(map2.keySet());
			userDto.setBusinessRole(brmList);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return userDto;
	}

	public boolean saveLogic(UserGrpBsnsRoleDTO usrDto) {
		try {
			Long selUsrGrpId1 = usrDto.getUserGroupMap().get(usrDto.getSelUserGroupId());
			int selUsrGrpId = Integer.parseInt(String.valueOf(selUsrGrpId1));
			List<String> list = usrDto.getSelectedBusinessRole();
			HashMap<String, Integer> brMap = usrDto.getBusinessRoleMap();
			List idList = new ArrayList<Integer>();

			Iterator<String> iter = list.iterator();
			while (iter.hasNext()) {
				String string =  iter.next();
				idList.add(brMap.get(string));
			}
			DynamicQuery deleteDynamicQuery = UsergroupBusinessroleLocalServiceUtil.dynamicQuery();
			deleteDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.USERGROUP_ID, selUsrGrpId));
			Criterion cri = RestrictionsFactoryUtil.in(CommonUtils.BUSINESS_ROLE_MASTERSID, idList);
			deleteDynamicQuery.add(RestrictionsFactoryUtil.not(cri));
			List<UsergroupBusinessrole> deleteUG = dao.getBsnsRoles(deleteDynamicQuery);
			for (Iterator iterator = deleteUG.iterator(); iterator.hasNext();) {
				UsergroupBusinessrole usergroupBusinessroleMaster = (UsergroupBusinessrole) iterator.next();
				auditMessageProducer.logAuditMessage(PortalUtil.getDefaultCompanyId(), usergroupBusinessroleMaster+" Removed ", this.getClass().getName());
				dao.deleteUsergroupBusinessroleMaster(usergroupBusinessroleMaster);
			}

			UsergroupBusinessrole usrBrMaster;
			Date date = new Date();
			for (Iterator iterator = idList.iterator(); iterator.hasNext();) {
				int brId = (Integer) iterator.next();
				DynamicQuery checkDynamicQuery = UsergroupBusinessroleLocalServiceUtil.dynamicQuery();
				checkDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.USERGROUP_ID, selUsrGrpId));
				checkDynamicQuery.add(RestrictionsFactoryUtil.eq(CommonUtils.BUSINESS_ROLE_MASTERSID, brId));
				List<UsergroupBusinessrole> checkUG = dao.getBsnsRoles(checkDynamicQuery);
				if (checkUG.size() > 0) {
					usrBrMaster = checkUG.get(0);
					usrBrMaster.setModifiedDate(date);
					usrBrMaster.setCreatedBy(0);
					usrBrMaster.setModifiedBy(0);
					dao.updateUserGroup(usrBrMaster);
				} else {
					LOGGER.debug("else-----");
					usrBrMaster = UsergroupBusinessroleLocalServiceUtil.createUsergroupBusinessrole(0);
					usrBrMaster.setUsergroupId(selUsrGrpId);
					usrBrMaster.setBusinessroleMasterSid(brId);
					usrBrMaster.setCreatedDate(date);
					usrBrMaster.setModifiedDate(date);
					usrBrMaster.setCreatedBy(0);
					usrBrMaster.setModifiedBy(0);
					auditMessageProducer.logAuditMessage(PortalUtil.getDefaultCompanyId(), usrBrMaster+" added ", this.getClass().getName());
					dao.saveUserGroup(usrBrMaster);

				}

			}
			return true;

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return false;
		}
	}
}
