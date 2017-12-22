package com.stpl.app.cff.bpm.service;

import com.stpl.app.cff.util.StringConstantsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.internal.task.api.UserGroupCallback;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author arulmurugan
 */
public class CustomUserCallBack implements UserGroupCallback {

    private Map<String, User> userMap = new HashMap<>();
    private Map<String, Role> roleMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(CustomUserCallBack.class);

    public CustomUserCallBack() {
        try {
            List<User> usersList = UserLocalServiceUtil.getUsers(0, UserLocalServiceUtil.getUsersCount());
            List<Role> groupList = RoleLocalServiceUtil.getRoles(0, RoleLocalServiceUtil.getRolesCount());
            for (User user : usersList) {
                userMap.put(user.getScreenName(), user);
            }
            for (Role role : groupList) {
                roleMap.put(role.getName(), role);
            }
            if (userMap.containsKey(StringConstantsUtil.ADMINISTRATOR)) {
                userMap.remove(StringConstantsUtil.ADMINISTRATOR);
            }
            if (roleMap.containsKey(StringConstantsUtil.ADMINISTRATOR)) {
                roleMap.remove(StringConstantsUtil.ADMINISTRATOR);
            }
        } catch (SystemException e) {

            LOGGER.error(e);
        }
    }

    public boolean existsUser(String userId) {
        return userMap.containsKey(userId) || userId.equals(StringConstantsUtil.ADMINISTRATOR);
    }

    public boolean existsGroup(String groupId) {
        if (groupId.contains(",")) {
            String[] groups = groupId.split(",");
            if (groups != null) {
                for (int i = 0; i < groups.length; i++) {
                    if (roleMap.containsKey(groups[i].trim())) {
                        return true;
                    }

                }
            }
        }
        return roleMap.containsKey(groupId);
    }

    public List<String> getGroupsForUser(String userId, List<String> roleIds, List<String> allExistingGroupIds) {
        List<String> userRoles = new ArrayList<>();
        List<Role> roles = null;
        if (userMap.containsKey(userId)) {
            try {
                roles = RoleLocalServiceUtil.getUserRoles(userMap.get(userId).getUserId());
            } catch (SystemException e) {
                LOGGER.error(e);
            }
            for (Role role : roles) {
                if (!StringConstantsUtil.ADMINISTRATOR.equals(role.getName())) {
                    userRoles.add(role.getName());
                }
            }
        }
        return userRoles;
    }
}
